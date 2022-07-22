package server.shops;

import server.maps.MapleMapObjectType;
import server.maps.MapleMapObject;
import tools.packet.PlayerShopPacket;
import client.inventory.IItem;
import handling.channel.ChannelServer;
import tools.FileoutputUtil;
import constants.ServerConfig;
import server.MapleItemInformationProvider;
import constants.GameConstants;
import server.MapleInventoryManipulator;
import client.inventory.ItemFlag;
import tools.MaplePacketCreator;
import client.MapleClient;
import java.util.Iterator;
import server.Timer.EtcTimer;
import java.util.LinkedList;
import client.MapleCharacter;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

public class HiredMerchant extends AbstractPlayerStore
{
    public ScheduledFuture<?> schedule;
    private final List<String> blacklist;
    private int storeid;
    private final long start;
    
    public HiredMerchant(final MapleCharacter owner, final int itemId, final String desc) {
        super(owner, itemId, desc, "", 3);
        this.start = System.currentTimeMillis();
        this.blacklist = new LinkedList<String>();
        this.schedule = EtcTimer.getInstance().schedule((Runnable)new Runnable() {
            @Override
            public void run() {
                HiredMerchant.this.removeAllVisitors(-1, -1);
                HiredMerchant.this.closeShop(true, true);
            }
        }, 259200000L);
    }
    
    @Override
    public byte getShopType() {
        return 1;
    }
    
    public final void setStoreId(final int storeid) {
        this.storeid = storeid;
    }
    
    public List<MaplePlayerShopItem> searchItem(final int itemSearch) {
        final List<MaplePlayerShopItem> itemz = new LinkedList<MaplePlayerShopItem>();
        for (final MaplePlayerShopItem item : this.items) {
            if (item.item.getItemId() == itemSearch && item.bundles > 0) {
                itemz.add(item);
            }
        }
        return itemz;
    }
    
    @Override
    public void buy(final MapleClient c, final int item, final short quantity) {
        final MaplePlayerShopItem pItem = (MaplePlayerShopItem)this.items.get(item);
        final IItem shopItem = pItem.item;
        final IItem newItem = shopItem.copy();
        final short perbundle = newItem.getQuantity();
        final int theQuantity = pItem.price * quantity;
        newItem.setQuantity((short)(quantity * perbundle));
        if (pItem.bundles <= 0 || pItem.bundles >= 60000) {
            c.getPlayer().dropMessage(1, "系統繁忙，請稍後再試！");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        final byte flag = newItem.getFlag();
        if (ItemFlag.KARMA_EQ.check((int)flag)) {
            newItem.setFlag((byte)(flag - ItemFlag.KARMA_EQ.getValue()));
        }
        else if (ItemFlag.KARMA_USE.check((int)flag)) {
            newItem.setFlag((byte)(flag - ItemFlag.KARMA_USE.getValue()));
        }
        if (!c.getPlayer().canHold(newItem.getItemId())) {
            c.getPlayer().dropMessage(1, "您的背包滿了");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (pItem.bundles <= 0 || pItem.bundles >= 60000) {
            c.getPlayer().dropMessage(1, "系統繁忙，請稍後再試！");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (MapleInventoryManipulator.addFromDrop(c, newItem, false)) {
            final MaplePlayerShopItem maplePlayerShopItem = pItem;
            maplePlayerShopItem.bundles -= quantity;
            final int gainmeso = this.getMeso() + pItem.price * quantity - GameConstants.EntrustedStoreTax(pItem.price * quantity);
            this.setMeso(gainmeso);
            c.getPlayer().gainMeso(-pItem.price * quantity, false);
            final MapleCharacter Owner = this.getMCOwnerWorld();
            if (Owner != null) {
                Owner.dropMessage(5, "道具 " + MapleItemInformationProvider.getInstance().getName(newItem.getItemId()) + " (" + (int)perbundle + ") × " + (int)quantity + " 已被其他玩家购买，還剩下：" + (int)pItem.bundles + " 個");
            }
            newItem.setGMLog(c.getPlayer().getName() + " Buy from  " + this.getOwnerName() + "'s Merchant " + newItem.getItemId() + "x" + (int)quantity + " Prize : " + pItem.price);
            if (ServerConfig.LOG_MRECHANT) {
                FileoutputUtil.logToFile("logs/Data/精灵商人.txt", "\r\n 时间\u3000[" + FileoutputUtil.NowTime() + "] IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 玩家 " + c.getAccountName() + " " + c.getPlayer().getName() + " 從  " + this.getOwnerName() + " 的精灵商人购买了" + MapleItemInformationProvider.getInstance().getName(newItem.getItemId()) + " (" + newItem.getItemId() + ") x" + (int)quantity + " 單個價錢為 : " + pItem.price);
            }
            final StringBuilder sb = new StringBuilder("[GM 密语] 玩家 " + c.getPlayer().getName() + " 從  " + this.getOwnerName() + " 的精灵商人购买了 " + MapleItemInformationProvider.getInstance().getName(newItem.getItemId()) + "(" + newItem.getItemId() + ") x" + (int)quantity + " 單個價錢為 : " + pItem.price);
            for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharactersThreadSafe()) {
                    if (chr.get_control_精灵商人()) {
                        chr.dropMessage(sb.toString());
                    }
                }
            }
        }
        else {
            c.getPlayer().dropMessage(1, "您的背包滿了，請檢查您的背包！");
            c.sendPacket(MaplePacketCreator.enableActions());
        }
    }
    
    @Override
    public void closeShop(final boolean saveItems, final boolean remove) {
        try {
            if (this.schedule != null) {
                this.schedule.cancel(false);
            }
            if (saveItems) {
                this.saveItems();
                this.items.clear();
            }
            if (remove) {
                ChannelServer.getInstance(this.channel).removeMerchant(this);
                this.getMap().broadcastMessage(PlayerShopPacket.destroyHiredMerchant(this.getOwnerId()));
            }
            this.setCanShop(false);
            this.getMap().removeMapObject((MapleMapObject)this);
            this.schedule = null;
        }
        catch (Exception se) {
            FileoutputUtil.outError("logs/精灵商人关闭異常.txt", (Throwable)se);
        }
    }
    
    public int getTimeLeft() {
        return (int)((System.currentTimeMillis() - this.start) / 1000L);
    }
    
    public final int getStoreId() {
        return this.storeid;
    }
    
    @Override
    public MapleMapObjectType getType() {
        return MapleMapObjectType.HIRED_MERCHANT;
    }
    
    @Override
    public void sendDestroyData(final MapleClient client) {
        if (this.isAvailable()) {
            client.sendPacket(PlayerShopPacket.destroyHiredMerchant(this.getOwnerId()));
        }
    }
    
    @Override
    public void sendSpawnData(final MapleClient client) {
        if (this.isAvailable()) {
            client.sendPacket(PlayerShopPacket.spawnHiredMerchant(this));
        }
    }
    
    public final boolean isInBlackList(final String bl) {
        return this.blacklist.contains((Object)bl);
    }
    
    public final void addBlackList(final String bl) {
        this.blacklist.add(bl);
    }
    
    public final void removeBlackList(final String bl) {
        this.blacklist.remove((Object)bl);
    }
    
    public final void sendBlackList(final MapleClient c) {
        c.sendPacket(PlayerShopPacket.MerchantBlackListView(this.blacklist));
    }
    
    public final void sendVisitor(final MapleClient c) {
        c.sendPacket(PlayerShopPacket.MerchantVisitorView(this.visitors));
    }
}
