package server;

import java.util.Collection;
import tools.packet.MTSCSPacket;
import java.util.List;
import java.sql.SQLException;
import constants.GameConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import tools.FileoutputUtil;
import tools.FilePrinter;
import client.inventory.MapleInventoryType;
import tools.Pair;
import client.inventory.ItemLoader;
import database.DBConPool;
import java.util.Iterator;
import java.util.Map.Entry;
import client.inventory.IItem;
import java.util.LinkedHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;

public class MTSStorage
{
    private static final long serialVersionUID = 231541893513228L;
    private long lastUpdate;
    private final Map<Integer, MTSCart> idToCart;
    private final AtomicInteger packageId;
    private final Map<Integer, MTSItemInfo> buyNow;
    private static MTSStorage instance;
    private boolean end;
    private final ReentrantReadWriteLock mutex;
    private final ReentrantReadWriteLock cart_mutex;
    
    public MTSStorage() {
        this.lastUpdate = System.currentTimeMillis();
        this.end = false;
        System.out.println("[正在加载] -> MTSStorage :::");
        this.idToCart = new LinkedHashMap<Integer, MTSCart>();
        this.buyNow = new LinkedHashMap<Integer, MTSItemInfo>();
        this.packageId = new AtomicInteger(1);
        this.mutex = new ReentrantReadWriteLock();
        this.cart_mutex = new ReentrantReadWriteLock();
    }
    
    public static final MTSStorage getInstance() {
        return MTSStorage.instance;
    }
    
    public static final void load() {
        if (MTSStorage.instance == null) {
            (MTSStorage.instance = new MTSStorage()).loadBuyNow();
        }
    }
    
    public final boolean check(final int packageid) {
        return this.getSingleItem(packageid) != null;
    }
    
    public final boolean checkCart(final int packageid, final int charID) {
        final MTSItemInfo item = this.getSingleItem(packageid);
        return item != null && item.getCharacterId() != charID;
    }
    
    public final MTSItemInfo getSingleItem(final int packageid) {
        this.mutex.readLock().lock();
        try {
            return (MTSItemInfo)this.buyNow.get((Object)Integer.valueOf(packageid));
        }
        finally {
            this.mutex.readLock().unlock();
        }
    }
    
    public final void addToBuyNow(final MTSCart cart, final IItem item, final int price, final int cid, final String seller, final long expiration) {
        this.mutex.writeLock().lock();
        int id;
        try {
            id = this.packageId.incrementAndGet();
            this.buyNow.put(Integer.valueOf(id), new MTSItemInfo(price, item, seller, id, cid, expiration));
        }
        finally {
            this.mutex.writeLock().unlock();
        }
        cart.addToNotYetSold(id);
    }
    
    public final boolean removeFromBuyNow(final int id, final int cidBought, final boolean check) {
        IItem item = null;
        this.mutex.writeLock().lock();
        try {
            if (this.buyNow.containsKey((Object)Integer.valueOf(id))) {
                final MTSItemInfo r = (MTSItemInfo)this.buyNow.get((Object)Integer.valueOf(id));
                if (!check || r.getCharacterId() == cidBought) {
                    item = r.getItem();
                    this.buyNow.remove((Object)Integer.valueOf(id));
                }
            }
        }
        finally {
            this.mutex.writeLock().unlock();
        }
        if (item != null) {
            this.cart_mutex.readLock().lock();
            try {
                for (final Entry<Integer, MTSCart> c : this.idToCart.entrySet()) {
                    ((MTSCart)c.getValue()).removeFromCart(id);
                    ((MTSCart)c.getValue()).removeFromNotYetSold(id);
                    if ((int)Integer.valueOf(c.getKey()) == cidBought) {
                        ((MTSCart)c.getValue()).addToInventory(item);
                    }
                }
            }
            finally {
                this.cart_mutex.readLock().unlock();
            }
        }
        return item != null;
    }
    
