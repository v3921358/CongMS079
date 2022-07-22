package handling.world;

import server.ServerProperties;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.List;
import java.io.Serializable;

public class MapleParty implements Serializable
{
    private static final long serialVersionUID = 9179541993413738569L;
    private MaplePartyCharacter leader;
    private final List<MaplePartyCharacter> members;
    private int id;
    private int averageLevel;
    public static String 开服名字;
    public static String IP地址;
    public static int 互相伤害;
    public static int 通缉BOSS;
    public static int 地图回收;
    public static int 通缉地图;
    public static int 神秘商人线程;
    public static int 神秘商人;
    public static int 神秘商人频道;
    public static int 神秘商人地图;
    public static int 神秘商人坐标X;
    public static int 神秘商人坐标Y;
    public static int 神秘商人时间;
    public static int 蝙蝠魔A部队;
    public static int 蝙蝠魔B部队;
    public static int 蝙蝠魔C部队;
    public static int 蝙蝠魔D部队;
    public static int 幸运职业;
    public static int 特等奖;
    public static int 一等奖;
    public static int 二等奖;
    public static int 三等奖;
    public static int 服务端;
    public static int 服务端启动中;
    public static int OX答题活动;
    public static int 任务修复;
    public static int 服务端启动模式;
    public static int 容纳人数;
    public static int 冷却1;
    public static String 启动账号;
    public static int 反馈冷却;
    public static int 启动进度;
    public static int 反馈冷却1;
    public static int 反馈冷却2;
    public static int 反馈冷却3;
    public static int yzcs;
    public static int 版本检测;
    public static int 版本检测2;
    public static int 机器人待机;
    public static int 每日清理;
    public static int 伤害显示;
    public static int 出题间隔;
    public static int 信息同步;
    public static int 服务端调查;
    public static int 服务端运行时长;
    public static int 玩家登陆次数;
    public static int 角色登陆次数;
    public static int 游戏公告群输出;
    public static int 人数统计;
    public static int 流畅模式;
    public static int 试密码;
    public static int 调试3;
    public static int 市场测试;
    public static int 技能调试;
    public static int 云黑名单;
    public static int 大逃杀活动;
    public static int 双区域模式;
    public static int 队列人数;
    public static int 商城出售通知;
    public static int ZEVMSmxd;
    public static int 单机使用模式;
    public static int 联机使用模式;
    public static int 远控启动开关;
    public static int 强制更新开关;
    public static int 广告阅读开关;
    public static int 配置检测开关;
    public static int 自动更新开关;
    public static int 脚本编辑器授权开关;
    public static int 脚本判断1;
    public static int 脚本收取1;
    public static int 脚本给予1;
    public static int 脚本判断数量;
    public static int 脚本收取数量;
    public static int 买了否冷;
    public static String 复读机彩旦;
    public static int 活动安排;
    public static int 雪球赛;
    
    private void calculateAverageLevel() {
        int value = 0;
        for (final MaplePartyCharacter chr : this.members) {
            value += chr.getLevel();
        }
        value = (int)((double)this.averageLevel / (double)this.members.size());
        this.averageLevel = value;
    }
    
    public MapleParty(final int id, final MaplePartyCharacter chrfor) {
        this.members = new LinkedList<MaplePartyCharacter>();
        this.averageLevel = 0;
        this.leader = chrfor;
        this.members.add(this.leader);
        this.id = id;
        this.averageLevel = 0;
    }
    
    public boolean containsMembers(final MaplePartyCharacter member) {
        return this.members.contains((Object)member);
    }
    
    public void addMember(final MaplePartyCharacter member) {
        this.members.add(member);
        this.calculateAverageLevel();
    }
    
    public void removeMember(final MaplePartyCharacter member) {
        this.members.remove((Object)member);
        this.calculateAverageLevel();
    }
    
    public void updateMember(final MaplePartyCharacter member) {
        for (int i = 0; i < this.members.size(); ++i) {
            final MaplePartyCharacter chr = (MaplePartyCharacter)this.members.get(i);
            if (chr.equals((Object)member)) {
                this.members.set(i, member);
            }
        }
        this.calculateAverageLevel();
    }
    
    public MaplePartyCharacter getMemberById(final int id) {
        for (final MaplePartyCharacter chr : this.members) {
            if (chr.getId() == id) {
                return chr;
            }
        }
        return null;
    }
    
