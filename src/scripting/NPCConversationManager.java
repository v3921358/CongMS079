package scripting;

import merchant.merchant_main;
import gui.CongMS;
import client.inventory.MaplePet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;
import server.MaplePortal;
import java.util.LinkedHashSet;
import constants.ServerConfig;
import database.DatabaseConnection;
import handling.channel.handler.InterServerHandler;
import client.inventory.Item;
import server.gashapon.GashaponFactory;
import server.gashapon.Gashapon;
import server.shops.HiredMerchant;
import server.life.MonsterDropEntry;
import client.MapleJob;
import handling.channel.MapleGuildRanking.JobRankingInfo;
import tools.FilePrinter;
import tools.SearchGenerator;
import server.Timer.EventTimer;
import handling.world.World.Family;
import server.Timer.CloneTimer;
import server.StructPotentialItem;
import java.util.Collection;
import java.util.ArrayList;
import handling.world.guild.MapleGuild;
import handling.world.MapleParty;
import handling.world.World.Alliance;
import server.MapleStatEffect;
import java.awt.Point;
import client.inventory.ItemFlag;
import java.util.HashMap;
import server.SpeedRunner;
import server.maps.SpeedRunType;
import tools.Pair;
import java.util.EnumMap;
import server.MapleCarnivalChallenge;
import server.MapleCarnivalParty;
import server.maps.AramiaFireWorks;
import server.maps.Event_PyramidSubway;
import server.maps.Event_DojoAgent;
import tools.packet.PlayerShopPacket;
import server.MapleDueyActions;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import tools.FileoutputUtil;
import database.DBConPool;
import server.life.MapleMonster;
import server.maps.MapleMapObject;
import java.util.Arrays;
import server.maps.MapleMapObjectType;
import server.life.MapleMonsterInformationProvider;
import client.inventory.Equip;
import handling.channel.MapleGuildRanking;
import handling.world.World.Guild;
import tools.StringUtil;
import server.MapleSquad;
import server.maps.MapleMap;
import handling.channel.ChannelServer;
import handling.world.MaplePartyCharacter;
import client.MapleCharacter;
import client.SkillFactory;
import java.util.Map;
import client.SkillEntry;
import client.ISkill;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.List;
import client.inventory.MapleInventory;
import java.util.LinkedList;
import client.inventory.MapleInventoryType;
import server.quest.MapleQuest;
import client.inventory.IItem;
import handling.world.World.Broadcast;
import constants.GameConstants;
import server.MapleInventoryManipulator;
import server.MapleItemInformationProvider;
import server.MapleShopFactory;
import handling.world.World;
import server.Randomizer;
import client.MapleStat;
import tools.MaplePacketCreator;
import javax.script.Invocable;
import client.MapleClient;

public class NPCConversationManager extends AbstractPlayerInteraction
{
    private final MapleClient c;
    private final int npc;
    private final int questid;
    private final int mode;
    protected String script;
    private String getText;
    private final byte type;
    private byte lastMsg;
    public boolean pendingDisposal;
    private final Invocable iv;
    public int p;
    
    public NPCConversationManager(final MapleClient c, final int npc, final int questid, final int mode, final String npcscript, final byte type, final Invocable iv) {
        super(c);
        this.lastMsg = -1;
        this.pendingDisposal = false;
        this.p = 0;
        this.c = c;
        this.npc = npc;
        this.questid = questid;
        this.mode = mode;
        this.type = type;
        this.iv = iv;
        this.script = npcscript;
    }
    
    public Invocable getIv() {
        return this.iv;
    }
    
    public int getMode() {
        return this.mode;
    }
    
    public int getNpc() {
        return this.npc;
    }
    
    public int getQuest() {
        return this.questid;
    }
    
    public String getScript() {
        return this.script;
    }
    
    public byte getType() {
        return this.type;
    }
    
    public void safeDispose() {
        this.pendingDisposal = true;
    }
    
    public void dispose() {
        NPCScriptManager.getInstance().dispose(this.c);
    }
    
    public void askMapSelection(final String sel) {
        if (this.lastMsg > -1) {
            return;
        }
        this.c.sendPacket(MaplePacketCreator.getMapSelection(this.npc, sel));
        this.lastMsg = 13;
    }
    
    public void sendNext(final String text) {
        if (this.lastMsg > -1) {
            return;
        }
        if (text.contains((CharSequence)"#L")) {
            this.sendSimple(text);
            return;
        }
        this.c.sendPacket(MaplePacketCreator.getNPCTalk(this.npc, (byte)0, text, "00 01", (byte)0));
        this.lastMsg = 0;
    }
    
    public void sendNextS(final String text, final byte type) {
        if (this.lastMsg > -1) {
            return;
        }
        if (text.contains((CharSequence)"#L")) {
            this.sendSimpleS(text, type);
            return;
        }
        this.c.sendPacket(MaplePacketCreator.getNPCTalk(this.npc, (byte)0, text, "00 01", type));
        this.lastMsg = 0;
    }
    
    public void sendPrev(final String text) {
        if (this.lastMsg > -1) {
            return;
        }
        if (text.contains((CharSequence)"#L")) {
            this.sendSimple(text);
            return;
        }
        this.c.sendPacket(MaplePacketCreator.getNPCTalk(this.npc, (byte)0, text, "01 00", (byte)0));
        this.lastMsg = 0;
    }
    
    public void sendPrevS(final String text, final byte type) {
        if (this.lastMsg > -1) {
            return;
        }
        if (text.contains((CharSequence)"#L")) {
            this.sendSimpleS(text, type);
            return;
        }
        this.c.sendPacket(MaplePacketCreator.getNPCTalk(this.npc, (byte)0, text, "01 00", type));
        this.lastMsg = 0;
    }
    
    public void sendNextPrev(final String text) {
        if (this.lastMsg > -1) {
            return;
        }
        if (text.contains((CharSequence)"#L")) {
            this.sendSimple(text);
            return;
        }
        this.c.sendPacket(MaplePacketCreator.getNPCTalk(this.npc, (byte)0, text, "01 01", (byte)0));
        this.lastMsg = 0;
    }
    
    public void PlayerToNpc(final String text) {
        this.sendNextPrevS(text, (byte)3);
    }
    
    public void sendNextPrevS(final String text) {
        this.sendNextPrevS(text, (byte)3);
    }
    
    public void sendNextPrevS(final String text, final byte type) {
        if (this.lastMsg > -1) {
            return;
        }
        if (text.contains((CharSequence)"#L")) {
            this.sendSimpleS(text, type);
            return;
        }
        this.c.sendPacket(MaplePacketCreator.getNPCTalk(this.npc, (byte)0, text, "01 01", type));
        this.lastMsg = 0;
    }
    
    public void sendOk(final String text) {
        if (this.lastMsg > -1) {
            return;
        }
        if (text.contains((CharSequence)"#L")) {
            this.sendSimple(text);
            return;
        }
        this.c.sendPacket(MaplePacketCreator.getNPCTalk(this.npc, (byte)0, text, "00 00", (byte)0));
        this.lastMsg = 0;
    }
    
    public void sendOkS(final String text, final byte type) {
        if (this.lastMsg > -1) {
            return;
        }
        if (text.contains((CharSequence)"#L")) {
            this.sendSimpleS(text, type);
            return;
        }
        this.c.sendPacket(MaplePacketCreator.getNPCTalk(this.npc, (byte)0, text, "00 00", type));
        this.lastMsg = 0;
    }
    
    public void sendYesNo(final String text) {
        if (this.lastMsg > -1) {
            return;
        }
        if (text.contains((CharSequence)"#L")) {
            this.sendSimple(text);
            return;
        }
        this.c.sendPacket(MaplePacketCreator.getNPCTalk(this.npc, (byte)1, text, "", (byte)0));
        this.lastMsg = 1;
    }
    
    public void sendYesNoS(final String text, final byte type) {
        if (this.lastMsg > -1) {
            return;
        }
        if (text.contains((CharSequence)"#L")) {
            this.sendSimpleS(text, type);
            return;
        }
        this.c.sendPacket(MaplePacketCreator.getNPCTalk(this.npc, (byte)1, text, "", type));
        this.lastMsg = 1;
    }
    
    public void sendAcceptDecline(final String text) {
        this.askAcceptDecline(text);
    }
    
    public void sendAcceptDeclineNoESC(final String text) {
        this.askAcceptDeclineNoESC(text);
    }
    
    public void askAcceptDecline(final String text) {
        if (this.lastMsg > -1) {
            return;
        }
        if (text.contains((CharSequence)"#L")) {
            this.sendSimple(text);
            return;
        }
        this.c.sendPacket(MaplePacketCreator.getNPCTalk(this.npc, (byte)11, text, "", (byte)0));
        this.lastMsg = 11;
    }
    
    public void askAcceptDeclineNoESC(final String text) {
        if (this.lastMsg > -1) {
            return;
        }
        if (text.contains((CharSequence)"#L")) {
            this.sendSimple(text);
            return;
        }
        this.c.sendPacket(MaplePacketCreator.getNPCTalk(this.npc, (byte)12, text, "", (byte)0));
        this.lastMsg = 12;
    }
    
    public void askAvatar(final String text, final int... args) {
        if (this.lastMsg > -1) {
            return;
        }
        this.c.sendPacket(MaplePacketCreator.getNPCTalkStyle(this.npc, text, args));
        this.lastMsg = 7;
    }
    
    public void sendSimple(final String text) {
        if (this.lastMsg > -1) {
            return;
        }
        if (!text.contains((CharSequence)"#L")) {
            this.sendNext(text);
            return;
        }
        this.c.sendPacket(MaplePacketCreator.getNPCTalk(this.npc, (byte)4, text, "", (byte)0));
        this.lastMsg = 4;
    }
    
    public void sendSimpleS(final String text, final byte type) {
        if (this.lastMsg > -1) {
            return;
        }
        if (!text.contains((CharSequence)"#L")) {
            this.sendNextS(text, type);
            return;
        }
        this.c.sendPacket(MaplePacketCreator.getNPCTalk(this.npc, (byte)4, text, "", type));
        this.lastMsg = 4;
    }
    
    public void sendStyle(final String text, final int[] styles) {
        if (this.lastMsg > -1) {
            return;
        }
        this.c.sendPacket(MaplePacketCreator.getNPCTalkStyle(this.npc, text, styles));
        this.lastMsg = 7;
    }
    
    public void sendGetNumber(final String text, final int def, final int min, final int max) {
        if (this.lastMsg > -1) {
            return;
        }
        if (text.contains((CharSequence)"#L")) {
            this.sendSimple(text);
            return;
        }
        this.c.sendPacket(MaplePacketCreator.getNPCTalkNum(this.npc, text, def, min, max));
        this.lastMsg = 3;
    }
    
    public void sendGetText(final String text) {
        if (this.lastMsg > -1) {
            return;
        }
        if (text.contains((CharSequence)"#L")) {
            this.sendSimple(text);
            return;
        }
        this.c.sendPacket(MaplePacketCreator.getNPCTalkText(this.npc, text));
        this.lastMsg = 2;
    }
    
    public void setGetText(final String text) {
        this.getText = text;
    }
    
    public String getText() {
        return this.getText;
    }
    
    public void setHair(final int hair) {
        this.getPlayer().setHair(hair);
        this.getPlayer().updateSingleStat(MapleStat.HAIR, hair);
        this.getPlayer().equipChanged();
    }
    
    public void setFace(final int face) {
        this.getPlayer().setFace(face);
        this.getPlayer().updateSingleStat(MapleStat.FACE, face);
        this.getPlayer().equipChanged();
    }
    
    public void setSkin(final int color) {
        this.getPlayer().setSkinColor((byte)color);
        this.getPlayer().updateSingleStat(MapleStat.SKIN, color);
        this.getPlayer().equipChanged();
    }
    
    public int setRandomAvatar(final int ticket, final int... args_all) {
        if (!this.haveItem(ticket)) {
            return -1;
        }
        this.gainItem(ticket, (short)(-1));
        final int args = args_all[Randomizer.nextInt(args_all.length)];
        if (args < 100) {
            this.c.getPlayer().setSkinColor((byte)args);
            this.c.getPlayer().updateSingleStat(MapleStat.SKIN, args);
        }
        else if (args < 30000) {
            this.c.getPlayer().setFace(args);
            this.c.getPlayer().updateSingleStat(MapleStat.FACE, args);
        }
        else {
            this.c.getPlayer().setHair(args);
            this.c.getPlayer().updateSingleStat(MapleStat.HAIR, args);
        }
        this.c.getPlayer().equipChanged();
        return 1;
    }
    
    public int setAvatar(final int ticket, final int args) {
        if (!this.haveItem(ticket)) {
            return -1;
        }
        this.gainItem(ticket, (short)(-1));
        if (args < 100) {
            this.c.getPlayer().setSkinColor((byte)args);
            this.c.getPlayer().updateSingleStat(MapleStat.SKIN, args);
        }
        else if (args < 30000) {
            this.c.getPlayer().setFace(args);
            this.c.getPlayer().updateSingleStat(MapleStat.FACE, args);
        }
        else {
            this.c.getPlayer().setHair(args);
            this.c.getPlayer().updateSingleStat(MapleStat.HAIR, args);
        }
        this.c.getPlayer().equipChanged();
        return 1;
    }
    
    public void sendStorage() {
        if (this.getPlayer().hasBlockedInventory2(true)) {
            this.c.getPlayer().dropMessage(1, "系統錯誤，請联繫管理員。");
            this.c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (!World.isShutDown) {
            if (!World.isShopShutDown) {
                this.c.getPlayer().setConversation(4);
                this.c.getPlayer().getStorage().sendStorage(this.c, this.npc);
            }
            else {
                this.c.getPlayer().dropMessage(1, "目前不能使用仓库。");
                this.c.sendPacket(MaplePacketCreator.enableActions());
            }
        }
        else {
            this.c.getPlayer().dropMessage(1, "目前不能使用仓库。");
            this.c.sendPacket(MaplePacketCreator.enableActions());
        }
    }
    
    public void openShop(final int id) {
        MapleShopFactory.getInstance().getShop(id).sendShop(this.c);
    }
    
    public int gainGachaponItemTime(final int id, final int quantity, final long period) {
        return this.gainGachaponItemTime(id, quantity, this.c.getPlayer().getMap().getStreetName() + " - " + this.c.getPlayer().getMap().getMapName(), period);
    }
    
    public int gainGachaponItemTime(final int id, final int quantity, final String msg, final long period) {
        try {
            final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            if (!ii.itemExists(id)) {
                return -1;
            }
            final IItem item = ii.isCash(id) ? MapleInventoryManipulator.addbyId_GachaponTime(this.c, id, (short)quantity, period) : MapleInventoryManipulator.addbyId_Gachapon(this.c, id, (short)quantity);
            if (item == null) {
                return -1;
            }
            final byte rareness = GameConstants.gachaponRareItem(item.getItemId());
            if (rareness == 1) {
                if (this.c.getPlayer().getMapId() == 910000000) {
                    Broadcast.broadcastMessage(MaplePacketCreator.getGachaponMega("[自由市場]", " : 恭喜玩家 " + this.c.getPlayer().getName() + " 在" + msg + "获得！", item, rareness));
                }
                else {
                    Broadcast.broadcastMessage(MaplePacketCreator.getGachaponMega("[隱藏地图-轉蛋屋]", " : 恭喜玩家 " + this.c.getPlayer().getName() + " 在" + msg + "获得！", item, rareness));
                }
            }
            else if (rareness == 2) {
                Broadcast.broadcastMessage(MaplePacketCreator.getGachaponMega("[" + msg + "] " + this.c.getPlayer().getName(), " : 被他成功轉到了，大家恭喜他吧！", item, rareness));
            }
            else if (rareness > 2) {
                Broadcast.broadcastMessage(MaplePacketCreator.getGachaponMega("[" + msg + "] " + this.c.getPlayer().getName(), " : 被他從楓葉转蛋机轉到了，大家恭喜他吧！", item, rareness));
            }
            return item.getItemId();
        }
        catch (Exception ex) {
            return -1;
        }
    }
    
    public void changeJob(final int job) {
        this.c.getPlayer().changeJob(job);
    }
    
    public void startQuest(final int id) {
        MapleQuest.getInstance(id).start(this.getPlayer(), this.npc);
    }
    
    public void completeQuest(final int id) {
        MapleQuest.getInstance(id).complete(this.getPlayer(), this.npc);
    }
    
    public void forfeitQuest(final int id) {
        MapleQuest.getInstance(id).forfeit(this.getPlayer());
    }
    
    public void forceStartQuest() {
        MapleQuest.getInstance(this.questid).forceStart(this.getPlayer(), this.getNpc(), null);
    }
    
    @Override
    public void forceStartQuest(final int id) {
        MapleQuest.getInstance(id).forceStart(this.getPlayer(), this.getNpc(), null);
    }
    
    public void forceStartQuest(final String customData) {
        MapleQuest.getInstance(this.questid).forceStart(this.getPlayer(), this.getNpc(), customData);
    }
    
    public void forceCompleteQuest() {
        MapleQuest.getInstance(this.questid).forceComplete(this.getPlayer(), this.getNpc());
    }
    
    @Override
    public void forceCompleteQuest(final int id) {
        MapleQuest.getInstance(id).forceComplete(this.getPlayer(), this.getNpc());
    }
    
    public String getQuestCustomData() {
        return this.c.getPlayer().getQuestNAdd(MapleQuest.getInstance(this.questid)).getCustomData();
    }
    
    public void setQuestCustomData(final String customData) {
        this.getPlayer().getQuestNAdd(MapleQuest.getInstance(this.questid)).setCustomData(customData);
    }
    
    public int getMeso() {
        return this.getPlayer().getMeso();
    }
    
    public void gainAp(final int amount) {
        this.c.getPlayer().gainAp((short)amount);
    }
    
    public void expandInventory(final byte type, final int amt) {
        this.c.getPlayer().expandInventory(type, amt);
    }
    
    public void unequipEverything() {
        final MapleInventory equipped = this.getPlayer().getInventory(MapleInventoryType.EQUIPPED);
        final MapleInventory equip = this.getPlayer().getInventory(MapleInventoryType.EQUIP);
        final List<Short> ids = new LinkedList<Short>();
        for (final IItem item : equipped.list()) {
            ids.add(Short.valueOf(item.getPosition()));
        }
        final Iterator<Short> iterator2 = ids.iterator();
        while (iterator2.hasNext()) {
            final short id = (short)Short.valueOf(iterator2.next());
            MapleInventoryManipulator.unequip(this.getC(), id, equip.getNextFreeSlot());
        }
    }
    
    public final void clearSkills() {
        final Map<ISkill, SkillEntry> skills = this.getPlayer().getSkills();
        for (final Entry<ISkill, SkillEntry> skill : skills.entrySet()) {
            this.getPlayer().changeSkillLevel((ISkill)skill.getKey(), (byte)0, (byte)0);
        }
    }
    
    public boolean hasSkill(final int skillid) {
        final ISkill theSkill = SkillFactory.getSkill(skillid);
        return theSkill != null && this.c.getPlayer().getSkillLevel(theSkill) > 0;
    }
    
    public void showEffect(final boolean broadcast, final String effect) {
        if (broadcast) {
            this.c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.showEffect(effect));
        }
        else {
            this.c.sendPacket(MaplePacketCreator.showEffect(effect));
        }
    }
    
