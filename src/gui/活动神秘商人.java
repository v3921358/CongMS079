package gui;

import java.awt.Point;
import server.maps.MapleMap;
import tools.FileoutputUtil;
import handling.world.World.Broadcast;
import tools.MaplePacketCreator;
import handling.channel.ChannelServer;
import handling.world.MapleParty;
import server.ServerProperties;

public class 活动神秘商人
{
    public static int 神秘商人;
    public static int 存在时间;
    
    public static void 启动神秘商人() {
        随机商人出现条件();
    }
    
    public static void 随机商人出现条件() {
        int pind = (int)Math.ceil(Math.random() * (double)Integer.parseInt(ServerProperties.getProperty("CongMS.channel.count")));
        if (pind == 0) {
            ++pind;
        }
        int huor = (int)Math.ceil(Math.random() * 23.0);
        if (MapleParty.神秘商人时间 == huor) {
            if (huor == 23) {
                --huor;
            }
            else {
                ++huor;
            }
        }
        final int rand = (int)Math.ceil(Math.random() * 52.0);
        final int[][] 坐标 = { { 106010000, 488, 215 }, { 240010000, 905, -298 }, { 240010100, 2098, -508 }, { 240010200, 2920, -688 }, { 240010200, 409, -688 }, { 240010300, -679, -1048 }, { 240010300, 222, -868 }, { 240010300, 719, 32 }, { 110000000, -64, 151 }, { 110000000, 379, -143 }, { 110010000, -1594, -113 }, { 110010000, 1204, -473 }, { 110020000, -1077, -113 }, { 110020000, -310, -118 }, { 110020000, 1167, 182 }, { 110030000, -1558, 149 }, { 110030000, 164, 173 }, { 104040000, 1059, -687 }, { 104040000, 48, -685 }, { 104030000, -858, -385 }, { 104030000, 1481, -985 }, { 104020000, 1418, -1345 }, { 104010000, 2329, -115 }, { 104010002, -1401, -25 }, { 100000002, -16, -475 }, { 100000002, 214, -475 }, { 100010000, 198, 505 }, { 100030000, -3652, -205 }, { 100040000, 349, 1752 }, { 105050000, 2282, 1619 }, { 230010300, 44, 40 }, { 230010300, -1744, -320 }, { 541020100, 958, -346 }, { 105090301, 928, -923 }, { 105040305, 1245, 2295 }, { 100030000, -2977, -1465 }, { 100000006, -705, 215 }, { 220030000, 638, 162 }, { 600000000, 5682, -632 }, { 200070000, -132, -715 }, { 222010201, 120, -1047 }, { 100040104, 66, 812 }, { 260010400, 199, -85 }, { 103010000, -1088, 232 }, { 230010201, -50, -17 }, { 240020100, -889, -508 }, { 221020000, 11, 2162 }, { 105090700, 523, -181 }, { 220070201, 811, 1695 }, { 211040300, -289, 454 }, { 541010040, 1075, -1695 }, { 600020300, 1, -204 }, { 261000001, -47, 64 } };
        MapleParty.神秘商人时间 = huor;
        MapleParty.神秘商人频道 = pind;
        MapleParty.神秘商人地图 = 坐标[rand][0];
        MapleParty.神秘商人坐标X = 坐标[rand][1];
        MapleParty.神秘商人坐标Y = 坐标[rand][2];
        final ChannelServer channelServer = ChannelServer.getInstance(MapleParty.神秘商人频道);
        final MapleMap mapleMap = channelServer.getMapFactory().getMap(MapleParty.神秘商人地图);
        final String 信息 = "[神秘商人] : 一个神秘的商人 " + MapleParty.神秘商人时间 + " 时将出现在 " + MapleParty.神秘商人频道 + " 频道的某个地方。";
        Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, 信息));
        System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : " + 信息);
        System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : 出现在地图: " + 坐标[rand][0] + "( " + mapleMap.getMapName() + " ) 坐标: " + 坐标[rand][1] + "/" + 坐标[rand][2]);
    }
    
    public static void 召唤神秘商人() {
        final ChannelServer channelServer = ChannelServer.getInstance(MapleParty.神秘商人频道);
        final MapleMap mapleMap = channelServer.getMapFactory().getMap(MapleParty.神秘商人地图);
        mapleMap.spawnNpc(活动神秘商人.神秘商人, new Point(MapleParty.神秘商人坐标X, MapleParty.神秘商人坐标Y));
        final String 信息 = "[神秘商人] : 一个神秘的商人出现在 " + MapleParty.神秘商人频道 + " 频道" + mapleMap.getMapName() + "。";
        Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, 信息));
        System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : " + 信息);
        ++MapleParty.神秘商人;
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep((long)(60000 * 存在时间));
                    活动神秘商人.删除神秘商人();
                }
                catch (InterruptedException ex) {}
            }
        }.start();
    }
    
    public static void 删除神秘商人() {
        final ChannelServer channelServer = ChannelServer.getInstance(MapleParty.神秘商人频道);
        final MapleMap mapleMap = channelServer.getMapFactory().getMap(MapleParty.神秘商人地图);
        mapleMap.removeNpc(活动神秘商人.神秘商人);
        MapleParty.神秘商人 = 0;
        随机商人出现条件();
    }
    
    static {
        活动神秘商人.神秘商人 = 9900001;
        活动神秘商人.存在时间 = 5;
    }
}
