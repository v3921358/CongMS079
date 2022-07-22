package constants;

import java.util.HashMap;
import client.LoginCrypto;
import java.util.Map;

public class PiPiConfig
{
    public static boolean Start_Check;
    public static boolean autoban;
    public static boolean CommandLock;
    public static int 商店一次拍賣获得最大金币;
    protected static String acc;
    public static String[] banText;
    public static Map<Integer, String> BlackList;
    
    protected static boolean counting(final String pw) {
        final String news = LoginCrypto.hexSha1(pw);
        final String newSalt = LoginCrypto.makeSalt();
        LoginCrypto.makeSaltedSha512Hash(news, newSalt);
        return news.equals((Object)PiPiConfig.acc);
    }
    
    public static boolean doCheck(final String pw) {
        return counting(pw);
    }
    
    public static Map<Integer, String> getBlackList() {
        return PiPiConfig.BlackList;
    }
    
    public static void setBlackList(final int accid, final String name) {
        PiPiConfig.BlackList.put(Integer.valueOf(accid), name);
    }
    
    public static boolean getAutoban() {
        return PiPiConfig.autoban;
    }
    
    public static void setAutoban(final boolean x) {
        PiPiConfig.autoban = x;
    }
    
    public static boolean getCommandLock() {
        return PiPiConfig.CommandLock;
    }
    
    public static void setCommandLock(final boolean x) {
        PiPiConfig.CommandLock = x;
    }
    
    public static boolean isCanTalkText(final String text) {
        final String message = text.toLowerCase();
        for (int i = 0; i < PiPiConfig.banText.length; ++i) {
            if (message.contains((CharSequence)PiPiConfig.banText[i])) {
                return false;
            }
        }
        return (!message.contains((CharSequence)"垃") || !message.contains((CharSequence)"圾")) && (!message.contains((CharSequence)"雖") || !message.contains((CharSequence)"小")) && (!message.contains((CharSequence)"沙") || !message.contains((CharSequence)"小")) && (!message.contains((CharSequence)"殺") || !message.contains((CharSequence)"小")) && (!message.contains((CharSequence)"三") || !message.contains((CharSequence)"小")) && (!message.contains((CharSequence)"北") || !message.contains((CharSequence)"七")) && (!message.contains((CharSequence)"北") || !message.contains((CharSequence)"7")) && (!message.contains((CharSequence)"巴") || !message.contains((CharSequence)"七")) && (!message.contains((CharSequence)"巴") || !message.contains((CharSequence)"7")) && (!message.contains((CharSequence)"八") || !message.contains((CharSequence)"七")) && (!message.contains((CharSequence)"八") || !message.contains((CharSequence)"7")) && (!message.contains((CharSequence)"白") || !message.contains((CharSequence)"目")) && (!message.contains((CharSequence)"白") || !message.contains((CharSequence)"癡")) && (!message.contains((CharSequence)"白") || !message.contains((CharSequence)"吃")) && (!message.contains((CharSequence)"白") || !message.contains((CharSequence)"ㄔ")) && (!message.contains((CharSequence)"白") || !message.contains((CharSequence)"ㄘ")) && (!message.contains((CharSequence)"機") || !message.contains((CharSequence)"車")) && (!message.contains((CharSequence)"機") || !message.contains((CharSequence)"八")) && (!message.contains((CharSequence)"伶") || !message.contains((CharSequence)"北")) && (!message.contains((CharSequence)"林") || !message.contains((CharSequence)"北")) && (!message.contains((CharSequence)"廢") || !message.contains((CharSequence)"物")) && (!message.contains((CharSequence)"媽") || !message.contains((CharSequence)"的")) && (!message.contains((CharSequence)"俗") || !message.contains((CharSequence)"辣")) && (!message.contains((CharSequence)"智") || !message.contains((CharSequence)"障")) && (!message.contains((CharSequence)"低") || !message.contains((CharSequence)"能")) && (!message.contains((CharSequence)"乞") || !message.contains((CharSequence)"丐")) && (!message.contains((CharSequence)"乾") || !message.contains((CharSequence)"娘")) && (!message.contains((CharSequence)"ㄎ") || !message.contains((CharSequence)"ㄅ")) && (!message.contains((CharSequence)"ㄌ") || !message.contains((CharSequence)"ㄐ")) && (!message.contains((CharSequence)"ㄋ") || !message.contains((CharSequence)"ㄠ") || !message.contains((CharSequence)"ˇ")) && (!message.contains((CharSequence)"ㄍ") || !message.contains((CharSequence)"ˋ")) && (!message.contains((CharSequence)"e") || !message.contains((CharSequence)"0") || !message.contains((CharSequence)"4"));
    }
    
    static {
        PiPiConfig.Start_Check = false;
        PiPiConfig.autoban = true;
        PiPiConfig.CommandLock = false;
        PiPiConfig.商店一次拍賣获得最大金币 = 1500000;
        PiPiConfig.acc = "7c4a8d09ca3762af61e59520943dc26494f8941b";
        PiPiConfig.banText = new String[] { "幹", "靠", "屎", "糞", "淦", "靠" };
        PiPiConfig.BlackList = (Map<Integer, String>)new HashMap() {
            {
                this.put(Integer.valueOf(5307), "一朵花");
                this.put(Integer.valueOf(4291), "DoubleWinds");
                this.put(Integer.valueOf(3373), "史萊姆哥哥");
            }
        };
    }
}
