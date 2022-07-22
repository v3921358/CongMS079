package scripting;

import java.util.Calendar;
import constants.ServerConfig;
import tools.packet.UIPacket;
import server.MapleItemInformationProvider;
import server.MapleCarnivalParty;
import server.maps.MapleMapFactory;
import java.util.Collections;
import client.MapleQuestStatus;
import server.quest.MapleQuest;
import java.util.Collection;
import server.MapleSquad;
import handling.channel.ChannelServer;
import handling.world.MaplePartyCharacter;
import server.maps.MapleMap;
import handling.world.MapleParty;
import java.util.Iterator;
import tools.MaplePacketCreator;
import server.Timer.EventTimer;
import javax.script.ScriptException;
import java.util.concurrent.RejectedExecutionException;
import tools.FilePrinter;
import java.util.HashMap;
import java.util.LinkedList;
import client.MapleClient;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.ScheduledFuture;
import java.util.Properties;
import java.util.Map;
import server.life.MapleMonster;
import client.MapleCharacter;
import java.util.List;

public class EventInstanceManager
{
    private List<MapleCharacter> chars;
    private List<Integer> dced;
    private List<MapleMonster> mobs;
    private Map<Integer, Integer> killCount;
    private final EventManager em;
    private final int channel;
    private final String name;
    private Properties props;
    private long timeStarted;
    private long eventTime;
    private List<Integer> mapIds;
    private List<Boolean> isInstanced;
    private ScheduledFuture<?> eventTimer;
    private final ReentrantReadWriteLock mutex;
    private final Lock rL;
    private final Lock wL;
    private boolean disposed;
    private MapleClient c;
    
    public EventInstanceManager(final EventManager em, final String name, final int channel) {
        this.chars = new LinkedList<MapleCharacter>();
        this.dced = new LinkedList<Integer>();
        this.mobs = new LinkedList<MapleMonster>();
        this.killCount = new HashMap<Integer, Integer>();
        this.props = new Properties();
        this.timeStarted = 0L;
        this.eventTime = 0L;
        this.mapIds = new LinkedList<Integer>();
        this.isInstanced = new LinkedList<Boolean>();
        this.mutex = new ReentrantReadWriteLock();
        this.rL = this.mutex.readLock();
        this.wL = this.mutex.writeLock();
        this.disposed = false;
        this.em = em;
        this.name = name;
        this.channel = channel;
    }
    
    public void registerPlayer(final MapleCharacter chr) {
        if (this.disposed || chr == null) {
            return;
        }
        try {
            this.wL.lock();
            try {
                this.chars.add(chr);
            }
            finally {
                this.wL.unlock();
            }
            chr.setEventInstance(this);
            this.em.getIv().invokeFunction("playerEntry", this, chr);
        }
        catch (NullPointerException ex) {
            FilePrinter.printError("EventInstanceManager.txt", (Throwable)ex);
        }
        catch (RejectedExecutionException ex4) {}
        catch (ScriptException | NoSuchMethodException ex5) {
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : playerEntry:\n" + (Object)ex5);
            System.err.println("Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : playerEntry:\n" + (Object)ex5);
        }
    }
    
    public void changedMap(final MapleCharacter chr, final int mapid) {
        if (this.disposed) {
            return;
        }
        try {
            this.em.getIv().invokeFunction("changedMap", this, chr, Integer.valueOf(mapid));
        }
        catch (NullPointerException ex2) {}
        catch (Exception ex) {
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : changedMap:\n" + (Object)ex);
            System.err.println("Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : changedMap:\n" + (Object)ex);
        }
    }
    
    public void timeOut(final long delay, final EventInstanceManager eim) {
        if (this.disposed || eim == null) {
            return;
        }
        this.eventTimer = EventTimer.getInstance().schedule((Runnable)new Runnable() {
            @Override
            public void run() {
                if (disposed || eim == null || em == null) {
                    return;
                }
                try {
                    em.getIv().invokeFunction("scheduledTimeout", eim);
                }
                catch (Exception ex) {
                    FilePrinter.printError("EventInstanceManager.txt", "Event name" + em.getName() + ", Instance name : " + name + ", method Name : scheduledTimeout:\n" + (Object)ex);
                    System.err.println("Event name" + em.getName() + ", Instance name : " + name + ", method Name : scheduledTimeout:\n" + (Object)ex);
                }
            }
        }, delay);
    }
    
    public void stopEventTimer() {
        this.eventTime = 0L;
        this.timeStarted = 0L;
        if (this.eventTimer != null) {
            this.eventTimer.cancel(false);
        }
    }
    
