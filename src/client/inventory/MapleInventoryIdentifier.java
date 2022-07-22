package client.inventory;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import tools.FileoutputUtil;
import database.DBConPool;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.Serializable;

public class MapleInventoryIdentifier implements Serializable
{
    private static final long serialVersionUID = 21830921831301L;
    private AtomicInteger runningUID;
    private ReentrantReadWriteLock rwl;
    private Lock readLock;
    private Lock writeLock;
    private static MapleInventoryIdentifier instance;
    
    public MapleInventoryIdentifier() {
        this.rwl = new ReentrantReadWriteLock();
        this.readLock = this.rwl.readLock();
        this.writeLock = this.rwl.writeLock();
        this.runningUID = new AtomicInteger(0);
        this.getNextUniqueId();
    }
    
    public static int getInstance() {
        return MapleInventoryIdentifier.instance.getNextUniqueId();
    }
    
    public int getNextUniqueId() {
        if (this.grabRunningUID() <= 0) {
            this.setRunningUID(this.initUID());
        }
        this.incrementRunningUID();
        return this.grabRunningUID();
    }
    
    public int grabRunningUID() {
        this.readLock.lock();
        try {
            return this.runningUID.get();
        }
        finally {
            this.readLock.unlock();
        }
    }
    
    public void incrementRunningUID() {
        this.setRunningUID(this.grabRunningUID() + 1);
    }
    
    public void setRunningUID(final int rUID) {
        if (rUID < this.grabRunningUID()) {
            return;
        }
        this.writeLock.lock();
        try {
            this.runningUID.set(rUID);
        }
        finally {
            this.writeLock.unlock();
        }
    }
    
    public int initUID() {
        int ret = 0;
        if (this.grabRunningUID() > 0) {
            return this.grabRunningUID();
        }
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final int[] ids = new int[4];
            PreparedStatement ps = con.prepareStatement("SELECT MAX(uniqueid) FROM inventoryitems");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ids[0] = rs.getInt(1) + 1;
            }
            rs.close();
            ps.close();
            ps = con.prepareStatement("SELECT MAX(petid) FROM pets");
            rs = ps.executeQuery();
            if (rs.next()) {
                ids[1] = rs.getInt(1) + 1;
            }
            rs.close();
            ps.close();
            ps = con.prepareStatement("SELECT MAX(ringid) FROM rings");
            rs = ps.executeQuery();
            if (rs.next()) {
                ids[2] = rs.getInt(1) + 1;
            }
            rs.close();
            ps.close();
            ps = con.prepareStatement("SELECT MAX(partnerringid) FROM rings");
            rs = ps.executeQuery();
            if (rs.next()) {
                ids[3] = rs.getInt(1) + 1;
            }
            rs.close();
            ps.close();
            for (int i = 0; i < 4; ++i) {
                if (ids[i] > ret) {
                    ret = ids[i];
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
        }
        return ret;
    }
    
    static {
        MapleInventoryIdentifier.instance = new MapleInventoryIdentifier();
    }
}
