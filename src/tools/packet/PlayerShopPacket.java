package tools.packet;

import java.util.List;
import server.shops.MapleMiniGame;
import client.MapleClient;
import client.inventory.IItem;
import server.MerchItemPackage;
import server.shops.IMaplePlayerShop;
import server.shops.MaplePlayerShop;
import java.util.Iterator;
import server.shops.MaplePlayerShopItem;
import server.shops.AbstractPlayerStore.BoughtItem;
import tools.Pair;
import server.shops.HiredMerchant;
import handling.SendPacketOpcode;
import tools.data.MaplePacketLittleEndianWriter;
import client.MapleCharacter;

public class PlayerShopPacket
{
    public static final byte[] addCharBox(final MapleCharacter c, final int type) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.UPDATE_CHAR_BOX.getValue());
        mplew.writeInt(c.getId());
        PacketHelper.addAnnounceBox(mplew, c);
        return mplew.getPacket();
    }
    
    public static final byte[] removeCharBox(final MapleCharacter c) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.UPDATE_CHAR_BOX.getValue());
        mplew.writeInt(c.getId());
        mplew.write(0);
        return mplew.getPacket();
    }
    
    public static final byte[] sendTitleBox() {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.ENTRUSTED_SHOP_CHECK_RESULT.getValue());
        mplew.write(7);
        return mplew.getPacket();
    }
    
    public static final byte[] sendPlayerShopBox(final MapleCharacter c) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.UPDATE_CHAR_BOX.getValue());
        mplew.writeInt(c.getId());
        PacketHelper.addAnnounceBox(mplew, c);
        return mplew.getPacket();
    }
    
    public static final byte[] getHiredMerch(final MapleCharacter chr, final HiredMerchant merch, final boolean firstTime) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(5);
        mplew.write(5);
        mplew.write(4);
        mplew.write(merch.getVisitorSlot(chr));
        mplew.write(0);
        mplew.writeInt(merch.getItemId());
        mplew.writeMapleAsciiString("精灵商人");
        for (final Pair<Byte, MapleCharacter> storechr : merch.getVisitors()) {
            mplew.write((byte)Byte.valueOf(storechr.left));
            PacketHelper.addCharLook(mplew, (MapleCharacter)storechr.right, false);
            mplew.writeMapleAsciiString(((MapleCharacter)storechr.right).getName());
        }
        mplew.write(-1);
        if (merch.isOwner(chr)) {
            mplew.writeShort(merch.getMessages().size());
            for (int i = 0; i < merch.getMessages().size(); ++i) {
                mplew.writeMapleAsciiString((String)((Pair<String, Byte>)merch.getMessages().get(i)).getLeft());
                mplew.write((byte)Byte.valueOf(((Pair<String, Byte>)merch.getMessages().get(i)).getRight()));
            }
        }
        else {
            mplew.writeShort(0);
        }
        mplew.writeMapleAsciiString(merch.getOwnerName());
        if (merch.isOwner(chr)) {
            mplew.writeInt(merch.getTimeLeft());
            mplew.write((int)(firstTime ? 1 : 0));
            mplew.write(merch.getBoughtItems().size());
            for (final BoughtItem SoldItem : merch.getBoughtItems()) {
                mplew.writeInt(SoldItem.id);
                mplew.writeShort(SoldItem.quantity);
                mplew.writeInt(SoldItem.totalPrice);
                mplew.writeMapleAsciiString(SoldItem.buyer);
            }
            mplew.writeInt(merch.getMeso());
        }
        mplew.writeMapleAsciiString(merch.getDescription());
        mplew.write(10);
        mplew.writeInt(merch.getMeso());
        mplew.write(merch.getItems().size());
        for (final MaplePlayerShopItem item : merch.getItems()) {
            mplew.writeShort((int)item.bundles);
            mplew.writeShort((int)item.item.getQuantity());
            mplew.writeInt(item.price);
            PacketHelper.addItemInfo(mplew, item.item, true, true);
        }
        return mplew.getPacket();
    }
    
    public static final byte[] getPlayerStore(final MapleCharacter chr, final boolean firstTime) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        final IMaplePlayerShop ips = chr.getPlayerShop();
        switch (ips.getShopType()) {
            case 2: {
                mplew.write(5);
                mplew.write(4);
                mplew.write(4);
                break;
            }
            case 3: {
                mplew.write(5);
                mplew.write(2);
                mplew.write(2);
                break;
            }
            case 4: {
                mplew.write(5);
                mplew.write(1);
                mplew.write(2);
                break;
            }
        }
        mplew.writeShort((int)ips.getVisitorSlot(chr));
        PacketHelper.addCharLook(mplew, ((MaplePlayerShop)ips).getMCOwner(), false);
        mplew.writeMapleAsciiString(ips.getOwnerName());
        for (final Pair<Byte, MapleCharacter> storechr : ips.getVisitors()) {
            mplew.write((byte)Byte.valueOf(storechr.left));
            PacketHelper.addCharLook(mplew, (MapleCharacter)storechr.right, false);
            mplew.writeMapleAsciiString(((MapleCharacter)storechr.right).getName());
        }
        mplew.write(255);
        mplew.writeMapleAsciiString(ips.getDescription());
        mplew.write(10);
        mplew.write(ips.getItems().size());
        for (final MaplePlayerShopItem item : ips.getItems()) {
            mplew.writeShort((int)item.bundles);
            mplew.writeShort((int)item.item.getQuantity());
            mplew.writeInt(item.price);
            PacketHelper.addItemInfo(mplew, item.item, true, true);
        }
        return mplew.getPacket();
    }
    
    public static final byte[] shopChat(final String message, final int slot) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(6);
        mplew.write(9);
        mplew.write(slot);
        mplew.writeMapleAsciiString(message);
        return mplew.getPacket();
    }
    
    public static final byte[] shopErrorMessage(final int error, final int type) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(10);
        mplew.write(type);
        mplew.write(error);
        return mplew.getPacket();
    }
    
    public static final byte[] spawnHiredMerchant(final HiredMerchant hm) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SPAWN_HIRED_MERCHANT.getValue());
        mplew.writeInt(hm.getOwnerId());
        mplew.writeInt(hm.getItemId());
        mplew.writePos(hm.getPosition());
        mplew.writeShort(0);
        mplew.writeMapleAsciiString(hm.getOwnerName());
        PacketHelper.addInteraction(mplew, (IMaplePlayerShop)hm);
        return mplew.getPacket();
    }
    
    public static final byte[] destroyHiredMerchant(final int id) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.DESTROY_HIRED_MERCHANT.getValue());
        mplew.writeInt(id);
        return mplew.getPacket();
    }
    
    public static final byte[] shopItemUpdate(final IMaplePlayerShop shop) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(23);
        if (shop.getShopType() == 1) {
            mplew.writeInt(0);
        }
        mplew.write(shop.getItems().size());
        for (final MaplePlayerShopItem item : shop.getItems()) {
            mplew.writeShort((int)item.bundles);
            mplew.writeShort((int)item.item.getQuantity());
            mplew.writeInt(item.price);
            PacketHelper.addItemInfo(mplew, item.item, true, true);
        }
        return mplew.getPacket();
    }
    
    public static final byte[] shopVisitorAdd(final MapleCharacter chr, final int slot) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(4);
        mplew.write(slot);
        PacketHelper.addCharLook(mplew, chr, false);
        mplew.writeMapleAsciiString(chr.getName());
        return mplew.getPacket();
    }
    
    public static final byte[] shopVisitorLeave(final byte slot) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write((byte)10);
        mplew.write(slot);
        return mplew.getPacket();
    }
    
    public static final byte[] Merchant_Buy_Error(final byte message) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(24);
        mplew.write(message);
        return mplew.getPacket();
    }
    
    public static final byte[] updateHiredMerchant(final HiredMerchant shop) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.UPDATE_HIRED_MERCHANT.getValue());
        mplew.writeInt(shop.getOwnerId());
        PacketHelper.addInteraction(mplew, (IMaplePlayerShop)shop);
        return mplew.getPacket();
    }
    
    public static final byte[] merchItem_Message(final byte op) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MERCH_ITEM_MSG.getValue());
        mplew.write(op);
        return mplew.getPacket();
    }
    
    public static final byte[] ShowMerchItemStore(final int npc, final int mapid, final int ch) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MERCH_ITEM_STORE.getValue());
        mplew.write(37);
        mplew.writeInt(npc);
        mplew.writeInt(mapid);
        mplew.write(ch - 1);
        return mplew.getPacket();
    }
    
    public static final byte[] merchItemStore(final byte op) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MERCH_ITEM_STORE.getValue());
        mplew.write(op);
        switch (op) {
            case 36: {
                mplew.writeZeroBytes(8);
                break;
            }
            case 37: {
                mplew.writeInt(9030000);
                mplew.writeInt(999999999);
                mplew.write(0);
                break;
            }
            default: {
                mplew.write(0);
                break;
            }
        }
        return mplew.getPacket();
    }
    
    public static final byte[] merchItemStore_ItemData(final MerchItemPackage pack) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MERCH_ITEM_STORE.getValue());
        mplew.write(35);
        mplew.writeInt(9030000);
        mplew.writeInt(32272);
        mplew.writeZeroBytes(5);
        mplew.writeInt(pack.getMesos());
        mplew.write(0);
        mplew.write(pack.getItems().size());
        for (final IItem item : pack.getItems()) {
            PacketHelper.addItemInfo(mplew, item, true, true);
        }
        mplew.writeZeroBytes(3);
        return mplew.getPacket();
    }
    
    public static byte[] getMiniGame(final MapleClient c, final MapleMiniGame minigame) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(5);
        mplew.write(minigame.getGameType());
        mplew.write(minigame.getMaxSize());
        mplew.writeShort((int)minigame.getVisitorSlot(c.getPlayer()));
        PacketHelper.addCharLook(mplew, minigame.getMCOwner(), false);
        mplew.writeMapleAsciiString(minigame.getOwnerName());
        for (final Pair<Byte, MapleCharacter> visitorz : minigame.getVisitors()) {
            mplew.write((byte)Byte.valueOf(visitorz.getLeft()));
            PacketHelper.addCharLook(mplew, (MapleCharacter)visitorz.getRight(), false);
            mplew.writeMapleAsciiString(((MapleCharacter)visitorz.getRight()).getName());
        }
        mplew.write(-1);
        mplew.write(0);
        addGameInfo(mplew, minigame.getMCOwner(), minigame);
        for (final Pair<Byte, MapleCharacter> visitorz : minigame.getVisitors()) {
            mplew.write((byte)Byte.valueOf(visitorz.getLeft()));
            addGameInfo(mplew, (MapleCharacter)visitorz.getRight(), minigame);
        }
        mplew.write(-1);
        mplew.writeMapleAsciiString(minigame.getDescription());
        mplew.writeShort(minigame.getPieceType());
        return mplew.getPacket();
    }
    
    public static byte[] getMiniGameReady(final boolean ready) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(ready ? 57 : 58);
        return mplew.getPacket();
    }
    
    public static byte[] getMiniGameExitAfter(final boolean ready) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(ready ? 55 : 56);
        return mplew.getPacket();
    }
    
    public static byte[] getMiniGameStart(final int loser) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(60);
        mplew.write((int)((loser != 1) ? 1 : 0));
        return mplew.getPacket();
    }
    
    public static byte[] getMiniGameSkip(final int slot) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(62);
        mplew.write(slot);
        return mplew.getPacket();
    }
    
    public static byte[] getMiniGameSkip1(final int slot) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(62);
        mplew.write((int)((slot != 1) ? 1 : 0));
        return mplew.getPacket();
    }
    
    public static byte[] getMiniGameRequestTie() {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(48);
        return mplew.getPacket();
    }
    
    public static byte[] getMiniGameRequestREDO() {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(53);
        return mplew.getPacket();
    }
    
    public static byte[] getMiniGameDenyTie() {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(49);
        return mplew.getPacket();
    }
    
    public static byte[] getMiniGameDenyREDO() {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(48);
        return mplew.getPacket();
    }
    
    public static byte[] getMiniGameFull() {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.writeShort(5);
        mplew.write(2);
        return mplew.getPacket();
    }
    
    public static byte[] getMiniGameMoveOmok(final int move1, final int move2, final int move3) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(63);
        mplew.writeInt(move1);
        mplew.writeInt(move2);
        mplew.write(move3);
        return mplew.getPacket();
    }
    
    public static byte[] getMiniGameNewVisitor(final MapleCharacter c, final int slot, final MapleMiniGame game) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(4);
        mplew.write(slot);
        PacketHelper.addCharLook(mplew, c, false);
        mplew.writeMapleAsciiString(c.getName());
        addGameInfo(mplew, c, game);
        return mplew.getPacket();
    }
    
    public static void addGameInfo(final MaplePacketLittleEndianWriter mplew, final MapleCharacter chr, final MapleMiniGame game) {
        mplew.writeInt(game.getGameType());
        mplew.writeInt(game.getWins(chr));
        mplew.writeInt(game.getTies(chr));
        mplew.writeInt(game.getLosses(chr));
        mplew.writeInt(game.getScore(chr));
        mplew.writeInt(0);
    }
    
    public static byte[] getMiniGameClose(final byte number) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(10);
        mplew.write(1);
        mplew.write(number);
        return mplew.getPacket();
    }
    
    public static byte[] getMatchCardStart(final MapleMiniGame game, final int loser) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(60);
        mplew.write((int)((loser != 1) ? 1 : 0));
        final int times = (game.getPieceType() == 1) ? 20 : ((game.getPieceType() == 2) ? 30 : 12);
        mplew.write(times);
        for (int i = 1; i <= times; ++i) {
            mplew.writeInt(game.getCardId(i));
        }
        return mplew.getPacket();
    }
    
    public static byte[] getMatchCardSelect(final int turn, final int slot, final int firstslot, final int type) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(67);
        mplew.write(turn);
        mplew.write(slot);
        if (turn == 0) {
            mplew.write(firstslot);
            mplew.write(type);
        }
        return mplew.getPacket();
    }
    
    public static byte[] getMiniGameResult(final MapleMiniGame game, final int type, final int x) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(61);
        mplew.write(type);
        game.setPoints(x, type);
        if (type != 0) {
            game.setPoints((int)((x != 1) ? 1 : 0), (int)((type != 2) ? 1 : 0));
        }
        if (type != 1) {
            if (type == 0) {
                mplew.write((int)((x != 1) ? 1 : 0));
            }
            else {
                mplew.write(x);
            }
        }
        addGameInfo(mplew, game.getMCOwner(), game);
        for (final Pair<Byte, MapleCharacter> visitorz : game.getVisitors()) {
            addGameInfo(mplew, (MapleCharacter)visitorz.right, game);
        }
        return mplew.getPacket();
    }
    
    public static final byte[] MerchantVisitorView(final List<String> visitor) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(44);
        mplew.writeShort(visitor.size());
        for (final String visit : visitor) {
            mplew.writeMapleAsciiString(visit);
            mplew.writeInt(1);
        }
        return mplew.getPacket();
    }
    
    public static final byte[] MerchantBlackListView(final List<String> blackList) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(45);
        mplew.writeShort(blackList.size());
        for (int i = 0; i < blackList.size(); ++i) {
            if (blackList.get(i) != null) {
                mplew.writeMapleAsciiString((String)blackList.get(i));
            }
        }
        return mplew.getPacket();
    }
    
    public static final byte[] sendHiredMerchantMessage(final byte type) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MERCH_ITEM_MSG.getValue());
        mplew.write(type);
        return mplew.getPacket();
    }
    
    public static final byte[] shopMessage(final int type) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PLAYER_INTERACTION.getValue());
        mplew.write(type);
        mplew.write(0);
        return mplew.getPacket();
    }
}
