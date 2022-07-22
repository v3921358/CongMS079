package constants;

import gui.CongMS;
import server.ServerProperties;

public class WorldConstants
{
    public static Option WORLD;
    public static boolean ADMIN_ONLY;
    public static boolean JZSD;
    public static boolean WUYANCHI;
    public static boolean LieDetector;
    public static boolean DropItem;
    public static int USER_LIMIT;
    public static int MAX_CHAR_VIEW;
    public static boolean GMITEMS;
    public static boolean CS_ENABLE;
    public static int EXP_RATE;
    public static int MESO_RATE;
    public static int DROP_RATE;
    public static byte FLAG;
    public static int CHANNEL_COUNT;
    public static String WORLD_TIP;
    public static String SCROLL_MESSAGE;
    public static boolean AVAILABLE;
    public static final int gmserver = -1;
    public static final byte recommended = -1;
    public static final String recommendedmsg;
    
    public static Option[] values() {
        return ServerConstants.TESPIA ? TespiaWorldOption.values() : WorldOption.values();
    }
    
    public static Option valueOf(final String name) {
        return ServerConstants.TESPIA ? TespiaWorldOption.valueOf(name) : WorldOption.valueOf(name);
    }
    
    public static Option getById(final int g) {
        for (final Option e : values()) {
            if (e.getWorld() == g) {
                return e;
            }
        }
        return null;
    }
    
    public static boolean isExists(final int id) {
        return getById(id) != null;
    }
    
    public static String getNameById(final int serverid) {
        if (getById(serverid) == null) {
            System.err.println("World doesn't exists exception. ID: " + serverid);
            return "";
        }
        return getById(serverid).name();
    }
    
    public static void loadSetting() {
        WorldConstants.ADMIN_ONLY = ServerProperties.getProperty("CongMS.admin", WorldConstants.ADMIN_ONLY);
        WorldConstants.FLAG = ServerProperties.getProperty("CongMS.flag", WorldConstants.FLAG);
        WorldConstants.EXP_RATE = ServerProperties.getProperty("CongMS.expRate", WorldConstants.EXP_RATE);
        WorldConstants.MESO_RATE = ServerProperties.getProperty("CongMS.mesoRate", WorldConstants.MESO_RATE);
        WorldConstants.DROP_RATE = ServerProperties.getProperty("CongMS.dropRat", WorldConstants.DROP_RATE);
        WorldConstants.WORLD_TIP = ServerProperties.getProperty("CongMS.eventMessage", WorldConstants.WORLD_TIP);
        WorldConstants.SCROLL_MESSAGE = ServerProperties.getProperty("CongMS.serverMessage", WorldConstants.SCROLL_MESSAGE);
        WorldConstants.CHANNEL_COUNT = ServerProperties.getProperty("CongMS.channel.count", WorldConstants.CHANNEL_COUNT);
        WorldConstants.USER_LIMIT = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"服务端最大人数"));
        WorldConstants.MAX_CHAR_VIEW = ServerProperties.getProperty("CongMS.maxCharView", WorldConstants.MAX_CHAR_VIEW);
        WorldConstants.GMITEMS = ServerProperties.getProperty("CongMS.gmitems", WorldConstants.GMITEMS);
        WorldConstants.CS_ENABLE = ServerProperties.getProperty("CongMS.cashshop.enable", WorldConstants.CS_ENABLE);
    }
    
    static {
        WorldConstants.WORLD = WorldOption.绿水灵;
        WorldConstants.ADMIN_ONLY = true;
        WorldConstants.JZSD = false;
        WorldConstants.WUYANCHI = true;
        WorldConstants.LieDetector = false;
        WorldConstants.DropItem = true;
        WorldConstants.USER_LIMIT = 10000;
        WorldConstants.MAX_CHAR_VIEW = 20;
        WorldConstants.GMITEMS = false;
        WorldConstants.CS_ENABLE = true;
        WorldConstants.EXP_RATE = 1;
        WorldConstants.MESO_RATE = 1;
        WorldConstants.DROP_RATE = 1;
        WorldConstants.FLAG = 3;
        WorldConstants.CHANNEL_COUNT = 2;
        WorldConstants.WORLD_TIP = "請享受楓之谷的冒險之旅吧!";
        WorldConstants.SCROLL_MESSAGE = "";
        WorldConstants.AVAILABLE = true;
        recommendedmsg = "";
        loadSetting();
    }
    
    public enum WorldOption implements Option
    {
        蓝蜗牛(0), 
        蘑菇仔(1), 
        绿水灵(2), 
        漂漂猪(3), 
        小青蛇(4), 
        红螃蟹(5), 
        大海龟(6), 
        章鱼怪(7), 
        顽皮猴(8), 
        星精灵(9), 
        胖企鹅(10), 
        童话村(121);
        
        private final int world;
        
        private WorldOption(final int world) {
            this.world = world;
        }
        
        @Override
        public int getWorld() {
            return this.world;
        }
    }
    
    public enum TespiaWorldOption implements Option
    {
        測試機("t0");
        
        private final int world;
        private final String worldName;
        
        private TespiaWorldOption(final String world) {
            this.world = Integer.parseInt(world.replaceAll("t", ""));
            this.worldName = world;
        }
        
        @Override
        public int getWorld() {
            return this.world;
        }
    }
    
    public interface Option
    {
        int getWorld();
        
        String name();
    }
}
