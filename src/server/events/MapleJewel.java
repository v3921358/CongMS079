package server.events;

import java.util.Iterator;
import server.Timer.EventTimer;
import tools.MaplePacketCreator;
import client.MapleCharacter;
import java.util.concurrent.ScheduledFuture;

public class MapleJewel extends MapleEvent
{
    private static final long serialVersionUID = 845748950824L;
    private final long time = 600000L;
    private long timeStarted;
    private ScheduledFuture<?> fitnessSchedule;
    private ScheduledFuture<?> msgSchedule;
    
    public MapleJewel(final int channel, final int[] mapid) {
        super(channel, mapid);
        this.timeStarted = 0L;
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
                for (int i = 0; i < MapleJewel.this.mapid.length; ++i) {
                    for (final MapleCharacter chr : MapleJewel.this.getMap(i).getCharactersThreadsafe()) {
                        MapleJewel.this.warpBack(chr);
                    }
                }
                MapleJewel.this.unreset();
            }
        };
        this.getClass();
        this.fitnessSchedule = instance.schedule((Runnable)r, 600000L);
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
        if (this.fitnessSchedule != null) {
            this.fitnessSchedule.cancel(false);
        }
        this.fitnessSchedule = null;
        if (this.msgSchedule != null) {
            this.msgSchedule.cancel(false);
        }
        this.msgSchedule = null;
    }
    
    @Override
    public void reset() {
        super.reset();
        this.resetSchedule();
        this.getMap(0).getPortal("join00").setPortalState(false);
    }
    
    @Override
    public void unreset() {
        super.unreset();
        this.resetSchedule();
        this.getMap(0).getPortal("join00").setPortalState(true);
    }
    
    public long getTimeLeft() {
        return 600000L - (System.currentTimeMillis() - this.timeStarted);
    }
}
