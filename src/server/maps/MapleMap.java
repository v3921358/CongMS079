package server.maps;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import abc.离线人偶;
import client.MapleBuffStat;
import client.MapleCharacter;
import client.MapleClient;
import client.inventory.Equip;
import client.inventory.IItem;
import client.inventory.Item;
import client.inventory.MapleInventoryType;
import client.inventory.MaplePet;
import client.status.MonsterStatus;
import client.status.MonsterStatusEffect;
import constants.GameConstants;
import constants.MapConstants;
import database.DBConPool;
import database.DatabaseConnection;
import gui.CongMS;
import gui.活动野外通缉;
import gui.进阶BOSS.进阶BOSS线程;
import handling.channel.ChannelServer;
import handling.channel.handler.InventoryHandler;
import handling.world.MapleParty;
import handling.world.MaplePartyCharacter;
import handling.world.PartyOperation;
import handling.world.World.Broadcast;
import handling.world.World.Find;
import io.netty.channel.Channel;
import scripting.EventManager;
import server.MapleCarnivalFactory;
import server.MapleCarnivalFactory.MCSkill;
import server.MapleInventoryManipulator;
import server.MapleItemInformationProvider;
import server.MaplePortal;
import server.MapleSquad;
import server.MapleSquad.MapleSquadType;
import server.MapleStatEffect;
import server.Randomizer;
import server.ServerProperties;
import server.SpeedRunner;
import server.Timer.MapTimer;
import server.Timer.WorldTimer;
import server.custom.bossrank.BossRankManager;
import server.events.MapleEvent;
import server.life.MapleLifeFactory;
import server.life.MapleMonster;
import server.life.MapleMonsterInformationProvider;
import server.life.MapleNPC;
import server.life.MonsterDropEntry;
import server.life.MonsterGlobalDropEntry;
import server.life.SpawnPoint;
import server.life.SpawnPointAreaBoss;
import server.life.Spawns;
import server.maps.MapleNodes.MapleNodeInfo;
import server.maps.MapleNodes.MaplePlatform;
import server.maps.MapleNodes.MonsterPoint;
import tools.FilePrinter;
import tools.FileoutputUtil;
import tools.MaplePacketCreator;
import tools.MockIOSession;
import tools.Pair;
import tools.packet.MobPacket;
import tools.packet.PetPacket;

public final class MapleMap
{
    private final Map<MapleMapObjectType, LinkedHashMap<Integer, MapleMapObject>> mapObjects;
    private final Map<MapleMapObjectType, ReentrantReadWriteLock> mapObjectLocks;
    private final List<MapleCharacter> characters;
    private final ReentrantReadWriteLock charactersLock;
    private int runningOid;
    private final Lock runningOidLock;
    private final Map<String, Integer> environment;
    private final List<Spawns> monsterSpawn;
    private final AtomicInteger spawnedMonstersOnMap;
    private final Map<Integer, MaplePortal> portals;
    private final List<Integer> disconnectedClients;
    private static final Map<Integer, HashMap<String, Integer>> PointsGained;
    private final byte channel;
    private final int mapid;
    private final float monsterRate;
    private float recoveryRate;
    private MapleFootholdTree footholds;
    private MapleMapEffect mapEffect;
    private short decHP;
    private short createMobInterval;
    private int consumeItemCoolTime;
    private int protectItem;
    private int decHPInterval;
    private int returnMapId;
    private int timeLimit;
    private int fieldLimit;
    private int maxRegularSpawn;
    private int fixedMob;
    private int forcedReturnMap;
    private int lvForceMove;
    private int lvLimit;
    private int permanentWeather;
    private boolean town;
    private boolean personalShop;
    private boolean everlast;
    private boolean dropsDisabled;
    private boolean gDropsDisabled;
    private boolean soaring;
    private boolean squadTimer;
    private boolean isSpawns;
    private String mapName;
    private String streetName;
    private String onUserEnter;
    private String onFirstUserEnter;
    private String speedRunLeader;
    private ScheduledFuture<?> squadSchedule;
    private ScheduledFuture<?> MulungDojoLeaveTask;
    private long speedRunStart;
    private long lastSpawnTime;
    private long lastHurtTime;
    private MapleNodes nodes;
    private MapleSquadType squad;
    private boolean clock;
    private boolean boat;
    private boolean docked;
    private boolean PapfightStart;
    private static boolean 特殊宠物吸取开关;
    private static boolean 特殊宠物吸物开关;
    private static boolean 特殊宠物吸金开关;
    private static boolean 特殊宠物吸物无法使用地图开关;
    private static String[] 特殊宠物吸物无法使用地图;
    private static int 持有物道具;
    private short top;
    private short bottom;
    private short left;
    private short right;
    
    public MapleMap(final int mapid, final int channel, final int returnMapId, final float monsterRate) {
        this.characters = new LinkedList<MapleCharacter>();
        this.charactersLock = new ReentrantReadWriteLock();
        this.runningOid = 100000;
        this.runningOidLock = new ReentrantLock();
        this.environment = new LinkedHashMap<String, Integer>();
        this.monsterSpawn = new ArrayList<Spawns>();
        this.spawnedMonstersOnMap = new AtomicInteger(0);
        this.portals = new HashMap<Integer, MaplePortal>();
        this.disconnectedClients = new ArrayList<Integer>();
        this.footholds = null;
        this.decHP = 0;
        this.createMobInterval = 1000;
        this.consumeItemCoolTime = 0;
        this.protectItem = 0;
        this.decHPInterval = 10000;
        this.maxRegularSpawn = 0;
        this.forcedReturnMap = 999999999;
        this.lvForceMove = 0;
        this.lvLimit = 0;
        this.permanentWeather = 0;
        this.everlast = false;
        this.dropsDisabled = false;
        this.gDropsDisabled = false;
        this.soaring = false;
        this.squadTimer = false;
        this.isSpawns = true;
        this.speedRunLeader = "";
        this.squadSchedule = null;
        this.MulungDojoLeaveTask = null;
        this.speedRunStart = 0L;
        this.lastSpawnTime = 0L;
        this.lastHurtTime = 0L;
        this.PapfightStart = false;
        this.top = 0;
        this.bottom = 0;
        this.left = 0;
        this.right = 0;
        this.mapid = mapid;
        this.channel = (byte)channel;
        this.returnMapId = returnMapId;
        if (this.returnMapId == 999999999) {
            this.returnMapId = mapid;
        }
        if (GameConstants.isNotToMap(mapid)) {
            this.returnMapId = 211060000;
        }
        this.monsterRate = monsterRate;
        final EnumMap<MapleMapObjectType, LinkedHashMap<Integer, MapleMapObject>> objsMap = new EnumMap<MapleMapObjectType, LinkedHashMap<Integer, MapleMapObject>>(MapleMapObjectType.class);
        final EnumMap<MapleMapObjectType, ReentrantReadWriteLock> objlockmap = new EnumMap<MapleMapObjectType, ReentrantReadWriteLock>(MapleMapObjectType.class);
        for (final MapleMapObjectType type : MapleMapObjectType.values()) {
            objsMap.put(type, new LinkedHashMap<Integer, MapleMapObject>());
            objlockmap.put(type, new ReentrantReadWriteLock());
        }
        this.mapObjects = Collections.unmodifiableMap((Map<? extends MapleMapObjectType, ? extends LinkedHashMap<Integer, MapleMapObject>>)objsMap);
        this.mapObjectLocks = Collections.unmodifiableMap((Map<? extends MapleMapObjectType, ? extends ReentrantReadWriteLock>)objlockmap);
    }
    
