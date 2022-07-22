package server;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import tools.FileoutputUtil;
import database.DBConPool;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import tools.Pair;
import java.util.List;

public class FishingRewardFactory
{
    private final List<Pair<Long, FishingReward>> rewards;
    private Long total;
    private final Random rand;
    private final int[] typesChance;
    private final int[] typesChanceAcc;
    private final int typesChanceTotal = 100;
    private static final FishingRewardFactory instance;
    
    public FishingRewardFactory() {
        this.total = Long.valueOf(0L);
        this.typesChance = new int[] { 40, 40, 20 };
        this.typesChanceAcc = new int[] { 40, 80, 100 };
        System.out.println("[正在加载] -> 钓鱼系统物品道具奖励");
        this.rewards = new LinkedList<Pair<Long, FishingReward>>();
        this.rand = new Random(System.currentTimeMillis());
        this.loadItems();
    }
    
    public static FishingRewardFactory getInstance() {
        return FishingRewardFactory.instance;
    }
    
    public int getNextRewardType() {
        final Random rand = this.rand;
        this.getClass();
        final Integer n = Integer.valueOf(rand.nextInt(100));
        for (int i = 0; i < 3; ++i) {
            if ((int)n <= this.typesChanceAcc[i]) {
                return i;
            }
        }
        return 0;
    }
    
    public FishingReward getNextRewardItemId() {
        if (this.rewards.isEmpty()) {
            this.loadItems();
        }
        final Iterator<Pair<Long, FishingReward>> iterator = this.rewards.iterator();
        if ((long)this.total != 0L) {
            final Long n = Long.valueOf(Math.abs(this.rand.nextLong() * System.currentTimeMillis() + 47L * System.currentTimeMillis()) % (long)this.total);
            while (iterator.hasNext()) {
                final Pair<Long, FishingReward> c = (Pair<Long, FishingReward>)iterator.next();
                if ((long)n <= (long)Long.valueOf(c.left)) {
                    return (FishingReward)c.right;
                }
            }
        }
        return null;
    }
    
    public void reloadItems() {
        this.loadItems();
    }
    
    private void loadItems() {
        this.rewards.clear();
        Long acc = Long.valueOf(0L);
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT * FROM 钓鱼物品 ORDER BY chance ASC");
             final ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                final int itemid = rs.getInt("itemid");
                final int chance = rs.getInt("chance");
                final int expirtaion = rs.getInt("expiration");
                acc = Long.valueOf((long)acc + (long)chance);
                this.rewards.add(new Pair<Long, FishingReward>(acc, new FishingReward(itemid, expirtaion)));
            }
        }
        catch (SQLException e) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
        }
        this.total = acc;
    }
    
    static {
        instance = new FishingRewardFactory();
    }
    
    public class FishingReward
    {
        private final int itemid;
        private final int expiration;
        
        public FishingReward(final int itemid, final int expiration) {
            this.expiration = expiration;
            this.itemid = itemid;
        }
        
        public int getItemId() {
            return this.itemid;
        }
        
        public int getExpiration() {
            return this.expiration;
        }
    }
}
