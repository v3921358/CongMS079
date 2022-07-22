package scripting;

import server.MapleItemInformationProvider;
import java.util.Calendar;
import constants.ServerConfig;
import handling.world.World.Broadcast;
import server.events.MapleEvent;
import server.Randomizer;
import server.events.MapleEventType;
import tools.MaplePacketCreator;
import server.life.MapleLifeFactory;
import server.life.MapleMonster;
import java.util.ArrayList;
import server.life.OverrideMonsterStats;
import server.maps.MapleMapFactory;
import server.maps.MapleMapObject;
import java.util.Iterator;
import java.util.List;
import server.maps.MapleMap;
import handling.world.MapleParty;
import client.MapleCharacter;
import server.MapleSquad;
import java.util.Collections;
import java.util.Collection;
import tools.FileoutputUtil;
import server.Timer.EventTimer;
import java.util.concurrent.ScheduledFuture;
import javax.script.ScriptException;
import tools.FilePrinter;
import java.util.WeakHashMap;
import handling.channel.ChannelServer;
import client.MapleClient;
import java.util.Properties;
import java.util.Map;
import javax.script.Invocable;

public class EventManager
{
    private static int[] eventChannel;
    private Invocable iv;
    private int channel;
    private Map<String, EventInstanceManager> instances;
    private Properties props;
    private String name;
    private MapleClient c;
    
    public EventManager(final ChannelServer cserv, final Invocable iv, final String name) {
        this.instances = new WeakHashMap<String, EventInstanceManager>();
        this.props = new Properties();
        this.iv = iv;
        this.channel = cserv.getChannel();
        this.name = name;
    }
    
    public void cancel() {
        try {
            this.iv.invokeFunction("cancelSchedule", new Object[] { null });
        }
        catch (ScriptException | NoSuchMethodException ex3) {
            System.err.println("Event name : " + this.name + ", method Name : cancelSchedule:\n" + (Object)ex3);
            FilePrinter.printError("EventManager.txt", "Event name : " + this.name + ", method Name : cancelSchedule:\n" + (Object)ex3);
        }
    }
    
