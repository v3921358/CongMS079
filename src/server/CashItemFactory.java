package server;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import database.DBConPool;
import provider.MapleData;
import provider.MapleDataProvider;
import provider.MapleDataProviderFactory;
import provider.MapleDataTool;
import server.CashItemInfo.CashModInfo;
import tools.FileoutputUtil;

public class CashItemFactory
{
    private static final CashItemFactory instance;
    private static final int[] bestItems;
    private boolean initialized;
    private final Map<Integer, List<Integer>> openBox;
    private final Map<Integer, CashItemInfo> itemStats;
    private final Map<Integer, List<CashItemInfo>> itemPackage;
    private final Map<Integer, CashModInfo> itemMods;
    private final Map<Integer, Integer> itemIdToSN;
    private final Map<Integer, Integer> itemIdToSn;
    private final MapleDataProvider data;
    private final MapleDataProvider itemStringInfo;
    private Map<Integer, Integer> idLookup;
    
    public static final CashItemFactory getInstance() {
        return CashItemFactory.instance;
    }
    
    protected CashItemFactory() {
        this.initialized = false;
        this.openBox = new HashMap<Integer, List<Integer>>();
        this.itemStats = new HashMap<Integer, CashItemInfo>();
        this.itemPackage = new HashMap<Integer, List<CashItemInfo>>();
        this.itemMods = new HashMap<Integer, CashModInfo>();
        this.itemIdToSN = new HashMap<Integer, Integer>();
        this.itemIdToSn = new HashMap<Integer, Integer>();
        this.data = MapleDataProviderFactory.getDataProvider("Etc.wz");
        this.itemStringInfo = MapleDataProviderFactory.getDataProvider(new File(System.getProperty("net.sf.odinms.wzpath") + "/String.wz"));
        this.idLookup = new HashMap<Integer, Integer>();
    }
    
    public void initialize() {
        System.out.println("[正在加载] -> 商城系统加载中请耐心等待");
        final List<Integer> itemids = new ArrayList<Integer>();
        for (final MapleData field : this.data.getData("Commodity.img").getChildren()) {
            final int itemId = MapleDataTool.getIntConvert("ItemId", field, 0);
            final int SN = MapleDataTool.getIntConvert("SN", field, 0);
            final CashItemInfo stats = new CashItemInfo(itemId, MapleDataTool.getIntConvert("Count", field, 1), MapleDataTool.getIntConvert("Price", field, 0), SN, MapleDataTool.getIntConvert("Period", field, 0), MapleDataTool.getIntConvert("Gender", field, 2), MapleDataTool.getIntConvert("OnSale", field, 0) > 0, 0);
            if (SN > 0) {
                this.itemStats.put(Integer.valueOf(SN), stats);
                this.itemIdToSN.put(Integer.valueOf(stats.getId()), Integer.valueOf(SN));
            }
            if (itemId > 0) {
                itemids.add(Integer.valueOf(itemId));
            }
        }
        this.refreshAllModInfo();
        final Iterator<Integer> iterator2 = itemids.iterator();
        while (iterator2.hasNext()) {
            final int i = (int)Integer.valueOf(iterator2.next());
            this.getPackageItems(i);
        }
        final Iterator<Integer> iterator3 = this.itemStats.keySet().iterator();
        while (iterator3.hasNext()) {
            final int i = (int)Integer.valueOf(iterator3.next());
            this.getModInfo(i);
            this.getItem(i);
        }
        this.initialized = true;
    }
    
    public final int getSnByItemItd(final int itemid) {
        final int sn = (int)Integer.valueOf(this.itemIdToSN.get((Object)Integer.valueOf(itemid)));
        return sn;
    }
    
    public final int getSnByItemItd2(final int itemid) {
        final int sn = (int)Integer.valueOf(this.itemIdToSn.get((Object)Integer.valueOf(itemid)));
        return sn;
    }
    
    public final CashItemInfo getItem(final int sn) {
        final CashItemInfo stats = (CashItemInfo)this.itemStats.get((Object)Integer.valueOf(sn));
        final CashModInfo z = this.getModInfo(sn);
        if (z != null && z.showUp) {
            return z.toCItem(stats);
        }
        if (stats == null || !stats.onSale()) {
            return null;
        }
        return stats;
    }
    
    public final Set<Integer> getAllItemSNs() {
        return this.itemStats.keySet();
    }
    
