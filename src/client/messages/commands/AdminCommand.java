package client.messages.commands;

import constants.ServerConstants;
import java.util.List;
import java.util.Arrays;
import server.maps.MapleMapObjectType;
import java.awt.Point;
import gui.CongMS;
import abc.Game;
import server.gashapon.GashaponFactory;
import server.MaplePortal;
import tools.HexTool;
import tools.data.MaplePacketLittleEndianWriter;
import constants.PiPiConfig;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import database.DBConPool;
import java.io.IOException;
import java.io.Writer;
import java.io.FileWriter;
import java.io.File;
import tools.CPUSampler;
import server.life.OverrideMonsterStats;
import client.MapleCharacterUtil;
import io.netty.channel.Channel;
import tools.MapleAESOFB;
import tools.MockIOSession;
import server.life.PlayerNPC;
import server.life.MapleNPC;
import server.life.MapleLifeFactory;
import server.maps.MapleMap;
import handling.world.World;
import client.anticheat.CheatingOffense;
import server.life.MobSkillFactory;
import client.MapleDisease;
import client.inventory.ModifyInventory;
import server.events.MapleEventType;
import server.Timer.EventTimer;
import server.events.MapleEvent;
import java.util.concurrent.ScheduledFuture;
import scripting.EventManager;
import server.maps.MapleMapObject;
import tools.packet.MobPacket;
import server.life.MapleMonster;
import tools.StringUtil;
import client.inventory.ItemFlag;
import client.inventory.IItem;
import client.inventory.Item;
import client.inventory.Equip;
import client.inventory.MapleInventoryType;
import server.MapleInventoryManipulator;
import client.inventory.MaplePet;
import client.inventory.MapleInventoryIdentifier;
import constants.GameConstants;
import server.MapleItemInformationProvider;
import client.messages.CommandProcessorUtil;
import handling.world.World.Broadcast;
import tools.MaplePacketCreator;
import client.MapleCharacter;
import client.MapleStat;
import handling.world.World.Find;
import java.util.Iterator;
import handling.channel.ChannelServer;
import tools.FileoutputUtil;
import client.MapleClient;
import constants.ServerConstants.PlayerGMRank;

public class AdminCommand
{
    public static PlayerGMRank getPlayerLevelRequired() {
        return PlayerGMRank.超级管理员;
    }
    