    public void restartEventTimer(final long time) {
        try {
            if (this.disposed) {
                return;
            }
            this.timeStarted = System.currentTimeMillis();
            this.eventTime = time;
            if (this.eventTimer != null) {
                this.eventTimer.cancel(false);
            }
            this.eventTimer = null;
            final int timesend = (int)time / 1000;
            for (final MapleCharacter chr : this.getPlayers()) {
                chr.getClient().sendPacket(MaplePacketCreator.getClock(timesend));
            }
            this.timeOut(time, this);
        }
        catch (Exception ex) {
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : restartEventTimer:\n" + (Object)ex);
            System.err.println("Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : restartEventTimer:\n");
            ex.printStackTrace();
        }
    }
    
    public void startEventTimer(final long time) {
        this.restartEventTimer(time);
    }
    
    public boolean isTimerStarted() {
        return this.eventTime > 0L && this.timeStarted > 0L;
    }
    
    public long getTimeLeft() {
        return this.eventTime - (System.currentTimeMillis() - this.timeStarted);
    }
    
    public void registerParty(final MapleParty party, final MapleMap map) {
        if (this.disposed) {
            return;
        }
        for (final MaplePartyCharacter pc : party.getMembers()) {
            final MapleCharacter c = map.getCharacterById(pc.getId());
            this.registerPlayer(c);
        }
    }
    
    public void unregisterPlayer(final MapleCharacter chr) {
        if (this.disposed) {
            chr.setEventInstance(null);
            return;
        }
        this.wL.lock();
        try {
            this.unregisterPlayer_NoLock(chr);
        }
        finally {
            this.wL.unlock();
        }
    }
    
    private boolean unregisterPlayer_NoLock(final MapleCharacter chr) {
        if (this.name.equals((Object)"CWKPQ")) {
            final MapleSquad squad = ChannelServer.getInstance(this.channel).getMapleSquad("CWKPQ");
            if (squad != null) {
                squad.removeMember(chr.getName());
                if (squad.getLeaderName().equals((Object)chr.getName())) {
                    this.em.setProperty("leader", "false");
                }
            }
        }
        chr.setEventInstance(null);
        if (this.disposed) {
            return false;
        }
        if (this.chars.contains((Object)chr)) {
            this.chars.remove((Object)chr);
            return true;
        }
        return false;
    }
    
    public boolean check() {
        for (final MapleCharacter chr : this.getPlayers()) {
            if (chr.getLevel() < 30 || chr.getLevel() > 50) {
                return false;
            }
        }
        return true;
    }
    
    public boolean check1() {
        for (final MapleCharacter chr : this.getPlayers()) {
            if (chr.getLevel() < 51 || chr.getLevel() > 120) {
                return false;
            }
        }
        return true;
    }
    
    public final boolean disposeIfPlayerBelow(final byte size, final int towarp) {
        if (this.disposed) {
            return true;
        }
        MapleMap map = null;
        if (towarp > 0) {
            map = this.getMapFactory().getMap(towarp);
        }
        this.wL.lock();
        try {
            if (this.chars.size() <= size) {
                final List<MapleCharacter> chrs = new LinkedList<MapleCharacter>((Collection<? extends MapleCharacter>)this.chars);
                for (final MapleCharacter chr : chrs) {
                    this.unregisterPlayer_NoLock(chr);
                    if (towarp > 0) {
                        chr.changeMap(map, map.getPortal(0));
                    }
                }
                this.dispose_NoLock();
                return true;
            }
        }
        finally {
            this.wL.unlock();
        }
        return false;
    }
    
    public final void saveBossQuest(final int points) {
        if (this.disposed) {
            return;
        }
        for (final MapleCharacter chr : this.getPlayers()) {
            final MapleQuestStatus record = chr.getQuestNAdd(MapleQuest.getInstance(150001));
            if (record.getCustomData() != null) {
                record.setCustomData(String.valueOf(points + Integer.parseInt(record.getCustomData())));
            }
            else {
                record.setCustomData(String.valueOf(points));
            }
        }
    }
    
    public final void saveNX(final int points) {
        if (this.disposed) {
            return;
        }
        for (final MapleCharacter chr : this.getPlayers()) {
            chr.modifyCSPoints(1, points, true);
        }
    }
    
