package client.inventory;

import java.util.HashMap;
import provider.MapleDataProviderFactory;
import provider.MapleData;
import provider.MapleDataTool;
import tools.Pair;
import java.util.Map;
import provider.MapleDataProvider;

public class PetDataFactory
{
    private static final MapleDataProvider dataRoot;
    private static final Map<Pair<Integer, Integer>, PetCommand> petCommands;
    private static final Map<Integer, Integer> petHunger;
    
    public static final PetCommand getPetCommand(final int petId, final int skillId) {
        PetCommand ret = (PetCommand)PetDataFactory.petCommands.get((Object)new Pair((Object)Integer.valueOf(petId), (Object)Integer.valueOf(skillId)));
        if (ret != null) {
            return ret;
        }
        final MapleData skillData = PetDataFactory.dataRoot.getData("Pet/" + petId + ".img");
        int prob = 0;
        int inc = 0;
        if (skillData != null) {
            prob = MapleDataTool.getInt("interact/" + skillId + "/prob", skillData, 0);
            inc = MapleDataTool.getInt("interact/" + skillId + "/inc", skillData, 0);
        }
        ret = new PetCommand(petId, skillId, prob, inc);
        PetDataFactory.petCommands.put(new Pair<Integer, Integer>(Integer.valueOf(petId), Integer.valueOf(skillId)), ret);
        return ret;
    }
    
    public static final int getHunger(final int petId) {
        Integer ret = PetDataFactory.petHunger.get((Object)Integer.valueOf(petId));
        if (ret != null) {
            return (int)ret;
        }
        final MapleData hungerData = PetDataFactory.dataRoot.getData("Pet/" + petId + ".img").getChildByPath("info/hungry");
        ret = Integer.valueOf(MapleDataTool.getInt(hungerData, 1));
        PetDataFactory.petHunger.put(Integer.valueOf(petId), ret);
        return (int)ret;
    }
    
    static {
        dataRoot = MapleDataProviderFactory.getDataProvider("Item.wz");
        petCommands = new HashMap<Pair<Integer, Integer>, PetCommand>();
        petHunger = new HashMap<Integer, Integer>();
    }
}
