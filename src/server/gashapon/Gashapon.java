package server.gashapon;

import tools.FileoutputUtil;
import handling.world.World.Broadcast;
import tools.MaplePacketCreator;
import client.MapleCharacter;
import server.MapleItemInformationProvider;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import tools.FilePrinter;
import database.DBConPool;
import java.util.Iterator;
import server.Randomizer;
import java.util.LinkedList;
import tools.Pair;
import java.util.List;

public final class Gashapon
{
    private final int id;
    private final int npcId;
    private final String name;
    private final List<Pair<Long, GashaponReward>> items;
    
    public Gashapon(final int id, final int npcId, final String name) {
        this.items = new LinkedList<Pair<Long, GashaponReward>>();
        this.id = id;
        this.npcId = npcId;
        this.name = name;
        this.reloadItems();
    }
    
    public int getId() {
        return this.id;
    }
    
    public int getNpcId() {
        return this.npcId;
    }
    
    public String getName() {
        return this.name;
    }
    
    public GashaponReward generateReward() {
        if (this.items.isEmpty()) {
            this.reloadItems();
        }
        final Iterator<Pair<Long, GashaponReward>> iterator = this.items.iterator();
        final long total = (long)Long.valueOf(((Pair<Long, GashaponReward>)this.items.get(this.items.size() - 1)).left);
        final Long n = Long.valueOf(Math.abs(Randomizer.nextLong() * System.currentTimeMillis() + 47L * System.currentTimeMillis()) % total);
        while (iterator.hasNext()) {
            final Pair<Long, GashaponReward> c = (Pair<Long, GashaponReward>)iterator.next();
            if ((long)n <= (long)Long.valueOf(c.left)) {
                return (GashaponReward)c.right;
            }
        }
        return null;
    }
    
