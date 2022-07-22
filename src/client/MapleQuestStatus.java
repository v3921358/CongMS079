package client;

import server.life.MapleLifeFactory;
import tools.FileoutputUtil;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.LinkedHashMap;
import constants.GameConstants;
import java.util.Map;
import server.quest.MapleQuest;
import java.io.Serializable;

public class MapleQuestStatus implements Serializable
{
    private static final long serialVersionUID = 91795419934134L;
    private final transient MapleQuest quest;
    private byte status;
    private Map<Integer, Integer> killedMobs;
    private int npc;
    private long completionTime;
    private int forfeited;
    private String customData;
    
    public MapleQuestStatus(final MapleQuest quest, final int status) {
        this.killedMobs = null;
        this.forfeited = 0;
        this.quest = quest;
        this.setStatus((byte)status);
        this.completionTime = System.currentTimeMillis();
        if (status == 1 && !quest.getRelevantMobs().isEmpty()) {
            this.registerMobs();
        }
    }
    
    public MapleQuestStatus(final MapleQuest quest, final byte status, final int npc) {
        this.killedMobs = null;
        this.forfeited = 0;
        this.quest = quest;
        this.setStatus(status);
        this.setNpc(npc);
        this.completionTime = System.currentTimeMillis();
        if (status == 1 && !quest.getRelevantMobs().isEmpty()) {
            this.registerMobs();
        }
    }
    
    public final MapleQuest getQuest() {
        return this.quest;
    }
    
    public final byte getStatus() {
        return this.status;
    }
    
    public final void setStatus(final byte status) {
        this.status = status;
    }
    
    public final int getNpc() {
        return this.npc;
    }
    
    public final void setNpc(final int npc) {
        this.npc = npc;
    }
    
    public boolean isCustom() {
        return GameConstants.isCustomQuest(this.quest.getId());
    }
    
    private void registerMobs() {
        this.killedMobs = new LinkedHashMap<Integer, Integer>();
        final Iterator<Integer> iterator = this.quest.getRelevantMobs().keySet().iterator();
        while (iterator.hasNext()) {
            final int i = (int)Integer.valueOf(iterator.next());
            this.killedMobs.put(Integer.valueOf(i), Integer.valueOf(0));
        }
    }
    
    private int maxMob(final int mobid) {
        for (final Entry<Integer, Integer> qs : this.quest.getRelevantMobs().entrySet()) {
            if ((int)Integer.valueOf(qs.getKey()) == mobid) {
                return (int)Integer.valueOf(qs.getValue());
            }
        }
        return 0;
    }
    
    public final boolean mobKilled(final int id, final int skillID) {
        try {
            if (this.quest != null && this.quest.getSkillID() > 0 && this.quest.getSkillID() != skillID) {
                return false;
            }
            final Integer mob = this.killedMobs.get((Object)Integer.valueOf(id));
            if (mob != null) {
                final int mo = this.maxMob(id);
                if ((int)mob >= mo) {
                    return false;
                }
                this.killedMobs.put(Integer.valueOf(id), Integer.valueOf(Math.min((int)mob + 1, mo)));
                return true;
            }
            else {
                for (final Entry<Integer, Integer> mo2 : this.killedMobs.entrySet()) {
                    if (this.questCount((int)Integer.valueOf(mo2.getKey()), id)) {
                        final int mobb = this.maxMob((int)Integer.valueOf(mo2.getKey()));
                        if ((int)Integer.valueOf(mo2.getValue()) >= mobb) {
                            return false;
                        }
                        this.killedMobs.put(mo2.getKey(), Integer.valueOf(Math.min((int)Integer.valueOf(mo2.getValue()) + 1, mobb)));
                        return true;
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            FileoutputUtil.outError("logs/殺死怪物計次異常.txt", (Throwable)ex);
        }
        return false;
    }
    
    private boolean questCount(final int mo, final int id) {
        if (MapleLifeFactory.getQuestCount(mo) != null) {
            final Iterator<Integer> iterator = MapleLifeFactory.getQuestCount(mo).iterator();
            while (iterator.hasNext()) {
                final int i = (int)Integer.valueOf(iterator.next());
                if (i == id) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public final void setMobKills(final int id, final int count) {
        if (this.killedMobs == null) {
            this.registerMobs();
        }
        this.killedMobs.put(Integer.valueOf(id), Integer.valueOf(count));
    }
    
    public final boolean hasMobKills() {
        return this.killedMobs != null && this.killedMobs.size() > 0;
    }
    
    public final int getMobKills(final int id) {
        final Integer mob = this.killedMobs.get((Object)Integer.valueOf(id));
        if (mob == null) {
            return 0;
        }
        return (int)mob;
    }
    
    public final Map<Integer, Integer> getMobKills() {
        return this.killedMobs;
    }
    
    public final long getCompletionTime() {
        return this.completionTime;
    }
    
    public final void setCompletionTime(final long completionTime) {
        this.completionTime = completionTime;
    }
    
    public final int getForfeited() {
        return this.forfeited;
    }
    
    public final void setForfeited(final int forfeited) {
        if (forfeited >= this.forfeited) {
            this.forfeited = forfeited;
            return;
        }
        throw new IllegalArgumentException("Can't set forfeits to something lower than before.");
    }
    
    public final void setCustomData(final String customData) {
        this.customData = customData;
    }
    
    public final String getCustomData() {
        return this.customData;
    }
}
