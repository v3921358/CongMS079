package gui.进阶BOSS;

import server.maps.MapleMap;
import server.life.MapleMonster;
import server.life.MapleLifeFactory;
import server.life.MobSkill;
import client.MapleDisease;
import server.life.MobSkillFactory;
import server.MapleItemInformationProvider;
import java.util.Iterator;
import handling.world.MapleParty;
import client.MapleCharacter;
import handling.channel.ChannelServer;
import server.Timer.BuffTimer;
import java.awt.Point;
import java.util.concurrent.ScheduledFuture;

public class 进阶BOSS线程
{
    public static ScheduledFuture<?> 进阶BOSS线程;
    public static ScheduledFuture<?> 进阶BOSS线程伤害;
    public static ScheduledFuture<?> 全图掉HP;
    public static ScheduledFuture<?> 全图掉MP;
    public static ScheduledFuture<?> 全图封锁;
    public static int 进阶红蜗牛长老;
    public static int 飞鱼;
    public static int 地图;
    public static int 频道;
    public static Point 坐标;
    
    public static void 开启进阶BOSS线程() {
        召唤怪物();
        开启进阶BOSS线程伤害();
        if (gui.进阶BOSS.进阶BOSS线程.进阶BOSS线程 == null) {
            gui.进阶BOSS.进阶BOSS线程.进阶BOSS线程 = BuffTimer.getInstance().register((Runnable)new Runnable() {
                @Override
                public void run() {
                    final double 随机 = Math.ceil(Math.random() * 20.0);
                    if (随机 <= 0.0) {
                        gui.进阶BOSS.进阶BOSS线程.全图掉HP();
                    }
                    else if (随机 == 1.0) {
                        gui.进阶BOSS.进阶BOSS线程.全图掉MP();
                    }
                    else if (随机 == 2.0) {
                        gui.进阶BOSS.进阶BOSS线程.全图封锁();
                    }
                    else if (随机 == 3.0) {
                        gui.进阶BOSS.进阶BOSS线程.全图黑暗();
                    }
                    else if (随机 == 4.0) {
                        gui.进阶BOSS.进阶BOSS线程.全图虚弱();
                    }
                    else if (随机 == 5.0) {
                        gui.进阶BOSS.进阶BOSS线程.全图诅咒();
                    }
                    else if (随机 == 6.0) {
                        gui.进阶BOSS.进阶BOSS线程.全图诱导();
                        gui.进阶BOSS.进阶BOSS线程.减少血量();
                    }
                    else if (随机 == 7.0) {
                        gui.进阶BOSS.进阶BOSS线程.全图诱导();
                    }
                    else if (随机 == 8.0) {
                        gui.进阶BOSS.进阶BOSS线程.全图诱导();
                    }
                    else if (随机 == 9.0) {
                        gui.进阶BOSS.进阶BOSS线程.全图掉HP();
                        gui.进阶BOSS.进阶BOSS线程.全图诱导();
                    }
                    else if (随机 == 10.0 || 随机 == 11.0 || 随机 == 12.0 || 随机 == 13.0) {
                        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                            for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                                if (chr == null) {
                                    continue;
                                }
                                if (chr.getMapId() != 地图 || chr.getClient().getChannel() != 频道) {
                                    continue;
                                }
                                chr.startMapEffect("【进阶BOSS】 : 红蜗牛王使用出黑暗魔法，3秒后将吞噬在场所有人。", 5120027);
                            }
                        }
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(3000L);
                                    gui.进阶BOSS.进阶BOSS线程.直接死亡();
                                }
                                catch (InterruptedException ex) {}
                            }
                        }.start();
                    }
                    else if (随机 == 14.0 || 随机 == 15.0 || 随机 == 16.0 || 随机 == 17.0) {
                        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                            for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                                if (chr == null) {
                                    continue;
                                }
                                if (chr.getMapId() != 地图 || chr.getClient().getChannel() != 频道) {
                                    continue;
                                }
                                chr.startMapEffect("【进阶BOSS】 : 红蜗牛王使用出黑暗魔法，3秒后将驱散在场所有人。", 5120027);
                            }
                        }
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(3000L);
                                    gui.进阶BOSS.进阶BOSS线程.直接驱散();
                                }
                                catch (InterruptedException ex) {}
                            }
                        }.start();
                    }
                    else {
                        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                            for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                                if (chr == null) {
                                    continue;
                                }
                                if (chr.getMapId() != 地图 || chr.getClient().getChannel() != 频道) {
                                    continue;
                                }
                                chr.startMapEffect("【进阶BOSS】 : 红蜗牛王使用出黑暗魔法，诱导玩家可以互相攻击。", 5120027);
                            }
                        }
                        ++MapleParty.互相伤害;
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(10000L);
                                    MapleParty.互相伤害 = 0;
                                }
                                catch (InterruptedException ex) {}
                            }
                        }.start();
                    }
                }
            }, 40000L);
        }
    }
    
    public static void 关闭进阶BOSS线程() {
        if (gui.进阶BOSS.进阶BOSS线程.进阶BOSS线程 != null) {
            关闭进阶BOSS线程伤害();
            gui.进阶BOSS.进阶BOSS线程.进阶BOSS线程.cancel(false);
            gui.进阶BOSS.进阶BOSS线程.进阶BOSS线程 = null;
        }
    }
    
    public static void 开启进阶BOSS线程伤害() {
        if (gui.进阶BOSS.进阶BOSS线程.进阶BOSS线程伤害 == null) {
            gui.进阶BOSS.进阶BOSS线程.进阶BOSS线程伤害 = BuffTimer.getInstance().register((Runnable)new Runnable() {
                @Override
                public void run() {
                    final int 随机 = (int)Math.ceil(Math.random() * 2.0);
                    switch (随机) {
                        case 0: {
                            gui.进阶BOSS.进阶BOSS线程.减少血量();
                            break;
                        }
                        case 1: {
                            gui.进阶BOSS.进阶BOSS线程.减少蓝量();
                            break;
                        }
                        default: {
                            gui.进阶BOSS.进阶BOSS线程.减少血量();
                            gui.进阶BOSS.进阶BOSS线程.减少蓝量();
                            break;
                        }
                    }
                }
            }, 4000L);
        }
    }
    
    public static void 关闭进阶BOSS线程伤害() {
        if (gui.进阶BOSS.进阶BOSS线程.进阶BOSS线程伤害 != null) {
            gui.进阶BOSS.进阶BOSS线程.进阶BOSS线程伤害.cancel(false);
            gui.进阶BOSS.进阶BOSS线程.进阶BOSS线程伤害 = null;
        }
    }
    
    public static void 直接驱散() {
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() != gui.进阶BOSS.进阶BOSS线程.地图 || chr.getClient().getChannel() != gui.进阶BOSS.进阶BOSS线程.频道) {
                    continue;
                }
                final double y = chr.getPosition().getY();
                if (y == -355.0 || y == -85.0 || y == 185.0 || y == 455.0 || y == 395.0 || y == 335.0 || y == 515.0) {
                    MapleItemInformationProvider.getInstance().getItemEffect(2030000).applyReturnScroll(chr);
                    chr.dropMessage(5, "直接驱散");
                }
                else {
                    chr.dropMessage(5, "躲避了直接驱散");
                }
            }
        }
    }
    
    public static void 直接死亡() {
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() != gui.进阶BOSS.进阶BOSS线程.地图 || chr.getClient().getChannel() != gui.进阶BOSS.进阶BOSS线程.频道) {
                    continue;
                }
                final double y = chr.getPosition().getY();
                if (y == -355.0 || y == -85.0 || y == 185.0 || y == 455.0 || y == 395.0 || y == 335.0 || y == 515.0) {
                    chr.addHP(-30000);
                    chr.dropMessage(5, "直接死亡 HP - 999999999");
                }
                else {
                    chr.dropMessage(5, "躲避了直接死亡");
                }
            }
        }
    }
    
    public static void 减少血量() {
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() != gui.进阶BOSS.进阶BOSS线程.地图 || chr.getClient().getChannel() != gui.进阶BOSS.进阶BOSS线程.频道) {
                    continue;
                }
                final double y = chr.getPosition().getY();
                if (y == -355.0 || y == -85.0 || y == 185.0 || y == 455.0 || y == 395.0 || y == 335.0 || y == 515.0) {
                    int 血量 = (int)Math.ceil(Math.random() * 30000.0);
                    if (chr.getEquippedFuMoMap().get((Object)Integer.valueOf(21)) != null) {
                        final long 附魔减伤 = (long)(血量 / 100 * (int)Integer.valueOf(chr.getEquippedFuMoMap().get((Object)Integer.valueOf(21))));
                        血量 = (int)((long)血量 - 附魔减伤);
                    }
                    chr.addHP(-血量);
                    chr.dropMessage(5, "HP - " + 血量);
                }
                else {
                    int 血量 = (int)Math.ceil(Math.random() * 30000.0);
                    if (chr.getEquippedFuMoMap().get((Object)Integer.valueOf(21)) != null) {
                        final long 附魔减伤 = (long)(血量 / 100 * (int)Integer.valueOf(chr.getEquippedFuMoMap().get((Object)Integer.valueOf(21))));
                        血量 = (int)((long)血量 - 附魔减伤);
                    }
                    chr.addHP(-血量 / 2);
                    chr.dropMessage(5, "HP - " + 血量 / 2);
                }
            }
        }
    }
    
    public static void 减少蓝量() {
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() != gui.进阶BOSS.进阶BOSS线程.地图 || chr.getClient().getChannel() != gui.进阶BOSS.进阶BOSS线程.频道) {
                    continue;
                }
                final double y = chr.getPosition().getY();
                final int 蓝量 = (int)Math.ceil(Math.random() * 30000.0);
                if (y == -355.0 || y == -85.0 || y == 185.0 || y == 455.0 || y == 395.0 || y == 335.0 || y == 515.0) {
                    chr.addMP(-蓝量);
                    chr.dropMessage(5, "HP - " + 蓝量);
                }
                else {
                    chr.addMP(-蓝量 / 2);
                    chr.dropMessage(5, "HP - " + 蓝量 / 2);
                }
            }
        }
    }
    
    public static void 全图诱导() {
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() != gui.进阶BOSS.进阶BOSS线程.地图 || chr.getClient().getChannel() != gui.进阶BOSS.进阶BOSS线程.频道) {
                    continue;
                }
                if (chr.getJob() != 230 || chr.getJob() != 231 || chr.getJob() != 232) {
                    final MobSkill mobSkill = MobSkillFactory.getMobSkill(128, 1);
                    MapleDisease disease = null;
                    disease = MapleDisease.getBySkill(128);
                    chr.giveDebuff(disease, mobSkill);
                    chr.dropMessage(5, "被诱导");
                }
                else {
                    chr.dropMessage(5, "主教职业群，免疫被诱导");
                }
            }
        }
    }
    
    public static void 全图诅咒() {
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() != gui.进阶BOSS.进阶BOSS线程.地图 || chr.getClient().getChannel() != gui.进阶BOSS.进阶BOSS线程.频道) {
                    continue;
                }
                final MobSkill mobSkill = MobSkillFactory.getMobSkill(124, 1);
                MapleDisease disease = null;
                disease = MapleDisease.getBySkill(124);
                chr.giveDebuff(disease, mobSkill);
                chr.dropMessage(5, "被诅咒");
            }
        }
    }
    
    public static void 全图虚弱() {
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() != gui.进阶BOSS.进阶BOSS线程.地图 || chr.getClient().getChannel() != gui.进阶BOSS.进阶BOSS线程.频道) {
                    continue;
                }
                final MobSkill mobSkill = MobSkillFactory.getMobSkill(122, 1);
                MapleDisease disease = null;
                disease = MapleDisease.getBySkill(122);
                chr.giveDebuff(disease, mobSkill);
                chr.dropMessage(5, "被虚弱");
            }
        }
    }
    
    public static void 全图黑暗() {
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() != gui.进阶BOSS.进阶BOSS线程.地图 || chr.getClient().getChannel() != gui.进阶BOSS.进阶BOSS线程.频道) {
                    continue;
                }
                final MobSkill mobSkill = MobSkillFactory.getMobSkill(121, 1);
                MapleDisease disease = null;
                disease = MapleDisease.getBySkill(121);
                chr.giveDebuff(disease, mobSkill);
                chr.dropMessage(5, "被黑暗");
            }
        }
    }
    
    public static void 全图封锁() {
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() != gui.进阶BOSS.进阶BOSS线程.地图 || chr.getClient().getChannel() != gui.进阶BOSS.进阶BOSS线程.频道) {
                    continue;
                }
                final MobSkill mobSkill = MobSkillFactory.getMobSkill(120, 1);
                MapleDisease disease = null;
                disease = MapleDisease.getBySkill(120);
                chr.giveDebuff(disease, mobSkill);
                chr.dropMessage(5, "被封锁");
            }
        }
    }
    
    public static void 全图掉HP() {
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() != gui.进阶BOSS.进阶BOSS线程.地图 || chr.getClient().getChannel() != gui.进阶BOSS.进阶BOSS线程.频道) {
                    continue;
                }
                chr.setHp(1);
            }
        }
    }
    
    public static void 全图掉MP() {
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr == null) {
                    continue;
                }
                if (chr.getMapId() != gui.进阶BOSS.进阶BOSS线程.地图 || chr.getClient().getChannel() != gui.进阶BOSS.进阶BOSS线程.频道) {
                    continue;
                }
                chr.setMp(1);
            }
        }
    }
    
    public static void 召唤怪物() {
        final ChannelServer channelServer = ChannelServer.getInstance(gui.进阶BOSS.进阶BOSS线程.频道);
        final MapleMonster mapleMonster = MapleLifeFactory.getMonster(gui.进阶BOSS.进阶BOSS线程.进阶红蜗牛长老);
        final MapleMap mapleMap = channelServer.getMapFactory().getMap(gui.进阶BOSS.进阶BOSS线程.地图);
        mapleMonster.setPosition(gui.进阶BOSS.进阶BOSS线程.坐标);
        mapleMap.spawnMonster(mapleMonster, -2);
    }
    
    static {
        gui.进阶BOSS.进阶BOSS线程.进阶BOSS线程 = null;
        gui.进阶BOSS.进阶BOSS线程.进阶BOSS线程伤害 = null;
        gui.进阶BOSS.进阶BOSS线程.全图掉HP = null;
        gui.进阶BOSS.进阶BOSS线程.全图掉MP = null;
        gui.进阶BOSS.进阶BOSS线程.全图封锁 = null;
        gui.进阶BOSS.进阶BOSS线程.进阶红蜗牛长老 = 9500337;
        gui.进阶BOSS.进阶BOSS线程.飞鱼 = 2230107;
        gui.进阶BOSS.进阶BOSS线程.地图 = 104000400;
        gui.进阶BOSS.进阶BOSS线程.频道 = 2;
        gui.进阶BOSS.进阶BOSS线程.坐标 = new Point(232, 185);
    }
}