    public void reloadItems() {
        long chanceTotal = 0L;
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("SELECT * FROM gashapon_items WHERE gashaponsid = ? ORDER BY itemid ASC")) {
            ps.setInt(1, this.getId());
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final GashaponReward reward = new GashaponReward(rs.getInt("itemid"), rs.getInt("chance"), rs.getBoolean("showmsg"), rs.getInt("quantity"));
                chanceTotal += (long)reward.getChance();
                this.items.add(new Pair<Long, GashaponReward>(Long.valueOf(chanceTotal), reward));
            }
        }
        catch (SQLException ex) {
            FilePrinter.printError("Gashapon.txt", (Throwable)ex, "reloadItems");
        }
    }
    
    public String ShowItem(final String type) {
        final StringBuilder sb = new StringBuilder();
        final Iterator<Pair<Long, GashaponReward>> iterator = this.items.iterator();
        sb.append("#b此转蛋机物品有:\r\n");
        while (iterator.hasNext()) {
            final Pair<Long, GashaponReward> c = (Pair<Long, GashaponReward>)iterator.next();
            if (MapleItemInformationProvider.getInstance().itemExists(((GashaponReward)c.right).getItemId()) && ((GashaponReward)c.right).getChance() > 0) {
                int n = -1;
                switch (type.hashCode()) {
                    case 49: {
                        if (type.equals((Object)"1")) {
                            n = 0;
                            break;
                        }
                        break;
                    }
                    case 50: {
                        if (type.equals((Object)"2")) {
                            n = 1;
                            break;
                        }
                        break;
                    }
                    case 2278: {
                        if (type.equals((Object)"GM")) {
                            n = 2;
                            break;
                        }
                        break;
                    }
                }
                switch (n) {
                    case 0: {
                        sb.append("#v" + ((GashaponReward)c.right).getItemId() + "#");
                        continue;
                    }
                    case 1: {
                        sb.append("#v" + ((GashaponReward)c.right).getItemId() + "#  道具名稱: #z" + ((GashaponReward)c.right).getItemId() + "#\r\n");
                        continue;
                    }
                    case 2: {
                        sb.append("#L" + ((GashaponReward)c.right).getItemId() + "##v" + ((GashaponReward)c.right).getItemId() + "##z" + ((GashaponReward)c.right).getItemId() + "# 機率:" + ((GashaponReward)c.right).getChance() + "# 數量:" + ((GashaponReward)c.right).getQuantity() + "(點選更改)\r\n");
                        continue;
                    }
                    default: {
                        sb.append("指定顯示型態錯誤!");
                        continue;
                    }
                }
            }
        }
        if (type == "GM") {
            sb.append("\r\n#b#L10000#我要新增转蛋机物品!!#l\r\n");
        }
        return sb.toString();
    }
    
    public String ShowItem_GM() {
        final StringBuilder sb = new StringBuilder();
        final Iterator<Pair<Long, GashaponReward>> iterator = this.items.iterator();
        sb.append("#b此转蛋机物品有:\r\n");
        while (iterator.hasNext()) {
            final Pair<Long, GashaponReward> c = (Pair<Long, GashaponReward>)iterator.next();
            if (MapleItemInformationProvider.getInstance().itemExists(((GashaponReward)c.right).getItemId())) {
                sb.append("#L" + this.items.size() + "##v" + ((GashaponReward)c.right).getItemId() + "##z" + ((GashaponReward)c.right).getItemId() + "# 機率:" + ((GashaponReward)c.right).getChance() + "# 數量:" + ((GashaponReward)c.right).getQuantity() + "(點選更改)\r\n");
            }
        }
        return sb.toString();
    }
    
    public void ChangeChance(final MapleCharacter chr, final int itemid, final int chance) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("UPDATE gashapon_items SET chance = ? WHERE gashaponsid = ? AND itemid = ?")) {
            ps.setInt(1, chance);
            ps.setInt(2, this.getId());
            ps.setInt(3, itemid);
            ps.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("Error GashaponChance" + (Object)ex);
            FilePrinter.printError("GashaponChance.txt", (Throwable)ex, "GashaponChance has SQL Exception");
        }
        Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM 密语系統] " + chr.getName() + "  更改物品:" + MapleItemInformationProvider.getInstance().getName(itemid) + " 機率更改為" + chance));
        FileoutputUtil.logToFile("logs/GM_LOG/GM更改轉蛋物機率.txt", "\r\n" + FileoutputUtil.NowTime() + "GM: " + chr.getName() + "  更改物品:" + MapleItemInformationProvider.getInstance().getName(itemid) + " 機率更改為" + chance);
    }
    
    public void ChangeQuantity(final MapleCharacter chr, final int itemid, final int quantity) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("UPDATE gashapon_items SET quantity = ? WHERE gashaponsid = ? AND itemid = ?")) {
            ps.setInt(1, quantity);
            ps.setInt(2, this.getId());
            ps.setInt(3, itemid);
            ps.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("Error GashaponChance" + (Object)ex);
            FilePrinter.printError("GashaponChance.txt", (Throwable)ex, "GashaponChance has SQL Exception");
        }
        Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM 密语系統] " + chr.getName() + "  更改物品:" + MapleItemInformationProvider.getInstance().getName(itemid) + " 數量更改為" + quantity));
        FileoutputUtil.logToFile("logs/GM_LOG/GM更改轉蛋物機率.txt", "\r\n" + FileoutputUtil.NowTime() + "GM: " + chr.getName() + "  更改物品:" + MapleItemInformationProvider.getInstance().getName(itemid) + " 數量更改為" + quantity);
    }
    
    public void AddItem(final MapleCharacter chr, final int itemid, final int chance, final boolean msg, final int quantity) {
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
             final PreparedStatement ps = con.prepareStatement("INSERT INTO gashapon_items SET chance = ? , gashaponsid = ? , itemid = ? , name = ?, showmsg = ?, quantity = ?")) {
            ps.setInt(1, chance);
            ps.setInt(2, this.getId());
            ps.setInt(3, itemid);
            ps.setString(4, MapleItemInformationProvider.getInstance().getName(itemid));
            ps.setInt(5, (int)(msg ? 1 : 0));
            ps.setInt(6, quantity);
            ps.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("Error GashaponAddItem" + (Object)ex);
            FilePrinter.printError("GashaponAddItem.txt", (Throwable)ex, "GashaponAddItem has SQL Exception");
        }
        Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[GM 密语系統] " + chr.getName() + "  新增轉蛋物品:" + MapleItemInformationProvider.getInstance().getName(itemid) + " 機率設定為" + chance + " 是否上綠廣:" + (msg ? "是" : "否") + " 數量設定為" + quantity));
        FileoutputUtil.logToFile("logs/GM_LOG/GM新增轉蛋物機率.txt", "\r\n" + FileoutputUtil.NowTime() + "GM: " + chr.getName() + "  新增轉蛋物品:" + MapleItemInformationProvider.getInstance().getName(itemid) + " 機率設定為" + chance + " 是否上綠廣:" + (msg ? "是" : ("否 數量設定為" + quantity)));
    }
}
