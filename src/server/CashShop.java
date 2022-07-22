package server;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import tools.FileoutputUtil;
import database.DBConPool;
import client.inventory.Item;
import client.inventory.MapleRing;
import client.inventory.Equip;
import client.inventory.MapleInventoryIdentifier;
import client.inventory.MaplePet;
import client.MapleCharacter;
import tools.packet.MTSCSPacket;
import constants.GameConstants;
import client.MapleClient;
import java.sql.SQLException;
import java.util.Iterator;
import client.inventory.MapleInventoryType;
import tools.Pair;
import java.util.ArrayList;
import client.inventory.IItem;
import java.util.List;
import client.inventory.ItemLoader;
import java.io.Serializable;

public class CashShop implements Serializable
{
    private static final long serialVersionUID = 231541893513373579L;
    private int accountId;
    private int characterId;
    private ItemLoader factory;
    private List<IItem> inventory;
    private List<Integer> uniqueids;
    
    public CashShop(final int accountId, final int characterId, final int jobType) throws SQLException {
        this.inventory = new ArrayList<IItem>();
        this.uniqueids = new ArrayList<Integer>();
        this.accountId = accountId;
        this.characterId = characterId;
        this.factory = ItemLoader.CASHSHOP_EXPLORER;
        for (final Pair<IItem, MapleInventoryType> item : this.factory.loadItems(false, Integer.valueOf(accountId)).values()) {
            this.inventory.add(item.getLeft());
        }
    }
    
    public int getItemsSize() {
        return this.inventory.size();
    }
    
    public List<IItem> getInventory() {
        return this.inventory;
    }
    
    public IItem findByCashId(final int cashId) {
        for (final IItem item : this.inventory) {
            if (item.getUniqueId() == cashId) {
                return item;
            }
        }
        return null;
    }
    
    public void checkExpire(final MapleClient c) {
        final List<IItem> toberemove = new ArrayList<IItem>();
        for (final IItem item : this.inventory) {
            if (item != null && !GameConstants.isPet(item.getItemId()) && item.getExpiration() > 0L && item.getExpiration() < System.currentTimeMillis()) {
                toberemove.add(item);
            }
        }
        if (toberemove.size() > 0) {
            for (final IItem item : toberemove) {
                this.removeFromInventory(item);
                c.sendPacket(MTSCSPacket.cashItemExpired(item.getUniqueId()));
            }
            toberemove.clear();
        }
    }
    
    public IItem toItem(final CashItemInfo cItem, final MapleCharacter chr) {
        return this.toItem(cItem, MapleInventoryManipulator.getUniqueId(cItem.getId(), null), "", chr);
    }
    
    public IItem toItem(final CashItemInfo cItem) {
        return this.toItem(cItem, MapleInventoryManipulator.getUniqueId(cItem.getId(), null), "");
    }
    
    public IItem toItem(final CashItemInfo cItem, final String gift) {
        return this.toItem(cItem, MapleInventoryManipulator.getUniqueId(cItem.getId(), null), gift);
    }
    
    public IItem toItem(final CashItemInfo cItem, final int uniqueid) {
        return this.toItem(cItem, uniqueid, "");
    }
    
    public IItem toItem(final CashItemInfo cItem, final int uniqueid, final String gift) {
        return this.toItem(cItem, null, uniqueid, gift);
    }
    
    public IItem toItem(final CashItemInfo cItem, final int uniqueid, final String gift, final MapleCharacter chr) {
        return this.toItem(cItem, chr, uniqueid, gift);
    }
    