    public List<MapleCharacter> getPlayers() {
        if (this.disposed) {
            return Collections.emptyList();
        }
        this.rL.lock();
        try {
            return new LinkedList<MapleCharacter>((Collection<? extends MapleCharacter>)this.chars);
        }
        finally {
            this.rL.unlock();
        }
    }
    
    public List<Integer> getDisconnected() {
        return this.dced;
    }
    
    public final int getPlayerCount() {
        if (this.disposed) {
            return 0;
        }
        return this.chars.size();
    }
    
    public void registerMonster(final MapleMonster mob) {
        if (this.disposed) {
            return;
        }
        this.mobs.add(mob);
        mob.setEventInstance(this);
    }
    
    public void unregisterMonster(final MapleMonster mob) {
        mob.setEventInstance(null);
        if (this.disposed) {
            return;
        }
        this.mobs.remove((Object)mob);
        if (this.mobs.isEmpty()) {
            try {
                this.em.getIv().invokeFunction("allMonstersDead", this);
            }
            catch (RejectedExecutionException ex3) {}
            catch (ScriptException | NoSuchMethodException ex4) {
                FilePrinter.printError("EventInstanceManager.txt", "Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : allMonstersDead:\n" + (Object)ex4);
                System.err.println("Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : allMonstersDead:\n" + (Object)ex4);
            }
        }
    }
    
    public void playerKilled(final MapleCharacter chr) {
        if (this.disposed) {
            return;
        }
        try {
            this.em.getIv().invokeFunction("playerDead", this, chr);
        }
        catch (RejectedExecutionException ex3) {}
        catch (ScriptException | NoSuchMethodException ex4) {
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : playerDead:\n" + (Object)ex4);
            System.err.println("Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : playerDead:\n" + (Object)ex4);
        }
    }
    
    public boolean revivePlayer(final MapleCharacter chr) {
        if (this.disposed) {
            return false;
        }
        try {
            final Object b = this.em.getIv().invokeFunction("playerRevive", this, chr);
            if (b instanceof Boolean) {
                return (boolean)(Boolean)b;
            }
        }
        catch (RejectedExecutionException ex3) {}
        catch (ScriptException | NoSuchMethodException ex4) {
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : playerRevive:\n" + (Object)ex4);
            System.err.println("Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : playerRevive:\n" + (Object)ex4);
        }
        return true;
    }
    
    public void playerDisconnected(final MapleCharacter chr, final int idz) {
        if (this.disposed) {
            return;
        }
        byte ret;
        try {
            ret = ((Double)this.em.getIv().invokeFunction("playerDisconnected", this, chr)).byteValue();
        }
        catch (Exception e) {
            ret = 0;
        }
        this.wL.lock();
        try {
            if (this.disposed) {
                return;
            }
            this.dced.add(Integer.valueOf(idz));
            if (chr != null) {
                this.unregisterPlayer_NoLock(chr);
            }
            if (ret == 0) {
                if (this.getPlayerCount() <= 0) {
                    this.dispose_NoLock();
                }
            }
            else if ((ret > 0 && this.getPlayerCount() < ret) || (ret < 0 && (this.isLeader(chr) || this.getPlayerCount() < ret * -1))) {
                final List<MapleCharacter> chrs = new LinkedList<MapleCharacter>((Collection<? extends MapleCharacter>)this.chars);
                for (final MapleCharacter player : chrs) {
                    if (player.getId() != idz) {
                        this.removePlayer(player);
                    }
                }
                this.dispose_NoLock();
            }
        }
        catch (Exception ex) {
            FilePrinter.printError("EventInstanceManager.txt", (Throwable)ex);
        }
        finally {
            this.wL.unlock();
        }
    }
    
    public void monsterKilled(final MapleCharacter chr, final MapleMonster mob) {
        if (this.disposed) {
            return;
        }
        try {
            try {
                Integer kc = this.killCount.get((Object)Integer.valueOf(chr.getId()));
                final int inc = (int)(Integer)this.em.getIv().invokeFunction("monsterValue", this, Integer.valueOf(mob.getId()));
                if (this.disposed) {
                    return;
                }
                if (kc == null) {
                    kc = Integer.valueOf(inc);
                }
                else {
                    kc = Integer.valueOf((int)kc + inc);
                }
                this.killCount.put(Integer.valueOf(chr.getId()), kc);
                if (chr.getCarnivalParty() != null && (mob.getStats().getPoint() > 0 || mob.getStats().getCP() > 0)) {
                    this.em.getIv().invokeFunction("monsterKilled", this, chr, Integer.valueOf((mob.getStats().getCP() > 0) ? mob.getStats().getCP() : mob.getStats().getPoint()));
                }
            }
            catch (RejectedExecutionException ex2) {}
            catch (NoSuchMethodException ex) {
                System.err.println("Event name" + ((this.em == null) ? "null" : this.em.getName()) + ", Instance name : " + this.name + ", method Name : monsterValue:\n" + (Object)ex);
                FilePrinter.printError("EventInstanceManager.txt", "Event name" + ((this.em == null) ? "null" : this.em.getName()) + ", Instance name : " + this.name + ", method Name : monsterValue:\n" + (Object)ex);
            }
        }
        catch (ScriptException ex3) {}
    }
    
