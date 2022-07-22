package client;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import tools.FileoutputUtil;
import database.DBConPool;
import java.util.concurrent.atomic.AtomicLong;

public class MapleEquipOnlyId
{
    private final AtomicLong runningId;
    
    public static MapleEquipOnlyId getInstance() {
        return SingletonHolder.instance;
    }
    
    private MapleEquipOnlyId() {
        this.runningId = new AtomicLong(0L);
    }
    
    public long getNextEquipOnlyId() {
        if (this.runningId.get() <= 0L) {
            this.runningId.set(this.initOnlyId());
        }
        else {
            this.runningId.set(this.runningId.get() + 1L);
        }
        return this.runningId.get();
    }
    
    public long initOnlyId() {
        long ret = 0L;
        try (final PreparedStatement ps = DBConPool.getInstance().getDataSource().getConnection().prepareStatement("SELECT MAX(equipOnlyId) FROM inventoryitems WHERE equipOnlyId > 0");
             final ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                ret = (long)(rs.getInt(1) + 1);
            }
            ps.close();
        }
        catch (SQLException e) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
            e.printStackTrace();
        }
        return ret;
    }
    
    private static class SingletonHolder
    {
        protected static final MapleEquipOnlyId instance;
        
        static {
            instance = new MapleEquipOnlyId();
        }
    }
}
