package server.custom.bankitem;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import database.DatabaseConnection;
import java.util.ArrayList;
import java.util.List;
import server.MapleInventoryManipulator;
import constants.GameConstants;
import client.inventory.IItem;
import server.MapleItemInformationProvider;
import client.inventory.MapleInventoryType;
import client.MapleCharacter;

public class BankItemManager
{
    public static BankItemManager getInstance() {
        return InstanceHolder.instance;
    }
    
    private BankItemManager() {
    }
    
    public int saveItem(final MapleCharacter player, final byte type, final short slot, final short count) {
        final int ret = 1;
        if (type != 2 && type != 3 && type != 4) {
            return -2;
        }
        if (slot < 0) {
            return -3;
        }
        final MapleInventoryType itemtype = MapleInventoryType.getByType(type);
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        final IItem source = player.getInventory(itemtype).getItem(slot);
        return this.saveItem(player, source, count);
    }
    
    public int saveItem(final MapleCharacter player, final IItem source, short count) {
        int ret = 1;
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        final byte type = (byte)(source.getItemId() / 1000000);
        if (type != 2 && type != 3 && type != 4) {
            return -2;
        }
        if (source == null) {
            return -4;
        }
        if (ii.isCash(source.getItemId()) || source.getExpiration() > 0L) {
            return -5;
        }
        final byte flag = source.getFlag();
        if (count > source.getQuantity() || count < 1) {
            return -6;
        }
        ret = this.add(player.getId(), source.getItemId(), (int)count);
        if (ret < 1) {
            return -8;
        }
        final MapleInventoryType itemtype = MapleInventoryType.getByType(type);
        if (GameConstants.isThrowingStar(source.getItemId()) || GameConstants.isBullet(source.getItemId())) {
            count = source.getQuantity();
        }
        MapleInventoryManipulator.removeFromSlot(player.getClient(), itemtype, source.getPosition(), count, false);
        return ret;
    }
    
    public List<BankItem> getItems(final int cid) {
        final List<BankItem> items = new ArrayList<BankItem>();
        final Connection con1 = DatabaseConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con1.prepareStatement("select * from bank_item where cid = ?");
            ps.setInt(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                final BankItem info = new BankItem();
                info.setId(rs.getLong("id"));
                info.setItemid(rs.getInt("itemid"));
                info.setCid(rs.getInt("cid"));
                info.setCount(rs.getInt("count"));
                items.add(info);
            }
        }
        catch (Exception Ex) {
            Ex.printStackTrace();
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            catch (SQLException ex2) {
                ex2.printStackTrace();
            }
        }
        return items;
    }
    
    public int add(final int cid, final int itemid, final int count) {
        final BankItem item = new BankItem();
        item.setCid(cid);
        item.setItemid(itemid);
        item.setCount(count);
        return this.add(item);
    }
    
    public int add(final BankItem item) {
        int ret = -1;
        if (item == null) {
            return -1;
        }
        final Connection con1 = DatabaseConnection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con1.prepareStatement("insert into bank_item (id,cid,itemid,count) values (?,?,?,?)");
            ps.setLong(1, item.getId());
            ps.setInt(2, item.getCid());
            ps.setInt(3, item.getItemid());
            ps.setInt(4, item.getCount());
            ret = ps.executeUpdate();
        }
        catch (Exception Ex) {
            Ex.printStackTrace();
            return ret;
        }
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return ret;
    }
    
    public int delete(final long id) {
        int ret = -1;
        final Connection con1 = DatabaseConnection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con1.prepareStatement("delete from bank_item where id = ?");
            ps.setLong(1, id);
            ret = ps.executeUpdate();
        }
        catch (Exception Ex) {
            Ex.printStackTrace();
            return ret;
        }
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return ret;
    }
    
    public int update(final BankItem bankItem) {
        int ret = -1;
        final Connection con1 = DatabaseConnection.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con1.prepareStatement("update bank_item set cid = ? ,itemid = ? ,count = ? where id = ?");
            ps.setInt(1, bankItem.getCid());
            ps.setInt(2, bankItem.getItemid());
            ps.setInt(3, bankItem.getCount());
            ps.setLong(4, bankItem.getId());
            ret = ps.executeUpdate();
        }
        catch (Exception Ex) {
            Ex.printStackTrace();
            return ret;
        }
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return ret;
    }
    
    private static class InstanceHolder
    {
        public static final BankItemManager instance;
        
        static {
            instance = new BankItemManager();
        }
    }
}
