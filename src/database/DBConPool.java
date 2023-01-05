package database;

import java.io.IOException;
import java.io.Reader;
import java.io.FileReader;
import server.ServerProperties;
import java.util.Properties;
import com.alibaba.druid.pool.DruidDataSource;

public class DBConPool
{
    private static DruidDataSource dataSource;
    public static final int RETURN_GENERATED_KEYS = 1;
    public static String dbUser;
    public static String dbPass;
    public static String dbIp;
    public static String dbName;
    public static int dbport;
    private static Properties dbProps;
    private static String dbHost;
    private static String dbPort;
    
    public static void InitDB() {
        DBConPool.dbName = ServerProperties.getProperty("CongMS.db.name", DBConPool.dbName);
        DBConPool.dbIp = ServerProperties.getProperty("CongMS.db.ip", DBConPool.dbIp);
        DBConPool.dbport = ServerProperties.getProperty("CongMS.db.port", DBConPool.dbport);
        DBConPool.dbUser = ServerProperties.getProperty("CongMS.db.user", DBConPool.dbUser);
        DBConPool.dbPass = ServerProperties.getProperty("CongMS.db.password", DBConPool.dbPass);
    }
    
    public static DBConPool getInstance() {
        return InstanceHolder.instance;
    }
    
    private DBConPool() {
    }
    
    public DruidDataSource getDataSource() {
        if (DBConPool.dataSource == null) {
            this.InitDBConPool();
        }
        return DBConPool.dataSource;
    }
    
    private void InitDBConPool() {
        try {
            final FileReader fR = new FileReader("配置.ini");
            DBConPool.dbProps.load((Reader)fR);
            fR.close();
        }
        catch (IOException ex) {
            System.err.println("加载数据库配置出错，请检查" + (Object)ex);
        }
        DBConPool.dbName = DBConPool.dbProps.getProperty("CongMS.db.name");
        DBConPool.dbHost = DBConPool.dbProps.getProperty("CongMS.db.ip");
        DBConPool.dbPort = DBConPool.dbProps.getProperty("CongMS.db.port");
        DBConPool.dbUser = DBConPool.dbProps.getProperty("CongMS.db.user");
        DBConPool.dbPass = DBConPool.dbProps.getProperty("CongMS.db.password");
        (DBConPool.dataSource = new DruidDataSource()).setName("mysql_pool");
        DBConPool.dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        DBConPool.dataSource.setUrl("jdbc:mysql://" + DBConPool.dbHost + ":" + DBConPool.dbport + "/" + DBConPool.dbName + "?useUnicode=true&characterEncoding=UTF8");
        DBConPool.dataSource.setUsername(DBConPool.dbUser);
        DBConPool.dataSource.setPassword(DBConPool.dbPass);
        DBConPool.dataSource.setInitialSize(300);
        DBConPool.dataSource.setMinIdle(500);
        DBConPool.dataSource.setMaxActive(3000);
        DBConPool.dataSource.setTimeBetweenEvictionRunsMillis(60000L);
        DBConPool.dataSource.setMinEvictableIdleTimeMillis(300000L);
        DBConPool.dataSource.setValidationQuery("SELECT 'x'");
        DBConPool.dataSource.setTestOnBorrow(false);
        DBConPool.dataSource.setTestOnReturn(false);
        DBConPool.dataSource.setTestWhileIdle(true);
        DBConPool.dataSource.setMaxWait(60000L);
        DBConPool.dataSource.setUseUnfairLock(true);
    }
    
    static {
        DBConPool.dataSource = null;
        DBConPool.dbUser = "";
        DBConPool.dbPass = "root";
        DBConPool.dbIp = "localhost";
        DBConPool.dbName = "v079";
        DBConPool.dbport = 3306;
        DBConPool.dbProps = new Properties();
        InitDB();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("[数据库信息] 找不到JDBC驱动.");
            System.exit(0);
        }
    }
    
    private static class InstanceHolder
    {
        public static final DBConPool instance;
        
        static {
            instance = new DBConPool();
        }
    }
}