    public static class GC extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            System.gc();
            System.out.println("系统释放记忆体 ---- " + FileoutputUtil.NowTime());
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!GC - 系统释放记忆体").toString();
        }
    }
    
    public static class 清理内存 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            System.gc();
            System.out.println("系统释放记忆体 ---- " + FileoutputUtil.NowTime());
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!清理内存 - 系统释放内存").toString();
        }
    }
    
    public static class SavePlayerShops extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                cserv.closeAllMerchant();
            }
            c.getPlayer().dropMessage(6, "精灵商人储存完毕.");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!savePlayerShops - 储存精灵商人").toString();
        }
    }
    
    public static class Fame extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final MapleCharacter player = c.getPlayer();
            if (splitted.length < 2) {
                return false;
            }
            final String name = splitted[1];
            final int ch = Find.findChannel(name);
            if (ch <= 0) {
                return false;
            }
            final MapleCharacter victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
            short fame;
            try {
                fame = Short.parseShort(splitted[2]);
            }
            catch (Exception nfe) {
                c.getPlayer().dropMessage(6, "不合法的数字");
                return false;
            }
            if (victim != null && player.allowedToTarget(victim)) {
                victim.addFame((int)fame);
                victim.updateSingleStat(MapleStat.FAME, (int)victim.getFame());
            }
            else {
                c.getPlayer().dropMessage(6, "[fame] 角色不存在");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!fame <角色名称> <人气> ...  - 人气").toString();
        }
    }
    
    public static class 给人气 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final MapleCharacter player = c.getPlayer();
            if (splitted.length < 2) {
                return false;
            }
            final String name = splitted[1];
            final int ch = Find.findChannel(name);
            if (ch <= 0) {
                return false;
            }
            final MapleCharacter victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
            short fame;
            try {
                fame = Short.parseShort(splitted[2]);
            }
            catch (Exception nfe) {
                c.getPlayer().dropMessage(6, "不合法的数字");
                return false;
            }
            if (victim != null && player.allowedToTarget(victim)) {
                victim.addFame((int)fame);
                victim.updateSingleStat(MapleStat.FAME, (int)victim.getFame());
            }
            else {
                c.getPlayer().dropMessage(6, "[fame] 角色不存在");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!给人气 <角色名称> <人气数量> ...  - 给指定玩家人气").toString();
        }
    }
    
    public static class 无敌 extends GodMode
    {
        @Override
        public String getMessage() {
            return new StringBuilder().append("!无敌 - 无敌开关").toString();
        }
    }
    
    public static class GodMode extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final MapleCharacter player = c.getPlayer();
            if (player.isInvincible()) {
                player.setInvincible(false);
                player.dropMessage(6, "无敌已经关闭");
            }
            else {
                player.setInvincible(true);
                player.dropMessage(6, "无敌已经开启.");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!godmode  - 无敌开关").toString();
        }
    }
    
    public static class GainCash extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 3) {
                return false;
            }
            int amount = 0;
            String name = "";
            try {
                amount = Integer.parseInt(splitted[1]);
                name = splitted[2];
            }
            catch (Exception ex) {
                return false;
            }
            final int ch = Find.findChannel(name);
            if (ch <= 0) {
                c.getPlayer().dropMessage("该玩家不在线上");
                return true;
            }
            final MapleCharacter player = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
            if (player == null) {
                c.getPlayer().dropMessage("该玩家不在线上");
                return true;
            }
            player.modifyCSPoints(1, amount, true);
            player.dropMessage("已经收到点卷" + amount + "点");
            final String msg = "[GM 密语] GM " + c.getPlayer().getName() + " 给了 " + player.getName() + " 点卷 " + amount + "点";
            FileoutputUtil.logToFile("logs/Data/给予点数.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " GM " + c.getPlayer().getName() + " 给了 " + player.getName() + " Gash点数 " + amount + "点");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!gaingash <数量> <玩家> - 给指定玩家点卷").toString();
        }
    }
    
    public static class 给点卷 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 3) {
                return false;
            }
            int amount = 0;
            String name = "";
            try {
                amount = Integer.parseInt(splitted[1]);
                name = splitted[2];
            }
            catch (Exception ex) {
                return false;
            }
            final int ch = Find.findChannel(name);
            if (ch <= 0) {
                c.getPlayer().dropMessage("该玩家不在线上");
                return true;
            }
            final MapleCharacter player = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
            if (player == null) {
                c.getPlayer().dropMessage("该玩家不在线上");
                return true;
            }
            player.modifyCSPoints(1, amount, true);
            player.dropMessage("已经收到点卷" + amount + "点");
            final String msg = "[GM 密语] GM " + c.getPlayer().getName() + " 给了 " + player.getName() + " 点卷 " + amount + "点";
            FileoutputUtil.logToFile("logs/Data/给予点数.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " GM " + c.getPlayer().getName() + " 给了 " + player.getName() + " Gash点数 " + amount + "点");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!给点卷 <数量> <玩家> - 给指定玩家点卷").toString();
        }
    }
    
    public static class 给抵用 extends GainMaplePoint
    {
        @Override
        public String getMessage() {
            return new StringBuilder().append("!给抵用 <数量> <玩家> - 给玩家抵用卷").toString();
        }
    }
    
    public static class GainMaplePoint extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 3) {
                return false;
            }
            int amount = 0;
            String name = "";
            try {
                amount = Integer.parseInt(splitted[1]);
                name = splitted[2];
            }
            catch (Exception ex) {}
            final int ch = Find.findChannel(name);
            if (ch <= 0) {
                c.getPlayer().dropMessage("该玩家不在线上");
                return true;
            }
            final MapleCharacter player = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
            if (player == null) {
                c.getPlayer().dropMessage("该玩家不在线上");
                return true;
            }
            player.modifyCSPoints(2, amount, true);
            final String msg = "[GM 密语] GM " + c.getPlayer().getName() + " 给了 " + player.getName() + " 抵用卷 " + amount + "点";
            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, msg));
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!gainmaplepoint <数量> <玩家> - 给指定玩家抵用卷").toString();
        }
    }
    
    public static class 给抵用卷 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 3) {
                return false;
            }
            int amount = 0;
            String name = "";
            try {
                amount = Integer.parseInt(splitted[1]);
                name = splitted[2];
            }
            catch (Exception ex) {}
            final int ch = Find.findChannel(name);
            if (ch <= 0) {
                c.getPlayer().dropMessage("该玩家不在线上");
                return true;
            }
            final MapleCharacter player = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
            if (player == null) {
                c.getPlayer().dropMessage("该玩家不在线上");
                return true;
            }
            player.modifyCSPoints(2, amount, true);
            final String msg = "[GM 密语] GM " + c.getPlayer().getName() + " 给了 " + player.getName() + " 抵用卷 " + amount + "点";
            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, msg));
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!给抵用卷 <数量> <玩家> - 给指定玩家抵用卷").toString();
        }
    }
    
    public static class GainPoint extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 3) {
                return false;
            }
            int amount = 0;
            String name = "";
            try {
                amount = Integer.parseInt(splitted[1]);
                name = splitted[2];
            }
            catch (Exception ex) {}
            final int ch = Find.findChannel(name);
            if (ch <= 0) {
                c.getPlayer().dropMessage("该玩家不在线上");
                return true;
            }
            final MapleCharacter player = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
            if (player == null) {
                c.getPlayer().dropMessage("该玩家不在线上");
                return true;
            }
            player.setPoints(player.getPoints() + amount);
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!gainpoint <数量> <玩家> - 取得Point").toString();
        }
    }
    
    public static class GainVP extends GainPoint
    {
    }
    
    public static class 给所有人道具 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length > 2) {
                int itemId = 0;
                try {
                    itemId = Integer.parseInt(splitted[1]);
                }
                catch (Exception ex) {}
                final short quantity = (short)CommandProcessorUtil.getOptionalIntArg(splitted, 2, 1);
                final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
                int ret = 0;
                for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                    for (final MapleCharacter mch : cserv.getPlayerStorage().getAllCharacters()) {
                        if (GameConstants.isPet(itemId)) {
                            final MaplePet pet = MaplePet.createPet(itemId, MapleInventoryIdentifier.getInstance());
                            if (pet != null) {
                                MapleInventoryManipulator.addById(mch.getClient(), itemId, (short)1, "", pet, (long)ii.getPetLife(itemId));
                            }
                        }
                        else if (!ii.itemExists(itemId)) {
                            c.getPlayer().dropMessage(5, itemId + " - 物品不存在");
                        }
                        else {
                            IItem item;
                            if (GameConstants.getInventoryType(itemId) == MapleInventoryType.EQUIP) {
                                item = ii.randomizeStats((Equip)ii.getEquipById(itemId));
                            }
                            else {
                                item = new client.inventory.Item(itemId, (short)0, quantity, (byte)0);
                                if (GameConstants.getInventoryType(itemId) != MapleInventoryType.USE) {}
                            }
                            item.setGMLog(c.getPlayer().getName());
                            MapleInventoryManipulator.addbyItem(mch.getClient(), item);
                        }
                        ++ret;
                    }
                }
                for (final ChannelServer cserv2 : ChannelServer.getAllInstances()) {
                    for (final MapleCharacter mch : cserv2.getPlayerStorage().getAllCharacters()) {
                        mch.startMapEffect(c.getPlayer().getName() + "发放礼物" + (int)quantity + "个" + ii.getName(itemId) + "给在线的所有玩家！祝您玩得开心快乐", 5121009);
                    }
                }
                c.getPlayer().dropMessage(6, "命令使用成功，当前共有: " + ret + " 个玩家获得: " + (int)quantity + " 个" + ii.getName(itemId));
            }
            else {
                c.getPlayer().dropMessage(6, "用法: !给所有人道具 [物品ID] [数量] ");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!给所有人道具 [物品ID] [数量]").toString();
        }
    }
    
    public static class Item extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            int itemId = 0;
            try {
                itemId = Integer.parseInt(splitted[1]);
            }
            catch (Exception ex) {}
            final short quantity = (short)CommandProcessorUtil.getOptionalIntArg(splitted, 2, 1);
            final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            if (GameConstants.isPet(itemId)) {
                final MaplePet pet = MaplePet.createPet(itemId, MapleInventoryIdentifier.getInstance());
                if (pet != null) {
                    MapleInventoryManipulator.addById(c, itemId, (short)1, c.getPlayer().getName(), pet, (long)ii.getPetLife(itemId));
                }
            }
            else if (!ii.itemExists(itemId)) {
                c.getPlayer().dropMessage(5, itemId + " - 物品不存在");
            }
            else {
                byte flag = 0;
                flag |= (byte)ItemFlag.LOCK.getValue();
                IItem item;
                if (GameConstants.getInventoryType(itemId) == MapleInventoryType.EQUIP) {
                    item = ii.randomizeStats((Equip)ii.getEquipById(itemId));
                    item.setFlag(flag);
                }
                else {
                    item = new client.inventory.Item(itemId, (short)0, quantity, (byte)0);
                    if (GameConstants.getInventoryType(itemId) != MapleInventoryType.USE) {
                        item.setFlag(flag);
                    }
                }
                item.setOwner(c.getPlayer().getName());
                item.setGMLog(c.getPlayer().getName());
                MapleInventoryManipulator.addbyItem(c, item);
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!item <道具ID> - 取得道具").toString();
        }
    }
    
    public static class serverMsg extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length > 1) {
                final StringBuilder sb = new StringBuilder();
                sb.append(StringUtil.joinStringFrom(splitted, 1));
                for (final ChannelServer ch : ChannelServer.getAllInstances()) {
                    ch.setServerMessage(sb.toString());
                }
                Broadcast.broadcastMessage(MaplePacketCreator.serverMessage(sb.toString()));
                return true;
            }
            return false;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!servermsg 讯息 - 更改上方黄色公告").toString();
        }
    }
    
    public static class 修改顶部公告 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length > 1) {
                final StringBuilder sb = new StringBuilder();
                sb.append(StringUtil.joinStringFrom(splitted, 1));
                for (final ChannelServer ch : ChannelServer.getAllInstances()) {
                    ch.setServerMessage(sb.toString());
                }
                Broadcast.broadcastMessage(MaplePacketCreator.serverMessage(sb.toString()));
                return true;
            }
            return false;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!修改顶部公告 讯息 - 更改上方黄色公告").toString();
        }
    }
    
    public static class MobVac extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            for (final MapleMapObject mmo : c.getPlayer().getMap().getAllMonstersThreadsafe()) {
                final MapleMonster monster = (MapleMonster)mmo;
                c.getPlayer().getMap().broadcastMessage(MobPacket.moveMonster(false, 0, 0, monster.getObjectId(), monster.getPosition(), c.getPlayer().getPosition(), c.getPlayer().getLastRes()));
                monster.setPosition(c.getPlayer().getPosition());
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!mobvac - 全图吸怪").toString();
        }
    }
    
    public static class ItemVac extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final boolean ItemVac = c.getPlayer().getItemVac();
            if (!ItemVac) {
                c.getPlayer().stopItemVac();
                c.getPlayer().startItemVac();
            }
            else {
                c.getPlayer().stopItemVac();
            }
            c.getPlayer().dropMessage(6, "目前自动捡物状态:" + (ItemVac ? "关闭" : "开启"));
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!ItemVac - 全图吸物开关").toString();
        }
    }
    
    public static class 开启自动活动 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final EventManager em = c.getChannelServer().getEventSM().getEventManager("AutomatedEvent");
            if (em != null) {
                em.scheduleRandomEvent();
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!开启自动活动 - 开启自动活动").toString();
        }
    }
    
    public static class 活动开始 extends CommandExecute
    {
        private static ScheduledFuture<?> ts;
        private int min;
        private int sec;
        
        public 活动开始() {
            this.min = 1;
            this.sec = 0;
        }
        
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (c.getChannelServer().getEvent() == c.getPlayer().getMapId()) {
                MapleEvent.setEvent(c.getChannelServer(), false);
                if (c.getPlayer().getMapId() == 109020001) {
                    this.sec = 10;
                    c.getPlayer().dropMessage(5, "已经关闭活动入口，１０秒后开始活动。");
                    Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "频道:" + c.getChannel() + "活动目前已经关闭大门口，１０秒后开始活动。"));
                    c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.getClock(this.sec));
                }
                else {
                    this.sec = 60;
                    c.getPlayer().dropMessage(5, "已经关闭活动入口，６０秒后开始活动。");
                    Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "频道:" + c.getChannel() + "活动目前已经关闭大门口，６０秒后开始活动。"));
                    c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.getClock(this.sec));
                }
                活动开始.ts = EventTimer.getInstance().register((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (min == 0) {
                            MapleEvent.onStartEvent(c.getPlayer());
                            ts.cancel(false);
                            return;
                        }
                        min--;
                    }
                }, (long)(this.sec * 1000));
                return true;
            }
            c.getPlayer().dropMessage(5, "您必须先使用 !选择活动 设定当前频道的活动，并在当前频道活动地图裡使用。");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!活动开始 - 活动开始").toString();
        }
        
        static {
            活动开始.ts = null;
        }
    }
    
    public static class 选择活动 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final MapleEventType type = MapleEventType.getByString(splitted[1]);
            if (type == null) {
                final StringBuilder sb = new StringBuilder("目前开放的活动有: ");
                for (final MapleEventType t : MapleEventType.values()) {
                    sb.append(t.name()).append(",");
                }
                c.getPlayer().dropMessage(5, sb.toString().substring(0, sb.toString().length() - 1));
            }
            final String msg = MapleEvent.scheduleEvent(type, c.getChannelServer());
            if (msg.length() > 0) {
                c.getPlayer().dropMessage(5, msg);
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!选择活动 - 选择活动").toString();
        }
    }
    
    public static class LockItem extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 3) {
                return false;
            }
            final MapleCharacter chr = c.getChannelServer().getPlayerStorage().getCharacterByName(splitted[1]);
            if (chr == null) {
                c.getPlayer().dropMessage(6, "此玩家并不存在");
            }
            else {
                final int itemid = Integer.parseInt(splitted[2]);
                MapleInventoryType type = GameConstants.getInventoryType(itemid);
                for (final IItem item : chr.getInventory(type).listById(itemid)) {
                    item.setFlag((byte)(item.getFlag() | ItemFlag.LOCK.getValue()));
                    chr.getClient().sendPacket(MaplePacketCreator.modifyInventory(false, new ModifyInventory(1, item)));
                }
                if (type == MapleInventoryType.EQUIP) {
                    type = MapleInventoryType.EQUIPPED;
                    for (final IItem item : chr.getInventory(type).listById(itemid)) {
                        item.setFlag((byte)(item.getFlag() | ItemFlag.LOCK.getValue()));
                        chr.getClient().sendPacket(MaplePacketCreator.modifyInventory(false, new ModifyInventory(1, item)));
                    }
                }
                c.getPlayer().dropMessage(6, "玩家 " + splitted[1] + "身上所有ID为 " + splitted[2] + " 的道具已经从锁定了");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!lockitem <角色名称> <物品ID> - 上锁玩家身上的道具").toString();
        }
    }
    
    public static class 锁玩家道具 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 3) {
                return false;
            }
            final MapleCharacter chr = c.getChannelServer().getPlayerStorage().getCharacterByName(splitted[1]);
            if (chr == null) {
                c.getPlayer().dropMessage(6, "此玩家并不存在");
            }
            else {
                final int itemid = Integer.parseInt(splitted[2]);
                MapleInventoryType type = GameConstants.getInventoryType(itemid);
                for (final IItem item : chr.getInventory(type).listById(itemid)) {
                    item.setFlag((byte)(item.getFlag() | ItemFlag.LOCK.getValue()));
                    chr.getClient().sendPacket(MaplePacketCreator.modifyInventory(false, new ModifyInventory(1, item)));
                }
                if (type == MapleInventoryType.EQUIP) {
                    type = MapleInventoryType.EQUIPPED;
                    for (final IItem item : chr.getInventory(type).listById(itemid)) {
                        item.setFlag((byte)(item.getFlag() | ItemFlag.LOCK.getValue()));
                        chr.getClient().sendPacket(MaplePacketCreator.modifyInventory(false, new ModifyInventory(1, item)));
                    }
                }
                c.getPlayer().dropMessage(6, "玩家 " + splitted[1] + "身上所有ID为 " + splitted[2] + " 的道具已经从锁定了");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!锁玩家道具 <角色名称> <物品ID> - 上锁玩家身上的道具").toString();
        }
    }
    
    public static class KillMap extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            for (final MapleCharacter map : c.getPlayer().getMap().getCharactersThreadsafe()) {
                if (map != null && !map.isGM()) {
                    map.getStat().setHp(0);
                    map.getStat().setMp(0);
                    map.updateSingleStat(MapleStat.HP, 0);
                    map.updateSingleStat(MapleStat.MP, 0);
                }
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!killmap - 杀掉所有玩家").toString();
        }
    }
    
    public static class 杀掉所有玩家 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            for (final MapleCharacter map : c.getPlayer().getMap().getCharactersThreadsafe()) {
                if (map != null && !map.isGM()) {
                    map.getStat().setHp(0);
                    map.getStat().setMp(0);
                    map.updateSingleStat(MapleStat.HP, 0);
                    map.updateSingleStat(MapleStat.MP, 0);
                }
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!杀掉所有玩家 - 杀掉所有玩家").toString();
        }
    }
    
    public static class Disease extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 3) {
                return false;
            }
            int type;
            if (splitted[1].equalsIgnoreCase("SEAL")) {
                type = 120;
            }
            else if (splitted[1].equalsIgnoreCase("DARKNESS")) {
                type = 121;
            }
            else if (splitted[1].equalsIgnoreCase("WEAKEN")) {
                type = 122;
            }
            else if (splitted[1].equalsIgnoreCase("STUN")) {
                type = 123;
            }
            else if (splitted[1].equalsIgnoreCase("CURSE")) {
                type = 124;
            }
            else if (splitted[1].equalsIgnoreCase("POISON")) {
                type = 125;
            }
            else if (splitted[1].equalsIgnoreCase("SLOW")) {
                type = 126;
            }
            else if (splitted[1].equalsIgnoreCase("SEDUCE")) {
                type = 128;
            }
            else if (splitted[1].equalsIgnoreCase("REVERSE")) {
                type = 132;
            }
            else if (splitted[1].equalsIgnoreCase("ZOMBIFY")) {
                type = 133;
            }
            else if (splitted[1].equalsIgnoreCase("POTION")) {
                type = 134;
            }
            else if (splitted[1].equalsIgnoreCase("SHADOW")) {
                type = 135;
            }
            else if (splitted[1].equalsIgnoreCase("BLIND")) {
                type = 136;
            }
            else {
                if (!splitted[1].equalsIgnoreCase("FREEZE")) {
                    return false;
                }
                type = 137;
            }
            final MapleDisease dis = MapleDisease.getByMobSkill(type);
            if (splitted.length == 4) {
                final String name = splitted[2];
                final int ch = Find.findChannel(name);
                if (ch <= 0) {
                    c.getPlayer().dropMessage(6, "玩家必须上线");
                    return true;
                }
                final MapleCharacter victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
                if (victim == null) {
                    c.getPlayer().dropMessage(5, "找不到此玩家");
                }
                else {
                    victim.setChair(0);
                    victim.getClient().sendPacket(MaplePacketCreator.cancelChair(-1));
                    victim.getMap().broadcastMessage(victim, MaplePacketCreator.showChair(c.getPlayer().getId(), 0), false);
                    victim.getDiseaseBuff(dis, MobSkillFactory.getMobSkill(type, CommandProcessorUtil.getOptionalIntArg(splitted, 3, 1)));
                }
            }
            else {
                for (final MapleCharacter victim2 : c.getPlayer().getMap().getCharactersThreadsafe()) {
                    victim2.setChair(0);
                    victim2.getClient().sendPacket(MaplePacketCreator.cancelChair(-1));
                    victim2.getMap().broadcastMessage(victim2, MaplePacketCreator.showChair(c.getPlayer().getId(), 0), false);
                    victim2.getDiseaseBuff(dis, MobSkillFactory.getMobSkill(type, CommandProcessorUtil.getOptionalIntArg(splitted, 2, 1)));
                }
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!disease <SEAL/DARKNESS/WEAKEN/STUN/CURSE/POISON/SLOW/SEDUCE/REVERSE/ZOMBIFY/POTION/SHADOW/BLIND/FREEZE> [角色名称] <状态等级> - 让人得到特殊状态").toString();
        }
    }
    
    public static class SendAllNote extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length >= 1) {
                final String text = StringUtil.joinStringFrom(splitted, 1);
                for (final MapleCharacter mch : c.getChannelServer().getPlayerStorage().getAllCharactersThreadSafe()) {
                    c.getPlayer().sendNote(mch.getName(), text);
                }
                return true;
            }
            return false;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!sendallnote <文字> 传送Note给目前频道的所有人").toString();
        }
    }
    
    public static class giveMeso extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final String name = splitted[1];
            final int gain = Integer.parseInt(splitted[2]);
            final int ch = Find.findChannel(name);
            if (ch <= 0) {
                c.getPlayer().dropMessage(6, "玩家必须上线");
                return true;
            }
            final MapleCharacter victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
            if (victim == null) {
                c.getPlayer().dropMessage(5, "找不到 '" + name);
            }
            else {
                victim.gainMeso(gain, false);
                final String msg = "[GM 密语] GM " + c.getPlayer().getName() + " 给了 " + victim.getName() + " 金币 " + gain + "点";
                Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, msg));
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!gainmeso <名字> <数量> - 给玩家金币").toString();
        }
    }
    
    public static class MesoEveryone extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final int gain = Integer.parseInt(splitted[1]);
            for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (final MapleCharacter mch : cserv.getPlayerStorage().getAllCharactersThreadSafe()) {
                    mch.gainMeso(gain, true);
                }
            }
            final String msg = "[GM 密语] GM " + c.getPlayer().getName() + " 给了 所有玩家 金币 " + gain + "点";
            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, msg));
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!mesoeveryone <数量> - 给所有玩家金币").toString();
        }
    }
    
    public static class 给金币 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final String name = splitted[1];
            final int gain = Integer.parseInt(splitted[2]);
            final int ch = Find.findChannel(name);
            if (ch <= 0) {
                c.getPlayer().dropMessage(6, "玩家必须上线");
                return true;
            }
            final MapleCharacter victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
            if (victim == null) {
                c.getPlayer().dropMessage(5, "找不到 '" + name);
            }
            else {
                victim.gainMeso(gain, false);
                final String msg = "[GM 密语] GM " + c.getPlayer().getName() + " 给了 " + victim.getName() + " 枫币 " + gain + "点";
                Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, msg));
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!给金币 <名字> <数量> - 给玩家金币").toString();
        }
    }
    
    public static class 给全体玩家金币 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final int gain = Integer.parseInt(splitted[1]);
            for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (final MapleCharacter mch : cserv.getPlayerStorage().getAllCharactersThreadSafe()) {
                    mch.gainMeso(gain, true);
                }
            }
            final String msg = "[GM 密语] GM " + c.getPlayer().getName() + " 给了 所有玩家 枫币 " + gain + "点";
            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, msg));
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!给全体玩家金币 <数量> - 给所有玩家金币").toString();
        }
    }
    
    public static class CloneMe extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            c.getPlayer().cloneLook();
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!cloneme - 产生克龙体").toString();
        }
    }
    
    public static class DisposeClones extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            c.getPlayer().dropMessage(6, c.getPlayer().getCloneSize() + "个克龙体消失了.");
            c.getPlayer().disposeClones();
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!disposeclones - 摧毁克龙体").toString();
        }
    }
    
    public static class Monitor extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final MapleCharacter target = c.getChannelServer().getPlayerStorage().getCharacterByName(splitted[1]);
            if (target != null) {
                if (target.getClient().isMonitored()) {
                    target.getClient().setMonitored(false);
                    c.getPlayer().dropMessage(5, "Not monitoring " + target.getName() + " anymore.");
                }
                else {
                    target.getClient().setMonitored(true);
                    c.getPlayer().dropMessage(5, "Monitoring " + target.getName() + ".");
                }
            }
            else {
                c.getPlayer().dropMessage(5, "找不到该玩家");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!monitor <玩家> - 记录玩家资讯").toString();
        }
    }
    
    public static class PermWeather extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (c.getPlayer().getMap().getPermanentWeather() > 0) {
                c.getPlayer().getMap().setPermanentWeather(0);
                c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.removeMapEffect());
                c.getPlayer().dropMessage(5, "Map weather has been disabled.");
            }
            else {
                final int weather = CommandProcessorUtil.getOptionalIntArg(splitted, 1, 5120000);
                if (!MapleItemInformationProvider.getInstance().itemExists(weather) || weather / 10000 != 512) {
                    c.getPlayer().dropMessage(5, "Invalid ID.");
                }
                else {
                    c.getPlayer().getMap().setPermanentWeather(weather);
                    c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.startMapEffect("", weather, false));
                    c.getPlayer().dropMessage(5, "Map weather has been enabled.");
                }
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!permweather - 设定天气").toString();
        }
    }
    
    public static class Threads extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final Thread[] threads = new Thread[Thread.activeCount()];
            Thread.enumerate(threads);
            String filter = "";
            if (splitted.length > 1) {
                filter = splitted[1];
            }
            for (int i = 0; i < threads.length; ++i) {
                final String tstring = threads[i].toString();
                if (tstring.toLowerCase().contains((CharSequence)filter.toLowerCase())) {
                    c.getPlayer().dropMessage(6, i + ": " + tstring);
                }
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!threads - 查看Threads资讯").toString();
        }
    }
    
    public static class ShowTrace extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final Thread[] threads = new Thread[Thread.activeCount()];
            Thread.enumerate(threads);
            final Thread t = threads[Integer.parseInt(splitted[1])];
            c.getPlayer().dropMessage(6, t.toString() + ":");
            for (final StackTraceElement elem : t.getStackTrace()) {
                c.getPlayer().dropMessage(6, elem.toString());
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!showtrace - show trace info").toString();
        }
    }
    
    public static class ToggleOffense extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            try {
                final CheatingOffense co = CheatingOffense.valueOf(splitted[1]);
                co.setEnabled(!co.isEnabled());
            }
            catch (IllegalArgumentException iae) {
                c.getPlayer().dropMessage(6, "Offense " + splitted[1] + " not found");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!toggleoffense <Offense> - 开启或关闭CheatOffense").toString();
        }
    }
    
    public static class toggleDrop extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            c.getPlayer().getMap().toggleDrops();
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!toggledrop - 开启或关闭掉落").toString();
        }
    }
    
    public static class ToggleMegaphone extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            World.toggleMegaphoneMuteState();
            c.getPlayer().dropMessage(6, "广播是否封锁 : " + (c.getChannelServer().getMegaphoneMuteState() ? "是" : "否"));
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!togglemegaphone - 开启或关闭广播").toString();
        }
    }
    
    public static class DCAll extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            int range = -1;
            if (splitted.length < 2) {
                return false;
            }
            String input = null;
            try {
                input = splitted[1];
            }
            catch (Exception ex) {}
            final String s = splitted[1];
            int n = -1;
            switch (s.hashCode()) {
                case 109: {
                    if (s.equals((Object)"m")) {
                        n = 0;
                        break;
                    }
                    break;
                }
                case 99: {
                    if (s.equals((Object)"c")) {
                        n = 1;
                        break;
                    }
                    break;
                }
                case 119: {
                    if (s.equals((Object)"w")) {
                        n = 2;
                        break;
                    }
                    break;
                }
            }
            switch (n) {
                case 0: {
                    range = 0;
                    break;
                }
                case 1: {
                    range = 1;
                    break;
                }
                default: {
                    range = 2;
                    break;
                }
            }
            if (range == -1) {
                range = 1;
            }
            if (range == 0) {
                c.getPlayer().getMap().disconnectAll(c.getPlayer());
            }
            else if (range == 1) {
                c.getChannelServer().getPlayerStorage().disconnectAll(c.getPlayer());
            }
            else if (range == 2) {
                for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                    cserv.getPlayerStorage().disconnectAll(true);
                }
            }
            String show = "";
            switch (range) {
                case 0: {
                    show = "地图";
                    break;
                }
                case 1: {
                    show = "频道";
                    break;
                }
                case 2: {
                    show = "世界";
                    break;
                }
            }
            final String msg = "[GM 密语] GM " + c.getPlayer().getName() + "  DC 了 " + show + "玩家";
            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, msg));
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!dcall [m|c|w] - 所有玩家断线").toString();
        }
    }
    
    public static class 断线 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            int range = -1;
            if (splitted.length < 2) {
                return false;
            }
            String input = null;
            try {
                input = splitted[1];
            }
            catch (Exception ex) {}
            final String s = splitted[1];
            int n = -1;
            switch (s.hashCode()) {
                case 109: {
                    if (s.equals((Object)"m")) {
                        n = 0;
                        break;
                    }
                    break;
                }
                case 99: {
                    if (s.equals((Object)"c")) {
                        n = 1;
                        break;
                    }
                    break;
                }
                case 119: {
                    if (s.equals((Object)"w")) {
                        n = 2;
                        break;
                    }
                    break;
                }
            }
            switch (n) {
                case 0: {
                    range = 0;
                    break;
                }
                case 1: {
                    range = 1;
                    break;
                }
                default: {
                    range = 2;
                    break;
                }
            }
            if (range == -1) {
                range = 1;
            }
            if (range == 0) {
                c.getPlayer().getMap().disconnectAll(c.getPlayer());
            }
            else if (range == 1) {
                c.getChannelServer().getPlayerStorage().disconnectAll(c.getPlayer());
            }
            else if (range == 2) {
                for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                    cserv.getPlayerStorage().disconnectAll(true);
                }
            }
            String show = "";
            switch (range) {
                case 0: {
                    show = "地图";
                    break;
                }
                case 1: {
                    show = "频道";
                    break;
                }
                case 2: {
                    show = "世界";
                    break;
                }
            }
            final String msg = "[GM 密语] GM " + c.getPlayer().getName() + "  DC 了 " + show + "玩家";
            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, msg));
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!断线 [m|c|w] - 所有玩家断线").toString();
        }
    }
    
    public static class KillMonsterByOID extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final MapleMap map = c.getPlayer().getMap();
            final int targetId = Integer.parseInt(splitted[1]);
            final MapleMonster monster = map.getMonsterByOid(targetId);
            if (monster != null) {
                map.killMonster(monster, c.getPlayer(), false, false, (byte)1);
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!killmonsterbyoid <moboid> - 杀掉地图上某个怪物").toString();
        }
    }
    
    public static class 杀掉怪物 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final MapleMap map = c.getPlayer().getMap();
            final int targetId = Integer.parseInt(splitted[1]);
            final MapleMonster monster = map.getMonsterByOid(targetId);
            if (monster != null) {
                map.killMonster(monster, c.getPlayer(), false, false, (byte)1);
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!杀掉怪物 <moboid> - 杀掉地图上某个怪物").toString();
        }
    }
    
    public static class HitMonsterByOID extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final MapleMap map = c.getPlayer().getMap();
            final int targetId = Integer.parseInt(splitted[1]);
            final int damage = Integer.parseInt(splitted[2]);
            final MapleMonster monster = map.getMonsterByOid(targetId);
            if (monster != null) {
                map.broadcastMessage(MobPacket.damageMonster(targetId, (long)damage));
                monster.damage(c.getPlayer(), (long)damage, false);
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!hitmonsterbyoid <moboid> <damage> - 碰撞地图上某个怪物").toString();
        }
    }
    
    public static class NPC extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            int npcId = 0;
            try {
                npcId = Integer.parseInt(splitted[1]);
            }
            catch (Exception ex) {}
            final MapleNPC npc = MapleLifeFactory.getNPC(npcId);
            if (npc != null && !npc.getName().equals((Object)"MISSINGNO")) {
                npc.setPosition(c.getPlayer().getPosition());
                npc.setCy(c.getPlayer().getPosition().y);
                npc.setRx0(c.getPlayer().getPosition().x + 50);
                npc.setRx1(c.getPlayer().getPosition().x - 50);
                npc.setFh(c.getPlayer().getMap().getFootholds().findBelow(c.getPlayer().getPosition()).getId());
                npc.setCustom(true);
                c.getPlayer().getMap().addMapObject((MapleMapObject)npc);
                c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.spawnNPC(npc, true));
            }
            else {
                c.getPlayer().dropMessage(6, "找不到此代码为" + npcId + "的Npc");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!npc <npcid> - 呼叫出NPC").toString();
        }
    }
    
    public static class 打开NPC extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            int npcId = 0;
            try {
                npcId = Integer.parseInt(splitted[1]);
            }
            catch (Exception ex) {}
            final MapleNPC npc = MapleLifeFactory.getNPC(npcId);
            if (npc != null && !npc.getName().equals((Object)"MISSINGNO")) {
                npc.setPosition(c.getPlayer().getPosition());
                npc.setCy(c.getPlayer().getPosition().y);
                npc.setRx0(c.getPlayer().getPosition().x + 50);
                npc.setRx1(c.getPlayer().getPosition().x - 50);
                npc.setFh(c.getPlayer().getMap().getFootholds().findBelow(c.getPlayer().getPosition()).getId());
                npc.setCustom(true);
                c.getPlayer().getMap().addMapObject((MapleMapObject)npc);
                c.getPlayer().getMap().broadcastMessage(MaplePacketCreator.spawnNPC(npc, true));
            }
            else {
                c.getPlayer().dropMessage(6, "找不到此代码为" + npcId + "的Npc");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!打开NPC <npcid> - 呼叫出NPC").toString();
        }
    }
    
    public static class MakePNPC extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 3) {
                return false;
            }
            try {
                c.getPlayer().dropMessage(6, "Making playerNPC...");
                final String name = splitted[1];
                final int ch = Find.findChannel(name);
                if (ch <= 0) {
                    c.getPlayer().dropMessage(6, "玩家必须上线");
                    return true;
                }
                final MapleCharacter chhr = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
                if (chhr == null) {
                    c.getPlayer().dropMessage(6, splitted[1] + " is not online");
                }
                else {
                    final int npcId = Integer.parseInt(splitted[2]);
                    final MapleNPC npc_c = MapleLifeFactory.getNPC(npcId);
                    if (npcId < 9901500 || npcId > 9901551 || npcId < 9901000 || npcId < 9901319) {
                        c.getPlayer().dropMessage(6, "NPC不存在");
                        return true;
                    }
                    final PlayerNPC npc = new PlayerNPC(chhr, npcId, c.getPlayer().getMap(), c.getPlayer());
                    npc.addToServer();
                    c.getPlayer().dropMessage(6, "Done");
                }
            }
            catch (Exception e) {
                c.getPlayer().dropMessage(6, "NPC failed... : " + e.getMessage());
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!makepnpc <playername> <npcid> - 创造玩家NPC").toString();
        }
    }
    
    public static class 创造玩家NPC extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 3) {
                return false;
            }
            try {
                c.getPlayer().dropMessage(6, "Making playerNPC...");
                final String name = splitted[1];
                final int ch = Find.findChannel(name);
                if (ch <= 0) {
                    c.getPlayer().dropMessage(6, "玩家必须上线");
                    return true;
                }
                final MapleCharacter chhr = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
                if (chhr == null) {
                    c.getPlayer().dropMessage(6, splitted[1] + " is not online");
                }
                else {
                    final int npcId = Integer.parseInt(splitted[2]);
                    final MapleNPC npc_c = MapleLifeFactory.getNPC(npcId);
                    if (npcId < 9901500 || npcId > 9901551 || npcId < 9901000 || npcId < 9901319) {
                        c.getPlayer().dropMessage(6, "NPC不存在");
                        return true;
                    }
                    final PlayerNPC npc = new PlayerNPC(chhr, npcId, c.getPlayer().getMap(), c.getPlayer());
                    npc.addToServer();
                    c.getPlayer().dropMessage(6, "Done");
                }
            }
            catch (Exception e) {
                c.getPlayer().dropMessage(6, "NPC failed... : " + e.getMessage());
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!创造玩家NPC <playername> <npcid> - 创造玩家NPC").toString();
        }
    }
    
    public static class MakeOfflineP extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            try {
                c.getPlayer().dropMessage(6, "Making playerNPC...");
                final MapleClient cs = new MapleClient(null, null, (Channel)new MockIOSession());
                final MapleCharacter chhr = MapleCharacter.loadCharFromDB(MapleCharacterUtil.getIdByName(splitted[1]), cs, false);
                if (chhr == null) {
                    c.getPlayer().dropMessage(6, splitted[1] + " does not exist");
                }
                else {
                    final int npcId = Integer.parseInt(splitted[2]);
                    if (npcId < 9901500 || npcId > 9901551 || npcId < 9901000 || npcId < 9901319) {
                        c.getPlayer().dropMessage(6, "NPC不存在");
                        return true;
                    }
                    final PlayerNPC npc = new PlayerNPC(chhr, npcId, c.getPlayer().getMap(), c.getPlayer());
                    npc.addToServer();
                    c.getPlayer().dropMessage(6, "Done");
                }
            }
            catch (Exception e) {
                c.getPlayer().dropMessage(6, "NPC failed... : " + e.getMessage());
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!deletepnpc <charname> <npcid> - 创造离线PNPC").toString();
        }
    }
    
    public static class DestroyPNPC extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            try {
                c.getPlayer().dropMessage(6, "Destroying playerNPC...");
                final MapleNPC npc = c.getPlayer().getMap().getNPCByOid(Integer.parseInt(splitted[1]));
                if (npc instanceof PlayerNPC) {
                    ((PlayerNPC)npc).destroy(true);
                    c.getPlayer().dropMessage(6, "Done");
                }
                else {
                    c.getPlayer().dropMessage(6, "!destroypnpc [objectid]");
                }
            }
            catch (Exception e) {
                c.getPlayer().dropMessage(6, "NPC failed... : " + e.getMessage());
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!destroypnpc [objectid] - 删除PNPC").toString();
        }
    }
    
    public static class 删除NPC extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            try {
                c.getPlayer().dropMessage(6, "Destroying playerNPC...");
                final MapleNPC npc = c.getPlayer().getMap().getNPCByOid(Integer.parseInt(splitted[1]));
                if (npc instanceof PlayerNPC) {
                    ((PlayerNPC)npc).destroy(true);
                    c.getPlayer().dropMessage(6, "Done");
                }
                else {
                    c.getPlayer().dropMessage(6, "!destroypnpc [objectid]");
                }
            }
            catch (Exception e) {
                c.getPlayer().dropMessage(6, "NPC failed... : " + e.getMessage());
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!删除NPC [objectid] - 删除PNPC").toString();
        }
    }
    
    public static class Spawn extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            int mid = 0;
            try {
                mid = Integer.parseInt(splitted[1]);
            }
            catch (Exception ex) {}
            int num = Math.min(CommandProcessorUtil.getOptionalIntArg(splitted, 2, 1), 500);
            if (num > 1000) {
                num = 1000;
            }
            final Long hp = CommandProcessorUtil.getNamedLongArg(splitted, 1, "hp");
            final Integer mp = CommandProcessorUtil.getNamedIntArg(splitted, 1, "mp");
            final Integer exp = CommandProcessorUtil.getNamedIntArg(splitted, 1, "exp");
            final Double php = CommandProcessorUtil.getNamedDoubleArg(splitted, 1, "php");
            final Double pmp = CommandProcessorUtil.getNamedDoubleArg(splitted, 1, "pmp");
            final Double pexp = CommandProcessorUtil.getNamedDoubleArg(splitted, 1, "pexp");
            MapleMonster onemob;
            try {
                onemob = MapleLifeFactory.getMonster(mid);
            }
            catch (RuntimeException e) {
                c.getPlayer().dropMessage(5, "错误: " + e.getMessage());
                return true;
            }
            long newhp;
            if (hp != null) {
                newhp = (long)hp;
            }
            else if (php != null) {
                newhp = (long)((double)onemob.getMobMaxHp() * ((double)php / 100.0));
            }
            else {
                newhp = onemob.getMobMaxHp();
            }
            if (mp != null) {
                final int newmp = (int)mp;
            }
            else if (pmp != null) {
                final int newmp = (int)((double)onemob.getMobMaxMp() * ((double)pmp / 100.0));
            }
            else {
                final int newmp = onemob.getMobMaxMp();
            }
            int newexp;
            if (exp != null) {
                newexp = (int)exp;
            }
            else if (pexp != null) {
                newexp = (int)((double)onemob.getMobExp() * ((double)pexp / 100.0));
            }
            else {
                newexp = onemob.getMobExp();
            }
            if (newhp < 1L) {
                newhp = 1L;
            }
            final OverrideMonsterStats overrideStats = new OverrideMonsterStats(newhp, onemob.getMobMaxMp(), newexp, false);
            for (int i = 0; i < num; ++i) {
                final MapleMonster mob = MapleLifeFactory.getMonster(mid);
                mob.setHp(newhp);
                mob.setOverrideStats(overrideStats);
                c.getPlayer().getMap().spawnMonsterOnGroundBelow(mob, c.getPlayer().getPosition());
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!spawn <怪物ID> <hp|exp|php||pexp = ?> - 召唤怪物").toString();
        }
    }
    
    public static class 召唤怪物 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            int mid = 0;
            try {
                mid = Integer.parseInt(splitted[1]);
            }
            catch (Exception ex) {}
            int num = Math.min(CommandProcessorUtil.getOptionalIntArg(splitted, 2, 1), 500);
            if (num > 1000) {
                num = 1000;
            }
            final Long hp = CommandProcessorUtil.getNamedLongArg(splitted, 1, "hp");
            final Integer mp = CommandProcessorUtil.getNamedIntArg(splitted, 1, "mp");
            final Integer exp = CommandProcessorUtil.getNamedIntArg(splitted, 1, "exp");
            final Double php = CommandProcessorUtil.getNamedDoubleArg(splitted, 1, "php");
            final Double pmp = CommandProcessorUtil.getNamedDoubleArg(splitted, 1, "pmp");
            final Double pexp = CommandProcessorUtil.getNamedDoubleArg(splitted, 1, "pexp");
            MapleMonster onemob;
            try {
                onemob = MapleLifeFactory.getMonster(mid);
            }
            catch (RuntimeException e) {
                c.getPlayer().dropMessage(5, "错误: " + e.getMessage());
                return true;
            }
            long newhp;
            if (hp != null) {
                newhp = (long)hp;
            }
            else if (php != null) {
                newhp = (long)((double)onemob.getMobMaxHp() * ((double)php / 100.0));
            }
            else {
                newhp = onemob.getMobMaxHp();
            }
            if (mp != null) {
                final int newmp = (int)mp;
            }
            else if (pmp != null) {
                final int newmp = (int)((double)onemob.getMobMaxMp() * ((double)pmp / 100.0));
            }
            else {
                final int newmp = onemob.getMobMaxMp();
            }
            int newexp;
            if (exp != null) {
                newexp = (int)exp;
            }
            else if (pexp != null) {
                newexp = (int)((double)onemob.getMobExp() * ((double)pexp / 100.0));
            }
            else {
                newexp = onemob.getMobExp();
            }
            if (newhp < 1L) {
                newhp = 1L;
            }
            final OverrideMonsterStats overrideStats = new OverrideMonsterStats(newhp, onemob.getMobMaxMp(), newexp, false);
            for (int i = 0; i < num; ++i) {
                final MapleMonster mob = MapleLifeFactory.getMonster(mid);
                mob.setHp(newhp);
                mob.setOverrideStats(overrideStats);
                c.getPlayer().getMap().spawnMonsterOnGroundBelow(mob, c.getPlayer().getPosition());
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!召唤怪物 <怪物ID> <hp|exp|php||pexp = ?> - 召唤怪物").toString();
        }
    }
    
    public static class WarpPlayersTo extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            try {
                final MapleMap target = c.getChannelServer().getMapFactory().getMap(Integer.parseInt(splitted[1]));
                final MapleMap from = c.getPlayer().getMap();
                for (final MapleCharacter chr : from.getCharactersThreadsafe()) {
                    chr.changeMap(target, target.getPortal(0));
                }
            }
            catch (Exception e) {
                return false;
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!WarpPlayersTo <maipid> 把所有玩家传送到某个地图").toString();
        }
    }
    
    public static class 传送所有玩家到地图 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            try {
                final MapleMap target = c.getChannelServer().getMapFactory().getMap(Integer.parseInt(splitted[1]));
                final MapleMap from = c.getPlayer().getMap();
                for (final MapleCharacter chr : from.getCharactersThreadsafe()) {
                    chr.changeMap(target, target.getPortal(0));
                }
            }
            catch (Exception e) {
                return false;
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!传送所有玩家到地图 <maipid> 把所有玩家传送到某个地图").toString();
        }
    }
    
    public static class WarpAllHere extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            for (final ChannelServer CS : ChannelServer.getAllInstances()) {
                for (final MapleCharacter mch : CS.getPlayerStorage().getAllCharactersThreadSafe()) {
                    if (mch.getMapId() != c.getPlayer().getMapId()) {
                        mch.changeMap(c.getPlayer().getMap(), c.getPlayer().getPosition());
                    }
                    if (mch.getClient().getChannel() != c.getPlayer().getClient().getChannel()) {
                        mch.changeChannel(c.getPlayer().getClient().getChannel());
                    }
                }
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!WarpAllHere 把所有玩家传送到GM这裡").toString();
        }
    }
    
    public static class 传送所有玩家 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            for (final ChannelServer CS : ChannelServer.getAllInstances()) {
                for (final MapleCharacter mch : CS.getPlayerStorage().getAllCharactersThreadSafe()) {
                    if (mch.getMapId() != c.getPlayer().getMapId()) {
                        mch.changeMap(c.getPlayer().getMap(), c.getPlayer().getPosition());
                    }
                    if (mch.getClient().getChannel() != c.getPlayer().getClient().getChannel()) {
                        mch.changeChannel(c.getPlayer().getClient().getChannel());
                    }
                }
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!传送所有玩家 把所有玩家传送到GM这里").toString();
        }
    }
    
    public static class 拉小黑屋 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            MapleCharacter victim = c.getChannelServer().getPlayerStorage().getCharacterByName(splitted[1]);
            if (victim != null) {
                final MapleMap mapp = victim.getClient().getChannelServer().getMapFactory().getMap(180000001);
                victim.changeMap(mapp, mapp.getPortal(0));
            }
            else {
                final int ch = Find.findChannel(splitted[1]);
                if (ch < 0) {
                    c.getPlayer().dropMessage(5, "角色不在线");
                    return true;
                }
                victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(splitted[1]);
                c.getPlayer().dropMessage(5, "正在传送玩家到小黑屋");
                victim.dropMessage(5, "GM正在传送你至小黑屋");
                if (victim.getMapId() != 180000001) {
                    final MapleMap mapp2 = victim.getClient().getChannelServer().getMapFactory().getMap(180000001);
                    victim.changeMap(mapp2, mapp2.getPortal(0));
                }
                victim.changeChannel(c.getChannel());
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!拉小黑屋 +空格+ 名字").toString();
        }
    }
    
    public static class LOLCastle extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length != 2) {
                return false;
            }
            final MapleMap target = c.getChannelServer().getEventSM().getEventManager("lolcastle").getInstance("lolcastle" + splitted[1]).getMapFactory().getMap(990000300, false, false);
            c.getPlayer().changeMap(target, target.getPortal(0));
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!lolcastle level (level = 1-5) - 不知道是啥").toString();
        }
    }
    
    public static class StartProfiling extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final CPUSampler sampler = CPUSampler.getInstance();
            sampler.addIncluded("client");
            sampler.addIncluded("constants");
            sampler.addIncluded("database");
            sampler.addIncluded("handling");
            sampler.addIncluded("provider");
            sampler.addIncluded("scripting");
            sampler.addIncluded("server");
            sampler.addIncluded("tools");
            sampler.start();
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!startprofiling 开始纪录JVM资讯").toString();
        }
    }
    
    public static class StopProfiling extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final CPUSampler sampler = CPUSampler.getInstance();
            try {
                String filename = "odinprofile.txt";
                if (splitted.length > 1) {
                    filename = splitted[1];
                }
                final File file = new File(filename);
                if (file.exists()) {
                    c.getPlayer().dropMessage(6, "The entered filename already exists, choose a different one");
                    return true;
                }
                sampler.stop();
                try (final FileWriter fw = new FileWriter(file)) {
                    sampler.save((Writer)fw, 1, 10);
                }
            }
            catch (IOException e) {
                System.err.println("Error saving profile" + (Object)e);
            }
            sampler.reset();
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!stopprofiling <filename> - 取消纪录JVM资讯并储存到档案").toString();
        }
    }
    
    public static class ReloadMap extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final int mapId = Integer.parseInt(splitted[1]);
            for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                if (cserv.getMapFactory().isMapLoaded(mapId) && cserv.getMapFactory().getMap(mapId).getCharactersSize() > 0) {
                    c.getPlayer().dropMessage(5, "There exists characters on channel " + cserv.getChannel());
                    return true;
                }
            }
            for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                if (cserv.getMapFactory().isMapLoaded(mapId)) {
                    cserv.getMapFactory().removeMap(mapId);
                }
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!reloadmap <maipid> - 重置某个地图").toString();
        }
    }
    
    public static class Respawn extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            c.getPlayer().getMap().respawn(true);
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!respawn - 重新进入地图").toString();
        }
    }
    
    public static class ResetMap extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            c.getPlayer().getMap().resetFully();
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!respawn - 重置这个地图").toString();
        }
    }
    
    public static class PNPC extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final int npcId = Integer.parseInt(splitted[1]);
            final MapleNPC npc = MapleLifeFactory.getNPC(npcId);
            if (npc != null && !npc.getName().equals((Object)"MISSINGNO")) {
                final int xpos = c.getPlayer().getPosition().x;
                final int ypos = c.getPlayer().getPosition().y;
                final int fh = c.getPlayer().getMap().getFootholds().findBelow(c.getPlayer().getPosition()).getId();
                npc.setPosition(c.getPlayer().getPosition());
                npc.setCy(ypos);
                npc.setRx0(xpos);
                npc.setRx1(xpos);
                npc.setFh(fh);
                npc.setCustom(true);
                try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
                     final PreparedStatement ps = con.prepareStatement("INSERT INTO wz_customlife (dataid, f, hide, fh, cy, rx0, rx1, type, x, y, mid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                    ps.setInt(1, npcId);
                    ps.setInt(2, 0);
                    ps.setInt(3, 0);
                    ps.setInt(4, fh);
                    ps.setInt(5, ypos);
                    ps.setInt(6, xpos);
                    ps.setInt(7, xpos);
                    ps.setString(8, "n");
                    ps.setInt(9, xpos);
                    ps.setInt(10, ypos);
                    ps.setInt(11, c.getPlayer().getMapId());
                    ps.executeUpdate();
                }
                catch (SQLException e) {
                    c.getPlayer().dropMessage(6, "Failed to save NPC to the database");
                    FileoutputUtil.outError("logs/资料库异常.txt", (Throwable)e);
                }
                for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                    cserv.getMapFactory().getMap(c.getPlayer().getMapId()).addMapObject((MapleMapObject)npc);
                    cserv.getMapFactory().getMap(c.getPlayer().getMapId()).broadcastMessage(MaplePacketCreator.spawnNPC(npc, true));
                }
                c.getPlayer().dropMessage(6, "Please do not reload this map or else the NPC will disappear till the next restart.");
            }
            else {
                c.getPlayer().dropMessage(6, "查无此 Npc ");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!pnpc - 建立永久NPC").toString();
        }
    }
    
    public static class 永久NPC extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final int npcId = Integer.parseInt(splitted[1]);
            final MapleNPC npc = MapleLifeFactory.getNPC(npcId);
            if (npc != null && !npc.getName().equals((Object)"MISSINGNO")) {
                final int xpos = c.getPlayer().getPosition().x;
                final int ypos = c.getPlayer().getPosition().y;
                final int fh = c.getPlayer().getMap().getFootholds().findBelow(c.getPlayer().getPosition()).getId();
                npc.setPosition(c.getPlayer().getPosition());
                npc.setCy(ypos);
                npc.setRx0(xpos);
                npc.setRx1(xpos);
                npc.setFh(fh);
                npc.setCustom(true);
                try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
                     final PreparedStatement ps = con.prepareStatement("INSERT INTO wz_customlife (dataid, f, hide, fh, cy, rx0, rx1, type, x, y, mid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                    ps.setInt(1, npcId);
                    ps.setInt(2, 0);
                    ps.setInt(3, 0);
                    ps.setInt(4, fh);
                    ps.setInt(5, ypos);
                    ps.setInt(6, xpos);
                    ps.setInt(7, xpos);
                    ps.setString(8, "n");
                    ps.setInt(9, xpos);
                    ps.setInt(10, ypos);
                    ps.setInt(11, c.getPlayer().getMapId());
                    ps.executeUpdate();
                }
                catch (SQLException e) {
                    c.getPlayer().dropMessage(6, "Failed to save NPC to the database");
                    FileoutputUtil.outError("logs/资料库异常.txt", (Throwable)e);
                }
                for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                    cserv.getMapFactory().getMap(c.getPlayer().getMapId()).addMapObject((MapleMapObject)npc);
                    cserv.getMapFactory().getMap(c.getPlayer().getMapId()).broadcastMessage(MaplePacketCreator.spawnNPC(npc, true));
                }
                c.getPlayer().dropMessage(6, "Please do not reload this map or else the NPC will disappear till the next restart.");
            }
            else {
                c.getPlayer().dropMessage(6, "查无此 Npc ");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!永久NPC - 建立永久NPC").toString();
        }
    }
    
    public static class autoban extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            PiPiConfig.setAutoban(!PiPiConfig.getAutoban());
            c.getPlayer().dropMessage("自动封锁: " + (PiPiConfig.getAutoban() ? "开启" : "关闭"));
            System.out.println("自动封锁: " + (PiPiConfig.getAutoban() ? "开启" : "关闭"));
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!Autoban - 自动封锁开关").toString();
        }
    }
    
    public static class 自动封号 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            PiPiConfig.setAutoban(!PiPiConfig.getAutoban());
            c.getPlayer().dropMessage("自动封锁: " + (PiPiConfig.getAutoban() ? "开启" : "关闭"));
            System.out.println("自动封锁: " + (PiPiConfig.getAutoban() ? "开启" : "关闭"));
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!自动封号 - 自动封锁开关").toString();
        }
    }
    
    public static class Packet extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
            final int packetheader = Integer.parseInt(splitted[1]);
            String packet_in = " 00 00 00 00 00 00 00 00 00 ";
            if (splitted.length > 2) {
                packet_in = StringUtil.joinStringFrom(splitted, 2);
            }
            mplew.writeShort(packetheader);
            mplew.write(HexTool.getByteArrayFromHexString(packet_in));
            mplew.writeZeroBytes(20);
            c.sendPacket(mplew.getPacket());
            c.getPlayer().dropMessage(packetheader + "已传送封包[" + packetheader + "] : " + mplew.toString());
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!Packet - <封包内容>").toString();
        }
    }
    
    public static class maxmeso extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            c.getPlayer().gainMeso(Integer.MAX_VALUE - c.getPlayer().getMeso(), true);
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!maxmeso - 金币满").toString();
        }
    }
    
    public static class mesos extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            int meso = 0;
            try {
                meso = Integer.parseInt(splitted[1]);
            }
            catch (Exception ex) {}
            c.getPlayer().gainMeso(meso, true);
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!mesos <需要的数量> - 得到金币").toString();
        }
    }
    
    public static class 满金币 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            c.getPlayer().gainMeso(Integer.MAX_VALUE - c.getPlayer().getMeso(), true);
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!满金币 - 满金币").toString();
        }
    }
    
    public static class 刷金币 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            int meso = 0;
            try {
                meso = Integer.parseInt(splitted[1]);
            }
            catch (Exception ex) {}
            c.getPlayer().gainMeso(meso, true);
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!刷金币 <需要的数量> - 得到金币").toString();
        }
    }
    
    public static class Drop extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            int itemId = 0;
            String name = null;
            try {
                itemId = Integer.parseInt(splitted[1]);
                name = splitted[3];
            }
            catch (Exception ex) {}
            final short quantity = (short)CommandProcessorUtil.getOptionalIntArg(splitted, 2, 1);
            final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            if (GameConstants.isPet(itemId)) {
                c.getPlayer().dropMessage(5, "宠物请到购物商城购买.");
            }
            else if (!ii.itemExists(itemId)) {
                c.getPlayer().dropMessage(5, itemId + " - 物品不存在");
            }
            else {
                IItem toDrop;
                if (GameConstants.getInventoryType(itemId) == MapleInventoryType.EQUIP) {
                    toDrop = ii.randomizeStats((Equip)ii.getEquipById(itemId));
                }
                else {
                    toDrop = new client.inventory.Item(itemId, (short)0, quantity, (byte)0);
                }
                toDrop.setGMLog(c.getPlayer().getName());
                if (name != null) {
                    final int ch = Find.findChannel(name);
                    if (ch > 0) {
                        final MapleCharacter victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
                        if (victim != null) {
                            victim.getMap().spawnItemDrop((MapleMapObject)victim, victim, toDrop, victim.getPosition(), true, true);
                        }
                    }
                    else {
                        c.getPlayer().dropMessage("玩家: [" + name + "] 不在线上唷");
                    }
                }
                else {
                    c.getPlayer().getMap().spawnItemDrop((MapleMapObject)c.getPlayer(), c.getPlayer(), toDrop, c.getPlayer().getPosition(), true, true);
                }
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!Drop <道具ID> - 掉落道具").toString();
        }
    }
    
    public static class 丢 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            int itemId = 0;
            String name = null;
            try {
                itemId = Integer.parseInt(splitted[1]);
                name = splitted[3];
            }
            catch (Exception ex) {}
            final short quantity = (short)CommandProcessorUtil.getOptionalIntArg(splitted, 2, 1);
            final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            if (GameConstants.isPet(itemId)) {
                c.getPlayer().dropMessage(5, "宠物请到购物商城购买.");
            }
            else if (!ii.itemExists(itemId)) {
                c.getPlayer().dropMessage(5, itemId + " - 物品不存在");
            }
            else {
                IItem toDrop;
                if (GameConstants.getInventoryType(itemId) == MapleInventoryType.EQUIP) {
                    toDrop = ii.randomizeStats((Equip)ii.getEquipById(itemId));
                }
                else {
                    toDrop = new client.inventory.Item(itemId, (short)0, quantity, (byte)0);
                }
                toDrop.setGMLog(c.getPlayer().getName());
                if (name != null) {
                    final int ch = Find.findChannel(name);
                    if (ch > 0) {
                        final MapleCharacter victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
                        if (victim != null) {
                            victim.getMap().spawnItemDrop((MapleMapObject)victim, victim, toDrop, victim.getPosition(), true, true);
                        }
                    }
                    else {
                        c.getPlayer().dropMessage("玩家: [" + name + "] 不在线上唷");
                    }
                }
                else {
                    c.getPlayer().getMap().spawnItemDrop((MapleMapObject)c.getPlayer(), c.getPlayer(), toDrop, c.getPlayer().getPosition(), true, true);
                }
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!丢 <道具ID> - 掉落道具").toString();
        }
    }
    
    public static class ProDrop extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 3) {
                return false;
            }
            int itemId = 0;
            final int quantity = 1;
            int Str = 0;
            int Dex = 0;
            int Int = 0;
            int Luk = 0;
            int HP = 0;
            int MP = 0;
            int Watk = 0;
            int Matk = 0;
            int Wdef = 0;
            int Mdef = 0;
            int Scroll = 0;
            int Upg = 0;
            int Acc = 0;
            int Avoid = 0;
            int jump = 0;
            int speed = 0;
            int day = 0;
            try {
                int splitted_count = 1;
                itemId = Integer.parseInt(splitted[splitted_count++]);
                Str = Integer.parseInt(splitted[splitted_count++]);
                Dex = Integer.parseInt(splitted[splitted_count++]);
                Int = Integer.parseInt(splitted[splitted_count++]);
                Luk = Integer.parseInt(splitted[splitted_count++]);
                HP = Integer.parseInt(splitted[splitted_count++]);
                MP = Integer.parseInt(splitted[splitted_count++]);
                Watk = Integer.parseInt(splitted[splitted_count++]);
                Matk = Integer.parseInt(splitted[splitted_count++]);
                Wdef = Integer.parseInt(splitted[splitted_count++]);
                Mdef = Integer.parseInt(splitted[splitted_count++]);
                Upg = Integer.parseInt(splitted[splitted_count++]);
                Acc = Integer.parseInt(splitted[splitted_count++]);
                Avoid = Integer.parseInt(splitted[splitted_count++]);
                speed = Integer.parseInt(splitted[splitted_count++]);
                jump = Integer.parseInt(splitted[splitted_count++]);
                Scroll = Integer.parseInt(splitted[splitted_count++]);
                day = Integer.parseInt(splitted[splitted_count++]);
            }
            catch (Exception ex) {}
            final boolean Str_check = Str != 0;
            final boolean Int_check = Int != 0;
            final boolean Dex_check = Dex != 0;
            final boolean Luk_check = Luk != 0;
            final boolean HP_check = HP != 0;
            final boolean MP_check = MP != 0;
            final boolean WATK_check = Watk != 0;
            final boolean MATK_check = Matk != 0;
            final boolean WDEF_check = Wdef != 0;
            final boolean MDEF_check = Mdef != 0;
            final boolean SCROLL_check = true;
            final boolean UPG_check = Upg != 0;
            final boolean ACC_check = Acc != 0;
            final boolean AVOID_check = Avoid != 0;
            final boolean JUMP_check = jump != 0;
            final boolean SPEED_check = speed != 0;
            final boolean DAY_check = day != 0;
            final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            if (GameConstants.isPet(itemId)) {
                c.getPlayer().dropMessage(5, "请从商城购买宠物.");
                return true;
            }
            if (!ii.itemExists(itemId)) {
                c.getPlayer().dropMessage(5, itemId + " 不存在");
                return true;
            }
            if (GameConstants.getInventoryType(itemId) == MapleInventoryType.EQUIP) {
                final Equip equip = ii.randomizeStats((Equip)ii.getEquipById(itemId));
                equip.setGMLog(c.getPlayer().getName() + " 使用 !Prodrop");
                if (Str_check) {
                    equip.setStr((short)Str);
                }
                if (Luk_check) {
                    equip.setLuk((short)Luk);
                }
                if (Dex_check) {
                    equip.setDex((short)Dex);
                }
                if (Int_check) {
                    equip.setInt((short)Int);
                }
                if (HP_check) {
                    equip.setHp((short)HP);
                }
                if (MP_check) {
                    equip.setMp((short)MP);
                }
                if (WATK_check) {
                    equip.setWatk((short)Watk);
                }
                if (MATK_check) {
                    equip.setMatk((short)Matk);
                }
                if (WDEF_check) {
                    equip.setWdef((short)Wdef);
                }
                if (MDEF_check) {
                    equip.setMdef((short)Mdef);
                }
                if (ACC_check) {
                    equip.setAcc((short)Acc);
                }
                if (AVOID_check) {
                    equip.setAvoid((short)Avoid);
                }
                if (SCROLL_check) {
                    equip.setUpgradeSlots((byte)Scroll);
                }
                if (UPG_check) {
                    equip.setLevel((byte)Upg);
                }
                if (JUMP_check) {
                    equip.setJump((short)jump);
                }
                if (SPEED_check) {
                    equip.setSpeed((short)speed);
                }
                if (DAY_check) {
                    equip.setExpiration(System.currentTimeMillis() + (long)(day * 24 * 60 * 60 * 1000));
                }
                c.getPlayer().getMap().spawnItemDrop((MapleMapObject)c.getPlayer(), c.getPlayer(), (IItem)equip, c.getPlayer().getPosition(), true, true);
            }
            else {
                final IItem toDrop = new client.inventory.Item(itemId, (short)0, (short)quantity, (byte)0);
                toDrop.setGMLog(c.getPlayer().getName() + " 使用 !Prodrop");
                c.getPlayer().getMap().spawnItemDrop((MapleMapObject)c.getPlayer(), c.getPlayer(), toDrop, c.getPlayer().getPosition(), true, true);
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!ProDrop <物品代码> (<力量> <敏捷> <智力> <幸运> <HP> <MP> <物攻> <魔攻> <物防> <魔防> <武器+x> <命中> <迴避> <移动> <跳跃> <衝捲数> <天数>)").toString();
        }
    }
    
    public static class 给点数 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 4) {
                return false;
            }
            String error = null;
            final String input = splitted[1];
            final String name = splitted[2];
            int nx = 0;
            int gain = 0;
            try {
                final String s = input;
                int n = -1;
                switch (s.hashCode()) {
                    case 920535: {
                        if (s.equals((Object)"点数")) {
                            n = 0;
                            break;
                        }
                        break;
                    }
                    case 851566: {
                        if (s.equals((Object)"枫点")) {
                            n = 1;
                            break;
                        }
                        break;
                    }
                }
                switch (n) {
                    case 0: {
                        nx = 1;
                        break;
                    }
                    case 1: {
                        nx = 2;
                        break;
                    }
                    default: {
                        error = "输入的文字不是[点数]和[枫点] 而是[" + input + "]";
                        break;
                    }
                }
                gain = Integer.parseInt(splitted[3]);
            }
            catch (Exception ex) {
                error = "请输入数字以及不能给予超过2147483647的 " + input + " 错误为: " + ex.toString();
            }
            if (error != null) {
                c.getPlayer().dropMessage(error);
                return true;
            }
            final int ch = Find.findChannel(name);
            if (ch <= 0) {
                c.getPlayer().dropMessage("玩家必须上线");
                return true;
            }
            final MapleCharacter victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
            if (victim == null) {
                c.getPlayer().dropMessage("找不到此玩家");
            }
            else {
                c.getPlayer().dropMessage("已经给予玩家[" + name + "] " + input + " " + gain);
                FileoutputUtil.logToFile("logs/Data/给点数.txt", "\r\n " + FileoutputUtil.NowTime() + " GM " + c.getPlayer().getName() + " 给了 " + victim.getName() + " " + input + " " + gain + "点");
                victim.modifyCSPoints(nx, gain, true);
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!给点数 点数/枫点 玩家名称 数量").toString();
        }
    }
    
    public static class ResetMobs extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            c.getPlayer().getMap().killAllMonsters(false);
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!resetmobs - 重置地图上所有怪物").toString();
        }
    }
    
    public static class 最近传送点 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final MaplePortal portal = c.getPlayer().getMap().findClosestPortal(c.getPlayer().getTruePosition());
            c.getPlayer().dropMessage(-11, portal.getName() + " id: " + portal.getId() + " script: " + portal.getScriptName());
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!最近传送点 - 查看最近的传送点").toString();
        }
    }
    
    public static class 给所有人点数 extends CashEveryone
    {
    }
    
    public static class CashEveryone extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length > 2) {
                int type = Integer.parseInt(splitted[1]);
                int quantity = Integer.parseInt(splitted[2]);
                if (type == 1) {
                    type = 1;
                }
                else {
                    if (type != 2) {
                        c.getPlayer().dropMessage(6, "用法: !给所有人点数 [类型1-2] [点卷数量] 1是点卷.2是抵用卷");
                        return true;
                    }
                    type = 2;
                }
                if (quantity > 10000) {
                    quantity = 10000;
                }
                int ret = 0;
                for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                    for (final MapleCharacter mch : cserv.getPlayerStorage().getAllCharacters()) {
                        mch.modifyCSPoints(type, quantity);
                        ++ret;
                    }
                }
                final String show = (type == 1) ? "点卷" : "抵用卷";
                for (final ChannelServer cserv2 : ChannelServer.getAllInstances()) {
                    for (final MapleCharacter mch2 : cserv2.getPlayerStorage().getAllCharacters()) {
                        mch2.startMapEffect("管理员发放" + quantity + show + "给在线的所有玩家！祝您玩得开心快乐", 5121009);
                    }
                }
                c.getPlayer().dropMessage(6, "命令使用成功，当前共有: " + ret + " 个玩家获得: " + quantity + " 点的" + ((type == 1) ? "点卷 " : " 抵用卷 ") + " 共计: " + ret * quantity);
            }
            else {
                c.getPlayer().dropMessage(6, "用法: !给所有人点数 [类型1-2] [点数数量] 1是点卷.2是抵用卷");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!给所有人点数 - [类型1-2] [点数数量]").toString();
        }
    }
    
    public static class 给地图人点数 extends CashMap
    {
    }
    
    public static class CashMap extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length > 2) {
                int type = Integer.parseInt(splitted[1]);
                int quantity = Integer.parseInt(splitted[2]);
                if (type == 1) {
                    type = 1;
                }
                else {
                    if (type != 2) {
                        c.getPlayer().dropMessage(6, "用法: !给所有人点数 [类型1-2] [点数数量] 1是点卷.2是抵用卷");
                        return true;
                    }
                    type = 2;
                }
                if (quantity > 10000) {
                    quantity = 10000;
                }
                int ret = 0;
                final MapleMap from = c.getPlayer().getMap();
                for (final MapleCharacter chrr : from.getCharactersThreadsafe()) {
                    chrr.modifyCSPoints(type, quantity);
                    ++ret;
                }
                final String show = (type == 1) ? "点卷" : "抵用卷";
                final MapleMap froma = c.getPlayer().getMap();
                for (final MapleCharacter chrrr : froma.getCharactersThreadsafe()) {
                    chrrr.startMapEffect("管理员发放" + quantity + show + "给地图的所有玩家！祝您玩得开心快乐", 5121009);
                }
                c.getPlayer().dropMessage(6, "命令使用成功，当前共有: " + ret + " 个玩家获得: " + quantity + " 点的" + ((type == 1) ? "GASH " : " 枫叶点数 ") + " 共计: " + ret * quantity);
            }
            else {
                c.getPlayer().dropMessage(6, "用法: !给地图人点数 [类型1-2] [点数数量] 1是点卷.2是抵用卷");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!给地图人点数 - [类型1-2] [点数数量]").toString();
        }
    }
    
    public static class 给地图人枫币 extends MesoMap
    {
    }
    
    public static class MesoMap extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length > 1) {
                final int quantity = Integer.parseInt(splitted[1]);
                int ret = 0;
                final MapleMap from = c.getPlayer().getMap();
                for (final MapleCharacter chrr : from.getCharactersThreadsafe()) {
                    chrr.gainMeso(quantity, true);
                    ++ret;
                }
                final MapleMap froma = c.getPlayer().getMap();
                for (final MapleCharacter chrrr : froma.getCharactersThreadsafe()) {
                    chrrr.startMapEffect("管理员发放" + quantity + "金币给地图的所有玩家！祝您玩得开心快乐", 5121009);
                }
                c.getPlayer().dropMessage(6, "命令使用成功，当前共有: " + ret + " 个玩家获得: " + quantity + " 金币" + " 共计: " + ret * quantity);
            }
            else {
                c.getPlayer().dropMessage(6, "用法: !给地图人金币 [数量]");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!给地图人金币 - [数量]").toString();
        }
    }
    
    public static class 给地图人红利 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length > 1) {
                final int quantity = Integer.parseInt(splitted[1]);
                int ret = 0;
                final MapleMap from = c.getPlayer().getMap();
                for (final MapleCharacter chrr : from.getCharactersThreadsafe()) {
                    chrr.modifyCSPoints(3, quantity, true);
                    ++ret;
                }
                final MapleMap froma = c.getPlayer().getMap();
                for (final MapleCharacter chrrr : froma.getCharactersThreadsafe()) {
                    chrrr.startMapEffect("管理员发放" + quantity + "元宝给地图的所有玩家！祝您玩得开心快乐", 5121009);
                }
                c.getPlayer().dropMessage(6, "命令使用成功，当前共有: " + ret + " 个玩家获得: " + quantity + " 元宝" + " 共计: " + ret * quantity);
            }
            else {
                c.getPlayer().dropMessage(6, "用法: !给地图人元宝 [数量]");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!给地图人元宝 - [数量]").toString();
        }
    }
    
    public static class 给推广积分 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 3) {
                return false;
            }
            final String name = splitted[1];
            final int gain = Integer.parseInt(splitted[2]);
            final int accid = MapleClient.findAccIdForCharacterName(name);
            final int tgjf = MapleClient.getTGJF(accid);
            if (tgjf > 0) {
                c.getPlayer().dropMessage("玩家[" + name + "] 推广积分不等于0。");
                return true;
            }
            final byte ret = MapleClient.setTGJF(name, gain);
            if (ret == -2) {
                c.getPlayer().dropMessage(6, "[给推广积分] SQL 错误");
            }
            else if (ret == -1) {
                c.getPlayer().dropMessage(6, "[给推广积分] 目标玩家不存在");
            }
            else {
                c.getPlayer().dropMessage(6, "[给推广积分] 成功给予推广积分");
            }
            c.getPlayer().dropMessage("已经给予玩家[" + name + "] " + gain + " 推广积分");
            FileoutputUtil.logToFile("logs/Data/给推广积分.txt", "\r\n " + FileoutputUtil.NowTime() + " GM " + c.getPlayer().getName() + " 给了 " + name + " " + gain + "推广积分");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!给推广积分 玩家名称 数量").toString();
        }
    }
    
    public static class 给FB积分 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 3) {
                return false;
            }
            final String name = splitted[1];
            final int gain = Integer.parseInt(splitted[2]);
            final int accid = MapleClient.findAccIdForCharacterName(name);
            final int tjjf = MapleClient.getTJJF(accid);
            if (tjjf > 0) {
                c.getPlayer().dropMessage("玩家[" + name + "] FB积分不等于0。");
                return true;
            }
            final byte ret = MapleClient.setTJJF(name, gain);
            if (ret == -2) {
                c.getPlayer().dropMessage(6, "[给FB积分] SQL 错误");
            }
            else if (ret == -1) {
                c.getPlayer().dropMessage(6, "[给FB积分] 目标玩家不存在");
            }
            else {
                c.getPlayer().dropMessage(6, "[给FB积分] 成功给予FB积分");
            }
            c.getPlayer().dropMessage("已经给予玩家[" + name + "] " + gain + " FB积分");
            FileoutputUtil.logToFile("logs/Data/给FB积分.txt", "\r\n " + FileoutputUtil.NowTime() + " GM " + c.getPlayer().getName() + " 给了 " + name + " " + gain + "FB积分");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!给推广积分 玩家名称 数量").toString();
        }
    }
    
    public static class 测谎所有人 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            int ret = 0;
            for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (final MapleCharacter mch : cserv.getPlayerStorage().getAllCharacters()) {
                    mch.startLieDetector(false);
                    ++ret;
                }
            }
            c.getPlayer().dropMessage(6, "命令使用成功，当前共有: " + ret + " 个玩家被测谎");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!测谎所有人 - [测谎所有人]").toString();
        }
    }
    
    public static class ReloadGashapon extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            GashaponFactory.getInstance().reloadGashapons();
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!reloadGashapon - 重新载入转蛋机").toString();
        }
    }
    
    public static class 刷 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (Game.刷 == "关") {
                return true;
            }
            if (splitted.length < 2) {
                return false;
            }
            int itemId = 0;
            try {
                itemId = Integer.parseInt(splitted[1]);
            }
            catch (Exception ex) {}
            final short quantity = (short)CommandProcessorUtil.getOptionalIntArg(splitted, 2, 1);
            final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            if (GameConstants.isPet(itemId)) {
                final MaplePet pet = MaplePet.createPet(itemId, MapleInventoryIdentifier.getInstance());
                if (pet != null) {
                    MapleInventoryManipulator.addById(c, itemId, (short)1, c.getPlayer().getName(), pet, 90L, (byte)0);
                }
            }
            else if (!ii.itemExists(itemId)) {
                c.getPlayer().dropMessage(5, itemId + " - 物品不存在");
            }
            else {
                byte flag = 0;
                flag |= (byte)ItemFlag.LOCK.getValue();
                IItem item;
                if (GameConstants.getInventoryType(itemId) == MapleInventoryType.EQUIP) {
                    item = ii.randomizeStats((Equip)ii.getEquipById(itemId));
                }
                else {
                    item = new client.inventory.Item(itemId, (short)0, quantity, (byte)0);
                    if (GameConstants.getInventoryType(itemId) != MapleInventoryType.USE) {}
                }
                item.setOwner(c.getPlayer().getName());
                item.setGMLog(c.getPlayer().getName());
                MapleInventoryManipulator.addbyItem(c, item);
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"指令通知开关")) <= 0) {
                    Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[管理员信息]:[管理员:" + c.getPlayer().getName() + "]刷出了物品: " + MapleItemInformationProvider.getInstance().getName(Integer.parseInt(splitted[1])) + " [" + splitted[1] + "]"));
                }
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!刷 <道具ID> - 刷出道具").toString();
        }
    }
    
    public static class 我的位置 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (Game.我的位置 == "关") {
                return true;
            }
            final Point pos = c.getPlayer().getPosition();
            c.getPlayer().dropMessage(6, "X: " + pos.x + " | Y: " + pos.y + " | RX0: " + (pos.x + 50) + " | RX1: " + (pos.x - 50) + " | FH: " + c.getPlayer().getFH() + "| CY:" + pos.y);
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"指令通知开关")) <= 0) {
                Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[管理员信息]:[管理员:" + c.getPlayer().getName() + "]使用了我的位置指令"));
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!我的位置").toString();
        }
    }
    
    public static class 地图代码 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            c.getPlayer().dropMessage(5, "地图: " + c.getPlayer().getMap().getMapName() + " ");
            c.getPlayer().dropMessage(5, "代码: " + c.getPlayer().getMap().getId() + " ");
            c.getPlayer().dropMessage(5, "坐标: " + String.valueOf(c.getPlayer().getPosition().x) + " , " + String.valueOf(c.getPlayer().getPosition().y) + "");
            c.getPlayer().dropMessage(5, "使用 !传送+<空格>+<地图ID> 可直接传送到目标地图");
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"指令通知开关")) <= 0) {
                Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[管理员信息]:[管理员:" + c.getPlayer().getName() + "]使用了查询当前地图代码功能"));
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    
    public static class 传送 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (Game.传送 == "关") {
                return true;
            }
            if (splitted.length < 2) {
                return false;
            }
            try {
                final MapleMap target = c.getChannelServer().getMapFactory().getMap(Integer.parseInt(splitted[1]));
                if (target == null) {
                    c.getPlayer().dropMessage(5, "地图不存在.");
                    return true;
                }
                MaplePortal targetPortal = null;
                if (splitted.length > 2) {
                    try {
                        targetPortal = target.getPortal(Integer.parseInt(splitted[2]));
                    }
                    catch (IndexOutOfBoundsException e2) {
                        c.getPlayer().dropMessage(5, "传送点错误.");
                    }
                    catch (NumberFormatException ex) {}
                }
                if (targetPortal == null) {
                    targetPortal = target.getPortal(0);
                }
                c.getPlayer().changeMap(target, targetPortal);
                if ((int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"指令通知开关")) <= 0) {
                    Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "[管理员信息]:[管理员:" + c.getPlayer().getName() + "]传送到地图[" + splitted[1] + "]"));
                }
            }
            catch (Exception e) {
                c.getPlayer().dropMessage(5, "Error: " + e.getMessage());
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!传送 <mapid|charname> [portal] - 传送到某地图/人").toString();
        }
    }
    
    public static class 清怪 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            MapleMap map = c.getPlayer().getMap();
            double range = Double.POSITIVE_INFINITY;
            boolean drop = false;
            if (splitted.length > 1) {
                final int irange = 9999;
                if (splitted.length < 2) {
                    range = (double)(irange * irange);
                }
                else {
                    try {
                        map = c.getChannelServer().getMapFactory().getMap(Integer.parseInt(splitted[1]));
                        range = (double)(Integer.parseInt(splitted[2]) * Integer.parseInt(splitted[2]));
                    }
                    catch (Exception ex) {}
                }
                if (splitted.length >= 3) {
                    drop = splitted[3].equalsIgnoreCase("true");
                }
            }
            final List<MapleMapObject> monsters = map.getMapObjectsInRange(c.getPlayer().getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER));
            for (final MapleMapObject monstermo : map.getMapObjectsInRange(c.getPlayer().getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER))) {
                final MapleMonster mob = (MapleMonster)monstermo;
                map.killMonster(mob, c.getPlayer(), drop, false, (byte)1);
            }
            c.getPlayer().dropMessage("您总共杀了 " + monsters.size() + " 怪物");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!清怪 [range] [mapid] - 杀掉所有怪物").toString();
        }
    }
    
    public static class 雇佣加成开关 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            ServerConstants.setENABLE_H_EXP(!ServerConstants.getENABLE_H_EXP().get());
            c.getPlayer().dropMessage(5, "当前雇佣经验开关：" + (ServerConstants.getENABLE_H_EXP().get() ? "开" : "关"));
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!雇佣加成开关").toString();
        }
    }
}