    public void monsterDamaged(final MapleCharacter chr, final MapleMonster mob, final int damage) {
        if (this.disposed || mob.getId() != 9700037) {
            return;
        }
        try {
            this.em.getIv().invokeFunction("monsterDamaged", this, chr, Integer.valueOf(mob.getId()), Integer.valueOf(damage));
        }
        catch (RejectedExecutionException ex4) {}
        catch (ScriptException ex) {
            System.err.println("Event name" + ((this.em == null) ? "null" : this.em.getName()) + ", Instance name : " + this.name + ", method Name : monsterValue:\n" + (Object)ex);
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : restartEventTimer:\n" + (Object)ex);
        }
        catch (NoSuchMethodException ex2) {
            System.err.println("Event name" + ((this.em == null) ? "null" : this.em.getName()) + ", Instance name : " + this.name + ", method Name : monsterValue:\n" + (Object)ex2);
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : restartEventTimer:\n" + (Object)ex2);
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : restartEventTimer:\n" + (Object)ex3);
        }
    }
    
    public int getKillCount(final MapleCharacter chr) {
        if (this.disposed) {
            return 0;
        }
        final Integer kc = this.killCount.get((Object)Integer.valueOf(chr.getId()));
        if (kc == null) {
            return 0;
        }
        return (int)kc;
    }
    
    public void dispose_NoLock() {
        if (this.disposed || this.em == null) {
            return;
        }
        final String emN = this.em.getName();
        try {
            this.disposed = true;
            for (final MapleCharacter chr : this.chars) {
                chr.setEventInstance(null);
            }
            this.chars.clear();
            this.chars = null;
            for (final MapleMonster mob : this.mobs) {
                mob.setEventInstance(null);
            }
            this.mobs.clear();
            this.mobs = null;
            this.killCount.clear();
            this.killCount = null;
            this.dced.clear();
            this.dced = null;
            this.timeStarted = 0L;
            this.eventTime = 0L;
            this.props.clear();
            this.props = null;
            for (int i = 0; i < this.mapIds.size(); ++i) {
                if ((boolean)Boolean.valueOf(this.isInstanced.get(i))) {
                    this.getMapFactory().removeInstanceMap((int)Integer.valueOf(this.mapIds.get(i)));
                }
            }
            this.mapIds.clear();
            this.mapIds = null;
            this.isInstanced.clear();
            this.isInstanced = null;
            this.em.disposeInstance(this.name);
        }
        catch (Exception e) {
            System.err.println("Caused by : " + emN + " instance name: " + this.name + " method: dispose: " + (Object)e);
            FilePrinter.printError("EventInstanceManager.txt", "Caused by : " + emN + " instance name: " + this.name + " method: dispose: " + (Object)e);
        }
    }
    
    public void dispose() {
        this.wL.lock();
        try {
            this.dispose_NoLock();
        }
        finally {
            this.wL.unlock();
        }
    }
    
    public ChannelServer getChannelServer() {
        return ChannelServer.getInstance(this.channel);
    }
    
    public List<MapleMonster> getMobs() {
        return this.mobs;
    }
    
    public final void broadcastPlayerMsg(final int type, final String msg) {
        if (this.disposed) {
            return;
        }
        for (final MapleCharacter chr : this.getPlayers()) {
            chr.getClient().sendPacket(MaplePacketCreator.serverNotice(type, msg));
        }
    }
    
    public final MapleMap createInstanceMap(final int mapid) {
        if (this.disposed) {
            return null;
        }
        final int assignedid = this.getChannelServer().getEventSM().getNewInstanceMapId();
        this.mapIds.add(Integer.valueOf(assignedid));
        this.isInstanced.add(Boolean.valueOf(true));
        return this.getMapFactory().CreateInstanceMap(mapid, true, true, true, assignedid);
    }
    
