package gui;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import database.DatabaseConnection;
import java.util.Iterator;
import client.MapleCharacter;
import server.life.MapleMonster;
import server.life.MapleLifeFactory;
import server.maps.MapleMap;
import handling.channel.ChannelServer;
import handling.world.MapleParty;
import java.util.Calendar;
import server.Timer.BuffTimer;
import handling.world.World.Broadcast;
import tools.MaplePacketCreator;
import tools.FileoutputUtil;
import java.awt.Point;
import java.util.concurrent.ScheduledFuture;

public class 活动魔族攻城
{
    public static int 蝙蝠魔;
    public static int 每波数量;
    public static ScheduledFuture<?> 魔族攻城线程;
    public static int 魔族攻城;
    public static ScheduledFuture<?> 射手村线程;
    public static int 破坏射手村;
    public static ScheduledFuture<?> 勇士部落线程;
    public static int 破坏勇士部落;
    public static ScheduledFuture<?> 魔法密林线程;
    public static int 破坏魔法密林;
    public static ScheduledFuture<?> 废弃都市线程;
    public static int 破坏废弃都市;
    public static int 破坏间隔;
    public static Point A1坐标;
    public static Point A2坐标;
    public static Point A3坐标;
    public static Point B1坐标;
    public static Point B2坐标;
    public static Point B3坐标;
    public static Point B4坐标;
    public static Point B5坐标;
    public static Point B6坐标;
    public static Point C1坐标;
    public static Point C2坐标;
    public static Point C3坐标;
    public static Point C4坐标;
    public static Point C5坐标;
    public static Point C6坐标;
    public static Point D1坐标;
    public static Point D2坐标;
    public static Point D3坐标;
    public static Point D4坐标;
    public static Point D5坐标;
    public static int 射手村防御成功;
    public static int 废弃都市防御成功;
    public static int 勇士部落防御成功;
    public static int 魔法密林防御成功;
    public static ScheduledFuture<?> 射手村1;
    public static ScheduledFuture<?> 射手村2;
    public static ScheduledFuture<?> 射手村3;
    public static int A出数;
    public static int 射手村2出数;
    public static int 射手村3出数;
    
