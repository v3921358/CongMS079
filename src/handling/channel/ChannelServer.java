package handling.channel;

import java.sql.PreparedStatement;
import java.sql.Connection;
import database.DatabaseConnection;
import handling.cashshop.CashShopServer;
import client.MapleClient;
import tools.FileoutputUtil;
import tools.CollectionUtil;
import handling.world.CheaterData;
import java.util.LinkedList;
import java.util.List;
import server.maps.MapleMapObject;
import java.util.Collections;
import constants.WorldConstants;
import gui.CongMS;
import client.MapleCharacter;
import java.util.Iterator;
import java.util.Map.Entry;
import handling.login.LoginServer;
import tools.MaplePacketCreator;
import constants.ServerConfig;
import server.ServerProperties;
import server.events.MapleJewel;
import server.events.MapleSnowball;
import server.events.MapleOla;
import server.events.MapleFitness;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.EnumMap;
import java.util.HashMap;
import tools.ConcurrentEnumMap;
import abc.离线人偶;
import java.util.ArrayList;
import server.events.MapleEvent;
import server.events.MapleEventType;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import server.life.PlayerNPC;
import server.shops.MaplePlayerShop;
import server.shops.HiredMerchant;
import server.MapleSquad;
import server.MapleSquad.MapleSquadType;
import java.util.Map;
import scripting.EventScriptManager;
import server.maps.MapleMapFactory;
import handling.mina.ServerConnection;
import configs.Config;
import java.io.Serializable;

public class ChannelServer implements Serializable
{
    public static boolean 离线挂机;
    public static long serverStartTime;
    private static Config cf;
    private short port;
    private static final short DEFAULT_PORT;
    public int channel;
    private int running_MerchantID;
    private int running_PlayerShopID;
    private String socket;
    private boolean shutdown;
    private boolean finishedShutdown;
    private boolean MegaphoneMuteState;
    private PlayerStorage players;
    private ServerConnection acceptor;
    private final MapleMapFactory mapFactory;
    private EventScriptManager eventSM;
    private static final Map<Integer, ChannelServer> instances;
    private final Map<MapleSquadType, MapleSquad> mapleSquads;
    private final Map<Integer, HiredMerchant> merchants;
    private final Map<Integer, MaplePlayerShop> playershops;
    private final Map<Integer, PlayerNPC> playerNPCs;
    private final ReentrantReadWriteLock merchLock;
    private final ReentrantReadWriteLock squadLock;
    private int eventmap;
    private final Map<MapleEventType, MapleEvent> events;
    public static ArrayList<离线人偶> clones;
    
    private ChannelServer(final int channel) {
        this.port = (short)Integer.parseInt(ChannelServer.cf.getConfig("CongMS.channel.port1"));
        this.running_MerchantID = 0;
        this.running_PlayerShopID = 0;
        this.shutdown = false;
        this.finishedShutdown = false;
        this.MegaphoneMuteState = false;
        this.mapleSquads = new ConcurrentEnumMap<MapleSquadType, MapleSquad>(MapleSquadType.class);
        this.merchants = new HashMap<Integer, HiredMerchant>();
        this.playershops = new HashMap<Integer, MaplePlayerShop>();
        this.playerNPCs = new HashMap<Integer, PlayerNPC>();
        this.merchLock = new ReentrantReadWriteLock();
        this.squadLock = new ReentrantReadWriteLock();
        this.eventmap = -1;
        this.events = new EnumMap<MapleEventType, MapleEvent>(MapleEventType.class);
        this.channel = channel;
        (this.mapFactory = new MapleMapFactory()).setChannel(channel);
    }
    
    public static Set<Integer> getAllChannels() {
        return new HashSet<Integer>((Collection<? extends Integer>)ChannelServer.instances.keySet());
    }
    
    public final void loadEvents() {
        if (!this.events.isEmpty()) {
            return;
        }
        this.events.put(MapleEventType.終極忍耐, new MapleFitness(this.channel, MapleEventType.終極忍耐.mapids));
        this.events.put(MapleEventType.爬繩子, new MapleOla(this.channel, MapleEventType.爬繩子.mapids));
        this.events.put(MapleEventType.滾雪球, new MapleSnowball(this.channel, MapleEventType.滾雪球.mapids));
        this.events.put(MapleEventType.尋寶, new MapleJewel(this.channel, MapleEventType.尋寶.mapids));
    }
    
