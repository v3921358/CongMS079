package handling.channel.handler;

import client.inventory.IItem;
import tools.FileoutputUtil;
import server.MapleInventoryManipulator;
import server.shops.MaplePlayerShopItem;
import client.inventory.ItemFlag;
import constants.GameConstants;
import server.MapleItemInformationProvider;
import scripting.NPCScriptManager;
import handling.world.World.Broadcast;
import tools.Pair;
import server.shops.HiredMerchant;
import tools.packet.PlayerShopPacket;
import server.shops.MaplePlayerShop;
import handling.world.World;
import client.inventory.MapleInventoryType;
import server.maps.MapleMapObject;
import server.shops.IMaplePlayerShop;
import server.shops.MapleMiniGame;
import server.maps.FieldLimitType;
import java.util.Arrays;
import server.maps.MapleMapObjectType;
import server.MapleTrade;
import tools.MaplePacketCreator;
import client.MapleCharacter;
import client.MapleClient;
import tools.data.LittleEndianAccessor;

public class PlayerInteractionHandler
{
    public static final byte CREATE = 0;
    public static final byte INVITE_TRADE = 2;
    public static final byte DENY_TRADE = 3;
    public static final byte VISIT = 4;
    public static final byte CHAT = 6;
    public static final byte EXIT = 10;
    public static final byte OPEN = 11;
    public static final byte CASH_ITEM_INTER = 13;
    public static final byte SET_ITEMS = 14;
    public static final byte SET_MESO = 15;
    public static final byte CONFIRM_TRADE = 16;
    public static final byte TRADE_SOMETHING = 18;
    public static final byte PLAYER_SHOP_ADD_ITEM = 20;
    public static final byte BUY_ITEM_PLAYER_SHOP = 21;
    public static final byte KICK_Player = 26;
    public static final byte MERCHANT_EXIT = 27;
    public static final byte ADD_ITEM = 31;
    public static final byte BUY_ITEM_STORE = 32;
    public static final byte BUY_ITEM_HIREDMERCHANT = 33;
    public static final byte REMOVE_ITEM = 35;
    public static final byte REMOVE_ITEM_PS = 36;
    public static final byte MAINTANCE_OFF = 37;
    public static final byte MAINTANCE_ORGANISE = 38;
    public static final byte CLOSE_MERCHANT = 39;
    public static final byte ADMIN_STORE_NAMECHANGE = 43;
    public static final byte VIEW_MERCHANT_VISITOR = 44;
    public static final byte VIEW_MERCHANT_BLACKLIST = 45;
    public static final byte MERCHANT_BLACKLIST_ADD = 46;
    public static final byte MERCHANT_BLACKLIST_REMOVE = 47;
    public static final byte REQUEST_TIE = 48;
    public static final byte ANSWER_TIE = 49;
    public static final byte GIVE_UP = 50;
    public static final byte REQUEST_REDO = 53;
    public static final byte ANSWER_REDO = 54;
    public static final byte EXIT_AFTER_GAME = 55;
    public static final byte CANCEL_EXIT = 56;
    public static final byte READY = 57;
    public static final byte UN_READY = 58;
    public static final byte EXPEL = 59;
    public static final byte START = 60;
    public static final byte SKIP = 61;
    public static final byte MOVE_OMOK = 62;
    public static final byte SELECT_CARD = 67;
    
