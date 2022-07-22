package server;

import gui.CongMS;
import client.inventory.MapleInventoryType;
import tools.packet.PlayerShopPacket;
import handling.channel.ChannelServer;
import constants.ServerConfig;
import client.messages.CommandProcessor;
import constants.ServerConstants.CommandType;
import client.MapleClient;
import java.util.Iterator;
import handling.world.World.Broadcast;
import tools.MaplePacketCreator;
import tools.FileoutputUtil;
import constants.GameConstants;
import client.inventory.ItemFlag;
import java.util.LinkedList;
import client.MapleCharacter;
import java.lang.ref.WeakReference;
import client.inventory.IItem;
import java.util.List;

public class MapleTrade
{
    private MapleTrade partner;
    private final List<IItem> items;
    private List<IItem> exchangeItems;
    private int meso;
    private int exchangeMeso;
    private boolean locked;
    private final WeakReference<MapleCharacter> chr;
    private final byte tradingslot;
    
    public MapleTrade(final byte tradingslot, final MapleCharacter chr) {
        this.partner = null;
        this.items = new LinkedList<IItem>();
        this.meso = 0;
        this.exchangeMeso = 0;
        this.locked = false;
        this.tradingslot = tradingslot;
        this.chr = new WeakReference<MapleCharacter>(chr);
    }
    
