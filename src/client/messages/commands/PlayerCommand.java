package client.messages.commands;

import client.inventory.IItem;
import server.gashapon.GashaponFactory;
import client.inventory.MapleInventory;
import server.MapleInventoryManipulator;
import client.inventory.MapleInventoryType;
import tools.FileoutputUtil;
import handling.world.World.Broadcast;
import constants.PiPiConfig;
import tools.StringUtil;
import java.util.Iterator;
import server.life.MapleMonster;
import server.maps.MapleMapObject;
import java.util.Arrays;
import server.maps.MapleMapObjectType;
import java.util.Calendar;
import tools.FilePrinter;
import tools.MaplePacketCreator;
import client.MapleStat;
import server.maps.MapleMap;
import server.maps.SavedLocationType;
import constants.GameConstants;
import scripting.NPCScriptManager;
import client.MapleClient;
import constants.ServerConstants.PlayerGMRank;

public class PlayerCommand
{
    public static PlayerGMRank getPlayerLevelRequired() {
        return PlayerGMRank.普通玩家;
    }
    
    public static class 帮助 extends help
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            NPCScriptManager.getInstance().start(c, 9330079, "玩家指令查询");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("@帮助 - 帮助").toString();
        }
    }
    
    public static class help extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            NPCScriptManager.getInstance().start(c, 9330079, "玩家指令查询");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("@help - 帮助").toString();
        }
    }
    
    public abstract static class OpenNPCCommand extends CommandExecute
    {
        protected int npc;
        private static final int[] npcs;
        
        public OpenNPCCommand() {
            this.npc = -1;
        }
        
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (this.npc != 1 && c.getPlayer().getMapId() != 910000000) {
                for (final int i : GameConstants.blockedMaps) {
                    if (c.getPlayer().getMapId() == i) {
                        c.getPlayer().dropMessage(1, "你不能在这裡使用指令.");
                        return true;
                    }
                }
                if (this.npc != 2 && c.getPlayer().getLevel() < 10) {
                    c.getPlayer().dropMessage(1, "你的等级必须是10等.");
                    return true;
                }
                if (c.getPlayer().getMap().getSquadByMap() != null || c.getPlayer().getEventInstance() != null || c.getPlayer().getMap().getEMByMap() != null || c.getPlayer().getMapId() >= 990000000) {
                    c.getPlayer().dropMessage(1, "你不能在这裡使用指令.");
                    return true;
                }
                if ((c.getPlayer().getMapId() >= 680000210 && c.getPlayer().getMapId() <= 680000502) || (c.getPlayer().getMapId() / 1000 == 980000 && c.getPlayer().getMapId() != 980000000) || c.getPlayer().getMapId() / 100 == 1030008 || c.getPlayer().getMapId() / 100 == 922010 || c.getPlayer().getMapId() / 10 == 13003000) {
                    c.getPlayer().dropMessage(1, "你不能在这裡使用指令.");
                    return true;
                }
            }
            NPCScriptManager.getInstance().start(c, OpenNPCCommand.npcs[this.npc]);
            return true;
        }
        
        static {
            npcs = new int[] { 9010017, 9000001, 9000058, 9330082, 9209002 };
        }
    }
    
    public static class 丢装 extends DropCash
    {
        @Override
        public String getMessage() {
            return new StringBuilder().append("@丢装 - 呼叫清除现金道具npc").toString();
        }
    }
    
    public static class DropCash extends OpenNPCCommand
    {
        public DropCash() {
            this.npc = 0;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("@dropbash - 呼叫清除现金道具npc").toString();
        }
    }
    
    public static class event extends OpenNPCCommand
    {
        public event() {
            this.npc = 1;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("@event - 呼叫活动npc").toString();
        }
    }
    
    public static class npc extends 万能
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            NPCScriptManager.getInstance().start(c, 9900004, "拍卖功能");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("@npc - 呼叫万能npc").toString();
        }
    }
    
    public static class 万能 extends OpenNPCCommand
    {
        public 万能() {
            this.npc = 2;
        }
        
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            NPCScriptManager.getInstance().start(c, 9900004, "拍卖功能");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("@万能 - 呼叫万能npc").toString();
        }
    }
    
    public static class FM extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            for (final int i : GameConstants.blockedMaps) {
                if (c.getPlayer().getMapId() == i) {
                    c.getPlayer().dropMessage(5, "当前地图无法使用.");
                    return false;
                }
            }
            if (c.getPlayer().getLevel() < 10) {
                c.getPlayer().dropMessage(5, "你的等级不足10级无法使用.");
                return false;
            }
            if (c.getPlayer().hasBlockedInventory(true) || c.getPlayer().getMap().getSquadByMap() != null || c.getPlayer().getEventInstance() != null || c.getPlayer().getMap().getEMByMap() != null || c.getPlayer().getMapId() >= 990000000) {
                c.getPlayer().dropMessage(5, "请稍后再试");
                return false;
            }
            if (c.getPlayer().getMapId() == 180000001) {
                c.getPlayer().dropMessage(5, "该地图无法使用该功能!");
                return false;
            }
            if ((c.getPlayer().getMapId() >= 680000210 && c.getPlayer().getMapId() <= 680000502) || (c.getPlayer().getMapId() / 1000 == 980000 && c.getPlayer().getMapId() != 980000000) || c.getPlayer().getMapId() / 100 == 1030008 || c.getPlayer().getMapId() / 100 == 922010 || c.getPlayer().getMapId() / 10 == 13003000) {
                c.getPlayer().dropMessage(5, "请稍后再试.");
                return false;
            }
            c.getPlayer().saveLocation(SavedLocationType.FREE_MARKET, c.getPlayer().getMap().getReturnMap().getId());
            final MapleMap map = c.getChannelServer().getMapFactory().getMap(910000000);
            c.getPlayer().changeMap(map, map.getPortal(0));
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("FM - 回自由").toString();
        }
    }
    
    public static class expfix extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            c.getPlayer().setExp(0);
            c.getPlayer().updateSingleStat(MapleStat.EXP, c.getPlayer().getExp());
            c.getPlayer().dropMessage(5, "经验修复完成");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("@expfix - 经验归零").toString();
        }
    }
    
    public static class TSmega extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            c.getPlayer().setSmega();
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("@TSmega - 开/关闭广播").toString();
        }
    }
    
    public static class Gashponmega extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            c.getPlayer().setGashponmega();
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("@Gashponmega - 开/关闭转蛋广播").toString();
        }
    }
    
    public static class 解卡 extends ea
    {
        @Override
        public String getMessage() {
            return new StringBuilder().append("@解卡 - 解卡").toString();
        }
    }
    
    public static class 查看 extends ea
    {
        @Override
        public String getMessage() {
            return new StringBuilder().append("@查看 - 解卡").toString();
        }
    }
    
    public static class ea extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            c.removeClickedNPC();
            NPCScriptManager.getInstance().dispose(c);
            c.sendPacket(MaplePacketCreator.enableActions());
            c.sendPacket(MaplePacketCreator.sendHint("解卡完毕..\r\n当前系统时间" + FilePrinter.getLocalDateString() + " 星期" + getDayOfWeek() + "\r\n目前剩余: 点卷 " + c.getPlayer().getCSPoints(1) + "  抵用卷 " + c.getPlayer().getCSPoints(2) + "  \r\n当前延迟 " + c.getPlayer().getClient().getLatency() + " 毫秒", 350, 5));
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("@ea - 解卡").toString();
        }
        
        public static String getDayOfWeek() {
            final int dayOfWeek = Calendar.getInstance().get(7) - 1;
            String dd = String.valueOf(dayOfWeek);
            switch (dayOfWeek) {
                case 0: {
                    dd = "日";
                    break;
                }
                case 1: {
                    dd = "一";
                    break;
                }
                case 2: {
                    dd = "二";
                    break;
                }
                case 3: {
                    dd = "三";
                    break;
                }
                case 4: {
                    dd = "四";
                    break;
                }
                case 5: {
                    dd = "五";
                    break;
                }
                case 6: {
                    dd = "六";
                    break;
                }
            }
            return dd;
        }
    }
    
    public static class 怪物 extends mob
    {
        @Override
        public String getMessage() {
            return new StringBuilder().append("@怪物 - 查看怪物状态").toString();
        }
    }
    
    public static class mob extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            MapleMonster monster = null;
            for (final MapleMapObject monstermo : c.getPlayer().getMap().getMapObjectsInRange(c.getPlayer().getPosition(), 100000.0, Arrays.asList(MapleMapObjectType.MONSTER))) {
                monster = (MapleMonster)monstermo;
                if (monster.isAlive()) {
                    c.getPlayer().dropMessage(6, "怪物 " + monster.toString());
                }
            }
            if (monster == null) {
                c.getPlayer().dropMessage(6, "找不到地图上的怪物");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("@mob - 查看怪物状态").toString();
        }
    }
    
    public static class CGM extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            boolean autoReply = false;
            if (splitted.length < 2) {
                return false;
            }
            final String talk = StringUtil.joinStringFrom(splitted, 1);
            if (c.getPlayer().isGM()) {
                c.getPlayer().dropMessage(6, "因为你自己是GM所以无法使用此指令,可以尝试!cngm <讯息> 来建立GM聊天频道~");
            }
            else if (!c.getPlayer().getCheatTracker().GMSpam(100000, 1)) {
                boolean fake = false;
                boolean showmsg = true;
                if (PiPiConfig.getBlackList().containsKey((Object)Integer.valueOf(c.getAccID()))) {
                    fake = true;
                }
                if (talk.contains((CharSequence)"抢") && talk.contains((CharSequence)"图")) {
                    c.getPlayer().dropMessage(1, "抢图自行解决！！");
                    fake = true;
                    showmsg = false;
                }
                else if ((talk.contains((CharSequence)"被") && talk.contains((CharSequence)"骗")) || (talk.contains((CharSequence)"点") && talk.contains((CharSequence)"骗"))) {
                    c.getPlayer().dropMessage(1, "被骗请自行解决");
                    fake = true;
                    showmsg = false;
                }
                else if (talk.contains((CharSequence)"删") && (talk.contains((CharSequence)"角") || talk.contains((CharSequence)"脚")) && talk.contains((CharSequence)"错")) {
                    c.getPlayer().dropMessage(1, "删错角色请自行解决");
                    fake = true;
                    showmsg = false;
                }
                else if (talk.contains((CharSequence)"乱") && talk.contains((CharSequence)"名") && talk.contains((CharSequence)"声")) {
                    c.getPlayer().dropMessage(1, "请自行解决");
                    fake = true;
                    showmsg = false;
                }
                if (talk.toUpperCase().contains((CharSequence)"VIP") && (talk.contains((CharSequence)"领") || talk.contains((CharSequence)"获")) && talk.contains((CharSequence)"取")) {
                    c.getPlayer().dropMessage(1, "VIP将会于储值后一段时间后自行发放，请耐心等待");
                    autoReply = true;
                }
                else if (talk.contains((CharSequence)"贡献") || talk.contains((CharSequence)"666") || ((talk.contains((CharSequence)"取") || talk.contains((CharSequence)"拿") || talk.contains((CharSequence)"发") || talk.contains((CharSequence)"领")) && (talk.contains((CharSequence)"勳") || talk.contains((CharSequence)"徽") || talk.contains((CharSequence)"勋")) && talk.contains((CharSequence)"章"))) {
                    c.getPlayer().dropMessage(1, "勳章请去点拍卖NPC案领取勳章\r\n如尚未被加入清单请耐心等候GM。");
                    autoReply = true;
                }
                else if ((talk.contains((CharSequence)"商人") && talk.contains((CharSequence)"吃")) || (talk.contains((CharSequence)"商店") && talk.contains((CharSequence)"补偿"))) {
                    c.getPlayer().dropMessage(1, "目前精灵商人装备和枫币有机率被吃\r\n如被吃了请务必将当时的情况完整描述给管理员\r\n\r\nPS: 不会补偿任何物品");
                    autoReply = true;
                }
                else if (talk.contains((CharSequence)"档") && talk.contains((CharSequence)"案") && talk.contains((CharSequence)"受") && talk.contains((CharSequence)"损")) {
                    c.getPlayer().dropMessage(1, "档案受损请重新解压缩主程式唷");
                    autoReply = true;
                }
                else if ((talk.contains((CharSequence)"缺") || talk.contains((CharSequence)"少")) && ((talk.contains((CharSequence)"技") && talk.contains((CharSequence)"能") && talk.contains((CharSequence)"点")) || talk.toUpperCase().contains((CharSequence)"SP"))) {
                    c.getPlayer().dropMessage(1, "缺少技能点请重练，没有其他方法了唷");
                    autoReply = true;
                }
                if (showmsg) {
                    c.getPlayer().dropMessage(6, "讯息已经寄送给GM了!");
                }
                if (!fake) {
                    Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, "[管理员帮帮忙]频道 " + c.getPlayer().getClient().getChannel() + " 玩家 [" + c.getPlayer().getName() + "] (" + c.getPlayer().getId() + "): " + talk + (autoReply ? " -- (系统已自动回复)" : "")));
                }
                FileoutputUtil.logToFile("logs/data/管理员帮帮忙.txt", "\r\n " + FileoutputUtil.NowTime() + " 玩家[" + c.getPlayer().getName() + "] 帐号[" + c.getAccountName() + "]: " + talk + (autoReply ? " -- (系统已自动回复)" : "") + "\r\n");
            }
            else {
                c.getPlayer().dropMessage(6, "为了防止对GM刷屏所以每1分钟只能发一次.");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("@cgm - 跟GM回报").toString();
        }
    }
    
    public static class 清除道具 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 4) {
                return false;
            }
            String Column = "null";
            int start = -1;
            int end = -1;
            try {
                Column = splitted[1];
                start = Integer.parseInt(splitted[2]);
                end = Integer.parseInt(splitted[3]);
            }
            catch (Exception ex) {}
            if (start == -1 || end == -1) {
                c.getPlayer().dropMessage("@清除道具 <装备栏/消耗栏/装饰栏/其他栏/特殊栏> <开始格数> <结束格数>");
                return true;
            }
            if (start < 1) {
                start = 1;
            }
            if (end > 96) {
                end = 96;
            }
            final String s = Column;
            int n = -1;
            switch (s.hashCode()) {
                case 34380653: {
                    if (s.equals((Object)"装备栏")) {
                        n = 0;
                        break;
                    }
                    break;
                }
                case 27989600: {
                    if (s.equals((Object)"消耗栏")) {
                        n = 1;
                        break;
                    }
                    break;
                }
                case 34891812: {
                    if (s.equals((Object)"装饰栏")) {
                        n = 2;
                        break;
                    }
                    break;
                }
                case 20692975: {
                    if (s.equals((Object)"其他栏")) {
                        n = 3;
                        break;
                    }
                    break;
                }
                case 29042174: {
                    if (s.equals((Object)"特殊栏")) {
                        n = 4;
                        break;
                    }
                    break;
                }
            }
            MapleInventoryType type = null;
            switch (n) {
                case 0: {
                    type = MapleInventoryType.EQUIP;
                    break;
                }
                case 1: {
                    type = MapleInventoryType.USE;
                    break;
                }
                case 2: {
                    type = MapleInventoryType.SETUP;
                    break;
                }
                case 3: {
                    type = MapleInventoryType.ETC;
                    break;
                }
                case 4: {
                    type = MapleInventoryType.CASH;
                    break;
                }
                default: {
                    type = null;
                    break;
                }
            }
            if (type == null) {
                c.getPlayer().dropMessage("@清除道具 <装备栏/消耗栏/装饰栏/其他栏/特殊栏> <开始格数> <结束格数>");
                return true;
            }
            final MapleInventory inv = c.getPlayer().getInventory(type);
            for (int i = start; i <= end; ++i) {
                if (inv.getItem((short)i) != null) {
                    MapleInventoryManipulator.removeFromSlot(c, type, (short)i, inv.getItem((short)i).getQuantity(), true);
                }
            }
            FileoutputUtil.logToFile("logs/Data/玩家指令.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " 帐号: " + c.getAccountName() + " 玩家: " + c.getPlayer().getName() + " 使用了指令 " + StringUtil.joinStringFrom(splitted, 0));
            c.getPlayer().dropMessage(6, "您已经清除了第 " + start + " 格到 " + end + "格的" + Column + "道具");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("@清除道具 <装备栏/消耗栏/装饰栏/其他栏/特殊栏> <开始格数> <结束格数>").toString();
        }
    }
    
    public static class jk_hm extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            c.getPlayer().RemoveHired();
            c.getPlayer().dropMessage("卡精灵商人已经解除");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("@jk_hm - 卡精灵商人解除").toString();
        }
    }
    
    public static class jcds extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            int gain = c.getPlayer().getMP();
            if (gain <= 0) {
                c.getPlayer().dropMessage("目前没有任何在线点数唷。");
                return true;
            }
            if (splitted.length < 2) {
                c.getPlayer().dropMessage("目前枫叶点数: " + c.getPlayer().getCSPoints(2));
                c.getPlayer().dropMessage("目前在线点数已经累积: " + gain + " 点，若要领取请输入 @jcds true");
            }
            else if ("true".equals((Object)splitted[1])) {
                gain = c.getPlayer().getMP();
                c.getPlayer().modifyCSPoints(2, gain, true);
                c.getPlayer().setMP(0);
                c.getPlayer().saveToDB(false, false);
                c.getPlayer().dropMessage("领取了 " + gain + " 点在线点数, 目前枫叶点数: " + c.getPlayer().getCSPoints(2));
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("@jcds - 领取在线点数").toString();
        }
    }
    
    public static class 在线点数 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            int gain = c.getPlayer().getMP();
            if (gain <= 0) {
                c.getPlayer().dropMessage("目前没有任何在线点数唷。");
                return true;
            }
            if (splitted.length < 2) {
                c.getPlayer().dropMessage("目前枫叶点数: " + c.getPlayer().getCSPoints(2));
                c.getPlayer().dropMessage("目前在线点数已经累积: " + gain + " 点，若要领取请输入 @在线点数 是");
            }
            else if ("是".equals((Object)splitted[1])) {
                gain = c.getPlayer().getMP();
                c.getPlayer().modifyCSPoints(2, gain, true);
                c.getPlayer().setMP(0);
                c.getPlayer().saveToDB(false, false);
                c.getPlayer().dropMessage("领取了 " + gain + " 点在线点数, 目前枫叶点数: " + c.getPlayer().getCSPoints(2));
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("@在线点数 - 领取在线点数").toString();
        }
    }
    
    public static class 出来吧皮卡丘 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final int id = Integer.parseInt(splitted[1]);
            final int quantity = 1;
            final int mod = Integer.parseInt(splitted[2]);
            final String npcname = GashaponFactory.getInstance().getGashaponByNpcId(mod).getName();
            final IItem item = MapleInventoryManipulator.addbyId_GachaponGM(c, id, (short)quantity);
            Broadcast.broadcastGashponmega(MaplePacketCreator.getGachaponMega(c.getPlayer().getName(), " : x" + quantity + "恭喜玩家 " + c.getPlayer().getName() + " 在" + npcname + "获得！", item, (byte)1, c.getPlayer().getClient().getChannel()));
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("你就是傻逼").toString();
        }
    }
    
    public static class 丢弃点装 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            c.sendPacket(MaplePacketCreator.enableActions());
            NPCScriptManager.getInstance().start(c, 9010000, "丢弃点装");
            return true;
        }
        
        @Override
        public String getMessage() {
            return "@" + this.getClass().getSimpleName().toLowerCase() + "丢弃点装 [点装在装备栏的位置]";
        }
    }
}
