package client;

import java.util.concurrent.ScheduledFuture;
import server.MapleStatEffect;

public class MapleBuffStatValueHolder
{
    public MapleStatEffect effect;
    public long startTime;
    public int value;
    public int localDuration;
    public int cid;
    public int skillid;
    public ScheduledFuture<?> schedule;
    
    public MapleBuffStatValueHolder(final MapleStatEffect effect, final long startTime, final ScheduledFuture<?> schedule, final int value, final int localDuration, final int cid, final int skillid) {
        this.effect = effect;
        this.startTime = startTime;
        this.schedule = schedule;
        this.value = value;
        this.localDuration = localDuration;
        this.cid = cid;
        this.skillid = skillid;
    }
}