    public final MapleMap createInstanceMapS(final int mapid) {
        if (this.disposed) {
            return null;
        }
        final int assignedid = this.getChannelServer().getEventSM().getNewInstanceMapId();
        this.mapIds.add(Integer.valueOf(assignedid));
        this.isInstanced.add(Boolean.valueOf(true));
        return this.getMapFactory().CreateInstanceMap(mapid, false, false, false, assignedid);
    }
    
    public final MapleMap setInstanceMap(final int mapid) {
        if (this.disposed) {
            return this.getMapFactory().getMap(mapid);
        }
        this.mapIds.add(Integer.valueOf(mapid));
        this.isInstanced.add(Boolean.valueOf(false));
        return this.getMapFactory().getMap(mapid);
    }
    
    public final MapleMapFactory getMapFactory() {
        return this.getChannelServer().getMapFactory();
    }
    
    public final MapleMap getMapInstance(final int args) {
        if (this.disposed) {
            return null;
        }
        try {
            boolean instanced = false;
            int trueMapID;
            if (args >= this.mapIds.size()) {
                trueMapID = args;
            }
            else {
                trueMapID = (int)Integer.valueOf(this.mapIds.get(args));
                instanced = (boolean)Boolean.valueOf(this.isInstanced.get(args));
            }
            MapleMap map;
            if (!instanced) {
                map = this.getMapFactory().getMap(trueMapID);
                if (map == null) {
                    return null;
                }
                if (map.getCharactersSize() == 0 && this.em.getProperty("shuffleReactors") != null && this.em.getProperty("shuffleReactors").equals((Object)"true")) {
                    map.shuffleReactors();
                }
            }
            else {
                map = this.getMapFactory().getInstanceMap(trueMapID);
                if (map == null) {
                    return null;
                }
                if (map.getCharactersSize() == 0 && this.em.getProperty("shuffleReactors") != null && this.em.getProperty("shuffleReactors").equals((Object)"true")) {
                    map.shuffleReactors();
                }
            }
            return map;
        }
        catch (NullPointerException ex) {
            FilePrinter.printError("EventInstanceManager.txt", (Throwable)ex);
            return null;
        }
    }
    
    public final void schedule(final String methodName, final long delay) {
        if (this.disposed) {
            return;
        }
        EventTimer.getInstance().schedule((Runnable)new Runnable() {
            @Override
            public void run() {
                if (disposed || EventInstanceManager.this == null || em == null) {
                    return;
                }
                try {
                    em.getIv().invokeFunction(methodName, EventInstanceManager.this);
                }
                catch (NullPointerException ex3) {}
                catch (RejectedExecutionException ex4) {}
                catch (ScriptException | NoSuchMethodException ex5) {
                    System.err.println("Event name" + em.getName() + ", Instance name : " + name + ", method Name : " + methodName + ":\n" + (Object)ex5);
                    FilePrinter.printError("EventInstanceManager.txt", "Event name" + em.getName() + ", Instance name : " + name + ", method Name : " + methodName + ":\n" + (Object)ex5);
                }
            }
        }, delay);
    }
    
    public final String getName() {
        return this.name;
    }
    
    public final void setProperty(final String key, final String value) {
        if (this.disposed) {
            return;
        }
        this.props.setProperty(key, value);
    }
    
    public final Object setProperty(final String key, final String value, final boolean prev) {
        if (this.disposed) {
            return null;
        }
        return this.props.setProperty(key, value);
    }
    
    public final String getProperty(final String key) {
        if (this.disposed) {
            return "";
        }
        return this.props.getProperty(key);
    }
    
    public final Properties getProperties() {
        return this.props;
    }
    
    public final void leftParty(final MapleCharacter chr) {
        if (this.disposed) {
            return;
        }
        try {
            this.em.getIv().invokeFunction("leftParty", this, chr);
        }
        catch (Exception ex) {
            System.err.println("Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : leftParty:\n" + (Object)ex);
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : leftParty:\n" + (Object)ex);
        }
    }
    
    public final void disbandParty() {
        if (this.disposed) {
            return;
        }
        try {
            this.em.getIv().invokeFunction("disbandParty", this);
        }
        catch (Exception ex) {
            System.out.println("Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : disbandParty:\n" + (Object)ex);
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : disbandParty:\n" + (Object)ex);
        }
    }
    
    public final void finishPQ() {
        if (this.disposed) {
            return;
        }
        try {
            this.em.getIv().invokeFunction("clearPQ", this);
        }
        catch (RejectedExecutionException ex3) {}
        catch (ScriptException | NoSuchMethodException ex4) {
            System.err.println("Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : clearPQ:\n" + (Object)ex4);
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : clearPQ:\n" + (Object)ex4);
        }
    }
    
