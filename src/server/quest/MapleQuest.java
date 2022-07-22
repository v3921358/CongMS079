package server.quest;

import client.inventory.MapleInventoryType;
import client.MapleQuestStatus;
import tools.MaplePacketCreator;
import client.inventory.MaplePet;
import server.MapleInventoryManipulator;
import scripting.NPCScriptManager;
import client.MapleCharacter;
import java.util.Collection;
import java.sql.Connection;
import tools.FileoutputUtil;
import database.DBConPool;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import tools.Pair;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

public class MapleQuest implements Serializable
{
    private static final long serialVersionUID = 9179541993413738569L;
    private static final Map<Integer, MapleQuest> quests;
    protected int id;
    protected final List<MapleQuestRequirement> startReqs;
    protected final List<MapleQuestRequirement> completeReqs;
    protected final List<MapleQuestAction> startActs;
    protected final List<MapleQuestAction> completeActs;
    protected final Map<String, List<Pair<String, Pair<String, Integer>>>> partyQuestInfo;
    protected final Map<Integer, Integer> relevantMobs;
    private boolean autoStart;
    private boolean autoPreComplete;
    private boolean repeatable;
    private boolean customend;
    private boolean blocked;
    private boolean autoAccept;
    private boolean autoComplete;
    private boolean scriptedStart;
    private int viewMedalItem;
    private int selectedSkillID;
    protected String name;
    
    protected MapleQuest(final int id) {
        this.startReqs = new LinkedList<MapleQuestRequirement>();
        this.completeReqs = new LinkedList<MapleQuestRequirement>();
        this.startActs = new LinkedList<MapleQuestAction>();
        this.completeActs = new LinkedList<MapleQuestAction>();
        this.partyQuestInfo = new LinkedHashMap<String, List<Pair<String, Pair<String, Integer>>>>();
        this.relevantMobs = new LinkedHashMap<Integer, Integer>();
        this.autoStart = false;
        this.autoPreComplete = false;
        this.repeatable = false;
        this.customend = false;
        this.blocked = false;
        this.autoAccept = false;
        this.autoComplete = false;
        this.scriptedStart = false;
        this.viewMedalItem = 0;
        this.selectedSkillID = 0;
        this.name = "";
        this.id = id;
    }
    
    private static MapleQuest loadQuest(final ResultSet rs, final PreparedStatement psr, final PreparedStatement psa, final PreparedStatement pss, final PreparedStatement psq, final PreparedStatement psi, final PreparedStatement psp) throws SQLException {
        final MapleQuest ret = new MapleQuest(rs.getInt("questid"));
        ret.name = rs.getString("name");
        ret.autoStart = (rs.getInt("autoStart") > 0);
        ret.autoPreComplete = (rs.getInt("autoPreComplete") > 0);
        ret.autoAccept = (rs.getInt("autoAccept") > 0);
        ret.autoComplete = (rs.getInt("autoComplete") > 0);
        ret.viewMedalItem = rs.getInt("viewMedalItem");
        ret.selectedSkillID = rs.getInt("selectedSkillID");
        ret.blocked = (rs.getInt("blocked") > 0);
        psr.setInt(1, ret.id);
        ResultSet rse = psr.executeQuery();
        while (rse.next()) {
            final MapleQuestRequirementType type = MapleQuestRequirementType.getByWZName(rse.getString("name"));
            final MapleQuestRequirement req = new MapleQuestRequirement(ret, type, rse);
            if (type.equals((Object)MapleQuestRequirementType.interval)) {
                ret.repeatable = true;
            }
            else if (type.equals((Object)MapleQuestRequirementType.normalAutoStart)) {
                ret.repeatable = true;
                ret.autoStart = true;
            }
            else if (type.equals((Object)MapleQuestRequirementType.startscript)) {
                ret.scriptedStart = true;
            }
            else if (type.equals((Object)MapleQuestRequirementType.endscript)) {
                ret.customend = true;
            }
            else if (type.equals((Object)MapleQuestRequirementType.mob)) {
                for (final Pair<Integer, Integer> mob : req.getDataStore()) {
                    ret.relevantMobs.put(mob.left, mob.right);
                }
            }
            if (rse.getInt("type") == 0) {
                ret.startReqs.add(req);
            }
            else {
                ret.completeReqs.add(req);
            }
        }
        rse.close();
        psa.setInt(1, ret.id);
        rse = psa.executeQuery();
        while (rse.next()) {
            final MapleQuestActionType ty = MapleQuestActionType.getByWZName(rse.getString("name"));
            if (rse.getInt("type") == 0) {
                if (ty == MapleQuestActionType.item && ret.id == 7103) {
                    continue;
                }
                ret.startActs.add(new MapleQuestAction(ty, rse, ret, pss, psq, psi));
            }
            else {
                if (ty == MapleQuestActionType.item && ret.id == 7102) {
                    continue;
                }
                ret.completeActs.add(new MapleQuestAction(ty, rse, ret, pss, psq, psi));
            }
        }
        rse.close();
        psp.setInt(1, ret.id);
        rse = psp.executeQuery();
        while (rse.next()) {
            if (!ret.partyQuestInfo.containsKey((Object)rse.getString("rank"))) {
                ret.partyQuestInfo.put(rse.getString("rank"), new ArrayList<Pair<String, Pair<String, Integer>>>());
            }
            ((List<Pair<String, Pair<String, Integer>>>)ret.partyQuestInfo.get((Object)rse.getString("rank"))).add(new Pair<String, Pair<String, Integer>>(rse.getString("mode"), new Pair<String, Integer>(rse.getString("property"), Integer.valueOf(rse.getInt("value")))));
        }
        rse.close();
        return ret;
    }
    
