package client.messages.commands;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import database.DBConPool;
import handling.cashshop.CashShopServer;
import client.MapleCharacterUtil;
import client.SkillFactory;
import handling.world.World.Broadcast;
import tools.MaplePacketCreator;
import tools.FileoutputUtil;
import tools.StringUtil;
import server.maps.MapleMap;
import handling.world.World.Find;
import java.util.Iterator;
import java.util.List;
import handling.channel.ChannelServer;
import client.MapleCharacter;
import java.util.LinkedList;
import client.MapleClient;
import constants.ServerConstants.PlayerGMRank;

public class PracticerCommand
{
    public static PlayerGMRank getPlayerLevelRequired() {
        return PlayerGMRank.新实习生;
    }
    
    public static class WarpT extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final List<MapleCharacter> chrs = new LinkedList<MapleCharacter>();
            final String input = splitted[1].toLowerCase();
            MapleCharacter smart_victim = null;
            final StringBuilder sb = new StringBuilder();
            for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharactersThreadSafe()) {
                    final String name = chr.getName().toLowerCase();
                    if (name.contains((CharSequence)input)) {
                        if (smart_victim == null) {
                            smart_victim = chr;
                        }
                        chrs.add(chr);
                    }
                }
            }
            if (chrs.size() > 1) {
                sb.append("寻找到的玩家共").append(chrs.size()).append("位 名单如下 : ");
                c.getPlayer().dropMessage(5, sb.toString());
                for (final MapleCharacter list : chrs) {
                    c.getPlayer().dropMessage(5, "频道" + list.getClient().getChannel() + ": " + list.getName() + "(" + list.getId() + ") -- " + list.getMapId() + "(" + list.getMap().getMapName() + ")");
                }
                return true;
            }
            if (chrs.isEmpty()) {
                c.getPlayer().dropMessage(6, "没有搜寻到名称含有 '" + input + "' 的角色");
            }
            else if (smart_victim != null) {
                c.getPlayer().changeMap(smart_victim.getMap(), smart_victim.getMap().findClosestSpawnpoint(smart_victim.getTruePosition()));
            }
            else {
                c.getPlayer().dropMessage(6, "角色不存在或是不在线上");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!WarpT [玩家名称片段] - 移动到某个地图或某个玩家所在的地方").toString();
        }
    }
    
    public static class Warp extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            String input = "";
            try {
                input = splitted[1];
            }
            catch (Exception ex2) {}
            MapleCharacter victim = c.getChannelServer().getPlayerStorage().getCharacterByName(input);
            if (victim != null) {
                if (splitted.length == 2) {
                    c.getPlayer().changeMap(victim.getMap(), victim.getMap().findClosestSpawnpoint(victim.getPosition()));
                }
                else {
                    MapleMap target = null;
                    try {
                        target = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(Integer.parseInt(splitted[2]));
                    }
                    catch (Exception ex3) {}
                    if (target == null) {
                        c.getPlayer().dropMessage(6, "地图不存在");
                    }
                    else {
                        victim.changeMap(target, target.getPortal(0));
                    }
                }
            }
            else {
                final int ch = Find.findChannel(input);
                if (ch < 0) {
                    Integer map = null;
                    MapleMap target2 = null;
                    try {
                        map = Integer.valueOf(Integer.parseInt(input));
                        target2 = c.getChannelServer().getMapFactory().getMap((int)map);
                    }
                    catch (Exception ex) {
                        if (map == null || target2 == null) {
                            c.getPlayer().dropMessage(6, "地图不存在");
                            return true;
                        }
                    }
                    if (target2 == null) {
                        c.getPlayer().dropMessage(6, "地图不存在");
                    }
                    else {
                        c.getPlayer().changeMap(target2, target2.getPortal(0));
                    }
                }
                else {
                    victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(input);
                    if (victim != null) {
                        if (victim.getMapId() != c.getPlayer().getMapId()) {
                            final MapleMap mapp = c.getChannelServer().getMapFactory().getMap(victim.getMapId());
                            c.getPlayer().changeMap(mapp, mapp.getPortal(0));
                        }
                        c.getPlayer().dropMessage(6, "正在改变频道请等待");
                        c.getPlayer().changeChannel(ch);
                    }
                    else {
                        c.getPlayer().dropMessage(6, "角色不存在");
                    }
                }
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!warp [玩家名称] <地图ID> - 移动到某个地图或某个玩家所在的地方").toString();
        }
    }
    
    public static class WarpID extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            int input = 0;
            try {
                input = Integer.parseInt(splitted[1]);
            }
            catch (Exception ex) {}
            final int ch = Find.findChannel(input);
            if (ch < 0) {
                c.getPlayer().dropMessage(6, "玩家编号[" + input + "] 不在线上");
                return true;
            }
            MapleCharacter victim = c.getChannelServer().getPlayerStorage().getCharacterById(input);
            if (victim != null) {
                if (splitted.length == 2) {
                    c.getPlayer().changeMap(victim.getMap(), victim.getMap().findClosestSpawnpoint(victim.getPosition()));
                }
                else {
                    final MapleMap target = ChannelServer.getInstance(c.getChannel()).getMapFactory().getMap(Integer.parseInt(splitted[2]));
                    if (target == null) {
                        c.getPlayer().dropMessage(6, "地图不存在");
                    }
                    else {
                        victim.changeMap(target, target.getPortal(0));
                    }
                }
            }
            else {
                try {
                    victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterById(Integer.parseInt(splitted[1]));
                    if (victim != null) {
                        if (victim.getMapId() != c.getPlayer().getMapId()) {
                            final MapleMap mapp = c.getChannelServer().getMapFactory().getMap(victim.getMapId());
                            c.getPlayer().changeMap(mapp, mapp.getPortal(0));
                        }
                        c.getPlayer().dropMessage(6, "正在改变频道请等待");
                        c.getPlayer().changeChannel(ch);
                    }
                    else {
                        c.getPlayer().dropMessage(6, "角色不存在");
                    }
                }
                catch (Exception e) {
                    c.getPlayer().dropMessage(6, "出问题了 " + e.getMessage());
                }
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!warpID [玩家编号] - 移动到某个玩家所在的地方").toString();
        }
    }
    
    public static class Ban extends CommandExecute
    {
        protected boolean hellban;
        
        public Ban() {
            this.hellban = false;
        }
        
        private String getCommand() {
            return "Ban";
        }
        
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final StringBuilder sb = new StringBuilder(c.getPlayer().getName());
            sb.append(" 封锁 ").append(splitted[1]).append(": ").append(StringUtil.joinStringFrom(splitted, 2));
            boolean offline = false;
            boolean ban = false;
            String name = "";
            String input = "null";
            try {
                name = splitted[1];
                input = splitted[2];
            }
            catch (Exception ex) {}
            final int ch = Find.findChannel(name);
            if (ch <= 0) {
                if (!c.getPlayer().OfflineBanByName(name, sb.toString())) {
                    c.getPlayer().dropMessage(6, "[" + this.getCommand() + "] 封锁失败 " + splitted[1]);
                    return true;
                }
                c.getPlayer().dropMessage(6, "[" + this.getCommand() + "] 成功离线封锁 " + splitted[1] + ".");
                ban = true;
                offline = true;
            }
            else {
                final MapleCharacter target = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
                if (target != null) {
                    if (c.getPlayer().getGMLevel() < target.getGMLevel()) {
                        c.getPlayer().dropMessage(6, "[" + this.getCommand() + "] 无法封锁GMs...");
                        return true;
                    }
                    sb.append(" (IP: ").append(target.getClient().getSessionIPAddress()).append(")");
                    if (!target.ban(sb.toString(), c.getPlayer().hasGmLevel(5), false, this.hellban)) {
                        c.getPlayer().dropMessage(6, "[" + this.getCommand() + "] 封锁失败.");
                        return true;
                    }
                    ban = true;
                    c.getPlayer().dropMessage(6, "[" + this.getCommand() + "] 成功封锁 " + target.getName() + ".");
                    target.getClient().getSession().close();
                }
            }
            FileoutputUtil.logToFile("logs/Hack/指令封锁名单.txt", "\r\n " + FileoutputUtil.NowTime() + " " + c.getPlayer().getName() + " 封锁了 " + splitted[1] + " 原因: " + sb.toString() + " 是否离线封锁: " + offline);
            final String reason = "null".equals((Object)input) ? "使用违法程式练功" : StringUtil.joinStringFrom(splitted, 2);
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[封锁系统] " + splitted[1] + " 因为" + reason + "而被管理员永久停权。"));
            final String msg = "[GM 密语] GM " + c.getPlayer().getName() + "  封锁了 " + splitted[1] + " 是否离线封锁 " + offline + " 原因：" + reason;
            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, msg));
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!ban <玩家> <原因> - 封锁玩家").toString();
        }
    }
    
    public static class BanID extends CommandExecute
    {
        protected boolean hellban;
        
        public BanID() {
            this.hellban = false;
        }
        
        private String getCommand() {
            return "Ban";
        }
        
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final StringBuilder sb = new StringBuilder(c.getPlayer().getName());
            sb.append(" 封锁 ").append(splitted[1]).append(": ").append(StringUtil.joinStringFrom(splitted, 2));
            boolean offline = false;
            boolean ban = false;
            int id = 0;
            String input = "null";
            try {
                id = Integer.parseInt(splitted[1]);
                input = splitted[2];
            }
            catch (Exception ex) {}
            final int ch = Find.findChannel(id);
            c.getPlayer();
            String name = MapleCharacter.getCharacterNameById(id);
            if (ch <= 0) {
                if (!c.getPlayer().OfflineBanById(id, sb.toString())) {
                    c.getPlayer().dropMessage(6, "[" + this.getCommand() + "] 封锁失败 " + splitted[1]);
                    return true;
                }
                c.getPlayer().dropMessage(6, "[" + this.getCommand() + "] 成功离线封锁 " + name + ".");
                ban = true;
                offline = true;
            }
            else {
                final MapleCharacter target = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterById(id);
                if (target != null) {
                    if (c.getPlayer().getGMLevel() <= target.getGMLevel() && !c.getPlayer().hasGmLevel(5)) {
                        c.getPlayer().dropMessage(6, "[" + this.getCommand() + "] 无法封锁GMs...");
                        return true;
                    }
                    sb.append(" (IP: ").append(target.getClient().getSessionIPAddress()).append(")");
                    if (!target.ban(sb.toString(), c.getPlayer().hasGmLevel(5), false, this.hellban)) {
                        c.getPlayer().dropMessage(6, "[" + this.getCommand() + "] 封锁失败.");
                        return true;
                    }
                    ban = true;
                    c.getPlayer().dropMessage(6, "[" + this.getCommand() + "] 成功封锁 " + target.getName() + ".");
                    target.getClient().getSession().close();
                    name = target.getName();
                }
            }
            FileoutputUtil.logToFile("logs/Hack/指令封锁名单.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " " + c.getPlayer().getName() + " 封锁了 " + name + " 原因: " + sb.toString() + " 是否离线封锁: " + offline);
            final String reason = "null".equals((Object)input) ? "使用违法程式练功" : StringUtil.joinStringFrom(splitted, 2);
            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(6, "[封锁系统] " + name + " 因为" + reason + "而被管理员永久停权。"));
            final String msg = "[GM 密语] GM " + c.getPlayer().getName() + "  封锁了 " + name + " 是否离线封锁 " + offline + " 原因：" + reason;
            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(6, msg));
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!BanID <玩家ID> <原因> - 封锁玩家").toString();
        }
    }
    
    public static class CnGM extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            Broadcast.broadcastGMMessage(MaplePacketCreator.serverNotice(5, "<GM聊天视窗>频道" + c.getPlayer().getClient().getChannel() + " [" + c.getPlayer().getName() + "](" + c.getPlayer().getId() + ") : " + StringUtil.joinStringFrom(splitted, 1)));
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!cngm <讯息> - GM聊天").toString();
        }
    }
    
    public static class Hide extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            SkillFactory.getSkill(9001004).getEffect(1).applyTo(c.getPlayer());
            c.getPlayer().dropMessage(6, "管理员隐藏 = 开启 \r\n 解除请输入!unhide");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!hide - 隐藏").toString();
        }
    }
    
    public static class UnHide extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            c.getPlayer().dispelBuff(9001004);
            c.getPlayer().dropMessage(6, "管理员隐藏 = 关闭 \r\n 开启请输入!hide");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!unhide - 解除隐藏").toString();
        }
    }
    
    public static class 精灵商人讯息 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final MapleCharacter p = c.getPlayer();
            boolean x = p.get_control_精灵商人();
            if (x) {
                p.control_精灵商人(false);
            }
            else {
                p.control_精灵商人(true);
            }
            x = p.get_control_精灵商人();
            p.dropMessage("目前精灵商人购买讯息状态: " + (x ? "开启 " : " 关闭 ") + "");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!精灵商人讯息 - 开启精灵商人购买讯息显示").toString();
        }
    }
    
    public static class 玩家私聊 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final MapleCharacter p = c.getPlayer();
            boolean x = p.get_control_玩家私聊();
            if (x) {
                p.control_玩家私聊(false);
            }
            else {
                p.control_玩家私聊(true);
            }
            x = p.get_control_玩家私聊();
            p.dropMessage("目前玩家私聊状态: " + (x ? "开启 " : "关闭 ") + "");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!玩家私聊 - 开启玩家讯息显示").toString();
        }
    }
    
    public static class 玩家密语 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final MapleCharacter p = c.getPlayer();
            boolean x = p.get_control_玩家密语();
            if (x) {
                p.control_玩家密语(false);
            }
            else {
                p.control_玩家密语(true);
            }
            x = p.get_control_玩家密语();
            p.dropMessage("目前玩家密语状态: " + (x ? "开启 " : "关闭 ") + "");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!玩家密语 - 开启玩家讯息显示").toString();
        }
    }
    
    public static class 好友聊天 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final MapleCharacter p = c.getPlayer();
            boolean x = p.get_control_好友聊天();
            if (x) {
                p.control_好友聊天(false);
            }
            else {
                p.control_好友聊天(true);
            }
            x = p.get_control_好友聊天();
            p.dropMessage("目前好友聊天状态: " + (x ? "开启 " : "关闭 ") + "");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!好友聊天 - 开启玩家讯息显示").toString();
        }
    }
    
    public static class 队伍聊天 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final MapleCharacter p = c.getPlayer();
            boolean x = p.get_control_队伍聊天();
            if (x) {
                p.control_队伍聊天(false);
            }
            else {
                p.control_队伍聊天(true);
            }
            x = p.get_control_队伍聊天();
            p.dropMessage("目前队伍聊天状态: " + (x ? "开启 " : "关闭 ") + "");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!队伍聊天 - 开启玩家讯息显示").toString();
        }
    }
    
    public static class 公会聊天 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final MapleCharacter p = c.getPlayer();
            boolean x = p.get_control_公会聊天();
            if (x) {
                p.control_公会聊天(false);
            }
            else {
                p.control_公会聊天(true);
            }
            x = p.get_control_公会聊天();
            p.dropMessage("目前公会聊天状态: " + (x ? "开启 " : "关闭 ") + "");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!公会聊天 - 开启玩家讯息显示").toString();
        }
    }
    
    public static class 联盟聊天 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final MapleCharacter p = c.getPlayer();
            boolean x = p.get_control_联盟聊天();
            if (x) {
                p.control_联盟聊天(false);
            }
            else {
                p.control_联盟聊天(true);
            }
            x = p.get_control_联盟聊天();
            p.dropMessage("目前联盟聊天状态: " + (x ? "开启 " : "关闭 ") + "");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!联盟聊天 - 开启玩家讯息显示").toString();
        }
    }
    
    public static class online extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            int total = 0;
            final int curConnected = c.getChannelServer().getConnectedClients();
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            c.getPlayer().dropMessage(6, "频道: " + c.getChannelServer().getChannel() + " 线上人数: " + curConnected);
            total += curConnected;
            for (final MapleCharacter chr : c.getChannelServer().getPlayerStorage().getAllCharactersThreadSafe()) {
                if (chr != null && c.getPlayer().getGMLevel() >= chr.getGMLevel()) {
                    final StringBuilder ret = new StringBuilder();
                    ret.append(" 角色暱称 ");
                    ret.append(StringUtil.getRightPaddedStr(chr.getName(), ' ', 13));
                    ret.append(" ID: ");
                    ret.append(StringUtil.getRightPaddedStr(chr.getId() + "", ' ', 5));
                    ret.append(" 等级: ");
                    ret.append(StringUtil.getRightPaddedStr(String.valueOf((int)chr.getLevel()), ' ', 3));
                    ret.append(" 职业: ");
                    ret.append(StringUtil.getRightPaddedStr(String.valueOf((int)chr.getJob()), ' ', 4));
                    if (chr.getMap() == null) {
                        continue;
                    }
                    ret.append(" 地图: ");
                    ret.append(chr.getMapId()).append("(").append(chr.getMap().getMapName()).append(")");
                    c.getPlayer().dropMessage(6, ret.toString());
                }
            }
            c.getPlayer().dropMessage(6, "当前频道总计线上人数: " + total);
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            final int channelOnline = c.getChannelServer().getConnectedClients();
            int totalOnline = 0;
            for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                totalOnline += cserv.getConnectedClients();
            }
            c.getPlayer().dropMessage(6, "当前伺服器总计线上人数: " + totalOnline + "个");
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!online - 查看线上人数").toString();
        }
    }
    
    public static class 全频道地图人数 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            int total = 0;
            if (splitted.length < 2) {
                return false;
            }
            final int mapId = Integer.parseInt(splitted[1]);
            c.getPlayer().dropMessage(6, "---------------------------------------------------------------------------------------");
            for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                final int curConnected = cserv.getConnectedClients();
                c.getPlayer().dropMessage(6, "频道: " + cserv.getChannel());
                total += curConnected;
                for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                    if (chr != null && c.getPlayer().getGMLevel() >= chr.getGMLevel()) {
                        final StringBuilder ret = new StringBuilder();
                        if (chr.getMapId() == mapId) {
                            ret.append(" 角色暱称 ");
                            ret.append(StringUtil.getRightPaddedStr(chr.getName(), ' ', 13));
                            ret.append(" ID: ");
                            ret.append(StringUtil.getRightPaddedStr(chr.getId() + "", ' ', 5));
                            ret.append(" 等级: ");
                            ret.append(StringUtil.getRightPaddedStr(String.valueOf((int)chr.getLevel()), ' ', 3));
                            ret.append(" 职业: ");
                            ret.append(StringUtil.getRightPaddedStr(String.valueOf((int)chr.getJob()), ' ', 4));
                            if (chr.getMap() != null) {
                                ret.append(" 地图: ");
                                ret.append(chr.getMapId());
                                ret.append(" - ");
                                ret.append(chr.getMap().getMapName());
                            }
                        }
                        c.getPlayer().dropMessage(6, ret.toString());
                    }
                }
            }
            c.getPlayer().dropMessage(6, "---------------------------------------------------------------------------------------");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!全频道地图人数 - 全频道地图人数").toString();
        }
    }
    
    public static class onlineGM extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            int channelOnline = 0;
            int totalOnline = 0;
            int GmInChannel = 0;
            List<MapleCharacter> chrs = new LinkedList<MapleCharacter>();
            for (final MapleCharacter chr : c.getChannelServer().getPlayerStorage().getAllCharactersThreadSafe()) {
                if (chr.getGMLevel() > 0) {
                    ++channelOnline;
                }
            }
            for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (final MapleCharacter chr2 : cserv.getPlayerStorage().getAllCharactersThreadSafe()) {
                    if (chr2 != null && chr2.getGMLevel() > 0) {
                        ++totalOnline;
                    }
                }
            }
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                for (final MapleCharacter chr2 : cserv.getPlayerStorage().getAllCharactersThreadSafe()) {
                    if (chr2 != null && chr2.getGMLevel() > 0) {
                        chrs.add(chr2);
                    }
                }
                GmInChannel = chrs.size();
                if (GmInChannel > 0) {
                    c.getPlayer().dropMessage(6, "频道: " + cserv.getChannel() + " 线上GM人数: " + GmInChannel);
                    for (final MapleCharacter chr2 : chrs) {
                        if (chr2 != null) {
                            final StringBuilder ret = new StringBuilder();
                            ret.append(" GM暱称 ");
                            ret.append(StringUtil.getRightPaddedStr(chr2.getName(), ' ', 13));
                            ret.append(" ID: ");
                            ret.append(StringUtil.getRightPaddedStr(chr2.getId() + "", ' ', 5));
                            ret.append(" 权限: ");
                            ret.append(StringUtil.getRightPaddedStr(String.valueOf(chr2.getGMLevel()), ' ', 3));
                            c.getPlayer().dropMessage(6, ret.toString());
                        }
                    }
                }
                chrs = new LinkedList<MapleCharacter>();
            }
            c.getPlayer().dropMessage(6, "当前频道总计GM线上人数: " + channelOnline);
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            c.getPlayer().dropMessage(6, "当前伺服器GM总计线上人数: " + totalOnline + "个");
            c.getPlayer().dropMessage(6, "-------------------------------------------------------------------------------------");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!onlineGM - 查看线上人数GM").toString();
        }
    }
    
    public static class WarpHere extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            MapleCharacter victim = c.getChannelServer().getPlayerStorage().getCharacterByName(splitted[1]);
            if (victim != null) {
                victim.changeMap(c.getPlayer().getMap(), c.getPlayer().getMap().findClosestSpawnpoint(c.getPlayer().getPosition()));
            }
            else {
                final int ch = Find.findChannel(splitted[1]);
                if (ch < 0) {
                    c.getPlayer().dropMessage(5, "找不到");
                }
                else {
                    victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(splitted[1]);
                    c.getPlayer().dropMessage(5, "正在把玩家传到这来");
                    victim.dropMessage(5, "正在传送到GM那边");
                    if (victim.getMapId() != c.getPlayer().getMapId()) {
                        final MapleMap mapp = victim.getClient().getChannelServer().getMapFactory().getMap(c.getPlayer().getMapId());
                        victim.changeMap(mapp, mapp.getPortal(0));
                    }
                    victim.changeChannel(c.getChannel());
                }
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!warphere 把玩家传送到这裡").toString();
        }
    }
    
    public static class Whoshere extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            StringBuilder builder = new StringBuilder("在地图上的玩家 : ").append(c.getPlayer().getMap().getCharactersThreadsafe().size()).append(", ");
            for (final MapleCharacter chr : c.getPlayer().getMap().getCharactersThreadsafe()) {
                if (builder.length() > 150) {
                    builder.setLength(builder.length() - 2);
                    c.getPlayer().dropMessage(6, builder.toString());
                    builder = new StringBuilder();
                }
                builder.append(MapleCharacterUtil.makeMapleReadable(chr.getName()));
                builder.append(", ");
            }
            builder.setLength(builder.length() - 2);
            c.getPlayer().dropMessage(6, builder.toString());
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!Whoshere - 查地图上玩家").toString();
        }
    }
    
    public static class 吸怪信息 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final MapleCharacter p = c.getPlayer();
            boolean x = p.get_control_吸怪信息();
            if (x) {
                p.control_吸怪信息(false);
            }
            else {
                p.control_吸怪信息(true);
            }
            x = p.get_control_吸怪信息();
            p.dropMessage("目前吸怪讯息状态: " + (x ? "开启 " : "关闭 ") + "");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!吸怪讯息 - 开启玩家吸怪讯息讯息显示").toString();
        }
    }
    
    public static class UnHellBan extends UnBan
    {
        public UnHellBan() {
            this.hellban = true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!UnHellBan <玩家> - 解锁玩家").toString();
        }
    }
    
    public static class UnBan extends CommandExecute
    {
        protected boolean hellban;
        
        public UnBan() {
            this.hellban = false;
        }
        
        private String getCommand() {
            return "UnBan";
        }
        
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            byte ret;
            if (this.hellban) {
                ret = MapleClient.unHellban(splitted[1]);
            }
            else {
                ret = MapleClient.unban(splitted[1]);
            }
            if (ret == -2) {
                c.getPlayer().dropMessage(6, "[" + this.getCommand() + "] SQL 错误");
            }
            else if (ret == -1) {
                c.getPlayer().dropMessage(6, "[" + this.getCommand() + "] 目标玩家不存在");
            }
            else {
                c.getPlayer().dropMessage(6, "[" + this.getCommand() + "] 成功解除锁定");
            }
            final byte ret_ = MapleClient.unbanIPMacs(splitted[1]);
            if (ret_ == -2) {
                c.getPlayer().dropMessage(6, "[" + this.getCommand() + "] SQL 错误.");
            }
            else if (ret_ == -1) {
                c.getPlayer().dropMessage(6, "[" + this.getCommand() + "] 角色不存在.");
            }
            else if (ret_ == 0) {
                c.getPlayer().dropMessage(6, "[" + this.getCommand() + "] No IP or Mac with that character exists!");
            }
            else if (ret_ == 1) {
                c.getPlayer().dropMessage(6, "[" + this.getCommand() + "] IP或Mac已解锁其中一个.");
            }
            else if (ret_ == 2) {
                c.getPlayer().dropMessage(6, "[" + this.getCommand() + "] IP以及Mac已成功解锁.");
            }
            if (ret_ == 1 || ret_ == 2) {
                FileoutputUtil.logToFile("logs/Hack/ban/解除封锁名单.txt", "\r\n " + FileoutputUtil.NowTime() + " IP: " + c.getSession().remoteAddress().toString().split(":")[0] + " " + c.getPlayer().getName() + " 解锁了 " + splitted[1]);
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!unban <玩家> - 解锁玩家").toString();
        }
    }
    
    public static class DCID extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final int id = Integer.parseInt(splitted[1]);
            final int ch = Find.findChannel(id);
            if (ch <= 0) {
                c.getPlayer().dropMessage("该玩家不在线上");
                return true;
            }
            final MapleCharacter victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterById(id);
            if (victim != null) {
                victim.getClient().disconnect(true, false);
                victim.getClient().getSession().close();
            }
            else {
                c.getPlayer().dropMessage("该玩家不在线上");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!DCID <玩家ID> - 让玩家断线").toString();
        }
    }
    
    public static class DC extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final String name = splitted[1];
            final int ch = Find.findChannel(name);
            if (ch <= 0) {
                c.getPlayer().dropMessage("该玩家不在线上1");
                return true;
            }
            final MapleCharacter victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
            if (victim != null) {
                victim.getClient().disconnect(true, false);
                victim.getClient().getSession().close();
            }
            else {
                c.getPlayer().dropMessage("该玩家不在线上2");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!dc <玩家> - 让玩家断线").toString();
        }
    }
    
    public static class 商城DC extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final String name = splitted[1];
            final int ch = Find.findChannel(name);
            c.getPlayer().dropMessage("玩家在 " + ch);
            try {
                final MapleCharacter victim = CashShopServer.getPlayerStorage().getCharacterByName(name);
                if (victim != null) {
                    victim.getClient().disconnect(true, true);
                    victim.getClient().getSession().close();
                    c.getPlayer().dropMessage(" 玩家 " + name + "商城解卡成功");
                }
                else {
                    c.getPlayer().dropMessage("该玩家不在商城线上");
                }
            }
            catch (Exception e) {
                c.getPlayer().dropMessage("异常抛出：玩家解卡失败");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!商城DC <玩家> - 让玩家断线").toString();
        }
    }
    
    public static class 商城DC2 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final String name = splitted[1];
            final int ch = Find.findChannel(name);
            c.getPlayer().dropMessage("玩家在 " + ch);
            try {
                final MapleCharacter victim1 = MapleCharacter.getCharacterByName(name);
                if (victim1 != null) {
                    victim1.getClient().disconnect(true, true);
                    victim1.getClient().getSession().close();
                    c.getPlayer().dropMessage(" 玩家 " + name + "商城解卡成功");
                }
                else {
                    c.getPlayer().dropMessage("该玩家不在商城线上");
                }
            }
            catch (Exception e) {
                c.getPlayer().dropMessage("异常抛出：玩家解卡失败");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!商城DC2 <玩家> - 让玩家断线").toString();
        }
    }
    
    public static class 特殊商城DC extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final String name = splitted[1];
            final int ch = Find.findChannel(name);
            c.getPlayer().dropMessage("玩家在 " + ch);
            try {
                final MapleCharacter victim = MapleCharacter.getCharacterByName(name);
                if (victim != null) {
                    CashShopServer.getPlayerStorage().deregisterPlayer(victim.getId(), victim.getName());
                    c.getPlayer().dropMessage(" 玩家 " + name + "商城解卡成功");
                }
                else {
                    c.getPlayer().dropMessage(5, "找不到此玩家.");
                }
            }
            catch (Exception e) {
                c.getPlayer().dropMessage("异常抛出：玩家解卡失败");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!特殊商城DC <玩家> - 让玩家断线").toString();
        }
    }
    
    public static class 特殊商城DC2 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final String name = splitted[1];
            final int ch = Find.findChannel(name);
            c.getPlayer().dropMessage("玩家在 " + ch);
            try {
                final MapleCharacter victim = CashShopServer.getPlayerStorage().getCharacterByName(name);
                if (victim != null) {
                    CashShopServer.getPlayerStorage().deregisterPlayer(victim.getId(), victim.getName());
                    c.getPlayer().dropMessage(" 玩家 " + name + "商城解卡成功");
                }
                else {
                    c.getPlayer().dropMessage("该玩家不在商城线上");
                }
            }
            catch (Exception e) {
                c.getPlayer().dropMessage("异常抛出：玩家解卡失败");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!商城DC2 <玩家> - 让玩家断线").toString();
        }
    }
    
    public static class 频道DC extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final String name = splitted[1];
            final int ch = Find.findChannel(name);
            c.getPlayer().dropMessage("玩家在 " + ch);
            for (final ChannelServer cs : ChannelServer.getAllInstances()) {
                final MapleCharacter character = cs.getPlayerStorage().getCharacterByName(name);
                if (character != null) {
                    character.getClient().disconnect(true, false);
                    character.getClient().getSession().close();
                    c.getPlayer().dropMessage(" 玩家 " + name + "频道解卡成功");
                }
                else {
                    c.getPlayer().dropMessage("该玩家不在频道线上");
                }
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!频道DC <玩家> - 让玩家断线").toString();
        }
    }
    
    public static class 特殊频道DC extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final String name = splitted[1];
            final int ch = Find.findChannel(name);
            c.getPlayer().dropMessage("玩家在 " + ch);
            for (final ChannelServer cs : ChannelServer.getAllInstances()) {
                final MapleCharacter character = cs.getPlayerStorage().getCharacterByName(name);
                if (character != null) {
                    character.getClient().disconnect(true, false);
                    cs.removePlayer(character.getId(), character.getName());
                    character.getClient().getSession().close();
                    c.getPlayer().dropMessage(" 玩家 " + name + "频道解卡成功");
                }
                else {
                    c.getPlayer().dropMessage("该玩家不在频道线上");
                }
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!特殊频道DC <玩家> - 让玩家断线").toString();
        }
    }
    
    public static class 特殊DC extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final String name = splitted[1];
            final int ch = Find.findChannel(name);
            c.getPlayer().dropMessage("玩家在 " + ch);
            for (final ChannelServer cs : ChannelServer.getAllInstances()) {
                final MapleCharacter character = cs.getPlayerStorage().getCharacterByName(name);
                if (character != null) {
                    character.getClient().disconnect(true, false);
                    character.getClient().getSession().close();
                    c.getPlayer().dropMessage(" 玩家 " + name + "频道解卡成功");
                }
                else {
                    c.getPlayer().dropMessage("该玩家不在频道线上");
                }
            }
            final MapleCharacter charactercs = CashShopServer.getPlayerStorage().getCharacterByName(name);
            if (charactercs != null) {
                charactercs.getClient().disconnect(true, true);
                charactercs.getClient().getSession().close();
                c.getPlayer().dropMessage(" 玩家 " + name + "商城解卡成功2");
            }
            else {
                c.getPlayer().dropMessage("该玩家不在商城线上2");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!特殊DC <玩家> - 让玩家断线").toString();
        }
    }
    
    public static class 卡登处理 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final String playername = splitted[1];
            final int playerid = MapleCharacter.getCharacterIdByName(playername);
            if (playerid == -1) {
                c.getPlayer().dropMessage("玩家[" + playername + "]不存在于资料库内。");
                return true;
            }
            final MapleCharacter victim = MapleCharacter.getCharacterById(playerid);
            if (victim != null) {
                victim.updateNewState(0, victim.getAccountID());
                c.getPlayer().dropMessage("修改玩家[" + playername + "]登录状态成功。");
            }
            else {
                c.getPlayer().dropMessage("玩家[" + playername + "]不存在于资料库内。");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!卡登处理 <玩家名字> - 修改资料库玩家登录状态").toString();
        }
    }
    
    public static class Job extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            int jobid = 0;
            try {
                jobid = Integer.parseInt(splitted[1]);
            }
            catch (Exception ex) {
                return false;
            }
            c.getPlayer().changeJob(jobid);
            c.getPlayer().dispelDebuffs();
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!job <职业代码> - 更换职业").toString();
        }
    }
    
    public static class 吸怪自动传送 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            c.getPlayer().setAuto吸怪(!c.getPlayer().getAuto吸怪());
            c.getPlayer().dropMessage("自动吸怪传送已经: " + (c.getPlayer().getAuto吸怪() ? "开启" : "关闭") + "");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!吸怪自动传送 - 吸怪自动传送").toString();
        }
    }
    
    public static class WhereAmI extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            c.getPlayer().dropMessage(5, "目前地图 " + c.getPlayer().getMap().getId() + "座标 (" + String.valueOf(c.getPlayer().getPosition().x) + " , " + String.valueOf(c.getPlayer().getPosition().y) + ")");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!whereami - 目前地图").toString();
        }
    }
    
    public static class BanStatus extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final String name = splitted[1];
            String mac = "";
            String ip = "";
            int acid = 0;
            boolean Systemban = false;
            boolean ACbanned = false;
            boolean IPbanned = false;
            boolean MACbanned = false;
            String reason = null;
            try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
                PreparedStatement ps = con.prepareStatement("select accountid from characters where name = ?");
                ps.setString(1, name);
                try (final ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        acid = rs.getInt("accountid");
                    }
                }
                ps = con.prepareStatement("select banned, banreason, macs, Sessionip from accounts where id = ?");
                ps.setInt(1, acid);
                try (final ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Systemban = (rs.getInt("banned") == 2);
                        ACbanned = (rs.getInt("banned") == 1 || rs.getInt("banned") == 2);
                        reason = rs.getString("banreason");
                        mac = rs.getString("macs");
                        ip = rs.getString("Sessionip");
                    }
                }
                ps.close();
            }
            catch (Exception e) {
                FileoutputUtil.outError("logs/资料库异常.txt", (Throwable)e);
            }
            if (reason == null || reason.isEmpty()) {
                reason = "无";
            }
            if (c.isBannedIP(ip)) {
                IPbanned = true;
            }
            if (c.hasBannedMac()) {
                MACbanned = true;
            }
            c.getPlayer().dropMessage("玩家[" + name + "] 帐号ID[" + acid + "]是否被封锁: " + (ACbanned ? "是" : "否") + (Systemban ? "(系统自动封锁)" : "") + ", 原因: " + reason);
            c.getPlayer().dropMessage("IP: " + ip + " 是否在封锁IP名单: " + (IPbanned ? "是" : "否"));
            for (final String SingleMac : mac.split(", ")) {
                c.getPlayer().dropMessage("MAC: " + SingleMac + " 是否在封锁MAC名单: " + (c.isBannedMac(SingleMac) ? "是" : "否"));
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!BanStatus <玩家名称> - 查看玩家是否被封锁及原因").toString();
        }
    }
    
    public static class banMac extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final String mac = splitted[1];
            if (mac.equalsIgnoreCase("00-00-00-00-00-00") || mac.length() != 17) {
                c.getPlayer().dropMessage("封锁MAC失败，可能为格式错误或是长度错误 Ex: 00-00-00-00-00-00 ");
                return true;
            }
            c.getPlayer().dropMessage("封锁MAC [" + mac + "] 成功");
            try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection();
                 final PreparedStatement ps = con.prepareStatement("INSERT INTO macbans (mac) VALUES (?)")) {
                ps.setString(1, mac);
                ps.executeUpdate();
                ps.close();
            }
            catch (SQLException e) {
                System.err.println("Error banning MACs" + (Object)e);
                FileoutputUtil.outError("logs/资料库异常.txt", (Throwable)e);
                return true;
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!BanMAC <MAC> - 封锁MAC ").toString();
        }
    }
    
    public static class 个人公告 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 4) {
                return false;
            }
            final int type = Integer.parseInt(splitted[1]);
            final String name = splitted[2];
            final String str = splitted[3];
            if (type != 0 && type != 1 && type != 2 && type != 3) {
                c.getPlayer().dropMessage("类型错误");
                return true;
            }
            for (final ChannelServer cs : ChannelServer.getAllInstances()) {
                final MapleCharacter character = cs.getPlayerStorage().getCharacterByName(name);
                if (character != null) {
                    byte[] p = null;
                    switch (type) {
                        case 0: {
                            p = MaplePacketCreator.serverNotice(6, "[公告事项] " + str);
                            break;
                        }
                        case 1: {
                            p = MaplePacketCreator.serverNotice(1, str);
                            break;
                        }
                        case 2: {
                            p = MaplePacketCreator.serverNotice(5, str);
                            break;
                        }
                        case 3: {
                            p = MaplePacketCreator.getNPCTalk(2007, (byte)0, str, "00 00", (byte)0);
                            break;
                        }
                    }
                    character.getClient().getSession().writeAndFlush((Object)p);
                    c.getPlayer().dropMessage(" 给玩家 " + name + "发送公告成功。");
                }
                else {
                    c.getPlayer().dropMessage("该玩家不在频道线上");
                }
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!个人公告 <类型> <玩家> <内容>- 给玩家发送公告").toString();
        }
    }
}