    public static final void PlayerInteraction(final LittleEndianAccessor slea, final MapleClient c, final MapleCharacter chr) {
        if (chr == null) {
            return;
        }
        if (chr.getAntiMacro().inProgress()) {
            chr.dropMessage(5, "被使用測謊儀時無法操作。");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        final byte action = slea.readByte();
        switch (action) {
            case 26: {
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips != null) {
                    ips.removeAllVisitors(5, 1);
                    break;
                }
                break;
            }
            case 0: {
                final byte createType = slea.readByte();
                if (createType == 3) {
                    MapleTrade.startTrade(chr);
                    break;
                }
                if (createType != 1 && createType != 2 && createType != 4 && createType != 5) {
                    break;
                }
                if (!chr.getMap().getMapObjectsInRange(chr.getPosition(), 20000.0, Arrays.asList(MapleMapObjectType.SHOP, MapleMapObjectType.HIRED_MERCHANT)).isEmpty()) {
                    chr.dropMessage(1, "此处无法建立商店");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                if ((createType == 1 || createType == 2) && FieldLimitType.Minigames.check(chr.getMap().getFieldLimit())) {
                    chr.dropMessage(1, "此处无法使用小游戏.");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                final String desc = slea.readMapleAsciiString();
                String pass = "";
                if (slea.readByte() > 0 && (createType == 1 || createType == 2)) {
                    pass = slea.readMapleAsciiString();
                }
                if (createType == 1 || createType == 2) {
                    final int piece = slea.readByte();
                    final int itemId = (createType == 1) ? (4080000 + piece) : 4080100;
                    if (!chr.haveItem(itemId) || (c.getPlayer().getMapId() >= 910000001 && c.getPlayer().getMapId() <= 910000022)) {
                        return;
                    }
                    final MapleMiniGame game = new MapleMiniGame(chr, itemId, desc, pass, (int)createType);
                    game.setPieceType(piece);
                    chr.setPlayerShop((IMaplePlayerShop)game);
                    game.setAvailable(true);
                    game.setOpen(true);
                    game.send(c);
                    chr.getMap().addMapObject((MapleMapObject)game);
                    game.update();
                }
                else {
                    final IItem shop = c.getPlayer().getInventory(MapleInventoryType.CASH).getItem((short)(byte)slea.readShort());
                    if (shop == null || shop.getQuantity() <= 0 || shop.getItemId() != slea.readInt() || c.getPlayer().getMapId() < 910000001 || c.getPlayer().getMapId() > 910000022) {
                        return;
                    }
                    if (World.isShutDown) {
                        chr.dropMessage(1, "游戏即将关闭所以不能使用商店.");
                        c.sendPacket(MaplePacketCreator.enableActions());
                        return;
                    }
                    if (World.isShopShutDown) {
                        chr.dropMessage(1, "游戏即将关闭所以不能使用商店.");
                        c.sendPacket(MaplePacketCreator.enableActions());
                        return;
                    }
                    if (!chr.getCanTalk()) {
                        c.sendPacket(MaplePacketCreator.enableActions());
                        return;
                    }
                    if (createType == 4) {
                        final MaplePlayerShop mps = new MaplePlayerShop(chr, shop.getItemId(), desc);
                        chr.setPlayerShop((IMaplePlayerShop)mps);
                        chr.getMap().addMapObject((MapleMapObject)mps);
                        c.sendPacket(PlayerShopPacket.getPlayerStore(chr, true));
                    }
                    else {
                        final HiredMerchant merch = new HiredMerchant(chr, shop.getItemId(), desc);
                        chr.setPlayerShop((IMaplePlayerShop)merch);
                        chr.getMap().addMapObject((MapleMapObject)merch);
                        merch.setCanShop(true);
                        c.sendPacket(PlayerShopPacket.getHiredMerch(chr, merch, true));
                        chr.getStat().recalcLocalStats();
                    }
                }
                break;
            }
            case 2: {
                MapleTrade.inviteTrade(chr, chr.getMap().getCharacterById(slea.readInt()));
                break;
            }
            case 3: {
                MapleTrade.declineTrade(chr);
                break;
            }
            case 13: {
                final byte type = slea.readByte();
                final byte csInvite = slea.readByte();
                final int 未知类型 = slea.readInt();
                if (World.isShutDown) {
                    chr.dropMessage(1, "游戏即將关闭所以不能使用商店.");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                if (World.isShopShutDown) {
                    chr.dropMessage(1, "游戏即將关闭所以不能使用商店.");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                if (chr.getTrade() != null && chr.getTrade().getPartner() != null) {
                    MapleTrade.visitTrade(chr, chr.getTrade().getPartner().getChr());
                    break;
                }
                if (chr.getMap() != null) {
                    final int obid = slea.readInt();
                    MapleMapObject ob = chr.getMap().getMapObject(obid, MapleMapObjectType.HIRED_MERCHANT);
                    if (ob == null) {
                        ob = chr.getMap().getMapObject(obid, MapleMapObjectType.SHOP);
                    }
                    if (ob instanceof IMaplePlayerShop && chr.getPlayerShop() == null) {
                        final IMaplePlayerShop ips2 = (IMaplePlayerShop)ob;
                        if (ob instanceof HiredMerchant) {
                            final HiredMerchant merchant = (HiredMerchant)ips2;
                            if (merchant.isOwner(chr)) {
                                merchant.setOpen(false);
                                merchant.removeAllVisitors(18, 1);
                                chr.setPlayerShop(ips2);
                                c.sendPacket(PlayerShopPacket.getHiredMerch(chr, merchant, false));
                            }
                            else if (!merchant.isOpen() || !merchant.isAvailable()) {
                                chr.dropMessage(1, "这个商店在整理或者是沒在贩卖东西。");
                            }
                            else if (ips2.getFreeSlot() == -1) {
                                chr.dropMessage(1, "商店人数已经满了，请稍后再进入。");
                            }
                            else if (merchant.isInBlackList(chr.getName())) {
                                chr.dropMessage(1, "被加入黑名单了，所以不能进入。");
                            }
                            else {
                                chr.setPlayerShop(ips2);
                                merchant.addVisitor(chr);
                                c.sendPacket(PlayerShopPacket.getHiredMerch(chr, merchant, false));
                            }
                        }
                        else if (ips2 instanceof MaplePlayerShop && ((MaplePlayerShop)ips2).isBanned(chr.getName())) {
                            chr.dropMessage(1, "被加入黑名单了，所以不能进入。");
                        }
                        else if (ips2.getFreeSlot() < 0 || ips2.getVisitorSlot(chr) > -1 || !ips2.isOpen() || !ips2.isAvailable()) {
                            c.sendPacket(PlayerShopPacket.getMiniGameFull());
                        }
                        else {
                            if (slea.available() > 0L && slea.readByte() > 0) {
                                final String pass2 = slea.readMapleAsciiString();
                                if (!pass2.equals((Object)ips2.getPassword())) {
                                    c.getPlayer().dropMessage(1, "你输入的密码错误，请重新再试一次.");
                                    return;
                                }
                            }
                            else if (ips2.getPassword().length() > 0) {
                                c.getPlayer().dropMessage(1, "你输入的密码错误，请重新再试一次.");
                                return;
                            }
                            chr.setPlayerShop(ips2);
                            ips2.addVisitor(chr);
                            if (ips2 instanceof MapleMiniGame) {
                                ((MapleMiniGame)ips2).send(c);
                            }
                            else {
                                c.sendPacket(PlayerShopPacket.getPlayerStore(chr, false));
                            }
                        }
                    }
                    break;
                }
                break;
            }
            case 4: {
                if (World.isShutDown) {
                    chr.dropMessage(1, "游戏即將关闭所以不能使用商店.");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                if (World.isShopShutDown) {
                    chr.dropMessage(1, "游戏即將关闭所以不能使用商店.");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                if (chr.getTrade() != null && chr.getTrade().getPartner() != null) {
                    MapleTrade.visitTrade(chr, chr.getTrade().getPartner().getChr());
                    break;
                }
                if (chr.getMap() != null) {
                    final int obid2 = slea.readInt();
                    MapleMapObject ob2 = chr.getMap().getMapObject(obid2, MapleMapObjectType.HIRED_MERCHANT);
                    if (ob2 == null) {
                        ob2 = chr.getMap().getMapObject(obid2, MapleMapObjectType.SHOP);
                    }
                    if (ob2 instanceof IMaplePlayerShop && chr.getPlayerShop() == null) {
                        final IMaplePlayerShop ips3 = (IMaplePlayerShop)ob2;
                        if (ob2 instanceof HiredMerchant) {
                            final HiredMerchant merchant2 = (HiredMerchant)ips3;
                            if (merchant2.isOwner(chr)) {
                                merchant2.setOpen(false);
                                merchant2.removeAllVisitors(18, 1);
                                chr.setPlayerShop(ips3);
                                c.sendPacket(PlayerShopPacket.getHiredMerch(chr, merchant2, false));
                            }
                            else if (!merchant2.isOpen() || !merchant2.isAvailable()) {
                                chr.dropMessage(1, "这个商店在整理或者是沒在贩卖东西。");
                            }
                            else if (ips3.getFreeSlot() == -1) {
                                chr.dropMessage(1, "商店人数已经满了，请稍后再进入。");
                            }
                            else if (merchant2.isInBlackList(chr.getName())) {
                                chr.dropMessage(1, "被加入黑名單了，所以不能进入。");
                            }
                            else {
                                chr.setPlayerShop(ips3);
                                merchant2.addVisitor(chr);
                                c.sendPacket(PlayerShopPacket.getHiredMerch(chr, merchant2, false));
                            }
                        }
                        else if (ips3 instanceof MaplePlayerShop && ((MaplePlayerShop)ips3).isBanned(chr.getName())) {
                            chr.dropMessage(1, "被加入黑名單了，所以不能進入。");
                        }
                        else if (ips3.getFreeSlot() < 0 || ips3.getVisitorSlot(chr) > -1 || !ips3.isOpen() || !ips3.isAvailable()) {
                            c.sendPacket(PlayerShopPacket.getMiniGameFull());
                        }
                        else {
                            if (slea.available() > 0L && slea.readByte() > 0) {
                                final String pass3 = slea.readMapleAsciiString();
                                if (!pass3.equals((Object)ips3.getPassword())) {
                                    c.getPlayer().dropMessage(1, "你输入的密码错误，请重新再试一次.");
                                    return;
                                }
                            }
                            else if (ips3.getPassword().length() > 0) {
                                c.getPlayer().dropMessage(1, "你输入的密码错误，请重新再试一次.");
                                return;
                            }
                            chr.setPlayerShop(ips3);
                            ips3.addVisitor(chr);
                            if (ips3 instanceof MapleMiniGame) {
                                ((MapleMiniGame)ips3).send(c);
                            }
                            else {
                                c.sendPacket(PlayerShopPacket.getPlayerStore(chr, false));
                            }
                        }
                    }
                    break;
                }
                break;
            }
            case 6: {
                final String message = slea.readMapleAsciiString();
                if (chr.getTrade() != null) {
                    chr.getTrade().chat(message);
                    break;
                }
                if (chr.getPlayerShop() != null) {
                    final IMaplePlayerShop ips4 = chr.getPlayerShop();
                    ips4.getMessages().add(new Pair<String, Byte>(chr.getName() + " : " + message, Byte.valueOf(ips4.getVisitorSlot(chr))));
                    ips4.broadcastToVisitors(PlayerShopPacket.shopChat(chr.getName() + " : " + message, (int)ips4.getVisitorSlot(chr)));
                    if (chr.getClient().isMonitored()) {
                        Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, chr.getName() + " said in " + ips4.getOwnerName() + " shop : " + message));
                    }
                    break;
                }
                break;
            }
            case 10: {
                if (chr.getTrade() != null) {
                    if (chr.getTrade().getPartner() != null) {
                        final MapleTrade local = c.getPlayer().getTrade();
                        final MapleTrade partners = local.getPartner();
                        if (local.isLocked() && partners.isLocked()) {
                            c.getSession().write((Object)MaplePacketCreator.enableActions());
                        }
                        else {
                            MapleTrade.cancelTrade(chr.getTrade(), chr.getClient());
                        }
                        break;
                    }
                    MapleTrade.cancelTrade(chr.getTrade(), chr.getClient());
                    break;
                }
                else {
                    final IMaplePlayerShop ips = chr.getPlayerShop();
                    if (ips == null) {
                        return;
                    }
                    if (!ips.isAvailable() || (ips.isOwner(chr) && ips.getShopType() != 1)) {
                        ips.closeShop(false, ips.isAvailable());
                    }
                    else {
                        ips.removeVisitor(chr);
                    }
                    chr.setPlayerShop(null);
                    NPCScriptManager.getInstance().dispose(c);
                    c.sendPacket(MaplePacketCreator.enableActions());
                    break;
                }
            }
            case 11: {
                final IMaplePlayerShop shop2 = chr.getPlayerShop();
                if (shop2 == null || !shop2.isOwner(chr) || shop2.getShopType() >= 3) {
                    break;
                }
                if (!chr.getMap().allowPersonalShop()) {
                    c.getSession().close();
                    break;
                }
                if (World.isShutDown) {
                    chr.dropMessage(1, "游戏即將关闭所以不能使用商店.");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    shop2.closeShop(shop2.getShopType() == 1, false);
                    return;
                }
                if (World.isShopShutDown) {
                    chr.dropMessage(1, "游戏即將关闭所以不能使用商店.");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    shop2.closeShop(shop2.getShopType() == 1, false);
                    return;
                }
                if (shop2.getShopType() == 1) {
                    final HiredMerchant merchant3 = (HiredMerchant)shop2;
                    merchant3.setStoreId(c.getChannelServer().addMerchant(merchant3));
                    merchant3.setOpen(true);
                    merchant3.setAvailable(true);
                    merchant3.setCanShop(true);
                    chr.getMap().broadcastMessage(PlayerShopPacket.spawnHiredMerchant(merchant3));
                    chr.setPlayerShop(null);
                    break;
                }
                if (shop2.getShopType() == 2) {
                    shop2.setOpen(true);
                    shop2.setAvailable(true);
                    shop2.setCanShop(true);
                    shop2.update();
                    final MaplePlayerShop playershop = (MaplePlayerShop)shop2;
                    c.getChannelServer().addPlayerShop(playershop);
                    break;
                }
                break;
            }
            case 14: {
                final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
                final MapleInventoryType ivType = MapleInventoryType.getByType(slea.readByte());
                final IItem item = chr.getInventory(ivType).getItem((short)(byte)slea.readShort());
                final short quantity = slea.readShort();
                final byte targetSlot = slea.readByte();
                if (chr.getTrade() != null && item != null && ((quantity <= item.getQuantity() && quantity >= 0) || GameConstants.isThrowingStar(item.getItemId()) || GameConstants.isBullet(item.getItemId()))) {
                    chr.getTrade().setItems(c, item, targetSlot, (int)quantity);
                    break;
                }
                break;
            }
            case 15: {
                final MapleTrade trade = chr.getTrade();
                if (trade != null) {
                    trade.setMeso(slea.readInt());
                    break;
                }
                break;
            }
            case 16: {
                if (chr.getTrade() != null) {
                    MapleTrade.completeTrade(chr);
                    break;
                }
                break;
            }
            case 20:
            case 31: {
                final MapleInventoryType type2 = MapleInventoryType.getByType(slea.readByte());
                final byte slot = (byte)slea.readShort();
                final short bundles = slea.readShort();
                final short perBundle = slea.readShort();
                final int price = slea.readInt();
                if (price <= 0 || bundles <= 0 || perBundle <= 0) {
                    return;
                }
                final IMaplePlayerShop shop3 = chr.getPlayerShop();
                if (shop3 == null || !shop3.isOwner(chr) || shop3 instanceof MapleMiniGame) {
                    return;
                }
                final IItem ivItem = chr.getInventory(type2).getItem((short)slot);
                final MapleItemInformationProvider ii2 = MapleItemInformationProvider.getInstance();
                if (ivItem == null) {
                    break;
                }
                final long check = (long)(bundles * perBundle);
                if (check > 32767L || check <= 0L) {
                    return;
                }
                final short bundles_perbundle = (short)(bundles * perBundle);
                if (ivItem.getQuantity() >= bundles_perbundle) {
                    final byte flag = ivItem.getFlag();
                    if (ItemFlag.UNTRADEABLE.check((int)flag) || ItemFlag.LOCK.check((int)flag)) {
                        c.sendPacket(MaplePacketCreator.enableActions());
                        return;
                    }
                    if ((ii2.isDropRestricted(ivItem.getItemId()) || ii2.isAccountShared(ivItem.getItemId())) && !ItemFlag.KARMA_EQ.check((int)flag) && !ItemFlag.KARMA_USE.check((int)flag)) {
                        c.sendPacket(MaplePacketCreator.enableActions());
                        return;
                    }
                    if (bundles_perbundle >= 50 && GameConstants.isUpgradeScroll(ivItem.getItemId())) {
                        c.setMonitored(true);
                    }
                    if (GameConstants.isThrowingStar(ivItem.getItemId()) || GameConstants.isBullet(ivItem.getItemId())) {
                        final IItem sellItem = ivItem.copyWithQuantity(ivItem.getQuantity());
                        shop3.addItem(new MaplePlayerShopItem(sellItem, (short)1, price));
                        MapleInventoryManipulator.removeFromSlot(c, type2, (short)slot, ivItem.getQuantity(), true);
                        FileoutputUtil.logToFile("logs/Data/精灵商人放入道具.txt", FileoutputUtil.NowTime() + "账号角色名字:" + c.getAccountName() + " " + c.getPlayer().getName() + " 道具： " + ivItem.getItemId() + " 數量:  " + (int)bundles + "\r\n");
                    }
                    else {
                        MapleInventoryManipulator.removeFromSlot(c, type2, (short)slot, bundles_perbundle, true);
                        final IItem sellItem = ivItem.copy();
                        sellItem.setQuantity(perBundle);
                        shop3.addItem(new MaplePlayerShopItem(sellItem, bundles, price));
                        FileoutputUtil.logToFile("logs/Data/精灵商人放入道具.txt", FileoutputUtil.NowTime() + "账号角色名字:" + c.getAccountName() + " " + c.getPlayer().getName() + " 道具： " + ivItem.getItemId() + " 數量:  " + (int)bundles + "\r\n");
                    }
                    c.sendPacket(PlayerShopPacket.shopItemUpdate(shop3));
                }
                break;
            }
            case 21:
            case 32:
            case 33: {
                if (chr.getTrade() != null) {
                    MapleTrade.completeTrade(chr);
                    break;
                }
                final int item2 = slea.readByte();
                final short quantity2 = slea.readShort();
                final IMaplePlayerShop shop4 = chr.getPlayerShop();
                if (shop4 == null || !shop4.getCanShop() || shop4.isOwner(chr) || shop4 instanceof MapleMiniGame || item2 >= shop4.getItems().size()) {
                    return;
                }
                final MaplePlayerShopItem tobuy = (MaplePlayerShopItem)shop4.getItems().get(item2);
                if (tobuy == null) {
                    return;
                }
                final long check2 = (long)(tobuy.bundles * quantity2);
                final long check3 = (long)(tobuy.price * quantity2);
                final long check4 = (long)(tobuy.item.getQuantity() * quantity2);
                if (check2 <= 0L || check3 > 2147483647L || check3 <= 0L || check4 > 32767L || check4 < 0L) {
                    return;
                }
                if (tobuy.bundles < quantity2 || (tobuy.bundles % quantity2 != 0 && GameConstants.isEquip(tobuy.item.getItemId())) || (long)chr.getMeso() - check3 > 2147483647L || (long)shop4.getMeso() + check3 < 0L) {
                    return;
                }
                if ((long)chr.getMeso() - check3 < 0L) {
                    c.getPlayer().dropMessage(1, "您的金币不足.");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                if ((long)shop4.getMeso() + check3 > 2147483647L) {
                    c.getPlayer().dropMessage(1, "您购买的商店營業額已經超標，請通知店主來收錢。");
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                if (quantity2 >= 50 && tobuy.item.getItemId() == 2340000) {
                    c.setMonitored(true);
                }
                shop4.buy(c, item2, quantity2);
                shop4.broadcastToVisitors(PlayerShopPacket.shopItemUpdate(shop4));
                break;
            }
            case 35:
            case 36: {
                final int slot2 = slea.readShort();
                final IMaplePlayerShop shop5 = chr.getPlayerShop();
                if (shop5 == null || !shop5.getCanShop() || !shop5.isOwner(chr) || shop5 instanceof MapleMiniGame || shop5.getItems().size() <= 0 || shop5.getItems().size() <= slot2 || slot2 < 0) {
                    return;
                }
                final MaplePlayerShopItem item3 = (MaplePlayerShopItem)shop5.getItems().get(slot2);
                if (item3 != null && item3.bundles > 0) {
                    final IItem item_get = item3.item.copy();
                    final int count = item3.item.getQuantity();
                    final long check5 = (long)(item3.bundles * count);
                    if (check5 <= 0L || check5 > 32767L) {
                        return;
                    }
                    item_get.setQuantity((short)(int)check5);
                    if (item_get.getQuantity() >= 50 && GameConstants.isUpgradeScroll(item3.item.getItemId())) {
                        c.setMonitored(true);
                    }
                    if (MapleInventoryManipulator.checkSpace(c, item_get.getItemId(), (int)item_get.getQuantity(), item_get.getOwner())) {
                        MapleInventoryManipulator.addFromDrop(c, item_get, false);
                        item3.bundles = 0;
                        shop5.removeFromSlot(slot2);
                    }
                    FileoutputUtil.logToFile("logs/Data/精灵商人道具拿出.txt", FileoutputUtil.NowTime() + "账号角色名字:" + c.getAccountName() + " " + c.getPlayer().getName() + " 道具： " + item_get.getItemId() + " 數量:  " + (int)item_get.getQuantity() + "\r\n");
                }
                c.sendPacket(PlayerShopPacket.shopItemUpdate(shop5));
                break;
            }
            case 37: {
                final IMaplePlayerShop shop2 = chr.getPlayerShop();
                if (shop2 != null && shop2 instanceof HiredMerchant && shop2.isOwner(chr)) {
                    shop2.setOpen(true);
                    chr.setPlayerShop(null);
                    break;
                }
                break;
            }
            case 38: {
                final IMaplePlayerShop imps = chr.getPlayerShop();
                if (imps == null || !imps.getCanShop() || !imps.isOwner(chr) || imps instanceof MapleMiniGame) {
                    c.sendPacket(MaplePacketCreator.enableActions());
                    break;
                }
                for (int i = 0; i < imps.getItems().size(); ++i) {
                    if (((MaplePlayerShopItem)imps.getItems().get(i)).bundles == 0) {
                        imps.getItems().remove(i);
                    }
                }
                if (chr.getMeso() + imps.getMeso() < 0) {
                    c.sendPacket(PlayerShopPacket.shopItemUpdate(imps));
                    break;
                }
                FileoutputUtil.logToFile("logs/Data/精灵商人整理道具.txt", FileoutputUtil.NowTime() + "账号角色名字:" + c.getAccountName() + " " + c.getPlayer().getName() + " 获得金幣： " + imps.getMeso() + "\r\n");
                chr.gainMeso(imps.getMeso(), false);
                imps.setMeso(0);
                c.sendPacket(PlayerShopPacket.shopItemUpdate(imps));
                break;
            }
            case 39: {
                try {
                    final IMaplePlayerShop merchant4 = chr.getPlayerShop();
                    if (merchant4 != null && merchant4.getShopType() == 1 && merchant4.isOwner(chr) && merchant4.isAvailable()) {
                        c.sendPacket(PlayerShopPacket.shopErrorMessage(21, 0));
                        c.sendPacket(MaplePacketCreator.serverNotice(1, "请在自由市场找弗兰德里领取你的装备和金币"));
                        c.sendPacket(MaplePacketCreator.enableActions());
                        merchant4.removeAllVisitors(-1, -1);
                        chr.setPlayerShop(null);
                        merchant4.closeShop(true, true);
                    }
                }
                catch (Exception e) {
                    FileoutputUtil.outError("logs/精灵商人关闭异常.txt", (Throwable)e);
                }
            }
            case 44: {
                final IMaplePlayerShop merchant4 = chr.getPlayerShop();
                if (merchant4 != null && merchant4.getShopType() == 1 && merchant4.isOwner(chr)) {
                    ((HiredMerchant)merchant4).sendVisitor(c);
                    break;
                }
                break;
            }
            case 45: {
                final IMaplePlayerShop merchant4 = chr.getPlayerShop();
                if (merchant4 != null && merchant4.getShopType() == 1 && merchant4.isOwner(chr)) {
                    ((HiredMerchant)merchant4).sendBlackList(c);
                    break;
                }
                break;
            }
            case 46: {
                final IMaplePlayerShop merchant4 = chr.getPlayerShop();
                if (merchant4 != null && merchant4.getShopType() == 1 && merchant4.isOwner(chr)) {
                    ((HiredMerchant)merchant4).addBlackList(slea.readMapleAsciiString());
                    break;
                }
                break;
            }
            case 47: {
                final IMaplePlayerShop merchant4 = chr.getPlayerShop();
                if (merchant4 != null && merchant4.getShopType() == 1 && merchant4.isOwner(chr)) {
                    ((HiredMerchant)merchant4).removeBlackList(slea.readMapleAsciiString());
                    break;
                }
                break;
            }
            case 50: {
                c.sendPacket(MaplePacketCreator.enableActions());
                break;
            }
            case 59: {
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips == null || !(ips instanceof MapleMiniGame)) {
                    break;
                }
                if (!((MapleMiniGame)ips).isOpen()) {
                    break;
                }
                ips.removeAllVisitors(5, 1);
                break;
            }
            case 57:
            case 58: {
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips != null && ips instanceof MapleMiniGame) {
                    final MapleMiniGame game2 = (MapleMiniGame)ips;
                    if (!game2.isOwner(chr) && game2.isOpen()) {
                        game2.setReady((int)game2.getVisitorSlot(chr));
                        game2.broadcastToVisitors(PlayerShopPacket.getMiniGameReady(game2.isReady((int)game2.getVisitorSlot(chr))));
                    }
                    break;
                }
                break;
            }
            case 60: {
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips != null && ips instanceof MapleMiniGame) {
                    final MapleMiniGame game2 = (MapleMiniGame)ips;
                    if (game2.isOwner(chr) && game2.isOpen()) {
                        for (int j = 1; j < ips.getSize(); ++j) {
                            if (!game2.isReady(j)) {
                                return;
                            }
                        }
                        game2.setGameType();
                        game2.shuffleList();
                        if (game2.getGameType() == 1) {
                            game2.broadcastToVisitors(PlayerShopPacket.getMiniGameStart(game2.getLoser()));
                        }
                        else {
                            game2.broadcastToVisitors(PlayerShopPacket.getMatchCardStart(game2, game2.getLoser()));
                        }
                        game2.setOpen(false);
                        game2.update();
                    }
                    break;
                }
                break;
            }
            case 48: {
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips == null || !(ips instanceof MapleMiniGame)) {
                    break;
                }
                final MapleMiniGame game2 = (MapleMiniGame)ips;
                if (game2.isOpen()) {
                    break;
                }
                if (game2.isOwner(chr)) {
                    game2.broadcastToVisitors(PlayerShopPacket.getMiniGameRequestTie(), false);
                }
                else {
                    game2.getMCOwner().getClient().sendPacket(PlayerShopPacket.getMiniGameRequestTie());
                }
                game2.setRequestedTie((int)game2.getVisitorSlot(chr));
                break;
            }
            case 49: {
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips == null || !(ips instanceof MapleMiniGame)) {
                    break;
                }
                final MapleMiniGame game2 = (MapleMiniGame)ips;
                if (game2.isOpen()) {
                    break;
                }
                if (game2.getRequestedTie() > -1 && game2.getRequestedTie() != game2.getVisitorSlot(chr)) {
                    if (slea.readByte() > 0) {
                        game2.broadcastToVisitors(PlayerShopPacket.getMiniGameResult(game2, 1, game2.getRequestedTie()));
                        game2.nextLoser();
                        game2.setOpen(true);
                        game2.update();
                        game2.checkExitAfterGame();
                    }
                    else {
                        game2.broadcastToVisitors(PlayerShopPacket.getMiniGameDenyTie());
                    }
                    game2.setRequestedTie(-1);
                }
                break;
            }
            case 53: {
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips == null || !(ips instanceof MapleMiniGame)) {
                    break;
                }
                final MapleMiniGame game2 = (MapleMiniGame)ips;
                if (game2.isOpen()) {
                    break;
                }
                if (game2.isOwner(chr)) {
                    game2.broadcastToVisitors(PlayerShopPacket.getMiniGameRequestREDO(), false);
                }
                else {
                    game2.getMCOwner().getClient().sendPacket(PlayerShopPacket.getMiniGameRequestREDO());
                }
                game2.setRequestedTie((int)game2.getVisitorSlot(chr));
                break;
            }
            case 54: {
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips == null || !(ips instanceof MapleMiniGame)) {
                    break;
                }
                final MapleMiniGame game2 = (MapleMiniGame)ips;
                if (game2.isOpen()) {
                    break;
                }
                if (slea.readByte() > 0) {
                    ips.broadcastToVisitors(PlayerShopPacket.getMiniGameSkip1((int)ips.getVisitorSlot(chr)));
                    game2.nextLoser();
                }
                else {
                    game2.broadcastToVisitors(PlayerShopPacket.getMiniGameDenyTie());
                }
                game2.setRequestedTie(-1);
                break;
            }
            case 61: {
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips == null || !(ips instanceof MapleMiniGame)) {
                    break;
                }
                final MapleMiniGame game2 = (MapleMiniGame)ips;
                if (game2.isOpen()) {
                    break;
                }
                ips.broadcastToVisitors(PlayerShopPacket.getMiniGameSkip((int)ips.getVisitorSlot(chr)));
                game2.nextLoser();
                break;
            }
            case 62: {
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips == null || !(ips instanceof MapleMiniGame)) {
                    break;
                }
                final MapleMiniGame game2 = (MapleMiniGame)ips;
                if (game2.isOpen()) {
                    break;
                }
                game2.setPiece(slea.readInt(), slea.readInt(), (int)slea.readByte(), chr);
                break;
            }
            case 67: {
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips == null || !(ips instanceof MapleMiniGame)) {
                    break;
                }
                final MapleMiniGame game2 = (MapleMiniGame)ips;
                if (game2.isOpen()) {
                    break;
                }
                if (slea.readByte() != game2.getTurn()) {
                    game2.broadcastToVisitors(PlayerShopPacket.shopChat("不能放在通過 " + chr.getName() + ". 失敗者: " + game2.getLoser() + " 遊客: " + (int)game2.getVisitorSlot(chr) + " 是否為真: " + game2.getTurn(), (int)game2.getVisitorSlot(chr)));
                    return;
                }
                final int slot3 = slea.readByte();
                final int turn = game2.getTurn();
                final int fs = game2.getFirstSlot();
                if (turn == 1) {
                    game2.setFirstSlot(slot3);
                    if (game2.isOwner(chr)) {
                        game2.broadcastToVisitors(PlayerShopPacket.getMatchCardSelect(turn, slot3, fs, turn), false);
                    }
                    else {
                        game2.getMCOwner().getClient().sendPacket(PlayerShopPacket.getMatchCardSelect(turn, slot3, fs, turn));
                    }
                    game2.setTurn(0);
                    return;
                }
                if (fs > 0 && game2.getCardId(fs + 1) == game2.getCardId(slot3 + 1)) {
                    game2.broadcastToVisitors(PlayerShopPacket.getMatchCardSelect(turn, slot3, fs, game2.isOwner(chr) ? 2 : 3));
                    game2.setPoints((int)game2.getVisitorSlot(chr));
                }
                else {
                    game2.broadcastToVisitors(PlayerShopPacket.getMatchCardSelect(turn, slot3, fs, (int)(game2.isOwner(chr) ? 0 : 1)));
                    game2.nextLoser();
                }
                game2.setTurn(1);
                game2.setFirstSlot(0);
                break;
            }
            case 55: {
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips == null || !(ips instanceof MapleMiniGame)) {
                    break;
                }
                final MapleMiniGame game2 = (MapleMiniGame)ips;
                if (game2.isOpen()) {
                    break;
                }
                game2.broadcastToVisitors(PlayerShopPacket.getMiniGameResult(game2, 0, (int)game2.getVisitorSlot(chr)));
                game2.nextLoser();
                game2.setOpen(true);
                game2.update();
                game2.checkExitAfterGame();
                break;
            }
            case 56: {
                final IMaplePlayerShop ips = chr.getPlayerShop();
                if (ips == null || !(ips instanceof MapleMiniGame)) {
                    break;
                }
                final MapleMiniGame game2 = (MapleMiniGame)ips;
                if (game2.isOpen()) {
                    break;
                }
                game2.setExitAfter(chr);
                game2.broadcastToVisitors(PlayerShopPacket.getMiniGameExitAfter(game2.isExitAfter(chr)));
                break;
            }
        }
    }
}