    private void loadBuyNow() {
        int lastPackage = 0;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT * FROM mts_items WHERE tab = 1");
             final ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lastPackage = rs.getInt("id");
                final int cId = rs.getInt("characterid");
                if (!this.idToCart.containsKey((Object)Integer.valueOf(cId))) {
                    this.idToCart.put(Integer.valueOf(cId), new MTSCart(cId));
                }
                final Map<Long, Pair<IItem, MapleInventoryType>> items = ItemLoader.MTS.loadItems(false, Integer.valueOf(lastPackage));
                if (items != null && items.size() > 0) {
                    for (final Pair<IItem, MapleInventoryType> i : items.values()) {
                        this.buyNow.put(Integer.valueOf(lastPackage), new MTSItemInfo(rs.getInt("price"), (IItem)i.getLeft(), rs.getString("seller"), lastPackage, cId, rs.getLong("expiration")));
                    }
                }
            }
        }
        catch (Exception e) {
            FilePrinter.printError("MTSStorage.txt", (Throwable)e, "loadBuyNow");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
        }
        this.packageId.set(lastPackage);
    }
    
    public final void saveBuyNow(final boolean isShutDown) {
        if (this.end) {
            return;
        }
        this.end = isShutDown;
        if (isShutDown) {
            System.out.println("Saving MTS...");
        }
        final Map<Integer, ArrayList<IItem>> expire = new HashMap<Integer, ArrayList<IItem>>();
        final List<Integer> toRemove = new ArrayList<Integer>();
        final long now = System.currentTimeMillis();
        final Map<Integer, ArrayList<Pair<IItem, MapleInventoryType>>> items = new HashMap<Integer, ArrayList<Pair<IItem, MapleInventoryType>>>();
        this.mutex.writeLock().lock();
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM mts_items WHERE tab = 1");
            ps.execute();
            ps.close();
            ps = con.prepareStatement("INSERT INTO mts_items VALUES (?, ?, ?, ?, ?, ?)");
            for (final MTSItemInfo m : this.buyNow.values()) {
                if (now > m.getEndingDate()) {
                    if (!expire.containsKey((Object)Integer.valueOf(m.getCharacterId()))) {
                        expire.put(Integer.valueOf(m.getCharacterId()), new ArrayList<IItem>());
                    }
                    ((ArrayList<IItem>)expire.get((Object)Integer.valueOf(m.getCharacterId()))).add(m.getItem());
                    toRemove.add(Integer.valueOf(m.getId()));
                    items.put(Integer.valueOf(m.getId()), null);
                }
                else {
                    ps.setInt(1, m.getId());
                    ps.setByte(2, (byte)1);
                    ps.setInt(3, m.getPrice());
                    ps.setInt(4, m.getCharacterId());
                    ps.setString(5, m.getSeller());
                    ps.setLong(6, m.getEndingDate());
                    ps.executeUpdate();
                    if (!items.containsKey((Object)Integer.valueOf(m.getId()))) {
                        items.put(Integer.valueOf(m.getId()), new ArrayList<Pair<IItem, MapleInventoryType>>());
                    }
                    ((ArrayList<Pair<IItem, MapleInventoryType>>)items.get((Object)Integer.valueOf(m.getId()))).add(new Pair<IItem, MapleInventoryType>(m.getItem(), GameConstants.getInventoryType(m.getItem().getItemId())));
                }
            }
            final Iterator<Integer> iterator2 = toRemove.iterator();
            while (iterator2.hasNext()) {
                final int i = (int)Integer.valueOf(iterator2.next());
                this.buyNow.remove((Object)Integer.valueOf(i));
            }
            ps.close();
        }
        catch (SQLException e) {
            FilePrinter.printError("MTSStorage.txt", (Throwable)e, "saveBuyNow");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
        }
        finally {
            this.mutex.writeLock().unlock();
        }
        if (isShutDown) {
            System.out.println("Saving MTS items...");
        }
        try {
            for (final Entry<Integer, ArrayList<Pair<IItem, MapleInventoryType>>> ite : items.entrySet()) {
                ItemLoader.MTS.saveItems((List<Pair<IItem, MapleInventoryType>>)(ArrayList<Pair<IItem, MapleInventoryType>>)ite.getValue(), Integer.valueOf(ite.getKey()));
            }
        }
        catch (SQLException e) {
            FilePrinter.printError("MTSStorage.txt", (Throwable)e, "saveBuyNow");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
        }
        if (isShutDown) {
            System.out.println("Saving MTS carts...");
        }
        this.cart_mutex.writeLock().lock();
        try {
            for (final Entry<Integer, MTSCart> c : this.idToCart.entrySet()) {
                final Iterator<Integer> iterator5 = toRemove.iterator();
                while (iterator5.hasNext()) {
                    final int j = (int)Integer.valueOf(iterator5.next());
                    ((MTSCart)c.getValue()).removeFromCart(j);
                    ((MTSCart)c.getValue()).removeFromNotYetSold(j);
                }
                if (expire.containsKey((Object)c.getKey())) {
                    for (final IItem item : (ArrayList<IItem>)expire.get((Object)c.getKey())) {
                        ((MTSCart)c.getValue()).addToInventory(item);
                    }
                }
                ((MTSCart)c.getValue()).save();
            }
        }
        catch (SQLException e) {
            FilePrinter.printError("MTSStorage.txt", (Throwable)e, "saveBuyNow");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
        }
        finally {
            this.cart_mutex.writeLock().unlock();
        }
        this.lastUpdate = System.currentTimeMillis();
    }
    
    public final void checkExpirations() {
        if (System.currentTimeMillis() - this.lastUpdate > 3600000L) {
            this.saveBuyNow(false);
        }
    }
    
    public final MTSCart getCart(final int characterId) {
        this.cart_mutex.readLock().lock();
        MTSCart ret;
        try {
            ret = (MTSCart)this.idToCart.get((Object)Integer.valueOf(characterId));
        }
        finally {
            this.cart_mutex.readLock().unlock();
        }
        if (ret == null) {
            this.cart_mutex.writeLock().lock();
            try {
                ret = new MTSCart(characterId);
                this.idToCart.put(Integer.valueOf(characterId), ret);
            }
            catch (SQLException e) {
                FilePrinter.printError("MTSStorage.txt", (Throwable)e, "getCart");
            }
            finally {
                this.cart_mutex.writeLock().unlock();
            }
        }
        return ret;
    }
    
    public final byte[] getCurrentMTS(final MTSCart cart) {
        this.mutex.readLock().lock();
        try {
            if (cart.getTab() == 1) {
                return MTSCSPacket.sendMTS(this.getBuyNow(cart.getType(), cart.getPage()), cart.getTab(), cart.getType(), cart.getPage(), this.buyNow.size() / 16 + ((this.buyNow.size() % 16 > 0) ? 1 : 0));
            }
            if (cart.getTab() == 4) {
                return MTSCSPacket.sendMTS(this.getCartItems(cart), cart.getTab(), cart.getType(), cart.getPage(), 0);
            }
            return MTSCSPacket.sendMTS((List<MTSItemInfo>)new ArrayList<MTSItemInfo>(), cart.getTab(), cart.getType(), cart.getPage(), 0);
        }
        finally {
            this.mutex.readLock().unlock();
        }
    }
    
    public final byte[] getCurrentNotYetSold(final MTSCart cart) {
        this.mutex.readLock().lock();
        try {
            final List<MTSItemInfo> nys = new ArrayList<MTSItemInfo>();
            final List<Integer> nyss = new ArrayList<Integer>((Collection<? extends Integer>)cart.getNotYetSold());
            final Iterator<Integer> iterator = nyss.iterator();
            while (iterator.hasNext()) {
                final int i = (int)Integer.valueOf(iterator.next());
                final MTSItemInfo r = (MTSItemInfo)this.buyNow.get((Object)Integer.valueOf(i));
                if (r == null) {
                    cart.removeFromNotYetSold(i);
                }
                else {
                    nys.add(r);
                }
            }
            return MTSCSPacket.getNotYetSoldInv(nys);
        }
        finally {
            this.mutex.readLock().unlock();
        }
    }
    
    public final byte[] getCurrentTransfer(final MTSCart cart, final boolean changed) {
        return MTSCSPacket.getTransferInventory(cart.getInventory(), changed);
    }
    
    private List<MTSItemInfo> getBuyNow(final int type, int page) {
        final int size = this.buyNow.size() / 16 + ((this.buyNow.size() % 16 > 0) ? 1 : 0);
        final List<MTSItemInfo> ret = new ArrayList<MTSItemInfo>();
        final List<MTSItemInfo> rett = new ArrayList<MTSItemInfo>((Collection<? extends MTSItemInfo>)this.buyNow.values());
        if (page > size) {
            page = 0;
        }
        for (int i = page * 16; i < page * 16 + 16 && this.buyNow.size() >= i + 1; ++i) {
            final MTSItemInfo r = (MTSItemInfo)rett.get(i);
            if (r != null && (type == 0 || GameConstants.getInventoryType(r.getItem().getItemId()).getType() == type)) {
                ret.add(r);
            }
        }
        return ret;
    }
    
    private List<MTSItemInfo> getCartItems(final MTSCart cart) {
        final List<MTSItemInfo> ret = new ArrayList<MTSItemInfo>();
        final List<Integer> cartt = new ArrayList<Integer>((Collection<? extends Integer>)cart.getCart());
        final Iterator<Integer> iterator = cartt.iterator();
        while (iterator.hasNext()) {
            final int i = (int)Integer.valueOf(iterator.next());
            final MTSItemInfo r = (MTSItemInfo)this.buyNow.get((Object)Integer.valueOf(i));
            if (r == null) {
                cart.removeFromCart(i);
            }
            else {
                if (cart.getType() != 0 && GameConstants.getInventoryType(r.getItem().getItemId()).getType() != cart.getType()) {
                    continue;
                }
                ret.add(r);
            }
        }
        return ret;
    }
    
    public static class MTSItemInfo
    {
        private final int price;
        private final IItem item;
        private final String seller;
        private final int id;
        private final int cid;
        private final long date;
        
        public MTSItemInfo(final int price, final IItem item, final String seller, final int id, final int cid, final long date) {
            this.item = item;
            this.price = price;
            this.seller = seller;
            this.id = id;
            this.cid = cid;
            this.date = date;
        }
        
        public IItem getItem() {
            return this.item;
        }
        
        public int getPrice() {
            return this.price;
        }
        
        public int getRealPrice() {
            return this.price + this.getTaxes();
        }
        
        public int getTaxes() {
            return 100 + this.price * 10 / 100;
        }
        
        public int getId() {
            return this.id;
        }
        
        public int getCharacterId() {
            return this.cid;
        }
        
        public long getEndingDate() {
            return this.date;
        }
        
        public String getSeller() {
            return this.seller;
        }
    }
}