    public final void CompleteTrade() {
        final MapleTrade local = ((MapleCharacter)this.chr.get()).getTrade();
        final MapleTrade partner = local.getPartner();
        if (this.exchangeItems != null) {
            for (final IItem item : this.exchangeItems) {
                final byte flag = item.getFlag();
                if (ItemFlag.KARMA_EQ.check((int)flag)) {
                    item.setFlag((byte)(flag - ItemFlag.KARMA_EQ.getValue()));
                }
                else if (ItemFlag.KARMA_USE.check((int)flag)) {
                    item.setFlag((byte)(flag - ItemFlag.KARMA_USE.getValue()));
                }
                MapleInventoryManipulator.addFromDrop(((MapleCharacter)this.chr.get()).getClient(), item, false);
            }
            String output = "";
            for (final IItem item2 : this.exchangeItems) {
                output = output + item2.getItemId() + "(" + (int)item2.getQuantity() + "), ";
            }
            if (this.exchangeMeso - GameConstants.getTaxAmount(this.exchangeMeso) >= 50000000) {
                FileoutputUtil.logToFile("logs/Data/大額金幣交易.txt", FileoutputUtil.NowTime() + "角色名字:" + ((MapleCharacter)this.chr.get()).getName() + " 和 " + ((MapleCharacter)partner.chr.get()).getName() + " 交易获得 金幣" + (this.exchangeMeso - GameConstants.getTaxAmount(this.exchangeMeso)) + "\r\n");
                Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语] 大額金幣交易 疑似賣游戏币 角色名字:" + ((MapleCharacter)this.chr.get()).getName() + " 和 " + ((MapleCharacter)partner.chr.get()).getName() + " 交易获得 金幣" + (this.exchangeMeso - GameConstants.getTaxAmount(this.exchangeMeso))));
            }
            FileoutputUtil.logToFile("logs/Data/交易記錄.txt", FileoutputUtil.NowTime() + " 账号角色名字:" + ((MapleCharacter)this.chr.get()).getClient().getAccountName() + " " + ((MapleCharacter)this.chr.get()).getName() + " 和 " + ((MapleCharacter)partner.chr.get()).getClient().getAccountName() + " " + ((MapleCharacter)partner.chr.get()).getName() + " 交易获得 金幣" + (this.exchangeMeso - GameConstants.getTaxAmount(this.exchangeMeso)) + " 和 " + this.exchangeItems.size() + "件物品[" + output + "]\r\n");
            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM密语] 角色名字:" + ((MapleCharacter)this.chr.get()).getName() + " 和 " + ((MapleCharacter)partner.chr.get()).getName() + " 交易获得 金幣" + (this.exchangeMeso - GameConstants.getTaxAmount(this.exchangeMeso)) + " 和 " + this.exchangeItems.size() + "件物品[" + output + "]"));
            this.exchangeItems.clear();
        }
        if (this.exchangeMeso > 0) {
            ((MapleCharacter)this.chr.get()).gainMeso(this.exchangeMeso - GameConstants.getTaxAmount(this.exchangeMeso), false, true, false);
        }
        this.exchangeMeso = 0;
        ((MapleCharacter)this.chr.get()).getClient().sendPacket(MaplePacketCreator.TradeMessage(this.tradingslot, (byte)8));
        try {
            ((MapleCharacter)this.chr.get()).saveToDB(false, false);
        }
        catch (Exception ex) {
            FileoutputUtil.logToFile("logs/交易存檔保存数据異常.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + ((MapleCharacter)this.chr.get()).getClient().getSession().remoteAddress().toString().split(":")[0] + " 账号 " + ((MapleCharacter)this.chr.get()).getClient().getAccountName() + " 账号ID " + ((MapleCharacter)this.chr.get()).getClient().getAccID() + " 角色名 " + ((MapleCharacter)this.chr.get()).getName() + " 角色ID " + ((MapleCharacter)this.chr.get()).getId());
            FileoutputUtil.outError("logs/交易存檔保存数据異常.txt", (Throwable)ex);
            System.err.println("封鎖出現錯誤 " + (Object)ex);
            FileoutputUtil.outError("logs/交易異常.txt", (Throwable)ex);
        }
    }
    
    public final void cancel(final MapleClient c) {
        this.cancel(c, 0, false);
    }
    
    public final void cancel(final MapleClient c, final int unsuccessful, final boolean check) {
        final MapleTrade local = c.getPlayer().getTrade();
        final MapleTrade partners = local.getPartner();
        if (local.isLocked() && partners.isLocked() && !check) {
            this.meso = 0;
            this.items.clear();
        }
        if (this.items != null) {
            for (final IItem item : this.items) {
                MapleInventoryManipulator.addFromDrop(c, item, false);
            }
            this.items.clear();
        }
        if (this.meso > 0) {
            c.getPlayer().gainMeso(this.meso, false, true, false);
        }
        this.meso = 0;
        c.sendPacket(MaplePacketCreator.getTradeCancel(this.tradingslot, unsuccessful));
    }
    
    public final boolean isLocked() {
        return this.locked;
    }
    
    public final void setMeso(final int meso) {
        if (this.locked || this.partner == null || meso <= 0 || this.meso + meso <= 0) {
            return;
        }
        if (((MapleCharacter)this.chr.get()).getMeso() >= meso) {
            ((MapleCharacter)this.chr.get()).gainMeso(-meso, false, true, false);
            this.meso += meso;
            ((MapleCharacter)this.chr.get()).getClient().sendPacket(MaplePacketCreator.getTradeMesoSet((byte)0, this.meso));
            if (this.partner != null) {
                this.partner.getChr().getClient().sendPacket(MaplePacketCreator.getTradeMesoSet((byte)1, this.meso));
            }
        }
    }
    
    public final void addItem(final IItem item) {
        if (this.locked || this.partner == null) {
            return;
        }
        this.items.add(item);
        ((MapleCharacter)this.chr.get()).getClient().sendPacket(MaplePacketCreator.getTradeItemAdd((byte)0, item));
        if (this.partner != null) {
            this.partner.getChr().getClient().sendPacket(MaplePacketCreator.getTradeItemAdd((byte)1, item));
        }
    }
    
    public final void chat(final String message) {
        if (this.partner == null) {
            return;
        }
        if (!CommandProcessor.processCommand(((MapleCharacter)this.chr.get()).getClient(), message, CommandType.TRADE)) {
            if (((MapleCharacter)this.chr.get()).getCanTalk()) {
                ((MapleCharacter)this.chr.get()).dropMessage(-2, ((MapleCharacter)this.chr.get()).getName() + " : " + message);
            }
            if (ServerConfig.LOG_CHAT) {
                FileoutputUtil.logToFile("logs/聊天/交易聊天.txt", " " + FileoutputUtil.NowTime() + " IP: " + ((MapleCharacter)this.chr.get()).getClient().getSession().remoteAddress().toString().split(":")[0] + " 『" + ((MapleCharacter)this.chr.get()).getName() + "』對『" + this.partner.getChr().getName() + "』的交易聊天：  " + message + "\r\n");
            }
            final StringBuilder sb = new StringBuilder("[GM 密语] 『" + ((MapleCharacter)this.chr.get()).getName() + "』對『" + this.partner.getChr().getName() + "』的交易聊天：  " + message);
            for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (final MapleCharacter chr_ : cserv.getPlayerStorage().getAllCharactersThreadSafe()) {
                    if (chr_.get_control_玩家私聊()) {
                        chr_.dropMessage(sb.toString());
                    }
                }
            }
            if (this.partner != null && ((MapleCharacter)this.chr.get()).getCanTalk()) {
                this.partner.getChr().getClient().sendPacket(PlayerShopPacket.shopChat(((MapleCharacter)this.chr.get()).getName() + " : " + message, 1));
            }
        }
    }
    
    public final MapleTrade getPartner() {
        return this.partner;
    }
    
    public final void setPartner(final MapleTrade partner) {
        if (this.locked) {
            return;
        }
        this.partner = partner;
    }
    
    public final MapleCharacter getChr() {
        return (MapleCharacter)this.chr.get();
    }
    
    public final int getNextTargetSlot() {
        if (this.items.size() >= 9) {
            return -1;
        }
        int ret = 1;
        for (final IItem item : this.items) {
            if (item.getPosition() == ret) {
                ++ret;
            }
        }
        return ret;
    }
    
    public final boolean setItems(final MapleClient c, final IItem item, byte targetSlot, final int quantity) {
        final int target = this.getNextTargetSlot();
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        if (target == -1 || GameConstants.isPet(item.getItemId()) || this.isLocked() || (GameConstants.getInventoryType(item.getItemId()) == MapleInventoryType.CASH && quantity != 1) || (GameConstants.getInventoryType(item.getItemId()) == MapleInventoryType.EQUIP && quantity != 1)) {
            return false;
        }
        if (ii.isCash(item.getItemId())) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return false;
        }
        final byte flag = item.getFlag();
        if (ItemFlag.UNTRADEABLE.check((int)flag) || ItemFlag.LOCK.check((int)flag)) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return false;
        }
        if ((ii.isDropRestricted(item.getItemId()) || ii.isAccountShared(item.getItemId())) && !ItemFlag.KARMA_EQ.check((int)flag) && !ItemFlag.KARMA_USE.check((int)flag)) {
            c.sendPacket(MaplePacketCreator.enableActions());
            return false;
        }
        final IItem tradeItem = item.copy();
        if (GameConstants.isThrowingStar(item.getItemId()) || GameConstants.isBullet(item.getItemId())) {
            tradeItem.setQuantity(item.getQuantity());
            MapleInventoryManipulator.removeFromSlot(c, GameConstants.getInventoryType(item.getItemId()), item.getPosition(), item.getQuantity(), true);
        }
        else {
            tradeItem.setQuantity((short)quantity);
            MapleInventoryManipulator.removeFromSlot(c, GameConstants.getInventoryType(item.getItemId()), item.getPosition(), (short)quantity, true);
        }
        if (targetSlot < 0) {
            targetSlot = (byte)target;
        }
        else {
            for (final IItem itemz : this.items) {
                if (itemz.getPosition() == targetSlot) {
                    targetSlot = (byte)target;
                    break;
                }
            }
        }
        tradeItem.setPosition((short)targetSlot);
        this.addItem(tradeItem);
        return true;
    }
    
    private int check() {
        if (((MapleCharacter)this.chr.get()).getMeso() + this.exchangeMeso < 0) {
            return 1;
        }
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        byte eq = 0;
        byte use = 0;
        byte setup = 0;
        byte etc = 0;
        byte cash = 0;
        for (final IItem item : this.exchangeItems) {
            switch (GameConstants.getInventoryType(item.getItemId())) {
                case EQUIP: {
                    ++eq;
                    break;
                }
                case USE: {
                    ++use;
                    break;
                }
                case SETUP: {
                    ++setup;
                    break;
                }
                case ETC: {
                    ++etc;
                    break;
                }
                case CASH: {
                    ++cash;
                    break;
                }
            }
            if (ii.isPickupRestricted(item.getItemId()) && ((MapleCharacter)this.chr.get()).getInventory(GameConstants.getInventoryType(item.getItemId())).findById(item.getItemId()) != null) {
                return 2;
            }
            if (ii.isPickupRestricted(item.getItemId()) && ((MapleCharacter)this.chr.get()).haveItem(item.getItemId(), 1, true, true)) {
                return 2;
            }
        }
        if (((MapleCharacter)this.chr.get()).getInventory(MapleInventoryType.EQUIP).getNumFreeSlot() < eq || ((MapleCharacter)this.chr.get()).getInventory(MapleInventoryType.USE).getNumFreeSlot() < use || ((MapleCharacter)this.chr.get()).getInventory(MapleInventoryType.SETUP).getNumFreeSlot() < setup || ((MapleCharacter)this.chr.get()).getInventory(MapleInventoryType.ETC).getNumFreeSlot() < etc || ((MapleCharacter)this.chr.get()).getInventory(MapleInventoryType.CASH).getNumFreeSlot() < cash) {
            return 1;
        }
        return 0;
    }
    
    public static final void completeTrade(final MapleCharacter c) {
        final MapleTrade local = c.getTrade();
        final MapleTrade partner = local.getPartner();
        if (partner == null || local.locked) {
            return;
        }
        local.locked = true;
        partner.getChr().getClient().sendPacket(MaplePacketCreator.getTradeConfirmation());
        partner.exchangeItems = local.items;
        partner.exchangeMeso = local.meso;
        if (partner.isLocked()) {
            final int lz = local.check();
            final int lz2 = partner.check();
            if (lz == 0 && lz2 == 0) {
                local.CompleteTrade();
                partner.CompleteTrade();
            }
            else {
                partner.cancel(partner.getChr().getClient(), (lz == 0) ? lz2 : lz, true);
                local.cancel(c.getClient(), (lz == 0) ? lz2 : lz, true);
            }
            partner.getChr().setTrade(null);
            c.setTrade(null);
            if (local.getChr().getClient().getAccID() == partner.getChr().getClient().getAccID()) {
                local.getChr().ban("修改数据包 - 同账号角色交易", true, true, false);
                partner.getChr().ban("修改数据包 - 同账号角色交易", true, true, false);
                FileoutputUtil.logToFile("logs/Hack/ban/交易異常.txt", "时间: " + FileoutputUtil.NowTime() + " IP: " + local.getChr().getClient().getSessionIPAddress() + " MAC: " + local.getChr().getNowMacs() + " " + local.getChr().getName() + " 和 " + partner.getChr().getName() + " 為同個账号的角色且進行交易\r\n");
                local.getChr().getClient().getSession().close();
                partner.getChr().getClient().getSession().close();
            }
        }
    }
    
    public static final void cancelTrade(final MapleTrade Localtrade, final MapleClient c) {
        Localtrade.cancel(c);
        final MapleTrade partner = Localtrade.getPartner();
        if (partner != null && partner.getChr() != null) {
            if (partner.getChr().getClient() != null) {
                partner.cancel(partner.getChr().getClient());
            }
            partner.getChr().setTrade(null);
        }
        if (Localtrade.chr.get() != null) {
            ((MapleCharacter)Localtrade.chr.get()).setTrade(null);
        }
    }
    
    public static final void startTrade(final MapleCharacter c) {
        if (c.getTrade() == null) {
            c.setTrade(new MapleTrade((byte)0, c));
            c.getClient().sendPacket(MaplePacketCreator.getTradeStart(c.getClient(), c.getTrade(), (byte)0));
        }
        else {
            c.getClient().sendPacket(MaplePacketCreator.serverNotice(5, "您目前已經在交易了"));
        }
    }
    
    public static final void inviteTrade(final MapleCharacter c1, final MapleCharacter c2) {
        final int 玩家交易开关 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"玩家交易开关"));
        if (玩家交易开关 > 0) {
            c1.getClient().sendPacket(MaplePacketCreator.serverNotice(1, "管理员从后台关闭了交易功能。"));
            return;
        }
        if (c1 == null || c1.getTrade() == null || c2 == null) {
            return;
        }
        if (c2.getPlayerShop() != null) {
            c1.getTrade().cancel(c1.getClient(), 1, false);
            c1.setTrade(null);
            c1.getClient().sendPacket(MaplePacketCreator.serverNotice(5, "對方正在忙碌中。"));
            return;
        }
        if (c1.getTrade().getPartner() != null) {
            c1.getClient().sendPacket(MaplePacketCreator.serverNotice(5, "對方正在忙碌中。"));
            c1.getClient().sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        if (c2.getTrade() == null && c1.getTrade().getPartner() == null) {
            c2.setTrade(new MapleTrade((byte)1, c2));
            c2.getTrade().setPartner(c1.getTrade());
            c1.getTrade().setPartner(c2.getTrade());
            c2.getClient().sendPacket(MaplePacketCreator.getTradeInvite(c1));
        }
        else {
            c1.getClient().sendPacket(MaplePacketCreator.serverNotice(5, "另一位玩家正在交易中"));
        }
    }
    
    public static final void visitTrade(final MapleCharacter c1, final MapleCharacter c2) {
        if (c1.getTrade() != null && c1.getTrade().getPartner() == c2.getTrade() && c2.getTrade() != null && c2.getTrade().getPartner() == c1.getTrade()) {
            c2.getClient().sendPacket(MaplePacketCreator.getTradePartnerAdd(c1));
            c1.getClient().sendPacket(MaplePacketCreator.getTradeStart(c1.getClient(), c1.getTrade(), (byte)1));
        }
        else {
            c1.getClient().sendPacket(MaplePacketCreator.serverNotice(5, "交易已經被关闭."));
        }
    }
    
    public static final void declineTrade(final MapleCharacter c) {
        final MapleTrade trade = c.getTrade();
        if (trade != null) {
            if (trade.getPartner() != null) {
                final MapleCharacter other = trade.getPartner().getChr();
                if (other != null) {
                    other.getTrade().cancel(other.getClient());
                    other.setTrade(null);
                    other.dropMessage(5, c.getName() + " 拒絕了你的邀請.");
                }
            }
            trade.cancel(c.getClient());
            c.setTrade(null);
        }
    }
}