    public final List<CashItemInfo> getAllItems() {
        return new ArrayList<CashItemInfo>((Collection<? extends CashItemInfo>)this.itemStats.values());
    }
    
    public final List<CashItemInfo> getPackageItems(final int itemId) {
        if (this.itemPackage.get((Object)Integer.valueOf(itemId)) != null) {
            return (List<CashItemInfo>)this.itemPackage.get((Object)Integer.valueOf(itemId));
        }
        final List<CashItemInfo> packageItems = new ArrayList<CashItemInfo>();
        final MapleData b = this.data.getData("CashPackage.img");
        if (b == null || b.getChildByPath(itemId + "/SN") == null) {
            return null;
        }
        for (final MapleData d : b.getChildByPath(itemId + "/SN").getChildren()) {
            packageItems.add(this.itemStats.get((Object)Integer.valueOf(MapleDataTool.getIntConvert(d))));
        }
        this.itemPackage.put(Integer.valueOf(itemId), packageItems);
        return packageItems;
    }
    
    public final Map<Integer, List<Integer>> getRandomItemInfo() {
        return this.openBox;
    }
    
    public final CashModInfo getModInfo(final int sn) {
        CashModInfo ret = (CashModInfo)this.itemMods.get((Object)Integer.valueOf(sn));
        if (ret == null) {
            if (this.initialized) {
                return null;
            }
            try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
                 final PreparedStatement ps = con.prepareStatement("SELECT * FROM cashshop_modified_items WHERE serial = ?")) {
                ps.setInt(1, sn);
                final ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    ret = new CashModInfo(sn, rs.getInt("discount_price"), rs.getInt("mark"), rs.getInt("showup") > 0, rs.getInt("itemid"), rs.getInt("priority"), rs.getInt("package") > 0, rs.getInt("period"), rs.getInt("gender"), rs.getInt("count"), rs.getInt("meso"), rs.getInt("unk_1"), rs.getInt("unk_2"), rs.getInt("unk_3"), rs.getInt("extra_flags"), rs.getInt("mod"));
                    this.itemMods.put(Integer.valueOf(sn), ret);
                }
                rs.close();
            }
            catch (Exception e) {
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
                e.printStackTrace();
            }
        }
        return ret;
    }
    
    private void refreshAllModInfo() {
        this.itemMods.clear();
        this.itemIdToSn.clear();
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM cashshop_modified_items");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final Integer sn = Integer.valueOf(rs.getInt("serial"));
                final CashModInfo ret = new CashModInfo((int)sn, rs.getInt("discount_price"), rs.getInt("mark"), rs.getInt("showup") > 0, rs.getInt("itemid"), rs.getInt("priority"), rs.getInt("package") > 0, rs.getInt("period"), rs.getInt("gender"), rs.getInt("count"), rs.getInt("meso"), rs.getInt("unk_1"), rs.getInt("unk_2"), rs.getInt("unk_3"), rs.getInt("extra_flags"), rs.getInt("mod"));
                this.itemMods.put(sn, ret);
                this.itemIdToSn.put(Integer.valueOf(ret.itemid), sn);
            }
            rs.close();
            ps.close();
        }
        catch (Exception e) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
            e.printStackTrace();
        }
    }
    
    public final Collection<CashModInfo> getAllModInfo() {
        if (this.itemMods.isEmpty()) {
            this.refreshAllModInfo();
        }
        return this.itemMods.values();
    }
    
    public final int[] getBestItems() {
        return CashItemFactory.bestItems;
    }
    
    public void clearItems() {
        this.refreshAllModInfo();
    }
    
    public int getSnFromId(final int itemId) {
        return (int)Integer.valueOf(this.idLookup.get((Object)Integer.valueOf(itemId)));
    }
    
    public final void clearCashShop() {
        this.itemStats.clear();
        this.itemPackage.clear();
        this.itemMods.clear();
        this.idLookup.clear();
        this.initialized = false;
        this.initialize();
    }
    
    public final int getItemSN(final int itemid) {
        for (final Entry<Integer, CashItemInfo> ci : this.itemStats.entrySet()) {
            if (((CashItemInfo)ci.getValue()).getId() == itemid) {
                return ((CashItemInfo)ci.getValue()).getSN();
            }
        }
        return 0;
    }
    
    static {
        instance = new CashItemFactory();
        bestItems = new int[] { 50100010, 50100010, 50100010, 50100010, 50100010 };
    }
}