    public ScheduledFuture<?> schedule(final String methodName, final long delay) {
        return EventTimer.getInstance().schedule((Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    iv.invokeFunction(methodName, new Object[] { null });
                }
                catch (ScriptException | NoSuchMethodException ex3) {
                    System.err.println("Event name : " + name + ", method Name : " + methodName + ":\n" + (Object)ex3);
                    FilePrinter.printError("EventManager.txt", "Event name : " + name + ", method Name : " + methodName + ":\n" + (Object)ex3);
                }
            }
        }, delay);
    }
    
    public ScheduledFuture<?> schedule(final String methodName, final long delay, final EventInstanceManager eim) {
        return EventTimer.getInstance().schedule((Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    iv.invokeFunction(methodName, eim);
                }
                catch (ScriptException | NoSuchMethodException ex3) {
                    System.err.println("Event name : " + name + ", method Name : " + methodName + ":\n" + (Object)ex3);
                    FilePrinter.printError("EventManager.txt", "Event name : " + name + ", method Name : " + methodName + ":\n" + (Object)ex3);
                    FileoutputUtil.log("logs\\Log_Script_Except.txt", "Event name : " + name + ", method Name : " + methodName + ":\n" + (Object)ex3);
                }
            }
        }, delay);
    }
    
    public ScheduledFuture<?> scheduleAtTimestamp(final String methodName, final long timestamp) {
        return EventTimer.getInstance().scheduleAtTimestamp((Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    iv.invokeFunction(methodName, new Object[] { null });
                }
                catch (ScriptException | NoSuchMethodException ex3) {
                    System.err.println("Event name : " + name + ", method Name : " + methodName + ":\n" + (Object)ex3);
                    FilePrinter.printError("EventManager.txt", "Event name : " + name + ", method Name : " + methodName + ":\n" + (Object)ex3);
                    FileoutputUtil.log("logs\\Log_Script_Except.txt", "Event name : " + name + ", method Name : " + methodName + ":\n" + (Object)ex3);
                }
            }
        }, timestamp);
    }
    
    public int getChannel() {
        return this.channel;
    }
    
    public ChannelServer getChannelServer() {
        return ChannelServer.getInstance(this.channel);
    }
    
    public EventInstanceManager getInstance(final String name) {
        return (EventInstanceManager)this.instances.get((Object)name);
    }
    
    public Collection<EventInstanceManager> getInstances() {
        return Collections.unmodifiableCollection((Collection<? extends EventInstanceManager>)this.instances.values());
    }
    
    public EventInstanceManager newInstance(final String name) {
        final EventInstanceManager ret = new EventInstanceManager(this, name, this.channel);
        this.instances.put(name, ret);
        return ret;
    }
    
    public void disposeInstance(final String name) {
        this.instances.remove((Object)name);
        if (this.getProperty("state") != null && this.instances.isEmpty()) {
            this.setProperty("state", "0");
        }
        if (this.getProperty("leader") != null && this.instances.isEmpty() && this.getProperty("leader").equals((Object)"false")) {
            this.setProperty("leader", "true");
        }
        if (this.name.equals((Object)"CWKPQ")) {
            final MapleSquad squad = ChannelServer.getInstance(this.channel).getMapleSquad("CWKPQ");
            if (squad != null) {
                squad.clear();
            }
        }
    }
    
    public Invocable getIv() {
        return this.iv;
    }
    
    public void setProperty(final String key, final String value) {
        this.props.setProperty(key, value);
    }
    
    public String getProperty(final String key) {
        return this.props.getProperty(key);
    }
    
    public final Properties getProperties() {
        return this.props;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void startInstance() {
        try {
            this.iv.invokeFunction("setup", new Object[] { null });
        }
        catch (ScriptException | NoSuchMethodException ex3) {
            System.err.println("Event name : " + this.name + ", method Name : setup:\n" + (Object)ex3);
            FilePrinter.printError("EventManager.txt", "Event name : " + this.name + ", method Name : setup:\n" + (Object)ex3);
        }
    }
    
    public void startInstance(final String mapid, final MapleCharacter chr) {
        try {
            final EventInstanceManager eim = (EventInstanceManager)this.iv.invokeFunction("setup", mapid);
            eim.registerCarnivalParty(chr, chr.getMap(), (byte)0);
        }
        catch (ScriptException | NoSuchMethodException ex3) {
            System.err.println("Event name : " + this.name + ", method Name : setup:\n" + (Object)ex3);
            FilePrinter.printError("EventManager.txt", "Event name : " + this.name + ", method Name : setup:\n" + (Object)ex3);
        }
    }
    
    public void startInstance_Party(final String mapid, final MapleCharacter chr) {
        try {
            final EventInstanceManager eim = (EventInstanceManager)this.iv.invokeFunction("setup", mapid);
            eim.registerParty(chr.getParty(), chr.getMap());
        }
        catch (ScriptException | NoSuchMethodException ex3) {
            System.err.println("Event name : " + this.name + ", method Name : setup:\n" + (Object)ex3);
            FilePrinter.printError("EventManager.txt", "Event name : " + this.name + ", method Name : setup:\n" + (Object)ex3);
        }
    }
    
    public void startInstance(final MapleCharacter character, final String leader) {
        try {
            final EventInstanceManager eim = (EventInstanceManager)(EventInstanceManager)this.iv.invokeFunction("setup", new Object[] { null });
            eim.registerPlayer(character);
            eim.setProperty("leader", leader);
            eim.setProperty("guildid", String.valueOf(character.getGuildId()));
            this.setProperty("guildid", String.valueOf(character.getGuildId()));
        }
        catch (ScriptException | NoSuchMethodException ex3) {
            System.err.println("Event name : " + this.name + ", method Name : setup-Guild:\n" + (Object)ex3);
            FilePrinter.printError("EventManager.txt", "Event name : " + this.name + ", method Name : setup-Guild:\n" + (Object)ex3);
        }
    }
    
    public void startInstance_CharID(final MapleCharacter character) {
        try {
            final EventInstanceManager eim = (EventInstanceManager)(EventInstanceManager)this.iv.invokeFunction("setup", Integer.valueOf(character.getId()));
            eim.registerPlayer(character);
        }
        catch (ScriptException | NoSuchMethodException ex3) {
            System.err.println("Event name : " + this.name + ", method Name : setup-CharID:\n" + (Object)ex3);
            FilePrinter.printError("EventManager.txt", "Event name : " + this.name + ", method Name : setup-CharID:\n" + (Object)ex3);
        }
    }
    
    public void startInstance(final MapleCharacter character) {
        try {
            final EventInstanceManager eim = (EventInstanceManager)(EventInstanceManager)this.iv.invokeFunction("setup", new Object[] { null });
            eim.registerPlayer(character);
        }
        catch (ScriptException | NoSuchMethodException ex3) {
            System.err.println("Event name : " + this.name + ", method Name : setup-character:\n" + (Object)ex3);
            FilePrinter.printError("EventManager.txt", "Event name : " + this.name + ", method Name : setup-character:\n" + (Object)ex3);
        }
    }
    
    public void startInstance(final MapleParty party, final MapleMap map) {
        try {
            final EventInstanceManager eim = (EventInstanceManager)(EventInstanceManager)this.iv.invokeFunction("setup", Integer.valueOf(party.getId()));
            eim.registerParty(party, map);
        }
        catch (ScriptException ex) {
            System.err.println("Event name : " + this.name + ", method Name : setup-partyid:\n" + (Object)ex);
            FilePrinter.printError("EventManager.txt", "Event name : " + this.name + ", method Name : setup-partyid:\n" + (Object)ex);
        }
        catch (NoSuchMethodException ex2) {
            this.startInstance_NoID(party, map, (Exception)ex2);
        }
    }
    
    public void startInstance_NoID(final MapleParty party, final MapleMap map) {
        this.startInstance_NoID(party, map, null);
    }
    
    public void startInstance_NoID(final MapleParty party, final MapleMap map, final Exception old) {
        try {
            final EventInstanceManager eim = (EventInstanceManager)(EventInstanceManager)this.iv.invokeFunction("setup", new Object[] { null });
            eim.registerParty(party, map);
        }
        catch (ScriptException | NoSuchMethodException ex3) {
            System.err.println("Event name : " + this.name + ", method Name : setup-party:\n" + (Object)ex3);
            FilePrinter.printError("EventManager.txt", "Event name : " + this.name + ", method Name : setup-party:\n" + (Object)ex3 + "\n" + (Object)((old == null) ? "no old exception" : old));
        }
    }
    
    public void startInstance(final EventInstanceManager eim, final String leader) {
        try {
            this.iv.invokeFunction("setup", eim);
            eim.setProperty("leader", leader);
        }
        catch (ScriptException | NoSuchMethodException ex3) {
            System.err.println("Event name : " + this.name + ", method Name : setup-leader:\n" + (Object)ex3);
            FilePrinter.printError("EventManager.txt", "Event name : " + this.name + ", method Name : setup-leader:\n" + (Object)ex3);
        }
    }
    
    public void startInstance(final MapleSquad squad, final MapleMap map) {
        this.startInstance(squad, map, -1);
    }
    
    public void startInstance(final MapleSquad squad, final MapleMap map, final int questID) {
        if (squad.getStatus() == 0) {
            return;
        }
        if (!squad.getLeader().isGM() && this.name.equals((Object)"CWKPQ") && squad.getJobs().size() < 5) {
            squad.getLeader().dropMessage(5, "The squad requires members from every type of job.");
            return;
        }
        try {
            final EventInstanceManager eim = (EventInstanceManager)(EventInstanceManager)this.iv.invokeFunction("setup", squad.getLeaderName());
            eim.registerSquad(squad, map, questID);
        }
        catch (ScriptException | NoSuchMethodException ex3) {
            System.err.println("Event name : " + this.name + ", method Name : setup-squad:\n" + (Object)ex3);
            FilePrinter.printError("EventManager.txt", "Event name : " + this.name + ", method Name : setup-squad:\n" + (Object)ex3);
        }
    }
    
    public void warpAllPlayer(final int from, final int to) {
        final MapleMap tomap = this.getMapFactory().getMap(to);
        final MapleMap frommap = this.getMapFactory().getMap(from);
        final List<MapleCharacter> list = frommap.getCharactersThreadsafe();
        if (tomap != null && list != null && frommap.getCharactersSize() > 0) {
            for (final MapleMapObject mmo : list) {
                ((MapleCharacter)mmo).changeMap(tomap, tomap.getPortal(0));
            }
        }
    }
    
    public MapleMapFactory getMapFactory() {
        return this.getChannelServer().getMapFactory();
    }
    
    public OverrideMonsterStats newMonsterStats() {
        return new OverrideMonsterStats();
    }
    
    public List<MapleCharacter> newCharList() {
        return new ArrayList<MapleCharacter>();
    }
    
    public MapleMonster getMonster(final int id) {
        return MapleLifeFactory.getMonster(id);
    }
    
    public void broadcastYellowMsg(final String msg) {
        this.getChannelServer().broadcastPacket(MaplePacketCreator.yellowChat(msg));
    }
    
    public void broadcastServerMsg(final String msg) {
        this.getChannelServer().broadcastPacket(MaplePacketCreator.serverNotice(6, msg));
    }
    
    public void broadcastServerMsg(final int type, final String msg, final boolean weather) {
        if (!weather) {
            this.getChannelServer().broadcastPacket(MaplePacketCreator.serverNotice(type, msg));
        }
        else {
            for (final MapleMap load : this.getMapFactory().getAllMaps()) {
                if (load.getCharactersSize() > 0) {
                    load.startMapEffect(msg, type);
                }
            }
        }
    }
    
    public boolean scheduleRandomEvent() {
        boolean omg = false;
        for (int i = 0; i < EventManager.eventChannel.length; ++i) {
            omg |= this.scheduleRandomEventInChannel(EventManager.eventChannel[i]);
        }
        return omg;
    }
    
    public boolean scheduleRandomEventInChannel(final int chz) {
        final ChannelServer cs = ChannelServer.getInstance(chz);
        if (cs == null || cs.getEvent() > -1) {
            return false;
        }
        MapleEventType t;
        MapleEventType x = null;
        for (t = null; t == null; t = x) {
            final MapleEventType[] values = MapleEventType.values();
            for (int length = values.length, i = 0; i < length; ++i) {
                x = values[i];
                if (Randomizer.nextInt(MapleEventType.values().length) == 0 && x != MapleEventType.爬繩子) {
                    break;
                }
            }
        }
        final String msg = MapleEvent.scheduleEvent(t, cs);
        if (msg.length() > 0) {
            this.broadcastYellowMsg(msg);
            return false;
        }
        EventTimer.getInstance().schedule((Runnable)new Runnable() {
            @Override
            public void run() {
                if (cs.getEvent() >= 0) {
                    MapleEvent.setEvent(cs, true);
                }
            }
        }, 600000L);
        return true;
    }
    
    public void setWorldEvent() {
        for (int i = 0; i < EventManager.eventChannel.length; ++i) {
            EventManager.eventChannel[i] = Randomizer.nextInt(ChannelServer.getAllInstances().size()) + i;
        }
    }
    
    public final void invokeFunctionMethod(final String methodName) {
        try {
            this.iv.invokeFunction(methodName, this);
        }
        catch (ScriptException | NoSuchMethodException ex3) {
            System.out.println("Event name" + this.getName() + ", Instance name : " + this.name + ", method Name : " + methodName + ":\n" + (Object)ex3);
            FilePrinter.printError("EventManager.txt", "Event name : " + this.name + ", method Name : setup-squad:\n" + (Object)ex3);
        }
    }
    
    public final void worldMessage(final int type, final String message) {
        Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(type, message));
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
    
    static {
        EventManager.eventChannel = new int[4];
    }
}
