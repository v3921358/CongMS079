package tools;

import java.util.Iterator;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import server.MapleItemInformationProvider;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import database.DBConPool;
import java.util.ArrayList;
import java.util.List;

public class FixDropNullItem
{
    private List<Integer> loadFromDB(final int type) {
        final List<Integer> dropid = new ArrayList<Integer>();
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final StringBuilder sb = new StringBuilder();
            sb.append("SELECT itemid FROM drop_data ORDER BY itemid");
            if (type == 1) {
                sb.append(" DESC");
            }
            final PreparedStatement ps = con.prepareStatement(sb.toString());
            final ResultSet rs = ps.executeQuery();
            int itemId = 0;
            while (rs.next()) {
                try {
                    itemId = rs.getInt("itemid");
                }
                catch (Exception ex) {}
                if (itemId != 0) {
                    dropid.add(Integer.valueOf(itemId));
                }
            }
            rs.close();
            ps.close();
        }
        catch (SQLException e) {
            System.err.println("無法載入掉落物");
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
        }
        return dropid;
    }
    
    private void FixDropData(final int type, final int itemId) {
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT itemid, dropperid FROM drop_data WHERE itemid = ? ORDER BY itemid");
        if (type == 1) {
            sb.append(" DESC");
        }
        final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
            final PreparedStatement ps = con.prepareStatement(sb.toString());
            ps.setInt(1, itemId);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (!ii.itemExists(itemId)) {
                    System.out.println("道具: " + MapleItemInformationProvider.getInstance().getName(itemId) + " 道具ID: " + itemId + " 怪物ID: " + rs.getInt("dropperid") + " 不存在，已從資料庫移除");
                    try {
                        final PreparedStatement pp = con.prepareStatement("Delete From drop_data WHERE itemid = ?");
                        pp.setInt(1, itemId);
                        pp.executeUpdate();
                        pp.close();
                    }
                    catch (Exception ex) {
                        System.out.println((Object)ex);
                    }
                }
            }
            rs.close();
            ps.close();
        }
        catch (SQLException e) {
            System.out.println("處理掉落物失敗, 道具ID:" + itemId);
            FileoutputUtil.outError("logs/資料庫異常.txt", (Throwable)e);
        }
    }
    
    public static void main(final String[] args) {
        System.out.println("請輸入種類，0為降逆排列 1為升冪排列");
        int type = 0;
        try {
            final BufferedReader br = new BufferedReader((Reader)new InputStreamReader(System.in));
            type = Integer.parseInt(br.readLine());
        }
        catch (IOException ex) {
            Logger.getLogger(FixDropNullItem.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
        }
        if (type > 1 || type < 0) {
            type = 0;
        }
        final FixDropNullItem i = new FixDropNullItem();
        System.out.println("正在加載道具数据......");
        MapleItemInformationProvider.getInstance().load();
        System.out.println("正在讀取掉落物品......");
        final List<Integer> list = i.loadFromDB(type);
        System.out.println("正在處理不存在之掉落物......， 種類為 : " + type);
        final Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            final int ii = (int)Integer.valueOf(iterator.next());
            i.FixDropData(type, ii);
        }
        System.out.println("處理不存在之掉落物結束。");
    }
}