    public static void 魔族攻城线程() {
        if (活动魔族攻城.魔族攻城线程 == null) {
            System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + "魔族攻城线程启动");
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[城镇警报] : 据有效情报，魔族大军正在2频道林中之城处集结，恐怕要兵临城下了，射手村，勇士部落，废弃都市，魔法密林，这四转城镇危在旦夕，向冒险家们发出援助请求。"));
            活动魔族攻城.魔族攻城线程 = BuffTimer.getInstance().register((Runnable)new Runnable() {
                @Override
                public void run() {
                    final int 时 = Calendar.getInstance().get(11);
                    final int 分 = Calendar.getInstance().get(12);
                    if (时 == 21 && 分 == 10 && 射手村1 == null) {
                        活动魔族攻城.射手村攻城1();
                    }
                    if (时 == 21 && 分 == 40) {
                        活动魔族攻城.关闭魔族攻城();
                    }
                }
            }, 10000L);
        }
    }
    
    public static void 关闭魔族攻城() {
        if (活动魔族攻城.废弃都市线程 != null) {
            活动魔族攻城.废弃都市线程.cancel(true);
            活动魔族攻城.废弃都市线程 = null;
        }
    }
    
    public static void 射手村攻城1() {
        if (活动魔族攻城.射手村1 == null) {
            final String 信息 = "蝙蝠魔大军正在进行射手村迷宫入口集结 ";
            System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : [城镇警报] : " + 信息);
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[城镇警报] : " + 信息));
            活动魔族攻城.射手村1 = BuffTimer.getInstance().register((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (MapleParty.蝙蝠魔A部队 <= 500) {
                        活动魔族攻城.攻城部队A(1);
                        ++MapleParty.蝙蝠魔A部队;
                    }
                    else if (射手村1 != null) {
                        射手村1.cancel(true);
                        射手村1 = null;
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(60000L);
                                    if (MapleParty.蝙蝠魔A部队 > 0) {
                                        活动魔族攻城.清怪(106010100);
                                        活动魔族攻城.射手村攻城2();
                                    }
                                    else {
                                        活动魔族攻城.射手村防御成功();
                                    }
                                }
                                catch (InterruptedException ex) {}
                            }
                        }.start();
                    }
                }
            }, 1000L);
        }
    }
    
    public static void 射手村攻城2() {
        if (活动魔族攻城.射手村2 == null) {
            final String 信息 = "蝙蝠魔大军已经抵达迷宫通道，此次兵力大概 " + MapleParty.蝙蝠魔A部队 + " ";
            System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : [城镇警报] : " + 信息);
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[城镇警报] : " + 信息));
            活动魔族攻城.射手村2 = BuffTimer.getInstance().register((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (射手村2出数 <= MapleParty.蝙蝠魔A部队) {
                        活动魔族攻城.攻城部队A(2);
                        ++射手村2出数;
                    }
                    else if (射手村2 != null) {
                        射手村2.cancel(true);
                        射手村2 = null;
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(60000L);
                                    if (MapleParty.蝙蝠魔A部队 > 0) {
                                        活动魔族攻城.清怪(106010000);
                                        活动魔族攻城.射手村攻城3();
                                    }
                                    else {
                                        活动魔族攻城.射手村防御成功();
                                    }
                                }
                                catch (InterruptedException ex) {}
                            }
                        }.start();
                    }
                }
            }, 1000L);
        }
    }
    
    public static void 射手村攻城3() {
        final String 信息 = "蝙蝠魔大军已经抵达射手村，此次兵力大概 " + MapleParty.蝙蝠魔A部队 + " ";
        System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : [城镇警报] : " + 信息);
        if (活动魔族攻城.射手村3 == null) {
            活动魔族攻城.射手村3 = BuffTimer.getInstance().register((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (射手村3出数 <= MapleParty.蝙蝠魔A部队) {
                        活动魔族攻城.攻城部队A(3);
                        ++射手村3出数;
                    }
                    else if (射手村3 != null) {
                        射手村3.cancel(true);
                        射手村3 = null;
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(60000L);
                                    if (MapleParty.蝙蝠魔A部队 > 0) {
                                        活动魔族攻城.破坏射手村();
                                    }
                                    else {
                                        活动魔族攻城.射手村防御成功();
                                    }
                                }
                                catch (InterruptedException ex) {}
                            }
                        }.start();
                    }
                }
            }, 1000L);
        }
    }
    
    public static void 破坏射手村() {
        if (活动魔族攻城.射手村线程 == null) {
            活动魔族攻城.射手村线程 = BuffTimer.getInstance().register((Runnable)new Runnable() {
                @Override
                public void run() {
                    final ChannelServer channelServer = ChannelServer.getInstance(2);
                    final MapleMap mapleMap = channelServer.getMapFactory().getMap(100000000);
                    if (破坏射手村 < 10) {
                        if (mapleMap.getMonsterById(蝙蝠魔) != null) {
                            final int 繁荣度 = 活动魔族攻城.Getcharactera("射手村繁荣度", 1);
                            final int 减少 = (int)((double)繁荣度 * 0.1);
                            if (繁荣度 > 10000) {
                                活动魔族攻城.Gaincharactera("射手村繁荣度", 1, -减少);
                            }
                            final String 信息 = "射手村正在被破坏，射手村正在被破坏，被破坏····";
                            System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [城镇警报] : " + 信息);
                            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[城镇警报] : " + 信息));
                            ++破坏射手村;
                        }
                        else {
                            活动魔族攻城.关闭破坏射手村(0);
                        }
                    }
                    else {
                        活动魔族攻城.关闭破坏射手村(1);
                    }
                }
            }, (long)(1000 * 活动魔族攻城.破坏间隔));
        }
    }
    
    public static void 关闭破坏射手村(final int a) {
        if (活动魔族攻城.射手村线程 != null) {
            活动魔族攻城.射手村线程.cancel(true);
            活动魔族攻城.射手村线程 = null;
            if (a == 1) {
                清怪(100000000);
                final String 信息 = "攻击停止了，射手村繁荣度大大下降了";
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : [城镇警报] : " + 信息);
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[城镇警报] : " + 信息));
            }
            else {
                final String 信息 = "虽然射手村被破坏了，但还是挽救回来了一些";
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : [城镇警报] : " + 信息);
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[城镇警报] : " + 信息));
            }
        }
    }
    
    public static void 射手村防御成功() {
        活动魔族攻城.射手村防御成功 = 0;
        final String 信息 = "勇士们成功防消灭了进攻射手村的魔族，恭喜你们了。";
        System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : [城镇通知] : " + 信息);
        Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[城镇通知] : " + 信息));
    }
    
    public static void 攻城部队A(final int a) {
        final ChannelServer channelServer = ChannelServer.getInstance(2);
        final MapleMonster mapleMonster = MapleLifeFactory.getMonster(活动魔族攻城.蝙蝠魔);
        switch (a) {
            case 1: {
                final int 地图 = 106010100;
                final MapleMap mapleMap = channelServer.getMapFactory().getMap(地图);
                mapleMonster.setPosition(活动魔族攻城.A1坐标);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            case 2: {
                final int 地图 = 106010000;
                final MapleMap mapleMap = channelServer.getMapFactory().getMap(地图);
                mapleMonster.setPosition(活动魔族攻城.A2坐标);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            case 3: {
                final int 地图 = 100000000;
                final MapleMap mapleMap = channelServer.getMapFactory().getMap(地图);
                mapleMonster.setPosition(活动魔族攻城.A3坐标);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
        }
    }
    
    public static void 清怪(final int a) {
        final ChannelServer channelServer = ChannelServer.getInstance(2);
        final MapleMap mapleMap = channelServer.getMapFactory().getMap(a);
        mapleMap.resetFully();
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() != a) {
                    continue;
                }
                chr.changeMap(a, 0);
            }
        }
    }
    
    public static void Gaincharactera(final String Name, final int Channale, final int Piot) {
        try {
            int ret = Getcharactera(Name, Channale);
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO charactera (channel, Name,Point) VALUES (?, ?, ?)");
                    ps.setInt(1, Channale);
                    ps.setString(2, Name);
                    ps.setInt(3, ret);
                    ps.execute();
                }
                catch (SQLException e) {
                    System.out.println("xxxxxxxx:" + (Object)e);
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    }
                    catch (SQLException e2) {
                        System.out.println("xxxxxxxxzzzzzzz:" + (Object)e2);
                    }
                }
                finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    }
                    catch (SQLException e2) {
                        System.out.println("xxxxxxxxzzzzzzz:" + (Object)e2);
                    }
                }
            }
            ret += Piot;
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps2 = con.prepareStatement("UPDATE charactera SET `Point` = ? WHERE Name = ? and channel = ?");
            ps2.setInt(1, ret);
            ps2.setString(2, Name);
            ps2.setInt(3, Channale);
            ps2.execute();
            ps2.close();
        }
        catch (SQLException sql) {
            System.err.println("Getcharactera!!55" + (Object)sql);
        }
    }
    
    public static int Getcharactera(final String Name, final int Channale) {
        int ret = -1;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM charactera WHERE channel = ? and Name = ?");
            ps.setInt(1, Channale);
            ps.setString(2, Name);
            final ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("Point");
            rs.close();
            ps.close();
        }
        catch (SQLException ex) {}
        return ret;
    }
    
    static {
        活动魔族攻城.蝙蝠魔 = 8150000;
        活动魔族攻城.每波数量 = 20;
        活动魔族攻城.魔族攻城线程 = null;
        活动魔族攻城.魔族攻城 = 0;
        活动魔族攻城.射手村线程 = null;
        活动魔族攻城.破坏射手村 = 0;
        活动魔族攻城.勇士部落线程 = null;
        活动魔族攻城.破坏勇士部落 = 0;
        活动魔族攻城.魔法密林线程 = null;
        活动魔族攻城.破坏魔法密林 = 0;
        活动魔族攻城.废弃都市线程 = null;
        活动魔族攻城.破坏废弃都市 = 0;
        活动魔族攻城.破坏间隔 = 30;
        活动魔族攻城.A1坐标 = new Point(3114, 125);
        活动魔族攻城.A2坐标 = new Point(2835, 275);
        活动魔族攻城.A3坐标 = new Point(6084, -176);
        活动魔族攻城.B1坐标 = new Point(1597, 101);
        活动魔族攻城.B2坐标 = new Point(1855, 124);
        活动魔族攻城.B3坐标 = new Point(1669, 124);
        活动魔族攻城.B4坐标 = new Point(1666, 125);
        活动魔族攻城.B5坐标 = new Point(1320, 121);
        活动魔族攻城.B6坐标 = new Point(-849, 373);
        活动魔族攻城.C1坐标 = new Point(213, -48);
        活动魔族攻城.C2坐标 = new Point(-1344, 23);
        活动魔族攻城.C3坐标 = new Point(-359, 24);
        活动魔族攻城.C4坐标 = new Point(-357, 825);
        活动魔族攻城.C5坐标 = new Point(-1642, 1560);
        活动魔族攻城.C6坐标 = new Point(-1091, -2806);
        活动魔族攻城.D1坐标 = new Point(-415, 2025);
        活动魔族攻城.D2坐标 = new Point(1372, 2145);
        活动魔族攻城.D3坐标 = new Point(-437, 2025);
        活动魔族攻城.D4坐标 = new Point(758, 2025);
        活动魔族攻城.D5坐标 = new Point(2824, 1935);
        活动魔族攻城.射手村防御成功 = 0;
        活动魔族攻城.废弃都市防御成功 = 0;
        活动魔族攻城.勇士部落防御成功 = 0;
        活动魔族攻城.魔法密林防御成功 = 0;
        活动魔族攻城.射手村1 = null;
        活动魔族攻城.射手村2 = null;
        活动魔族攻城.射手村3 = null;
        活动魔族攻城.A出数 = 0;
        活动魔族攻城.射手村2出数 = 0;
        活动魔族攻城.射手村3出数 = 0;
    }
}
