package server;

import java.util.ArrayList;
import tools.Pair;
import java.util.List;
import java.util.Iterator;
import provider.MapleDataTool;
import provider.MapleData;
import provider.MapleDataProviderFactory;
import java.util.HashMap;
import java.util.Map;

public class ItemMakerFactory
{
    private static final ItemMakerFactory instance;
    protected Map<Integer, ItemMakerCreateEntry> createCache;
    protected Map<Integer, GemCreateEntry> gemCache;
    
    public static ItemMakerFactory getInstance() {
        return ItemMakerFactory.instance;
    }
    
    protected ItemMakerFactory() {
        this.createCache = new HashMap<Integer, ItemMakerCreateEntry>();
        this.gemCache = new HashMap<Integer, GemCreateEntry>();
        System.out.println("[正在加载] -> 其它物品信息");
        final MapleData info = MapleDataProviderFactory.getDataProvider("Etc.wz").getData("ItemMake.img");
        for (final MapleData dataType : info.getChildren()) {
            final int type = Integer.parseInt(dataType.getName());
            switch (type) {
                case 0: {
                    for (final MapleData itemFolder : dataType.getChildren()) {
                        final int reqLevel = MapleDataTool.getInt("reqLevel", itemFolder, 0);
                        final byte reqMakerLevel = (byte)MapleDataTool.getInt("reqSkillLevel", itemFolder, 0);
                        final int cost = MapleDataTool.getInt("meso", itemFolder, 0);
                        final int quantity = MapleDataTool.getInt("itemNum", itemFolder, 0);
                        final GemCreateEntry ret = new GemCreateEntry(cost, reqLevel, (int)reqMakerLevel, quantity);
                        for (final MapleData rewardNRecipe : itemFolder.getChildren()) {
                            for (final MapleData ind : rewardNRecipe.getChildren()) {
                                if (rewardNRecipe.getName().equals((Object)"randomReward")) {
                                    ret.addRandomReward(MapleDataTool.getInt("item", ind, 0), MapleDataTool.getInt("prob", ind, 0));
                                }
                                else {
                                    if (!rewardNRecipe.getName().equals((Object)"recipe")) {
                                        continue;
                                    }
                                    ret.addReqRecipe(MapleDataTool.getInt("item", ind, 0), MapleDataTool.getInt("count", ind, 0));
                                }
                            }
                        }
                        this.gemCache.put(Integer.valueOf(Integer.parseInt(itemFolder.getName())), ret);
                    }
                    continue;
                }
                case 1:
                case 2:
                case 4:
                case 8:
                case 16: {
                    for (final MapleData itemFolder : dataType.getChildren()) {
                        final int reqLevel = MapleDataTool.getInt("reqLevel", itemFolder, 0);
                        final byte reqMakerLevel = (byte)MapleDataTool.getInt("reqSkillLevel", itemFolder, 0);
                        final int cost = MapleDataTool.getInt("meso", itemFolder, 0);
                        final int quantity = MapleDataTool.getInt("itemNum", itemFolder, 0);
                        final byte totalupgrades = (byte)MapleDataTool.getInt("tuc", itemFolder, 0);
                        final int stimulator = MapleDataTool.getInt("catalyst", itemFolder, 0);
                        final ItemMakerCreateEntry imt = new ItemMakerCreateEntry(cost, reqLevel, reqMakerLevel, quantity, totalupgrades, stimulator);
                        for (final MapleData Recipe : itemFolder.getChildren()) {
                            for (final MapleData ind : Recipe.getChildren()) {
                                if (Recipe.getName().equals((Object)"recipe")) {
                                    imt.addReqItem(MapleDataTool.getInt("item", ind, 0), MapleDataTool.getInt("count", ind, 0));
                                }
                            }
                        }
                        this.createCache.put(Integer.valueOf(Integer.parseInt(itemFolder.getName())), imt);
                    }
                    continue;
                }
            }
        }
    }
    
    public GemCreateEntry getGemInfo(final int itemid) {
        return (GemCreateEntry)this.gemCache.get((Object)Integer.valueOf(itemid));
    }
    
    public ItemMakerCreateEntry getCreateInfo(final int itemid) {
        return (ItemMakerCreateEntry)this.createCache.get((Object)Integer.valueOf(itemid));
    }
    
    static {
        instance = new ItemMakerFactory();
    }
    
    public static class GemCreateEntry
    {
        private int reqLevel;
        private int reqMakerLevel;
        private int cost;
        private int quantity;
        private List<Pair<Integer, Integer>> randomReward;
        private List<Pair<Integer, Integer>> reqRecipe;
        
        public GemCreateEntry(final int cost, final int reqLevel, final int reqMakerLevel, final int quantity) {
            this.randomReward = new ArrayList<Pair<Integer, Integer>>();
            this.reqRecipe = new ArrayList<Pair<Integer, Integer>>();
            this.cost = cost;
            this.reqLevel = reqLevel;
            this.reqMakerLevel = reqMakerLevel;
            this.quantity = quantity;
        }
        
        public int getRewardAmount() {
            return this.quantity;
        }
        
        public List<Pair<Integer, Integer>> getRandomReward() {
            return this.randomReward;
        }
        
        public List<Pair<Integer, Integer>> getReqRecipes() {
            return this.reqRecipe;
        }
        
        public int getReqLevel() {
            return this.reqLevel;
        }
        
        public int getReqSkillLevel() {
            return this.reqMakerLevel;
        }
        
        public int getCost() {
            return this.cost;
        }
        
        protected void addRandomReward(final int itemId, final int prob) {
            this.randomReward.add(new Pair<Integer, Integer>(Integer.valueOf(itemId), Integer.valueOf(prob)));
        }
        
        protected void addReqRecipe(final int itemId, final int count) {
            this.reqRecipe.add(new Pair<Integer, Integer>(Integer.valueOf(itemId), Integer.valueOf(count)));
        }
    }
    
    public static class ItemMakerCreateEntry
    {
        private int reqLevel;
        private int cost;
        private int quantity;
        private int stimulator;
        private byte tuc;
        private byte reqMakerLevel;
        private List<Pair<Integer, Integer>> reqItems;
        private List<Integer> reqEquips;
        
        public ItemMakerCreateEntry(final int cost, final int reqLevel, final byte reqMakerLevel, final int quantity, final byte tuc, final int stimulator) {
            this.reqItems = new ArrayList<Pair<Integer, Integer>>();
            this.reqEquips = new ArrayList<Integer>();
            this.cost = cost;
            this.tuc = tuc;
            this.reqLevel = reqLevel;
            this.reqMakerLevel = reqMakerLevel;
            this.quantity = quantity;
            this.stimulator = stimulator;
        }
        
        public byte getTUC() {
            return this.tuc;
        }
        
        public int getRewardAmount() {
            return this.quantity;
        }
        
        public List<Pair<Integer, Integer>> getReqItems() {
            return this.reqItems;
        }
        
        public List<Integer> getReqEquips() {
            return this.reqEquips;
        }
        
        public int getReqLevel() {
            return this.reqLevel;
        }
        
        public byte getReqSkillLevel() {
            return this.reqMakerLevel;
        }
        
        public int getCost() {
            return this.cost;
        }
        
        public int getStimulator() {
            return this.stimulator;
        }
        
        protected void addReqItem(final int itemId, final int amount) {
            this.reqItems.add(new Pair<Integer, Integer>(Integer.valueOf(itemId), Integer.valueOf(amount)));
        }
    }
}
