package handling.channel.handler;

import java.util.Iterator;
import java.util.Map;
import java.util.LinkedList;
import client.MapleCharacter;
import java.sql.ResultSet;
import java.util.Collections;
import tools.Pair;
import client.inventory.ItemLoader;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import tools.FilePrinter;
import tools.FileoutputUtil;
import database.DBConPool;
import client.inventory.IItem;
import server.MapleInventoryManipulator;
import server.MapleItemInformationProvider;
import client.inventory.ItemFlag;
import server.MapleDueyActions;
import java.util.List;
import client.inventory.MapleInventoryType;
import client.MapleCharacterUtil;
import constants.GameConstants;
import tools.MaplePacketCreator;
import client.MapleClient;
import tools.data.LittleEndianAccessor;

public class DueyHandler
{
    public static final void DueyOperation(final LittleEndianAccessor slea, final MapleClient c) {
        final byte operation = slea.readByte();
        switch (operation) {
            case 1: {
                final String AS13Digit = slea.readMapleAsciiString();
                final int conv = c.getPlayer().getConversation();
                if (conv == 2) {
                    c.sendPacket(MaplePacketCreator.sendDuey((byte)10, loadItems(c.getPlayer())));
                    break;
                }
                break;
            }
            case 3: {
                if (c.getPlayer().getConversation() != 2) {
                    return;
                }
                final byte inventId = slea.readByte();
                final short itemPos = slea.readShort();
                final short amount = slea.readShort();
                final int mesos = slea.readInt();
                final String recipient = slea.readMapleAsciiString();
                final boolean quickdelivery = slea.readByte() > 0;
                final int finalcost = mesos + GameConstants.getTaxAmount(mesos) + (quickdelivery ? 0 : 5000);
                if (mesos >= 0 && mesos <= 100000000 && c.getPlayer().getMeso() >= finalcost) {
                    final int accid = MapleCharacterUtil.getIdByName(recipient);
                    if (accid != -1) {
                        if (accid != c.getAccID()) {
                            final boolean recipientOn = false;
                            final MapleClient rClient = null;
                            if (inventId > 0) {
                                final MapleInventoryType inv = MapleInventoryType.getByType(inventId);
                                final IItem item = c.getPlayer().getInventory(inv).getItem((short)(byte)itemPos);
                                if (item == null) {
                                    c.sendPacket(MaplePacketCreator.sendDuey((byte)17, null));
                                    return;
                                }
                                final byte flag = item.getFlag();
                                if (ItemFlag.UNTRADEABLE.check((int)flag) || ItemFlag.LOCK.check((int)flag)) {
                                    c.sendPacket(MaplePacketCreator.enableActions());
                                    return;
                                }
                                if (c.getPlayer().getItemQuantity(item.getItemId(), false) >= amount) {
                                    final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
                                    if (!ii.isDropRestricted(item.getItemId()) && !ii.isAccountShared(item.getItemId())) {
                                        if (addItemToDB(item, (int)amount, mesos, c.getPlayer().getName(), accid, recipientOn)) {
                                            if (GameConstants.isThrowingStar(item.getItemId()) || GameConstants.isBullet(item.getItemId())) {
                                                MapleInventoryManipulator.removeFromSlot(c, inv, (short)(byte)itemPos, item.getQuantity(), true);
                                            }
                                            else {
                                                MapleInventoryManipulator.removeFromSlot(c, inv, (short)(byte)itemPos, amount, true, false);
                                            }
                                            c.getPlayer().gainMeso(-finalcost, false);
                                            c.sendPacket(MaplePacketCreator.sendDuey((byte)19, null));
                                        }
                                        else {
                                            c.sendPacket(MaplePacketCreator.sendDuey((byte)17, null));
                                        }
                                    }
                                    else {
                                        c.sendPacket(MaplePacketCreator.sendDuey((byte)17, null));
                                    }
                                }
                                else {
                                    c.sendPacket(MaplePacketCreator.sendDuey((byte)17, null));
                                }
                            }
                            else if (addMesoToDB(mesos, c.getPlayer().getName(), accid, recipientOn)) {
                                c.getPlayer().gainMeso(-finalcost, false);
                                c.sendPacket(MaplePacketCreator.sendDuey((byte)19, null));
                            }
                            else {
                                c.sendPacket(MaplePacketCreator.sendDuey((byte)17, null));
                            }
                        }
                        else {
                            c.sendPacket(MaplePacketCreator.sendDuey((byte)15, null));
                        }
                    }
                    else {
                        c.sendPacket(MaplePacketCreator.sendDuey((byte)14, null));
                    }
                    break;
                }
                c.sendPacket(MaplePacketCreator.sendDuey((byte)12, null));
                break;
            }
            case 5: {
                if (c.getPlayer().getConversation() != 2) {
                    return;
                }
                final int packageid = slea.readInt();
                final MapleDueyActions dp = loadSingleItem(packageid, c.getPlayer().getId());
                if (dp == null) {
                    return;
                }
                if (dp.getItem() != null && !MapleInventoryManipulator.checkSpace(c, dp.getItem().getItemId(), (int)dp.getItem().getQuantity(), dp.getItem().getOwner())) {
                    c.sendPacket(MaplePacketCreator.sendDuey((byte)16, null));
                    return;
                }
                if (dp.getMesos() < 0 || dp.getMesos() + c.getPlayer().getMeso() < 0) {
                    c.sendPacket(MaplePacketCreator.sendDuey((byte)17, null));
                    return;
                }
                removeItemFromDB(packageid, c.getPlayer().getId());
                if (dp.getItem() != null) {
                    MapleInventoryManipulator.addFromDrop(c, dp.getItem(), false);
                }
                if (dp.getMesos() != 0) {
                    c.getPlayer().gainMeso(dp.getMesos(), false);
                }
                c.sendPacket(MaplePacketCreator.removeItemFromDuey(false, packageid));
                break;
            }
            case 6: {
                if (c.getPlayer().getConversation() != 2) {
                    return;
                }
                final int packageid = slea.readInt();
                removeItemFromDB(packageid, c.getPlayer().getId());
                c.sendPacket(MaplePacketCreator.removeItemFromDuey(true, packageid));
                break;
            }
            case 8: {
                c.getPlayer().setConversation(0);
                break;
            }
            default: {
                System.out.println("Unhandled Duey operation : " + slea.toString());
                break;
            }
        }
    }
    
