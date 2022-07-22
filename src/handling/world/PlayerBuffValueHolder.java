package handling.world;

import client.MapleBuffStat;
import tools.Pair;
import java.util.List;
import server.MapleStatEffect;
import java.io.Serializable;

public class PlayerBuffValueHolder implements Serializable
{
    private static final long serialVersionUID = 9179541993413738569L;
    public long startTime;
    public int localDuration;
    public int cid;
    public MapleStatEffect effect;
    public List<Pair<MapleBuffStat, Integer>> statup;
    
    public PlayerBuffValueHolder(final long startTime, final MapleStatEffect effect, final List<Pair<MapleBuffStat, Integer>> statup, final int localDuration, final int cid) {
        this.startTime = startTime;
        this.effect = effect;
        this.statup = statup;
        this.localDuration = localDuration;
        this.cid = cid;
    }
}
