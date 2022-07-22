package server;

import tools.MaplePacketCreator;
import java.util.Comparator;
import client.MapleClient;
import java.util.Collections;
import java.util.Collection;
import constants.GameConstants;
import java.util.ArrayList;
import java.util.Iterator;
import tools.Pair;
import client.inventory.ItemLoader;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import database.DatabaseException;
import tools.FileoutputUtil;
import database.DBConPool;
import java.util.LinkedList;
import java.util.EnumMap;
import client.inventory.MapleInventoryType;
import java.util.Map;
import client.inventory.IItem;
import java.util.List;
import java.io.Serializable;

public class MapleStorage implements Serializable
{
    private static final long serialVersionUID = 9179541993413738569L;
    private final int id;
    private final int accountId;
    private final List<IItem> items;
    private final Map<MapleInventoryType, List<IItem>> typeItems;
    private int meso;
    private byte slots;
    private boolean changed;
    
    private MapleStorage(final int id, final byte slots, final int meso, final int accountId) {
        this.typeItems = new EnumMap<MapleInventoryType, List<IItem>>(MapleInventoryType.class);
        this.changed = false;
        this.id = id;
        this.slots = slots;
        this.items = new LinkedList<IItem>();
        this.meso = meso;
        this.accountId = accountId;
    }
    
