package database;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;

public class ConnectionMysqlJDBC
{
    private String address;
    private String userName;
    private String password;
    private String dataBaseName;
    private String prot;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    
    public ConnectionMysqlJDBC(final String address, final String userName, final String password, final String dataBaseName, final String prot) {
        this.address = address;
        this.userName = userName;
        this.password = password;
        this.dataBaseName = dataBaseName;
        this.prot = prot;
    }
    
    public Connection getConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionMysqlJDBC.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
        }
        final String URl = "jdbc:mysql://" + this.address + ":" + this.prot + "/" + this.dataBaseName;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URl, this.userName, this.password);
        }
        catch (SQLException ex2) {
            return null;
        }
        return conn;
    }
    
    public void closeAllConnection(final Connection con, final PreparedStatement ps, final ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    }
}
