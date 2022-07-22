package server;

import tools.Pair;
import client.inventory.EquipAdditions;
import java.util.EnumMap;
import java.util.Map;
import client.inventory.Equip;
import java.util.List;

public class ItemInformation
{
    public List<Integer> scrollReqs;
    public List<Integer> questItems;
    public List<Integer> incSkill;
    public short slotMax;
    public short itemMakeLevel;
    public Equip eq;
    public Map<String, Integer> equipStats;
    public double price;
    public int itemId;
    public int wholePrice;
    public int monsterBook;
    public int stateChange;
    public int meso;
    public int questId;
    public int totalprob;
    public int replaceItem;
    public int mob;
    public int cardSet;
    public int create;
    public int flag;
    public String name;
    public String desc;
    public String msg;
    public String replaceMsg;
    public String afterImage;
    public byte karmaEnabled;
    public List<StructRewardItem> rewardItems;
    public EnumMap<EquipAdditions, Pair<Integer, Integer>> equipAdditions;
    public Map<Integer, Map<String, Integer>> equipIncs;
    
    public ItemInformation() {
        this.scrollReqs = null;
        this.questItems = null;
        this.incSkill = null;
        this.eq = null;
        this.price = 0.0;
        this.rewardItems = null;
        this.equipAdditions = null;
        this.equipIncs = null;
    }
}