    public IItem toItem(final CashItemInfo cItem, final MapleCharacter chr, int uniqueid, final String gift) {
        if (uniqueid <= 0) {
            uniqueid = MapleInventoryIdentifier.getInstance();
        }
        long period = (long)cItem.getPeriod();
        if (period <= 0L) {
            period = 45L;
        }
        boolean 加倍卡 = false;
        if ((cItem.getId() >= 5210000 && cItem.getId() <= 5210011) || (cItem.getId() >= 5360000 && cItem.getId() <= 5360015)) {
            加倍卡 = true;
        }
        if (!加倍卡) {
            period = 36500L;
        }
        if (cItem.getId() == 1112015 || cItem.getId() == 1112810 || cItem.getId() == 1112811 || cItem.getId() == 1112001 || cItem.getId() == 1112007 || cItem.getId() == 1112012 || cItem.getId() == 1112003) {
            period = 36500L;
        }
        if (cItem.getId() == 5320000) {
            period = 90L;
        }
        if (GameConstants.isPet(cItem.getId())) {
            period = 90L;
        }
        IItem ret = null;
        if (GameConstants.getInventoryType(cItem.getId()) == MapleInventoryType.EQUIP) {
            final Equip eq = (Equip)MapleItemInformationProvider.getInstance().getEquipById(cItem.getId());
            eq.setUniqueId(uniqueid);
            eq.setExpiration(System.currentTimeMillis() + period * 24L * 60L * 60L * 1000L);
            eq.setGiftFrom(gift);
            if (GameConstants.isEffectRing(cItem.getId()) && uniqueid > 0) {
                final MapleRing ring = MapleRing.loadFromDb(uniqueid);
                if (ring != null) {
                    eq.setRing(ring);
                }
            }
            ret = eq.copy();
        }
        else {
            final Item item = new Item(cItem.getId(), (short)0, (short)cItem.getCount(), (byte)0, uniqueid);
            item.setExpiration(System.currentTimeMillis() + period * 24L * 60L * 60L * 1000L);
            item.setGiftFrom(gift);
            if (GameConstants.isPet(cItem.getId())) {
                final MaplePet pet = MaplePet.createPet(cItem.getId(), uniqueid);
                if (pet != null) {
                    item.setPet(pet);
                }
            }
            ret = item.copy();
        }
        return ret;
    }
    
    public void addToInventory(final IItem item) {
        this.inventory.add(item);
    }
    
    public void removeFromInventory(final IItem item) {
        this.inventory.remove((Object)item);
    }
    
    public void gift(final int recipient, final String from, final String message, final int sn) {
        this.gift(recipient, from, message, sn, 0);
    }
    
    public void gift(final int recipient, final String from, final String message, final int sn, final int uniqueid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("INSERT INTO `gifts` VALUES (DEFAULT, ?, ?, ?, ?, ?)");
            ps.setInt(1, recipient);
            ps.setString(2, from);
            ps.setString(3, message);
            ps.setInt(4, sn);
            ps.setInt(5, uniqueid);
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)sqle);
        }
    }
    
    public List<Pair<IItem, String>> loadGifts() {
        final List<Pair<IItem, String>> gifts = new ArrayList<Pair<IItem, String>>();
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM `gifts` WHERE `recipient` = ?");
            ps.setInt(1, this.characterId);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final CashItemInfo cItem = CashItemFactory.getInstance().getItem(rs.getInt("sn"));
                if (cItem == null) {
                    continue;
                }
                final IItem item = this.toItem(cItem, rs.getInt("uniqueid"), rs.getString("from"));
                gifts.add(new Pair<IItem, String>(item, rs.getString("message")));
                this.uniqueids.add(Integer.valueOf(item.getUniqueId()));
                final List<CashItemInfo> packages = CashItemFactory.getInstance().getPackageItems(cItem.getId());
                if (packages != null && packages.size() > 0) {
                    for (final CashItemInfo packageItem : packages) {
                        this.addToInventory(this.toItem(packageItem, rs.getString("from")));
                    }
                }
                else {
                    this.addToInventory(item);
                }
            }
            rs.close();
            ps.close();
            ps = con.prepareStatement("DELETE FROM `gifts` WHERE `recipient` = ?");
            ps.setInt(1, this.characterId);
            ps.executeUpdate();
            ps.close();
            this.save(null);
        }
        catch (SQLException sqle) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)sqle);
            sqle.printStackTrace();
        }
        return gifts;
    }
    
    public boolean canSendNote(final int uniqueid) {
        return this.uniqueids.contains((Object)Integer.valueOf(uniqueid));
    }
    
    public void sendedNote(final int uniqueid) {
        for (int i = 0; i < this.uniqueids.size(); ++i) {
            if ((int)Integer.valueOf(this.uniqueids.get(i)) == uniqueid) {
                this.uniqueids.remove(i);
            }
        }
    }
    
    public void save(final Connection con) throws SQLException {
        final List<Pair<IItem, MapleInventoryType>> itemsWithType = new ArrayList<Pair<IItem, MapleInventoryType>>();
        for (final IItem item : this.inventory) {
            itemsWithType.add(new Pair<IItem, MapleInventoryType>(item, GameConstants.getInventoryType(item.getItemId())));
        }
        if (con != null) {
            this.factory.saveItems(itemsWithType, con, Integer.valueOf(this.accountId));
        }
        else {
            this.factory.saveItems(itemsWithType, Integer.valueOf(this.accountId));
        }
    }
}