    public final void setup() {
        this.setChannel(this.channel);
        try {
            this.eventSM = new EventScriptManager(this, ServerProperties.getProperty("CongMS.events").split(","));
            this.port = (short)(ServerProperties.getProperty("CongMS.channel.port", ChannelServer.DEFAULT_PORT) + this.channel - 1);
        }
        catch (Exception e) {
            throw new RuntimeException((Throwable)e);
        }
        this.socket = ServerConfig.IP + ":" + (int)this.port;
        this.players = new PlayerStorage(this.channel);
        this.loadEvents();
        (this.acceptor = new ServerConnection((int)this.port, 0, this.channel)).run();
        System.out.println("[正在启动] 频道" + this.getChannel() + "端口:" + (int)this.port + "");
        this.eventSM.init();
    }
    
    public final void shutdown() {
        if (this.finishedShutdown) {
            return;
        }
        this.broadcastPacket(MaplePacketCreator.serverNotice(0, "[频道" + this.getChannel() + "] 频道正在关闭"));
        this.shutdown = true;
        System.out.println("[频道" + this.getChannel() + "] 保存角色资料");
        System.out.println("[频道" + this.getChannel() + "] 解除端口绑定中");
        try {
            if (this.acceptor != null) {
                this.acceptor.close();
                System.out.println("[频道" + this.getChannel() + "] 解除端口成功");
            }
        }
        catch (Exception e) {
            System.out.println("[频道" + this.getChannel() + "] 解除端口失败");
        }
        ChannelServer.instances.remove((Object)Integer.valueOf(this.channel));
        LoginServer.removeChannel(this.channel);
        this.setFinishShutdown();
    }
    
    public void closeAllMerchants() {
        int ret = 0;
        final long Start = System.currentTimeMillis();
        this.merchLock.writeLock().lock();
        try {
            final Iterator<Map.Entry<Integer, HiredMerchant>> hmit = this.merchants.entrySet().iterator();
            while (hmit.hasNext()) {
                ((HiredMerchant)(hmit.next()).getValue()).closeShop(true, false);
                hmit.remove();
                ++ret;
            }
        }
        catch (Exception e) {
            System.out.println("关闭雇佣商店出现错误" + (Object)e);
        }
        finally {
            this.merchLock.writeLock().unlock();
        }
        System.out.println("频道 " + this.channel + " 共保存雇佣商店: " + ret + " | 耗时: " + (System.currentTimeMillis() - Start) + " 毫秒");
    }
    
    public final boolean hasFinishedShutdown() {
        return this.finishedShutdown;
    }
    
    public final MapleMapFactory getMapFactory() {
        return this.mapFactory;
    }
    