    public void 地图回收() {
        WorldTimer.getInstance().register((Runnable)new Runnable() {
            @Override
            public void run() {
                if (MapleParty.神秘商人时间 == 1) {
                    if (MapleMap.this.getAllMonstersThreadsafe().size() > 0 && MapleMap.this.getCharactersSize() == 0) {
                        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                            System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : 系统正在回收地图 √ " + MapleMap.this.getId());
                            cserv.getMapFactory().destroyMap(MapleMap.this.getId(), true);
                            cserv.getMapFactory().HealMap(MapleMap.this.getId());
                        }
                    }
                    System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : 系统正在回收地图 √");
                }
            }
        }, 30000L);
    }
    
    public void 妖僧地图回收() {
        WorldTimer.getInstance().register((Runnable)new Runnable() {
            @Override
            public void run() {
                if (MapleMap.this.getId() == 702060000 && MapleMap.this.getAllMonstersThreadsafe().size() > 0 && MapleMap.this.getCharactersSize() == 0) {
                    MapleMap.this.清怪();
                    Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(0, ";妖僧副本已被重置。"));
                }
            }
        }, 60000L);
    }
    
    public void 定时召唤蜗牛王(final int time) {
        WorldTimer.getInstance().register((Runnable)new Runnable() {
            @Override
            public void run() {
                if (MapleMap.this.getMonsterById(2220000) == null) {
                    try {
                        final MapleMonster mob1 = MapleLifeFactory.getMonster(2220000);
                        MapleMap.this.spawnMonsterOnGroundBelow(mob1, new Point(439, 185));
                    }
                    catch (Exception ex) {}
                }
            }
        }, (long)(60000 * time));
    }
    
    public void 定时召唤普通扎昆(final int time) {
        WorldTimer.getInstance().register((Runnable)new Runnable() {
            @Override
            public void run() {
                if (MapleMap.this.getAllMonstersThreadsafe().isEmpty()) {
                    try {
                        if (MapleMap.this.getChannel() != 1) {
                            MapleMap.this.spawnZakum(-10, -215);
                            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(0, ";扎昆在祭台出现了。"));
                        }
                    }
                    catch (Exception ex) {}
                }
            }
        }, (long)(60000 * time));
    }
    
    public void 定时召唤暗黑龙王(final int time) {
        WorldTimer.getInstance().register((Runnable)new Runnable() {
            @Override
            public void run() {
                if (MapleMap.this.getAllMonstersThreadsafe().isEmpty()) {
                    try {
                        if (MapleMap.this.getChannel() != 1) {
                            final MapleMonster mob1 = MapleLifeFactory.getMonster(8810026);
                            MapleMap.this.spawnMonsterOnGroundBelow(mob1, new Point(71, 260));
                            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(0, ";暗黑龙王出现了。"));
                        }
                    }
                    catch (Exception ex) {}
                }
            }
        }, (long)(60000 * time));
    }
    
    public void 定时召唤时间宠儿(final int time) {
        WorldTimer.getInstance().register((Runnable)new Runnable() {
            @Override
            public void run() {
                if (MapleMap.this.getId() == 270050100 && MapleMap.this.getAllMonstersThreadsafe().isEmpty() && MapleMap.this.getMonsterById(8820008) == null) {
                    try {
                        if (MapleMap.this.getChannel() != 1) {
                            final MapleMonster mob1 = MapleLifeFactory.getMonster(8820008);
                            MapleMap.this.spawnMonsterOnGroundBelow(mob1, new Point(2, -42));
                        }
                    }
                    catch (Exception ex) {}
                }
            }
        }, (long)(60000 * time));
    }
    
    public void 定时召唤蝙蝠怪(final int time) {
        WorldTimer.getInstance().register((Runnable)new Runnable() {
            @Override
            public void run() {
                if (MapleMap.this.getId() == 105100300 && MapleMap.this.getMonsterById(8830000) == null && MapleMap.this.getMonsterById(8830001) == null && MapleMap.this.getMonsterById(8830002) == null) {
                    try {
                        if (MapleMap.this.getChannel() != 1) {
                            final MapleMonster mob1 = MapleLifeFactory.getMonster(8830000);
                            final MapleMonster mob2 = MapleLifeFactory.getMonster(8830001);
                            final MapleMonster mob3 = MapleLifeFactory.getMonster(8830002);
                            MapleMap.this.spawnMonsterOnGroundBelow(mob1, new Point(483, 258));
                            MapleMap.this.spawnMonsterOnGroundBelow(mob2, new Point(483, 258));
                            MapleMap.this.spawnMonsterOnGroundBelow(mob3, new Point(483, 258));
                        }
                    }
                    catch (Exception ex) {}
                }
            }
        }, (long)(60000 * time));
    }
    
    public void 定时召唤混沌女王(final int time) {
        WorldTimer.getInstance().register((Runnable)new Runnable() {
            @Override
            public void run() {
                if (MapleMap.this.getMonsterById(8920102) == null) {
                    try {
                        if (MapleMap.this.getChannel() != 1) {
                            final MapleMonster mob1 = MapleLifeFactory.getMonster(8920000);
                            MapleMap.this.spawnMonsterOnGroundBelow(mob1, new Point(-2118, 86));
                        }
                    }
                    catch (Exception ex) {}
                }
            }
        }, (long)(60000 * time));
    }
    
    public void 定时召唤月妙巨兔(final int time) {
        WorldTimer.getInstance().register((Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    if (MapleMap.this.getChannel() != 1) {
                        final MapleMonster mob1 = MapleLifeFactory.getMonster(9500006);
                        MapleMap.this.spawnMonsterOnGroundBelow(mob1, new Point(475, 35));
                    }
                }
                catch (Exception ex) {}
            }
        }, (long)(60000 * time));
    }
    
    public void 定时召唤雷昂(final int time) {
        WorldTimer.getInstance().register((Runnable)new Runnable() {
            @Override
            public void run() {
                if (MapleMap.this.getAllMonstersThreadsafe().size() == 0) {
                    try {
                        if (MapleMap.this.getChannel() != 1) {
                            final MapleMonster mob1 = MapleLifeFactory.getMonster(8840000);
                            MapleMap.this.spawnMonsterOnGroundBelow(mob1, new Point(-570, 102));
                        }
                    }
                    catch (Exception ex) {}
                }
            }
        }, (long)(60000 * time));
    }
    
    public void 定时召唤石像塔(final int time) {
        WorldTimer.getInstance().register((Runnable)new Runnable() {
            @Override
            public void run() {
                if (MapleMap.this.getMonsterById(2500360) == null) {
                    try {
                        if (MapleMap.this.getChannel() != 1) {
                            final MapleMonster mob1 = MapleLifeFactory.getMonster(2500360);
                            MapleMap.this.spawnMonsterOnGroundBelow(mob1, new Point(1927, 2205));
                        }
                    }
                    catch (Exception ex) {}
                }
            }
        }, (long)(60000 * time));
    }
    
    public void 定时召唤守护塔(final int time) {
        WorldTimer.getInstance().register((Runnable)new Runnable() {
            @Override
            public void run() {
                if (MapleMap.this.getMonsterById(2500402) == null) {
                    try {
                        if (MapleMap.this.getChannel() != 1) {
                            final MapleMonster mob1 = MapleLifeFactory.getMonster(2500400);
                            MapleMap.this.spawnMonsterOnGroundBelow(mob1, new Point(821, 195));
                        }
                    }
                    catch (Exception ex) {}
                }
            }
        }, (long)(60000 * time));
    }
    
    public final void setSpawns(final boolean fm) {
        this.isSpawns = fm;
    }
    
    public final boolean getSpawns() {
        return this.isSpawns;
    }
    
    public final void setFixedMob(final int fm) {
        this.fixedMob = fm;
    }
    
    public final void setForceMove(final int fm) {
        this.lvForceMove = fm;
    }
    
    public final int getForceMove() {
        return this.lvForceMove;
    }
    
    public final void setLevelLimit(final int fm) {
        this.lvLimit = fm;
    }
    
    public final int getLevelLimit() {
        return this.lvLimit;
    }
    
    public final void setReturnMapId(final int rmi) {
        this.returnMapId = rmi;
    }
    
    public final void setSoaring(final boolean b) {
        this.soaring = b;
    }
    
    public final boolean canSoar() {
        return this.soaring;
    }
    
    public final void toggleDrops() {
        this.dropsDisabled = !this.dropsDisabled;
    }
    
    public final void setDrops(final boolean b) {
        this.dropsDisabled = b;
    }
    
    public final void toggleGDrops() {
        this.gDropsDisabled = !this.gDropsDisabled;
    }
    
    public final int getId() {
        return this.mapid;
    }
    
    public final MapleMap getReturnMap() {
        return ChannelServer.getInstance((int)this.channel).getMapFactory().getMap(this.returnMapId);
    }
    
    public final int getReturnMapId() {
        return this.returnMapId;
    }
    
    public final int getForcedReturnId() {
        return this.forcedReturnMap;
    }
    
    public final MapleMap getForcedReturnMap() {
        return ChannelServer.getInstance((int)this.channel).getMapFactory().getMap(this.forcedReturnMap);
    }
    
    public final void setForcedReturnMap(final int map) {
        this.forcedReturnMap = map;
    }
    
    public final float getRecoveryRate() {
        return this.recoveryRate;
    }
    
    public final void setRecoveryRate(final float recoveryRate) {
        this.recoveryRate = recoveryRate;
    }
    
    public final int getFieldLimit() {
        return this.fieldLimit;
    }
    
    public final void setFieldLimit(final int fieldLimit) {
        this.fieldLimit = fieldLimit;
    }
    
    public final void setCreateMobInterval(final short createMobInterval) {
        this.createMobInterval = createMobInterval;
    }
    
    public final void setTimeLimit(final int timeLimit) {
        this.timeLimit = timeLimit;
    }
    
    public final void setMapName(final String mapName) {
        this.mapName = mapName;
    }
    
    public final String getMapName() {
        return this.mapName;
    }
    
    public final String getStreetName() {
        return this.streetName;
    }
    
    public final void setFirstUserEnter(final String onFirstUserEnter) {
        this.onFirstUserEnter = onFirstUserEnter;
    }
    
    public final void setUserEnter(final String onUserEnter) {
        this.onUserEnter = onUserEnter;
    }
    
    public final boolean hasClock() {
        return this.clock;
    }
    
    public final void setClock(final boolean hasClock) {
        this.clock = hasClock;
    }
    
    private int hasBoat() {
        return this.docked ? 2 : (this.boat ? 1 : 0);
    }
    
    public void setBoat(final boolean hasBoat) {
        this.boat = hasBoat;
    }
    
    public void setDocked(final boolean isDocked) {
        this.docked = isDocked;
    }
    
    public final boolean isTown() {
        return this.town;
    }
    
    public final void setTown(final boolean town) {
        this.town = town;
    }
    
    public final boolean allowPersonalShop() {
        return this.personalShop;
    }
    
    public final void setPersonalShop(final boolean personalShop) {
        this.personalShop = personalShop;
    }
    
    public final void setStreetName(final String streetName) {
        this.streetName = streetName;
    }
    
    public final void setEverlast(final boolean everlast) {
        this.everlast = everlast;
    }
    
    public final boolean getEverlast() {
        return this.everlast;
    }
    
    public final int getHPDec() {
        return this.decHP;
    }
    
    public final void setHPDec(final int delta) {
        if (delta > 0 || this.mapid == 749040100) {
            this.lastHurtTime = System.currentTimeMillis();
        }
        this.decHP = (short)delta;
    }
    
    public final int getHPDecInterval() {
        return this.decHPInterval;
    }
    
    public final void setHPDecInterval(final int delta) {
        this.decHPInterval = delta;
    }
    
    public final int getHPDecProtect() {
        return this.protectItem;
    }
    
    public final void setHPDecProtect(final int delta) {
        this.protectItem = delta;
    }
    
    public final int getCurrentPartyId() {
        this.charactersLock.readLock().lock();
        try {
            for (final MapleCharacter chr : this.characters) {
                if (chr.getPartyId() != -1) {
                    return chr.getPartyId();
                }
            }
        }
        finally {
            this.charactersLock.readLock().unlock();
        }
        return -1;
    }
    
    public final void addMapObject(final MapleMapObject mapobject) {
        this.runningOidLock.lock();
        int newOid;
        try {
            newOid = ++this.runningOid;
        }
        finally {
            this.runningOidLock.unlock();
        }
        mapobject.setObjectId(newOid);
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)mapobject.getType())).writeLock().lock();
        try {
            ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)mapobject.getType())).put(Integer.valueOf(newOid), mapobject);
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)mapobject.getType())).writeLock().unlock();
        }
    }
    
    private void spawnAndAddRangedMapObject(final MapleMapObject mapobject, final DelayedPacketCreation packetbakery, final SpawnCondition condition) {
        this.addMapObject(mapobject);
        this.charactersLock.readLock().lock();
        try {
            for (final MapleCharacter chr : this.characters) {
                if ((condition == null || condition.canSpawn(chr)) && !chr.isClone() && chr.getPosition().distanceSq((Point2D)mapobject.getPosition()) <= (double)GameConstants.maxViewRangeSq()) {
                    packetbakery.sendPackets(chr.getClient());
                    chr.addVisibleMapObject(mapobject);
                }
            }
        }
        finally {
            this.charactersLock.readLock().unlock();
        }
    }
    
    public final void removeMapObject(final MapleMapObject obj) {
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)obj.getType())).writeLock().lock();
        try {
            ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)obj.getType())).remove((Object)Integer.valueOf(obj.getObjectId()));
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)obj.getType())).writeLock().unlock();
        }
    }
    
    public final Point calcPointBelow(final Point initial) {
        final MapleFoothold fh = this.footholds.findBelow(initial);
        if (fh == null) {
            return null;
        }
        int dropY = fh.getY1();
        final int dropX = (initial.x < this.left + 30) ? (this.left + 30) : ((initial.x > this.right - 30) ? (this.right - 30) : initial.x);
        if (!fh.isWall() && fh.getY1() != fh.getY2()) {
            final double s1 = (double)Math.abs(fh.getY2() - fh.getY1());
            final double s2 = (double)Math.abs(fh.getX2() - fh.getX1());
            if (fh.getY2() < fh.getY1()) {
                dropY = fh.getY1() - (int)(Math.cos(Math.atan(s2 / s1)) * ((double)Math.abs(initial.x - fh.getX1()) / Math.cos(Math.atan(s1 / s2))));
            }
            else {
                dropY = fh.getY1() + (int)(Math.cos(Math.atan(s2 / s1)) * ((double)Math.abs(initial.x - fh.getX1()) / Math.cos(Math.atan(s1 / s2))));
            }
        }
        return new Point(dropX, dropY);
    }
    
    public final Point calcDropPos(final Point initial, final Point fallback) {
        final Point ret = this.calcPointBelow(new Point(initial.x, initial.y - 50));
        if (ret == null) {
            return fallback;
        }
        return ret;
    }
    
    private void dropFromMonster(final MapleCharacter chr, final MapleMonster mob) {
        if (mob == null || chr == null || ChannelServer.getInstance((int)this.channel) == null || this.dropsDisabled || mob.dropsDisabled() || chr.getPyramidSubway() != null) {
            return;
        }
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        final byte droptype = (byte)(mob.getStats().isExplosiveReward() ? 3 : (mob.getStats().isFfaLoot() ? 2 : ((chr.getParty() != null) ? 1 : 0)));
        final int mobpos = mob.getPosition().x;
        final int cmServerrate = ChannelServer.getInstance((int)this.channel).getMesoRate();
        final int chServerrate = ChannelServer.getInstance((int)this.channel).getDropRate();
        byte d = 1;
        final Point pos = new Point(0, mob.getPosition().y);
        double showdown = 100.0;
        final MonsterStatusEffect mse = mob.getBuff(MonsterStatus.SHOWDOWN);
        if (mse != null) {
            showdown += (double)(int)mse.getX();
        }
        final MapleMonsterInformationProvider mi = MapleMonsterInformationProvider.getInstance();
        final List<MonsterDropEntry> drops = mi.retrieveDrop(mob.getId());
        if (drops == null) {
            return;
        }
        final List<MonsterDropEntry> dropEntry = mi.retrieveDrop(mob.getId());
        if (chr.getDebugMessage()) {
            chr.dropMessage("怪物: " + mob.getId());
            chr.dropMessage("掉寶如下: ");
            for (final MonsterDropEntry de : dropEntry) {
                chr.dropMessage(" 道具: " + de.itemId + " 機率: " + (double)(de.chance * chServerrate * chr.getDropMod()) * chr.getDropm() * ((double)chr.getVipExpRate() / 100.0 + 1.0) * ((chr.getStat().realDropBuff - 100.0 <= 0.0) ? 100.0 : (chr.getStat().realDropBuff - 100.0)) / 100.0 * (showdown / 100.0) + " 最大/小掉落量: " + de.Maximum + "/" + de.Minimum);
            }
        }
        Collections.shuffle(dropEntry);
        boolean mesoDropped = false;
        for (final MonsterDropEntry de2 : dropEntry) {
            if (de2.itemId == mob.getStolen()) {
                continue;
            }
            final double lastDrop = (chr.getStat().realDropBuff - 100.0 <= 0.0) ? 100.0 : (chr.getStat().realDropBuff - 100.0);
            if (Randomizer.nextInt(999999) >= ((de2.itemId == 1012168 || de2.itemId == 1012169 || de2.itemId == 1012170 || de2.itemId == 1012171) ? de2.chance : ((int)((double)(de2.chance * chServerrate * chr.getDropMod()) * chr.getDropm() * ((double)(chr.getVipExpRate() / 100) + 1.0) * lastDrop / 100.0 * (showdown / 100.0))))) {
                continue;
            }
            if (mesoDropped && droptype != 3 && de2.itemId == 0) {
                continue;
            }
            if (de2.questid > 0 && chr.getQuestStatus((int)de2.questid) != 1) {
                continue;
            }
            if (de2.itemId / 10000 == 238 && !mob.getStats().isBoss() && chr.getMonsterBook().getLevelByCard(ii.getCardMobId(de2.itemId)) >= 2) {
                continue;
            }
            if (droptype == 3) {
                pos.x = mobpos + ((d % 2 == 0) ? (40 * (d + 1) / 2) : (-(40 * (d / 2))));
            }
            else {
                pos.x = mobpos + ((d % 2 == 0) ? (25 * (d + 1) / 2) : (-(25 * (d / 2))));
            }
            if (de2.itemId == 0) {
                final int mesos = Randomizer.nextInt(1 + Math.abs(de2.Maximum - de2.Minimum)) + de2.Minimum;
                if (mesos > 0) {
                    this.spawnMobMesoDrop((int)((double)mesos * (chr.getStat().mesoBuff / 100.0) * (double)chr.getDropMod() * (double)cmServerrate), this.calcDropPos(pos, mob.getTruePosition()), (MapleMapObject)mob, chr, false, droptype);
                    mesoDropped = true;
                }
            }
            else {
                IItem idrop;
                if (GameConstants.getInventoryType(de2.itemId) == MapleInventoryType.EQUIP) {
                    idrop = ii.randomizeStats((Equip)ii.getEquipById(de2.itemId));
                }
                else {
                    final int range = Math.abs(de2.Maximum - de2.Minimum);
                    idrop = new Item(de2.itemId, (short)0, (short)((de2.Maximum != 1) ? (Randomizer.nextInt((range <= 0) ? 1 : range) + de2.Minimum) : 1), (byte)0);
                }
                this.spawnMobDrop(idrop, this.calcDropPos(pos, mob.getPosition()), mob, chr, droptype, de2.questid);
            }
            ++d;
        }
        final List<MonsterGlobalDropEntry> globalEntry = new ArrayList<MonsterGlobalDropEntry>((Collection<? extends MonsterGlobalDropEntry>)mi.getGlobalDrop());
        Collections.shuffle(globalEntry);
        final int cashz = (mob.getStats().isBoss() && mob.getStats().getHPDisplayType() == 0) ? 20 : 1;
        final int cashModifier = (int)(mob.getStats().isBoss() ? 0L : ((long)(mob.getMobExp() / 1000) + mob.getMobMaxHp() / 10000L));
        for (final MonsterGlobalDropEntry de3 : globalEntry) {
            if (Randomizer.nextInt(999999) < de3.chance && (de3.continent < 0 || (de3.continent < 10 && this.mapid / 100000000 == de3.continent) || (de3.continent < 100 && this.mapid / 10000000 == de3.continent) || (de3.continent < 1000 && this.mapid / 1000000 == de3.continent))) {
                if (droptype == 3) {
                    pos.x = mobpos + ((d % 2 == 0) ? (40 * (d + 1) / 2) : (-(40 * (d / 2))));
                }
                else {
                    pos.x = mobpos + ((d % 2 == 0) ? (25 * (d + 1) / 2) : (-(25 * (d / 2))));
                }
                if (de3.itemId == 0) {
                    continue;
                }
                if (this.gDropsDisabled) {
                    continue;
                }
                IItem idrop;
                if (GameConstants.getInventoryType(de3.itemId) == MapleInventoryType.EQUIP) {
                    idrop = ii.randomizeStats((Equip)ii.getEquipById(de3.itemId));
                }
                else {
                    idrop = new Item(de3.itemId, (short)0, (short)((de3.Maximum != 1) ? (Randomizer.nextInt(de3.Maximum - de3.Minimum) + de3.Minimum) : 1), (byte)0);
                }
                this.spawnMobDrop(idrop, this.calcDropPos(pos, mob.getPosition()), mob, chr, (byte)(de3.onlySelf ? 0 : droptype), de3.questid);
                ++d;
            }
        }
    }
    
    public void removeMonster(final MapleMonster monster) {
        if (monster == null) {
            return;
        }
        this.spawnedMonstersOnMap.decrementAndGet();
        this.broadcastMessage(MobPacket.killMonster(monster.getObjectId(), 0));
        this.removeMapObject((MapleMapObject)monster);
        monster.killed();
    }
    
    private void killMonster(final MapleMonster monster) {
        this.spawnedMonstersOnMap.decrementAndGet();
        monster.setHp(0L);
        monster.spawnRevives(this);
        this.broadcastMessage(MobPacket.killMonster(monster.getObjectId(), 1));
        this.removeMapObject((MapleMapObject)monster);
    }
    
    public final void killMonster(final MapleMonster monster, final MapleCharacter chr, final boolean withDrops, final boolean second, final byte animation) {
        this.killMonster(monster, chr, withDrops, second, animation, 0);
    }
    
    public final void 地图杀怪(final MapleMonster monster, final MapleCharacter chr) {
        final int mobid = monster.getId();
        if (mobid == MapleParty.通缉BOSS && this.mapid == MapleParty.通缉地图) {
            MapleParty.通缉BOSS = 0;
            MapleParty.通缉地图 = 0;
            final String 信息 = "[野外通缉] : " + chr.getName() + " 完成了此次通缉令，下一次通缉令将在 1 小时后发布。";
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, 信息));
            System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : " + 信息);
            chr.击杀野外BOSS特效2();
            chr.打开奖励();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3600000L);
                        活动野外通缉.随机通缉();
                    }
                    catch (InterruptedException ex) {}
                }
            }.start();
        }
        else if (mobid == 9500337 && this.mapid == 104000400) {
            进阶BOSS线程.关闭进阶BOSS线程();
        }
        else if (mobid == 2220000 && this.mapid == 104000400) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[红蜗牛王屠杀令]: " + chr.getName() + " 在海岸草丛III击杀了红蜗牛王"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("每日击杀红蜗牛王");
            chr.setBossLog("击杀高级怪物");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀红蜗牛王", (byte)2, 1);
        }
        else if (mobid == 3220000 && this.mapid == 101030404) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[树妖王屠杀令]: " + chr.getName() + " 在东部岩山Ⅴ击杀了树妖王"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀树妖王");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀树妖王", (byte)2, 1);
        }
        else if (mobid == 5220001 && this.mapid == 110040000) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[巨居蟹屠杀令]: " + chr.getName() + " 在阳光沙滩击杀了巨居蟹"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀巨居蟹");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀巨居蟹", (byte)2, 1);
        }
        else if (mobid == 7220000 && this.mapid == 250010304) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[肯德熊屠杀令]: " + chr.getName() + " 在流浪熊的地盘击杀了肯德熊"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀肯德熊");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀肯德熊", (byte)2, 1);
        }
        else if (mobid == 8220000 && this.mapid == 200010300) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[艾利杰屠杀令]: " + chr.getName() + " 在天空楼梯Ⅱ击杀了艾利杰"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀艾利杰");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀艾利杰", (byte)2, 1);
        }
        else if (mobid == 7220002 && this.mapid == 250010503) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[妖怪禅师屠杀令]: " + chr.getName() + " 在妖怪森林击杀了妖怪禅师"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀妖怪禅师");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀妖怪禅师", (byte)2, 1);
        }
        else if (mobid == 7220001 && this.mapid == 222010310) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[九尾狐屠杀令]: " + chr.getName() + " 在月岭击杀了九尾狐"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀九尾狐");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀九尾狐", (byte)2, 1);
        }
        else if (mobid == 6220000 && this.mapid == 107000300) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[多尔屠杀令]: " + chr.getName() + " 在鳄鱼潭Ⅰ击杀了多尔"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀多尔");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀多尔", (byte)2, 1);
        }
        else if (mobid == 5220002 && this.mapid == 100040105) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[浮士德屠杀令]: " + chr.getName() + " 在巫婆森林Ⅰ击杀了浮士德"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀浮士德");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀浮士德", (byte)2, 1);
        }
        else if (mobid == 5220003 && this.mapid == 220050100) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[提莫屠杀令]: " + chr.getName() + " 在时间漩涡击杀了提莫"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀提莫");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀提莫", (byte)2, 1);
        }
        else if (mobid == 6220001 && this.mapid == 221040301) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[朱诺屠杀令]: " + chr.getName() + " 在哥雷草原击杀了朱诺"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀朱诺");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀朱诺", (byte)2, 1);
        }
        else if (mobid == 8220003 && this.mapid == 240040401) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[大海兽屠杀令]: " + chr.getName() + " 在大海兽 峡谷击杀了大海兽"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀大海兽");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀大海兽", (byte)2, 1);
        }
        else if (mobid == 3220001 && this.mapid == 260010201) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[大宇屠杀令]: " + chr.getName() + " 在仙人掌爸爸沙漠击杀了大宇"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀大宇");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀大宇", (byte)2, 1);
        }
        else if (mobid == 8220002 && this.mapid == 261030000) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[吉米拉屠杀令]: " + chr.getName() + " 在研究所地下秘密通道击杀了吉米拉"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀吉米拉");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀吉米拉", (byte)2, 1);
        }
        else if (mobid == 4220000 && this.mapid == 230020100) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[歇尔夫屠杀令]: " + chr.getName() + " 在海草之塔击杀了歇尔夫"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀歇尔夫");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀歇尔夫", (byte)2, 1);
        }
        else if (mobid == 6130101 && this.mapid == 100000005) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[蘑菇王屠杀令]: " + chr.getName() + " 在铁甲猪公园3击杀了蘑菇王"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀蘑菇王");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀蘑菇王", (byte)2, 1);
        }
        else if (mobid == 6300005 && this.mapid == 105070002) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[僵尸蘑菇王屠杀令]: " + chr.getName() + " 在蘑菇王之墓击杀了僵尸蘑菇王"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀僵尸蘑菇王");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀僵尸蘑菇王", (byte)2, 1);
        }
        else if (mobid == 8130100 && this.mapid == 105090900) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[蝙蝠怪屠杀令]: " + chr.getName() + " 在被诅咒的寺院击杀了蝙蝠怪"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀蝙蝠怪");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀蝙蝠怪", (byte)2, 1);
        }
        else if (mobid == 9400205 && this.mapid == 800010100) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[蓝蘑菇王屠杀令]: " + chr.getName() + " 在天皇殿堂击杀了蓝蘑菇王"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀蓝蘑菇王");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀蓝蘑菇王", (byte)2, 1);
        }
        else if (mobid == 9400120 && this.mapid == 801030000) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[老板屠杀令]: " + chr.getName() + " 在昭和内部街道3击杀了老板"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀老板");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀老板", (byte)2, 1);
        }
        else if (mobid == 8220001 && this.mapid == 211040101) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[驮狼雪人屠杀令]: " + chr.getName() + " 在雪人谷击杀了驮狼雪人"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀驮狼雪人");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀驮狼雪人", (byte)2, 1);
        }
        else if (mobid == 8180000 && this.mapid == 240020401) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[火焰龙屠杀令]: " + chr.getName() + " 在喷火龙栖息地击杀了火焰龙"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀火焰龙");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀火焰龙", (byte)2, 1);
        }
        else if (mobid == 8180001 && this.mapid == 240020101) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[天鹰屠杀令]: " + chr.getName() + " 在格瑞芬多森林击杀了天鹰"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀天鹰");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀天鹰", (byte)2, 1);
        }
        else if (mobid == 8220006 && this.mapid == 270030500) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[雷卡屠杀令]: " + chr.getName() + " 在忘却之路5击杀了雷卡"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀雷卡");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀雷卡", (byte)2, 1);
        }
        else if (mobid == 8220005 && this.mapid == 270020500) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[玄冰独角兽屠杀令]: " + chr.getName() + " 在后悔之路5击杀了玄冰独角兽"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀玄冰独角兽");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀玄冰独角兽", (byte)2, 1);
        }
        else if (mobid == 8220004 && this.mapid == 270010500) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[多多屠杀令]: " + chr.getName() + " 在追忆之路5击杀了多多"));
            }
            chr.击杀野外BOSS特效();
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀多多");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀多多", (byte)2, 1);
        }
        else if (mobid == 8220004) {
            chr.setBossLog("蜈蚣");
            chr.击杀野外BOSS特效();
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "蜈蚣", (byte)2, 1);
        }
        else if (mobid == 8500002 && this.mapid == 220080001) {
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"屠令广播开关")) <= 0) {
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(4, "[帕普拉图斯屠杀令]: " + chr.getName() + " 在时间塔的本源击杀了帕普拉图斯"));
            }
            chr.setBossLog("击杀高级怪物");
            chr.setBossLog("每日击杀帕普拉图斯");
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "每日挑战帕普拉图斯", (byte)2, 1);
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "个人击杀帕普拉图斯", (byte)2, 1);
        }
        else if (mobid == 9300003 && this.mapid == 103000804) {
            BossRankManager.getInstance().setLog(chr.getId(), chr.getName(), "废弃副本BOOS击杀次数", (byte)2, 1);
        }
        else if (mobid == 8830000 && this.mapid == 105100300) {
            final MapleMap map = chr.getMap();
            final boolean drop = false;
            final double range = Double.POSITIVE_INFINITY;
            final List<MapleMapObject> monsters = map.getMapObjectsInRange(chr.getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER));
            for (final MapleMapObject monstermo : map.getMapObjectsInRange(chr.getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER))) {
                final MapleMonster mob = (MapleMonster)monstermo;
                map.killMonster(mob, chr, drop, false, (byte)1);
            }
            for (final MapleMapObject monstermo : map.getMapObjectsInRange(chr.getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER))) {
                final MapleMonster mob = (MapleMonster)monstermo;
                map.killMonster(mob, chr, drop, false, (byte)1);
            }
        }
        else if (mobid == 8800002 && this.mapid == 280030000) {
            final MapleMap map = chr.getMap();
            final boolean drop = false;
            final double range = Double.POSITIVE_INFINITY;
            final List<MapleMapObject> monsters = map.getMapObjectsInRange(chr.getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER));
            for (final MapleMapObject monstermo : map.getMapObjectsInRange(chr.getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER))) {
                final MapleMonster mob = (MapleMonster)monstermo;
                map.killMonster(mob, chr, drop, false, (byte)1);
            }
            for (final MapleMapObject monstermo : map.getMapObjectsInRange(chr.getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER))) {
                final MapleMonster mob = (MapleMonster)monstermo;
                map.killMonster(mob, chr, drop, false, (byte)1);
            }
        }
    }
    
    public final void killMonster(final MapleMonster monster, final MapleCharacter chr, final boolean withDrops, final boolean second, byte animation, final int lastSkill) {
        if ((monster.getId() == 8810122 || monster.getId() == 8810018) && !second) {
            MapTimer.getInstance().schedule((Runnable)new Runnable() {
                @Override
                public void run() {
                    MapleMap.this.killMonster(monster, chr, true, true, (byte)1);
                    MapleMap.this.killAllMonsters(true);
                }
            }, 3000L);
            return;
        }
        if (monster.getId() == 8150000) {
            if (MapleParty.蝙蝠魔A部队 > 0) {
                if (this.mapid == 106010100 || this.mapid == 106010000 || this.mapid == 100000000) {
                    --MapleParty.蝙蝠魔A部队;
                }
            }
            else if (MapleParty.蝙蝠魔B部队 > 0) {
                if (this.mapid == 107000400 || this.mapid == 107000300 || this.mapid == 107000200 || this.mapid == 107000100 || this.mapid == 107000000 || this.mapid == 103000000) {
                    --MapleParty.蝙蝠魔B部队;
                }
            }
            else if (MapleParty.蝙蝠魔C部队 > 0) {
                if (this.mapid == 101010103 || this.mapid == 101010102 || this.mapid == 101010101 || this.mapid == 101010100 || this.mapid == 101010000 || this.mapid == 101000000) {
                    --MapleParty.蝙蝠魔C部队;
                }
            }
            else if (MapleParty.蝙蝠魔D部队 > 0 && (this.mapid == 106000300 || this.mapid == 106000200 || this.mapid == 106000100 || this.mapid == 106000000 || this.mapid == 102000000)) {
                --MapleParty.蝙蝠魔D部队;
            }
        }
        if (monster.getId() == 8820014) {
            this.killMonster(8820000);
        }
        else if (monster.getId() == 9300166) {
            animation = 2;
        }
        else if (this.getId() == 910320100) {}
        this.spawnedMonstersOnMap.decrementAndGet();
        this.removeMapObject((MapleMapObject)monster);
        final int dropOwner = monster.killBy(chr, lastSkill);
        this.broadcastMessage(MobPacket.killMonster(monster.getObjectId(), (int)animation));
        if (monster.getBuffToGive() > -1) {
            final int buffid = monster.getBuffToGive();
            final MapleStatEffect buff = MapleItemInformationProvider.getInstance().getItemEffect(buffid);
            this.charactersLock.readLock().lock();
            try {
                for (final MapleCharacter mc : this.characters) {
                    if (mc.isAlive()) {
                        buff.applyTo(mc);
                        switch (monster.getId()) {
                            case 8810018:
                            case 8810122:
                            case 8820001: {
                                mc.getClient().sendPacket(MaplePacketCreator.showOwnBuffEffect(buffid, 11));
                                this.broadcastMessage(mc, MaplePacketCreator.showBuffeffect(mc.getId(), buffid, 11), false);
                                continue;
                            }
                        }
                    }
                }
            }
            finally {
                this.charactersLock.readLock().unlock();
            }
        }
        final int mobid = monster.getId();
        SpeedRunType type = SpeedRunType.NULL;
        final MapleSquad sqd = this.getSquadByMap();
        this.地图杀怪(monster, chr);
        if (this.mapid == 702060000 && monster.getId() == 9600025) {
            String 挑战者 = "";
            for (final MapleCharacter player : this.getCharacters()) {
                挑战者 = 挑战者 + player.getName() + " ";
                player.setBossLog("妖僧经验限制");
            }
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[挑战]:少林妖僧被 " + 挑战者 + "击败了。"));
        }
        else if (this.mapid == 541020800 && monster.getId() == 9420521) {
            String 挑战者 = "";
            for (final MapleCharacter player : this.getCharacters()) {
                挑战者 = 挑战者 + player.getName() + " ";
                player.setBossLog("树精经验限制");
            }
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[挑战]:克雷塞尔被 " + 挑战者 + "击败了。"));
        }
        else if (mobid == 8810018 && this.mapid == 240060200) {
            String 挑战者 = "";
            for (final MapleCharacter player : this.getCharacters()) {
                挑战者 = 挑战者 + player.getName() + " ";
                player.setBossLog("黑龙经验限制");
            }
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[挑战]:黑暗龙王斯被 " + 挑战者 + "击败了。"));
            if (this.speedRunStart > 0L) {
                type = SpeedRunType.Horntail;
            }
            if (sqd != null) {
                this.doShrine(true);
            }
            try (final PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characterz SET Point = 0 WHERE channel = 501")) {
                ps.executeUpdate();
            }
            catch (SQLException ex) {}
        }
        else if (mobid == 8500002 && this.mapid == 220080001) {
            String 挑战者 = "";
            for (final MapleCharacter player : this.getCharacters()) {
                挑战者 = 挑战者 + player.getName() + " ";
                player.setBossLog("闹钟经验限制");
            }
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[挑战]:帕普拉图斯被 " + 挑战者 + "击败了。"));
            if (this.speedRunStart > 0L) {
                type = SpeedRunType.Papulatus;
            }
        }
        else if ((mobid == 9420549 || mobid == 9420544) && this.mapid == 551030200) {
            String 挑战者 = "";
            for (final MapleCharacter player : this.getCharacters()) {
                挑战者 = 挑战者 + player.getName() + " ";
            }
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[挑战]:心疤狮王被 " + 挑战者 + "击败了。"));
            if (this.speedRunStart > 0L) {
                if (mobid == 9420549) {
                    type = SpeedRunType.Scarlion;
                }
                else {
                    type = SpeedRunType.Targa;
                }
            }
        }
        else if (mobid == 8820001 && this.mapid == 270050100) {
            String 挑战者 = "";
            for (final MapleCharacter player : this.getCharacters()) {
                挑战者 += player.getName();
            }
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[挑战]:品克缤被" + 挑战者 + "击败了。"));
            if (this.speedRunStart > 0L) {
                type = SpeedRunType.Pink_Bean;
            }
            if (sqd != null) {
                this.doShrine(true);
            }
        }
        else if (mobid >= 8800003 && mobid <= 8800010) {
            boolean makeZakReal = true;
            final Collection<MapleMonster> monsters = this.getAllMonstersThreadsafe();
            for (final MapleMonster mons : monsters) {
                if (mons.getId() >= 8800003 && mons.getId() <= 8800010) {
                    makeZakReal = false;
                    break;
                }
            }
            if (makeZakReal) {
                for (final MapleMapObject object : monsters) {
                    final MapleMonster mons2 = (MapleMonster)object;
                    if (mons2.getId() == 8800000) {
                        final Point pos = mons2.getPosition();
                        this.killAllMonsters(true);
                        this.spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(8800000), pos);
                        break;
                    }
                }
            }
        }
        else if (mobid == 8800002 && this.mapid == 280030000) {
            String 挑战者 = "";
            for (final MapleCharacter player : this.getCharacters()) {
                挑战者 = 挑战者 + player.getName() + " ";
                player.setBossLog("扎昆经验限制");
            }
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[挑战]:扎昆被 " + 挑战者 + "击败了。"));
            if (this.speedRunStart > 0L) {
                type = SpeedRunType.Zakum;
            }
            if (sqd != null) {
                this.doShrine(true);
            }
            try (final PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characterz SET Point = 0 WHERE channel = 500")) {
                ps.executeUpdate();
            }
            catch (SQLException ex2) {}
        }
        if (mobid >= 8800103 && mobid <= 8800110) {
            boolean makeZakReal = true;
            final Collection<MapleMonster> monsters = this.getAllMonstersThreadsafe();
            for (final MapleMonster mons : monsters) {
                if (mons.getId() >= 8800103 && mons.getId() <= 8800110) {
                    makeZakReal = false;
                    break;
                }
            }
            if (makeZakReal) {
                for (final MapleMapObject object : monsters) {
                    final MapleMonster mons2 = (MapleMonster)object;
                    if (mons2.getId() == 8800100) {
                        final Point pos = mons2.getPosition();
                        this.killAllMonsters(true);
                        this.spawnMonsterOnGroundBelow(MapleLifeFactory.getMonster(8800100), pos);
                        break;
                    }
                }
            }
        }
        else if (mobid == 8800102 && this.mapid == 280030000) {
            String 挑战者 = "";
            for (final MapleCharacter player : this.getCharacters()) {
                挑战者 = 挑战者 + player.getName() + " ";
                player.setBossLog("扎昆经验限制");
            }
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[挑战]:进阶扎昆被 " + 挑战者 + "击败了。"));
            if (this.speedRunStart > 0L) {
                type = SpeedRunType.JjZakum;
            }
            if (sqd != null) {
                this.doShrine(true);
            }
            try (final PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characterz SET Point = 0 WHERE channel = 510")) {
                ps.executeUpdate();
            }
            catch (SQLException ex3) {}
        }
        if (mobid == 8820008) {
            for (final MapleMapObject mmo : this.getAllMonstersThreadsafe()) {
                final MapleMonster mons3 = (MapleMonster)mmo;
                if (mons3.getLinkOid() != monster.getObjectId()) {
                    this.killMonster(mons3, chr, false, false, animation);
                }
            }
        }
        else if (mobid >= 8820010 && mobid <= 8820014) {
            for (final MapleMapObject mmo : this.getAllMonstersThreadsafe()) {
                final MapleMonster mons3 = (MapleMonster)mmo;
                if (mons3.getId() != 8820000 && mons3.getObjectId() != monster.getObjectId() && mons3.getLinkOid() != monster.getObjectId()) {
                    this.killMonster(mons3, chr, false, false, animation);
                }
            }
        }
        if (withDrops) {
            MapleCharacter drop = null;
            if (dropOwner <= 0) {
                drop = chr;
            }
            else {
                drop = this.getCharacterById(dropOwner);
                if (drop == null) {
                    drop = chr;
                }
            }
            this.dropFromMonster(drop, monster);
        }
    }
    
    public List<MapleReactor> getAllReactor() {
        return this.getAllReactorsThreadsafe();
    }
    
    public List<MapleReactor> getAllReactorsThreadsafe() {
        final ArrayList<MapleReactor> ret = new ArrayList<MapleReactor>();
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.REACTOR)).readLock().lock();
        try {
            for (final MapleMapObject mmo : ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.REACTOR)).values()) {
                ret.add((MapleReactor)mmo);
            }
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.REACTOR)).readLock().unlock();
        }
        return ret;
    }
    
    public List<MapleMapObject> getAllDoor() {
        return this.getAllDoorsThreadsafe();
    }
    
    public List<MapleMapObject> getAllDoorsThreadsafe() {
        final ArrayList<MapleMapObject> ret = new ArrayList<MapleMapObject>();
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.DOOR)).readLock().lock();
        try {
            for (final MapleMapObject mmo : ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.DOOR)).values()) {
                ret.add(mmo);
            }
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.DOOR)).readLock().unlock();
        }
        return ret;
    }
    
    public List<MapleMapObject> getAllMerchant() {
        return this.getAllHiredMerchantsThreadsafe();
    }
    
    public List<MapleMapObject> getAllHiredMerchantsThreadsafe() {
        final ArrayList<MapleMapObject> ret = new ArrayList<MapleMapObject>();
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.HIRED_MERCHANT)).readLock().lock();
        try {
            for (final MapleMapObject mmo : ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.HIRED_MERCHANT)).values()) {
                ret.add(mmo);
            }
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.HIRED_MERCHANT)).readLock().unlock();
        }
        return ret;
    }
    
    public List<MapleMonster> getAllMonster() {
        return this.getAllMonstersThreadsafe();
    }
    
    public List<MapleMonster> getAllMonstersThreadsafe() {
        final ArrayList<MapleMonster> ret = new ArrayList<MapleMonster>();
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.MONSTER)).readLock().lock();
        try {
            for (final MapleMapObject mmo : ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.MONSTER)).values()) {
                ret.add((MapleMonster)mmo);
            }
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.MONSTER)).readLock().unlock();
        }
        return ret;
    }
    
    public List<Integer> getAllUniqueMonsters() {
        final ArrayList<Integer> ret = new ArrayList<Integer>();
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.MONSTER)).readLock().lock();
        try {
            for (final MapleMapObject mmo : ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.MONSTER)).values()) {
                final int theId = ((MapleMonster)mmo).getId();
                if (!ret.contains((Object)Integer.valueOf(theId))) {
                    ret.add(Integer.valueOf(theId));
                }
            }
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.MONSTER)).readLock().unlock();
        }
        return ret;
    }
    
    public Collection<MapleCharacter> getNearestPvpChar(final Point attacker, final double maxRange, final double maxHeight, final Collection<MapleCharacter> chr) {
        final Collection<MapleCharacter> character = new LinkedList<MapleCharacter>();
        for (final MapleCharacter a : this.characters) {
            if (chr.contains((Object)a.getClient().getPlayer())) {
                final Point attackedPlayer = a.getPosition();
                final MaplePortal Port = a.getMap().findClosestSpawnpoint(a.getPosition());
                final Point nearestPort = Port.getPosition();
                final double safeDis = attackedPlayer.distance((Point2D)nearestPort);
                final double distanceX = attacker.distance(attackedPlayer.getX(), attackedPlayer.getY());
                if (MaplePvp.isLeft && attacker.x > attackedPlayer.x && distanceX < maxRange && distanceX > 2.0 && (double)attackedPlayer.y >= (double)attacker.y - maxHeight && (double)attackedPlayer.y <= (double)attacker.y + maxHeight && safeDis > 2.0) {
                    character.add(a);
                }
                if (!MaplePvp.isRight || attacker.x >= attackedPlayer.x || distanceX >= maxRange || distanceX <= 2.0 || (double)attackedPlayer.y < (double)attacker.y - maxHeight || (double)attackedPlayer.y > (double)attacker.y + maxHeight || safeDis <= 2.0) {
                    continue;
                }
                character.add(a);
            }
        }
        return character;
    }
    
    public final void KillFk(final boolean animate) {
        final List<MapleMapObject> monsters = this.getMapObjectsInRange(new Point(0, 0), Double.POSITIVE_INFINITY, Arrays.asList(MapleMapObjectType.MONSTER));
        for (final MapleMapObject monstermo : monsters) {
            final MapleMonster monster = (MapleMonster)monstermo;
            if (monster.getId() == 3230300 || monster.getId() == 3230301) {
                this.spawnedMonstersOnMap.decrementAndGet();
                monster.setHp(0L);
                this.broadcastMessage(MobPacket.killMonster(monster.getObjectId(), (int)(animate ? 1 : 0)));
                this.removeMapObject((MapleMapObject)monster);
                monster.killed();
            }
        }
        this.broadcastMessage(MaplePacketCreator.serverNotice(6, "由於受詛咒的岩石被摧殘，然而被詛咒的蝴蝶精消失了。"));
    }
    
    public final void killAllMonsters(final boolean animate) {
        for (final MapleMapObject monstermo : this.getAllMonstersThreadsafe()) {
            final MapleMonster monster = (MapleMonster)monstermo;
            this.spawnedMonstersOnMap.decrementAndGet();
            monster.setHp(0L);
            this.broadcastMessage(MobPacket.killMonster(monster.getObjectId(), (int)(animate ? 1 : 0)));
            this.removeMapObject((MapleMapObject)monster);
            monster.killed();
        }
    }
    
    public final void killMonster(final int monsId) {
        for (final MapleMapObject mmo : this.getAllMonstersThreadsafe()) {
            if (((MapleMonster)mmo).getId() == monsId) {
                this.spawnedMonstersOnMap.decrementAndGet();
                this.removeMapObject(mmo);
                this.broadcastMessage(MobPacket.killMonster(mmo.getObjectId(), 1));
                break;
            }
        }
    }
    
    private String MapDebug_Log() {
        final StringBuilder sb = new StringBuilder("擊敗时间 : ");
        sb.append(FilePrinter.getLocalDateString());
        sb.append(" | 地图代码 : ").append(this.mapid);
        this.charactersLock.readLock().lock();
        try {
            sb.append(" 玩家 [").append(this.characters.size()).append("] | ");
            for (final MapleCharacter mc : this.characters) {
                sb.append(mc.getName()).append(", ");
            }
        }
        finally {
            this.charactersLock.readLock().unlock();
        }
        return sb.toString();
    }
    
    public final void limitReactor(final int rid, final int num) {
        final List<MapleReactor> toDestroy = new ArrayList<MapleReactor>();
        final Map<Integer, Integer> contained = new LinkedHashMap<Integer, Integer>();
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.REACTOR)).readLock().lock();
        try {
            for (final MapleMapObject obj : ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.REACTOR)).values()) {
                final MapleReactor mr = (MapleReactor)obj;
                if (contained.containsKey((Object)Integer.valueOf(mr.getReactorId()))) {
                    if ((int)Integer.valueOf(contained.get((Object)Integer.valueOf(mr.getReactorId()))) >= num) {
                        toDestroy.add(mr);
                    }
                    else {
                        contained.put(Integer.valueOf(mr.getReactorId()), Integer.valueOf((int)Integer.valueOf(contained.get((Object)Integer.valueOf(mr.getReactorId()))) + 1));
                    }
                }
                else {
                    contained.put(Integer.valueOf(mr.getReactorId()), Integer.valueOf(1));
                }
            }
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.REACTOR)).readLock().unlock();
        }
        for (final MapleReactor mr2 : toDestroy) {
            this.destroyReactor(mr2.getObjectId());
        }
    }
    
    public final void destroyReactors(final int first, final int last) {
        final List<MapleReactor> toDestroy = new ArrayList<MapleReactor>();
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.REACTOR)).readLock().lock();
        try {
            for (final MapleMapObject obj : ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.REACTOR)).values()) {
                final MapleReactor mr = (MapleReactor)obj;
                if (mr.getReactorId() >= first && mr.getReactorId() <= last) {
                    toDestroy.add(mr);
                }
            }
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.REACTOR)).readLock().unlock();
        }
        for (final MapleReactor mr2 : toDestroy) {
            this.destroyReactor(mr2.getObjectId());
        }
    }
    
    public final void destroyReactor(final int oid) {
        final MapleReactor reactor = this.getReactorByOid(oid);
        this.broadcastMessage(MaplePacketCreator.destroyReactor(reactor));
        reactor.setAlive(false);
        this.removeMapObject((MapleMapObject)reactor);
        reactor.setTimerActive(false);
        if (reactor.getDelay() > 0) {
            try {
                MapTimer.getInstance().schedule((Runnable)new Runnable() {
                    @Override
                    public final void run() {
                        MapleMap.this.respawnReactor(reactor);
                    }
                }, (long)reactor.getDelay());
            }
            catch (RejectedExecutionException ex) {}
        }
    }
    
    public final void reloadReactors() {
        final List<MapleReactor> toSpawn = new ArrayList<MapleReactor>();
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.REACTOR)).readLock().lock();
        try {
            for (final MapleMapObject obj : ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.REACTOR)).values()) {
                final MapleReactor reactor = (MapleReactor)obj;
                this.broadcastMessage(MaplePacketCreator.destroyReactor(reactor));
                reactor.setAlive(false);
                reactor.setTimerActive(false);
                toSpawn.add(reactor);
            }
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.REACTOR)).readLock().unlock();
        }
        for (final MapleReactor r : toSpawn) {
            this.removeMapObject((MapleMapObject)r);
            if (r.getReactorId() != 9980000 && r.getReactorId() != 9980001) {
                this.respawnReactor(r);
            }
        }
    }
    
    public final void resetReactors() {
        this.setReactorState((byte)0);
    }
    
    public final void setReactorState() {
        this.setReactorState((byte)1);
    }
    
    public final void setReactorState(final byte state) {
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.REACTOR)).readLock().lock();
        try {
            for (final MapleMapObject obj : ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.REACTOR)).values()) {
                ((MapleReactor)obj).forceHitReactor(state);
            }
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.REACTOR)).readLock().unlock();
        }
    }
    
    public final void shuffleReactors() {
        this.shuffleReactors(0, 9999999);
    }
    
    public final void shuffleReactors(final int first, final int last) {
        final List<Point> points = new ArrayList<Point>();
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.REACTOR)).readLock().lock();
        try {
            for (final MapleMapObject obj : ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.REACTOR)).values()) {
                final MapleReactor mr = (MapleReactor)obj;
                if (mr.getReactorId() >= first && mr.getReactorId() <= last) {
                    points.add(mr.getPosition());
                }
            }
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.REACTOR)).readLock().unlock();
        }
        Collections.shuffle(points);
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.REACTOR)).readLock().lock();
        try {
            for (final MapleMapObject obj : ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.REACTOR)).values()) {
                final MapleReactor mr = (MapleReactor)obj;
                if (mr.getReactorId() >= first && mr.getReactorId() <= last) {
                    mr.setPosition((Point)points.remove(points.size() - 1));
                }
            }
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.REACTOR)).readLock().unlock();
        }
    }
    
    public final void updateMonsterController(final MapleMonster monster) {
        if (!monster.isAlive()) {
            return;
        }
        if (monster.getController() != null) {
            if (monster.getController().getMap() == this) {
                return;
            }
            monster.getController().stopControllingMonster(monster);
        }
        int mincontrolled = -1;
        MapleCharacter newController = null;
        this.charactersLock.readLock().lock();
        try {
            for (final MapleCharacter chr : this.characters) {
                if (!chr.isHidden() && !chr.isClone() && (chr.getControlledSize() < mincontrolled || mincontrolled == -1)) {
                    mincontrolled = chr.getControlledSize();
                    newController = chr;
                }
            }
        }
        finally {
            this.charactersLock.readLock().unlock();
        }
        if (newController != null) {
            if (monster.isFirstAttack()) {
                newController.controlMonster(monster, true);
                monster.setControllerHasAggro(true);
                monster.setControllerKnowsAboutAggro(true);
            }
            else {
                newController.controlMonster(monster, false);
            }
        }
    }
    
    public final MapleMapObject getMapObject(final int oid, final MapleMapObjectType type) {
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)type)).readLock().lock();
        try {
            return (MapleMapObject)((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)type)).get((Object)Integer.valueOf(oid));
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)type)).readLock().unlock();
        }
    }
    
    public final boolean containsNPC(final int npcid) {
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.NPC)).readLock().lock();
        try {
            for (final MapleNPC n : (java.util.Collection<MapleNPC>)(java.util.Collection)((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.NPC)).values()) {
                if (n.getId() == npcid) {
                    return true;
                }
            }
            return false;
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.NPC)).readLock().unlock();
        }
    }
    
    public MapleNPC getNPCById(final int id) {
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.NPC)).readLock().lock();
        try {
            for (final MapleNPC n : (java.util.Collection<MapleNPC>)(java.util.Collection)((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.NPC)).values()) {
                if (n.getId() == id) {
                    return n;
                }
            }
            return null;
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.NPC)).readLock().unlock();
        }
    }
    
    public MapleMonster getMonsterById(final int id) {
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.MONSTER)).readLock().lock();
        try {
            MapleMonster ret = null;
            for (final MapleMonster n : (java.util.Collection<MapleMonster>)(java.util.Collection)((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.MONSTER)).values()) {
                if (n.getId() == id) {
                    ret = n;
                    break;
                }
            }
            return ret;
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.MONSTER)).readLock().unlock();
        }
    }
    
    public int countMonsterById(final int id) {
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.MONSTER)).readLock().lock();
        try {
            int ret = 0;
            for (final MapleMonster n : (java.util.Collection<MapleMonster>)(java.util.Collection)((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.MONSTER)).values()) {
                if (n.getId() == id) {
                    ++ret;
                }
            }
            return ret;
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.MONSTER)).readLock().unlock();
        }
    }
    
    public MapleReactor getReactorById(final int id) {
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.REACTOR)).readLock().lock();
        try {
            MapleReactor ret = null;
            for (final MapleReactor n : (java.util.Collection<MapleReactor>)(java.util.Collection)(((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.REACTOR)).values())) {
                if (n.getReactorId() == id) {
                    ret = n;
                    break;
                }
            }
            return ret;
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.REACTOR)).readLock().unlock();
        }
    }
    
    public final MapleMonster getMonsterByOid(final int oid) {
        final MapleMapObject mmo = this.getMapObject(oid, MapleMapObjectType.MONSTER);
        if (mmo == null) {
            return null;
        }
        return (MapleMonster)mmo;
    }
    
    public final MapleNPC getNPCByOid(final int oid) {
        final MapleMapObject mmo = this.getMapObject(oid, MapleMapObjectType.NPC);
        if (mmo == null) {
            return null;
        }
        return (MapleNPC)mmo;
    }
    
    public final MapleReactor getReactorByOid(final int oid) {
        final MapleMapObject mmo = this.getMapObject(oid, MapleMapObjectType.REACTOR);
        if (mmo == null) {
            return null;
        }
        return (MapleReactor)mmo;
    }
    
    public final MapleReactor getReactorByName(final String name) {
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.REACTOR)).readLock().lock();
        try {
            for (final MapleMapObject obj : ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.REACTOR)).values()) {
                final MapleReactor mr = (MapleReactor)obj;
                if (mr.getName().equalsIgnoreCase(name)) {
                    return mr;
                }
            }
            return null;
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.REACTOR)).readLock().unlock();
        }
    }
    
    public final void spawnNpc(final int id, final Point pos) {
        final MapleNPC npc = MapleLifeFactory.getNPC(id);
        npc.setPosition(pos);
        npc.setCy(pos.y);
        npc.setRx0(pos.x + 50);
        npc.setRx1(pos.x - 50);
        npc.setFh(this.getFootholds().findBelow(pos).getId());
        npc.setCustom(true);
        this.addMapObject((MapleMapObject)npc);
        this.broadcastMessage(MaplePacketCreator.spawnNPC(npc, true));
    }
    
    public final void removeNpc_(final int npcid) {
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.NPC)).writeLock().lock();
        try {
            final Iterator<MapleMapObject> itr = ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.NPC)).values().iterator();
            while (itr.hasNext()) {
                final MapleNPC npc = (MapleNPC)itr.next();
                if (npcid == -1 || npc.getId() == npcid) {
                    this.broadcastMessage(MaplePacketCreator.removeNPCController(npc.getObjectId()));
                    this.broadcastMessage(MaplePacketCreator.removeNPC(npc.getObjectId()));
                    itr.remove();
                }
            }
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.NPC)).writeLock().unlock();
        }
    }
    
    public final void removeNpc(final int npcid) {
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.NPC)).writeLock().lock();
        try {
            final Iterator<MapleMapObject> itr = ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.NPC)).values().iterator();
            while (itr.hasNext()) {
                final MapleNPC npc = (MapleNPC)itr.next();
                if (npc.isCustom() && (npcid == -1 || npc.getId() == npcid)) {
                    this.broadcastMessage(MaplePacketCreator.removeNPCController(npc.getObjectId()));
                    this.broadcastMessage(MaplePacketCreator.removeNPC(npc.getObjectId()));
                    itr.remove();
                }
            }
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.NPC)).writeLock().unlock();
        }
    }
    
    public final void spawnMonster_sSack(final MapleMonster mob, final Point pos, final int spawnType) {
        final Point spos = this.calcPointBelow(new Point(pos.x, pos.y - 1));
        mob.setPosition(spos);
        this.spawnMonster(mob, spawnType);
    }
    
    public final void spawnMonsterOnGroundBelow(final MapleMonster mob, final Point pos) {
        this.spawnMonster_sSack(mob, pos, -2);
    }
    
    public final int spawnMonsterWithEffectBelow(final MapleMonster mob, final Point pos, final int effect) {
        final Point spos = this.calcPointBelow(new Point(pos.x, pos.y - 1));
        return this.spawnMonsterWithEffect(mob, effect, spos);
    }
    
    public final void spawnZakum(final int x, final int y) {
        final Point pos = new Point(x, y);
        final MapleMonster mainb = MapleLifeFactory.getMonster(8800000);
        final Point spos = this.calcPointBelow(new Point(pos.x, pos.y - 1));
        mainb.setPosition(spos);
        mainb.setFake(true);
        this.spawnFakeMonster(mainb);
        final int[] array;
        final int[] zakpart = array = new int[] { 8800003, 8800004, 8800005, 8800006, 8800007, 8800008, 8800009, 8800010 };
        for (final int i : array) {
            final MapleMonster part = MapleLifeFactory.getMonster(i);
            part.setPosition(spos);
            this.spawnMonster(part, -2);
        }
        if (this.squadSchedule != null) {
            this.cancelSquadSchedule();
            this.broadcastMessage(MaplePacketCreator.stopClock());
        }
    }
    
    public final void spawnChaosZakum(final int x, final int y) {
        final Point pos = new Point(x, y);
        final MapleMonster mainb = MapleLifeFactory.getMonster(8800100);
        final Point spos = this.calcPointBelow(new Point(pos.x, pos.y - 1));
        final int[] array;
        final int[] zakpart = array = new int[] { 8800103, 8800104, 8800105, 8800106, 8800107, 8800108, 8800109, 8800110 };
        for (final int i : array) {
            final MapleMonster part = MapleLifeFactory.getMonster(i);
            part.setPosition(spos);
            this.spawnMonster(part, -2);
        }
        mainb.setPosition(spos);
        mainb.setFake(true);
        this.spawnFakeMonster(mainb);
        if (this.squadSchedule != null) {
            this.cancelSquadSchedule();
        }
    }
    
    public List<MapleMist> getAllMistsThreadsafe() {
        final ArrayList<MapleMist> ret = new ArrayList<MapleMist>();
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.MIST)).readLock().lock();
        try {
            for (final MapleMapObject mmo : ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.MIST)).values()) {
                ret.add((MapleMist)mmo);
            }
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.MIST)).readLock().unlock();
        }
        return ret;
    }
    
    public final void spawnFakeMonsterOnGroundBelow(final MapleMonster mob, final Point pos) {
        final Point calcPointBelow;
        final Point spos = calcPointBelow = this.calcPointBelow(new Point(pos.x, pos.y - 1));
        --calcPointBelow.y;
        mob.setPosition(spos);
        this.spawnFakeMonster(mob);
    }
    
    private void checkRemoveAfter(final MapleMonster monster) {
        final int ra = monster.getStats().getRemoveAfter();
        if (ra > 0) {
            MapTimer.getInstance().schedule((Runnable)new Runnable() {
                @Override
                public final void run() {
                    if (monster != null && monster == MapleMap.this.getMapObject(monster.getObjectId(), monster.getType())) {
                        MapleMap.this.killMonster(monster);
                    }
                }
            }, (long)(ra * 1000));
        }
    }
    
    public final void spawnRevives(final MapleMonster monster, final int oid) {
        monster.setMap(this);
        this.checkRemoveAfter(monster);
        monster.setLinkOid(oid);
        this.spawnAndAddRangedMapObject((MapleMapObject)monster, (DelayedPacketCreation)new DelayedPacketCreation() {
            @Override
            public final void sendPackets(final MapleClient c) {
                c.sendPacket(MobPacket.spawnMonster(monster, -2, 0, oid));
            }
        }, null);
        this.updateMonsterController(monster);
        this.spawnedMonstersOnMap.incrementAndGet();
    }
    
    public final void spawnMonster(final MapleMonster monster, final int spawnType) {
        monster.setMap(this);
        this.checkRemoveAfter(monster);
        if (monster.getId() == 9300166) {
            MapTimer.getInstance().schedule((Runnable)new Runnable() {
                @Override
                public void run() {
                    MapleMap.this.broadcastMessage(MobPacket.killMonster(monster.getObjectId(), 2));
                }
            }, (long)new Random().nextInt(5000));
        }
        this.spawnAndAddRangedMapObject((MapleMapObject)monster, (DelayedPacketCreation)new DelayedPacketCreation() {
            @Override
            public final void sendPackets(final MapleClient c) {
                c.sendPacket(MobPacket.spawnMonster(monster, spawnType, 0, 0));
            }
        }, null);
        this.updateMonsterController(monster);
        this.spawnedMonstersOnMap.incrementAndGet();
    }
    
    public final int spawnMonsterWithEffect(final MapleMonster monster, final int effect, final Point pos) {
        try {
            monster.setMap(this);
            monster.setPosition(pos);
            this.spawnAndAddRangedMapObject((MapleMapObject)monster, (DelayedPacketCreation)new DelayedPacketCreation() {
                @Override
                public final void sendPackets(final MapleClient c) {
                    c.sendPacket(MobPacket.spawnMonster(monster, -2, effect, 0));
                }
            }, null);
            this.updateMonsterController(monster);
            this.spawnedMonstersOnMap.incrementAndGet();
            return monster.getObjectId();
        }
        catch (Exception e) {
            return -1;
        }
    }
    
    public final void spawnFakeMonster(final MapleMonster monster) {
        monster.setMap(this);
        monster.setFake(true);
        this.spawnAndAddRangedMapObject((MapleMapObject)monster, (DelayedPacketCreation)new DelayedPacketCreation() {
            @Override
            public final void sendPackets(final MapleClient c) {
                c.sendPacket(MobPacket.spawnMonster(monster, -2, 252, 0));
            }
        }, null);
        this.updateMonsterController(monster);
        this.spawnedMonstersOnMap.incrementAndGet();
    }
    
    public final void spawnReactor(final MapleReactor reactor) {
        reactor.setMap(this);
        this.spawnAndAddRangedMapObject((MapleMapObject)reactor, (DelayedPacketCreation)new DelayedPacketCreation() {
            @Override
            public final void sendPackets(final MapleClient c) {
                c.sendPacket(MaplePacketCreator.spawnReactor(reactor));
            }
        }, null);
    }
    
    private void respawnReactor(final MapleReactor reactor) {
        reactor.setState((byte)0);
        reactor.setAlive(true);
        this.spawnReactor(reactor);
    }
    
    public final void spawnDoor(final MapleDoor door) {
        this.spawnAndAddRangedMapObject((MapleMapObject)door, (DelayedPacketCreation)new DelayedPacketCreation() {
            @Override
            public final void sendPackets(final MapleClient c) {
                door.sendSpawnData(c);
                c.sendPacket(MaplePacketCreator.enableActions());
            }
        }, (SpawnCondition)new SpawnCondition() {
            @Override
            public final boolean canSpawn(final MapleCharacter chr) {
                return door.getTarget().getId() == chr.getMapId() || door.getOwnerId() == chr.getId() || (door.getOwner() != null && door.getOwner().getParty() != null && door.getOwner().getParty().getMemberById(chr.getId()) != null);
            }
        });
    }
    
    public final void spawnSummon(final MapleSummon summon) {
        summon.updateMap(this);
        this.spawnAndAddRangedMapObject((MapleMapObject)summon, (DelayedPacketCreation)new DelayedPacketCreation() {
            @Override
            public void sendPackets(final MapleClient c) {
                if (c != null && c.getPlayer() != null && summon != null && (!summon.isChangedMap() || summon.getOwnerId() == c.getPlayer().getId())) {
                    c.sendPacket(MaplePacketCreator.spawnSummon(summon, true));
                }
            }
        }, null);
    }
    
    public final void spawnMist(final MapleMist mist, final int duration, final boolean fake) {
        this.spawnAndAddRangedMapObject((MapleMapObject)mist, (DelayedPacketCreation)new DelayedPacketCreation() {
            @Override
            public void sendPackets(final MapleClient c) {
                mist.sendSpawnData(c);
            }
        }, null);
        final MapTimer tMan = MapTimer.getInstance();
        ScheduledFuture<?> poisonSchedule = null;
        switch (mist.isPoisonMist()) {
            case 1: {
                final MapleCharacter owner = this.getCharacterById(mist.getOwnerId());
                poisonSchedule = tMan.register((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        for (final MapleMapObject mo : MapleMap.this.getMapObjectsInRect(mist.getBox(), Collections.singletonList(MapleMapObjectType.MONSTER))) {
                            if (mist.makeChanceResult() && !((MapleMonster)mo).isBuffed(MonsterStatus.POISON)) {
                                ((MapleMonster)mo).applyStatus(owner, new MonsterStatusEffect(MonsterStatus.POISON, Integer.valueOf(1), mist.getSourceSkill().getId(), null, false), true, (long)duration, ((MapleMonster)mo).getStats().isBoss(), mist.getSource());
                            }
                        }
                    }
                }, 2000L, 2500L);
                break;
            }
            case 2: {
                poisonSchedule = tMan.register((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        for (final MapleMapObject mo : MapleMap.this.getMapObjectsInRect(mist.getBox(), Collections.singletonList(MapleMapObjectType.PLAYER))) {
                            if (mist.makeChanceResult()) {
                                final MapleCharacter chr = (MapleCharacter)mo;
                                chr.addMP((int)((double)mist.getSource().getX() * ((double)chr.getStat().getMaxMp() / 100.0)));
                            }
                        }
                    }
                }, 2000L, 2500L);
                break;
            }
            default: {
                poisonSchedule = null;
                break;
            }
        }
        try {
        	final ScheduledFuture<?> poisonSchedule2 = poisonSchedule;
            tMan.schedule((Runnable)new Runnable() {
                @Override
                public void run() {
                    MapleMap.this.broadcastMessage(MaplePacketCreator.removeMist(mist.getObjectId(), false));
                    MapleMap.this.removeMapObject((MapleMapObject)mist);
                    if (poisonSchedule2 != null) {
                        poisonSchedule2.cancel(false);
                    }
                }
            }, (long)duration);
        }
        catch (RejectedExecutionException ex) {}
    }
    
    public final void disappearingItemDrop(final MapleMapObject dropper, final MapleCharacter owner, final IItem item, final Point pos) {
        final Point droppos = this.calcDropPos(pos, pos);
        final MapleMapItem drop = new MapleMapItem(item, droppos, dropper, owner, (byte)1, false);
        this.broadcastMessage(MaplePacketCreator.dropItemFromMapObject(drop, dropper.getPosition(), droppos, (byte)3), drop.getPosition());
    }
    
    public final void spawnMesoDrop(final int meso, final Point position, final MapleMapObject dropper, final MapleCharacter owner, final boolean playerDrop, final byte droptype) {
        final Point droppos = this.calcDropPos(position, position);
        final MapleMapItem mdrop = new MapleMapItem(meso, droppos, dropper, owner, droptype, playerDrop);
        this.spawnAndAddRangedMapObject((MapleMapObject)mdrop, (DelayedPacketCreation)new DelayedPacketCreation() {
            @Override
            public void sendPackets(final MapleClient c) {
                c.sendPacket(MaplePacketCreator.dropItemFromMapObject(mdrop, dropper.getPosition(), droppos, (byte)1));
            }
        }, null);
        if (!this.everlast) {
            mdrop.registerExpire(120000L);
            if (droptype == 0 || droptype == 1) {
                mdrop.registerFFA(30000L);
            }
        }
    }
    
    public final void spawnMobMesoDrop(final int meso, final Point position, final MapleMapObject dropper, final MapleCharacter owner, final boolean playerDrop, final byte droptype) {
        final MapleMapItem mdrop = new MapleMapItem(meso, position, dropper, owner, droptype, playerDrop);
        this.spawnAndAddRangedMapObject((MapleMapObject)mdrop, (DelayedPacketCreation)new DelayedPacketCreation() {
            @Override
            public void sendPackets(final MapleClient c) {
                c.sendPacket(MaplePacketCreator.dropItemFromMapObject(mdrop, dropper.getPosition(), position, (byte)1));
            }
        }, null);
        if (MapleMap.特殊宠物吸取开关 && MapleMap.特殊宠物吸金开关 && owner.getEventInstance() == null) {
            boolean 吸物状态 = false;
            int 宠物数据库ID = 0;
            if (owner.getId() == mdrop.character_ownerid) {
                for (final MaplePet pet : owner.getSummonedPets()) {
                    if (owner.getItemQuantity(MapleMap.持有物道具, false) > 0 && pet.getPetItemId() != 0) {
                        宠物数据库ID = pet.getUniqueId();
                        吸物状态 = true;
                        break;
                    }
                }
                if (吸物状态 && mdrop.getMeso() > 0) {
                    if (owner.getParty() != null && mdrop.getOwner() == owner.getId()) {
                        final List<MapleCharacter> toGive = new LinkedList<MapleCharacter>();
                        final int splitMeso = mdrop.getMeso() * 40 / 100;
                        for (final MaplePartyCharacter z : owner.getParty().getMembers()) {
                            final MapleCharacter m = owner.getMap().getCharacterById(z.getId());
                            if (m != null && m.getId() != owner.getId()) {
                                toGive.add(m);
                            }
                        }
                        for (final MapleCharacter i : toGive) {
                            i.gainMeso(splitMeso / toGive.size() + (i.getStat().hasPartyBonus ? ((int)((double)mdrop.getMeso() / 20.0)) : 0), true);
                        }
                        owner.gainMeso(mdrop.getMeso() - splitMeso, true);
                    }
                    else {
                        owner.gainMeso(mdrop.getMeso(), true);
                    }
                    final byte petz = owner.getPetIndex(宠物数据库ID);
                    InventoryHandler.removeItemPet(owner, mdrop, 宠物数据库ID);
                }
            }
        }
        mdrop.registerExpire(120000L);
        if (droptype == 0 || droptype == 1) {
            mdrop.registerFFA(30000L);
        }
    }
    
    public final void spawnMobDrop(final IItem idrop, final Point dropPos, final MapleMonster mob, final MapleCharacter chr, final byte droptype, final short questid) {
        final MapleMapItem mdrop = new MapleMapItem(idrop, dropPos, (MapleMapObject)mob, chr, droptype, false, (int)questid);
        this.spawnAndAddRangedMapObject((MapleMapObject)mdrop, (DelayedPacketCreation)new DelayedPacketCreation() {
            @Override
            public void sendPackets(final MapleClient c) {
                if (questid <= 0 || c.getPlayer().getQuestStatus((int)questid) == 1) {
                    c.sendPacket(MaplePacketCreator.dropItemFromMapObject(mdrop, mob.getPosition(), dropPos, (byte)1));
                }
            }
        }, null);
        if (MapleMap.特殊宠物吸取开关 && MapleMap.特殊宠物吸物开关 && chr.getEventInstance() == null && !mob.getStats().isBoss()) {
            boolean 吸物状态 = false;
            int 宠物数据库ID = 0;
            if (chr.getId() == mdrop.character_ownerid) {
                for (final MaplePet pet : chr.getSummonedPets()) {
                    if (chr.getItemQuantity(MapleMap.持有物道具, false) > 0 && pet.getPetItemId() != 0) {
                        宠物数据库ID = pet.getUniqueId();
                        吸物状态 = true;
                        break;
                    }
                }
                for (final MaplePet pet : chr.getSummonedPets()) {
                    final List excluded = pet.getExcluded();
                    if (excluded.size() > 0) {
                        for (final Object excluded2 : excluded) {
                            if ((int)(Integer)excluded2 == mdrop.getItemId()) {
                                吸物状态 = false;
                                break;
                            }
                        }
                    }
                }
                if (吸物状态 && MapleMap.特殊宠物吸物无法使用地图开关) {
                    for (int i = 0; i < MapleMap.特殊宠物吸物无法使用地图.length; ++i) {
                        if (this.mapid == Integer.parseInt(MapleMap.特殊宠物吸物无法使用地图[i])) {
                            吸物状态 = false;
                            break;
                        }
                    }
                }
                if (吸物状态 && mdrop.getItem().getItemId() != 0 && MapleInventoryManipulator.checkSpace(chr.getClient(), mdrop.getItemId(), (int)mdrop.getItem().getQuantity(), mdrop.getItem().getOwner())) {
                    final byte petz = chr.getPetIndex(宠物数据库ID);
                    InventoryHandler.removeItemPet(chr, mdrop, (int)petz);
                    MapleInventoryManipulator.addFromDrop(chr.getClient(), mdrop.getItem(), true, mdrop.getDropper() instanceof MapleMonster, true);
                }
            }
        }
        mdrop.registerExpire(120000L);
        if (droptype == 0 || droptype == 1) {
            mdrop.registerFFA(30000L);
        }
        this.activateItemReactors(mdrop, chr.getClient());
    }
    
    public final void spawnRandDrop() {
        if (this.mapid != 910000000 || this.channel != 1) {
            return;
        }
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.ITEM)).readLock().lock();
        try {
            for (final MapleMapObject o : ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.ITEM)).values()) {
                if (((MapleMapItem)o).isRandDrop()) {
                    return;
                }
            }
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.ITEM)).readLock().unlock();
        }
        MapTimer.getInstance().schedule((Runnable)new Runnable() {
            @Override
            public void run() {
                final Point pos = new Point(Randomizer.nextInt(800) + 531, -806);
                final int theItem = Randomizer.nextInt(1000);
                int itemid = 0;
                if (theItem < 950) {
                    itemid = GameConstants.normalDrops[Randomizer.nextInt(GameConstants.normalDrops.length)];
                }
                else if (theItem < 990) {
                    itemid = GameConstants.rareDrops[Randomizer.nextInt(GameConstants.rareDrops.length)];
                }
                else {
                    itemid = GameConstants.superDrops[Randomizer.nextInt(GameConstants.superDrops.length)];
                }
                MapleMap.this.spawnAutoDrop(itemid, pos);
            }
        }, 20000L);
    }
    
    public final void spawnAutoDrop(final int itemid, final Point pos) {
        IItem idrop = null;
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        if (GameConstants.getInventoryType(itemid) == MapleInventoryType.EQUIP) {
            idrop = ii.randomizeStats((Equip)ii.getEquipById(itemid));
        }
        else {
            idrop = new Item(itemid, (short)0, (short)1, (byte)0);
        }
        final MapleMapItem mdrop = new MapleMapItem(pos, idrop);
        this.spawnAndAddRangedMapObject((MapleMapObject)mdrop, (DelayedPacketCreation)new DelayedPacketCreation() {
            @Override
            public void sendPackets(final MapleClient c) {
                c.sendPacket(MaplePacketCreator.dropItemFromMapObject(mdrop, pos, pos, (byte)1));
            }
        }, null);
        this.broadcastMessage(MaplePacketCreator.dropItemFromMapObject(mdrop, pos, pos, (byte)0));
        mdrop.registerExpire(120000L);
    }
    
    public final void spawnAutoDrop2(final int itemid, final Point pos) {
        IItem idrop = null;
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        if (GameConstants.getInventoryType(itemid) == MapleInventoryType.EQUIP) {
            idrop = ii.randomizeStats((Equip)ii.getEquipById(itemid));
        }
        else {
            idrop = new Item(itemid, (short)0, (short)1, (byte)0);
        }
        final MapleMapItem mdrop = new MapleMapItem(pos, idrop);
        this.spawnAndAddRangedMapObject((MapleMapObject)mdrop, (DelayedPacketCreation)new DelayedPacketCreation() {
            @Override
            public void sendPackets(final MapleClient c) {
                if ((boolean)c.getPlayer().isCheating) {
                    return;
                }
                c.sendPacket(MaplePacketCreator.dropItemFromMapObject(mdrop, pos, pos, (byte)1));
            }
        }, null);
        this.broadcastMessage(MaplePacketCreator.dropItemFromMapObject(mdrop, pos, pos, (byte)0));
        mdrop.registerExpire(10000L);
    }
    
    public final void 物品掉落(final MapleMapObject dropper, final MapleCharacter owner, final IItem item, final Point pos, final boolean ffaDrop, final boolean playerDrop) {
        final Point droppos = this.calcDropPos(pos, pos);
        final MapleMapItem drop = new MapleMapItem(item, droppos, dropper, owner, (byte)2, playerDrop);
        this.spawnAndAddRangedMapObject((MapleMapObject)drop, (DelayedPacketCreation)new DelayedPacketCreation() {
            @Override
            public void sendPackets(final MapleClient c) {
                if ((boolean)owner.isCheating) {
                    return;
                }
                c.sendPacket(MaplePacketCreator.dropItemFromMapObject(drop, dropper.getPosition(), droppos, (byte)1));
            }
        }, null);
        if ((boolean)owner.isCheating) {
            return;
        }
        this.broadcastMessage(MaplePacketCreator.dropItemFromMapObject(drop, dropper.getPosition(), droppos, (byte)0));
        if (!this.everlast) {
            drop.registerExpire(10000L);
            this.activateItemReactors(drop, owner.getClient());
        }
    }
    
    public final void spawnItemDrop(final MapleMapObject dropper, final MapleCharacter owner, final IItem item, final Point pos, final boolean ffaDrop, final boolean playerDrop) {
        final Point droppos = this.calcDropPos(pos, pos);
        final MapleMapItem drop = new MapleMapItem(item, droppos, dropper, owner, (byte)2, playerDrop);
        this.spawnAndAddRangedMapObject((MapleMapObject)drop, (DelayedPacketCreation)new DelayedPacketCreation() {
            @Override
            public void sendPackets(final MapleClient c) {
                c.sendPacket(MaplePacketCreator.dropItemFromMapObject(drop, dropper.getPosition(), droppos, (byte)1));
            }
        }, null);
        this.broadcastMessage(MaplePacketCreator.dropItemFromMapObject(drop, dropper.getPosition(), droppos, (byte)0));
        if (!this.everlast) {
            drop.registerExpire(120000L);
            this.activateItemReactors(drop, owner.getClient());
        }
    }
    
    private void activateItemReactors(final MapleMapItem drop, final MapleClient c) {
        final IItem item = drop.getItem();
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.REACTOR)).readLock().lock();
        try {
            for (final MapleMapObject o : ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.REACTOR)).values()) {
                final MapleReactor react = (MapleReactor)o;
                if (react.getReactorType() == 100 && GameConstants.isCustomReactItem(react.getReactorId(), item.getItemId(), (int)Integer.valueOf(react.getReactItem().getLeft())) && (int)Integer.valueOf(react.getReactItem().getRight()) == item.getQuantity() && react.getArea().contains(drop.getPosition()) && !react.isTimerActive()) {
                    MapTimer.getInstance().schedule((Runnable)new ActivateItemReactor(drop, react, c), 5000L);
                    react.setTimerActive(true);
                    break;
                }
            }
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.REACTOR)).readLock().unlock();
        }
    }
    
    public int getItemsSize() {
        return ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.ITEM)).size();
    }
    
    public int getMobsSize() {
        return ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.MONSTER)).size();
    }
    
    public List<MapleMapItem> getAllItems() {
        return this.getAllItemsThreadsafe();
    }
    
    public List<MapleMapItem> getAllItemsThreadsafe() {
        final ArrayList<MapleMapItem> ret = new ArrayList<MapleMapItem>();
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.ITEM)).readLock().lock();
        try {
            for (final MapleMapObject mmo : ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.ITEM)).values()) {
                ret.add((MapleMapItem)mmo);
            }
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.ITEM)).readLock().unlock();
        }
        return ret;
    }
    
    public final void returnEverLastItem(final MapleCharacter chr) {
        for (final MapleMapObject o : this.getAllItemsThreadsafe()) {
            final MapleMapItem item = (MapleMapItem)o;
            if (item.getOwner() == chr.getId()) {
                item.setPickedUp(true);
                this.broadcastMessage(MaplePacketCreator.removeItemFromMap(item.getObjectId(), 2, chr.getId()), item.getPosition());
                if (item.getMeso() > 0) {
                    chr.gainMeso(item.getMeso(), false);
                }
                else {
                    MapleInventoryManipulator.addFromDrop(chr.getClient(), item.getItem(), false);
                }
                this.removeMapObject((MapleMapObject)item);
            }
        }
        this.spawnRandDrop();
    }
    
    public final void talkMonster(final String msg, final int itemId, final int objectid) {
        if (itemId > 0) {
            this.startMapEffect(msg, itemId, false);
        }
        this.broadcastMessage(MobPacket.talkMonster(objectid, itemId, msg));
        this.broadcastMessage(MobPacket.removeTalkMonster(objectid));
    }
    
    public final void startMapEffect(final String msg, final int itemId) {
        this.startMapEffect(msg, itemId, false);
    }
    
    public final void startMapEffect(final String msg, final int itemId, final boolean jukebox) {
        if (this.mapEffect != null) {
            return;
        }
        (this.mapEffect = new MapleMapEffect(msg, itemId)).setJukebox(jukebox);
        this.broadcastMessage(this.mapEffect.makeStartData());
        MapTimer.getInstance().schedule((Runnable)new Runnable() {
            @Override
            public void run() {
                MapleMap.this.broadcastMessage(mapEffect.makeDestroyData());
                mapEffect = null;
            }
        }, jukebox ? 300000L : 30000L);
    }
    
    public final void startExtendedMapEffect(final String msg, final int itemId) {
        this.broadcastMessage(MaplePacketCreator.startMapEffect(msg, itemId, true));
        MapTimer.getInstance().schedule((Runnable)new Runnable() {
            @Override
            public void run() {
                MapleMap.this.broadcastMessage(MaplePacketCreator.removeMapEffect());
                MapleMap.this.broadcastMessage(MaplePacketCreator.startMapEffect(msg, itemId, false));
            }
        }, 60000L);
    }
    
    public final void startJukebox(final String msg, final int itemId) {
        this.startMapEffect(msg, itemId, true);
    }
    
    public final void addPlayer(final MapleCharacter chr) {
        final List<MapleCharacter> players = this.getAllPlayersThreadsafe();
        for (final MapleCharacter c : players) {
            if (c.getId() == chr.getId()) {
                this.removePlayer(c);
            }
        }
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.PLAYER)).writeLock().lock();
        try {
            ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.PLAYER)).put(Integer.valueOf(chr.getObjectId()), chr);
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.PLAYER)).writeLock().unlock();
        }
        this.charactersLock.writeLock().lock();
        try {
            this.characters.add(chr);
        }
        finally {
            this.charactersLock.writeLock().unlock();
        }
        chr.setChangeTime(true);
        if (this.mapid == 109080000 || this.mapid == 109080001 || this.mapid == 109080002 || this.mapid == 109080003 || this.mapid == 109080010 || this.mapid == 109080011 || this.mapid == 109080012) {
            chr.setCoconutTeam((int)(this.getAndSwitchTeam() ? 0 : 1));
        }
        final byte[] packet = MaplePacketCreator.spawnPlayerMapobject(chr);
        if (!chr.isHidden()) {
            this.broadcastMessage(chr, packet, false);
            if (chr.isGM() && this.speedRunStart > 0L) {
                this.endSpeedRun();
                this.broadcastMessage(MaplePacketCreator.serverNotice(5, "The speed run has ended."));
            }
        }
        else {
            this.broadcastGMMessage(chr, packet, false);
        }
        if (!chr.isClone()) {
            if (!this.onFirstUserEnter.equals((Object)"") && this.getCharactersSize() == 1) {
                MapScriptMethods.startScript_FirstUser(chr.getClient(), this.onFirstUserEnter);
            }
            this.sendObjectPlacement(chr);
            chr.getClient().sendPacket(MaplePacketCreator.spawnPlayerMapobject(chr));
            if (!this.onUserEnter.equals((Object)"")) {
                MapScriptMethods.startScript_User(chr.getClient(), this.onUserEnter);
            }
            switch (this.mapid) {
                case 109030001:
                case 109040000:
                case 109060001:
                case 109080000:
                case 109080010: {
                    chr.getClient().sendPacket(MaplePacketCreator.showEventInstructions());
                    break;
                }
                case 809000101:
                case 809000201: {
                    chr.getClient().sendPacket(MaplePacketCreator.showEquipEffect());
                    break;
                }
                case 910000000: {
                    MapleCharacter victim = null;
                    chr.getClient().getChannelServer();
                    if (!ChannelServer.clones.isEmpty()) {
                        chr.getClient().getChannelServer();
                        ArrayList<离线人偶> clone = ChannelServer.clones;
                        if (!chr.isGM()) {
                            for (final 离线人偶 jr : clone) {
                                if (chr.getClient().getAccID() == jr.AccId) {
                                    chr.getClient().getChannelServer();
                                    ChannelServer.clones.remove((Object)jr);
                                    break;
                                }
                            }
                        }
                        final int nowchannel = chr.getClient().getChannelServer().getChannel();
                        chr.getClient().getChannelServer();
                        clone = ChannelServer.clones;
                        for (final 离线人偶 jr2 : clone) {
                            if (jr2.channel == nowchannel) {
                                victim = MapleCharacter.loadCharFromDB(jr2.charId, new MapleClient(null, null, (Channel)new MockIOSession()), true);
                                if (victim == null) {
                                    continue;
                                }
                                final int ch = Find.findChannel(victim.getName());
                                if (ch != -1) {
                                    continue;
                                }
                                if (jr2.chairId > 0) {
                                    victim.setChair(jr2.chairId);
                                }
                                victim.setPosition(new Point(jr2.x, jr2.y));
                                victim.setStance(Randomizer.rand(4, 5));
                                chr.getClient().getSession().write((Object)MaplePacketCreator.spawnPlayerMapobject(victim));
                            }
                        }
                        break;
                    }
                    break;
                }
            }
        }
        for (final MaplePet pet : chr.getSummonedPets()) {
            if (pet.getSummoned()) {
                this.broadcastMessage(chr, PetPacket.showPet(chr, pet, false, false), false);
                chr.getClient().sendPacket(PetPacket.showPet(chr, pet, false, false));
                chr.getClient().sendPacket(PetPacket.petStatUpdate(chr));
                chr.getClient().sendPacket(PetPacket.loadExceptionList(chr, pet));
            }
        }
        if (chr.getParty() != null && !chr.isClone()) {
            chr.silentPartyUpdate();
            chr.getClient().sendPacket(MaplePacketCreator.updateParty(chr.getClient().getChannel(), chr.getParty(), PartyOperation.SILENT_UPDATE, null));
            chr.updatePartyMemberHP();
            chr.receivePartyMemberHP();
        }
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"地图名称开关")) == 0) {
            chr.startMapEffect(chr.getMap().getMapName(), 5120023, 5000);
        }
        final MapleStatEffect stat = chr.getStatForBuff(MapleBuffStat.SUMMON);
        if (stat != null && !chr.isClone()) {
            final MapleSummon summon = (MapleSummon)chr.getSummons().get((Object)Integer.valueOf(stat.getSourceId()));
            summon.setPosition(chr.getPosition());
            try {
                summon.setFh(this.getFootholds().findBelow(chr.getPosition()).getId());
            }
            catch (NullPointerException e) {
                summon.setFh(0);
            }
            chr.addVisibleMapObject((MapleMapObject)summon);
            this.spawnSummon(summon);
        }
        if (this.mapEffect != null) {
            this.mapEffect.sendStartData(chr.getClient());
        }
        if (this.timeLimit > 0 && this.getForcedReturnMap() != null && !chr.isClone()) {
            chr.startMapTimeLimitTask(this.timeLimit, this.getForcedReturnMap());
        }
        if (chr.getBuffedValue(MapleBuffStat.MONSTER_RIDING) != null && FieldLimitType.Mount.check(this.fieldLimit)) {
            chr.cancelBuffStats(MapleBuffStat.MONSTER_RIDING);
        }
        if (this.hasBoat() == 2) {
            chr.getClient().sendPacket(MaplePacketCreator.boatPacket(true));
        }
        else if (this.hasBoat() == 1 && (chr.getMapId() != 200090000 || chr.getMapId() != 200090010)) {
            chr.getClient().sendPacket(MaplePacketCreator.boatPacket(false));
        }
        if (!chr.isClone()) {
            if (chr.getEventInstance() != null && chr.getEventInstance().isTimerStarted() && !chr.isClone()) {
                chr.getClient().sendPacket(MaplePacketCreator.getClock((int)(chr.getEventInstance().getTimeLeft() / 1000L)));
            }
            if (this.hasClock()) {
                final Calendar cal = Calendar.getInstance();
                chr.getClient().sendPacket(MaplePacketCreator.getClockTime(cal.get(11), cal.get(12), cal.get(13)));
            }
            if (chr.getCarnivalParty() != null && chr.getEventInstance() != null) {
                chr.getEventInstance().onMapLoad(chr);
            }
            MapleEvent.mapLoad(chr, (int)this.channel);
            if (this.getSquadBegin() != null && this.getSquadBegin().getTimeLeft() > 0L && this.getSquadBegin().getStatus() == 1) {
                chr.getClient().sendPacket(MaplePacketCreator.getClock((int)(this.getSquadBegin().getTimeLeft() / 1000L)));
            }
            if (this.mapid != 280030000 && this.mapid != 240060000 && this.mapid != 240060100 && this.mapid != 240060200 && this.mapid != 270050100 && this.mapid != 551030200 && this.mapid / 1000 != 105100 && this.mapid / 100 != 8020003 && this.mapid / 100 != 8020008) {
                final MapleSquad sqd = this.getSquadByMap();
                if (!this.squadTimer && sqd != null && chr.getName().equals((Object)sqd.getLeaderName()) && !chr.isClone()) {
                    this.doShrine(false);
                    this.squadTimer = true;
                }
            }
            for (final WeakReference<MapleCharacter> chrz : chr.getClones()) {
                if (chrz.get() != null) {
                    ((MapleCharacter)chrz.get()).setPosition(new Point(chr.getPosition()));
                    ((MapleCharacter)chrz.get()).setMap(this);
                    this.addPlayer((MapleCharacter)chrz.get());
                }
            }
            if (this.mapid == 914000000) {
                chr.getClient().sendPacket(MaplePacketCreator.temporaryStats_Aran());
            }
            else if (this.mapid == 105100300 && chr.getLevel() >= 91) {
                chr.getClient().sendPacket(MaplePacketCreator.temporaryStats_Balrog(chr));
            }
            else if (this.mapid == 140090000 || this.mapid == 105100301 || this.mapid == 105100100) {
                chr.getClient().sendPacket(MaplePacketCreator.temporaryStats_Reset());
            }
        }
        if (this.permanentWeather > 0) {
            chr.getClient().sendPacket(MaplePacketCreator.startMapEffect("", this.permanentWeather, false));
        }
        if (this.getPlatforms().size() > 0) {
            chr.getClient().sendPacket(MaplePacketCreator.getMovingPlatforms(this));
        }
        if (this.environment.size() > 0) {
            chr.getClient().sendPacket(MaplePacketCreator.getUpdateEnvironment(this));
        }
    }
    
    public int getNumItems() {
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.ITEM)).readLock().lock();
        try {
            return ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.ITEM)).size();
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.ITEM)).readLock().unlock();
        }
    }
    
    public int getNumMonsters() {
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.MONSTER)).readLock().lock();
        try {
            return ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.MONSTER)).size();
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.MONSTER)).readLock().unlock();
        }
    }
    
    public void doShrine(final boolean spawned) {
        if (this.squadSchedule != null) {
            this.cancelSquadSchedule();
        }
        final int mode = (this.mapid == 280030000) ? 1 : ((this.mapid == 280030001) ? 2 : ((this.mapid == 240060200 || this.mapid == 240060201) ? 3 : 0));
        final MapleSquad sqd = this.getSquadByMap();
        final EventManager em = this.getEMByMap();
        if (sqd != null && em != null && this.getCharactersSize() > 0) {
            final String leaderName = sqd.getLeaderName();
            final String state = em.getProperty("state");
            MapleMap returnMapa = this.getForcedReturnMap();
            if (returnMapa == null || returnMapa.getId() == this.mapid) {
                returnMapa = this.getReturnMap();
            }
            if (mode == 1) {
                this.broadcastMessage(MaplePacketCreator.showZakumShrine(spawned, 5));
            }
            else if (mode == 2) {
                this.broadcastMessage(MaplePacketCreator.showChaosZakumShrine(spawned, 5));
            }
            else if (mode == 3) {
                this.broadcastMessage(MaplePacketCreator.showChaosHorntailShrine(spawned, 5));
            }
            else {
                this.broadcastMessage(MaplePacketCreator.showHorntailShrine(spawned, 5));
            }
            if (mode == 1 || spawned) {
                this.broadcastMessage(MaplePacketCreator.getClock(300));
            }
            final MapleMap returnMapz = returnMapa;
            Runnable run;
            if (!spawned) {
                final List<MapleMonster> monsterz = this.getAllMonstersThreadsafe();
                final List<Integer> monsteridz = new ArrayList<Integer>();
                for (final MapleMapObject m : monsterz) {
                    monsteridz.add(Integer.valueOf(m.getObjectId()));
                }
                run = new Runnable() {
                    @Override
                    public void run() {
                        final MapleSquad sqnow = MapleMap.this.getSquadByMap();
                        if (MapleMap.this.getCharactersSize() > 0 && MapleMap.this.getNumMonsters() == monsterz.size() && sqnow != null && sqnow.getStatus() == 2 && sqnow.getLeaderName().equals((Object)leaderName) && MapleMap.this.getEMByMap().getProperty("state").equals((Object)state)) {
                            boolean passed = monsterz.isEmpty();
                            for (final MapleMapObject m : MapleMap.this.getAllMonstersThreadsafe()) {
                                final Iterator<Integer> iterator2 = monsteridz.iterator();
                                while (iterator2.hasNext()) {
                                    final int i = (int)Integer.valueOf(iterator2.next());
                                    if (m.getObjectId() == i) {
                                        passed = true;
                                        break;
                                    }
                                }
                                if (passed) {
                                    break;
                                }
                            }
                            if (passed) {
                                byte[] packet;
                                if (mode == 1) {
                                    packet = MaplePacketCreator.showZakumShrine(spawned, 0);
                                }
                                else if (mode == 2) {
                                    packet = MaplePacketCreator.showChaosZakumShrine(spawned, 0);
                                }
                                else {
                                    packet = MaplePacketCreator.showHorntailShrine(spawned, 0);
                                }
                                for (final MapleCharacter chr : MapleMap.this.getCharactersThreadsafe()) {
                                    chr.getClient().sendPacket(packet);
                                    chr.changeMap(returnMapz, returnMapz.getPortal(0));
                                }
                                MapleMap.this.checkStates("");
                                MapleMap.this.resetFully();
                            }
                        }
                    }
                };
            }
            else {
                run = new Runnable() {
                    @Override
                    public void run() {
                        final MapleSquad sqnow = MapleMap.this.getSquadByMap();
                        if (MapleMap.this.getCharactersSize() > 0 && sqnow != null && sqnow.getStatus() == 2 && sqnow.getLeaderName().equals((Object)leaderName) && MapleMap.this.getEMByMap().getProperty("state").equals((Object)state)) {
                            byte[] packet;
                            if (mode == 1) {
                                packet = MaplePacketCreator.showZakumShrine(spawned, 0);
                            }
                            else if (mode == 2) {
                                packet = MaplePacketCreator.showChaosZakumShrine(spawned, 0);
                            }
                            else {
                                packet = MaplePacketCreator.showHorntailShrine(spawned, 0);
                            }
                            for (final MapleCharacter chr : MapleMap.this.getCharactersThreadsafe()) {
                                chr.getClient().sendPacket(packet);
                                chr.changeMap(returnMapz, returnMapz.getPortal(0));
                            }
                            MapleMap.this.checkStates("");
                            MapleMap.this.resetFully();
                        }
                    }
                };
            }
            this.squadSchedule = MapTimer.getInstance().schedule(run, 300000L);
        }
    }
    
    public final MapleSquad getSquadByMap() {
        MapleSquadType zz = null;
        switch (this.mapid) {
            case 105100300: {
                zz = MapleSquadType.bossbalrog;
                break;
            }
            case 280030000: {
                zz = MapleSquadType.zak;
                break;
            }
            case 280030001: {
                zz = MapleSquadType.chaoszak;
                break;
            }
            case 240060000:
            case 240060100:
            case 240060200: {
                zz = MapleSquadType.horntail;
                break;
            }
            case 240060201: {
                zz = MapleSquadType.chaosht;
                break;
            }
            case 270050100: {
                zz = MapleSquadType.pinkbean;
                break;
            }
            case 802000111: {
                zz = MapleSquadType.nmm_squad;
                break;
            }
            case 802000211: {
                zz = MapleSquadType.vergamot;
                break;
            }
            case 802000411: {
                zz = MapleSquadType.dunas;
                break;
            }
            case 802000611: {
                zz = MapleSquadType.nibergen_squad;
                break;
            }
            case 802000711: {
                zz = MapleSquadType.dunas2;
                break;
            }
            case 802000801:
            case 802000802:
            case 802000803: {
                zz = MapleSquadType.core_blaze;
                break;
            }
            case 802000821:
            case 802000823: {
                zz = MapleSquadType.aufheben;
                break;
            }
            case 211070100:
            case 211070101:
            case 211070110: {
                zz = MapleSquadType.vonleon;
                break;
            }
            case 551030200: {
                zz = MapleSquadType.scartar;
                break;
            }
            case 271040100: {
                zz = MapleSquadType.cygnus;
                break;
            }
            default: {
                return null;
            }
        }
        return ChannelServer.getInstance((int)this.channel).getMapleSquad(zz);
    }
    
    public final MapleSquad getSquadBegin() {
        if (this.squad != null) {
            return ChannelServer.getInstance((int)this.channel).getMapleSquad(this.squad);
        }
        return null;
    }
    
    public final EventManager getEMByMap() {
        String em = null;
        switch (this.mapid) {
            case 105100300: {
                em = "BossBalrog";
                break;
            }
            case 280030000: {
                em = "ZakumBattle";
                break;
            }
            case 240060000:
            case 240060100:
            case 240060200: {
                em = "HorntailBattle";
                break;
            }
            case 280030001: {
                em = "ChaosZakum";
                break;
            }
            case 240060201: {
                em = "ChaosHorntail";
                break;
            }
            case 270050100: {
                em = "PinkBeanBattle";
                break;
            }
            case 802000111: {
                em = "NamelessMagicMonster";
                break;
            }
            case 802000211: {
                em = "Vergamot";
                break;
            }
            case 802000311: {
                em = "tokyo_2095";
                break;
            }
            case 802000411: {
                em = "Dunas";
                break;
            }
            case 802000611: {
                em = "Nibergen";
                break;
            }
            case 802000711: {
                em = "Dunas2";
                break;
            }
            case 802000801:
            case 802000802:
            case 802000803: {
                em = "CoreBlaze";
                break;
            }
            case 802000821:
            case 802000823: {
                em = "Aufhaven";
                break;
            }
            case 211070100:
            case 211070101:
            case 211070110: {
                em = "VonLeonBattle";
                break;
            }
            case 551030200: {
                em = "ScarTarBattle";
                break;
            }
            case 271040100: {
                em = "CygnusBattle";
                break;
            }
            case 262030300: {
                em = "HillaBattle";
                break;
            }
            case 262031300: {
                em = "DarkHillaBattle";
                break;
            }
            case 272020110:
            case 272030400: {
                em = "ArkariumBattle";
                break;
            }
            case 955000100:
            case 955000200:
            case 955000300: {
                em = "AswanOffSeason";
                break;
            }
            case 280030100: {
                em = "ZakumBattle";
                break;
            }
            case 272020200: {
                em = "Akayile";
                break;
            }
            case 689013000: {
                em = "PinkZakum";
                break;
            }
            case 703200400: {
                em = "0AllBoss";
                break;
            }
            default: {
                return null;
            }
        }
        return ChannelServer.getInstance((int)this.channel).getEventSM().getEventManager(em);
    }
    
    public void broadcastNONGMMessage(final MapleCharacter source, final byte[] packet, final boolean repeatToSource) {
        this.broadcastNONGMMessage(repeatToSource ? null : source, packet);
    }
    
    private void broadcastNONGMMessage(final MapleCharacter source, final byte[] packet) {
        this.charactersLock.readLock().lock();
        try {
            if (source == null) {
                for (final MapleCharacter chr : this.characters) {
                    if (!chr.isStaff()) {
                        chr.getClient().getSession().writeAndFlush((Object)packet);
                    }
                }
            }
            else {
                for (final MapleCharacter chr : this.characters) {
                    if (chr != source && chr.getGMLevel() < 3) {
                        chr.getClient().getSession().writeAndFlush((Object)packet);
                    }
                }
            }
        }
        finally {
            this.charactersLock.readLock().unlock();
        }
    }
    
    public final void broadcastMessage(final byte[] packet) {
        this.broadcastMessage(null, packet, Double.POSITIVE_INFINITY, null);
    }
    
    public final void broadcastMessage(final MapleCharacter source, final byte[] packet, final boolean repeatToSource) {
        this.broadcastMessage(repeatToSource ? null : source, packet, Double.POSITIVE_INFINITY, source.getPosition());
    }
    
    public final int playerCount() {
        final List<MapleMapObject> players = this.getMapObjectsInRange(new Point(0, 0), Double.POSITIVE_INFINITY, Arrays.asList(MapleMapObjectType.PLAYER));
        return players.size();
    }
    
    public final int mobCount() {
        final List<MapleMapObject> mobsCount = this.getMapObjectsInRange(new Point(0, 0), Double.POSITIVE_INFINITY, Arrays.asList(MapleMapObjectType.MONSTER));
        return mobsCount.size();
    }
    
    public final void broadcastMessage(final byte[] packet, final Point rangedFrom) {
        this.broadcastMessage(null, packet, (double)GameConstants.maxViewRangeSq(), rangedFrom);
    }
    
    public final void broadcastMessage(final MapleCharacter source, final byte[] packet, final Point rangedFrom) {
        this.broadcastMessage(source, packet, (double)GameConstants.maxViewRangeSq(), rangedFrom);
    }
    
    private void broadcastMessage(final MapleCharacter source, final byte[] packet, final double rangeSq, final Point rangedFrom) {
        this.charactersLock.readLock().lock();
        try {
            for (final MapleCharacter chr : this.characters) {
                if (chr != source) {
                    if (rangeSq < Double.POSITIVE_INFINITY) {
                        if (rangedFrom.distanceSq((Point2D)chr.getPosition()) > rangeSq) {
                            continue;
                        }
                        chr.getClient().sendPacket(packet);
                    }
                    else {
                        chr.getClient().sendPacket(packet);
                    }
                }
            }
        }
        finally {
            this.charactersLock.readLock().unlock();
        }
    }
    
    private void sendObjectPlacement(final MapleCharacter c) {
        if (c == null || c.isClone()) {
            return;
        }
        for (final MapleMapObject o : this.getAllMonstersThreadsafe()) {
            this.updateMonsterController((MapleMonster)o);
        }
        for (final MapleMapObject o : this.getMapObjectsInRange(c.getPosition(), (double)GameConstants.maxViewRangeSq(), GameConstants.rangedMapobjectTypes)) {
            if (o.getType() == MapleMapObjectType.REACTOR && !((MapleReactor)o).isAlive()) {
                continue;
            }
            o.sendSpawnData(c.getClient());
            c.addVisibleMapObject(o);
        }
    }
    
    public final List<MapleMapObject> getMapObjectsInRange(final Point from, final double rangeSq) {
        final List<MapleMapObject> ret = new ArrayList<MapleMapObject>();
        for (final MapleMapObjectType type : MapleMapObjectType.values()) {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)type)).readLock().lock();
            try {
                for (final MapleMapObject mmo : ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)type)).values()) {
                    if (from.distanceSq((Point2D)mmo.getPosition()) <= rangeSq) {
                        ret.add(mmo);
                    }
                }
            }
            finally {
                ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)type)).readLock().unlock();
            }
        }
        return ret;
    }
    
    public List<MapleMapObject> getItemsInRange(final Point from, final double rangeSq) {
        return this.getMapObjectsInRange(from, rangeSq, Arrays.asList(MapleMapObjectType.ITEM));
    }
    
    public final List<MapleMapObject> getMapObjectsInRange(final Point from, final double rangeSq, final List<MapleMapObjectType> MapObject_types) {
        final List<MapleMapObject> ret = new ArrayList<MapleMapObject>();
        for (final MapleMapObjectType type : MapObject_types) {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)type)).readLock().lock();
            try {
                for (final MapleMapObject mmo : ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)type)).values()) {
                    if (from.distanceSq((Point2D)mmo.getPosition()) <= rangeSq) {
                        ret.add(mmo);
                    }
                }
            }
            finally {
                ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)type)).readLock().unlock();
            }
        }
        return ret;
    }
    
    public final List<MapleMapObject> getMapObjectsInRect(final Rectangle box, final List<MapleMapObjectType> MapObject_types) {
        final List<MapleMapObject> ret = new ArrayList<MapleMapObject>();
        for (final MapleMapObjectType type : MapObject_types) {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)type)).readLock().lock();
            try {
                for (final MapleMapObject mmo : ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)type)).values()) {
                    if (box.contains(mmo.getPosition())) {
                        ret.add(mmo);
                    }
                }
            }
            finally {
                ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)type)).readLock().unlock();
            }
        }
        return ret;
    }
    
    public List<MapleCharacter> getAllPlayersThreadsafe() {
        final List<MapleCharacter> ret = new LinkedList<MapleCharacter>();
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.PLAYER)).readLock().lock();
        try {
            for (final MapleMapObject chr : ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.PLAYER)).values()) {
                ret.add((MapleCharacter)chr);
            }
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.PLAYER)).readLock().unlock();
        }
        return ret;
    }
    
    public final List<MapleCharacter> getPlayersInRectThreadsafe(final Rectangle box, final List<MapleCharacter> chrList) {
        final List<MapleCharacter> character = new LinkedList<MapleCharacter>();
        this.charactersLock.readLock().lock();
        try {
            for (final MapleCharacter a : this.characters) {
                if (chrList.contains((Object)a) && box.contains(a.getPosition())) {
                    character.add(a);
                }
            }
        }
        finally {
            this.charactersLock.readLock().unlock();
        }
        return character;
    }
    
    public final void addPortal(final MaplePortal myPortal) {
        this.portals.put(Integer.valueOf(myPortal.getId()), myPortal);
    }
    
    public final MaplePortal getPortal(final String portalname) {
        for (final MaplePortal port : this.portals.values()) {
            if (port.getName().equals((Object)portalname)) {
                return port;
            }
        }
        return null;
    }
    
    public final MaplePortal getPortal(final int portalid) {
        return (MaplePortal)this.portals.get((Object)Integer.valueOf(portalid));
    }
    
    public final void resetPortals() {
        for (final MaplePortal port : this.portals.values()) {
            port.setPortalState(true);
        }
    }
    
    public final void setFootholds(final MapleFootholdTree footholds) {
        this.footholds = footholds;
    }
    
    public final MapleFootholdTree getFootholds() {
        return this.footholds;
    }
    
    public final void loadMonsterRate(final boolean first) {
        final int spawnSize = this.monsterSpawn.size();
        this.maxRegularSpawn = Math.round((float)spawnSize * this.monsterRate);
        if (this.maxRegularSpawn < 2) {
            this.maxRegularSpawn = 2;
        }
        else if (this.maxRegularSpawn > spawnSize) {
            this.maxRegularSpawn = spawnSize - spawnSize / 15;
        }
        if (this.fixedMob > 0) {
            this.maxRegularSpawn = this.fixedMob;
        }
        final Collection<Spawns> newSpawn = new LinkedList<Spawns>();
        final Collection<Spawns> newBossSpawn = new LinkedList<Spawns>();
        for (final Spawns s : this.monsterSpawn) {
            if (s.getCarnivalTeam() >= 2) {
                continue;
            }
            if (s.getMonster().getStats().isBoss()) {
                newBossSpawn.add(s);
            }
            else {
                newSpawn.add(s);
            }
        }
        this.monsterSpawn.clear();
        this.monsterSpawn.addAll((Collection<? extends Spawns>)newBossSpawn);
        this.monsterSpawn.addAll((Collection<? extends Spawns>)newSpawn);
        if (first && spawnSize > 0) {
            this.lastSpawnTime = System.currentTimeMillis();
            if (GameConstants.isForceRespawn(this.mapid)) {
                this.createMobInterval = 1000;
            }
        }
    }
    
    public final SpawnPoint addMonsterSpawn(final MapleMonster monster, final int mobTime, final byte carnivalTeam, final String msg) {
        final Point calcPointBelow;
        final Point newpos = calcPointBelow = this.calcPointBelow(monster.getPosition());
        --calcPointBelow.y;
        final SpawnPoint sp = new SpawnPoint(monster, newpos, mobTime, carnivalTeam, msg);
        if (carnivalTeam > -1) {
            this.monsterSpawn.add(0, sp);
        }
        else {
            this.monsterSpawn.add(sp);
        }
        return sp;
    }
    
    public final void addAreaMonsterSpawn(final MapleMonster monster, Point pos1, Point pos2, Point pos3, final int mobTime, final String msg) {
        pos1 = this.calcPointBelow(pos1);
        pos2 = this.calcPointBelow(pos2);
        pos3 = this.calcPointBelow(pos3);
        if (pos1 != null) {
            final Point point = pos1;
            --point.y;
        }
        if (pos2 != null) {
            final Point point2 = pos2;
            --point2.y;
        }
        if (pos3 != null) {
            final Point point3 = pos3;
            --point3.y;
        }
        if (pos1 == null && pos2 == null && pos3 == null) {
            System.err.println("警告: 地图 " + this.mapid + ", 怪物代码 " + monster.getId() + " 召喚失敗. (pos1 == null && pos2 == null && pos3 == null)");
            return;
        }
        if (pos1 != null) {
            if (pos2 == null) {
                pos2 = new Point(pos1);
            }
            if (pos3 == null) {
                pos3 = new Point(pos1);
            }
        }
        else if (pos2 != null) {
            if (pos1 == null) {
                pos1 = new Point(pos2);
            }
            if (pos3 == null) {
                pos3 = new Point(pos2);
            }
        }
        else if (pos3 != null) {
            if (pos1 == null) {
                pos1 = new Point(pos3);
            }
            if (pos2 == null) {
                pos2 = new Point(pos3);
            }
        }
        this.monsterSpawn.add(new SpawnPointAreaBoss(monster, pos1, pos2, pos3, mobTime, msg));
    }
    
    public final List<MapleCharacter> getCharacters() {
        return this.getCharactersThreadsafe();
    }
    
    public final List<MapleCharacter> getCharactersThreadsafe() {
        final List<MapleCharacter> chars = new ArrayList<MapleCharacter>();
        this.charactersLock.readLock().lock();
        try {
            for (final MapleCharacter mc : this.characters) {
                chars.add(mc);
            }
        }
        finally {
            this.charactersLock.readLock().unlock();
        }
        return chars;
    }
    
    public MapleCharacter getCharacterByName(final String id) {
        this.charactersLock.readLock().lock();
        try {
            for (final MapleCharacter mc : this.characters) {
                if (mc.getName().equalsIgnoreCase(id)) {
                    final MapleCharacter localMapleCharacter1 = mc;
                    return localMapleCharacter1;
                }
            }
        }
        finally {
            this.charactersLock.readLock().unlock();
        }
        return null;
    }
    
    public final MapleCharacter getCharacterById_InMap(final int id) {
        return this.getCharacterById(id);
    }
    
    public final MapleCharacter getCharacterById(final int id) {
        this.charactersLock.readLock().lock();
        try {
            for (final MapleCharacter mc : this.characters) {
                if (mc.getId() == id) {
                    return mc;
                }
            }
        }
        finally {
            this.charactersLock.readLock().unlock();
        }
        return null;
    }
    
    public final void updateMapObjectVisibility(final MapleCharacter chr, final MapleMapObject mo) {
        if (chr == null || chr.isClone()) {
            return;
        }
        if (!chr.isMapObjectVisible(mo)) {
            if (mo.getType() == MapleMapObjectType.SUMMON || mo.getPosition().distanceSq((Point2D)chr.getPosition()) <= (double)GameConstants.maxViewRangeSq()) {
                chr.addVisibleMapObject(mo);
                mo.sendSpawnData(chr.getClient());
            }
        }
        else if (mo.getType() != MapleMapObjectType.SUMMON && mo.getPosition().distanceSq((Point2D)chr.getPosition()) > (double)GameConstants.maxViewRangeSq()) {
            chr.removeVisibleMapObject(mo);
            mo.sendDestroyData(chr.getClient());
        }
    }
    
    public void moveMonster(final MapleMonster monster, final Point reportedPos) {
        monster.setPosition(reportedPos);
        this.charactersLock.readLock().lock();
        try {
            for (final MapleCharacter mc : this.characters) {
                this.updateMapObjectVisibility(mc, (MapleMapObject)monster);
            }
        }
        finally {
            this.charactersLock.readLock().unlock();
        }
    }
    
    public void movePlayer(final MapleCharacter player, final Point newPosition) {
        player.setPosition(newPosition);
        if (!player.isClone()) {
            try {
                final Collection<MapleMapObject> visibleObjects = player.getAndWriteLockVisibleMapObjects();
                final ArrayList<MapleMapObject> copy = new ArrayList<MapleMapObject>((Collection<? extends MapleMapObject>)visibleObjects);
                for (final MapleMapObject mo : copy) {
                    if (mo != null && this.getMapObject(mo.getObjectId(), mo.getType()) == mo) {
                        this.updateMapObjectVisibility(player, mo);
                    }
                    else {
                        if (mo == null) {
                            continue;
                        }
                        visibleObjects.remove((Object)mo);
                    }
                }
                for (final MapleMapObject mo2 : this.getMapObjectsInRange(player.getPosition(), (double)GameConstants.maxViewRangeSq())) {
                    if (mo2 != null && !player.isMapObjectVisible(mo2)) {
                        mo2.sendSpawnData(player.getClient());
                        visibleObjects.add(mo2);
                    }
                }
            }
            finally {
                player.unlockWriteVisibleMapObjects();
            }
        }
    }
    
    public MaplePortal findClosestSpawnpoint(final Point from) {
        MaplePortal closest = null;
        double shortestDistance = Double.POSITIVE_INFINITY;
        for (final MaplePortal portal : this.portals.values()) {
            final double distance = portal.getPosition().distanceSq((Point2D)from);
            if (portal.getType() >= 0 && portal.getType() <= 2 && distance < shortestDistance && portal.getTargetMapId() == 999999999) {
                closest = portal;
                shortestDistance = distance;
            }
        }
        return closest;
    }
    
    public MaplePortal findClosestPortal(final Point from) {
        MaplePortal closest = this.getPortal(0);
        double shortestDistance = Double.POSITIVE_INFINITY;
        for (final MaplePortal portal : this.portals.values()) {
            final double distance = portal.getPosition().distanceSq((Point2D)from);
            if (distance < shortestDistance) {
                closest = portal;
                shortestDistance = distance;
            }
        }
        return closest;
    }
    
    public String spawnDebug() {
        final StringBuilder sb = new StringBuilder("Mapobjects in map : ");
        sb.append(this.getMapObjectSize());
        sb.append(" spawnedMonstersOnMap: ");
        sb.append((Object)this.spawnedMonstersOnMap);
        sb.append(" spawnpoints: ");
        sb.append(this.monsterSpawn.size());
        sb.append(" maxRegularSpawn: ");
        sb.append(this.maxRegularSpawn);
        sb.append(" actual monsters: ");
        sb.append(this.getNumMonsters());
        return sb.toString();
    }
    
    public int characterSize() {
        return this.characters.size();
    }
    
    public final int getMapObjectSize() {
        return this.mapObjects.size() + this.getCharactersSize() - this.characters.size();
    }
    
    public final int getCharactersSize() {
        int ret = 0;
        this.charactersLock.readLock().lock();
        try {
            for (final MapleCharacter chr : this.characters) {
                if (!chr.isClone()) {
                    ++ret;
                }
            }
        }
        finally {
            this.charactersLock.readLock().unlock();
        }
        return ret;
    }
    
    public Collection<MaplePortal> getPortals() {
        return Collections.unmodifiableCollection((Collection<? extends MaplePortal>)this.portals.values());
    }
    
    public int getSpawnedMonstersOnMap() {
        return this.spawnedMonstersOnMap.get();
    }
    
    public final void spawnKite(final MapleKite Kite) {
        this.addMapObject((MapleMapObject)Kite);
        this.broadcastMessage(Kite.makeSpawnData());
        MapTimer.getInstance().schedule((Runnable)new Runnable() {
            @Override
            public void run() {
                MapleMap.this.broadcastMessage(Kite.makeDestroyData());
                MapleMap.this.removeMapObject((MapleMapObject)Kite);
            }
        }, 3600000L);
    }
    
    public void respawn(final boolean force) {
        this.lastSpawnTime = System.currentTimeMillis();
        if (force) {
            final int numShouldSpawn = this.monsterSpawn.size() * MapConstants.isMonsterSpawn(this) - this.spawnedMonstersOnMap.get();
            if (numShouldSpawn > 0) {
                int spawned = 0;
                for (final Spawns spawnPoint : this.monsterSpawn) {
                    spawnPoint.spawnMonster(this);
                    if (++spawned >= numShouldSpawn) {
                        break;
                    }
                }
            }
        }
        else {
            final int 地图最大怪物 = 300;
            final int defaultNum = (GameConstants.isForceRespawn(this.mapid) ? this.monsterSpawn.size() : this.maxRegularSpawn) - this.spawnedMonstersOnMap.get();
            final int numShouldSpawn2 = (地图最大怪物 > 0) ? Math.max(defaultNum, 地图最大怪物) : defaultNum;
            if (numShouldSpawn2 > 0) {
                int spawned2 = 0;
                final List<Spawns> randomSpawn = new ArrayList<Spawns>((Collection<? extends Spawns>)this.monsterSpawn);
                Collections.shuffle(randomSpawn);
                for (final Spawns spawnPoint2 : randomSpawn) {
                    if (spawnPoint2.shouldSpawn() || MapConstants.isForceRespawn(this.mapid)) {
                        spawnPoint2.spawnMonster(this);
                        ++spawned2;
                    }
                    if (spawned2 >= numShouldSpawn2 && !GameConstants.isCarnivalMaps(this.mapid)) {
                        break;
                    }
                }
            }
        }
    }
    
    public String getSnowballPortal() {
        final int[] teamss = new int[2];
        for (final MapleCharacter chr : this.getCharactersThreadsafe()) {
            if (chr.getPosition().y > -80) {
                final int[] array = teamss;
                final int n = 0;
                ++array[n];
            }
            else {
                final int[] array2 = teamss;
                final int n2 = 1;
                ++array2[n2];
            }
        }
        if (teamss[0] > teamss[1]) {
            return "st01";
        }
        return "st00";
    }
    
    public boolean isDisconnected(final int id) {
        return this.disconnectedClients.contains((Object)Integer.valueOf(id));
    }
    
    public void addDisconnected(final int id) {
        this.disconnectedClients.add(Integer.valueOf(id));
    }
    
    public void resetDisconnected() {
        this.disconnectedClients.clear();
    }
    
    public void startSpeedRun() {
        final MapleSquad squad = this.getSquadByMap();
        if (squad != null) {
            for (final MapleCharacter chr : this.getCharactersThreadsafe()) {
                if (chr.getName().equals((Object)squad.getLeaderName())) {
                    this.startSpeedRun(chr.getName());
                }
            }
        }
    }
    
    public void startSpeedRun(final String leader) {
        this.speedRunStart = System.currentTimeMillis();
        this.speedRunLeader = leader;
    }
    
    public void endSpeedRun() {
        this.speedRunStart = 0L;
        this.speedRunLeader = "";
    }
    
    public boolean getPapfight() {
        return this.PapfightStart;
    }
    
    public void Papfight() {
        this.PapfightStart = true;
    }
    
    public void EndPapfight() {
        this.PapfightStart = false;
    }
    
    public static int getMerchantMap(final MapleCharacter chr) {
        for (final ChannelServer cs : ChannelServer.getAllInstances()) {
            final int map = cs.getMerchantMap(chr);
            if (map != -1) {
                return map;
            }
        }
        return -1;
    }
    
    public static int getMerchantChannel(final MapleCharacter chr) {
        for (final ChannelServer cs : ChannelServer.getAllInstances()) {
            final int map = cs.getMerchantMap(chr);
            if (map != -1) {
                return cs.getChannel();
            }
        }
        return -1;
    }
    
    public void getRankAndAdd(final String leader, final String time, final SpeedRunType type, final long timz, final Collection<String> squad) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final StringBuilder rett = new StringBuilder();
            if (squad != null) {
                for (final String chr : squad) {
                    rett.append(chr);
                    rett.append(",");
                }
            }
            String z = rett.toString();
            if (squad != null) {
                z = z.substring(0, z.length() - 1);
            }
            final PreparedStatement ps = con.prepareStatement("INSERT INTO speedruns(`type`, `leader`, `timestring`, `time`, `members`) VALUES (?,?,?,?,?)");
            ps.setString(1, type.name());
            ps.setString(2, leader);
            ps.setString(3, time);
            ps.setLong(4, timz);
            ps.setString(5, z);
            ps.executeUpdate();
            ps.close();
            if (SpeedRunner.getInstance().getSpeedRunData(type) == null) {
                SpeedRunner.getInstance().addSpeedRunData(type, SpeedRunner.getInstance().addSpeedRunData(new StringBuilder("#rThese are the speedrun times for " + (Object)type + ".#k\r\n\r\n"), (Map<Integer, String>)new HashMap<Integer, String>(), z, leader, 1, time));
            }
            else {
                SpeedRunner.getInstance().removeSpeedRunData(type);
                SpeedRunner.getInstance().loadSpeedRunData(type);
            }
        }
        catch (Exception e) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
            e.printStackTrace();
        }
    }
    
    public long getSpeedRunStart() {
        return this.speedRunStart;
    }
    
    public final void disconnectAll(final MapleCharacter chr) {
        for (final MapleCharacter chrs : this.getCharactersThreadsafe()) {
            if (chrs.getGMLevel() < chr.getGMLevel()) {
                chrs.getClient().disconnect(true, false);
                chrs.getClient().getSession().close();
            }
        }
    }
    
    public final void disconnectAll() {
        for (final MapleCharacter chr : this.getCharactersThreadsafe()) {
            if (!chr.isGM()) {
                chr.getClient().disconnect(true, false);
                chr.getClient().getSession().close();
            }
        }
    }
    
    public List<MapleNPC> getAllNPCs() {
        return this.getAllNPCsThreadsafe();
    }
    
    public List<MapleNPC> getAllNPCsThreadsafe() {
        final ArrayList<MapleNPC> ret = new ArrayList<MapleNPC>();
        ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.NPC)).readLock().lock();
        try {
            for (final MapleMapObject mmo : ((LinkedHashMap<Integer, MapleMapObject>)this.mapObjects.get((Object)MapleMapObjectType.NPC)).values()) {
                ret.add((MapleNPC)mmo);
            }
        }
        finally {
            ((ReentrantReadWriteLock)this.mapObjectLocks.get((Object)MapleMapObjectType.NPC)).readLock().unlock();
        }
        return ret;
    }
    
    public final void resetNPCs() {
        this.removeNpc(-1);
    }
    
    public final void resetFully() {
        this.resetFully(true);
    }
    
    public final void resetFully(final boolean respawn) {
        this.killAllMonsters(false);
        this.reloadReactors();
        this.removeDrops();
        this.resetNPCs();
        this.resetSpawns();
        this.resetDisconnected();
        this.endSpeedRun();
        this.cancelSquadSchedule();
        this.resetPortals();
        this.environment.clear();
        if (this.MulungDojoLeaveTask != null && !this.MulungDojoLeaveTask.isCancelled()) {
            this.MulungDojoLeaveTask.cancel(true);
            this.MulungDojoLeaveTask = null;
        }
        if (respawn) {
            this.respawn(true);
        }
    }
    
    public void setMulungDojoLeaveTask(final ScheduledFuture<?> task) {
        this.MulungDojoLeaveTask = task;
    }
    
    public final void cancelSquadSchedule() {
        this.squadTimer = false;
        if (this.squadSchedule != null) {
            this.squadSchedule.cancel(false);
            this.squadSchedule = null;
        }
    }
    
    public final void removeDrops() {
        final List<MapleMapItem> items = this.getAllItemsThreadsafe();
        for (final MapleMapItem i : items) {
            i.expire(this);
        }
    }
    
    public final void resetAllSpawnPoint(final int mobid, final int mobTime) {
        final Collection<Spawns> sss = new LinkedList<Spawns>((Collection<? extends Spawns>)this.monsterSpawn);
        this.resetFully();
        this.monsterSpawn.clear();
        for (final Spawns s : sss) {
            final MapleMonster newMons = MapleLifeFactory.getMonster(mobid);
            final MapleMonster oldMons = s.getMonster();
            newMons.setCy(oldMons.getCy());
            newMons.setF(oldMons.getF());
            newMons.setFh(oldMons.getFh());
            newMons.setRx0(oldMons.getRx0());
            newMons.setRx1(oldMons.getRx1());
            newMons.setPosition(new Point(oldMons.getPosition()));
            newMons.setHide(oldMons.isHidden());
            this.addMonsterSpawn(newMons, mobTime, (byte)(-1), null);
        }
        this.loadMonsterRate(true);
    }
    
    public final void resetSpawns() {
        boolean changed = false;
        final Iterator<Spawns> sss = this.monsterSpawn.iterator();
        while (sss.hasNext()) {
            if (((Spawns)sss.next()).getCarnivalId() > -1) {
                sss.remove();
                changed = true;
            }
        }
        this.setSpawns(true);
        if (changed) {
            this.loadMonsterRate(true);
        }
    }
    
    public final boolean makeCarnivalSpawn(final int team, final MapleMonster newMons, final int num) {
        MonsterPoint ret = null;
        for (final MonsterPoint mp : this.nodes.getMonsterPoints()) {
            if (mp.team == team || mp.team == -1) {
                final Point calcPointBelow;
                final Point newpos = calcPointBelow = this.calcPointBelow(new Point(mp.x, mp.y));
                --calcPointBelow.y;
                boolean found = false;
                for (final Spawns s : this.monsterSpawn) {
                    if (s.getCarnivalId() > -1 && (mp.team == -1 || s.getCarnivalTeam() == mp.team) && s.getPosition().x == newpos.x && s.getPosition().y == newpos.y) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    ret = mp;
                    break;
                }
                continue;
            }
        }
        if (ret != null) {
            newMons.setCy(ret.cy);
            newMons.setF(0);
            newMons.setFh(ret.fh);
            newMons.setRx0(ret.x + 50);
            newMons.setRx1(ret.x - 50);
            newMons.setPosition(new Point(ret.x, ret.y));
            newMons.setHide(false);
            newMons.setCarnivalTeam((byte)team);
            final SpawnPoint sp = this.addMonsterSpawn(newMons, 1, (byte)team, null);
            sp.setCarnival(num);
        }
        return ret != null;
    }
    
    public final boolean makeCarnivalReactor(final int team, final int num) {
        final MapleReactor old = this.getReactorByName(team + "" + num);
        if (old != null && old.getState() < 5) {
            return false;
        }
        Point guardz = null;
        final List<MapleReactor> react = this.getAllReactorsThreadsafe();
        for (final Pair<Point, Integer> guard : this.nodes.getGuardians()) {
            if ((int)Integer.valueOf(guard.right) == team || (int)Integer.valueOf(guard.right) == -1) {
                boolean found = false;
                for (final MapleReactor r : react) {
                    if (r.getPosition().x == ((Point)guard.left).x && r.getPosition().y == ((Point)guard.left).y && r.getState() < 5) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    guardz = (Point)guard.left;
                    break;
                }
                continue;
            }
        }
        if (guardz != null) {
            final MapleReactorStats stats = MapleReactorFactory.getReactor(9980000 + team);
            final MapleReactor my = new MapleReactor(stats, 9980000 + team);
            stats.setFacingDirection((byte)0);
            my.setPosition(guardz);
            my.setState((byte)1);
            my.setDelay(0);
            my.setName(team + "" + num);
            this.spawnReactor(my);
            final MCSkill skil = MapleCarnivalFactory.getInstance().getGuardian(num);
            if (skil != null && skil.getMobSkill() != null) {
                for (final MapleMonster mons : this.getAllMonstersThreadsafe()) {
                    if (mons.getCarnivalTeam() == team) {
                        skil.getMobSkill().applyEffect(null, mons, false);
                    }
                }
            }
        }
        return guardz != null;
    }
    
    public final void blockAllPortal() {
        for (final MaplePortal p : this.portals.values()) {
            p.setPortalState(false);
        }
    }
    
    public boolean getAndSwitchTeam() {
        return this.getCharactersSize() % 2 != 0;
    }
    
    public void setSquad(final MapleSquadType s) {
        this.squad = s;
    }
    
    public int getChannel() {
        return this.channel;
    }
    
    public int getConsumeItemCoolTime() {
        return this.consumeItemCoolTime;
    }
    
    public void setConsumeItemCoolTime(final int ciit) {
        this.consumeItemCoolTime = ciit;
    }
    
    public void setPermanentWeather(final int pw) {
        this.permanentWeather = pw;
    }
    
    public int getPermanentWeather() {
        return this.permanentWeather;
    }
    
    public void checkStates(final String chr) {
        final MapleSquad sqd = this.getSquadByMap();
        final EventManager em = this.getEMByMap();
        final int size = this.getCharactersSize();
        if (sqd != null) {
            sqd.removeMember(chr);
            if (em != null) {
                if (sqd.getLeaderName().equals((Object)chr)) {
                    em.setProperty("leader", "false");
                }
                if (chr.equals((Object)"") || size == 0) {
                    sqd.clear();
                    em.setProperty("state", "0");
                    em.setProperty("leader", "true");
                    this.cancelSquadSchedule();
                }
            }
        }
        if (em != null && em.getProperty("state") != null && size == 0) {
            em.setProperty("state", "0");
            if (em.getProperty("leader") != null) {
                em.setProperty("leader", "true");
            }
        }
        if (this.speedRunStart > 0L && this.speedRunLeader.equalsIgnoreCase(chr)) {
            if (size > 0) {
                this.broadcastMessage(MaplePacketCreator.serverNotice(5, "由於遠征队队長離開了，所以遠征队任務失敗。"));
            }
            this.endSpeedRun();
        }
    }
    
    public void setNodes(final MapleNodes mn) {
        this.nodes = mn;
    }
    
    public final List<MaplePlatform> getPlatforms() {
        return this.nodes.getPlatforms();
    }
    
    public Collection<MapleNodeInfo> getNodes() {
        return this.nodes.getNodes();
    }
    
    public MapleNodeInfo getNode(final int index) {
        return this.nodes.getNode(index);
    }
    
    public final List<Rectangle> getAreas() {
        return this.nodes.getAreas();
    }
    
    public final Rectangle getArea(final int index) {
        return this.nodes.getArea(index);
    }
    
    public final void changeEnvironment(final String ms, final int type) {
        this.broadcastMessage(MaplePacketCreator.environmentChange(ms, type));
    }
    
    public final void toggleEnvironment(final String ms) {
        if (this.environment.containsKey((Object)ms)) {
            this.moveEnvironment(ms, ((int)Integer.valueOf(this.environment.get((Object)ms)) == 1) ? 2 : 1);
        }
        else {
            this.moveEnvironment(ms, 1);
        }
    }
    
    public final void moveEnvironment(final String ms, final int type) {
        this.broadcastMessage(MaplePacketCreator.environmentMove(ms, type));
        this.environment.put(ms, Integer.valueOf(type));
    }
    
    public final Map<String, Integer> getEnvironment() {
        return this.environment;
    }
    
    public final int getNumPlayersInArea(final int index) {
        int ret = 0;
        this.charactersLock.readLock().lock();
        try {
            final Iterator<MapleCharacter> ltr = this.characters.iterator();
            while (ltr.hasNext()) {
                if (this.getArea(index).contains(((MapleCharacter)ltr.next()).getPosition())) {
                    ++ret;
                }
            }
        }
        finally {
            this.charactersLock.readLock().unlock();
        }
        return ret;
    }
    
    public void broadcastGMMessage(final MapleCharacter source, final byte[] packet, final boolean repeatToSource) {
        this.broadcastGMMessage(repeatToSource ? null : source, packet, Double.POSITIVE_INFINITY, (source == null) ? new Point(0, 0) : source.getPosition(), (source == null) ? 1 : source.getGMLevel());
    }
    
    private void broadcastGMMessage(final MapleCharacter source, final byte[] packet, final double rangeSq, final Point rangedFrom, final int lowestLevel) {
        this.charactersLock.readLock().lock();
        try {
            for (final MapleCharacter chr : this.characters) {
                if (chr != source && chr.getGMLevel() >= lowestLevel) {
                    chr.getClient().sendPacket(packet);
                }
            }
        }
        finally {
            this.charactersLock.readLock().unlock();
        }
    }
    
    public void Killdpm(final boolean animate) {
        final List<MapleMapObject> monsters = this.getMapObjectsInRange(new Point(0, 0), Double.POSITIVE_INFINITY, Arrays.asList(MapleMapObjectType.MONSTER));
        for (final MapleMapObject monstermo : monsters) {
            final MapleMonster monster = (MapleMonster)monstermo;
            if (monster.getId() == 9001007) {
                this.spawnedMonstersOnMap.decrementAndGet();
                monster.setHp(0L);
                this.broadcastMessage(MobPacket.killMonster(monster.getObjectId(), (int)(animate ? 1 : 0)));
                this.removeMapObject((MapleMapObject)monster);
                monster.killed();
            }
        }
    }
    
    public final List<Pair<Integer, Integer>> getMobsToSpawn() {
        return this.nodes.getMobsToSpawn();
    }
    
    public final List<Integer> getSkillIds() {
        return this.nodes.getSkillIds();
    }
    
    public final boolean canSpawn() {
        return this.lastSpawnTime > 0L && this.isSpawns && this.lastSpawnTime + (long)this.createMobInterval < System.currentTimeMillis();
    }
    
    public final boolean canHurt() {
        if (this.lastHurtTime > 0L && this.lastHurtTime + (long)this.decHPInterval < System.currentTimeMillis()) {
            this.lastHurtTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }
    
    public short getTop() {
        return this.top;
    }
    
    public short getBottom() {
        return this.bottom;
    }
    
    public short getLeft() {
        return this.left;
    }
    
    public short getRight() {
        return this.right;
    }
    
    public void setTop(final int ii) {
        this.top = (short)ii;
    }
    
    public void setBottom(final int ii) {
        this.bottom = (short)ii;
    }
    
    public void setLeft(final int ii) {
        this.left = (short)ii;
    }
    
    public void setRight(final int ii) {
        this.right = (short)ii;
    }
    
    public final void 清怪() {
        this.killAllMonsters(true);
    }
    
    public final void removePlayer2(final MapleCharacter chr) {
        if (this.everlast) {
            this.returnEverLastItem(chr);
        }
        this.charactersLock.writeLock().lock();
        try {
            this.characters.remove((Object)chr);
        }
        finally {
            this.charactersLock.writeLock().unlock();
        }
        final int nowChannel = chr.getClient().getChannelServer().getChannel();
        chr.getClient().getChannelServer();
        ChannelServer.clones.add(new 离线人偶(chr.getClient().getAccID(), chr.getId(), chr.getPosition().x, chr.getPosition().y, (chr.getChair() > 0) ? chr.getChair() : 0, nowChannel));
        this.removeMapObject((MapleMapObject)chr);
        chr.checkFollow();
        this.broadcastMessage(MaplePacketCreator.removePlayerFromMap(chr.getId()));
        if (!chr.isClone()) {
            final List<MapleMonster> update = new ArrayList<MapleMonster>();
            final Iterator<MapleMonster> controlled = chr.getControlled().iterator();
            while (controlled.hasNext()) {
                final MapleMonster monster = (MapleMonster)controlled.next();
                if (monster != null) {
                    monster.setController(null);
                    monster.setControllerHasAggro(false);
                    monster.setControllerKnowsAboutAggro(false);
                    controlled.remove();
                    update.add(monster);
                }
            }
            for (final MapleMonster mons : update) {
                this.updateMonsterController(mons);
            }
            chr.leaveMap();
            this.checkStates(chr.getName());
            if (this.mapid == 109020001) {
                chr.canTalk(true);
            }
            for (final WeakReference<MapleCharacter> chrz : chr.getClones()) {
                if (chrz.get() != null) {
                    this.removePlayer((MapleCharacter)chrz.get());
                }
            }
        }
        chr.cancelEffectFromBuffStat(MapleBuffStat.PUPPET);
        boolean cancelSummons = false;
        for (final MapleSummon summon : chr.getSummons().values()) {
            if (summon.getMovementType() == SummonMovementType.STATIONARY || summon.getMovementType() == SummonMovementType.CIRCLE_STATIONARY || summon.getMovementType() == SummonMovementType.WALK_STATIONARY) {
                cancelSummons = true;
            }
            else {
                summon.setChangedMap(true);
                this.removeMapObject((MapleMapObject)summon);
            }
        }
        if (cancelSummons) {
            chr.cancelEffectFromBuffStat(MapleBuffStat.SUMMON);
        }
    }
    
    public final void removePlayer3(final MapleCharacter chr) {
        this.removeMapObject((MapleMapObject)chr);
        this.broadcastMessage(MaplePacketCreator.removePlayerFromMap(chr.getId()));
    }
    
    public final void removePlayer(final MapleCharacter chr) {
        if (this.everlast) {
            this.returnEverLastItem(chr);
        }
        this.charactersLock.writeLock().lock();
        try {
            this.characters.remove((Object)chr);
        }
        catch (Exception ex) {
            System.err.println("移除CHR失敗" + (Object)ex);
            FileoutputUtil.outputFileError("logs/移除CHR失敗.txt", (Throwable)ex);
        }
        finally {
            this.charactersLock.writeLock().unlock();
        }
        this.removeMapObject((MapleMapObject)chr);
        chr.checkFollow();
        if (chr.getMapId() == 220080001 && chr.getMap().playerCount() <= 0) {
            final MapleMap map = chr.getClient().getChannelServer().getMapFactory().getMap(220080000);
            map.EndPapfight();
            map.resetReactors();
        }
        this.broadcastMessage(MaplePacketCreator.removePlayerFromMap(chr.getId()));
        if (!chr.isClone()) {
            chr.leaveMap();
            this.checkStates(chr.getName());
            if (this.mapid == 109020001) {
                chr.canTalk(true);
            }
            for (final WeakReference<MapleCharacter> chrz : chr.getClones()) {
                if (chrz.get() != null) {
                    this.removePlayer((MapleCharacter)chrz.get());
                }
            }
        }
        chr.cancelEffectFromBuffStat(MapleBuffStat.PUPPET);
        boolean cancelSummons = false;
        for (final MapleSummon summon : chr.getSummons().values()) {
            if (summon.getMovementType() == SummonMovementType.STATIONARY || summon.getMovementType() == SummonMovementType.CIRCLE_STATIONARY || summon.getMovementType() == SummonMovementType.WALK_STATIONARY) {
                cancelSummons = true;
            }
            else {
                summon.setChangedMap(true);
                this.removeMapObject((MapleMapObject)summon);
            }
        }
        if (cancelSummons) {
            chr.cancelEffectFromBuffStat(MapleBuffStat.SUMMON);
        }
    }

    
    static {
        PointsGained = new HashMap<Integer, HashMap<String, Integer>>();
        MapleMap.特殊宠物吸取开关 = Boolean.parseBoolean(ServerProperties.getProperty("CongMS.特殊宠物吸取开关"));
        MapleMap.特殊宠物吸物开关 = Boolean.parseBoolean(ServerProperties.getProperty("CongMS.特殊宠物吸物开关"));
        MapleMap.特殊宠物吸金开关 = Boolean.parseBoolean(ServerProperties.getProperty("CongMS.特殊宠物吸金开关"));
        MapleMap.特殊宠物吸物无法使用地图开关 = Boolean.parseBoolean(ServerProperties.getProperty("CongMS.特殊宠物吸物无法使用地图开关"));
        MapleMap.特殊宠物吸物无法使用地图 = ServerProperties.getProperty("CongMS.特殊宠物吸物无法使用地图").split(",");
        MapleMap.持有物道具 = (int)Integer.valueOf(ServerProperties.getProperty("CongMS.持有物道具"));
    }
    
    private class ActivateItemReactor implements Runnable
    {
        private MapleMapItem mapitem;
        private MapleReactor reactor;
        private MapleClient c;
        
        public ActivateItemReactor(final MapleMapItem mapitem, final MapleReactor reactor, final MapleClient c) {
            this.mapitem = mapitem;
            this.reactor = reactor;
            this.c = c;
        }
        
        @Override
        public void run() {
            if (this.mapitem != null && this.mapitem == MapleMap.this.getMapObject(this.mapitem.getObjectId(), this.mapitem.getType())) {
                if (this.mapitem.isPickedUp()) {
                    this.reactor.setTimerActive(false);
                    return;
                }
                this.mapitem.expire(MapleMap.this);
                this.reactor.hitReactor(this.c);
                this.reactor.setTimerActive(false);
                if (this.reactor.getDelay() > 0) {
                    MapTimer.getInstance().schedule((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            reactor.forceHitReactor((byte)0);
                        }
                    }, (long)this.reactor.getDelay());
                }
            }
            else {
                this.reactor.setTimerActive(false);
            }
        }
        

    }
    
    private interface SpawnCondition
    {
        boolean canSpawn(final MapleCharacter p0);
    }
    
    private interface DelayedPacketCreation
    {
        void sendPackets(final MapleClient p0);
    }
}
