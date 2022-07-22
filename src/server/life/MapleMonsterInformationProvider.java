package server.life;

import java.util.LinkedList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import tools.FileoutputUtil;
import database.DBConPool;
import java.util.Iterator;
import provider.MapleDataProvider;
import provider.MapleDataTool;
import provider.MapleData;
import provider.MapleDataProviderFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapleMonsterInformationProvider
{
    private static final MapleMonsterInformationProvider instance;
    private final Map<Integer, String> mobCache;
    private final Map<Integer, List<MonsterDropEntry>> drops;
    private final List<MonsterGlobalDropEntry> globaldrops;
    
    protected MapleMonsterInformationProvider() {
        this.mobCache = new HashMap<Integer, String>();
        this.drops = new HashMap<Integer, List<MonsterDropEntry>>();
        this.globaldrops = new ArrayList<MonsterGlobalDropEntry>();
        this.retrieveGlobal();
    }
    
    public static final MapleMonsterInformationProvider getInstance() {
        return MapleMonsterInformationProvider.instance;
    }
    
    public final List<MonsterGlobalDropEntry> getGlobalDrop() {
        return this.globaldrops;
    }
    
    public Map<Integer, String> getAllMonsters() {
        if (this.mobCache.isEmpty()) {
            final MapleDataProvider stringData = MapleDataProviderFactory.getDataProvider("String.wz");
            final MapleData mobsData = stringData.getData("Mob.img");
            for (final MapleData itemFolder : mobsData.getChildren()) {
                this.mobCache.put(Integer.valueOf(Integer.parseInt(itemFolder.getName())), MapleDataTool.getString("name", itemFolder, "NO-NAME"));
            }
        }
        return this.mobCache;
    }
    
    private void retrieveGlobal() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            ps = con.prepareStatement("SELECT * FROM drop_data_global WHERE chance > 0");
            rs = ps.executeQuery();
            while (rs.next()) {
                this.globaldrops.add(new MonsterGlobalDropEntry(rs.getInt("itemid"), rs.getInt("chance"), rs.getInt("continent"), rs.getByte("dropType"), rs.getInt("minimum_quantity"), rs.getInt("maximum_quantity"), rs.getShort("questid")));
            }
            rs.close();
            ps.close();
        }
        catch (SQLException e) {
            System.err.println("Error retrieving drop" + (Object)e);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            }
            catch (SQLException ignore) {
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ignore);
            }
        }
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            }
            catch (SQLException ignore2) {
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ignore2);
            }
        }
    }
    
    public final List<MonsterDropEntry> retrieveDrop(final int monsterId) {
        if (this.drops.containsKey((Object)Integer.valueOf(monsterId))) {
            return (List<MonsterDropEntry>)this.drops.get((Object)Integer.valueOf(monsterId));
        }
        final List<MonsterDropEntry> ret = new LinkedList<MonsterDropEntry>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
                ps = con.prepareStatement("SELECT * FROM drop_data WHERE dropperid = ?");
                ps.setInt(1, monsterId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    final int itemid = rs.getInt("itemid");
                    final int chance = rs.getInt("chance");
                    ret.add(new MonsterDropEntry(itemid, chance, rs.getInt("minimum_quantity"), rs.getInt("maximum_quantity"), rs.getShort("questid")));
                }
            }
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            }
            catch (SQLException ignore) {
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ignore);
                return ret;
            }
        }
        catch (SQLException e) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
            return ret;
        }
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            }
            catch (SQLException ignore2) {
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ignore2);
                return ret;
            }
        }
        this.drops.put(Integer.valueOf(monsterId), ret);
        return ret;
    }
    
    public final void clearDrops() {
        this.drops.clear();
        this.globaldrops.clear();
        this.retrieveGlobal();
    }
    
    public int getDropQuest(final int monsterId) {
        int quest = 0;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("SELECT questid FROM drop_data where dropperid = ?");
            ps.setInt(1, monsterId);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                quest = rs.getInt("questid");
            }
        }
        catch (SQLException e) {
            System.out.println("Error getDropQuest" + (Object)e);
            FileoutputUtil.outputFileError("logs/資料庫異常.txt", (Throwable)e);
        }
        return quest;
    }
    
    public List<Integer> getMobByItem(final int itemId) {
        final List<Integer> mobs = new LinkedList<Integer>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
                ps = con.prepareStatement("SELECT * FROM drop_data WHERE itemid = ?");
                ps.setInt(1, itemId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    final int mobid = rs.getInt("dropperid");
                    if (!mobs.contains((Object)Integer.valueOf(mobid))) {
                        mobs.add(Integer.valueOf(mobid));
                    }
                }
            }
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            }
            catch (SQLException ignore) {
                System.out.println("Error getMobByItem" + (Object)ignore);
                FileoutputUtil.outputFileError("logs/資料庫異常.txt", (Throwable)ignore);
                return null;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error getMobByItem" + (Object)e);
            FileoutputUtil.outputFileError("logs/資料庫異常.txt", (Throwable)e);
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            }
            catch (SQLException ignore) {
                System.out.println("Error getMobByItem" + (Object)ignore);
                FileoutputUtil.outputFileError("logs/資料庫異常.txt", (Throwable)ignore);
                return null;
            }
        }
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            }
            catch (SQLException ignore2) {
                System.out.println("Error getMobByItem" + (Object)ignore2);
                FileoutputUtil.outputFileError("logs/資料庫異常.txt", (Throwable)ignore2);
                return null;
            }
        }
        return mobs;
    }
    
    public int getDropChance(final int monsterId) {
        int chance = 0;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("SELECT chance FROM drop_data where dropperid = ?");
            ps.setInt(1, monsterId);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                chance = rs.getInt("chance");
            }
        }
        catch (SQLException e) {
            System.out.println("Error getDropChance" + (Object)e);
            FileoutputUtil.outputFileError("logs/資料庫異常.txt", (Throwable)e);
        }
        return chance;
    }
    
    static {
        instance = new MapleMonsterInformationProvider();
    }
}
