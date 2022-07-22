package server;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import tools.FileoutputUtil;
import database.DBConPool;
import constants.GameConstants;
import java.sql.SQLException;
import java.util.Iterator;
import client.inventory.MapleInventoryType;
import tools.Pair;
import client.inventory.ItemLoader;
import java.util.ArrayList;
import client.inventory.IItem;
import java.util.List;
import java.io.Serializable;

public class MTSCart implements Serializable
{
    private static final long serialVersionUID = 231541893513373578L;
    private int characterId;
    private int tab;
    private int type;
    private int page;
    private List<IItem> transfer;
    private List<Integer> cart;
    private List<Integer> notYetSold;
    private int owedNX;
    
    public MTSCart(final int characterId) throws SQLException {
        this.tab = 1;
        this.type = 0;
        this.page = 0;
        this.transfer = new ArrayList<IItem>();
        this.cart = new ArrayList<Integer>();
        this.notYetSold = new ArrayList<Integer>(10);
        this.owedNX = 0;
        this.characterId = characterId;
        for (final Pair<IItem, MapleInventoryType> item : ItemLoader.MTS_TRANSFER.loadItems(false, Integer.valueOf(characterId)).values()) {
            this.transfer.add(item.getLeft());
        }
        this.loadCart();
        this.loadNotYetSold();
    }
    
    public List<IItem> getInventory() {
        return this.transfer;
    }
    
    public void addToInventory(final IItem item) {
        this.transfer.add(item);
    }
    
    public void removeFromInventory(final IItem item) {
        this.transfer.remove((Object)item);
    }
    
    public List<Integer> getCart() {
        return this.cart;
    }
    
    public boolean addToCart(final int car) {
        if (!this.cart.contains((Object)Integer.valueOf(car))) {
            this.cart.add(Integer.valueOf(car));
            return true;
        }
        return false;
    }
    
    public void removeFromCart(final int car) {
        for (int i = 0; i < this.cart.size(); ++i) {
            if ((int)Integer.valueOf(this.cart.get(i)) == car) {
                this.cart.remove(i);
            }
        }
    }
    
    public List<Integer> getNotYetSold() {
        return this.notYetSold;
    }
    
    public void addToNotYetSold(final int car) {
        this.notYetSold.add(Integer.valueOf(car));
    }
    
    public void removeFromNotYetSold(final int car) {
        for (int i = 0; i < this.notYetSold.size(); ++i) {
            if ((int)Integer.valueOf(this.notYetSold.get(i)) == car) {
                this.notYetSold.remove(i);
            }
        }
    }
    
    public final int getSetOwedNX() {
        final int on = this.owedNX;
        this.owedNX = 0;
        return on;
    }
    
    public void increaseOwedNX(final int newNX) {
        this.owedNX += newNX;
    }
    
    public void save() throws SQLException {
        final List<Pair<IItem, MapleInventoryType>> itemsWithType = new ArrayList<Pair<IItem, MapleInventoryType>>();
        for (final IItem item : this.getInventory()) {
            itemsWithType.add(new Pair<IItem, MapleInventoryType>(item, GameConstants.getInventoryType(item.getItemId())));
        }
        ItemLoader.MTS_TRANSFER.saveItems(itemsWithType, Integer.valueOf(this.characterId));
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM mts_cart WHERE characterid = ?");
            ps.setInt(1, this.characterId);
            ps.execute();
            ps.close();
            ps = con.prepareStatement("INSERT INTO mts_cart VALUES(DEFAULT, ?, ?)");
            ps.setInt(1, this.characterId);
            final Iterator<Integer> iterator2 = this.cart.iterator();
            while (iterator2.hasNext()) {
                final int i = (int)Integer.valueOf(iterator2.next());
                ps.setInt(2, i);
                ps.executeUpdate();
            }
            if (this.owedNX > 0) {
                ps.setInt(2, -this.owedNX);
                ps.executeUpdate();
            }
            ps.close();
        }
        catch (SQLException ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
    }
    
    public void loadCart() throws SQLException {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM mts_cart WHERE characterid = ?");
            ps.setInt(1, this.characterId);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final int iId = rs.getInt("itemid");
                if (iId < 0) {
                    this.owedNX -= iId;
                }
                else {
                    if (!MTSStorage.getInstance().check(iId)) {
                        continue;
                    }
                    this.cart.add(Integer.valueOf(iId));
                }
            }
            rs.close();
            ps.close();
        }
        catch (SQLException ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
    }
    
    public void loadNotYetSold() throws SQLException {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM mts_items WHERE characterid = ?");
            ps.setInt(1, this.characterId);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final int pId = rs.getInt("id");
                if (MTSStorage.getInstance().check(pId)) {
                    this.notYetSold.add(Integer.valueOf(pId));
                }
            }
            rs.close();
            ps.close();
        }
        catch (SQLException ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
    }
    
    public void changeInfo(final int tab, final int type, final int page) {
        this.tab = tab;
        this.type = type;
        this.page = page;
    }
    
    public int getTab() {
        return this.tab;
    }
    
    public int getType() {
        return this.type;
    }
    
    public int getPage() {
        return this.page;
    }
}
