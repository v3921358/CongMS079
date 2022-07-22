package scripting;

import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import database.DatabaseConnection;
import server.custom.bankitem2.BankItem2;
import server.custom.bankitem1.BankItem1;
import server.custom.bankitem.BankItem;
import server.custom.bankitem2.BankItemManager2;
import server.custom.bankitem1.BankItemManager1;
import server.custom.bankitem.BankItemManager;
import server.custom.bossrank10.BossRankManager10;
import server.custom.bossrank10.BossRankInfo10;
import server.custom.bossrank9.BossRankManager9;
import server.custom.bossrank9.BossRankInfo9;
import server.custom.bossrank8.BossRankManager8;
import server.custom.bossrank8.BossRankInfo8;
import server.custom.bossrank7.BossRankManager7;
import server.custom.bossrank7.BossRankInfo7;
import server.custom.bossrank6.BossRankManager6;
import server.custom.bossrank6.BossRankInfo6;
import server.custom.bossrank5.BossRankManager5;
import server.custom.bossrank5.BossRankInfo5;
import server.custom.bossrank4.BossRankManager4;
import server.custom.bossrank4.BossRankInfo4;
import server.custom.bossrank3.BossRankManager3;
import server.custom.bossrank3.BossRankInfo3;
import server.custom.bossrank2.BossRankManager2;
import server.custom.bossrank2.BossRankInfo2;
import server.custom.bossrank1.BossRankManager1;
import server.custom.bossrank1.BossRankInfo1;
import server.custom.bossrank.BossRankManager;
import server.custom.bossrank.BossRankInfo;
import java.util.Calendar;
import constants.ServerConfig;
import java.util.Map;
import java.util.EnumMap;
import client.MapleStat;
import client.inventory.Item;
import client.inventory.MapleInventory;
import java.util.ArrayList;
import server.RandomRewards;
import tools.FileoutputUtil;
import client.messages.CommandProcessor;
import constants.ServerConstants.CommandType;
import handling.channel.handler.InterServerHandler;
import server.maps.MapleMapFactory;
import server.maps.SavedLocationType;
import server.events.MapleEventType;
import server.events.MapleEvent;
import server.maps.Event_DojoAgent;
import client.ISkill;
import server.life.MapleMonster;
import tools.packet.PetPacket;
import tools.packet.UIPacket;
import client.SkillFactory;
import java.util.List;
import handling.world.MapleParty;
import handling.world.guild.MapleGuild;
import handling.world.World.Guild;
import handling.world.World.Broadcast;
import client.inventory.MaplePet;
import client.inventory.MapleInventoryIdentifier;
import constants.ItemConstants.類型;
import client.inventory.Equip;
import server.MapleItemInformationProvider;
import server.maps.MapleMapObject;
import server.maps.MapleReactor;
import server.quest.MapleQuest;
import client.MapleQuestStatus;
import server.MapleInventoryManipulator;
import client.inventory.MapleInventoryType;
import client.inventory.IItem;
import constants.GameConstants;
import server.life.MapleLifeFactory;
import java.awt.geom.Point2D;
import tools.MaplePacketCreator;
import java.awt.Point;
import server.maps.MapleMap;
import server.Randomizer;
import handling.channel.ChannelServer;
import java.util.Iterator;
import handling.world.MaplePartyCharacter;
import client.MapleCharacter;
import client.MapleClient;

public abstract class AbstractPlayerInteraction
{
    private MapleClient c;
    
    public AbstractPlayerInteraction(final MapleClient c) {
        this.c = c;
    }
    
    public final MapleClient getClient() {
        return this.c;
    }
    
    public final MapleClient getC() {
        return this.c;
    }
    
    public MapleCharacter getChar() {
        return this.getClient().getPlayer();
    }
    
    public int getOneTimeLog(final String bossid) {
        return this.getPlayer().getOneTimeLog(bossid);
    }
    
    public void setOneTimeLog(final String bossid) {
        this.getPlayer().setOneTimeLog(bossid);
    }
    
    public void deleteOneTimeLog(final String bossid) {
        this.getPlayer().deleteOneTimeLog(bossid);
    }
    
    public int getAcLog(final String bossid) {
        return this.getPlayer().getAcLog(bossid);
    }
    
    public int getAcLogS(final String bossid) {
        return this.getPlayer().getAcLogS(bossid);
    }
    
    public void setAcLog(final String bossid) {
        this.getPlayer().setAcLog(bossid);
    }
    
    public int 获得破功() {
        return this.getPlayer().获得破功();
    }
    
    public void 添加破功(final int pg) {
        this.c.getPlayer().添加破功(pg);
    }
    
    public int getBossLog(final String bossid) {
        return this.c.getPlayer().getBossLog(bossid);
    }
    
    public int getBossLog(final String bossid, final int type) {
        return this.c.getPlayer().getBossLog(bossid, type);
    }
    
    public void setBossLog(final String bossid) {
        this.c.getPlayer().setBossLog(bossid);
    }
    
    public void setBossLog(final String bossid, final int type) {
        this.c.getPlayer().setBossLog(bossid, type);
    }
    
    public void setBossLog(final String bossid, final int type, final int count) {
        this.c.getPlayer().setBossLog(bossid, type, count);
    }
    
    public void resetBossLog(final String bossid) {
        this.c.getPlayer().resetBossLog(bossid);
    }
    
    public void resetBossLog(final String bossid, final int type) {
        this.c.getPlayer().resetBossLog(bossid, type);
    }
    
    public void setPartyBossLog(final String bossid) {
        this.setPartyBossLog(bossid, 0);
    }
    
    public void setPartyBossLog(final String bossid, final int type) {
        this.setPartyBossLog(bossid, type, 1);
    }
    
    public void setPartyBossLog(final String bossid, final int type, final int count) {
        if (this.getPlayer().getParty() == null || this.getPlayer().getParty().getMembers().size() == 1) {
            this.c.getPlayer().setBossLog(bossid, type, count);
            return;
        }
        final int cMap = this.getPlayer().getMapId();
        for (final MaplePartyCharacter chr : this.getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = this.getPlayer().getMap().getCharacterById(chr.getId());
            if (curChar != null && curChar.getMapId() == cMap) {
                curChar.setBossLog(bossid, type, count);
            }
        }
    }
    
    public int getBossLogAcc(final String bossid) {
        return this.c.getPlayer().getBossLogAcc(bossid);
    }
    
    public void setBossLogAcc(final String bossid) {
        this.c.getPlayer().setBossLogAcc(bossid);
    }
    
    public void setBossLogAcc(final String bossid, final int bosscount) {
        this.c.getPlayer().setBossLogAcc(bossid, bosscount);
    }
    
    public final ChannelServer getChannelServer() {
        return this.getClient().getChannelServer();
    }
    
    public final MapleCharacter getPlayer() {
        return this.getClient().getPlayer();
    }
    
    public final EventManager getEventManager(final String event) {
        return this.getClient().getChannelServer().getEventSM().getEventManager(event);
    }
    
    public final EventInstanceManager getEventInstance() {
        return this.getClient().getPlayer().getEventInstance();
    }
    
    public final void warp(final int map) {
        final MapleMap mapz = this.getWarpMap(map);
        try {
            this.getClient().getPlayer().changeMap(mapz, mapz.getPortal(Randomizer.nextInt(mapz.getPortals().size())));
        }
        catch (Exception e) {
            this.getClient().getPlayer().changeMap(mapz, mapz.getPortal(0));
        }
    }
    
    public final void warp_Instanced(final int map) {
        final MapleMap mapz = this.getMap_Instanced(map);
        try {
            this.getClient().getPlayer().changeMap(mapz, mapz.getPortal(Randomizer.nextInt(mapz.getPortals().size())));
        }
        catch (Exception e) {
            this.getClient().getPlayer().changeMap(mapz, mapz.getPortal(0));
        }
    }
    
    public final void instantMapWarp(final int map, final int portal) {
        final MapleMap mapz = this.getWarpMap(map);
        if (portal != 0 && map == this.c.getPlayer().getMapId()) {
            final Point portalPos = new Point(this.c.getPlayer().getMap().getPortal(portal).getPosition());
            this.c.getSession().writeAndFlush((Object)MaplePacketCreator.instantMapWarp((byte)portal));
            this.c.getPlayer().checkFollow();
            this.c.getPlayer().getMap().movePlayer(this.c.getPlayer(), portalPos);
        }
        else {
            this.c.getPlayer().changeMap(mapz, mapz.getPortal(portal));
        }
    }
    
    public final void warp(final int map, final int portal) {
        final MapleMap mapz = this.getWarpMap(map);
        if (portal != 0 && map == this.getClient().getPlayer().getMapId()) {
            final Point portalPos = new Point(this.c.getPlayer().getMap().getPortal(portal).getPosition());
            if (portalPos.distanceSq((Point2D)this.getPlayer().getPosition()) < 90000.0) {
                this.getClient().sendPacket(MaplePacketCreator.instantMapWarp((byte)portal));
                this.getClient().getPlayer().checkFollow();
                this.getClient().getPlayer().getMap().movePlayer(this.c.getPlayer(), portalPos);
            }
            else {
                this.getClient().getPlayer().changeMap(mapz, mapz.getPortal(portal));
            }
        }
        else {
            this.getClient().getPlayer().changeMap(mapz, mapz.getPortal(portal));
        }
    }
    
    public final void warpS(final int map, final int portal) {
        final MapleMap mapz = this.getWarpMap(map);
        this.getClient().getPlayer().changeMap(mapz, mapz.getPortal(portal));
    }
    
    public final void warp(final int map, String portal) {
        final MapleMap mapz = this.getWarpMap(map);
        if (map == 109060000 || map == 109060002 || map == 109060004) {
            portal = mapz.getSnowballPortal();
        }
        if (map == this.getClient().getPlayer().getMapId()) {
            final Point portalPos = new Point(this.c.getPlayer().getMap().getPortal(portal).getPosition());
            if (portalPos.distanceSq((Point2D)this.getPlayer().getPosition()) < 90000.0) {
                this.getClient().getPlayer().checkFollow();
                this.getClient().sendPacket(MaplePacketCreator.instantMapWarp((byte)this.getClient().getPlayer().getMap().getPortal(portal).getId()));
                this.getClient().getPlayer().getMap().movePlayer(this.c.getPlayer(), new Point(this.c.getPlayer().getMap().getPortal(portal).getPosition()));
            }
            else {
                this.getClient().getPlayer().changeMap(mapz, mapz.getPortal(portal));
            }
        }
        else {
            this.getClient().getPlayer().changeMap(mapz, mapz.getPortal(portal));
        }
    }
    
    public final void warpS(final int map, String portal) {
        final MapleMap mapz = this.getWarpMap(map);
        if (map == 109060000 || map == 109060002 || map == 109060004) {
            portal = mapz.getSnowballPortal();
        }
        this.getClient().getPlayer().changeMap(mapz, mapz.getPortal(portal));
    }
    
    public final void warpMap(final int mapid, final String portal) {
        final MapleMap map = this.getMap(mapid);
        for (final MapleCharacter chr : this.getClient().getPlayer().getMap().getCharactersThreadsafe()) {
            chr.changeMap(map, map.getPortal(portal));
        }
    }
    
    public final void warpMap(final int mapid, final int portal) {
        final MapleMap map = this.getMap(mapid);
        for (final MapleCharacter chr : this.getClient().getPlayer().getMap().getCharactersThreadsafe()) {
            chr.changeMap(map, map.getPortal(portal));
        }
    }
    
    public final void playPortalSE() {
        this.getClient().sendPacket(MaplePacketCreator.showOwnBuffEffect(0, 8));
    }
    
    private final MapleMap getWarpMap(final int map) {
        return ChannelServer.getInstance(this.c.getChannel()).getMapFactory().getMap(map);
    }
    
    public final MapleMap getMap() {
        return this.getClient().getPlayer().getMap();
    }
    
    public final MapleMap getMap(final int map) {
        return this.getWarpMap(map);
    }
    
    public final MapleMap getMap_Instanced(final int map) {
        return (this.getClient().getPlayer().getEventInstance() == null) ? this.getMap(map) : this.getClient().getPlayer().getEventInstance().getMapInstance(map);
    }
    
    public void spawnMonster(final int id, final int qty) {
        this.spawnMob(id, qty, new Point(this.c.getPlayer().getPosition()));
    }
    
