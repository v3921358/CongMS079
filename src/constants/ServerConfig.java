package constants;

import abc.Game;
import handling.world.MapleParty;
import server.ServerProperties;
import java.io.File;

public class ServerConfig
{
    public static boolean pvp;
    public static int pvpch;
    public static boolean LOG_MRECHANT;
    public static boolean LOG_CSBUY;
    public static boolean LOG_DAMAGE;
    public static boolean LOG_CHAT;
    public static boolean LOG_MEGA;
    public static boolean LOG_PACKETS;
    public static boolean CHRLOG_PACKETS;
    public static boolean AUTO_REGISTER;
    public static boolean LOCALHOST;
    public static boolean Encoder;
    public static boolean TESPIA;
    public static boolean shieldWardAll;
    public static boolean DISCOUNTED;
    public static boolean 泡点系统;
    public static int 泡点地图;
    public static int 点卷数量;
    public static int 抵用卷数量;
    public static int 豆豆数量;
    public static int 等级经验倍率;
    public static String SERVERNAME;
    public static String version;
    public static String TOUDING;
    public static String IP;
    public static String wzpath;
    private static String EVENTS;
    public static boolean DEBUG_MODE;
    public static boolean NMGB;
    public static boolean PDCS;
    public static int RSGS;
    public static int maxlevel;
    public static int kocmaxlevel;
    public static int BeiShu1;
    public static int BeiShu2;
    public static int BeiShu3;
    public static String[] ipStr;
    public static final byte[] Gateway_IP;
    public static final byte[] Gateway_IP2;
    
    public static boolean isPvPChannel(final int ch) {
        return ServerConfig.pvp && ch == ServerConfig.pvpch;
    }
    
    public static String[] getEvents(final boolean reLoad) {
        return getEventList(reLoad).split(",");
    }
    
    public static String getEventList(final boolean reLoad) {
        if (ServerConfig.EVENTS == null || reLoad) {
            final File root = new File("脚本/事件");
            final File[] files = root.listFiles();
            ServerConfig.EVENTS = "";
            for (final File file : files) {
                if (!file.isDirectory()) {
                    final String[] fileName = file.getName().split("\\.");
                    if (fileName.length > 1 && "js".equals((Object)fileName[fileName.length - 1])) {
                        for (int i = 0; i < fileName.length - 1; ++i) {
                            ServerConfig.EVENTS += fileName[i];
                        }
                        ServerConfig.EVENTS += ",";
                    }
                }
            }
        }
        return ServerConfig.EVENTS;
    }
    
    public static boolean isAutoRegister() {
        return ServerConfig.AUTO_REGISTER;
    }
    
    public static String getVipMedalName(final int lv) {
        String medal = "";
        if (ServerConfig.SERVERNAME.equals((Object)"冒险岛")) {
            switch (lv) {
                case 1: {
                    medal = " <普通VIP>";
                    break;
                }
                case 2: {
                    medal = " <進階VIP>";
                    break;
                }
                case 3: {
                    medal = " <高級VIP>";
                    break;
                }
                case 4: {
                    medal = " <尊貴VIP>";
                    break;
                }
                case 5: {
                    medal = " <至尊VIP>";
                    break;
                }
                default: {
                    medal = " <VIP" + medal + ">";
                    break;
                }
            }
        }
        else if (ServerConfig.SERVERNAME.equals((Object)"冒险岛")) {
            switch (lv) {
                case 1: {
                    medal = "☆";
                    break;
                }
                case 2: {
                    medal = "☆★";
                    break;
                }
                case 3: {
                    medal = "☆★☆";
                    break;
                }
                case 4: {
                    medal = "☆★☆★";
                    break;
                }
                case 5: {
                    medal = "☆★☆★☆";
                    break;
                }
                case 6: {
                    medal = "☆★☆★☆★";
                    break;
                }
                case 7: {
                    medal = "☆★☆★☆★☆";
                    break;
                }
                case 8: {
                    medal = "☆★☆★☆★☆★";
                    break;
                }
                case 9: {
                    medal = "☆★☆★☆★☆★☆";
                    break;
                }
                case 10: {
                    medal = "☆★☆★☆★☆★☆★";
                    break;
                }
                case 11: {
                    medal = "楓之谷第一土豪";
                    break;
                }
                default: {
                    medal = "<VIP" + medal + ">";
                    break;
                }
            }
        }
        return medal;
    }
    
