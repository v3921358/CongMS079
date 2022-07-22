package handling.world;

import java.util.concurrent.ConcurrentHashMap;
import client.MapleDiseaseValueHolder;
import client.MapleCoolDownValueHolder;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

public class PlayerBuffStorage implements Serializable
{
    private static final Map<Integer, List<PlayerBuffValueHolder>> buffs;
    private static final Map<Integer, List<MapleCoolDownValueHolder>> coolDowns;
    private static final Map<Integer, List<MapleDiseaseValueHolder>> diseases;
    
    public static final void addBuffsToStorage(final int chrid, final List<PlayerBuffValueHolder> toStore) {
        PlayerBuffStorage.buffs.put(Integer.valueOf(chrid), toStore);
    }
    
    public static final void addCooldownsToStorage(final int chrid, final List<MapleCoolDownValueHolder> toStore) {
        PlayerBuffStorage.coolDowns.put(Integer.valueOf(chrid), toStore);
    }
    
    public static final void addDiseaseToStorage(final int chrid, final List<MapleDiseaseValueHolder> toStore) {
        PlayerBuffStorage.diseases.put(Integer.valueOf(chrid), toStore);
    }
    
    public static final List<PlayerBuffValueHolder> getBuffsFromStorage(final int chrid) {
        return (List<PlayerBuffValueHolder>)PlayerBuffStorage.buffs.remove((Object)Integer.valueOf(chrid));
    }
    
    public static final List<MapleCoolDownValueHolder> getCooldownsFromStorage(final int chrid) {
        return (List<MapleCoolDownValueHolder>)PlayerBuffStorage.coolDowns.remove((Object)Integer.valueOf(chrid));
    }
    
    public static final List<MapleDiseaseValueHolder> getDiseaseFromStorage(final int chrid) {
        return (List<MapleDiseaseValueHolder>)PlayerBuffStorage.diseases.remove((Object)Integer.valueOf(chrid));
    }
    
    static {
        buffs = new ConcurrentHashMap<Integer, List<PlayerBuffValueHolder>>();
        coolDowns = new ConcurrentHashMap<Integer, List<MapleCoolDownValueHolder>>();
        diseases = new ConcurrentHashMap<Integer, List<MapleDiseaseValueHolder>>();
    }
}
