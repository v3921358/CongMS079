package constants;

import server.ServerProperties;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServerConstants
{
    public static boolean TESPIA;
    public static final boolean PollEnabled = false;
    public static final String Poll_Question = "Are you mudkiz?";
    public static final String[] Poll_Answers;
    public static final short MAPLE_VERSION = 79;
    public static final String MAPLE_PATCH = "1";
    public static MapleType MAPLE_TYPE;
    public static boolean Use_Fixed_IV;
    public static final int MIN_MTS = 110;
    public static final int MTS_BASE = 100;
    public static final int MTS_TAX = 10;
    public static final int MTS_MESO = 5000;
    public static final int CHANNEL_COUNT = 200;
    public static final String CashShop_Key = "a;!%dfb_=*-a123d9{P~";
    public static final String Login_Key = "pWv]xq:SPTCtk^LGnU9F";
    public static int[] hot_sell;
    public static boolean MobDropMPoint;
    public static int MobDropMPointRate;
    public static int MobDropMPointLimit;
    public static int MobDropMPointMin;
    public static int MopDropMPointMax;
    public static AtomicBoolean ENABLE_H_EXP;
    
    public static AtomicBoolean getENABLE_H_EXP() {
        return ServerConstants.ENABLE_H_EXP;
    }
    
    public static void setENABLE_H_EXP(final boolean ENABLE_H_EXP) {
        ServerConstants.ENABLE_H_EXP.set(ENABLE_H_EXP);
    }
    
    public static void loadSetting() {
        final String[] x = ServerProperties.getProperty("CongMS.cashshop.HotSell", "10000007, 10000008, 10000009, 10000010, 10000011").split(",");
        final int[] y = new int[x.length];
        for (int i = 0; i < x.length; ++i) {
            y[i] = Integer.parseInt(x[i].replace((CharSequence)" ", (CharSequence)""));
        }
        ServerConstants.hot_sell = y;
        ServerConstants.TESPIA = ServerProperties.getProperty("CongMS.tespia", ServerConstants.TESPIA);
        ServerConstants.Use_Fixed_IV = ServerProperties.getProperty("server.crypt", ServerConstants.Use_Fixed_IV);
    }
    
    static {
        ServerConstants.TESPIA = false;
        Poll_Answers = new String[] { "test1", "test2", "test3" };
        ServerConstants.MAPLE_TYPE = MapleType.中国;
        ServerConstants.Use_Fixed_IV = false;
        ServerConstants.hot_sell = new int[] { 10000007, 10000008, 10000009, 10000010, 10000011 };
        ServerConstants.MobDropMPoint = true;
        ServerConstants.MobDropMPointRate = 30;
        ServerConstants.MobDropMPointLimit = 2500;
        ServerConstants.MobDropMPointMin = 1;
        ServerConstants.MopDropMPointMax = 2;
        ServerConstants.ENABLE_H_EXP = new AtomicBoolean(true);
        loadSetting();
    }
    
    public enum PlayerGMRank
    {
        普通玩家(0), 
        新实习生(1), 
        老實習生(2), 
        巡逻者(3), 
        领导者(4), 
        超级管理员(5), 
        神(100);
        
        private final char commandPrefix;
        private final int level;
        
        private PlayerGMRank(final int level) {
            this.commandPrefix = ((level > 0) ? '!' : '@');
            this.level = level;
        }
        
        public char getCommandPrefix() {
            return this.commandPrefix;
        }
        
        public int getLevel() {
            return this.level;
        }
    }
    
    public enum CommandType
    {
        NORMAL(0), 
        TRADE(1);
        
        private final int level;
        
        private CommandType(final int level) {
            this.level = level;
        }
        
        public int getType() {
            return this.level;
        }
    }
    
    public enum MapleType
    {
        UNKNOWN(-1, "UTF-8"), 
        한국(1, "EUC_KR"), 
        한국_TEST(1, "EUC_KR"), 
        日本(3, "Shift_JIS"), 
        中国(4, "GBK"), 
        TESPIA(5, "UTF-8"), 
        台灣(6, "BIG5"), 
        SEA(7, "UTF-8"), 
        GLOBAL(8, "UTF-8"), 
        BRAZIL(9, "UTF-8");
        
        byte type;
        final String ANSI;
        
        private MapleType(final int type, final String ANSI) {
            this.type = (byte)type;
            this.ANSI = ANSI;
        }
        
        public byte getType() {
            if (!ServerConstants.TESPIA) {
                return this.type;
            }
            if (this == MapleType.한국 || this == MapleType.한국_TEST) {
                return 2;
            }
            return 5;
        }
        
        public String getANSI() {
            return this.ANSI;
        }
        
        public void setType(final int type) {
            this.type = (byte)type;
        }
        
        public static MapleType getByType(final byte type) {
            for (final MapleType l : values()) {
                if (l.getType() == type) {
                    return l;
                }
            }
            return MapleType.UNKNOWN;
        }
    }
}
