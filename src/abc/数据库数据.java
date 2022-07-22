package abc;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.sql.Connection;
import handling.channel.ChannelServer;
import database.DatabaseConnection;

public class 数据库数据
{
    public static void getOfflineTimeList() {
        try {
            final Connection conn = DatabaseConnection.getConnection();
            for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                final PreparedStatement ps = conn.prepareStatement("SELECT * FROM lefttime WHERE channel=?");
                ps.setInt(1, cserv.channel);
                try (final ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        ChannelServer.clones.add(new 离线人偶(rs.getInt("accid"), rs.getInt("charid"), rs.getInt("x"), rs.getInt("y"), rs.getInt("chairid"), rs.getInt("channel"), rs.getLong("lefttime")));
                    }
                    rs.close();
                }
                ps.close();
            }
            conn.close();
        }
        catch (Exception Ex) {
            System.out.println("离线挂机数据读取异常" + (Object)Ex);
        }
    }
}