    public static void loadSetting() {
        ServerConfig.LOG_MRECHANT = ServerProperties.getProperty("CongMS.merchantLog", ServerConfig.LOG_MRECHANT);
        ServerConfig.LOG_MEGA = ServerProperties.getProperty("CongMS.megaLog", ServerConfig.LOG_MEGA);
        ServerConfig.LOG_CSBUY = ServerProperties.getProperty("CongMS.csLog", ServerConfig.LOG_CSBUY);
        ServerConfig.LOG_DAMAGE = ServerProperties.getProperty("CongMS.damLog", ServerConfig.LOG_DAMAGE);
        ServerConfig.LOG_CHAT = ServerProperties.getProperty("CongMS.chatLog", ServerConfig.LOG_CHAT);
        ServerConfig.LOG_PACKETS = ServerProperties.getProperty("CongMS.packetLog", ServerConfig.LOG_PACKETS);
        ServerConfig.AUTO_REGISTER = ServerProperties.getProperty("CongMS.autoRegister", ServerConfig.AUTO_REGISTER);
        ServerConfig.SERVERNAME = ServerProperties.getProperty("CongMS.serverName", ServerConfig.SERVERNAME);
        ServerConfig.DEBUG_MODE = ServerProperties.getProperty("CongMS.debug", ServerConfig.DEBUG_MODE);
        ServerConfig.BeiShu1 = ServerProperties.getProperty("CongMS.BeiShu1", ServerConfig.BeiShu1);
        ServerConfig.BeiShu2 = ServerProperties.getProperty("CongMS.BeiShu2", ServerConfig.BeiShu2);
        ServerConfig.BeiShu3 = ServerProperties.getProperty("CongMS.BeiShu3", ServerConfig.BeiShu3);
    }
    
    static {
        ServerConfig.pvp = false;
        ServerConfig.pvpch = 1;
        ServerConfig.LOG_MRECHANT = true;
        ServerConfig.LOG_CSBUY = true;
        ServerConfig.LOG_DAMAGE = false;
        ServerConfig.LOG_CHAT = true;
        ServerConfig.LOG_MEGA = true;
        ServerConfig.LOG_PACKETS = false;
        ServerConfig.CHRLOG_PACKETS = false;
        ServerConfig.AUTO_REGISTER = true;
        ServerConfig.LOCALHOST = false;
        ServerConfig.Encoder = false;
        ServerConfig.TESPIA = false;
        ServerConfig.shieldWardAll = false;
        ServerConfig.DISCOUNTED = false;
        ServerConfig.泡点系统 = false;
        ServerConfig.泡点地图 = 910000000;
        ServerConfig.点卷数量 = 0;
        ServerConfig.抵用卷数量 = 0;
        ServerConfig.豆豆数量 = 0;
        ServerConfig.等级经验倍率 = 0;
        ServerConfig.SERVERNAME = "冒险岛";
        ServerConfig.version = "1.7版本[ 黑金用户版 ]";
        ServerConfig.TOUDING = "Ver.079版本";
        ServerConfig.IP = MapleParty.IP地址;
        ServerConfig.wzpath = "WZ";
        ServerConfig.EVENTS = null;
        ServerConfig.DEBUG_MODE = false;
        ServerConfig.NMGB = true;
        ServerConfig.PDCS = false;
        ServerConfig.RSGS = 0;
        ServerConfig.maxlevel = 250;
        ServerConfig.kocmaxlevel = 200;
        ServerConfig.BeiShu1 = 1;
        ServerConfig.BeiShu2 = 1;
        ServerConfig.BeiShu3 = 1;
        ServerConfig.ipStr = ServerConfig.IP.split("\\.");
        Gateway_IP = new byte[] { (byte)Integer.parseInt(ServerConfig.ipStr[0]), (byte)Integer.parseInt(ServerConfig.ipStr[1]), (byte)Integer.parseInt(ServerConfig.ipStr[2]), (byte)Integer.parseInt(ServerConfig.ipStr[3]) };
        Gateway_IP2 = new byte[] { (byte)Integer.parseInt(ServerConfig.ipStr[0]), (byte)Integer.parseInt(ServerConfig.ipStr[1]), (byte)Integer.parseInt(ServerConfig.ipStr[2]), (byte)Integer.parseInt(ServerConfig.ipStr[3]) };
        loadSetting();
    }
}
