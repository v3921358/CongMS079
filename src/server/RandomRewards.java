package server;

import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import database.DatabaseConnection;
import java.util.Collections;
import constants.GameConstants;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class RandomRewards
{
    private static final RandomRewards instance;
    private List<Integer> compiledGold;
    private List<Integer> compiledSilver;
    private List<Integer> compiledJxbox;
    private List<Integer> compiledFishing;
    private List<Integer> compiledEvent;
    private List<Integer> compiledEventC;
    private List<Integer> compiledEventB;
    private List<Integer> compiledEventA;
    public Map<Integer, String> FishingRewardItemNameMap;
    
    public void refreshFishingRewards() {
        this.FishingRewardItemNameMap.clear();
        this.compiledFishing.clear();
        this.getFishingRewards();
        System.out.println("[正在加载] -> 已加载随机奖励物品 " + this.compiledFishing.size());
    }
    
    private void getFishingRewards() {
        this.FishingRewardItemNameMap = this.loadFishingRewardsMapFromDb();
        final List<Integer> list = new ArrayList<Integer>();
        this.processRewards(list, this.loadFishingRewardFromDb());
        this.compiledFishing = list;
        System.out.println("[正在加载] -> 已加载随机奖励物品 " + this.compiledFishing.size());
    }
    
    public static RandomRewards getInstance() {
        return RandomRewards.instance;
    }
    
    protected RandomRewards() {
        this.compiledGold = null;
        this.compiledSilver = null;
        this.compiledJxbox = null;
        this.compiledFishing = null;
        this.compiledEvent = null;
        this.compiledEventC = null;
        this.compiledEventB = null;
        this.compiledEventA = null;
        this.FishingRewardItemNameMap = null;
        this.getFishingRewards();
        List<Integer> returnArray = new ArrayList<Integer>();
        this.processRewards(returnArray, GameConstants.goldrewards);
        this.compiledGold = returnArray;
        returnArray = new ArrayList<Integer>();
        this.processRewards(returnArray, GameConstants.silverrewards);
        this.compiledSilver = returnArray;
        returnArray = new ArrayList<Integer>();
        this.processRewards(returnArray, GameConstants.eventCommonReward);
        this.compiledEventC = returnArray;
        returnArray = new ArrayList<Integer>();
        this.processRewards(returnArray, GameConstants.eventUncommonReward);
        this.compiledEventB = returnArray;
        returnArray = new ArrayList<Integer>();
        this.processRewards(returnArray, GameConstants.eventRareReward);
        this.compiledEventA = returnArray;
        returnArray = new ArrayList<Integer>();
        this.processRewards(returnArray, GameConstants.eventSuperReward);
        this.compiledEvent = returnArray;
    }
    
    private final void processRewards(final List<Integer> returnArray, final int[] list) {
        int lastitem = 0;
        for (int i = 0; i < list.length; ++i) {
            if (i % 2 == 0) {
                lastitem = list[i];
            }
            else {
                for (int j = 0; j < list[i]; ++j) {
                    returnArray.add(Integer.valueOf(lastitem));
                }
            }
        }
        Collections.shuffle(returnArray);
    }
    
    public final int getGoldBoxReward() {
        return (int)Integer.valueOf(this.compiledGold.get(Randomizer.nextInt(this.compiledGold.size())));
    }
    
    public final int getSilverBoxReward() {
        return (int)Integer.valueOf(this.compiledSilver.get(Randomizer.nextInt(this.compiledSilver.size())));
    }
    
    public final int getFishingReward() {
        return (int)Integer.valueOf(this.compiledFishing.get(Randomizer.nextInt(this.compiledFishing.size())));
    }
    
    public final int getJxBoxReward() {
        return (int)Integer.valueOf(this.compiledJxbox.get(Randomizer.nextInt(this.compiledJxbox.size())));
    }
    
    public final int getEventReward() {
        final int chance = Randomizer.nextInt(100);
        if (chance < 50) {
            return (int)Integer.valueOf(this.compiledEventC.get(Randomizer.nextInt(this.compiledEventC.size())));
        }
        if (chance < 80) {
            return (int)Integer.valueOf(this.compiledEventB.get(Randomizer.nextInt(this.compiledEventB.size())));
        }
        if (chance < 95) {
            return (int)Integer.valueOf(this.compiledEventA.get(Randomizer.nextInt(this.compiledEventA.size())));
        }
        return (int)Integer.valueOf(this.compiledEvent.get(Randomizer.nextInt(this.compiledEvent.size())));
    }
    
    public static int[] toIntArray(final List<Integer> list) {
        final int[] ret = new int[list.size()];
        for (int i = 0; i < ret.length; ++i) {
            ret[i] = (int)Integer.valueOf(list.get(i));
        }
        return ret;
    }
    
    public int[] loadFishingRewardFromDb() {
        final List<Integer> data = new ArrayList<Integer>();
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT itemid, chance FROM 钓鱼物品 WHERE expiration = 1");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                data.add(Integer.valueOf(rs.getInt("itemid")));
                data.add(Integer.valueOf(rs.getInt("chance")));
            }
            rs.close();
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("查询钓鱼奖励数据出错 - 数据库查询失败：" + (Object)Ex);
        }
        return toIntArray(data);
    }
    
    private Map<Integer, String> loadFishingRewardsMapFromDb() {
        final Map<Integer, String> data = new HashMap<Integer, String>();
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT itemid, name FROM 钓鱼物品 WHERE expiration = 1");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                data.put(Integer.valueOf(rs.getInt("itemid")), rs.getString("name"));
            }
            rs.close();
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("查询钓鱼奖励数据出错2 - 数据库查询失败：" + (Object)Ex);
        }
        return data;
    }
    
    static {
        instance = new RandomRewards();
    }
}
