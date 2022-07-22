package server.gashapon;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import tools.FilePrinter;
import database.DBConPool;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.Map;

public class GashaponFactory
{
    private final Map<Integer, Gashapon> gashapons;
    private final ReentrantReadWriteLock lock;
    private static GashaponFactory instance;
    
    public GashaponFactory() {
        this.gashapons = new HashMap<Integer, Gashapon>();
        this.lock = new ReentrantReadWriteLock();
    }
    
    public static GashaponFactory getInstance() {
        if (GashaponFactory.instance == null) {
            (GashaponFactory.instance = new GashaponFactory()).reloadGashapons();
        }
        return GashaponFactory.instance;
    }
    
    public void reloadGashapons() {
        this.gashapons.clear();
        final long chanceTotal = 0L;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT * FROM gashapons;");
             final ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                final Gashapon gashapon = new Gashapon(rs.getInt("id"), rs.getInt("npcId"), rs.getString("name"));
                this.lock.writeLock().lock();
                try {
                    this.gashapons.put(Integer.valueOf(gashapon.getNpcId()), gashapon);
                }
                finally {
                    this.lock.writeLock().unlock();
                }
            }
        }
        catch (SQLException ex) {
            FilePrinter.printError("GashaponFactory.txt", (Throwable)ex, "reloadGashapons");
        }
    }
    
    public void reloadGashapons(final int npcId) {
        getInstance().gashapons.clear();
        final long chanceTotal = 0L;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT * FROM gashapons WHERE npcId = ?")) {
            ps.setInt(1, npcId);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                final Gashapon gashapon = new Gashapon(rs.getInt("gashaponsid"), rs.getInt("npcId"), rs.getString("name"));
                this.lock.writeLock().lock();
                try {
                    this.gashapons.remove((Object)Integer.valueOf(gashapon.getNpcId()));
                    this.gashapons.put(Integer.valueOf(gashapon.getNpcId()), gashapon);
                }
                finally {
                    this.lock.writeLock().unlock();
                }
            }
        }
        catch (SQLException ex) {
            FilePrinter.printError("GashaponFactory.txt", (Throwable)ex, "reloadGashapons");
        }
    }
    
    public Gashapon getGashaponByNpcId(final int NpcId) {
        Gashapon ret = null;
        this.lock.readLock().lock();
        try {
            ret = (Gashapon)this.gashapons.get((Object)Integer.valueOf(NpcId));
        }
        finally {
            this.lock.readLock().unlock();
        }
        return ret;
    }
    
    static {
        GashaponFactory.instance = null;
    }
}
