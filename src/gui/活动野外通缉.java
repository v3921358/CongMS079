package gui;

import server.life.MapleMonster;
import server.maps.MapleMap;
import tools.FileoutputUtil;
import handling.world.World.Broadcast;
import tools.MaplePacketCreator;
import server.life.MapleLifeFactory;
import handling.channel.ChannelServer;
import handling.world.MapleParty;

public class 活动野外通缉
{
    public static void 随机通缉() {
        if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"野外通缉开关")) == 0) {
            final int a = (int)Math.ceil(Math.random() * 25.0);
            final int[][] 通缉 = { { 2220000, 104000400 }, { 5220001, 110040000 }, { 7220000, 250010304 }, { 8220000, 200010300 }, { 7220002, 250010503 }, { 7220001, 222010310 }, { 6220000, 107000300 }, { 5220002, 100040105 }, { 5220003, 220050100 }, { 6220001, 221040301 }, { 8220003, 240040401 }, { 3220001, 260010201 }, { 8220002, 261030000 }, { 4220000, 230020100 }, { 6130101, 100000005 }, { 6300005, 105070002 }, { 8130100, 105090900 }, { 9400205, 800010100 }, { 9400120, 801030000 }, { 8220001, 211040101 }, { 8180000, 240020401 }, { 8180001, 240020101 }, { 8220006, 270030500 }, { 8220005, 270020500 }, { 8220004, 270010500 }, { 3220000, 101030404 } };
            MapleParty.通缉BOSS = 通缉[a][0];
            MapleParty.通缉地图 = 通缉[a][1];
            final ChannelServer channelServer = ChannelServer.getInstance(1);
            final MapleMap mapleMap = channelServer.getMapFactory().getMap(MapleParty.通缉地图);
            final MapleMonster mobName = MapleLifeFactory.getMonster(MapleParty.通缉BOSS);
            final String 信息 = "[野外通缉] : 系统发布了一份通缉令，请在 " + mapleMap.getMapName() + " 通缉 " + mobName.getStats().getName() + "";
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, 信息));
            System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : " + 信息);
        }
    }
}
