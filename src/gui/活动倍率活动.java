package gui;

import java.util.Iterator;
import handling.world.World.Broadcast;
import tools.MaplePacketCreator;
import handling.channel.ChannelServer;
import handling.world.World;
import server.ServerProperties;

public class 活动倍率活动
{
    public static void 倍率活动线程() {
        final int 随机 = (int)Math.ceil(Math.random() * 9.0);
        switch (随机) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4: {
                爆率倍率活动();
                break;
            }
            case 5:
            case 6:
            case 7:
            case 8: {
                经验倍率活动();
                break;
            }
            case 9: {
                经验倍率活动();
                爆率倍率活动();
                break;
            }
        }
    }
    
    public static void 经验倍率活动() {
        final int 原始经验 = Integer.parseInt(ServerProperties.getProperty("CongMS.expRate"));
        final int 经验活动 = 原始经验 * 2;
        final int seconds = 0;
        final int mins = 0;
        final int hours = 24;
        final int time = seconds + mins * 60 + hours * 60 * 60;
        final String rate = "经验";
        World.scheduleRateDelay("经验", (long)time);
        for (final ChannelServer cservs : ChannelServer.getAllInstances()) {
            cservs.setExpRate(经验活动);
        }
        Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(9, 20, "[倍率活动] : 游戏开始 2 倍打怪爆率活动，将持续 24 小时，周末请各位玩家狂欢吧！"));
    }
    
    public static void 爆率倍率活动() {
        final int 原始爆率 = Integer.parseInt(ServerProperties.getProperty("CongMS.dropRate"));
        final int 爆率活动 = 原始爆率 * 2;
        final int seconds = 0;
        final int mins = 0;
        final int hours = 24;
        final int time = seconds + mins * 60 + hours * 60 * 60;
        final String rate = "爆率";
        World.scheduleRateDelay("爆率", (long)time);
        for (final ChannelServer cservs : ChannelServer.getAllInstances()) {
            cservs.setDropRate(爆率活动);
        }
        Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(9, 20, "[倍率活动] : 游戏开始 2 倍打怪爆率活动，将持续 24 小时，周末请各位玩家狂欢吧！"));
    }
}