    public void playSound(final boolean broadcast, final String sound) {
        if (broadcast) {
            this.c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.playSound(sound));
        }
        else {
            this.c.sendPacket(MaplePacketCreator.playSound(sound));
        }
    }
    
    public void environmentChange(final boolean broadcast, final String env) {
        if (broadcast) {
            this.c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.environmentChange(env, 2));
        }
        else {
            this.c.sendPacket(MaplePacketCreator.environmentChange(env, 2));
        }
    }
    
    public void updateBuddyCapacity(final int capacity) {
        this.c.getPlayer().setBuddyCapacity((byte)capacity);
    }
    
    public int getBuddyCapacity() {
        return this.c.getPlayer().getBuddyCapacity();
    }
    
    public int partyMembersInMap() {
        int inMap = 0;
        for (final MapleCharacter char2 : this.getPlayer().getMap().getCharactersThreadsafe()) {
            if (char2.getParty() == this.getPlayer().getParty()) {
                ++inMap;
            }
        }
        return inMap;
    }
    
    public List<MapleCharacter> getPartyMembers() {
        if (this.getPlayer().getParty() == null) {
            return null;
        }
        final List<MapleCharacter> chars = new LinkedList<MapleCharacter>();
        for (final MaplePartyCharacter chr : this.getPlayer().getParty().getMembers()) {
            for (final ChannelServer channel : ChannelServer.getAllInstances()) {
                final MapleCharacter ch = channel.getPlayerStorage().getCharacterById(chr.getId());
                if (ch != null) {
                    chars.add(ch);
                }
            }
        }
        return chars;
    }
    
    public void warpPartyWithExp(final int mapId, final int exp) {
        final MapleMap target = this.getMap(mapId);
        for (final MaplePartyCharacter chr : this.getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = this.c.getChannelServer().getPlayerStorage().getCharacterByName(chr.getName());
            if ((curChar.getEventInstance() == null && this.getPlayer().getEventInstance() == null) || curChar.getEventInstance() == this.getPlayer().getEventInstance()) {
                curChar.changeMap(target, target.getPortal(0));
                curChar.gainExp(exp, true, false, true);
            }
        }
    }
    
    public void warpPartyWithExpMeso(final int mapId, final int exp, final int meso) {
        final MapleMap target = this.getMap(mapId);
        for (final MaplePartyCharacter chr : this.getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = this.c.getChannelServer().getPlayerStorage().getCharacterByName(chr.getName());
            if ((curChar.getEventInstance() == null && this.getPlayer().getEventInstance() == null) || curChar.getEventInstance() == this.getPlayer().getEventInstance()) {
                curChar.changeMap(target, target.getPortal(0));
                curChar.gainExp(exp, true, false, true);
                curChar.gainMeso(meso, true);
            }
        }
    }
    
    public MapleSquad getSquad(final String type) {
        return this.c.getChannelServer().getMapleSquad(type);
    }
    
    public int getSquadAvailability(final String type) {
        final MapleSquad squad = this.c.getChannelServer().getMapleSquad(type);
        if (squad == null) {
            return -1;
        }
        return squad.getStatus();
    }
    
    public boolean registerSquad(final String type, final int minutes, final String startText) {
        if (this.c.getChannelServer().getMapleSquad(type) == null) {
            final MapleSquad squad = new MapleSquad(this.c.getChannel(), type, this.c.getPlayer(), minutes * 60 * 1000, startText);
            final boolean ret = this.c.getChannelServer().addMapleSquad(squad, type);
            if (ret) {
                final MapleMap map = this.c.getPlayer().getMap();
                map.broadcastMessage(MaplePacketCreator.getClock(minutes * 60));
                map.broadcastMessage(MaplePacketCreator.serverNotice(6, this.c.getPlayer().getName() + startText));
            }
            else {
                squad.clear();
            }
            return ret;
        }
        return false;
    }
    
    public boolean getSquadList(final String type, final byte type_) {
        final MapleSquad squad = this.c.getChannelServer().getMapleSquad(type);
        if (squad == null) {
            return false;
        }
        if (type_ == 0 || type_ == 3) {
            this.sendNext(squad.getSquadMemberString(type_));
        }
        else if (type_ == 1) {
            this.sendSimple(squad.getSquadMemberString(type_));
        }
        else if (type_ == 2) {
            if (squad.getBannedMemberSize() > 0) {
                this.sendSimple(squad.getSquadMemberString(type_));
            }
            else {
                this.sendNext(squad.getSquadMemberString(type_));
            }
        }
        return true;
    }
    
    public byte isSquadLeader(final String type) {
        final MapleSquad squad = this.c.getChannelServer().getMapleSquad(type);
        if (squad == null) {
            return -1;
        }
        if (squad.getLeader() != null && squad.getLeader().getId() == this.c.getPlayer().getId()) {
            return 1;
        }
        return 0;
    }
    
    public boolean reAdd(final String eim, final String squad) {
        final EventInstanceManager eimz = this.getDisconnected(eim);
        final MapleSquad squadz = this.getSquad(squad);
        if (eimz != null && squadz != null) {
            squadz.reAddMember(this.getPlayer());
            eimz.registerPlayer(this.getPlayer());
            return true;
        }
        return false;
    }
    
    public void banMember(final String type, final int pos) {
        final MapleSquad squad = this.c.getChannelServer().getMapleSquad(type);
        if (squad != null) {
            squad.banMember(pos);
        }
    }
    
    public void acceptMember(final String type, final int pos) {
        final MapleSquad squad = this.c.getChannelServer().getMapleSquad(type);
        if (squad != null) {
            squad.acceptMember(pos);
        }
    }
    
    public String getReadableMillis(final long startMillis, final long endMillis) {
        return StringUtil.getReadableMillis(startMillis, endMillis);
    }
    
    public int addMember(final String type, final boolean join) {
        final MapleSquad squad = this.c.getChannelServer().getMapleSquad(type);
        if (squad != null) {
            return squad.addMember(this.c.getPlayer(), join);
        }
        return -1;
    }
    
    public byte isSquadMember(final String type) {
        final MapleSquad squad = this.c.getChannelServer().getMapleSquad(type);
        if (squad == null) {
            return -1;
        }
        if (squad.getMembers().contains((Object)this.c.getPlayer().getName())) {
            return 1;
        }
        if (squad.isBanned(this.c.getPlayer())) {
            return 2;
        }
        return 0;
    }
    
    public void resetReactors() {
        this.getPlayer().getMap().resetReactors();
    }
    
    public void genericGuildMessage(final int code) {
        this.c.sendPacket(MaplePacketCreator.genericGuildMessage((byte)code));
    }
    
    public void disbandGuild() {
        final int gid = this.c.getPlayer().getGuildId();
        if (gid <= 0 || this.c.getPlayer().getGuildRank() != 1) {
            return;
        }
        Guild.disbandGuild(gid);
    }
    
    public void increaseGuildCapacity() {
        if (this.c.getPlayer().getMeso() < 5000000) {
            this.c.sendPacket(MaplePacketCreator.serverNotice(1, "You do not have enough mesos."));
            return;
        }
        final int gid = this.c.getPlayer().getGuildId();
        if (gid <= 0) {
            return;
        }
        Guild.increaseGuildCapacity(gid);
        this.c.getPlayer().gainMeso(-5000000, true, false, true);
    }
    
    public void displayGuildRanks() {
        this.c.sendPacket(MaplePacketCreator.showGuildRanks(this.npc, MapleGuildRanking.getInstance().getGuildRank()));
    }
    
    public void showlvl() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().getLevelRank()));
    }
    
    public void showmeso() {
        this.c.sendPacket(MaplePacketCreator.showmesoRanks(this.npc, MapleGuildRanking.getInstance().getMesoRank()));
    }
    
    public boolean removePlayerFromInstance() {
        if (this.c.getPlayer().getEventInstance() != null) {
            this.c.getPlayer().getEventInstance().removePlayer(this.c.getPlayer());
            return true;
        }
        return false;
    }
    
    public boolean isPlayerInstance() {
        return this.c.getPlayer().getEventInstance() != null;
    }
    
    public void changeStat(final byte slot, final int type, final short amount) {
        final Equip sel = (Equip)this.c.getPlayer().getInventory(MapleInventoryType.EQUIPPED).getItem((short)slot);
        switch (type) {
            case 0: {
                sel.setStr(amount);
                break;
            }
            case 1: {
                sel.setDex(amount);
                break;
            }
            case 2: {
                sel.setInt(amount);
                break;
            }
            case 3: {
                sel.setLuk(amount);
                break;
            }
            case 4: {
                sel.setHp(amount);
                break;
            }
            case 5: {
                sel.setMp(amount);
                break;
            }
            case 6: {
                sel.setWatk(amount);
                break;
            }
            case 7: {
                sel.setMatk(amount);
                break;
            }
            case 8: {
                sel.setWdef(amount);
                break;
            }
            case 9: {
                sel.setMdef(amount);
                break;
            }
            case 10: {
                sel.setAcc(amount);
                break;
            }
            case 11: {
                sel.setAvoid(amount);
                break;
            }
            case 12: {
                sel.setHands(amount);
                break;
            }
            case 13: {
                sel.setSpeed(amount);
                break;
            }
            case 14: {
                sel.setJump(amount);
                break;
            }
            case 15: {
                sel.setUpgradeSlots((byte)amount);
                break;
            }
            case 16: {
                sel.setViciousHammer((byte)amount);
                break;
            }
            case 17: {
                sel.setLevel((byte)amount);
                break;
            }
            case 18: {
                sel.setEnhance((byte)amount);
                break;
            }
            case 19: {
                sel.setPotential1(amount);
                break;
            }
            case 20: {
                sel.setPotential2(amount);
                break;
            }
            case 21: {
                sel.setPotential3(amount);
                break;
            }
            case 22: {
                sel.setOwner(this.getText());
                break;
            }
        }
        this.c.getPlayer().equipChanged();
    }
    
    public void cleardrops() {
        MapleMonsterInformationProvider.getInstance().clearDrops();
    }
    
    public void killAllMonsters() {
        final MapleMap map = this.c.getPlayer().getMap();
        final double range = Double.POSITIVE_INFINITY;
        for (final MapleMapObject monstermo : map.getMapObjectsInRange(this.c.getPlayer().getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER))) {
            final MapleMonster mob = (MapleMonster)monstermo;
            if (mob.getStats().isBoss()) {
                map.killMonster(mob, this.c.getPlayer(), false, false, (byte)1);
            }
        }
    }
    
    public void giveMerchantMesos() {
        long mesos = 0L;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT mesos FROM hiredmerchants WHERE merchantid = ?");
            ps.setInt(1, this.getPlayer().getId());
            final ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                rs.close();
                ps.close();
            }
            else {
                mesos = rs.getLong("mesos");
            }
            rs.close();
            ps.close();
            ps = con.prepareStatement("UPDATE hiredmerchants SET mesos = 0 WHERE merchantid = ?");
            ps.setInt(1, this.getPlayer().getId());
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException ex) {
            System.err.println("Error gaining mesos in hired merchant" + (Object)ex);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        this.c.getPlayer().gainMeso((int)mesos, true);
    }
    
    public void dc() {
        final MapleCharacter victim = this.getChannelServer().getPlayerStorage().getCharacterByName(this.getPlayer().getName());
        victim.getClient().getSession().close();
        victim.getClient().disconnect(true, false);
    }
    
    public long getMerchantMesos() {
        long mesos = 0L;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT mesos FROM hiredmerchants WHERE merchantid = ?")) {
            ps.setInt(1, this.getPlayer().getId());
            try (final ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    rs.close();
                    ps.close();
                }
                else {
                    mesos = rs.getLong("mesos");
                }
            }
        }
        catch (SQLException ex) {
            System.err.println("Error gaining mesos in hired merchant" + (Object)ex);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        return mesos;
    }
    
    public void openDuey() {
        this.c.getPlayer().setConversation(2);
        this.c.sendPacket(MaplePacketCreator.sendDuey((byte)9, null));
    }
    
    public void openMerchantItemStore() {
        if (!World.isShutDown) {
            this.c.getPlayer().setConversation(3);
            this.c.sendPacket(PlayerShopPacket.merchItemStore((byte)34));
        }
        else {
            this.c.getPlayer().dropMessage(1, "目前不能使用精灵商人領取。");
            this.c.sendPacket(MaplePacketCreator.enableActions());
        }
    }
    
    public void sendRepairWindow() {
        this.c.sendPacket(MaplePacketCreator.sendRepairWindow(this.npc));
    }
    
    public final int getDojoPoints() {
        return this.c.getPlayer().getDojo();
    }
    
    public void setDojoPoints(final int point) {
        this.c.getPlayer().setDojo(this.c.getPlayer().getDojo() + point);
    }
    
    public final int getDojoRecord() {
        return this.c.getPlayer().getDojoRecord();
    }
    
    public void setDojoRecord(final boolean reset) {
        this.c.getPlayer().setDojoRecord(reset);
    }
    
    public boolean start_DojoAgent(final boolean dojo, final boolean party) {
        if (dojo) {
            return Event_DojoAgent.warpStartDojo(this.c.getPlayer(), party);
        }
        return Event_DojoAgent.warpStartAgent(this.c.getPlayer(), party);
    }
    
    public boolean start_PyramidSubway(final int pyramid) {
        if (pyramid >= 0) {
            return Event_PyramidSubway.warpStartPyramid(this.c.getPlayer(), pyramid);
        }
        return Event_PyramidSubway.warpStartSubway(this.c.getPlayer());
    }
    
    public boolean bonus_PyramidSubway(final int pyramid) {
        if (pyramid >= 0) {
            return Event_PyramidSubway.warpBonusPyramid(this.c.getPlayer(), pyramid);
        }
        return Event_PyramidSubway.warpBonusSubway(this.c.getPlayer());
    }
    
    public final short getKegs() {
        return AramiaFireWorks.getInstance().getKegsPercentage();
    }
    
    public void giveKegs(final int kegs) {
        AramiaFireWorks.getInstance().giveKegs(this.c.getPlayer(), kegs);
    }
    
    public final short getSunshines() {
        return AramiaFireWorks.getInstance().getSunsPercentage();
    }
    
    public void addSunshines(final int kegs) {
        AramiaFireWorks.getInstance().giveSuns(this.c.getPlayer(), kegs);
    }
    
    public final short getDecorations() {
        return AramiaFireWorks.getInstance().getDecsPercentage();
    }
    
    public void addDecorations(final int kegs) {
        try {
            AramiaFireWorks.getInstance().giveDecs(this.c.getPlayer(), kegs);
        }
        catch (Exception ex) {}
    }
    
    public final MapleInventory getInventory(final int type) {
        return this.c.getPlayer().getInventory(MapleInventoryType.getByType((byte)type));
    }
    
    public final MapleCarnivalParty getCarnivalParty() {
        return this.c.getPlayer().getCarnivalParty();
    }
    
    public final MapleCarnivalChallenge getNextCarnivalRequest() {
        return this.c.getPlayer().getNextCarnivalRequest();
    }
    
    public final MapleCarnivalChallenge getCarnivalChallenge(final MapleCharacter chr) {
        return new MapleCarnivalChallenge(chr);
    }
    
    public void maxStats() {
        final Map<MapleStat, Integer> statup = new EnumMap<MapleStat, Integer>(MapleStat.class);
        this.c.getPlayer().getStat().setStr((short)32767);
        this.c.getPlayer().getStat().setDex((short)32767);
        this.c.getPlayer().getStat().setInt((short)32767);
        this.c.getPlayer().getStat().setLuk((short)32767);
        this.c.getPlayer().getStat().setMaxHp((short)30000);
        this.c.getPlayer().getStat().setMaxMp((short)30000);
        this.c.getPlayer().getStat().setHp(30000);
        this.c.getPlayer().getStat().setMp(30000);
        statup.put(MapleStat.STR, Integer.valueOf(32767));
        statup.put(MapleStat.DEX, Integer.valueOf(32767));
        statup.put(MapleStat.LUK, Integer.valueOf(32767));
        statup.put(MapleStat.INT, Integer.valueOf(32767));
        statup.put(MapleStat.HP, Integer.valueOf(30000));
        statup.put(MapleStat.MAXHP, Integer.valueOf(30000));
        statup.put(MapleStat.MP, Integer.valueOf(30000));
        statup.put(MapleStat.MAXMP, Integer.valueOf(30000));
        this.c.sendPacket(MaplePacketCreator.updatePlayerStats(statup, this.c.getPlayer()));
    }
    
    public Pair<String, Map<Integer, String>> getSpeedRun(final String typ) {
        final SpeedRunType stype = SpeedRunType.valueOf(typ);
        if (SpeedRunner.getInstance().getSpeedRunData(stype) != null) {
            return SpeedRunner.getInstance().getSpeedRunData(stype);
        }
        return new Pair<String, Map<Integer, String>>("", (Map<Integer, String>)new HashMap<Integer, String>());
    }
    
    public boolean getSR(final Pair<String, Map<Integer, String>> ma, final int sel) {
        if (((Map<Integer, String>)ma.getRight()).get((Object)Integer.valueOf(sel)) == null || ((String)((Map<Integer, String>)ma.getRight()).get((Object)Integer.valueOf(sel))).length() <= 0) {
            this.dispose();
            return false;
        }
        this.sendOk((String)((Map<Integer, String>)ma.getRight()).get((Object)Integer.valueOf(sel)));
        return true;
    }
    
    public Equip getEquip(final int itemid) {
        return (Equip)MapleItemInformationProvider.getInstance().getEquipById(itemid);
    }
    
    public void setExpiration(final Object statsSel, final long expire) {
        if (statsSel instanceof Equip) {
            ((Equip)statsSel).setExpiration(System.currentTimeMillis() + expire * 24L * 60L * 60L * 1000L);
        }
    }
    
    public void setLock(final Object statsSel) {
        if (statsSel instanceof Equip) {
            final Equip eq = (Equip)statsSel;
            if (eq.getExpiration() == -1L) {
                eq.setFlag((byte)(eq.getFlag() | ItemFlag.LOCK.getValue()));
            }
            else {
                eq.setFlag((byte)(eq.getFlag() | ItemFlag.UNTRADEABLE.getValue()));
            }
        }
    }
    
    public boolean addFromDrop(final Object statsSel) {
        if (statsSel instanceof IItem) {
            final IItem it = (IItem)statsSel;
            return MapleInventoryManipulator.checkSpace(this.getClient(), it.getItemId(), (int)it.getQuantity(), it.getOwner()) && MapleInventoryManipulator.addFromDrop(this.getClient(), it, false);
        }
        return false;
    }
    
    public boolean replaceItem(final int slot, final int invType, final Object statsSel, final int offset, final String type) {
        return this.replaceItem(slot, invType, statsSel, offset, type, false);
    }
    
    public boolean replaceItem(final int slot, final int invType, final Object statsSel, final int offset, final String type, final boolean takeSlot) {
        final MapleInventoryType inv = MapleInventoryType.getByType((byte)invType);
        if (inv == null) {
            return false;
        }
        IItem item = this.getPlayer().getInventory(inv).getItem((short)(byte)slot);
        if (item == null || statsSel instanceof IItem) {
            item = (IItem)statsSel;
        }
        if (offset > 0) {
            if (inv != MapleInventoryType.EQUIP) {
                return false;
            }
            final Equip eq = (Equip)item;
            if (takeSlot) {
                if (eq.getUpgradeSlots() < 1) {
                    return false;
                }
                eq.setUpgradeSlots((byte)(eq.getUpgradeSlots() - 1));
            }
            if (type.equalsIgnoreCase("Slots")) {
                eq.setUpgradeSlots((byte)(eq.getUpgradeSlots() + offset));
            }
            else if (type.equalsIgnoreCase("Level")) {
                eq.setLevel((byte)(eq.getLevel() + offset));
            }
            else if (type.equalsIgnoreCase("Hammer")) {
                eq.setViciousHammer((byte)(eq.getViciousHammer() + offset));
            }
            else if (type.equalsIgnoreCase("STR")) {
                eq.setStr((short)(eq.getStr() + offset));
            }
            else if (type.equalsIgnoreCase("DEX")) {
                eq.setDex((short)(eq.getDex() + offset));
            }
            else if (type.equalsIgnoreCase("INT")) {
                eq.setInt((short)(eq.getInt() + offset));
            }
            else if (type.equalsIgnoreCase("LUK")) {
                eq.setLuk((short)(eq.getLuk() + offset));
            }
            else if (type.equalsIgnoreCase("HP")) {
                eq.setHp((short)(eq.getHp() + offset));
            }
            else if (type.equalsIgnoreCase("MP")) {
                eq.setMp((short)(eq.getMp() + offset));
            }
            else if (type.equalsIgnoreCase("WATK")) {
                eq.setWatk((short)(eq.getWatk() + offset));
            }
            else if (type.equalsIgnoreCase("MATK")) {
                eq.setMatk((short)(eq.getMatk() + offset));
            }
            else if (type.equalsIgnoreCase("WDEF")) {
                eq.setWdef((short)(eq.getWdef() + offset));
            }
            else if (type.equalsIgnoreCase("MDEF")) {
                eq.setMdef((short)(eq.getMdef() + offset));
            }
            else if (type.equalsIgnoreCase("ACC")) {
                eq.setAcc((short)(eq.getAcc() + offset));
            }
            else if (type.equalsIgnoreCase("Avoid")) {
                eq.setAvoid((short)(eq.getAvoid() + offset));
            }
            else if (type.equalsIgnoreCase("Hands")) {
                eq.setHands((short)(eq.getHands() + offset));
            }
            else if (type.equalsIgnoreCase("Speed")) {
                eq.setSpeed((short)(eq.getSpeed() + offset));
            }
            else if (type.equalsIgnoreCase("Jump")) {
                eq.setJump((short)(eq.getJump() + offset));
            }
            else if (type.equalsIgnoreCase("ItemEXP")) {
                eq.setItemEXP(eq.getItemEXP() + offset);
            }
            else if (type.equalsIgnoreCase("Expiration")) {
                eq.setExpiration(eq.getExpiration() + (long)offset);
            }
            else if (type.equalsIgnoreCase("Flag")) {
                eq.setFlag((byte)(eq.getFlag() + offset));
            }
            if (eq.getExpiration() == -1L) {
                eq.setFlag((byte)(eq.getFlag() | ItemFlag.LOCK.getValue()));
            }
            else {
                eq.setFlag((byte)(eq.getFlag() | ItemFlag.UNTRADEABLE.getValue()));
            }
            item = eq.copy();
        }
        MapleInventoryManipulator.removeFromSlot(this.getClient(), inv, (short)slot, item.getQuantity(), false);
        return MapleInventoryManipulator.addFromDrop(this.getClient(), item, false);
    }
    
    public boolean replaceItem(final int slot, final int invType, final Object statsSel, final int upgradeSlots) {
        return this.replaceItem(slot, invType, statsSel, upgradeSlots, "Slots");
    }
    
    public boolean isCash(final int itemId) {
        return MapleItemInformationProvider.getInstance().isCash(itemId);
    }
    
    public void buffGuild(final int buff, final int duration, final String msg) {
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        if (ii.getItemEffect(buff) != null && this.getPlayer().getGuildId() > 0) {
            final MapleStatEffect mse = ii.getItemEffect(buff);
            for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharactersThreadSafe()) {
                    if (chr.getGuildId() == this.getPlayer().getGuildId()) {
                        mse.applyTo(chr, chr, true, null, duration);
                        chr.dropMessage(5, "Your guild has gotten a " + msg + " buff.");
                    }
                }
            }
        }
    }
    
    public boolean createAlliance(final String alliancename) {
        final MapleParty pt = this.c.getPlayer().getParty();
        final MapleCharacter otherChar = this.c.getChannelServer().getPlayerStorage().getCharacterById(pt.getMemberByIndex(1).getId());
        if (otherChar == null || otherChar.getId() == this.c.getPlayer().getId()) {
            return false;
        }
        try {
            return Alliance.createAlliance(alliancename, this.c.getPlayer().getId(), otherChar.getId(), this.c.getPlayer().getGuildId(), otherChar.getGuildId());
        }
        catch (Exception re) {
            return false;
        }
    }
    
    public boolean addCapacityToAlliance() {
        try {
            final MapleGuild gs = Guild.getGuild(this.c.getPlayer().getGuildId());
            if (gs != null && this.c.getPlayer().getGuildRank() == 1 && this.c.getPlayer().getAllianceRank() == 1 && Alliance.getAllianceLeader(gs.getAllianceId()) == this.c.getPlayer().getId() && Alliance.changeAllianceCapacity(gs.getAllianceId())) {
                this.gainMeso(-10000000);
                return true;
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    public boolean disbandAlliance() {
        try {
            final MapleGuild gs = Guild.getGuild(this.c.getPlayer().getGuildId());
            if (gs != null && this.c.getPlayer().getGuildRank() == 1 && this.c.getPlayer().getAllianceRank() == 1 && Alliance.getAllianceLeader(gs.getAllianceId()) == this.c.getPlayer().getId() && Alliance.disbandAlliance(gs.getAllianceId())) {
                return true;
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    public byte getLastMsg() {
        return this.lastMsg;
    }
    
    public final void setLastMsg(final byte last) {
        this.lastMsg = last;
    }
    
    @Override
    public void setPartyBossLog(final String bossid) {
        final MapleParty party = this.getPlayer().getParty();
        for (final MaplePartyCharacter pc : party.getMembers()) {
            final MapleCharacter chr = World.getStorage(this.getChannelNumber()).getCharacterById(pc.getId());
            if (chr != null) {
                chr.setBossLog(bossid);
            }
        }
    }
    
    public final void maxAllSkills() {
        for (final ISkill skil : SkillFactory.getAllSkills()) {
            if (GameConstants.isApplicableSkill(skil.getId())) {
                this.teachSkill(skil.getId(), skil.getMaxLevel(), skil.getMaxLevel());
            }
        }
    }
    
    public final void resetStats(final int str, final int dex, final int z, final int luk) {
        this.c.getPlayer().resetStats(str, dex, z, luk);
    }
    
    public final boolean dropItem(final int slot, final int invType, final int quantity) {
        final MapleInventoryType inv = MapleInventoryType.getByType((byte)invType);
        return inv != null && MapleInventoryManipulator.drop(this.c, inv, (short)slot, (short)quantity, true);
    }
    
    public final List<Integer> getAllPotentialInfo() {
        return new ArrayList<Integer>((Collection<? extends Integer>)MapleItemInformationProvider.getInstance().getAllPotentialInfo().keySet());
    }
    
    public final String getPotentialInfo(final int id) {
        final List<StructPotentialItem> potInfo = MapleItemInformationProvider.getInstance().getPotentialInfo(id);
        final StringBuilder builder = new StringBuilder("#b#ePOTENTIAL INFO FOR ID: ");
        builder.append(id);
        builder.append("#n#k\r\n\r\n");
        int minLevel = 1;
        int maxLevel = 10;
        for (final StructPotentialItem item : potInfo) {
            builder.append("#eLevels ");
            builder.append(minLevel);
            builder.append("~");
            builder.append(maxLevel);
            builder.append(": #n");
            builder.append(item.toString());
            minLevel += 10;
            maxLevel += 10;
            builder.append("\r\n");
        }
        return builder.toString();
    }
    
    public final void sendRPS() {
        this.c.sendPacket(MaplePacketCreator.getRPSMode((byte)8, -1, -1, -1));
    }
    
    public final void setQuestRecord(final Object ch, final int questid, final String data) {
        ((MapleCharacter)ch).getQuestNAdd(MapleQuest.getInstance(questid)).setCustomData(data);
    }
    
    public final void doWeddingEffect(final Object ch) {
        final MapleCharacter chr = (MapleCharacter)ch;
        this.getMap().broadcastMessage(MaplePacketCreator.yellowChat(chr.getName() + ", 妳願意承認 " + this.getPlayer().getName() + " 做妳的丈夫，誠實遵照上帝的誡命，和他生活在一起，無論在什麼環境願順服他、愛惜他、安慰他、尊重他保護他，以致奉召歸主？？"));
        CloneTimer.getInstance().schedule((Runnable)new Runnable() {
            @Override
            public void run() {
                if (chr == null || NPCConversationManager.this.getPlayer() == null) {
                    NPCConversationManager.this.warpMap(680000500, 0);
                }
                else {
                    NPCConversationManager.this.getMap().broadcastMessage(MaplePacketCreator.yellowChat(NPCConversationManager.this.getPlayer().getName() + ", 你願意承認接納 " + chr.getName() + " 做你的妻子，誠實遵照上帝的誡命，和她生活在一起，無論在什麼環境，願意終生養她、愛惜她、安慰她、尊重她、保護她，以至奉召歸主？？"));
                }
            }
        }, 10000L);
        CloneTimer.getInstance().schedule((Runnable)new Runnable() {
            @Override
            public void run() {
                if (chr == null || NPCConversationManager.this.getPlayer() == null) {
                    if (NPCConversationManager.this.getPlayer() != null) {
                        NPCConversationManager.this.setQuestRecord((Object)NPCConversationManager.this.getPlayer(), 160001, "3");
                        NPCConversationManager.this.setQuestRecord((Object)NPCConversationManager.this.getPlayer(), 160002, "0");
                    }
                    else if (chr != null) {
                        NPCConversationManager.this.setQuestRecord((Object)chr, 160001, "3");
                        NPCConversationManager.this.setQuestRecord((Object)chr, 160002, "0");
                    }
                    NPCConversationManager.this.warpMap(680000500, 0);
                }
                else {
                    NPCConversationManager.this.setQuestRecord((Object)NPCConversationManager.this.getPlayer(), 160001, "2");
                    NPCConversationManager.this.setQuestRecord((Object)chr, 160001, "2");
                    NPCConversationManager.this.sendNPCText(NPCConversationManager.this.getPlayer().getName() + " 和 " + chr.getName() + "， 我希望你們兩個能在此時此刻永遠愛著對方！", 9201002);
                    NPCConversationManager.this.getMap().startExtendedMapEffect("那麼現在請新娘親吻 " + NPCConversationManager.this.getPlayer().getName() + "！", 5120006);
                    if (chr.getGuildId() > 0) {
                        Guild.guildPacket(chr.getGuildId(), MaplePacketCreator.sendMarriage(false, chr.getName()));
                    }
                    if (chr.getFamilyId() > 0) {
                        Family.familyPacket(chr.getFamilyId(), MaplePacketCreator.sendMarriage(true, chr.getName()), chr.getId());
                    }
                    if (NPCConversationManager.this.getPlayer().getGuildId() > 0) {
                        Guild.guildPacket(NPCConversationManager.this.getPlayer().getGuildId(), MaplePacketCreator.sendMarriage(false, NPCConversationManager.this.getPlayer().getName()));
                    }
                    if (NPCConversationManager.this.getPlayer().getFamilyId() > 0) {
                        Family.familyPacket(NPCConversationManager.this.getPlayer().getFamilyId(), MaplePacketCreator.sendMarriage(true, chr.getName()), NPCConversationManager.this.getPlayer().getId());
                    }
                }
            }
        }, 20000L);
    }
    
    public void 开启小鋼珠(final int type) {
        this.c.sendPacket(MaplePacketCreator.openBeans(this.getPlayer().getBeans(), type));
    }
    
    public void worldMessage(final String text) {
        Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, text));
    }
    
    public int getBeans() {
        return this.getClient().getPlayer().getBeans();
    }
    
    public void warpBack(final int mid, final int retmap, final int time) {
        final MapleMap warpMap = this.c.getChannelServer().getMapFactory().getMap(mid);
        this.c.getPlayer().changeMap(warpMap, warpMap.getPortal(0));
        this.c.sendPacket(MaplePacketCreator.getClock(time));
        EventTimer.getInstance().schedule((Runnable)new Runnable() {
            @Override
            public void run() {
                final MapleMap warpMap = c.getChannelServer().getMapFactory().getMap(retmap);
                if (c.getPlayer() != null) {
                    c.sendPacket(MaplePacketCreator.stopClock());
                    c.getPlayer().changeMap(warpMap, warpMap.getPortal(0));
                    c.getPlayer().dropMessage(6, "已經到達目的地了!");
                }
            }
        }, (long)(1000 * time));
    }
    
    public void ChangeName(final String name) {
        this.getPlayer().setName(name);
        this.save();
        this.getPlayer().fakeRelog();
    }
    
    public String searchData(final int type, final String search) {
        return SearchGenerator.searchData(type, search);
    }
    
    public int[] getSearchData(final int type, final String search) {
        final Map<Integer, String> data = SearchGenerator.getSearchData(type, search);
        if (data.isEmpty()) {
            return null;
        }
        final int[] searches = new int[data.size()];
        int i = 0;
        final Iterator<Integer> iterator = data.keySet().iterator();
        while (iterator.hasNext()) {
            final int key = (int)Integer.valueOf(iterator.next());
            searches[i] = key;
            ++i;
        }
        return searches;
    }
    
    public boolean foundData(final int type, final String search) {
        return SearchGenerator.foundData(type, search);
    }
    
    public boolean ReceiveMedal() {
        final int acid = this.getPlayer().getAccountID();
        final int id = this.getPlayer().getId();
        final String name = this.getPlayer().getName();
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        final int item = 1142475;
        if (!this.getPlayer().canHold(item)) {
            return false;
        }
        if (this.getPlayer().haveItem(item)) {
            return false;
        }
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT id FROM RCmedals WHERE name = ?");
            ps.setString(1, name);
            final ResultSet rs = ps.executeQuery();
            if (!rs.first()) {
                return false;
            }
            ps.close();
            rs.close();
            ps = con.prepareStatement("Update RCmedals set amount = ? Where id = ?");
            ps.setInt(1, 0);
            ps.setInt(2, id);
            ps.execute();
            ps.close();
        }
        catch (Exception ex) {
            FilePrinter.printError("NPCConversationManager.txt", (Throwable)ex, "ReceiveMedal(" + name + ")");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        final IItem toDrop = ii.randomizeStats((Equip)ii.getEquipById(item));
        toDrop.setGMLog(this.getPlayer().getName() + " 領取勳章");
        MapleInventoryManipulator.addbyItem(this.c, toDrop);
        FileoutputUtil.logToFile("logs/Data/NPC領取勳章.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + this.c.getSession().remoteAddress().toString().split(":")[0] + " 账号: " + this.c.getAccountName() + " 玩家: " + this.c.getPlayer().getName() + " 領取了RC勳章");
        return true;
    }
    
    public String ShowJobRank(final int type) {
        final StringBuilder sb = new StringBuilder();
        final List<JobRankingInfo> Ranking = MapleGuildRanking.getInstance().getJobRank(type);
        if (Ranking != null) {
            int num = 0;
            for (final JobRankingInfo info : Ranking) {
                ++num;
                sb.append("#n#e#k排名:#r ");
                sb.append(num);
                sb.append("\r\n#n#e#k玩家名稱:#d ");
                sb.append(StringUtil.getRightPaddedStr(info.getName(), ' ', 13));
                sb.append("\r\n#n#e#k等級:#e#r ");
                sb.append(StringUtil.getRightPaddedStr(String.valueOf(info.getLevel()), ' ', 3));
                sb.append("\r\n#n#e#k职业:#e#b ");
                sb.append(MapleJob.getName(MapleJob.getById(info.getJob())));
                sb.append("\r\n#n#e#k力量:#e#d ");
                sb.append(StringUtil.getRightPaddedStr(String.valueOf(info.getStr()), ' ', 4));
                sb.append("\r\n#n#e#k敏捷:#e#d ");
                sb.append(StringUtil.getRightPaddedStr(String.valueOf(info.getDex()), ' ', 4));
                sb.append("\r\n#n#e#k智力:#e#d ");
                sb.append(StringUtil.getRightPaddedStr(String.valueOf(info.getInt()), ' ', 4));
                sb.append("\r\n#n#e#k幸運:#e#d ");
                sb.append(StringUtil.getRightPaddedStr(String.valueOf(info.getLuk()), ' ', 4));
                sb.append("\r\n");
                sb.append("#n#k======================================================\r\n");
            }
        }
        else {
            sb.append("#r查詢無任何結果唷");
        }
        return sb.toString();
    }
    
    public static boolean hairExists(final int hair) {
        return MapleItemInformationProvider.hairList.containsKey((Object)Integer.valueOf(hair));
    }
    
    public int[] getCanHair(final int[] hairs) {
        final List<Integer> canHair = new ArrayList<Integer>();
        final List<Integer> cantHair = new ArrayList<Integer>();
        for (final int hair : hairs) {
            if (hairExists(hair)) {
                canHair.add(Integer.valueOf(hair));
            }
            else {
                cantHair.add(Integer.valueOf(hair));
            }
        }
        if (cantHair.size() > 0 && this.c.getPlayer().isAdmin()) {
            final StringBuilder sb = new StringBuilder("正在讀取的发型裏有");
            sb.append(cantHair.size()).append("個发型客戶端不支持顯示，已經被清除：");
            for (int i = 0; i < cantHair.size(); ++i) {
                sb.append((Object)cantHair.get(i));
                if (i < cantHair.size() - 1) {
                    sb.append(",");
                }
            }
            this.playerMessage(sb.toString());
        }
        final int[] getHair = new int[canHair.size()];
        for (int i = 0; i < canHair.size(); ++i) {
            getHair[i] = (int)Integer.valueOf(canHair.get(i));
        }
        return getHair;
    }
    
    public static boolean faceExists(final int face) {
        return MapleItemInformationProvider.faceLists.containsKey((Object)Integer.valueOf(face));
    }
    
    public int[] getCanFace(final int[] faces) {
        final List<Integer> canFace = new ArrayList<Integer>();
        final List<Integer> cantFace = new ArrayList<Integer>();
        for (final int face : faces) {
            if (faceExists(face)) {
                canFace.add(Integer.valueOf(face));
            }
            else {
                cantFace.add(Integer.valueOf(face));
            }
        }
        if (cantFace.size() > 0 && this.c.getPlayer().isAdmin()) {
            final StringBuilder sb = new StringBuilder("正在讀取的脸型裏有");
            sb.append(cantFace.size()).append("個脸型客戶端不支持顯示，已經被清除：");
            for (int i = 0; i < cantFace.size(); ++i) {
                sb.append((Object)cantFace.get(i));
                if (i < cantFace.size() - 1) {
                    sb.append(",");
                }
            }
            this.playerMessage(sb.toString());
        }
        final int[] getFace = new int[canFace.size()];
        for (int i = 0; i < canFace.size(); ++i) {
            getFace[i] = (int)Integer.valueOf(canFace.get(i));
        }
        return getFace;
    }
    
    public String checkDrop(final int mobId) {
        final List<MonsterDropEntry> ranks = MapleMonsterInformationProvider.getInstance().retrieveDrop(mobId);
        if (ranks != null && ranks.size() > 0) {
            int num = 0;
            int itemId = 0;
            int ch = 0;
            final StringBuilder name = new StringBuilder();
            for (int i = 0; i < ranks.size(); ++i) {
                final MonsterDropEntry de = (MonsterDropEntry)ranks.get(i);
                if (de.chance > 0 && (de.questid <= 0 || (de.questid > 0 && MapleQuest.getInstance((int)de.questid).getName().length() > 0))) {
                    itemId = de.itemId;
                    if (num == 0) {
                        name.append("當前怪物 #o" + mobId + "# 的爆率為:\r\n");
                        name.append("--------------------------------------\r\n");
                    }
                    String namez = "#z" + itemId + "#";
                    if (itemId == 0) {
                        itemId = 4031041;
                        namez = de.Minimum * this.getClient().getChannelServer().getMesoRate() + " 到 " + de.Maximum * this.getClient().getChannelServer().getMesoRate() + " 金币";
                    }
                    ch = de.chance * this.getClient().getChannelServer().getDropRate();
                    name.append(num + 1 + ") #v" + itemId + "#" + namez + ((de.questid > 0 && MapleQuest.getInstance((int)de.questid).getName().length() > 0) ? ("需要接受任務 " + MapleQuest.getInstance((int)de.questid).getName() + "") : "") + "\r\n");
                    ++num;
                }
            }
            if (name.length() > 0) {
                return name.toString();
            }
        }
        return "沒有當前怪物的爆率数据。";
    }
    
    public String checkDrop(final MapleCharacter chr, final int mobId, final boolean GM) {
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        final List<MonsterDropEntry> ranks = MapleMonsterInformationProvider.getInstance().retrieveDrop(mobId);
        if (ranks != null && ranks.size() > 0) {
            int num = 0;
            int itemId = 0;
            int ch = 0;
            final StringBuilder name = new StringBuilder();
            final StringBuilder error = new StringBuilder();
            name.append("【#r#o" + mobId + "##k】掉寶物品查詢列表:#b\r\n");
            for (int i = 0; i < ranks.size(); ++i) {
                final MonsterDropEntry de = (MonsterDropEntry)ranks.get(i);
                if (de.chance > 0 && (de.questid <= 0 || (de.questid > 0 && MapleQuest.getInstance((int)de.questid).getName().length() > 0))) {
                    itemId = de.itemId;
                    String namez = "#z" + itemId + "#";
                    if (itemId == 0) {
                        itemId = 4031041;
                        namez = de.Minimum * this.getClient().getChannelServer().getMesoRate() + " to " + de.Maximum * this.getClient().getChannelServer().getMesoRate() + " #b金币#l#k";
                    }
                    else if (itemId != 0 && ii.itemExists(itemId)) {
                        ch = de.chance * this.getClient().getChannelServer().getDropRate();
                        if (!GM) {
                            name.append("#k" + (num + 1) + ": #v" + itemId + "# " + namez + (chr.isGM() ? ("#d  掉落機率：" + (double)Integer.valueOf((ch >= 999999) ? 1000000 : ch) / 10000.0 + "%\r\n") : "\r\n") + "#b(掉落條件:" + ((de.questid > 0 && MapleQuest.getInstance((int)de.questid).getName().length() > 0) ? ("需要接取任務#r " + MapleQuest.getInstance((int)de.questid).getName() + " #b)\r\n") : "#r無#b)") + "\r\n");
                        }
                        else {
                            name.append("#L" + itemId + "##k" + (num + 1) + ": #v" + itemId + "# " + namez + (chr.isGM() ? ("#d  掉落機率：" + (double)Integer.valueOf((ch >= 999999) ? 1000000 : ch) / 10000.0 + "%(點選更改)\r\n") : "\r\n") + "#b(掉落條件:" + ((de.questid > 0 && MapleQuest.getInstance((int)de.questid).getName().length() > 0) ? ("需要接取任務#r " + MapleQuest.getInstance((int)de.questid).getName() + " #b)\r\n") : "#r無#b)") + "\r\n");
                        }
                        ++num;
                    }
                    else {
                        error.append(itemId + "\r\n");
                    }
                }
            }
            if (GM) {
                name.append("\r\n#L10000##k" + (num + 1) + ": #b我要額外新增掉落物品!");
            }
            if (error.length() > 0) {
                chr.dropMessage(1, "無效的物品ID:\r\n" + error.toString());
            }
            if (name.length() > 0) {
                return name.toString();
            }
        }
        return "該怪物查無任何掉寶数据。";
    }
    
    public void gainBeans(final int s) {
        this.getPlayer().gainBeans(s);
        this.c.getSession().write((Object)MaplePacketCreator.updateBeans(this.c.getPlayer()));
    }
    
    public void openBeans() {
        this.c.getSession().write((Object)MaplePacketCreator.openBeans(this.getPlayer().getBeans(), 0));
        this.c.getPlayer().dropMessage(5, "按住左右鍵可以調整力道,建議調好角度一路給他打,不要按暫停若九宮格卡住沒反應請離開在近來");
    }
    
    public void setMonsterRiding(final int itemid) {
        final short src = this.getClient().getPlayer().haveItemPos(itemid);
        if (src == 100) {
            this.c.getPlayer().dropMessage(5, "你沒有當前坐騎。");
        }
        else {
            MapleInventoryManipulator.equip(this.c, src, (short)(-18));
            this.c.getPlayer().dropMessage(5, "装备坐騎成功。");
        }
    }
    
    public int getRandom(final int... args_all) {
        final int args = args_all[Randomizer.nextInt(args_all.length)];
        return args;
    }
    
    public void OwlAdv(final int point, final int itemid) {
        owlse(this.c, point, itemid);
    }
    
    public static void owlse(final MapleClient c, final int point, final int itemid) {
        final int itemSearch = itemid;
        final List<HiredMerchant> hms = new ArrayList<HiredMerchant>();
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            if (!cserv.searchMerchant(itemSearch).isEmpty()) {
                hms.addAll((Collection<? extends HiredMerchant>)cserv.searchMerchant(itemSearch));
            }
        }
        if (hms.size() > 0) {
            if (c.getPlayer().haveItem(5230000, 1)) {
                MapleInventoryManipulator.removeById(c, MapleInventoryType.CASH, 5230000, 1, true, false);
            }
            else if (c.getPlayer().getCSPoints(point) >= 5) {
                c.getPlayer().modifyCSPoints(point, -5, true);
            }
            else {
                c.getPlayer().dropMessage(1, "點數不足，無法查詢！");
                if (NPCScriptManager.getInstance().getCM(c) != null) {
                    NPCScriptManager.getInstance().dispose(c);
                    c.sendPacket(MaplePacketCreator.enableActions());
                }
            }
            if (NPCScriptManager.getInstance().getCM(c) != null) {
                NPCScriptManager.getInstance().dispose(c);
            }
            c.sendPacket(MaplePacketCreator.getOwlSearched(itemSearch, hms));
        }
        else {
            if (NPCScriptManager.getInstance().getCM(c) != null) {
                NPCScriptManager.getInstance().dispose(c);
                c.sendPacket(MaplePacketCreator.enableActions());
            }
            c.getPlayer().dropMessage(1, "找不到物品");
        }
    }
    
    public void checkMobs(final MapleCharacter chr) {
        if (this.getMap().getAllMonstersThreadsafe().size() <= 0) {
            this.sendOk("#地图上沒有怪物哦!!。");
            this.dispose();
        }
        String msg = "玩家 #b" + chr.getName() + "#k 此地图怪物掉寶查詢:\r\n#r(若有任何掉寶問題,請至社團BUG區回報怪物名稱和代码)\r\n#d";
        for (final Object monsterid : this.getMap().getAllUniqueMonsters()) {
            msg = msg + "#L" + monsterid + "##o" + monsterid + "# 代码:" + monsterid + " (查看)#l\r\n";
        }
        this.sendOk(msg);
    }
    
    public void getMobs(final int itemid) {
        final MapleMonsterInformationProvider mi = MapleMonsterInformationProvider.getInstance();
        final List<Integer> mobs = MapleMonsterInformationProvider.getInstance().getMobByItem(itemid);
        String text = "#d這些怪物会掉落您查詢的物品#k: \r\n\r\n";
        for (int i = 0; i < mobs.size(); ++i) {
            int quest = 0;
            if (mi.getDropQuest((int)Integer.valueOf(mobs.get(i))) > 0) {
                quest = mi.getDropQuest((int)Integer.valueOf(mobs.get(i)));
            }
            final int chance = mi.getDropChance((int)Integer.valueOf(mobs.get(i))) * this.getClient().getChannelServer().getDropRate();
            text = text + "#r#o" + (Object)mobs.get(i) + "##k " + ((quest > 0 && MapleQuest.getInstance(quest).getName().length() > 0) ? ("#b需要進行 " + MapleQuest.getInstance(quest).getName() + " 任務來取得#k") : "") + "\r\n";
        }
        this.sendNext(text);
    }
    
    public Gashapon getGashapon() {
        return GashaponFactory.getInstance().getGashaponByNpcId(this.getNpc());
    }
    
    public void getGachaponMega(final String msg, final Item item, final int quantity) {
        Broadcast.broadcastGashponmega(MaplePacketCreator.getGachaponMega(this.c.getPlayer().getName(), " : x" + quantity + "恭喜玩家 " + this.c.getPlayer().getName() + " 在" + msg + "获得！", (IItem)item, (byte)1, this.c.getPlayer().getClient().getChannel()));
    }
    
    public void EnterCS(final int mod) {
        this.c.getPlayer().setCsMod(mod);
        InterServerHandler.EnterCashShop(this.c, this.c.getPlayer(), false);
    }
    
    public int[] getSavedFaces() {
        return this.getPlayer().getSavedFaces();
    }
    
    public int getSavedFace(final int sel) {
        return this.getPlayer().getSavedFace(sel);
    }
    
    public void setSavedFace(final int sel, final int id) {
        this.getPlayer().setSavedFace(sel, id);
    }
    
    public int[] getSavedHairs() {
        return this.getPlayer().getSavedHairs();
    }
    
    public int getSavedHair(final int sel) {
        return this.getPlayer().getSavedHair(sel);
    }
    
    public void setSavedHair(final int sel, final int id) {
        this.getPlayer().setSavedHair(sel, id);
    }
    
    public int 获取推广人ID() {
        int 推广人ID = 0;
        try {
            final int cid = this.getPlayer().getAccountID();
            final Connection con = DatabaseConnection.getConnection();
            ResultSet rs;
            try (final PreparedStatement limitCheck = con.prepareStatement("SELECT * FROM accounts WHERE id=" + cid + "")) {
                rs = limitCheck.executeQuery();
                if (rs.next()) {
                    推广人ID = rs.getInt("推广人ID");
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            ex.getStackTrace();
        }
        return 推广人ID;
    }
    
    public void 写入推广人ID(final int slot) {
        try {
            final int cid = this.getPlayer().getAccountID();
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("UPDATE accounts SET 推广人ID = " + slot + " WHERE id = " + cid + "");
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    public int 获取推广值() {
        int 推广值 = 0;
        try {
            final int cid = this.getPlayer().getAccountID();
            final Connection con = DatabaseConnection.getConnection();
            ResultSet rs;
            try (final PreparedStatement limitCheck = con.prepareStatement("SELECT * FROM accounts WHERE id=" + cid + "")) {
                rs = limitCheck.executeQuery();
                if (rs.next()) {
                    推广值 = rs.getInt("推广值");
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            ex.getStackTrace();
        }
        return 推广值;
    }
    
    public void 写入推广值(final int slot) {
        try {
            final int cid = this.获取推广人ID();
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("UPDATE accounts SET 推广值 = 推广值 + " + slot + " WHERE id = " + cid + "");
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    public void 更改推广值(final int slot) {
        try {
            final int cid = this.getPlayer().getAccountID();
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("UPDATE accounts SET 推广值 = 推广值+" + slot + " WHERE id = " + cid + "");
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    @Override
    public String getServerName() {
        return ServerConfig.SERVERNAME;
    }
    
    public String 开服名字() {
        return this.c.getChannelServer().getServerName();
    }
    
    public String 显示物品(final int a) {
        String data = "";
        data = "#v" + a + "# #b#z" + a + "##k";
        return data;
    }
    
    public void 刷新状态() {
        this.c.getPlayer().getClient().getSession().write((Object)MaplePacketCreator.getCharInfo(this.c.getPlayer()));
        this.c.getPlayer().getMap().removePlayer(this.c.getPlayer());
        this.c.getPlayer().getMap().addPlayer(this.c.getPlayer());
        this.c.getSession().write((Object)MaplePacketCreator.enableActions());
    }
    
    public void 刷新地图() {
        final boolean custMap = true;
        final int mapid = this.c.getPlayer().getMapId();
        final MapleMap map = custMap ? this.c.getPlayer().getClient().getChannelServer().getMapFactory().getMap(mapid) : this.c.getPlayer().getMap();
        if (this.c.getPlayer().getClient().getChannelServer().getMapFactory().destroyMap(mapid)) {
            final MapleMap newMap = this.c.getPlayer().getClient().getChannelServer().getMapFactory().getMap(mapid);
            final MaplePortal newPor = newMap.getPortal(0);
            final LinkedHashSet<MapleCharacter> mcs = new LinkedHashSet<MapleCharacter>((Collection<? extends MapleCharacter>)map.getCharacters());
            for (final MapleCharacter m : mcs) {
                int x = 0;
                while (x < 5) {
                    try {
                        m.changeMap(newMap, newPor);
                    }
                    catch (Throwable t) {
                        ++x;
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    public void 说明文字(final String text) {
        if (this.lastMsg > -1) {
            return;
        }
        if (text.contains((CharSequence)"#L")) {
            this.sendSimple(text);
            return;
        }
        this.c.sendPacket(MaplePacketCreator.getNPCTalk(this.npc, (byte)0, text, "00 00", (byte)0));
        this.lastMsg = 0;
    }
    
    public void 是否说明文字(final String text) {
        if (this.lastMsg > -1) {
            return;
        }
        if (text.contains((CharSequence)"#L")) {
            this.sendSimple(text);
            return;
        }
        this.c.sendPacket(MaplePacketCreator.getNPCTalk(this.npc, (byte)1, text, "", (byte)0));
        this.lastMsg = 1;
    }
    
    public int 在线人数() {
        int count = 0;
        for (final ChannelServer chl : ChannelServer.getAllInstances()) {
            count += chl.getPlayerStorage().getAllCharacters().size();
        }
        return count;
    }
    
    public void 打开网页(final String web) {
        this.c.sendPacket(MaplePacketCreator.openWeb(web));
    }
    
    public void 给技能(final int action, final byte level, final byte masterlevel) {
        this.c.getPlayer().changeSkillLevel(SkillFactory.getSkill(action), level, masterlevel);
    }
    
    public void 对话结束() {
        NPCScriptManager.getInstance().dispose(this.c);
    }
    
    public int 判断豆豆数量() {
        return this.getClient().getPlayer().getBeans();
    }
    
    public void 给豆豆(final int s) {
        this.getPlayer().gainBeans(s);
        this.c.sendPacket(MaplePacketCreator.updateBeans(this.c.getPlayer()));
    }
    
    public void 收豆豆(final int s) {
        this.getPlayer().gainBeans(-s);
        this.c.sendPacket(MaplePacketCreator.updateBeans(this.c.getPlayer()));
    }
    
    public void 强化加卷次数(final int upgr) {
        final Equip item = (Equip)this.c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short)1).copy();
        item.setUpgradeSlots((byte)(item.getUpgradeSlots() + upgr));
        MapleInventoryManipulator.removeFromSlot(this.getC(), MapleInventoryType.EQUIP, (short)1, (short)1, true);
        MapleInventoryManipulator.addFromDrop(this.getChar().getClient(), (IItem)item, false);
    }
    
    public void gainEquiPproperty(final int upgr, final int watk, final int matk, final int str, final int dex, final int Int, final int luk, final int hp, final int mp, final int acc, final int avoid) {
        final Equip item = (Equip)this.c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short)1).copy();
        item.setUpgradeSlots((byte)(item.getUpgradeSlots() + upgr));
        item.setWatk((short)(item.getWatk() + watk));
        item.setMatk((short)(item.getMatk() + matk));
        item.setStr((short)(item.getStr() + str));
        item.setDex((short)(item.getDex() + dex));
        item.setInt((short)(item.getInt() + Int));
        item.setLuk((short)(item.getLuk() + luk));
        item.setHp((short)(item.getHp() + hp));
        item.setMp((short)(item.getMp() + mp));
        item.setAcc((short)(byte)(item.getAcc() + acc));
        item.setAvoid((short)(byte)(item.getAvoid() + avoid));
        MapleInventoryManipulator.removeFromSlot(this.getC(), MapleInventoryType.EQUIP, (short)1, (short)1, true);
        MapleInventoryManipulator.addFromDrop(this.getChar().getClient(), (IItem)item, false);
    }
    
    public String 显示装备属性() {
        final StringBuilder name = new StringBuilder();
        final Equip item = (Equip)this.c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short)1).copy();
        if (item.getUpgradeSlots() > 0) {
            name.append("升级次数:#b" + (int)item.getUpgradeSlots() + "#k\r\n");
        }
        if (item.getWatk() > 0) {
            name.append("物理攻击力:#b" + (int)item.getWatk() + "#k\r\n");
        }
        if (item.getMatk() > 0) {
            name.append("魔法攻击力:#b" + (int)item.getMatk() + "#k\r\n");
        }
        if (item.getWdef() > 0) {
            name.append("物理防御力:#b" + (int)item.getWdef() + "#k\r\n");
        }
        if (item.getMdef() > 0) {
            name.append("魔法防御力:#b" + (int)item.getMdef() + "#k\r\n");
        }
        if (item.getStr() > 0) {
            name.append("力量:#b" + (int)item.getStr() + "#k\r\n");
        }
        if (item.getDex() > 0) {
            name.append("敏捷:#b" + (int)item.getDex() + "#k\r\n");
        }
        if (item.getLuk() > 0) {
            name.append("运气:#b" + (int)item.getLuk() + "#k\r\n");
        }
        if (item.getInt() > 0) {
            name.append("智力:#b" + (int)item.getInt() + "#k\r\n");
        }
        if (item.getHp() > 0) {
            name.append("HP:#b" + (int)item.getHp() + "#k\r\n");
        }
        if (item.getMp() > 0) {
            name.append("MP:#b" + (int)item.getMp() + "#k\r\n");
        }
        if (item.getAcc() > 0) {
            name.append("命中率:#b" + (int)item.getAcc() + "#k\r\n");
        }
        if (item.getAvoid() > 0) {
            name.append("闪避率:#b" + (int)item.getAvoid() + "#k\r\n");
        }
        if (item.getSpeed() > 0) {
            name.append("移动速度:#b" + (int)item.getSpeed() + "#k\r\n");
        }
        return name.toString();
    }
    
    public void 强化穿戴装备(final int aa, final int bb, final int cc, final int dd) {
        final Equip item = (Equip)this.c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short)aa).copy();
    }
    
    public void 打开商店(final int id) {
        MapleShopFactory.getInstance().getShop(id).sendShop(this.c);
    }
    
    public int getLevel() {
        return this.getPlayer().getLevel();
    }
    
    public void 设置等级(final int s) {
        this.c.getPlayer().setLevel((short)s);
    }
    
    public int 判断等级() {
        return this.getPlayer().getLevel();
    }
    
    public void 刷新() {
        final MapleCharacter player = this.c.getPlayer();
        this.c.sendPacket(MaplePacketCreator.getCharInfo(player));
        player.getMap().removePlayer(player);
        player.getMap().addPlayer(player);
    }
    
    public int 判断金币() {
        return this.getPlayer().getMeso();
    }
    
    public int 判断角色ID() {
        return this.c.getPlayer().getId();
    }
    
    public int 判断点券() {
        return this.c.getPlayer().getCSPoints(1);
    }
    
    public int 判断抵用券() {
        return this.c.getPlayer().getCSPoints(2);
    }
    
    public int 判断声望() {
        return this.getPlayer().getCurrentRep();
    }
    
    public int 判断学院() {
        return this.getPlayer().getFamilyId();
    }
    
    public int 判断师傅() {
        return this.getPlayer().getSeniorId();
    }
    
    public void 给声望(final int s) {
        this.c.getPlayer().setCurrentRep(s);
    }
    
    public void 组队人数() {
        if (this.getParty() != null) {
            this.c.getPlayer().getParty().getMembers().size();
        }
    }
    
    public int 判断经验() {
        return this.c.getPlayer().getExp();
    }
    
    public int 判断当前地图怪物数量() {
        return this.c.getPlayer().getMap().getAllMonstersThreadsafe().size();
    }
    
    public int 判断指定地图怪物数量(final int a) {
        return this.getMap(a).getAllMonstersThreadsafe().size();
    }
    
    public int 判断当前地图玩家数量() {
        return this.c.getPlayer().getMap().getCharactersSize();
    }
    
    public int 随机数(final int a) {
        return (int)Math.ceil(Math.random() * (double)a);
    }
    
    @Override
    public int 获取当前星期() {
        return Calendar.getInstance().get(7);
    }
    
    public void 公告(final int lx, final String msg) {
        switch (lx) {
            case 1: {
                Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(11, this.c.getChannel(), "[" + ServerConfig.SERVERNAME + "] : " + msg));
                break;
            }
            case 2: {
                Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(12, this.c.getChannel(), "[" + ServerConfig.SERVERNAME + "] : " + msg));
                break;
            }
            case 3: {
                Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(3, this.c.getChannel(), "[" + ServerConfig.SERVERNAME + "] : " + msg));
                break;
            }
        }
    }
    
    public void 通知(final String text) {
        Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, text));
    }
    
    public void 喇叭(final int lx, final String msg) {
        switch (lx) {
            case 1: {
                Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(11, this.c.getChannel(), "[" + ServerConfig.SERVERNAME + "] : " + msg));
                break;
            }
            case 2: {
                Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(12, this.c.getChannel(), "[" + ServerConfig.SERVERNAME + "] : " + msg));
                break;
            }
            case 3: {
                Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(3, this.c.getChannel(), "[" + ServerConfig.SERVERNAME + "] : " + msg));
                break;
            }
            case 4: {
                Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(9, this.c.getChannel(), "[" + ServerConfig.SERVERNAME + "] : " + msg));
                break;
            }
            case 5: {
                Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(2, this.c.getChannel(), "[" + ServerConfig.SERVERNAME + "] : " + msg));
                break;
            }
        }
    }
    
    public void 组队征集喇叭(final int lx, final String msg) {
        switch (lx) {
            case 1: {
                Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(11, this.c.getChannel(), "[组队征集令]  : " + msg));
                break;
            }
            case 2: {
                Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(12, this.c.getChannel(), "[组队征集令] : " + msg));
                break;
            }
            case 3: {
                Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(3, this.c.getChannel(), "[组队征集令] :" + msg));
                break;
            }
            case 4: {
                Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(9, this.c.getChannel(), "[组队征集令] : " + msg));
                break;
            }
            case 5: {
                Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(2, this.c.getChannel(), "[组队征集令] :" + msg));
                break;
            }
        }
    }
    
    public static String SN取出售(final int id) {
        String data = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT Point as DATA FROM character7 WHERE Name = ? && channel = 1");
            ps.setInt(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取角色ID取名字出错 - 数据库查询失败：" + (Object)Ex);
        }
        if (data == null) {
            data = "匿名人士";
        }
        return data;
    }
    
    public static String SN取库存(final int id) {
        String data = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT Point as DATA FROM character7 WHERE Name = ? &&  channel = 2");
            ps.setInt(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取角色ID取名字出错 - 数据库查询失败：" + (Object)Ex);
        }
        if (data == null) {
            data = "匿名人士";
        }
        return data;
    }
    
    public static String SN取折扣(final int id) {
        String data = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT Point as DATA FROM character7 WHERE Name = ? &&  channel = 3");
            ps.setInt(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取角色ID取名字出错 - 数据库查询失败：" + (Object)Ex);
        }
        if (data == null) {
            data = "匿名人士";
        }
        return data;
    }
    
    public static String SN取限购(final int id) {
        String data = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT Point as DATA FROM character7 WHERE Name = ? &&  channel = 4");
            ps.setInt(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取角色ID取名字出错 - 数据库查询失败：" + (Object)Ex);
        }
        if (data == null) {
            data = "匿名人士";
        }
        return data;
    }
    
    public static String SN取类型(final int id) {
        String data = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT Point as DATA FROM character7 WHERE Name = ? &&  channel = 5");
            ps.setInt(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取角色ID取名字出错 - 数据库查询失败：" + (Object)Ex);
        }
        if (data == null) {
            data = "匿名人士";
        }
        return data;
    }
    
    public static int 角色名字取ID(final String id) {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT id as DATA FROM characters WHERE name = ?");
            ps.setString(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取角色名字取ID出错 - 数据库查询失败：" + (Object)Ex);
        }
        return data;
    }
    
    public static String 角色ID取名字(final int id) {
        String data = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT name as DATA FROM characters WHERE id = ?");
            ps.setInt(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取角色ID取名字出错 - 数据库查询失败：" + (Object)Ex);
        }
        if (data == null) {
            data = "匿名人士";
        }
        return data;
    }
    
    public static int 角色名字取账号ID(final String id) {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT accountid as DATA FROM characters WHERE name = ?");
            ps.setString(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取角色名字取ID出错 - 数据库查询失败：" + (Object)Ex);
        }
        return data;
    }
    
    public static String IP取账号(final String id) {
        String data = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT name as DATA FROM accounts WHERE SessionIP = ?");
            ps.setString(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                    return data;
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取角色ID取名字出错 - 数据库查询失败：" + (Object)Ex);
        }
        if (data == null) {
            data = "匿名人士";
        }
        return data;
    }
    
    public static String MAC取账号(final String id) {
        String data = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT name as DATA FROM accounts WHERE macs = ?");
            ps.setString(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取角色ID取名字出错 - 数据库查询失败：" + (Object)Ex);
        }
        if (data == null) {
            data = "匿名人士";
        }
        return data;
    }
    
    public static String 账号ID取账号(final String id) {
        String data = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT name as DATA FROM accounts WHERE id = ?");
            ps.setString(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取角色名字取ID出错 - 数据库查询失败：" + (Object)Ex);
        }
        return data;
    }
    
    public static String 账号ID取在线(final int id) {
        String data = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT loggedin as DATA FROM accounts WHERE id = ?");
            ps.setInt(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取角色名字取ID出错 - 数据库查询失败：" + (Object)Ex);
        }
        return data;
    }
    
    public static String 角色名字取等级(final String id) {
        String data = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT level as DATA FROM characters WHERE name = ?");
            ps.setString(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取角色名字取ID出错 - 数据库查询失败：" + (Object)Ex);
        }
        if (data == null) {
            data = "匿名人士";
        }
        return data;
    }
    
    public static String 物品获取掉落怪物(final int itemid) {
        String data = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT dropperid as DATA FROM drop_data WHERE itemid = ?");
            ps.setInt(1, itemid);
            try (final ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取物品获取掉落怪物出错 - 数据库查询失败：" + (Object)Ex);
        }
        return data;
    }
    
    public static String 获取家族名称(final int guildId) {
        String data = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT name as DATA FROM guilds WHERE guildid = ?");
            ps.setInt(1, guildId);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取家族名称出错 - 数据库查询失败：" + (Object)Ex);
        }
        return data;
    }
    
    public static String 获取最高等级玩家名字() {
        String name = "";
        String level = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT `name`, `level` FROM characters WHERE gm = 0 ORDER BY `level` DESC LIMIT 1");
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("name");
                    level = rs.getString("level");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取家族名称出错 - 数据库查询失败：" + (Object)Ex);
        }
        return String.format("%s", name);
    }
    
    public int 角色ID取雇佣数据(final int id) {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT cid as DATA FROM hire WHERE cid = ?");
            ps.setInt(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("角色名字取账号ID、出错");
        }
        return data;
    }
    
    public static int 角色ID取账号ID(final int id) {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT accountid as DATA FROM characters WHERE id = ?");
            ps.setInt(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("角色名字取账号ID、出错");
        }
        return data;
    }
    
    public static String 账号ID取绑定QQ(final int id) {
        String data = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT qq as DATA FROM accounts WHERE id = ?");
            ps.setInt(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("账号ID取账号、出错");
        }
        return data;
    }
    
    public int getzs() {
        return this.getPlayer().getzs();
    }
    
    public void setzs(final int set) {
        this.getPlayer().setzs(set);
    }
    
    public void gainzs(final int gain) {
        this.getPlayer().gainzs(gain);
    }
    
    public int getjf() {
        return this.getPlayer().getjf();
    }
    
    public void setjf(final int set) {
        this.getPlayer().setjf(set);
    }
    
    public void gainjf(final int gain) {
        this.getPlayer().gainjf(gain);
    }
    
    public int getzdjf() {
        return this.getPlayer().getzdjf();
    }
    
    public void setzdjf(final int set) {
        this.getPlayer().setzdjf(set);
    }
    
    public void gainzdjf(final int gain) {
        this.getPlayer().gainzdjf(gain);
    }
    
    public int getrwjf() {
        return this.getPlayer().getrwjf();
    }
    
    public void setrwjf(final int set) {
        this.getPlayer().setrwjf(set);
    }
    
    public void gainrwjf(final int gain) {
        this.getPlayer().gainrwjf(gain);
    }
    
    public int getcz() {
        return this.getPlayer().getcz();
    }
    
    public void setcz(final int set) {
        this.getPlayer().setcz(set);
    }
    
    public void gaincz(final int gain) {
        this.getPlayer().gaincz(gain);
    }
    
    public int getdy() {
        return this.getPlayer().getdy();
    }
    
    public void setdy(final int set) {
        this.getPlayer().setdy(set);
    }
    
    public void gaindy(final int gain) {
        this.getPlayer().gaindy(gain);
    }
    
    public int getrmb() {
        return this.getPlayer().getrmb();
    }
    
    public void setrmb(final int set) {
        this.getPlayer().setrmb(set);
    }
    
    public void gainrmb(final int gain) {
        this.getPlayer().gainrmb(gain);
    }
    
    public int getyb() {
        return this.getPlayer().getyb();
    }
    
    public void setyb(final int set) {
        this.getPlayer().setyb(set);
    }
    
    public void gainyb(final int gain) {
        this.getPlayer().gainyb(gain);
    }
    
    public int getplayerPoints() {
        return this.getPlayer().getplayerPoints();
    }
    
    public void setplayerPoints(final int set) {
        this.getPlayer().setplayerPoints(set);
    }
    
    public void gainplayerPoints(final int gain) {
        this.getPlayer().gainplayerPoints(gain);
    }
    
    public int getplayerEnergy() {
        return this.getPlayer().getplayerEnergy();
    }
    
    public void setplayerEnergy(final int set) {
        this.getPlayer().setplayerEnergy(set);
    }
    
    public void gainplayerEnergy(final int gain) {
        this.getPlayer().gainplayerEnergy(gain);
    }
    
    public int getjf1() {
        return this.getPlayer().getjf1();
    }
    
    public void setjf1(final int set) {
        this.getPlayer().setjf1(set);
    }
    
    public void gainjf1(final int gain) {
        this.getPlayer().gainjf1(gain);
    }
    
    public int getjf2() {
        return this.getPlayer().getjf2();
    }
    
    public void setjf2(final int set) {
        this.getPlayer().setjf2(set);
    }
    
    public void gainjf2(final int gain) {
        this.getPlayer().gainjf2(gain);
    }
    
    public int getjf3() {
        return this.getPlayer().getjf3();
    }
    
    public void setjf3(final int set) {
        this.getPlayer().setjf3(set);
    }
    
    public void gainjf3(final int gain) {
        this.getPlayer().gainjf3(gain);
    }
    
    public int getjf4() {
        return this.getPlayer().getjf4();
    }
    
    public void setjf4(final int set) {
        this.getPlayer().setjf4(set);
    }
    
    public void gainjf4(final int gain) {
        this.getPlayer().gainjf4(gain);
    }
    
    public int getjf5() {
        return this.getPlayer().getjf5();
    }
    
    public void setjf5(final int set) {
        this.getPlayer().setjf5(set);
    }
    
    public void gainjf5(final int gain) {
        this.getPlayer().gainjf5(gain);
    }
    
    public int getjf6() {
        return this.getPlayer().getjf6();
    }
    
    public void setjf6(final int set) {
        this.getPlayer().setjf6(set);
    }
    
    public void gainjf6(final int gain) {
        this.getPlayer().gainjf6(gain);
    }
    
    public int getjf7() {
        return this.getPlayer().getjf7();
    }
    
    public void setjf7(final int set) {
        this.getPlayer().setjf7(set);
    }
    
    public void gainjf7(final int gain) {
        this.getPlayer().gainjf7(gain);
    }
    
    public int getjf8() {
        return this.getPlayer().getjf8();
    }
    
    public void setjf8(final int set) {
        this.getPlayer().setjf8(set);
    }
    
    public void gainjf8(final int gain) {
        this.getPlayer().gainjf8(gain);
    }
    
    public int getjf9() {
        return this.getPlayer().getjf9();
    }
    
    public void setjf9(final int set) {
        this.getPlayer().setjf9(set);
    }
    
    public void gainjf9(final int gain) {
        this.getPlayer().gainjf9(gain);
    }
    
    public int getjf10() {
        return this.getPlayer().getjf10();
    }
    
    public void setjf10(final int set) {
        this.getPlayer().setjf10(set);
    }
    
    public void gainjf10(final int gain) {
        this.getPlayer().gainjf10(gain);
    }
    
    public void 个人存档() {
        this.c.getPlayer().saveToDB(false, false);
    }
    
    public void 角色ID() {
        this.c.getPlayer().getId();
    }
    
    public void 全服存档() {
        try {
            for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                    if (chr == null) {
                        continue;
                    }
                    chr.saveToDB(false, false);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public static void 商城物品(final int id, final int key) throws SQLException {
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ps = con.prepareStatement("INSERT INTO cashshop_modified_items (itemid, meso) VALUES (?, ?)");
            ps.setInt(1, id);
            ps.setInt(2, key);
        }
        catch (SQLException ex) {
            Logger.getLogger(NPCConversationManager.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
        }
    }
    
    public void 新键盘上技能(final int id, final int key, final int type, final int action, final byte level) {
        final ISkill skill = SkillFactory.getSkill(action);
        this.c.getPlayer().changeSkillLevel(skill, level, skill.getMaxLevel());
        this.c.getPlayer().changeKeybinding(key, (byte)type, action);
        this.c.sendPacket(MaplePacketCreator.getKeymap(this.c.getPlayer().getKeyLayout()));
    }
    
    public void 键盘上技能(final int id, final int key, final int type, final int action, final byte level) throws SQLException {
        final ISkill skill = SkillFactory.getSkill(action);
        this.c.getPlayer().changeSkillLevel(skill, level, skill.getMaxLevel());
        this.c.getPlayer().dropMessage(1, "<提示>\r\n5秒后你会自动下线，请1分钟后再次登陆。");
        this.c.getPlayer().saveToDB(false, false);
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000L);
                    c.getPlayer().getClient().getSession().close();
                    Thread.sleep(2000L);
                    String SqlStr = "";
                    final Connection con = DatabaseConnection.getConnection();
                    PreparedStatement ps = null;
                    SqlStr = "SELECT * from keymap where characterid=" + id + " and keye=" + key + "";
                    ps = con.prepareStatement(SqlStr);
                    final ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        PreparedStatement psu = null;
                        SqlStr = "UPDATE keymap set type=" + type + ",action=" + action + " where characterid=" + id + " and keye=" + key + "";
                        psu = con.prepareStatement(SqlStr);
                        psu.execute();
                        psu.close();
                    }
                    else {
                        PreparedStatement psu = null;
                        psu = con.prepareStatement("INSERT INTO keymap (characterid, `keye`, `type`, `action`) VALUES (?, ?, ?, ?)");
                        psu.setInt(1, id);
                        psu.setInt(2, key);
                        psu.setInt(3, type);
                        psu.setInt(4, action);
                        psu.executeUpdate();
                        psu.close();
                    }
                    rs.close();
                    ps.close();
                }
                catch (InterruptedException ex2) {}
                catch (SQLException ex) {
                    Logger.getLogger(NPCConversationManager.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
                }
            }
        }.start();
    }
    
    public void 删除角色(final int id) {
        PreparedStatement ps1 = null;
        try {
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM characters WHERE id = ?");
        }
        catch (SQLException ex) {}
        final String sqlstr = " delete from characters where id =" + id + "";
        try {
            ps1.executeUpdate(sqlstr);
            this.c.getPlayer().dropMessage(1, "角色删除成功。");
        }
        catch (SQLException ex2) {}
    }
    
    public void 开始计时() {
        System.currentTimeMillis();
    }
    
    public void 剑客排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().剑客()));
    }
    
    public void 勇士排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().勇士()));
    }
    
    public void 英雄排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().英雄()));
    }
    
    public void 准骑士排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().准骑士()));
    }
    
    public void 骑士排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().骑士()));
    }
    
    public void 圣骑士排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().圣骑士()));
    }
    
    public void 枪战士排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().枪战士()));
    }
    
    public void 龙骑士排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().龙骑士()));
    }
    
    public void 黑骑士排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().黑骑士()));
    }
    
    public void 火毒法师排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().火毒法师()));
    }
    
    public void 火毒巫师排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().火毒巫师()));
    }
    
    public void 火毒魔导师排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().火毒魔导师()));
    }
    
    public void 冰雷法师排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().冰雷法师()));
    }
    
    public void 冰雷巫师排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().冰雷巫师()));
    }
    
    public void 冰雷魔导师排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().冰雷魔导师()));
    }
    
    public void 牧师排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().牧师()));
    }
    
    public void 祭师排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().祭师()));
    }
    
    public void 主教排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().主教()));
    }
    
    public void 猎人排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().猎人()));
    }
    
    public void 射手排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().射手()));
    }
    
    public void 神射手排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().神射手()));
    }
    
    public void 弩弓手排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().弩弓手()));
    }
    
    public void 游侠排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().游侠()));
    }
    
    public void 箭神排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().箭神()));
    }
    
    public void 刺客排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().刺客()));
    }
    
    public void 无影人排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().无影人()));
    }
    
    public void 隐士排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().隐士()));
    }
    
    public void 侠客排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().侠客()));
    }
    
    public void 独行客排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().独行客()));
    }
    
    public void 侠盗排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().侠盗()));
    }
    
    public void 拳手排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().拳手()));
    }
    
    public void 斗士排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().斗士()));
    }
    
    public void 冲锋队长排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().冲锋队长()));
    }
    
    public void 火枪手排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().火枪手()));
    }
    
    public void 大副排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().大副()));
    }
    
    public void 船长排行榜() {
        this.c.sendPacket(MaplePacketCreator.showlevelRanks(this.npc, MapleGuildRanking.getInstance().船长()));
    }
    
    public int 给全服发点卷(final int 数量, final int 类型) {
        int count = 0;
        try {
            if (数量 <= 0 || 类型 <= 0) {
                return 0;
            }
            if (类型 == 1 || 类型 == 2) {
                for (final ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (final MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        mch.modifyCSPoints(类型, 数量);
                        String cash = null;
                        if (类型 == 1) {
                            cash = "点卷";
                        }
                        else if (类型 == 2) {
                            cash = "抵用卷";
                        }
                        ++count;
                    }
                }
            }
            else if (类型 == 3) {
                for (final ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (final MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        mch.gainMeso(数量, true);
                        ++count;
                    }
                }
            }
            else if (类型 == 4) {
                for (final ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (final MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        mch.gainExp(数量, true, false, true);
                        ++count;
                    }
                }
            }
        }
        catch (Exception e) {
            this.c.getPlayer().dropMessage("给全服发点卷出错：" + e.getMessage());
        }
        return count;
    }
    
    public int 给当前地图发点卷(final int 数量, final int 类型) {
        int count = 0;
        final int mapId = this.c.getPlayer().getMapId();
        try {
            if (数量 <= 0 || 类型 <= 0) {
                return 0;
            }
            if (类型 == 1 || 类型 == 2) {
                for (final ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (final MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        if (mch.getMapId() != mapId) {
                            continue;
                        }
                        mch.modifyCSPoints(类型, 数量);
                        String cash = null;
                        if (类型 == 1) {
                            cash = "点卷";
                        }
                        else if (类型 == 2) {
                            cash = "抵用卷";
                        }
                        ++count;
                    }
                }
            }
            else if (类型 == 3) {
                for (final ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (final MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        if (mch.getMapId() != mapId) {
                            continue;
                        }
                        mch.gainMeso(数量, true);
                        ++count;
                    }
                }
            }
            else if (类型 == 4) {
                for (final ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (final MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        if (mch.getMapId() != mapId) {
                            continue;
                        }
                        mch.gainExp(数量, true, false, true);
                        ++count;
                    }
                }
            }
        }
        catch (Exception e) {
            this.c.getPlayer().dropMessage("给当前地图发点卷出错：" + e.getMessage());
        }
        return count;
    }
    
    public int 给当前频道发点卷(final int 数量, final int 类型) {
        int count = 0;
        final int chlId = this.c.getPlayer().getMap().getChannel();
        try {
            if (数量 <= 0 || 类型 <= 0) {
                return 0;
            }
            if (类型 == 1 || 类型 == 2) {
                for (final ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    if (cserv1.getChannel() != chlId) {
                        continue;
                    }
                    for (final MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        mch.modifyCSPoints(类型, 数量);
                        String cash = null;
                        if (类型 == 1) {
                            cash = "点卷";
                        }
                        else if (类型 == 2) {
                            cash = "抵用卷";
                        }
                        ++count;
                    }
                }
            }
            else if (类型 == 3) {
                for (final ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    if (cserv1.getChannel() != chlId) {
                        continue;
                    }
                    for (final MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        mch.gainMeso(数量, true);
                        ++count;
                    }
                }
            }
            else if (类型 == 4) {
                for (final ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    if (cserv1.getChannel() != chlId) {
                        continue;
                    }
                    for (final MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        mch.gainExp(数量, true, false, true);
                        ++count;
                    }
                }
            }
        }
        catch (Exception e) {
            this.c.getPlayer().dropMessage("给当前频道发点卷出错：" + e.getMessage());
        }
        return count;
    }
    
    public int 给全服发物品(final int 物品ID, final int 数量, final int 力量, final int 敏捷, final int 智力, final int 运气, final int HP, final int MP, final int 可加卷次数, final String 制作人名字, final int 给予时间, final String 是否可以交易, final int 攻击力, final int 魔法力, final int 物理防御, final int 魔法防御) {
        int count = 0;
        try {
            final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            final MapleInventoryType type = GameConstants.getInventoryType(物品ID);
            for (final ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (final MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    if (数量 >= 0) {
                        if (!MapleInventoryManipulator.checkSpace(mch.getClient(), 物品ID, 数量, "")) {
                            return 0;
                        }
                        if ((type.equals((Object)MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(物品ID) && !GameConstants.isBullet(物品ID)) || (type.equals((Object)MapleInventoryType.CASH) && 物品ID >= 5000000 && 物品ID <= 5000100)) {
                            final Equip item = (Equip)(Equip)ii.getEquipById(物品ID);
                            if (ii.isCash(物品ID)) {
                                item.setUniqueId(1);
                            }
                            if (力量 > 0 && 力量 <= 32767) {
                                item.setStr((short)力量);
                            }
                            if (敏捷 > 0 && 敏捷 <= 32767) {
                                item.setDex((short)敏捷);
                            }
                            if (智力 > 0 && 智力 <= 32767) {
                                item.setInt((short)智力);
                            }
                            if (运气 > 0 && 运气 <= 32767) {
                                item.setLuk((short)运气);
                            }
                            if (攻击力 > 0 && 攻击力 <= 32767) {
                                item.setWatk((short)攻击力);
                            }
                            if (魔法力 > 0 && 魔法力 <= 32767) {
                                item.setMatk((short)魔法力);
                            }
                            if (物理防御 > 0 && 物理防御 <= 32767) {
                                item.setWdef((short)物理防御);
                            }
                            if (魔法防御 > 0 && 魔法防御 <= 32767) {
                                item.setMdef((short)魔法防御);
                            }
                            if (HP > 0 && HP <= 30000) {
                                item.setHp((short)HP);
                            }
                            if (MP > 0 && MP <= 30000) {
                                item.setMp((short)MP);
                            }
                            if ("可以交易".equals((Object)是否可以交易)) {
                                byte flag = item.getFlag();
                                if (item.getType() == MapleInventoryType.EQUIP.getType()) {
                                    flag |= (byte)ItemFlag.KARMA_EQ.getValue();
                                }
                                else {
                                    flag |= (byte)ItemFlag.KARMA_USE.getValue();
                                }
                                item.setFlag(flag);
                            }
                            if (给予时间 > 0) {
                                item.setExpiration(System.currentTimeMillis() + (long)(给予时间 * 24 * 60 * 60 * 1000));
                            }
                            if (可加卷次数 > 0) {
                                item.setUpgradeSlots((byte)可加卷次数);
                            }
                            if (制作人名字 != null) {
                                item.setOwner(制作人名字);
                            }
                            final String name = ii.getName(物品ID);
                            if (物品ID / 10000 == 114 && name != null && name.length() > 0) {
                                final String msg = "你已获得称号 <" + name + ">";
                                mch.getClient().getPlayer().dropMessage(5, msg);
                            }
                            MapleInventoryManipulator.addbyItem(mch.getClient(), item.copy());
                        }
                        else {
                            MapleInventoryManipulator.addById(mch.getClient(), 物品ID, (short)数量, "", null, (long)给予时间, (byte)0);
                        }
                    }
                    else {
                        MapleInventoryManipulator.removeById(mch.getClient(), GameConstants.getInventoryType(物品ID), 物品ID, -数量, true, false);
                    }
                    mch.getClient().sendPacket(MaplePacketCreator.getShowItemGain(物品ID, (short)数量, true));
                    ++count;
                }
            }
        }
        catch (Exception e) {
            this.c.getPlayer().dropMessage("给全服发物品出错：" + e.getMessage());
        }
        return count;
    }
    
    public int 给当前地图发物品(final int 物品ID, final int 数量, final int 力量, final int 敏捷, final int 智力, final int 运气, final int HP, final int MP, final int 可加卷次数, final String 制作人名字, final int 给予时间, final String 是否可以交易, final int 攻击力, final int 魔法力, final int 物理防御, final int 魔法防御) {
        int count = 0;
        final int mapId = this.c.getPlayer().getMapId();
        try {
            final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            final MapleInventoryType type = GameConstants.getInventoryType(物品ID);
            for (final ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (final MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    if (mch.getMapId() != mapId) {
                        continue;
                    }
                    if (数量 >= 0) {
                        if (!MapleInventoryManipulator.checkSpace(mch.getClient(), 物品ID, 数量, "")) {
                            return 0;
                        }
                        if ((type.equals((Object)MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(物品ID) && !GameConstants.isBullet(物品ID)) || (type.equals((Object)MapleInventoryType.CASH) && 物品ID >= 5000000 && 物品ID <= 5000100)) {
                            final Equip item = (Equip)(Equip)ii.getEquipById(物品ID);
                            if (ii.isCash(物品ID)) {
                                item.setUniqueId(1);
                            }
                            if (力量 > 0 && 力量 <= 32767) {
                                item.setStr((short)力量);
                            }
                            if (敏捷 > 0 && 敏捷 <= 32767) {
                                item.setDex((short)敏捷);
                            }
                            if (智力 > 0 && 智力 <= 32767) {
                                item.setInt((short)智力);
                            }
                            if (运气 > 0 && 运气 <= 32767) {
                                item.setLuk((short)运气);
                            }
                            if (攻击力 > 0 && 攻击力 <= 32767) {
                                item.setWatk((short)攻击力);
                            }
                            if (魔法力 > 0 && 魔法力 <= 32767) {
                                item.setMatk((short)魔法力);
                            }
                            if (物理防御 > 0 && 物理防御 <= 32767) {
                                item.setWdef((short)物理防御);
                            }
                            if (魔法防御 > 0 && 魔法防御 <= 32767) {
                                item.setMdef((short)魔法防御);
                            }
                            if (HP > 0 && HP <= 30000) {
                                item.setHp((short)HP);
                            }
                            if (MP > 0 && MP <= 30000) {
                                item.setMp((short)MP);
                            }
                            if ("可以交易".equals((Object)是否可以交易)) {
                                byte flag = item.getFlag();
                                if (item.getType() == MapleInventoryType.EQUIP.getType()) {
                                    flag |= (byte)ItemFlag.KARMA_EQ.getValue();
                                }
                                else {
                                    flag |= (byte)ItemFlag.KARMA_USE.getValue();
                                }
                                item.setFlag(flag);
                            }
                            if (给予时间 > 0) {
                                item.setExpiration(System.currentTimeMillis() + (long)(给予时间 * 24 * 60 * 60 * 1000));
                            }
                            if (可加卷次数 > 0) {
                                item.setUpgradeSlots((byte)可加卷次数);
                            }
                            if (制作人名字 != null) {
                                item.setOwner(制作人名字);
                            }
                            final String name = ii.getName(物品ID);
                            if (物品ID / 10000 == 114 && name != null && name.length() > 0) {
                                final String msg = "你已获得称号 <" + name + ">";
                                mch.getClient().getPlayer().dropMessage(5, msg);
                            }
                            MapleInventoryManipulator.addbyItem(mch.getClient(), item.copy());
                        }
                        else {
                            MapleInventoryManipulator.addById(mch.getClient(), 物品ID, (short)数量, "", null, (long)给予时间, (byte)0);
                        }
                    }
                    else {
                        MapleInventoryManipulator.removeById(mch.getClient(), GameConstants.getInventoryType(物品ID), 物品ID, -数量, true, false);
                    }
                    mch.getClient().sendPacket(MaplePacketCreator.getShowItemGain(物品ID, (short)数量, true));
                    ++count;
                }
            }
        }
        catch (Exception e) {
            this.c.getPlayer().dropMessage("给当前地图发物品出错：" + e.getMessage());
        }
        return count;
    }
    
    public int 给当前频道发物品(final int 物品ID, final int 数量, final int 力量, final int 敏捷, final int 智力, final int 运气, final int HP, final int MP, final int 可加卷次数, final String 制作人名字, final int 给予时间, final String 是否可以交易, final int 攻击力, final int 魔法力, final int 物理防御, final int 魔法防御) {
        int count = 0;
        final int chlId = this.c.getPlayer().getMap().getChannel();
        try {
            final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            final MapleInventoryType type = GameConstants.getInventoryType(物品ID);
            for (final ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                if (cserv1.getChannel() != chlId) {
                    continue;
                }
                for (final MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    if (数量 >= 0) {
                        if (!MapleInventoryManipulator.checkSpace(mch.getClient(), 物品ID, 数量, "")) {
                            return 0;
                        }
                        if ((type.equals((Object)MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(物品ID) && !GameConstants.isBullet(物品ID)) || (type.equals((Object)MapleInventoryType.CASH) && 物品ID >= 5000000 && 物品ID <= 5000100)) {
                            final Equip item = (Equip)(Equip)ii.getEquipById(物品ID);
                            if (ii.isCash(物品ID)) {
                                item.setUniqueId(1);
                            }
                            if (力量 > 0 && 力量 <= 32767) {
                                item.setStr((short)力量);
                            }
                            if (敏捷 > 0 && 敏捷 <= 32767) {
                                item.setDex((short)敏捷);
                            }
                            if (智力 > 0 && 智力 <= 32767) {
                                item.setInt((short)智力);
                            }
                            if (运气 > 0 && 运气 <= 32767) {
                                item.setLuk((short)运气);
                            }
                            if (攻击力 > 0 && 攻击力 <= 32767) {
                                item.setWatk((short)攻击力);
                            }
                            if (魔法力 > 0 && 魔法力 <= 32767) {
                                item.setMatk((short)魔法力);
                            }
                            if (物理防御 > 0 && 物理防御 <= 32767) {
                                item.setWdef((short)物理防御);
                            }
                            if (魔法防御 > 0 && 魔法防御 <= 32767) {
                                item.setMdef((short)魔法防御);
                            }
                            if (HP > 0 && HP <= 30000) {
                                item.setHp((short)HP);
                            }
                            if (MP > 0 && MP <= 30000) {
                                item.setMp((short)MP);
                            }
                            if ("可以交易".equals((Object)是否可以交易)) {
                                byte flag = item.getFlag();
                                if (item.getType() == MapleInventoryType.EQUIP.getType()) {
                                    flag |= (byte)ItemFlag.KARMA_EQ.getValue();
                                }
                                else {
                                    flag |= (byte)ItemFlag.KARMA_USE.getValue();
                                }
                                item.setFlag(flag);
                            }
                            if (给予时间 > 0) {
                                item.setExpiration(System.currentTimeMillis() + (long)(给予时间 * 24 * 60 * 60 * 1000));
                            }
                            if (可加卷次数 > 0) {
                                item.setUpgradeSlots((byte)可加卷次数);
                            }
                            if (制作人名字 != null) {
                                item.setOwner(制作人名字);
                            }
                            final String name = ii.getName(物品ID);
                            if (物品ID / 10000 == 114 && name != null && name.length() > 0) {
                                final String msg = "你已获得称号 <" + name + ">";
                                mch.getClient().getPlayer().dropMessage(5, msg);
                            }
                            MapleInventoryManipulator.addbyItem(mch.getClient(), item.copy());
                        }
                        else {
                            MapleInventoryManipulator.addById(mch.getClient(), 物品ID, (short)数量, "", null, (long)给予时间, (byte)0);
                        }
                    }
                    else {
                        MapleInventoryManipulator.removeById(mch.getClient(), GameConstants.getInventoryType(物品ID), 物品ID, -数量, true, false);
                    }
                    mch.getClient().sendPacket(MaplePacketCreator.getShowItemGain(物品ID, (short)数量, true));
                    ++count;
                }
            }
        }
        catch (Exception e) {
            this.c.getPlayer().dropMessage("给当前频道发物品出错：" + e.getMessage());
        }
        return count;
    }
    
    public int 传送当前地图所有人到指定地图(final int destMapId, final Boolean includeSelf) {
        int count = 0;
        final int myMapId = this.c.getPlayer().getMapId();
        final int myId = this.c.getPlayer().getId();
        try {
            final MapleMap tomap = this.getMapFactory().getMap(destMapId);
            final MapleMap frommap = this.getMapFactory().getMap(myMapId);
            final List<MapleCharacter> list = frommap.getCharactersThreadsafe();
            if (tomap != null && frommap != null && list != null && frommap.getCharactersSize() > 0) {
                for (final MapleMapObject mmo : list) {
                    final MapleCharacter chr = (MapleCharacter)mmo;
                    if (chr.getId() == myId) {
                        if (!(boolean)includeSelf) {
                            continue;
                        }
                        chr.changeMap(tomap, tomap.getPortal(0));
                        ++count;
                    }
                    else {
                        chr.changeMap(tomap, tomap.getPortal(0));
                        ++count;
                    }
                }
            }
        }
        catch (Exception e) {
            this.c.getPlayer().dropMessage("传送当前地图所有人到指定地图出错：" + e.getMessage());
        }
        return count;
    }
    
    public int 杀死当前地图所有人(final Boolean includeSelf) {
        int count = 0;
        final int myMapId = this.c.getPlayer().getMapId();
        final int myId = this.c.getPlayer().getId();
        try {
            final MapleMap frommap = this.getMapFactory().getMap(myMapId);
            final List<MapleCharacter> list = frommap.getCharactersThreadsafe();
            if (frommap != null && list != null && frommap.getCharactersSize() > 0) {
                for (final MapleMapObject mmo : list) {
                    if (mmo != null) {
                        final MapleCharacter chr = (MapleCharacter)mmo;
                        if (chr.getId() == myId) {
                            if (!(boolean)includeSelf) {
                                continue;
                            }
                            chr.setHp(0);
                            chr.updateSingleStat(MapleStat.HP, 0);
                            ++count;
                        }
                        else {
                            chr.setHp(0);
                            chr.updateSingleStat(MapleStat.HP, 0);
                            ++count;
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            this.c.getPlayer().dropMessage("杀死当前地图所有人出错：" + e.getMessage());
        }
        return count;
    }
    
    public int 复活当前地图所有人(final Boolean includeSelf) {
        int count = 0;
        final int myMapId = this.c.getPlayer().getMapId();
        final int myId = this.c.getPlayer().getId();
        try {
            final MapleMap frommap = this.getMapFactory().getMap(myMapId);
            final List<MapleCharacter> list = frommap.getCharactersThreadsafe();
            if (frommap != null && list != null && frommap.getCharactersSize() > 0) {
                for (final MapleMapObject mmo : list) {
                    if (mmo != null) {
                        final MapleCharacter chr = (MapleCharacter)mmo;
                        if (chr.getId() == myId) {
                            if (!(boolean)includeSelf) {
                                continue;
                            }
                            chr.getStat().setHp((int)chr.getStat().getMaxHp());
                            chr.updateSingleStat(MapleStat.HP, (int)chr.getStat().getMaxHp());
                            chr.getStat().setMp((int)chr.getStat().getMaxMp());
                            chr.updateSingleStat(MapleStat.MP, (int)chr.getStat().getMaxMp());
                            chr.dispelDebuffs();
                            ++count;
                        }
                        else {
                            chr.getStat().setHp((int)chr.getStat().getMaxHp());
                            chr.updateSingleStat(MapleStat.HP, (int)chr.getStat().getMaxHp());
                            chr.getStat().setMp((int)chr.getStat().getMaxMp());
                            chr.updateSingleStat(MapleStat.MP, (int)chr.getStat().getMaxMp());
                            chr.dispelDebuffs();
                            ++count;
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            this.c.getPlayer().dropMessage("复活当前地图所有人出错：" + e.getMessage());
        }
        return count;
    }
    
    public void 跟踪玩家(final String charName) {
        for (final ChannelServer chl : ChannelServer.getAllInstances()) {
            for (final MapleCharacter chr : chl.getPlayerStorage().getAllCharacters()) {
                if (chr.getName() == charName) {
                    this.c.getPlayer().changeMap(chr.getMapId());
                }
            }
        }
    }
    
    public int 给指定地图发物品(int 地图ID, final int 物品ID, final int 数量, final int 力量, final int 敏捷, final int 智力, final int 运气, final int HP, final int MP, final int 可加卷次数, final String 制作人名字, final int 给予时间, final String 是否可以交易, final int 攻击力, final int 魔法力, final int 物理防御, final int 魔法防御) {
        int count = 0;
        if (地图ID < 1) {
            地图ID = this.c.getPlayer().getMapId();
        }
        try {
            final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            final MapleInventoryType type = GameConstants.getInventoryType(物品ID);
            final MapleMap frommap = this.getMapFactory().getMap(地图ID);
            final List<MapleCharacter> list = frommap.getCharactersThreadsafe();
            if (list != null && frommap.getCharactersSize() > 0) {
                for (final MapleMapObject mmo : list) {
                    if (mmo != null) {
                        final MapleCharacter chr = (MapleCharacter)mmo;
                        if (数量 >= 0) {
                            if (!MapleInventoryManipulator.checkSpace(chr.getClient(), 物品ID, 数量, "")) {
                                return 0;
                            }
                            if ((type.equals((Object)MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(物品ID) && !GameConstants.isBullet(物品ID)) || (type.equals((Object)MapleInventoryType.CASH) && 物品ID >= 5000000 && 物品ID <= 5000100)) {
                                final Equip item = (Equip)(Equip)ii.getEquipById(物品ID);
                                if (ii.isCash(物品ID)) {
                                    item.setUniqueId(1);
                                }
                                if (力量 > 0 && 力量 <= 32767) {
                                    item.setStr((short)力量);
                                }
                                if (敏捷 > 0 && 敏捷 <= 32767) {
                                    item.setDex((short)敏捷);
                                }
                                if (智力 > 0 && 智力 <= 32767) {
                                    item.setInt((short)智力);
                                }
                                if (运气 > 0 && 运气 <= 32767) {
                                    item.setLuk((short)运气);
                                }
                                if (攻击力 > 0 && 攻击力 <= 32767) {
                                    item.setWatk((short)攻击力);
                                }
                                if (魔法力 > 0 && 魔法力 <= 32767) {
                                    item.setMatk((short)魔法力);
                                }
                                if (物理防御 > 0 && 物理防御 <= 32767) {
                                    item.setWdef((short)物理防御);
                                }
                                if (魔法防御 > 0 && 魔法防御 <= 32767) {
                                    item.setMdef((short)魔法防御);
                                }
                                if (HP > 0 && HP <= 30000) {
                                    item.setHp((short)HP);
                                }
                                if (MP > 0 && MP <= 30000) {
                                    item.setMp((short)MP);
                                }
                                if ("可以交易".equals((Object)是否可以交易)) {
                                    byte flag = item.getFlag();
                                    if (item.getType() == MapleInventoryType.EQUIP.getType()) {
                                        flag |= (byte)ItemFlag.KARMA_EQ.getValue();
                                    }
                                    else {
                                        flag |= (byte)ItemFlag.KARMA_USE.getValue();
                                    }
                                    item.setFlag(flag);
                                }
                                if (给予时间 > 0) {
                                    item.setExpiration(System.currentTimeMillis() + (long)(给予时间 * 24 * 60 * 60 * 1000));
                                }
                                if (可加卷次数 > 0) {
                                    item.setUpgradeSlots((byte)可加卷次数);
                                }
                                if (制作人名字 != null) {
                                    item.setOwner(制作人名字);
                                }
                                final String name = ii.getName(物品ID);
                                if (物品ID / 10000 == 114 && name != null && name.length() > 0) {
                                    final String msg = "你已获得称号 <" + name + ">";
                                    chr.dropMessage(5, msg);
                                }
                                MapleInventoryManipulator.addbyItem(chr.getClient(), item.copy());
                            }
                            else {
                                MapleInventoryManipulator.addById(chr.getClient(), 物品ID, (short)数量, "", null, (long)给予时间, (byte)0);
                            }
                        }
                        else {
                            MapleInventoryManipulator.removeById(chr.getClient(), GameConstants.getInventoryType(物品ID), 物品ID, -数量, true, false);
                        }
                        chr.getClient().sendPacket(MaplePacketCreator.getShowItemGain(物品ID, (short)数量, true));
                        ++count;
                    }
                }
            }
        }
        catch (Exception e) {
            this.c.getPlayer().dropMessage("给指定地图发物品出错：" + e.getMessage());
        }
        return count;
    }
    
    public int 给指定地图发物品(final int 地图ID, final int 物品ID, final int 数量) {
        return this.给指定地图发物品(地图ID, 物品ID, 数量, 0, 0, 0, 0, 0, 0, 0, "", 0, "", 0, 0, 0, 0);
    }
    
    public int 给指定地图发点卷(int 地图ID, final int 数量, final int 类型) {
        int count = 0;
        final String name = this.c.getPlayer().getName();
        if (地图ID < 1) {
            地图ID = this.c.getPlayer().getMapId();
        }
        try {
            if (数量 <= 0 || 类型 <= 0) {
                return 0;
            }
            final MapleMap frommap = this.getMapFactory().getMap(地图ID);
            final List<MapleCharacter> list = frommap.getCharactersThreadsafe();
            if (list != null && frommap.getCharactersSize() > 0) {
                if (类型 == 1 || 类型 == 2) {
                    for (final MapleMapObject mmo : list) {
                        if (mmo != null) {
                            final MapleCharacter chr = (MapleCharacter)mmo;
                            chr.modifyCSPoints(类型, 数量);
                            String cash = null;
                            if (类型 == 1) {
                                cash = "点卷";
                            }
                            else if (类型 == 2) {
                                cash = "抵用卷";
                            }
                            ++count;
                        }
                    }
                }
                else if (类型 == 3) {
                    for (final MapleMapObject mmo : list) {
                        if (mmo != null) {
                            final MapleCharacter chr = (MapleCharacter)mmo;
                            chr.gainMeso(数量, true);
                            ++count;
                        }
                    }
                }
                else if (类型 == 4) {
                    for (final MapleMapObject mmo : list) {
                        if (mmo != null) {
                            final MapleCharacter chr = (MapleCharacter)mmo;
                            chr.gainExp(数量, true, false, true);
                            ++count;
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            this.c.getPlayer().dropMessage("给指定地图发点卷出错：" + e.getMessage());
        }
        return count;
    }
    
    public int 获取指定地图玩家数量(final int mapId) {
        return this.getMapFactory().getMap(mapId).characterSize();
    }
    
    public int 判断指定地图玩家数量(final int mapId) {
        return this.getMapFactory().getMap(mapId).characterSize();
    }
    
    public void 给指定地图发公告(final int mapId, final String msg, final int itemId) {
        this.getMapFactory().getMap(mapId).startMapEffect(msg, itemId);
    }
    
    public void 设置天气(final int 天气ID) {
        if (this.c.getPlayer().getMap().getPermanentWeather() > 0) {
            this.c.getPlayer().getMap().setPermanentWeather(0);
            this.c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.removeMapEffect());
        }
        else if (!MapleItemInformationProvider.getInstance().itemExists(天气ID) || 天气ID / 10000 != 512) {
            this.c.getPlayer().dropMessage(5, "无效的天气ID。");
        }
        else {
            this.c.getPlayer().getMap().setPermanentWeather(天气ID);
            this.c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.startMapEffect("", 天气ID, false));
            this.c.getPlayer().dropMessage(5, "地图天气已启用。");
        }
    }
    
    public void 爆物开关() {
        this.c.getPlayer().getMap().toggleDrops();
    }
    
    public long 查看蓄力一击() {
        return (System.currentTimeMillis() - this.c.getPlayer().蓄力一击) / 10L;
    }
    
    public void 给人气(final int r) {
        this.c.getPlayer().addFame(r);
    }
    
    public void 判断人气() {
        this.c.getPlayer().getFame();
    }
    
    public void 回收地图() {
        if (this.判断地图(this.c.getPlayer().getMapId()) <= 0) {
            final int 地图 = this.c.getPlayer().getMapId();
            this.记录地图(地图);
            this.c.getPlayer().dropMessage(1, "回收成功，此地图将在 5 分钟后被回收。");
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(300000L);
                        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                            for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                                if (chr == null) {
                                    continue;
                                }
                                if (chr.getMapId() != 地图) {
                                    continue;
                                }
                                chr.getClient().getSession().close();
                            }
                        }
                        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                            cserv.getMapFactory().destroyMap(地图, true);
                            cserv.getMapFactory().HealMap(地图);
                        }
                        NPCConversationManager.this.删除地图(地图);
                    }
                    catch (InterruptedException ex) {}
                }
            }.start();
        }
        else {
            this.c.getPlayer().dropMessage(1, "回收失败，此地图在回收队列中。");
        }
    }
    
    public void 回收地图(final int a) {
        if (this.判断地图(a) <= 0) {
            final int 地图 = a;
            this.记录地图(地图);
            this.c.getPlayer().dropMessage(1, "回收成功，此地图将在 1 小时后被重置。");
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(60000L);
                        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                            cserv.getMapFactory().destroyMap(地图, true);
                            cserv.getMapFactory().HealMap(地图);
                        }
                        NPCConversationManager.this.删除地图(地图);
                    }
                    catch (InterruptedException ex) {}
                }
            }.start();
        }
        else {
            this.c.getPlayer().dropMessage(1, "回收失败，此地图在回收队列中。");
        }
    }
    
    public void 记录地图(final int a) {
        try (final Connection con = DatabaseConnection.getConnection();
             final PreparedStatement ps = con.prepareStatement("INSERT INTO map (id) VALUES ( ?)")) {
            ps.setInt(1, a);
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException ex) {}
    }
    
    public void 删除地图(final int a) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM map where id =" + a + "");
            rs = ps1.executeQuery();
            if (rs.next()) {
                final String sqlstr = " Delete from map where id = '" + a + "'";
                ps1.executeUpdate(sqlstr);
            }
        }
        catch (SQLException ex) {}
    }
    
    public int 判断地图(final int a) {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM map where id =" + a + "");
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ++data;
            }
            ps.close();
        }
        catch (SQLException ex) {}
        return data;
    }
    
    public void 人气排行榜() {
        MapleGuild.人气排行(this.getClient(), this.npc);
    }
    
    public void 声望排行榜() {
        MapleGuild.声望排行(this.getClient(), this.npc);
    }
    
    public void 豆豆排行榜() {
        MapleGuild.豆豆排行(this.getClient(), this.npc);
    }
    
    public void 战斗力排行榜() {
        MapleGuild.战斗力排行(this.getClient(), this.npc);
    }
    
    public void 总在线时间排行榜() {
        MapleGuild.总在线时间排行(this.getClient(), this.npc);
    }
    
    public int 查询今日在线时间() {
        int data = 0;
        final Connection con = DatabaseConnection.getConnection();
        try {
            final PreparedStatement psu = con.prepareStatement("SELECT todayOnlineTime FROM characters WHERE id = ?");
            psu.setInt(1, this.c.getPlayer().getId());
            final ResultSet rs = psu.executeQuery();
            if (rs.next()) {
                data = rs.getInt("todayOnlineTime");
            }
            rs.close();
            psu.close();
        }
        catch (SQLException ex) {
            System.err.println("查询今日在线时间出错：" + ex.getMessage());
        }
        return data;
    }
    
    public int 查询总在线时间() {
        int data = 0;
        final Connection con = DatabaseConnection.getConnection();
        try {
            final PreparedStatement psu = con.prepareStatement("SELECT totalOnlineTime FROM characters WHERE id = ?");
            psu.setInt(1, this.c.getPlayer().getId());
            final ResultSet rs = psu.executeQuery();
            if (rs.next()) {
                data = rs.getInt("totalOnlineTime");
            }
            rs.close();
            psu.close();
        }
        catch (SQLException ex) {
            System.err.println("查询总在线时间出错：" + ex.getMessage());
        }
        return data;
    }
    
    public int 查询在线人数() {
        int count = 0;
        for (final ChannelServer chl : ChannelServer.getAllInstances()) {
            count += chl.getPlayerStorage().getAllCharacters().size();
        }
        return count;
    }
    
    public static int 判断背包位置是否有物品(final int a, final int b, final int c) {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE characterid =" + a + " && inventorytype = " + b + " && position = " + c + "");
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ++data;
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("判断背包位置是否有物品 - 数据库查询失败：" + (Object)Ex);
        }
        return data;
    }
    
    public static int 判断背包位置代码(final int a, final int b, final int c) {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE characterid =" + a + " && inventorytype = " + b + " && position = " + c + "");
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("itemid");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("判断背包位置是否有物品 - 数据库查询失败：" + (Object)Ex);
        }
        return data;
    }
    
    public static int 判断玩家是否穿戴某装备(final int a, final int b, final int c) {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE characterid =" + a + " && itemid = " + b + " && inventorytype = " + c + "");
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ++data;
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("判断玩家是否穿戴某装备 - 数据库查询失败：" + (Object)Ex);
        }
        return data;
    }
    
    public static int 获取最高玩家等级() {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT MAX(level) as DATA FROM characters WHERE gm = 0");
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取最高玩家等级出错 - 数据库查询失败：" + (Object)Ex);
        }
        return data;
    }
    
    public static int 获取最高等级() {
        int level = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT  `level` FROM characters WHERE gm = 0 ORDER BY `level` DESC LIMIT 1");
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    level = rs.getInt("level");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取家族名称出错 - 数据库查询失败：" + (Object)Ex);
        }
        return level;
    }
    
    public static int 获取最高玩家人气() {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT MAX(fame) as DATA FROM characters WHERE gm = 0");
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取最高玩家等级出错 - 数据库查询失败：" + (Object)Ex);
        }
        return data;
    }
    
    public static String 获取最高人气玩家名字() {
        String name = "";
        String level = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT `name`, `fame` FROM characters WHERE gm = 0 ORDER BY `fame` DESC LIMIT 1");
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("name");
                    level = rs.getString("fame");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取家族名称出错 - 数据库查询失败：" + (Object)Ex);
        }
        return String.format("%s", name);
    }
    
    public static int 获取最高玩家金币() {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT MAX(meso) as DATA FROM characters WHERE gm = 0");
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取最高玩家等级出错 - 数据库查询失败：" + (Object)Ex);
        }
        return data;
    }
    
    public static String 获取最高金币玩家名字() {
        String name = "";
        String level = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT `name`, `meso` FROM characters WHERE gm = 0 ORDER BY `meso` DESC LIMIT 1");
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("name");
                    level = rs.getString("meso");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取家族名称出错 - 数据库查询失败：" + (Object)Ex);
        }
        return String.format("%s", name);
    }
    
    public static int 获取最高玩家在线() {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT MAX(totalOnlineTime) as DATA FROM characters WHERE gm = 0");
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取最高玩家等级出错 - 数据库查询失败：" + (Object)Ex);
        }
        return data;
    }
    
    public static String 获取最高在线玩家名字() {
        String name = "";
        String level = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT `name`, `totalOnlineTime` FROM characters WHERE gm = 0 ORDER BY `totalOnlineTime` DESC LIMIT 1");
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("name");
                    level = rs.getString("totalOnlineTime");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取家族名称出错 - 数据库查询失败：" + (Object)Ex);
        }
        return String.format("%s", name);
    }
    
    public static String 获取今日在线玩家名字() {
        String name = "";
        String level = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT `name`, `todayOnlineTime` FROM characters WHERE gm = 0 ORDER BY `todayOnlineTime` DESC LIMIT 1");
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("name");
                    level = rs.getString("todayOnlineTime");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取家族名称出错 - 数据库查询失败：" + (Object)Ex);
        }
        return String.format("%s", name);
    }
    
    public static String 获取最强家族名称() {
        String name = "";
        String level = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT `name`, `GP` FROM guilds  ORDER BY `GP` DESC LIMIT 1");
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("name");
                    level = rs.getString("GP");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取家族名称出错 - 数据库查询失败：" + (Object)Ex);
        }
        return String.format("%s", name);
    }
    
    public static String 获取家族族长备注(final int guildId) {
        String data = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT rank1title as DATA FROM guilds WHERE guildid = ?");
            ps.setInt(1, guildId);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取家族名称出错 - 数据库查询失败：" + (Object)Ex);
        }
        return data;
    }
    
    public static String 获取家族副族长备注(final int guildId) {
        String data = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT rank2title as DATA FROM guilds WHERE guildid = ?");
            ps.setInt(1, guildId);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取家族名称出错 - 数据库查询失败：" + (Object)Ex);
        }
        return data;
    }
    
    public static String 获取家族一级成员备注(final int guildId) {
        String data = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT rank3title as DATA FROM guilds WHERE guildid = ?");
            ps.setInt(1, guildId);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取家族名称出错 - 数据库查询失败：" + (Object)Ex);
        }
        return data;
    }
    
    public static String 获取家族二级成员备注(final int guildId) {
        String data = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT rank4title as DATA FROM guilds WHERE guildid = ?");
            ps.setInt(1, guildId);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取家族名称出错 - 数据库查询失败：" + (Object)Ex);
        }
        return data;
    }
    
    public static String 获取家族三级成员备注(final int guildId) {
        String data = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT rank5title as DATA FROM guilds WHERE guildid = ?");
            ps.setInt(1, guildId);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取家族名称出错 - 数据库查询失败：" + (Object)Ex);
        }
        return data;
    }
    
    public static String 获取家族族长ID(final int guildId) {
        String data = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT leader as DATA FROM guilds WHERE guildid = ?");
            ps.setInt(1, guildId);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取家族名称出错 - 数据库查询失败：" + (Object)Ex);
        }
        return data;
    }
    
    public static int 家族成员数(final int a) {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM characters ");
            try (final ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    if (rs.getInt("guildid") == a) {
                        ++data;
                    }
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("获取家族家族成员数失败：" + (Object)Ex);
        }
        return data;
    }
    
    public String 等级排行榜() {
        int 名次 = 1;
        final StringBuilder name = new StringBuilder();
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM characters  WHERE gm = 0 order by level desc");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("level") < (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"冒险家等级上限")) && rs.getInt("level") > 30) {
                    if (名次 < 10) {
                        final String 玩家名字 = rs.getString("name");
                        final String 职业 = this.职业(rs.getInt("job"));
                        name.append("Top.#e#d").append(名次).append("#n#k   ");
                        name.append("#b").append(玩家名字).append("#k");
                        for (int j = 13 - 玩家名字.getBytes().length; j > 0; --j) {
                            name.append(" ");
                        }
                        name.append("  ").append(职业).append("");
                        for (int j = 15 - 职业.getBytes().length; j > 0; --j) {
                            name.append(" ");
                        }
                        name.append("  Lv.#d").append(rs.getInt("level")).append("#k\r\n");
                        ++名次;
                    }
                    else if (名次 >= 10 && 名次 <= 99) {
                        final String 玩家名字 = rs.getString("name");
                        final String 职业 = this.职业(rs.getInt("job"));
                        name.append("Top.#e#d").append(名次).append("#n#k  ");
                        name.append("#b").append(玩家名字).append("#k");
                        for (int j = 13 - 玩家名字.getBytes().length; j > 0; --j) {
                            name.append(" ");
                        }
                        name.append("  ").append(职业).append("");
                        for (int j = 15 - 职业.getBytes().length; j > 0; --j) {
                            name.append(" ");
                        }
                        name.append("  Lv.#d").append(rs.getInt("level")).append("#k\r\n");
                        ++名次;
                    }
                    else {
                        if (名次 <= 99) {
                            continue;
                        }
                        final String 玩家名字 = rs.getString("name");
                        final String 职业 = this.职业(rs.getInt("job"));
                        name.append("Top.#e#d").append(名次).append("#n#k ");
                        name.append("#b").append(玩家名字).append("#k");
                        for (int j = 13 - 玩家名字.getBytes().length; j > 0; --j) {
                            name.append(" ");
                        }
                        name.append("  ").append(职业).append("");
                        for (int j = 15 - 职业.getBytes().length; j > 0; --j) {
                            name.append(" ");
                        }
                        name.append("  Lv.#d").append(rs.getInt("level")).append("#k\r\n");
                        ++名次;
                    }
                }
            }
        }
        catch (SQLException ex) {}
        name.append("\r\n\r\n");
        return name.toString();
    }
    
    public String 满级排行榜() {
        final int 名次 = 1;
        final StringBuilder name = new StringBuilder();
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM characters  WHERE gm = 0 order by level desc");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("level") == (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"冒险家等级上限"))) {
                    final String 玩家名字 = rs.getString("name");
                    final String 职业 = this.职业(rs.getInt("job"));
                    final int 家族编号 = rs.getInt("guildid");
                    name.append("    ");
                    name.append("#b").append(玩家名字).append("#k");
                    for (int j = 13 - 玩家名字.getBytes().length; j > 0; --j) {
                        name.append(" ");
                    }
                    name.append("  ").append(职业).append("");
                    for (int j = 15 - 职业.getBytes().length; j > 0; --j) {
                        name.append(" ");
                    }
                    name.append("家族.#d").append(获取家族名称(家族编号)).append("#k\r\n");
                }
            }
        }
        catch (SQLException ex) {}
        name.append("\r\n\r\n");
        return name.toString();
    }
    
    public String 怪物卡片排行榜() {
        final int 名次 = 1;
        final StringBuilder name = new StringBuilder();
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM monsterbook   order by level desc");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("level") == (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"冒险家等级上限"))) {
                    final String 玩家名字 = rs.getString("name");
                    final String 职业 = this.职业(rs.getInt("job"));
                    final int 家族编号 = rs.getInt("guildid");
                    name.append("    ");
                    name.append("#b").append(玩家名字).append("#k");
                    for (int j = 13 - 玩家名字.getBytes().length; j > 0; --j) {
                        name.append(" ");
                    }
                    name.append("  ").append(职业).append("");
                    for (int j = 15 - 职业.getBytes().length; j > 0; --j) {
                        name.append(" ");
                    }
                    name.append("家族.#d").append(获取家族名称(家族编号)).append("#k\r\n");
                }
            }
        }
        catch (SQLException ex) {}
        name.append("\r\n\r\n");
        return name.toString();
    }
    
    public String 财富排行榜() {
        int 名次 = 1;
        final StringBuilder name = new StringBuilder();
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE gm = 0 order by meso desc LIMIT 20 ");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("meso") > 0) {
                    if (名次 < 10) {
                        final String 玩家名字 = rs.getString("name");
                        final String 金币 = rs.getString("meso");
                        name.append("Top.#e#d").append(名次).append("#n#k   ");
                        name.append("#b").append(玩家名字).append("#k");
                        for (int j = 13 - 玩家名字.getBytes().length; j > 0; --j) {
                            name.append(" ");
                        }
                        name.append("     Meso.#d").append(金币).append("#n\r\n");
                        ++名次;
                    }
                    else {
                        if (名次 < 10 || 名次 > 20) {
                            continue;
                        }
                        final String 玩家名字 = rs.getString("name");
                        final String 金币 = rs.getString("meso");
                        name.append("Top.#e#d").append(名次).append("#n#k  ");
                        name.append("#b").append(玩家名字).append("#k");
                        for (int j = 13 - 玩家名字.getBytes().length; j > 0; --j) {
                            name.append(" ");
                        }
                        name.append("     Meso.#d").append(金币).append("#n\r\n");
                        ++名次;
                    }
                }
            }
        }
        catch (SQLException ex) {}
        name.append("\r\n\r\n");
        return name.toString();
    }
    
    public String 在线排行榜() {
        int 名次 = 1;
        final StringBuilder name = new StringBuilder();
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM characters WHERE gm = 0 order by totalOnlineTime desc LIMIT 20 ");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("totalOnlineTime") > 0) {
                    if (名次 < 10) {
                        final String 玩家名字 = rs.getString("name");
                        final String 总在线 = rs.getString("totalOnlineTime");
                        final String 今在线 = rs.getString("todayOnlineTime");
                        name.append("Top.#e#d").append(名次).append("#n#k   ");
                        name.append("#b").append(玩家名字).append("#k");
                        for (int j = 13 - 玩家名字.getBytes().length; j > 0; --j) {
                            name.append(" ");
                        }
                        name.append("     (tal/day).#d[").append(总在线).append(" / ").append(今在线).append("])\r\n");
                        ++名次;
                    }
                    else {
                        if (名次 < 10 || 名次 > 20) {
                            continue;
                        }
                        final String 玩家名字 = rs.getString("name");
                        final String 总在线 = rs.getString("totalOnlineTime");
                        final String 今在线 = rs.getString("todayOnlineTime");
                        name.append("Top.#e#d").append(名次).append("#n#k  ");
                        name.append("#b").append(玩家名字).append("#k");
                        for (int j = 13 - 玩家名字.getBytes().length; j > 0; --j) {
                            name.append(" ");
                        }
                        name.append("     (tal/day).#d[").append(总在线).append(" / ").append(今在线).append("])\r\n");
                        ++名次;
                    }
                }
            }
        }
        catch (SQLException ex) {}
        name.append("\r\n\r\n");
        return name.toString();
    }
    
    public String 永恒重生排行榜() {
        int 名次 = 1;
        final StringBuilder name = new StringBuilder();
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM inventoryequipment order by itemlevel desc LIMIT 20");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("itemlevel") > 0) {
                    if (名次 < 10) {
                        final int 玩家ID = 道具id获取主人(rs.getInt("inventoryitemid"));
                        final String 玩家名字 = 角色ID取名字(玩家ID);
                        if (角色ID取GM(玩家ID) != 0) {
                            continue;
                        }
                        final int 道具IP = 道具id获取道具ID(rs.getInt("inventoryitemid"));
                        name.append("Top.#e#d").append(名次).append("#n#k   ");
                        name.append("拥有者:#b").append(玩家名字).append("#k");
                        for (int j = 15 - 玩家名字.getBytes().length; j > 0; --j) {
                            name.append(" ");
                        }
                        name.append(" lv.#r").append(rs.getInt("itemlevel")).append("#k #b#t").append(道具IP).append("##k\r\n");
                        ++名次;
                    }
                    else {
                        if (名次 < 10 || 名次 > 20) {
                            continue;
                        }
                        final int 玩家ID = 道具id获取主人(rs.getInt("inventoryitemid"));
                        final String 玩家名字 = 角色ID取名字(玩家ID);
                        if (角色ID取GM(玩家ID) != 0) {
                            continue;
                        }
                        final int 道具IP = 道具id获取道具ID(rs.getInt("inventoryitemid"));
                        name.append("Top.#e#d").append(名次).append("#n#k  ");
                        name.append("拥有者:#b").append(玩家名字).append("#k");
                        for (int j = 15 - 玩家名字.getBytes().length; j > 0; --j) {
                            name.append(" ");
                        }
                        name.append(" lv.#r").append(rs.getInt("itemlevel")).append("#k #b#t").append(道具IP).append("##k\r\n");
                        ++名次;
                    }
                }
            }
        }
        catch (SQLException ex) {}
        name.append("\r\n\r\n");
        return name.toString();
    }
    
    public static int 道具id获取道具ID(final int a) {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE inventoryitemid = " + a + "");
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                data = rs.getInt("itemid");
            }
            ps.close();
        }
        catch (SQLException ex) {}
        return data;
    }
    
    public static int 道具id获取主人(final int a) {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE inventoryitemid = " + a + "");
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                data = rs.getInt("characterid");
            }
            ps.close();
        }
        catch (SQLException ex) {}
        return data;
    }
    
    public static int 今日全服总在线时间() {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM characters");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("todayOnlineTime") > 0) {
                    data += rs.getInt("todayOnlineTime");
                }
            }
        }
        catch (SQLException ex) {}
        return data;
    }
    
    public static int 今日家族总在线时间(final int a) {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM characters");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("guildid") == a && rs.getInt("todayOnlineTime") > 0) {
                    data += rs.getInt("todayOnlineTime");
                }
            }
        }
        catch (SQLException ex) {}
        return data;
    }
    
    public static int 角色ID取GM(final int id) {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT gm as DATA FROM characters WHERE id = ?");
            ps.setInt(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("角色ID取上线喇叭、出错");
        }
        return data;
    }
    
    public void 清除地图物品(final int a) {
        this.getMap(a).removeDrops();
    }
    
    public String 职业(final int a) {
        return MapleCarnivalChallenge.getJobNameById(a);
    }
    
    public String 幸运职业() {
        final String 职业1 = MapleCarnivalChallenge.getJobNameById(MapleParty.幸运职业 - 1);
        final String 职业2 = MapleCarnivalChallenge.getJobNameById(MapleParty.幸运职业);
        final String 职业3 = MapleCarnivalChallenge.getJobNameById(MapleParty.幸运职业 + 1);
        final String 职业4 = 职业1 + "," + 职业2 + "," + 职业3;
        return 职业4;
    }
    
    public String 显示商品(final int id) {
        final StringBuilder name = new StringBuilder();
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM mysterious WHERE f = " + id + "");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final int 编号 = rs.getInt("id");
                final int 物品 = rs.getInt("itemid");
                final int 数量 = rs.getInt("数量");
                final int 点券 = rs.getInt("点卷");
                final int 金币 = rs.getInt("金币");
                name.append("   #L").append(编号).append("# #v").append(物品).append("# #b#t").append(物品).append("##k x ").append(数量).append("");
                name.append(" #d[券/币]:#b").append(点券).append("#k/#b").append(金币).append("#k#l\r\n");
            }
        }
        catch (SQLException ex) {}
        return name.toString();
    }
    
    public void 购买物品(final int id) {
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM mysterious WHERE f = " + id + "");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final int 编号 = rs.getInt("id");
                final int 物品 = rs.getInt("itemid");
                final int 数量 = rs.getInt("数量");
                final int 点券 = rs.getInt("点卷");
                final int 金币 = rs.getInt("金币");
                this.gainItem(物品, (short)数量);
            }
        }
        catch (SQLException ex) {}
    }
    
    public void 清怪() {
        final MapleMap map = this.c.getPlayer().getMap();
        final double range = Double.POSITIVE_INFINITY;
        for (final MapleMapObject monstermo : map.getMapObjectsInRange(this.c.getPlayer().getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER))) {
            final MapleMonster mob = (MapleMonster)monstermo;
            map.killMonster(mob, this.c.getPlayer(), true, false, (byte)1);
        }
    }
    
    public void setzb(final int slot) {
        try {
            final int cid = this.getPlayer().getAccountID();
            final Connection con = DatabaseConnection.getConnection();
            try (final PreparedStatement ps = con.prepareStatement("UPDATE accounts SET money =money+ " + slot + " WHERE id = " + cid + "")) {
                ps.executeUpdate();
            }
        }
        catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    @Override
    public void openWeb(final String web) {
        this.c.sendPacket(MaplePacketCreator.openWeb(web));
    }
    
    public final boolean getPartyBosslog(final String bossid, final int lcishu) {
        final MapleParty party = this.getPlayer().getParty();
        for (final MaplePartyCharacter pc : party.getMembers()) {
            final MapleCharacter chr = World.getStorage(this.getChannelNumber()).getCharacterById(pc.getId());
            if (chr != null && chr.getBossLog(bossid) >= lcishu) {
                return false;
            }
        }
        return true;
    }
    
    public void setPartyBosslog(final String bossid) {
        final MapleParty party = this.getPlayer().getParty();
        for (final MaplePartyCharacter pc : party.getMembers()) {
            final MapleCharacter chr = World.getStorage(this.getChannelNumber()).getCharacterById(pc.getId());
            if (chr != null) {
                chr.setBossLog(bossid);
            }
        }
    }
    
    public int gainGachaponItem(final int id, final int quantity) {
        return this.gainGachaponItem(id, quantity, this.c.getPlayer().getMap().getStreetName() + " - " + this.c.getPlayer().getMap().getMapName());
    }
    
    public int gainGachaponItem(final int id, final int quantity, final String msg) {
        try {
            if (!MapleItemInformationProvider.getInstance().itemExists(id)) {
                return -1;
            }
            final IItem item = MapleInventoryManipulator.addbyId_Gachapon(this.c, id, (short)quantity);
            if (item == null) {
                return -1;
            }
            final byte rareness = GameConstants.gachaponRareItem(item.getItemId());
            if (rareness > 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.getGachaponMega("[" + msg + "] " + this.c.getPlayer().getName(), " : 恭喜获得道具!", item, rareness, this.getPlayer().getClient().getChannel()));
            }
            return item.getItemId();
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public int gainGachaponItem(final int id, final int quantity, final String msg, final int 概率) {
        try {
            if (!MapleItemInformationProvider.getInstance().itemExists(id)) {
                return -1;
            }
            final IItem item = MapleInventoryManipulator.addbyId_Gachapon(this.c, id, (short)quantity);
            if (item == null) {
                return -1;
            }
            if (概率 > 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.getGachaponMega("[ " + msg + " ] : 已经被玩家 [ " + this.c.getPlayer().getName(), " ] 幸运抽中！", item, (byte)0, this.getPlayer().getClient().getChannel()));
            }
            return item.getItemId();
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public int gainGachaponItem2(final int id, final int quantity, final String msg, final int 概率) {
        try {
            if (!MapleItemInformationProvider.getInstance().itemExists(id)) {
                return -1;
            }
            final IItem item = MapleInventoryManipulator.addbyId_Gachapon(this.c, id, (short)quantity);
            if (item == null) {
                return -1;
            }
            if (概率 > 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.getGachaponMega("[ " + msg + " ] : 已经被玩家 [ " + this.c.getPlayer().getName(), " ] 幸运抽中！", item, (byte)0, this.getPlayer().getClient().getChannel()));
            }
            return item.getItemId();
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public int gainGachaponItem3(final int id, final int quantity, final String msg, final int 概率) {
        try {
            if (!MapleItemInformationProvider.getInstance().itemExists(id)) {
                return -1;
            }
            final IItem item = MapleInventoryManipulator.addbyId_Gachapon(this.c, id, (short)quantity);
            if (item == null) {
                return -1;
            }
            if (概率 > 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.getGachaponMega("[ " + msg + " ] : 已经被玩家 [ " + this.c.getPlayer().getName(), " ] 幸运抽中！", item, (byte)0, this.getPlayer().getClient().getChannel()));
            }
            return item.getItemId();
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public merchant_main getMerchant_main() {
        return merchant_main.getInstance();
    }
    
    public void logToFile_chr(final String path, final String msg) {
        FileoutputUtil.logToFile(path, msg);
    }
    
    public void gainmoneym(final int slot) {
        this.gainmoneym(this.c.getPlayer().getAccountID(), slot);
    }
    
    public void gainmoneym(final int cid, final int slot) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("UPDATE accounts SET moneym =moneym+" + slot + " WHERE id = " + cid + "");
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException ex) {
            ex.getStackTrace();
        }
        FileoutputUtil.logToFile("日志/商人交易系统/商人利益点更改.txt", " 更改值(" + (Object)((this.c.getPlayer().getAccountID() == cid) ? "自身" : Integer.valueOf(cid)) + ") " + slot + "");
    }
    
    public void setmoneym(final int slot) {
        this.setmoneym(this.c.getPlayer().getAccountID(), slot);
    }
    
    public void setmoneym(final int cid, final int slot) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("UPDATE accounts SET moneym = " + slot + " WHERE id = " + cid + "");
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException ex) {
            ex.getStackTrace();
        }
        FileoutputUtil.logToFile("日志/商人交易系统/商人利益点更改.txt", " 设置值(" + (Object)((this.c.getPlayer().getAccountID() == cid) ? "自身" : Integer.valueOf(cid)) + ") " + slot + "");
    }
    
    public int getmoneym() {
        int moneyb = 0;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final int cid = this.getPlayer().getAccountID();
            ResultSet rs;
            try (final PreparedStatement limitCheck = con.prepareStatement("SELECT * FROM accounts WHERE id=" + cid + "")) {
                rs = limitCheck.executeQuery();
                if (rs.next()) {
                    moneyb = rs.getInt("moneym");
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            ex.getStackTrace();
        }
        return moneyb;
    }
    
    public boolean checkHold(final int itemid, int quantity) {
        byte need_solt = 0;
        while (quantity > 0) {
            ++need_solt;
            if (quantity < 32767) {
                break;
            }
            quantity -= 32767;
        }
        return this.c.getPlayer().getInventory(GameConstants.getInventoryType(itemid)).getNumFreeSlot() >= need_solt;
    }
    
    public void 全服突破世界等级奖励() {
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (final MapleCharacter mch : cserv.getPlayerStorage().getAllCharacters()) {
                mch.modifyCSPoints(1, 1000);
                MapleInventoryManipulator.addById(mch.getClient(), 2340000, (short)1, "");
                MapleInventoryManipulator.addById(mch.getClient(), 4000463, (short)5, "");
                mch.dropMessage(-11, "[突破限制等级] 恭喜您获得管理员赠送给您的" + "点券" + 1000 + " 点." + "祝福卷轴1个、国庆纪念币5个.");
                mch.dropMessage(-1, "[突破限制等级] 恭喜您获得管理员赠送给您的" + "点券 " + 1000 + " 点." + "祝福卷轴1个、国庆纪念币5个.");
            }
        }
        for (final ChannelServer cserv2 : ChannelServer.getAllInstances()) {
            for (final MapleCharacter mch : cserv2.getPlayerStorage().getAllCharacters()) {
                mch.startMapEffect("[突破限制等级] ：奖励1000点券、祝福卷轴1个、国庆纪念币5个.给在线的所有玩家！", 5121006);
            }
        }
    }
    
    public int 取限制等级() {
        int 限制等级 = 0;
        try {
            final int cid = 4001128;
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement limitCheck = con.prepareStatement("SELECT * FROM shijiexianzhidengji WHERE huoyaotongid=" + cid + "");
            final ResultSet rs = limitCheck.executeQuery();
            if (rs.next()) {
                限制等级 = rs.getInt("xianzhidengji");
            }
            limitCheck.close();
            rs.close();
        }
        catch (SQLException ex) {}
        return 限制等级;
    }
    
    public int 取火药桶数量() {
        int 火药桶 = 0;
        try {
            final int cid = 4001128;
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement limitCheck = con.prepareStatement("SELECT * FROM shijiexianzhidengji WHERE huoyaotongid=" + cid + "");
            final ResultSet rs = limitCheck.executeQuery();
            if (rs.next()) {
                火药桶 = rs.getInt("dangqianshuliang");
            }
            limitCheck.close();
            rs.close();
        }
        catch (SQLException ex) {}
        return 火药桶;
    }
    
    public int 取火药桶总数量() {
        int 火药桶总数量 = 0;
        try {
            final int cid = 4001128;
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement limitCheck = con.prepareStatement("SELECT * FROM shijiexianzhidengji WHERE huoyaotongid=" + cid + "");
            final ResultSet rs = limitCheck.executeQuery();
            if (rs.next()) {
                火药桶总数量 = rs.getInt("zongshuliang");
            }
            limitCheck.close();
            rs.close();
        }
        catch (SQLException ex) {}
        return 火药桶总数量;
    }
    
    public void 写入火药桶数量(final int slot) {
        try {
            final int cid = 4001128;
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("UPDATE shijiexianzhidengji SET dangqianshuliang =dangqianshuliang+ " + slot + " WHERE huoyaotongid = " + cid + "");
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException ex) {}
    }
    
    public void 写入火药桶总数量(final int slot) {
        try {
            final int cid = 4001128;
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("UPDATE shijiexianzhidengji SET zongshuliang =zongshuliang+ " + slot + " WHERE huoyaotongid = " + cid + "");
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException ex) {}
    }
    
    public void 写入限制等级(final int slot) {
        try {
            final int cid = 4001128;
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("UPDATE shijiexianzhidengji SET xianzhidengji =xianzhidengji+ " + slot + " WHERE huoyaotongid = " + cid + "");
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException ex) {}
    }
    
    public void 扣除火药桶数量(final int slot) {
        try {
            final int cid = 4001128;
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("UPDATE shijiexianzhidengji SET dangqianshuliang =dangqianshuliang- " + slot + " WHERE huoyaotongid = " + cid + "");
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException ex) {}
    }
    
    public void spawnChaosZakum(final int x, final int y) {
        final MapleMap mapp = this.c.getChannelServer().getMapFactory().getMap(this.c.getPlayer().getMapId());
        mapp.spawnChaosZakum(x, y);
    }
    
    public void spawnZakum(final int x, final int y) {
        final MapleMap mapp = this.c.getChannelServer().getMapFactory().getMap(this.c.getPlayer().getMapId());
        mapp.spawnZakum(x, y);
    }
}