    public static int create(final int id) throws SQLException {
        ResultSet rs;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("INSERT INTO storages (accountid, slots, meso) VALUES (?, ?, ?)", 1)) {
            ps.setInt(1, id);
            ps.setInt(2, 4);
            ps.setInt(3, 0);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                final int storageid = rs.getInt(1);
                ps.close();
                rs.close();
                return storageid;
            }
        }
        catch (SQLException ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
            throw new DatabaseException("Inserting char failed.");
        }
        rs.close();
        throw new DatabaseException("Inserting char failed.");
    }
    
    public static MapleStorage loadStorage(final int id) {
        MapleStorage ret = null;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM storages WHERE accountid = ?");
            ps.setInt(1, id);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                final int storeId = rs.getInt("storageid");
                ret = new MapleStorage(storeId, rs.getByte("slots"), rs.getInt("meso"), id);
                rs.close();
                ps.close();
                for (final Pair<IItem, MapleInventoryType> mit : ItemLoader.STORAGE.loadItems(false, Integer.valueOf(id)).values()) {
                    ret.items.add(mit.getLeft());
                }
            }
            else {
                final int storeId = create(id);
                ret = new MapleStorage(storeId, (byte)4, 0, id);
                rs.close();
                ps.close();
            }
        }
        catch (SQLException ex) {
            System.err.println("Error loading storage" + (Object)ex);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
        return ret;
    }
    
    public void saveToDB() {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            this.saveToDB(con);
        }
        catch (SQLException ex) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
    }
    
    public void saveToDB(final Connection con) {
        if (!this.changed) {
            return;
        }
        try {
            try (final PreparedStatement ps = con.prepareStatement("UPDATE storages SET slots = ?, meso = ? WHERE storageid = ?")) {
                ps.setInt(1, (int)this.slots);
                ps.setInt(2, this.meso);
                ps.setInt(3, this.id);
                ps.executeUpdate();
            }
            catch (SQLException e) {
                System.err.println("Error saving storage" + (Object)e);
                FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
            }
            final List<Pair<IItem, MapleInventoryType>> listing = new ArrayList<Pair<IItem, MapleInventoryType>>();
            for (final IItem item : this.items) {
                listing.add(new Pair<IItem, MapleInventoryType>(item, GameConstants.getInventoryType(item.getItemId())));
            }
            ItemLoader.STORAGE.saveItems(listing, con, Integer.valueOf(this.accountId));
        }
        catch (SQLException ex) {
            System.err.println("Error saving storage" + (Object)ex);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)ex);
        }
    }
    
    public IItem takeOut(final byte slot) {
        if (slot >= this.items.size() || slot < 0) {
            return null;
        }
        this.changed = true;
        final IItem ret = (IItem)this.items.remove((int)slot);
        final MapleInventoryType type = GameConstants.getInventoryType(ret.getItemId());
        this.typeItems.put(type, new ArrayList<IItem>((Collection<? extends IItem>)this.filterItems(type)));
        return ret;
    }
    
    public void store(final IItem item) {
        this.changed = true;
        this.items.add(item);
        final MapleInventoryType type = GameConstants.getInventoryType(item.getItemId());
        this.typeItems.put(type, new ArrayList<IItem>((Collection<? extends IItem>)this.filterItems(type)));
    }
    
    public List<IItem> getItems() {
        return Collections.unmodifiableList((List<? extends IItem>)this.items);
    }
    
    private List<IItem> filterItems(final MapleInventoryType type) {
        final List<IItem> ret = new LinkedList<IItem>();
        for (final IItem item : this.items) {
            if (GameConstants.getInventoryType(item.getItemId()) == type) {
                ret.add(item);
            }
        }
        return ret;
    }
    
    public byte getSlot(final MapleInventoryType type, final byte slot) {
        byte ret = 0;
        final List<IItem> it = (List<IItem>)this.typeItems.get((Object)type);
        if (slot >= it.size() || slot < 0) {
            return -1;
        }
        for (final IItem item : this.items) {
            if (item == it.get((int)slot)) {
                return ret;
            }
            ++ret;
        }
        return -1;
    }
    
    public void sendStorage(final MapleClient c, final int npcId) {
        Collections.sort(this.items, (Comparator<? super IItem>)new Comparator<IItem>() {
            @Override
            public int compare(final IItem o1, final IItem o2) {
                if (GameConstants.getInventoryType(o1.getItemId()).getType() < GameConstants.getInventoryType(o2.getItemId()).getType()) {
                    return -1;
                }
                if (GameConstants.getInventoryType(o1.getItemId()) == GameConstants.getInventoryType(o2.getItemId())) {
                    return 0;
                }
                return 1;
            }
        });
        for (final MapleInventoryType type : MapleInventoryType.values()) {
            this.typeItems.put(type, new ArrayList<IItem>((Collection<? extends IItem>)this.items));
        }
        c.sendPacket(MaplePacketCreator.getStorage(npcId, this.slots, (Collection<IItem>)this.items, this.meso));
    }
    
    public void sendStored(final MapleClient c, final MapleInventoryType type) {
        c.sendPacket(MaplePacketCreator.storeStorage(this.slots, type, (Collection<IItem>)(List<IItem>)this.typeItems.get((Object)type)));
    }
    
    public void sendTakenOut(final MapleClient c, final MapleInventoryType type) {
        c.sendPacket(MaplePacketCreator.takeOutStorage(this.slots, type, (Collection<IItem>)(List<IItem>)this.typeItems.get((Object)type)));
    }
    
    public int getMeso() {
        return this.meso;
    }
    
    public void arrange() {
        Collections.sort(this.items, (Comparator<? super IItem>)new Comparator<IItem>() {
            @Override
            public int compare(final IItem o1, final IItem o2) {
                if (o1.getItemId() < o2.getItemId()) {
                    return -1;
                }
                if (o1.getItemId() == o2.getItemId()) {
                    return 0;
                }
                return 1;
            }
        });
        for (final MapleInventoryType type : MapleInventoryType.values()) {
            this.typeItems.put(type, this.items);
        }
    }
    
    public IItem findById(final int itemId) {
        for (final IItem item : this.items) {
            if (item.getItemId() == itemId) {
                return item;
            }
        }
        return null;
    }
    
    public void setMeso(final int meso) {
        if (meso < 0) {
            return;
        }
        this.changed = true;
        this.meso = meso;
    }
    
    public void sendMeso(final MapleClient c) {
        c.sendPacket(MaplePacketCreator.mesoStorage(this.slots, this.meso));
    }
    
    public boolean isFull() {
        return this.items.size() >= this.slots;
    }
    
    public int getSlots() {
        return this.slots;
    }
    
    public void increaseSlots(final byte gain) {
        this.changed = true;
        this.slots += gain;
    }
    
    public void setSlots(final byte set) {
        this.changed = true;
        this.slots = set;
    }
    
    public void close() {
        this.typeItems.clear();
    }
    
    public void update(final MapleClient c) {
        c.sendPacket(MaplePacketCreator.arrangeStorage(this.slots, (Collection<IItem>)this.items, true));
    }
    
    public boolean removeItemByInventoryItemId(final long inventoryitemid) {
        int slot = 0;
        boolean process = false;
        if (this.getItems() != null) {
            final List<IItem> equips = this.getItems();
            for (final IItem equip : equips) {
                ++slot;
                if (equip.getInventoryId() == inventoryitemid) {
                    process = true;
                    this.items.remove(slot);
                    break;
                }
            }
            this.changed = true;
        }
        return process;
    }
}
