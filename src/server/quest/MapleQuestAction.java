package server.quest;

import tools.FileoutputUtil;
import handling.world.World.Broadcast;
import client.inventory.MapleInventoryType;
import client.ISkill;
import java.util.Map;
import client.MapleStat;
import client.SkillFactory;
import client.MapleQuestStatus;
import client.inventory.MaplePet;
import server.MapleItemInformationProvider;
import tools.MaplePacketCreator;
import client.inventory.InventoryException;
import server.Randomizer;
import java.util.HashMap;
import constants.GameConstants;
import server.MapleInventoryManipulator;
import java.util.Iterator;
import client.MapleCharacter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import tools.Pair;
import tools.Triple;
import java.util.List;
import java.io.Serializable;

public class MapleQuestAction implements Serializable
{
    private static final long serialVersionUID = 9179541993413738569L;
    private MapleQuestActionType type;
    private MapleQuest quest;
    private int intStore;
    private List<Integer> applicableJobs;
    private List<QuestItem> items;
    private List<Triple<Integer, Integer, Integer>> skill;
    private List<Pair<Integer, Integer>> state;
    
    public MapleQuestAction(final MapleQuestActionType type, final ResultSet rse, final MapleQuest quest, final PreparedStatement pss, final PreparedStatement psq, final PreparedStatement psi) throws SQLException {
        this.intStore = 0;
        this.applicableJobs = new ArrayList<Integer>();
        this.items = null;
        this.skill = null;
        this.state = null;
        this.type = type;
        this.quest = quest;
        this.intStore = rse.getInt("intStore");
        final String[] jobs = rse.getString("applicableJobs").split(", ");
        if (jobs.length <= 0 && rse.getString("applicableJobs").length() > 0) {
            this.applicableJobs.add(Integer.valueOf(Integer.parseInt(rse.getString("applicableJobs"))));
        }
        for (final String j : jobs) {
            if (j.length() > 0) {
                this.applicableJobs.add(Integer.valueOf(Integer.parseInt(j)));
            }
        }
        switch (type) {
            case item: {
                this.items = new ArrayList<QuestItem>();
                psi.setInt(1, rse.getInt("uniqueid"));
                final ResultSet rs = psi.executeQuery();
                while (rs.next()) {
                    this.items.add(new QuestItem(rs.getInt("itemid"), rs.getInt("count"), rs.getInt("period"), rs.getInt("gender"), rs.getInt("job"), rs.getInt("jobEx"), rs.getInt("prop")));
                }
                rs.close();
                break;
            }
            case quest: {
                this.state = new ArrayList<Pair<Integer, Integer>>();
                psq.setInt(1, rse.getInt("uniqueid"));
                final ResultSet rs = psq.executeQuery();
                while (rs.next()) {
                    this.state.add(new Pair<Integer, Integer>(Integer.valueOf(rs.getInt("quest")), Integer.valueOf(rs.getInt("state"))));
                }
                rs.close();
                break;
            }
            case skill: {
                this.skill = new ArrayList<Triple<Integer, Integer, Integer>>();
                pss.setInt(1, rse.getInt("uniqueid"));
                final ResultSet rs = pss.executeQuery();
                while (rs.next()) {
                    this.skill.add(new Triple<Integer, Integer, Integer>(Integer.valueOf(rs.getInt("skillid")), Integer.valueOf(rs.getInt("skillLevel")), Integer.valueOf(rs.getInt("masterLevel"))));
                }
                rs.close();
                break;
            }
        }
    }
    
