package handling.world.family;

import java.util.concurrent.ScheduledFuture;
import server.Timer.BuffTimer;
import server.MapleStatEffect.CancelEffectAction;
import server.MapleItemInformationProvider;
import server.MapleStatEffect;
import tools.MaplePacketCreator;
import client.MapleCharacter;
import client.MapleBuffStat;
import tools.Pair;
import java.util.ArrayList;
import java.util.List;

public class MapleFamilyBuff
{
    private static final int event = 2;
    private static final int[] type;
    private static final int[] duration;
    private static final int[] effect;
    private static final int[] rep;
    private static final String[] name;
    private static final String[] desc;
    private static final List<MapleFamilyBuffEntry> buffEntries;
    
    public static List<MapleFamilyBuffEntry> getBuffEntry() {
        return MapleFamilyBuff.buffEntries;
    }
    
    public static MapleFamilyBuffEntry getBuffEntry(final int i) {
        return (MapleFamilyBuffEntry)MapleFamilyBuff.buffEntries.get(i);
    }
    
    static {
        type = new int[] { 0, 1, 2, 3, 4, 2, 3, 2, 3, 2, 3 };
        duration = new int[] { 0, 0, 15, 15, 30, 15, 15, 30, 30, 30, 30 };
        effect = new int[] { 0, 0, 150, 150, 200, 200, 200, 200, 200, 200, 200 };
        rep = new int[] { 0, 0, 300, 500, 700, 800, 1000, 1200, 1500, 2000, 2500, 4000, 5000 };
        name = new String[] { "立刻移動至家族成員", "立刻召喚家族成員" };
        desc = new String[] { "[對象] 自己\n[效果] 移動到想要的上線家族成員所在地图。", "[對象] 1個家族對象\n[效果] 召喚指定的上線家族成員到自己所在的地图。" };
        buffEntries = new ArrayList<MapleFamilyBuffEntry>();
        for (int i = 0; i < 2; ++i) {
            MapleFamilyBuff.buffEntries.add(new MapleFamilyBuffEntry(i, MapleFamilyBuff.name[i], MapleFamilyBuff.desc[i], 1, MapleFamilyBuff.rep[i], MapleFamilyBuff.type[i], 190000 + i, MapleFamilyBuff.duration[i], MapleFamilyBuff.effect[i]));
        }
    }
    
    public static class MapleFamilyBuffEntry
    {
        public String name;
        public String desc;
        public int count;
        public int rep;
        public int type;
        public int index;
        public int questID;
        public int duration;
        public int effect;
        public List<Pair<MapleBuffStat, Integer>> effects;
        
        public MapleFamilyBuffEntry(final int index, final String name, final String desc, final int count, final int rep, final int type, final int questID, final int duration, final int effect) {
            this.name = name;
            this.desc = desc;
            this.count = count;
            this.rep = rep;
            this.type = type;
            this.questID = questID;
            this.index = index;
            this.duration = duration;
            this.effect = effect;
            this.effects = this.getEffects();
        }
        
        public int getEffectId() {
            switch (this.type) {
                case 2: {
                    return 2022694;
                }
                case 3: {
                    return 2450018;
                }
                default: {
                    return 2022332;
                }
            }
        }
        
        public final List<Pair<MapleBuffStat, Integer>> getEffects() {
            final List<Pair<MapleBuffStat, Integer>> ret = new ArrayList<Pair<MapleBuffStat, Integer>>();
            switch (this.type) {
                case 2: {
                    ret.add(new Pair<MapleBuffStat, Integer>(MapleBuffStat.DROP_RATE, Integer.valueOf(this.effect)));
                    ret.add(new Pair<MapleBuffStat, Integer>(MapleBuffStat.MESO_RATE, Integer.valueOf(this.effect)));
                    break;
                }
                case 3: {
                    ret.add(new Pair<MapleBuffStat, Integer>(MapleBuffStat.EXPRATE, Integer.valueOf(this.effect)));
                    break;
                }
                case 4: {
                    ret.add(new Pair<MapleBuffStat, Integer>(MapleBuffStat.EXPRATE, Integer.valueOf(this.effect)));
                    ret.add(new Pair<MapleBuffStat, Integer>(MapleBuffStat.DROP_RATE, Integer.valueOf(this.effect)));
                    ret.add(new Pair<MapleBuffStat, Integer>(MapleBuffStat.MESO_RATE, Integer.valueOf(this.effect)));
                    break;
                }
            }
            return ret;
        }
        
        public void applyTo(final MapleCharacter chr) {
            chr.getClient().sendPacket(MaplePacketCreator.giveBuff(-this.getEffectId(), this.duration * 60000, this.effects, null));
            final MapleStatEffect eff = MapleItemInformationProvider.getInstance().getItemEffect(this.getEffectId());
            chr.cancelEffect(eff, true, -1L, this.effects);
            final long starttime = System.currentTimeMillis();
            final CancelEffectAction cancelAction = new CancelEffectAction(chr, eff, starttime);
            final ScheduledFuture<?> schedule = BuffTimer.getInstance().schedule((Runnable)cancelAction, starttime + (long)(this.duration * 60000) - starttime);
            chr.registerEffect(eff, starttime, schedule, this.effects, false, this.duration, chr.getId());
        }
    }
}