    public MaplePartyCharacter getMemberByIndex(final int index) {
        return (MaplePartyCharacter)this.members.get(index);
    }
    
    public Collection<MaplePartyCharacter> getMembers() {
        return new LinkedList<MaplePartyCharacter>((Collection<? extends MaplePartyCharacter>)this.members);
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public MaplePartyCharacter getLeader() {
        return this.leader;
    }
    
    public void setLeader(final MaplePartyCharacter nLeader) {
        this.leader = nLeader;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + this.id;
        return result;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final MapleParty other = (MapleParty)obj;
        return this.id == other.id;
    }
    
    public int getAverageLevel() {
        return this.averageLevel;
    }
    
    static {
        MapleParty.开服名字 = "冒险岛";
        MapleParty.IP地址 = ServerProperties.getProperty("CongMS.CustomIP");
        MapleParty.互相伤害 = 0;
        MapleParty.通缉BOSS = 0;
        MapleParty.地图回收 = 0;
        MapleParty.通缉地图 = 0;
        MapleParty.神秘商人线程 = 0;
        MapleParty.神秘商人 = 0;
        MapleParty.神秘商人频道 = 0;
        MapleParty.神秘商人地图 = 0;
        MapleParty.神秘商人坐标X = 0;
        MapleParty.神秘商人坐标Y = 0;
        MapleParty.神秘商人时间 = 0;
        MapleParty.蝙蝠魔A部队 = 0;
        MapleParty.蝙蝠魔B部队 = 0;
        MapleParty.蝙蝠魔C部队 = 0;
        MapleParty.蝙蝠魔D部队 = 0;
        MapleParty.幸运职业 = 0;
        MapleParty.特等奖 = 0;
        MapleParty.一等奖 = 0;
        MapleParty.二等奖 = 0;
        MapleParty.三等奖 = 0;
        MapleParty.服务端 = 1;
        MapleParty.服务端启动中 = 0;
        MapleParty.OX答题活动 = 0;
        MapleParty.任务修复 = 0;
        MapleParty.服务端启动模式 = 0;
        MapleParty.容纳人数 = 500;
        MapleParty.冷却1 = 0;
        MapleParty.启动账号 = "";
        MapleParty.反馈冷却 = 0;
        MapleParty.启动进度 = 0;
        MapleParty.反馈冷却1 = 0;
        MapleParty.反馈冷却2 = 0;
        MapleParty.反馈冷却3 = 0;
        MapleParty.yzcs = 0;
        MapleParty.版本检测 = 0;
        MapleParty.版本检测2 = 0;
        MapleParty.机器人待机 = 0;
        MapleParty.每日清理 = 0;
        MapleParty.伤害显示 = 1;
        MapleParty.出题间隔 = 0;
        MapleParty.信息同步 = 0;
        MapleParty.服务端调查 = 0;
        MapleParty.服务端运行时长 = 0;
        MapleParty.玩家登陆次数 = 0;
        MapleParty.角色登陆次数 = 0;
        MapleParty.游戏公告群输出 = 0;
        MapleParty.人数统计 = 0;
        MapleParty.流畅模式 = 0;
        MapleParty.试密码 = 0;
        MapleParty.调试3 = 0;
        MapleParty.市场测试 = 0;
        MapleParty.技能调试 = 0;
        MapleParty.云黑名单 = 0;
        MapleParty.大逃杀活动 = 0;
        MapleParty.双区域模式 = 0;
        MapleParty.队列人数 = 0;
        MapleParty.商城出售通知 = 1;
        MapleParty.ZEVMSmxd = 0;
        MapleParty.单机使用模式 = 0;
        MapleParty.联机使用模式 = 0;
        MapleParty.远控启动开关 = 0;
        MapleParty.强制更新开关 = 0;
        MapleParty.广告阅读开关 = 0;
        MapleParty.配置检测开关 = 0;
        MapleParty.自动更新开关 = 0;
        MapleParty.脚本编辑器授权开关 = 0;
        MapleParty.脚本判断1 = 0;
        MapleParty.脚本收取1 = 0;
        MapleParty.脚本给予1 = 0;
        MapleParty.脚本判断数量 = 0;
        MapleParty.脚本收取数量 = 0;
        MapleParty.买了否冷 = 0;
        MapleParty.复读机彩旦 = "";
        MapleParty.活动安排 = 0;
        MapleParty.雪球赛 = 0;
    }
}
