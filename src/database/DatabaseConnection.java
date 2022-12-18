package database;

import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.DriverManager;
import java.io.IOException;
import java.io.Reader;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantLock;
import java.util.HashMap;

public class DatabaseConnection {
    private static final HashMap<Integer, ConWrapper> connections;
    private static final ReentrantLock lock;
    private static String dbDriver;
    private static String dbUrl;
    private static String dbUser;
    private static String dbPass;
    private static String dbHost;
    private static String dbPort;
    private static String dbName;
    private static boolean propsInited;
    private static Properties dbProps;
    private static long connectionTimeOut;
    public static final int CLOSE_CURRENT_RESULT = 1;
    public static final int KEEP_CURRENT_RESULT = 2;
    public static final int CLOSE_ALL_RESULTS = 3;
    public static final int SUCCESS_NO_INFO = -2;
    public static final int EXECUTE_FAILED = -3;
    public static final int RETURN_GENERATED_KEYS = 1;
    public static final int NO_GENERATED_KEYS = 2;
    public static final Runnable CloseSQLConnections;

    private DatabaseConnection() {
    }

    public static Connection getConnection() {
        final Thread cThread = Thread.currentThread();
        final int threadID = (int) cThread.getId();
        ConWrapper ret = DatabaseConnection.connections.get(threadID);
        if (ret == null) {
            final Connection retCon = connectToDB();
            ret = new ConWrapper(retCon);
            ret.id = threadID;
            DatabaseConnection.connections.put(threadID, ret);
        }
        return ret.getConnection();
    }

    private static long getWaitTimeout(final Connection con) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SHOW VARIABLES LIKE 'wait_timeout'");
            if (rs.next()) {
                return (long) Math.max(1000, rs.getInt(2) * 1000 - 1000);
            }
            return -1L;
        } catch (SQLException ex) {
            final long n = -1L;
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex2) {
                } finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException ex3) {
                        }
                    }
                }
            }
            return n;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex4) {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException ex5) {
                        }
                    }
                } finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException ex6) {
                        }
                    }
                }
            }
        }
    }

    private static Connection connectToDB() {
        if (!DatabaseConnection.propsInited) {
            try {
                final FileReader fR = new FileReader("配置.ini");
                DatabaseConnection.dbProps.load((Reader) fR);
                fR.close();
            } catch (IOException ex) {
                System.err.println("加载数据库配置出错，请检查" + (Object) ex);
            }
            DatabaseConnection.dbDriver = "com.mysql.jdbc.Driver";
            DatabaseConnection.dbName = DatabaseConnection.dbProps.getProperty("CongMS.db.name");
            DatabaseConnection.dbHost = DatabaseConnection.dbProps.getProperty("CongMS.db.host");
            DatabaseConnection.dbPort = DatabaseConnection.dbProps.getProperty("CongMS.db.port");
            DatabaseConnection.dbUrl = "jdbc:mysql://" + DatabaseConnection.dbHost + ":" + DatabaseConnection.dbPort + "/" + DatabaseConnection.dbName + "?useUnicode=true&characterEncoding=UTF8";
            DatabaseConnection.dbUser = DatabaseConnection.dbProps.getProperty("CongMS.db.user");
            DatabaseConnection.dbPass = DatabaseConnection.dbProps.getProperty("CongMS.db.password");
            System.out.println("[正在加载] -> 数据库逾时时间 " + DatabaseConnection.connectionTimeOut);
        }
        try {
            Class.forName(DatabaseConnection.dbDriver);
        } catch (ClassNotFoundException ex2) {
        }
        try {
            final Connection con = DriverManager.getConnection(DatabaseConnection.dbUrl, DatabaseConnection.dbUser, DatabaseConnection.dbPass);
            if (!DatabaseConnection.propsInited) {
                final long timeout = getWaitTimeout(con);
                if (timeout != -1L) {
                    DatabaseConnection.connectionTimeOut = timeout;
                }
                DatabaseConnection.propsInited = true;
            }
            return con;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public static void closeAll() throws SQLException {
        for (final ConWrapper con : DatabaseConnection.connections.values()) {
            con.connection.close();
        }
        DatabaseConnection.connections.clear();
    }

    public static void closeTimeout() {
        int i = 0;
        DatabaseConnection.lock.lock();
        final List<Integer> keys = new ArrayList<Integer>((Collection<? extends Integer>) DatabaseConnection.connections.keySet());
        try {
            for (final Integer tid : keys) {
                final ConWrapper con = (ConWrapper) DatabaseConnection.connections.get((Object) tid);
                if (con.close()) {
                    ++i;
                }
            }
        } finally {
            DatabaseConnection.lock.unlock();
        }
    }

    static {
        connections = new HashMap<Integer, ConWrapper>();
        lock = new ReentrantLock();
        DatabaseConnection.propsInited = false;
        DatabaseConnection.dbProps = new Properties();
        DatabaseConnection.connectionTimeOut = 300000L;
        CloseSQLConnections = new Runnable() {
            @Override
            public void run() {
                DatabaseConnection.closeTimeout();
            }
        };
    }

    public static class ConWrapper {
        private long lastAccessTime;
        private Connection connection;
        private int id;

        public ConWrapper(final Connection con) {
            this.lastAccessTime = 0L;
            this.connection = con;
        }

        public Connection getConnection() {
            if (this.expiredConnection()) {
                try {
                    this.connection.close();
                } catch (Throwable t) {
                }
                this.connection = connectToDB();
            }
            this.lastAccessTime = System.currentTimeMillis();
            return this.connection;
        }

        public boolean expiredConnection() {
            if (this.lastAccessTime == 0L) {
                return false;
            }
            try {
                return System.currentTimeMillis() - this.lastAccessTime >= connectionTimeOut || this.connection.isClosed();
            } catch (Throwable ex) {
                return true;
            }
        }

        private boolean close() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