    private static boolean canGetItem(final QuestItem item, final MapleCharacter c) {
        if (item.gender != 2 && item.gender >= 0 && item.gender != c.getGender()) {
            return false;
        }
        if (item.job > 0) {
            final List<Integer> code = getJobBy5ByteEncoding(item.job);
            boolean jobFound = false;
            final Iterator<Integer> iterator = code.iterator();
            while (iterator.hasNext()) {
                final int codec = (int)Integer.valueOf(iterator.next());
                if (codec / 100 == c.getJob() / 100) {
                    jobFound = true;
                    break;
                }
            }
            if (!jobFound && item.jobEx > 0) {
                final List<Integer> codeEx = getJobBySimpleEncoding(item.jobEx);
                final Iterator<Integer> iterator2 = codeEx.iterator();
                while (iterator2.hasNext()) {
                    final int codec2 = (int)Integer.valueOf(iterator2.next());
                    if (codec2 / 100 % 10 == c.getJob() / 100 % 10) {
                        jobFound = true;
                        break;
                    }
                }
            }
            return jobFound;
        }
        return true;
    }
    
    public final boolean RestoreLostItem(final MapleCharacter c, final int itemid) {
        if (this.type == MapleQuestActionType.item) {
            for (final QuestItem item : this.items) {
                if (item.itemid == itemid) {
                    if (!c.haveItem(item.itemid, item.count, true, false)) {
                        MapleInventoryManipulator.addById(c.getClient(), item.itemid, (short)item.count);
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    public void runStart(final MapleCharacter c, final Integer extSelection) {
        QuestItem item;
        switch (this.type) {
            case exp: {
                final MapleQuestStatus status = c.getQuest(this.quest);
                if (status.getForfeited() > 0) {
                    break;
                }
                c.gainExp(this.intStore * GameConstants.getExpRate_Quest((int)c.getLevel()), true, true, true);
                break;
            }
            case item: {
                final Map<Integer, Integer> props = new HashMap<Integer, Integer>();
                final Iterator<QuestItem> iterator = this.items.iterator();
                while (iterator.hasNext()) {
                    item = (QuestItem)iterator.next();
                    if (item.prop > 0 && canGetItem(item, c)) {
                        for (int i = 0; i < item.prop; ++i) {
                            props.put(Integer.valueOf(props.size()), Integer.valueOf(item.itemid));
                        }
                    }
                }
                int selection = 0;
                int extNum = 0;
                if (props.size() > 0) {
                    selection = (int)Integer.valueOf(props.get((Object)Integer.valueOf(Randomizer.nextInt(props.size()))));
                }
                for (final QuestItem item2 : this.items) {
                    if (!canGetItem(item2, c)) {
                        continue;
                    }
                    final int id = item2.itemid;
                    if (item2.prop != -2) {
                        if (item2.prop == -1) {
                            if (extSelection != null && (int)extSelection != extNum++) {
                                continue;
                            }
                        }
                        else if (id != selection) {
                            continue;
                        }
                    }
                    final short count = (short)item2.count;
                    if (count < 0) {
                        try {
                            MapleInventoryManipulator.removeById(c.getClient(), GameConstants.getInventoryType(id), id, count * -1, true, false);
                        }
                        catch (InventoryException ie) {
                            System.err.println("[h4x] Completing a quest without meeting the requirements" + (Object)ie);
                        }
                        c.getClient().getSession().writeAndFlush((Object)MaplePacketCreator.getShowItemGain(id, count, true));
                    }
                    else {
                        final int period = item2.period / 1440;
                        final String name = MapleItemInformationProvider.getInstance().getName(id);
                        if (id / 10000 == 114 && name != null && name.length() > 0) {
                            final String msg = "你已获得稱號 <" + name + ">";
                            c.dropMessage(-1, msg);
                            c.dropMessage(5, msg);
                        }
                        MapleInventoryManipulator.addById(c.getClient(), id, count, "", null, (long)period);
                        c.getClient().getSession().writeAndFlush((Object)MaplePacketCreator.getShowItemGain(id, count, true));
                    }
                }
                break;
            }
            case nextQuest: {
                final MapleQuestStatus status = c.getQuest(this.quest);
                if (status.getForfeited() > 0) {
                    break;
                }
                c.getClient().getSession().writeAndFlush((Object)MaplePacketCreator.updateQuestFinish(this.quest.getId(), status.getNpc(), this.intStore));
                break;
            }
            case money: {
                final MapleQuestStatus status = c.getQuest(this.quest);
                if (status.getForfeited() > 0) {
                    break;
                }
                c.gainMeso(this.intStore, true, true);
                break;
            }
            case quest: {
                for (final Pair<Integer, Integer> q : this.state) {
                    c.updateQuest(new MapleQuestStatus(MapleQuest.getInstance((int)Integer.valueOf(q.left)), (int)Integer.valueOf(q.right)));
                }
                break;
            }
            case skill: {
                for (final Triple<Integer, Integer, Integer> skills : this.skill) {
                    final int skillid = (int)Integer.valueOf(skills.left);
                    final int skillLevel = (int)Integer.valueOf(skills.mid);
                    final int masterLevel = (int)Integer.valueOf(skills.right);
                    final ISkill skillObject = SkillFactory.getSkill(skillid);
                    boolean found = false;
                    final Iterator<Integer> iterator5 = this.applicableJobs.iterator();
                    while (iterator5.hasNext()) {
                        final int applicableJob = (int)Integer.valueOf(iterator5.next());
                        if (c.getJob() == applicableJob) {
                            found = true;
                            break;
                        }
                    }
                    if (skillObject.isBeginnerSkill() || found) {
                        c.changeSkillLevel(skillObject, (byte)Math.max(skillLevel, (int)c.getSkillLevel(skillObject)), (byte)Math.max(masterLevel, (int)c.getMasterLevel(skillObject)));
                    }
                }
                break;
            }
            case pop: {
                final MapleQuestStatus status = c.getQuest(this.quest);
                if (status.getForfeited() > 0) {
                    break;
                }
                final int fameGain = this.intStore;
                c.addFame(fameGain);
                c.updateSingleStat(MapleStat.FAME, (int)c.getFame());
                c.getClient().getSession().writeAndFlush((Object)MaplePacketCreator.getShowFameGain(fameGain));
                break;
            }
            case buffItemID: {
                final MapleQuestStatus status = c.getQuest(this.quest);
                if (status.getForfeited() > 0) {
                    break;
                }
                final int tobuff = this.intStore;
                if (tobuff <= 0) {
                    break;
                }
                MapleItemInformationProvider.getInstance().getItemEffect(tobuff).applyTo(c);
                break;
            }
            case sp: {
                final MapleQuestStatus status = c.getQuest(this.quest);
                if (status.getForfeited() > 0) {
                    break;
                }
                final int sp_val = this.intStore;
                if (this.applicableJobs.size() > 0) {
                    int finalJob = 0;
                    final Iterator<Integer> iterator6 = this.applicableJobs.iterator();
                    while (iterator6.hasNext()) {
                        final int job_val = (int)Integer.valueOf(iterator6.next());
                        if (c.getJob() >= job_val && job_val > finalJob) {
                            finalJob = job_val;
                        }
                    }
                    if (finalJob == 0) {
                        c.gainSP(sp_val);
                    }
                    else {
                        c.gainSP(sp_val, GameConstants.getSkillBook(finalJob));
                    }
                    break;
                }
                c.gainSP(sp_val);
                break;
            }
        }
    }
    
    public boolean checkEnd(final MapleCharacter c, final Integer extSelection) {
        QuestItem item;
        switch (this.type) {
            case item: {
                final Map<Integer, Integer> props = new HashMap<Integer, Integer>();
                final Iterator<QuestItem> iterator = this.items.iterator();
                while (iterator.hasNext()) {
                    item = (QuestItem)iterator.next();
                    if (item.prop > 0 && canGetItem(item, c)) {
                        for (int i = 0; i < item.prop; ++i) {
                            props.put(Integer.valueOf(props.size()), Integer.valueOf(item.itemid));
                        }
                    }
                }
                int selection = 0;
                int extNum = 0;
                if (props.size() > 0) {
                    selection = (int)Integer.valueOf(props.get((Object)Integer.valueOf(Randomizer.nextInt(props.size()))));
                }
                byte eq = 0;
                byte use = 0;
                byte setup = 0;
                byte etc = 0;
                byte cash = 0;
                for (final QuestItem item2 : this.items) {
                    if (!canGetItem(item2, c)) {
                        continue;
                    }
                    final int id = item2.itemid;
                    if (item2.prop != -2) {
                        if (item2.prop == -1) {
                            if (extSelection != null && (int)extSelection != extNum++) {
                                continue;
                            }
                        }
                        else if (id != selection) {
                            continue;
                        }
                    }
                    final short count = (short)item2.count;
                    if (count < 0) {
                        if (!c.haveItem(id, (int)count, false, true)) {
                            c.dropMessage(1, "你的任務道具不夠，還不能完成任務。");
                            return false;
                        }
                        continue;
                    }
                    else {
                        if (MapleItemInformationProvider.getInstance().isPickupRestricted(id) && c.haveItem(id, 1, true, false)) {
                            c.dropMessage(1, "You have this item already: " + MapleItemInformationProvider.getInstance().getName(id));
                            return false;
                        }
                        switch (GameConstants.getInventoryType(id)) {
                            case EQUIP: {
                                ++eq;
                                continue;
                            }
                            case USE: {
                                ++use;
                                continue;
                            }
                            case SETUP: {
                                ++setup;
                                continue;
                            }
                            case ETC: {
                                ++etc;
                                continue;
                            }
                            case CASH: {
                                ++cash;
                                continue;
                            }
                        }
                    }
                }
                if (c.getInventory(MapleInventoryType.EQUIP).getNumFreeSlot() < eq) {
                    c.dropMessage(1, "請確認裝备栏是否滿了。");
                    return false;
                }
                if (c.getInventory(MapleInventoryType.USE).getNumFreeSlot() < use) {
                    c.dropMessage(1, "請確認消耗栏是否滿了。");
                    return false;
                }
                if (c.getInventory(MapleInventoryType.SETUP).getNumFreeSlot() < setup) {
                    c.dropMessage(1, "請確認裝飾栏是否滿了。");
                    return false;
                }
                if (c.getInventory(MapleInventoryType.ETC).getNumFreeSlot() < etc) {
                    c.dropMessage(1, "請確認其他栏是否滿了。");
                    return false;
                }
                if (c.getInventory(MapleInventoryType.CASH).getNumFreeSlot() < cash) {
                    c.dropMessage(1, "請確認特殊栏是否滿了。");
                    return false;
                }
                return true;
            }
            case money: {
                final int meso = this.intStore;
                if (c.getMeso() + meso < 0) {
                    c.dropMessage(1, "金币不足。");
                    return false;
                }
                if (meso < 0 && c.getMeso() < Math.abs(meso)) {
                    c.dropMessage(1, "金币不足。");
                    return false;
                }
                return true;
            }
            default: {
                return true;
            }
        }
    }
    
    public void runEnd(final MapleCharacter c, final Integer extSelection, final int qusetid) {
        QuestItem item;
        switch (this.type) {
            case exp: {
                c.gainExp(this.intStore * GameConstants.getExpRate_Quest((int)c.getLevel()), true, true, true);
                break;
            }
            case item: {
                final Map<Integer, Integer> props = new HashMap<Integer, Integer>();
                final Iterator<QuestItem> iterator = this.items.iterator();
                while (iterator.hasNext()) {
                    item = (QuestItem)iterator.next();
                    if (item.prop > 0 && canGetItem(item, c)) {
                        for (int i = 0; i < item.prop; ++i) {
                            props.put(Integer.valueOf(props.size()), Integer.valueOf(item.itemid));
                        }
                    }
                }
                int selection = 0;
                int extNum = 0;
                if (props.size() > 0) {
                    selection = (int)Integer.valueOf(props.get((Object)Integer.valueOf(Randomizer.nextInt(props.size()))));
                }
                for (final QuestItem item2 : this.items) {
                    if (!canGetItem(item2, c)) {
                        continue;
                    }
                    final int id = item2.itemid;
                    if (item2.prop != -2) {
                        if (item2.prop == -1) {
                            if (extSelection != null && (int)extSelection != extNum++) {
                                continue;
                            }
                        }
                        else if (id != selection) {
                            continue;
                        }
                    }
                    final short count = (short)item2.count;
                    if (count < 0) {
                        MapleInventoryManipulator.removeById(c.getClient(), GameConstants.getInventoryType(id), id, count * -1, true, false);
                        c.getClient().getSession().writeAndFlush((Object)MaplePacketCreator.getShowItemGain(id, count, true));
                    }
                    else {
                        final int period = item2.period / 1440;
                        final String name = MapleItemInformationProvider.getInstance().getName(id);
                        if (id / 10000 == 114 && name != null && name.length() > 0) {
                            final String msg = "你已获得稱號 <" + name + ">";
                            c.dropMessage(-1, msg);
                            c.dropMessage(5, msg);
                        }
                        if (qusetid == 8630) {
                            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语] 任務ID:" + qusetid + " 玩家:" + c.getName() + " 使用10張楓彩票兌換物品:" + name + "ID:" + id));
                            FileoutputUtil.logToFile("logs/Data/楓彩票兌換.txt", "\r\n 时间\u3000[" + FileoutputUtil.NowTime() + "] IP: " + c.getClient().getSession().remoteAddress().toString().split(":")[0] + " 任務ID:" + qusetid + " 玩家:" + c.getName() + " 使用10張楓彩票兌換物品:" + name + "ID:" + id);
                        }
                        if (qusetid == 8631) {
                            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语] 任務ID:" + qusetid + " 玩家:" + c.getName() + " 使用50張楓彩票兌換物品:" + name + "ID:" + id));
                            FileoutputUtil.logToFile("logs/Data/楓彩票兌換.txt", "\r\n 时间\u3000[" + FileoutputUtil.NowTime() + "] IP: " + c.getClient().getSession().remoteAddress().toString().split(":")[0] + " 任務ID:" + qusetid + " 玩家:" + c.getName() + " 使用50張楓彩票兌換物品:" + name + "ID:" + id);
                        }
                        if (qusetid == 8632) {
                            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语] 任務ID:" + qusetid + " 玩家:" + c.getName() + " 使用100張楓彩票兌換物品:" + name + "ID:" + id));
                            FileoutputUtil.logToFile("logs/Data/楓彩票兌換.txt", "\r\n 时间\u3000[" + FileoutputUtil.NowTime() + "] IP: " + c.getClient().getSession().remoteAddress().toString().split(":")[0] + " 任務ID:" + qusetid + " 玩家:" + c.getName() + " 使用100張楓彩票兌換物品:" + name + "ID:" + id);
                        }
                        if (qusetid == 8633) {
                            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语] 任務ID:" + qusetid + " 玩家:" + c.getName() + " 使用200張楓彩票兌換物品:" + name + "ID:" + id));
                            FileoutputUtil.logToFile("logs/Data/楓彩票兌換.txt", "\r\n 时间\u3000[" + FileoutputUtil.NowTime() + "] IP: " + c.getClient().getSession().remoteAddress().toString().split(":")[0] + " 任務ID:" + qusetid + " 玩家:" + c.getName() + " 使用200張楓彩票兌換物品:" + name + "ID:" + id);
                        }
                        if (qusetid == 8634) {
                            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语] 任務ID:" + qusetid + " 玩家:" + c.getName() + " 使用500張楓彩票兌換物品:" + name + "ID:" + id));
                            FileoutputUtil.logToFile("logs/Data/楓彩票兌換.txt", "\r\n 时间\u3000[" + FileoutputUtil.NowTime() + "] IP: " + c.getClient().getSession().remoteAddress().toString().split(":")[0] + " 任務ID:" + qusetid + " 玩家:" + c.getName() + " 使用500張楓彩票兌換物品:" + name + "ID:" + id);
                        }
                        if (qusetid == 8635) {
                            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语] 任務ID:" + qusetid + " 玩家:" + c.getName() + " 使用1000張楓彩票兌換物品:" + name + "ID:" + id));
                            FileoutputUtil.logToFile("logs/Data/楓彩票兌換.txt", "\r\n 时间\u3000[" + FileoutputUtil.NowTime() + "] IP: " + c.getClient().getSession().remoteAddress().toString().split(":")[0] + " 任務ID:" + qusetid + " 玩家:" + c.getName() + " 使用1000張楓彩票兌換物品:" + name + "ID:" + id);
                        }
                        if (qusetid == 8636) {
                            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语] 任務ID:" + qusetid + " 玩家:" + c.getName() + " 使用3000張楓彩票兌換物品:" + name + "ID:" + id));
                            FileoutputUtil.logToFile("logs/Data/楓彩票兌換.txt", "\r\n 时间\u3000[" + FileoutputUtil.NowTime() + "] IP: " + c.getClient().getSession().remoteAddress().toString().split(":")[0] + " 任務ID:" + qusetid + " 玩家:" + c.getName() + " 使用3000張楓彩票兌換物品:" + name + "ID:" + id);
                        }
                        MapleInventoryManipulator.addById(c.getClient(), id, count, "", null, (long)period);
                        c.getClient().getSession().writeAndFlush((Object)MaplePacketCreator.getShowItemGain(id, count, true));
                    }
                }
                break;
            }
            case nextQuest: {
                c.getClient().getSession().writeAndFlush((Object)MaplePacketCreator.updateQuestFinish(this.quest.getId(), c.getQuest(this.quest).getNpc(), this.intStore));
                break;
            }
            case money: {
                c.gainMeso(this.intStore, true, true);
                break;
            }
            case quest: {
                for (final Pair<Integer, Integer> q : this.state) {
                    c.updateQuest(new MapleQuestStatus(MapleQuest.getInstance((int)Integer.valueOf(q.left)), (int)Integer.valueOf(q.right)));
                }
                break;
            }
            case skill: {
                for (final Triple<Integer, Integer, Integer> skills : this.skill) {
                    final int skillid = (int)Integer.valueOf(skills.left);
                    final int skillLevel = (int)Integer.valueOf(skills.mid);
                    final int masterLevel = (int)Integer.valueOf(skills.right);
                    final ISkill skillObject = SkillFactory.getSkill(skillid);
                    boolean found = false;
                    final Iterator<Integer> iterator5 = this.applicableJobs.iterator();
                    while (iterator5.hasNext()) {
                        final int applicableJob = (int)Integer.valueOf(iterator5.next());
                        if (c.getJob() == applicableJob) {
                            found = true;
                            break;
                        }
                    }
                    if (skillObject.isBeginnerSkill() || found) {
                        c.changeSkillLevel(skillObject, (byte)Math.max(skillLevel, (int)c.getSkillLevel(skillObject)), (byte)Math.max(masterLevel, (int)c.getMasterLevel(skillObject)));
                    }
                }
                break;
            }
            case pop: {
                final int fameGain = this.intStore;
                c.addFame(fameGain);
                c.updateSingleStat(MapleStat.FAME, (int)c.getFame());
                c.getClient().getSession().writeAndFlush((Object)MaplePacketCreator.getShowFameGain(fameGain));
                break;
            }
            case buffItemID: {
                final int tobuff = this.intStore;
                if (tobuff <= 0) {
                    break;
                }
                MapleItemInformationProvider.getInstance().getItemEffect(tobuff).applyTo(c);
                break;
            }
            case sp: {
                final int sp_val = this.intStore;
                if (this.applicableJobs.size() > 0) {
                    int finalJob = 0;
                    final Iterator<Integer> iterator6 = this.applicableJobs.iterator();
                    while (iterator6.hasNext()) {
                        final int job_val = (int)Integer.valueOf(iterator6.next());
                        if (c.getJob() >= job_val && job_val > finalJob) {
                            finalJob = job_val;
                        }
                    }
                    if (finalJob == 0) {
                        c.gainSP(sp_val);
                    }
                    else {
                        c.gainSP(sp_val, GameConstants.getSkillBook(finalJob));
                    }
                    break;
                }
                c.gainSP(sp_val);
                break;
            }
        }
    }
    
    private static List<Integer> getJobBy5ByteEncoding(final int encoded) {
        final List<Integer> ret = new ArrayList<Integer>();
        if ((encoded & 0x1) != 0x0) {
            ret.add(Integer.valueOf(0));
        }
        if ((encoded & 0x2) != 0x0) {
            ret.add(Integer.valueOf(100));
        }
        if ((encoded & 0x4) != 0x0) {
            ret.add(Integer.valueOf(200));
        }
        if ((encoded & 0x8) != 0x0) {
            ret.add(Integer.valueOf(300));
        }
        if ((encoded & 0x10) != 0x0) {
            ret.add(Integer.valueOf(400));
        }
        if ((encoded & 0x20) != 0x0) {
            ret.add(Integer.valueOf(500));
        }
        if ((encoded & 0x400) != 0x0) {
            ret.add(Integer.valueOf(1000));
        }
        if ((encoded & 0x800) != 0x0) {
            ret.add(Integer.valueOf(1100));
        }
        if ((encoded & 0x1000) != 0x0) {
            ret.add(Integer.valueOf(1200));
        }
        if ((encoded & 0x2000) != 0x0) {
            ret.add(Integer.valueOf(1300));
        }
        if ((encoded & 0x4000) != 0x0) {
            ret.add(Integer.valueOf(1400));
        }
        if ((encoded & 0x8000) != 0x0) {
            ret.add(Integer.valueOf(1500));
        }
        if ((encoded & 0x20000) != 0x0) {
            ret.add(Integer.valueOf(2001));
            ret.add(Integer.valueOf(2200));
        }
        if ((encoded & 0x100000) != 0x0) {
            ret.add(Integer.valueOf(2000));
            ret.add(Integer.valueOf(2001));
        }
        if ((encoded & 0x200000) != 0x0) {
            ret.add(Integer.valueOf(2100));
        }
        if ((encoded & 0x400000) != 0x0) {
            ret.add(Integer.valueOf(2001));
            ret.add(Integer.valueOf(2200));
        }
        if ((encoded & 0x40000000) != 0x0) {
            ret.add(Integer.valueOf(3000));
            ret.add(Integer.valueOf(3200));
            ret.add(Integer.valueOf(3300));
            ret.add(Integer.valueOf(3500));
        }
        return ret;
    }
    
    private static List<Integer> getJobBySimpleEncoding(final int encoded) {
        final List<Integer> ret = new ArrayList<Integer>();
        if ((encoded & 0x1) != 0x0) {
            ret.add(Integer.valueOf(200));
        }
        if ((encoded & 0x2) != 0x0) {
            ret.add(Integer.valueOf(300));
        }
        if ((encoded & 0x4) != 0x0) {
            ret.add(Integer.valueOf(400));
        }
        if ((encoded & 0x8) != 0x0) {
            ret.add(Integer.valueOf(500));
        }
        return ret;
    }
    
    public MapleQuestActionType getType() {
        return this.type;
    }
    
    @Override
    public String toString() {
        return this.type.toString();
    }
    
    public List<Triple<Integer, Integer, Integer>> getSkills() {
        return this.skill;
    }
    
    public List<QuestItem> getItems() {
        return this.items;
    }
    
    public static class QuestItem
    {
        public int itemid;
        public int count;
        public int period;
        public int gender;
        public int job;
        public int jobEx;
        public int prop;
        
        public QuestItem(final int itemid, final int count, final int period, final int gender, final int job, final int jobEx, final int prop) {
            this.itemid = itemid;
            this.count = count;
            this.period = period;
            this.gender = gender;
            this.job = job;
            this.jobEx = jobEx;
            this.prop = prop;
        }
    }
}
