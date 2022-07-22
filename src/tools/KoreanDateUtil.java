package tools;

import java.util.Date;
import java.util.TimeZone;

public class KoreanDateUtil
{
    private static final int ITEM_YEAR2000 = -1085019342;
    private static final long REAL_YEAR2000 = 946681229830L;
    private static final int QUEST_UNIXAGE = 27111908;
    private static final long FT_UT_OFFSET = 116444736000000000L;
    
    public static final long getTempBanTimestamp(final long realTimestamp) {
        return realTimestamp * 10000L + 116444736000000000L;
    }
    
    public static final int getItemTimestamp(final long realTimestamp) {
        final int time = (int)((realTimestamp - 946681229830L) / 1000L / 60L);
        return (int)((double)time * 35.762787) - 1085019342;
    }
    
    public static final int getQuestTimestamp(final long realTimestamp) {
        final int time = (int)(realTimestamp / 1000L / 60L);
        return (int)((double)time * 0.1396987) + 27111908;
    }
    
    public static boolean isDST() {
        return TimeZone.getDefault().inDaylightTime(new Date());
    }
    
    public static long getFileTimestamp(long timeStampinMillis, final boolean roundToMinutes) {
        if (isDST()) {
            timeStampinMillis -= 3600000L;
        }
        long time;
        if (roundToMinutes) {
            time = timeStampinMillis / 1000L / 60L * 600000000L;
        }
        else {
            time = timeStampinMillis * 10000L;
        }
        return time + 116444736000000000L;
    }
}
