package server.events;

import server.Randomizer;
import java.util.Iterator;
import server.Timer.EventTimer;
import tools.MaplePacketCreator;
import client.MapleCharacter;
import java.util.concurrent.ScheduledFuture;

public class MapleOla extends MapleEvent
{
    private static final long serialVersionUID = 845748150824L;
    private final long time = 600000L;
    private long timeStarted;
    private transient ScheduledFuture<?> olaSchedule;
    private int[] stages;
    
    public MapleOla(final int channel, final int[] mapid) {
        super(channel, mapid);
        this.timeStarted = 0L;
        this.stages = new int[3];
    }
    
    @Override
    public void finished(final MapleCharacter chr) {
        this.givePrize(chr);
    }
    
    @Override
    public void onMapLoad(final MapleCharacter chr) {
        if (this.isTimerStarted()) {
            chr.getClient().sendPacket(MaplePacketCreator.getClock((int)(this.getTimeLeft() / 1000L)));
        }
    }
    
    @Override
    public void startEvent() {
        this.unreset();
        super.reset();
        this.broadcast(MaplePacketCreator.getClock(600));
        this.timeStarted = System.currentTimeMillis();
        final EventTimer instance = EventTimer.getInstance();
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < MapleOla.this.mapid.length; ++i) {
                    for (final MapleCharacter chr : MapleOla.this.getMap(i).getCharactersThreadsafe()) {
                        MapleOla.this.warpBack(chr);
                    }
                    MapleOla.this.unreset();
                }
            }
        };
        this.getClass();
        this.olaSchedule = instance.schedule((Runnable)r, 600000L);
        this.broadcast(MaplePacketCreator.serverNotice(0, "活動已經開始，請通過中間的入口開始游戏。"));
    }
    
    public boolean isTimerStarted() {
        return this.timeStarted > 0L;
    }
    
    public long getTime() {
        return 600000L;
    }
    
    public void resetSchedule() {
        this.timeStarted = 0L;
        if (this.olaSchedule != null) {
            this.olaSchedule.cancel(false);
        }
        this.olaSchedule = null;
    }
    
    @Override
    public void reset() {
        super.reset();
        this.resetSchedule();
        this.getMap(0).getPortal("join00").setPortalState(false);
        this.stages = new int[] { 0, 0, 0 };
    }
    
    @Override
    public void unreset() {
        super.unreset();
        this.resetSchedule();
        this.getMap(0).getPortal("join00").setPortalState(true);
        this.stages = new int[] { Randomizer.nextInt(5), Randomizer.nextInt(8), Randomizer.nextInt(15) };
    }
    
    public long getTimeLeft() {
        return 600000L - (System.currentTimeMillis() - this.timeStarted);
    }
    
    public boolean isCharCorrect(final String portalName, final int mapid) {
        final int st = this.stages[mapid % 10 - 1];
        return portalName.equals((Object)("ch" + ((st < 10) ? "0" : "") + st));
    }
}