    public final void addPlayer(final MapleCharacter chr) {
        this.getPlayerStorage().registerPlayer(chr);
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"滚动公告开关")) <= 0) {
            chr.getClient().sendPacket(MaplePacketCreator.serverMessage(this.getServerMessage()));
        }
    }
    
    public final PlayerStorage getPlayerStorage() {
        if (this.players == null) {
            this.players = new PlayerStorage(this.channel);
        }
        return this.players;
    }
    
    public final void removePlayer(final MapleCharacter chr) {
        this.getPlayerStorage().deregisterPlayer(chr);
    }
    
    public final void removePlayer(final int idz, final String namez) {
        this.getPlayerStorage().deregisterPlayer(idz, namez);
    }
    
    public final String getServerMessage() {
        return WorldConstants.SCROLL_MESSAGE;
    }
    
    public final void setServerMessage(final String newMessage) {
        WorldConstants.SCROLL_MESSAGE = newMessage;
    }
    
    public final void broadcastPacket(final byte[] data) {
        this.getPlayerStorage().broadcastPacket(data);
    }
    
    public final void broadcastSmegaPacket(final byte[] data) {
        this.getPlayerStorage().broadcastSmegaPacket(data);
    }
    
    public final void broadcastGashponmegaPacket(final byte[] data) {
        this.getPlayerStorage().broadcastGashponmegaPacket(data);
    }
    
    public final void broadcastGMPacket(final byte[] data) {
        this.getPlayerStorage().broadcastGMPacket(data);
    }
    
    public final void broadcastGMPacket(final byte[] data, final boolean 吸怪) {
        this.getPlayerStorage().broadcastGMPacket(data, 吸怪);
    }
    
    public final int getExpRate() {
        return WorldConstants.EXP_RATE;
    }
    
    public final void setExpRate(final int expRate) {
        WorldConstants.EXP_RATE = expRate;
    }
    
    public final int getMesoRate() {
        return WorldConstants.MESO_RATE;
    }
    
    public final void setMesoRate(final int mesoRate) {
        WorldConstants.MESO_RATE = mesoRate;
    }
    
    public final int getDropRate() {
        return WorldConstants.DROP_RATE;
    }
    
    public final void setDropRate(final int dropRate) {
        WorldConstants.DROP_RATE = dropRate;
    }
    
    public final int getChannel() {
        return this.channel;
    }
    
    public final void setChannel(final int channel) {
        ChannelServer.instances.put(Integer.valueOf(channel), this);
        LoginServer.addChannel(channel);
    }
    
    public static final Collection<ChannelServer> getAllInstances() {
        return Collections.unmodifiableCollection((Collection<? extends ChannelServer>)ChannelServer.instances.values());
    }
    
    public final String getSocket() {
        return this.socket;
    }
    
    public final boolean isShutdown() {
        return this.shutdown;
    }
    
    public final int getLoadedMaps() {
        return this.mapFactory.getLoadedMaps();
    }
    
    public final EventScriptManager getEventSM() {
        return this.eventSM;
    }
    
    public final void reloadEvents() {
        this.eventSM.cancel();
        (this.eventSM = new EventScriptManager(this, ServerProperties.getProperty("CongMS.events").split(","))).init();
    }
    
    public Map<MapleSquadType, MapleSquad> getAllSquads() {
        return Collections.unmodifiableMap((Map<? extends MapleSquadType, ? extends MapleSquad>)this.mapleSquads);
    }
    
    public final MapleSquad getMapleSquad(final String type) {
        return this.getMapleSquad(MapleSquadType.valueOf(type.toLowerCase()));
    }
    
    public final MapleSquad getMapleSquad(final MapleSquadType type) {
        return (MapleSquad)this.mapleSquads.get((Object)type);
    }
    
    public final boolean addMapleSquad(final MapleSquad squad, final String type) {
        final MapleSquadType types = MapleSquadType.valueOf(type.toLowerCase());
        if (types != null && !this.mapleSquads.containsKey((Object)types)) {
            this.mapleSquads.put(types, squad);
            squad.scheduleRemoval();
            return true;
        }
        return false;
    }
    
    public final boolean removeMapleSquad(final MapleSquadType types) {
        if (types != null && this.mapleSquads.containsKey((Object)types)) {
            this.mapleSquads.remove((Object)types);
            return true;
        }
        return false;
    }
    
    public final int closeAllPlayerShop() {
        int ret = 0;
        this.merchLock.writeLock().lock();
        try {
            final Iterator<Entry<Integer, MaplePlayerShop>> playershops_ = this.playershops.entrySet().iterator();
            while (playershops_.hasNext()) {
                final MaplePlayerShop hm = (MaplePlayerShop)((Entry<Integer, MaplePlayerShop>)playershops_.next()).getValue();
                hm.closeShop(true, false);
                hm.getMap().removeMapObject((MapleMapObject)hm);
                playershops_.remove();
                ++ret;
            }
        }
        finally {
            this.merchLock.writeLock().unlock();
        }
        return ret;
    }
    
    public final int closeAllMerchant() {
        int ret = 0;
        this.merchLock.writeLock().lock();
        try {
            final Iterator<Entry<Integer, HiredMerchant>> merchants_ = this.merchants.entrySet().iterator();
            while (merchants_.hasNext()) {
                final HiredMerchant hm = (HiredMerchant)((Entry<Integer, HiredMerchant>)merchants_.next()).getValue();
                hm.closeShop(true, false);
                hm.getMap().removeMapObject((MapleMapObject)hm);
                merchants_.remove();
                ++ret;
            }
        }
        finally {
            this.merchLock.writeLock().unlock();
        }
        for (int i = 910000001; i <= 910000022; ++i) {
            for (final MapleMapObject mmo : this.mapFactory.getMap(i).getAllHiredMerchantsThreadsafe()) {
                ((HiredMerchant)mmo).closeShop(true, false);
                ++ret;
            }
        }
        return ret;
    }
    
    public final int addPlayerShop(final MaplePlayerShop PlayerShop) {
        this.merchLock.writeLock().lock();
        int runningmer = 0;
        try {
            runningmer = this.running_PlayerShopID;
            this.playershops.put(Integer.valueOf(this.running_PlayerShopID), PlayerShop);
            ++this.running_PlayerShopID;
        }
        finally {
            this.merchLock.writeLock().unlock();
        }
        return runningmer;
    }
    
    public final int addMerchant(final HiredMerchant hMerchant) {
        this.merchLock.writeLock().lock();
        int runningmer = 0;
        try {
            runningmer = this.running_MerchantID;
            this.merchants.put(Integer.valueOf(this.running_MerchantID), hMerchant);
            ++this.running_MerchantID;
        }
        finally {
            this.merchLock.writeLock().unlock();
        }
        return runningmer;
    }
    
    public final void removeMerchant(final HiredMerchant hMerchant) {
        this.merchLock.writeLock().lock();
        try {
            this.merchants.remove((Object)Integer.valueOf(hMerchant.getStoreId()));
        }
        finally {
            this.merchLock.writeLock().unlock();
        }
    }
    
    public final boolean containsMerchant(final int accid) {
        boolean contains = false;
        this.merchLock.readLock().lock();
        try {
            final Iterator itr = this.merchants.values().iterator();
            while (itr.hasNext()) {
                if (((HiredMerchant)itr.next()).getOwnerAccId() == accid) {
                    contains = true;
                    break;
                }
            }
        }
        finally {
            this.merchLock.readLock().unlock();
        }
        return contains;
    }
    
    public final List<HiredMerchant> searchMerchant(final int itemSearch) {
        final List<HiredMerchant> list = new LinkedList<HiredMerchant>();
        this.merchLock.readLock().lock();
        try {
            for (final HiredMerchant hm : this.merchants.values()) {
                if (hm.searchItem(itemSearch).size() > 0) {
                    list.add(hm);
                }
            }
        }
        finally {
            this.merchLock.readLock().unlock();
        }
        return list;
    }
    
    public final void toggleMegaphoneMuteState() {
        this.MegaphoneMuteState = !this.MegaphoneMuteState;
    }
    
    public final boolean getMegaphoneMuteState() {
        return this.MegaphoneMuteState;
    }
    
    public int getEvent() {
        return this.eventmap;
    }
    
    public final void setEvent(final int ze) {
        this.eventmap = ze;
    }
    
    public MapleEvent getEvent(final MapleEventType t) {
        return (MapleEvent)this.events.get((Object)t);
    }
    
    public final Collection<PlayerNPC> getAllPlayerNPC() {
        return this.playerNPCs.values();
    }
    
    public final PlayerNPC getPlayerNPC(final int id) {
        return (PlayerNPC)this.playerNPCs.get((Object)Integer.valueOf(id));
    }
    
    public final void addPlayerNPC(final PlayerNPC npc) {
        if (this.playerNPCs.containsKey((Object)Integer.valueOf(npc.getId()))) {
            this.removePlayerNPC(npc);
        }
        this.playerNPCs.put(Integer.valueOf(npc.getId()), npc);
        this.getMapFactory().getMap(npc.getMapId()).addMapObject((MapleMapObject)npc);
    }
    
    public final void removePlayerNPC(final PlayerNPC npc) {
        if (this.playerNPCs.containsKey((Object)Integer.valueOf(npc.getId()))) {
            this.playerNPCs.remove((Object)Integer.valueOf(npc.getId()));
            this.getMapFactory().getMap(npc.getMapId()).removeMapObject((MapleMapObject)npc);
        }
    }
    
    public final String getServerName() {
        return ServerConfig.SERVERNAME;
    }
    
    public final void setServerName(final String sn) {
        ServerConfig.SERVERNAME = sn;
    }
    
    public final int getPort() {
        return this.port;
    }
    
    public final void setPrepareShutdown() {
        this.shutdown = true;
        System.out.println("[頻道" + this.getChannel() + "] 准备关闭");
    }
    
    public final void setFinishShutdown() {
        this.finishedShutdown = true;
        System.out.println("[頻道" + this.getChannel() + "] 已經关闭完成.");
    }
    
    public final boolean isAdminOnly() {
        return WorldConstants.ADMIN_ONLY;
    }
    
    public static Map<Integer, Integer> getChannelLoad() {
        final Map<Integer, Integer> ret = new HashMap<Integer, Integer>();
        for (final ChannelServer cs : ChannelServer.instances.values()) {
            ret.put(Integer.valueOf(cs.getChannel()), Integer.valueOf(cs.getConnectedClients()));
        }
        return ret;
    }
    
    public int getConnectedClients() {
        final double bfb = (double)LoginServer.getRSGS() / 100.0 * (double)this.getPlayerStorage().getConnectedClients();
        return this.getPlayerStorage().getConnectedClients() + (int)Math.ceil(bfb);
    }
    
    public List<CheaterData> getCheaters() {
        final List<CheaterData> cheaters = this.getPlayerStorage().getCheaters();
        Collections.sort(cheaters);
        return CollectionUtil.copyFirst(cheaters, 20);
    }
    
    public void broadcastMessage(final byte[] message) {
        this.broadcastPacket(message);
    }
    
    public void broadcastSmega(final byte[] message) {
        this.broadcastSmegaPacket(message);
    }
    
    public void broadcastGashponmega(final byte[] message) {
        this.broadcastGashponmegaPacket(message);
    }
    
    public void broadcastGMMessage(final byte[] message, final boolean 吸怪) {
        this.broadcastGMPacket(message, 吸怪);
    }
    
    public void broadcastGMMessage(final byte[] message) {
        this.broadcastGMPacket(message);
    }
    
    public void saveAll() {
        int ppl = 0;
        int lastnumber = 0;
        final List<MapleCharacter> all = this.players.getAllCharactersThreadSafe();
        for (final MapleCharacter chr : all) {
            try {
                final int res = chr.saveToDB(false, false);
                if (ChannelServer.离线挂机) {
                    lastnumber = this.getLastOfflineTime2();
                }
                if (res == 1) {
                    ++ppl;
                }
                else {
                    System.out.println("[自动存档] 角色:" + chr.getName() + " 储存失败");
                }
            }
            catch (Exception e) {
                FileoutputUtil.logToFile("logs/saveAll存檔保存数据異常.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + chr.getClient().getSession().remoteAddress().toString().split(":")[0] + " 账号 " + chr.getClient().getAccountName() + " 账号ID " + chr.getClient().getAccID() + " 角色名 " + chr.getName() + " 角色ID " + chr.getId());
                FileoutputUtil.outError("logs/saveAll存檔保存数据異常.txt", (Throwable)e);
            }
        }
    }
    
    public boolean CanGMItem() {
        return WorldConstants.GMITEMS;
    }
    
    public final int getMerchantMap(final MapleCharacter chr) {
        final int ret = -1;
        for (int i = 910000001; i <= 910000022; ++i) {
            for (final MapleMapObject mmo : this.mapFactory.getMap(i).getAllHiredMerchantsThreadsafe()) {
                if (((HiredMerchant)mmo).getOwnerId() == chr.getId()) {
                    return this.mapFactory.getMap(i).getId();
                }
            }
        }
        return ret;
    }
    
    public static final int getChannelCount() {
        return ChannelServer.instances.size();
    }
    
    public static void forceRemovePlayerByAccId(final MapleClient client, final int accid) {
        for (final ChannelServer ch : getAllInstances()) {
            Collection<MapleCharacter> chrs = ch.getPlayerStorage().getAllCharactersThreadSafe();
            for (final MapleCharacter c : chrs) {
                if (c.getAccountID() == accid) {
                    try {
                        if (c.getClient() != null && c.getClient() != client) {
                            c.getClient().unLockDisconnect();
                        }
                    }
                    catch (Exception ex) {}
                    chrs = ch.getPlayerStorage().getAllCharactersThreadSafe();
                    if (!chrs.contains((Object)c)) {
                        continue;
                    }
                    ch.removePlayer(c);
                }
            }
        }
        try {
            final Collection<MapleCharacter> chrs2 = CashShopServer.getPlayerStorage().getAllCharactersThreadSafe();
            for (final MapleCharacter c2 : chrs2) {
                if (c2.getAccountID() == accid) {
                    try {
                        if (c2.getClient() == null || c2.getClient() == client) {
                            continue;
                        }
                        c2.getClient().unLockDisconnect();
                    }
                    catch (Exception ex2) {}
                }
            }
        }
        catch (Exception ex3) {}
    }
    
    public static final Set<Integer> getChannels() {
        return new HashSet<Integer>((Collection<? extends Integer>)ChannelServer.instances.keySet());
    }
    
    public static final ChannelServer newInstance(final int channel) {
        return new ChannelServer(channel);
    }
    
    public static final ChannelServer getInstance(final int channel) {
        return (ChannelServer)ChannelServer.instances.get((Object)Integer.valueOf(channel));
    }
    
    public static final void startAllChannels() {
        ChannelServer.serverStartTime = System.currentTimeMillis();
        for (int channelCount = WorldConstants.CHANNEL_COUNT, i = 1; i <= Math.min(20, (channelCount > 0) ? channelCount : 1); ++i) {
            newInstance(i).setup();
        }
    }
    
    public static final void startChannel(final int channel) {
        ChannelServer.serverStartTime = System.currentTimeMillis();
        if (channel <= WorldConstants.CHANNEL_COUNT) {
            newInstance(channel).setup();
        }
    }
    
    public static void forceRemovePlayerByCharName(final MapleClient client, final String Name) {
        for (final ChannelServer ch : getAllInstances()) {
            Collection<MapleCharacter> chrs = ch.getPlayerStorage().getAllCharactersThreadSafe();
            for (final MapleCharacter c : chrs) {
                if (c.getName().equalsIgnoreCase(Name)) {
                    try {
                        if (c.getClient() != null && c.getClient() != client) {
                            c.getClient().unLockDisconnect();
                        }
                    }
                    catch (Exception ex) {}
                    chrs = ch.getPlayerStorage().getAllCharactersThreadSafe();
                    if (chrs.contains((Object)c)) {
                        ch.removePlayer(c);
                    }
                    c.getMap().removePlayer(c);
                }
            }
        }
    }
    
    public static void forceRemovePlayerByCharNameFromDataBase(final MapleClient client, final List<String> Name) {
        for (final ChannelServer ch : getAllInstances()) {
            for (final String name : Name) {
                if (ch.getPlayerStorage().getCharacterByName(name) != null) {
                    final MapleCharacter c = ch.getPlayerStorage().getCharacterByName(name);
                    try {
                        if (c.getClient() != null && c.getClient() != client) {
                            c.getClient().unLockDisconnect();
                        }
                    }
                    catch (Exception ex) {}
                    if (ch.getPlayerStorage().getAllCharactersThreadSafe().contains((Object)c)) {
                        ch.removePlayer(c);
                    }
                    c.getMap().removePlayer(c);
                }
            }
        }
        for (final String name2 : Name) {
            if (CashShopServer.getPlayerStorage().getCharacterByName(name2) != null) {
                final MapleCharacter c2 = CashShopServer.getPlayerStorage().getCharacterByName(name2);
                try {
                    if (c2.getClient() == null || c2.getClient() == client) {
                        continue;
                    }
                    c2.getClient().unLockDisconnect();
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    public int getLastOfflineTime2() {
        int retnumber = -1;
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            final PreparedStatement ps = conn.prepareStatement("TRUNCATE TABLE lefttime");
            ps.executeUpdate();
            final ArrayList<离线人偶> clone = ChannelServer.clones;
            for (final 离线人偶 jr : clone) {
                final PreparedStatement psu = conn.prepareStatement("insert into lefttime (accid,charid,x,y,chairid,lefttime,channel) values (?,?,?,?,?,?,?)");
                psu.setInt(1, jr.AccId);
                psu.setInt(2, jr.charId);
                psu.setInt(3, jr.x);
                psu.setInt(4, jr.y);
                psu.setInt(5, jr.chairId);
                psu.setLong(6, jr.liftTime);
                psu.setInt(7, jr.channel);
                psu.executeUpdate();
                psu.close();
            }
            ps.close();
            retnumber = 1;
        }
        catch (Exception Ex) {
            System.out.println("离线挂机数据保存异常" + (Object)Ex);
            try {
                if (conn != null) {
                    conn.close();
                }
            }
            catch (Exception Ex2) {
                System.out.println("离线挂机数据保存自动关闭数据库异常" + (Object)Ex2);
            }
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            }
            catch (Exception Ex2) {
                System.out.println("离线挂机数据保存自动关闭数据库异常" + (Object)Ex2);
            }
        }
        return retnumber;
    }
    
    static {
        ChannelServer.离线挂机 = Boolean.parseBoolean(ServerProperties.getProperty("CongMS.离线挂机"));
        ChannelServer.cf = new Config();
        DEFAULT_PORT = (short)Integer.parseInt(ChannelServer.cf.getConfig("CongMS.channel.port1"));
        instances = new HashMap<Integer, ChannelServer>();
        ChannelServer.clones = new ArrayList<离线人偶>();
    }
}
