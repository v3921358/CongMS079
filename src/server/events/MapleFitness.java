package server.events;

import java.util.Iterator;
import server.Timer.EventTimer;
import tools.MaplePacketCreator;
import client.MapleCharacter;
import java.util.concurrent.ScheduledFuture;

public class MapleFitness extends MapleEvent
{
    private static final long serialVersionUID = 845748950824L;
    private final long time = 600000L;
    private long timeStarted;
    private ScheduledFuture<?> fitnessSchedule;
    private ScheduledFuture<?> msgSchedule;
    
    public MapleFitness(final int channel, final int[] mapid) {
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
        this.checkAndMessage();
        final EventTimer instance = EventTimer.getInstance();
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < MapleFitness.this.mapid.length; ++i) {
                    for (final MapleCharacter chr : MapleFitness.this.getMap(i).getCharactersThreadsafe()) {
                        MapleFitness.this.warpBack(chr);
                    }
                }
                MapleFitness.this.unreset();
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
        this.getMap(0).getPortal("in00").setPortalState(false);
    }
    
    @Override
    public void unreset() {
        super.unreset();
        this.resetSchedule();
        this.getMap(0).getPortal("join00").setPortalState(true);
        this.getMap(0).getPortal("in00").setPortalState(true);
    }
    
    public long getTimeLeft() {
        return 600000L - (System.currentTimeMillis() - this.timeStarted);
    }
    
    public void checkAndMessage() {
        this.msgSchedule = EventTimer.getInstance().register((Runnable)new Runnable() {
            @Override
            public void run() {
                final long timeLeft = MapleFitness.this.getTimeLeft();
                if (timeLeft > 9000L && timeLeft < 11000L) {
                    MapleFitness.this.broadcast(MaplePacketCreator.serverNotice(0, "你還有10秒左右的时间，那些你不能擊敗的玩家，我希望你下次贏得勝利，回頭見。"));
                }
                else if (timeLeft > 11000L && timeLeft < 101000L) {
                    MapleFitness.this.broadcast(MaplePacketCreator.serverNotice(0, "好吧，你剩下沒有多少时间了，請抓緊时间衝向終點。"));
                }
                else if (timeLeft > 101000L && timeLeft < 241000L) {
                    MapleFitness.this.broadcast(MaplePacketCreator.serverNotice(0, "這已經是最後了不要放棄，豐富的大獎等著你！"));
                }
                else if (timeLeft > 241000L && timeLeft < 301000L) {
                    MapleFitness.this.broadcast(MaplePacketCreator.serverNotice(0, "這跳完就剩下一階了加油！"));
                }
                else if (timeLeft > 301000L && timeLeft < 361000L) {
                    MapleFitness.this.broadcast(MaplePacketCreator.serverNotice(0, "請小心掉落。"));
                }
                else if (timeLeft > 361000L && timeLeft < 501000L) {
                    MapleFitness.this.broadcast(MaplePacketCreator.serverNotice(0, "請小心HP歸零。"));
                }
                else if (timeLeft > 501000L && timeLeft < 601000L) {
                    MapleFitness.this.broadcast(MaplePacketCreator.serverNotice(0, "請小心猴子。"));
                }
                else if (timeLeft > 601000L && timeLeft < 661000L) {
                    MapleFitness.this.broadcast(MaplePacketCreator.serverNotice(0, "第二階的技巧請利用猴子。"));
                }
                else if (timeLeft > 661000L && timeLeft < 701000L) {
                    MapleFitness.this.broadcast(MaplePacketCreator.serverNotice(0, "請小心HP歸零。"));
                }
                else if (timeLeft > 701000L && timeLeft < 781000L) {
                    MapleFitness.this.broadcast(MaplePacketCreator.serverNotice(0, "大家知道 [終極忍耐] 很好玩的！"));
                }
                else if (timeLeft > 781000L && timeLeft < 841000L) {
                    MapleFitness.this.broadcast(MaplePacketCreator.serverNotice(0, "有可能会小LAG一下不過不需要擔心。"));
                }
                else if (timeLeft > 841000L) {
                    MapleFitness.this.broadcast(MaplePacketCreator.serverNotice(0, "[終極忍耐] 總共有四階，如果你碰巧在游戏過程中死亡，你会從游戏中消失，所以請注意這一點。"));
                }
            }
        }, 90000L);
    }
}
