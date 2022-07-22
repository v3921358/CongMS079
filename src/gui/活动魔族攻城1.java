package gui;

import java.util.Iterator;
import client.MapleCharacter;
import server.life.MapleMonster;
import server.life.MapleLifeFactory;
import handling.world.MapleParty;
import server.maps.MapleMap;
import handling.channel.ChannelServer;
import java.util.Calendar;
import server.Timer.BuffTimer;
import handling.world.World.Broadcast;
import tools.MaplePacketCreator;
import tools.FileoutputUtil;
import java.awt.Point;
import java.util.concurrent.ScheduledFuture;

public class 活动魔族攻城1
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
    public static int A;
    public static int B;
    public static int C;
    public static int D;
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
    public static int A队数量;
    public static int B队数量;
    public static int C队数量;
    public static int D队数量;
    
    public static void 魔族攻城线程() {
        if (活动魔族攻城1.魔族攻城线程 == null) {
            System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + "魔族攻城线程启动");
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[城镇警报] : 据有效情报，魔族大军正在2频道林中之城处集结，恐怕要兵临城下了，射手村，勇士部落，废弃都市，魔法密林，这四转城镇危在旦夕，向冒险家们发出援助请求。"));
            活动魔族攻城1.魔族攻城线程 = BuffTimer.getInstance().register((Runnable)new Runnable() {
                @Override
                public void run() {
                    final int 时 = Calendar.getInstance().get(11);
                    final int 分 = Calendar.getInstance().get(12);
                    if (A < 3 && 射手村防御成功 == 0) {
                        活动魔族攻城1.攻城部队A();
                    }
                    if (B < 6 && 废弃都市防御成功 == 0) {
                        活动魔族攻城1.攻城部队B();
                    }
                    if (C < 6 && 魔法密林防御成功 == 0) {
                        活动魔族攻城1.攻城部队C();
                    }
                    if (D < 5 && 勇士部落防御成功 == 0) {
                        活动魔族攻城1.攻城部队D();
                    }
                    if (时 == 21 && 分 == 40) {
                        活动魔族攻城1.关闭魔族攻城();
                    }
                }
            }, 10000L);
        }
    }
    
    public static void 关闭魔族攻城() {
        if (活动魔族攻城1.废弃都市线程 != null) {
            活动魔族攻城1.废弃都市线程.cancel(true);
            活动魔族攻城1.废弃都市线程 = null;
        }
    }
    
    public static void 破坏射手村() {
        if (活动魔族攻城1.射手村线程 == null) {
            活动魔族攻城1.射手村线程 = BuffTimer.getInstance().register((Runnable)new Runnable() {
                @Override
                public void run() {
                    final ChannelServer channelServer = ChannelServer.getInstance(2);
                    final MapleMap mapleMap = channelServer.getMapFactory().getMap(100000000);
                    if (破坏射手村 < 10) {
                        if (mapleMap.getMonsterById(蝙蝠魔) != null) {
                            final String 信息 = "射手村正在被破坏，射手村正在被破坏，被破坏····";
                            System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [城镇警报] : " + 信息);
                            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[城镇警报] : " + 信息));
                            ++破坏射手村;
                        }
                        else {
                            活动魔族攻城1.关闭破坏射手村(0);
                        }
                    }
                    else {
                        活动魔族攻城1.关闭破坏射手村(1);
                    }
                }
            }, (long)(1000 * 活动魔族攻城1.破坏间隔));
        }
    }
    
    public static void 关闭破坏射手村(final int a) {
        if (活动魔族攻城1.射手村线程 != null) {
            活动魔族攻城1.射手村线程.cancel(true);
            活动魔族攻城1.射手村线程 = null;
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
        活动魔族攻城1.射手村防御成功 = 0;
        final String 信息 = "勇士们成功防消灭了进攻射手村的魔族，恭喜你们了。";
        System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : [城镇通知] : " + 信息);
        Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[城镇通知] : " + 信息));
    }
    
    public static void 破坏废弃都市() {
        if (活动魔族攻城1.废弃都市线程 == null) {
            活动魔族攻城1.废弃都市线程 = BuffTimer.getInstance().register((Runnable)new Runnable() {
                @Override
                public void run() {
                    final ChannelServer channelServer = ChannelServer.getInstance(2);
                    final MapleMap mapleMap = channelServer.getMapFactory().getMap(103000000);
                    if (破坏废弃都市 <= 10) {
                        if (mapleMap.getMonsterById(蝙蝠魔) != null) {
                            final String 信息 = "射手村正在被破坏，射手村正在被破坏，被破坏····";
                            System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [城镇警报] : " + 信息);
                            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[城镇警报] : " + 信息));
                            ++破坏废弃都市;
                        }
                        else {
                            活动魔族攻城1.关闭破坏废弃都市(0);
                        }
                    }
                    else {
                        活动魔族攻城1.关闭破坏废弃都市(1);
                    }
                }
            }, (long)(1000 * 活动魔族攻城1.破坏间隔));
        }
    }
    
    public static void 关闭破坏废弃都市(final int a) {
        if (活动魔族攻城1.废弃都市线程 != null) {
            活动魔族攻城1.废弃都市线程.cancel(true);
            活动魔族攻城1.废弃都市线程 = null;
            if (a == 1) {
                清怪(103000000);
                final String 信息 = "攻击停止了，废弃都市繁荣度大大下降了";
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : [城镇警报] : " + 信息);
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[城镇警报] : " + 信息));
            }
            else {
                final String 信息 = "虽然废弃都市被破坏了，但还是挽救回来了一些";
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : [城镇警报] : " + 信息);
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[城镇警报] : " + 信息));
            }
        }
    }
    
    public static void 废弃都市防御成功() {
        活动魔族攻城1.废弃都市防御成功 = 0;
        final String 信息 = "勇士们成功防消灭了进攻废弃都市的魔族，恭喜你们了。";
        System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : [城镇通知] : " + 信息);
        Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[城镇通知] : " + 信息));
    }
    
    public static void 破坏勇士部落() {
        if (活动魔族攻城1.勇士部落线程 == null) {
            活动魔族攻城1.勇士部落线程 = BuffTimer.getInstance().register((Runnable)new Runnable() {
                @Override
                public void run() {
                    final ChannelServer channelServer = ChannelServer.getInstance(2);
                    final MapleMap mapleMap = channelServer.getMapFactory().getMap(103000000);
                    if (破坏勇士部落 <= 10) {
                        if (mapleMap.getMonsterById(蝙蝠魔) != null) {
                            final String 信息 = "勇士部落正在被破坏，勇士部落正在被破坏，被破坏····";
                            System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [城镇警报] : " + 信息);
                            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[城镇警报] : " + 信息));
                            ++破坏勇士部落;
                        }
                        else {
                            活动魔族攻城1.关闭破坏勇士部落(0);
                        }
                    }
                    else {
                        活动魔族攻城1.关闭破坏勇士部落(1);
                    }
                }
            }, (long)(1000 * 活动魔族攻城1.破坏间隔));
        }
    }
    
    public static void 关闭破坏勇士部落(final int a) {
        if (活动魔族攻城1.勇士部落线程 != null) {
            活动魔族攻城1.勇士部落线程.cancel(true);
            活动魔族攻城1.勇士部落线程 = null;
            if (a == 1) {
                清怪(102000000);
                final String 信息 = "攻击停止了，勇士部落繁荣度大大下降了";
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : [城镇警报] : " + 信息);
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[城镇警报] : " + 信息));
            }
            else {
                final String 信息 = "虽然勇士部落被破坏了，但还是挽救回来了一些";
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : [城镇警报] : " + 信息);
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[城镇警报] : " + 信息));
            }
        }
    }
    
    public static void 勇士部落防御成功() {
        活动魔族攻城1.勇士部落防御成功 = 0;
        final String 信息 = "勇士们成功防消灭了进攻勇士部落的魔族，恭喜你们了。";
        System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : [城镇通知] : " + 信息);
        Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[城镇通知] : " + 信息));
    }
    
    public static void 破坏魔法密林() {
        if (活动魔族攻城1.魔法密林线程 == null) {
            活动魔族攻城1.魔法密林线程 = BuffTimer.getInstance().register((Runnable)new Runnable() {
                @Override
                public void run() {
                    final ChannelServer channelServer = ChannelServer.getInstance(2);
                    final MapleMap mapleMap = channelServer.getMapFactory().getMap(103000000);
                    if (破坏魔法密林 < 10) {
                        if (mapleMap.getMonsterById(蝙蝠魔) != null) {
                            final String 信息 = "魔法密林正在被破坏，魔法密林正在被破坏，被破坏····";
                            System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [城镇警报] : " + 信息);
                            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[城镇警报] : " + 信息));
                            ++破坏魔法密林;
                        }
                        else {
                            活动魔族攻城1.关闭破坏魔法密林(0);
                        }
                    }
                    else {
                        活动魔族攻城1.关闭破坏魔法密林(1);
                    }
                }
            }, (long)(1000 * 活动魔族攻城1.破坏间隔));
        }
    }
    
    public static void 关闭破坏魔法密林(final int a) {
        if (活动魔族攻城1.魔法密林线程 != null) {
            活动魔族攻城1.魔法密林线程.cancel(true);
            活动魔族攻城1.魔法密林线程 = null;
            if (a == 1) {
                清怪(101000000);
                final String 信息 = "攻击停止了，魔法密林繁荣度大大下降了";
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : [城镇警报] : " + 信息);
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[城镇警报] : " + 信息));
            }
            else {
                final String 信息 = "虽然魔法密林被破坏了，但还是挽救回来了一些";
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " : [城镇警报] : " + 信息);
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[城镇警报] : " + 信息));
            }
        }
    }
    
    public static void 魔法密林防御成功() {
        活动魔族攻城1.魔法密林防御成功 = 0;
        final String 信息 = "勇士们成功防消灭了进攻魔法密林的魔族，恭喜你们了。";
        Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[城镇通知] : " + 信息));
    }
    
    public static void 攻城部队A() {
        final int 时 = Calendar.getInstance().get(11);
        final int 分 = Calendar.getInstance().get(12);
        if (时 == 21 && 分 == 10 && 活动魔族攻城1.A == 0) {
            MapleParty.蝙蝠魔A部队 = 活动魔族攻城1.每波数量 * 10;
            final int 路 = 1;
            for (int i = 0; i <= 活动魔族攻城1.每波数量; ++i) {
                攻城部队A(1);
            }
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(30000L);
                        for (int i = 0; i <= 每波数量; ++i) {
                            活动魔族攻城1.攻城部队A(1);
                        }
                    }
                    catch (InterruptedException ex) {}
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(60000L);
                        for (int i = 0; i <= 每波数量; ++i) {
                            活动魔族攻城1.攻城部队A(1);
                        }
                    }
                    catch (InterruptedException ex) {}
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(90000L);
                        for (int i = 0; i <= 每波数量; ++i) {
                            活动魔族攻城1.攻城部队A(1);
                        }
                    }
                    catch (InterruptedException ex) {}
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(120000L);
                        for (int i = 0; i <= 每波数量; ++i) {
                            活动魔族攻城1.攻城部队A(1);
                        }
                    }
                    catch (InterruptedException ex) {}
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(150000L);
                        for (int i = 0; i <= 每波数量; ++i) {
                            活动魔族攻城1.攻城部队A(1);
                        }
                    }
                    catch (InterruptedException ex) {}
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(180000L);
                        for (int i = 0; i <= 每波数量; ++i) {
                            活动魔族攻城1.攻城部队A(1);
                        }
                    }
                    catch (InterruptedException ex) {}
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(210000L);
                        for (int i = 0; i <= 每波数量; ++i) {
                            活动魔族攻城1.攻城部队A(1);
                        }
                    }
                    catch (InterruptedException ex) {}
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(240000L);
                        for (int i = 0; i <= 每波数量; ++i) {
                            活动魔族攻城1.攻城部队A(1);
                        }
                    }
                    catch (InterruptedException ex) {}
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(270000L);
                        for (int i = 0; i <= 每波数量; ++i) {
                            活动魔族攻城1.攻城部队A(1);
                        }
                    }
                    catch (InterruptedException ex) {}
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(300000L);
                    }
                    catch (InterruptedException ex) {}
                }
            }.start();
        }
    }
    
    public static void 攻城部队B() {
        final int 时 = Calendar.getInstance().get(11);
        final int 分 = Calendar.getInstance().get(12);
        if (时 == 21 && 分 == 10 && 活动魔族攻城1.B == 0) {
            MapleParty.蝙蝠魔B部队 = 活动魔族攻城1.每波数量;
            final int 路 = 1;
            活动魔族攻城1.B队数量 = MapleParty.蝙蝠魔B部队;
            for (int i = 0; i <= 活动魔族攻城1.B队数量 / 4; ++i) {
                攻城部队B(1);
            }
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(60000L);
                        for (int i = 0; i <= B队数量 / 4; ++i) {
                            活动魔族攻城1.攻城部队B(1);
                        }
                    }
                    catch (InterruptedException ex) {}
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(120000L);
                        for (int i = 0; i <= B队数量 / 4; ++i) {
                            活动魔族攻城1.攻城部队B(1);
                        }
                    }
                    catch (InterruptedException ex) {}
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(180000L);
                        for (int i = 0; i <= B队数量 / 4; ++i) {
                            活动魔族攻城1.攻城部队B(1);
                        }
                    }
                    catch (InterruptedException ex) {}
                }
            }.start();
            final String 信息 = "(B部队)已经抵达鳄鱼潭二，正在向废弃都市逼近";
            System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : " + 信息);
            System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : 部队兵力 " + MapleParty.蝙蝠魔B部队);
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[魔族攻城] : " + 信息));
            活动魔族攻城1.B = 1;
        }
        else if (时 == 21 && 分 == 15 && 活动魔族攻城1.B == 1) {
            if (MapleParty.蝙蝠魔B部队 == 0) {
                废弃都市防御成功();
            }
            else {
                final int 路 = 2;
                活动魔族攻城1.B队数量 = MapleParty.蝙蝠魔B部队;
                for (int i = 0; i <= 活动魔族攻城1.B队数量 / 4; ++i) {
                    攻城部队B(2);
                }
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(60000L);
                            for (int i = 0; i <= B队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队B(2);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(120000L);
                            for (int i = 0; i <= B队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队B(2);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(180000L);
                            for (int i = 0; i <= B队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队B(2);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                final String 信息 = "(B部队)已经抵达鳄鱼潭一，正在向废弃都市逼近";
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : " + 信息);
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : 部队兵力 " + MapleParty.蝙蝠魔B部队);
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[魔族攻城] : " + 信息));
                活动魔族攻城1.B = 2;
                清怪(107000400);
            }
        }
        else if (时 == 21 && 分 == 20 && 活动魔族攻城1.B == 2) {
            if (MapleParty.蝙蝠魔B部队 == 0) {
                废弃都市防御成功();
            }
            else {
                final int 路 = 3;
                活动魔族攻城1.B队数量 = MapleParty.蝙蝠魔B部队;
                for (int i = 0; i <= 活动魔族攻城1.B队数量 / 4; ++i) {
                    攻城部队B(3);
                }
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(60000L);
                            for (int i = 0; i <= B队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队B(3);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(120000L);
                            for (int i = 0; i <= B队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队B(3);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(180000L);
                            for (int i = 0; i <= B队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队B(3);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                final String 信息 = "(B部队)已经抵达沼泽地III，正在向废弃都市逼近";
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : " + 信息);
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : 部队兵力 " + MapleParty.蝙蝠魔B部队);
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[魔族攻城] : " + 信息));
                活动魔族攻城1.B = 3;
                清怪(107000300);
            }
        }
        else if (时 == 21 && 分 == 25 && 活动魔族攻城1.B == 3) {
            if (MapleParty.蝙蝠魔B部队 == 0) {
                废弃都市防御成功();
            }
            else {
                final int 路 = 4;
                活动魔族攻城1.B队数量 = MapleParty.蝙蝠魔B部队;
                for (int i = 0; i <= 活动魔族攻城1.B队数量 / 4; ++i) {
                    攻城部队B(4);
                }
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(60000L);
                            for (int i = 0; i <= B队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队B(4);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(120000L);
                            for (int i = 0; i <= B队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队B(4);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(180000L);
                            for (int i = 0; i <= B队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队B(4);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                final String 信息 = "(B部队)已经抵达沼泽地II，正在向废弃都市逼近";
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : " + 信息);
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : 部队兵力 " + MapleParty.蝙蝠魔B部队);
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[魔族攻城] : " + 信息));
                活动魔族攻城1.B = 4;
                清怪(107000200);
            }
        }
        else if (时 == 21 && 分 == 30 && 活动魔族攻城1.B == 4) {
            if (MapleParty.蝙蝠魔B部队 == 0) {
                废弃都市防御成功();
            }
            else {
                final int 路 = 5;
                活动魔族攻城1.B队数量 = MapleParty.蝙蝠魔B部队;
                for (int i = 0; i <= 活动魔族攻城1.B队数量 / 4; ++i) {
                    攻城部队B(5);
                }
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(60000L);
                            for (int i = 0; i <= B队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队B(5);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(120000L);
                            for (int i = 0; i <= B队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队B(5);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(180000L);
                            for (int i = 0; i <= B队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队B(5);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                final String 信息 = "(B部队)已经抵达沼泽地I，正在向废弃都市逼近";
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : " + 信息);
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : 部队兵力 " + MapleParty.蝙蝠魔B部队);
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[魔族攻城] : " + 信息));
                活动魔族攻城1.B = 5;
                清怪(107000100);
            }
        }
        else if (时 == 21 && 分 == 35 && 活动魔族攻城1.B == 5) {
            if (MapleParty.蝙蝠魔B部队 == 0) {
                废弃都市防御成功();
            }
            else {
                final int 路 = 6;
                活动魔族攻城1.B队数量 = MapleParty.蝙蝠魔B部队;
                for (int i = 0; i <= 活动魔族攻城1.B队数量 / 4; ++i) {
                    攻城部队B(6);
                }
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(60000L);
                            for (int i = 0; i <= B队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队B(6);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(120000L);
                            for (int i = 0; i <= B队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队B(6);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(180000L);
                            for (int i = 0; i <= B队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队B(6);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                final String 信息 = "(B部队)已经抵达废弃都市，正在破坏废弃都市";
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : " + 信息);
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : 部队兵力 " + MapleParty.蝙蝠魔B部队);
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[魔族攻城] : " + 信息));
                活动魔族攻城1.B = 6;
                清怪(107000000);
                破坏废弃都市();
            }
        }
    }
    
    public static void 攻城部队C() {
        final int 时 = Calendar.getInstance().get(11);
        final int 分 = Calendar.getInstance().get(12);
        if (时 == 21 && 分 == 10 && 活动魔族攻城1.C == 0) {
            MapleParty.蝙蝠魔C部队 = 活动魔族攻城1.每波数量;
            final int 路 = 1;
            活动魔族攻城1.C队数量 = MapleParty.蝙蝠魔C部队;
            for (int i = 0; i <= 活动魔族攻城1.C队数量 / 4; ++i) {
                攻城部队C(1);
            }
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(60000L);
                        for (int i = 0; i <= C队数量 / 4; ++i) {
                            活动魔族攻城1.攻城部队C(1);
                        }
                    }
                    catch (InterruptedException ex) {}
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(120000L);
                        for (int i = 0; i <= C队数量 / 4; ++i) {
                            活动魔族攻城1.攻城部队C(1);
                        }
                    }
                    catch (InterruptedException ex) {}
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(180000L);
                        for (int i = 0; i <= C队数量 / 4; ++i) {
                            活动魔族攻城1.攻城部队C(1);
                        }
                    }
                    catch (InterruptedException ex) {}
                }
            }.start();
            final String 信息 = "(C部队)已经抵达大木林上层，正在向魔法密林逼近";
            System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : " + 信息);
            System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : 部队兵力 " + MapleParty.蝙蝠魔C部队);
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[魔族攻城] : " + 信息));
            活动魔族攻城1.C = 1;
        }
        else if (时 == 21 && 分 == 15 && 活动魔族攻城1.C == 1) {
            if (MapleParty.蝙蝠魔C部队 == 0) {
                魔法密林防御成功();
            }
            else {
                final int 路 = 2;
                活动魔族攻城1.C队数量 = MapleParty.蝙蝠魔C部队;
                for (int i = 0; i <= 活动魔族攻城1.C队数量 / 4; ++i) {
                    攻城部队C(2);
                }
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(60000L);
                            for (int i = 0; i <= C队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队C(2);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(120000L);
                            for (int i = 0; i <= C队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队C(2);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(180000L);
                            for (int i = 0; i <= C队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队C(2);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                final String 信息 = "(C部队)已经抵达大木林Ⅲ，正在向魔法密林逼近";
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : " + 信息);
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : 部队兵力 " + MapleParty.蝙蝠魔C部队);
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[魔族攻城] : " + 信息));
                活动魔族攻城1.C = 2;
                清怪(101010103);
            }
        }
        else if (时 == 21 && 分 == 20 && 活动魔族攻城1.C == 2) {
            if (MapleParty.蝙蝠魔C部队 == 0) {
                魔法密林防御成功();
            }
            else {
                final int 路 = 3;
                活动魔族攻城1.C队数量 = MapleParty.蝙蝠魔C部队;
                for (int i = 0; i <= 活动魔族攻城1.C队数量 / 4; ++i) {
                    攻城部队C(3);
                }
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(60000L);
                            for (int i = 0; i <= C队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队C(3);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(120000L);
                            for (int i = 0; i <= C队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队C(3);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(180000L);
                            for (int i = 0; i <= C队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队C(3);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                final String 信息 = "(C部队)已经抵达大木林Ⅱ，正在向魔法密林逼近";
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : " + 信息);
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : 部队兵力 " + MapleParty.蝙蝠魔C部队);
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[魔族攻城] : " + 信息));
                活动魔族攻城1.C = 3;
                清怪(101010102);
            }
        }
        else if (时 == 21 && 分 == 25 && 活动魔族攻城1.C == 3) {
            if (MapleParty.蝙蝠魔C部队 == 0) {
                魔法密林防御成功();
            }
            else {
                final int 路 = 4;
                活动魔族攻城1.C队数量 = MapleParty.蝙蝠魔C部队;
                for (int i = 0; i <= 活动魔族攻城1.C队数量 / 4; ++i) {
                    攻城部队C(4);
                }
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(60000L);
                            for (int i = 0; i <= C队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队C(4);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(120000L);
                            for (int i = 0; i <= C队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队C(4);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(180000L);
                            for (int i = 0; i <= C队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队C(4);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                final String 信息 = "(C部队)已经抵达大木林I，正在向魔法密林逼近";
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : " + 信息);
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : 部队兵力 " + MapleParty.蝙蝠魔C部队);
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[魔族攻城] : " + 信息));
                活动魔族攻城1.C = 4;
                清怪(101010101);
            }
        }
        else if (时 == 21 && 分 == 30 && 活动魔族攻城1.C == 4) {
            if (MapleParty.蝙蝠魔C部队 == 0) {
                魔法密林防御成功();
            }
            else {
                final int 路 = 5;
                活动魔族攻城1.C队数量 = MapleParty.蝙蝠魔C部队;
                for (int i = 0; i <= 活动魔族攻城1.C队数量 / 4; ++i) {
                    攻城部队C(5);
                }
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(60000L);
                            for (int i = 0; i <= C队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队C(5);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(120000L);
                            for (int i = 0; i <= C队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队C(5);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(180000L);
                            for (int i = 0; i <= C队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队C(5);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                final String 信息 = "(C部队)已经抵达魔法密林北郊，正在向魔法密林逼近";
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : " + 信息);
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : 部队兵力 " + MapleParty.蝙蝠魔C部队);
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[魔族攻城] : " + 信息));
                活动魔族攻城1.C = 5;
                清怪(101010100);
            }
        }
        else if (时 == 21 && 分 == 35 && 活动魔族攻城1.C == 5) {
            if (MapleParty.蝙蝠魔C部队 == 0) {
                魔法密林防御成功();
            }
            else {
                final int 路 = 6;
                活动魔族攻城1.C队数量 = MapleParty.蝙蝠魔C部队;
                for (int i = 0; i <= 活动魔族攻城1.C队数量 / 4; ++i) {
                    攻城部队C(6);
                }
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(60000L);
                            for (int i = 0; i <= C队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队C(6);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(120000L);
                            for (int i = 0; i <= C队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队C(6);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(180000L);
                            for (int i = 0; i <= C队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队C(6);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                final String 信息 = "(C部队)已经抵达魔法密林，正在破坏魔法密林";
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : " + 信息);
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : 部队兵力 " + MapleParty.蝙蝠魔C部队);
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[魔族攻城] : " + 信息));
                活动魔族攻城1.C = 6;
                清怪(101010000);
                破坏魔法密林();
            }
        }
    }
    
    public static void 攻城部队D() {
        final int 时 = Calendar.getInstance().get(11);
        final int 分 = Calendar.getInstance().get(12);
        if (时 == 21 && 分 == 10 && 活动魔族攻城1.D == 0) {
            MapleParty.蝙蝠魔D部队 = 活动魔族攻城1.每波数量;
            final int 路 = 1;
            活动魔族攻城1.D队数量 = MapleParty.蝙蝠魔D部队;
            for (int i = 0; i <= 活动魔族攻城1.D队数量 / 4; ++i) {
                攻城部队B(1);
            }
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(60000L);
                        for (int i = 0; i <= D队数量 / 4; ++i) {
                            活动魔族攻城1.攻城部队D(1);
                        }
                    }
                    catch (InterruptedException ex) {}
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(120000L);
                        for (int i = 0; i <= D队数量 / 4; ++i) {
                            活动魔族攻城1.攻城部队D(1);
                        }
                    }
                    catch (InterruptedException ex) {}
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(180000L);
                        for (int i = 0; i <= B队数量 / 4; ++i) {
                            活动魔族攻城1.攻城部队D(1);
                        }
                    }
                    catch (InterruptedException ex) {}
                }
            }.start();
            final String 信息 = "(D部队)已经抵达勇士部落迷宫入口，正在向勇士部落逼近";
            System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : " + 信息);
            System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : 部队兵力 " + MapleParty.蝙蝠魔D部队);
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[魔族攻城] : " + 信息));
            活动魔族攻城1.D = 1;
        }
        else if (时 == 21 && 分 == 15 && 活动魔族攻城1.D == 1) {
            if (MapleParty.蝙蝠魔D部队 == 0) {
                勇士部落防御成功();
            }
            else {
                final int 路 = 2;
                活动魔族攻城1.D队数量 = MapleParty.蝙蝠魔D部队;
                for (int i = 0; i <= 活动魔族攻城1.D队数量 / 4; ++i) {
                    攻城部队B(2);
                }
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(60000L);
                            for (int i = 0; i <= D队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队D(2);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(120000L);
                            for (int i = 0; i <= D队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队D(2);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(180000L);
                            for (int i = 0; i <= B队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队D(2);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                final String 信息 = "(D部队)已经抵达幽深峡谷Ⅲ，正在向勇士部落逼近";
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : " + 信息);
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : 部队兵力 " + MapleParty.蝙蝠魔D部队);
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[魔族攻城] : " + 信息));
                活动魔族攻城1.D = 2;
                清怪(106000300);
            }
        }
        else if (时 == 21 && 分 == 20 && 活动魔族攻城1.D == 2) {
            if (MapleParty.蝙蝠魔D部队 == 0) {
                勇士部落防御成功();
            }
            else {
                final int 路 = 3;
                活动魔族攻城1.D队数量 = MapleParty.蝙蝠魔D部队;
                for (int i = 0; i <= 活动魔族攻城1.D队数量 / 4; ++i) {
                    攻城部队B(3);
                }
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(60000L);
                            for (int i = 0; i <= D队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队D(3);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(120000L);
                            for (int i = 0; i <= D队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队D(3);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(180000L);
                            for (int i = 0; i <= B队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队D(3);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                final String 信息 = "(D部队)已经抵达幽深峡谷Ⅱ，正在向勇士部落逼近";
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : " + 信息);
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : 部队兵力 " + MapleParty.蝙蝠魔D部队);
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[魔族攻城] : " + 信息));
                活动魔族攻城1.D = 3;
                清怪(106000200);
            }
        }
        else if (时 == 21 && 分 == 25 && 活动魔族攻城1.D == 3) {
            if (MapleParty.蝙蝠魔D部队 == 0) {
                勇士部落防御成功();
            }
            else {
                final int 路 = 4;
                活动魔族攻城1.D队数量 = MapleParty.蝙蝠魔D部队;
                for (int i = 0; i <= 活动魔族攻城1.D队数量 / 4; ++i) {
                    攻城部队B(4);
                }
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(60000L);
                            for (int i = 0; i <= D队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队D(4);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(120000L);
                            for (int i = 0; i <= D队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队D(4);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(180000L);
                            for (int i = 0; i <= B队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队D(4);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                final String 信息 = "(D部队)已经抵达幽深峡谷Ⅰ，正在向勇士部落逼近";
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : " + 信息);
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : 部队兵力 " + MapleParty.蝙蝠魔D部队);
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[魔族攻城] : " + 信息));
                活动魔族攻城1.D = 4;
                清怪(106000100);
            }
        }
        else if (时 == 21 && 分 == 30 && 活动魔族攻城1.D == 4) {
            if (MapleParty.蝙蝠魔D部队 == 0) {
                勇士部落防御成功();
            }
            else {
                final int 路 = 5;
                活动魔族攻城1.D队数量 = MapleParty.蝙蝠魔D部队;
                for (int i = 0; i <= 活动魔族攻城1.D队数量 / 4; ++i) {
                    攻城部队B(5);
                }
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(60000L);
                            for (int i = 0; i <= D队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队D(5);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(120000L);
                            for (int i = 0; i <= D队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队D(5);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(180000L);
                            for (int i = 0; i <= B队数量 / 4; ++i) {
                                活动魔族攻城1.攻城部队D(5);
                            }
                        }
                        catch (InterruptedException ex) {}
                    }
                }.start();
                final String 信息 = "(D部队)已经抵达勇士部落，正在破坏勇士部落";
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : " + 信息);
                System.err.println("[服务端]" + FileoutputUtil.CurrentReadable_Time() + " [魔族攻城] : 部队兵力 " + MapleParty.蝙蝠魔D部队);
                Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[魔族攻城] : " + 信息));
                活动魔族攻城1.D = 5;
                清怪(106000000);
                破坏勇士部落();
            }
        }
    }
    
    public static void 攻城部队D(final int a) {
        final ChannelServer channelServer = ChannelServer.getInstance(2);
        final MapleMonster mapleMonster = MapleLifeFactory.getMonster(活动魔族攻城1.蝙蝠魔);
        switch (a) {
            case 1: {
                final int 地图 = 106000300;
                final MapleMap mapleMap = channelServer.getMapFactory().getMap(地图);
                mapleMonster.setPosition(活动魔族攻城1.D1坐标);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            case 2: {
                final int 地图 = 106000200;
                final MapleMap mapleMap = channelServer.getMapFactory().getMap(地图);
                mapleMonster.setPosition(活动魔族攻城1.D2坐标);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            case 3: {
                final int 地图 = 106000100;
                final MapleMap mapleMap = channelServer.getMapFactory().getMap(地图);
                mapleMonster.setPosition(活动魔族攻城1.D3坐标);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            case 4: {
                final int 地图 = 106000000;
                final MapleMap mapleMap = channelServer.getMapFactory().getMap(地图);
                mapleMonster.setPosition(活动魔族攻城1.D4坐标);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            case 5: {
                final int 地图 = 102000000;
                final MapleMap mapleMap = channelServer.getMapFactory().getMap(地图);
                mapleMonster.setPosition(活动魔族攻城1.D5坐标);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
        }
    }
    
    public static void 攻城部队C(final int a) {
        final ChannelServer channelServer = ChannelServer.getInstance(2);
        final MapleMonster mapleMonster = MapleLifeFactory.getMonster(活动魔族攻城1.蝙蝠魔);
        switch (a) {
            case 1: {
                final int 地图 = 101010103;
                final MapleMap mapleMap = channelServer.getMapFactory().getMap(地图);
                mapleMonster.setPosition(活动魔族攻城1.C1坐标);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            case 2: {
                final int 地图 = 101010102;
                final MapleMap mapleMap = channelServer.getMapFactory().getMap(地图);
                mapleMonster.setPosition(活动魔族攻城1.C2坐标);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            case 3: {
                final int 地图 = 101010101;
                final MapleMap mapleMap = channelServer.getMapFactory().getMap(地图);
                mapleMonster.setPosition(活动魔族攻城1.C3坐标);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            case 4: {
                final int 地图 = 101010100;
                final MapleMap mapleMap = channelServer.getMapFactory().getMap(地图);
                mapleMonster.setPosition(活动魔族攻城1.C4坐标);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            case 5: {
                final int 地图 = 101010000;
                final MapleMap mapleMap = channelServer.getMapFactory().getMap(地图);
                mapleMonster.setPosition(活动魔族攻城1.C5坐标);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            case 6: {
                final int 地图 = 101000000;
                final MapleMap mapleMap = channelServer.getMapFactory().getMap(地图);
                mapleMonster.setPosition(活动魔族攻城1.C6坐标);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
        }
    }
    
    public static void 攻城部队B(final int a) {
        final ChannelServer channelServer = ChannelServer.getInstance(2);
        final MapleMonster mapleMonster = MapleLifeFactory.getMonster(活动魔族攻城1.蝙蝠魔);
        switch (a) {
            case 1: {
                final int 地图 = 107000400;
                final MapleMap mapleMap = channelServer.getMapFactory().getMap(地图);
                mapleMonster.setPosition(活动魔族攻城1.B1坐标);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            case 2: {
                final int 地图 = 107000300;
                final MapleMap mapleMap = channelServer.getMapFactory().getMap(地图);
                mapleMonster.setPosition(活动魔族攻城1.B2坐标);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            case 3: {
                final int 地图 = 107000200;
                final MapleMap mapleMap = channelServer.getMapFactory().getMap(地图);
                mapleMonster.setPosition(活动魔族攻城1.B3坐标);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            case 4: {
                final int 地图 = 107000100;
                final MapleMap mapleMap = channelServer.getMapFactory().getMap(地图);
                mapleMonster.setPosition(活动魔族攻城1.B4坐标);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            case 5: {
                final int 地图 = 107000000;
                final MapleMap mapleMap = channelServer.getMapFactory().getMap(地图);
                mapleMonster.setPosition(活动魔族攻城1.B5坐标);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            case 6: {
                final int 地图 = 103000000;
                final MapleMap mapleMap = channelServer.getMapFactory().getMap(地图);
                mapleMonster.setPosition(活动魔族攻城1.B6坐标);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
        }
    }
    
    public static void 攻城部队A(final int a) {
        final ChannelServer channelServer = ChannelServer.getInstance(2);
        final MapleMonster mapleMonster = MapleLifeFactory.getMonster(活动魔族攻城1.蝙蝠魔);
        switch (a) {
            case 1: {
                final int 地图 = 106010100;
                final MapleMap mapleMap = channelServer.getMapFactory().getMap(地图);
                mapleMonster.setPosition(活动魔族攻城1.A1坐标);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            case 2: {
                final int 地图 = 106010000;
                final MapleMap mapleMap = channelServer.getMapFactory().getMap(地图);
                mapleMonster.setPosition(活动魔族攻城1.A2坐标);
                mapleMap.spawnMonster(mapleMonster, -2);
                break;
            }
            case 3: {
                final int 地图 = 100000000;
                final MapleMap mapleMap = channelServer.getMapFactory().getMap(地图);
                mapleMonster.setPosition(活动魔族攻城1.A3坐标);
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
    
    static {
        活动魔族攻城1.蝙蝠魔 = 8150000;
        活动魔族攻城1.每波数量 = 40;
        活动魔族攻城1.魔族攻城线程 = null;
        活动魔族攻城1.魔族攻城 = 0;
        活动魔族攻城1.射手村线程 = null;
        活动魔族攻城1.破坏射手村 = 0;
        活动魔族攻城1.勇士部落线程 = null;
        活动魔族攻城1.破坏勇士部落 = 0;
        活动魔族攻城1.魔法密林线程 = null;
        活动魔族攻城1.破坏魔法密林 = 0;
        活动魔族攻城1.废弃都市线程 = null;
        活动魔族攻城1.破坏废弃都市 = 0;
        活动魔族攻城1.A = 0;
        活动魔族攻城1.B = 0;
        活动魔族攻城1.C = 0;
        活动魔族攻城1.D = 0;
        活动魔族攻城1.破坏间隔 = 30;
        活动魔族攻城1.A1坐标 = new Point(3114, 125);
        活动魔族攻城1.A2坐标 = new Point(2835, 275);
        活动魔族攻城1.A3坐标 = new Point(6084, -176);
        活动魔族攻城1.B1坐标 = new Point(1597, 101);
        活动魔族攻城1.B2坐标 = new Point(1855, 124);
        活动魔族攻城1.B3坐标 = new Point(1669, 124);
        活动魔族攻城1.B4坐标 = new Point(1666, 125);
        活动魔族攻城1.B5坐标 = new Point(1320, 121);
        活动魔族攻城1.B6坐标 = new Point(-849, 373);
        活动魔族攻城1.C1坐标 = new Point(213, -48);
        活动魔族攻城1.C2坐标 = new Point(-1344, 23);
        活动魔族攻城1.C3坐标 = new Point(-359, 24);
        活动魔族攻城1.C4坐标 = new Point(-357, 825);
        活动魔族攻城1.C5坐标 = new Point(-1642, 1560);
        活动魔族攻城1.C6坐标 = new Point(-1091, -2806);
        活动魔族攻城1.D1坐标 = new Point(-415, 2025);
        活动魔族攻城1.D2坐标 = new Point(1372, 2145);
        活动魔族攻城1.D3坐标 = new Point(-437, 2025);
        活动魔族攻城1.D4坐标 = new Point(758, 2025);
        活动魔族攻城1.D5坐标 = new Point(2824, 1935);
        活动魔族攻城1.射手村防御成功 = 0;
        活动魔族攻城1.废弃都市防御成功 = 0;
        活动魔族攻城1.勇士部落防御成功 = 0;
        活动魔族攻城1.魔法密林防御成功 = 0;
        活动魔族攻城1.A队数量 = 0;
        活动魔族攻城1.B队数量 = 0;
        活动魔族攻城1.C队数量 = 0;
        活动魔族攻城1.D队数量 = 0;
    }
}
