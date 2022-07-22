package gui;

import server.life.MapleMonster;
import java.util.Iterator;
import java.util.Calendar;
import server.life.MapleLifeFactory;
import abc.Game;
import client.MapleCharacter;
import handling.channel.ChannelServer;
import server.Timer.BuffTimer;
import handling.world.World.Broadcast;
import tools.MaplePacketCreator;
import tools.FileoutputUtil;
import java.util.concurrent.ScheduledFuture;

public class 活动魔族入侵
{
    public static ScheduledFuture<?> 活动魔族的骚动;
    public static Boolean 调试;
    public static int x;
    public static int 蝙蝠魔;
    public static int S;
    
    public static void main(final String[] args) {
        魔族入侵线程();
    }
    
    public static void 魔族入侵线程() {
        if (活动魔族入侵.活动魔族的骚动 == null) {
            System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : [魔族入侵] : 魔族已经悄然接近冒险大陆，请各位冒险家呆在主城等安全区域");
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(1, "[冒险警报]\r\n魔族已经悄然接近冒险大陆，请各位冒险家呆在主城等安全区域。"));
            活动魔族入侵.活动魔族的骚动 = BuffTimer.getInstance().register((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (S > 0) {
                        ++x;
                        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                            for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                                if (chr == null) {
                                    continue;
                                }
                                final double 随机 = Math.ceil(Math.random() * 100.0);
                                int 被袭击 = 100;
                                final int 等级 = chr.getLevel();
                                if (等级 >= 10 && 等级 <= 30) {
                                    被袭击 -= 100;
                                }
                                else if (等级 > 30 && 等级 <= 70) {
                                    被袭击 -= 30;
                                }
                                else if (等级 > 70 && 等级 <= 120) {
                                    被袭击 -= 40;
                                }
                                else if (等级 > 120 && 等级 <= 150) {
                                    被袭击 -= 50;
                                }
                                else if (等级 > 150 && 等级 <= 200) {
                                    被袭击 -= 60;
                                }
                                else if (等级 > 200) {
                                    被袭击 -= 70;
                                }
                                final int 地图人数 = chr.getMap().getCharactersSize();
                                if (地图人数 > 3) {
                                    被袭击 -= 30;
                                }
                                else if (地图人数 > 1) {
                                    被袭击 -= 10;
                                }
                                if (chr.getMapId() < 100000000) {
                                    continue;
                                }
                                if (随机 <= (double)被袭击) {
                                    if ((chr.getMapId() <= 910000024 && chr.getMapId() >= 910000000) || Game.主城(chr.getMapId())) {
                                        continue;
                                    }
                                    final MapleMonster mob1 = MapleLifeFactory.getMonster(蝙蝠魔);
                                    chr.getMap().spawnMonsterOnGroundBelow(mob1, chr.getPosition());
                                    final MapleMonster mob2 = MapleLifeFactory.getMonster(蝙蝠魔);
                                    chr.getMap().spawnMonsterOnGroundBelow(mob2, chr.getPosition());
                                    final MapleMonster mob3 = MapleLifeFactory.getMonster(蝙蝠魔);
                                    chr.getMap().spawnMonsterOnGroundBelow(mob3, chr.getPosition());
                                    Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[冒险警报] : 玩家 " + chr.getName() + " 在 " + chr.getMap().getMapName() + " 被魔族袭击。"));
                                    chr.dropMessage(5, "你被魔族袭击了。");
                                }
                                else {
                                    chr.dropMessage(5, "一阵凉风吹过。");
                                }
                            }
                        }
                        if (Calendar.getInstance().get(12) >= 10) {
                            活动魔族入侵.关闭活动魔族的骚动();
                        }
                    }
                    else {
                        ++S;
                    }
                }
            }, 120000L);
        }
    }
    
    public static void 关闭活动魔族的骚动() {
        if (活动魔族入侵.活动魔族的骚动 != null) {
            活动魔族入侵.活动魔族的骚动.cancel(true);
            活动魔族入侵.活动魔族的骚动 = null;
            System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : [魔族入侵] : 魔族已经暂且离去，它们可能会再次卷土重来");
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[冒险警报] : 魔族已经暂且离去，它们可能会再次卷土重来。"));
        }
    }
    
    static {
        活动魔族入侵.活动魔族的骚动 = null;
        活动魔族入侵.调试 = Boolean.valueOf(false);
        活动魔族入侵.x = 1;
        活动魔族入侵.蝙蝠魔 = 8150000;
        活动魔族入侵.S = 0;
    }
}
