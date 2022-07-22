package server.custom.treasure_house;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import database.DatabaseConnection;

public class treasure_x
{
    public static int 判断本地图有没有宝藏(final int id) {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM treasure_house WHERE map = ?");
            ps.setInt(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ++data;
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("判断本地图有没有宝藏_出错。");
        }
        return data;
    }
    
    public static int 取藏宝坐标x(final int id) {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM treasure_house WHERE map = ?");
            ps.setInt(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("x");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("取藏宝坐标x_出错。");
        }
        return data;
    }
    
    public static int 取藏宝坐标y(final int id) {
        int data = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM treasure_house WHERE map = ?");
            ps.setInt(1, id);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = rs.getInt("y");
                }
            }
            ps.close();
        }
        catch (SQLException Ex) {
            System.err.println("取藏宝坐标y_出错。");
        }
        return data;
    }
}