    public List<Pair<String, Pair<String, Integer>>> getInfoByRank(final String rank) {
        return (List<Pair<String, Pair<String, Integer>>>)this.partyQuestInfo.get((Object)rank);
    }
    
    public final int getSkillID() {
        return this.selectedSkillID;
    }
    
    public final String getName() {
        return this.name;
    }
    
    public static void initQuests() {
        System.out.println("[正在加载] -> 游戏任务系统");
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM wz_questdata");
            final PreparedStatement psr = con.prepareStatement("SELECT * FROM wz_questreqdata WHERE questid = ?");
            final PreparedStatement psa = con.prepareStatement("SELECT * FROM wz_questactdata WHERE questid = ?");
            final PreparedStatement pss = con.prepareStatement("SELECT * FROM wz_questactskilldata WHERE uniqueid = ?");
            final PreparedStatement psq = con.prepareStatement("SELECT * FROM wz_questactquestdata WHERE uniqueid = ?");
            final PreparedStatement psi = con.prepareStatement("SELECT * FROM wz_questactitemdata WHERE uniqueid = ?");
            final PreparedStatement psp = con.prepareStatement("SELECT * FROM wz_questpartydata WHERE questid = ?");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MapleQuest.quests.put(Integer.valueOf(rs.getInt("questid")), loadQuest(rs, psr, psa, pss, psq, psi, psp));
            }
            rs.close();
            ps.close();
            psr.close();
            psa.close();
            pss.close();
            psq.close();
            psi.close();
            psp.close();
        }
        catch (SQLException e) {
            FileoutputUtil.outputFileError("logs/資料庫異常.txt", (Throwable)e);
            e.printStackTrace();
        }
    }
    
    public static void clearQuests() {
        MapleQuest.quests.clear();
        initQuests();
    }
    
    public static Collection<MapleQuest> getAllInstances() {
        return MapleQuest.quests.values();
    }
    
    public static MapleQuest getInstance(final int id) {
        MapleQuest ret = (MapleQuest)MapleQuest.quests.get((Object)Integer.valueOf(id));
        if (ret == null) {
            ret = new MapleQuest(id);
            MapleQuest.quests.put(Integer.valueOf(id), ret);
        }
        return ret;
    }
    
    public boolean canStart(final MapleCharacter c, final Integer npcid) {
        if (c.getQuest(this).getStatus() != 0 && (c.getQuest(this).getStatus() != 2 || !this.repeatable)) {
            return false;
        }
        if (this.blocked && !c.isGM()) {
            return false;
        }
        for (final MapleQuestRequirement r : this.startReqs) {
            if (r.getType() == MapleQuestRequirementType.dayByDay && npcid != null) {
                this.forceComplete(c, (int)npcid);
                return false;
            }
            if (!r.check(c, npcid)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean canComplete(final MapleCharacter c, final Integer npcid) {
        if (c.getQuest(this).getStatus() != 1) {
            return false;
        }
        if (this.blocked && !c.isGM()) {
            return false;
        }
        if (this.autoComplete && npcid != null && this.viewMedalItem <= 0) {
            this.forceComplete(c, (int)npcid);
            return false;
        }
        for (final MapleQuestRequirement r : this.completeReqs) {
            if (!r.check(c, npcid)) {
                return false;
            }
        }
        return true;
    }
    
    public final void RestoreLostItem(final MapleCharacter c, final int itemid) {
        if (this.blocked && !c.isGM()) {
            return;
        }
        for (final MapleQuestAction a : this.startActs) {
            if (a.RestoreLostItem(c, itemid)) {
                break;
            }
        }
    }
    
    public void start(final MapleCharacter c, final int npc) {
        if ((this.autoStart || this.checkNPCOnMap(c, npc)) && this.canStart(c, Integer.valueOf(npc))) {
            for (final MapleQuestAction a : this.startActs) {
                if (!a.checkEnd(c, null)) {
                    return;
                }
            }
            for (final MapleQuestAction a : this.startActs) {
                a.runStart(c, null);
            }
            if (!this.customend) {
                this.forceStart(c, npc, null);
            }
            else {
                NPCScriptManager.getInstance().endQuest(c.getClient(), npc, this.getId(), true);
            }
        }
        else if (this.autoStart && this.canStart(c, Integer.valueOf(npc))) {
            for (final MapleQuestAction a : this.startActs) {
                if (!a.checkEnd(c, null)) {
                    return;
                }
            }
            for (final MapleQuestAction a : this.startActs) {
                a.runStart(c, null);
            }
            if (!this.customend) {
                this.forceStart(c, npc, null);
            }
            else {
                NPCScriptManager.getInstance().endQuest(c.getClient(), npc, this.getId(), true);
            }
        }
        else if (this.id == 8249) {
            for (final MapleQuestAction a : this.startActs) {
                if (!a.checkEnd(c, null)) {
                    return;
                }
            }
            for (final MapleQuestAction a : this.startActs) {
                a.runStart(c, null);
            }
            if (!this.customend) {
                this.forceStart(c, npc, null);
            }
            else {
                NPCScriptManager.getInstance().endQuest(c.getClient(), npc, this.getId(), true);
            }
        }
    }
    
    public void complete(final MapleCharacter c, final int npc) {
        this.complete(c, npc, null);
    }
    
    public void complete(final MapleCharacter c, final int npc, final Integer selection) {
        if (!this.canHold(c)) {
            c.dropMessage(1, "您的背包空間不足，無法完成任務。");
            return;
        }
        if (this.id == 2337) {
            if (!this.canHold(c)) {
                c.dropMessage(1, "您的背包空間不足，無法完成任務。");
                return;
            }
            if (!c.haveItem(1142188, 1, false, true)) {
                MapleInventoryManipulator.addById(c.getClient(), 1142188, (short)1, "", null, 0L);
            }
        }
        if ((this.autoPreComplete || this.checkNPCOnMap(c, npc)) && this.canComplete(c, Integer.valueOf(npc))) {
            for (final MapleQuestAction a : this.completeActs) {
                if (!a.checkEnd(c, selection)) {
                    return;
                }
            }
            this.forceComplete(c, npc);
            for (final MapleQuestAction a : this.completeActs) {
                a.runEnd(c, selection, this.id);
            }
            c.getClient().sendPacket(MaplePacketCreator.showSpecialEffect(10));
            c.getMap().broadcastMessage(c, MaplePacketCreator.showSpecialEffect(c.getId(), 10), false);
        }
        else if (this.id == 29507) {
            for (final MapleQuestAction a : this.completeActs) {
                if (!a.checkEnd(c, selection)) {
                    return;
                }
            }
            this.forceComplete(c, npc);
            for (final MapleQuestAction a : this.completeActs) {
                a.runEnd(c, selection, this.id);
            }
            c.getClient().sendPacket(MaplePacketCreator.showSpecialEffect(10));
            c.getMap().broadcastMessage(c, MaplePacketCreator.showSpecialEffect(c.getId(), 10), false);
        }
    }
    
    public void forfeit(final MapleCharacter c) {
        if (c.getQuest(this).getStatus() != 1) {
            return;
        }
        final MapleQuestStatus oldStatus = c.getQuest(this);
        final MapleQuestStatus newStatus = new MapleQuestStatus(this, 0);
        newStatus.setForfeited(oldStatus.getForfeited() + 1);
        newStatus.setCompletionTime(oldStatus.getCompletionTime());
        c.updateQuest(newStatus);
    }
    
    public void forceStart(final MapleCharacter c, final int npc, final String customData) {
        final MapleQuestStatus newStatus = new MapleQuestStatus(this, (byte)1, npc);
        newStatus.setForfeited(c.getQuest(this).getForfeited());
        newStatus.setCompletionTime(c.getQuest(this).getCompletionTime());
        newStatus.setCustomData(customData);
        c.updateQuest(newStatus);
    }
    
    public void forceComplete(final MapleCharacter c, final int npc) {
        final MapleQuestStatus newStatus = new MapleQuestStatus(this, (byte)2, npc);
        newStatus.setForfeited(c.getQuest(this).getForfeited());
        c.updateQuest(newStatus);
    }
    
    public int getId() {
        return this.id;
    }
    
    public Map<Integer, Integer> getRelevantMobs() {
        return this.relevantMobs;
    }
    
    private boolean checkNPCOnMap(final MapleCharacter player, final int npcid) {
        return npcid == 1013000 || (player.getMap() != null && player.getMap().containsNPC(npcid)) || !player.getMap().containsNPC(npcid);
    }
    
    public int getMedalItem() {
        return this.viewMedalItem;
    }
    
    public boolean hasStartScript() {
        return this.scriptedStart;
    }
    
    public final boolean canHold(final MapleCharacter chr) {
        for (int i = 1; i <= 5; ++i) {
            if (chr.getInventory(MapleInventoryType.getByType((byte)i)).getNextFreeSlot() <= -1) {
                return false;
            }
        }
        return true;
    }
    
    static {
        quests = new LinkedHashMap<Integer, MapleQuest>();
    }
    
    public enum MedalQuest
    {
        新手冒險家(29005, 29015, 15, new int[] { 104000000, 104010001, 100000006, 104020000, 100000000, 100010000, 100040000, 100040100, 101010103, 101020000, 101000000, 102000000, 101030104, 101030406, 102020300, 103000000, 102050000, 103010001, 103030200, 110000000 }), 
        冰原雪域山脈探險家(29006, 29012, 50, new int[] { 200000000, 200010100, 200010300, 200080000, 200080100, 211000000, 211030000, 211040300, 211041200, 211041800 }), 
        路德斯湖探險家(29007, 29012, 40, new int[] { 222000000, 222010400, 222020000, 220000000, 220020300, 220040200, 221020701, 221000000, 221030600, 221040400 }), 
        海底探險家(29008, 29012, 40, new int[] { 230000000, 230010400, 230010200, 230010201, 230020000, 230020201, 230030100, 230040000, 230040200, 230040400 }), 
        武陵桃園探險家(29009, 29012, 50, new int[] { 251000000, 251010200, 251010402, 251010500, 250010500, 250010504, 250000000, 250010300, 250010304, 250020300 }), 
        納希沙漠探險家(29010, 29012, 70, new int[] { 261030000, 261020401, 261020000, 261010100, 261000000, 260020700, 260020300, 260000000, 260010600, 260010300 }), 
        米納爾森林探險家(29011, 29012, 70, new int[] { 240000000, 240010200, 240010800, 240020401, 240020101, 240030000, 240040400, 240040511, 240040521, 240050000 }), 
        奇幻村探險家(29014, 29015, 50, new int[] { 105040300, 105070001, 105040305, 105090200, 105090300, 105090301, 105090312, 105090500, 105090900, 105080000 });
        
        public int questid;
        public int level;
        public int lquestid;
        public int[] maps;
        
        private MedalQuest(final int questid, final int lquestid, final int level, final int[] maps) {
            this.questid = questid;
            this.level = level;
            this.lquestid = lquestid;
            this.maps = maps;
        }
    }
}