    private static boolean addMesoToDB(final int mesos, final String sName, final int recipientID, final boolean isOn) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            try (final PreparedStatement ps = con.prepareStatement("INSERT INTO dueypackages (RecieverId, SenderName, Mesos, TimeStamp, Checked, Type) VALUES (?, ?, ?, ?, ?, ?)")) {
                ps.setInt(1, recipientID);
                ps.setString(2, sName);
                ps.setInt(3, mesos);
                ps.setLong(4, System.currentTimeMillis());
                ps.setInt(5, (int)(isOn ? 0 : 1));
                ps.setInt(6, 3);
                ps.executeUpdate();
            }
            return true;
        }
        catch (SQLException se) {
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)se);
            FilePrinter.printError("DueyHandler.txt", (Throwable)se, "addMesoToDB");
            return false;
        }
    }
    
    private static boolean addItemToDB(final IItem item, final int quantity, final int mesos, final String sName, final int recipientID, final boolean isOn) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            try (final PreparedStatement ps = con.prepareStatement("INSERT INTO dueypackages (RecieverId, SenderName, Mesos, TimeStamp, Checked, Type) VALUES (?, ?, ?, ?, ?, ?)", 1)) {
                ps.setInt(1, recipientID);
                ps.setString(2, sName);
                ps.setInt(3, mesos);
                ps.setLong(4, System.currentTimeMillis());
                ps.setInt(5, (int)(isOn ? 0 : 1));
                ps.setInt(6, (int)item.getType());
                ps.executeUpdate();
                try (final ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        ItemLoader.DUEY.saveItems(Collections.singletonList(new Pair<IItem, MapleInventoryType>(item, GameConstants.getInventoryType(item.getItemId()))), Integer.valueOf(rs.getInt(1)));
                    }
                }
            }
            return true;
        }
        catch (SQLException se) {
            FilePrinter.printError("DueyHandler.txt", (Throwable)se, "addItemToDB");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)se);
            return false;
        }
    }
    
    public static final List<MapleDueyActions> loadItems(final MapleCharacter chr) {
        final List<MapleDueyActions> packages = new LinkedList<MapleDueyActions>();
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            try (final PreparedStatement ps = con.prepareStatement("SELECT * FROM dueypackages WHERE RecieverId = ?")) {
                ps.setInt(1, chr.getId());
                try (final ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        final MapleDueyActions dueypack = getItemByPID(rs.getInt("packageid"));
                        dueypack.setSender(rs.getString("SenderName"));
                        dueypack.setMesos(rs.getInt("Mesos"));
                        dueypack.setSentTime(rs.getLong("TimeStamp"));
                        packages.add(dueypack);
                    }
                }
            }
            return packages;
        }
        catch (SQLException se) {
            FilePrinter.printError("DueyHandler.txt", (Throwable)se, "loadItems");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)se);
            return null;
        }
    }
    
    public static final MapleDueyActions loadSingleItem(final int packageid, final int charid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM dueypackages WHERE PackageId = ? and RecieverId = ?");
            ps.setInt(1, packageid);
            ps.setInt(2, charid);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                final MapleDueyActions dueypack = getItemByPID(packageid);
                dueypack.setSender(rs.getString("SenderName"));
                dueypack.setMesos(rs.getInt("Mesos"));
                dueypack.setSentTime(rs.getLong("TimeStamp"));
                rs.close();
                ps.close();
                return dueypack;
            }
            rs.close();
            ps.close();
            return null;
        }
        catch (SQLException se) {
            FilePrinter.printError("DueyHandler.txt", (Throwable)se, "loadSingleItem");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)se);
            return null;
        }
    }
    
    public static final void reciveMsg(final MapleClient c, final int recipientId) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("UPDATE dueypackages SET Checked = 0 where RecieverId = ?")) {
            ps.setInt(1, recipientId);
            ps.executeUpdate();
        }
        catch (SQLException se) {
            FilePrinter.printError("DueyHandler.txt", (Throwable)se, "reciveMsg");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)se);
        }
    }
    
    private static void removeItemFromDB(final int packageid, final int charid) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("DELETE FROM dueypackages WHERE PackageId = ? and RecieverId = ?")) {
            ps.setInt(1, packageid);
            ps.setInt(2, charid);
            ps.executeUpdate();
        }
        catch (SQLException se) {
            FilePrinter.printError("DueyHandler.txt", (Throwable)se, "removeItemFromDB");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)se);
        }
    }
    
    private static MapleDueyActions getItemByPID(final int packageid) {
        try {
            final Map<Long, Pair<IItem, MapleInventoryType>> iter = ItemLoader.DUEY.loadItems(false, Integer.valueOf(packageid));
            if (iter != null && iter.size() > 0) {
                final Iterator<Pair<IItem, MapleInventoryType>> iterator = iter.values().iterator();
                if (iterator.hasNext()) {
                    final Pair<IItem, MapleInventoryType> i = (Pair<IItem, MapleInventoryType>)iterator.next();
                    return new MapleDueyActions(packageid, (IItem)i.getLeft());
                }
            }
        }
        catch (Exception se) {
            FilePrinter.printError("DueyHandler.txt", (Throwable)se, "getItemByPID");
        }
        return new MapleDueyActions(packageid);
    }
}