    public final void removePlayer(final MapleCharacter chr) {
        if (this.disposed) {
            return;
        }
        try {
            this.em.getIv().invokeFunction("playerExit", this, chr);
        }
        catch (RejectedExecutionException ex3) {}
        catch (ScriptException | NoSuchMethodException ex4) {
            System.err.println("Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : playerExit:\n" + (Object)ex4);
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : playerExit:\n" + (Object)ex4);
        }
    }
    
    public final void registerCarnivalParty(final MapleCharacter leader, final MapleMap map, final byte team) {
        if (this.disposed) {
            return;
        }
        leader.clearCarnivalRequests();
        final List<MapleCharacter> characters = new LinkedList<MapleCharacter>();
        final MapleParty party = leader.getParty();
        if (party == null) {
            return;
        }
        for (final MaplePartyCharacter pc : party.getMembers()) {
            final MapleCharacter c = map.getCharacterById(pc.getId());
            if (c != null) {
                characters.add(c);
                this.registerPlayer(c);
                c.resetCP();
            }
        }
        final MapleCarnivalParty carnivalParty = new MapleCarnivalParty(leader, characters, team);
        for (final MapleCharacter chr : characters) {
            chr.setCarnivalParty(carnivalParty);
        }
        try {
            this.em.getIv().invokeFunction("registerCarnivalParty", this, carnivalParty);
        }
        catch (RejectedExecutionException ex2) {}
        catch (ScriptException ex) {
            System.err.println("Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : registerCarnivalParty:\n" + (Object)ex);
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : registerCarnivalParty:\n" + (Object)ex);
        }
        catch (NoSuchMethodException ex3) {}
    }
    
    public void onMapLoad(final MapleCharacter chr) {
        if (this.disposed) {
            return;
        }
        try {
            this.em.getIv().invokeFunction("onMapLoad", this, chr);
        }
        catch (RejectedExecutionException ex2) {}
        catch (ScriptException ex) {
            System.err.println("Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : onMapLoad:\n" + (Object)ex);
            FilePrinter.printError("EventInstanceManager.txt", "Event name" + this.em.getName() + ", Instance name : " + this.name + ", method Name : onMapLoad:\n" + (Object)ex);
        }
        catch (NoSuchMethodException ex3) {}
    }
    
    public boolean isLeader(final MapleCharacter chr) {
        return chr != null && chr.getParty() != null && chr.getParty().getLeader().getId() == chr.getId();
    }
    
    public void registerSquad(final MapleSquad squad, final MapleMap map, final int questID) {
        if (this.disposed) {
            return;
        }
        final int mapid = map.getId();
        for (final String chr : squad.getMembers()) {
            final MapleCharacter player = squad.getChar(chr);
            if (player != null && player.getMapId() == mapid) {
                if (questID > 0) {
                    player.getQuestNAdd(MapleQuest.getInstance(questID)).setCustomData(String.valueOf(System.currentTimeMillis()));
                }
                this.registerPlayer(player);
            }
        }
        squad.setStatus((byte)2);
        squad.getBeginMap().broadcastMessage(MaplePacketCreator.stopClock());
    }
    
    public boolean isDisconnected(final MapleCharacter chr) {
        return !this.disposed && this.dced.contains((Object)Integer.valueOf(chr.getId()));
    }
    
    public void removeDisconnected(final int id) {
        if (this.disposed) {
            return;
        }
        this.dced.remove(id);
    }
    
    public EventManager getEventManager() {
        return this.em;
    }
    
    public void applyBuff(final MapleCharacter chr, final int id) {
        MapleItemInformationProvider.getInstance().getItemEffect(id).applyTo(chr);
        chr.getClient().sendPacket(UIPacket.getStatusMsg(id));
    }
    
    public String getServerName() {
        return ServerConfig.SERVERNAME;
    }
    
    public int 获取当前星期() {
        return Calendar.getInstance().get(7);
    }
    
    public final MapleClient getClient() {
        return this.c;
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
    
    public MapleItemInformationProvider getItemInfo() {
        return MapleItemInformationProvider.getInstance();
    }
    
    public void setPQLog(final String log) {
        this.getPlayers().parallelStream().forEach(p -> p.setPQLog(log));
    }
    
    public void setEventCount(final String log) {
        this.getPlayers().parallelStream().forEach(p -> p.setEventCount(log));
    }
    
}
