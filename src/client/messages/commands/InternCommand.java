package client.messages.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import client.MapleCharacter;
import client.MapleCharacterUtil;
import client.MapleClient;
import client.MapleStat;
import client.messages.commands.PracticerCommand.Ban;
import constants.ServerConstants.PlayerGMRank;
import database.DBConPool;
import handling.channel.ChannelServer;
import handling.world.CheaterData;
import handling.world.World;
import handling.world.World.Find;
import server.MaplePortal;
import server.maps.MapleMap;
import tools.FilePrinter;
import tools.FileoutputUtil;
import tools.StringUtil;

public class InternCommand
{
    public static PlayerGMRank getPlayerLevelRequired() {
        return PlayerGMRank.巡逻者;
    }
    
    public static class 发勳章 extends CommandExecute
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
            final int accountid = victim.getAccountID();
            final int id = this.createPlayer(accountid, playerid, playername);
            if (id < 0) {
                if (id == -3) {
                    c.getPlayer().dropMessage("帐号ID[" + accountid + "]已经在领取勳章清单内。");
                }
                else if (id == -2) {
                    c.getPlayer().dropMessage("玩家[" + playername + "]已经在领取勳章清单内。");
                }
                else if (id == -1) {
                    c.getPlayer().dropMessage("发生未知的错误。");
                }
                return true;
            }
            c.getPlayer().dropMessage("玩家[" + playername + "]已经成功添加到领取勳章清单内(编号" + id + ")。");
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!发勳章 <玩家名称> - 将勳章领取名单添加玩家").toString();
        }
        
        public int createPlayer(final int accountid, final int id, final String name) {
            try (final Connection con = (Connection)DBConPool.getInstance().getDataSource().getConnection()) {
                PreparedStatement ps = con.prepareStatement("SELECT id FROM RCmedals WHERE name = ?");
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();
                if (rs.first()) {
                    rs.close();
                    ps.close();
                    return -2;
                }
                ps.close();
                rs.close();
                ps = con.prepareStatement("SELECT accountid FROM RCmedals WHERE accountid = ?");
                ps.setInt(1, accountid);
                rs = ps.executeQuery();
                if (rs.first()) {
                    rs.close();
                    ps.close();
                    return -3;
                }
                ps.close();
                rs.close();
                ps = con.prepareStatement("INSERT INTO RCmedals (`accountid`,`id`, `name`, `amount`) VALUES (?, ?, ?, ?)", 1);
                ps.setInt(1, accountid);
                ps.setInt(2, id);
                ps.setString(3, name);
                ps.setInt(4, 1);
                ps.execute();
                rs = ps.getGeneratedKeys();
                int ret = 0;
                if (rs.next()) {
                    ret = rs.getInt(1);
                }
                rs.close();
                ps.close();
                return ret;
            }
            catch (Exception ex) {
                FilePrinter.printError("InternCommand.txt", (Throwable)ex, "createPlayer(" + name + ")");
                FileoutputUtil.outError("logs/资料库异常.txt", (Throwable)ex);
                return -1;
            }
        }
    }
    
    public static class Mute extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            String name = "";
            try {
                name = splitted[1];
            }
            catch (Exception ex) {}
            final int ch = Find.findChannel(name);
            if (ch > 0) {
                final MapleCharacter victim = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(splitted[1]);
                victim.canTalk(!victim.getCanTalk());
                c.getPlayer().dropMessage("玩家[" + victim.getName() + "] 目前已经" + (victim.getCanTalk() ? "可以说话" : "闭嘴"));
            }
            else {
                c.getPlayer().dropMessage("玩家[" + splitted[1] + "] 目前不在线上或是正在购物商城");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!Mute \t玩家名称 - 让玩家闭嘴或可以说话").toString();
        }
    }
    
    public static class MuteMap extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            for (final MapleCharacter chr : c.getPlayer().getMap().getCharactersThreadsafe()) {
                chr.canTalk(!chr.getCanTalk());
                final StringBuilder ret = new StringBuilder();
                ret.append(" 角色暱称 ");
                ret.append(StringUtil.getRightPaddedStr(chr.getName(), ' ', 13));
                ret.append(" ID: ");
                ret.append(StringUtil.getRightPaddedStr(chr.getId() + "", ' ', 5));
                ret.append(" 目前已经: ");
                ret.append(chr.getCanTalk() ? "可以说话" : "闭嘴");
                c.getPlayer().dropMessage(ret.toString());
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!MuteMap \t- 让地图玩家闭嘴或可以说话").toString();
        }
    }
    
    public static class HellBan extends Ban
    {
        public HellBan() {
            this.hellban = true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!hellban <玩家名称> <原因> - hellban").toString();
        }
    }
    
    public static class CC extends ChangeChannel
    {
        @Override
        public String getMessage() {
            return new StringBuilder().append("!cc <频道> - 更换频道").toString();
        }
    }
    
    public static class ChangeChannel extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final int cc = Integer.parseInt(splitted[1]);
            if (c.getChannel() != cc) {
                c.getPlayer().changeChannel(cc);
            }
            else {
                c.getPlayer().dropMessage(5, "请输入正确的频道。");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!changechannel <频道> - 更换频道").toString();
        }
    }
    
    public static class 角色讯息 extends spy
    {
    }
    
    public static class spy extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final String name = splitted[1];
            final MapleCharacter victim = MapleCharacter.getCharacterByName(name);
            final int ch = Find.findChannel(name);
            if (victim != null) {
                if (victim.getGMLevel() > c.getPlayer().getGMLevel()) {
                    c.getPlayer().dropMessage(5, "你不能查看比你高权限的人!");
                }
                else {
                    int mesoInStorage = 0;
                    try {
                        if (victim.getStorage() != null) {
                            mesoInStorage = victim.getStorage().getMeso();
                        }
                    }
                    catch (Exception ex) {}
                    c.getPlayer().dropMessage(5, "此玩家状态:");
                    c.getPlayer().dropMessage(5, "玩家名称: " + victim.getName() + " 玩家编号: " + victim.getId() + " 帐号: " + victim.getClient().getAccountName() + " 帐号ID: " + victim.getAccountID());
                    c.getPlayer().dropMessage(5, "玩家权限: " + victim.getGMLevel() + " 等级: " + (int)victim.getLevel() + " 职业: " + (int)victim.getJob() + " 名声: " + (int)victim.getFame());
                    c.getPlayer().dropMessage(5, "地图: " + victim.getMapId() + " - " + victim.getMap().getMapName());
                    c.getPlayer().dropMessage(5, "目前HP: " + (int)victim.getStat().getHp() + " 目前MP: " + (int)victim.getStat().getMp());
                    c.getPlayer().dropMessage(5, "最大HP: " + (int)victim.getStat().getMaxHp() + " 最大MP: " + (int)victim.getStat().getMaxMp());
                    c.getPlayer().dropMessage(5, "力量: " + (int)victim.getStat().getStr() + "  ||  敏捷: " + (int)victim.getStat().getDex() + "  ||  智力: " + (int)victim.getStat().getInt() + "  ||  幸运: " + (int)victim.getStat().getLuk());
                    c.getPlayer().dropMessage(5, "物理攻击: " + victim.getStat().getTotalWatk() + "  ||  魔法攻击: " + victim.getStat().getTotalMagic());
                    c.getPlayer().dropMessage(5, "经验倍率: " + victim.getStat().expBuff + " 金钱倍率: " + victim.getStat().mesoBuff + " 掉宝倍率: " + victim.getStat().dropBuff);
                    c.getPlayer().dropMessage(5, "GASH: " + victim.getOfflineAcash(victim) + " 枫叶点数: " + victim.getCSPoints(2) + " 枫币: " + victim.getMeso() + " 仓库枫币 " + mesoInStorage);
                    c.getPlayer().dropMessage(5, "赞助红利: " + victim.getCSPoints(3));
                    c.getPlayer().dropMessage(5, "赞助总额: " + victim.getMoneyAll() + " 新台币");
                    if (ch <= 0) {
                        c.getPlayer().dropMessage(5, "该角色为离线状态");
                    }
                    else {
                        c.getPlayer().dropMessage(5, "IP:" + victim.getClient().getSessionIPAddress() + " 目前MAC:" + victim.getNowMacs() + " 所有MAC:" + (Object)victim.getClient().getMacs());
                        c.getPlayer().dropMessage(5, "对伺服器延迟: " + victim.getClient().getLatency());
                    }
                }
            }
            else {
                c.getPlayer().dropMessage(5, "找不到此玩家.");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return "!" + this.getClass().getSimpleName().toLowerCase() + " <玩家名字> - 观察玩家";
        }
    }
    
    public static class spyid extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final int id = Integer.parseInt(splitted[1]);
            final MapleCharacter victim = MapleCharacter.getCharacterById(id);
            final int ch = Find.findChannel(id);
            if (victim != null) {
                if (victim.getGMLevel() > c.getPlayer().getGMLevel()) {
                    c.getPlayer().dropMessage(5, "你不能查看比你高权限的人!");
                }
                else {
                    int mesoInStorage = 0;
                    try {
                        if (victim.getStorage() != null) {
                            mesoInStorage = victim.getStorage().getMeso();
                        }
                    }
                    catch (Exception ex) {}
                    c.getPlayer().dropMessage(5, "此玩家状态:");
                    c.getPlayer().dropMessage(5, "玩家名称: " + victim.getName() + " 玩家编号: " + victim.getId() + " 帐号: " + victim.getClient().getAccountName() + " 帐号ID: " + victim.getAccountID());
                    c.getPlayer().dropMessage(5, "玩家权限: " + victim.getGMLevel() + " 等级: " + (int)victim.getLevel() + " 职业: " + (int)victim.getJob() + " 名声: " + (int)victim.getFame());
                    c.getPlayer().dropMessage(5, "地图: " + victim.getMapId() + " - " + victim.getMap().getMapName());
                    c.getPlayer().dropMessage(5, "目前HP: " + (int)victim.getStat().getHp() + " 目前MP: " + (int)victim.getStat().getMp());
                    c.getPlayer().dropMessage(5, "最大HP: " + (int)victim.getStat().getMaxHp() + " 最大MP: " + (int)victim.getStat().getMaxMp());
                    c.getPlayer().dropMessage(5, "力量: " + (int)victim.getStat().getStr() + "  ||  敏捷: " + (int)victim.getStat().getDex() + "  ||  智力: " + (int)victim.getStat().getInt() + "  ||  幸运: " + (int)victim.getStat().getLuk());
                    c.getPlayer().dropMessage(5, "物理攻击: " + victim.getStat().getTotalWatk() + "  ||  魔法攻击: " + victim.getStat().getTotalMagic());
                    c.getPlayer().dropMessage(5, "经验倍率: " + victim.getStat().expBuff + " 金钱倍率: " + victim.getStat().mesoBuff + " 掉宝倍率: " + victim.getStat().dropBuff);
                    c.getPlayer().dropMessage(5, "GASH: " + victim.getOfflineAcash(victim) + " 枫叶点数: " + victim.getCSPoints(2) + " 枫币: " + victim.getMeso() + " 仓库枫币 " + mesoInStorage);
                    c.getPlayer().dropMessage(5, "赞助红利: " + victim.getCSPoints(3));
                    c.getPlayer().dropMessage(5, "赞助总额: " + victim.getMoneyAll() + " 新台币");
                    if (ch <= 0) {
                        c.getPlayer().dropMessage(5, "该角色为离线状态");
                    }
                    else {
                        c.getPlayer().dropMessage(5, "IP:" + victim.getClient().getSessionIPAddress() + " 目前MAC:" + victim.getNowMacs() + " 所有MAC:" + (Object)victim.getClient().getMacs());
                        c.getPlayer().dropMessage(5, "对伺服器延迟: " + victim.getClient().getLatency());
                    }
                }
            }
            else {
                c.getPlayer().dropMessage(5, "找不到此玩家.");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return "!" + this.getClass().getSimpleName().toLowerCase() + " <玩家ID> - 观察玩家";
        }
    }
    
    public static class Map extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
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
            }
            catch (Exception e) {
                c.getPlayer().dropMessage(5, "Error: " + e.getMessage());
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!map <mapid|charname> [portal] - 传送到某地图/人").toString();
        }
    }
    
    public static class WarpMap extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            try {
                final MapleMap target = c.getChannelServer().getMapFactory().getMap(Integer.parseInt(splitted[1]));
                if (target == null) {
                    c.getPlayer().dropMessage(6, "地图不存在。");
                    return false;
                }
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
            return new StringBuilder().append("!WarpMap \t地图代码\u0010 - 把地图上的人全部传到那张地图").toString();
        }
    }
    
    public static class Debug extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            c.getPlayer().setDebugMessage(!c.getPlayer().getDebugMessage());
            c.getPlayer().dropMessage("DeBug讯息已经" + (c.getPlayer().getDebugMessage() ? "开启" : "关闭"));
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!Debug - 开启Debug讯息").toString();
        }
    }
    
    public static class CharInfo extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final StringBuilder builder = new StringBuilder();
            final String name = splitted[1];
            final int ch = Find.findChannel(name);
            if (ch <= 0) {
                c.getPlayer().dropMessage(6, "玩家必须上线");
                return true;
            }
            final MapleCharacter other = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
            if (other == null) {
                builder.append("角色不存在");
                c.getPlayer().dropMessage(6, builder.toString());
            }
            else {
                if (other.getClient().getLastPing() <= 0L) {
                    other.getClient().sendPing();
                }
                builder.append(MapleClient.getLogMessage(other, ""));
                builder.append(" 在 ").append(other.getPosition().x);
                builder.append(" /").append(other.getPosition().y);
                builder.append(" || 血量 : ");
                builder.append((int)other.getStat().getHp());
                builder.append(" /");
                builder.append((int)other.getStat().getCurrentMaxHp());
                builder.append(" || 魔量 : ");
                builder.append((int)other.getStat().getMp());
                builder.append(" /");
                builder.append((int)other.getStat().getCurrentMaxMp());
                builder.append(" || 物理攻击力 : ");
                builder.append(other.getStat().getTotalWatk());
                builder.append(" || 魔法攻击力 : ");
                builder.append(other.getStat().getTotalMagic());
                builder.append(" || 最高攻击 : ");
                builder.append(other.getStat().getCurrentMaxBaseDamage());
                builder.append(" || 攻击%数 : ");
                builder.append(other.getStat().dam_r);
                builder.append(" || BOSS攻击%数 : ");
                builder.append(other.getStat().bossdam_r);
                builder.append(" || 力量 : ");
                builder.append((int)other.getStat().getStr());
                builder.append(" || 敏捷 : ");
                builder.append((int)other.getStat().getDex());
                builder.append(" || 智力 : ");
                builder.append((int)other.getStat().getInt());
                builder.append(" || 幸运 : ");
                builder.append((int)other.getStat().getLuk());
                builder.append(" || 全部力量 : ");
                builder.append(other.getStat().getTotalStr());
                builder.append(" || 全部敏捷 : ");
                builder.append(other.getStat().getTotalDex());
                builder.append(" || 全部智力 : ");
                builder.append(other.getStat().getTotalInt());
                builder.append(" || 全部幸运 : ");
                builder.append(other.getStat().getTotalLuk());
                builder.append(" || 经验值 : ");
                builder.append(other.getExp());
                builder.append(" || 组队状态 : ");
                builder.append(other.getParty() != null);
                builder.append(" || 交易状态: ");
                builder.append(other.getTrade() != null);
                builder.append(" || Latency: ");
                builder.append(other.getClient().getLatency());
                builder.append(" || 最后PING: ");
                builder.append(other.getClient().getLastPing());
                builder.append(" || 最后PONG: ");
                builder.append(other.getClient().getLastPong());
                builder.append(" || IP: ");
                builder.append(other.getClient().getSessionIPAddress());
                other.getClient().DebugMessage(builder);
                c.getPlayer().dropMessage(6, builder.toString());
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!charinfo <角色名称> - 查看角色状态").toString();
        }
    }
    
    public static class Cheaters extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final List<CheaterData> cheaters = World.getCheaters();
            for (int x = cheaters.size() - 1; x >= 0; --x) {
                final CheaterData cheater = (CheaterData)cheaters.get(x);
                c.getPlayer().dropMessage(6, cheater.getInfo());
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!cheaters - 查看作弊角色").toString();
        }
    }
    
    public static class ItemCheck extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 3 || splitted[1] == null || splitted[1].equals((Object)"") || splitted[2] == null || splitted[2].equals((Object)"")) {
                return false;
            }
            final int item = Integer.parseInt(splitted[2]);
            final String name = splitted[1];
            final int ch = Find.findChannel(name);
            if (ch <= 0) {
                c.getPlayer().dropMessage(6, "玩家必须上线");
                return true;
            }
            final MapleCharacter chr = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
            final int itemamount = chr.getItemQuantity(item, true);
            if (itemamount > 0) {
                c.getPlayer().dropMessage(6, chr.getName() + " 有 " + itemamount + " (" + item + ").");
            }
            else {
                c.getPlayer().dropMessage(6, chr.getName() + " 并没有 (" + item + ")");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!itemcheck <playername> <itemid> - 检查物品").toString();
        }
    }
    
    public static class CheckGash extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final String name = splitted[1];
            final int ch = Find.findChannel(name);
            if (ch <= 0) {
                c.getPlayer().dropMessage(6, "玩家必须上线");
                return true;
            }
            final MapleCharacter chrs = ChannelServer.getInstance(ch).getPlayerStorage().getCharacterByName(name);
            if (chrs == null) {
                c.getPlayer().dropMessage(5, "找不到该角色");
            }
            else {
                c.getPlayer().dropMessage(6, chrs.getName() + " 有 " + chrs.getCSPoints(1) + " 点数.");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!checkgash <玩家名称> - 检查点数").toString();
        }
    }
    
    public static class whoishere extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            StringBuilder builder = new StringBuilder("在此地图的玩家: ");
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
            return new StringBuilder().append("!whoishere - 查看目前地图上的玩家").toString();
        }
    }
    
    public static class Connected extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final java.util.Map<Integer, Integer> connected = World.getConnected();
            final StringBuilder conStr = new StringBuilder("已连接的客户端: ");
            boolean first = true;
            final Iterator<Integer> iterator = connected.keySet().iterator();
            while (iterator.hasNext()) {
                final int i = (int)Integer.valueOf(iterator.next());
                if (!first) {
                    conStr.append(", ");
                }
                else {
                    first = false;
                }
                if (i == 0) {
                    conStr.append("所有: ");
                    conStr.append((Object)connected.get((Object)Integer.valueOf(i)));
                }
                else {
                    conStr.append("频道 ");
                    conStr.append(i);
                    conStr.append(": ");
                    conStr.append((Object)connected.get((Object)Integer.valueOf(i)));
                }
            }
            c.getPlayer().dropMessage(6, conStr.toString());
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!connected - 查看已连线的客户端").toString();
        }
    }
    
    public static class openmap extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            int mapid = 0;
            String input = null;
            final MapleMap map = null;
            if (splitted.length < 2) {
                return false;
            }
            try {
                input = splitted[1];
                mapid = Integer.parseInt(input);
            }
            catch (Exception ex) {}
            for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                cserv.getMapFactory().HealMap(mapid);
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!openmap - 开放地图").toString();
        }
    }
    
    public static class closemap extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            int mapid = 0;
            String input = null;
            final MapleMap map = null;
            if (splitted.length < 2) {
                return false;
            }
            try {
                input = splitted[1];
                mapid = Integer.parseInt(input);
            }
            catch (Exception ex) {}
            if (c.getChannelServer().getMapFactory().getMap(mapid) == null) {
                c.getPlayer().dropMessage("地图不存在");
                return true;
            }
            for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                cserv.getMapFactory().destroyMap(mapid, true);
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return new StringBuilder().append("!closemap - 关闭地图").toString();
        }
    }
    
    public static class 查看玩家状态 extends PlayerStats
    {
    }
    
    public static class PlayerStats extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            if (splitted.length < 2) {
                return false;
            }
            final String name = splitted[1];
            final MapleCharacter victim = MapleCharacter.getCharacterByName(name);
            final int ch = Find.findChannel(name);
            if (victim != null) {
                if (victim.getGMLevel() > c.getPlayer().getGMLevel()) {
                    c.getPlayer().dropMessage(5, "你不能查看比你高权限的人!");
                }
                else if (ch <= 0) {
                    c.getPlayer().dropMessage(5, "该角色为离线状态");
                }
                else {
                    c.getPlayer().dropMessage(5, "玩家名称: " + victim.getName() + " 等级: " + (int)victim.getLevel() + " 职业: " + (int)victim.getJob());
                    c.getPlayer().dropMessage(5, "力量: " + victim.getStr() + " 敏捷: " + victim.getDex() + " 智力: " + victim.getInt() + " 幸运: " + victim.getLuk());
                    c.getPlayer().dropMessage(5, "HP: " + victim.getHp() + " 最大HP: " + victim.getMaxHp() + " MP: " + victim.getMp() + " 最大MP: " + victim.getMaxMp());
                    c.getPlayer().dropMessage(5, "Buff技能数量: " + victim.getSkillID().size());
                    c.getPlayer().dropMessage(5, "技能ID: " + victim.getSkillID().keySet().toString());
                }
            }
            else {
                c.getPlayer().dropMessage(5, "找不到此玩家.");
            }
            return true;
        }
        
        @Override
        public String getMessage() {
            return "!" + this.getClass().getSimpleName().toLowerCase() + " <玩家名字> - 查看玩家技能数据";
        }
    }
    
    public static class 生死活动 extends CommandExecute
    {
        @Override
        public boolean execute(final MapleClient c, final String[] splitted) {
            final MapleCharacter chr = c.getPlayer();
            if (splitted.length < 2) {
                c.getPlayer().dropMessage(5, "[用法] !生死活动 <1干死右边，2干死左边>");
                return false;
            }
            if (chr.getMapId() != 109020001) {
                c.getPlayer().dropMessage(5, "只有地图ID为109020001的地图才能使用。");
                return true;
            }
            final int answer = Integer.parseInt(splitted[1]);
            final List<MapleCharacter> chrlist = chr.getMap().getCharacters();
            for (final MapleCharacter chra : chrlist) {
                if (isCorrectAnswer(chra, answer)) {
                    chra.getStat().setHp(0);
                    chra.getStat().setMp(0);
                    chra.updateSingleStat(MapleStat.HP, 0);
                    chra.updateSingleStat(MapleStat.MP, 0);
                }
            }
            return true;
        }
        
        public static boolean isCorrectAnswer(final MapleCharacter chr, final int answer) {
            final double x = chr.getTruePosition().getX();
            final double y = chr.getTruePosition().getY();
            return (x > -234.0 && y > -26.0 && answer == 1) || (x < -234.0 && y > -26.0 && answer == 2);
        }
        
        @Override
        public String getMessage() {
            return "!" + this.getClass().getSimpleName().toLowerCase() + " <1干死右边，2干死左边>";
        }
    }
}
