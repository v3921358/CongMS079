package tools.packet;

import tools.KoreanDateUtil;
import server.MTSStorage.MTSItemInfo;
import tools.Pair;
import server.CashShop;
import server.CashItemInfo;
import java.util.Map.Entry;
import java.util.Map;
import client.inventory.IItem;
import java.sql.SQLException;
import java.sql.ResultSet;
import client.MapleCharacter;
import java.util.Iterator;
import java.util.List;
import java.util.Collection;
import constants.ServerConstants;
import server.CashItemInfo.CashModInfo;
import java.util.ArrayList;
import server.CashItemFactory;
import tools.HexTool;
import handling.SendPacketOpcode;
import client.MapleClient;
import tools.data.MaplePacketLittleEndianWriter;

public class MTSCSPacket
{
    private static final byte Operation_Code = 61;
    
    public static byte[] showPredictCard(final String name, final String otherName, final int love, final int cardId, final int commentId) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(103);
        mplew.writeMapleAsciiString(name);
        mplew.writeMapleAsciiString(otherName);
        mplew.writeInt(love);
        mplew.writeInt(cardId);
        mplew.writeInt(commentId);
        return mplew.getPacket();
    }
    
    public static byte[] warpCS(final MapleClient c) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SET_CASH_SHOP.getValue());
        PacketHelper.addCharacterInfo(mplew, c.getPlayer(), true);
        mplew.writeMapleAsciiString(c.getAccountName());
        final int v13 = 70;
        mplew.writeInt(v13);
        mplew.write(HexTool.getByteArrayFromHexString("07 A5 9B 00 08 A5 9B 00 09 A5 9B 00 0A A5 9B 00 0B A5 9B 00 0C A5 9B 00 0D A5 9B 00 0E A5 9B 00 0F A5 9B 00 10 A5 9B 00 11 A5 9B 00 12 A5 9B 00 13 A5 9B 00 14 A5 9B 00 15 A5 9B 00 16 A5 9B 00 17 A5 9B 00 18 A5 9B 00 19 A5 9B 00 1A A5 9B 00 1B A5 9B 00 1C A5 9B 00 1D A5 9B 00 1E A5 9B 00 1F A5 9B 00 20 A5 9B 00 21 A5 9B 00 22 A5 9B 00 23 A5 9B 00 24 A5 9B 00 25 A5 9B 00 26 A5 9B 00 27 A5 9B 00 28 A5 9B 00 29 A5 9B 00 2A A5 9B 00 2B A5 9B 00 2C A5 9B 00 2D A5 9B 00 2E A5 9B 00 2F A5 9B 00 30 A5 9B 00 31 A5 9B 00 32 A5 9B 00 33 A5 9B 00 34 A5 9B 00 35 A5 9B 00 36 A5 9B 00 37 A5 9B 00 38 A5 9B 00 39 A5 9B 00 3A A5 9B 00 3B A5 9B 00 3C A5 9B 00 3D A5 9B 00 3E A5 9B 00 3F A5 9B 00 40 A5 9B 00 41 A5 9B 00 42 A5 9B 00 43 A5 9B 00 44 A5 9B 00 45 A5 9B 00 46 A5 9B 00 47 A5 9B 00 48 A5 9B 00 49 A5 9B 00 4A A5 9B 00 4B A5 9B 00 4C A5 9B 00"));
        final Collection<CashModInfo> cmi = CashItemFactory.getInstance().getAllModInfo();
        final List<CashModInfo> cmii = new ArrayList<CashModInfo>();
        if (c.getPlayer().getCsMod() == 2) {
            for (final CashModInfo mmc : cmi) {
                if (mmc.mod != 1) {
                    continue;
                }
                cmii.add(mmc);
            }
            mplew.writeShort(cmii.size());
            for (final CashModInfo cm : cmii) {
                addModCashItemInfo(mplew, cm);
            }
        }
        else {
            for (final CashModInfo mmc : cmi) {
                if (mmc.mod != 0) {
                    continue;
                }
                cmii.add(mmc);
            }
            mplew.writeShort(cmii.size());
            for (final CashModInfo cm : cmii) {
                addModCashItemInfo(mplew, cm);
            }
        }
        mplew.writeShort(0);
        mplew.write(0);
        mplew.writeZeroBytes(120);
        for (int i = 1; i <= 8; ++i) {
            for (int j = 0; j < 2; ++j) {
                for (int hs = 0; hs < 5; ++hs) {
                    mplew.writeInt(i);
                    mplew.writeInt(j);
                    mplew.writeInt(ServerConstants.hot_sell[hs]);
                }
            }
        }
        mplew.writeShort(0);
        mplew.writeShort(0);
        mplew.write(0);
        return mplew.getPacket();
    }
    
    public static byte[] enableCSUse() {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_USE.getValue());
        mplew.write(1);
        mplew.writeInt(0);
        return mplew.getPacket();
    }
    
    public static byte[] showCashShopAcc(final MapleClient c) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(351);
        mplew.write(1);
        mplew.writeMapleAsciiString(c.getAccountName());
        return mplew.getPacket();
    }
    
    public static byte[] playCashSong(final int itemid, final String name) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CASH_SONG.getValue());
        mplew.writeInt(itemid);
        mplew.writeMapleAsciiString(name);
        return mplew.getPacket();
    }
    
    public static byte[] useCharm(final byte charmsleft, final byte daysleft) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SHOW_ITEM_GAIN_INCHAT.getValue());
        mplew.write(6);
        mplew.write(1);
        mplew.write(charmsleft);
        mplew.write(daysleft);
        return mplew.getPacket();
    }
    
    public static byte[] useWheel(final byte charmsleft) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SHOW_ITEM_GAIN_INCHAT.getValue());
        mplew.write(25);
        mplew.writeLong((long)charmsleft);
        return mplew.getPacket();
    }
    
    public static byte[] itemExpired(final int itemid) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SHOW_STATUS_INFO.getValue());
        mplew.write(2);
        mplew.writeInt(itemid);
        return mplew.getPacket();
    }
    
    public static byte[] ViciousHammer(final boolean start, final int hammered) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.VICIOUS_HAMMER.getValue());
        if (start) {
            mplew.write(49);
            mplew.writeInt(0);
            mplew.writeInt(hammered);
        }
        else {
            mplew.write(53);
            mplew.writeInt(0);
        }
        return mplew.getPacket();
    }
    
    public static byte[] changePetFlag(final int uniqueId, final boolean added, final int flagAdded) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PET_FLAG_CHANGE.getValue());
        mplew.writeLong((long)uniqueId);
        mplew.write((int)(added ? 1 : 0));
        mplew.writeShort(flagAdded);
        return mplew.getPacket();
    }
    
    public static byte[] changePetName(final MapleCharacter chr, final String newname, final int slot) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.PET_NAMECHANGE.getValue());
        mplew.writeInt(chr.getId());
        mplew.write(0);
        mplew.writeMapleAsciiString(newname);
        mplew.write(slot);
        return mplew.getPacket();
    }
    
    public static byte[] showNotes(final ResultSet notes, final int count) throws SQLException {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SHOW_NOTES.getValue());
        mplew.write(3);
        mplew.write(count);
        for (int i = 0; i < count; ++i) {
            mplew.writeInt(notes.getInt("id"));
            mplew.writeMapleAsciiString(notes.getString("from"));
            mplew.writeMapleAsciiString(notes.getString("message"));
            mplew.writeLong(PacketHelper.getKoreanTimestamp(notes.getLong("timestamp")));
            mplew.write(notes.getInt("gift"));
            notes.next();
        }
        return mplew.getPacket();
    }
    
    public static byte[] useChalkboard(final int charid, final String msg) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CHALKBOARD.getValue());
        mplew.writeInt(charid);
        if (msg == null || msg.length() <= 0) {
            mplew.write(0);
        }
        else {
            mplew.write(1);
            mplew.writeMapleAsciiString(msg);
        }
        return mplew.getPacket();
    }
    
    public static byte[] getTrockRefresh(final MapleCharacter chr, final byte vip, final boolean delete) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MAP_TRANSFER_RESULT.getValue());
        mplew.write(delete ? 2 : 3);
        mplew.write(vip);
        if (vip == 1) {
            final int[] map = chr.getRocks();
            for (int i = 0; i < 10; ++i) {
                mplew.writeInt(map[i]);
            }
        }
        else {
            final int[] map = chr.getRegRocks();
            for (int i = 0; i < 5; ++i) {
                mplew.writeInt(map[i]);
            }
        }
        return mplew.getPacket();
    }
    
    public static byte[] sendShowWishList(final MapleCharacter chr) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_OPERATION.getValue());
        mplew.write(70);
        final int[] list = chr.getWishlist();
        for (int i = 0; i < 10; ++i) {
            mplew.writeInt((list[i] != -1) ? list[i] : 0);
        }
        return mplew.getPacket();
    }
    
    public static byte[] sendShowWishListFail(final MapleClient c, final int flag) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_OPERATION.getValue());
        mplew.write(75);
        mplew.write(flag);
        return mplew.getPacket();
    }
    
    public static byte[] setWishList(final MapleCharacter chr) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_OPERATION.getValue());
        mplew.write(74);
        final int[] list = chr.getWishlist();
        for (int i = 0; i < 10; ++i) {
            mplew.writeInt((list[i] != -1) ? list[i] : 0);
        }
        return mplew.getPacket();
    }
    
    public static byte[] sendSetWishListFail(final MapleClient c, final int flag) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_OPERATION.getValue());
        mplew.write(77);
        mplew.write(flag);
        return mplew.getPacket();
    }
    
    public static byte[] showBoughtCashItem(final int itemid, final int sn, final int uniqueid, final int accid, final int quantity, final String giftFrom, final long expire) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_OPERATION.getValue());
        mplew.write(76);
        addCashItemInfo(mplew, uniqueid, accid, itemid, sn, quantity, giftFrom, expire);
        return mplew.getPacket();
    }
    
    public static byte[] showBoughtCashItem(final IItem item, final int sn, final int accid) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_OPERATION.getValue());
        mplew.write(76);
        addCashItemInfo(mplew, item, accid, sn);
        return mplew.getPacket();
    }
    
    public static byte[] sendShowBoughtCashItemFail(final int flag, final int value) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_OPERATION.getValue());
        mplew.write(79);
        mplew.writeShort(flag);
        if (flag == 194 || flag == 193) {
            mplew.writeInt(value);
        }
        return mplew.getPacket();
    }
    
    public static byte[] showBoughtCashPackage(final Map<Integer, IItem> ccc, final int accid) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_OPERATION.getValue());
        mplew.write(126);
        mplew.write(ccc.size());
        for (final Entry<Integer, IItem> sn : ccc.entrySet()) {
            addCashItemInfo(mplew, (IItem)sn.getValue(), accid, (int)Integer.valueOf(sn.getKey()));
        }
        mplew.writeShort(0);
        return mplew.getPacket();
    }
    
    public static byte[] sendShowBoughtCashPackageFail(final int flag, final int value) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_OPERATION.getValue());
        mplew.write(129);
        mplew.writeShort(flag);
        if (flag == 194 || flag == 193) {
            mplew.writeInt(value);
        }
        return mplew.getPacket();
    }
    
    public static byte[] sendGift(final String to, final CashItemInfo item, final int gainMaplePoint, final boolean isPackage) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_OPERATION.getValue());
        mplew.write(83);
        mplew.writeMapleAsciiString(to);
        mplew.writeInt(item.getId());
        mplew.writeShort(item.getCount());
        if (isPackage) {
            mplew.writeShort(gainMaplePoint);
        }
        return mplew.getPacket();
    }
    
    public static byte[] sendGiftFail(final int flag, final int page, final boolean isPackage) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_OPERATION.getValue());
        mplew.write(isPackage ? 131 : 86);
        mplew.writeShort(flag);
        if (flag == 194 || flag == 193) {
            mplew.writeInt(page);
        }
        return mplew.getPacket();
    }
    
    public static byte[] showNXMapleTokens(final MapleCharacter chr) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_UPDATE.getValue());
        mplew.writeInt(chr.getCSPoints(1));
        mplew.writeInt(chr.getCSPoints(2));
        return mplew.getPacket();
    }
    
    public static byte[] showXmasSurprise(final int idFirst, final IItem item, final int accid) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.XMAS_SURPRISE.getValue());
        mplew.write(230);
        mplew.writeLong((long)idFirst);
        mplew.writeInt(0);
        addCashItemInfo(mplew, item, accid, 0);
        mplew.writeInt(item.getItemId());
        mplew.write(1);
        mplew.write(1);
        return mplew.getPacket();
    }
    
    public static final void addCashItemInfo(final MaplePacketLittleEndianWriter mplew, final IItem item, final int accId, final int sn) {
        addCashItemInfo(mplew, item, accId, sn, true);
    }
    
    public static final void addCashItemInfo(final MaplePacketLittleEndianWriter mplew, final IItem item, final int accId, final int sn, final boolean isFirst) {
        addCashItemInfo(mplew, item.getUniqueId(), accId, item.getItemId(), sn, (int)item.getQuantity(), item.getGiftFrom(), item.getExpiration(), isFirst);
    }
    
    public static final void addCashItemInfo(final MaplePacketLittleEndianWriter mplew, final int uniqueid, final int accId, final int itemid, final int sn, final int quantity, final String sender, final long expire) {
        addCashItemInfo(mplew, uniqueid, accId, itemid, sn, quantity, sender, expire, true);
    }
    
    public static final void addCashItemInfo(final MaplePacketLittleEndianWriter mplew, final int uniqueid, final int accId, final int itemid, final int sn, final int quantity, final String sender, final long expire, final boolean isFirst) {
        mplew.writeLong((uniqueid > 0) ? ((long)uniqueid) : 0L);
        mplew.writeLong((long)accId);
        mplew.writeInt(itemid);
        mplew.writeInt(isFirst ? sn : 0);
        mplew.writeShort(quantity);
        mplew.writeAsciiString(sender, 13);
        PacketHelper.addExpirationTime(mplew, expire);
        mplew.writeLong(0L);
    }
    
    public static void addModCashItemInfo(final MaplePacketLittleEndianWriter mplew, final CashModInfo item) {
        final int flags = item.flags;
        mplew.writeInt(item.sn);
        mplew.writeInt(flags);
        if ((flags & 0x1) != 0x0) {
            mplew.writeInt(item.itemid);
        }
        if ((flags & 0x2) != 0x0) {
            mplew.writeShort(item.count);
        }
        if ((flags & 0x4) != 0x0) {
            mplew.writeInt(item.discountPrice);
        }
        if ((flags & 0x8) != 0x0) {
            mplew.write(item.unk_1 - 1);
        }
        if ((flags & 0x10) != 0x0) {
            mplew.write(item.priority);
        }
        if ((flags & 0x20) != 0x0) {
            mplew.writeShort(item.period);
        }
        if ((flags & 0x40) != 0x0) {
            mplew.writeInt(0);
        }
        if ((flags & 0x80) != 0x0) {
            mplew.writeInt(item.meso);
        }
        if ((flags & 0x100) != 0x0) {
            mplew.write(item.unk_2 - 1);
        }
        if ((flags & 0x200) != 0x0) {
            mplew.write(item.gender);
        }
        if ((flags & 0x400) != 0x0) {
            mplew.write((int)(item.showUp ? 1 : 0));
        }
        if ((flags & 0x800) != 0x0) {
            mplew.write(item.mark);
        }
        if ((flags & 0x1000) != 0x0) {
            mplew.write(item.unk_3 - 1);
        }
        if ((flags & 0x2000) != 0x0) {
            mplew.writeShort(0);
        }
        if ((flags & 0x4000) != 0x0) {
            mplew.writeShort(0);
        }
        if ((flags & 0x8000) != 0x0) {
            mplew.writeShort(0);
        }
        if ((flags & 0x10000) != 0x0) {
            final List<CashItemInfo> pack = CashItemFactory.getInstance().getPackageItems(item.sn);
            if (pack == null) {
                mplew.write(0);
            }
            else {
                mplew.write(pack.size());
                for (int i = 0; i < pack.size(); ++i) {
                    mplew.writeInt(((CashItemInfo)pack.get(i)).getSN());
                }
            }
        }
    }
    
    public static byte[] showBoughtCSQuestItem(final int price, final short quantity, final byte position, final int itemid) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_OPERATION.getValue());
        mplew.write(111);
        mplew.writeInt(price);
        mplew.writeShort((int)quantity);
        mplew.writeShort((int)position);
        mplew.writeInt(itemid);
        return mplew.getPacket();
    }
    
    public static byte[] sendCSFail(final int err) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_OPERATION.getValue());
        mplew.write(123);
        mplew.writeShort(err);
        if (err == 194 || err == 193) {
            mplew.writeInt(err);
        }
        return mplew.getPacket();
    }
    
    public static byte[] showCouponRedeemedItem(final int itemid) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_OPERATION.getValue());
        mplew.writeShort(80);
        mplew.writeInt(0);
        mplew.writeInt(1);
        mplew.writeShort(1);
        mplew.writeShort(26);
        mplew.writeInt(itemid);
        mplew.writeInt(0);
        return mplew.getPacket();
    }
    
    public static byte[] showCouponRedeemedItem(final Map<Integer, IItem> items, final int mesos, final int maplePoints, final MapleClient c) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_OPERATION.getValue());
        mplew.write(80);
        mplew.write(items.size());
        for (final Entry<Integer, IItem> item : items.entrySet()) {
            addCashItemInfo(mplew, (IItem)item.getValue(), c.getAccID(), (int)Integer.valueOf(item.getKey()));
        }
        mplew.writeLong((long)maplePoints);
        mplew.writeInt(mesos);
        return mplew.getPacket();
    }
    
    public static byte[] showCashInventory(final MapleClient c) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_OPERATION.getValue());
        mplew.write(66);
        final CashShop mci = c.getPlayer().getCashInventory();
        mplew.writeShort(mci.getItemsSize());
        for (final IItem itemz : mci.getInventory()) {
            addCashItemInfo(mplew, itemz, c.getAccID(), 0);
        }
        mplew.writeShort(c.getPlayer().getStorage().getSlots());
        mplew.writeShort(c.getCharacterSlots());
        return mplew.getPacket();
    }
    
    public static byte[] showGifts(final MapleClient c) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_OPERATION.getValue());
        mplew.write(68);
        final List<Pair<IItem, String>> mci = c.getPlayer().getCashInventory().loadGifts();
        mplew.writeShort(mci.size());
        for (final Pair<IItem, String> mcz : mci) {
            mplew.writeLong((long)((IItem)mcz.getLeft()).getUniqueId());
            mplew.writeInt(((IItem)mcz.getLeft()).getItemId());
            mplew.writeAsciiString(((IItem)mcz.getLeft()).getGiftFrom(), 13);
            mplew.writeAsciiString((String)mcz.getRight(), 73);
        }
        return mplew.getPacket();
    }
    
    public static byte[] cashItemExpired(final int uniqueid) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_OPERATION.getValue());
        mplew.write(103);
        mplew.writeLong((long)uniqueid);
        return mplew.getPacket();
    }
    
    public static byte[] increasedInvSlots(final int inv, final int slots) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_OPERATION.getValue());
        mplew.write(101);
        mplew.write(inv);
        mplew.writeShort(slots);
        return mplew.getPacket();
    }
    
    public static byte[] increasedStorageSlots(final int slots) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_OPERATION.getValue());
        mplew.write(91);
        mplew.writeShort(slots);
        return mplew.getPacket();
    }
    
    public static byte[] confirmToCSInventory(final IItem item, final int accId, final int sn) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_OPERATION.getValue());
        mplew.write(95);
        addCashItemInfo(mplew, item, accId, sn, true);
        return mplew.getPacket();
    }
    
    public static byte[] confirmFromCSInventory(final IItem item, final short pos) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_OPERATION.getValue());
        mplew.write(93);
        mplew.writeShort((int)pos);
        PacketHelper.addItemInfo(mplew, item, true, false);
        return mplew.getPacket();
    }
    
    public static byte[] sendMesobagFailed() {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MESOBAG_FAILURE.getValue());
        return mplew.getPacket();
    }
    
    public static byte[] sendMesobagSuccess(final int mesos) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MESOBAG_SUCCESS.getValue());
        mplew.writeInt(mesos);
        return mplew.getPacket();
    }
    
    public static final byte[] startMTS(final MapleCharacter chr, final MapleClient c) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.SET_ITC.getValue());
        PacketHelper.addCharacterInfo(mplew, chr, true);
        mplew.writeMapleAsciiString(c.getAccountName());
        mplew.writeInt(5000);
        mplew.writeInt(10);
        mplew.writeInt(100);
        mplew.writeInt(24);
        mplew.writeInt(168);
        mplew.writeLong(PacketHelper.getTime(System.currentTimeMillis()));
        return mplew.getPacket();
    }
    
    public static final byte[] sendMTS(final List<MTSItemInfo> items, final int tab, final int type, final int page, final int pages) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MTS_OPERATION.getValue());
        mplew.write(21);
        mplew.writeInt(pages * 10);
        mplew.writeInt(items.size());
        mplew.writeInt(tab);
        mplew.writeInt(type);
        mplew.writeInt(page);
        mplew.write(1);
        mplew.write(1);
        for (final MTSItemInfo item : items) {
            addMTSItemInfo(mplew, item);
        }
        mplew.write(1);
        return mplew.getPacket();
    }
    
    public static final byte[] showMTSCash(final MapleCharacter p) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.GET_MTS_TOKENS.getValue());
        mplew.writeInt(p.getCSPoints(2));
        return mplew.getPacket();
    }
    
    public static final byte[] getMTSWantedListingOver(final int nx, final int items) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MTS_OPERATION.getValue());
        mplew.write(61);
        mplew.writeInt(nx);
        mplew.writeInt(items);
        return mplew.getPacket();
    }
    
    public static final byte[] getMTSConfirmSell() {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MTS_OPERATION.getValue());
        mplew.write(29);
        return mplew.getPacket();
    }
    
    public static final byte[] getMTSFailSell() {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MTS_OPERATION.getValue());
        mplew.write(30);
        mplew.write(66);
        return mplew.getPacket();
    }
    
    public static final byte[] getMTSConfirmBuy() {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MTS_OPERATION.getValue());
        mplew.write(51);
        return mplew.getPacket();
    }
    
    public static final byte[] getMTSFailBuy() {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MTS_OPERATION.getValue());
        mplew.write(52);
        mplew.write(66);
        return mplew.getPacket();
    }
    
    public static final byte[] getMTSConfirmCancel() {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MTS_OPERATION.getValue());
        mplew.write(37);
        return mplew.getPacket();
    }
    
    public static final byte[] getMTSFailCancel() {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MTS_OPERATION.getValue());
        mplew.write(38);
        mplew.write(66);
        return mplew.getPacket();
    }
    
    public static final byte[] getMTSConfirmTransfer(final int quantity, final int pos) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MTS_OPERATION.getValue());
        mplew.write(39);
        mplew.writeInt(quantity);
        mplew.writeInt(pos);
        return mplew.getPacket();
    }
    
    private static final void addMTSItemInfo(final MaplePacketLittleEndianWriter mplew, final MTSItemInfo item) {
        PacketHelper.addItemInfo(mplew, item.getItem(), true, true);
        mplew.writeInt(item.getId());
        mplew.writeInt(item.getTaxes());
        mplew.writeInt(item.getPrice());
        mplew.writeInt(0);
        mplew.writeInt(KoreanDateUtil.getQuestTimestamp(item.getEndingDate()));
        mplew.writeInt(KoreanDateUtil.getQuestTimestamp(item.getEndingDate()));
        mplew.writeMapleAsciiString(item.getSeller());
        mplew.writeMapleAsciiString(item.getSeller());
        mplew.writeZeroBytes(28);
    }
    
    public static final byte[] getNotYetSoldInv(final List<MTSItemInfo> items) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MTS_OPERATION.getValue());
        mplew.write(35);
        mplew.writeInt(items.size());
        for (final MTSItemInfo item : items) {
            addMTSItemInfo(mplew, item);
        }
        return mplew.getPacket();
    }
    
    public static final byte[] getTransferInventory(final List<IItem> items, final boolean changed) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MTS_OPERATION.getValue());
        mplew.write(33);
        mplew.writeInt(items.size());
        int i = 0;
        for (final IItem item : items) {
            PacketHelper.addItemInfo(mplew, item, true, true);
            mplew.writeInt(Integer.MAX_VALUE - i);
            mplew.writeInt(110);
            mplew.writeInt(1011);
            mplew.writeZeroBytes(48);
            ++i;
        }
        mplew.writeInt(-47 + i - 1);
        mplew.write((int)(changed ? 1 : 0));
        return mplew.getPacket();
    }
    
    public static final byte[] addToCartMessage(final boolean fail, final boolean remove) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.MTS_OPERATION.getValue());
        if (remove) {
            if (fail) {
                mplew.write(44);
                mplew.writeInt(-1);
            }
            else {
                mplew.write(43);
            }
        }
        else if (fail) {
            mplew.write(42);
            mplew.writeInt(-1);
        }
        else {
            mplew.write(41);
        }
        return mplew.getPacket();
    }
    
    public static byte[] sendWEB(final MapleClient c) {
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort((int)SendPacketOpcode.CS_WEB.getValue());
        return mplew.getPacket();
    }
}
