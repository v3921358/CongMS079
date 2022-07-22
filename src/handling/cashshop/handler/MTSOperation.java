package handling.cashshop.handler;

import server.MTSStorage.MTSItemInfo;
import server.MTSCart;
import client.inventory.IItem;
import server.MapleInventoryManipulator;
import client.inventory.Equip;
import client.inventory.MapleInventoryType;
import server.MapleItemInformationProvider;
import constants.GameConstants;
import tools.packet.MTSCSPacket;
import server.MTSStorage;
import client.MapleClient;
import tools.data.LittleEndianAccessor;

public class MTSOperation
{
    public static void MTSOperation(final LittleEndianAccessor slea, final MapleClient c) {
        if (c != null && c.getPlayer() != null && MTSStorage.getInstance() != null) {
            final MTSCart cart = MTSStorage.getInstance().getCart(c.getPlayer().getId());
            if (slea.available() <= 0L) {
                doMTSPackets(cart, c);
                return;
            }
            final byte op = slea.readByte();
            if (op == 1) {
                final byte invType = slea.readByte();
                if (invType != 1 && invType != 2) {
                    c.sendPacket(MTSCSPacket.getMTSFailSell());
                    doMTSPackets(cart, c);
                    return;
                }
                final int itemid = slea.readInt();
                if (slea.readByte() != 0) {
                    c.sendPacket(MTSCSPacket.getMTSFailSell());
                    doMTSPackets(cart, c);
                    return;
                }
                slea.skip(8);
                short stars = 1;
                short quantity = 1;
                byte slot = 0;
                if (invType == 1) {
                    slea.skip(32);
                }
                else {
                    stars = slea.readShort();
                }
                slea.readMapleAsciiString();
                if (invType == 1) {
                    slea.skip(32);
                }
                else {
                    slea.readShort();
                    if (GameConstants.isThrowingStar(itemid) || GameConstants.isBullet(itemid)) {
                        slea.skip(8);
                    }
                    slot = (byte)slea.readInt();
                    if (GameConstants.isThrowingStar(itemid) || GameConstants.isBullet(itemid)) {
                        quantity = stars;
                        slea.skip(4);
                    }
                    else {
                        quantity = (short)slea.readInt();
                    }
                }
                final int price = slea.readInt();
                final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
                final MapleInventoryType type = GameConstants.getInventoryType(itemid);
                final IItem item = c.getPlayer().getInventory(type).getItem((short)slot).copy();
                if (ii.isCash(itemid) || quantity <= 0 || item == null || item.getQuantity() <= 0 || item.getItemId() != itemid || item.getUniqueId() > 0 || item.getQuantity() < quantity || price < 110 || c.getPlayer().getMeso() < 5000 || cart.getNotYetSold().size() >= 10 || ii.isDropRestricted(itemid) || ii.isAccountShared(itemid) || item.getExpiration() > -1L || item.getFlag() > 0) {
                    c.sendPacket(MTSCSPacket.getMTSFailSell());
                    doMTSPackets(cart, c);
                    return;
                }
                if (type == MapleInventoryType.EQUIP) {
                    final Equip eq = (Equip)item;
                    if (eq.getState() > 0 || eq.getEnhance() > 0 || eq.getDurability() > -1) {
                        c.sendPacket(MTSCSPacket.getMTSFailSell());
                        doMTSPackets(cart, c);
                        return;
                    }
                }
                if (quantity >= 50 && GameConstants.isUpgradeScroll(item.getItemId())) {
                    c.setMonitored(true);
                }
                final long expiration = System.currentTimeMillis() + 604800000L;
                item.setQuantity(quantity);
                MTSStorage.getInstance().addToBuyNow(cart, item, price, c.getPlayer().getId(), c.getPlayer().getName(), expiration);
                MapleInventoryManipulator.removeFromSlot(c, type, (short)slot, quantity, false);
                c.getPlayer().gainMeso(-5000, false);
                c.sendPacket(MTSCSPacket.getMTSConfirmSell());
            }
            else if (op == 4) {
                cart.changeInfo(slea.readInt(), slea.readInt(), slea.readInt());
            }
            else if (op == 7) {
                if (MTSStorage.getInstance().removeFromBuyNow(slea.readInt(), c.getPlayer().getId(), true)) {
                    c.sendPacket(MTSCSPacket.getMTSConfirmCancel());
                    sendMTSPackets(cart, c, true);
                    return;
                }
                c.sendPacket(MTSCSPacket.getMTSFailCancel());
            }
            else if (op == 8) {
                final int id = Integer.MAX_VALUE - slea.readInt();
                if (id >= cart.getInventory().size()) {
                    c.getPlayer().dropMessage(1, "Please try it again later.");
                    sendMTSPackets(cart, c, true);
                    return;
                }
                final IItem item2 = (IItem)cart.getInventory().get(id);
                if (item2 != null && item2.getQuantity() > 0 && MapleInventoryManipulator.checkSpace(c, item2.getItemId(), (int)item2.getQuantity(), item2.getOwner())) {
                    final IItem item_ = item2.copy();
                    final short pos = MapleInventoryManipulator.addbyItem(c, item_, true);
                    if (pos >= 0) {
                        if (item_.getPet() != null) {
                            item_.getPet().setInventoryPosition(pos);
                            c.getPlayer().addPet(item_.getPet());
                        }
                        cart.removeFromInventory(item2);
                        c.sendPacket(MTSCSPacket.getMTSConfirmTransfer((int)item_.getQuantity(), (int)pos));
                        sendMTSPackets(cart, c, true);
                        return;
                    }
                    c.sendPacket(MTSCSPacket.getMTSFailBuy());
                }
                else {
                    c.sendPacket(MTSCSPacket.getMTSFailBuy());
                }
            }
            else if (op == 9) {
                final int id = slea.readInt();
                if (MTSStorage.getInstance().checkCart(id, c.getPlayer().getId()) && cart.addToCart(id)) {
                    c.sendPacket(MTSCSPacket.addToCartMessage(false, false));
                }
                else {
                    c.sendPacket(MTSCSPacket.addToCartMessage(true, false));
                }
            }
            else if (op == 10) {
                final int id = slea.readInt();
                if (cart.getCart().contains((Object)Integer.valueOf(id))) {
                    cart.removeFromCart(id);
                    c.sendPacket(MTSCSPacket.addToCartMessage(false, true));
                }
                else {
                    c.sendPacket(MTSCSPacket.addToCartMessage(true, true));
                }
            }
            else if (op == 16 || op == 17) {
                final MTSItemInfo mts = MTSStorage.getInstance().getSingleItem(slea.readInt());
                if (mts != null && mts.getCharacterId() != c.getPlayer().getId()) {
                    if (c.getPlayer().getCSPoints(1) > mts.getRealPrice()) {
                        if (MTSStorage.getInstance().removeFromBuyNow(mts.getId(), c.getPlayer().getId(), false)) {
                            c.getPlayer().modifyCSPoints(1, -mts.getRealPrice(), false);
                            MTSStorage.getInstance().getCart(mts.getCharacterId()).increaseOwedNX(mts.getPrice());
                            c.sendPacket(MTSCSPacket.getMTSConfirmBuy());
                            sendMTSPackets(cart, c, true);
                            return;
                        }
                        c.sendPacket(MTSCSPacket.getMTSFailBuy());
                    }
                    else {
                        c.sendPacket(MTSCSPacket.getMTSFailBuy());
                    }
                }
                else {
                    c.sendPacket(MTSCSPacket.getMTSFailBuy());
                }
            }
            else if (c.getPlayer().hasGmLevel(5)) {}
            doMTSPackets(cart, c);
        }
    }
    
    public static void MTSUpdate(final MTSCart cart, final MapleClient c) {
        c.getPlayer().modifyCSPoints(1, MTSStorage.getInstance().getCart(c.getPlayer().getId()).getSetOwedNX(), false);
        c.sendPacket(MTSCSPacket.getMTSWantedListingOver(0, 0));
        doMTSPackets(cart, c);
    }
    
    private static void doMTSPackets(final MTSCart cart, final MapleClient c) {
        sendMTSPackets(cart, c, false);
    }
    
    private static void sendMTSPackets(final MTSCart cart, final MapleClient c, final boolean changed) {
        c.sendPacket(MTSStorage.getInstance().getCurrentMTS(cart));
        c.sendPacket(MTSStorage.getInstance().getCurrentNotYetSold(cart));
        c.sendPacket(MTSStorage.getInstance().getCurrentTransfer(cart, changed));
        c.sendPacket(MTSCSPacket.showMTSCash(c.getPlayer()));
        c.sendPacket(MTSCSPacket.enableCSUse());
        MTSStorage.getInstance().checkExpirations();
    }
}
