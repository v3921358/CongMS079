package server.custom.capture;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import database.DatabaseConnection;

public class capture_yongfa
{
    public static String 显示参赛人员() {
        final StringBuilder name = new StringBuilder();
        name.append("\t\t\t参赛人数([#b蓝队#k].").append(判断蓝色队伍()).append(" |[#r红队#k].").append(判断红色队伍()).append(")\r\n");
        name.append("——————————————————————————\r\n");
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM capture_cs order by id desc");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final String 玩家名字 = Rolename(rs.getInt("cid"));
                String 状态 = "";
                if (rs.getInt("zhuangtai") == 0) {
                    状态 = "[未准备]";
                }
                else {
                    状态 = "[已准备]";
                }
                if (rs.getInt("duiwu") == 0) {
                    name.append("\t\t\t[#b蓝队#k] ").append(状态).append(" 玩家: #d").append(玩家名字).append("\r\n");
                }
                if (rs.getInt("duiwu") > 0) {
                    name.append("\t\t\t[#r红队#k] ").append(状态).append(" 玩家: #d").append(玩家名字).append("\r\n");
                }
            }
        }
        catch (SQLException ex) {}
        name.append("——————————————————————————\r\n");
        return name.toString();
    }
    
    public static void 参加(final int id) {
        try (final Connection con = DatabaseConnection.getConnection();
             final PreparedStatement ps = con.prepareStatement("INSERT INTO capture_cs ( cid,duiwu) VALUES ( ?, ?)")) {
            ps.setInt(1, id);
            if (判断蓝色队伍() > 判断红色队伍()) {
                ps.setInt(2, 1);
            }
            else {
                ps.setInt(2, 0);
            }
            ps.executeUpdate();
        }
        catch (SQLException ex) {}
    }
    
    public static String Rolename(final int id) {
        String data = "";
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT name as DATA FROM characters WHERE id = ?");
            ps.setInt(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getString("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("capture_yongfa_Rolename_err");
        }
        return data;
    }
    
    public static int 判断蓝色队伍() {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM capture_cs");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("duiwu") == 0) {
                    ++data;
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("capture_yongfa_Rolename_判断蓝色队伍");
        }
        return data;
    }
    
    public static int 判断红色队伍() {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM capture_cs");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("duiwu") == 1) {
                    ++data;
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("capture_yongfa_Rolename_判断红色队伍");
        }
        return data;
    }
    
    public static int 判断是否已经参加(final int id) {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT cid as DATA FROM capture_cs WHERE cid = ?");
            ps.setInt(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("capture_yongfa_判断是否已经参加");
        }
        return data;
    }
    
    public static int 判断队伍(final int id) {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT duiwu as DATA FROM capture_cs WHERE cid = ?");
            ps.setInt(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("capture_yongfa_判断队伍");
        }
        return data;
    }
    
    public static int 判断队伍旗帜数量(final int id) {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT zhankuang as DATA FROM capture_zk WHERE duiwu = ?");
            ps.setInt(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("DATA");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("capture_yongfa_判断队伍旗帜数量");
        }
        return data;
    }
    
    public static void 增减旗帜(final int id) {
    }
}