    public final void spawnMobOnMap(final int id, final int qty, final int x, final int y, final int map) {
        for (int i = 0; i < qty; ++i) {
            this.getMap(map).spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(id), new Point(x, y));
        }
    }
    
    public final void spawnMob(final int id, final int qty, final int x, final int y) {
        this.spawnMob(id, qty, new Point(x, y));
    }
    
    public final void spawnMob(final int id, final int x, final int y) {
        this.spawnMob(id, 1, new Point(x, y));
    }
    
    private void spawnMob(final int id, final int qty, final Point pos) {
        for (int i = 0; i < qty; ++i) {
            this.getClient().getPlayer().getMap().spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(id), pos);
        }
    }
    
    public final void killMob(final int ids) {
        this.getClient().getPlayer().getMap().killMonster(ids);
    }
    
    public final void killAllMob() {
        this.getClient().getPlayer().getMap().killAllMonsters(true);
    }
    
    public final void addHP(final int delta) {
        this.getClient().getPlayer().addHP(delta);
    }
    
    public final int getPlayerStat(final String type) {
        if (type.equals((Object)"LVL")) {
            return this.getClient().getPlayer().getLevel();
        }
        if (type.equals((Object)"STR")) {
            return this.getClient().getPlayer().getStat().getStr();
        }
        if (type.equals((Object)"DEX")) {
            return this.getClient().getPlayer().getStat().getDex();
        }
        if (type.equals((Object)"INT")) {
            return this.getClient().getPlayer().getStat().getInt();
        }
        if (type.equals((Object)"LUK")) {
            return this.getClient().getPlayer().getStat().getLuk();
        }
        if (type.equals((Object)"HP")) {
            return this.getClient().getPlayer().getStat().getHp();
        }
        if (type.equals((Object)"MP")) {
            return this.getClient().getPlayer().getStat().getMp();
        }
        if (type.equals((Object)"MAXHP")) {
            return this.getClient().getPlayer().getStat().getMaxHp();
        }
        if (type.equals((Object)"MAXMP")) {
            return this.getClient().getPlayer().getStat().getMaxMp();
        }
        if (type.equals((Object)"RAP")) {
            return this.getClient().getPlayer().getRemainingAp();
        }
        if (type.equals((Object)"RSP")) {
            return this.getClient().getPlayer().getRemainingSp();
        }
        if (type.equals((Object)"GID")) {
            return this.getClient().getPlayer().getGuildId();
        }
        if (type.equals((Object)"GRANK")) {
            return this.getClient().getPlayer().getGuildRank();
        }
        if (type.equals((Object)"ARANK")) {
            return this.getClient().getPlayer().getAllianceRank();
        }
        if (type.equals((Object)"GM")) {
            return this.getClient().getPlayer().isGM() ? 1 : 0;
        }
        if (type.equals((Object)"ADMIN")) {
            return this.getClient().getPlayer().hasGmLevel(5) ? 1 : 0;
        }
        if (type.equals((Object)"GENDER")) {
            return this.getClient().getPlayer().getGender();
        }
        if (type.equals((Object)"FACE")) {
            return this.getClient().getPlayer().getFace();
        }
        if (type.equals((Object)"HAIR")) {
            return this.getClient().getPlayer().getHair();
        }
        return -1;
    }
    
    public final String getName() {
        return this.getClient().getPlayer().getName();
    }
    
    public final boolean haveItemTime(final int itemid) {
        if (this.haveItem(itemid)) {
            final MapleInventoryType type = GameConstants.getInventoryType(itemid);
            for (final IItem item : this.getChar().getInventory(type)) {
                if (item.getItemId() == itemid) {
                    return item.getExpiration() == -1L;
                }
            }
            return false;
        }
        return false;
    }
    
    public final boolean haveItemTimeNo(final int itemid) {
        if (this.haveItem(itemid)) {
            final MapleInventoryType type = GameConstants.getInventoryType(itemid);
            for (final IItem item : this.getChar().getInventory(type)) {
                if (item.getItemId() == itemid) {
                    return item.getExpiration() > 0L;
                }
            }
            return false;
        }
        return false;
    }
    
    public final boolean haveItem(final int itemid) {
        return this.haveItem(itemid, 1);
    }
    
    public final boolean haveItem(final int itemid, final int quantity) {
        return this.haveItem(itemid, quantity, false, true);
    }
    
    public final boolean haveItem(final int itemid, final int quantity, final boolean checkEquipped, final boolean greaterOrEquals) {
        return this.getClient().getPlayer().haveItem(itemid, quantity, checkEquipped, greaterOrEquals);
    }
    
    public final boolean canHold() {
        for (int i = 1; i <= 5; ++i) {
            if (this.c.getPlayer().getInventory(MapleInventoryType.getByType((byte)i)).getNextFreeSlot() <= -1) {
                return false;
            }
        }
        return true;
    }
    
    public final boolean canHoldByType(final byte bytype, final int num) {
        return this.c.getPlayer().getInventory(MapleInventoryType.getByType(bytype)).getSlotLimit() - (this.c.getPlayer().getInventory(MapleInventoryType.getByType(bytype)).getNumSlotLimit() + 1) > num;
    }
    
    public final boolean canHoldByTypea(final byte bytype, final int num) {
        return this.c.getPlayer().getInventory(MapleInventoryType.getByType(bytype)).getSlotLimit() - (this.c.getPlayer().getInventory(MapleInventoryType.getByType(bytype)).getNextFreeSlot() - 1) > num;
    }
    
    public final boolean canHold(final int itemid) {
        return this.getClient().getPlayer().getInventory(GameConstants.getInventoryType(itemid)).getNextFreeSlot() > -1;
    }
    
    public final boolean canHold(final int itemid, final int quantity) {
        return MapleInventoryManipulator.checkSpace(this.c, itemid, quantity, "");
    }
    
    public final MapleQuestStatus getQuestRecord(final int id) {
        return this.getClient().getPlayer().getQuestNAdd(MapleQuest.getInstance(id));
    }
    
    public final byte getQuestStatus(final int id) {
        return this.getClient().getPlayer().getQuestStatus(id);
    }
    
    public final boolean isQuestActive(final int id) {
        return this.getQuestStatus(id) == 1;
    }
    
    public final boolean isQuestFinished(final int id) {
        return this.getQuestStatus(id) == 2;
    }
    
    public final void showQuestMsg(final String msg) {
        this.getClient().sendPacket(MaplePacketCreator.showQuestMsg(msg));
    }
    
    public final void forceStartQuest(final int id, final String data) {
        MapleQuest.getInstance(id).forceStart(this.c.getPlayer(), 0, data);
    }
    
    public final void forceStartQuest(final int id, final int data, final boolean filler) {
        MapleQuest.getInstance(id).forceStart(this.c.getPlayer(), 0, filler ? String.valueOf(data) : null);
    }
    
    public void forceStartQuest(final int id) {
        MapleQuest.getInstance(id).forceStart(this.c.getPlayer(), 0, null);
    }
    
    public void forceCompleteQuest(final int id) {
        MapleQuest.getInstance(id).forceComplete(this.getPlayer(), 0);
    }
    
    public void spawnNpc(final int npcId) {
        this.getClient().getPlayer().getMap().spawnNpc(npcId, this.getClient().getPlayer().getPosition());
    }
    
    public final void spawnNpc(final int npcId, final int x, final int y) {
        this.getClient().getPlayer().getMap().spawnNpc(npcId, new Point(x, y));
    }
    
    public final void spawnNpc(final int npcId, final Point pos) {
        this.getClient().getPlayer().getMap().spawnNpc(npcId, pos);
    }
    
    public final void removeNpc(final int mapid, final int npcId) {
        this.getClient().getChannelServer().getMapFactory().getMap(mapid).removeNpc(npcId);
    }
    
    public final void forceStartReactor(final int mapid, final int id) {
        final MapleMap map = this.getClient().getChannelServer().getMapFactory().getMap(mapid);
        for (final MapleMapObject remo : map.getAllReactorsThreadsafe()) {
            final MapleReactor react = (MapleReactor)remo;
            if (react.getReactorId() == id) {
                react.forceStartReactor(this.c);
                break;
            }
        }
    }
    
    public final void destroyReactor(final int mapid, final int id) {
        final MapleMap map = this.getClient().getChannelServer().getMapFactory().getMap(mapid);
        for (final MapleMapObject remo : map.getAllReactorsThreadsafe()) {
            final MapleReactor react = (MapleReactor)remo;
            if (react.getReactorId() == id) {
                react.hitReactor(this.c);
                break;
            }
        }
    }
    
    public final void hitReactor(final int mapid, final int id) {
        final MapleMap map = this.getClient().getChannelServer().getMapFactory().getMap(mapid);
        for (final MapleMapObject remo : map.getAllReactorsThreadsafe()) {
            final MapleReactor react = (MapleReactor)remo;
            if (react.getReactorId() == id) {
                react.hitReactor(this.c);
                break;
            }
        }
    }
    
    public final int getJob() {
        return this.getClient().getPlayer().getJob();
    }
    
    public final void gainPotion(final int type, final int amount) {
        this.getClient().getPlayer().modifyCSPoints(type, amount, true);
    }
    
    public final int getPotion(final int type) {
        return this.getClient().getPlayer().getCSPoints(type);
    }
    
    public final void gainNX(final int amount) {
        this.gainPotion(1, amount);
    }
    
    public final int getNX() {
        return this.getPotion(1);
    }
    
    public final void gainMaplePoint(final int amount) {
        this.gainPotion(2, amount);
    }
    
    public final int getMaplePoint() {
        return this.getPotion(2);
    }
    
    public final void gainItemPeriod(final int id, final short quantity, final int period) {
        this.gainItem(id, quantity, false, (long)period, -1, "");
    }
    
    public final void gainItemPeriod(final int id, final short quantity, final long period, final String owner) {
        this.gainItem(id, quantity, false, period, -1, owner);
    }
    
    public final void gainItem(final int id, final short quantity) {
        this.gainItem(id, quantity, false, 0L, -1, "");
    }
    
    public final void gainItem(final int id, final short quantity, final boolean randomStats) {
        this.gainItem(id, quantity, randomStats, 0L, -1, "");
    }
    
    public final void gainItem(final int id, final short quantity, final boolean randomStats, final int slots) {
        this.gainItem(id, quantity, randomStats, 0L, slots, "");
    }
    
    public final void gainItem(final int id, final short quantity, final long period) {
        this.gainItem(id, quantity, false, period, -1, "");
    }
    
    public final void gainItemTime(final int id, final short quantity, final long period) {
        if (MapleItemInformationProvider.getInstance().isCash(id)) {
            this.gainItem(id, quantity, false, period, -1, "");
        }
        else {
            this.gainItem(id, quantity, false, 0L, -1, "");
        }
    }
    
    public final void gainItem(final int id, final short quantity, final boolean randomStats, final long period, final int slots) {
        this.gainItem(id, quantity, randomStats, period, slots, "");
    }
    
    public final void gainItem(final int id, final short quantity, final boolean randomStats, final long period, final int slots, final String owner) {
        this.gainItem(id, quantity, randomStats, period, slots, owner, this.c);
    }
    
    public final void gainItem(final int id, final short quantity, final boolean randomStats, final long period, final int slots, final String owner, final MapleClient cg) {
        if (quantity >= 0) {
            final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            final MapleInventoryType type = GameConstants.getInventoryType(id);
            if (!MapleInventoryManipulator.checkSpace(cg, id, (int)quantity, "")) {
                return;
            }
            if (type.equals((Object)MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(id) && !GameConstants.isBullet(id)) {
                final Equip item = (Equip)(Equip)(randomStats ? ii.randomizeStats((Equip)ii.getEquipById(id)) : ii.getEquipById(id));
                if (period > 0L) {
                    item.setExpiration(System.currentTimeMillis() + period * 60L * 60L * 1000L);
                }
                if (slots > 0) {
                    item.setUpgradeSlots((byte)(item.getUpgradeSlots() + slots));
                }
                if (owner != null) {
                    item.setOwner(owner);
                }
                final String name = ii.getName(id);
                if (id / 10000 == 114 && name != null && name.length() > 0) {
                    final String msg = "你已获得称号 <" + name + ">";
                    cg.getPlayer().dropMessage(-1, msg);
                    cg.getPlayer().dropMessage(5, msg);
                }
                MapleInventoryManipulator.addbyItem(cg, item.copy());
            }
            else {
                MaplePet pet;
                if (類型.寵物(id)) {
                    pet = MaplePet.createPet(id, MapleInventoryIdentifier.getInstance());
                }
                else {
                    pet = null;
                }
                MapleInventoryManipulator.addById(cg, id, quantity, (owner == null) ? "" : owner, pet, period);
            }
        }
        else {
            MapleInventoryManipulator.removeById(cg, GameConstants.getInventoryType(id), id, -quantity, true, false);
        }
        cg.sendPacket(MaplePacketCreator.getShowItemGain(id, quantity, true));
    }
    
    public final void gainItem(final int id, final int str, final int dex, final int luk, final int Int, final int hp, final int mp, final int watk, final int matk, final int wdef, final int mdef, final int hb, final int mz, final int ty, final int yd, final int time) {
        this.gainItemS(id, str, dex, luk, Int, hp, mp, watk, matk, wdef, mdef, hb, mz, ty, yd, this.c, time);
    }
    
    public final void gainItem(final int id, final int str, final int dex, final int luk, final int Int, final int hp, final int mp, final int watk, final int matk, final int wdef, final int mdef, final int hb, final int mz, final int ty, final int yd) {
        this.gainItemS(id, str, dex, luk, Int, hp, mp, watk, matk, wdef, mdef, hb, mz, ty, yd, this.c, 0);
    }
    
    public final void gainItemS(final int id, final int str, final int dex, final int luk, final int Int, final int hp, final int mp, final int watk, final int matk, final int wdef, final int mdef, final int hb, final int mz, final int ty, final int yd, final MapleClient cg, final int time) {
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        final MapleInventoryType type = GameConstants.getInventoryType(id);
        if (!MapleInventoryManipulator.checkSpace(cg, id, 1, "")) {
            return;
        }
        if (type.equals((Object)MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(id) && !GameConstants.isBullet(id)) {
            final Equip item = (Equip)ii.getEquipById(id);
            final String name = ii.getName(id);
            if (id / 10000 == 114 && name != null && name.length() > 0) {
                final String msg = "你已获得称号 <" + name + ">";
                cg.getPlayer().dropMessage(5, msg);
                cg.getPlayer().dropMessage(5, msg);
            }
            if (time > 0) {
                item.setExpiration(System.currentTimeMillis() + (long)(time * 60 * 60 * 1000));
            }
            if (str > 0) {
                item.setStr((short)str);
            }
            if (dex > 0) {
                item.setDex((short)dex);
            }
            if (luk > 0) {
                item.setLuk((short)luk);
            }
            if (Int > 0) {
                item.setInt((short)Int);
            }
            if (hp > 0) {
                item.setHp((short)hp);
            }
            if (mp > 0) {
                item.setMp((short)mp);
            }
            if (watk > 0) {
                item.setWatk((short)watk);
            }
            if (matk > 0) {
                item.setMatk((short)matk);
            }
            if (wdef > 0) {
                item.setWdef((short)wdef);
            }
            if (mdef > 0) {
                item.setMdef((short)mdef);
            }
            if (hb > 0) {
                item.setAvoid((short)hb);
            }
            if (mz > 0) {
                item.setAcc((short)mz);
            }
            if (ty > 0) {
                item.setJump((short)ty);
            }
            if (yd > 0) {
                item.setSpeed((short)yd);
            }
            MapleInventoryManipulator.addbyItem(cg, item.copy());
        }
        else {
            MapleInventoryManipulator.addById(cg, id, (short)1, "", (byte)0);
        }
        cg.getSession().write((Object)MaplePacketCreator.getShowItemGain(id, (short)1, true));
    }
    
    public final void gainItemStatus(final int id, final short quantity) {
        this.gainItemStatus(id, quantity, false, 0L, -1, "");
    }
    
    public final void gainItemStatus(final int id, final short quantity, final boolean randomStats, final long period, final int slots, final String owner) {
        this.gainItemStatus(id, quantity, randomStats, period, slots, owner, this.c);
    }
    
    public final void gainItemStatus(final int id, final short quantity, final boolean randomStats, final long period, final int slots, final String owner, final MapleClient cg) {
        if (quantity >= 0) {
            final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            final MapleInventoryType type = GameConstants.getInventoryType(id);
            if (!MapleInventoryManipulator.checkSpace(cg, id, (int)quantity, "")) {
                return;
            }
            if (type.equals((Object)MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(id) && !GameConstants.isBullet(id)) {
                final Equip item = (Equip)(Equip)(randomStats ? ii.randomizeStats((Equip)ii.getEquipById(id)) : ii.getEquipById(id));
                if (period > 0L) {
                    item.setExpiration(System.currentTimeMillis() + period * 24L * 60L * 60L * 1000L);
                }
                if (slots > 0) {
                    item.setUpgradeSlots((byte)(item.getUpgradeSlots() + slots));
                }
                if (owner != null) {
                    item.setOwner(owner);
                }
                item.setStr((short)1);
                item.setDex((short)1);
                item.setInt((short)1);
                item.setLuk((short)1);
                final String name = ii.getName(id);
                if (id / 10000 == 114 && name != null && name.length() > 0) {
                    final String msg = "你已获得称号 <" + name + ">";
                    cg.getPlayer().dropMessage(-1, msg);
                    cg.getPlayer().dropMessage(5, msg);
                }
                MapleInventoryManipulator.addbyItem(cg, item.copy());
            }
            else {
                MaplePet pet;
                if (類型.寵物(id)) {
                    pet = MaplePet.createPet(id, MapleInventoryIdentifier.getInstance());
                }
                else {
                    pet = null;
                }
                MapleInventoryManipulator.addById(cg, id, quantity, (owner == null) ? "" : owner, pet, period);
            }
        }
        else {
            MapleInventoryManipulator.removeById(cg, GameConstants.getInventoryType(id), id, -quantity, true, false);
        }
        cg.sendPacket(MaplePacketCreator.getShowItemGain(id, quantity, true));
    }
    
    public final void changeMusic(final String songName) {
        this.getPlayer().getMap().broadcastMessage(MaplePacketCreator.musicChange(songName));
    }
    
    public final void worldMessage(final int type, final String message) {
        Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(type, message));
    }
    
    public final void playerMessage(final String message) {
        this.playerMessage(5, message);
    }
    
    public final void mapMessage(final String message) {
        this.mapMessage(5, message);
    }
    
    public final void guildMessage(final String message) {
        this.guildMessage(5, message);
    }
    
    public final void playerMessage(final int type, final String message) {
        this.getClient().sendPacket(MaplePacketCreator.serverNotice(type, message));
    }
    
    public final void mapMessage(final int type, final String message) {
        this.getClient().getPlayer().getMap().broadcastMessage(MaplePacketCreator.serverNotice(type, message));
    }
    
    public final void guildMessage(final int type, final String message) {
        if (this.getPlayer().getGuildId() > 0) {
            Guild.guildPacket(this.getPlayer().getGuildId(), MaplePacketCreator.serverNotice(type, message));
        }
    }
    
    public final MapleGuild getGuild() {
        return this.getGuild(this.getPlayer().getGuildId());
    }
    
    public final MapleGuild getGuild(final int guildid) {
        return Guild.getGuild(guildid);
    }
    
    public final MapleParty getParty() {
        return this.getClient().getPlayer().getParty();
    }
    
    public final int getCurrentPartyId(final int mapid) {
        return this.getMap(mapid).getCurrentPartyId();
    }
    
    public final boolean isLeader() {
        return this.getParty() != null && this.getParty().getLeader().getId() == this.getClient().getPlayer().getId();
    }
    
    public final boolean isAllPartyMembersAllowedJob(final int job) {
        if (this.c.getPlayer().getParty() == null) {
            return false;
        }
        for (final MaplePartyCharacter mem : this.getClient().getPlayer().getParty().getMembers()) {
            if (mem.getJobId() / 100 != job) {
                return false;
            }
        }
        return true;
    }
    
    public final boolean allMembersHere() {
        if (this.c.getPlayer().getParty() == null) {
            return false;
        }
        for (final MaplePartyCharacter mem : this.getClient().getPlayer().getParty().getMembers()) {
            final MapleCharacter chr = this.getClient().getPlayer().getMap().getCharacterById(mem.getId());
            if (chr == null) {
                return false;
            }
        }
        return true;
    }
    
    public final void warpParty(final int mapId) {
        if (this.getPlayer().getParty() == null || this.getPlayer().getParty().getMembers().size() == 1) {
            this.warp(mapId, 0);
            return;
        }
        final MapleMap target = this.getMap(mapId);
        final int cMap = this.getPlayer().getMapId();
        for (final MaplePartyCharacter chr : this.getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = this.getChannelServer().getPlayerStorage().getCharacterById(chr.getId());
            if (curChar != null && (curChar.getMapId() == cMap || curChar.getEventInstance() == this.getPlayer().getEventInstance())) {
                curChar.changeMap(target, target.getPortal(0));
            }
        }
    }
    
    public final void warpParty(final int mapId, final int portal) {
        if (this.getPlayer().getParty() == null || this.getPlayer().getParty().getMembers().size() == 1) {
            if (portal < 0) {
                this.warp(mapId);
            }
            else {
                this.warp(mapId, portal);
            }
            return;
        }
        final boolean rand = portal < 0;
        final MapleMap target = this.getMap(mapId);
        final int cMap = this.getPlayer().getMapId();
        for (final MaplePartyCharacter chr : this.getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = this.getChannelServer().getPlayerStorage().getCharacterById(chr.getId());
            if (curChar != null && (curChar.getMapId() == cMap || curChar.getEventInstance() == this.getPlayer().getEventInstance())) {
                if (rand) {
                    try {
                        curChar.changeMap(target, target.getPortal(Randomizer.nextInt(target.getPortals().size())));
                    }
                    catch (Exception e) {
                        curChar.changeMap(target, target.getPortal(0));
                    }
                }
                else {
                    curChar.changeMap(target, target.getPortal(portal));
                }
            }
        }
    }
    
    public final void warpParty_Instanced(final int mapId) {
        if (this.getPlayer().getParty() == null || this.getPlayer().getParty().getMembers().size() == 1) {
            this.warp_Instanced(mapId);
            return;
        }
        final MapleMap target = this.getMap_Instanced(mapId);
        final int cMap = this.getPlayer().getMapId();
        for (final MaplePartyCharacter chr : this.getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = this.getChannelServer().getPlayerStorage().getCharacterById(chr.getId());
            if (curChar != null && (curChar.getMapId() == cMap || curChar.getEventInstance() == this.getPlayer().getEventInstance())) {
                curChar.changeMap(target, target.getPortal(0));
            }
        }
    }
    
    public void gainMeso(final int gain) {
        this.getClient().getPlayer().gainMeso(gain, true, false, true);
    }
    
    public void gainExp(final int gain) {
        this.getClient().getPlayer().gainExp(gain, true, true, true);
    }
    
    public void gainExpR(final int gain) {
        this.getClient().getPlayer().gainExp(gain * this.getClient().getChannelServer().getExpRate(), true, true, true);
    }
    
    public final void givePartyItems(final int id, final short quantity, final List<MapleCharacter> party) {
        for (final MapleCharacter chr : party) {
            if (quantity >= 0) {
                MapleInventoryManipulator.addById(chr.getClient(), id, quantity);
            }
            else {
                MapleInventoryManipulator.removeById(chr.getClient(), GameConstants.getInventoryType(id), id, -quantity, true, false);
            }
            chr.getClient().sendPacket(MaplePacketCreator.getShowItemGain(id, quantity, true));
        }
    }
    
    public final void givePartyItems(final int id, final short quantity) {
        this.givePartyItems(id, quantity, false);
    }
    
    public final boolean canPartyHold() {
        for (final MaplePartyCharacter chr : this.getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = this.getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                for (int i = 1; i <= 5; ++i) {
                    if (curChar.getInventory(MapleInventoryType.getByType((byte)i)).getNextFreeSlot() <= -1) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    public final void givePartyItems(final int id, final short quantity, final boolean removeAll) {
        if (this.getPlayer().getParty() == null || this.getPlayer().getParty().getMembers().size() == 1) {
            this.gainItem(id, (short)(removeAll ? (-this.getPlayer().itemQuantity(id)) : quantity));
            return;
        }
        for (final MaplePartyCharacter chr : this.getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = this.getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                this.gainItem(id, (short)(removeAll ? (-curChar.itemQuantity(id)) : quantity), false, 0L, 0, "", curChar.getClient());
            }
        }
    }
    
    public final void givePartyExp(final int amount, final List<MapleCharacter> party) {
        for (final MapleCharacter chr : party) {
            chr.gainExp(amount * this.getClient().getChannelServer().getExpRate(), true, true, true);
        }
    }
    
    public final void givePartyExp(final int amount) {
        if (this.getPlayer().getParty() == null || this.getPlayer().getParty().getMembers().size() == 1) {
            this.gainExp(amount * this.getClient().getChannelServer().getExpRate());
            return;
        }
        for (final MaplePartyCharacter chr : this.getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = this.getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                curChar.gainExp(amount * this.getClient().getChannelServer().getExpRate(), true, true, true);
            }
        }
    }
    
    public final void givePartyNX(final int amount, final List<MapleCharacter> party) {
        for (final MapleCharacter chr : party) {
            chr.modifyCSPoints(1, amount, true);
        }
    }
    
    public final void givePartyNX(final int amount) {
        if (this.getPlayer().getParty() == null || this.getPlayer().getParty().getMembers().size() == 1) {
            this.gainNX(amount);
            return;
        }
        for (final MaplePartyCharacter chr : this.getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = this.getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                curChar.modifyCSPoints(1, amount, true);
            }
        }
    }
    
    public final void endPartyQuest(final int amount, final List<MapleCharacter> party) {
        for (final MapleCharacter chr : party) {
            chr.endPartyQuest(amount);
        }
    }
    
    public final void endPartyQuest(final int amount) {
        if (this.getPlayer().getParty() == null || this.getPlayer().getParty().getMembers().size() == 1) {
            this.getPlayer().endPartyQuest(amount);
            return;
        }
        for (final MaplePartyCharacter chr : this.getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = this.getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                curChar.endPartyQuest(amount);
            }
        }
    }
    
    public final void removeFromParty(final int id, final List<MapleCharacter> party) {
        for (final MapleCharacter chr : party) {
            final int possesed = chr.getInventory(GameConstants.getInventoryType(id)).countById(id);
            if (possesed > 0) {
                MapleInventoryManipulator.removeById(this.c, GameConstants.getInventoryType(id), id, possesed, true, false);
                chr.getClient().sendPacket(MaplePacketCreator.getShowItemGain(id, (short)(-possesed), true));
            }
        }
    }
    
    public final void removeFromParty(final int id) {
        this.givePartyItems(id, (short)0, true);
    }
    
    public final void useSkill(final int skill, final int level) {
        if (level <= 0) {
            return;
        }
        SkillFactory.getSkill(skill).getEffect(level).applyTo(this.c.getPlayer());
    }
    
    public final void useItem(final int id) {
        MapleItemInformationProvider.getInstance().getItemEffect(id).applyTo(this.c.getPlayer());
        this.getClient().sendPacket(UIPacket.getStatusMsg(id));
    }
    
    public final void useItemEffect(final int id) {
        MapleItemInformationProvider.getInstance().getItemEffect(id).applyTo(this.c.getPlayer());
        this.getClient().sendPacket(MaplePacketCreator.enableActions());
    }
    
    public final void cancelItem(final int id) {
        this.getClient().getPlayer().cancelEffect(MapleItemInformationProvider.getInstance().getItemEffect(id), false, -1L);
    }
    
    public final int getMorphState() {
        return this.getClient().getPlayer().getMorphState();
    }
    
    public final void removeAll(final int id) {
        this.getClient().getPlayer().removeAll(id, true);
    }
    
    public final void gainCloseness(final int closeness, final int index) {
        final MaplePet pet = this.getPlayer().getPet(index);
        if (pet != null) {
            pet.setCloseness(pet.getCloseness() + closeness);
            this.getClient().sendPacket(PetPacket.updatePet(pet, this.getPlayer().getInventory(MapleInventoryType.CASH).getItem((short)(byte)pet.getInventoryPosition())));
        }
    }
    
    public final void gainClosenessAll(final int closeness) {
        for (final MaplePet pet : this.getPlayer().getPets()) {
            if (pet != null) {
                pet.setCloseness(pet.getCloseness() + closeness);
                this.getClient().sendPacket(PetPacket.updatePet(pet, this.getPlayer().getInventory(MapleInventoryType.CASH).getItem((short)(byte)pet.getInventoryPosition())));
            }
        }
    }
    
    public final void resetMap(final int mapid) {
        this.getMap(mapid).resetFully();
    }
    
    public final void openNpc(final int id) {
        this.openNpc(id, null);
    }
    
    public final void openNpc(final int id, final int mode) {
        this.openNpc(this.getClient(), id, mode, null);
    }
    
    public final void openNpc(final MapleClient cg, final int id) {
        NPCScriptManager.getInstance().dispose(cg);
        this.openNpc(cg, id, 0, null);
    }
    
    public final void openNpc(final int id, final String script) {
        this.openNpc(this.getClient(), id, script);
    }
    
    public final void openNpc(final MapleClient cg, final int id, final String script) {
        this.openNpc(this.getClient(), id, 0, script);
    }
    
    public final void openNpc(final MapleClient cg, final int id, final int mode, final String script) {
        cg.removeClickedNPC();
        NPCScriptManager.getInstance().start(cg, id, mode, script);
    }
    
    public final int getMapId() {
        return this.getClient().getPlayer().getMapId();
    }
    
    public final boolean haveMonster(final int mobid) {
        for (final MapleMapObject obj : this.getClient().getPlayer().getMap().getAllMonstersThreadsafe()) {
            final MapleMonster mob = (MapleMonster)obj;
            if (mob.getId() == mobid) {
                return true;
            }
        }
        return false;
    }
    
    public final int getChannelNumber() {
        return this.getClient().getChannel();
    }
    
    public final int getMonsterCount(final int mapid) {
        return this.getClient().getChannelServer().getMapFactory().getMap(mapid).getNumMonsters();
    }
    
    public final void teachSkill(final int id, final byte level, final byte masterlevel) {
        this.getPlayer().changeSkillLevel(SkillFactory.getSkill(id), level, masterlevel);
    }
    
    public final void teachSkill(final int id, byte level) {
        final ISkill skil = SkillFactory.getSkill(id);
        if (this.getPlayer().getSkillLevel(skil) > level) {
            level = this.getPlayer().getSkillLevel(skil);
        }
        this.getPlayer().changeSkillLevel(skil, level, skil.getMaxLevel());
    }
    
    public final int getPlayerCount(final int mapid) {
        return this.getClient().getChannelServer().getMapFactory().getMap(mapid).getCharactersSize();
    }
    
    public final void dojo_getUp() {
        this.getClient().sendPacket(MaplePacketCreator.updateInfoQuest(1207, "pt=1;min=4;belt=1;tuto=1"));
        this.getClient().sendPacket(MaplePacketCreator.Mulung_DojoUp2());
        this.getClient().sendPacket(MaplePacketCreator.instantMapWarp((byte)6));
    }
    
    public final boolean dojoAgent_NextMap(final boolean dojo, final boolean fromresting) {
        if (dojo) {
            return Event_DojoAgent.warpNextMap(this.c.getPlayer(), fromresting);
        }
        return Event_DojoAgent.warpNextMap_Agent(this.c.getPlayer(), fromresting);
    }
    
    public final int dojo_getPts() {
        return this.getClient().getPlayer().getDojo();
    }
    
    public final MapleEvent getEvent(final String loc) {
        return this.getClient().getChannelServer().getEvent(MapleEventType.valueOf(loc));
    }
    
    public final int getSavedLocation(final String loc) {
        final Integer ret = this.getClient().getPlayer().getSavedLocation(SavedLocationType.fromString(loc));
        if (ret == null || (int)ret == -1) {
            return 100000000;
        }
        return (int)ret;
    }
    
    public final void saveLocation(final String loc) {
        this.getClient().getPlayer().saveLocation(SavedLocationType.fromString(loc));
    }
    
    public final void saveReturnLocation(final String loc) {
        this.getClient().getPlayer().saveLocation(SavedLocationType.fromString(loc), this.getClient().getPlayer().getMap().getReturnMap().getId());
    }
    
    public final void clearSavedLocation(final String loc) {
        this.getClient().getPlayer().clearSavedLocation(SavedLocationType.fromString(loc));
    }
    
    public final void summonMsg(final String msg) {
        if (!this.c.getPlayer().hasSummon()) {
            this.playerSummonHint(true);
        }
        this.getClient().sendPacket(UIPacket.summonMessage(msg));
    }
    
    public final void summonMsg(final int type) {
        if (!this.c.getPlayer().hasSummon()) {
            this.playerSummonHint(true);
        }
        this.getClient().sendPacket(UIPacket.summonMessage(type));
    }
    
    public final void showInstruction(final String msg, final int width, final int height) {
        this.getClient().sendPacket(MaplePacketCreator.sendHint(msg, width, height));
    }
    
    public final void playerSummonHint(final boolean summon) {
        this.getClient().getPlayer().setHasSummon(summon);
        this.getClient().sendPacket(UIPacket.summonHelper(summon));
    }
    
    public final String getInfoQuest(final int id) {
        return this.getClient().getPlayer().getInfoQuest(id);
    }
    
    public final void updateInfoQuest(final int id, final String data) {
        this.getClient().getPlayer().updateInfoQuest(id, data);
    }
    
    public final boolean getEvanIntroState(final String data) {
        return this.getInfoQuest(22013).equals((Object)data);
    }
    
    public final void updateEvanIntroState(final String data) {
        this.updateInfoQuest(22013, data);
    }
    
    public final void Aran_Start() {
        this.getClient().sendPacket(UIPacket.Aran_Start());
    }
    
    public final void evanTutorial(final String data, final int v1) {
        this.getClient().sendPacket(MaplePacketCreator.getEvanTutorial(data));
    }
    
    public final void AranTutInstructionalBubble(final String data) {
        this.getClient().sendPacket(UIPacket.AranTutInstructionalBalloon(data));
    }
    
    public final void ShowWZEffect(final String data) {
        this.getClient().sendPacket(UIPacket.AranTutInstructionalBalloon(data));
    }
    
    public final void showWZEffect(final String data) {
        this.getClient().sendPacket(UIPacket.ShowWZEffect(data));
    }
    
    public final void EarnTitleMsg(final String data) {
        this.getClient().sendPacket(UIPacket.EarnTitleMsg(data));
    }
    
    public final void MovieClipIntroUI(final boolean enabled) {
        this.getClient().sendPacket(UIPacket.IntroDisableUI(enabled));
        this.getClient().sendPacket(UIPacket.IntroLock(enabled));
    }
    
    public MapleInventoryType getInvType(final int i) {
        return MapleInventoryType.getByType((byte)i);
    }
    
    public String getItemName(final int id) {
        return MapleItemInformationProvider.getInstance().getName(id);
    }
    
    public void gainPet(final int id, final String name, final int level, final int closeness, final int fullness) {
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        this.gainPet(id, name, level, closeness, fullness, (long)ii.getPetLife(id), ii.getPetFlagInfo(id));
    }
    
    public void gainPet(final int id, final String name, final int level, final int closeness, final int fullness, final int period) {
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        this.gainPet(id, name, level, closeness, fullness, (long)period, ii.getPetFlagInfo(id));
    }
    
    public void gainPet(int id, final String name, int level, int closeness, int fullness, final long period, final short flags) {
        if (id > 5010000 || id < 5000000) {
            id = 5000000;
        }
        if (level > 30) {
            level = 30;
        }
        if (closeness > 30000) {
            closeness = 30000;
        }
        if (fullness > 100) {
            fullness = 100;
        }
        try {
            MapleInventoryManipulator.addById(this.c, id, (short)1, "", MaplePet.createPet(id, name, level, closeness, fullness, MapleInventoryIdentifier.getInstance(), (id == 5000054) ? ((int)period) : 0, flags), 45L);
        }
        catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }
    
    public void removeSlot(final int invType, final byte slot, final short quantity) {
        MapleInventoryManipulator.removeFromSlot(this.c, this.getInvType(invType), (short)slot, quantity, true);
    }
    
    public void gainGP(final int gp) {
        if (this.getPlayer().getGuildId() <= 0) {
            return;
        }
        Guild.gainGP(this.getPlayer().getGuildId(), gp);
    }
    
    public int getGP() {
        if (this.getPlayer().getGuildId() <= 0) {
            return 0;
        }
        return Guild.getGP(this.getPlayer().getGuildId());
    }
    
    public void showMapEffect(final String path) {
        this.getClient().sendPacket(UIPacket.MapEff(path));
    }
    
    public int itemQuantity(final int itemid) {
        return this.getPlayer().itemQuantity(itemid);
    }
    
    public EventInstanceManager getDisconnected(final String event) {
        final EventManager em = this.getEventManager(event);
        if (em == null) {
            return null;
        }
        for (final EventInstanceManager eim : em.getInstances()) {
            if (eim.isDisconnected(this.c.getPlayer()) && eim.getPlayerCount() > 0) {
                return eim;
            }
        }
        return null;
    }
    
    public boolean isAllReactorState(final int reactorId, final int state) {
        boolean ret = false;
        for (final MapleReactor r : this.getMap().getAllReactorsThreadsafe()) {
            if (r.getReactorId() == reactorId) {
                ret = (r.getState() == state);
            }
        }
        return ret;
    }
    
    public long getCurrentTime() {
        return System.currentTimeMillis();
    }
    
    public void spawnMonster(final int id) {
        this.spawnMonster(id, 1, new Point(this.getPlayer().getPosition()));
    }
    
    public void spawnMonster(final int id, final int x, final int y) {
        this.spawnMonster(id, 1, new Point(x, y));
    }
    
    public void spawnMonster(final int id, final int qty, final int x, final int y) {
        this.spawnMonster(id, qty, new Point(x, y));
    }
    
    public void spawnMonster(final int id, final int qty, final Point pos) {
        for (int i = 0; i < qty; ++i) {
            this.getMap().spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(id), pos);
        }
    }
    
    public void sendNPCText(final String text, final int npc) {
        this.getMap().broadcastMessage(MaplePacketCreator.getNPCTalk(npc, (byte)0, text, "00 00", (byte)0));
    }
    
    public void warpAllPlayer(final int from, final int to) {
        final MapleMap tomap = this.getMapFactory().getMap(to);
        final MapleMap frommap = this.getMapFactory().getMap(from);
        final List<MapleCharacter> list = frommap.getCharactersThreadsafe();
        if (tomap != null && frommap != null && list != null && frommap.getCharactersSize() > 0) {
            for (final MapleMapObject mmo : list) {
                ((MapleCharacter)mmo).changeMap(tomap, tomap.getPortal(0));
            }
        }
    }
    
    public MapleMapFactory getMapFactory() {
        return this.getChannelServer().getMapFactory();
    }
    
    public void enterMTS() {
        InterServerHandler.EnterCashShop(this.c, this.c.getPlayer(), true);
    }
    
    public int getChannelOnline() {
        return this.getClient().getChannelServer().getConnectedClients();
    }
    
    public int getTotalOnline() {
        return (int)Integer.valueOf(ChannelServer.getAllInstances().stream().map(cserv -> Integer.valueOf(cserv.getConnectedClients())).reduce(Integer.valueOf(0), Integer::sum));
    }
    
    public int getMP() {
        return this.getPlayer().getMP();
    }
    
    public void setMP(final int x) {
        this.getPlayer().setMP(x);
    }
    
    public int save(final boolean dc, final boolean fromcs) {
        try {
            return this.getPlayer().saveToDB(dc, fromcs);
        }
        catch (UnsupportedOperationException ex) {
            return 0;
        }
    }
    
    public void save() {
        this.save(false, false);
    }
    
    public boolean hasSquadByMap() {
        return this.getPlayer().getMap().getSquadByMap() != null;
    }
    
    public boolean hasEventInstance() {
        return this.getPlayer().getEventInstance() != null;
    }
    
    public boolean hasEMByMap() {
        return this.getPlayer().getMap().getEMByMap() != null;
    }
    
    public void processCommand(final String line) {
        CommandProcessor.processCommand(this.getClient(), line, CommandType.NORMAL);
    }
    
    public void warpPlayer(final int from, final int to) {
        final MapleMap mapto = this.c.getChannelServer().getMapFactory().getMap(to);
        final MapleMap mapfrom = this.c.getChannelServer().getMapFactory().getMap(from);
        for (final MapleCharacter chr : mapfrom.getCharactersThreadsafe()) {
            chr.changeMap(mapto, mapto.getPortal(0));
        }
    }
    
    public void isVipMedalName() {
        if (this.getOneTimeLog("关闭VIP星星數顯示") < 1) {
            this.setOneTimeLog("关闭VIP星星數顯示");
            this.c.getPlayer().dropMessage(5, "关闭VIP星星數顯示。");
        }
        else {
            this.deleteOneTimeLog("关闭VIP星星數顯示");
            this.c.getPlayer().dropMessage(5, "开启VIP星星數顯示。");
        }
    }
    
    public int getVip() {
        return this.getPlayer().getVip();
    }
    
    public void getItemLog(final String mob, final String itemmob) {
        FileoutputUtil.logToFile("logs/Data/" + mob + ".txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + this.c.getSession().remoteAddress().toString().split(":")[0] + " 账号 " + this.c.getAccountName() + " 账号ID " + this.c.getAccID() + " 角色名 " + this.c.getPlayer().getName() + " 角色ID " + this.c.getPlayer().getId() + " " + itemmob);
    }
    
    public int getAccNewTime(final String time) {
        return this.getPlayer().getAccNewTime(time);
    }
    
    public int getQianDaoTime(final String time) {
        return this.getPlayer().getQianDaoTime(time);
    }
    
    public int getQianDaoAcLog(final String time) {
        return this.getPlayer().getQianDaoAcLog(time);
    }
    
    public void giveEventPrize() {
        final int reward = RandomRewards.getInstance().getEventReward();
        if (reward == 0) {
            this.getPlayer().gainMeso(66666, true, false, false);
            this.getPlayer().dropMessage(5, "你获得 66666 金币");
        }
        else if (reward == 1) {
            this.getPlayer().gainMeso(399999, true, false, false);
            this.getPlayer().dropMessage(5, "你获得 399999 金币");
        }
        else if (reward == 2) {
            this.getPlayer().gainMeso(666666, true, false, false);
            this.getPlayer().dropMessage(5, "你获得 666666 金币");
        }
        else if (reward == 3) {
            this.getPlayer().addFame(10);
            this.getPlayer().dropMessage(5, "你获得 10 名聲");
        }
        else {
            int max_quantity = 1;
            switch (reward) {
                case 5062000: {
                    max_quantity = 3;
                    break;
                }
                case 5220000: {
                    max_quantity = 25;
                    break;
                }
                case 4031307:
                case 5050000: {
                    max_quantity = 5;
                    break;
                }
                case 2022121: {
                    max_quantity = 10;
                    break;
                }
            }
            final int quantity = ((max_quantity > 1) ? Randomizer.nextInt(max_quantity) : 0) + 1;
            if (MapleInventoryManipulator.checkSpace(this.getPlayer().getClient(), reward, quantity, "")) {
                MapleInventoryManipulator.addById(this.getPlayer().getClient(), reward, (short)quantity);
                this.getPlayer().dropMessage(5, "恭喜获得" + MapleItemInformationProvider.getInstance().getName(reward));
            }
            else {
                this.getPlayer().gainMeso(100000, true, false, false);
                this.getPlayer().dropMessage(5, "參加獎 100000 金币");
            }
        }
    }
    
    public List<IItem> getMonsterRidinglist() {
        final MapleInventory Equip = this.c.getPlayer().getInventory(MapleInventoryType.EQUIP);
        final List<IItem> ret = new ArrayList<IItem>();
        for (final IItem tep : Equip) {
            if (tep.getItemId() >= 1930000 && tep.getItemId() <= 1992050) {
                ret.add(tep);
            }
        }
        return ret;
    }
    
    public String getCharacterNameById(final int id) {
        this.c.getPlayer();
        final String name = MapleCharacter.getCharacterNameById(id);
        return name;
    }
    
    public final int getCharacterIdByName(final String name) {
        this.c.getPlayer();
        final int id = MapleCharacter.getCharacterIdByName(name);
        return id;
    }
    
    public int getCharacterByNameLevel(final String name) {
        this.c.getPlayer();
        final int level = MapleCharacter.getCharacterByName(name).getLevel();
        return level;
    }
    
    public List<IItem> getCsEquipList() {
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        final MapleInventory Equip = this.c.getPlayer().getInventory(MapleInventoryType.EQUIP);
        final List<IItem> ret = new ArrayList<IItem>();
        for (final IItem tep : Equip) {
            if (ii.isCash(tep.getItemId())) {
                ret.add(tep);
            }
        }
        return ret;
    }
    
    public Equip getEquipStat(final byte slot) {
        final Equip sel = (Equip)this.c.getPlayer().getInventory(MapleInventoryType.EQUIP).getItem((short)slot);
        return sel;
    }
    
    public void dropCs(final byte type, final short src, final short quantity) {
        MapleInventoryManipulator.dropCs(this.c, MapleInventoryType.getByType(type), src, quantity);
    }
    
    public final boolean canwncs() {
        for (final int i : GameConstants.blockedMaps) {
            if (this.c.getPlayer().getMapId() == i) {
                this.c.getPlayer().dropMessage(5, "當前地图無法使用.");
                return false;
            }
        }
        if (this.c.getPlayer().getMapId() == 749060605 || this.c.getPlayer().getMapId() == 229010000 || this.c.getPlayer().getMapId() == 910000000) {
            this.c.getPlayer().dropMessage(5, "當前地图無法使用.");
            return false;
        }
        if (this.c.getPlayer().getLevel() < 10 && this.c.getPlayer().getJob() != 200) {
            this.c.getPlayer().dropMessage(5, "你的等級不足10級無法使用.");
            return false;
        }
        if (this.c.getPlayer().hasBlockedInventory(true) || this.c.getPlayer().getMap().getSquadByMap() != null || this.c.getPlayer().getEventInstance() != null || this.c.getPlayer().getMap().getEMByMap() != null || this.c.getPlayer().getMapId() >= 990000000) {
            this.c.getPlayer().dropMessage(5, "請稍後再試");
            return false;
        }
        if ((this.c.getPlayer().getMapId() >= 680000210 && this.c.getPlayer().getMapId() <= 680000502) || (this.c.getPlayer().getMapId() / 1000 == 980000 && this.c.getPlayer().getMapId() != 980000000) || this.c.getPlayer().getMapId() / 100 == 1030008 || this.c.getPlayer().getMapId() / 100 == 922010 || this.c.getPlayer().getMapId() / 10 == 13003000) {
            this.c.getPlayer().dropMessage(5, "請稍後再試.");
            return false;
        }
        return true;
    }
    
    public int getGamePoints() {
        return this.c.getPlayer().getGamePoints();
    }
    
    public void gainGamePoints(final int amount) {
        this.c.getPlayer().gainGamePoints(amount);
    }
    
    public void resetGamePoints() {
        this.c.getPlayer().updateGamePoints(0);
    }
    
    public int getGamePointsPD() {
        return this.c.getPlayer().get在线时间();
    }
    
    public void gainGamePointsPD(final int amount) {
        this.c.getPlayer().gainGamePointsPD(amount);
    }
    
    public void resetGamePointsPD() {
        this.c.getPlayer().resetGamePointsPD();
    }
    
    public int getEquipItemType(final int itemid) {
        if (類型.帽子(itemid)) {
            return 1;
        }
        if (類型.臉飾(itemid)) {
            return 2;
        }
        if (類型.眼飾(itemid)) {
            return 3;
        }
        if (類型.耳環(itemid)) {
            return 4;
        }
        if (類型.上衣(itemid)) {
            return 5;
        }
        if (類型.套服(itemid)) {
            return 6;
        }
        if (類型.褲裙(itemid)) {
            return 7;
        }
        if (類型.鞋子(itemid)) {
            return 8;
        }
        if (類型.手套(itemid)) {
            return 9;
        }
        if (類型.盾牌(itemid)) {
            return 9;
        }
        if (類型.披風(itemid)) {
            return 10;
        }
        if (類型.戒指(itemid)) {
            return 11;
        }
        if (類型.墜飾(itemid)) {
            return 12;
        }
        if (類型.腰帶(itemid)) {
            return 13;
        }
        if (類型.勳章(itemid)) {
            return 15;
        }
        if (類型.武器(itemid)) {
            return 16;
        }
        if (類型.副手(itemid)) {
            return 17;
        }
        return 0;
    }
    
    public void forceReAddItem(final Item item, final byte type) {
        this.c.getPlayer().forceReAddItem_Flag((IItem)item, MapleInventoryType.getByType(type));
        this.c.getPlayer().equipChanged();
    }
    
    public void StatsZs() {
        final Map<MapleStat, Integer> statups = new EnumMap<MapleStat, Integer>(MapleStat.class);
        this.c.getPlayer().setLevel((short)1);
        this.c.getPlayer().levelUp();
        if (this.c.getPlayer().getExp() < 0) {
            this.c.getPlayer().gainExp(-this.c.getPlayer().getExp(), false, false, true);
        }
        this.c.getPlayer().getStat().str = 4;
        this.c.getPlayer().getStat().dex = 4;
        this.c.getPlayer().getStat().int_ = 4;
        this.c.getPlayer().getStat().luk = 4;
        this.c.getPlayer().setHpMpApUsed((short)0);
        this.c.getPlayer().setRemainingAp((short)13);
        this.c.getPlayer().setRemainingSp(0);
        this.c.getSession().write((Object)MaplePacketCreator.updateSp(this.c.getPlayer(), false));
        statups.put(MapleStat.STR, Integer.valueOf((int)this.c.getPlayer().getStat().getStr()));
        statups.put(MapleStat.DEX, Integer.valueOf((int)this.c.getPlayer().getStat().getDex()));
        statups.put(MapleStat.LUK, Integer.valueOf((int)this.c.getPlayer().getStat().getLuk()));
        statups.put(MapleStat.INT, Integer.valueOf((int)this.c.getPlayer().getStat().getInt()));
        statups.put(MapleStat.HP, Integer.valueOf((int)this.c.getPlayer().getStat().getHp()));
        statups.put(MapleStat.MAXHP, Integer.valueOf((int)this.c.getPlayer().getStat().getMaxHp()));
        statups.put(MapleStat.MP, Integer.valueOf((int)this.c.getPlayer().getStat().getMp()));
        statups.put(MapleStat.MAXMP, Integer.valueOf((int)this.c.getPlayer().getStat().getMaxMp()));
        statups.put(MapleStat.AVAILABLEAP, Integer.valueOf((int)this.c.getPlayer().getRemainingAp()));
        this.c.getPlayer().getStat().recalcLocalStats();
        this.c.getSession().write((Object)MaplePacketCreator.updatePlayerStats(statups, this.c.getPlayer()));
        this.c.getPlayer().fakeRelog();
    }
    
    public void maxSkillsByJob() {
        this.c.getPlayer().maxSkillsByJob();
    }
    
    public String getServerName() {
        return ServerConfig.SERVERNAME;
    }
    
    public void gainDY(final int gain) {
        this.c.getPlayer().modifyCSPoints(2, gain, true);
    }
    
    public final void worldMessage2(final int type, final String message) {
        switch (type) {
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
            case 9:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18: {
                Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(type, this.c.getChannel(), message));
                break;
            }
            default: {
                Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(6, this.c.getChannel(), message));
                break;
            }
        }
    }
    
    public final void 全服黄色喇叭(final String message) {
        Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(9, this.c.getChannel(), message));
    }
    
    public boolean canHoldSlots(final int slot) {
        for (int i = 1; i <= 5; ++i) {
            if (this.c.getPlayer().getInventory(MapleInventoryType.getByType((byte)i)).isFull(slot)) {
                return false;
            }
        }
        return true;
    }
    
    public int getItemQuantity(final int itemid) {
        return this.c.getPlayer().getItemQuantity(itemid);
    }
    
    public final int getNX(final int 类型) {
        return this.c.getPlayer().getCSPoints(类型);
    }
    
    public final void gainD(final int amount) {
        this.c.getPlayer().modifyCSPoints(2, amount, true);
    }
    
    public final int 判断职业() {
        return this.c.getPlayer().getJob();
    }
    
    public final void 判断组队() {
        this.c.getPlayer().getParty();
    }
    
    public final void 判断频道() {
        this.getClient().getChannel();
    }
    
    public final void 给抵用券(final int amount) {
        this.c.getPlayer().modifyCSPoints(2, amount, true);
    }
    
    public final void 收抵用券(final int amount) {
        this.c.getPlayer().modifyCSPoints(2, -amount, true);
    }
    
    public final void 给点券(final int amount) {
        this.c.getPlayer().modifyCSPoints(1, amount, true);
    }
    
    public final void 收点券(final int amount) {
        this.c.getPlayer().modifyCSPoints(1, -amount, true);
    }
    
    public final void 给物品(final int id, final short quantity) {
        this.gainItem(id, quantity, false, 0L, -1, "");
    }
    
    public final void 物品兑换1(final int id1, final short shuliang1, final int id2, final int shuliang2) {
        if (!this.haveItem(id1, (int)shuliang1, true, true)) {
            this.c.getPlayer().dropMessage(1, "你没有足够的兑换物品。");
            return;
        }
        this.gainItem(id1, (short)(-shuliang1), false, 0L, -1, "");
        this.gainItem(id2, (short)shuliang2, false, 0L, -1, "");
        this.c.getPlayer().dropMessage(1, "兑换成功。");
    }
    
    public final void 概率给物品(final int id, final short quantity, final double 概率2, final String a) {
        this.概率给物品(id, quantity, 概率2);
    }
    
    public final void 概率给物品(final int id, final short quantity, double 概率2) {
        if (概率2 > 100.0) {
            概率2 = 100.0;
        }
        if (概率2 <= 0.0) {
            概率2 = 0.0;
        }
        final double 概率3 = Math.ceil(Math.random() * 100.0);
        if (概率2 > 0.0 && 概率3 <= 概率2) {
            this.gainItem(id, quantity, false, 0L, -1, "");
        }
    }
    
    public final void 概率给物品2(final int id, final short quantity, final double 概率2, final String a) {
        this.概率给物品2(id, quantity, 概率2);
    }
    
    public final void 概率给物品2(final int id, final short quantity, double 概率2) {
        if (概率2 > 100.0) {
            概率2 = 100.0;
        }
        if (概率2 <= 0.0) {
            概率2 = 0.0;
        }
        final double 概率3 = Math.ceil(Math.random() * 100.0);
        if (概率2 > 0.0 && 概率3 <= 概率2) {
            short 数量 = (short)(int)Math.ceil(Math.random() * (double)quantity);
            if (数量 == 0) {
                数量 = 1;
            }
            this.gainItem(id, 数量, false, 0L, -1, "");
        }
    }
    
    public final void 收物品(final int id, final short quantity) {
        this.gainItem(id, (short)(-quantity), false, 0L, -1, "");
    }
    
    public final void gainItemS(final String Owner, final int id, final int sj, final int Flag, final int str, final int dex, final int luk, final int Int, final int hp, final int mp, final int watk, final int matk, final int wdef, final int mdef, final int hb, final int mz, final int ty, final int yd, final MapleClient cg) {
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        final MapleInventoryType type = GameConstants.getInventoryType(id);
        if (!MapleInventoryManipulator.checkSpace(cg, id, 1, "")) {
            return;
        }
        if (type.equals((Object)MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(id) && !GameConstants.isBullet(id)) {
            final Equip item = (Equip)(Equip)ii.getEquipById(id);
            final String name = ii.getName(id);
            if (id / 10000 == 114 && name != null && name.length() > 0) {
                final String msg = "你已获得称号 <" + name + ">";
                cg.getPlayer().dropMessage(5, msg);
            }
            if (Owner != null) {
                item.setOwner(Owner);
            }
            if (sj > 0) {
                item.setUpgradeSlots((byte)(short)sj);
            }
            if (Flag > 0) {
                item.setFlag((byte)(short)Flag);
            }
            if (str > 0) {
                item.setStr((short)str);
            }
            if (dex > 0) {
                item.setDex((short)dex);
            }
            if (luk > 0) {
                item.setLuk((short)luk);
            }
            if (Int > 0) {
                item.setInt((short)Int);
            }
            if (hp > 0) {
                item.setHp((short)hp);
            }
            if (mp > 0) {
                item.setMp((short)mp);
            }
            if (watk > 0) {
                item.setWatk((short)watk);
            }
            if (matk > 0) {
                item.setMatk((short)matk);
            }
            if (wdef > 0) {
                item.setWdef((short)wdef);
            }
            if (mdef > 0) {
                item.setMdef((short)mdef);
            }
            if (hb > 0) {
                item.setAvoid((short)hb);
            }
            if (mz > 0) {
                item.setAcc((short)mz);
            }
            if (ty > 0) {
                item.setJump((short)ty);
            }
            if (yd > 0) {
                item.setSpeed((short)yd);
            }
            MapleInventoryManipulator.addbyItem(cg, item.copy());
        }
        else {
            MapleInventoryManipulator.addById(cg, id, (short)1, "", (byte)0);
        }
        cg.sendPacket(MaplePacketCreator.getShowItemGain(id, (short)1, true));
    }
    
    public final void gainItem(final String Owner, final int id, final int sj, final int Flag, final int str, final int dex, final int luk, final int Int, final int hp, final int mp, final int watk, final int matk, final int wdef, final int mdef, final int hb, final int mz, final int ty, final int yd) {
        this.gainItemS(Owner, id, sj, Flag, str, dex, luk, Int, hp, mp, watk, matk, wdef, mdef, hb, mz, ty, yd, this.c);
    }
    
    public final void 给属性装备(final int id, final int sj, final int Flag, final int str, final int dex, final int luk, final int Int, final int hp, final int mp, final int watk, final int matk, final int wdef, final int mdef, final int hb, final int mz, final int ty, final int yd) {
        this.给属性装备(id, sj, Flag, str, dex, luk, Int, hp, mp, watk, matk, wdef, mdef, hb, mz, ty, yd, 0L, this.c);
    }
    
    public final void 给属性装备(final int id, final int sj, final int Flag, final int str, final int dex, final int luk, final int Int, final int hp, final int mp, final int watk, final int matk, final int wdef, final int mdef, final int hb, final int mz, final int ty, final int yd, final int 给予时间) {
        this.给属性装备(id, sj, Flag, str, dex, luk, Int, hp, mp, watk, matk, wdef, mdef, hb, mz, ty, yd, (long)给予时间, this.c);
    }
    
    public final void 给属性装备(final int id, final int sj, final int Flag, final int str, final int dex, final int luk, final int Int, final int hp, final int mp, final int watk, final int matk, final int wdef, final int mdef, final int hb, final int mz, final int ty, final int yd, final long 给予时间, final MapleClient cg) {
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        final MapleInventoryType type = GameConstants.getInventoryType(id);
        if (!MapleInventoryManipulator.checkSpace(cg, id, 1, "")) {
            return;
        }
        if (type.equals((Object)MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(id) && !GameConstants.isBullet(id)) {
            final Equip item = (Equip)(Equip)ii.getEquipById(id);
            final String name = ii.getName(id);
            if (id / 10000 == 114 && name != null && name.length() > 0) {
                final String msg = "你已获得称号 <" + name + ">";
                cg.getPlayer().dropMessage(5, msg);
            }
            if (sj > 0) {
                item.setUpgradeSlots((byte)(short)sj);
            }
            if (Flag > 0) {
                item.setFlag((byte)(short)Flag);
            }
            if (str > 0) {
                item.setStr((short)str);
            }
            if (dex > 0) {
                item.setDex((short)dex);
            }
            if (luk > 0) {
                item.setLuk((short)luk);
            }
            if (Int > 0) {
                item.setInt((short)Int);
            }
            if (hp > 0) {
                item.setHp((short)hp);
            }
            if (mp > 0) {
                item.setMp((short)mp);
            }
            if (watk > 0) {
                item.setWatk((short)watk);
            }
            if (matk > 0) {
                item.setMatk((short)matk);
            }
            if (wdef > 0) {
                item.setWdef((short)wdef);
            }
            if (mdef > 0) {
                item.setMdef((short)mdef);
            }
            if (hb > 0) {
                item.setAvoid((short)hb);
            }
            if (mz > 0) {
                item.setAcc((short)mz);
            }
            if (ty > 0) {
                item.setJump((short)ty);
            }
            if (yd > 0) {
                item.setSpeed((short)yd);
            }
            if (给予时间 > 0L) {
                item.setExpiration(System.currentTimeMillis() + 给予时间 * 60L * 60L * 1000L);
            }
            MapleInventoryManipulator.addbyItem(cg, item.copy());
        }
        else {
            MapleInventoryManipulator.addById(cg, id, (short)1, "", (byte)0);
        }
        cg.sendPacket(MaplePacketCreator.getShowItemGain(id, (short)1, true));
    }
    
    public int getHour() {
        return Calendar.getInstance().get(11);
    }
    
    public int 判断日() {
        return Calendar.getInstance().get(5);
    }
    
    public int 判断时() {
        return Calendar.getInstance().get(11);
    }
    
    public int getMin() {
        return Calendar.getInstance().get(12);
    }
    
    public int 判断分() {
        return Calendar.getInstance().get(12);
    }
    
    public int getSec() {
        return Calendar.getInstance().get(13);
    }
    
    public final boolean 是否队长() {
        return this.getParty() != null && this.getParty().getLeader().getId() == this.c.getPlayer().getId();
    }
    
    public final void 团队传送地图(final int mapId, final int portal) {
        if (this.getPlayer().getParty() == null || this.getPlayer().getParty().getMembers().size() == 1) {
            if (portal < 0) {
                this.warp(mapId);
            }
            else {
                this.warp(mapId, portal);
            }
            return;
        }
        final boolean rand = portal < 0;
        final MapleMap target = this.getMap(mapId);
        final int cMap = this.getPlayer().getMapId();
        for (final MaplePartyCharacter chr : this.getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = this.getChannelServer().getPlayerStorage().getCharacterById(chr.getId());
            if (curChar != null && (curChar.getMapId() == cMap || curChar.getEventInstance() == this.getPlayer().getEventInstance())) {
                if (rand) {
                    try {
                        curChar.changeMap(target, target.getPortal(Randomizer.nextInt(target.getPortals().size())));
                    }
                    catch (Exception e) {
                        curChar.changeMap(target, target.getPortal(0));
                    }
                }
                else {
                    curChar.changeMap(target, target.getPortal(portal));
                }
            }
        }
    }
    
    public void 给金币(final int gain) {
        this.c.getPlayer().gainMeso(gain, true, false, true);
    }
    
    public void 收金币(final int gain) {
        this.c.getPlayer().gainMeso(-gain, true, false, true);
    }
    
    public void 给经验(final int gain) {
        this.c.getPlayer().gainExp(gain, true, true, true);
    }
    
    public void 收经验(final int gain) {
        this.c.getPlayer().gainExp(-gain, true, true, true);
    }
    
    public final void 给团队道具(final int id, final short quantity) {
        this.givePartyItems(id, quantity, false);
    }
    
    public final void 收团队道具(final int id, final short quantity) {
        this.givePartyItems2(id, quantity, false);
    }
    
    public final void givePartyItems2(final int id, final short quantity, final boolean removeAll) {
        if (this.getPlayer().getParty() == null || this.getPlayer().getParty().getMembers().size() == 1) {
            this.gainItem(id, (short)(removeAll ? (-this.getPlayer().itemQuantity(id)) : (-quantity)));
            return;
        }
        for (final MaplePartyCharacter chr : this.getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = this.getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                this.gainItem(id, (short)(removeAll ? (-curChar.itemQuantity(id)) : (-quantity)), false, 0L, 0, "", curChar.getClient());
            }
        }
    }
    
    public final void 给团队经验(final int amount) {
        if (this.getPlayer().getParty() == null || this.getPlayer().getParty().getMembers().size() == 1) {
            this.gainExp(amount);
            return;
        }
        for (final MaplePartyCharacter chr : this.getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = this.getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                curChar.gainExp(amount, true, true, true);
            }
        }
    }
    
    public final void 给团队点券(final int amount, final List<MapleCharacter> party) {
        for (final MapleCharacter chr : party) {
            chr.modifyCSPoints(1, amount, true);
        }
    }
    
    public final void 给团队抵用券(final int amount, final List<MapleCharacter> party) {
        for (final MapleCharacter chr : party) {
            chr.modifyCSPoints(2, amount, true);
        }
    }
    
    public final void givePartyDY(final int amount) {
        if (this.getPlayer().getParty() == null || this.getPlayer().getParty().getMembers().size() == 1) {
            this.gainDY(amount);
            return;
        }
        for (final MaplePartyCharacter chr : this.getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = this.getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                curChar.modifyCSPoints(2, amount, true);
            }
        }
    }
    
    public final void givePartyMeso(final int amount) {
        if (this.getPlayer().getParty() == null || this.getPlayer().getParty().getMembers().size() == 1) {
            this.gainMeso(amount);
            return;
        }
        for (final MaplePartyCharacter chr : this.getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = this.getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                curChar.gainMeso(amount, true);
            }
        }
    }
    
    public final void 给团队金币(final int amount) {
        if (this.getPlayer().getParty() == null || this.getPlayer().getParty().getMembers().size() == 1) {
            this.gainMeso(amount);
            return;
        }
        for (final MaplePartyCharacter chr : this.getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = this.getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                curChar.gainMeso(amount, true);
            }
        }
    }
    
    public final void 销毁物品(final int id) {
        this.c.getPlayer().removeAll(id);
    }
    
    public void 打开NPC(final int id, final int wh) {
        NPCScriptManager.getInstance().dispose(this.c);
        NPCScriptManager.getInstance().start(this.getClient(), id, wh);
    }
    
    public final int 判断地图() {
        return this.c.getPlayer().getMap().getId();
    }
    
    public final int 判断地图指定怪物数量(final int mobid) {
        int a = 0;
        for (final MapleMapObject obj : this.c.getPlayer().getMap().getAllMonstersThreadsafe()) {
            final MapleMonster mob = (MapleMonster)obj;
            if (mob.getId() == mobid) {
                ++a;
            }
        }
        return a;
    }
    
    public final boolean 判断当前地图指定怪物是否存在(final int mobid) {
        for (final MapleMapObject obj : this.c.getPlayer().getMap().getAllMonstersThreadsafe()) {
            final MapleMonster mob = (MapleMonster)obj;
            if (mob.getId() == mobid) {
                return true;
            }
        }
        return false;
    }
    
    public int 判断技能等级(final int id) {
        return this.getPlayer().getSkillLevel(id);
    }
    
    public final void 给予技能(final int id, final byte level, final byte masterlevel) {
        this.getPlayer().changeSkillLevel(SkillFactory.getSkill(id), level, masterlevel);
    }
    
    public void 给家族GP点(final int gp) {
        if (this.getPlayer().getGuildId() <= 0) {
            return;
        }
        Guild.gainGP(this.getPlayer().getGuildId(), gp);
    }
    
    public int 判断家族GP点() {
        if (this.getPlayer().getGuildId() <= 0) {
            return 0;
        }
        return Guild.getGP(this.getPlayer().getGuildId());
    }
    
    public final void 给团队每日(final String bossid) {
        if (this.getPlayer().getParty() == null || this.getPlayer().getParty().getMembers().size() == 1) {
            this.setBossLog(bossid);
            return;
        }
        for (final MaplePartyCharacter chr : this.getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = this.getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                curChar.setBossLog(bossid);
            }
        }
    }
    
    public int 判断团队每日(final String bossid) {
        int a = 0;
        for (final MaplePartyCharacter chr : this.getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = this.getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                a += curChar.getBossLog(bossid);
            }
        }
        return a;
    }
    
    public int 判断队友是否在场(final String bossid) {
        int a = 0;
        for (final MaplePartyCharacter chr : this.getPlayer().getParty().getMembers()) {
            final MapleCharacter curChar = this.getMap().getCharacterById(chr.getId());
            if (curChar != null) {
                a += curChar.getBossLog(bossid);
            }
        }
        return a;
    }
    
    public int 判断星期() {
        return Calendar.getInstance().get(7);
    }
    
    public int 获取当前星期() {
        return Calendar.getInstance().get(7);
    }
    
    public List<BossRankInfo> getBossRankPointsTop(final String bossname) {
        return BossRankManager.getInstance().getRank(bossname, 1);
    }
    
    public List<BossRankInfo1> getBossRankPointsTop1(final String bossname) {
        return BossRankManager1.getInstance().getRank(bossname, 1);
    }
    
    public List<BossRankInfo2> getBossRankPointsTop2(final String bossname) {
        return BossRankManager2.getInstance().getRank(bossname, 1);
    }
    
    public List<BossRankInfo3> getBossRankPointsTop3(final String bossname) {
        return BossRankManager3.getInstance().getRank(bossname, 1);
    }
    
    public List<BossRankInfo4> getBossRankPointsTop4(final String bossname) {
        return BossRankManager4.getInstance().getRank(bossname, 1);
    }
    
    public List<BossRankInfo5> getBossRankPointsTop5(final String bossname) {
        return BossRankManager5.getInstance().getRank(bossname, 1);
    }
    
    public List<BossRankInfo6> getBossRankPointsTop6(final String bossname) {
        return BossRankManager6.getInstance().getRank(bossname, 1);
    }
    
    public List<BossRankInfo7> getBossRankPointsTop7(final String bossname) {
        return BossRankManager7.getInstance().getRank(bossname, 1);
    }
    
    public List<BossRankInfo8> getBossRankPointsTop8(final String bossname) {
        return BossRankManager8.getInstance().getRank(bossname, 1);
    }
    
    public List<BossRankInfo9> getBossRankPointsTop9(final String bossname) {
        return BossRankManager9.getInstance().getRank(bossname, 1);
    }
    
    public List<BossRankInfo10> getBossRankPointsTop10(final String bossname) {
        return BossRankManager10.getInstance().getRank(bossname, 1);
    }
    
    public List<BossRankInfo> getBossRankCountTop(final String bossname) {
        return BossRankManager.getInstance().getRank(bossname, 2);
    }
    
    public List<BossRankInfo1> getBossRankCountTop1(final String bossname) {
        return BossRankManager1.getInstance().getRank(bossname, 2);
    }
    
    public List<BossRankInfo2> getBossRankCountTop2(final String bossname) {
        return BossRankManager2.getInstance().getRank(bossname, 2);
    }
    
    public List<BossRankInfo3> getBossRankCountTop3(final String bossname) {
        return BossRankManager3.getInstance().getRank(bossname, 2);
    }
    
    public List<BossRankInfo4> getBossRankCountTop4(final String bossname) {
        return BossRankManager4.getInstance().getRank(bossname, 2);
    }
    
    public List<BossRankInfo5> getBossRankCountTop5(final String bossname) {
        return BossRankManager5.getInstance().getRank(bossname, 2);
    }
    
    public List<BossRankInfo6> getBossRankCountTop6(final String bossname) {
        return BossRankManager6.getInstance().getRank(bossname, 2);
    }
    
    public List<BossRankInfo7> getBossRankCountTop7(final String bossname) {
        return BossRankManager7.getInstance().getRank(bossname, 2);
    }
    
    public List<BossRankInfo8> getBossRankCountTop8(final String bossname) {
        return BossRankManager8.getInstance().getRank(bossname, 2);
    }
    
    public List<BossRankInfo9> getBossRankCountTop9(final String bossname) {
        return BossRankManager9.getInstance().getRank(bossname, 2);
    }
    
    public List<BossRankInfo10> getBossRankCountTop10(final String bossname) {
        return BossRankManager10.getInstance().getRank(bossname, 2);
    }
    
    public List<BossRankInfo> getBossRankTop(final String bossname, final byte type) {
        return BossRankManager.getInstance().getRank(bossname, (int)type);
    }
    
    public List<BossRankInfo1> getBossRankTop1(final String bossname, final byte type) {
        return BossRankManager1.getInstance().getRank(bossname, (int)type);
    }
    
    public List<BossRankInfo2> getBossRankTop2(final String bossname, final byte type) {
        return BossRankManager2.getInstance().getRank(bossname, (int)type);
    }
    
    public List<BossRankInfo3> getBossRankTop3(final String bossname, final byte type) {
        return BossRankManager3.getInstance().getRank(bossname, (int)type);
    }
    
    public List<BossRankInfo4> getBossRankTop4(final String bossname, final byte type) {
        return BossRankManager4.getInstance().getRank(bossname, (int)type);
    }
    
    public List<BossRankInfo5> getBossRankTop5(final String bossname, final byte type) {
        return BossRankManager5.getInstance().getRank(bossname, (int)type);
    }
    
    public List<BossRankInfo6> getBossRankTop6(final String bossname, final byte type) {
        return BossRankManager6.getInstance().getRank(bossname, (int)type);
    }
    
    public List<BossRankInfo7> getBossRankTop7(final String bossname, final byte type) {
        return BossRankManager7.getInstance().getRank(bossname, (int)type);
    }
    
    public List<BossRankInfo8> getBossRankTop8(final String bossname, final byte type) {
        return BossRankManager8.getInstance().getRank(bossname, (int)type);
    }
    
    public List<BossRankInfo9> getBossRankTop9(final String bossname, final byte type) {
        return BossRankManager9.getInstance().getRank(bossname, (int)type);
    }
    
    public List<BossRankInfo10> getBossRankTop10(final String bossname, final byte type) {
        return BossRankManager10.getInstance().getRank(bossname, (int)type);
    }
    
    public int setBossRankPoints(final String bossname) {
        return this.setBossRank(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)1, 1);
    }
    
    public int setBossRankPoints1(final String bossname) {
        return this.setBossRank1(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)1, 1);
    }
    
    public int setBossRankPoints2(final String bossname) {
        return this.setBossRank2(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)1, 1);
    }
    
    public int setBossRankPoints3(final String bossname) {
        return this.setBossRank3(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)1, 1);
    }
    
    public int setBossRankPoints4(final String bossname) {
        return this.setBossRank4(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)1, 1);
    }
    
    public int setBossRankPoints5(final String bossname) {
        return this.setBossRank5(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)1, 1);
    }
    
    public int setBossRankPoints6(final String bossname) {
        return this.setBossRank6(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)1, 1);
    }
    
    public int setBossRankPoints7(final String bossname) {
        return this.setBossRank7(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)1, 1);
    }
    
    public int setBossRankPoints8(final String bossname) {
        return this.setBossRank8(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)1, 1);
    }
    
    public int setBossRankPoints9(final String bossname) {
        return this.setBossRank9(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)1, 1);
    }
    
    public int setBossRankPoints10(final String bossname) {
        return this.setBossRank10(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)1, 1);
    }
    
    public int setBossRankCount(final String bossname) {
        return this.setBossRank(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)2, 1);
    }
    
    public int setBossRankCount1(final String bossname) {
        return this.setBossRank1(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)2, 1);
    }
    
    public int setBossRankCount2(final String bossname) {
        return this.setBossRank2(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)2, 1);
    }
    
    public int setBossRankCount3(final String bossname) {
        return this.setBossRank3(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)2, 1);
    }
    
    public int setBossRankCount4(final String bossname) {
        return this.setBossRank4(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)2, 1);
    }
    
    public int setBossRankCount5(final String bossname) {
        return this.setBossRank5(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)2, 1);
    }
    
    public int setBossRankCount6(final String bossname) {
        return this.setBossRank6(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)2, 1);
    }
    
    public int setBossRankCount7(final String bossname) {
        return this.setBossRank7(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)2, 1);
    }
    
    public int setBossRankCount8(final String bossname) {
        return this.setBossRank8(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)2, 1);
    }
    
    public int setBossRankCount9(final String bossname) {
        return this.setBossRank9(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)2, 1);
    }
    
    public int setBossRankCount10(final String bossname) {
        return this.setBossRank10(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)2, 1);
    }
    
    public int setBossRankPoints(final String bossname, final int add) {
        return this.setBossRank(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)1, add);
    }
    
    public int setBossRankPoints1(final String bossname, final int add) {
        return this.setBossRank1(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)1, add);
    }
    
    public int setBossRankPoints2(final String bossname, final int add) {
        return this.setBossRank2(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)1, add);
    }
    
    public int setBossRankPoints3(final String bossname, final int add) {
        return this.setBossRank3(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)1, add);
    }
    
    public int setBossRankPoints4(final String bossname, final int add) {
        return this.setBossRank4(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)1, add);
    }
    
    public int setBossRankPoints5(final String bossname, final int add) {
        return this.setBossRank5(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)1, add);
    }
    
    public int setBossRankPoints6(final String bossname, final int add) {
        return this.setBossRank6(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)1, add);
    }
    
    public int setBossRankPoints7(final String bossname, final int add) {
        return this.setBossRank7(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)1, add);
    }
    
    public int setBossRankPoints8(final String bossname, final int add) {
        return this.setBossRank8(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)1, add);
    }
    
    public int setBossRankPoints9(final String bossname, final int add) {
        return this.setBossRank9(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)1, add);
    }
    
    public int setBossRankPoints10(final String bossname, final int add) {
        return this.setBossRank10(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)1, add);
    }
    
    public int setBossRankCount(final String bossname, final int add) {
        return this.setBossRank(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)2, add);
    }
    
    public int setBossRankCount1(final String bossname, final int add) {
        return this.setBossRank1(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)2, add);
    }
    
    public int setBossRankCount2(final String bossname, final int add) {
        return this.setBossRank2(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)2, add);
    }
    
    public int setBossRankCount3(final String bossname, final int add) {
        return this.setBossRank3(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)2, add);
    }
    
    public int setBossRankCount4(final String bossname, final int add) {
        return this.setBossRank4(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)2, add);
    }
    
    public int setBossRankCount5(final String bossname, final int add) {
        return this.setBossRank5(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)2, add);
    }
    
    public int setBossRankCount6(final String bossname, final int add) {
        return this.setBossRank6(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)2, add);
    }
    
    public int setBossRankCount7(final String bossname, final int add) {
        return this.setBossRank7(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)2, add);
    }
    
    public int setBossRankCount8(final String bossname, final int add) {
        return this.setBossRank8(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)2, add);
    }
    
    public int setBossRankCount9(final String bossname, final int add) {
        return this.setBossRank9(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)2, add);
    }
    
    public int setBossRankCount10(final String bossname, final int add) {
        return this.setBossRank10(this.getPlayer().getId(), this.getPlayer().getName(), bossname, (byte)2, add);
    }
    
    public int 任务(final int sj, final int add) {
        return this.setBossRank8(this.getPlayer().getId(), this.getPlayer().getName(), "赛季积分", (byte)sj, add);
    }
    
    public int 给赛季积分(final int sj, final int add) {
        return this.setBossRank8(this.getPlayer().getId(), this.getPlayer().getName(), "赛季积分", (byte)sj, add);
    }
    
    public int 给SSP点(final int add) {
        return this.setBossRank6(this.getPlayer().getId(), this.getPlayer().getName(), "超级技能点", (byte)2, add);
    }
    
    public int 收SSP点(final int add) {
        return this.setBossRank6(this.getPlayer().getId(), this.getPlayer().getName(), "超级技能点", (byte)2, -add);
    }
    
    public int 给炼金经验(final int add) {
        return this.setBossRank5(this.getPlayer().getId(), this.getPlayer().getName(), "炼金经验", (byte)2, add);
    }
    
    public int 给唠叨经验(final int add) {
        return this.setBossRank4(this.getPlayer().getId(), this.getPlayer().getName(), "唠叨经验", (byte)2, add);
    }
    
    public int 给泡点经验(final int add) {
        return this.setBossRank3(this.getPlayer().getId(), this.getPlayer().getName(), "泡点经验", (byte)2, add);
    }
    
    public int 给挖矿经验(final int add) {
        return this.setBossRank2(this.getPlayer().getId(), this.getPlayer().getName(), "挖矿经验", (byte)2, add);
    }
    
    public int 给钓鱼经验(final int add) {
        return this.setBossRank1(this.getPlayer().getId(), this.getPlayer().getName(), "钓鱼经验", (byte)2, add);
    }
    
    public int setBossRank(final String bossname, final byte type, final int add) {
        return this.setBossRank(this.getPlayer().getId(), this.getPlayer().getName(), bossname, type, add);
    }
    
    public int setBossRank1(final String bossname, final byte type, final int add) {
        return this.setBossRank1(this.getPlayer().getId(), this.getPlayer().getName(), bossname, type, add);
    }
    
    public int setBossRank2(final String bossname, final byte type, final int add) {
        return this.setBossRank2(this.getPlayer().getId(), this.getPlayer().getName(), bossname, type, add);
    }
    
    public int setBossRank3(final String bossname, final byte type, final int add) {
        return this.setBossRank3(this.getPlayer().getId(), this.getPlayer().getName(), bossname, type, add);
    }
    
    public int setBossRank4(final String bossname, final byte type, final int add) {
        return this.setBossRank4(this.getPlayer().getId(), this.getPlayer().getName(), bossname, type, add);
    }
    
    public int setBossRank5(final String bossname, final byte type, final int add) {
        return this.setBossRank5(this.getPlayer().getId(), this.getPlayer().getName(), bossname, type, add);
    }
    
    public int setBossRank6(final String bossname, final byte type, final int add) {
        return this.setBossRank6(this.getPlayer().getId(), this.getPlayer().getName(), bossname, type, add);
    }
    
    public int setBossRank7(final String bossname, final byte type, final int add) {
        return this.setBossRank7(this.getPlayer().getId(), this.getPlayer().getName(), bossname, type, add);
    }
    
    public int setBossRank8(final String bossname, final byte type, final int add) {
        return this.setBossRank8(this.getPlayer().getId(), this.getPlayer().getName(), bossname, type, add);
    }
    
    public int setBossRank9(final String bossname, final byte type, final int add) {
        return this.setBossRank9(this.getPlayer().getId(), this.getPlayer().getName(), bossname, type, add);
    }
    
    public int setBossRank10(final String bossname, final byte type, final int add) {
        return this.setBossRank10(this.getPlayer().getId(), this.getPlayer().getName(), bossname, type, add);
    }
    
    public int setBossRank(final int cid, final String cname, final String bossname, final byte type, final int add) {
        return BossRankManager.getInstance().setLog(cid, cname, bossname, type, add);
    }
    
    public int setBossRank1(final int cid, final String cname, final String bossname, final byte type, final int add) {
        return BossRankManager1.getInstance().setLog(cid, cname, bossname, type, add);
    }
    
    public int setBossRank2(final int cid, final String cname, final String bossname, final byte type, final int add) {
        return BossRankManager2.getInstance().setLog(cid, cname, bossname, type, add);
    }
    
    public int setBossRank3(final int cid, final String cname, final String bossname, final byte type, final int add) {
        return BossRankManager3.getInstance().setLog(cid, cname, bossname, type, add);
    }
    
    public int setBossRank4(final int cid, final String cname, final String bossname, final byte type, final int add) {
        return BossRankManager4.getInstance().setLog(cid, cname, bossname, type, add);
    }
    
    public int setBossRank5(final int cid, final String cname, final String bossname, final byte type, final int add) {
        return BossRankManager5.getInstance().setLog(cid, cname, bossname, type, add);
    }
    
    public int setBossRank6(final int cid, final String cname, final String bossname, final byte type, final int add) {
        return BossRankManager6.getInstance().setLog(cid, cname, bossname, type, add);
    }
    
    public int setBossRank7(final int cid, final String cname, final String bossname, final byte type, final int add) {
        return BossRankManager7.getInstance().setLog(cid, cname, bossname, type, add);
    }
    
    public int setBossRank8(final int cid, final String cname, final String bossname, final byte type, final int add) {
        return BossRankManager8.getInstance().setLog(cid, cname, bossname, type, add);
    }
    
    public int setBossRank9(final int cid, final String cname, final String bossname, final byte type, final int add) {
        return BossRankManager9.getInstance().setLog(cid, cname, bossname, type, add);
    }
    
    public int setBossRank10(final int cid, final String cname, final String bossname, final byte type, final int add) {
        return BossRankManager10.getInstance().setLog(cid, cname, bossname, type, add);
    }
    
    public int getBossRankPoints(final String bossname) {
        return this.getBossRank(bossname, (byte)1);
    }
    
    public int getBossRankPoints1(final String bossname) {
        return this.getBossRank1(bossname, (byte)1);
    }
    
    public int getBossRankPoints2(final String bossname) {
        return this.getBossRank2(bossname, (byte)1);
    }
    
    public int getBossRankPoints3(final String bossname) {
        return this.getBossRank3(bossname, (byte)1);
    }
    
    public int getBossRankPoints4(final String bossname) {
        return this.getBossRank4(bossname, (byte)1);
    }
    
    public int getBossRankPoints5(final String bossname) {
        return this.getBossRank5(bossname, (byte)1);
    }
    
    public int getBossRankPoints6(final String bossname) {
        return this.getBossRank6(bossname, (byte)1);
    }
    
    public int getBossRankPoints7(final String bossname) {
        return this.getBossRank7(bossname, (byte)1);
    }
    
    public int getBossRankPoints8(final String bossname) {
        return this.getBossRank8(bossname, (byte)1);
    }
    
    public int getBossRankPoints9(final String bossname) {
        return this.getBossRank9(bossname, (byte)1);
    }
    
    public int getBossRankPoints10(final String bossname) {
        return this.getBossRank10(bossname, (byte)1);
    }
    
    public int getBossRankCount(final String bossname) {
        return this.getBossRank(bossname, (byte)2);
    }
    
    public int getBossRankCount1(final String bossname) {
        return this.getBossRank1(bossname, (byte)2);
    }
    
    public int getBossRankCount2(final String bossname) {
        return this.getBossRank2(bossname, (byte)2);
    }
    
    public int getBossRankCount3(final String bossname) {
        return this.getBossRank3(bossname, (byte)2);
    }
    
    public int getBossRankCount4(final String bossname) {
        return this.getBossRank4(bossname, (byte)2);
    }
    
    public int getBossRankCount5(final String bossname) {
        return this.getBossRank5(bossname, (byte)2);
    }
    
    public int getBossRankCount6(final String bossname) {
        return this.getBossRank6(bossname, (byte)2);
    }
    
    public int getBossRankCount7(final String bossname) {
        return this.getBossRank7(bossname, (byte)2);
    }
    
    public int getBossRankCount8(final String bossname) {
        return this.getBossRank8(bossname, (byte)2);
    }
    
    public int getBossRankCount9(final String bossname) {
        return this.getBossRank9(bossname, (byte)2);
    }
    
    public int getBossRankCount10(final String bossname) {
        return this.getBossRank10(bossname, (byte)2);
    }
    
    public int getBossRank(final String bossname, final byte type) {
        return this.getBossRank(this.getPlayer().getId(), bossname, type);
    }
    
    public int getBossRank1(final String bossname, final byte type) {
        return this.getBossRank1(this.getPlayer().getId(), bossname, type);
    }
    
    public int getBossRank2(final String bossname, final byte type) {
        return this.getBossRank2(this.getPlayer().getId(), bossname, type);
    }
    
    public int getBossRank3(final String bossname, final byte type) {
        return this.getBossRank3(this.getPlayer().getId(), bossname, type);
    }
    
    public int getBossRank4(final String bossname, final byte type) {
        return this.getBossRank4(this.getPlayer().getId(), bossname, type);
    }
    
    public int getBossRank5(final String bossname, final byte type) {
        return this.getBossRank5(this.getPlayer().getId(), bossname, type);
    }
    
    public int getBossRank6(final String bossname, final byte type) {
        return this.getBossRank6(this.getPlayer().getId(), bossname, type);
    }
    
    public int getBossRank7(final String bossname, final byte type) {
        return this.getBossRank7(this.getPlayer().getId(), bossname, type);
    }
    
    public int getBossRank8(final String bossname, final byte type) {
        return this.getBossRank8(this.getPlayer().getId(), bossname, type);
    }
    
    public int getBossRank9(final String bossname, final byte type) {
        return this.getBossRank9(this.getPlayer().getId(), bossname, type);
    }
    
    public int getBossRank10(final String bossname, final byte type) {
        return this.getBossRank10(this.getPlayer().getId(), bossname, type);
    }
    
    public int getBossRank(final int cid, final String bossname, final byte type) {
        int ret = -1;
        final BossRankInfo info = BossRankManager.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1: {
                ret = info.getPoints();
                break;
            }
            case 2: {
                ret = info.getCount();
                break;
            }
        }
        return ret;
    }
    
    public int getBossRank1(final int cid, final String bossname, final byte type) {
        int ret = -1;
        final BossRankInfo1 info = BossRankManager1.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1: {
                ret = info.getPoints();
                break;
            }
            case 2: {
                ret = info.getCount();
                break;
            }
        }
        return ret;
    }
    
    public int getBossRank2(final int cid, final String bossname, final byte type) {
        int ret = -1;
        final BossRankInfo2 info = BossRankManager2.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1: {
                ret = info.getPoints();
                break;
            }
            case 2: {
                ret = info.getCount();
                break;
            }
        }
        return ret;
    }
    
    public int getBossRank3(final int cid, final String bossname, final byte type) {
        int ret = -1;
        final BossRankInfo3 info = BossRankManager3.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1: {
                ret = info.getPoints();
                break;
            }
            case 2: {
                ret = info.getCount();
                break;
            }
        }
        return ret;
    }
    
    public int getBossRank4(final int cid, final String bossname, final byte type) {
        int ret = -1;
        final BossRankInfo4 info = BossRankManager4.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1: {
                ret = info.getPoints();
                break;
            }
            case 2: {
                ret = info.getCount();
                break;
            }
        }
        return ret;
    }
    
    public int getBossRank5(final int cid, final String bossname, final byte type) {
        int ret = -1;
        final BossRankInfo5 info = BossRankManager5.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1: {
                ret = info.getPoints();
                break;
            }
            case 2: {
                ret = info.getCount();
                break;
            }
        }
        return ret;
    }
    
    public int getBossRank6(final int cid, final String bossname, final byte type) {
        int ret = -1;
        final BossRankInfo6 info = BossRankManager6.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1: {
                ret = info.getPoints();
                break;
            }
            case 2: {
                ret = info.getCount();
                break;
            }
        }
        return ret;
    }
    
    public int getBossRank7(final int cid, final String bossname, final byte type) {
        int ret = -1;
        final BossRankInfo7 info = BossRankManager7.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1: {
                ret = info.getPoints();
                break;
            }
            case 2: {
                ret = info.getCount();
                break;
            }
        }
        return ret;
    }
    
    public int getBossRank8(final int cid, final String bossname, final byte type) {
        int ret = -1;
        final BossRankInfo8 info = BossRankManager8.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1: {
                ret = info.getPoints();
                break;
            }
            case 2: {
                ret = info.getCount();
                break;
            }
        }
        return ret;
    }
    
    public int getBossRank9(final int cid, final String bossname, final byte type) {
        int ret = -1;
        final BossRankInfo9 info = BossRankManager9.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1: {
                ret = info.getPoints();
                break;
            }
            case 2: {
                ret = info.getCount();
                break;
            }
        }
        return ret;
    }
    
    public int getBossRank10(final int cid, final String bossname, final byte type) {
        int ret = -1;
        final BossRankInfo10 info = BossRankManager10.getInstance().getInfo(cid, bossname);
        if (null == info) {
            return ret;
        }
        switch (type) {
            case 1: {
                ret = info.getPoints();
                break;
            }
            case 2: {
                ret = info.getCount();
                break;
            }
        }
        return ret;
    }
    
    public List<IItem> getItemsByType(final byte type) {
        final List<IItem> items = new ArrayList<IItem>();
        final MapleInventoryType itemtype = MapleInventoryType.getByType(type);
        final MapleInventory mi = this.getPlayer().getInventory(itemtype);
        if (mi != null) {
            for (final IItem item : mi.list()) {
                items.add(item);
            }
        }
        return items;
    }
    
    public List<IItem> getItemsByType1(final byte type) {
        final List<IItem> items = new ArrayList<IItem>();
        final MapleInventoryType itemtype = MapleInventoryType.getByType(type);
        final MapleInventory mi = this.getPlayer().getInventory(itemtype);
        if (mi != null) {
            for (final IItem item : mi.list()) {
                items.add(item);
            }
        }
        return items;
    }
    
    public List<IItem> getItemsByType2(final byte type) {
        final List<IItem> items = new ArrayList<IItem>();
        final MapleInventoryType itemtype = MapleInventoryType.getByType(type);
        final MapleInventory mi = this.getPlayer().getInventory(itemtype);
        if (mi != null) {
            for (final IItem item : mi.list()) {
                items.add(item);
            }
        }
        return items;
    }
    
    public int saveBankItem(final IItem item, final short count) {
        return BankItemManager.getInstance().saveItem(this.getPlayer(), item, count);
    }
    
    public int saveBankItem1(final IItem item, final short count) {
        return BankItemManager1.getInstance().saveItem(this.getPlayer(), item, count);
    }
    
    public int saveBankItem2(final IItem item, final short count) {
        return BankItemManager2.getInstance().saveItem(this.getPlayer(), item, count);
    }
    
    public List<BankItem> getBankItems() {
        return BankItemManager.getInstance().getItems(this.getPlayer().getId());
    }
    
    public List<BankItem1> getBankItems1() {
        return BankItemManager1.getInstance().getItems(this.getPlayer().getguildid());
    }
    
    public List<BankItem2> getBankItems2() {
        return BankItemManager2.getInstance().getItems(this.getPlayer().getId());
    }
    
    public int GetPiot(final String Name, final int Channale) {
        int ret = -1;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM FullPoint WHERE channel = ? and Name = ?");
            ps.setInt(1, Channale);
            ps.setString(2, Name);
            final ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("Point");
            rs.close();
            ps.close();
        }
        catch (SQLException ex) {}
        return ret;
    }
    
    public int Getsaiji(final String Name, final int Channale) {
        int ret = -1;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM saiji WHERE channel = ? and Name = ?");
            ps.setInt(1, Channale);
            ps.setString(2, Name);
            final ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("Point");
            rs.close();
            ps.close();
        }
        catch (SQLException ex) {}
        return ret;
    }
    
    public void Gainsaiji(final String Name, final int Channale, final int saiji) {
        try {
            int ret = this.Getsaiji(Name, Channale);
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO saiji (channel, Name,Point) VALUES (?, ?, ?)");
                    ps.setInt(1, Channale);
                    ps.setString(2, Name);
                    ps.setInt(3, ret);
                    ps.execute();
                }
                catch (SQLException e) {
                    System.out.println("xxxxxxxx:" + (Object)e);
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    }
                    catch (SQLException e2) {
                        System.out.println("xxxxxxxxzzzzzzz:" + (Object)e2);
                    }
                }
                finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    }
                    catch (SQLException e2) {
                        System.out.println("xxxxxxxxzzzzzzz:" + (Object)e2);
                    }
                }
            }
            ret += saiji;
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps2 = con.prepareStatement("UPDATE saiji SET `Point` = ? WHERE Name = ? and channel = ?");
            ps2.setInt(1, ret);
            ps2.setString(2, Name);
            ps2.setInt(3, Channale);
            ps2.execute();
            ps2.close();
        }
        catch (SQLException sql) {
            System.err.println("獲取錯誤!!55" + (Object)sql);
        }
    }
    
    public List<ArrayList> getSevenDayPayLog(final int day) {
        final List<Integer> ret = new ArrayList();
        for (int i = 0; i < day; ++i) {
            ret.add(Integer.valueOf(0));
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM paylog WHERE account = ?");
            ps.setString(1, this.c.getAccountName());
            final ResultSet rs = ps.executeQuery();
            final Timestamp currtime = new Timestamp(System.currentTimeMillis());
            while (rs.next()) {
                final int rmb = rs.getInt("rmb");
                final Timestamp time = rs.getTimestamp("paytime");
                final int diffday = (int)((currtime.getTime() - time.getTime()) / 86400000L);
                if (diffday < day) {
                    ret.set(diffday, Integer.valueOf((int)Integer.valueOf(ret.get(diffday)) + rmb));
                }
            }
            ps.close();
            rs.close();
        }
        catch (SQLException e) {
            System.err.println("获取充值记录失败" + (Object)e);
        }
        return (List)ret;
    }
    
    public int 取破攻等级() {
        int 破功等级 = 0;
        try {
            final int cid = this.getPlayer().getId();
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement limitCheck = con.prepareStatement("SELECT * FROM characters WHERE id=" + cid + "");
            final ResultSet rs = limitCheck.executeQuery();
            if (rs.next()) {
                破功等级 = rs.getInt("PGSXDJ");
            }
            limitCheck.close();
            rs.close();
        }
        catch (SQLException ex) {}
        return 破功等级;
    }
    
    public void 给破攻等级(final int 等级) {
        try {
            final int cid = this.getPlayer().getId();
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("UPDATE characters SET PGSXDJ =PGSXDJ+ " + 等级 + " WHERE id = " + cid + "");
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException ex) {}
    }
    
    public void 给破攻等级1(final int 等级) {
        this.c.getPlayer().gainPGSXDJ(等级);
    }
    
    public int getRMB() {
        return this.getPlayer().getRMB();
    }
    
    public void setRMB(final int rmb) {
        this.getPlayer().setRMB(rmb);
    }
    
    public void gainRMB(final int rmb) {
        this.getPlayer().gainRMB(rmb);
    }
    
    public int getTotalRMB() {
        return this.getPlayer().getTotalRMB();
    }
    
    public MapleItemInformationProvider getItemInfo() {
        return MapleItemInformationProvider.getInstance();
    }
    
    public int getDaysPQLog(final String pqName, final int days) {
        return this.getPlayer().getDaysPQLog(pqName, 0, days);
    }
    
    public int getPQLog(final String pqName) {
        return this.getPlayer().getPQLog(pqName);
    }
    
    public int getPQLog(final String pqName, final int type) {
        return this.getPlayer().getPQLog(pqName, type);
    }
    
    public int getPQLog(final String pqName, final int type, final int days) {
        return this.getPlayer().getDaysPQLog(pqName, type, days);
    }
    
    public void setPQLog(final String pqName) {
        this.getPlayer().setPQLog(pqName);
    }
    
    public void setPQLog(final String pqName, final int type) {
        this.getPlayer().setPQLog(pqName, type);
    }
    
    public void setPQLog(final String pqName, final int type, final int count) {
        this.getPlayer().setPQLog(pqName, type, count);
    }
    
    public void resetPQLog(final String pqName) {
        this.getPlayer().resetPQLog(pqName);
    }
    
    public void resetPQLog(final String pqName, final int type) {
        this.getPlayer().resetPQLog(pqName, type);
    }
    
    public void setPartyPQLog(final String pqName) {
        this.setPartyPQLog(pqName, 0);
    }
    
    public void setPartyPQLog(final String pqName, final int type) {
        this.setPartyPQLog(pqName, type, 1);
    }
    
    public void setPartyPQLog(final String pqName, final int type, final int count) {
        if (this.getPlayer().getParty() == null || this.getPlayer().getParty().getMembers().size() == 1) {
            this.getPlayer().setPQLog(pqName, type, count);
            return;
        }
        final int n4 = this.getPlayer().getMapId();
        for (final MaplePartyCharacter partyCharacter : this.getPlayer().getParty().getMembers()) {
            final MapleCharacter player = this.getPlayer().getMap().getCharacterById(partyCharacter.getId());
            if (player != null) {
                if (player.getMapId() != n4) {
                    continue;
                }
                player.setPQLog(pqName, type, count);
            }
        }
    }
    
    public int getEventCount(final String eventId) {
        return this.c.getPlayer().getEventCount(eventId);
    }
    
    public int getEventCount(final String eventId, final int type) {
        return this.c.getPlayer().getEventCount(eventId, type);
    }
    
    public void setEventCount(final String eventId) {
        this.c.getPlayer().setEventCount(eventId);
    }
    
    public void setEventCount(final String eventId, final int type) {
        this.c.getPlayer().setEventCount(eventId, type);
    }
    
    public void setEventCount(final String eventId, final int type, final int count) {
        this.c.getPlayer().setEventCount(eventId, type, count);
    }
    
    public void resetEventCount(final String eventId) {
        this.c.getPlayer().resetEventCount(eventId);
    }
    
    public void resetEventCount(final String eventId, final int type) {
        this.c.getPlayer().resetEventCount(eventId, type);
    }
    
    public void setPartyEventCount(final String eventId) {
        this.setPartyEventCount(eventId, 0);
    }
    
    public void setPartyEventCount(final String eventId, final int type) {
        this.setPartyEventCount(eventId, type, 1);
    }
    
    public void setPartyEventCount(final String eventId, final int type, final int count) {
        if (this.getPlayer().getParty() == null || this.getPlayer().getParty().getMembers().size() == 1) {
            this.c.getPlayer().setEventCount(eventId, type, count);
            return;
        }
        final int checkMap = this.getPlayer().getMapId();
        for (final MaplePartyCharacter partyPlayer : this.getPlayer().getParty().getMembers()) {
            final MapleCharacter chr = this.getPlayer().getMap().getCharacterById(partyPlayer.getId());
            if (chr != null && chr.getMapId() == checkMap) {
                chr.setEventCount(eventId, type, count);
            }
        }
    }
    
    public boolean checkPartyEventCount(final String eventId) {
        return this.checkPartyEventCount(eventId, 1);
    }
    
    public boolean checkPartyEventCount(final String eventId, final int checkcount) {
        final MapleParty party = this.c.getPlayer().getParty();
        if (party == null || party.getMembers().size() == 1) {
            final int count = this.getEventCount(eventId);
            return count >= 0 && count < checkcount;
        }
        int check = 0;
        final int partySize = party.getMembers().size();
        for (final MaplePartyCharacter partyPlayer : party.getMembers()) {
            final MapleCharacter chr = this.getPlayer().getMap().getCharacterById(partyPlayer.getId());
            if (chr != null) {
                final int count = chr.getEventCount(eventId);
                if (count < 0 || count >= checkcount) {
                    continue;
                }
                ++check;
            }
        }
        return partySize == check;
    }
    
    public int getmoneyb() {
        int moneyb = 0;
        try (final Connection con = DatabaseConnection.getConnection()) {
            final int cid = this.getPlayer().getAccountID();
            ResultSet rs;
            try (final PreparedStatement limitCheck = con.prepareStatement("SELECT * FROM accounts WHERE id=" + cid + "")) {
                rs = limitCheck.executeQuery();
                if (rs.next()) {
                    moneyb = rs.getInt("moneyb");
                }
            }
            rs.close();
        }
        catch (SQLException ex) {
            System.err.println("getmoneyb" + (Object)ex);
            FileoutputUtil.outputFileError("logs/数据库异常.txt", (Throwable)ex);
            ex.getStackTrace();
        }
        return moneyb;
    }
    
    public void setmoneyb(final int slot) {
        try (final Connection con = DatabaseConnection.getConnection()) {
            final int cid = this.getPlayer().getAccountID();
            try (final PreparedStatement ps = con.prepareStatement("UPDATE accounts SET moneyb =moneyb+ " + slot + " WHERE id = " + cid + "")) {
                ps.executeUpdate();
            }
        }
        catch (SQLException ex) {
            System.err.println("setmoneyb" + (Object)ex);
            FileoutputUtil.outputFileError("logs/数据库异常.txt", (Throwable)ex);
            ex.getStackTrace();
        }
    }
    
    public void openWeb(final String web) {
        this.c.getSession().write((Object)MaplePacketCreator.openWeb(web));
    }
    
    public final boolean checkNumSpace(final int type, final int space) {
        if (type <= 5 && type > 0) {
            return this.c.getPlayer().getInventory(MapleInventoryType.getByType((byte)type)).getNumFreeSlot() >= space;
        }
        for (int i = 1; i <= 5; ++i) {
            if (this.c.getPlayer().getInventory(MapleInventoryType.getByType((byte)i)).getNumFreeSlot() < space) {
                return false;
            }
        }
        return true;
    }
    
    public void refreshAllStats() {
        final Map<MapleStat, Integer> statup = new EnumMap<MapleStat, Integer>(MapleStat.class);
        this.c.getPlayer().getStat().recalcLocalStats();
        this.c.getSession().write((Object)MaplePacketCreator.updatePlayerStats(statup, this.c.getPlayer()));
    }
    
    public void refreshMaplePoints() {
        this.c.getPlayer().getStat().recalcLocalStats();
        this.c.sendPacket(MaplePacketCreator.showCharCash(this.c.getPlayer()));
    }
}
