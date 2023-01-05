package gui;

import client.LoginCrypto;
import client.MapleCharacter;
import client.inventory.Equip;
import client.inventory.ItemFlag;
import client.inventory.MapleInventoryType;
import constants.GameConstants;
import database.DatabaseConnection;
import gui.tools.*;
import handling.channel.ChannelServer;
import handling.login.LoginServer;
import handling.login.handler.AutoRegister;
import handling.world.World;
import handling.world.World.Broadcast;
import handling.world.World.Find;
import merchant.merchant_main;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import provider.MapleData;
import provider.MapleDataProvider;
import provider.MapleDataProviderFactory;
import provider.MapleDataTool;
import scripting.NPCConversationManager;
import scripting.PortalScriptManager;
import scripting.ReactorScriptManager;
import server.*;
import server.Timer.EventTimer;
import server.life.MapleMonsterInformationProvider;
import server.quest.MapleQuest;
import tools.MaplePacketCreator;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CongMS extends JFrame {

    private static long starttime;
    private ImageIcon bgImg;
    private JLabel imgLabel;
    public static Map<String, Integer> ConfigValuesMap;
    private static CongMS instance;
    private Map<Windows, JFrame> windows;
    boolean 调试模式;
    boolean 自动注册;
    String 服务器名字;
    String 经验倍数;
    boolean 开启服务端;
    private boolean searchServer;
    String accname;
    String pwd;
    String money;
    String rmb;
    String dj;
    String dy;
    String ljzz;
    String mima;
    int accid;
    public static String[] DEFAULT_FONT;
    int t1;
    int year;
    int month;
    int date;
    int hour;
    int minute;
    int second;
    private static ScheduledFuture<?> ts;
    private int minutesLeft;
    private static Thread t;
    private JTextField GM;
    private JTextField HP;
    private JTextField MP;
    private JTextField QQ;
    private JLabel ZEVMS2提示框1;
    private JTextField a1;
    private Canvas canvas1;
    private JTable charTable;
    private JButton jButton12;
    private JButton jButton13;
    private JButton jButton16;
    private JButton jButton2;
    private JButton jButton25;
    private JButton jButton26;
    private JButton jButton27;
    private JButton jButton28;
    private JButton jButton29;
    private JButton jButton3;
    private JButton jButton31;
    private JButton jButton32;
    private JButton jButton33;
    private JButton jButton34;
    private JButton jButton35;
    private JButton jButton36;
    private JButton jButton37;
    private JButton jButton38;
    private JButton jButton39;
    private JButton jButton44;
    private JButton jButton45;
    private JButton jButton46;
    private JButton jButton7;
    private JButton jButton8;
    private JButton jButton9;
    private JLabel jLabel1;
    private JLabel jLabel106;
    private JLabel jLabel107;
    private JLabel jLabel11;
    private JLabel jLabel111;
    private JLabel jLabel117;
    private JLabel jLabel118;
    private JLabel jLabel119;
    private JLabel jLabel120;
    private JLabel jLabel131;
    private JLabel jLabel182;
    private JLabel jLabel183;
    private JLabel jLabel184;
    private JLabel jLabel185;
    private JLabel jLabel186;
    private JLabel jLabel187;
    private JLabel jLabel188;
    private JLabel jLabel189;
    private JLabel jLabel190;
    private JLabel jLabel191;
    private JLabel jLabel192;
    private JLabel jLabel193;
    private JLabel jLabel194;
    private JLabel jLabel195;
    private JLabel jLabel196;
    private JLabel jLabel198;
    private JLabel jLabel199;
    private JLabel jLabel2;
    private JLabel jLabel200;
    private JLabel jLabel201;
    private JLabel jLabel202;
    private JLabel jLabel203;
    private JLabel jLabel204;
    private JLabel jLabel205;
    private JLabel jLabel206;
    private JLabel jLabel209;
    private JLabel jLabel210;
    private JLabel jLabel211;
    private JLabel jLabel212;
    private JLabel jLabel213;
    private JLabel jLabel214;
    private JLabel jLabel217;
    private JLabel jLabel219;
    private JLabel jLabel220;
    private JLabel jLabel221;
    private JLabel jLabel222;
    private JLabel jLabel223;
    private JLabel jLabel224;
    private JLabel jLabel225;
    private JLabel jLabel226;
    private JLabel jLabel227;
    private JLabel jLabel228;
    private JLabel jLabel229;
    private JLabel jLabel230;
    private JLabel jLabel231;
    private JLabel jLabel232;
    private JLabel jLabel233;
    private JLabel jLabel234;
    private JLabel jLabel235;
    private JLabel jLabel240;
    private JLabel jLabel241;
    private JLabel jLabel242;
    private JLabel jLabel244;
    private JLabel jLabel246;
    private JLabel jLabel25;
    private JLabel jLabel252;
    private JLabel jLabel253;
    private JLabel jLabel259;
    private JLabel jLabel268;
    private JLabel jLabel269;
    private JLabel jLabel270;
    private JLabel jLabel271;
    private JLabel jLabel272;
    private JLabel jLabel273;
    private JLabel jLabel274;
    private JLabel jLabel275;
    private JLabel jLabel276;
    private JLabel jLabel277;
    private JLabel jLabel278;
    private JLabel jLabel279;
    private JLabel jLabel28;
    private JLabel jLabel280;
    private JLabel jLabel282;
    private JLabel jLabel283;
    private JLabel jLabel287;
    private JLabel jLabel288;
    private JLabel jLabel289;
    private JLabel jLabel29;
    private JLabel jLabel290;
    private JLabel jLabel291;
    private JLabel jLabel292;
    private JLabel jLabel293;
    private JLabel jLabel294;
    private JLabel jLabel295;
    private JLabel jLabel296;
    private JLabel jLabel297;
    private JLabel jLabel298;
    private JLabel jLabel299;
    private JLabel jLabel3;
    private JLabel jLabel30;
    private JLabel jLabel300;
    private JLabel jLabel301;
    private JLabel jLabel302;
    private JLabel jLabel303;
    private JLabel jLabel304;
    private JLabel jLabel305;
    private JLabel jLabel306;
    private JLabel jLabel307;
    private JLabel jLabel308;
    private JLabel jLabel309;
    private JLabel jLabel31;
    private JLabel jLabel310;
    private JLabel jLabel311;
    private JLabel jLabel312;
    private JLabel jLabel313;
    private JLabel jLabel314;
    private JLabel jLabel316;
    private JLabel jLabel32;
    private JLabel jLabel321;
    private JLabel jLabel322;
    private JLabel jLabel323;
    private JLabel jLabel324;
    private JLabel jLabel325;
    private JLabel jLabel326;
    private JLabel jLabel327;
    private JLabel jLabel328;
    private JLabel jLabel33;
    private JLabel jLabel337;
    private JLabel jLabel338;
    private JLabel jLabel339;
    private JLabel jLabel34;
    private JLabel jLabel340;
    private JLabel jLabel341;
    private JLabel jLabel342;
    private JLabel jLabel343;
    private JLabel jLabel344;
    private JLabel jLabel345;
    private JLabel jLabel346;
    private JLabel jLabel347;
    private JLabel jLabel348;
    private JLabel jLabel349;
    private JLabel jLabel35;
    private JLabel jLabel353;
    private JLabel jLabel354;
    private JLabel jLabel355;
    private JLabel jLabel356;
    private JLabel jLabel357;
    private JLabel jLabel359;
    private JLabel jLabel36;
    private JLabel jLabel360;
    private JLabel jLabel361;
    private JLabel jLabel362;
    private JLabel jLabel37;
    private JLabel jLabel379;
    private JLabel jLabel38;
    private JLabel jLabel380;
    private JLabel jLabel381;
    private JLabel jLabel382;
    private JLabel jLabel384;
    private JLabel jLabel385;
    private JLabel jLabel386;
    private JLabel jLabel39;
    private JLabel jLabel4;
    private JLabel jLabel40;
    private JLabel jLabel5;
    private JLabel jLabel55;
    private JLabel jLabel56;
    private JLabel jLabel57;
    private JLabel jLabel58;
    private JLabel jLabel59;
    private JLabel jLabel6;
    private JLabel jLabel60;
    private JLabel jLabel61;
    private JLabel jLabel62;
    private JLabel jLabel63;
    private JLabel jLabel65;
    private JLabel jLabel7;
    private JLabel jLabel86;
    private JLabel jLabel89;
    private JPanel jPanel10;
    private JPanel jPanel11;
    private JPanel jPanel12;
    private JPanel jPanel13;
    private JPanel jPanel15;
    private JPanel jPanel16;
    private JPanel jPanel17;
    private JPanel jPanel18;
    private JPanel jPanel19;
    private JPanel jPanel2;
    private JPanel jPanel20;
    private JPanel jPanel21;
    private JPanel jPanel22;
    private JPanel jPanel23;
    private JPanel jPanel24;
    private JPanel jPanel25;
    private JPanel jPanel26;
    private JPanel jPanel27;
    private JPanel jPanel28;
    private JPanel jPanel29;
    private JPanel jPanel3;
    private JPanel jPanel30;
    private JPanel jPanel31;
    private JPanel jPanel32;
    private JPanel jPanel33;
    private JPanel jPanel34;
    private JPanel jPanel35;
    private JPanel jPanel36;
    private JPanel jPanel37;
    private JPanel jPanel38;
    private JPanel jPanel39;
    private JPanel jPanel4;
    private JPanel jPanel40;
    private JPanel jPanel41;
    private JPanel jPanel42;
    private JPanel jPanel43;
    private JPanel jPanel44;
    private JPanel jPanel45;
    private JPanel jPanel46;
    private JPanel jPanel47;
    private JPanel jPanel48;
    private JPanel jPanel49;
    private JPanel jPanel5;
    private JPanel jPanel50;
    private JPanel jPanel51;
    private JPanel jPanel52;
    private JPanel jPanel55;
    private JPanel jPanel56;
    private JPanel jPanel57;
    private JPanel jPanel58;
    private JPanel jPanel59;
    private JPanel jPanel6;
    private JPanel jPanel60;
    private JPanel jPanel61;
    private JPanel jPanel62;
    private JPanel jPanel63;
    private JPanel jPanel64;
    private JPanel jPanel65;
    private JPanel jPanel66;
    private JPanel jPanel69;
    private JPanel jPanel7;
    private JPanel jPanel70;
    private JPanel jPanel72;
    private JPanel jPanel74;
    private JPanel jPanel75;
    private JPanel jPanel8;
    private JPanel jPanel9;
    private JPanel jPanel91;
    private JPanel jPanel93;
    private JScrollPane jScrollPane10;
    private JScrollPane jScrollPane107;
    private JScrollPane jScrollPane108;
    private JScrollPane jScrollPane132;
    private JScrollPane jScrollPane134;
    private JScrollPane jScrollPane136;
    private JScrollPane jScrollPane14;
    private JScrollPane jScrollPane15;
    private JScrollPane jScrollPane16;
    private JScrollPane jScrollPane17;
    private JScrollPane jScrollPane18;
    private JScrollPane jScrollPane19;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane20;
    private JScrollPane jScrollPane21;
    private JScrollPane jScrollPane22;
    private JScrollPane jScrollPane23;
    private JScrollPane jScrollPane24;
    private JScrollPane jScrollPane25;
    private JScrollPane jScrollPane26;
    private JScrollPane jScrollPane27;
    private JScrollPane jScrollPane3;
    private JScrollPane jScrollPane30;
    private JScrollPane jScrollPane4;
    private JScrollPane jScrollPane5;
    private JScrollPane jScrollPane6;
    private JScrollPane jScrollPane7;
    private JScrollPane jScrollPane8;
    private JScrollPane jScrollPane9;
    private JSeparator jSeparator11;
    private JSeparator jSeparator12;
    private JTabbedPane jTabbedPane2;
    private JTabbedPane jTabbedPane3;
    private JTabbedPane jTabbedPane5;
    private JTabbedPane jTabbedPane6;
    private JTabbedPane jTabbedPane7;
    private JTabbedPane jTabbedPane8;
    private JTabbedPane jTabbedPane9;
    private JTextArea jTextArea1;
    private JTextField jTextField2;
    private JTextField jTextField22;
    private JTextField noticeText;
    private JButton sendMsgNotice;
    private JButton sendNotice;
    private JButton sendNpcTalkNotice;
    private JButton sendWinNotice;
    private static JButton startserverbutton;
    private JButton z1;
    private JButton z2;
    private JButton z3;
    private JButton z4;
    private JButton z5;
    private JButton z6;
    private JTextField 三倍爆率持续时间;
    private JTextField 三倍经验持续时间;
    private JTextField 三倍金币持续时间;
    private JButton 上线提醒开关;
    private JButton 上衣;
    private JTable 世界爆物;
    private JTextField 世界爆物名称;
    private JTextField 世界爆物序列号;
    private JTextField 世界爆物爆率;
    private JTextField 世界爆物物品代码;
    private JButton 丢出物品开关;
    private JButton 丢出金币开关;
    private JTextField 个人发送物品代码;
    private JTextField 个人发送物品数量;
    private JTextField 个人发送物品玩家名字;
    private JButton 个人商店;
    private JButton 主题馆;
    private JButton 会员卡;
    private JButton 修改;
    private JButton 修改世界爆物;
    private JButton 修改冒险家等级上限;
    private JButton 修改反应堆物品;
    private JButton 修改商品;
    private JButton 修改广播;
    private JButton 修改怪物爆物;
    private JButton 修改技能;
    private JButton 修改技能1;
    private JButton 修改背包扩充价格;
    private JButton 修改账号点券抵用;
    private JButton 修改钓鱼物品;
    private JButton 修改骑士团等级上限;
    private JTextField 全服发送物品代码;
    private JTextField 全服发送物品数量;
    private JTextField 全服发送装备物品ID;
    private JTextField 全服发送装备装备HP;
    private JTextField 全服发送装备装备MP;
    private JTextField 全服发送装备装备制作人;
    private JTextField 全服发送装备装备力量;
    private JTextField 全服发送装备装备加卷;
    private JTextField 全服发送装备装备可否交易;
    private JTextField 全服发送装备装备攻击力;
    private JTextField 全服发送装备装备敏捷;
    private JTextField 全服发送装备装备智力;
    private JTextField 全服发送装备装备物理防御;
    private JTextField 全服发送装备装备给予时间;
    private JTextField 全服发送装备装备运气;
    private JTextField 全服发送装备装备魔法力;
    private JTextField 全服发送装备装备魔法防御;
    private JTextField 公告发布喇叭代码;
    private JButton 其他;
    private JTextField 其他背包物品代码;
    private JTextField 其他背包物品名字;
    private JTextField 其他背包物品序号;
    private JTextField 冒险家等级上限;
    private JButton 冒险家职业开关;
    private JTextField 删MAC代码;
    private JButton 删除IP;
    private JTextField 删除IP代码;
    private JButton 删除MAC;
    private JButton 删除世界爆物;
    private JButton 删除其他背包;
    private JButton 删除反应堆物品;
    private JButton 删除反应堆物品1;
    private JTextField 删除反应堆物品代码;
    private JButton 删除商品;
    private JButton 删除商城仓库;
    private JButton 删除广播;
    private JButton 删除怪物爆物;
    private JButton 删除技能;
    private JButton 删除拍卖行;
    private JButton 删除拍卖行1;
    private JTextField 删除指定的掉落;
    private JTextField 删除指定的掉落1;
    private JButton 删除指定的掉落按键;
    private JButton 删除指定的掉落按键1;
    private JButton 删除消耗背包;
    private JButton 删除游戏仓库;
    private JButton 删除特殊背包;
    private JButton 删除穿戴装备;
    private JButton 删除装备背包;
    private JButton 删除角色;
    private JButton 删除设置背包;
    private JButton 删除账号;
    private JButton 删除道具;
    private JButton 删除道具1;
    private JButton 删除道具2;
    private JButton 删除道具3;
    private JButton 删除道具4;
    private JButton 删除钓鱼物品;
    private JButton 刷新世界爆物;
    private JButton 刷新家族信息;
    private JButton 刷新封IP;
    private JButton 刷新封MAC;
    private JButton 刷新广告;
    private JButton 刷新怪物卡片;
    private JButton 刷新怪物爆物;
    private JButton 刷新角色信息;
    private JButton 刷新账号信息;
    private JButton 刷新钓鱼物品;
    private JTextField 力量;
    private JButton 卡号自救1;
    private JButton 卡号自救2;
    private JButton 卡家族解救;
    private JButton 卷轴;
    private JTextField 双倍爆率持续时间;
    private JTextField 双倍经验持续时间;
    private JTextField 双倍金币持续时间;
    private JTable 反应堆;
    private JTextField 反应堆代码;
    private JTextField 反应堆序列号;
    private JTextField 反应堆概率;
    private JTextField 反应堆物品;
    private JTextField 发型;
    private JButton 发布广告;
    private JTextField 发送装备玩家姓名;
    private JButton 吸怪检测开关;
    private JTextField 商品代码;
    private JTextField 商品价格;
    private JTextField 商品出售状态;
    private JTextField 商品名称;
    private JTextField 商品售价金币;
    private JTextField 商品序号;
    private JTextField 商品库存;
    private JTextField 商品折扣;
    private JTextField 商品数量;
    private JTextField 商品时间;
    private JTextField 商品物品代码;
    private JTextField 商品编码;
    private JTextField 商城仓库物品代码;
    private JTextField 商城仓库物品名字;
    private JTextField 商城仓库物品序号;
    private JTable 商城扩充价格;
    private JTextField 商城扩充价格修改;
    private JTextField 商店代码;
    private JButton 喜庆物品;
    private JButton 喷火龙开关;
    private JButton 回收地图开关;
    private JTable 在线泡点设置;
    private JButton 在线角色;
    private JButton 在线账号;
    private JTextField 地图;
    private JButton 地图名称开关;
    private JButton 大海龟开关;
    private JButton 大灰狼开关;
    private JButton 宠物;
    private JButton 宠物服饰;
    private JTextField 家族GP;
    private JTextField 家族ID;
    private JTable 家族信息;
    private JTextField 家族名称;
    private JTextField 家族成员2;
    private JTextField 家族成员3;
    private JTextField 家族成员4;
    private JTextField 家族成员5;
    private JTextField 家族族长;
    private JTable 封IP;
    private JTable 封MAC;
    private JButton 封锁账号;
    private JButton 小白兔开关;
    private JButton 小青蛇开关;
    private JButton 屠令广播开关;
    private JButton 已封账号;
    private JButton 帽子;
    private JButton 幸运职业开关;
    private JTable 广播信息;
    private JTextField 广播序号;
    private JTextField 广播文本;
    private JButton 开启三倍爆率;
    private JButton 开启三倍经验;
    private JButton 开启三倍金币;
    private JButton 开启双倍爆率;
    private JButton 开启双倍经验;
    private JButton 开启双倍金币;
    private JTable 怪物爆物;
    private JTextField 怪物爆物序列号;
    private JTextField 怪物爆物怪物代码;
    private JTextField 怪物爆物爆率;
    private JTextField 怪物爆物物品代码;
    private JTextField 怪物爆物物品名称;
    private JButton 怪物状态开关;
    private JButton 戒指;
    private JButton 战神职业开关;
    private JButton 手套;
    private JPanel 技能;
    private JTextField 技能代码;
    private JTable 技能信息;
    private JTextField 技能名字;
    private JTextField 技能序号;
    private JTextField 技能最高等级;
    private JTextField 技能目前等级;
    private JButton 披风;
    private JTextField 抵用;
    private JTextField 拍卖行物品代码;
    private JTextField 拍卖行物品代码1;
    private JTextField 拍卖行物品名字;
    private JTextField 拍卖行物品名字1;
    private JButton 指令通知开关;
    private JButton 效果;
    private JTextField 敏捷;
    private JButton 新增反应堆物品;
    private JButton 新增商品;
    private JButton 新增钓鱼物品;
    private JButton 星精灵开关;
    private JLabel 显示在线玩家;
    private JLabel 显示在线账号;
    private JButton 显示管理角色;
    private JTextField 显示类型;
    private JTextField 智力;
    private JTextField 查找反应堆掉落;
    private JTextField 查找物品;
    private JButton 查找道具;
    private JButton 查找道具1;
    private JButton 查找道具2;
    private JButton 查找道具3;
    private JButton 查找道具4;
    private JButton 查看技能;
    private JButton 查看背包;
    private JTextField 查询商店;
    private JButton 查询商店2;
    private JButton 查询怪物掉落;
    private JTextField 查询怪物掉落代码;
    private JButton 查询物品掉落;
    private JButton 查询物品掉落1;
    private JTextField 查询物品掉落代码;
    private JTextField 查询物品掉落代码1;
    private JButton 欢迎弹窗开关;
    private JButton 武器;
    private JTextField 每日限购;
    private JTextField 泡点值;
    private JButton 泡点值修改;
    private JTextField 泡点序号;
    private JButton 泡点抵用开关;
    private JButton 泡点点券开关;
    private JTextField 泡点类型;
    private JButton 泡点经验开关;
    private JButton 泡点豆豆开关;
    private JButton 泡点金币开关;
    private JTextField 注册的密码;
    private JTextField 注册的账号;
    private JButton 活动;
    private JTextField 消耗背包物品代码;
    private JTextField 消耗背包物品名字;
    private JTextField 消耗背包物品序号;
    private JButton 添加;
    private JButton 添加世界爆物;
    private JButton 添加怪物爆物;
    private JButton 游戏;
    private JTextField 游戏仓库物品代码;
    private JTextField 游戏仓库物品名字;
    private JTextField 游戏仓库物品序号;
    private JButton 游戏升级快讯;
    private JTable 游戏商店2;
    private JButton 游戏喇叭开关;
    private JPanel 游戏广播;
    private JButton 游戏指令开关;
    private JButton 游戏经验加成说明;
    private JTable 游戏道具;
    private JTextField 游戏道具代码;
    private JButton 滚动公告开关;
    private JButton 漂漂猪开关;
    private JButton 火野猪开关;
    private JTextField 点券;
    private JTextField 特殊背包物品代码;
    private JTextField 特殊背包物品名字;
    private JTextField 特殊背包物品序号;
    private JButton 玩家交易开关;
    private JButton 玩家聊天开关;
    private JButton 登陆帮助开关;
    private JButton 白雪人开关;
    private JButton 眼饰;
    private JButton 石头人开关;
    private JButton 神秘商人开关;
    private JButton 禁止登陆开关;
    private JLabel 福利提示语言2;
    private JButton 离线角色;
    private JButton 离线账号;
    private JButton 章鱼怪开关;
    private JTextField 等级;
    private JTextField 管理1;
    private JButton 管理加速开关;
    private JButton 管理隐身开关;
    private JButton 紫色猫开关;
    private JButton 红螃蟹开关;
    private JButton 纪念日;
    private JTable 经验加成表;
    private JButton 经验加成表修改;
    private JTextField 经验加成表序号;
    private JTextField 经验加成表数值;
    private JTextField 经验加成表类型;
    private JButton 给予物品;
    private JButton 给予物品1;
    private JButton 给予装备1;
    private JButton 给予装备2;
    private JButton 绿水灵开关;
    private JTextField 背包物品代码1;
    private JTextField 背包物品名字1;
    private JButton 胖企鹅开关;
    private JTextField 脸型;
    private JButton 脸饰;
    private JButton 花蘑菇开关;
    private JButton 蓝蜗牛开关;
    private JButton 蘑菇仔开关;
    private JButton 表情;
    private JTextField 装备背包物品代码;
    private JTextField 装备背包物品名字;
    private JTextField 装备背包物品序号;
    private JButton 裙裤;
    private JTextField 角色ID;
    private JTable 角色信息;
    private JPanel 角色信息1;
    private JTable 角色其他背包;
    private JTable 角色商城仓库;
    private JTextField 角色昵称;
    private JTable 角色消耗背包;
    private JTable 角色游戏仓库;
    private JTable 角色点券拍卖行;
    private JTextField 角色点券拍卖行序号;
    private JTable 角色特殊背包;
    private JPanel 角色背包;
    private JTable 角色背包穿戴;
    private JTable 角色装备背包;
    private JTable 角色设置背包;
    private JTable 角色金币拍卖行;
    private JTextField 角色金币拍卖行序号;
    private JButton 解卡;
    private JButton 解封;
    private JTextField 设置背包物品代码;
    private JTextField 设置背包物品名字;
    private JTextField 设置背包物品序号;
    private JButton 读取热销产品;
    private JTextField 账号;
    private JTextField 账号ID;
    private JTable 账号信息;
    private JLabel 账号提示语言;
    private JTextField 账号操作;
    private JTextField 货币类型;
    private JButton 越级打怪开关;
    private JTextField 身上穿戴序号1;
    private JTextArea 输出窗口;
    private JButton 过图存档开关;
    private JTextField 运气;
    private JButton 通讯物品;
    private JButton 重载任务;
    private JButton 重载传送门按钮;
    private JButton 重载副本按钮;
    private JButton 重载包头按钮;
    private JButton 重载反应堆按钮;
    private JButton 重载商城按钮;
    private JButton 重载商店按钮;
    private JButton 重载爆率按钮;
    private JButton 重载箱子反应堆按钮;
    private JTextField 金币1;
    private JTable 钓鱼物品;
    private JTextField 钓鱼物品代码;
    private JTextField 钓鱼物品名称;
    private JTextField 钓鱼物品序号;
    private JTextField 钓鱼物品概率;
    private JPanel 钓鱼管理;
    private JButton 长袍;
    private JButton 雇佣商人开关;
    private JButton 青鳄鱼开关;
    private JButton 鞋子;
    private JButton 顽皮猴开关;
    private JButton 飞镖;
    private JTextField 骑士团等级上限;
    private JButton 骑士团职业开关;
    private JButton 骑宠;
    private JButton 魔族攻城开关;
    private JButton 魔族突袭开关;

    private String 获取网络时间(final String httpbaiducom) {
        try {
            final URL url = new URL(httpbaiducom);
            final URLConnection uc = url.openConnection();
            uc.connect();
            final long ld = uc.getDate();
            final Date date = new Date(ld);
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            return sdf.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    private void 刷新钓鱼() {
        for (int i = this.钓鱼物品.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.钓鱼物品.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM 钓鱼物品");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) this.钓鱼物品.getModel()).insertRow(this.钓鱼物品.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("id")), Integer.valueOf(rs.getInt("itemid")), Integer.valueOf(rs.getInt("chance")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.钓鱼物品.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 钓鱼物品.getSelectedRow();
                final String a = 钓鱼物品.getValueAt(i, 0).toString();
                final String a2 = 钓鱼物品.getValueAt(i, 1).toString();
                final String a3 = 钓鱼物品.getValueAt(i, 2).toString();
                钓鱼物品序号.setText(a);
                钓鱼物品代码.setText(a2);
                钓鱼物品概率.setText(a3);
            }
        });
    }

    private void 刷新魔族突袭开关() {
        String 显示 = "";
        final int S = Integer.valueOf(CongMS.ConfigValuesMap.get("魔族突袭开关"));
        if (S <= 0) {
            显示 = "开启";
        } else {
            显示 = "关闭";
        }
        this.魔族突袭开关.setText(显示);
    }

    private void 刷新魔族攻城开关() {
        String 显示 = "";
        final int S = Integer.valueOf(CongMS.ConfigValuesMap.get("魔族攻城开关"));
        if (S <= 0) {
            显示 = "开启";
        } else {
            显示 = "关闭";
        }
        this.魔族攻城开关.setText(显示);
    }

    private void 刷新幸运职业开关() {
        String 显示 = "";
        final int S = Integer.valueOf(CongMS.ConfigValuesMap.get("幸运职业开关"));
        if (S <= 0) {
            显示 = "开启";
        } else {
            显示 = "关闭";
        }
        this.幸运职业开关.setText(显示);
    }

    private void 刷新神秘商人开关() {
        String 显示 = "";
        final int S = Integer.valueOf(CongMS.ConfigValuesMap.get("神秘商人开关"));
        if (S <= 0) {
            显示 = "开启";
        } else {
            显示 = "关闭";
        }
        this.神秘商人开关.setText(显示);
    }

    private void 刷新公告广播() {
        for (int i = this.广播信息.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.广播信息.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM 广播信息");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) this.广播信息.getModel()).insertRow(this.广播信息.getRowCount(), new Object[]{rs.getString("id"), rs.getString("广播")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.广播信息.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 广播信息.getSelectedRow();
                final String a = 广播信息.getValueAt(i, 0).toString();
                final String a2 = 广播信息.getValueAt(i, 1).toString();
                广播序号.setText(a);
                广播文本.setText(a2);
            }
        });
    }

    private void 刷物品() {
        try {
            String 名字;
            if ("玩家名字".equals(this.个人发送物品玩家名字.getText())) {
                名字 = "";
            } else {
                名字 = this.个人发送物品玩家名字.getText();
            }
            int 物品ID;
            if ("物品ID".equals(this.个人发送物品代码.getText())) {
                物品ID = 0;
            } else {
                物品ID = Integer.parseInt(this.个人发送物品代码.getText());
            }
            int 数量;
            if ("数量".equals(this.个人发送物品数量.getText())) {
                数量 = 0;
            } else {
                数量 = Integer.parseInt(this.个人发送物品数量.getText());
            }
            final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            final MapleInventoryType type = GameConstants.getInventoryType(物品ID);
            for (final ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (final MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    if (mch.getName().equals(名字)) {
                        if (数量 >= 0) {
                            if (!MapleInventoryManipulator.checkSpace(mch.getClient(), 物品ID, 数量, "")) {
                                return;
                            }
                            if ((type.equals(MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(物品ID) && !GameConstants.isBullet(物品ID)) || (type.equals(MapleInventoryType.CASH) && 物品ID >= 5000000 && 物品ID <= 5000100)) {
                                final Equip item = (Equip) ii.getEquipById(物品ID);
                                if (ii.isCash(物品ID)) {
                                    item.setUniqueId(1);
                                }
                                final String name = ii.getName(物品ID);
                                if (物品ID / 10000 == 114 && name != null && name.length() > 0) {
                                    final String msg = "你已获得称号 <" + name + ">";
                                    mch.getClient().getPlayer().dropMessage(5, msg);
                                }
                                MapleInventoryManipulator.addbyItem(mch.getClient(), item.copy());
                            } else {
                                MapleInventoryManipulator.addById(mch.getClient(), 物品ID, (short) 数量, "", null, (byte) 0);
                            }
                        } else {
                            MapleInventoryManipulator.removeById(mch.getClient(), GameConstants.getInventoryType(物品ID), 物品ID, -数量, true, false);
                        }
                        mch.getClient().sendPacket(MaplePacketCreator.getShowItemGain(物品ID, (short) 数量, true));
                    }
                }
            }
            this.个人发送物品玩家名字.setText("");
            this.个人发送物品代码.setText("");
            this.个人发送物品数量.setText("");
        } catch (Exception ex) {
        }
    }

    public static final CongMS getInstance() {
        return CongMS.instance;
    }

    public CongMS() {
        this.bgImg = new ImageIcon(this.getClass().getClassLoader().getResource("image/qqq.jpg"));
        this.imgLabel = new JLabel(this.bgImg);
        this.windows = new HashMap<Windows, JFrame>();
        this.调试模式 = false;
        this.自动注册 = false;
        this.服务器名字 = "获取中";
        this.经验倍数 = "获取中";
        this.开启服务端 = false;
        this.searchServer = false;
        this.accname = "null";
        this.pwd = "null";
        this.money = "null";
        this.rmb = "null";
        this.dj = "null";
        this.dy = "null";
        this.ljzz = "null";
        this.mima = "123456";
        this.accid = 0;
        this.t1 = 0;
        this.year = Calendar.getInstance().get(1);
        this.month = Calendar.getInstance().get(2) + 1;
        this.date = Calendar.getInstance().get(5);
        this.hour = Calendar.getInstance().get(11);
        this.minute = Calendar.getInstance().get(12);
        this.second = Calendar.getInstance().get(13);
        this.minutesLeft = 0;
        final ImageIcon icon = new ImageIcon(this.getClass().getClassLoader().getResource("image/Icon.png"));
        this.setIconImage(icon.getImage());
        this.setTitle("079联机服务端 [识别码079 - 2.0版本] 启动时间为:" + this.获取网络时间("http://baidu.com") + "");
        GetConfigValues();
        this.initComponents();
        this.刷新信息();
        this.刷新蓝蜗牛开关();
        this.刷新蘑菇仔开关();
        this.刷新绿水灵开关();
        this.刷新漂漂猪开关();
        this.刷新小青蛇开关();
        this.刷新红螃蟹开关();
        this.刷新大海龟开关();
        this.刷新章鱼怪开关();
        this.刷新顽皮猴开关();
        this.刷新星精灵开关();
        this.刷新胖企鹅开关();
        this.刷新白雪人开关();
        this.刷新石头人开关();
        this.刷新紫色猫开关();
        this.刷新大灰狼开关();
        this.刷新小白兔开关();
        this.刷新喷火龙开关();
        this.刷新火野猪开关();
        this.刷新青鳄鱼开关();
        this.刷新花蘑菇开关();
        this.initview();
        final DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(0);
        this.jPanel6.setOpaque(false);
        this.输出窗口.setEditable(false);
        this.输出窗口.setLineWrap(true);
        this.输出窗口.setWrapStyleWord(true);
        this.jTabbedPane2.setOpaque(false);
    }

    private void 刷新信息() {
        this.刷新公告广播();
        this.刷新魔族突袭开关();
        this.刷新魔族攻城开关();
        this.刷新幸运职业开关();
        this.刷新神秘商人开关();
        this.刷新冒险家职业开关();
        this.刷新战神职业开关();
        this.刷新骑士团职业开关();
        this.刷新冒险家等级上限();
        this.刷新骑士团等级上限();
        this.刷新账号信息();
        this.刷新角色信息();
        this.刷新泡点金币开关();
        this.刷新泡点点券开关();
        this.刷新泡点经验开关();
        this.刷新泡点抵用开关();
        this.刷新泡点豆豆开关();
        this.刷新泡点设置();
        this.刷新过图存档时间();
        this.刷新地图名称开关();
        this.刷新登陆帮助();
        this.刷新怪物状态开关();
        this.刷新越级打怪开关();
        this.刷新回收地图开关();
        this.刷新玩家聊天开关();
        this.刷新滚动公告开关();
        this.刷新指令通知开关();
        this.刷新管理隐身开关();
        this.刷新管理加速开关();
        this.刷新游戏指令开关();
        this.刷新游戏喇叭开关();
        this.刷新丢出金币开关();
        this.刷新丢出物品开关();
        this.刷新雇佣商人开关();
        this.刷新上线提醒开关();
        this.刷新升级快讯();
        this.刷新玩家交易开关();
        this.刷新欢迎弹窗开关();
        this.刷新禁止登陆开关();
        this.刷新吸怪检测开关();
        this.刷新经验加成表();
        this.刷新屠令广播开关();
    }

    @Override
    public void setVisible(final boolean bln) {
        final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int) (size.getWidth() - (double) this.getWidth()) / 2, (int) (size.getHeight() - (double) this.getHeight()) / 2);
        super.setVisible(bln);
    }

    public void actionPerformed(final ActionEvent e) {
        final Dis tt = new Dis();
        tt.start();
    }

    public static void GetConfigValues() {
        final Connection con = DatabaseConnection.getConnection();
        try (final PreparedStatement ps = con.prepareStatement("SELECT name, val FROM ConfigValues")) {
            try (final ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    final String name = rs.getString("name");
                    final int val = rs.getInt("val");
                    CongMS.ConfigValuesMap.put(name, Integer.valueOf(val));
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("读取动态数据库出错：" + ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void initview() {
        try {
            final LoopedStreams ls = new LoopedStreams();
            final PrintStream ps = new PrintStream(ls.getOutputStream());
            System.setOut(ps);
            System.setErr(ps);
            this.startConsoleReaderThread(ls.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        ((JPanel) this.getContentPane()).setOpaque(true);
        UIManager.put("TabbedPane.contentOpaque", Boolean.valueOf(true));
    }

    void startConsoleReaderThread(final InputStream inStream) {
        final BufferedReader br = new BufferedReader(new InputStreamReader(inStream));
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuffer sb = new StringBuffer();
                try {
                    String s;
                    while ((s = br.readLine()) != null) {
                        final boolean caretAtEnd = false;
                        sb.setLength(0);
                        输出窗口.append("" + s + '\n');
                        if (!caretAtEnd) {
                        }
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "从BufferedReader读取错误：" + e);
                    System.exit(1);
                }
            }
        }).start();
    }

    private void initComponents() {
        this.canvas1 = new Canvas();
        this.jTabbedPane2 = new JTabbedPane();
        this.jPanel3 = new JPanel();
        this.jLabel56 = new JLabel();
        this.jLabel57 = new JLabel();
        this.jLabel58 = new JLabel();
        this.jLabel59 = new JLabel();
        this.jTabbedPane6 = new JTabbedPane();
        this.jPanel51 = new JPanel();
        this.jPanel74 = new JPanel();
        this.蓝蜗牛开关 = new JButton();
        this.蘑菇仔开关 = new JButton();
        this.绿水灵开关 = new JButton();
        this.漂漂猪开关 = new JButton();
        this.小青蛇开关 = new JButton();
        this.红螃蟹开关 = new JButton();
        this.大海龟开关 = new JButton();
        this.章鱼怪开关 = new JButton();
        this.顽皮猴开关 = new JButton();
        this.星精灵开关 = new JButton();
        this.胖企鹅开关 = new JButton();
        this.白雪人开关 = new JButton();
        this.石头人开关 = new JButton();
        this.紫色猫开关 = new JButton();
        this.大灰狼开关 = new JButton();
        this.喷火龙开关 = new JButton();
        this.火野猪开关 = new JButton();
        this.小白兔开关 = new JButton();
        this.青鳄鱼开关 = new JButton();
        this.花蘑菇开关 = new JButton();
        this.jLabel11 = new JLabel();
        this.jLabel63 = new JLabel();
        this.jPanel17 = new JPanel();
        this.冒险家等级上限 = new JTextField();
        this.修改冒险家等级上限 = new JButton();
        this.jLabel253 = new JLabel();
        this.骑士团等级上限 = new JTextField();
        this.jLabel252 = new JLabel();
        this.修改骑士团等级上限 = new JButton();
        this.jPanel7 = new JPanel();
        this.冒险家职业开关 = new JButton();
        this.战神职业开关 = new JButton();
        this.骑士团职业开关 = new JButton();
        this.魔族突袭开关 = new JButton();
        this.jLabel2 = new JLabel();
        this.魔族攻城开关 = new JButton();
        this.jLabel5 = new JLabel();
        this.幸运职业开关 = new JButton();
        this.jLabel6 = new JLabel();
        this.神秘商人开关 = new JButton();
        this.jLabel7 = new JLabel();
        this.jPanel93 = new JPanel();
        this.jScrollPane136 = new JScrollPane();
        this.经验加成表 = new JTable();
        this.经验加成表序号 = new JTextField();
        this.经验加成表类型 = new JTextField();
        this.经验加成表数值 = new JTextField();
        this.经验加成表修改 = new JButton();
        this.jLabel384 = new JLabel();
        this.jLabel385 = new JLabel();
        this.jLabel386 = new JLabel();
        this.游戏经验加成说明 = new JButton();
        this.jPanel66 = new JPanel();
        this.jPanel69 = new JPanel();
        this.开启双倍经验 = new JButton();
        this.双倍经验持续时间 = new JTextField();
        this.jLabel359 = new JLabel();
        this.开启双倍爆率 = new JButton();
        this.双倍爆率持续时间 = new JTextField();
        this.jLabel360 = new JLabel();
        this.开启双倍金币 = new JButton();
        this.双倍金币持续时间 = new JTextField();
        this.jLabel361 = new JLabel();
        this.jPanel70 = new JPanel();
        this.开启三倍经验 = new JButton();
        this.三倍经验持续时间 = new JTextField();
        this.jLabel362 = new JLabel();
        this.开启三倍爆率 = new JButton();
        this.三倍爆率持续时间 = new JTextField();
        this.jLabel348 = new JLabel();
        this.开启三倍金币 = new JButton();
        this.三倍金币持续时间 = new JTextField();
        this.jLabel349 = new JLabel();
        this.jLabel3 = new JLabel();
        this.jLabel4 = new JLabel();
        this.jPanel52 = new JPanel();
        this.游戏广播 = new JPanel();
        this.jScrollPane9 = new JScrollPane();
        this.广播信息 = new JTable();
        this.刷新广告 = new JButton();
        this.删除广播 = new JButton();
        this.发布广告 = new JButton();
        this.修改广播 = new JButton();
        this.广播序号 = new JTextField();
        this.广播文本 = new JTextField();
        this.jPanel12 = new JPanel();
        this.jPanel72 = new JPanel();
        this.禁止登陆开关 = new JButton();
        this.滚动公告开关 = new JButton();
        this.玩家聊天开关 = new JButton();
        this.游戏升级快讯 = new JButton();
        this.丢出金币开关 = new JButton();
        this.丢出物品开关 = new JButton();
        this.游戏指令开关 = new JButton();
        this.上线提醒开关 = new JButton();
        this.回收地图开关 = new JButton();
        this.管理隐身开关 = new JButton();
        this.管理加速开关 = new JButton();
        this.游戏喇叭开关 = new JButton();
        this.玩家交易开关 = new JButton();
        this.雇佣商人开关 = new JButton();
        this.欢迎弹窗开关 = new JButton();
        this.登陆帮助开关 = new JButton();
        this.越级打怪开关 = new JButton();
        this.怪物状态开关 = new JButton();
        this.地图名称开关 = new JButton();
        this.过图存档开关 = new JButton();
        this.指令通知开关 = new JButton();
        this.吸怪检测开关 = new JButton();
        this.jTextField2 = new JTextField();
        this.jButton13 = new JButton();
        this.屠令广播开关 = new JButton();
        this.jScrollPane2 = new JScrollPane();
        this.输出窗口 = new JTextArea();
        this.jButton16 = new JButton();
        this.jTextField22 = new JTextField();
        this.jLabel29 = new JLabel();
        CongMS.startserverbutton = new JButton();
        this.jLabel28 = new JLabel();
        this.jLabel25 = new JLabel();
        this.jPanel5 = new JPanel();
        this.jScrollPane5 = new JScrollPane();
        this.jTextArea1 = new JTextArea();
        this.jPanel8 = new JPanel();
        this.jPanel6 = new JPanel();
        this.jLabel1 = new JLabel();
        this.jButton31 = new JButton();
        this.jButton29 = new JButton();
        this.jButton44 = new JButton();
        this.jButton39 = new JButton();
        this.jButton7 = new JButton();
        this.jButton8 = new JButton();
        this.jButton46 = new JButton();
        this.jPanel13 = new JPanel();
        this.重载副本按钮 = new JButton();
        this.重载爆率按钮 = new JButton();
        this.重载反应堆按钮 = new JButton();
        this.重载传送门按钮 = new JButton();
        this.重载商城按钮 = new JButton();
        this.重载商店按钮 = new JButton();
        this.重载包头按钮 = new JButton();
        this.重载任务 = new JButton();
        this.jPanel10 = new JPanel();
        this.jTabbedPane7 = new JTabbedPane();
        this.jPanel4 = new JPanel();
        this.jPanel59 = new JPanel();
        this.z2 = new JButton();
        this.z3 = new JButton();
        this.z1 = new JButton();
        this.z4 = new JButton();
        this.z5 = new JButton();
        this.z6 = new JButton();
        this.a1 = new JTextField();
        this.jLabel235 = new JLabel();
        this.jPanel57 = new JPanel();
        this.全服发送物品数量 = new JTextField();
        this.全服发送物品代码 = new JTextField();
        this.给予物品1 = new JButton();
        this.jLabel217 = new JLabel();
        this.jLabel234 = new JLabel();
        this.jPanel58 = new JPanel();
        this.全服发送装备装备加卷 = new JTextField();
        this.全服发送装备装备制作人 = new JTextField();
        this.全服发送装备装备力量 = new JTextField();
        this.全服发送装备装备MP = new JTextField();
        this.全服发送装备装备智力 = new JTextField();
        this.全服发送装备装备运气 = new JTextField();
        this.全服发送装备装备HP = new JTextField();
        this.全服发送装备装备攻击力 = new JTextField();
        this.全服发送装备装备给予时间 = new JTextField();
        this.全服发送装备装备可否交易 = new JTextField();
        this.全服发送装备装备敏捷 = new JTextField();
        this.全服发送装备物品ID = new JTextField();
        this.全服发送装备装备魔法力 = new JTextField();
        this.全服发送装备装备魔法防御 = new JTextField();
        this.全服发送装备装备物理防御 = new JTextField();
        this.给予装备1 = new JButton();
        this.jLabel219 = new JLabel();
        this.jLabel220 = new JLabel();
        this.jLabel221 = new JLabel();
        this.jLabel222 = new JLabel();
        this.jLabel223 = new JLabel();
        this.jLabel224 = new JLabel();
        this.jLabel225 = new JLabel();
        this.jLabel226 = new JLabel();
        this.jLabel227 = new JLabel();
        this.jLabel228 = new JLabel();
        this.jLabel229 = new JLabel();
        this.jLabel230 = new JLabel();
        this.jLabel231 = new JLabel();
        this.jLabel232 = new JLabel();
        this.jLabel233 = new JLabel();
        this.发送装备玩家姓名 = new JTextField();
        this.给予装备2 = new JButton();
        this.jLabel246 = new JLabel();
        this.jLabel244 = new JLabel();
        this.jPanel60 = new JPanel();
        this.个人发送物品数量 = new JTextField();
        this.个人发送物品玩家名字 = new JTextField();
        this.个人发送物品代码 = new JTextField();
        this.给予物品 = new JButton();
        this.jLabel240 = new JLabel();
        this.jLabel241 = new JLabel();
        this.jLabel242 = new JLabel();
        this.jPanel9 = new JPanel();
        this.jScrollPane134 = new JScrollPane();
        this.在线泡点设置 = new JTable();
        this.泡点序号 = new JTextField();
        this.泡点类型 = new JTextField();
        this.泡点值 = new JTextField();
        this.泡点值修改 = new JButton();
        this.jLabel322 = new JLabel();
        this.jLabel326 = new JLabel();
        this.jLabel327 = new JLabel();
        this.jPanel75 = new JPanel();
        this.泡点金币开关 = new JButton();
        this.泡点经验开关 = new JButton();
        this.泡点点券开关 = new JButton();
        this.泡点抵用开关 = new JButton();
        this.泡点豆豆开关 = new JButton();
        this.jLabel65 = new JLabel();
        this.jLabel328 = new JLabel();
        this.福利提示语言2 = new JLabel();
        this.jLabel60 = new JLabel();
        this.jLabel61 = new JLabel();
        this.jLabel62 = new JLabel();
        this.jPanel15 = new JPanel();
        this.jTabbedPane9 = new JTabbedPane();
        this.jPanel16 = new JPanel();
        this.jScrollPane26 = new JScrollPane();
        this.反应堆 = new JTable();
        this.jPanel61 = new JPanel();
        this.jButton26 = new JButton();
        this.反应堆序列号 = new JTextField();
        this.反应堆代码 = new JTextField();
        this.反应堆物品 = new JTextField();
        this.反应堆概率 = new JTextField();
        this.新增反应堆物品 = new JButton();
        this.删除反应堆物品1 = new JButton();
        this.查找反应堆掉落 = new JTextField();
        this.jButton36 = new JButton();
        this.查找物品 = new JTextField();
        this.jButton37 = new JButton();
        this.jLabel274 = new JLabel();
        this.jLabel275 = new JLabel();
        this.jLabel277 = new JLabel();
        this.jLabel278 = new JLabel();
        this.jLabel279 = new JLabel();
        this.jLabel280 = new JLabel();
        this.修改反应堆物品 = new JButton();
        this.重载箱子反应堆按钮 = new JButton();
        this.jLabel282 = new JLabel();
        this.删除反应堆物品代码 = new JTextField();
        this.删除反应堆物品 = new JButton();
        this.jPanel18 = new JPanel();
        this.jPanel27 = new JPanel();
        this.jScrollPane7 = new JScrollPane();
        this.怪物爆物 = new JTable();
        this.jLabel213 = new JLabel();
        this.怪物爆物序列号 = new JTextField();
        this.jLabel120 = new JLabel();
        this.怪物爆物怪物代码 = new JTextField();
        this.jLabel211 = new JLabel();
        this.怪物爆物物品代码 = new JTextField();
        this.jLabel212 = new JLabel();
        this.怪物爆物爆率 = new JTextField();
        this.jLabel39 = new JLabel();
        this.怪物爆物物品名称 = new JTextField();
        this.添加怪物爆物 = new JButton();
        this.删除怪物爆物 = new JButton();
        this.修改怪物爆物 = new JButton();
        this.刷新怪物爆物 = new JButton();
        this.jLabel324 = new JLabel();
        this.查询物品掉落代码 = new JTextField();
        this.查询物品掉落1 = new JButton();
        this.jLabel316 = new JLabel();
        this.jLabel325 = new JLabel();
        this.删除指定的掉落 = new JTextField();
        this.删除指定的掉落按键1 = new JButton();
        this.刷新怪物卡片 = new JButton();
        this.查询怪物掉落代码 = new JTextField();
        this.查询怪物掉落 = new JButton();
        this.jPanel19 = new JPanel();
        this.jPanel28 = new JPanel();
        this.jScrollPane8 = new JScrollPane();
        this.世界爆物 = new JTable();
        this.世界爆物序列号 = new JTextField();
        this.世界爆物物品代码 = new JTextField();
        this.世界爆物爆率 = new JTextField();
        this.添加世界爆物 = new JButton();
        this.删除世界爆物 = new JButton();
        this.jLabel210 = new JLabel();
        this.jLabel202 = new JLabel();
        this.jLabel209 = new JLabel();
        this.世界爆物名称 = new JTextField();
        this.jLabel40 = new JLabel();
        this.修改世界爆物 = new JButton();
        this.刷新世界爆物 = new JButton();
        this.jLabel323 = new JLabel();
        this.查询物品掉落代码1 = new JTextField();
        this.查询物品掉落 = new JButton();
        this.jLabel321 = new JLabel();
        this.删除指定的掉落1 = new JTextField();
        this.删除指定的掉落按键 = new JButton();
        this.jPanel20 = new JPanel();
        this.钓鱼管理 = new JPanel();
        this.jScrollPane10 = new JScrollPane();
        this.钓鱼物品 = new JTable();
        this.jSeparator11 = new JSeparator();
        this.jSeparator12 = new JSeparator();
        this.jPanel91 = new JPanel();
        this.修改钓鱼物品 = new JButton();
        this.刷新钓鱼物品 = new JButton();
        this.钓鱼物品代码 = new JTextField();
        this.新增钓鱼物品 = new JButton();
        this.钓鱼物品概率 = new JTextField();
        this.钓鱼物品名称 = new JTextField();
        this.删除钓鱼物品 = new JButton();
        this.钓鱼物品序号 = new JTextField();
        this.jLabel379 = new JLabel();
        this.jLabel380 = new JLabel();
        this.jLabel381 = new JLabel();
        this.jLabel382 = new JLabel();
        this.ZEVMS2提示框1 = new JLabel();
        this.jPanel21 = new JPanel();
        this.jTabbedPane8 = new JTabbedPane();
        this.jPanel23 = new JPanel();
        this.jScrollPane3 = new JScrollPane();
        this.账号信息 = new JTable();
        this.jPanel30 = new JPanel();
        this.抵用 = new JTextField();
        this.账号 = new JTextField();
        this.点券 = new JTextField();
        this.jLabel55 = new JLabel();
        this.jLabel131 = new JLabel();
        this.修改账号点券抵用 = new JButton();
        this.账号ID = new JTextField();
        this.jLabel206 = new JLabel();
        this.jLabel312 = new JLabel();
        this.管理1 = new JTextField();
        this.jLabel353 = new JLabel();
        this.QQ = new JTextField();
        this.jLabel357 = new JLabel();
        this.jPanel32 = new JPanel();
        this.注册的账号 = new JTextField();
        this.注册的密码 = new JTextField();
        this.jButton35 = new JButton();
        this.jLabel111 = new JLabel();
        this.jLabel201 = new JLabel();
        this.jButton32 = new JButton();
        this.刷新账号信息 = new JButton();
        this.离线账号 = new JButton();
        this.解封 = new JButton();
        this.已封账号 = new JButton();
        this.在线账号 = new JButton();
        this.删除账号 = new JButton();
        this.封锁账号 = new JButton();
        this.解卡 = new JButton();
        this.显示在线账号 = new JLabel();
        this.账号提示语言 = new JLabel();
        this.jButton12 = new JButton();
        this.账号操作 = new JTextField();
        this.角色信息1 = new JPanel();
        this.jScrollPane4 = new JScrollPane();
        this.角色信息 = new JTable();
        this.刷新角色信息 = new JButton();
        this.显示管理角色 = new JButton();
        this.jButton38 = new JButton();
        this.删除角色 = new JButton();
        this.角色昵称 = new JTextField();
        this.等级 = new JTextField();
        this.力量 = new JTextField();
        this.敏捷 = new JTextField();
        this.智力 = new JTextField();
        this.运气 = new JTextField();
        this.HP = new JTextField();
        this.MP = new JTextField();
        this.金币1 = new JTextField();
        this.地图 = new JTextField();
        this.GM = new JTextField();
        this.jLabel182 = new JLabel();
        this.jLabel183 = new JLabel();
        this.jLabel184 = new JLabel();
        this.jLabel185 = new JLabel();
        this.jLabel186 = new JLabel();
        this.jLabel187 = new JLabel();
        this.jLabel189 = new JLabel();
        this.jLabel190 = new JLabel();
        this.jLabel191 = new JLabel();
        this.jLabel192 = new JLabel();
        this.jLabel193 = new JLabel();
        this.角色ID = new JTextField();
        this.卡号自救1 = new JButton();
        this.卡号自救2 = new JButton();
        this.jLabel203 = new JLabel();
        this.查看技能 = new JButton();
        this.查看背包 = new JButton();
        this.卡家族解救 = new JButton();
        this.脸型 = new JTextField();
        this.发型 = new JTextField();
        this.jLabel214 = new JLabel();
        this.离线角色 = new JButton();
        this.在线角色 = new JButton();
        this.显示在线玩家 = new JLabel();
        this.角色背包 = new JPanel();
        this.jTabbedPane5 = new JTabbedPane();
        this.jPanel39 = new JPanel();
        this.jScrollPane15 = new JScrollPane();
        this.角色背包穿戴 = new JTable();
        this.背包物品名字1 = new JTextField();
        this.身上穿戴序号1 = new JTextField();
        this.背包物品代码1 = new JTextField();
        this.jLabel276 = new JLabel();
        this.jLabel283 = new JLabel();
        this.jLabel287 = new JLabel();
        this.删除穿戴装备 = new JButton();
        this.jPanel40 = new JPanel();
        this.jScrollPane16 = new JScrollPane();
        this.角色装备背包 = new JTable();
        this.装备背包物品名字 = new JTextField();
        this.装备背包物品序号 = new JTextField();
        this.装备背包物品代码 = new JTextField();
        this.jLabel288 = new JLabel();
        this.jLabel289 = new JLabel();
        this.jLabel290 = new JLabel();
        this.删除装备背包 = new JButton();
        this.jPanel41 = new JPanel();
        this.jScrollPane17 = new JScrollPane();
        this.角色消耗背包 = new JTable();
        this.消耗背包物品名字 = new JTextField();
        this.消耗背包物品序号 = new JTextField();
        this.消耗背包物品代码 = new JTextField();
        this.jLabel291 = new JLabel();
        this.jLabel292 = new JLabel();
        this.jLabel293 = new JLabel();
        this.删除消耗背包 = new JButton();
        this.jPanel42 = new JPanel();
        this.jScrollPane18 = new JScrollPane();
        this.角色设置背包 = new JTable();
        this.设置背包物品名字 = new JTextField();
        this.设置背包物品序号 = new JTextField();
        this.设置背包物品代码 = new JTextField();
        this.jLabel294 = new JLabel();
        this.jLabel295 = new JLabel();
        this.jLabel296 = new JLabel();
        this.删除设置背包 = new JButton();
        this.jPanel43 = new JPanel();
        this.jScrollPane19 = new JScrollPane();
        this.角色其他背包 = new JTable();
        this.其他背包物品名字 = new JTextField();
        this.其他背包物品序号 = new JTextField();
        this.其他背包物品代码 = new JTextField();
        this.jLabel297 = new JLabel();
        this.jLabel298 = new JLabel();
        this.jLabel299 = new JLabel();
        this.删除其他背包 = new JButton();
        this.jPanel44 = new JPanel();
        this.jScrollPane20 = new JScrollPane();
        this.角色特殊背包 = new JTable();
        this.特殊背包物品名字 = new JTextField();
        this.特殊背包物品序号 = new JTextField();
        this.特殊背包物品代码 = new JTextField();
        this.jLabel300 = new JLabel();
        this.jLabel301 = new JLabel();
        this.jLabel302 = new JLabel();
        this.删除特殊背包 = new JButton();
        this.jPanel45 = new JPanel();
        this.jScrollPane21 = new JScrollPane();
        this.角色游戏仓库 = new JTable();
        this.游戏仓库物品名字 = new JTextField();
        this.游戏仓库物品序号 = new JTextField();
        this.游戏仓库物品代码 = new JTextField();
        this.jLabel303 = new JLabel();
        this.jLabel304 = new JLabel();
        this.jLabel305 = new JLabel();
        this.删除游戏仓库 = new JButton();
        this.jPanel46 = new JPanel();
        this.jScrollPane22 = new JScrollPane();
        this.角色商城仓库 = new JTable();
        this.商城仓库物品名字 = new JTextField();
        this.商城仓库物品序号 = new JTextField();
        this.商城仓库物品代码 = new JTextField();
        this.jLabel306 = new JLabel();
        this.jLabel307 = new JLabel();
        this.jLabel308 = new JLabel();
        this.删除商城仓库 = new JButton();
        this.jPanel48 = new JPanel();
        this.jScrollPane30 = new JScrollPane();
        this.角色点券拍卖行 = new JTable();
        this.拍卖行物品名字1 = new JTextField();
        this.角色点券拍卖行序号 = new JTextField();
        this.拍卖行物品代码1 = new JTextField();
        this.jLabel354 = new JLabel();
        this.jLabel355 = new JLabel();
        this.jLabel356 = new JLabel();
        this.删除拍卖行1 = new JButton();
        this.jPanel47 = new JPanel();
        this.jScrollPane23 = new JScrollPane();
        this.角色金币拍卖行 = new JTable();
        this.拍卖行物品名字 = new JTextField();
        this.角色金币拍卖行序号 = new JTextField();
        this.拍卖行物品代码 = new JTextField();
        this.jLabel309 = new JLabel();
        this.jLabel310 = new JLabel();
        this.jLabel311 = new JLabel();
        this.删除拍卖行 = new JButton();
        this.技能 = new JPanel();
        this.jScrollPane14 = new JScrollPane();
        this.技能信息 = new JTable();
        this.技能代码 = new JTextField();
        this.技能目前等级 = new JTextField();
        this.技能最高等级 = new JTextField();
        this.技能名字 = new JTextField();
        this.jLabel86 = new JLabel();
        this.jLabel89 = new JLabel();
        this.jLabel107 = new JLabel();
        this.修改技能 = new JButton();
        this.技能序号 = new JTextField();
        this.jLabel188 = new JLabel();
        this.jLabel204 = new JLabel();
        this.jLabel205 = new JLabel();
        this.删除技能 = new JButton();
        this.修改技能1 = new JButton();
        this.jPanel50 = new JPanel();
        this.jScrollPane24 = new JScrollPane();
        this.家族信息 = new JTable();
        this.刷新家族信息 = new JButton();
        this.jLabel194 = new JLabel();
        this.家族ID = new JTextField();
        this.家族名称 = new JTextField();
        this.jLabel195 = new JLabel();
        this.家族族长 = new JTextField();
        this.jLabel196 = new JLabel();
        this.jLabel198 = new JLabel();
        this.家族成员2 = new JTextField();
        this.jLabel199 = new JLabel();
        this.家族成员3 = new JTextField();
        this.jLabel200 = new JLabel();
        this.家族成员4 = new JTextField();
        this.jLabel313 = new JLabel();
        this.家族成员5 = new JTextField();
        this.jLabel314 = new JLabel();
        this.家族GP = new JTextField();
        this.jButton34 = new JButton();
        this.jPanel65 = new JPanel();
        this.jScrollPane107 = new JScrollPane();
        this.封IP = new JTable();
        this.jScrollPane108 = new JScrollPane();
        this.封MAC = new JTable();
        this.刷新封IP = new JButton();
        this.刷新封MAC = new JButton();
        this.删除IP代码 = new JTextField();
        this.删除MAC = new JButton();
        this.删除IP = new JButton();
        this.删MAC代码 = new JTextField();
        this.jLabel346 = new JLabel();
        this.jLabel347 = new JLabel();
        this.jPanel22 = new JPanel();
        this.jTabbedPane3 = new JTabbedPane();
        this.jPanel24 = new JPanel();
        this.主题馆 = new JButton();
        this.读取热销产品 = new JButton();
        this.活动 = new JButton();
        this.jButton9 = new JButton();
        this.jPanel25 = new JPanel();
        this.帽子 = new JButton();
        this.脸饰 = new JButton();
        this.眼饰 = new JButton();
        this.长袍 = new JButton();
        this.上衣 = new JButton();
        this.裙裤 = new JButton();
        this.鞋子 = new JButton();
        this.手套 = new JButton();
        this.武器 = new JButton();
        this.戒指 = new JButton();
        this.飞镖 = new JButton();
        this.披风 = new JButton();
        this.骑宠 = new JButton();
        this.jPanel26 = new JPanel();
        this.喜庆物品 = new JButton();
        this.通讯物品 = new JButton();
        this.卷轴 = new JButton();
        this.jPanel29 = new JPanel();
        this.会员卡 = new JButton();
        this.表情 = new JButton();
        this.个人商店 = new JButton();
        this.纪念日 = new JButton();
        this.游戏 = new JButton();
        this.效果 = new JButton();
        this.jPanel31 = new JPanel();
        this.宠物 = new JButton();
        this.宠物服饰 = new JButton();
        this.其他 = new JButton();
        this.jScrollPane6 = new JScrollPane();
        this.charTable = new JTable();
        this.jPanel33 = new JPanel();
        this.商品数量 = new JTextField();
        this.商品编码 = new JTextField();
        this.商品代码 = new JTextField();
        this.jLabel30 = new JLabel();
        this.jLabel31 = new JLabel();
        this.商品价格 = new JTextField();
        this.商品时间 = new JTextField();
        this.jLabel32 = new JLabel();
        this.jLabel33 = new JLabel();
        this.jLabel34 = new JLabel();
        this.jLabel35 = new JLabel();
        this.商品库存 = new JTextField();
        this.商品折扣 = new JTextField();
        this.jLabel37 = new JLabel();
        this.jLabel36 = new JLabel();
        this.每日限购 = new JTextField();
        this.jLabel38 = new JLabel();
        this.货币类型 = new JTextField();
        this.jScrollPane132 = new JScrollPane();
        this.商城扩充价格 = new JTable();
        this.商城扩充价格修改 = new JTextField();
        this.商品出售状态 = new JTextField();
        this.显示类型 = new JTextField();
        this.jPanel34 = new JPanel();
        this.jButton3 = new JButton();
        this.jButton25 = new JButton();
        this.jButton27 = new JButton();
        this.jButton28 = new JButton();
        this.添加 = new JButton();
        this.jButton2 = new JButton();
        this.修改 = new JButton();
        this.修改背包扩充价格 = new JButton();
        this.jPanel35 = new JPanel();
        this.jPanel36 = new JPanel();
        this.jPanel55 = new JPanel();
        this.查询商店2 = new JButton();
        this.查询商店 = new JTextField();
        this.jLabel270 = new JLabel();
        this.jPanel56 = new JPanel();
        this.删除商品 = new JButton();
        this.新增商品 = new JButton();
        this.商品序号 = new JTextField();
        this.商店代码 = new JTextField();
        this.商品物品代码 = new JTextField();
        this.商品售价金币 = new JTextField();
        this.jLabel268 = new JLabel();
        this.jLabel269 = new JLabel();
        this.jLabel271 = new JLabel();
        this.jLabel272 = new JLabel();
        this.修改商品 = new JButton();
        this.商品名称 = new JTextField();
        this.jLabel273 = new JLabel();
        this.jButton33 = new JButton();
        this.jScrollPane25 = new JScrollPane();
        this.游戏商店2 = new JTable();
        this.jPanel11 = new JPanel();
        this.sendNotice = new JButton();
        this.sendWinNotice = new JButton();
        this.sendMsgNotice = new JButton();
        this.sendNpcTalkNotice = new JButton();
        this.noticeText = new JTextField();
        this.jLabel117 = new JLabel();
        this.jLabel118 = new JLabel();
        this.jLabel119 = new JLabel();
        this.jLabel106 = new JLabel();
        this.公告发布喇叭代码 = new JTextField();
        this.jButton45 = new JButton();
        this.jLabel259 = new JLabel();
        this.jPanel37 = new JPanel();
        this.jScrollPane27 = new JScrollPane();
        this.游戏道具 = new JTable();
        this.游戏道具代码 = new JTextField();
        this.jLabel338 = new JLabel();
        this.jPanel38 = new JPanel();
        this.查找道具 = new JButton();
        this.删除道具 = new JButton();
        this.jLabel337 = new JLabel();
        this.jPanel49 = new JPanel();
        this.查找道具1 = new JButton();
        this.删除道具1 = new JButton();
        this.jLabel339 = new JLabel();
        this.jPanel62 = new JPanel();
        this.查找道具2 = new JButton();
        this.删除道具2 = new JButton();
        this.jLabel340 = new JLabel();
        this.jPanel63 = new JPanel();
        this.查找道具3 = new JButton();
        this.删除道具3 = new JButton();
        this.jLabel341 = new JLabel();
        this.jPanel64 = new JPanel();
        this.查找道具4 = new JButton();
        this.删除道具4 = new JButton();
        this.jLabel342 = new JLabel();
        this.jLabel343 = new JLabel();
        this.jLabel344 = new JLabel();
        this.jLabel345 = new JLabel();
        this.jPanel2 = new JPanel();
        this.setDefaultCloseOperation(3);
        this.setBackground(new Color(255, 255, 255));
        this.setResizable(false);
        this.jTabbedPane2.setBackground(new Color(255, 255, 255));
        this.jLabel56.setFont(new Font("幼圆", 0, 15));
        this.jLabel57.setFont(new Font("幼圆", 0, 15));
        this.jLabel58.setFont(new Font("幼圆", 0, 15));
        this.jLabel59.setFont(new Font("幼圆", 0, 15));
        this.jTabbedPane6.setBackground(new Color(255, 255, 255));
        this.jTabbedPane6.setFont(new Font("幼圆", 0, 12));
        this.jPanel51.setBackground(new Color(255, 255, 240));
        this.jPanel74.setBackground(new Color(255, 255, 240));
        this.jPanel74.setBorder(BorderFactory.createTitledBorder(null, "风之大陆", 2, 0, new Font("宋体", 1, 12)));
        this.jPanel74.setLayout(new AbsoluteLayout());
        this.蓝蜗牛开关.setText("蓝蜗牛:X");
        this.蓝蜗牛开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.蓝蜗牛开关ActionPerformed(evt);
            }
        });
        this.jPanel74.add(this.蓝蜗牛开关, new AbsoluteConstraints(30, 20, 110, 40));
        this.蘑菇仔开关.setText("蘑菇仔:X");
        this.蘑菇仔开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.蘑菇仔开关ActionPerformed(evt);
            }
        });
        this.jPanel74.add(this.蘑菇仔开关, new AbsoluteConstraints(170, 20, 110, 40));
        this.绿水灵开关.setText("绿水灵:X");
        this.绿水灵开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.绿水灵开关ActionPerformed(evt);
            }
        });
        this.jPanel74.add(this.绿水灵开关, new AbsoluteConstraints(310, 20, 110, 40));
        this.漂漂猪开关.setText("漂漂猪:X");
        this.漂漂猪开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.漂漂猪开关ActionPerformed(evt);
            }
        });
        this.jPanel74.add(this.漂漂猪开关, new AbsoluteConstraints(310, 80, 110, 40));
        this.小青蛇开关.setText("小青蛇:X");
        this.小青蛇开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.小青蛇开关ActionPerformed(evt);
            }
        });
        this.jPanel74.add(this.小青蛇开关, new AbsoluteConstraints(460, 80, 110, 40));
        this.红螃蟹开关.setText("红螃蟹:X");
        this.红螃蟹开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.红螃蟹开关ActionPerformed(evt);
            }
        });
        this.jPanel74.add(this.红螃蟹开关, new AbsoluteConstraints(610, 80, 110, 40));
        this.大海龟开关.setText("大海龟:X");
        this.大海龟开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.大海龟开关ActionPerformed(evt);
            }
        });
        this.jPanel74.add(this.大海龟开关, new AbsoluteConstraints(30, 83, 110, 40));
        this.章鱼怪开关.setText("章鱼怪:X");
        this.章鱼怪开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.章鱼怪开关ActionPerformed(evt);
            }
        });
        this.jPanel74.add(this.章鱼怪开关, new AbsoluteConstraints(170, 210, 110, 40));
        this.顽皮猴开关.setText("顽皮猴:X");
        this.顽皮猴开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.顽皮猴开关ActionPerformed(evt);
            }
        });
        this.jPanel74.add(this.顽皮猴开关, new AbsoluteConstraints(170, 80, 110, 40));
        this.星精灵开关.setText("星精灵:X");
        this.星精灵开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.星精灵开关ActionPerformed(evt);
            }
        });
        this.jPanel74.add(this.星精灵开关, new AbsoluteConstraints(460, 23, 110, 40));
        this.胖企鹅开关.setText("胖企鹅:X");
        this.胖企鹅开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.胖企鹅开关ActionPerformed(evt);
            }
        });
        this.jPanel74.add(this.胖企鹅开关, new AbsoluteConstraints(610, 23, 110, 40));
        this.白雪人开关.setText("白雪人:X");
        this.白雪人开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.白雪人开关ActionPerformed(evt);
            }
        });
        this.jPanel74.add(this.白雪人开关, new AbsoluteConstraints(760, 23, 110, 40));
        this.石头人开关.setText("石头人:X");
        this.石头人开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.石头人开关ActionPerformed(evt);
            }
        });
        this.jPanel74.add(this.石头人开关, new AbsoluteConstraints(760, 80, 110, 40));
        this.紫色猫开关.setText("紫色猫:X");
        this.紫色猫开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.紫色猫开关ActionPerformed(evt);
            }
        });
        this.jPanel74.add(this.紫色猫开关, new AbsoluteConstraints(30, 150, 110, 40));
        this.大灰狼开关.setText("大灰狼:X");
        this.大灰狼开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.大灰狼开关ActionPerformed(evt);
            }
        });
        this.jPanel74.add(this.大灰狼开关, new AbsoluteConstraints(170, 150, 110, 40));
        this.喷火龙开关.setText("喷火龙:X");
        this.喷火龙开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.喷火龙开关ActionPerformed(evt);
            }
        });
        this.jPanel74.add(this.喷火龙开关, new AbsoluteConstraints(760, 150, 110, 40));
        this.火野猪开关.setText("火野猪:X");
        this.火野猪开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.火野猪开关ActionPerformed(evt);
            }
        });
        this.jPanel74.add(this.火野猪开关, new AbsoluteConstraints(460, 150, 110, 40));
        this.小白兔开关.setText("小白兔:X");
        this.小白兔开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.小白兔开关ActionPerformed(evt);
            }
        });
        this.jPanel74.add(this.小白兔开关, new AbsoluteConstraints(610, 150, 110, 40));
        this.青鳄鱼开关.setText("青鳄鱼:X");
        this.青鳄鱼开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.青鳄鱼开关ActionPerformed(evt);
            }
        });
        this.jPanel74.add(this.青鳄鱼开关, new AbsoluteConstraints(30, 210, 110, 40));
        this.花蘑菇开关.setText("花蘑菇:X");
        this.花蘑菇开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.花蘑菇开关ActionPerformed(evt);
            }
        });
        this.jPanel74.add(this.花蘑菇开关, new AbsoluteConstraints(310, 150, 110, 40));
        this.jLabel11.setFont(new Font("宋体", 1, 12));
        this.jLabel11.setText("游戏大区请勿全部都开启,会炸客户端的，每个区所建立的角色是不一样的,进入游戏后其他没有变化");
        this.jPanel74.add(this.jLabel11, new AbsoluteConstraints(290, 200, -1, -1));
        this.jLabel63.setFont(new Font("宋体", 1, 12));
        this.jLabel63.setText("本页所有功能都需要重启服务端生效，请务必在开启服务端之前配置好");
        this.jPanel74.add(this.jLabel63, new AbsoluteConstraints(350, 230, -1, -1));
        this.jPanel17.setBackground(new Color(255, 255, 240));
        this.jPanel17.setBorder(BorderFactory.createTitledBorder(null, "等级上限", 2, 2, new Font("幼圆", 0, 18)));
        this.jPanel17.setLayout(new AbsoluteLayout());
        this.冒险家等级上限.setText("250");
        this.jPanel17.add(this.冒险家等级上限, new AbsoluteConstraints(590, 40, 100, 40));
        this.修改冒险家等级上限.setText("修改");
        this.修改冒险家等级上限.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.修改冒险家等级上限ActionPerformed(evt);
            }
        });
        this.jPanel17.add(this.修改冒险家等级上限, new AbsoluteConstraints(710, 40, 70, 40));
        this.jLabel253.setFont(new Font("幼圆", 0, 14));
        this.jLabel253.setText("冒险家等级上限");
        this.jPanel17.add(this.jLabel253, new AbsoluteConstraints(470, 30, -1, 60));
        this.骑士团等级上限.setText("250");
        this.jPanel17.add(this.骑士团等级上限, new AbsoluteConstraints(220, 40, 100, 40));
        this.jLabel252.setFont(new Font("幼圆", 0, 14));
        this.jLabel252.setText("骑士团等级上限");
        this.jPanel17.add(this.jLabel252, new AbsoluteConstraints(110, 30, -1, 60));
        this.修改骑士团等级上限.setText("修改");
        this.修改骑士团等级上限.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.修改骑士团等级上限ActionPerformed(evt);
            }
        });
        this.jPanel17.add(this.修改骑士团等级上限, new AbsoluteConstraints(350, 40, 70, 40));
        this.jPanel7.setBackground(new Color(255, 255, 240));
        this.jPanel7.setBorder(BorderFactory.createTitledBorder(null, "职业开关", 2, 2, new Font("幼圆", 0, 18)));
        this.jPanel7.setLayout(new AbsoluteLayout());
        this.冒险家职业开关.setFont(new Font("幼圆", 0, 14));
        this.冒险家职业开关.setText("冒险家");
        this.冒险家职业开关.setToolTipText("<html>\n<strong><font color=\"#FF0000\">开启:</font></strong><br> \n开启后玩家可以创建冒险家职业。<br> \n<strong><font color=\"#FF0000\">关闭:</font></strong><br> \n关闭后玩家不能创建冒险家职业。<br> <br>  \n");
        this.冒险家职业开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.冒险家职业开关ActionPerformed(evt);
            }
        });
        this.jPanel7.add(this.冒险家职业开关, new AbsoluteConstraints(80, 60, 140, 40));
        this.战神职业开关.setFont(new Font("幼圆", 0, 14));
        this.战神职业开关.setText("战神");
        this.战神职业开关.setToolTipText("<html>\n<strong><font color=\"#FF0000\">开启:</font></strong><br> \n开启后玩家可以创建战神职业。<br> \n<strong><font color=\"#FF0000\">关闭:</font></strong><br> \n关闭后玩家不能创建战神职业。<br> <br>  ");
        this.战神职业开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.战神职业开关ActionPerformed(evt);
            }
        });
        this.jPanel7.add(this.战神职业开关, new AbsoluteConstraints(630, 60, 140, 40));
        this.骑士团职业开关.setFont(new Font("幼圆", 0, 14));
        this.骑士团职业开关.setText("骑士团");
        this.骑士团职业开关.setToolTipText("<html>\n<strong><font color=\"#FF0000\">开启:</font></strong><br> \n开启后玩家可以创建骑士团职业。<br> \n<strong><font color=\"#FF0000\">关闭:</font></strong><br> \n关闭后玩家不能创建骑士团职业。<br> <br>  ");
        this.骑士团职业开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.骑士团职业开关ActionPerformed(evt);
            }
        });
        this.jPanel7.add(this.骑士团职业开关, new AbsoluteConstraints(360, 60, 140, 40));
        this.魔族突袭开关.setText("开关");
        this.魔族突袭开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.魔族突袭开关ActionPerformed(evt);
            }
        });
        this.jLabel2.setFont(new Font("宋体", 0, 18));
        this.jLabel2.setText("魔族袭击");
        this.魔族攻城开关.setText("开关");
        this.魔族攻城开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.魔族攻城开关ActionPerformed(evt);
            }
        });
        this.jLabel5.setFont(new Font("宋体", 0, 18));
        this.jLabel5.setText("魔族攻城");
        this.幸运职业开关.setText("开关");
        this.幸运职业开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.幸运职业开关ActionPerformed(evt);
            }
        });
        this.jLabel6.setFont(new Font("宋体", 0, 18));
        this.jLabel6.setText("幸运职业");
        this.神秘商人开关.setText("开关");
        this.神秘商人开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.神秘商人开关ActionPerformed(evt);
            }
        });
        this.jLabel7.setFont(new Font("宋体", 0, 18));
        this.jLabel7.setText("神秘商人");
        final GroupLayout jPanel51Layout = new GroupLayout(this.jPanel51);
        this.jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(jPanel51Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jPanel17, -1, -1, 32767).addComponent(this.jPanel7, -1, -1, 32767).addGroup(jPanel51Layout.createSequentialGroup().addContainerGap().addGroup(jPanel51Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.jLabel2, -1, -1, 32767).addComponent(this.jLabel7, -1, -1, 32767)).addGap(18, 18, 18).addGroup(jPanel51Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel51Layout.createSequentialGroup().addComponent(this.神秘商人开关, -2, 105, -2).addContainerGap(-1, 32767)).addGroup(jPanel51Layout.createSequentialGroup().addComponent(this.魔族突袭开关, -2, 105, -2).addGap(141, 141, 141).addComponent(this.jLabel5, -2, 80, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.魔族攻城开关, -2, 105, -2).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(this.jLabel6, -2, 74, -2).addGap(18, 18, 18).addComponent(this.幸运职业开关, -2, 105, -2).addGap(16, 16, 16)))).addGroup(jPanel51Layout.createSequentialGroup().addComponent(this.jPanel74, -2, 1311, -2).addGap(0, 0, 32767)));
        jPanel51Layout.setVerticalGroup(jPanel51Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel51Layout.createSequentialGroup().addComponent(this.jPanel74, -2, 260, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jPanel17, -2, 106, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jPanel7, -2, 151, -2).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(jPanel51Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel51Layout.createSequentialGroup().addGap(1, 1, 1).addComponent(this.魔族突袭开关, -2, 37, -2)).addGroup(jPanel51Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.jLabel5).addComponent(this.幸运职业开关, -2, 38, -2).addComponent(this.jLabel6).addComponent(this.魔族攻城开关, -2, 38, -2))).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addGroup(jPanel51Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.神秘商人开关, -2, 37, -2).addComponent(this.jLabel7)).addContainerGap()));
        this.jTabbedPane6.addTab("游戏设置", this.jPanel51);
        this.jPanel93.setBackground(new Color(255, 255, 240));
        this.jPanel93.setBorder(BorderFactory.createTitledBorder(null, "游戏经验加成", 2, 2, new Font("幼圆", 0, 24)));
        this.jPanel93.setLayout(new AbsoluteLayout());
        this.经验加成表.setFont(new Font("幼圆", 0, 20));
        this.经验加成表.setForeground(new Color(102, 102, 255));
        this.经验加成表.setModel(new DefaultTableModel(new Object[0][], new String[]{"序号", "类型", "数值"}) {
            boolean[] canEdit = {false, false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.经验加成表.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane136.setViewportView(this.经验加成表);
        this.jPanel93.add(this.jScrollPane136, new AbsoluteConstraints(130, 70, 630, 390));
        this.经验加成表序号.setEditable(false);
        this.jPanel93.add(this.经验加成表序号, new AbsoluteConstraints(140, 500, 70, 30));
        this.经验加成表类型.setEditable(false);
        this.jPanel93.add(this.经验加成表类型, new AbsoluteConstraints(210, 500, 230, 30));
        this.jPanel93.add(this.经验加成表数值, new AbsoluteConstraints(440, 500, 100, 30));
        this.经验加成表修改.setFont(new Font("幼圆", 0, 15));
        this.经验加成表修改.setText("修改");
        this.经验加成表修改.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.经验加成表修改ActionPerformed(evt);
            }
        });
        this.jPanel93.add(this.经验加成表修改, new AbsoluteConstraints(540, 500, 100, 30));
        this.jLabel384.setFont(new Font("幼圆", 0, 18));
        this.jLabel384.setText("数值；");
        this.jPanel93.add(this.jLabel384, new AbsoluteConstraints(440, 480, -1, -1));
        this.jLabel385.setFont(new Font("幼圆", 0, 18));
        this.jLabel385.setText("类型；");
        this.jPanel93.add(this.jLabel385, new AbsoluteConstraints(210, 480, -1, -1));
        this.jLabel386.setFont(new Font("幼圆", 0, 18));
        this.jLabel386.setText("序号；");
        this.jPanel93.add(this.jLabel386, new AbsoluteConstraints(140, 480, -1, -1));
        this.游戏经验加成说明.setFont(new Font("幼圆", 0, 15));
        this.游戏经验加成说明.setText("说明");
        this.游戏经验加成说明.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.游戏经验加成说明ActionPerformed(evt);
            }
        });
        this.jPanel93.add(this.游戏经验加成说明, new AbsoluteConstraints(640, 500, 100, 30));
        this.jTabbedPane6.addTab("经验加成", this.jPanel93);
        this.jPanel66.setBackground(new Color(255, 255, 240));
        this.jPanel66.setBorder(BorderFactory.createTitledBorder("活动经验"));
        this.jPanel69.setBackground(new Color(255, 255, 240));
        this.jPanel69.setBorder(BorderFactory.createTitledBorder(null, "2倍率活动", 2, 2, new Font("幼圆", 0, 24)));
        this.jPanel69.setLayout(new AbsoluteLayout());
        this.开启双倍经验.setFont(new Font("幼圆", 0, 15));
        this.开启双倍经验.setText("开启双倍经验");
        this.开启双倍经验.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.开启双倍经验ActionPerformed(evt);
            }
        });
        this.jPanel69.add(this.开启双倍经验, new AbsoluteConstraints(280, 70, 140, 40));
        this.jPanel69.add(this.双倍经验持续时间, new AbsoluteConstraints(140, 80, 120, -1));
        this.jLabel359.setFont(new Font("幼圆", 0, 14));
        this.jLabel359.setText("持续时间/h");
        this.jPanel69.add(this.jLabel359, new AbsoluteConstraints(60, 80, -1, 20));
        this.开启双倍爆率.setFont(new Font("幼圆", 0, 15));
        this.开启双倍爆率.setText("开启双倍爆率");
        this.开启双倍爆率.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.开启双倍爆率ActionPerformed(evt);
            }
        });
        this.jPanel69.add(this.开启双倍爆率, new AbsoluteConstraints(670, 70, 140, 40));
        this.jPanel69.add(this.双倍爆率持续时间, new AbsoluteConstraints(540, 80, 120, -1));
        this.jLabel360.setFont(new Font("幼圆", 0, 14));
        this.jLabel360.setText("持续时间/h");
        this.jPanel69.add(this.jLabel360, new AbsoluteConstraints(460, 80, -1, 20));
        this.开启双倍金币.setFont(new Font("幼圆", 0, 15));
        this.开启双倍金币.setText("开启双倍金币");
        this.开启双倍金币.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.开启双倍金币ActionPerformed(evt);
            }
        });
        this.jPanel69.add(this.开启双倍金币, new AbsoluteConstraints(280, 150, 140, 40));
        this.jPanel69.add(this.双倍金币持续时间, new AbsoluteConstraints(140, 160, 120, -1));
        this.jLabel361.setFont(new Font("幼圆", 0, 14));
        this.jLabel361.setText("持续时间/h");
        this.jPanel69.add(this.jLabel361, new AbsoluteConstraints(60, 160, -1, 20));
        this.jPanel70.setBackground(new Color(255, 255, 240));
        this.jPanel70.setBorder(BorderFactory.createTitledBorder(null, "3倍率活动", 2, 2, new Font("幼圆", 0, 24)));
        this.jPanel70.setLayout(new AbsoluteLayout());
        this.开启三倍经验.setFont(new Font("幼圆", 0, 15));
        this.开启三倍经验.setText("开启三倍经验");
        this.开启三倍经验.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.开启三倍经验ActionPerformed(evt);
            }
        });
        this.jPanel70.add(this.开启三倍经验, new AbsoluteConstraints(280, 70, 140, 40));
        this.jPanel70.add(this.三倍经验持续时间, new AbsoluteConstraints(140, 80, 120, -1));
        this.jLabel362.setFont(new Font("幼圆", 0, 14));
        this.jLabel362.setText("持续时间/h");
        this.jPanel70.add(this.jLabel362, new AbsoluteConstraints(50, 80, -1, 20));
        this.开启三倍爆率.setFont(new Font("幼圆", 0, 15));
        this.开启三倍爆率.setText("开启三倍爆率");
        this.开启三倍爆率.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.开启三倍爆率ActionPerformed(evt);
            }
        });
        this.jPanel70.add(this.开启三倍爆率, new AbsoluteConstraints(670, 70, 140, 40));
        this.jPanel70.add(this.三倍爆率持续时间, new AbsoluteConstraints(540, 80, 120, -1));
        this.jLabel348.setFont(new Font("幼圆", 0, 14));
        this.jLabel348.setText("持续时间/h");
        this.jPanel70.add(this.jLabel348, new AbsoluteConstraints(460, 80, -1, 20));
        this.开启三倍金币.setFont(new Font("幼圆", 0, 15));
        this.开启三倍金币.setText("开启三倍金币");
        this.开启三倍金币.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.开启三倍金币ActionPerformed(evt);
            }
        });
        this.jPanel70.add(this.开启三倍金币, new AbsoluteConstraints(280, 160, 140, 40));
        this.jPanel70.add(this.三倍金币持续时间, new AbsoluteConstraints(140, 170, 120, -1));
        this.jLabel349.setFont(new Font("幼圆", 0, 14));
        this.jLabel349.setText("持续时间/h");
        this.jPanel70.add(this.jLabel349, new AbsoluteConstraints(50, 170, -1, 20));
        this.jLabel3.setFont(new Font("宋体", 1, 24));
        this.jLabel3.setText("功能说明：本功能无需重启服务端立即生效");
        this.jLabel4.setFont(new Font("宋体", 1, 24));
        this.jLabel4.setText("单位换算 h=小时 时间到期自动解除倍率");
        final GroupLayout jPanel66Layout = new GroupLayout(this.jPanel66);
        this.jPanel66.setLayout(jPanel66Layout);
        jPanel66Layout.setHorizontalGroup(jPanel66Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel66Layout.createSequentialGroup().addGroup(jPanel66Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel66Layout.createSequentialGroup().addGap(179, 179, 179).addComponent(this.jLabel3, -2, 491, -2)).addGroup(jPanel66Layout.createSequentialGroup().addGap(200, 200, 200).addComponent(this.jLabel4))).addContainerGap(-1, 32767)).addComponent(this.jPanel69, -1, -1, 32767).addComponent(this.jPanel70, Alignment.TRAILING, -1, -1, 32767));
        jPanel66Layout.setVerticalGroup(jPanel66Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel66Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel3, -2, 42, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.jLabel4, -2, 31, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.jPanel69, -2, 210, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jPanel70, -2, 235, -2).addContainerGap(-1, 32767)));
        this.jTabbedPane6.addTab("活动经验", this.jPanel66);
        this.jPanel52.setBackground(new Color(255, 255, 240));
        this.游戏广播.setBackground(new Color(255, 255, 240));
        this.游戏广播.setLayout(new AbsoluteLayout());
        this.广播信息.setBackground(new Color(153, 153, 153));
        this.广播信息.setFont(new Font("宋体", 0, 14));
        this.广播信息.setModel(new DefaultTableModel(new Object[][]{{null, null}, {null, null}, {null, null}, {null, null}}, new String[]{"序号", "5分钟一次随机广播内容"}) {
            boolean[] canEdit = {false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.jScrollPane9.setViewportView(this.广播信息);
        if (this.广播信息.getColumnModel().getColumnCount() > 0) {
            this.广播信息.getColumnModel().getColumn(0).setMinWidth(80);
            this.广播信息.getColumnModel().getColumn(0).setPreferredWidth(80);
            this.广播信息.getColumnModel().getColumn(0).setMaxWidth(80);
        }
        this.游戏广播.add(this.jScrollPane9, new AbsoluteConstraints(10, 0, 860, 540));
        this.刷新广告.setFont(new Font("幼圆", 0, 15));
        this.刷新广告.setText("刷新广播");
        this.刷新广告.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.刷新广告ActionPerformed(evt);
            }
        });
        this.删除广播.setFont(new Font("幼圆", 0, 15));
        this.删除广播.setText("删除广播");
        this.删除广播.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除广播ActionPerformed(evt);
            }
        });
        this.发布广告.setFont(new Font("幼圆", 0, 15));
        this.发布广告.setText("新增广播");
        this.发布广告.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.发布广告ActionPerformed(evt);
            }
        });
        this.修改广播.setFont(new Font("幼圆", 0, 15));
        this.修改广播.setText("修改广播");
        this.修改广播.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.修改广播ActionPerformed(evt);
            }
        });
        this.广播序号.setEditable(false);
        this.广播序号.setFont(new Font("幼圆", 1, 18));
        this.广播文本.setFont(new Font("幼圆", 1, 18));
        final GroupLayout jPanel52Layout = new GroupLayout(this.jPanel52);
        this.jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(jPanel52Layout.createParallelGroup(Alignment.LEADING).addComponent(this.游戏广播, -1, -1, 32767).addGroup(Alignment.TRAILING, jPanel52Layout.createSequentialGroup().addGroup(jPanel52Layout.createParallelGroup(Alignment.TRAILING).addGroup(jPanel52Layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.广播序号, -2, 68, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.广播文本, -2, 780, -2).addGap(8, 8, 8)).addGroup(jPanel52Layout.createSequentialGroup().addGap(108, 108, 108).addComponent(this.刷新广告, -2, 100, -2).addGap(63, 63, 63).addComponent(this.删除广播, -2, 100, -2).addGap(65, 65, 65).addComponent(this.发布广告, -2, 100, -2).addGap(72, 72, 72).addComponent(this.修改广播, -2, 100, -2).addGap(0, 0, 32767))).addGap(19, 19, 19)));
        jPanel52Layout.setVerticalGroup(jPanel52Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel52Layout.createSequentialGroup().addComponent(this.游戏广播, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel52Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.广播文本, -2, 30, -2).addComponent(this.广播序号, -2, 30, -2)).addGap(18, 18, 18).addGroup(jPanel52Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.刷新广告).addComponent(this.删除广播).addComponent(this.发布广告).addComponent(this.修改广播)).addGap(0, 0, 32767)));
        this.jTabbedPane6.addTab("游戏广播", this.jPanel52);
        this.jPanel12.setBackground(new Color(255, 255, 240));
        this.jPanel72.setBackground(new Color(255, 255, 240));//其他功能设置的背景色
        this.jPanel72.setBorder(BorderFactory.createTitledBorder(null, "其他功能设置[注意:本版块功能点击立即生效无需重启服务端]", 0, 0, new Font("宋体", 0, 15), new Color(255, 0, 0)));
        this.禁止登陆开关.setBackground(new Color(0, 204, 255));//发送悬浮公告的颜色
        this.禁止登陆开关.setIcon(new ImageIcon(this.getClass().getResource("/gui/1.png")));
        this.禁止登陆开关.setText("游戏登陆");
        this.禁止登陆开关.setToolTipText("<html>\n<strong><font color=\"#FF0000\">功能说明</font></strong><br> \n<strong>用于限制玩家登陆游戏<br> <br> <br> ");
        this.禁止登陆开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.禁止登陆开关ActionPerformed(evt);
            }
        });
        this.滚动公告开关.setBackground(new Color(0, 204, 255));
        this.滚动公告开关.setIcon(new ImageIcon(this.getClass().getResource("/gui/图片/pp/01032019.png")));
        this.滚动公告开关.setText("滚动公告");
        this.滚动公告开关.setToolTipText("<html>\n<strong><font color=\"#FF0000\">功能说明</font></strong><br> \n<strong>用于控制游戏顶部滚动公告<br> <br> <br> ");
        this.滚动公告开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.滚动公告开关ActionPerformed(evt);
            }
        });
        this.玩家聊天开关.setBackground(new Color(0, 204, 255));
        this.玩家聊天开关.setIcon(new ImageIcon(this.getClass().getResource("/gui/图片/pp/100.png")));
        this.玩家聊天开关.setText("玩家聊天");
        this.玩家聊天开关.setToolTipText("<html>\n<strong><font color=\"#FF0000\">功能说明</font></strong><br> \n<strong>用于控制游戏内玩家是否可以聊天说话<br> <br> <br> ");
        this.玩家聊天开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.玩家聊天开关ActionPerformed(evt);
            }
        });
        this.游戏升级快讯.setBackground(new Color(0, 153, 255));
        this.游戏升级快讯.setIcon(new ImageIcon(this.getClass().getResource("/gui/图片/pp/68.png")));
        this.游戏升级快讯.setText("升级快讯");
        this.游戏升级快讯.setToolTipText("<html>\n<strong><font color=\"#FF0000\">功能说明</font></strong><br> \n<strong>用于控制玩家升级了刷公告庆祝<br> <br> <br> ");
        this.游戏升级快讯.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.游戏升级快讯ActionPerformed(evt);
            }
        });
        this.丢出金币开关.setBackground(new Color(0, 204, 255));
        this.丢出金币开关.setIcon(new ImageIcon(this.getClass().getResource("/gui/图片/pp/66.png")));
        this.丢出金币开关.setText("丢出金币");
        this.丢出金币开关.setToolTipText("<html>\n<strong><font color=\"#FF0000\">功能说明</font></strong><br> \n<strong>用于控制玩家游戏内是否可以丢金币<br> <br> <br> ");
        this.丢出金币开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.丢出金币开关ActionPerformed(evt);
            }
        });
        this.丢出物品开关.setBackground(new Color(0, 204, 255));
        this.丢出物品开关.setIcon(new ImageIcon(this.getClass().getResource("/gui/图片/pp/01003824.png")));
        this.丢出物品开关.setText("丢出物品");
        this.丢出物品开关.setToolTipText("<html>\n<strong><font color=\"#FF0000\">功能说明</font></strong><br> \n<strong>用于控制游戏内玩家是否可以丢物品<br> <br> <br> ");
        this.丢出物品开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.丢出物品开关ActionPerformed(evt);
            }
        });
        this.游戏指令开关.setBackground(new Color(0, 204, 255));
        this.游戏指令开关.setText("GM游戏指令");
        this.游戏指令开关.setToolTipText("<html>\n<strong><font color=\"#FF0000\">功能说明</font></strong><br> \n<strong>用于控制GM号是否可以用GM命令<br> <br> <br> ");
        this.游戏指令开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.游戏指令开关ActionPerformed(evt);
            }
        });
        this.上线提醒开关.setBackground(new Color(0, 204, 255));
        this.上线提醒开关.setText("登录公告");
        this.上线提醒开关.setToolTipText("<html>\n<strong><font color=\"#FF0000\">功能说明</font></strong><br> \n<strong>玩家上线是否提示欢迎公告<br> <br>");
        this.上线提醒开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.上线提醒开关ActionPerformed(evt);
            }
        });
        this.回收地图开关.setBackground(new Color(0, 204, 255));
        this.回收地图开关.setText("回收地图");
        this.回收地图开关.setToolTipText("<html>\n<strong><font color=\"#FF0000\">功能说明</font></strong><br> \n<strong>用于游戏地图回收开关<br> <br> <br> ");
        this.回收地图开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.回收地图开关ActionPerformed(evt);
            }
        });
        this.管理隐身开关.setBackground(new Color(0, 204, 255));
        this.管理隐身开关.setText("管理隐身");
        this.管理隐身开关.setToolTipText("<html>\n<strong><font color=\"#FF0000\">功能说明</font></strong><br> \n<strong>用于管理员号上线默认是否开启隐身BUFF<br> <br> <br> ");
        this.管理隐身开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.管理隐身开关ActionPerformed(evt);
            }
        });
        this.管理加速开关.setBackground(new Color(0, 204, 255));
        this.管理加速开关.setText("管理加速");
        this.管理加速开关.setToolTipText("<html>\n<strong><font color=\"#FF0000\">功能说明</font></strong><br> \n<strong>用于管理员号上线默认是否开启轻功BUFF<br> <br> <br> ");
        this.管理加速开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.管理加速开关ActionPerformed(evt);
            }
        });
        this.游戏喇叭开关.setBackground(new Color(0, 153, 255));
        this.游戏喇叭开关.setText("游戏喇叭");
        this.游戏喇叭开关.setToolTipText("<html>\n<strong><font color=\"#FF0000\">功能说明</font></strong><br> \n<strong>用于控制是否让玩家使用游戏喇叭功能<br> <br> <br> ");
        this.游戏喇叭开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.游戏喇叭开关ActionPerformed(evt);
            }
        });
        this.玩家交易开关.setBackground(new Color(0, 204, 255));
        this.玩家交易开关.setText("玩家交易");
        this.玩家交易开关.setToolTipText("<html>\n<strong><font color=\"#FF0000\">功能说明</font></strong><br> \n<strong>用于限制游戏内玩家交易功能<br> <br>");
        this.玩家交易开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.玩家交易开关ActionPerformed(evt);
            }
        });
        this.雇佣商人开关.setBackground(new Color(0, 153, 255));
        this.雇佣商人开关.setText("雇佣商人");
        this.雇佣商人开关.setToolTipText("<html>\n<strong><font color=\"#FF0000\">功能说明</font></strong><br> \n<strong>是否允许玩家在自由摆摊<br> <br>");
        this.雇佣商人开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.雇佣商人开关ActionPerformed(evt);
            }
        });
        this.欢迎弹窗开关.setBackground(new Color(0, 204, 255));
        this.欢迎弹窗开关.setText("欢迎弹窗");
        this.欢迎弹窗开关.setToolTipText("<html>\n<strong><font color=\"#FF0000\">功能说明</font></strong><br> \n<strong>进入游戏是否弹出欢迎公告<br> <br>");
        this.欢迎弹窗开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.欢迎弹窗开关ActionPerformed(evt);
            }
        });
        this.登陆帮助开关.setBackground(new Color(0, 204, 255));
        this.登陆帮助开关.setText("登陆帮助");
        this.登陆帮助开关.setToolTipText("<html>\n<strong><font color=\"#FF0000\">功能说明</font></strong><br> \n<strong>进游戏是否提示登录帮助<br> <br>");
        this.登陆帮助开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.登陆帮助开关ActionPerformed(evt);
            }
        });
        this.越级打怪开关.setBackground(new Color(0, 204, 255));
        this.越级打怪开关.setText("越级打怪");
        this.越级打怪开关.setToolTipText("<html>\n<strong><font color=\"#FF0000\">功能说明</font></strong><br> \n<strong>超越本身等级打高级怪物不MISS<br> <br>");
        this.越级打怪开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.越级打怪开关ActionPerformed(evt);
            }
        });
        this.怪物状态开关.setBackground(new Color(0, 204, 255));
        this.怪物状态开关.setText("怪物状态");
        this.怪物状态开关.setToolTipText("<html>\n<strong><font color=\"#FF0000\">功能说明</font></strong><br> \n<strong>用于游戏内怪物状态释放技能是否提示<br> <br>");
        this.怪物状态开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.怪物状态开关ActionPerformed(evt);
            }
        });
        this.地图名称开关.setBackground(new Color(0, 204, 255));
        this.地图名称开关.setText("地图名称");
        this.地图名称开关.setToolTipText("<html>\n<strong><font color=\"#FF0000\">功能说明</font></strong><br> \n<strong>过地图是否提示地图名称<br> <br>");
        this.地图名称开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.地图名称开关ActionPerformed(evt);
            }
        });
        this.过图存档开关.setBackground(new Color(0, 204, 255));
        this.过图存档开关.setText("过图存档");
        this.过图存档开关.setToolTipText("<html>\n<strong><font color=\"#FF0000\">功能说明</font></strong><br> \n<strong>是否开启 玩家每过一张图保存当前玩家数据<br> <br>");
        this.过图存档开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.过图存档开关ActionPerformed(evt);
            }
        });
        this.指令通知开关.setBackground(new Color(0, 204, 255));
        this.指令通知开关.setText("指令通知");
        this.指令通知开关.setToolTipText("<html>\n<strong><font color=\"#FF0000\">功能说明</font></strong><br> \n<strong>角色上线是否提示命令代码<br> <br> <br> ");
        this.指令通知开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.指令通知开关ActionPerformed(evt);
            }
        });
        this.吸怪检测开关.setBackground(new Color(0, 204, 255));
        this.吸怪检测开关.setFont(new Font("幼圆", 0, 14));
        this.吸怪检测开关.setText("吸怪检测");
        this.吸怪检测开关.setToolTipText("");
        this.吸怪检测开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.吸怪检测开关ActionPerformed(evt);
            }
        });
        this.jTextField2.setBackground(new Color(0, 102, 153));
        this.jTextField2.setFont(new Font("宋体", 1, 14));
        this.jTextField2.setText("输入公告内容");
        this.jTextField2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jTextField2ActionPerformed(evt);
            }
        });
        this.jButton13.setBackground(new Color(51, 102, 255));
        this.jButton13.setFont(new Font("宋体", 1, 14));
        this.jButton13.setText("发送屏幕正中央漂浮公告");
        this.jButton13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton13ActionPerformed(evt);
            }
        });
        this.屠令广播开关.setBackground(new Color(0, 204, 255));
        this.屠令广播开关.setFont(new Font("幼圆", 0, 14));
        this.屠令广播开关.setIcon(new ImageIcon(this.getClass().getResource("/gui/图片/pp/101.png")));
        this.屠令广播开关.setText("屠令广播");
        this.屠令广播开关.setToolTipText("");
        this.屠令广播开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.屠令广播开关ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel72Layout = new GroupLayout(this.jPanel72);
        this.jPanel72.setLayout(jPanel72Layout);
        jPanel72Layout.setHorizontalGroup(jPanel72Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel72Layout.createSequentialGroup().addContainerGap().addGroup(jPanel72Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel72Layout.createSequentialGroup().addGroup(jPanel72Layout.createParallelGroup(Alignment.LEADING).addComponent(this.玩家交易开关, -2, 130, -2).addComponent(this.上线提醒开关, -2, 130, -2).addComponent(this.回收地图开关, -2, 130, -2)).addGap(18, 18, 18).addGroup(jPanel72Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.地图名称开关, -1, 130, 32767).addComponent(this.怪物状态开关, -1, -1, 32767).addComponent(this.指令通知开关, -1, -1, 32767))).addGroup(jPanel72Layout.createSequentialGroup().addGap(51, 51, 51).addComponent(this.禁止登陆开关, -2, 182, -2))).addGroup(jPanel72Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel72Layout.createSequentialGroup().addGap(18, 18, 18).addGroup(jPanel72Layout.createParallelGroup(Alignment.LEADING).addComponent(this.欢迎弹窗开关, -2, 130, -2).addComponent(this.过图存档开关, -2, 130, -2).addComponent(this.玩家聊天开关, -2, 164, -2)).addGroup(jPanel72Layout.createParallelGroup(Alignment.TRAILING).addGroup(jPanel72Layout.createSequentialGroup().addPreferredGap(ComponentPlacement.UNRELATED).addGroup(jPanel72Layout.createParallelGroup(Alignment.LEADING).addComponent(this.游戏指令开关, -2, 130, -2).addComponent(this.吸怪检测开关, -2, 130, -2)).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addGroup(jPanel72Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.游戏喇叭开关, -1, 130, 32767).addComponent(this.雇佣商人开关, -1, -1, 32767)).addGap(18, 18, 18).addGroup(jPanel72Layout.createParallelGroup(Alignment.LEADING).addComponent(this.管理隐身开关, -2, 130, -2).addComponent(this.登陆帮助开关, -2, 130, -2)).addGap(18, 18, 18).addGroup(jPanel72Layout.createParallelGroup(Alignment.LEADING).addComponent(this.越级打怪开关, -2, 139, -2).addGroup(jPanel72Layout.createSequentialGroup().addComponent(this.管理加速开关, -2, 139, -2).addGap(18, 18, 18).addComponent(this.滚动公告开关, -2, 165, -2))).addGap(101, 101, 101)).addGroup(jPanel72Layout.createSequentialGroup().addGap(18, 18, 18).addComponent(this.游戏升级快讯, -2, 173, -2).addGap(18, 18, 18).addComponent(this.屠令广播开关, -2, 173, -2).addGap(18, 18, 18).addComponent(this.丢出金币开关, -2, 189, -2).addGap(18, 18, 18).addComponent(this.丢出物品开关, -2, 188, -2).addContainerGap(-1, 32767)))).addGroup(jPanel72Layout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jTextField2, -2, 619, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton13).addContainerGap()))));
        jPanel72Layout.setVerticalGroup(jPanel72Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel72Layout.createSequentialGroup().addGap(18, 18, 18).addGroup(jPanel72Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.禁止登陆开关).addComponent(this.玩家聊天开关, -2, 30, -2).addComponent(this.游戏升级快讯, -2, 30, -2).addComponent(this.丢出金币开关).addComponent(this.丢出物品开关, -2, 30, -2).addComponent(this.屠令广播开关, -2, 30, -2)).addGroup(jPanel72Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel72Layout.createSequentialGroup().addGap(95, 95, 95).addGroup(jPanel72Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.上线提醒开关, -2, 30, -2).addComponent(this.地图名称开关, -2, 30, -2).addComponent(this.过图存档开关, -2, 30, -2).addComponent(this.吸怪检测开关, -2, 30, -2).addComponent(this.雇佣商人开关, -2, 30, -2).addComponent(this.登陆帮助开关, -2, 30, -2).addComponent(this.越级打怪开关, -2, 30, -2))).addGroup(jPanel72Layout.createSequentialGroup().addGap(30, 30, 30).addGroup(jPanel72Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.指令通知开关, -2, 30, -2).addComponent(this.玩家交易开关, -2, 30, -2).addComponent(this.欢迎弹窗开关, -2, 30, -2).addComponent(this.游戏指令开关, -2, 30, -2).addComponent(this.游戏喇叭开关, -2, 30, -2).addComponent(this.管理隐身开关, -2, 30, -2).addComponent(this.管理加速开关, -2, 30, -2).addComponent(this.滚动公告开关, -2, 30, -2)))).addGap(11, 11, 11).addGroup(jPanel72Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.回收地图开关, -2, 30, -2).addComponent(this.怪物状态开关, -2, 30, -2).addComponent(this.jTextField2, -2, -1, -2).addComponent(this.jButton13)).addGap(18, 26, 32767)));
        this.输出窗口.setBackground(new Color(255, 255, 240));
        this.输出窗口.setColumns(20);
        this.输出窗口.setFont(new Font("宋体", 1, 15));
        this.输出窗口.setRows(5);
        this.输出窗口.setFocusTraversalPolicyProvider(true);
        this.输出窗口.setInheritsPopupMenu(true);
        this.输出窗口.setSelectedTextColor(new Color(51, 0, 51));
        this.jScrollPane2.setViewportView(this.输出窗口);
        this.jButton16.setBackground(new Color(0, 0, 204));
        this.jButton16.setFont(new Font("宋体", 1, 12));
        this.jButton16.setIcon(new ImageIcon(this.getClass().getResource("/gui/图片/pp/关机.png")));
        this.jButton16.setText("关闭服务端");
        this.jButton16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton16ActionPerformed(evt);
            }
        });
        this.jTextField22.setForeground(new Color(255, 51, 51));
        this.jTextField22.setText("关闭时间/分钟");
        this.jTextField22.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jTextField22ActionPerformed(evt);
            }
        });
        this.jLabel29.setText(" 分钟");
        CongMS.startserverbutton.setBackground(new Color(51, 51, 255));
        CongMS.startserverbutton.setFont(new Font("宋体", 1, 12));
        CongMS.startserverbutton.setIcon(new ImageIcon(this.getClass().getResource("/gui/图片/pp/开机.png")));
        CongMS.startserverbutton.setText("启动CongMS");
        CongMS.startserverbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.startserverbuttonActionPerformed(evt);
            }
        });
        this.jLabel28.setText("游戏已开放：");
        this.jLabel25.setForeground(new Color(255, 51, 0));
        this.jLabel25.setText("读取中");
        final GroupLayout jPanel12Layout = new GroupLayout(this.jPanel12);
        this.jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(jPanel12Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel12Layout.createSequentialGroup().addGroup(jPanel12Layout.createParallelGroup(Alignment.TRAILING).addComponent(this.jPanel72, -2, 1297, -2).addGroup(jPanel12Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel12Layout.createSequentialGroup().addComponent(CongMS.startserverbutton, -2, 148, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jLabel28, -2, 107, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jLabel25, -2, 106, -2).addGap(278, 278, 278).addComponent(this.jLabel29, -2, 42, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jTextField22, -2, 177, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton16, -2, 202, -2)).addComponent(this.jScrollPane2, -2, 1287, -2))).addGap(0, 0, 32767)));
        jPanel12Layout.setVerticalGroup(jPanel12Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel12Layout.createSequentialGroup().addGap(11, 11, 11).addComponent(this.jPanel72, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jScrollPane2, -2, 274, -2).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(jPanel12Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jButton16, -2, 43, -2).addComponent(this.jTextField22, -2, 43, -2).addComponent(this.jLabel29, -2, 45, -2).addComponent(CongMS.startserverbutton, -2, 45, -2).addComponent(this.jLabel28, -2, 39, -2).addComponent(this.jLabel25, -2, 36, -2)).addContainerGap(-1, 32767)));
        this.jTabbedPane6.addTab("综合开关", this.jPanel12);
        final GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
        this.jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addGap(0, 0, 32767).addGroup(jPanel3Layout.createParallelGroup(Alignment.TRAILING).addComponent(this.jLabel56, Alignment.LEADING, -2, 64, -2).addComponent(this.jLabel57, Alignment.LEADING, -2, 64, -2).addComponent(this.jLabel58, Alignment.LEADING, -2, 64, -2).addComponent(this.jLabel59, Alignment.LEADING, -2, 64, -2)).addGap(917, 917, 917)).addGroup(jPanel3Layout.createSequentialGroup().addComponent(this.jTabbedPane6, -2, 1320, -2).addContainerGap(-1, 32767)));
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addComponent(this.jTabbedPane6, -2, -1, -2).addGap(139, 139, 139).addComponent(this.jLabel56, -2, 60, -2).addGap(0, 0, 32767).addComponent(this.jLabel57, -2, 60, -2).addGap(0, 0, 32767).addComponent(this.jLabel58, -2, 60, -2).addGap(0, 0, 32767).addComponent(this.jLabel59, -2, 60, -2).addGap(0, 0, 32767)));
        this.jTabbedPane2.addTab("首页功能", this.jPanel3);
        this.jPanel5.setBackground(new Color(255, 255, 255));
        this.jTextArea1.setEditable(false);
        this.jTextArea1.setBackground(new Color(255, 255, 255));
        this.jTextArea1.setColumns(20);
        this.jTextArea1.setFont(new Font("新宋体", 0, 12));
        this.jTextArea1.setRows(5);
        this.jTextArea1.setText("感谢使用CongMS服务端版本079\n" +
                "------------------------------------------\n更新内容如下\n------------------------------------------\n" +
                "[Windyboy] 2023-01-05\n" +
                "1.移除授權系統\n" +
                "2.整理連線資料庫IP設定\n" +
                "3.移除無用設定\n" +
                "[Windyboy] 2022-12-18\n" +
                "1.整理讀取IP設定\n" +
                "[Windyboy] 2022-08-01\n" +
                "1.修正GUI資料庫連線未釋放\n" +
                "[Windyboy] 2022-07-28\n" +
                "1.更通用的SQL資料\n" +
                "2.移除過期實現\n" +
                "3.整理支持庫\n" +
                "4.優化角色方法-Gainpersonal\n" +
                "5. 12:07:07\n" +
                "2021-8-20 更新说明\n1.修复海盗变身碰怪解除变身\n2.添加新BOSS\n3.修复商场封包问题\n\n\n2021-8-13 更新说明\n1.添加自由泡点取消世界泡点\n2.降低BOSS攻击\n\n\n2021-8-11 更新说明\n1.修复远程职业打不死怪\n2.优化源码\n3.修复打不死扎昆\n\n\n2021-8-1 更新说明\n1.修复火毒38问题\n2.修复部分已测试BUG\n3.添加程序窗口\n4.添加重载系统\n5.优化控制台\n\n");
        this.jScrollPane5.setViewportView(this.jTextArea1);
        final GroupLayout jPanel5Layout = new GroupLayout(this.jPanel5);
        this.jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jScrollPane5, -1, 1318, 32767));
        jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jScrollPane5, -1, 682, 32767));
        this.jTabbedPane2.addTab("更新内容", this.jPanel5);
        this.jPanel8.setBackground(new Color(255, 255, 255));
        this.jPanel6.setBackground(new Color(255, 255, 255));
        this.jLabel1.setText("工具系列：");
        this.jButton31.setText("代码查询器");
        this.jButton31.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton31ActionPerformed(evt);
            }
        });
        this.jButton29.setForeground(new Color(255, 51, 51));
        this.jButton29.setText("一键清空数据库");
        this.jButton29.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton29ActionPerformed(evt);
            }
        });
        this.jButton44.setText("游戏抽奖管理工具");
        this.jButton44.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton44ActionPerformed(evt);
            }
        });
        this.jButton39.setText("自添加NPC删除工具");
        this.jButton39.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton39ActionPerformed(evt);
            }
        });
        this.jButton7.setText("保存数据");
        this.jButton7.setMaximumSize(new Dimension(93, 23));
        this.jButton7.setMinimumSize(new Dimension(93, 23));
        this.jButton7.setPreferredSize(new Dimension(93, 23));
        this.jButton7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton7ActionPerformed(evt);
            }
        });
        this.jButton8.setText("保存雇佣");
        this.jButton8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton8ActionPerformed(evt);
            }
        });
        this.jButton46.setText("活动控制台");
        this.jButton46.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton46ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel6Layout = new GroupLayout(this.jPanel6);
        this.jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel6Layout.createSequentialGroup().addContainerGap().addGroup(jPanel6Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jLabel1).addGroup(jPanel6Layout.createSequentialGroup().addGroup(jPanel6Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel6Layout.createSequentialGroup().addGap(45, 45, 45).addComponent(this.jButton31, -2, 133, -2)).addComponent(this.jButton7, Alignment.TRAILING, -2, 134, -2)).addGap(63, 63, 63).addGroup(jPanel6Layout.createParallelGroup(Alignment.LEADING, false).addGroup(jPanel6Layout.createSequentialGroup().addComponent(this.jButton29, -2, 135, -2).addGap(61, 61, 61).addComponent(this.jButton44)).addGroup(jPanel6Layout.createSequentialGroup().addComponent(this.jButton8, -2, 135, -2).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(this.jButton46, -2, 153, -2))).addGap(60, 60, 60).addComponent(this.jButton39))).addContainerGap(-1, 32767)));
        jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel6Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addGap(18, 18, 18).addGroup(jPanel6Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jButton44, -2, 49, -2).addComponent(this.jButton39, -2, 46, -2).addComponent(this.jButton31, -2, 46, -2).addComponent(this.jButton29, -2, 49, -2)).addPreferredGap(ComponentPlacement.RELATED, 50, 32767).addGroup(jPanel6Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jButton7, -2, 46, -2).addComponent(this.jButton8, -2, 46, -2).addComponent(this.jButton46, -2, 46, -2)).addGap(26, 26, 26)));
        this.jPanel13.setBackground(new Color(255, 255, 240));
        this.jPanel13.setBorder(BorderFactory.createTitledBorder("重载系列"));
        this.重载副本按钮.setText("重载副本");
        this.重载副本按钮.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.重载副本按钮ActionPerformed(evt);
            }
        });
        this.重载爆率按钮.setText("重载爆率");
        this.重载爆率按钮.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.重载爆率按钮ActionPerformed(evt);
            }
        });
        this.重载反应堆按钮.setText("重载反应堆");
        this.重载反应堆按钮.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.重载反应堆按钮ActionPerformed(evt);
            }
        });
        this.重载传送门按钮.setText("重载传送门");
        this.重载传送门按钮.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.重载传送门按钮ActionPerformed(evt);
            }
        });
        this.重载商城按钮.setText("重载商城");
        this.重载商城按钮.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.重载商城按钮ActionPerformed(evt);
            }
        });
        this.重载商店按钮.setText("重载商店");
        this.重载商店按钮.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.重载商店按钮ActionPerformed(evt);
            }
        });
        this.重载包头按钮.setText("重载包头");
        this.重载包头按钮.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.重载包头按钮ActionPerformed(evt);
            }
        });
        this.重载任务.setText("重载任务");
        this.重载任务.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.重载任务ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel13Layout = new GroupLayout(this.jPanel13);
        this.jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(jPanel13Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel13Layout.createSequentialGroup().addGap(52, 52, 52).addGroup(jPanel13Layout.createParallelGroup(Alignment.LEADING).addComponent(this.重载任务, Alignment.TRAILING, -2, 103, -2).addComponent(this.重载副本按钮, Alignment.TRAILING, -2, 103, -2)).addPreferredGap(ComponentPlacement.RELATED, 528, 32767).addGroup(jPanel13Layout.createParallelGroup(Alignment.LEADING).addComponent(this.重载爆率按钮, -2, 107, -2).addComponent(this.重载商店按钮, -2, 107, -2)).addGap(108, 108, 108).addGroup(jPanel13Layout.createParallelGroup(Alignment.TRAILING).addComponent(this.重载商城按钮, -2, 117, -2).addComponent(this.重载包头按钮, -2, 117, -2)).addGap(116, 116, 116).addGroup(jPanel13Layout.createParallelGroup(Alignment.TRAILING).addComponent(this.重载反应堆按钮, -2, 110, -2).addComponent(this.重载传送门按钮, -2, 110, -2)).addGap(37, 37, 37)));
        jPanel13Layout.setVerticalGroup(jPanel13Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel13Layout.createSequentialGroup().addGap(26, 26, 26).addGroup(jPanel13Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.重载副本按钮, -2, 44, -2).addComponent(this.重载爆率按钮, -2, 44, -2).addComponent(this.重载包头按钮, -2, 44, -2).addComponent(this.重载传送门按钮, -2, 44, -2)).addPreferredGap(ComponentPlacement.RELATED, 37, 32767).addGroup(jPanel13Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.重载任务, -1, -1, 32767).addComponent(this.重载反应堆按钮, -2, 44, -2).addComponent(this.重载商城按钮, -2, 44, -2).addComponent(this.重载商店按钮, -1, -1, 32767)).addContainerGap()));
        final GroupLayout jPanel8Layout = new GroupLayout(this.jPanel8);
        this.jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(jPanel8Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel8Layout.createSequentialGroup().addContainerGap().addGroup(jPanel8Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jPanel13, -1, -1, 32767).addComponent(this.jPanel6, -1, -1, 32767)).addContainerGap()));
        jPanel8Layout.setVerticalGroup(jPanel8Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel8Layout.createSequentialGroup().addGap(64, 64, 64).addComponent(this.jPanel13, -2, -1, -2).addGap(59, 59, 59).addComponent(this.jPanel6, -2, -1, -2).addContainerGap(148, 32767)));
        this.jTabbedPane2.addTab("常用工具", this.jPanel8);
        this.jTabbedPane7.setFont(new Font("幼圆", 0, 12));
        this.jPanel4.setBackground(new Color(255, 255, 240));
        this.jPanel59.setBackground(new Color(255, 255, 240));
        this.jPanel59.setBorder(BorderFactory.createTitledBorder(null, "全服发送福利", 2, 2, new Font("幼圆", 0, 12)));
        this.jPanel59.setLayout(new AbsoluteLayout());
        this.z2.setFont(new Font("幼圆", 0, 15));
        this.z2.setText("发送抵用");
        this.z2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.z2ActionPerformed(evt);
            }
        });
        this.jPanel59.add(this.z2, new AbsoluteConstraints(70, 120, 100, 30));
        this.z3.setFont(new Font("幼圆", 0, 15));
        this.z3.setText("发送金币");
        this.z3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.z3ActionPerformed(evt);
            }
        });
        this.jPanel59.add(this.z3, new AbsoluteConstraints(70, 170, 100, 30));
        this.z1.setFont(new Font("幼圆", 0, 15));
        this.z1.setText("发送点券");
        this.z1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.z1ActionPerformed(evt);
            }
        });
        this.jPanel59.add(this.z1, new AbsoluteConstraints(70, 70, 100, 30));
        this.z4.setFont(new Font("幼圆", 0, 15));
        this.z4.setText("发送经验");
        this.z4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.z4ActionPerformed(evt);
            }
        });
        this.jPanel59.add(this.z4, new AbsoluteConstraints(170, 70, 100, 30));
        this.z5.setFont(new Font("幼圆", 0, 15));
        this.z5.setText("发送人气");
        this.z5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.z5ActionPerformed(evt);
            }
        });
        this.jPanel59.add(this.z5, new AbsoluteConstraints(170, 120, 100, 30));
        this.z6.setFont(new Font("幼圆", 0, 15));
        this.z6.setText("发送豆豆");
        this.z6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.z6ActionPerformed(evt);
            }
        });
        this.jPanel59.add(this.z6, new AbsoluteConstraints(170, 170, 100, 30));
        this.a1.setFont(new Font("宋体", 0, 18));
        this.a1.setText("2000");
        this.a1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.a1ActionPerformed(evt);
            }
        });
        this.jPanel59.add(this.a1, new AbsoluteConstraints(140, 30, 130, 30));
        this.jLabel235.setFont(new Font("幼圆", 1, 18));
        this.jLabel235.setText("数量");
        this.jPanel59.add(this.jLabel235, new AbsoluteConstraints(70, 30, 60, 30));
        this.jPanel57.setBackground(new Color(255, 255, 255));
        this.jPanel57.setBorder(BorderFactory.createTitledBorder(null, "全服发送福利", 2, 2, new Font("幼圆", 0, 12)));
        this.jPanel57.setLayout(new AbsoluteLayout());
        this.全服发送物品数量.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.全服发送物品数量ActionPerformed(evt);
            }
        });
        this.jPanel57.add(this.全服发送物品数量, new AbsoluteConstraints(130, 40, 100, 30));
        this.全服发送物品代码.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.全服发送物品代码ActionPerformed(evt);
            }
        });
        this.jPanel57.add(this.全服发送物品代码, new AbsoluteConstraints(20, 40, 110, 30));
        this.给予物品1.setFont(new Font("幼圆", 0, 15));
        this.给予物品1.setText("给予物品");
        this.给予物品1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.给予物品1ActionPerformed(evt);
            }
        });
        this.jPanel57.add(this.给予物品1, new AbsoluteConstraints(230, 40, 100, 30));
        this.jLabel217.setFont(new Font("幼圆", 0, 14));
        this.jLabel217.setText("物品数量；");
        this.jPanel57.add(this.jLabel217, new AbsoluteConstraints(140, 20, -1, -1));
        this.jLabel234.setFont(new Font("幼圆", 0, 14));
        this.jLabel234.setText("物品代码；");
        this.jPanel57.add(this.jLabel234, new AbsoluteConstraints(20, 20, -1, -1));
        this.jPanel58.setBackground(new Color(255, 255, 240));
        this.jPanel58.setBorder(BorderFactory.createTitledBorder(null, "全服发送福利", 2, 2, new Font("幼圆", 0, 12)));
        this.jPanel58.setLayout(new AbsoluteLayout());
        this.全服发送装备装备加卷.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.全服发送装备装备加卷ActionPerformed(evt);
            }
        });
        this.jPanel58.add(this.全服发送装备装备加卷, new AbsoluteConstraints(210, 100, 100, 30));
        this.全服发送装备装备制作人.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.全服发送装备装备制作人ActionPerformed(evt);
            }
        });
        this.jPanel58.add(this.全服发送装备装备制作人, new AbsoluteConstraints(310, 40, 100, 30));
        this.全服发送装备装备力量.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.全服发送装备装备力量ActionPerformed(evt);
            }
        });
        this.jPanel58.add(this.全服发送装备装备力量, new AbsoluteConstraints(10, 170, 100, 30));
        this.全服发送装备装备MP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.全服发送装备装备MPActionPerformed(evt);
            }
        });
        this.jPanel58.add(this.全服发送装备装备MP, new AbsoluteConstraints(10, 100, 100, 30));
        this.全服发送装备装备智力.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.全服发送装备装备智力ActionPerformed(evt);
            }
        });
        this.jPanel58.add(this.全服发送装备装备智力, new AbsoluteConstraints(210, 170, 100, 30));
        this.全服发送装备装备运气.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.全服发送装备装备运气ActionPerformed(evt);
            }
        });
        this.jPanel58.add(this.全服发送装备装备运气, new AbsoluteConstraints(310, 100, 100, 30));
        this.全服发送装备装备HP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.全服发送装备装备HPActionPerformed(evt);
            }
        });
        this.jPanel58.add(this.全服发送装备装备HP, new AbsoluteConstraints(110, 100, 100, 30));
        this.全服发送装备装备攻击力.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.全服发送装备装备攻击力ActionPerformed(evt);
            }
        });
        this.jPanel58.add(this.全服发送装备装备攻击力, new AbsoluteConstraints(210, 40, 100, 30));
        this.全服发送装备装备给予时间.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.全服发送装备装备给予时间ActionPerformed(evt);
            }
        });
        this.jPanel58.add(this.全服发送装备装备给予时间, new AbsoluteConstraints(210, 230, 100, 30));
        this.全服发送装备装备可否交易.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.全服发送装备装备可否交易ActionPerformed(evt);
            }
        });
        this.jPanel58.add(this.全服发送装备装备可否交易, new AbsoluteConstraints(310, 170, 100, 30));
        this.全服发送装备装备敏捷.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.全服发送装备装备敏捷ActionPerformed(evt);
            }
        });
        this.jPanel58.add(this.全服发送装备装备敏捷, new AbsoluteConstraints(110, 170, 100, 30));
        this.全服发送装备物品ID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.全服发送装备物品IDActionPerformed(evt);
            }
        });
        this.jPanel58.add(this.全服发送装备物品ID, new AbsoluteConstraints(10, 40, 100, 30));
        this.全服发送装备装备魔法力.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.全服发送装备装备魔法力ActionPerformed(evt);
            }
        });
        this.jPanel58.add(this.全服发送装备装备魔法力, new AbsoluteConstraints(110, 40, 100, 30));
        this.全服发送装备装备魔法防御.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.全服发送装备装备魔法防御ActionPerformed(evt);
            }
        });
        this.jPanel58.add(this.全服发送装备装备魔法防御, new AbsoluteConstraints(10, 230, 100, 30));
        this.全服发送装备装备物理防御.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.全服发送装备装备物理防御ActionPerformed(evt);
            }
        });
        this.jPanel58.add(this.全服发送装备装备物理防御, new AbsoluteConstraints(110, 230, 100, 30));
        this.给予装备1.setFont(new Font("幼圆", 0, 15));
        this.给予装备1.setText("个人发送");
        this.给予装备1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.给予装备1ActionPerformed(evt);
            }
        });
        this.jPanel58.add(this.给予装备1, new AbsoluteConstraints(410, 170, 100, 30));
        this.jLabel219.setFont(new Font("幼圆", 0, 14));
        this.jLabel219.setText("能否交易；填0");
        this.jPanel58.add(this.jLabel219, new AbsoluteConstraints(310, 150, -1, -1));
        this.jLabel220.setFont(new Font("幼圆", 0, 14));
        this.jLabel220.setText("HP加成；");
        this.jPanel58.add(this.jLabel220, new AbsoluteConstraints(110, 80, -1, -1));
        this.jLabel221.setFont(new Font("幼圆", 0, 14));
        this.jLabel221.setText("魔法攻击力；");
        this.jPanel58.add(this.jLabel221, new AbsoluteConstraints(110, 20, -1, -1));
        this.jLabel222.setFont(new Font("幼圆", 0, 14));
        this.jLabel222.setText("装备代码；");
        this.jPanel58.add(this.jLabel222, new AbsoluteConstraints(10, 20, -1, -1));
        this.jLabel223.setFont(new Font("幼圆", 0, 14));
        this.jLabel223.setText("MP加成；");
        this.jPanel58.add(this.jLabel223, new AbsoluteConstraints(10, 80, -1, -1));
        this.jLabel224.setFont(new Font("幼圆", 0, 14));
        this.jLabel224.setText("物理攻击力；");
        this.jPanel58.add(this.jLabel224, new AbsoluteConstraints(210, 20, -1, -1));
        this.jLabel225.setFont(new Font("幼圆", 0, 14));
        this.jLabel225.setText("可砸卷次数；");
        this.jPanel58.add(this.jLabel225, new AbsoluteConstraints(210, 80, -1, -1));
        this.jLabel226.setFont(new Font("幼圆", 0, 14));
        this.jLabel226.setText("装备署名；");
        this.jPanel58.add(this.jLabel226, new AbsoluteConstraints(310, 20, -1, -1));
        this.jLabel227.setFont(new Font("幼圆", 0, 14));
        this.jLabel227.setText("装备力量；");
        this.jPanel58.add(this.jLabel227, new AbsoluteConstraints(10, 150, -1, -1));
        this.jLabel228.setFont(new Font("幼圆", 0, 14));
        this.jLabel228.setText("装备敏捷；");
        this.jPanel58.add(this.jLabel228, new AbsoluteConstraints(110, 150, -1, -1));
        this.jLabel229.setFont(new Font("幼圆", 0, 14));
        this.jLabel229.setText("装备智力；");
        this.jPanel58.add(this.jLabel229, new AbsoluteConstraints(210, 150, -1, -1));
        this.jLabel230.setFont(new Font("幼圆", 0, 14));
        this.jLabel230.setText("装备运气；");
        this.jPanel58.add(this.jLabel230, new AbsoluteConstraints(310, 80, -1, -1));
        this.jLabel231.setFont(new Font("幼圆", 0, 14));
        this.jLabel231.setText("魔法防御；");
        this.jPanel58.add(this.jLabel231, new AbsoluteConstraints(10, 210, -1, -1));
        this.jLabel232.setFont(new Font("幼圆", 0, 14));
        this.jLabel232.setText("物理防御；");
        this.jPanel58.add(this.jLabel232, new AbsoluteConstraints(110, 210, -1, -1));
        this.jLabel233.setFont(new Font("幼圆", 0, 14));
        this.jLabel233.setText("限时时间；");
        this.jPanel58.add(this.jLabel233, new AbsoluteConstraints(210, 210, -1, -1));
        this.发送装备玩家姓名.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.发送装备玩家姓名ActionPerformed(evt);
            }
        });
        this.jPanel58.add(this.发送装备玩家姓名, new AbsoluteConstraints(410, 40, 100, 30));
        this.给予装备2.setFont(new Font("幼圆", 0, 15));
        this.给予装备2.setText("全服发送");
        this.给予装备2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.给予装备2ActionPerformed(evt);
            }
        });
        this.jPanel58.add(this.给予装备2, new AbsoluteConstraints(410, 100, 100, 30));
        this.jLabel246.setFont(new Font("幼圆", 0, 14));
        this.jLabel246.setText("玩家名字；");
        this.jPanel58.add(this.jLabel246, new AbsoluteConstraints(410, 20, -1, -1));
        this.jLabel244.setFont(new Font("幼圆", 0, 14));
        this.jLabel244.setText("个人发送需要填写名字");
        this.jPanel58.add(this.jLabel244, new AbsoluteConstraints(370, 230, -1, -1));
        this.jPanel60.setBackground(new Color(255, 255, 255));
        this.jPanel60.setBorder(BorderFactory.createTitledBorder(null, "个人发送物品", 2, 2, new Font("幼圆", 0, 12)));
        this.jPanel60.setLayout(new AbsoluteLayout());
        this.个人发送物品数量.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.个人发送物品数量ActionPerformed(evt);
            }
        });
        this.jPanel60.add(this.个人发送物品数量, new AbsoluteConstraints(270, 40, 80, 30));
        this.个人发送物品玩家名字.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.个人发送物品玩家名字ActionPerformed(evt);
            }
        });
        this.jPanel60.add(this.个人发送物品玩家名字, new AbsoluteConstraints(10, 40, 130, 30));
        this.个人发送物品代码.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.个人发送物品代码ActionPerformed(evt);
            }
        });
        this.jPanel60.add(this.个人发送物品代码, new AbsoluteConstraints(140, 40, 130, 30));
        this.给予物品.setBackground(new Color(255, 255, 255));
        this.给予物品.setFont(new Font("幼圆", 0, 15));
        this.给予物品.setText("给予物品");
        this.给予物品.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.给予物品ActionPerformed(evt);
            }
        });
        this.jPanel60.add(this.给予物品, new AbsoluteConstraints(350, 40, 100, 30));
        this.jLabel240.setFont(new Font("幼圆", 0, 14));
        this.jLabel240.setText("物品数量；");
        this.jPanel60.add(this.jLabel240, new AbsoluteConstraints(270, 20, -1, -1));
        this.jLabel241.setFont(new Font("幼圆", 0, 14));
        this.jLabel241.setText("玩家名字；");
        this.jPanel60.add(this.jLabel241, new AbsoluteConstraints(10, 20, -1, -1));
        this.jLabel242.setFont(new Font("幼圆", 0, 14));
        this.jLabel242.setText("物品代码；");
        this.jPanel60.add(this.jLabel242, new AbsoluteConstraints(140, 20, -1, -1));
        final GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
        this.jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addGroup(jPanel4Layout.createParallelGroup(Alignment.TRAILING).addComponent(this.jPanel60, -2, 510, -2).addComponent(this.jPanel57, -2, 508, -2)).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.jPanel59, -1, 775, 32767)).addGroup(jPanel4Layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel58, -1, -1, 32767))).addContainerGap()));
        jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addContainerGap().addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addComponent(this.jPanel57, -2, 77, -2).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(this.jPanel60, -2, 81, -2)).addComponent(this.jPanel59, -1, 214, 32767)).addGap(28, 28, 28).addComponent(this.jPanel58, -2, 311, -2).addGap(71, 71, 71)));
        this.jTabbedPane7.addTab("福利道具发送", this.jPanel4);
        this.jPanel9.setBackground(new Color(255, 255, 240));
        this.jPanel9.setBorder(BorderFactory.createTitledBorder(null, "玩家在线泡点", 2, 2, new Font("幼圆", 0, 24)));
        this.jPanel9.setLayout(new AbsoluteLayout());
        this.在线泡点设置.setBackground(new Color(153, 153, 153));
        this.在线泡点设置.setFont(new Font("幼圆", 0, 20));
        this.在线泡点设置.setModel(new DefaultTableModel(new Object[0][], new String[]{"序号", "类型", "数值"}) {
            boolean[] canEdit = {false, false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.在线泡点设置.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane134.setViewportView(this.在线泡点设置);
        this.jPanel9.add(this.jScrollPane134, new AbsoluteConstraints(10, 40, 470, 260));
        this.泡点序号.setEditable(false);
        this.jPanel9.add(this.泡点序号, new AbsoluteConstraints(50, 380, 70, 30));
        this.泡点类型.setEditable(false);
        this.jPanel9.add(this.泡点类型, new AbsoluteConstraints(190, 380, 110, 30));
        this.jPanel9.add(this.泡点值, new AbsoluteConstraints(360, 380, 120, 30));
        this.泡点值修改.setFont(new Font("幼圆", 0, 15));
        this.泡点值修改.setText("修改");
        this.泡点值修改.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.泡点值修改ActionPerformed(evt);
            }
        });
        this.jPanel9.add(this.泡点值修改, new AbsoluteConstraints(550, 380, 80, 30));
        this.jLabel322.setFont(new Font("幼圆", 0, 15));
        this.jLabel322.setText("类型数值；");
        this.jPanel9.add(this.jLabel322, new AbsoluteConstraints(360, 360, -1, -1));
        this.jLabel326.setFont(new Font("幼圆", 0, 18));
        this.jLabel326.setForeground(new Color(255, 0, 153));
        this.jLabel326.setText("提示：修改泡点时间需30分钟生效,其它设置即时生效。");
        this.jPanel9.add(this.jLabel326, new AbsoluteConstraints(10, 310, -1, -1));
        this.jLabel327.setFont(new Font("幼圆", 0, 15));
        this.jLabel327.setText("泡点奖励类型；");
        this.jPanel9.add(this.jLabel327, new AbsoluteConstraints(190, 360, -1, -1));
        this.jPanel75.setBackground(new Color(255, 255, 255));
        this.jPanel75.setBorder(BorderFactory.createTitledBorder(null, "在线泡点设置", 2, 2, new Font("幼圆", 0, 24)));
        this.泡点金币开关.setFont(new Font("幼圆", 0, 15));
        this.泡点金币开关.setText("泡点金币");
        this.泡点金币开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.泡点金币开关ActionPerformed(evt);
            }
        });
        this.泡点经验开关.setFont(new Font("幼圆", 0, 15));
        this.泡点经验开关.setText("泡点经验");
        this.泡点经验开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.泡点经验开关ActionPerformed(evt);
            }
        });
        this.泡点点券开关.setFont(new Font("幼圆", 0, 15));
        this.泡点点券开关.setText("泡点点券");
        this.泡点点券开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.泡点点券开关ActionPerformed(evt);
            }
        });
        this.泡点抵用开关.setFont(new Font("幼圆", 0, 15));
        this.泡点抵用开关.setText("泡点抵用");
        this.泡点抵用开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.泡点抵用开关ActionPerformed(evt);
            }
        });
        this.泡点豆豆开关.setFont(new Font("幼圆", 0, 15));
        this.泡点豆豆开关.setText("泡点豆豆");
        this.泡点豆豆开关.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.泡点豆豆开关ActionPerformed(evt);
            }
        });
        this.jLabel65.setForeground(new Color(255, 0, 153));
        this.jLabel65.setText("提示：在线泡点开关，无需重启服务端，即时生效");
        final GroupLayout jPanel75Layout = new GroupLayout(this.jPanel75);
        this.jPanel75.setLayout(jPanel75Layout);
        jPanel75Layout.setHorizontalGroup(jPanel75Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel75Layout.createSequentialGroup().addGap(24, 24, 24).addGroup(jPanel75Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.泡点豆豆开关, -2, 140, -2).addGroup(jPanel75Layout.createSequentialGroup().addComponent(this.泡点金币开关, -2, 140, -2).addGap(20, 20, 20).addComponent(this.泡点经验开关, -2, 140, -2)).addGroup(jPanel75Layout.createSequentialGroup().addGap(2, 2, 2).addComponent(this.泡点点券开关, -2, 140, -2).addGap(18, 18, 18).addComponent(this.泡点抵用开关, -2, 140, -2)).addComponent(this.jLabel65, -1, -1, 32767)).addGap(0, 0, 0)));
        jPanel75Layout.setVerticalGroup(jPanel75Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel75Layout.createSequentialGroup().addGap(1, 1, 1).addGroup(jPanel75Layout.createParallelGroup(Alignment.LEADING).addComponent(this.泡点金币开关, -2, 40, -2).addComponent(this.泡点经验开关, -2, 40, -2)).addPreferredGap(ComponentPlacement.RELATED, 28, 32767).addGroup(jPanel75Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.泡点点券开关, -2, 40, -2).addComponent(this.泡点抵用开关, -2, 40, -2)).addGap(27, 27, 27).addComponent(this.泡点豆豆开关, -2, 40, -2).addGap(18, 18, 18).addComponent(this.jLabel65, -2, 23, -2).addContainerGap()));
        this.jPanel9.add(this.jPanel75, new AbsoluteConstraints(500, 30, 360, 270));
        this.jLabel328.setFont(new Font("幼圆", 0, 15));
        this.jLabel328.setText("序号；");
        this.jPanel9.add(this.jLabel328, new AbsoluteConstraints(50, 360, -1, -1));
        this.福利提示语言2.setFont(new Font("幼圆", 0, 18));
        this.福利提示语言2.setText("[信息]：");
        this.jPanel9.add(this.福利提示语言2, new AbsoluteConstraints(10, 570, 800, 25));
        this.jLabel60.setText("金币==数值乘等级 列如：金币数值10，实际泡点所得金币等于10乘当前等级");
        this.jPanel9.add(this.jLabel60, new AbsoluteConstraints(150, 430, 510, -1));
        this.jLabel61.setText("经验==数值乘等级 列如：经验数值10，实际泡点所得经验等于10乘当前等级");
        this.jPanel9.add(this.jLabel61, new AbsoluteConstraints(150, 460, 500, -1));
        this.jLabel62.setText("其中：点卷/抵用卷/豆豆 这三个数值都是固定数值，设置10泡点所得就是10");
        this.jPanel9.add(this.jLabel62, new AbsoluteConstraints(150, 490, 520, -1));
        this.jTabbedPane7.addTab("福利在线泡点", this.jPanel9);
        final GroupLayout jPanel10Layout = new GroupLayout(this.jPanel10);
        this.jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(jPanel10Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jTabbedPane7));
        jPanel10Layout.setVerticalGroup(jPanel10Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel10Layout.createSequentialGroup().addComponent(this.jTabbedPane7).addContainerGap()));
        this.jTabbedPane2.addTab("全服福利", this.jPanel10);
        this.jTabbedPane9.setBackground(new Color(255, 255, 255));
        this.jTabbedPane9.setFont(new Font("幼圆", 0, 12));
        this.jPanel16.setBackground(new Color(255, 255, 240));
        this.jPanel16.setBorder(BorderFactory.createTitledBorder("反应堆/箱子爆率管理"));
        this.反应堆.setBackground(new Color(255, 255, 240));//8-8708
        this.反应堆.setFont(new Font("幼圆", 0, 15));
        this.反应堆.setModel(new DefaultTableModel(new Object[0][], new String[]{"序列号", "反应堆", "物品代码", "概率", "物品名字"}) {
            boolean[] canEdit = {false, false, false, false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.反应堆.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane26.setViewportView(this.反应堆);
        this.jPanel61.setBackground(new Color(102, 102, 102));
        this.jPanel61.setBorder(BorderFactory.createTitledBorder(null, "反应堆编辑", 2, 2, new Font("幼圆", 0, 18)));
        this.jPanel61.setLayout(new AbsoluteLayout());
        this.jButton26.setFont(new Font("幼圆", 0, 15));
        this.jButton26.setText("刷新列表信息");
        this.jButton26.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton26ActionPerformed(evt);
            }
        });
        this.jPanel61.add(this.jButton26, new AbsoluteConstraints(420, 110, 160, 40));
        this.反应堆序列号.setEditable(false);
        this.反应堆序列号.setFont(new Font("幼圆", 0, 15));
        this.jPanel61.add(this.反应堆序列号, new AbsoluteConstraints(10, 60, 70, 30));
        this.反应堆代码.setFont(new Font("幼圆", 0, 15));
        this.jPanel61.add(this.反应堆代码, new AbsoluteConstraints(80, 60, 90, 30));
        this.反应堆物品.setFont(new Font("幼圆", 0, 15));
        this.jPanel61.add(this.反应堆物品, new AbsoluteConstraints(170, 60, 90, 30));
        this.反应堆概率.setFont(new Font("幼圆", 0, 15));
        this.反应堆概率.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.反应堆概率ActionPerformed(evt);
            }
        });
        this.jPanel61.add(this.反应堆概率, new AbsoluteConstraints(260, 60, 90, 30));
        this.新增反应堆物品.setFont(new Font("幼圆", 0, 15));
        this.新增反应堆物品.setText("新增");
        this.新增反应堆物品.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.新增反应堆物品ActionPerformed(evt);
            }
        });
        this.jPanel61.add(this.新增反应堆物品, new AbsoluteConstraints(360, 60, 70, 30));
        this.删除反应堆物品1.setFont(new Font("幼圆", 0, 15));
        this.删除反应堆物品1.setText("删除");
        this.删除反应堆物品1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除反应堆物品1ActionPerformed(evt);
            }
        });
        this.jPanel61.add(this.删除反应堆物品1, new AbsoluteConstraints(440, 60, 70, 30));
        this.查找反应堆掉落.setFont(new Font("幼圆", 0, 15));
        this.jPanel61.add(this.查找反应堆掉落, new AbsoluteConstraints(170, 120, 90, 30));
        this.jButton36.setFont(new Font("幼圆", 0, 15));
        this.jButton36.setText("查找");
        this.jButton36.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton36ActionPerformed(evt);
            }
        });
        this.jPanel61.add(this.jButton36, new AbsoluteConstraints(260, 120, -1, 30));
        this.查找物品.setFont(new Font("幼圆", 0, 15));
        this.jPanel61.add(this.查找物品, new AbsoluteConstraints(10, 120, 90, 30));
        this.jButton37.setFont(new Font("幼圆", 0, 15));
        this.jButton37.setText("查找");
        this.jButton37.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton37ActionPerformed(evt);
            }
        });
        this.jPanel61.add(this.jButton37, new AbsoluteConstraints(100, 120, -1, 30));
        this.jLabel274.setFont(new Font("幼圆", 0, 15));
        this.jLabel274.setText("掉落概率；");
        this.jPanel61.add(this.jLabel274, new AbsoluteConstraints(260, 40, -1, 20));
        this.jLabel275.setFont(new Font("幼圆", 0, 15));
        this.jLabel275.setText("序号；");
        this.jPanel61.add(this.jLabel275, new AbsoluteConstraints(10, 40, -1, 20));
        this.jLabel277.setFont(new Font("幼圆", 0, 15));
        this.jLabel277.setText("物品代码；");
        this.jPanel61.add(this.jLabel277, new AbsoluteConstraints(170, 40, -1, 20));
        this.jLabel278.setFont(new Font("幼圆", 0, 15));
        this.jLabel278.setText("反应堆；");
        this.jPanel61.add(this.jLabel278, new AbsoluteConstraints(80, 40, -1, 20));
        this.jLabel279.setFont(new Font("幼圆", 0, 15));
        this.jLabel279.setText("反应堆；");
        this.jPanel61.add(this.jLabel279, new AbsoluteConstraints(170, 100, -1, 20));
        this.jLabel280.setFont(new Font("幼圆", 0, 15));
        this.jLabel280.setText("物品代码；");
        this.jPanel61.add(this.jLabel280, new AbsoluteConstraints(10, 100, -1, 20));
        this.修改反应堆物品.setFont(new Font("幼圆", 0, 15));
        this.修改反应堆物品.setText("修改");
        this.修改反应堆物品.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.修改反应堆物品ActionPerformed(evt);
            }
        });
        this.jPanel61.add(this.修改反应堆物品, new AbsoluteConstraints(520, 60, 70, 30));
        this.重载箱子反应堆按钮.setText("重载箱子反应堆");
        this.重载箱子反应堆按钮.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.重载箱子反应堆按钮ActionPerformed(evt);
            }
        });
        this.jPanel61.add(this.重载箱子反应堆按钮, new AbsoluteConstraints(660, 110, 137, 40));
        this.jLabel282.setFont(new Font("幼圆", 0, 15));
        this.jLabel282.setText("删除指定反应堆的所有物品；");
        this.jPanel61.add(this.jLabel282, new AbsoluteConstraints(630, 30, 209, 20));
        this.jPanel61.add(this.删除反应堆物品代码, new AbsoluteConstraints(660, 60, 95, 30));
        this.删除反应堆物品.setFont(new Font("幼圆", 0, 15));
        this.删除反应堆物品.setText("删除");
        this.删除反应堆物品.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除反应堆物品ActionPerformed(evt);
            }
        });
        this.jPanel61.add(this.删除反应堆物品, new AbsoluteConstraints(760, 60, 70, 30));
        final GroupLayout jPanel16Layout = new GroupLayout(this.jPanel16);
        this.jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(jPanel16Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel16Layout.createSequentialGroup().addComponent(this.jScrollPane26, -2, 839, -2).addContainerGap(-1, 32767)).addGroup(Alignment.TRAILING, jPanel16Layout.createSequentialGroup().addGap(0, 396, 32767).addComponent(this.jPanel61, -2, -1, -2).addGap(66, 66, 66)));
        jPanel16Layout.setVerticalGroup(jPanel16Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel16Layout.createSequentialGroup().addComponent(this.jScrollPane26, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jPanel61, -2, 163, -2).addContainerGap(30, 32767)));
        this.jTabbedPane9.addTab("箱子爆率", this.jPanel16);
        this.jPanel18.setBackground(new Color(255, 255, 240));
        this.jPanel27.setBackground(new Color(255, 255, 240));
        this.jPanel27.setBorder(BorderFactory.createTitledBorder(null, "怪物爆物/(10000=1%)", 2, 2, new Font("幼圆", 0, 18)));
        this.jPanel27.setLayout(new AbsoluteLayout());
        this.怪物爆物.setFont(new Font("幼圆", 0, 15));
        this.怪物爆物.setModel(new DefaultTableModel(new Object[0][], new String[]{"序列号", "怪物代码", "物品代码", "爆率", "物品名字"}) {
            boolean[] canEdit = {false, false, false, false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.怪物爆物.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane7.setViewportView(this.怪物爆物);
        this.jPanel27.add(this.jScrollPane7, new AbsoluteConstraints(10, 30, 850, 430));
        this.jLabel213.setFont(new Font("幼圆", 0, 15));
        this.jLabel213.setText("序列号；");
        this.怪物爆物序列号.setEditable(false);
        this.怪物爆物序列号.setFont(new Font("幼圆", 0, 15));
        this.jLabel120.setFont(new Font("幼圆", 0, 15));
        this.jLabel120.setText("怪物代码");
        this.怪物爆物怪物代码.setFont(new Font("幼圆", 0, 15));
        this.jLabel211.setFont(new Font("幼圆", 0, 15));
        this.jLabel211.setText("物品代码；");
        this.怪物爆物物品代码.setFont(new Font("幼圆", 0, 15));
        this.jLabel212.setFont(new Font("幼圆", 0, 15));
        this.jLabel212.setText("爆率；");
        this.怪物爆物爆率.setFont(new Font("幼圆", 0, 15));
        this.jLabel39.setFont(new Font("幼圆", 0, 15));
        this.jLabel39.setText("物品名；");
        this.怪物爆物物品名称.setEditable(false);
        this.怪物爆物物品名称.setFont(new Font("幼圆", 0, 15));
        this.添加怪物爆物.setFont(new Font("幼圆", 0, 12));
        this.添加怪物爆物.setText("添加");
        this.添加怪物爆物.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.添加怪物爆物ActionPerformed(evt);
            }
        });
        this.删除怪物爆物.setFont(new Font("幼圆", 0, 12));
        this.删除怪物爆物.setText("删除");
        this.删除怪物爆物.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除怪物爆物ActionPerformed(evt);
            }
        });
        this.修改怪物爆物.setFont(new Font("幼圆", 0, 12));
        this.修改怪物爆物.setText("修改");
        this.修改怪物爆物.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.修改怪物爆物ActionPerformed(evt);
            }
        });
        this.刷新怪物爆物.setFont(new Font("幼圆", 0, 12));
        this.刷新怪物爆物.setText("刷新怪物爆物");
        this.刷新怪物爆物.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.刷新怪物爆物ActionPerformed(evt);
            }
        });
        this.jLabel324.setFont(new Font("幼圆", 0, 12));
        this.jLabel324.setText("指定物品查询掉落");
        this.查询物品掉落代码.setFont(new Font("幼圆", 0, 15));
        this.查询物品掉落代码.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.查询物品掉落代码ActionPerformed(evt);
            }
        });
        this.查询物品掉落1.setFont(new Font("幼圆", 0, 12));
        this.查询物品掉落1.setText("查询物品掉落");
        this.查询物品掉落1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.查询物品掉落1ActionPerformed(evt);
            }
        });
        this.jLabel316.setText("指定怪物查物品掉落");
        this.jLabel325.setFont(new Font("幼圆", 0, 12));
        this.jLabel325.setText("删除指定物品掉落");
        this.删除指定的掉落.setFont(new Font("幼圆", 0, 15));
        this.删除指定的掉落按键1.setFont(new Font("幼圆", 0, 15));
        this.删除指定的掉落按键1.setText("删除指定掉落");
        this.删除指定的掉落按键1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除指定的掉落按键1ActionPerformed(evt);
            }
        });
        this.刷新怪物卡片.setFont(new Font("幼圆", 0, 15));
        this.刷新怪物卡片.setText("刷新卡片");
        this.刷新怪物卡片.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.刷新怪物卡片ActionPerformed(evt);
            }
        });
        this.查询怪物掉落代码.setFont(new Font("幼圆", 0, 15));
        this.查询怪物掉落.setFont(new Font("幼圆", 0, 15));
        this.查询怪物掉落.setText("查询怪物掉落");
        this.查询怪物掉落.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.查询怪物掉落ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel18Layout = new GroupLayout(this.jPanel18);
        this.jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(jPanel18Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel18Layout.createSequentialGroup().addContainerGap().addGroup(jPanel18Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jPanel27, -1, 1299, 32767).addGroup(jPanel18Layout.createSequentialGroup().addGroup(jPanel18Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel18Layout.createSequentialGroup().addComponent(this.jLabel213).addGap(18, 18, 18).addComponent(this.jLabel120)).addGroup(jPanel18Layout.createSequentialGroup().addGroup(jPanel18Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel18Layout.createSequentialGroup().addComponent(this.怪物爆物序列号, -2, 68, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.怪物爆物怪物代码, -2, 93, -2)).addComponent(this.查询物品掉落1, -2, 122, -2).addComponent(this.jLabel324, -2, 106, -2).addComponent(this.查询物品掉落代码, -2, 122, -2)).addGap(10, 10, 10).addGroup(jPanel18Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel18Layout.createSequentialGroup().addComponent(this.jLabel211).addGap(25, 25, 25).addComponent(this.jLabel212)).addGroup(jPanel18Layout.createSequentialGroup().addComponent(this.怪物爆物物品代码, -2, 90, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.怪物爆物爆率, -2, 90, -2)).addComponent(this.jLabel325).addGroup(jPanel18Layout.createParallelGroup(Alignment.TRAILING, false).addComponent(this.删除指定的掉落, Alignment.LEADING).addComponent(this.删除指定的掉落按键1, Alignment.LEADING, -1, -1, 32767))))).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel18Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel18Layout.createSequentialGroup().addComponent(this.怪物爆物物品名称, -2, 90, -2).addGap(18, 18, 18).addComponent(this.添加怪物爆物, -2, 70, -2).addGap(61, 61, 61).addComponent(this.修改怪物爆物, -2, 70, -2).addGap(18, 18, 18).addComponent(this.删除怪物爆物, -2, 70, -2)).addComponent(this.jLabel39).addGroup(jPanel18Layout.createSequentialGroup().addGap(3, 3, 3).addGroup(jPanel18Layout.createParallelGroup(Alignment.LEADING).addComponent(this.查询怪物掉落, -2, 140, -2).addComponent(this.jLabel316).addComponent(this.查询怪物掉落代码, -2, 132, -2)).addGap(81, 81, 81).addGroup(jPanel18Layout.createParallelGroup(Alignment.LEADING).addComponent(this.刷新怪物卡片, -2, 140, -2).addComponent(this.刷新怪物爆物, -2, 140, -2)))).addContainerGap(-1, 32767)))));
        jPanel18Layout.setVerticalGroup(jPanel18Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel18Layout.createSequentialGroup().addComponent(this.jPanel27, -2, 470, -2).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel18Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel213).addComponent(this.jLabel120).addComponent(this.jLabel211, -2, 20, -2).addComponent(this.jLabel212).addComponent(this.jLabel39)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel18Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.怪物爆物序列号, -2, -1, -2).addComponent(this.怪物爆物怪物代码).addComponent(this.怪物爆物物品代码).addComponent(this.怪物爆物爆率).addComponent(this.怪物爆物物品名称).addComponent(this.添加怪物爆物, -2, 30, -2).addComponent(this.修改怪物爆物, -2, 30, -2).addComponent(this.删除怪物爆物, -2, 30, -2)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel18Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel18Layout.createSequentialGroup().addGroup(jPanel18Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jLabel324).addGroup(jPanel18Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel325).addComponent(this.jLabel316))).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel18Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel18Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.查询物品掉落代码, -2, 26, -2).addComponent(this.删除指定的掉落, -2, 24, -2)).addComponent(this.查询怪物掉落代码, Alignment.TRAILING, -2, 28, -2)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel18Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.查询物品掉落1).addComponent(this.删除指定的掉落按键1, -2, 24, -2).addComponent(this.查询怪物掉落, -2, 25, -2))).addGroup(jPanel18Layout.createSequentialGroup().addComponent(this.刷新怪物卡片, -2, 38, -2).addGap(18, 18, 18).addComponent(this.刷新怪物爆物, -2, 38, -2))).addContainerGap(605, 32767)));
        this.jTabbedPane9.addTab("物品爆率", this.jPanel18);
        this.jPanel28.setBackground(new Color(255, 255, 240));
        this.jPanel28.setBorder(BorderFactory.createTitledBorder(null, "全局爆物/(10000=1%)", 2, 2, new Font("幼圆", 0, 18)));
        this.jPanel28.setLayout(new AbsoluteLayout());
        this.世界爆物.setFont(new Font("幼圆", 0, 15));
        this.世界爆物.setModel(new DefaultTableModel(new Object[0][], new String[]{"序列号", "物品代码", "爆率", "物品名"}) {
            boolean[] canEdit = {false, false, false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.世界爆物.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane8.setViewportView(this.世界爆物);
        this.jPanel28.add(this.jScrollPane8, new AbsoluteConstraints(10, 30, 840, -1));
        this.世界爆物序列号.setEditable(false);
        this.世界爆物序列号.setFont(new Font("幼圆", 0, 15));
        this.世界爆物序列号.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.世界爆物序列号ActionPerformed(evt);
            }
        });
        this.jPanel28.add(this.世界爆物序列号, new AbsoluteConstraints(10, 490, 100, -1));
        this.世界爆物物品代码.setFont(new Font("幼圆", 0, 15));
        this.世界爆物物品代码.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.世界爆物物品代码ActionPerformed(evt);
            }
        });
        this.jPanel28.add(this.世界爆物物品代码, new AbsoluteConstraints(140, 490, 100, 30));
        this.世界爆物爆率.setFont(new Font("幼圆", 0, 15));
        this.世界爆物爆率.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.世界爆物爆率ActionPerformed(evt);
            }
        });
        this.jPanel28.add(this.世界爆物爆率, new AbsoluteConstraints(270, 490, 100, 30));
        this.添加世界爆物.setFont(new Font("幼圆", 0, 12));
        this.添加世界爆物.setText("添加");
        this.添加世界爆物.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.添加世界爆物ActionPerformed(evt);
            }
        });
        this.jPanel28.add(this.添加世界爆物, new AbsoluteConstraints(530, 490, 70, 30));
        this.删除世界爆物.setFont(new Font("幼圆", 0, 12));
        this.删除世界爆物.setText("删除");
        this.删除世界爆物.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除世界爆物ActionPerformed(evt);
            }
        });
        this.jPanel28.add(this.删除世界爆物, new AbsoluteConstraints(730, 490, 70, 30));
        this.jLabel210.setFont(new Font("幼圆", 0, 15));
        this.jLabel210.setText("序列号；");
        this.jPanel28.add(this.jLabel210, new AbsoluteConstraints(10, 470, -1, -1));
        this.jLabel202.setFont(new Font("幼圆", 0, 15));
        this.jLabel202.setText("物品代码；");
        this.jPanel28.add(this.jLabel202, new AbsoluteConstraints(140, 470, -1, 20));
        this.jLabel209.setFont(new Font("幼圆", 0, 15));
        this.jLabel209.setText("爆率；");
        this.jPanel28.add(this.jLabel209, new AbsoluteConstraints(270, 470, -1, -1));
        this.世界爆物名称.setEditable(false);
        this.世界爆物名称.setFont(new Font("幼圆", 0, 15));
        this.世界爆物名称.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.世界爆物名称ActionPerformed(evt);
            }
        });
        this.jPanel28.add(this.世界爆物名称, new AbsoluteConstraints(400, 490, 100, 30));
        this.jLabel40.setFont(new Font("幼圆", 0, 15));
        this.jLabel40.setText("物品名；");
        this.jPanel28.add(this.jLabel40, new AbsoluteConstraints(400, 470, -1, -1));
        this.修改世界爆物.setFont(new Font("幼圆", 0, 12));
        this.修改世界爆物.setText("修改");
        this.修改世界爆物.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.修改世界爆物ActionPerformed(evt);
            }
        });
        this.jPanel28.add(this.修改世界爆物, new AbsoluteConstraints(630, 490, 70, 30));
        this.刷新世界爆物.setFont(new Font("幼圆", 0, 12));
        this.刷新世界爆物.setText("刷新世界爆物");
        this.刷新世界爆物.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.刷新世界爆物ActionPerformed(evt);
            }
        });
        this.jPanel28.add(this.刷新世界爆物, new AbsoluteConstraints(530, 530, 140, 30));
        this.jLabel323.setFont(new Font("幼圆", 0, 12));
        this.jLabel323.setText("指定物品查询掉落");
        this.jPanel28.add(this.jLabel323, new AbsoluteConstraints(10, 530, -1, -1));
        this.查询物品掉落代码1.setFont(new Font("幼圆", 0, 15));
        this.查询物品掉落代码1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.查询物品掉落代码1ActionPerformed(evt);
            }
        });
        this.jPanel28.add(this.查询物品掉落代码1, new AbsoluteConstraints(10, 550, 100, 30));
        this.查询物品掉落.setFont(new Font("幼圆", 0, 12));
        this.查询物品掉落.setText("查询物品掉落");
        this.查询物品掉落.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.查询物品掉落ActionPerformed(evt);
            }
        });
        this.jPanel28.add(this.查询物品掉落, new AbsoluteConstraints(10, 590, -1, -1));
        this.jLabel321.setFont(new Font("幼圆", 0, 12));
        this.jLabel321.setText("删除指定物品掉落");
        this.jPanel28.add(this.jLabel321, new AbsoluteConstraints(270, 530, -1, -1));
        this.删除指定的掉落1.setFont(new Font("幼圆", 0, 15));
        this.jPanel28.add(this.删除指定的掉落1, new AbsoluteConstraints(270, 550, 110, 30));
        this.删除指定的掉落按键.setFont(new Font("幼圆", 0, 15));
        this.删除指定的掉落按键.setText("删除指定掉落");
        this.删除指定的掉落按键.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除指定的掉落按键ActionPerformed(evt);
            }
        });
        this.jPanel28.add(this.删除指定的掉落按键, new AbsoluteConstraints(270, 590, -1, 30));
        final GroupLayout jPanel19Layout = new GroupLayout(this.jPanel19);
        this.jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(jPanel19Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel19Layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel28, -1, -1, 32767).addContainerGap()));
        jPanel19Layout.setVerticalGroup(jPanel19Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jPanel28, -1, -1, 32767));
        this.jTabbedPane9.addTab("全局爆率", this.jPanel19);
        this.jPanel20.setBackground(new Color(255, 255, 240));
        this.钓鱼管理.setBackground(new Color(255, 255, 255));
        this.钓鱼管理.setBorder(BorderFactory.createTitledBorder(null, "钓鱼管理", 2, 2, new Font("幼圆", 0, 24)));
        this.钓鱼管理.setLayout(new AbsoluteLayout());
        this.钓鱼物品.setBackground(new Color(102, 102, 102));
        this.钓鱼物品.setFont(new Font("幼圆", 0, 15));
        this.钓鱼物品.setModel(new DefaultTableModel(new Object[0][], new String[]{"序号", "代码", "概率", "物品名称"}) {
            boolean[] canEdit = {false, false, false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.钓鱼物品.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane10.setViewportView(this.钓鱼物品);
        this.钓鱼管理.add(this.jScrollPane10, new AbsoluteConstraints(10, 30, 860, 460));
        this.钓鱼管理.add(this.jSeparator11, new AbsoluteConstraints(600, 10, -1, -1));
        this.钓鱼管理.add(this.jSeparator12, new AbsoluteConstraints(600, 10, -1, -1));
        this.jPanel91.setBackground(new Color(255, 255, 240));
        this.jPanel91.setBorder(BorderFactory.createTitledBorder(null, "钓鱼编辑", 2, 2, new Font("幼圆", 0, 18)));
        this.jPanel91.setLayout(new AbsoluteLayout());
        this.修改钓鱼物品.setFont(new Font("幼圆", 0, 15));
        this.修改钓鱼物品.setText("修改");
        this.修改钓鱼物品.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.修改钓鱼物品ActionPerformed(evt);
            }
        });
        this.jPanel91.add(this.修改钓鱼物品, new AbsoluteConstraints(630, 50, -1, 30));
        this.刷新钓鱼物品.setFont(new Font("幼圆", 0, 15));
        this.刷新钓鱼物品.setText("刷新钓鱼物品");
        this.刷新钓鱼物品.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.刷新钓鱼物品ActionPerformed(evt);
            }
        });
        this.jPanel91.add(this.刷新钓鱼物品, new AbsoluteConstraints(610, 90, -1, 30));
        this.钓鱼物品代码.setFont(new Font("幼圆", 0, 15));
        this.钓鱼物品代码.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.钓鱼物品代码ActionPerformed(evt);
            }
        });
        this.jPanel91.add(this.钓鱼物品代码, new AbsoluteConstraints(100, 50, 110, 30));
        this.新增钓鱼物品.setFont(new Font("幼圆", 0, 15));
        this.新增钓鱼物品.setText("新增");
        this.新增钓鱼物品.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.新增钓鱼物品ActionPerformed(evt);
            }
        });
        this.jPanel91.add(this.新增钓鱼物品, new AbsoluteConstraints(750, 50, -1, 30));
        this.钓鱼物品概率.setFont(new Font("幼圆", 0, 15));
        this.jPanel91.add(this.钓鱼物品概率, new AbsoluteConstraints(210, 50, 100, 30));
        this.钓鱼物品名称.setEditable(false);
        this.钓鱼物品名称.setFont(new Font("幼圆", 0, 15));
        this.jPanel91.add(this.钓鱼物品名称, new AbsoluteConstraints(310, 50, 150, 30));
        this.删除钓鱼物品.setFont(new Font("幼圆", 0, 15));
        this.删除钓鱼物品.setText("删除");
        this.删除钓鱼物品.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除钓鱼物品ActionPerformed(evt);
            }
        });
        this.jPanel91.add(this.删除钓鱼物品, new AbsoluteConstraints(500, 50, -1, 30));
        this.钓鱼物品序号.setEditable(false);
        this.钓鱼物品序号.setFont(new Font("幼圆", 0, 15));
        this.钓鱼物品序号.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.钓鱼物品序号ActionPerformed(evt);
            }
        });
        this.jPanel91.add(this.钓鱼物品序号, new AbsoluteConstraints(20, 50, 80, 30));
        this.jLabel379.setFont(new Font("幼圆", 0, 15));
        this.jLabel379.setText("物品名字");
        this.jPanel91.add(this.jLabel379, new AbsoluteConstraints(310, 30, -1, -1));
        this.jLabel380.setFont(new Font("幼圆", 0, 15));
        this.jLabel380.setText("序列号");
        this.jPanel91.add(this.jLabel380, new AbsoluteConstraints(20, 30, -1, -1));
        this.jLabel381.setFont(new Font("幼圆", 0, 15));
        this.jLabel381.setText("物品代码");
        this.jPanel91.add(this.jLabel381, new AbsoluteConstraints(100, 30, -1, -1));
        this.jLabel382.setFont(new Font("幼圆", 0, 15));
        this.jLabel382.setText("垂钓概率");
        this.jPanel91.add(this.jLabel382, new AbsoluteConstraints(210, 30, -1, -1));
        this.钓鱼管理.add(this.jPanel91, new AbsoluteConstraints(10, 500, 860, 130));
        this.ZEVMS2提示框1.setFont(new Font("幼圆", 0, 18));
        this.ZEVMS2提示框1.setText("[信息]：");
        this.钓鱼管理.add(this.ZEVMS2提示框1, new AbsoluteConstraints(0, 725, 1260, 30));
        final GroupLayout jPanel20Layout = new GroupLayout(this.jPanel20);
        this.jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(jPanel20Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel20Layout.createSequentialGroup().addComponent(this.钓鱼管理, -2, 880, -2).addGap(0, 0, 32767)));
        jPanel20Layout.setVerticalGroup(jPanel20Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel20Layout.createSequentialGroup().addComponent(this.钓鱼管理, -2, 660, -2).addGap(0, 0, 32767)));
        this.jTabbedPane9.addTab("钓鱼物品", this.jPanel20);
        final GroupLayout jPanel15Layout = new GroupLayout(this.jPanel15);
        this.jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(jPanel15Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jTabbedPane9));
        jPanel15Layout.setVerticalGroup(jPanel15Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel15Layout.createSequentialGroup().addComponent(this.jTabbedPane9).addContainerGap()));
        this.jTabbedPane2.addTab("物品爆率", this.jPanel15);
        this.jTabbedPane8.setFont(new Font("幼圆", 0, 14));
        this.jPanel23.setBackground(new Color(255, 255, 240));
        this.账号信息.setFont(new Font("幼圆", 0, 14));
        this.账号信息.setModel(new DefaultTableModel(new Object[0][], new String[]{"账号ID", "账号", "IP地址", "MAC地址", "绑定QQ", "点券", "抵用", "最近上线", "在线", "封号", "GM"}) {
            Class[] types = {Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, String.class, Object.class};
            boolean[] canEdit = {false, false, false, false, false, false, false, false, false, false, false};

            @Override
            public Class getColumnClass(final int columnIndex) {
                return this.types[columnIndex];
            }

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.账号信息.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane3.setViewportView(this.账号信息);
        this.jPanel30.setBorder(BorderFactory.createTitledBorder(null, "账号修改", 2, 2, new Font("幼圆", 0, 18)));
        this.jPanel30.setLayout(new AbsoluteLayout());
        this.抵用.setFont(new Font("幼圆", 0, 12));
        this.jPanel30.add(this.抵用, new AbsoluteConstraints(520, 40, 120, 30));
        this.账号.setEditable(false);
        this.账号.setFont(new Font("幼圆", 0, 12));
        this.jPanel30.add(this.账号, new AbsoluteConstraints(100, 40, 100, 30));
        this.点券.setFont(new Font("幼圆", 0, 12));
        this.jPanel30.add(this.点券, new AbsoluteConstraints(370, 40, 120, 30));
        this.jLabel55.setFont(new Font("幼圆", 0, 12));
        this.jLabel55.setText("抵用；");
        this.jPanel30.add(this.jLabel55, new AbsoluteConstraints(520, 20, 60, -1));
        this.jLabel131.setFont(new Font("幼圆", 0, 12));
        this.jLabel131.setText("点券；");
        this.jPanel30.add(this.jLabel131, new AbsoluteConstraints(370, 20, -1, -1));
        this.修改账号点券抵用.setFont(new Font("幼圆", 0, 12));
        this.修改账号点券抵用.setText("修改");
        this.修改账号点券抵用.setToolTipText("<html>\n点击账号后可修改账号的<strong><font color=\"#FF0000\">抵用券</font></strong><strong>和<font color=\"#FF0000\">点券</font></strong>");
        this.修改账号点券抵用.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.修改账号点券抵用ActionPerformed(evt);
            }
        });
        this.jPanel30.add(this.修改账号点券抵用, new AbsoluteConstraints(780, 40, 70, 30));
        this.账号ID.setEditable(false);
        this.账号ID.setFont(new Font("幼圆", 0, 12));
        this.jPanel30.add(this.账号ID, new AbsoluteConstraints(10, 40, 70, 30));
        this.jLabel206.setFont(new Font("幼圆", 0, 12));
        this.jLabel206.setText("ID；");
        this.jPanel30.add(this.jLabel206, new AbsoluteConstraints(10, 20, -1, -1));
        this.jLabel312.setFont(new Font("幼圆", 0, 12));
        this.jLabel312.setText("管理；");
        this.jPanel30.add(this.jLabel312, new AbsoluteConstraints(670, 20, -1, -1));
        this.管理1.setFont(new Font("幼圆", 0, 12));
        this.jPanel30.add(this.管理1, new AbsoluteConstraints(670, 40, 70, 30));
        this.jLabel353.setFont(new Font("幼圆", 0, 12));
        this.jLabel353.setText("账号；");
        this.jPanel30.add(this.jLabel353, new AbsoluteConstraints(100, 20, -1, -1));
        this.QQ.setFont(new Font("幼圆", 0, 12));
        this.jPanel30.add(this.QQ, new AbsoluteConstraints(220, 40, 120, 30));
        this.jLabel357.setFont(new Font("幼圆", 0, 12));
        this.jLabel357.setText("绑定QQ；");
        this.jPanel30.add(this.jLabel357, new AbsoluteConstraints(220, 20, 80, -1));
        this.jPanel32.setBorder(BorderFactory.createTitledBorder(null, "注册/修改", 2, 2, new Font("幼圆", 0, 18)));
        this.jPanel32.setLayout(new AbsoluteLayout());
        this.注册的账号.setFont(new Font("幼圆", 0, 15));
        this.注册的账号.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.注册的账号ActionPerformed(evt);
            }
        });
        this.jPanel32.add(this.注册的账号, new AbsoluteConstraints(70, 30, 100, 30));
        this.注册的密码.setFont(new Font("幼圆", 0, 15));
        this.注册的密码.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.注册的密码ActionPerformed(evt);
            }
        });
        this.jPanel32.add(this.注册的密码, new AbsoluteConstraints(230, 30, 100, 30));
        this.jButton35.setFont(new Font("幼圆", 0, 14));
        this.jButton35.setText("注册");
        this.jButton35.setToolTipText("<html>\n输入<strong><font color=\"#FF0000\">账号</font></strong><strong>和<strong><font color=\"#FF0000\">密码</font></strong><strong>即可注册账号");
        this.jButton35.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton35ActionPerformed(evt);
            }
        });
        this.jPanel32.add(this.jButton35, new AbsoluteConstraints(420, 30, 70, 30));
        this.jLabel111.setFont(new Font("幼圆", 0, 18));
        this.jLabel111.setText("账号；");
        this.jPanel32.add(this.jLabel111, new AbsoluteConstraints(20, 30, -1, 30));
        this.jLabel201.setFont(new Font("幼圆", 0, 18));
        this.jLabel201.setText("密码；");
        this.jPanel32.add(this.jLabel201, new AbsoluteConstraints(180, 30, -1, 30));
        this.jButton32.setFont(new Font("幼圆", 0, 14));
        this.jButton32.setText("改密");
        this.jButton32.setToolTipText("<html>\n输入账号修改<strong><font color=\"#FF0000\">密码</font></strong><strong>");
        this.jButton32.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton32ActionPerformed(evt);
            }
        });
        this.jPanel32.add(this.jButton32, new AbsoluteConstraints(340, 30, 70, 30));
        this.刷新账号信息.setFont(new Font("幼圆", 0, 12));
        this.刷新账号信息.setText("全部账号");
        this.刷新账号信息.setToolTipText("显示所有玩家账号");
        this.刷新账号信息.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.刷新账号信息ActionPerformed(evt);
            }
        });
        this.离线账号.setFont(new Font("幼圆", 0, 12));
        this.离线账号.setText("离线账号");
        this.离线账号.setToolTipText("显示离线账号");
        this.离线账号.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.离线账号ActionPerformed(evt);
            }
        });
        this.解封.setFont(new Font("幼圆", 0, 12));
        this.解封.setText("解封账号");
        this.解封.setToolTipText("<html>\n在文本框<strong><font color=\"#FF0000\">操作的账号</font></strong>中输入账号即可解封已经被封禁的账号<br>\n");
        this.解封.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.解封ActionPerformed(evt);
            }
        });
        this.已封账号.setFont(new Font("幼圆", 0, 12));
        this.已封账号.setText("已封账号");
        this.已封账号.setToolTipText("显示已经被封禁的账号");
        this.已封账号.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.已封账号ActionPerformed(evt);
            }
        });
        this.在线账号.setFont(new Font("幼圆", 0, 12));
        this.在线账号.setText("在线账号");
        this.在线账号.setToolTipText("显示在线账号");
        this.在线账号.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.在线账号ActionPerformed(evt);
            }
        });
        this.删除账号.setFont(new Font("幼圆", 0, 12));
        this.删除账号.setText("删除账号");
        this.删除账号.setToolTipText("<html>\n在文本框<strong><font color=\"#FF0000\">操作的账号</font></strong>中输入账号即可删除账号<br>");
        this.删除账号.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除账号ActionPerformed(evt);
            }
        });
        this.封锁账号.setFont(new Font("幼圆", 0, 12));
        this.封锁账号.setText("封锁账号");
        this.封锁账号.setToolTipText("<html>\n在文本框<strong><font color=\"#FF0000\">操作的账号</font></strong>中输入账号即可封禁账号<br>");
        this.封锁账号.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.封锁账号ActionPerformed(evt);
            }
        });
        this.解卡.setFont(new Font("幼圆", 0, 12));
        this.解卡.setText("解卡账号");
        this.解卡.setToolTipText("<html>\n在文本框<strong><font color=\"#FF0000\">操作的账号</font></strong>中输入账号即可解卡账号<br>");
        this.解卡.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.解卡ActionPerformed(evt);
            }
        });
        this.显示在线账号.setFont(new Font("幼圆", 0, 18));
        this.账号提示语言.setFont(new Font("幼圆", 0, 18));
        this.账号提示语言.setText("[信息]：");
        this.jButton12.setFont(new Font("幼圆", 0, 14));
        this.jButton12.setText("查账号");
        this.jButton12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton12ActionPerformed(evt);
            }
        });
        this.账号操作.setFont(new Font("幼圆", 0, 15));
        this.账号操作.setText("327321366");
        final GroupLayout jPanel23Layout = new GroupLayout(this.jPanel23);
        this.jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(jPanel23Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel23Layout.createSequentialGroup().addGap(10, 10, 10).addGroup(jPanel23Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel23Layout.createSequentialGroup().addComponent(this.刷新账号信息).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.在线账号).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.离线账号).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.已封账号).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.删除账号).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.解卡).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.封锁账号).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.解封).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton12, -2, 80, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.账号操作, -2, 71, -2).addContainerGap(443, 32767)).addGroup(jPanel23Layout.createSequentialGroup().addGap(0, 0, 32767).addComponent(this.显示在线账号, -2, 130, -2)))).addComponent(this.jScrollPane3).addGroup(jPanel23Layout.createSequentialGroup().addComponent(this.账号提示语言, -2, 700, -2).addContainerGap(630, 32767)).addComponent(this.jPanel30, -1, -1, 32767).addComponent(this.jPanel32, -1, -1, 32767));
        jPanel23Layout.setVerticalGroup(jPanel23Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel23Layout.createSequentialGroup().addGroup(jPanel23Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.刷新账号信息, -2, 30, -2).addComponent(this.在线账号, -2, 30, -2).addComponent(this.离线账号, -2, 30, -2).addComponent(this.已封账号, -2, 30, -2).addComponent(this.删除账号, -2, 30, -2).addComponent(this.解卡, -2, 30, -2).addComponent(this.封锁账号, -2, 30, -2).addComponent(this.解封, -2, 30, -2).addComponent(this.jButton12, -2, 30, -2).addComponent(this.账号操作, -2, 30, -2)).addGap(5, 5, 5).addComponent(this.jScrollPane3, -2, 385, -2).addGap(18, 18, 18).addComponent(this.jPanel30, -2, 78, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jPanel32, -2, 68, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.账号提示语言, -2, 25, -2).addGap(27, 27, 27).addComponent(this.显示在线账号, -2, 30, -2).addContainerGap(-1, 32767)));
        this.jTabbedPane8.addTab("账号管理", this.jPanel23);
        this.角色信息1.setBackground(new Color(255, 255, 240));
        this.角色信息1.setLayout(new AbsoluteLayout());
        this.角色信息.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.角色信息.setFont(new Font("幼圆", 0, 12));
        this.角色信息.setModel(new DefaultTableModel(new Object[0][], new String[]{"角色ID", "账号ID", "角色昵称", "职业", "等级", "力量", "敏捷", "智力", "运气", "MaxHP", "MaxMP", "金币", "所在地图", "状态", "GM", "发型", "脸型"}) {
            boolean[] canEdit = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.角色信息.setName("");
        this.角色信息.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane4.setViewportView(this.角色信息);
        this.角色信息1.add(this.jScrollPane4, new AbsoluteConstraints(0, 0, 1330, 450));
        this.刷新角色信息.setFont(new Font("幼圆", 0, 15));
        this.刷新角色信息.setText("刷新");
        this.刷新角色信息.setToolTipText("显示所有角色");
        this.刷新角色信息.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.刷新角色信息ActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.刷新角色信息, new AbsoluteConstraints(10, 500, 110, 30));
        this.显示管理角色.setFont(new Font("幼圆", 0, 15));
        this.显示管理角色.setText("管理角色");
        this.显示管理角色.setToolTipText("显示所有GM管理员");
        this.显示管理角色.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.显示管理角色ActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.显示管理角色, new AbsoluteConstraints(10, 530, 110, 30));
        this.jButton38.setFont(new Font("幼圆", 0, 12));
        this.jButton38.setText("修改");
        this.jButton38.setToolTipText("<html>\n修改角色信息<strong><font color=\"#FF0000\">文本框不可留空</font></strong><strong>");
        this.jButton38.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton38ActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.jButton38, new AbsoluteConstraints(770, 580, 100, 40));
        this.删除角色.setFont(new Font("幼圆", 0, 12));
        this.删除角色.setText("删除角色");
        this.删除角色.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除角色ActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.删除角色, new AbsoluteConstraints(490, 500, 130, 30));
        this.角色昵称.setFont(new Font("幼圆", 0, 15));
        this.角色昵称.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.角色昵称ActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.角色昵称, new AbsoluteConstraints(60, 590, 70, 30));
        this.等级.setFont(new Font("幼圆", 0, 15));
        this.等级.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.等级ActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.等级, new AbsoluteConstraints(140, 590, 40, 30));
        this.力量.setFont(new Font("幼圆", 0, 15));
        this.力量.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.力量ActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.力量, new AbsoluteConstraints(190, 590, 40, 30));
        this.敏捷.setFont(new Font("幼圆", 0, 15));
        this.敏捷.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.敏捷ActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.敏捷, new AbsoluteConstraints(250, 590, 40, 30));
        this.智力.setFont(new Font("幼圆", 0, 15));
        this.智力.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.智力ActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.智力, new AbsoluteConstraints(310, 590, 40, 30));
        this.运气.setFont(new Font("幼圆", 0, 15));
        this.运气.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.运气ActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.运气, new AbsoluteConstraints(370, 590, 40, 30));
        this.HP.setFont(new Font("幼圆", 0, 15));
        this.HP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.HPActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.HP, new AbsoluteConstraints(420, 590, 50, 30));
        this.MP.setFont(new Font("幼圆", 0, 15));
        this.MP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.MPActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.MP, new AbsoluteConstraints(480, 590, 50, 30));
        this.金币1.setFont(new Font("幼圆", 0, 12));
        this.金币1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.金币1ActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.金币1, new AbsoluteConstraints(540, 590, 100, 30));
        this.地图.setFont(new Font("幼圆", 0, 12));
        this.地图.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.地图ActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.地图, new AbsoluteConstraints(650, 590, 110, 30));
        this.GM.setFont(new Font("幼圆", 0, 15));
        this.GM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.GMActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.GM, new AbsoluteConstraints(690, 540, 60, 30));
        this.jLabel182.setFont(new Font("幼圆", 0, 12));
        this.jLabel182.setText("GM等级；");
        this.角色信息1.add(this.jLabel182, new AbsoluteConstraints(630, 550, -1, -1));
        this.jLabel183.setFont(new Font("幼圆", 0, 12));
        this.jLabel183.setText("角色ID；");
        this.角色信息1.add(this.jLabel183, new AbsoluteConstraints(10, 570, -1, -1));
        this.jLabel184.setFont(new Font("幼圆", 0, 12));
        this.jLabel184.setText("等级；");
        this.角色信息1.add(this.jLabel184, new AbsoluteConstraints(140, 570, -1, -1));
        this.jLabel185.setFont(new Font("幼圆", 0, 12));
        this.jLabel185.setText("力量；");
        this.角色信息1.add(this.jLabel185, new AbsoluteConstraints(190, 570, -1, -1));
        this.jLabel186.setFont(new Font("幼圆", 0, 12));
        this.jLabel186.setText("敏捷；");
        this.角色信息1.add(this.jLabel186, new AbsoluteConstraints(250, 570, -1, -1));
        this.jLabel187.setFont(new Font("幼圆", 0, 12));
        this.jLabel187.setText("智力；");
        this.角色信息1.add(this.jLabel187, new AbsoluteConstraints(370, 570, -1, -1));
        this.jLabel189.setFont(new Font("幼圆", 0, 12));
        this.jLabel189.setText("MaxHP；");
        this.角色信息1.add(this.jLabel189, new AbsoluteConstraints(420, 570, -1, -1));
        this.jLabel190.setFont(new Font("幼圆", 0, 12));
        this.jLabel190.setText("MaxMP；");
        this.角色信息1.add(this.jLabel190, new AbsoluteConstraints(480, 570, -1, -1));
        this.jLabel191.setFont(new Font("幼圆", 0, 12));
        this.jLabel191.setText("金币；");
        this.角色信息1.add(this.jLabel191, new AbsoluteConstraints(540, 570, -1, -1));
        this.jLabel192.setFont(new Font("幼圆", 0, 12));
        this.jLabel192.setText("发型/脸型");
        this.角色信息1.add(this.jLabel192, new AbsoluteConstraints(630, 500, -1, 30));
        this.jLabel193.setFont(new Font("幼圆", 0, 12));
        this.jLabel193.setText("角色昵称；");
        this.角色信息1.add(this.jLabel193, new AbsoluteConstraints(60, 570, -1, -1));
        this.角色ID.setEditable(false);
        this.角色ID.setFont(new Font("幼圆", 0, 15));
        this.角色ID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.角色IDActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.角色ID, new AbsoluteConstraints(10, 590, 40, 30));
        this.卡号自救1.setFont(new Font("幼圆", 0, 15));
        this.卡号自救1.setText("卡发/脸型解救");
        this.卡号自救1.setToolTipText("<html>\n角色卡<strong><font color=\"#FF0000\">发型</font></strong><strong>或者<strong><font color=\"#FF0000\">脸型</font></strong><strong>时候可用此功能\n");
        this.卡号自救1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.卡号自救1ActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.卡号自救1, new AbsoluteConstraints(230, 500, 130, 30));
        this.卡号自救2.setFont(new Font("幼圆", 0, 15));
        this.卡号自救2.setText("卡物品解救");
        this.卡号自救2.setToolTipText("<html>\n次卡号解救会对角色进行<strong><font color=\"#FF0000\">清空物品</font></strong><strong>处理");
        this.卡号自救2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.卡号自救2ActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.卡号自救2, new AbsoluteConstraints(230, 530, 130, 30));
        this.jLabel203.setFont(new Font("幼圆", 0, 12));
        this.jLabel203.setText("运气；");
        this.角色信息1.add(this.jLabel203, new AbsoluteConstraints(310, 570, -1, -1));
        this.查看技能.setFont(new Font("幼圆", 0, 15));
        this.查看技能.setText("查看角色技能");
        this.查看技能.setToolTipText("<html>\n选择角色后，点击此功能，可查看角色所有<strong><font color=\"#FF0000\">技能信息</font></strong><strong>");
        this.查看技能.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.查看技能ActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.查看技能, new AbsoluteConstraints(490, 530, 130, 30));
        this.查看背包.setFont(new Font("幼圆", 0, 15));
        this.查看背包.setText("查看角色背包");
        this.查看背包.setToolTipText("<html>\n选择角色后，点击此功能，可查看角色所有<strong><font color=\"#FF0000\">物品信息</font></strong><strong>");
        this.查看背包.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.查看背包ActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.查看背包, new AbsoluteConstraints(360, 530, 130, 30));
        this.卡家族解救.setFont(new Font("幼圆", 0, 15));
        this.卡家族解救.setText("卡家族解救");
        this.卡家族解救.setToolTipText("<html>\n角色卡<strong><font color=\"#FF0000\">发型</font></strong><strong>或者<strong><font color=\"#FF0000\">脸型</font></strong><strong>时候可用此功能\n");
        this.卡家族解救.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.卡家族解救ActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.卡家族解救, new AbsoluteConstraints(360, 500, 130, 30));
        this.脸型.setFont(new Font("幼圆", 0, 15));
        this.脸型.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.脸型ActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.脸型, new AbsoluteConstraints(760, 500, 60, 30));
        this.发型.setFont(new Font("幼圆", 0, 15));
        this.发型.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.发型ActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.发型, new AbsoluteConstraints(690, 500, 60, 30));
        this.jLabel214.setFont(new Font("幼圆", 0, 12));
        this.jLabel214.setText("所在地图；");
        this.角色信息1.add(this.jLabel214, new AbsoluteConstraints(650, 570, -1, -1));
        this.离线角色.setFont(new Font("幼圆", 0, 15));
        this.离线角色.setText("离线角色");
        this.离线角色.setToolTipText("显示所有GM管理员");
        this.离线角色.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.离线角色ActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.离线角色, new AbsoluteConstraints(120, 530, 110, 30));
        this.在线角色.setFont(new Font("幼圆", 0, 15));
        this.在线角色.setText("在线角色");
        this.在线角色.setToolTipText("显示所有GM管理员");
        this.在线角色.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.在线角色ActionPerformed(evt);
            }
        });
        this.角色信息1.add(this.在线角色, new AbsoluteConstraints(120, 500, 110, 30));
        this.显示在线玩家.setFont(new Font("幼圆", 0, 18));
        this.角色信息1.add(this.显示在线玩家, new AbsoluteConstraints(1110, 495, 130, 30));
        this.jTabbedPane8.addTab("角色信息", this.角色信息1);
        this.角色背包.setBackground(new Color(255, 255, 240));
        this.角色背包.setLayout(new AbsoluteLayout());
        this.jTabbedPane5.setBackground(new Color(255, 255, 255));
        this.jTabbedPane5.setFont(new Font("幼圆", 0, 12));
        this.jPanel39.setBorder(BorderFactory.createTitledBorder(null, "角色穿戴装备信息", 2, 2, new Font("幼圆", 0, 24)));
        this.jPanel39.setLayout(new AbsoluteLayout());
        this.角色背包穿戴.setFont(new Font("幼圆", 0, 15));
        this.角色背包穿戴.setModel(new DefaultTableModel(new Object[0][], new String[]{"序号", "物品代码", "物品名字"}) {
            boolean[] canEdit = {false, false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.角色背包穿戴.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane15.setViewportView(this.角色背包穿戴);
        this.jPanel39.add(this.jScrollPane15, new AbsoluteConstraints(10, 40, 860, 480));
        this.背包物品名字1.setEditable(false);
        this.背包物品名字1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.背包物品名字1ActionPerformed(evt);
            }
        });
        this.jPanel39.add(this.背包物品名字1, new AbsoluteConstraints(440, 550, 150, 30));
        this.身上穿戴序号1.setEditable(false);
        this.身上穿戴序号1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.身上穿戴序号1ActionPerformed(evt);
            }
        });
        this.jPanel39.add(this.身上穿戴序号1, new AbsoluteConstraints(220, 550, 110, 30));
        this.背包物品代码1.setEditable(false);
        this.背包物品代码1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.背包物品代码1ActionPerformed(evt);
            }
        });
        this.jPanel39.add(this.背包物品代码1, new AbsoluteConstraints(330, 550, 110, 30));
        this.jLabel276.setFont(new Font("幼圆", 0, 15));
        this.jLabel276.setText("序号；");
        this.jPanel39.add(this.jLabel276, new AbsoluteConstraints(220, 530, -1, 20));
        this.jLabel283.setFont(new Font("幼圆", 0, 15));
        this.jLabel283.setText("物品名字；");
        this.jPanel39.add(this.jLabel283, new AbsoluteConstraints(440, 530, -1, 20));
        this.jLabel287.setFont(new Font("幼圆", 0, 15));
        this.jLabel287.setText("物品代码；");
        this.jPanel39.add(this.jLabel287, new AbsoluteConstraints(330, 530, -1, 20));
        this.删除穿戴装备.setFont(new Font("幼圆", 0, 15));
        this.删除穿戴装备.setText("删除");
        this.删除穿戴装备.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除穿戴装备ActionPerformed(evt);
            }
        });
        this.jPanel39.add(this.删除穿戴装备, new AbsoluteConstraints(600, 550, -1, 30));
        this.jTabbedPane5.addTab("身上穿戴", this.jPanel39);
        this.jPanel40.setBorder(BorderFactory.createTitledBorder(null, "装备背包", 2, 2, new Font("幼圆", 0, 24)));
        this.jPanel40.setLayout(new AbsoluteLayout());
        this.角色装备背包.setFont(new Font("幼圆", 0, 15));
        this.角色装备背包.setModel(new DefaultTableModel(new Object[0][], new String[]{"序号", "物品代码", "物品名字"}) {
            boolean[] canEdit = {false, false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.角色装备背包.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane16.setViewportView(this.角色装备背包);
        this.jPanel40.add(this.jScrollPane16, new AbsoluteConstraints(20, 40, 850, 480));
        this.装备背包物品名字.setEditable(false);
        this.装备背包物品名字.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.装备背包物品名字ActionPerformed(evt);
            }
        });
        this.jPanel40.add(this.装备背包物品名字, new AbsoluteConstraints(440, 550, 150, 30));
        this.装备背包物品序号.setEditable(false);
        this.装备背包物品序号.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.装备背包物品序号ActionPerformed(evt);
            }
        });
        this.jPanel40.add(this.装备背包物品序号, new AbsoluteConstraints(220, 550, 110, 30));
        this.装备背包物品代码.setEditable(false);
        this.装备背包物品代码.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.装备背包物品代码ActionPerformed(evt);
            }
        });
        this.jPanel40.add(this.装备背包物品代码, new AbsoluteConstraints(330, 550, 110, 30));
        this.jLabel288.setFont(new Font("幼圆", 0, 15));
        this.jLabel288.setText("序号；");
        this.jPanel40.add(this.jLabel288, new AbsoluteConstraints(220, 530, -1, 20));
        this.jLabel289.setFont(new Font("幼圆", 0, 15));
        this.jLabel289.setText("物品名字；");
        this.jPanel40.add(this.jLabel289, new AbsoluteConstraints(440, 530, -1, 20));
        this.jLabel290.setFont(new Font("幼圆", 0, 15));
        this.jLabel290.setText("物品代码；");
        this.jPanel40.add(this.jLabel290, new AbsoluteConstraints(330, 530, -1, 20));
        this.删除装备背包.setFont(new Font("幼圆", 0, 15));
        this.删除装备背包.setText("删除");
        this.删除装备背包.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除装备背包ActionPerformed(evt);
            }
        });
        this.jPanel40.add(this.删除装备背包, new AbsoluteConstraints(600, 550, -1, 30));
        this.jTabbedPane5.addTab("装备背包", this.jPanel40);
        this.jPanel41.setBorder(BorderFactory.createTitledBorder(null, "消耗背包", 2, 2, new Font("幼圆", 0, 24)));
        this.jPanel41.setLayout(new AbsoluteLayout());
        this.角色消耗背包.setFont(new Font("幼圆", 0, 15));
        this.角色消耗背包.setModel(new DefaultTableModel(new Object[0][], new String[]{"序号", "物品代码", "物品名字", "物品数量"}) {
            boolean[] canEdit = {false, false, false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.角色消耗背包.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane17.setViewportView(this.角色消耗背包);
        this.jPanel41.add(this.jScrollPane17, new AbsoluteConstraints(10, 30, 860, 490));
        this.消耗背包物品名字.setEditable(false);
        this.消耗背包物品名字.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.消耗背包物品名字ActionPerformed(evt);
            }
        });
        this.jPanel41.add(this.消耗背包物品名字, new AbsoluteConstraints(450, 550, 150, 30));
        this.消耗背包物品序号.setEditable(false);
        this.消耗背包物品序号.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.消耗背包物品序号ActionPerformed(evt);
            }
        });
        this.jPanel41.add(this.消耗背包物品序号, new AbsoluteConstraints(230, 550, 110, 30));
        this.消耗背包物品代码.setEditable(false);
        this.消耗背包物品代码.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.消耗背包物品代码ActionPerformed(evt);
            }
        });
        this.jPanel41.add(this.消耗背包物品代码, new AbsoluteConstraints(340, 550, 110, 30));
        this.jLabel291.setFont(new Font("幼圆", 0, 15));
        this.jLabel291.setText("序号；");
        this.jPanel41.add(this.jLabel291, new AbsoluteConstraints(230, 530, -1, 20));
        this.jLabel292.setFont(new Font("幼圆", 0, 15));
        this.jLabel292.setText("物品名字；");
        this.jPanel41.add(this.jLabel292, new AbsoluteConstraints(450, 530, -1, 20));
        this.jLabel293.setFont(new Font("幼圆", 0, 15));
        this.jLabel293.setText("物品代码；");
        this.jPanel41.add(this.jLabel293, new AbsoluteConstraints(340, 530, -1, 20));
        this.删除消耗背包.setFont(new Font("幼圆", 0, 15));
        this.删除消耗背包.setText("删除");
        this.删除消耗背包.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除消耗背包ActionPerformed(evt);
            }
        });
        this.jPanel41.add(this.删除消耗背包, new AbsoluteConstraints(610, 550, -1, 30));
        this.jTabbedPane5.addTab("消耗背包", this.jPanel41);
        this.jPanel42.setBorder(BorderFactory.createTitledBorder(null, "设置背包", 2, 2, new Font("幼圆", 0, 24)));
        this.jPanel42.setLayout(new AbsoluteLayout());
        this.角色设置背包.setFont(new Font("幼圆", 0, 15));
        this.角色设置背包.setModel(new DefaultTableModel(new Object[0][], new String[]{"序号", "物品代码", "物品名字", "物品数量"}) {
            boolean[] canEdit = {false, false, false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.角色设置背包.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane18.setViewportView(this.角色设置背包);
        this.jPanel42.add(this.jScrollPane18, new AbsoluteConstraints(10, 30, 860, 490));
        this.设置背包物品名字.setEditable(false);
        this.设置背包物品名字.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.设置背包物品名字ActionPerformed(evt);
            }
        });
        this.jPanel42.add(this.设置背包物品名字, new AbsoluteConstraints(450, 550, 150, 30));
        this.设置背包物品序号.setEditable(false);
        this.设置背包物品序号.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.设置背包物品序号ActionPerformed(evt);
            }
        });
        this.jPanel42.add(this.设置背包物品序号, new AbsoluteConstraints(230, 550, 110, 30));
        this.设置背包物品代码.setEditable(false);
        this.设置背包物品代码.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.设置背包物品代码ActionPerformed(evt);
            }
        });
        this.jPanel42.add(this.设置背包物品代码, new AbsoluteConstraints(340, 550, 110, 30));
        this.jLabel294.setFont(new Font("幼圆", 0, 15));
        this.jLabel294.setText("序号；");
        this.jPanel42.add(this.jLabel294, new AbsoluteConstraints(230, 530, -1, 20));
        this.jLabel295.setFont(new Font("幼圆", 0, 15));
        this.jLabel295.setText("物品名字；");
        this.jPanel42.add(this.jLabel295, new AbsoluteConstraints(450, 530, -1, 20));
        this.jLabel296.setFont(new Font("幼圆", 0, 15));
        this.jLabel296.setText("物品代码；");
        this.jPanel42.add(this.jLabel296, new AbsoluteConstraints(340, 530, -1, 20));
        this.删除设置背包.setFont(new Font("幼圆", 0, 15));
        this.删除设置背包.setText("删除");
        this.删除设置背包.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除设置背包ActionPerformed(evt);
            }
        });
        this.jPanel42.add(this.删除设置背包, new AbsoluteConstraints(610, 550, -1, 30));
        this.jTabbedPane5.addTab("设置背包", this.jPanel42);
        this.jPanel43.setBorder(BorderFactory.createTitledBorder(null, "其他背包", 2, 2, new Font("幼圆", 0, 24)));
        this.jPanel43.setLayout(new AbsoluteLayout());
        this.角色其他背包.setFont(new Font("幼圆", 0, 15));
        this.角色其他背包.setModel(new DefaultTableModel(new Object[0][], new String[]{"序号", "物品代码", "物品名字", "物品数量"}) {
            boolean[] canEdit = {false, false, false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.角色其他背包.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane19.setViewportView(this.角色其他背包);
        this.jPanel43.add(this.jScrollPane19, new AbsoluteConstraints(10, 30, 860, 490));
        this.其他背包物品名字.setEditable(false);
        this.其他背包物品名字.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.其他背包物品名字ActionPerformed(evt);
            }
        });
        this.jPanel43.add(this.其他背包物品名字, new AbsoluteConstraints(450, 550, 150, 30));
        this.其他背包物品序号.setEditable(false);
        this.其他背包物品序号.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.其他背包物品序号ActionPerformed(evt);
            }
        });
        this.jPanel43.add(this.其他背包物品序号, new AbsoluteConstraints(230, 550, 110, 30));
        this.其他背包物品代码.setEditable(false);
        this.其他背包物品代码.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.其他背包物品代码ActionPerformed(evt);
            }
        });
        this.jPanel43.add(this.其他背包物品代码, new AbsoluteConstraints(340, 550, 110, 30));
        this.jLabel297.setFont(new Font("幼圆", 0, 15));
        this.jLabel297.setText("序号；");
        this.jPanel43.add(this.jLabel297, new AbsoluteConstraints(230, 530, -1, 20));
        this.jLabel298.setFont(new Font("幼圆", 0, 15));
        this.jLabel298.setText("物品名字；");
        this.jPanel43.add(this.jLabel298, new AbsoluteConstraints(450, 530, -1, 20));
        this.jLabel299.setFont(new Font("幼圆", 0, 15));
        this.jLabel299.setText("物品代码；");
        this.jPanel43.add(this.jLabel299, new AbsoluteConstraints(340, 530, -1, 20));
        this.删除其他背包.setFont(new Font("幼圆", 0, 15));
        this.删除其他背包.setText("删除");
        this.删除其他背包.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除其他背包ActionPerformed(evt);
            }
        });
        this.jPanel43.add(this.删除其他背包, new AbsoluteConstraints(610, 550, -1, 30));
        this.jTabbedPane5.addTab("其他背包", this.jPanel43);
        this.jPanel44.setBorder(BorderFactory.createTitledBorder(null, "特殊背包", 2, 2, new Font("幼圆", 0, 24)));
        this.jPanel44.setLayout(new AbsoluteLayout());
        this.角色特殊背包.setFont(new Font("幼圆", 0, 15));
        this.角色特殊背包.setModel(new DefaultTableModel(new Object[0][], new String[]{"序号", "物品代码", "物品名字", "物品数量"}) {
            boolean[] canEdit = {false, false, false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.角色特殊背包.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane20.setViewportView(this.角色特殊背包);
        this.jPanel44.add(this.jScrollPane20, new AbsoluteConstraints(10, 30, 860, 490));
        this.特殊背包物品名字.setEditable(false);
        this.特殊背包物品名字.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.特殊背包物品名字ActionPerformed(evt);
            }
        });
        this.jPanel44.add(this.特殊背包物品名字, new AbsoluteConstraints(440, 550, 150, 30));
        this.特殊背包物品序号.setEditable(false);
        this.特殊背包物品序号.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.特殊背包物品序号ActionPerformed(evt);
            }
        });
        this.jPanel44.add(this.特殊背包物品序号, new AbsoluteConstraints(220, 550, 110, 30));
        this.特殊背包物品代码.setEditable(false);
        this.特殊背包物品代码.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.特殊背包物品代码ActionPerformed(evt);
            }
        });
        this.jPanel44.add(this.特殊背包物品代码, new AbsoluteConstraints(330, 550, 110, 30));
        this.jLabel300.setFont(new Font("幼圆", 0, 15));
        this.jLabel300.setText("序号；");
        this.jPanel44.add(this.jLabel300, new AbsoluteConstraints(220, 530, -1, 20));
        this.jLabel301.setFont(new Font("幼圆", 0, 15));
        this.jLabel301.setText("物品名字；");
        this.jPanel44.add(this.jLabel301, new AbsoluteConstraints(440, 530, -1, 20));
        this.jLabel302.setFont(new Font("幼圆", 0, 15));
        this.jLabel302.setText("物品代码；");
        this.jPanel44.add(this.jLabel302, new AbsoluteConstraints(330, 530, -1, 20));
        this.删除特殊背包.setFont(new Font("幼圆", 0, 15));
        this.删除特殊背包.setText("删除");
        this.删除特殊背包.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除特殊背包ActionPerformed(evt);
            }
        });
        this.jPanel44.add(this.删除特殊背包, new AbsoluteConstraints(600, 550, -1, 30));
        this.jTabbedPane5.addTab("特殊背包", this.jPanel44);
        this.jPanel45.setBorder(BorderFactory.createTitledBorder(null, "游戏仓库", 2, 2, new Font("幼圆", 0, 24)));
        this.jPanel45.setLayout(new AbsoluteLayout());
        this.角色游戏仓库.setFont(new Font("幼圆", 0, 15));
        this.角色游戏仓库.setModel(new DefaultTableModel(new Object[0][], new String[]{"序号", "物品代码", "物品名字", "物品数量"}) {
            boolean[] canEdit = {false, false, false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.角色游戏仓库.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane21.setViewportView(this.角色游戏仓库);
        this.jPanel45.add(this.jScrollPane21, new AbsoluteConstraints(10, 30, 860, 490));
        this.游戏仓库物品名字.setEditable(false);
        this.游戏仓库物品名字.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.游戏仓库物品名字ActionPerformed(evt);
            }
        });
        this.jPanel45.add(this.游戏仓库物品名字, new AbsoluteConstraints(430, 550, 150, 30));
        this.游戏仓库物品序号.setEditable(false);
        this.游戏仓库物品序号.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.游戏仓库物品序号ActionPerformed(evt);
            }
        });
        this.jPanel45.add(this.游戏仓库物品序号, new AbsoluteConstraints(210, 550, 110, 30));
        this.游戏仓库物品代码.setEditable(false);
        this.游戏仓库物品代码.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.游戏仓库物品代码ActionPerformed(evt);
            }
        });
        this.jPanel45.add(this.游戏仓库物品代码, new AbsoluteConstraints(320, 550, 110, 30));
        this.jLabel303.setFont(new Font("幼圆", 0, 15));
        this.jLabel303.setText("序号；");
        this.jPanel45.add(this.jLabel303, new AbsoluteConstraints(210, 530, -1, 20));
        this.jLabel304.setFont(new Font("幼圆", 0, 15));
        this.jLabel304.setText("物品名字；");
        this.jPanel45.add(this.jLabel304, new AbsoluteConstraints(430, 530, -1, 20));
        this.jLabel305.setFont(new Font("幼圆", 0, 15));
        this.jLabel305.setText("物品代码；");
        this.jPanel45.add(this.jLabel305, new AbsoluteConstraints(320, 530, -1, 20));
        this.删除游戏仓库.setFont(new Font("幼圆", 0, 15));
        this.删除游戏仓库.setText("删除");
        this.删除游戏仓库.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除游戏仓库ActionPerformed(evt);
            }
        });
        this.jPanel45.add(this.删除游戏仓库, new AbsoluteConstraints(590, 550, -1, 30));
        this.jTabbedPane5.addTab("游戏仓库", this.jPanel45);
        this.jPanel46.setBorder(BorderFactory.createTitledBorder(null, "商城仓库", 2, 2, new Font("幼圆", 0, 24)));
        this.jPanel46.setLayout(new AbsoluteLayout());
        this.角色商城仓库.setFont(new Font("幼圆", 0, 15));
        this.角色商城仓库.setModel(new DefaultTableModel(new Object[0][], new String[]{"序号", "物品代码", "物品名字", "物品数量"}) {
            boolean[] canEdit = {false, false, false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.角色商城仓库.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane22.setViewportView(this.角色商城仓库);
        this.jPanel46.add(this.jScrollPane22, new AbsoluteConstraints(10, 30, 860, 490));
        this.商城仓库物品名字.setEditable(false);
        this.商城仓库物品名字.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.商城仓库物品名字ActionPerformed(evt);
            }
        });
        this.jPanel46.add(this.商城仓库物品名字, new AbsoluteConstraints(460, 550, 150, 30));
        this.商城仓库物品序号.setEditable(false);
        this.商城仓库物品序号.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.商城仓库物品序号ActionPerformed(evt);
            }
        });
        this.jPanel46.add(this.商城仓库物品序号, new AbsoluteConstraints(240, 550, 110, 30));
        this.商城仓库物品代码.setEditable(false);
        this.商城仓库物品代码.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.商城仓库物品代码ActionPerformed(evt);
            }
        });
        this.jPanel46.add(this.商城仓库物品代码, new AbsoluteConstraints(350, 550, 110, 30));
        this.jLabel306.setFont(new Font("幼圆", 0, 15));
        this.jLabel306.setText("序号；");
        this.jPanel46.add(this.jLabel306, new AbsoluteConstraints(240, 530, -1, 20));
        this.jLabel307.setFont(new Font("幼圆", 0, 15));
        this.jLabel307.setText("物品名字；");
        this.jPanel46.add(this.jLabel307, new AbsoluteConstraints(460, 530, -1, 20));
        this.jLabel308.setFont(new Font("幼圆", 0, 15));
        this.jLabel308.setText("物品代码；");
        this.jPanel46.add(this.jLabel308, new AbsoluteConstraints(350, 530, -1, 20));
        this.删除商城仓库.setText("删除");
        this.删除商城仓库.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除商城仓库ActionPerformed(evt);
            }
        });
        this.jPanel46.add(this.删除商城仓库, new AbsoluteConstraints(620, 550, -1, 30));
        this.jTabbedPane5.addTab("商城仓库", this.jPanel46);
        this.jPanel48.setBorder(BorderFactory.createTitledBorder(null, "点券拍卖行", 2, 2, new Font("幼圆", 0, 24)));
        this.jPanel48.setLayout(new AbsoluteLayout());
        this.角色点券拍卖行.setFont(new Font("幼圆", 0, 15));
        this.角色点券拍卖行.setModel(new DefaultTableModel(new Object[0][], new String[]{"序号", "物品代码", "物品名字", "物品数量"}) {
            boolean[] canEdit = {false, false, false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.角色点券拍卖行.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane30.setViewportView(this.角色点券拍卖行);
        this.jPanel48.add(this.jScrollPane30, new AbsoluteConstraints(10, 30, 860, 490));
        this.拍卖行物品名字1.setEditable(false);
        this.拍卖行物品名字1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.拍卖行物品名字1ActionPerformed(evt);
            }
        });
        this.jPanel48.add(this.拍卖行物品名字1, new AbsoluteConstraints(460, 550, 150, 30));
        this.角色点券拍卖行序号.setEditable(false);
        this.角色点券拍卖行序号.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.角色点券拍卖行序号ActionPerformed(evt);
            }
        });
        this.jPanel48.add(this.角色点券拍卖行序号, new AbsoluteConstraints(240, 550, 110, 30));
        this.拍卖行物品代码1.setEditable(false);
        this.拍卖行物品代码1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.拍卖行物品代码1ActionPerformed(evt);
            }
        });
        this.jPanel48.add(this.拍卖行物品代码1, new AbsoluteConstraints(350, 550, 110, 30));
        this.jLabel354.setFont(new Font("幼圆", 0, 15));
        this.jLabel354.setText("序号；");
        this.jPanel48.add(this.jLabel354, new AbsoluteConstraints(240, 530, -1, 20));
        this.jLabel355.setFont(new Font("幼圆", 0, 15));
        this.jLabel355.setText("物品名字；");
        this.jPanel48.add(this.jLabel355, new AbsoluteConstraints(460, 530, -1, 20));
        this.jLabel356.setFont(new Font("幼圆", 0, 15));
        this.jLabel356.setText("物品代码；");
        this.jPanel48.add(this.jLabel356, new AbsoluteConstraints(350, 530, -1, 20));
        this.删除拍卖行1.setFont(new Font("幼圆", 0, 15));
        this.删除拍卖行1.setText("删除");
        this.删除拍卖行1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除拍卖行1ActionPerformed(evt);
            }
        });
        this.jPanel48.add(this.删除拍卖行1, new AbsoluteConstraints(620, 550, -1, 30));
        this.jTabbedPane5.addTab("点券拍卖行", this.jPanel48);
        this.jPanel47.setBorder(BorderFactory.createTitledBorder(null, "金币拍卖行", 2, 2, new Font("幼圆", 0, 24)));
        this.jPanel47.setLayout(new AbsoluteLayout());
        this.角色金币拍卖行.setFont(new Font("幼圆", 0, 15));
        this.角色金币拍卖行.setModel(new DefaultTableModel(new Object[0][], new String[]{"序号", "物品代码", "物品名字"}) {
            boolean[] canEdit = {false, false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.角色金币拍卖行.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane23.setViewportView(this.角色金币拍卖行);
        this.jPanel47.add(this.jScrollPane23, new AbsoluteConstraints(10, 30, 860, 490));
        this.拍卖行物品名字.setEditable(false);
        this.拍卖行物品名字.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.拍卖行物品名字ActionPerformed(evt);
            }
        });
        this.jPanel47.add(this.拍卖行物品名字, new AbsoluteConstraints(440, 550, 150, 30));
        this.角色金币拍卖行序号.setEditable(false);
        this.角色金币拍卖行序号.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.角色金币拍卖行序号ActionPerformed(evt);
            }
        });
        this.jPanel47.add(this.角色金币拍卖行序号, new AbsoluteConstraints(220, 550, 110, 30));
        this.拍卖行物品代码.setEditable(false);
        this.拍卖行物品代码.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.拍卖行物品代码ActionPerformed(evt);
            }
        });
        this.jPanel47.add(this.拍卖行物品代码, new AbsoluteConstraints(330, 550, 110, 30));
        this.jLabel309.setFont(new Font("幼圆", 0, 15));
        this.jLabel309.setText("序号；");
        this.jPanel47.add(this.jLabel309, new AbsoluteConstraints(220, 530, -1, 20));
        this.jLabel310.setFont(new Font("幼圆", 0, 15));
        this.jLabel310.setText("物品名字；");
        this.jPanel47.add(this.jLabel310, new AbsoluteConstraints(440, 530, -1, 20));
        this.jLabel311.setFont(new Font("幼圆", 0, 15));
        this.jLabel311.setText("物品代码；");
        this.jPanel47.add(this.jLabel311, new AbsoluteConstraints(330, 530, -1, 20));
        this.删除拍卖行.setFont(new Font("幼圆", 0, 15));
        this.删除拍卖行.setText("删除");
        this.删除拍卖行.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除拍卖行ActionPerformed(evt);
            }
        });
        this.jPanel47.add(this.删除拍卖行, new AbsoluteConstraints(600, 550, -1, 30));
        this.jTabbedPane5.addTab("金币拍卖行", this.jPanel47);
        this.角色背包.add(this.jTabbedPane5, new AbsoluteConstraints(0, 0, 870, 640));
        this.jTabbedPane8.addTab("角色道具信息", this.角色背包);
        this.技能.setBackground(new Color(255, 255, 240));
        this.技能.setBorder(BorderFactory.createTitledBorder(null, "角色技能", 2, 2, new Font("幼圆", 0, 24)));
        this.技能.setLayout(new AbsoluteLayout());
        this.技能信息.setFont(new Font("幼圆", 0, 15));
        this.技能信息.setModel(new DefaultTableModel(new Object[0][], new String[]{"序号", "技能名字", "技能代码", "目前等级", "最高等级"}) {
            boolean[] canEdit = {false, false, false, false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.技能信息.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane14.setViewportView(this.技能信息);
        this.技能.add(this.jScrollPane14, new AbsoluteConstraints(10, 30, 870, 430));
        this.技能代码.setEditable(false);
        this.技能代码.setFont(new Font("幼圆", 0, 15));
        this.技能.add(this.技能代码, new AbsoluteConstraints(390, 490, 120, 30));
        this.技能目前等级.setFont(new Font("幼圆", 0, 15));
        this.技能.add(this.技能目前等级, new AbsoluteConstraints(520, 490, 120, 30));
        this.技能最高等级.setFont(new Font("幼圆", 0, 15));
        this.技能.add(this.技能最高等级, new AbsoluteConstraints(650, 490, 120, 30));
        this.技能名字.setEditable(false);
        this.技能名字.setFont(new Font("幼圆", 0, 15));
        this.技能名字.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.技能名字ActionPerformed(evt);
            }
        });
        this.技能.add(this.技能名字, new AbsoluteConstraints(260, 490, 120, 30));
        this.jLabel86.setFont(new Font("幼圆", 0, 18));
        this.jLabel86.setText("技能代码；");
        this.技能.add(this.jLabel86, new AbsoluteConstraints(390, 470, -1, -1));
        this.jLabel89.setFont(new Font("幼圆", 0, 18));
        this.jLabel89.setText("目前等级；");
        this.技能.add(this.jLabel89, new AbsoluteConstraints(520, 470, -1, -1));
        this.jLabel107.setFont(new Font("幼圆", 0, 18));
        this.jLabel107.setText("最高等级；");
        this.技能.add(this.jLabel107, new AbsoluteConstraints(650, 470, -1, -1));
        this.修改技能.setFont(new Font("幼圆", 0, 15));
        this.修改技能.setText("修改");
        this.修改技能.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.修改技能ActionPerformed(evt);
            }
        });
        this.技能.add(this.修改技能, new AbsoluteConstraints(260, 530, 120, 40));
        this.技能序号.setEditable(false);
        this.技能序号.setFont(new Font("幼圆", 0, 15));
        this.技能.add(this.技能序号, new AbsoluteConstraints(170, 490, 80, 30));
        this.jLabel188.setFont(new Font("幼圆", 0, 18));
        this.jLabel188.setText("技能名字；");
        this.技能.add(this.jLabel188, new AbsoluteConstraints(260, 470, -1, -1));
        this.jLabel204.setFont(new Font("幼圆", 0, 18));
        this.jLabel204.setText("序号；");
        this.技能.add(this.jLabel204, new AbsoluteConstraints(170, 470, -1, -1));
        this.jLabel205.setFont(new Font("幼圆", 0, 24));
        this.jLabel205.setText("提示;技能无法超出正常范围值。");
        this.技能.add(this.jLabel205, new AbsoluteConstraints(260, 580, 360, 30));
        this.删除技能.setFont(new Font("幼圆", 0, 15));
        this.删除技能.setText("删除");
        this.删除技能.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除技能ActionPerformed(evt);
            }
        });
        this.技能.add(this.删除技能, new AbsoluteConstraints(520, 530, 120, 40));
        this.修改技能1.setFont(new Font("幼圆", 0, 15));
        this.修改技能1.setText("刷新");
        this.修改技能1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.修改技能1ActionPerformed(evt);
            }
        });
        this.技能.add(this.修改技能1, new AbsoluteConstraints(390, 530, 120, 40));
        this.jTabbedPane8.addTab("角色技能信息", this.技能);
        this.jPanel50.setBackground(new Color(255, 255, 240));
        this.家族信息.setFont(new Font("幼圆", 0, 15));
        this.家族信息.setModel(new DefaultTableModel(new Object[0][], new String[]{"家族ID", "家族名称", "族长/角色ID", "成员2", "成员3", "成员4", "成员5", "家族GP"}) {
            boolean[] canEdit = {false, false, false, false, false, false, false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.家族信息.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane24.setViewportView(this.家族信息);
        this.刷新家族信息.setText("刷新家族信息");
        this.刷新家族信息.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.刷新家族信息ActionPerformed(evt);
            }
        });
        this.jLabel194.setFont(new Font("幼圆", 0, 12));
        this.jLabel194.setText("家族ID；");
        this.家族ID.setEditable(false);
        this.家族ID.setFont(new Font("幼圆", 0, 15));
        this.家族ID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.家族IDActionPerformed(evt);
            }
        });
        this.家族名称.setFont(new Font("幼圆", 0, 15));
        this.家族名称.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.家族名称ActionPerformed(evt);
            }
        });
        this.jLabel195.setFont(new Font("幼圆", 0, 12));
        this.jLabel195.setText("家族名称；");
        this.家族族长.setEditable(false);
        this.家族族长.setFont(new Font("幼圆", 0, 15));
        this.家族族长.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.家族族长ActionPerformed(evt);
            }
        });
        this.jLabel196.setFont(new Font("幼圆", 0, 12));
        this.jLabel196.setText("家族族长；");
        this.jLabel198.setFont(new Font("幼圆", 0, 12));
        this.jLabel198.setText("家族成员2；");
        this.家族成员2.setFont(new Font("幼圆", 0, 15));
        this.家族成员2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.家族成员2ActionPerformed(evt);
            }
        });
        this.jLabel199.setFont(new Font("幼圆", 0, 12));
        this.jLabel199.setText("家族成员3；");
        this.家族成员3.setFont(new Font("幼圆", 0, 15));
        this.家族成员3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.家族成员3ActionPerformed(evt);
            }
        });
        this.jLabel200.setFont(new Font("幼圆", 0, 12));
        this.jLabel200.setText("家族成员4；");
        this.家族成员4.setFont(new Font("幼圆", 0, 15));
        this.家族成员4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.家族成员4ActionPerformed(evt);
            }
        });
        this.jLabel313.setFont(new Font("幼圆", 0, 12));
        this.jLabel313.setText("家族成员5；");
        this.家族成员5.setFont(new Font("幼圆", 0, 15));
        this.家族成员5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.家族成员5ActionPerformed(evt);
            }
        });
        this.jLabel314.setFont(new Font("幼圆", 0, 12));
        this.jLabel314.setText("家族GP；");
        this.家族GP.setFont(new Font("幼圆", 0, 15));
        this.家族GP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.家族GPActionPerformed(evt);
            }
        });
        this.jButton34.setText("更改家族GP点数");
        this.jButton34.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton34ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel50Layout = new GroupLayout(this.jPanel50);
        this.jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(jPanel50Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel50Layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane24)).addGroup(Alignment.TRAILING, jPanel50Layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.刷新家族信息, -2, 206, -2).addGap(26, 26, 26).addComponent(this.jButton34, -2, 176, -2).addGap(280, 280, 280)).addGroup(jPanel50Layout.createSequentialGroup().addGap(94, 94, 94).addGroup(jPanel50Layout.createParallelGroup(Alignment.TRAILING, false).addComponent(this.jLabel194, -1, -1, 32767).addComponent(this.家族ID, -2, 48, -2)).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(jPanel50Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel50Layout.createSequentialGroup().addComponent(this.家族名称, -2, 87, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.家族族长, -2, 82, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.家族成员2, -2, 67, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.家族成员3, -2, 67, -2).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.家族成员4, -2, 67, -2)).addGroup(jPanel50Layout.createSequentialGroup().addComponent(this.jLabel195).addGap(35, 35, 35).addComponent(this.jLabel196).addGap(26, 26, 26).addComponent(this.jLabel198).addGap(18, 18, 18).addComponent(this.jLabel199).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.jLabel200))).addGap(18, 18, 18).addGroup(jPanel50Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel50Layout.createSequentialGroup().addComponent(this.家族成员5, -2, 67, -2).addGap(18, 18, 18).addComponent(this.家族GP, -2, 83, -2)).addGroup(jPanel50Layout.createSequentialGroup().addComponent(this.jLabel313).addGap(18, 18, 18).addComponent(this.jLabel314))).addContainerGap(-1, 32767)));
        jPanel50Layout.setVerticalGroup(jPanel50Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel50Layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane24, -1, 485, 32767).addGroup(jPanel50Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel50Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel194).addComponent(this.jLabel195).addComponent(this.jLabel196).addComponent(this.jLabel198).addComponent(this.jLabel199).addComponent(this.jLabel200).addComponent(this.jLabel313).addComponent(this.jLabel314)).addGroup(jPanel50Layout.createSequentialGroup().addGap(20, 20, 20).addGroup(jPanel50Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.家族ID, -2, 30, -2).addComponent(this.家族名称, -2, 30, -2).addComponent(this.家族族长, -2, 30, -2).addComponent(this.家族成员2, -2, 30, -2).addComponent(this.家族成员3, -2, 30, -2).addComponent(this.家族成员4, -2, 30, -2).addComponent(this.家族成员5, -2, 30, -2).addComponent(this.家族GP, -2, 30, -2)))).addGap(22, 22, 22).addGroup(jPanel50Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.刷新家族信息, -2, 40, -2).addComponent(this.jButton34, -2, 40, -2)).addGap(20, 20, 20)));
        this.jTabbedPane8.addTab("游戏家族", this.jPanel50);
        this.jPanel65.setBackground(new Color(255, 255, 240));
        this.jPanel65.setBorder(BorderFactory.createTitledBorder("MAC/IP封禁"));
        this.封IP.setFont(new Font("幼圆", 0, 15));
        this.封IP.setModel(new DefaultTableModel(new Object[0][], new String[]{"序列号", "IP地址"}) {
            boolean[] canEdit = {false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.jScrollPane107.setViewportView(this.封IP);
        this.封MAC.setFont(new Font("幼圆", 0, 15));
        this.封MAC.setModel(new DefaultTableModel(new Object[0][], new String[]{"序列号", "MAC地址"}) {
            boolean[] canEdit = {false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.jScrollPane108.setViewportView(this.封MAC);
        this.刷新封IP.setFont(new Font("幼圆", 0, 15));
        this.刷新封IP.setText("刷新");
        this.刷新封IP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.刷新封IPActionPerformed(evt);
            }
        });
        this.刷新封MAC.setFont(new Font("幼圆", 0, 15));
        this.刷新封MAC.setText("刷新");
        this.刷新封MAC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.刷新封MACActionPerformed(evt);
            }
        });
        this.删除MAC.setFont(new Font("幼圆", 0, 15));
        this.删除MAC.setText("删除");
        this.删除MAC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除MACActionPerformed(evt);
            }
        });
        this.删除IP.setFont(new Font("幼圆", 0, 15));
        this.删除IP.setText("删除");
        this.删除IP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除IPActionPerformed(evt);
            }
        });
        this.jLabel346.setFont(new Font("幼圆", 0, 15));
        this.jLabel346.setText("序号；");
        this.jLabel347.setFont(new Font("幼圆", 0, 15));
        this.jLabel347.setText("序号；");
        final GroupLayout jPanel65Layout = new GroupLayout(this.jPanel65);
        this.jPanel65.setLayout(jPanel65Layout);
        jPanel65Layout.setHorizontalGroup(jPanel65Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel65Layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.jLabel347).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.删MAC代码, -2, 100, -2).addGap(10, 10, 10).addComponent(this.删除MAC, -2, 70, -2).addGap(0, 0, 0).addComponent(this.刷新封MAC, -2, 70, -2).addGap(101, 101, 101).addComponent(this.jLabel346).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.删除IP代码, -2, 100, -2).addGap(10, 10, 10).addComponent(this.删除IP, -2, 70, -2).addGap(0, 0, 0).addComponent(this.刷新封IP, -2, 70, -2).addGap(160, 160, 160)).addGroup(jPanel65Layout.createSequentialGroup().addComponent(this.jScrollPane108, -2, 437, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jScrollPane107, -2, 0, 32767).addContainerGap()));
        jPanel65Layout.setVerticalGroup(jPanel65Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel65Layout.createSequentialGroup().addGroup(jPanel65Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.jScrollPane108, -1, 506, 32767).addComponent(this.jScrollPane107)).addGap(20, 20, 20).addGroup(jPanel65Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel65Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.删MAC代码, -2, 30, -2).addComponent(this.jLabel347, -2, 20, -2)).addComponent(this.删除MAC, -2, 30, -2).addComponent(this.刷新封MAC, -2, 30, -2).addGroup(jPanel65Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.删除IP代码, -2, 30, -2).addComponent(this.jLabel346, -2, 20, -2)).addComponent(this.删除IP, -2, 30, -2).addComponent(this.刷新封IP, -2, 30, -2)).addContainerGap(-1, 32767)));
        this.jTabbedPane8.addTab("MAC/IP封禁", this.jPanel65);
        final GroupLayout jPanel21Layout = new GroupLayout(this.jPanel21);
        this.jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(jPanel21Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jTabbedPane8));
        jPanel21Layout.setVerticalGroup(jPanel21Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel21Layout.createSequentialGroup().addContainerGap().addComponent(this.jTabbedPane8, -2, 664, -2).addContainerGap(-1, 32767)));
        this.jTabbedPane2.addTab("人物信息", this.jPanel21);
        this.jPanel22.setBackground(new Color(255, 255, 240));
        this.jTabbedPane3.setBackground(new Color(255, 255, 255));
        this.jPanel24.setBackground(new Color(255, 255, 240));
        this.主题馆.setFont(new Font("幼圆", 0, 12));
        this.主题馆.setText("主题馆");
        this.主题馆.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">主题馆</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">主体馆</font></strong>分类下的所有商品<br> ");
        this.主题馆.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.主题馆ActionPerformed(evt);
            }
        });
        this.读取热销产品.setFont(new Font("幼圆", 0, 12));
        this.读取热销产品.setText("热销产品");
        this.读取热销产品.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">热销产品</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">热销产品</font></strong>分类下的所有商品<br> ");
        this.读取热销产品.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.读取热销产品ActionPerformed(evt);
            }
        });
        this.活动.setFont(new Font("幼圆", 0, 12));
        this.活动.setText("活动");
        this.活动.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">活动</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">活动</font></strong>分类下的所有商品<br> ");
        this.活动.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.活动ActionPerformed(evt);
            }
        });
        this.jButton9.setFont(new Font("幼圆", 0, 12));
        this.jButton9.setText("每日特卖");
        this.jButton9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton9ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel24Layout = new GroupLayout(this.jPanel24);
        this.jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(jPanel24Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel24Layout.createSequentialGroup().addComponent(this.读取热销产品, -2, 100, -2).addGap(0, 0, 0).addComponent(this.主题馆, -2, 100, -2).addGap(0, 0, 0).addComponent(this.活动, -2, 100, -2).addGap(0, 0, 0).addComponent(this.jButton9, -2, 100, -2)));
        jPanel24Layout.setVerticalGroup(jPanel24Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel24Layout.createSequentialGroup().addGroup(jPanel24Layout.createParallelGroup(Alignment.LEADING).addComponent(this.读取热销产品, -1, -1, 32767).addComponent(this.主题馆, -1, -1, 32767).addComponent(this.活动, -1, -1, 32767).addComponent(this.jButton9, -1, -1, 32767)).addContainerGap()));
        this.jTabbedPane3.addTab("热销产品", this.jPanel24);
        this.jPanel25.setLayout(new AbsoluteLayout());
        this.帽子.setFont(new Font("幼圆", 0, 12));
        this.帽子.setText("帽子");
        this.帽子.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">帽子</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">帽子</font></strong>分类下的所有商品<br> ");
        this.帽子.setPreferredSize(new Dimension(30, 27));
        this.帽子.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.帽子ActionPerformed(evt);
            }
        });
        this.jPanel25.add(this.帽子, new AbsoluteConstraints(0, 0, 70, 20));
        this.脸饰.setFont(new Font("幼圆", 0, 12));
        this.脸饰.setText("脸饰");
        this.脸饰.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">脸饰</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">脸饰</font></strong>分类下的所有商品<br> ");
        this.脸饰.setPreferredSize(new Dimension(30, 27));
        this.脸饰.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.脸饰ActionPerformed(evt);
            }
        });
        this.jPanel25.add(this.脸饰, new AbsoluteConstraints(330, 0, 60, 20));
        this.眼饰.setFont(new Font("幼圆", 0, 12));
        this.眼饰.setText("眼饰");
        this.眼饰.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">眼饰</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">眼饰</font></strong>分类下的所有商品<br> ");
        this.眼饰.setPreferredSize(new Dimension(30, 27));
        this.眼饰.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.眼饰ActionPerformed(evt);
            }
        });
        this.jPanel25.add(this.眼饰, new AbsoluteConstraints(570, 0, 60, 20));
        this.长袍.setFont(new Font("幼圆", 0, 12));
        this.长袍.setText("长袍");
        this.长袍.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">长袍</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">长袍</font></strong>分类下的所有商品<br> ");
        this.长袍.setPreferredSize(new Dimension(30, 27));
        this.长袍.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.长袍ActionPerformed(evt);
            }
        });
        this.jPanel25.add(this.长袍, new AbsoluteConstraints(270, 0, 60, 20));
        this.上衣.setFont(new Font("幼圆", 0, 12));
        this.上衣.setText("上衣");
        this.上衣.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">上衣</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">上衣</font></strong>分类下的所有商品<br> ");
        this.上衣.setPreferredSize(new Dimension(30, 27));
        this.上衣.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.上衣ActionPerformed(evt);
            }
        });
        this.jPanel25.add(this.上衣, new AbsoluteConstraints(750, 0, 60, 20));
        this.裙裤.setFont(new Font("幼圆", 0, 12));
        this.裙裤.setText("裙裤");
        this.裙裤.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">裙裤</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">裙裤</font></strong>分类下的所有商品<br> ");
        this.裙裤.setPreferredSize(new Dimension(30, 27));
        this.裙裤.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.裙裤ActionPerformed(evt);
            }
        });
        this.jPanel25.add(this.裙裤, new AbsoluteConstraints(70, 0, 70, 20));
        this.鞋子.setFont(new Font("幼圆", 0, 12));
        this.鞋子.setText("鞋子");
        this.鞋子.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">鞋子</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">鞋子</font></strong>分类下的所有商品<br> ");
        this.鞋子.setPreferredSize(new Dimension(30, 27));
        this.鞋子.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.鞋子ActionPerformed(evt);
            }
        });
        this.jPanel25.add(this.鞋子, new AbsoluteConstraints(390, 0, 60, 20));
        this.手套.setFont(new Font("幼圆", 0, 12));
        this.手套.setText("手套");
        this.手套.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">手套</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">手套</font></strong>分类下的所有商品<br> ");
        this.手套.setPreferredSize(new Dimension(30, 27));
        this.手套.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.手套ActionPerformed(evt);
            }
        });
        this.jPanel25.add(this.手套, new AbsoluteConstraints(630, 0, 60, 20));
        this.武器.setFont(new Font("幼圆", 0, 12));
        this.武器.setText("武器");
        this.武器.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">武器</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">武器</font></strong>分类下的所有商品<br> ");
        this.武器.setPreferredSize(new Dimension(30, 27));
        this.武器.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.武器ActionPerformed(evt);
            }
        });
        this.jPanel25.add(this.武器, new AbsoluteConstraints(690, 0, 60, 20));
        this.戒指.setFont(new Font("幼圆", 0, 12));
        this.戒指.setText("戒指");
        this.戒指.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">戒指</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">戒指</font></strong>分类下的所有商品<br> ");
        this.戒指.setPreferredSize(new Dimension(30, 27));
        this.戒指.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.戒指ActionPerformed(evt);
            }
        });
        this.jPanel25.add(this.戒指, new AbsoluteConstraints(510, 0, 60, 20));
        this.飞镖.setFont(new Font("幼圆", 0, 12));
        this.飞镖.setText("飞镖");
        this.飞镖.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">飞镖</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">飞镖</font></strong>分类下的所有商品<br> ");
        this.飞镖.setPreferredSize(new Dimension(30, 27));
        this.飞镖.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.飞镖ActionPerformed(evt);
            }
        });
        this.jPanel25.add(this.飞镖, new AbsoluteConstraints(210, 0, 60, 20));
        this.披风.setFont(new Font("幼圆", 0, 12));
        this.披风.setText("披风");
        this.披风.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">披风</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">披风</font></strong>分类下的所有商品<br> ");
        this.披风.setPreferredSize(new Dimension(30, 27));
        this.披风.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.披风ActionPerformed(evt);
            }
        });
        this.jPanel25.add(this.披风, new AbsoluteConstraints(140, 0, 70, 20));
        this.骑宠.setFont(new Font("幼圆", 0, 12));
        this.骑宠.setText("骑宠");
        this.骑宠.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">骑宠</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">骑宠</font></strong>分类下的所有商品<br> ");
        this.骑宠.setPreferredSize(new Dimension(30, 27));
        this.骑宠.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.骑宠ActionPerformed(evt);
            }
        });
        this.jPanel25.add(this.骑宠, new AbsoluteConstraints(450, 0, 60, 20));
        this.jTabbedPane3.addTab("装备", this.jPanel25);
        this.喜庆物品.setFont(new Font("幼圆", 0, 12));
        this.喜庆物品.setText("喜庆物品");
        this.喜庆物品.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">喜庆物品</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">喜庆物品</font></strong>分类下的所有商品<br> ");
        this.喜庆物品.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.喜庆物品ActionPerformed(evt);
            }
        });
        this.通讯物品.setFont(new Font("幼圆", 0, 12));
        this.通讯物品.setText("通讯物品");
        this.通讯物品.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">通讯物品</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">通讯物品</font></strong>分类下的所有商品<br> ");
        this.通讯物品.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.通讯物品ActionPerformed(evt);
            }
        });
        this.卷轴.setFont(new Font("幼圆", 0, 12));
        this.卷轴.setText("卷轴");
        this.卷轴.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">卷轴</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">卷轴</font></strong>分类下的所有商品<br> ");
        this.卷轴.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.卷轴ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel26Layout = new GroupLayout(this.jPanel26);
        this.jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(jPanel26Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel26Layout.createSequentialGroup().addComponent(this.喜庆物品, -2, 100, -2).addGap(0, 0, 0).addComponent(this.通讯物品, -2, 100, -2).addGap(0, 0, 0).addComponent(this.卷轴, -2, 100, -2)));
        jPanel26Layout.setVerticalGroup(jPanel26Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel26Layout.createSequentialGroup().addGroup(jPanel26Layout.createParallelGroup(Alignment.TRAILING, false).addComponent(this.通讯物品, Alignment.LEADING, -2, 0, 32767).addComponent(this.喜庆物品, Alignment.LEADING, -2, 17, -2).addComponent(this.卷轴, -2, 0, 32767)).addGap(18, 18, 18)));
        this.jTabbedPane3.addTab("消耗", this.jPanel26);
        this.jPanel29.setLayout(new AbsoluteLayout());
        this.会员卡.setFont(new Font("幼圆", 0, 12));
        this.会员卡.setText("会员卡");
        this.会员卡.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">会员卡</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">会员卡</font></strong>分类下的所有商品<br> ");
        this.会员卡.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.会员卡ActionPerformed(evt);
            }
        });
        this.jPanel29.add(this.会员卡, new AbsoluteConstraints(0, 0, 100, 20));
        this.表情.setFont(new Font("幼圆", 0, 12));
        this.表情.setText("表情");
        this.表情.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">表情</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">表情</font></strong>分类下的所有商品<br> ");
        this.表情.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.表情ActionPerformed(evt);
            }
        });
        this.jPanel29.add(this.表情, new AbsoluteConstraints(100, 0, 100, 20));
        this.个人商店.setFont(new Font("幼圆", 0, 12));
        this.个人商店.setText("个人商店");
        this.个人商店.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">个人商店</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">个人商店</font></strong>分类下的所有商品<br> ");
        this.个人商店.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.个人商店ActionPerformed(evt);
            }
        });
        this.jPanel29.add(this.个人商店, new AbsoluteConstraints(200, 0, 100, 20));
        this.纪念日.setFont(new Font("幼圆", 0, 12));
        this.纪念日.setText("纪念日");
        this.纪念日.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.纪念日ActionPerformed(evt);
            }
        });
        this.jPanel29.add(this.纪念日, new AbsoluteConstraints(500, 0, 100, 20));
        this.游戏.setFont(new Font("幼圆", 0, 12));
        this.游戏.setText("游戏");
        this.游戏.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">游戏</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">游戏</font></strong>分类下的所有商品<br> ");
        this.游戏.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.游戏ActionPerformed(evt);
            }
        });
        this.jPanel29.add(this.游戏, new AbsoluteConstraints(400, 0, 100, 20));
        this.效果.setFont(new Font("幼圆", 0, 12));
        this.效果.setText("效果");
        this.效果.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">效果</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">效果</font></strong>分类下的所有商品<br> ");
        this.效果.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.效果ActionPerformed(evt);
            }
        });
        this.jPanel29.add(this.效果, new AbsoluteConstraints(300, 0, 100, 20));
        this.jTabbedPane3.addTab("其他", this.jPanel29);
        this.宠物.setFont(new Font("幼圆", 0, 12));
        this.宠物.setText("宠物");
        this.宠物.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">宠物</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">宠物</font></strong>分类下的所有商品<br> ");
        this.宠物.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.宠物ActionPerformed(evt);
            }
        });
        this.宠物服饰.setFont(new Font("幼圆", 0, 12));
        this.宠物服饰.setText("宠物服饰");
        this.宠物服饰.setToolTipText("<html>\n<strong><font color=\"#FF0000\">点击后；</font></strong><br> \n可在<strong><font color=\"#0000E3\">宠物服饰</font></strong>分类下添加商品<br> \n显示<strong><font color=\"#0000E3\">宠物服饰</font></strong>分类下的所有商品<br> ");
        this.宠物服饰.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.宠物服饰ActionPerformed(evt);
            }
        });
        this.其他.setFont(new Font("幼圆", 0, 12));
        this.其他.setText("其他");
        this.其他.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.其他ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel31Layout = new GroupLayout(this.jPanel31);
        this.jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(jPanel31Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel31Layout.createSequentialGroup().addComponent(this.宠物, -2, 100, -2).addGap(0, 0, 0).addComponent(this.宠物服饰, -2, 100, -2).addGap(0, 0, 0).addComponent(this.其他, -2, 100, -2)));
        jPanel31Layout.setVerticalGroup(jPanel31Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel31Layout.createSequentialGroup().addGroup(jPanel31Layout.createParallelGroup(Alignment.TRAILING, false).addComponent(this.宠物服饰, Alignment.LEADING, -2, 0, 32767).addComponent(this.宠物, Alignment.LEADING, -2, 17, -2).addComponent(this.其他, -2, 0, 32767)).addGap(18, 18, 18)));
        this.jTabbedPane3.addTab("宠物", this.jPanel31);
        this.charTable.setFont(new Font("幼圆", 0, 15));
        this.charTable.setModel(new DefaultTableModel(new Object[0][], new String[]{"商品编码", "物品代码", "道具名称", "数量", "价格", "限时/天", "出售状态", "上/下架", "已售出", "库存", "反馈/%", "每日限购"}) {
            Class[] types = {String.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class};
            boolean[] canEdit = {false, false, false, false, false, false, false, false, false, false, false, false};

            @Override
            public Class getColumnClass(final int columnIndex) {
                return this.types[columnIndex];
            }

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.charTable.setToolTipText("");
        this.charTable.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane6.setViewportView(this.charTable);
        this.jPanel33.setBackground(new Color(255, 255, 240));
        this.jPanel33.setBorder(BorderFactory.createTitledBorder(null, "添加值", 2, 2, new Font("幼圆", 0, 18)));
        this.jPanel33.setLayout(new AbsoluteLayout());
        this.商品数量.setFont(new Font("幼圆", 0, 12));
        this.jPanel33.add(this.商品数量, new AbsoluteConstraints(235, 55, 65, 20));
        this.商品编码.setFont(new Font("幼圆", 0, 12));
        this.商品编码.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.商品编码ActionPerformed(evt);
            }
        });
        this.jPanel33.add(this.商品编码, new AbsoluteConstraints(84, 25, 65, 20));
        this.商品代码.setFont(new Font("幼圆", 0, 12));
        this.jPanel33.add(this.商品代码, new AbsoluteConstraints(84, 55, 65, -1));
        this.jLabel30.setFont(new Font("幼圆", 0, 15));
        this.jLabel30.setText("商品数量；");
        this.jPanel33.add(this.jLabel30, new AbsoluteConstraints(160, 50, -1, 30));
        this.jLabel31.setFont(new Font("幼圆", 0, 15));
        this.jLabel31.setText("商品代码；");
        this.jPanel33.add(this.jLabel31, new AbsoluteConstraints(10, 50, -1, 30));
        this.商品价格.setFont(new Font("幼圆", 0, 12));
        this.jPanel33.add(this.商品价格, new AbsoluteConstraints(234, 25, 65, -1));
        this.商品时间.setFont(new Font("幼圆", 0, 12));
        this.jPanel33.add(this.商品时间, new AbsoluteConstraints(380, 55, 65, 20));
        this.jLabel32.setFont(new Font("幼圆", 0, 15));
        this.jLabel32.setText("商品库存；");
        this.jPanel33.add(this.jLabel32, new AbsoluteConstraints(450, 20, 90, 30));
        this.jLabel33.setFont(new Font("幼圆", 0, 15));
        this.jLabel33.setText("限时时间；");
        this.jPanel33.add(this.jLabel33, new AbsoluteConstraints(310, 50, -1, 30));
        this.jLabel34.setFont(new Font("幼圆", 0, 15));
        this.jLabel34.setText("商品编码；");
        this.jPanel33.add(this.jLabel34, new AbsoluteConstraints(10, 20, -1, 30));
        this.jLabel35.setFont(new Font("幼圆", 0, 15));
        this.jLabel35.setText("商品价格；");
        this.jPanel33.add(this.jLabel35, new AbsoluteConstraints(160, 20, 90, 30));
        this.商品库存.setFont(new Font("幼圆", 0, 12));
        this.jPanel33.add(this.商品库存, new AbsoluteConstraints(520, 25, 65, -1));
        this.商品折扣.setFont(new Font("幼圆", 0, 12));
        this.jPanel33.add(this.商品折扣, new AbsoluteConstraints(380, 25, 65, -1));
        this.jLabel37.setFont(new Font("幼圆", 0, 15));
        this.jLabel37.setText("商品反馈；");
        this.jPanel33.add(this.jLabel37, new AbsoluteConstraints(310, 20, 90, 30));
        this.jLabel36.setFont(new Font("幼圆", 0, 15));
        this.jLabel36.setText("每日限购；");
        this.jPanel33.add(this.jLabel36, new AbsoluteConstraints(450, 50, 90, 30));
        this.每日限购.setFont(new Font("幼圆", 0, 12));
        this.jPanel33.add(this.每日限购, new AbsoluteConstraints(520, 55, 65, -1));
        this.jLabel38.setFont(new Font("幼圆", 0, 15));
        this.jLabel38.setText("货币类型；");
        this.jPanel33.add(this.jLabel38, new AbsoluteConstraints(590, 20, 90, 30));
        this.货币类型.setEditable(false);
        this.货币类型.setFont(new Font("幼圆", 0, 12));
        this.jPanel33.add(this.货币类型, new AbsoluteConstraints(660, 25, 65, -1));
        this.商城扩充价格.setFont(new Font("幼圆", 0, 14));
        this.商城扩充价格.setModel(new DefaultTableModel(new Object[0][], new String[]{"背包扩充价格"}) {
            boolean[] canEdit = {false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.商城扩充价格.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane132.setViewportView(this.商城扩充价格);
        this.商城扩充价格修改.setFont(new Font("幼圆", 0, 12));
        this.商品出售状态.setEditable(false);
        this.商品出售状态.setFont(new Font("幼圆", 0, 12));
        this.商品出售状态.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.商品出售状态ActionPerformed(evt);
            }
        });
        this.显示类型.setEditable(false);
        this.显示类型.setFont(new Font("幼圆", 1, 14));
        this.显示类型.setForeground(new Color(255, 0, 51));
        this.显示类型.setText("测试字体");
        this.显示类型.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.显示类型ActionPerformed(evt);
            }
        });
        this.jPanel34.setBackground(new Color(255, 255, 255));
        this.jPanel34.setLayout(new AbsoluteLayout());
        this.jButton3.setFont(new Font("幼圆", 0, 15));
        this.jButton3.setText("重载商城");
        this.jButton3.setToolTipText("<html>\n<strong><font color=\"#FF0000\">重载商城；</font></strong><br>\n在商城控制台中的修改需要重载才能在游戏中生效");
        this.jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton3ActionPerformed(evt);
            }
        });
        this.jButton25.setFont(new Font("幼圆", 0, 15));
        this.jButton25.setText("上架");
        this.jButton25.setToolTipText("<html>\n<strong><font color=\"#FF0000\">上架；</font></strong><br>\n1.选择物品<br>\n2.上架/下架<br>");
        this.jButton25.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton25ActionPerformed(evt);
            }
        });
        this.jButton27.setFont(new Font("幼圆", 0, 15));
        this.jButton27.setText("删除");
        this.jButton27.setToolTipText("<html>\n<strong><font color=\"#FF0000\">删除；</font></strong><br>\n1.选择物品<br>\n2.删除<br>");
        this.jButton27.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton27ActionPerformed(evt);
            }
        });
        this.jButton28.setFont(new Font("幼圆", 0, 15));
        this.jButton28.setText("下架");
        this.jButton28.setToolTipText("<html>\n<strong><font color=\"#FF0000\">下架；</font></strong><br>\n1.选择物品<br>\n2.上架/下架<br>");
        this.jButton28.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton28ActionPerformed(evt);
            }
        });
        this.添加.setFont(new Font("幼圆", 0, 15));
        this.添加.setText("添加");
        this.添加.setToolTipText("<html>\n<strong><font color=\"#FF0000\">添加；</font></strong><br> \n1.选择物品分类<br>\n2.输入商品代码<br>\n3.输入商品数量<br>\n4.输入商品价格<br>\n5.输入限时时间(0代表永久)<br>\n6.选择出售状态<br>");
        this.添加.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.添加ActionPerformed(evt);
            }
        });
        this.jButton2.setFont(new Font("幼圆", 0, 15));
        this.jButton2.setForeground(new Color(255, 0, 255));
        this.jButton2.setText("刷新");
        this.jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton2ActionPerformed(evt);
            }
        });
        this.修改.setFont(new Font("幼圆", 0, 15));
        this.修改.setText("修改");
        this.修改.setToolTipText("<html>\n<strong><font color=\"#FF0000\">修改；</font></strong><br> \n1.在列表中选择需要修改的物品<br>\n2.在文本框中输入修改值<br>\n");
        this.修改.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.修改ActionPerformed(evt);
            }
        });
        this.修改背包扩充价格.setFont(new Font("幼圆", 0, 15));
        this.修改背包扩充价格.setText("修改");
        this.修改背包扩充价格.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.修改背包扩充价格ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel22Layout = new GroupLayout(this.jPanel22);
        this.jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(jPanel22Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jTabbedPane3).addComponent(this.jScrollPane6).addGroup(jPanel22Layout.createSequentialGroup().addComponent(this.jPanel33, -2, 776, -2).addPreferredGap(ComponentPlacement.UNRELATED).addGroup(jPanel22Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jButton27, -2, 70, -2).addGroup(jPanel22Layout.createSequentialGroup().addGroup(jPanel22Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jButton28, -2, 70, -2).addComponent(this.jButton25, -2, 70, -2)).addGap(14, 14, 14).addGroup(jPanel22Layout.createParallelGroup(Alignment.TRAILING).addGroup(jPanel22Layout.createSequentialGroup().addComponent(this.添加, -2, 70, -2).addGap(77, 77, 77).addComponent(this.显示类型, -2, 120, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.商品出售状态, -2, 70, -2)).addGroup(jPanel22Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel22Layout.createSequentialGroup().addGap(62, 62, 62).addComponent(this.jPanel34, -2, -1, -2)).addGroup(jPanel22Layout.createSequentialGroup().addGroup(jPanel22Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel22Layout.createSequentialGroup().addComponent(this.jButton2, -2, 70, -2).addGap(22, 22, 22).addComponent(this.jScrollPane132, -2, 107, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.商城扩充价格修改, -2, 60, -2)).addComponent(this.修改, -2, 70, -2)).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.修改背包扩充价格, -2, 70, -2)))).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(this.jButton3, -2, 140, -2))).addContainerGap()));
        jPanel22Layout.setVerticalGroup(jPanel22Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel22Layout.createSequentialGroup().addContainerGap().addComponent(this.jTabbedPane3, -2, 63, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jScrollPane6, -2, 465, -2).addGroup(jPanel22Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel22Layout.createSequentialGroup().addGap(7, 7, 7).addComponent(this.jPanel33, -1, -1, 32767)).addGroup(jPanel22Layout.createSequentialGroup().addPreferredGap(ComponentPlacement.UNRELATED).addGroup(jPanel22Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel22Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jButton27, -2, 25, -2).addComponent(this.jButton2, -2, 25, -2)).addGroup(jPanel22Layout.createSequentialGroup().addGap(4, 4, 4).addGroup(jPanel22Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel22Layout.createSequentialGroup().addComponent(this.jScrollPane132, -2, 25, -2).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel22Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jButton25, -2, 25, -2).addComponent(this.修改, -2, 25, -2))).addGroup(jPanel22Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.商城扩充价格修改, -2, 27, -2).addComponent(this.修改背包扩充价格, -2, 27, -2))))).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addGroup(jPanel22Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jButton28, -2, 25, -2).addComponent(this.添加, -2, 25, -2).addComponent(this.显示类型, -2, 27, -2).addComponent(this.商品出售状态, -2, 27, -2).addComponent(this.jButton3, -2, 25, -2)).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.jPanel34, -2, -1, -2))).addGap(617, 617, 617)));
        this.jTabbedPane2.addTab("游戏商城", this.jPanel22);
        this.jPanel35.setBackground(new Color(255, 255, 255));
        this.jPanel36.setBackground(new Color(255, 255, 240));
        this.jPanel55.setBackground(new Color(255, 255, 240));
        this.jPanel55.setBorder(BorderFactory.createTitledBorder(null, "查询商品出售物品", 2, 2, new Font("幼圆", 0, 18)));
        this.jPanel55.setLayout(new AbsoluteLayout());
        this.查询商店2.setFont(new Font("幼圆", 0, 15));
        this.查询商店2.setText("查询商店");
        this.查询商店2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.查询商店2ActionPerformed(evt);
            }
        });
        this.jPanel55.add(this.查询商店2, new AbsoluteConstraints(210, 70, -1, 30));
        this.查询商店.setFont(new Font("幼圆", 0, 15));
        this.jPanel55.add(this.查询商店, new AbsoluteConstraints(90, 70, 110, 30));
        this.jLabel270.setFont(new Font("幼圆", 0, 15));
        this.jLabel270.setText("商店ID");
        this.jPanel55.add(this.jLabel270, new AbsoluteConstraints(30, 70, -1, 30));
        this.jPanel56.setBackground(new Color(255, 255, 240));
        this.jPanel56.setBorder(BorderFactory.createTitledBorder(null, "查询商品出售物品", 2, 2, new Font("幼圆", 0, 18)));
        this.jPanel56.setLayout(new AbsoluteLayout());
        this.删除商品.setFont(new Font("幼圆", 0, 15));
        this.删除商品.setText("删除");
        this.删除商品.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除商品ActionPerformed(evt);
            }
        });
        this.jPanel56.add(this.删除商品, new AbsoluteConstraints(190, 120, -1, 30));
        this.新增商品.setFont(new Font("幼圆", 0, 15));
        this.新增商品.setText("新增");
        this.新增商品.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.新增商品ActionPerformed(evt);
            }
        });
        this.jPanel56.add(this.新增商品, new AbsoluteConstraints(110, 120, -1, 30));
        this.商品序号.setEditable(false);
        this.商品序号.setFont(new Font("幼圆", 0, 15));
        this.jPanel56.add(this.商品序号, new AbsoluteConstraints(10, 70, 80, 30));
        this.商店代码.setFont(new Font("幼圆", 0, 15));
        this.商店代码.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.商店代码ActionPerformed(evt);
            }
        });
        this.jPanel56.add(this.商店代码, new AbsoluteConstraints(90, 70, 80, 30));
        this.商品物品代码.setFont(new Font("幼圆", 0, 15));
        this.jPanel56.add(this.商品物品代码, new AbsoluteConstraints(170, 70, 90, 30));
        this.商品售价金币.setFont(new Font("幼圆", 0, 15));
        this.jPanel56.add(this.商品售价金币, new AbsoluteConstraints(260, 70, 100, 30));
        this.jLabel268.setFont(new Font("幼圆", 0, 15));
        this.jLabel268.setText("出售金币");
        this.jPanel56.add(this.jLabel268, new AbsoluteConstraints(260, 50, -1, -1));
        this.jLabel269.setFont(new Font("幼圆", 0, 15));
        this.jLabel269.setText("序号");
        this.jPanel56.add(this.jLabel269, new AbsoluteConstraints(10, 50, -1, -1));
        this.jLabel271.setFont(new Font("幼圆", 0, 15));
        this.jLabel271.setText("物品名称");
        this.jPanel56.add(this.jLabel271, new AbsoluteConstraints(360, 50, -1, -1));
        this.jLabel272.setFont(new Font("幼圆", 0, 15));
        this.jLabel272.setText("商店ID");
        this.jPanel56.add(this.jLabel272, new AbsoluteConstraints(90, 50, -1, -1));
        this.修改商品.setFont(new Font("幼圆", 0, 15));
        this.修改商品.setText("修改");
        this.修改商品.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.修改商品ActionPerformed(evt);
            }
        });
        this.jPanel56.add(this.修改商品, new AbsoluteConstraints(270, 120, -1, 30));
        this.商品名称.setEditable(false);
        this.商品名称.setFont(new Font("幼圆", 0, 15));
        this.商品名称.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.商品名称ActionPerformed(evt);
            }
        });
        this.jPanel56.add(this.商品名称, new AbsoluteConstraints(360, 70, 100, 30));
        this.jLabel273.setFont(new Font("幼圆", 0, 15));
        this.jLabel273.setText("物品代码");
        this.jPanel56.add(this.jLabel273, new AbsoluteConstraints(170, 50, -1, -1));
        this.jButton33.setText("查看全部");
        this.jButton33.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton33ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel36Layout = new GroupLayout(this.jPanel36);
        this.jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(jPanel36Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel36Layout.createSequentialGroup().addComponent(this.jPanel55, -1, 836, 32767).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jPanel56, -2, 474, -2)).addGroup(jPanel36Layout.createSequentialGroup().addGap(283, 283, 283).addComponent(this.jButton33, -2, 258, -2).addContainerGap(-1, 32767)));
        jPanel36Layout.setVerticalGroup(jPanel36Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel36Layout.createSequentialGroup().addContainerGap(-1, 32767).addGroup(jPanel36Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jPanel55, Alignment.TRAILING, -2, 183, -2).addComponent(this.jPanel56, Alignment.TRAILING, -2, 183, -2)).addGap(18, 18, 18).addComponent(this.jButton33, -2, 63, -2).addGap(34, 34, 34)));
        this.游戏商店2.setBackground(new Color(153, 153, 153));
        this.游戏商店2.setFont(new Font("幼圆", 0, 15));
        this.游戏商店2.setModel(new DefaultTableModel(new Object[0][], new String[]{"序号", "商店ID", "物品代码", "销售金币", "物品名称"}) {
            boolean[] canEdit = {false, false, false, false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.游戏商店2.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane25.setViewportView(this.游戏商店2);
        final GroupLayout jPanel35Layout = new GroupLayout(this.jPanel35);
        this.jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(jPanel35Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jScrollPane25, -1, 1318, 32767).addGroup(jPanel35Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jPanel36, -1, -1, 32767)));
        jPanel35Layout.setVerticalGroup(jPanel35Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel35Layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane25, -2, 343, -2).addContainerGap(326, 32767)).addGroup(jPanel35Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel35Layout.createSequentialGroup().addContainerGap(358, 32767).addComponent(this.jPanel36, -2, -1, -2).addContainerGap(-1, 32767))));
        this.jTabbedPane2.addTab("游戏商店", this.jPanel35);
        this.jPanel11.setBackground(new Color(255, 255, 240));
        this.jPanel11.setBorder(BorderFactory.createTitledBorder("游戏公告"));
        this.sendNotice.setText("蓝色提示公告");
        this.sendNotice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.sendNoticeActionPerformed(evt);
            }
        });
        this.sendWinNotice.setText("顶部滚动公告");
        this.sendWinNotice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.sendWinNoticeActionPerformed(evt);
            }
        });
        this.sendMsgNotice.setText("弹窗公告");
        this.sendMsgNotice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.sendMsgNoticeActionPerformed(evt);
            }
        });
        this.sendNpcTalkNotice.setText("蓝色公告事项");
        this.sendNpcTalkNotice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.sendNpcTalkNoticeActionPerformed(evt);
            }
        });
        this.noticeText.setFont(new Font("宋体", 0, 24));
        this.noticeText.setText("游戏即将维护,请安全下线！造成不便请谅解！");
        this.jLabel117.setFont(new Font("幼圆", 0, 24));
        this.jLabel117.setText("1、不得散布谣言，扰乱社会秩序，破坏社会稳定的信息 ");
        this.jLabel118.setFont(new Font("幼圆", 0, 24));
        this.jLabel118.setText("2、不得散布赌博、暴力、凶杀、恐怖或者教唆犯罪的信息");
        this.jLabel119.setFont(new Font("幼圆", 0, 24));
        this.jLabel119.setText("3、不得侮辱或者诽谤他人，侵害他人合法权益");
        this.jLabel106.setFont(new Font("幼圆", 0, 24));
        this.jLabel106.setText("4、不得含有法律、行政法规禁止的其他内容");
        this.公告发布喇叭代码.setForeground(new Color(255, 51, 102));
        this.公告发布喇叭代码.setText("5120027");
        this.公告发布喇叭代码.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.公告发布喇叭代码ActionPerformed(evt);
            }
        });
        this.jButton45.setFont(new Font("幼圆", 0, 14));
        this.jButton45.setText("屏幕正中公告");
        this.jButton45.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.jButton45ActionPerformed(evt);
            }
        });
        this.jLabel259.setFont(new Font("幼圆", 0, 14));
        this.jLabel259.setText("喇叭代码");
        final GroupLayout jPanel11Layout = new GroupLayout(this.jPanel11);
        this.jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(jPanel11Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel11Layout.createSequentialGroup().addContainerGap().addComponent(this.noticeText).addContainerGap()).addGroup(Alignment.TRAILING, jPanel11Layout.createSequentialGroup().addContainerGap(-1, 32767).addGroup(jPanel11Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jLabel117, -2, 680, -2).addComponent(this.jLabel118, -2, 680, -2).addComponent(this.jLabel119, -2, 680, -2).addComponent(this.jLabel106, -2, 680, -2)).addGap(89, 89, 89)).addGroup(jPanel11Layout.createSequentialGroup().addGap(21, 21, 21).addComponent(this.sendNotice, -2, 123, -2).addGap(18, 18, 18).addComponent(this.sendWinNotice, -2, 136, -2).addGap(18, 18, 18).addComponent(this.sendMsgNotice, -2, 113, -2).addGap(18, 18, 18).addComponent(this.sendNpcTalkNotice, -2, 122, -2).addGap(18, 18, 18).addComponent(this.jButton45, -2, 130, -2).addGap(10, 10, 10).addGroup(jPanel11Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jLabel259).addComponent(this.公告发布喇叭代码, -2, 90, -2)).addContainerGap(-1, 32767)));
        jPanel11Layout.setVerticalGroup(jPanel11Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel11Layout.createSequentialGroup().addContainerGap(52, 32767).addComponent(this.noticeText, -2, 203, -2).addGap(59, 59, 59).addGroup(jPanel11Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel11Layout.createSequentialGroup().addComponent(this.jLabel259).addGap(0, 0, 0).addComponent(this.公告发布喇叭代码, -2, 30, -2)).addGroup(jPanel11Layout.createParallelGroup(Alignment.TRAILING, false).addComponent(this.jButton45, Alignment.LEADING, -1, -1, 32767).addComponent(this.sendNpcTalkNotice, -1, -1, 32767).addGroup(Alignment.LEADING, jPanel11Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.sendWinNotice, -2, 39, -2).addComponent(this.sendMsgNotice, -2, 39, -2)).addComponent(this.sendNotice, Alignment.LEADING, -1, -1, 32767))).addGap(76, 76, 76).addComponent(this.jLabel117, -2, 40, -2).addGap(0, 0, 0).addComponent(this.jLabel118, -2, 40, -2).addGap(0, 0, 0).addComponent(this.jLabel119, -2, 40, -2).addGap(0, 0, 0).addComponent(this.jLabel106, -2, 40, -2).addGap(55, 55, 55)));
        this.jTabbedPane2.addTab("游戏公告", this.jPanel11);
        this.jPanel37.setBackground(new Color(255, 255, 240));
        this.jPanel37.setBorder(BorderFactory.createTitledBorder("批量删除游戏物品"));
        this.游戏道具.setFont(new Font("幼圆", 0, 15));
        this.游戏道具.setModel(new DefaultTableModel(new Object[0][], new String[]{"角色ID", "角色名字", "道具ID", "道具名字"}) {
            boolean[] canEdit = {false, false, false, false};

            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.游戏道具.setColumnSelectionAllowed(true);
        this.jScrollPane27.setViewportView(this.游戏道具);
        this.jLabel338.setFont(new Font("幼圆", 0, 15));
        this.jLabel338.setForeground(new Color(255, 51, 51));
        this.jLabel338.setText("需离线操作。");
        this.jPanel38.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.jPanel38.setLayout(new AbsoluteLayout());
        this.查找道具.setText("查找道具");
        this.查找道具.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.查找道具ActionPerformed(evt);
            }
        });
        this.jPanel38.add(this.查找道具, new AbsoluteConstraints(10, 30, 140, 30));
        this.删除道具.setText("删除道具");
        this.删除道具.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除道具ActionPerformed(evt);
            }
        });
        this.jPanel38.add(this.删除道具, new AbsoluteConstraints(10, 60, 140, 30));
        this.jLabel337.setFont(new Font("幼圆", 0, 15));
        this.jLabel337.setText("角色背包，角色穿戴");
        this.jPanel38.add(this.jLabel337, new AbsoluteConstraints(10, 10, -1, 20));
        this.jPanel49.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.jPanel49.setLayout(new AbsoluteLayout());
        this.查找道具1.setText("查找道具");
        this.查找道具1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.查找道具1ActionPerformed(evt);
            }
        });
        this.jPanel49.add(this.查找道具1, new AbsoluteConstraints(10, 30, 140, 30));
        this.删除道具1.setText("删除道具");
        this.删除道具1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除道具1ActionPerformed(evt);
            }
        });
        this.jPanel49.add(this.删除道具1, new AbsoluteConstraints(10, 60, 140, 30));
        this.jLabel339.setFont(new Font("幼圆", 0, 15));
        this.jLabel339.setText("点券拍卖行");
        this.jPanel49.add(this.jLabel339, new AbsoluteConstraints(10, 10, -1, 20));
        this.jPanel62.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.jPanel62.setLayout(new AbsoluteLayout());
        this.查找道具2.setText("查找道具");
        this.查找道具2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.查找道具2ActionPerformed(evt);
            }
        });
        this.jPanel62.add(this.查找道具2, new AbsoluteConstraints(10, 30, 140, 30));
        this.删除道具2.setText("删除道具");
        this.删除道具2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除道具2ActionPerformed(evt);
            }
        });
        this.jPanel62.add(this.删除道具2, new AbsoluteConstraints(10, 60, 140, 30));
        this.jLabel340.setFont(new Font("幼圆", 0, 15));
        this.jLabel340.setText("金币拍卖行");
        this.jPanel62.add(this.jLabel340, new AbsoluteConstraints(10, 10, -1, 20));
        this.jPanel63.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.jPanel63.setLayout(new AbsoluteLayout());
        this.查找道具3.setText("查找道具");
        this.查找道具3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.查找道具3ActionPerformed(evt);
            }
        });
        this.jPanel63.add(this.查找道具3, new AbsoluteConstraints(10, 30, 140, 30));
        this.删除道具3.setText("删除道具");
        this.删除道具3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除道具3ActionPerformed(evt);
            }
        });
        this.jPanel63.add(this.删除道具3, new AbsoluteConstraints(10, 60, 140, 30));
        this.jLabel341.setFont(new Font("幼圆", 0, 15));
        this.jLabel341.setText("家族游戏仓库");
        this.jPanel63.add(this.jLabel341, new AbsoluteConstraints(10, 10, -1, 20));
        this.jPanel64.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.jPanel64.setLayout(new AbsoluteLayout());
        this.查找道具4.setText("查找道具");
        this.查找道具4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.查找道具4ActionPerformed(evt);
            }
        });
        this.jPanel64.add(this.查找道具4, new AbsoluteConstraints(10, 30, 140, 30));
        this.删除道具4.setText("删除道具");
        this.删除道具4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                CongMS.this.删除道具4ActionPerformed(evt);
            }
        });
        this.jPanel64.add(this.删除道具4, new AbsoluteConstraints(10, 60, 140, 30));
        this.jLabel342.setFont(new Font("幼圆", 0, 15));
        this.jLabel342.setText("个人游戏仓库");
        this.jPanel64.add(this.jLabel342, new AbsoluteConstraints(10, 10, -1, 20));
        this.jLabel343.setFont(new Font("幼圆", 0, 15));
        this.jLabel343.setText("游戏道具代码；");
        this.jLabel344.setFont(new Font("幼圆", 0, 15));
        this.jLabel344.setText("该功能可查询游戏内指定区域的游戏道具，可以查看");
        this.jLabel345.setFont(new Font("幼圆", 0, 15));
        this.jLabel345.setText("该道具有多少玩家拥有。可以一键删除所有该道具。");
        final GroupLayout jPanel37Layout = new GroupLayout(this.jPanel37);
        this.jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(jPanel37Layout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, jPanel37Layout.createSequentialGroup().addGroup(jPanel37Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel37Layout.createSequentialGroup().addGroup(jPanel37Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jScrollPane27).addGroup(jPanel37Layout.createSequentialGroup().addGap(160, 160, 160).addGroup(jPanel37Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jLabel345, -2, 350, -2).addComponent(this.jLabel344, -2, 350, -2)).addGap(0, 594, 32767))).addGap(18, 18, 18)).addGroup(jPanel37Layout.createSequentialGroup().addGap(276, 276, 276).addComponent(this.jLabel338).addPreferredGap(ComponentPlacement.RELATED, -1, 32767))).addGroup(jPanel37Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jLabel343).addComponent(this.游戏道具代码, -2, 160, -2).addComponent(this.jPanel38, -2, 160, -2).addComponent(this.jPanel62, -2, 160, -2).addComponent(this.jPanel49, -2, 160, -2).addComponent(this.jPanel64, -2, 160, -2).addComponent(this.jPanel63, -2, 160, -2)).addGap(24, 24, 24)));
        jPanel37Layout.setVerticalGroup(jPanel37Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel37Layout.createSequentialGroup().addGroup(jPanel37Layout.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING, jPanel37Layout.createSequentialGroup().addComponent(this.jScrollPane27, -2, 534, -2).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(this.jLabel344, -2, 20, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jLabel345, -2, 20, -2).addGap(2, 2, 2).addComponent(this.jLabel338, -2, 20, -2)).addGroup(Alignment.LEADING, jPanel37Layout.createSequentialGroup().addComponent(this.jLabel343, -2, 20, -2).addGap(0, 0, 0).addComponent(this.游戏道具代码, -2, 30, -2).addGap(10, 10, 10).addComponent(this.jPanel38, -2, 100, -2).addGap(10, 10, 10).addComponent(this.jPanel62, -2, 100, -2).addGap(10, 10, 10).addComponent(this.jPanel49, -2, 100, -2).addGap(10, 10, 10).addComponent(this.jPanel64, -2, 100, -2).addGap(18, 18, 18).addComponent(this.jPanel63, -2, 100, -2))).addContainerGap(-1, 32767)));
        this.jTabbedPane2.addTab("批量删除", this.jPanel37);
        final GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addGap(0, 1318, 32767));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addGap(0, 682, 32767));
        this.jTabbedPane2.addTab("", this.jPanel2);
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jTabbedPane2, -2, 1323, -2).addContainerGap(-1, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jTabbedPane2, -2, 714, 32767));
        this.pack();
    }

    private void 删除道具4ActionPerformed(final ActionEvent evt) {
        final String 输出 = "";
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result1 = this.游戏道具代码.getText().matches("[0-9]+");
        if (result1) {
            try {
                for (int i = this.游戏道具.getModel().getRowCount() - 1; i >= 0; --i) {
                    ((DefaultTableModel) this.游戏道具.getModel()).removeRow(i);
                }
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM bank_item WHERE itemid = ?");
                ps1.setInt(1, Integer.parseInt(this.游戏道具代码.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from bank_item where itemid =" + Integer.parseInt(this.游戏道具代码.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    JOptionPane.showMessageDialog(null, "成功删除所有代码为 " + Integer.parseInt(this.游戏道具代码.getText()) + " 物品。");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入你想要删除的<物品代码>");
        }
    }

    private void 查找道具4ActionPerformed(final ActionEvent evt) {
        final boolean result1 = this.游戏道具代码.getText().matches("[0-9]+");
        if (result1) {
            for (int i = this.游戏道具.getModel().getRowCount() - 1; i >= 0; --i) {
                ((DefaultTableModel) this.游戏道具.getModel()).removeRow(i);
            }
            try {
                final Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;
                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM bank_item WHERE itemid =" + Integer.parseInt(this.游戏道具代码.getText()) + "");
                rs = ps.executeQuery();
                while (rs.next()) {
                    ((DefaultTableModel) this.游戏道具.getModel()).insertRow(this.游戏道具.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("cid")), NPCConversationManager.角色ID取名字(rs.getInt("cid")), Integer.valueOf(rs.getInt("itemid")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入你要查找的<物品代码>");
        }
    }

    private void 删除道具3ActionPerformed(final ActionEvent evt) {
        final String 输出 = "";
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result1 = this.游戏道具代码.getText().matches("[0-9]+");
        if (result1) {
            try {
                for (int i = this.游戏道具.getModel().getRowCount() - 1; i >= 0; --i) {
                    ((DefaultTableModel) this.游戏道具.getModel()).removeRow(i);
                }
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM bank_item1 WHERE itemid = ?");
                ps1.setInt(1, Integer.parseInt(this.游戏道具代码.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from bank_item1 where itemid =" + Integer.parseInt(this.游戏道具代码.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    JOptionPane.showMessageDialog(null, "成功删除所有代码为 " + Integer.parseInt(this.游戏道具代码.getText()) + " 物品。");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入你想要删除的<物品代码>");
        }
    }

    private void 查找道具3ActionPerformed(final ActionEvent evt) {
        final boolean result1 = this.游戏道具代码.getText().matches("[0-9]+");
        if (result1) {
            for (int i = this.游戏道具.getModel().getRowCount() - 1; i >= 0; --i) {
                ((DefaultTableModel) this.游戏道具.getModel()).removeRow(i);
            }
            try {
                final Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;
                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM bank_item1 WHERE itemid =" + Integer.parseInt(this.游戏道具代码.getText()) + "");
                rs = ps.executeQuery();
                while (rs.next()) {
                    ((DefaultTableModel) this.游戏道具.getModel()).insertRow(this.游戏道具.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("cid")), NPCConversationManager.角色ID取名字(rs.getInt("cid")), Integer.valueOf(rs.getInt("itemid")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入你要查找的<物品代码>");
        }
    }

    private void 删除道具2ActionPerformed(final ActionEvent evt) {
        final String 输出 = "";
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result1 = this.游戏道具代码.getText().matches("[0-9]+");
        if (result1) {
            try {
                for (int i = this.游戏道具.getModel().getRowCount() - 1; i >= 0; --i) {
                    ((DefaultTableModel) this.游戏道具.getModel()).removeRow(i);
                }
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM auctionitems1 WHERE itemid = ?");
                ps1.setInt(1, Integer.parseInt(this.游戏道具代码.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from auctionitems1 where itemid =" + Integer.parseInt(this.游戏道具代码.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    JOptionPane.showMessageDialog(null, "成功删除所有代码为 " + Integer.parseInt(this.游戏道具代码.getText()) + " 物品。");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入你想要删除的<物品代码>");
        }
    }

    private void 查找道具2ActionPerformed(final ActionEvent evt) {
        final boolean result1 = this.游戏道具代码.getText().matches("[0-9]+");
        if (result1) {
            for (int i = this.游戏道具.getModel().getRowCount() - 1; i >= 0; --i) {
                ((DefaultTableModel) this.游戏道具.getModel()).removeRow(i);
            }
            try {
                final Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;
                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM auctionitems1 WHERE itemid =" + Integer.parseInt(this.游戏道具代码.getText()) + "");
                rs = ps.executeQuery();
                while (rs.next()) {
                    ((DefaultTableModel) this.游戏道具.getModel()).insertRow(this.游戏道具.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("characterid")), NPCConversationManager.角色ID取名字(rs.getInt("characterid")), Integer.valueOf(rs.getInt("itemid")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入你要查找的<物品代码>");
        }
    }

    private void 删除道具1ActionPerformed(final ActionEvent evt) {
        final String 输出 = "";
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result1 = this.游戏道具代码.getText().matches("[0-9]+");
        if (result1) {
            try {
                for (int i = this.游戏道具.getModel().getRowCount() - 1; i >= 0; --i) {
                    ((DefaultTableModel) this.游戏道具.getModel()).removeRow(i);
                }
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM auctionitems WHERE itemid = ?");
                ps1.setInt(1, Integer.parseInt(this.游戏道具代码.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from auctionitems where itemid =" + Integer.parseInt(this.游戏道具代码.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    JOptionPane.showMessageDialog(null, "成功删除所有代码为 " + Integer.parseInt(this.游戏道具代码.getText()) + " 物品。");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入你想要删除的<物品代码>");
        }
    }

    private void 查找道具1ActionPerformed(final ActionEvent evt) {
        final boolean result1 = this.游戏道具代码.getText().matches("[0-9]+");
        if (result1) {
            for (int i = this.游戏道具.getModel().getRowCount() - 1; i >= 0; --i) {
                ((DefaultTableModel) this.游戏道具.getModel()).removeRow(i);
            }
            try {
                final Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;
                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM auctionitems WHERE itemid =" + Integer.parseInt(this.游戏道具代码.getText()) + "");
                rs = ps.executeQuery();
                while (rs.next()) {
                    ((DefaultTableModel) this.游戏道具.getModel()).insertRow(this.游戏道具.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("characterid")), NPCConversationManager.角色ID取名字(rs.getInt("characterid")), Integer.valueOf(rs.getInt("itemid")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入你要查找的<物品代码>");
        }
    }

    private void 删除道具ActionPerformed(final ActionEvent evt) {
        final String 输出 = "";
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result1 = this.游戏道具代码.getText().matches("[0-9]+");
        if (result1) {
            try {
                for (int i = this.游戏道具.getModel().getRowCount() - 1; i >= 0; --i) {
                    ((DefaultTableModel) this.游戏道具.getModel()).removeRow(i);
                }
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM inventoryitems WHERE itemid = ?");
                ps1.setInt(1, Integer.parseInt(this.游戏道具代码.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from inventoryitems where itemid =" + Integer.parseInt(this.游戏道具代码.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    JOptionPane.showMessageDialog(null, "成功删除所有代码为 " + Integer.parseInt(this.游戏道具代码.getText()) + " 物品。");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入你想要删除的<物品代码>");
        }
    }

    private void 查找道具ActionPerformed(final ActionEvent evt) {
        final boolean result1 = this.游戏道具代码.getText().matches("[0-9]+");
        if (result1) {
            for (int i = this.游戏道具.getModel().getRowCount() - 1; i >= 0; --i) {
                ((DefaultTableModel) this.游戏道具.getModel()).removeRow(i);
            }
            try {
                final Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;
                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE itemid =" + Integer.parseInt(this.游戏道具代码.getText()) + "");
                rs = ps.executeQuery();
                while (rs.next()) {
                    ((DefaultTableModel) this.游戏道具.getModel()).insertRow(this.游戏道具.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("characterid")), NPCConversationManager.角色ID取名字(rs.getInt("characterid")), Integer.valueOf(rs.getInt("itemid")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入你要查找的<物品代码>");
        }
    }

    private void jButton45ActionPerformed(final ActionEvent evt) {
        this.sendNotice(4);
        System.out.println("[公告系统] 发送公告成功！");
        JOptionPane.showMessageDialog(null, "发送公告成功！");
    }

    private void 公告发布喇叭代码ActionPerformed(final ActionEvent evt) {
    }

    private void sendNpcTalkNoticeActionPerformed(final ActionEvent evt) {
        this.sendNotice(3);
        System.out.println("[公告系统] 发送黄色滚动公告成功！");
        JOptionPane.showMessageDialog(null, "发送黄色滚动公告成功！");
    }

    private void sendMsgNoticeActionPerformed(final ActionEvent evt) {
        this.sendNotice(2);
        System.out.println("[公告系统] 发送红色提示公告成功！");
        JOptionPane.showMessageDialog(null, "发送红色提示公告成功！");
    }

    private void sendWinNoticeActionPerformed(final ActionEvent evt) {
        this.sendNotice(1);
        System.out.println("[公告系统] 发送弹窗公告成功！");
        JOptionPane.showMessageDialog(null, "发送弹窗公告成功！");
    }

    private void sendNoticeActionPerformed(final ActionEvent evt) {
        this.sendNotice(0);
        System.out.println("[公告系统] 发送蓝色公告事项公告成功！");
        JOptionPane.showMessageDialog(null, "发送蓝色公告事项公告成功！");
    }

    private void jButton33ActionPerformed(final ActionEvent evt) {
        this.查询商店(0);
    }

    private void 商品名称ActionPerformed(final ActionEvent evt) {
    }

    private void 修改商品ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        final boolean result = this.商品物品代码.getText().matches("[0-9]+");
        final boolean result2 = this.商店代码.getText().matches("[0-9]+");
        final boolean result3 = this.商品售价金币.getText().matches("[0-9]+");
        if (result && result2 && result3) {
            if (Integer.parseInt(this.商店代码.getText()) < 0 && Integer.parseInt(this.商品物品代码.getText()) < 0 && Integer.parseInt(this.商品售价金币.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "请填写正确的值");
            }
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE shopitems SET itemid = ?,price = ?,shopid = ?WHERE shopitemid = ?");
                ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM shopitems WHERE shopitemid = ?");
                ps2.setInt(1, Integer.parseInt(this.商品序号.getText()));
                rs = ps2.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    String sqlString2 = null;
                    String sqlString3 = null;
                    sqlString1 = "update shopitems set itemid='" + this.商品物品代码.getText() + "' where shopitemid=" + this.商品序号.getText() + ";";
                    final PreparedStatement itemid = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    itemid.executeUpdate(sqlString1);
                    sqlString2 = "update shopitems set price='" + this.商品售价金币.getText() + "' where shopitemid=" + this.商品序号.getText() + ";";
                    final PreparedStatement price = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    price.executeUpdate(sqlString2);
                    sqlString3 = "update shopitems set shopid='" + this.商店代码.getText() + "' where shopitemid=" + this.商品序号.getText() + ";";
                    final PreparedStatement shopid = DatabaseConnection.getConnection().prepareStatement(sqlString3);
                    shopid.executeUpdate(sqlString3);
                    this.查询商店(1);
                }
                JOptionPane.showMessageDialog(null, "[信息]:商店商品修改成功。");
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:选择你要修改的商品,并填写<商店ID><物品代码><售价金币>。");
        }
    }

    private void 商店代码ActionPerformed(final ActionEvent evt) {
    }

    private void 新增商品ActionPerformed(final ActionEvent evt) {
        final boolean result = this.商品物品代码.getText().matches("[0-9]+");
        final boolean result2 = this.商店代码.getText().matches("[0-9]+");
        final boolean result3 = this.商品售价金币.getText().matches("[0-9]+");
        if (result && result2 && result3) {
            if (Integer.parseInt(this.商店代码.getText()) < 0 && Integer.parseInt(this.商品物品代码.getText()) < 0 && Integer.parseInt(this.商品售价金币.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "[信息]:请填写正确的值。");
                return;
            }
            try (final Connection con = DatabaseConnection.getConnection();
                 final PreparedStatement ps = con.prepareStatement("INSERT INTO shopitems (shopid ,itemid ,price ,pitch ,position ,reqitem ,reqitemq) VALUES ( ?, ?, ?, ?, ?, ?, ?)")) {
                ps.setInt(1, Integer.parseInt(this.商店代码.getText()));
                ps.setInt(2, Integer.parseInt(this.商品物品代码.getText()));
                ps.setInt(3, Integer.parseInt(this.商品售价金币.getText()));
                ps.setInt(4, 0);
                ps.setInt(5, 0);
                ps.setInt(6, 0);
                ps.setInt(7, 0);
                ps.executeUpdate();
                this.查询商店(1);
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "[信息]:新增商店商品成功。");
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:输入<商店ID><物品代码><售价>。");
        }
    }

    private void 删除商品ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result = this.商品序号.getText().matches("[0-9]+");
        if (result) {
            final int 商城SN编码 = Integer.parseInt(this.商品序号.getText());
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM shopitems WHERE shopitemid = ?");
                ps1.setInt(1, 商城SN编码);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from shopitems where shopitemid =" + 商城SN编码 + "";
                    ps1.executeUpdate(sqlstr);
                    this.查询商店(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "[信息]:删除商店商品成功。");
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:请选择你要删除的商品。");
        }
    }

    private void 查询商店2ActionPerformed(final ActionEvent evt) {
        this.查询商店(1);
    }

    private void 修改背包扩充价格ActionPerformed(final ActionEvent evt) {
        final boolean result1 = this.商城扩充价格修改.getText().matches("[0-9]+");
        if (result1) {
            if (Integer.parseInt(this.商城扩充价格修改.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "[信息]:请输入正确的修改值。");
                return;
            }
            PreparedStatement ps1 = null;
            ResultSet rs = null;
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps1.setInt(1, 1);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from configvalues where id =999";
                    ps1.executeUpdate(sqlstr);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, ex);
            }
            try (final Connection con = DatabaseConnection.getConnection();
                 final PreparedStatement ps2 = con.prepareStatement("INSERT INTO configvalues (id, name,Val) VALUES ( ?, ?, ?)")) {
                ps2.setInt(1, 999);
                ps2.setString(2, "商城扩充价格");
                ps2.setInt(3, Integer.parseInt(this.商城扩充价格修改.getText()));
                ps2.executeUpdate();
                this.刷新商城扩充价格();
                Start.GetConfigValues();
                JOptionPane.showMessageDialog(null, "[信息]:商城扩充背包价格修改成功，已经生效。");
            } catch (SQLException ex) {
                Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void 修改ActionPerformed(final ActionEvent evt) {
        final boolean result1 = this.商品编码.getText().matches("[0-9]+");
        final boolean result2 = this.商品数量.getText().matches("[0-9]+");
        final boolean result3 = this.商品价格.getText().matches("[0-9]+");
        final boolean result4 = this.商品时间.getText().matches("[0-9]+");
        final boolean result5 = this.商品库存.getText().matches("[0-9]+");
        final boolean result6 = this.每日限购.getText().matches("[0-9]+");
        final boolean result7 = this.商品折扣.getText().matches("[0-9]+");
        final boolean result8 = this.商品代码.getText().matches("[0-9]+");
        if (!result1 && !result2 && !result3 && !result4 && !result5 && !result6 && !result7 && !result8) {
            JOptionPane.showMessageDialog(null, "[信息]:请输入正确的数据。");
            return;
        }
        if (this.商品编码.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "[信息]:请点击商品分类选择添加类型。");
            return;
        }
        if (this.商品代码.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "[信息]:请输入添加的商品代码。");
            return;
        }
        if (this.商品价格.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "[信息]:请输入商品价格。");
            return;
        }
        if (Integer.parseInt(this.商品价格.getText()) > 999999999) {
            JOptionPane.showMessageDialog(null, "[信息]:商品数量不能大于999999999。");
            return;
        }
        if (this.商品时间.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "[信息]:请输入商品的给予时间，0 代表无限制。");
            return;
        }
        if (this.商品数量.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "[信息]:请输入商品的商品数量。");
            return;
        }
        if (Integer.parseInt(this.商品数量.getText()) > 100) {
            JOptionPane.showMessageDialog(null, "[信息]:商品数量不能大于100。");
            return;
        }
        if (this.商品出售状态.getText().equals("")) {
            final int 商品出售状态2 = -1;
        } else {
            final int 商品出售状态2 = Integer.parseInt(this.商品出售状态.getText());
        }
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        try {
            for (int i = this.charTable.getModel().getRowCount() - 1; i >= 0; --i) {
                ((DefaultTableModel) this.charTable.getModel()).removeRow(i);
            }
            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE cashshop_modified_items SET showup = ?, itemid = ?, priority = ?, period = ?, gender = ?, count = ?, meso = ?, discount_price = ?, mark = ?, unk_1 = ?, unk_2 = ?, unk_3 = ? WHERE serial = ?");
            ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM cashshop_modified_items WHERE serial = ?");
            ps2.setInt(1, Integer.parseInt(this.商品编码.getText()));
            rs = ps2.executeQuery();
            if (rs.next()) {
                String sqlString1 = null;
                String sqlString2 = null;
                String sqlString3 = null;
                String sqlString4 = null;
                String sqlString5 = null;
                sqlString1 = "update cashshop_modified_items set itemid='" + Integer.parseInt(this.商品代码.getText()) + "' where serial=" + Integer.parseInt(this.商品编码.getText()) + ";";
                final PreparedStatement itemid = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                itemid.executeUpdate(sqlString1);
                sqlString2 = "update cashshop_modified_items set period='" + Integer.parseInt(this.商品时间.getText()) + "' where serial=" + Integer.parseInt(this.商品编码.getText()) + ";";
                final PreparedStatement period = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                period.executeUpdate(sqlString2);
                sqlString3 = "update cashshop_modified_items set count='" + Integer.parseInt(this.商品数量.getText()) + "' where serial=" + Integer.parseInt(this.商品编码.getText()) + ";";
                final PreparedStatement count = DatabaseConnection.getConnection().prepareStatement(sqlString3);
                count.executeUpdate(sqlString3);
                sqlString4 = "update cashshop_modified_items set discount_price='" + Integer.parseInt(this.商品价格.getText()) + "' where serial=" + Integer.parseInt(this.商品编码.getText()) + ";";
                final PreparedStatement discount_price = DatabaseConnection.getConnection().prepareStatement(sqlString4);
                discount_price.executeUpdate(sqlString4);
                sqlString5 = "update cashshop_modified_items set mark='" + Integer.parseInt(this.商品出售状态.getText()) + "' where serial=" + Integer.parseInt(this.商品编码.getText()) + ";";
                final PreparedStatement mark = DatabaseConnection.getConnection().prepareStatement(sqlString5);
                mark.executeUpdate(sqlString5);
                if (!this.商品库存.getText().equals("")) {
                    final int SN库存 = Getcharacter7("" + Integer.parseInt(this.商品编码.getText()) + "", 2);
                    if (SN库存 == -1) {
                        Gaincharacter7("" + Integer.parseInt(this.商品编码.getText()) + "", 2, -2);
                    }
                    Gaincharacter7("" + Integer.parseInt(this.商品编码.getText()) + "", 2, -SN库存);
                    Gaincharacter7("" + Integer.parseInt(this.商品编码.getText()) + "", 2, Integer.parseInt(this.商品库存.getText()));
                } else {
                    this.删除SN库存2();
                }
                if (!this.商品折扣.getText().equals("")) {
                    final int SN库存 = Getcharacter7("" + Integer.parseInt(this.商品编码.getText()) + "", 3);
                    if (SN库存 == -1) {
                        Gaincharacter7("" + Integer.parseInt(this.商品编码.getText()) + "", 3, -2);
                    }
                    Gaincharacter7("" + Integer.parseInt(this.商品编码.getText()) + "", 3, -SN库存);
                    Gaincharacter7("" + Integer.parseInt(this.商品编码.getText()) + "", 3, Integer.parseInt(this.商品折扣.getText()));
                } else {
                    this.删除SN库存3();
                }
                if (!this.每日限购.getText().equals("")) {
                    final int SN库存 = Getcharacter7("" + Integer.parseInt(this.商品编码.getText()) + "", 4);
                    if (SN库存 == -1) {
                        Gaincharacter7("" + Integer.parseInt(this.商品编码.getText()) + "", 4, -2);
                    }
                    Gaincharacter7("" + Integer.parseInt(this.商品编码.getText()) + "", 4, -SN库存);
                    Gaincharacter7("" + Integer.parseInt(this.商品编码.getText()) + "", 4, Integer.parseInt(this.每日限购.getText()));
                } else {
                    this.删除SN库存4();
                }
                JOptionPane.showMessageDialog(null, "[信息]:修改物品载入成功。");
                final int n = JOptionPane.showConfirmDialog(this, "是否刷新？\r\n刷新所耗时间会根据物品数量，服务器配置决定。", "信息", 0);
                if (n == 0) {
                    this.刷新();
                }
            } else {
                JOptionPane.showMessageDialog(null, "[信息]:只是修改！如果需要添加新的SN编码！请点击添加。");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jButton2ActionPerformed(final ActionEvent evt) {
        final int n = JOptionPane.showConfirmDialog(this, "是否刷新？\r\n刷新所耗时间会根据物品数量，服务器配置决定。", "信息", 0);
        if (n == 0) {
            this.initCharacterPannel();
        }
        JOptionPane.showMessageDialog(null, "[信息]:刷新商城物品列表。");
    }

    private void 添加ActionPerformed(final ActionEvent evt) {
        final boolean result1 = this.商品编码.getText().matches("[0-9]+");
        final boolean result2 = this.商品数量.getText().matches("[0-9]+");
        final boolean result3 = this.商品价格.getText().matches("[0-9]+");
        final boolean result4 = this.商品时间.getText().matches("[0-9]+");
        final boolean result5 = this.商品库存.getText().matches("[0-9]+");
        final boolean result6 = this.每日限购.getText().matches("[0-9]+");
        final boolean result7 = this.商品折扣.getText().matches("[0-9]+");
        final boolean result8 = this.商品代码.getText().matches("[0-9]+");
        if (!result1 && !result2 && !result3 && !result4 && !result5 && !result6 && !result7 && !result8) {
            JOptionPane.showMessageDialog(null, "[信息]:请输入正确的数据。");
            return;
        }
        if (this.商品编码.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "[信息]:请点击商品分类选择添加类型。");
            return;
        }
        if (this.商品代码.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "[信息]:请输入添加的商品代码。");
            return;
        }
        if (this.商品价格.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "[信息]:请输入商品价格。");
            return;
        }
        if (Integer.parseInt(this.商品价格.getText()) > 999999999) {
            JOptionPane.showMessageDialog(null, "[信息]:商品数量不能大于999999999。");
            return;
        }
        if (this.商品时间.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "[信息]:请输入商品的给予时间，0 代表无限制。");
            return;
        }
        if (this.商品数量.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "[信息]:请输入商品的商品数量。");
            return;
        }
        if (Integer.parseInt(this.商品数量.getText()) > 100) {
            JOptionPane.showMessageDialog(null, "[信息]:商品数量不能大于100。");
            return;
        }
        int 商品出售状态2;
        if (this.商品出售状态.getText().equals("")) {
            商品出售状态2 = -1;
        } else {
            商品出售状态2 = Integer.parseInt(this.商品出售状态.getText());
        }
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM cashshop_modified_items WHERE serial = ?");
            ps1.setInt(1, Integer.parseInt(this.商品编码.getText()));
            rs = ps1.executeQuery();
            if (!rs.next()) {
                try (final Connection con = DatabaseConnection.getConnection();
                     final PreparedStatement ps2 = con.prepareStatement("INSERT INTO cashshop_modified_items (serial, showup,itemid,priority,period,gender,count,meso,discount_price,mark, unk_1, unk_2, unk_3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                    ps2.setInt(1, Integer.parseInt(this.商品编码.getText()));
                    ps2.setInt(2, 1);
                    ps2.setInt(3, Integer.parseInt(this.商品代码.getText()));
                    ps2.setInt(4, 0);
                    ps2.setInt(5, Integer.parseInt(this.商品时间.getText()));
                    ps2.setInt(6, 2);
                    ps2.setInt(7, Integer.parseInt(this.商品数量.getText()));
                    ps2.setInt(8, 0);
                    ps2.setInt(9, Integer.parseInt(this.商品价格.getText()));
                    ps2.setInt(10, 商品出售状态2);
                    ps2.setInt(11, 0);
                    ps2.setInt(12, 0);
                    ps2.setInt(13, 0);
                    ps2.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (!this.商品库存.getText().equals("")) {
                    final int SN库存 = Getcharacter7("" + Integer.parseInt(this.商品编码.getText()) + "", 2);
                    if (SN库存 == -1) {
                        Gaincharacter7("" + Integer.parseInt(this.商品编码.getText()) + "", 2, -2);
                    }
                    Gaincharacter7("" + Integer.parseInt(this.商品编码.getText()) + "", 2, -SN库存);
                    Gaincharacter7("" + Integer.parseInt(this.商品编码.getText()) + "", 2, Integer.parseInt(this.商品库存.getText()));
                }
                if (!this.商品折扣.getText().equals("")) {
                    final int SN库存 = Getcharacter7("" + Integer.parseInt(this.商品编码.getText()) + "", 3);
                    if (SN库存 == -1) {
                        Gaincharacter7("" + Integer.parseInt(this.商品编码.getText()) + "", 3, -2);
                    }
                    Gaincharacter7("" + Integer.parseInt(this.商品编码.getText()) + "", 3, -SN库存);
                    Gaincharacter7("" + Integer.parseInt(this.商品编码.getText()) + "", 3, Integer.parseInt(this.商品折扣.getText()));
                }
                JOptionPane.showMessageDialog(null, "[信息]:新物品载入成功。");
                final int n = JOptionPane.showConfirmDialog(this, "是否刷新？\r\n刷新所耗时间会根据物品数量，服务器配置决定。", "信息", 0);
                if (n == 0) {
                    this.刷新();
                }
            } else {
                JOptionPane.showMessageDialog(null, "[信息]:已存在的SN编码无法成功载入。");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jButton28ActionPerformed(final ActionEvent evt) {
        final int n = JOptionPane.showConfirmDialog(this, "确定为[ " + this.商品编码.getText() + " 商品]    下架?", "上架商品提示消息", 0);
        if (n == 0) {
            this.下架();
        }
    }

    private void jButton27ActionPerformed(final ActionEvent evt) {
        final String 输出 = "";
        PreparedStatement ps1 = null;
        ResultSet rs1 = null;
        final int 商城SN编码 = Integer.parseInt(this.商品编码.getText());
        try {
            ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM cashshop_modified_items WHERE serial = ?");
            ps1.setInt(1, 商城SN编码);
            rs1 = ps1.executeQuery();
            if (rs1.next()) {
                final String sqlstr = " delete from cashshop_modified_items where serial =" + 商城SN编码 + ";";
                ps1.executeUpdate(sqlstr);
                JOptionPane.showMessageDialog(null, "[信息]:成功删除商品。");
            } else {
                JOptionPane.showMessageDialog(null, "[信息]:删除商品失败具。");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.删除SN库存();
        final int n = JOptionPane.showConfirmDialog(this, "是否刷新？\r\n刷新所耗时间会根据物品数量，服务器配置决定。", "信息", 0);
        if (n == 0) {
            this.刷新();
        }
    }

    private void jButton25ActionPerformed(final ActionEvent evt) {
        final int n = JOptionPane.showConfirmDialog(this, "确定为[ " + this.商品编码.getText() + " 商品]    上架?", "上架商品提示消息", 0);
        if (n == 0) {
            this.上架();
        }
    }

    private void jButton3ActionPerformed(final ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "[信息]:商城重载开始。");
        CashItemFactory.getInstance().clearCashShop();
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            cserv.broadcastPacket(MaplePacketCreator.serverNotice(0, ";商城重新载入商品成功，维护完毕，开放进入。"));
        }
        JOptionPane.showMessageDialog(null, "[信息]:商城重载成功。");
    }

    private void 显示类型ActionPerformed(final ActionEvent evt) {
    }

    private void 商品出售状态ActionPerformed(final ActionEvent evt) {
    }

    private void 商品编码ActionPerformed(final ActionEvent evt) {
    }

    private void 其他ActionPerformed(final ActionEvent evt) {
        this.读取商品(60200000, 60300000, 5, 3);
    }

    private void 宠物服饰ActionPerformed(final ActionEvent evt) {
        this.读取商品(60100000, 60200000, 5, 2);
    }

    private void 宠物ActionPerformed(final ActionEvent evt) {
        this.读取商品(60000000, 60100000, 5, 1);
    }

    private void 效果ActionPerformed(final ActionEvent evt) {
        this.读取商品(50500000, 50600000, 4, 4);
    }

    private void 游戏ActionPerformed(final ActionEvent evt) {
        this.读取商品(50400000, 50500000, 4, 5);
    }

    private void 纪念日ActionPerformed(final ActionEvent evt) {
        this.读取商品(50300000, 50400000, 4, 6);
    }

    private void 个人商店ActionPerformed(final ActionEvent evt) {
        this.读取商品(50200000, 50300000, 4, 3);
    }

    private void 表情ActionPerformed(final ActionEvent evt) {
        this.读取商品(50100000, 50200000, 4, 2);
    }

    private void 会员卡ActionPerformed(final ActionEvent evt) {
        this.读取商品(50000000, 50100000, 4, 1);
    }

    private void 卷轴ActionPerformed(final ActionEvent evt) {
        this.读取商品(30200000, 30300000, 3, 3);
    }

    private void 通讯物品ActionPerformed(final ActionEvent evt) {
        this.读取商品(30100000, 30200000, 3, 2);
    }

    private void 喜庆物品ActionPerformed(final ActionEvent evt) {
        this.读取商品(30000000, 30100000, 3, 1);
    }

    private void 骑宠ActionPerformed(final ActionEvent evt) {
        this.读取商品(21200000, 21300000, 2, 8);
    }

    private void 披风ActionPerformed(final ActionEvent evt) {
        this.读取商品(21100000, 21200000, 2, 3);
    }

    private void 飞镖ActionPerformed(final ActionEvent evt) {
        this.读取商品(21000000, 21100000, 2, 4);
    }

    private void 戒指ActionPerformed(final ActionEvent evt) {
        this.读取商品(20900000, 21000000, 2, 9);
    }

    private void 武器ActionPerformed(final ActionEvent evt) {
        this.读取商品(20800000, 20900000, 2, 12);
    }

    private void 手套ActionPerformed(final ActionEvent evt) {
        this.读取商品(20700000, 20800000, 2, 11);
    }

    private void 鞋子ActionPerformed(final ActionEvent evt) {
        this.读取商品(20600000, 20700000, 2, 7);
    }

    private void 裙裤ActionPerformed(final ActionEvent evt) {
        this.读取商品(20500000, 20600000, 2, 2);
    }

    private void 上衣ActionPerformed(final ActionEvent evt) {
        this.读取商品(20400000, 20500000, 2, 13);
    }

    private void 长袍ActionPerformed(final ActionEvent evt) {
        this.读取商品(20300000, 20400000, 2, 5);
    }

    private void 眼饰ActionPerformed(final ActionEvent evt) {
        this.读取商品(20200000, 20300000, 2, 10);
    }

    private void 脸饰ActionPerformed(final ActionEvent evt) {
        this.读取商品(20100000, 20200000, 2, 6);
    }

    private void 帽子ActionPerformed(final ActionEvent evt) {
        this.读取商品(20000000, 20100000, 2, 1);
    }

    private void jButton9ActionPerformed(final ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "[信息]:未启用。");
    }

    private void 活动ActionPerformed(final ActionEvent evt) {
        this.读取商品(10200000, 10300000, 1, 3);
    }

    private void 读取热销产品ActionPerformed(final ActionEvent evt) {
        this.读取商品(10000000, 10100000, 1, 1);
    }

    private void 主题馆ActionPerformed(final ActionEvent evt) {
        this.读取商品(10100000, 10200000, 1, 2);
    }

    private void 删除IPActionPerformed(final ActionEvent evt) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result1 = this.删除IP代码.getText().matches("[0-9]+");
        if (result1) {
            if (Integer.parseInt(this.删除IP代码.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "请填写正确的值");
            }
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM ipbans WHERE ipbanid = ?");
                ps1.setInt(1, Integer.parseInt(this.删除IP代码.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from ipbans where ipbanid =" + Integer.parseInt(this.删除IP代码.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    this.刷新封IP();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入数字 ");
        }
    }

    private void 删除MACActionPerformed(final ActionEvent evt) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result1 = this.删MAC代码.getText().matches("[0-9]+");
        if (result1) {
            if (Integer.parseInt(this.删MAC代码.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "请填写正确的值");
            }
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM macbans WHERE macbanid = ?");
                ps1.setInt(1, Integer.parseInt(this.删MAC代码.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from macbans where macbanid =" + Integer.parseInt(this.删MAC代码.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    this.刷新封MAC();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入数字 ");
        }
    }

    private void 刷新封MACActionPerformed(final ActionEvent evt) {
        this.刷新封MAC();
    }

    private void 刷新封IPActionPerformed(final ActionEvent evt) {
        this.刷新封IP();
    }

    private void jButton34ActionPerformed(final ActionEvent evt) {
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("UPDATE guilds SET GP =" + this.家族GP.getText().toString() + " WHERE guildid = " + this.家族ID.getText().toString() + "");
            ps.executeUpdate();
            ps.close();
            System.out.println("update guild gp !");
            this.刷新家族信息();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }

    private void 家族GPActionPerformed(final ActionEvent evt) {
    }

    private void 家族成员5ActionPerformed(final ActionEvent evt) {
    }

    private void 家族成员4ActionPerformed(final ActionEvent evt) {
    }

    private void 家族成员3ActionPerformed(final ActionEvent evt) {
    }

    private void 家族成员2ActionPerformed(final ActionEvent evt) {
    }

    private void 家族族长ActionPerformed(final ActionEvent evt) {
    }

    private void 家族名称ActionPerformed(final ActionEvent evt) {
    }

    private void 家族IDActionPerformed(final ActionEvent evt) {
    }

    private void 刷新家族信息ActionPerformed(final ActionEvent evt) {
        this.刷新家族信息();
    }

    private void 修改技能1ActionPerformed(final ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "[信息]:查看玩家技能信息。");
        this.刷新技能信息();
    }

    private void 删除技能ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result1 = this.技能序号.getText().matches("[0-9]+");
        if (result1) {
            if (Integer.parseInt(this.技能序号.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "请填写正确的值");
            }
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM skills WHERE id = ?");
                ps1.setInt(1, Integer.parseInt(this.技能序号.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from skills where id =" + Integer.parseInt(this.技能序号.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    this.刷新技能信息();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请选择你要删除的技能");
        }
    }

    private void 修改技能ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        final boolean result1 = this.技能序号.getText().matches("[0-9]+");
        if (result1) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE skills SET skilllevel = ?,masterlevel = ? WHERE id = ?");
                ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM skills WHERE id = ?");
                ps2.setInt(1, Integer.parseInt(this.技能序号.getText()));
                rs = ps2.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    String sqlString2 = null;
                    sqlString1 = "update skills set skilllevel='" + this.技能目前等级.getText() + "' where id=" + this.技能序号.getText() + ";";
                    final PreparedStatement skilllevel = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    skilllevel.executeUpdate(sqlString1);
                    sqlString2 = "update skills set masterlevel='" + this.技能最高等级.getText() + "' where id=" + this.技能序号.getText() + ";";
                    final PreparedStatement masterlevel = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    masterlevel.executeUpdate(sqlString2);
                    this.刷新技能信息();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请选择你要修改的技能");
        }
    }

    private void 技能名字ActionPerformed(final ActionEvent evt) {
    }

    private void 删除拍卖行ActionPerformed(final ActionEvent evt) {
    }

    private void 拍卖行物品代码ActionPerformed(final ActionEvent evt) {
    }

    private void 角色金币拍卖行序号ActionPerformed(final ActionEvent evt) {
    }

    private void 拍卖行物品名字ActionPerformed(final ActionEvent evt) {
    }

    private void 删除拍卖行1ActionPerformed(final ActionEvent evt) {
    }

    private void 拍卖行物品代码1ActionPerformed(final ActionEvent evt) {
    }

    private void 角色点券拍卖行序号ActionPerformed(final ActionEvent evt) {
    }

    private void 拍卖行物品名字1ActionPerformed(final ActionEvent evt) {
    }

    private void 删除商城仓库ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result = this.商城仓库物品序号.getText().matches("[0-9]+");
        if (result) {
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM csitems WHERE inventoryitemid = ?");
                ps1.setInt(1, Integer.parseInt(this.商城仓库物品序号.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from csitems where inventoryitemid =" + Integer.parseInt(this.商城仓库物品序号.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    this.刷新角色商城仓库();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请选择你要删除的物品");
        }
    }

    private void 商城仓库物品代码ActionPerformed(final ActionEvent evt) {
    }

    private void 商城仓库物品序号ActionPerformed(final ActionEvent evt) {
    }

    private void 商城仓库物品名字ActionPerformed(final ActionEvent evt) {
    }

    private void 删除游戏仓库ActionPerformed(final ActionEvent evt) {
    }

    private void 游戏仓库物品代码ActionPerformed(final ActionEvent evt) {
    }

    private void 游戏仓库物品序号ActionPerformed(final ActionEvent evt) {
    }

    private void 游戏仓库物品名字ActionPerformed(final ActionEvent evt) {
    }

    private void 删除特殊背包ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result = this.特殊背包物品序号.getText().matches("[0-9]+");
        if (result) {
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM inventoryitems WHERE inventoryitemid = ?");
                ps1.setInt(1, Integer.parseInt(this.特殊背包物品序号.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from inventoryitems where inventoryitemid =" + Integer.parseInt(this.特殊背包物品序号.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    this.刷新角色特殊背包();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请选择你要删除的物品");
        }
    }

    private void 特殊背包物品代码ActionPerformed(final ActionEvent evt) {
    }

    private void 特殊背包物品序号ActionPerformed(final ActionEvent evt) {
    }

    private void 特殊背包物品名字ActionPerformed(final ActionEvent evt) {
    }

    private void 删除其他背包ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result = this.其他背包物品序号.getText().matches("[0-9]+");
        if (result) {
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM inventoryitems WHERE inventoryitemid = ?");
                ps1.setInt(1, Integer.parseInt(this.其他背包物品序号.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from inventoryitems where inventoryitemid =" + Integer.parseInt(this.其他背包物品序号.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    this.刷新角色其他背包();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请选择你要删除的物品");
        }
    }

    private void 其他背包物品代码ActionPerformed(final ActionEvent evt) {
    }

    private void 其他背包物品序号ActionPerformed(final ActionEvent evt) {
    }

    private void 其他背包物品名字ActionPerformed(final ActionEvent evt) {
    }

    private void 删除设置背包ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result = this.设置背包物品序号.getText().matches("[0-9]+");
        if (result) {
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM inventoryitems WHERE inventoryitemid = ?");
                ps1.setInt(1, Integer.parseInt(this.设置背包物品序号.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from inventoryitems where inventoryitemid =" + Integer.parseInt(this.设置背包物品序号.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    this.刷新角色设置背包();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请选择你要删除的物品");
        }
    }

    private void 设置背包物品代码ActionPerformed(final ActionEvent evt) {
    }

    private void 设置背包物品序号ActionPerformed(final ActionEvent evt) {
    }

    private void 设置背包物品名字ActionPerformed(final ActionEvent evt) {
    }

    private void 删除消耗背包ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result = this.消耗背包物品序号.getText().matches("[0-9]+");
        if (result) {
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM inventoryitems WHERE inventoryitemid = ?");
                ps1.setInt(1, Integer.parseInt(this.消耗背包物品序号.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from inventoryitems where inventoryitemid =" + Integer.parseInt(this.消耗背包物品序号.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    this.刷新角色消耗背包();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请选择你要删除的物品");
        }
    }

    private void 消耗背包物品代码ActionPerformed(final ActionEvent evt) {
    }

    private void 消耗背包物品序号ActionPerformed(final ActionEvent evt) {
    }

    private void 消耗背包物品名字ActionPerformed(final ActionEvent evt) {
    }

    private void 删除装备背包ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result = this.装备背包物品序号.getText().matches("[0-9]+");
        if (result) {
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM inventoryitems WHERE inventoryitemid = ?");
                ps1.setInt(1, Integer.parseInt(this.装备背包物品序号.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from inventoryitems where inventoryitemid =" + Integer.parseInt(this.装备背包物品序号.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    this.刷新角色装备背包();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请选择你要删除的物品");
        }
    }

    private void 装备背包物品代码ActionPerformed(final ActionEvent evt) {
    }

    private void 装备背包物品序号ActionPerformed(final ActionEvent evt) {
    }

    private void 装备背包物品名字ActionPerformed(final ActionEvent evt) {
    }

    private void 删除穿戴装备ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result = this.身上穿戴序号1.getText().matches("[0-9]+");
        if (result) {
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM inventoryitems WHERE inventoryitemid = ?");
                ps1.setInt(1, Integer.parseInt(this.身上穿戴序号1.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from inventoryitems where inventoryitemid =" + Integer.parseInt(this.身上穿戴序号1.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    this.刷新角色背包穿戴();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请选择你要删除的物品");
        }
    }

    private void 背包物品代码1ActionPerformed(final ActionEvent evt) {
    }

    private void 身上穿戴序号1ActionPerformed(final ActionEvent evt) {
    }

    private void 背包物品名字1ActionPerformed(final ActionEvent evt) {
    }

    private void 在线角色ActionPerformed(final ActionEvent evt) {
        this.显示在线玩家.setText("在线玩家; " + 在线玩家() + "");
        for (int i = this.角色信息.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.角色信息.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM characters order by id desc");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (Find.findChannel(rs.getString("name")) > 0) {
                    ((DefaultTableModel) this.角色信息.getModel()).insertRow(this.角色信息.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("id")), Integer.valueOf(rs.getInt("accountid")), rs.getString("name"), MapleCarnivalChallenge.getJobNameById(rs.getInt("job")), Integer.valueOf(rs.getInt("level")), Integer.valueOf(rs.getInt("str")), Integer.valueOf(rs.getInt("dex")), Integer.valueOf(rs.getInt("int")), Integer.valueOf(rs.getInt("luk")), Integer.valueOf(rs.getInt("maxhp")), Integer.valueOf(rs.getInt("maxmp")), Integer.valueOf(rs.getInt("meso")), Integer.valueOf(rs.getInt("map")), "在线", Integer.valueOf(rs.getInt("gm")), Integer.valueOf(rs.getInt("hair")), Integer.valueOf(rs.getInt("face"))});
                }
            }
            JOptionPane.showMessageDialog(null, "[信息]:显示游戏所有在线角色信息。");
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void 离线角色ActionPerformed(final ActionEvent evt) {
        this.显示在线玩家.setText("在线玩家; " + 在线玩家() + "");
        for (int i = this.角色信息.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.角色信息.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM characters order by id desc");
            rs = ps.executeQuery();
            while (rs.next()) {
                if (Find.findChannel(rs.getString("name")) <= 0) {
                    ((DefaultTableModel) this.角色信息.getModel()).insertRow(this.角色信息.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("id")), Integer.valueOf(rs.getInt("accountid")), rs.getString("name"), MapleCarnivalChallenge.getJobNameById(rs.getInt("job")), Integer.valueOf(rs.getInt("level")), Integer.valueOf(rs.getInt("str")), Integer.valueOf(rs.getInt("dex")), Integer.valueOf(rs.getInt("int")), Integer.valueOf(rs.getInt("luk")), Integer.valueOf(rs.getInt("maxhp")), Integer.valueOf(rs.getInt("maxmp")), Integer.valueOf(rs.getInt("meso")), Integer.valueOf(rs.getInt("map")), "在线", Integer.valueOf(rs.getInt("gm")), Integer.valueOf(rs.getInt("hair")), Integer.valueOf(rs.getInt("face"))});
                }
            }
            JOptionPane.showMessageDialog(null, "[信息]:显示游戏所有离线角色信息。");
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void 发型ActionPerformed(final ActionEvent evt) {
    }

    private void 脸型ActionPerformed(final ActionEvent evt) {
    }

    private void 卡家族解救ActionPerformed(final ActionEvent evt) {
        this.显示在线玩家.setText("在线玩家; " + 在线玩家() + "");
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        final boolean result1 = this.角色ID.getText().matches("[0-9]+");
        if (result1) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characters SET guildid = ?,guildrank = ?,allianceRank = ? WHERE id = ?");
                ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM characters WHERE id = ?");
                ps2.setInt(1, Integer.parseInt(this.角色ID.getText()));
                rs = ps2.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    String sqlString2 = null;
                    String sqlString3 = null;
                    sqlString1 = "update characters set guildid='0' where id=" + this.角色ID.getText() + ";";
                    final PreparedStatement hair = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    hair.executeUpdate(sqlString1);
                    sqlString2 = "update characters set guildrank='5' where id=" + this.角色ID.getText() + ";";
                    final PreparedStatement face = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    face.executeUpdate(sqlString2);
                    sqlString3 = "update characters set allianceRank='5' where id=" + this.角色ID.getText() + ";";
                    final PreparedStatement allianceRank = DatabaseConnection.getConnection().prepareStatement(sqlString3);
                    allianceRank.executeUpdate(sqlString3);
                    JOptionPane.showMessageDialog(null, "[信息]:解卡家族角色成功。");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:请选择卡家族的角色。");
        }
    }

    private void 查看背包ActionPerformed(final ActionEvent evt) {
        this.显示在线玩家.setText("在线玩家; " + 在线玩家() + "");
        final boolean result1 = this.角色ID.getText().matches("[0-9]+");
        if (!result1) {
            JOptionPane.showMessageDialog(null, "[信息]:请选择角色。");
            return;
        }
        if (this.账号ID.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "[信息]:请先选择账号，再选择账号下的角色，接下来才可以查看游戏仓库。");
            return;
        }
        JOptionPane.showMessageDialog(null, "[信息]:查询速度跟角色信息量有关，请耐心等候。");
        this.刷新角色背包穿戴();
        this.刷新角色装备背包();
        this.刷新角色消耗背包();
        this.刷新角色设置背包();
        this.刷新角色其他背包();
        this.刷新角色特殊背包();
        this.刷新角色游戏仓库();
        this.刷新角色商城仓库();
        JOptionPane.showMessageDialog(null, "[信息]:请转到角色道具信息面板查看。");
    }

    private void 查看技能ActionPerformed(final ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "[信息]:查看玩家技能信息。");
        this.刷新技能信息();
    }

    private void 卡号自救2ActionPerformed(final ActionEvent evt) {
        this.显示在线玩家.setText("在线玩家; " + 在线玩家() + "");
        final String 输出 = "";
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result1 = this.角色ID.getText().matches("[0-9]+");
        if (result1) {
            final int n = JOptionPane.showConfirmDialog(this, "你确定要解卡物品自救这个角色吗？", "信息", 0);
            if (n != 0) {
                return;
            }
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM inventoryitems WHERE characterid = ?");
                ps1.setInt(1, Integer.parseInt(this.角色ID.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr2 = " delete from inventoryitems where characterid =" + Integer.parseInt(this.角色ID.getText()) + "";
                    ps1.executeUpdate(sqlstr2);
                    JOptionPane.showMessageDialog(null, "[信息]:角色已经进行38处理。");
                    this.刷新角色信息();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:请选择要38处理的角色。");
        }
    }

    private void 卡号自救1ActionPerformed(final ActionEvent evt) {
        this.显示在线玩家.setText("在线玩家; " + 在线玩家() + "");
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        final boolean result1 = this.角色ID.getText().matches("[0-9]+");
        if (result1) {
            final int n = JOptionPane.showConfirmDialog(this, "你确定要解卡发型脸型自救这个角色吗？", "信息", 0);
            if (n != 0) {
                return;
            }
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characters SET hair = ?,face = ? WHERE id = ?");
                ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM characters WHERE id = ?");
                ps2.setInt(1, Integer.parseInt(this.角色ID.getText()));
                rs = ps2.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    String sqlString2 = null;
                    sqlString1 = "update characters set hair='30000' where id=" + this.角色ID.getText() + ";";
                    final PreparedStatement hair = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    hair.executeUpdate(sqlString1);
                    sqlString2 = "update characters set face='20000' where id=" + this.角色ID.getText() + ";";
                    final PreparedStatement face = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    face.executeUpdate(sqlString2);
                    JOptionPane.showMessageDialog(null, "[信息]:解救成功，发型脸型初始化。");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:请选择卡发型脸型的角色。");
        }
    }

    private void 角色IDActionPerformed(final ActionEvent evt) {
    }

    private void GMActionPerformed(final ActionEvent evt) {
    }

    private void 地图ActionPerformed(final ActionEvent evt) {
    }

    private void 金币1ActionPerformed(final ActionEvent evt) {
    }

    private void MPActionPerformed(final ActionEvent evt) {
    }

    private void HPActionPerformed(final ActionEvent evt) {
    }

    private void 运气ActionPerformed(final ActionEvent evt) {
    }

    private void 智力ActionPerformed(final ActionEvent evt) {
    }

    private void 敏捷ActionPerformed(final ActionEvent evt) {
    }

    private void 力量ActionPerformed(final ActionEvent evt) {
    }

    private void 等级ActionPerformed(final ActionEvent evt) {
    }

    private void 角色昵称ActionPerformed(final ActionEvent evt) {
    }

    private void 删除角色ActionPerformed(final ActionEvent evt) {
        final String 输出 = "";
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result1 = this.角色ID.getText().matches("[0-9]+");
        if (result1) {
            final int n = JOptionPane.showConfirmDialog(this, "你确定要删除这个角色吗？", "信息", 0);
            if (n == 0) {
                try {
                    ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM characters WHERE id = ?");
                    ps1.setInt(1, Integer.parseInt(this.角色ID.getText()));
                    rs = ps1.executeQuery();
                    if (rs.next()) {
                        final String sqlstr = " delete from characters where id =" + Integer.parseInt(this.角色ID.getText()) + "";
                        ps1.executeUpdate(sqlstr);
                        final String sqlstr2 = " delete from inventoryitems where characterid =" + Integer.parseInt(this.角色ID.getText()) + "";
                        ps1.executeUpdate(sqlstr2);
                        final String sqlstr3 = " delete from auctionitems where characterid =" + Integer.parseInt(this.角色ID.getText()) + "";
                        ps1.executeUpdate(sqlstr3);
                        final String sqlstr4 = " delete from auctionitems1 where characterid =" + Integer.parseInt(this.角色ID.getText()) + "";
                        ps1.executeUpdate(sqlstr4);
                        final String sqlstr5 = " delete from csitems where accountid =" + Integer.parseInt(this.角色ID.getText()) + "";
                        ps1.executeUpdate(sqlstr5);
                        final String sqlstr6 = " delete from bank_item where cid =" + Integer.parseInt(this.角色ID.getText()) + "";
                        ps1.executeUpdate(sqlstr6);
                        final String sqlstr7 = " delete from bossrank where cid =" + Integer.parseInt(this.角色ID.getText()) + "";
                        ps1.executeUpdate(sqlstr7);
                        final String sqlstr8 = " delete from skills where characterid =" + Integer.parseInt(this.角色ID.getText()) + "";
                        ps1.executeUpdate(sqlstr8);
                        JOptionPane.showMessageDialog(null, "[信息]:成功删除角色 " + Integer.parseInt(this.角色ID.getText()) + " ，以及所有相关信息。");
                        this.刷新角色信息();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:请选择删除的角色。");
        }
    }

    private void jButton38ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        final boolean A = this.等级.getText().matches("[0-9]+");
        final boolean B = this.GM.getText().matches("[0-9]+");
        final boolean C = this.地图.getText().matches("[0-9]+");
        final boolean D = this.金币1.getText().matches("[0-9]+");
        final boolean E = this.MP.getText().matches("[0-9]+");
        final boolean F = this.HP.getText().matches("[0-9]+");
        final boolean G = this.运气.getText().matches("[0-9]+");
        final boolean H = this.智力.getText().matches("[0-9]+");
        final boolean Y = this.敏捷.getText().matches("[0-9]+");
        final boolean J = this.力量.getText().matches("[0-9]+");
        if (this.角色昵称.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "角色昵称不能留空");
            return;
        }
        if (Find.findChannel(this.角色昵称.getText()) > 0) {
            JOptionPane.showMessageDialog(null, "请先将角色离线后再修改。");
            return;
        }
        final int n = JOptionPane.showConfirmDialog(this, "你确定要修改这个角色吗？", "信息", 0);
        if (n != 0) {
            return;
        }
        try {
            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE characters SET `name` = ?,level = ?, str = ?, dex = ?, luk = ?,int = ?,  maxhp = ?, maxmp = ?, meso = ?, map = ?, gm = ?, hair = ?, face = ? WHERE id = ?");
            ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM characters WHERE id = ?");
            ps2.setInt(1, Integer.parseInt(this.角色ID.getText()));
            rs = ps2.executeQuery();
            if (rs.next()) {
                String sqlString1 = null;
                String sqlString2 = null;
                String sqlString3 = null;
                String sqlString4 = null;
                String sqlString5 = null;
                String sqlString6 = null;
                String sqlString7 = null;
                String sqlString8 = null;
                String sqlString9 = null;
                String sqlString10 = null;
                String sqlString11 = null;
                String sqlString12 = null;
                String sqlString13 = null;
                sqlString1 = "update characters set name='" + this.角色昵称.getText() + "' where id=" + this.角色ID.getText() + ";";
                final PreparedStatement name = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                name.executeUpdate(sqlString1);
                sqlString2 = "update characters set level='" + this.等级.getText() + "' where id=" + this.角色ID.getText() + ";";
                final PreparedStatement level = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                level.executeUpdate(sqlString2);
                sqlString3 = "update characters set str='" + this.力量.getText() + "' where id=" + this.角色ID.getText() + ";";
                final PreparedStatement str = DatabaseConnection.getConnection().prepareStatement(sqlString3);
                str.executeUpdate(sqlString3);
                sqlString4 = "update characters set dex='" + this.敏捷.getText() + "' where id=" + this.角色ID.getText() + ";";
                final PreparedStatement dex = DatabaseConnection.getConnection().prepareStatement(sqlString4);
                dex.executeUpdate(sqlString4);
                sqlString5 = "update characters set luk='" + this.智力.getText() + "' where id=" + this.角色ID.getText() + ";";
                final PreparedStatement luk = DatabaseConnection.getConnection().prepareStatement(sqlString5);
                luk.executeUpdate(sqlString5);
                sqlString6 = "update characters set `int`='" + this.运气.getText() + "' where id=" + this.角色ID.getText() + ";";
                final PreparedStatement executeUpdate = DatabaseConnection.getConnection().prepareStatement(sqlString6);
                executeUpdate.executeUpdate(sqlString6);
                sqlString7 = "update characters set maxhp='" + this.HP.getText() + "' where id=" + this.角色ID.getText() + ";";
                final PreparedStatement maxhp = DatabaseConnection.getConnection().prepareStatement(sqlString7);
                maxhp.executeUpdate(sqlString7);
                sqlString8 = "update characters set maxmp='" + this.MP.getText() + "' where id=" + this.角色ID.getText() + ";";
                final PreparedStatement maxmp = DatabaseConnection.getConnection().prepareStatement(sqlString8);
                maxmp.executeUpdate(sqlString8);
                sqlString9 = "update characters set meso='" + this.金币1.getText() + "' where id=" + this.角色ID.getText() + ";";
                final PreparedStatement meso = DatabaseConnection.getConnection().prepareStatement(sqlString9);
                meso.executeUpdate(sqlString9);
                sqlString10 = "update characters set map='" + this.地图.getText() + "' where id=" + this.角色ID.getText() + ";";
                final PreparedStatement map = DatabaseConnection.getConnection().prepareStatement(sqlString10);
                map.executeUpdate(sqlString10);
                sqlString11 = "update characters set gm='" + this.GM.getText() + "' where id=" + this.角色ID.getText() + ";";
                final PreparedStatement gm = DatabaseConnection.getConnection().prepareStatement(sqlString11);
                gm.executeUpdate(sqlString11);
                sqlString12 = "update characters set hair='" + this.发型.getText() + "' where id=" + this.发型.getText() + ";";
                final PreparedStatement hair = DatabaseConnection.getConnection().prepareStatement(sqlString12);
                hair.executeUpdate(sqlString12);
                sqlString13 = "update characters set face='" + this.脸型.getText() + "' where id=" + this.脸型.getText() + ";";
                final PreparedStatement face = DatabaseConnection.getConnection().prepareStatement(sqlString13);
                face.executeUpdate(sqlString13);
                JOptionPane.showMessageDialog(null, "[信息]:角色信息修改成功。");
                this.刷新角色信息();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void 显示管理角色ActionPerformed(final ActionEvent evt) {
        this.显示在线玩家.setText("在线玩家; " + 在线玩家() + "");
        for (int i = this.角色信息.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.角色信息.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM characters  WHERE gm >0 ");
            rs = ps.executeQuery();
            while (rs.next()) {
                String 在线 = "";
                if (Find.findChannel(rs.getString("name")) > 0) {
                    在线 = "在线";
                } else {
                    在线 = "离线";
                }
                ((DefaultTableModel) this.角色信息.getModel()).insertRow(this.角色信息.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("id")), Integer.valueOf(rs.getInt("accountid")), rs.getString("name"), MapleCarnivalChallenge.getJobNameById(rs.getInt("job")), Integer.valueOf(rs.getInt("level")), Integer.valueOf(rs.getInt("str")), Integer.valueOf(rs.getInt("dex")), Integer.valueOf(rs.getInt("int")), Integer.valueOf(rs.getInt("luk")), Integer.valueOf(rs.getInt("maxhp")), Integer.valueOf(rs.getInt("maxmp")), Integer.valueOf(rs.getInt("meso")), Integer.valueOf(rs.getInt("map")), 在线, Integer.valueOf(rs.getInt("gm")), Integer.valueOf(rs.getInt("hair")), Integer.valueOf(rs.getInt("face"))});
            }
            JOptionPane.showMessageDialog(null, "[信息]:显示游戏所有管理员角色信息。");
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void 刷新角色信息ActionPerformed(final ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "[信息]:显示游戏所有玩家角色信息。");
        this.刷新角色信息();
        this.显示在线玩家.setText("在线玩家; " + 在线玩家() + "");
    }

    private void jButton12ActionPerformed(final ActionEvent evt) {
        this.显示在线账号.setText("账号在线; " + 在线账号() + "");
        this.查找账号();
    }

    private void 解卡ActionPerformed(final ActionEvent evt) {
        this.显示在线账号.setText("账号在线; " + 在线账号() + "");
        if (this.账号操作.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "请输入需要解卡的账号 ");
            return;
        }
        final String account = this.账号操作.getText();
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("Update accounts set loggedin = ? Where name = ?");
            ps.setInt(1, 0);
            ps.setString(2, account);
            ps.execute();
            ps.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "错误!\r\n" + ex);
        }
        this.账号提示语言.setText("[信息]:解卡账号 " + this.账号操作.getText() + " 成功。");
        this.刷新账号信息();
    }

    private void 封锁账号ActionPerformed(final ActionEvent evt) {
        this.显示在线账号.setText("账号在线; " + 在线账号() + "");
        if (this.账号操作.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "请输入需要封锁的账号 ");
            return;
        }
        final String account = this.账号操作.getText();
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("Update accounts set banned = ? Where name = ?");
            ps.setInt(1, 1);
            ps.setString(2, account);
            ps.execute();
            ps.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "错误!\r\n" + ex);
        }
        this.账号提示语言.setText("[信息]:封锁账号 " + this.账号操作.getText() + " 成功。");
        this.刷新账号信息();
    }

    private void 删除账号ActionPerformed(final ActionEvent evt) {
        this.显示在线账号.setText("账号在线; " + 在线账号() + "");
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            final int n = JOptionPane.showConfirmDialog(this, "你确定要删除这个账号吗？", "信息", 0);
            if (n == 0) {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM accounts ");
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " Delete from accounts where name ='" + this.账号操作.getText() + "'";
                    this.账号提示语言.setText("[信息]:删除账号 " + this.账号操作.getText() + " 成功。");
                    ps1.executeUpdate(sqlstr);
                    this.刷新账号信息();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void 在线账号ActionPerformed(final ActionEvent evt) {
        this.显示在线账号.setText("账号在线; " + 在线账号() + "");
        this.账号提示语言.setText("[信息]:显示游戏所有在线玩家账号信息。");
        for (int i = this.账号信息.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.账号信息.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM accounts  WHERE loggedin > 0 ");
            rs = ps.executeQuery();
            while (rs.next()) {
                String 封号 = "";
                if (rs.getInt("banned") == 0) {
                    封号 = "正常";
                } else {
                    封号 = "封禁";
                }
                String 在线 = "";
                if (rs.getInt("loggedin") == 0) {
                    在线 = "不在线";
                } else {
                    在线 = "在线";
                }
                String QQ = "";
                if (rs.getString("qq") != null) {
                    QQ = rs.getString("qq");
                } else {
                    QQ = "未绑定QQ";
                }
                ((DefaultTableModel) this.账号信息.getModel()).insertRow(this.账号信息.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("id")), rs.getString("name"), rs.getString("SessionIP"), rs.getString("macs"), QQ, Integer.valueOf(rs.getInt("ACash")), Integer.valueOf(rs.getInt("mPoints")), rs.getString("lastlogin"), 在线, 封号, Integer.valueOf(rs.getInt("gm"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.读取显示账号();
    }

    private void 已封账号ActionPerformed(final ActionEvent evt) {
        this.显示在线账号.setText("账号在线; " + 在线账号() + "");
        this.账号提示语言.setText("[信息]:显示游戏所有已被封禁的玩家账号信息。");
        for (int i = this.账号信息.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.账号信息.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM accounts WHERE banned > 0 ");
            rs = ps.executeQuery();
            while (rs.next()) {
                String 封号 = "";
                if (rs.getInt("banned") == 0) {
                    封号 = "正常";
                } else {
                    封号 = "封禁";
                }
                String 在线 = "";
                if (rs.getInt("loggedin") == 0) {
                    在线 = "不在线";
                } else {
                    在线 = "在线";
                }
                String QQ = "";
                if (rs.getString("qq") != null) {
                    QQ = rs.getString("qq");
                } else {
                    QQ = "未绑定QQ";
                }
                ((DefaultTableModel) this.账号信息.getModel()).insertRow(this.账号信息.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("id")), rs.getString("name"), rs.getString("SessionIP"), rs.getString("macs"), QQ, Integer.valueOf(rs.getInt("ACash")), Integer.valueOf(rs.getInt("mPoints")), rs.getString("lastlogin"), 在线, 封号, Integer.valueOf(rs.getInt("gm"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.读取显示账号();
    }

    private void 解封ActionPerformed(final ActionEvent evt) {
        this.显示在线账号.setText("账号在线; " + 在线账号() + "");
        if (this.账号操作.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "请输入需要解封的账号 ");
            return;
        }
        final String account = this.账号操作.getText();
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("Update accounts set banned = ? Where name = ?");
            ps.setInt(1, 0);
            ps.setString(2, account);
            ps.execute();
            ps.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "错误!\r\n" + ex);
        }
        this.账号提示语言.setText("[信息]:解封账号 " + account + " 成功。");
        this.刷新账号信息();
    }

    private void 离线账号ActionPerformed(final ActionEvent evt) {
        this.显示在线账号.setText("账号在线; " + 在线账号() + "");
        this.账号提示语言.setText("[信息]:显示游戏所有离线玩家账号信息。");
        for (int i = this.账号信息.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.账号信息.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM accounts  WHERE loggedin = 0 ");
            rs = ps.executeQuery();
            while (rs.next()) {
                String 封号 = "";
                if (rs.getInt("banned") == 0) {
                    封号 = "正常";
                } else {
                    封号 = "封禁";
                }
                String 在线 = "";
                if (rs.getInt("loggedin") == 0) {
                    在线 = "不在线";
                } else {
                    在线 = "在线";
                }
                String QQ = "";
                if (rs.getString("qq") != null) {
                    QQ = rs.getString("qq");
                } else {
                    QQ = "未绑定QQ";
                }
                ((DefaultTableModel) this.账号信息.getModel()).insertRow(this.账号信息.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("id")), rs.getString("name"), rs.getString("SessionIP"), rs.getString("macs"), QQ, Integer.valueOf(rs.getInt("ACash")), Integer.valueOf(rs.getInt("mPoints")), rs.getString("lastlogin"), 在线, 封号, Integer.valueOf(rs.getInt("gm"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.读取显示账号();
    }

    private void 刷新账号信息ActionPerformed(final ActionEvent evt) {
        this.账号提示语言.setText("[信息]:显示游戏所有玩家账号信息。");
        this.刷新账号信息();
        this.显示在线账号.setText("账号在线; " + 在线账号() + "");
    }

    private void jButton32ActionPerformed(final ActionEvent evt) {
        this.ChangePassWord();
    }

    private void jButton35ActionPerformed(final ActionEvent evt) {
        this.注册新账号();
    }

    private void 注册的密码ActionPerformed(final ActionEvent evt) {
    }

    private void 注册的账号ActionPerformed(final ActionEvent evt) {
    }

    private void 修改账号点券抵用ActionPerformed(final ActionEvent evt) {
        final boolean result1 = this.点券.getText().matches("[0-9]+");
        final boolean result2 = this.抵用.getText().matches("[0-9]+");
        final boolean result3 = this.管理1.getText().matches("[0-9]+");
        final boolean result4 = this.QQ.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        if (result1 && result2 && result3 && result4) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE accounts SET ACash = ?, mPoints = ?, gm = ?, qq = ? WHERE id = ?");
                ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM accounts  WHERE id = ? ");
                ps2.setInt(1, Integer.parseInt(this.账号ID.getText()));
                rs = ps2.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    String sqlString3 = null;
                    String sqlString4 = null;
                    String sqlString5 = null;
                    sqlString2 = "update accounts set ACash=" + Integer.parseInt(this.点券.getText()) + " where id ='" + Integer.parseInt(this.账号ID.getText()) + "';";
                    final PreparedStatement priority = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    priority.executeUpdate(sqlString2);
                    sqlString3 = "update accounts set mPoints=" + Integer.parseInt(this.抵用.getText()) + " where id='" + Integer.parseInt(this.账号ID.getText()) + "';";
                    final PreparedStatement period = DatabaseConnection.getConnection().prepareStatement(sqlString3);
                    period.executeUpdate(sqlString3);
                    sqlString4 = "update accounts set gm=" + Integer.parseInt(this.管理1.getText()) + " where id='" + Integer.parseInt(this.账号ID.getText()) + "';";
                    final PreparedStatement gm = DatabaseConnection.getConnection().prepareStatement(sqlString4);
                    gm.executeUpdate(sqlString4);
                    sqlString5 = "update accounts set qq=" + Integer.parseInt(this.QQ.getText()) + " where id='" + Integer.parseInt(this.账号ID.getText()) + "';";
                    final PreparedStatement qq = DatabaseConnection.getConnection().prepareStatement(sqlString5);
                    qq.executeUpdate(sqlString5);
                    this.刷新账号信息();
                    this.账号提示语言.setText("[信息]:修改账号 " + this.账号操作.getText() + " / 点券→" + Integer.parseInt(this.点券.getText()) + " / 抵用券→" + Integer.parseInt(this.抵用.getText()) + " 成功。");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.账号提示语言.setText("[信息]:请选择要修改的账号,数据不能为空，或者数值填写不对。");
        }
    }

    private void 钓鱼物品序号ActionPerformed(final ActionEvent evt) {
    }

    private void 删除钓鱼物品ActionPerformed(final ActionEvent evt) {
        final String 输出 = "";
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result1 = this.钓鱼物品序号.getText().matches("[0-9]+");
        if (result1) {
            try {
                for (int i = this.钓鱼物品.getModel().getRowCount() - 1; i >= 0; --i) {
                    ((DefaultTableModel) this.钓鱼物品.getModel()).removeRow(i);
                }
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM 钓鱼物品 WHERE id = ?");
                ps1.setInt(1, Integer.parseInt(this.钓鱼物品序号.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from 钓鱼物品 where id =" + Integer.parseInt(this.钓鱼物品序号.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    JOptionPane.showMessageDialog(null, "[信息]:删除钓鱼奖励物品成功。");
                    this.刷新钓鱼();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:请选择你要删除的钓鱼物品。");
        }
    }

    private void 新增钓鱼物品ActionPerformed(final ActionEvent evt) {
        final boolean result1 = this.钓鱼物品代码.getText().matches("[0-9]+");
        final boolean result2 = this.钓鱼物品概率.getText().matches("[0-9]+");
        if (result1 && result2) {
            if (Integer.parseInt(this.钓鱼物品代码.getText()) < 0 && Integer.parseInt(this.钓鱼物品概率.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "[信息]:请填写正确的值。");
                return;
            }
            try (final Connection con = DatabaseConnection.getConnection();
                 final PreparedStatement ps = con.prepareStatement("INSERT INTO 钓鱼物品 (itemid, chance ,expiration) VALUES (?, ?, ?)")) {
                ps.setInt(1, Integer.parseInt(this.钓鱼物品代码.getText()));
                ps.setInt(2, Integer.parseInt(this.钓鱼物品概率.getText()));
                ps.setInt(3, 1);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "[信息]:新增钓鱼奖励成功。");
                this.刷新钓鱼();
            } catch (SQLException ex) {
                Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:请输入<物品代码><概率>。");
        }
    }

    private void 钓鱼物品代码ActionPerformed(final ActionEvent evt) {
    }

    private void 刷新钓鱼物品ActionPerformed(final ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "[信息]:刷新钓鱼奖励成功。");
        this.刷新钓鱼();
    }

    private void 修改钓鱼物品ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        final boolean result1 = this.钓鱼物品序号.getText().matches("[0-9]+");
        if (result1) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE 钓鱼物品 SET itemid = ?,chance = ?WHERE id = ?");
                ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM 钓鱼物品 WHERE id = ?");
                ps2.setInt(1, Integer.parseInt(this.钓鱼物品序号.getText()));
                rs = ps2.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    String sqlString2 = null;
                    sqlString1 = "update 钓鱼物品 set itemid='" + this.钓鱼物品代码.getText() + "' where id=" + this.钓鱼物品序号.getText() + ";";
                    final PreparedStatement name = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    name.executeUpdate(sqlString1);
                    sqlString2 = "update 钓鱼物品 set chance='" + this.钓鱼物品概率.getText() + "' where id=" + this.钓鱼物品序号.getText() + ";";
                    final PreparedStatement level = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    level.executeUpdate(sqlString2);
                    JOptionPane.showMessageDialog(null, "修改钓鱼物品成功。");
                    this.刷新钓鱼();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:输入<物品代码><概率>。");
        }
    }

    private void 删除指定的掉落按键ActionPerformed(final ActionEvent evt) {
        final String 输出 = "";
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result = this.删除指定的掉落.getText().matches("[0-9]+");
        if (result) {
            final int 商城SN编码 = Integer.parseInt(this.删除指定的掉落.getText());
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM drop_data WHERE itemid = ?");
                ps1.setInt(1, 商城SN编码);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from drop_data where itemid =" + 商城SN编码 + "";
                    ps1.executeUpdate(sqlstr);
                    JOptionPane.showMessageDialog(null, "[信息]:成功删除 " + 商城SN编码 + " 物品。");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.刷新怪物爆物();
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:请输入你要查找的物品代码。");
        }
    }

    private void 查询物品掉落ActionPerformed(final ActionEvent evt) {
        final boolean result = this.查询物品掉落代码.getText().matches("[0-9]+");
        if (result) {
            if (Integer.parseInt(this.查询物品掉落代码.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "[信息]:请填写正确的值。");
                return;
            }
            for (int i = this.怪物爆物.getModel().getRowCount() - 1; i >= 0; --i) {
                ((DefaultTableModel) this.怪物爆物.getModel()).removeRow(i);
            }
            try {
                final Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;
                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM drop_data WHERE itemid =  " + Integer.parseInt(this.查询物品掉落代码.getText()) + "");
                rs = ps.executeQuery();
                while (rs.next()) {
                    ((DefaultTableModel) this.怪物爆物.getModel()).insertRow(this.怪物爆物.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("id")), Integer.valueOf(rs.getInt("dropperid")), Integer.valueOf(rs.getInt("itemid")), Integer.valueOf(rs.getInt("chance")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.怪物爆物.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    final int i = 怪物爆物.getSelectedRow();
                    final String a = 怪物爆物.getValueAt(i, 0).toString();
                    final String a2 = 怪物爆物.getValueAt(i, 1).toString();
                    final String a3 = 怪物爆物.getValueAt(i, 2).toString();
                    final String a4 = 怪物爆物.getValueAt(i, 3).toString();
                    怪物爆物序列号.setText(a);
                    怪物爆物怪物代码.setText(a2);
                    怪物爆物物品代码.setText(a3);
                    怪物爆物爆率.setText(a4);
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:请输入你要查找的物品代码。");
        }
    }

    private void 查询物品掉落代码1ActionPerformed(final ActionEvent evt) {
    }

    private void 刷新世界爆物ActionPerformed(final ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "[信息]:刷新世界物品掉落数据。");
        this.刷新世界爆物();
    }

    private void 修改世界爆物ActionPerformed(final ActionEvent evt) {
        final boolean result2 = this.世界爆物物品代码.getText().matches("[0-9]+");
        final boolean result3 = this.世界爆物爆率.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        if (result2 && result3) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE drop_data_global SET dropperid = ?, itemid = ?, chance = ? WHERE id = ?");
                ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM drop_data_global WHERE id = ?");
                ps2.setInt(1, Integer.parseInt(this.世界爆物序列号.getText()));
                rs = ps2.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    String sqlString3 = null;
                    sqlString2 = "update drop_data_global set itemid='" + this.世界爆物物品代码.getText() + "' where id=" + this.世界爆物序列号.getText() + ";";
                    final PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                    sqlString3 = "update drop_data_global set chance='" + this.世界爆物爆率.getText() + "' where id=" + this.世界爆物序列号.getText() + ";";
                    final PreparedStatement itemid = DatabaseConnection.getConnection().prepareStatement(sqlString3);
                    itemid.executeUpdate(sqlString3);
                    JOptionPane.showMessageDialog(null, "[信息]:修改成功。");
                    this.刷新世界爆物();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:请选择你要修改的数据。");
        }
    }

    private void 世界爆物名称ActionPerformed(final ActionEvent evt) {
    }

    private void 删除世界爆物ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result = this.世界爆物序列号.getText().matches("[0-9]+");
        if (result) {
            final int 商城SN编码 = Integer.parseInt(this.世界爆物序列号.getText());
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM drop_data_global WHERE id = ?");
                ps1.setInt(1, 商城SN编码);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from drop_data_global where id =" + 商城SN编码 + "";
                    ps1.executeUpdate(sqlstr);
                    JOptionPane.showMessageDialog(null, "[信息]:删除成功。");
                    this.刷新世界爆物();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:请选择你要删除的物品。");
        }
    }

    private void 添加世界爆物ActionPerformed(final ActionEvent evt) {
        final boolean result1 = this.世界爆物物品代码.getText().matches("[0-9]+");
        final boolean result2 = this.世界爆物爆率.getText().matches("[0-9]+");
        if (result1 && result2) {
            if (Integer.parseInt(this.世界爆物物品代码.getText()) < 0 && Integer.parseInt(this.世界爆物爆率.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "[信息]:请填写正确的值。");
                return;
            }
            final PreparedStatement ps1 = null;
            final ResultSet rs = null;
            try (final Connection con = DatabaseConnection.getConnection();
                 final PreparedStatement ps2 = con.prepareStatement("INSERT INTO drop_data_global (continent,dropType,itemid,minimum_quantity,maximum_quantity,questid,chance) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
                ps2.setInt(1, 1);
                ps2.setInt(2, 1);
                ps2.setInt(3, Integer.parseInt(this.世界爆物物品代码.getText()));
                ps2.setInt(4, 1);
                ps2.setInt(5, 1);
                ps2.setInt(6, 0);
                ps2.setInt(7, Integer.parseInt(this.世界爆物爆率.getText()));
                ps2.executeUpdate();
                JOptionPane.showMessageDialog(null, "[信息]:世界爆物添加成功。");
                this.刷新世界爆物();
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:请输入<物品代码>，<物品爆率> 。");
        }
    }

    private void 世界爆物爆率ActionPerformed(final ActionEvent evt) {
    }

    private void 世界爆物物品代码ActionPerformed(final ActionEvent evt) {
    }

    private void 世界爆物序列号ActionPerformed(final ActionEvent evt) {
    }

    private void 查询怪物掉落ActionPerformed(final ActionEvent evt) {
        final boolean result = this.查询怪物掉落代码.getText().matches("[0-9]+");
        if (result) {
            if (Integer.parseInt(this.查询怪物掉落代码.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "[信息]:请填写正确的值。");
                return;
            }
            for (int i = this.怪物爆物.getModel().getRowCount() - 1; i >= 0; --i) {
                ((DefaultTableModel) this.怪物爆物.getModel()).removeRow(i);
            }
            try {
                final Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;
                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM drop_data WHERE dropperid =  " + Integer.parseInt(this.查询怪物掉落代码.getText()) + " && itemid !=0");
                rs = ps.executeQuery();
                while (rs.next()) {
                    ((DefaultTableModel) this.怪物爆物.getModel()).insertRow(this.怪物爆物.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("id")), Integer.valueOf(rs.getInt("dropperid")), Integer.valueOf(rs.getInt("itemid")), Integer.valueOf(rs.getInt("chance")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.怪物爆物.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    final int i = 怪物爆物.getSelectedRow();
                    final String a = 怪物爆物.getValueAt(i, 0).toString();
                    final String a2 = 怪物爆物.getValueAt(i, 1).toString();
                    final String a3 = 怪物爆物.getValueAt(i, 2).toString();
                    final String a4 = 怪物爆物.getValueAt(i, 3).toString();
                    final String a5 = 怪物爆物.getValueAt(i, 4).toString();
                    怪物爆物序列号.setText(a);
                    怪物爆物怪物代码.setText(a2);
                    怪物爆物物品代码.setText(a3);
                    怪物爆物爆率.setText(a4);
                    怪物爆物物品名称.setText(a5);
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:请输入你要查找的怪物代码。");
        }
    }

    private void 刷新怪物卡片ActionPerformed(final ActionEvent evt) {
        this.刷新怪物卡片();
    }

    private void 删除指定的掉落按键1ActionPerformed(final ActionEvent evt) {
        final String 输出 = "";
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result = this.删除指定的掉落.getText().matches("[0-9]+");
        if (result) {
            final int 商城SN编码 = Integer.parseInt(this.删除指定的掉落.getText());
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM drop_data WHERE itemid = ?");
                ps1.setInt(1, 商城SN编码);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from drop_data where itemid =" + 商城SN编码 + "";
                    ps1.executeUpdate(sqlstr);
                    JOptionPane.showMessageDialog(null, "[信息]:成功删除 " + 商城SN编码 + " 物品。");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.刷新怪物爆物();
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:请输入你要查找的物品代码。");
        }
    }

    private void 查询物品掉落1ActionPerformed(final ActionEvent evt) {
        final boolean result = this.查询物品掉落代码.getText().matches("[0-9]+");
        if (result) {
            if (Integer.parseInt(this.查询物品掉落代码.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "[信息]:请填写正确的值。");
                return;
            }
            for (int i = this.怪物爆物.getModel().getRowCount() - 1; i >= 0; --i) {
                ((DefaultTableModel) this.怪物爆物.getModel()).removeRow(i);
            }
            try {
                final Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;
                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM drop_data WHERE itemid =  " + Integer.parseInt(this.查询物品掉落代码.getText()) + "");
                rs = ps.executeQuery();
                while (rs.next()) {
                    ((DefaultTableModel) this.怪物爆物.getModel()).insertRow(this.怪物爆物.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("id")), Integer.valueOf(rs.getInt("dropperid")), Integer.valueOf(rs.getInt("itemid")), Integer.valueOf(rs.getInt("chance")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.怪物爆物.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    final int i = 怪物爆物.getSelectedRow();
                    final String a = 怪物爆物.getValueAt(i, 0).toString();
                    final String a2 = 怪物爆物.getValueAt(i, 1).toString();
                    final String a3 = 怪物爆物.getValueAt(i, 2).toString();
                    final String a4 = 怪物爆物.getValueAt(i, 3).toString();
                    怪物爆物序列号.setText(a);
                    怪物爆物怪物代码.setText(a2);
                    怪物爆物物品代码.setText(a3);
                    怪物爆物爆率.setText(a4);
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:请输入你要查找的物品代码。");
        }
    }

    private void 查询物品掉落代码ActionPerformed(final ActionEvent evt) {
    }

    private void 刷新怪物爆物ActionPerformed(final ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "[信息]:刷新怪物物品掉落数据。");
        this.刷新怪物爆物();
    }

    private void 修改怪物爆物ActionPerformed(final ActionEvent evt) {
        final boolean result1 = this.怪物爆物怪物代码.getText().matches("[0-9]+");
        final boolean result2 = this.怪物爆物物品代码.getText().matches("[0-9]+");
        final boolean result3 = this.怪物爆物爆率.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        if (result1 && result2 && result3) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE drop_data SET dropperid = ?, itemid = ?, chance = ? WHERE id = ?");
                ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM drop_data WHERE id = ?");
                ps2.setInt(1, Integer.parseInt(this.怪物爆物序列号.getText()));
                rs = ps2.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    String sqlString3 = null;
                    String sqlString4 = null;
                    sqlString2 = "update drop_data set dropperid='" + this.怪物爆物怪物代码.getText() + "' where id=" + this.怪物爆物序列号.getText() + ";";
                    final PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                    sqlString3 = "update drop_data set itemid='" + this.怪物爆物物品代码.getText() + "' where id=" + this.怪物爆物序列号.getText() + ";";
                    final PreparedStatement itemid = DatabaseConnection.getConnection().prepareStatement(sqlString3);
                    itemid.executeUpdate(sqlString3);
                    sqlString4 = "update drop_data set chance='" + this.怪物爆物爆率.getText() + "' where id=" + this.怪物爆物序列号.getText() + ";";
                    final PreparedStatement chance = DatabaseConnection.getConnection().prepareStatement(sqlString4);
                    chance.executeUpdate(sqlString4);
                    JOptionPane.showMessageDialog(null, "[信息]:修改成功。");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:请选择你要修改的数据。");
        }
    }

    private void 删除怪物爆物ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result = this.怪物爆物序列号.getText().matches("[0-9]+");
        if (result) {
            final int 商城SN编码 = Integer.parseInt(this.怪物爆物序列号.getText());
            try {
                for (int i = this.怪物爆物.getModel().getRowCount() - 1; i >= 0; --i) {
                    ((DefaultTableModel) this.怪物爆物.getModel()).removeRow(i);
                }
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM drop_data WHERE id = ?");
                ps1.setInt(1, 商城SN编码);
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from drop_data where id =" + 商城SN编码 + "";
                    ps1.executeUpdate(sqlstr);
                    JOptionPane.showMessageDialog(null, "[信息]:删除爆物成功。");
                    this.刷新指定怪物爆物();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void 添加怪物爆物ActionPerformed(final ActionEvent evt) {
        final boolean result1 = this.怪物爆物怪物代码.getText().matches("[0-9]+");
        final boolean result2 = this.怪物爆物物品代码.getText().matches("[0-9]+");
        final boolean result3 = this.怪物爆物爆率.getText().matches("[0-9]+");
        if (result1 && result2 && result3) {
            if (Integer.parseInt(this.怪物爆物怪物代码.getText()) < 0 && Integer.parseInt(this.怪物爆物物品代码.getText()) < 0 && Integer.parseInt(this.怪物爆物爆率.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "[信息]:请填写正确的值。");
                return;
            }
            try (final Connection con = DatabaseConnection.getConnection();
                 final PreparedStatement ps = con.prepareStatement("INSERT INTO drop_data ( dropperid,itemid,minimum_quantity,maximum_quantity,chance) VALUES ( ?, ?, ?, ?, ?)")) {
                ps.setInt(1, Integer.parseInt(this.怪物爆物怪物代码.getText()));
                ps.setInt(2, Integer.parseInt(this.怪物爆物物品代码.getText()));
                ps.setInt(3, 1);
                ps.setInt(4, 1);
                ps.setInt(5, Integer.parseInt(this.怪物爆物爆率.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "[信息]:添加成功。");
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:请输入<怪物代码><物品代码><物品爆率>的格式来添加。");
        }
    }

    private void 删除反应堆物品ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result1 = this.删除反应堆物品代码.getText().matches("[0-9]+");
        if (result1) {
            if (Integer.parseInt(this.删除反应堆物品代码.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "请填写正确的值");
            }
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM reactordrops WHERE itemid = ?");
                ps1.setInt(1, Integer.parseInt(this.删除反应堆物品代码.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from reactordrops where itemid =" + Integer.parseInt(this.删除反应堆物品代码.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    JOptionPane.showMessageDialog(null, "成功删除 " + Integer.parseInt(this.删除反应堆物品代码.getText()) + " 物品，重载后生效。");
                    this.刷新反应堆();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入你要删除的反应堆代码 ");
        }
    }

    private void 重载箱子反应堆按钮ActionPerformed(final ActionEvent evt) {
        ReactorScriptManager.getInstance().clearDrops();
        System.out.println("[重载系统] 箱子反应堆重载成功。");
        JOptionPane.showMessageDialog(null, "箱子反应堆重载成功。");
    }

    private void 修改反应堆物品ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        final boolean result = this.反应堆代码.getText().matches("[0-9]+");
        final boolean result2 = this.反应堆物品.getText().matches("[0-9]+");
        final boolean result3 = this.反应堆概率.getText().matches("[0-9]+");
        if (result && result2 && result3) {
            if (Integer.parseInt(this.反应堆代码.getText()) < 0 && Integer.parseInt(this.反应堆物品.getText()) < 0 && Integer.parseInt(this.反应堆概率.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "请填写正确的值");
            }
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE reactordrops SET reactorid = ?,itemid = ?,chance = ?WHERE reactordropid = ?");
                ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM reactordrops WHERE reactordropid = ?");
                ps2.setInt(1, Integer.parseInt(this.反应堆序列号.getText()));
                rs = ps2.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    String sqlString2 = null;
                    String sqlString3 = null;
                    sqlString1 = "update reactordrops set reactorid='" + this.反应堆代码.getText() + "' where reactordropid=" + this.反应堆序列号.getText() + ";";
                    final PreparedStatement itemid = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    itemid.executeUpdate(sqlString1);
                    sqlString2 = "update reactordrops set itemid='" + this.反应堆物品.getText() + "' where reactordropid=" + this.反应堆序列号.getText() + ";";
                    final PreparedStatement price = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    price.executeUpdate(sqlString2);
                    sqlString3 = "update reactordrops set chance='" + this.反应堆概率.getText() + "' where reactordropid=" + this.反应堆序列号.getText() + ";";
                    final PreparedStatement shopid = DatabaseConnection.getConnection().prepareStatement(sqlString3);
                    shopid.executeUpdate(sqlString3);
                    this.刷新反应堆();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请选择你要修改的数据");
        }
    }

    private void jButton37ActionPerformed(final ActionEvent evt) {
        final boolean result2 = this.查找反应堆掉落.getText().matches("[0-9]+");
        if (result2) {
            for (int i = this.反应堆.getModel().getRowCount() - 1; i >= 0; --i) {
                ((DefaultTableModel) this.反应堆.getModel()).removeRow(i);
            }
            try {
                final Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;
                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM reactordrops WHERE itemid = " + Integer.parseInt(this.查找物品.getText()));
                rs = ps.executeQuery();
                while (rs.next()) {
                    ((DefaultTableModel) this.反应堆.getModel()).insertRow(this.反应堆.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("reactordropid")), Integer.valueOf(rs.getInt("reactorid")), Integer.valueOf(rs.getInt("itemid")), Integer.valueOf(rs.getInt("chance")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入你要查找的物品代码 ");
        }
    }

    private void jButton36ActionPerformed(final ActionEvent evt) {
        final boolean result2 = this.查找反应堆掉落.getText().matches("[0-9]+");
        if (result2) {
            for (int i = this.反应堆.getModel().getRowCount() - 1; i >= 0; --i) {
                ((DefaultTableModel) this.反应堆.getModel()).removeRow(i);
            }
            try {
                final Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;
                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM reactordrops WHERE reactorid = " + Integer.parseInt(this.查找反应堆掉落.getText()));
                rs = ps.executeQuery();
                while (rs.next()) {
                    ((DefaultTableModel) this.反应堆.getModel()).insertRow(this.反应堆.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("reactordropid")), Integer.valueOf(rs.getInt("reactorid")), Integer.valueOf(rs.getInt("itemid")), Integer.valueOf(rs.getInt("chance")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入你要查找的反应堆 ");
        }
    }

    private void 删除反应堆物品1ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result1 = this.反应堆序列号.getText().matches("[0-9]+");
        if (result1) {
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM reactordrops WHERE reactordropid = ?");
                ps1.setInt(1, Integer.parseInt(this.反应堆序列号.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from reactordrops where reactordropid =" + Integer.parseInt(this.反应堆序列号.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    this.刷新反应堆();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请选择你要删除的物品 ");
        }
    }

    private void 新增反应堆物品ActionPerformed(final ActionEvent evt) {
        final boolean result2 = this.反应堆代码.getText().matches("[0-9]+");
        if (result2) {
            try (final Connection con = DatabaseConnection.getConnection();
                 final PreparedStatement ps = con.prepareStatement("INSERT INTO reactordrops ( reactorid ,itemid ,chance ,questid ) VALUES ( ?, ?, ?, ?)")) {
                ps.setInt(1, Integer.parseInt(this.反应堆代码.getText()));
                ps.setInt(2, Integer.parseInt(this.反应堆物品.getText()));
                ps.setInt(3, Integer.parseInt(this.反应堆概率.getText()));
                ps.setInt(4, -1);
                ps.executeUpdate();
                this.刷新反应堆();
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请输入反应堆代码，物品代码，掉落概率 ");
        }
    }

    private void 反应堆概率ActionPerformed(final ActionEvent evt) {
    }

    private void jButton26ActionPerformed(final ActionEvent evt) {
        this.刷新反应堆();
    }

    private void 泡点豆豆开关ActionPerformed(final ActionEvent evt) {
        final int 泡点豆豆开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("泡点豆豆开关"));
        if (泡点豆豆开关 <= 0) {
            this.按键开关("泡点豆豆开关", 711);
            this.刷新泡点豆豆开关();
        } else {
            this.按键开关("泡点豆豆开关", 711);
            this.刷新泡点豆豆开关();
        }
    }

    private void 泡点抵用开关ActionPerformed(final ActionEvent evt) {
        final int 泡点抵用开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("泡点抵用开关"));
        if (泡点抵用开关 <= 0) {
            this.按键开关("泡点抵用开关", 707);
            this.刷新泡点抵用开关();
        } else {
            this.按键开关("泡点抵用开关", 707);
            this.刷新泡点抵用开关();
        }
    }

    private void 泡点点券开关ActionPerformed(final ActionEvent evt) {
        final int 泡点点券开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("泡点点券开关"));
        if (泡点点券开关 <= 0) {
            this.按键开关("泡点点券开关", 703);
            this.刷新泡点点券开关();
        } else {
            this.按键开关("泡点点券开关", 703);
            this.刷新泡点点券开关();
        }
    }

    private void 泡点经验开关ActionPerformed(final ActionEvent evt) {
        final int 泡点经验开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("泡点经验开关"));
        if (泡点经验开关 <= 0) {
            this.按键开关("泡点经验开关", 705);
            this.刷新泡点经验开关();
        } else {
            this.按键开关("泡点经验开关", 705);
            this.刷新泡点经验开关();
        }
    }

    private void 泡点金币开关ActionPerformed(final ActionEvent evt) {
        final int 泡点金币开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("泡点金币开关"));
        if (泡点金币开关 <= 0) {
            this.按键开关("泡点金币开关", 701);
            this.刷新泡点金币开关();
        } else {
            this.按键开关("泡点金币开关", 701);
            this.刷新泡点金币开关();
        }
    }

    private void 泡点值修改ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        final boolean result1 = this.泡点值.getText().matches("[0-9]+");
        if (result1) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps2.setInt(1, Integer.parseInt(this.泡点序号.getText()));
                rs = ps2.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    sqlString1 = "update configvalues set Val = '" + this.泡点值.getText() + "' where id= " + this.泡点序号.getText() + ";";
                    final PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    Val.executeUpdate(sqlString1);
                    this.刷新泡点设置();
                    GetConfigValues();
                    this.福利提示语言2.setText("[信息]:修改成功已经生效。");
                }
            } catch (SQLException ex) {
                Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.福利提示语言2.setText("[信息]:请选择你要修改的值。");
        }
    }

    private void 给予物品ActionPerformed(final ActionEvent evt) {
        this.刷物品();
    }

    private void 个人发送物品代码ActionPerformed(final ActionEvent evt) {
    }

    private void 个人发送物品玩家名字ActionPerformed(final ActionEvent evt) {
    }

    private void 个人发送物品数量ActionPerformed(final ActionEvent evt) {
    }

    private void 给予装备2ActionPerformed(final ActionEvent evt) {
        this.刷装备2(1);
    }

    private void 发送装备玩家姓名ActionPerformed(final ActionEvent evt) {
    }

    private void 给予装备1ActionPerformed(final ActionEvent evt) {
        this.刷装备2(2);
    }

    private void 全服发送装备装备物理防御ActionPerformed(final ActionEvent evt) {
    }

    private void 全服发送装备装备魔法防御ActionPerformed(final ActionEvent evt) {
    }

    private void 全服发送装备装备魔法力ActionPerformed(final ActionEvent evt) {
    }

    private void 全服发送装备物品IDActionPerformed(final ActionEvent evt) {
    }

    private void 全服发送装备装备敏捷ActionPerformed(final ActionEvent evt) {
    }

    private void 全服发送装备装备可否交易ActionPerformed(final ActionEvent evt) {
    }

    private void 全服发送装备装备给予时间ActionPerformed(final ActionEvent evt) {
    }

    private void 全服发送装备装备攻击力ActionPerformed(final ActionEvent evt) {
    }

    private void 全服发送装备装备HPActionPerformed(final ActionEvent evt) {
    }

    private void 全服发送装备装备运气ActionPerformed(final ActionEvent evt) {
    }

    private void 全服发送装备装备智力ActionPerformed(final ActionEvent evt) {
    }

    private void 全服发送装备装备MPActionPerformed(final ActionEvent evt) {
    }

    private void 全服发送装备装备力量ActionPerformed(final ActionEvent evt) {
    }

    private void 全服发送装备装备制作人ActionPerformed(final ActionEvent evt) {
    }

    private void 全服发送装备装备加卷ActionPerformed(final ActionEvent evt) {
    }

    private void 给予物品1ActionPerformed(final ActionEvent evt) {
        this.刷物品2();
    }

    private void 全服发送物品代码ActionPerformed(final ActionEvent evt) {
    }

    private void 全服发送物品数量ActionPerformed(final ActionEvent evt) {
    }

    private void a1ActionPerformed(final ActionEvent evt) {
    }

    private void z6ActionPerformed(final ActionEvent evt) {
        this.发送福利(6);
    }

    private void z5ActionPerformed(final ActionEvent evt) {
        this.发送福利(5);
    }

    private void z4ActionPerformed(final ActionEvent evt) {
        this.发送福利(4);
    }

    private void z1ActionPerformed(final ActionEvent evt) {
        this.发送福利(1);
    }

    private void z3ActionPerformed(final ActionEvent evt) {
        this.发送福利(3);
    }

    private void z2ActionPerformed(final ActionEvent evt) {
        this.发送福利(2);
    }

    private void 重载任务ActionPerformed(final ActionEvent evt) {
        MapleQuest.clearQuests();
        System.out.println("[重载系统] 任务重载成功。");
        JOptionPane.showMessageDialog(null, "任务重载成功。");
    }

    private void 重载包头按钮ActionPerformed(final ActionEvent evt) {
        System.out.println("[重载系统] 包头重载成功。");
        JOptionPane.showMessageDialog(null, "包头重载成功。");
    }

    private void 重载商店按钮ActionPerformed(final ActionEvent evt) {
        MapleShopFactory.getInstance().clear();
        System.out.println("[重载系统] 商店重载成功。");
        JOptionPane.showMessageDialog(null, "商店重载成功。");
    }

    private void 重载商城按钮ActionPerformed(final ActionEvent evt) {
        CashItemFactory.getInstance().clearCashShop();
        System.out.println("[重载系统] 商城重载成功。");
        JOptionPane.showMessageDialog(null, "商城重载成功。");
    }

    private void 重载传送门按钮ActionPerformed(final ActionEvent evt) {
        PortalScriptManager.getInstance().clearScripts();
        System.out.println("[重载系统] 传送门重载成功。");
        JOptionPane.showMessageDialog(null, "传送门重载成功。");
    }

    private void 重载反应堆按钮ActionPerformed(final ActionEvent evt) {
        ReactorScriptManager.getInstance().clearDrops();
        System.out.println("[重载系统] 反应堆重载成功。");
        JOptionPane.showMessageDialog(null, "反应堆重载成功。");
    }

    private void 重载爆率按钮ActionPerformed(final ActionEvent evt) {
        MapleMonsterInformationProvider.getInstance().clearDrops();
        System.out.println("[重载系统] 爆率重载成功。");
        JOptionPane.showMessageDialog(null, "爆率重载成功。");
    }

    private void 重载副本按钮ActionPerformed(final ActionEvent evt) {
        for (final ChannelServer instance1 : ChannelServer.getAllInstances()) {
            if (instance1 != null) {
                instance1.reloadEvents();
            }
        }
        System.out.println("[重载系统] 副本重载成功。");
        JOptionPane.showMessageDialog(null, "副本重载成功。");
    }

    private void jButton46ActionPerformed(final ActionEvent evt) {
        this.openWindow(Windows.活动控制台);
    }

    private void jButton8ActionPerformed(final ActionEvent evt) {
        int p = 0;
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            ++p;
            cserv.closeAllMerchants();
        }
        merchant_main.getInstance().save_data();
        final String 输出 = "[保存雇佣商人系统] 雇佣商人保存" + p + "个频道成功";
        JOptionPane.showMessageDialog(null, "雇佣商人保存" + p + "个频道成功");
        this.printChatLog(输出);
    }

    private void jButton7ActionPerformed(final ActionEvent evt) {
        int p = 0;
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                ++p;
                chr.saveToDB(true, true);
            }
        }
        final String 输出 = "[保存数据系统] 保存" + p + "个成功。";
        JOptionPane.showMessageDialog(null, 输出);
        this.printChatLog(输出);
    }

    private void jButton39ActionPerformed(final ActionEvent evt) {
        this.openWindow(Windows.删除自添加NPC工具);
    }

    private void jButton44ActionPerformed(final ActionEvent evt) {
        this.openWindow(Windows.游戏抽奖工具);
    }

    private void jButton29ActionPerformed(final ActionEvent evt) {
        this.openWindow(Windows.一键还原);
    }

    private void jButton31ActionPerformed(final ActionEvent evt) {
        this.openWindow(Windows.代码查询工具);
        if (!LoginServer.isShutdown() || this.searchServer) {
            return;
        }
    }

    private void startserverbuttonActionPerformed(final ActionEvent evt) {
        if (!this.开启服务端) {
            this.开启服务端 = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Start.是否控制台启动 = true;
                    Start.main(null);
                    final String 输出 = "[启动完毕] 开服成功，可以进入游戏了!";
                    startserverbutton.setText("正在运行中...");
                    // CongMS.this.printChatLog(输出);
                }
            }).start();
            final Dis tt = new Dis();
            tt.start();
            return;
        }
        System.out.println("CongMS服务端正在运行中！");
    }

    private void jTextField22ActionPerformed(final ActionEvent evt) {
    }

    private void jButton16ActionPerformed(final ActionEvent evt) {
        this.重启服务器();
    }

    private void 屠令广播开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("屠令广播开关", 2016);
        this.刷新屠令广播开关();
    }

    private void jButton13ActionPerformed(final ActionEvent evt) {
        this.sendNoticeGG();
    }

    private void jTextField2ActionPerformed(final ActionEvent evt) {
    }

    private void 吸怪检测开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("吸怪检测开关", 2130);
        this.刷新吸怪检测开关();
    }

    private void 指令通知开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("指令通知开关", 2028);
        this.刷新指令通知开关();
    }

    private void 过图存档开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("过图存档开关", 2140);
        this.刷新过图存档时间();
    }

    private void 地图名称开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("地图名称开关", 2136);
        this.刷新地图名称开关();
    }

    private void 怪物状态开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("怪物状态开关", 2061);
        this.刷新怪物状态开关();
    }

    private void 越级打怪开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("越级打怪开关", 2125);
        this.刷新越级打怪开关();
    }

    private void 登陆帮助开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("登陆帮助开关", 2058);
        this.刷新登陆帮助();
    }

    private void 欢迎弹窗开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("欢迎弹窗开关", 2015);
        this.刷新欢迎弹窗开关();
    }

    private void 雇佣商人开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("雇佣商人开关", 2020);
        this.刷新雇佣商人开关();
    }

    private void 玩家交易开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("玩家交易开关", 2011);
        this.刷新玩家交易开关();
    }

    private void 游戏喇叭开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("游戏喇叭开关", 2009);
        this.刷新游戏喇叭开关();
    }

    private void 管理加速开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("管理加速开关", 2007);
        this.刷新管理加速开关();
    }

    private void 管理隐身开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("管理隐身开关", 2006);
        this.刷新管理隐身开关();
    }

    private void 回收地图开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("回收地图开关", 2029);
        this.刷新回收地图开关();
    }

    private void 上线提醒开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("上线提醒开关", 2021);
        this.刷新上线提醒开关();
    }

    private void 游戏指令开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("游戏指令开关", 2008);
        this.刷新游戏指令开关();
    }

    private void 丢出物品开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("丢出物品开关", 2012);
        this.刷新丢出物品开关();
    }

    private void 丢出金币开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("丢出金币开关", 2010);
        this.刷新丢出金币开关();
    }

    private void 游戏升级快讯ActionPerformed(final ActionEvent evt) {
        this.按键开关("升级快讯开关", 2003);
        this.刷新升级快讯();
    }

    private void 玩家聊天开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("玩家聊天开关", 2024);
        this.刷新玩家聊天开关();
    }

    private void 滚动公告开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("滚动公告开关", 2026);
        this.刷新滚动公告开关();
    }

    private void 禁止登陆开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("禁止登陆开关", 2013);
        this.刷新禁止登陆开关();
    }

    private void 修改广播ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getConnection().prepareStatement("UPDATE 广播信息 SET 广播 = ? WHERE id = ?");
            ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM 广播信息  WHERE id = ? ");
            ps2.setInt(1, Integer.parseInt(this.广播序号.getText()));
            rs = ps2.executeQuery();
            if (rs.next()) {
                String sqlString1 = null;
                sqlString1 = "update 广播信息 set 广播 = '" + this.广播文本.getText() + "' where id = " + Integer.parseInt(this.广播序号.getText()) + ";";
                final PreparedStatement a1 = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                a1.executeUpdate(sqlString1);
                this.刷新公告广播();
                JOptionPane.showMessageDialog(null, "修改成功。");
            }
        } catch (SQLException ex) {
        }
    }

    private void 发布广告ActionPerformed(final ActionEvent evt) {
        if (this.广播文本.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "请填写广告信息哦。");
            return;
        }
        try (final Connection con = DatabaseConnection.getConnection();
             final PreparedStatement ps = con.prepareStatement("INSERT INTO 广播信息 ( 广播 ) VALUES ( ? )")) {
            ps.setString(1, this.广播文本.getText());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.刷新公告广播();
        JOptionPane.showMessageDialog(null, "发布完成。");
    }

    private void 删除广播ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result1 = this.广播序号.getText().matches("[0-9]+");
        if (result1) {
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM 广播信息 WHERE id = ?");
                ps1.setInt(1, Integer.parseInt(this.广播序号.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from 广播信息 where id =" + Integer.parseInt(this.广播序号.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    this.刷新公告广播();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void 刷新广告ActionPerformed(final ActionEvent evt) {
        this.刷新公告广播();
    }

    private void 开启三倍金币ActionPerformed(final ActionEvent evt) {
        final boolean result1 = this.三倍金币持续时间.getText().matches("[0-9]+");
        if (result1) {
            if (this.三倍金币持续时间.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "持续时间不能为空");
                return;
            }
            final int 原始金币 = Integer.parseInt(ServerProperties.getProperty("CongMS.mesoRate"));
            final int 三倍金币活动 = 原始金币 * 3;
            final int seconds = 0;
            final int mins = 0;
            final int hours = Integer.parseInt(this.三倍金币持续时间.getText());
            final int time = seconds + mins * 60 + hours * 60 * 60;
            final String rate = "金币";
            World.scheduleRateDelay("金币", time);
            for (final ChannelServer cservs : ChannelServer.getAllInstances()) {
                cservs.setExpRate(三倍金币活动);
            }
            Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(9, 20, "[倍率活动] : 游戏开始 3 倍打怪金币活动，将持续 " + hours + " 小时，请各位玩家狂欢吧！"));
            JOptionPane.showMessageDialog(null, "成功开启三倍金币活动，持续 " + hours + " 小时");
        } else {
            JOptionPane.showMessageDialog(null, "持续时间输入不正确");
        }
    }

    private void 开启三倍爆率ActionPerformed(final ActionEvent evt) {
        final boolean result1 = this.三倍爆率持续时间.getText().matches("[0-9]+");
        if (result1) {
            if (this.三倍爆率持续时间.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "持续时间不能为空");
                return;
            }
            final int 原始爆率 = Integer.parseInt(ServerProperties.getProperty("CongMS.dropRate"));
            final int 三倍爆率活动 = 原始爆率 * 3;
            final int seconds = 0;
            final int mins = 0;
            final int hours = Integer.parseInt(this.三倍经验持续时间.getText());
            final int time = seconds + mins * 60 + hours * 60 * 60;
            final String rate = "爆率";
            World.scheduleRateDelay("爆率", time);
            for (final ChannelServer cservs : ChannelServer.getAllInstances()) {
                cservs.setExpRate(三倍爆率活动);
            }
            Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(9, 20, "[倍率活动] : 游戏开始 3 倍打怪爆率活动，将持续 " + hours + " 小时，请各位玩家狂欢吧！"));
            JOptionPane.showMessageDialog(null, "成功开启三倍爆率活动，持续 " + hours + " 小时");
        } else {
            JOptionPane.showMessageDialog(null, "持续时间输入不正确");
        }
    }

    private void 开启三倍经验ActionPerformed(final ActionEvent evt) {
        final boolean result1 = this.三倍经验持续时间.getText().matches("[0-9]+");
        if (result1) {
            if (this.三倍经验持续时间.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "持续时间不能为空");
                return;
            }
            final int 原始经验 = Integer.parseInt(ServerProperties.getProperty("CongMS.expRate"));
            final int 三倍经验活动 = 原始经验 * 3;
            final int seconds = 0;
            final int mins = 0;
            final int hours = Integer.parseInt(this.三倍经验持续时间.getText());
            final int time = seconds + mins * 60 + hours * 60 * 60;
            final String rate = "经验";
            World.scheduleRateDelay("经验", time);
            for (final ChannelServer cservs : ChannelServer.getAllInstances()) {
                cservs.setExpRate(三倍经验活动);
            }
            Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(9, 20, "[倍率活动] : 游戏开始 3 倍打怪经验活动，将持续 " + hours + " 小时，请各位玩家狂欢吧！"));
            JOptionPane.showMessageDialog(null, "成功开启三倍经验活动，持续 " + hours + " 小时");
        } else {
            JOptionPane.showMessageDialog(null, "持续时间输入不正确");
        }
    }

    private void 开启双倍金币ActionPerformed(final ActionEvent evt) {
        final boolean result1 = this.双倍金币持续时间.getText().matches("[0-9]+");
        if (result1) {
            if (this.双倍金币持续时间.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "持续时间不能为空");
                return;
            }
            final int 原始金币 = Integer.parseInt(ServerProperties.getProperty("CongMS.mesoRate"));
            final int 双倍金币活动 = 原始金币 * 2;
            final int seconds = 0;
            final int mins = 0;
            final int hours = Integer.parseInt(this.双倍金币持续时间.getText());
            final int time = seconds + mins * 60 + hours * 60 * 60;
            final String rate = "金币";
            World.scheduleRateDelay("金币", time);
            for (final ChannelServer cservs : ChannelServer.getAllInstances()) {
                cservs.setExpRate(双倍金币活动);
            }
            Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(9, 20, "[倍率活动] : 游戏开始 2 倍打怪金币活动，将持续 " + hours + " 小时，请各位玩家狂欢吧！"));
            JOptionPane.showMessageDialog(null, "成功开启双倍金币活动，持续 " + hours + " 小时");
        } else {
            JOptionPane.showMessageDialog(null, "持续时间输入不正确");
        }
    }

    private void 开启双倍爆率ActionPerformed(final ActionEvent evt) {
        final boolean result1 = this.双倍爆率持续时间.getText().matches("[0-9]+");
        if (result1) {
            if (this.双倍爆率持续时间.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "持续时间不能为空");
                return;
            }
            final int 原始爆率 = Integer.parseInt(ServerProperties.getProperty("CongMS.dropRate"));
            final int 双倍爆率活动 = 原始爆率 * 2;
            final int seconds = 0;
            final int mins = 0;
            final int hours = Integer.parseInt(this.双倍经验持续时间.getText());
            final int time = seconds + mins * 60 + hours * 60 * 60;
            final String rate = "爆率";
            World.scheduleRateDelay("爆率", time);
            for (final ChannelServer cservs : ChannelServer.getAllInstances()) {
                cservs.setExpRate(双倍爆率活动);
            }
            Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(9, 20, "[倍率活动] : 游戏开始 2 倍打怪爆率活动，将持续 " + hours + " 小时，请各位玩家狂欢吧！"));
            JOptionPane.showMessageDialog(null, "成功开启双倍爆率活动，持续 " + hours + " 小时");
        } else {
            JOptionPane.showMessageDialog(null, "持续时间输入不正确");
        }
    }

    private void 开启双倍经验ActionPerformed(final ActionEvent evt) {
        final boolean result1 = this.双倍经验持续时间.getText().matches("[0-9]+");
        if (result1) {
            if (this.双倍经验持续时间.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "持续时间不能为空");
                return;
            }
            final int 原始经验 = Integer.parseInt(ServerProperties.getProperty("CongMS.expRate"));
            final int 双倍经验活动 = 原始经验 * 2;
            final int seconds = 0;
            final int mins = 0;
            final int hours = Integer.parseInt(this.双倍经验持续时间.getText());
            final int time = seconds + mins * 60 + hours * 60 * 60;
            final String rate = "经验";
            World.scheduleRateDelay("经验", time);
            for (final ChannelServer cservs : ChannelServer.getAllInstances()) {
                cservs.setExpRate(双倍经验活动);
            }
            Broadcast.broadcastSmega(MaplePacketCreator.serverNotice(9, 20, "[倍率活动] : 游戏开始 2 倍打怪经验活动，将持续 " + hours + " 小时，请各位玩家狂欢吧！"));
            JOptionPane.showMessageDialog(null, "成功开启双倍经验活动，持续 " + hours + " 小时");
        } else {
            JOptionPane.showMessageDialog(null, "持续时间输入不正确");
        }
    }

    private void 游戏经验加成说明ActionPerformed(final ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "<相关说明文>\r\n\r\n1:相对应数值为0则为关闭经验加成。\r\n2:人气经验 = 人气 * 人气经验加成数值。\r\n\r\n");
    }

    private void 经验加成表修改ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        final boolean result1 = this.经验加成表序号.getText().matches("[0-9]+");
        if (result1) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps2.setInt(1, Integer.parseInt(this.经验加成表序号.getText()));
                rs = ps2.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    sqlString1 = "update configvalues set Val = '" + this.经验加成表数值.getText() + "' where id= " + this.经验加成表序号.getText() + ";";
                    final PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    Val.executeUpdate(sqlString1);
                    this.刷新经验加成表();
                    GetConfigValues();
                    JOptionPane.showMessageDialog(null, "修改成功已经生效");
                }
            } catch (SQLException ex) {
                Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "请选择你要修改的值");
        }
    }

    private void 神秘商人开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("神秘商人开关", 2406);
        this.刷新神秘商人开关();
    }

    private void 幸运职业开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("幸运职业开关", 749);
        this.刷新幸运职业开关();
    }

    private void 魔族攻城开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("魔族攻城开关", 2404);
        this.刷新魔族攻城开关();
    }

    private void 魔族突袭开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("魔族突袭开关", 2400);
        this.刷新魔族突袭开关();
    }

    private void 骑士团职业开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("骑士团职业开关", 2001);
        this.刷新骑士团职业开关();
    }

    private void 战神职业开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("战神职业开关", 2002);
        this.刷新战神职业开关();
    }

    private void 冒险家职业开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("冒险家职业开关", 2000);
        this.刷新冒险家职业开关();
    }

    private void 修改骑士团等级上限ActionPerformed(final ActionEvent evt) {
        if (this.骑士团等级上限.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "不能为空");
            return;
        }
        final boolean result2 = this.骑士团等级上限.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        if (result2) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps2.setInt(1, 2301);
                rs = ps2.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    sqlString2 = "update configvalues set Val='" + this.骑士团等级上限.getText() + "' where id = 2301;";
                    final PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                    GetConfigValues();
                    this.刷新骑士团等级上限();
                    JOptionPane.showMessageDialog(null, "修改成功");
                }
            } catch (SQLException ex) {
                Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void 修改冒险家等级上限ActionPerformed(final ActionEvent evt) {
        if (this.冒险家等级上限.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "不能为空");
            return;
        }
        final boolean result2 = this.冒险家等级上限.getText().matches("[0-9]+");
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        if (result2) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps2.setInt(1, 2300);
                rs = ps2.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    sqlString2 = "update configvalues set Val='" + this.冒险家等级上限.getText() + "' where id = 2300;";
                    final PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                    GetConfigValues();
                    this.刷新冒险家等级上限();
                    JOptionPane.showMessageDialog(null, "修改成功");
                }
            } catch (SQLException ex) {
                Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void 花蘑菇开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("花蘑菇开关", 2219);
        this.刷新花蘑菇开关();
        JOptionPane.showMessageDialog(null, "[信息]:修改成功!");
    }

    private void 青鳄鱼开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("青鳄鱼开关", 2218);
        this.刷新青鳄鱼开关();
        JOptionPane.showMessageDialog(null, "[信息]:修改成功!");
    }

    private void 小白兔开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("小白兔开关", 2215);
        this.刷新小白兔开关();
        JOptionPane.showMessageDialog(null, "[信息]:修改成功!");
    }

    private void 火野猪开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("火野猪开关", 2217);
        this.刷新火野猪开关();
        JOptionPane.showMessageDialog(null, "[信息]:修改成功!");
    }

    private void 喷火龙开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("喷火龙开关", 2216);
        this.刷新喷火龙开关();
        JOptionPane.showMessageDialog(null, "[信息]:修改成功!");
    }

    private void 大灰狼开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("大灰狼开关", 2214);
        this.刷新大灰狼开关();
        JOptionPane.showMessageDialog(null, "[信息]:修改成功!");
    }

    private void 紫色猫开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("紫色猫开关", 2213);
        this.刷新紫色猫开关();
        JOptionPane.showMessageDialog(null, "[信息]:修改成功!");
    }

    private void 石头人开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("石头人开关", 2212);
        this.刷新石头人开关();
        JOptionPane.showMessageDialog(null, "[信息]:修改成功!");
    }

    private void 白雪人开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("白雪人开关", 2211);
        this.刷新白雪人开关();
        JOptionPane.showMessageDialog(null, "[信息]:修改成功!");
    }

    private void 胖企鹅开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("胖企鹅开关", 2210);
        this.刷新胖企鹅开关();
        JOptionPane.showMessageDialog(null, "[信息]:修改成功!");
    }

    private void 星精灵开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("星精灵开关", 2209);
        this.刷新星精灵开关();
        JOptionPane.showMessageDialog(null, "[信息]:修改成功!");
    }

    private void 顽皮猴开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("顽皮猴开关", 2208);
        this.刷新顽皮猴开关();
        JOptionPane.showMessageDialog(null, "[信息]:修改成功!");
    }

    private void 章鱼怪开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("章鱼怪开关", 2207);
        this.刷新章鱼怪开关();
        JOptionPane.showMessageDialog(null, "[信息]:修改成功!");
    }

    private void 大海龟开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("大海龟开关", 2206);
        this.刷新大海龟开关();
        JOptionPane.showMessageDialog(null, "[信息]:修改成功!");
    }

    private void 红螃蟹开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("红螃蟹开关", 2205);
        this.刷新红螃蟹开关();
        JOptionPane.showMessageDialog(null, "[信息]:修改成功!");
    }

    private void 小青蛇开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("小青蛇开关", 2204);
        this.刷新小青蛇开关();
        JOptionPane.showMessageDialog(null, "[信息]:修改成功!");
    }

    private void 漂漂猪开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("漂漂猪开关", 2203);
        this.刷新漂漂猪开关();
        JOptionPane.showMessageDialog(null, "[信息]:修改成功!");
    }

    private void 绿水灵开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("绿水灵开关", 2202);
        this.刷新绿水灵开关();
        JOptionPane.showMessageDialog(null, "[信息]:修改成功!");
    }

    private void 蘑菇仔开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("蘑菇仔开关", 2201);
        this.刷新蘑菇仔开关();
        JOptionPane.showMessageDialog(null, "[信息]:修改成功!");
    }

    private void 蓝蜗牛开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("蓝蜗牛开关", 2200);
        this.刷新蓝蜗牛开关();
        JOptionPane.showMessageDialog(null, "[信息]:修改成功!");
    }

    private void 刷物品2() {
        try {
            final int 物品ID = Integer.parseInt(this.全服发送物品代码.getText());
            final int 数量 = Integer.parseInt(this.全服发送物品数量.getText());
            final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            final MapleInventoryType type = GameConstants.getInventoryType(物品ID);
            for (final ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (final MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    if (数量 >= 0) {
                        if (!MapleInventoryManipulator.checkSpace(mch.getClient(), 物品ID, 数量, "")) {
                            return;
                        }
                        if ((type.equals(MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(物品ID) && !GameConstants.isBullet(物品ID)) || (type.equals(MapleInventoryType.CASH) && 物品ID >= 5000000 && 物品ID <= 5000100)) {
                            final Equip item = (Equip) ii.getEquipById(物品ID);
                            if (ii.isCash(物品ID)) {
                                item.setUniqueId(1);
                            }
                            final String name = ii.getName(物品ID);
                            if (物品ID / 10000 == 114 && name != null && name.length() > 0) {
                                final String msg = "你已获得称号 <" + name + ">";
                                mch.getClient().getPlayer().dropMessage(5, msg);
                            }
                            MapleInventoryManipulator.addbyItem(mch.getClient(), item.copy());
                        } else {
                            MapleInventoryManipulator.addById(mch.getClient(), 物品ID, (short) 数量, "", null, (byte) 0);
                        }
                    } else {
                        MapleInventoryManipulator.removeById(mch.getClient(), GameConstants.getInventoryType(物品ID), 物品ID, -数量, true, false);
                    }
                    mch.getClient().getSession().write(MaplePacketCreator.getShowItemGain(物品ID, (short) 数量, true));
                }
            }
            this.全服发送物品代码.setText("");
            this.全服发送物品数量.setText("");
            JOptionPane.showMessageDialog(null, "[信息]:发送成功。");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "[信息]:错误!" + e);
        }
    }

    private void 刷装备2(final int a) {
        try {
            int 物品ID;
            if ("物品ID".equals(this.全服发送装备物品ID.getText())) {
                物品ID = 0;
            } else {
                物品ID = Integer.parseInt(this.全服发送装备物品ID.getText());
            }
            int 力量;
            if ("力量".equals(this.全服发送装备装备力量.getText())) {
                力量 = 0;
            } else {
                力量 = Integer.parseInt(this.全服发送装备装备力量.getText());
            }
            int 敏捷;
            if ("敏捷".equals(this.全服发送装备装备敏捷.getText())) {
                敏捷 = 0;
            } else {
                敏捷 = Integer.parseInt(this.全服发送装备装备敏捷.getText());
            }
            int 智力;
            if ("智力".equals(this.全服发送装备装备智力.getText())) {
                智力 = 0;
            } else {
                智力 = Integer.parseInt(this.全服发送装备装备智力.getText());
            }
            int 运气;
            if ("运气".equals(this.全服发送装备装备运气.getText())) {
                运气 = 0;
            } else {
                运气 = Integer.parseInt(this.全服发送装备装备运气.getText());
            }
            int HP;
            if ("HP设置".equals(this.全服发送装备装备HP.getText())) {
                HP = 0;
            } else {
                HP = Integer.parseInt(this.全服发送装备装备HP.getText());
            }
            int MP;
            if ("MP设置".equals(this.全服发送装备装备MP.getText())) {
                MP = 0;
            } else {
                MP = Integer.parseInt(this.全服发送装备装备MP.getText());
            }
            int 可加卷次数;
            if ("加卷次数".equals(this.全服发送装备装备加卷.getText())) {
                可加卷次数 = 0;
            } else {
                可加卷次数 = Integer.parseInt(this.全服发送装备装备加卷.getText());
            }
            String 制作人名字;
            if ("制作人".equals(this.全服发送装备装备制作人.getText())) {
                制作人名字 = "";
            } else {
                制作人名字 = this.全服发送装备装备制作人.getText();
            }
            int 给予时间;
            if ("给予物品时间".equals(this.全服发送装备装备给予时间.getText())) {
                给予时间 = 0;
            } else {
                给予时间 = Integer.parseInt(this.全服发送装备装备给予时间.getText());
            }
            final String 是否可以交易 = this.全服发送装备装备可否交易.getText();
            int 攻击力;
            if ("攻击力".equals(this.全服发送装备装备攻击力.getText())) {
                攻击力 = 0;
            } else {
                攻击力 = Integer.parseInt(this.全服发送装备装备攻击力.getText());
            }
            int 魔法力;
            if ("魔法力".equals(this.全服发送装备装备魔法力.getText())) {
                魔法力 = 0;
            } else {
                魔法力 = Integer.parseInt(this.全服发送装备装备魔法力.getText());
            }
            int 物理防御;
            if ("物理防御".equals(this.全服发送装备装备物理防御.getText())) {
                物理防御 = 0;
            } else {
                物理防御 = Integer.parseInt(this.全服发送装备装备物理防御.getText());
            }
            int 魔法防御;
            if ("魔法防御".equals(this.全服发送装备装备魔法防御.getText())) {
                魔法防御 = 0;
            } else {
                魔法防御 = Integer.parseInt(this.全服发送装备装备魔法防御.getText());
            }
            final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            final MapleInventoryType type = GameConstants.getInventoryType(物品ID);
            for (final ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (final MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    if (a == 1) {
                        if (!MapleInventoryManipulator.checkSpace(mch.getClient(), 物品ID, 1, "")) {
                            return;
                        }
                        if ((type.equals(MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(物品ID) && !GameConstants.isBullet(物品ID)) || (type.equals(MapleInventoryType.CASH) && 物品ID >= 5000000 && 物品ID <= 5000100)) {
                            final Equip item = (Equip) ii.getEquipById(物品ID);
                            if (ii.isCash(物品ID)) {
                                item.setUniqueId(1);
                            }
                            if (力量 > 0 && 力量 <= 32767) {
                                item.setStr((short) 力量);
                            }
                            if (敏捷 > 0 && 敏捷 <= 32767) {
                                item.setDex((short) 敏捷);
                            }
                            if (智力 > 0 && 智力 <= 32767) {
                                item.setInt((short) 智力);
                            }
                            if (运气 > 0 && 运气 <= 32767) {
                                item.setLuk((short) 运气);
                            }
                            if (攻击力 > 0 && 攻击力 <= 32767) {
                                item.setWatk((short) 攻击力);
                            }
                            if (魔法力 > 0 && 魔法力 <= 32767) {
                                item.setMatk((short) 魔法力);
                            }
                            if (物理防御 > 0 && 物理防御 <= 32767) {
                                item.setWdef((short) 物理防御);
                            }
                            if (魔法防御 > 0 && 魔法防御 <= 32767) {
                                item.setMdef((short) 魔法防御);
                            }
                            if (HP > 0 && HP <= 30000) {
                                item.setHp((short) HP);
                            }
                            if (MP > 0 && MP <= 30000) {
                                item.setMp((short) MP);
                            }
                            if ("可以交易".equals(是否可以交易)) {
                                short flag = item.getFlag();
                                if (item.getType() == MapleInventoryType.EQUIP.getType()) {
                                    flag |= (short) ItemFlag.KARMA_EQ.getValue();
                                } else {
                                    flag |= (short) ItemFlag.KARMA_USE.getValue();
                                }
                                item.setFlag((byte) flag);
                            }
                            if (给予时间 > 0) {
                                item.setExpiration(System.currentTimeMillis() + (long) (给予时间 * 24 * 60 * 60 * 1000));
                            }
                            if (可加卷次数 > 0) {
                                item.setUpgradeSlots((byte) 可加卷次数);
                            }
                            if (制作人名字 != null) {
                                item.setOwner(制作人名字);
                            }
                            final String name = ii.getName(物品ID);
                            if (物品ID / 10000 == 114 && name != null && name.length() > 0) {
                                final String msg = "你已获得称号 <" + name + ">";
                                mch.getClient().getPlayer().dropMessage(5, msg);
                            }
                            MapleInventoryManipulator.addbyItem(mch.getClient(), item.copy());
                        } else {
                            MapleInventoryManipulator.addById(mch.getClient(), 物品ID, (short) 1, "", null, (byte) 0);
                        }
                        mch.getClient().getSession().write(MaplePacketCreator.getShowItemGain(物品ID, (short) 1, true));
                    } else {
                        if (!mch.getName().equals(this.发送装备玩家姓名.getText())) {
                            continue;
                        }
                        if (!MapleInventoryManipulator.checkSpace(mch.getClient(), 物品ID, 1, "")) {
                            return;
                        }
                        if ((type.equals(MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(物品ID) && !GameConstants.isBullet(物品ID)) || (type.equals(MapleInventoryType.CASH) && 物品ID >= 5000000 && 物品ID <= 5000100)) {
                            final Equip item = (Equip) ii.getEquipById(物品ID);
                            if (ii.isCash(物品ID)) {
                                item.setUniqueId(1);
                            }
                            if (力量 > 0 && 力量 <= 32767) {
                                item.setStr((short) 力量);
                            }
                            if (敏捷 > 0 && 敏捷 <= 32767) {
                                item.setDex((short) 敏捷);
                            }
                            if (智力 > 0 && 智力 <= 32767) {
                                item.setInt((short) 智力);
                            }
                            if (运气 > 0 && 运气 <= 32767) {
                                item.setLuk((short) 运气);
                            }
                            if (攻击力 > 0 && 攻击力 <= 32767) {
                                item.setWatk((short) 攻击力);
                            }
                            if (魔法力 > 0 && 魔法力 <= 32767) {
                                item.setMatk((short) 魔法力);
                            }
                            if (物理防御 > 0 && 物理防御 <= 32767) {
                                item.setWdef((short) 物理防御);
                            }
                            if (魔法防御 > 0 && 魔法防御 <= 32767) {
                                item.setMdef((short) 魔法防御);
                            }
                            if (HP > 0 && HP <= 30000) {
                                item.setHp((short) HP);
                            }
                            if (MP > 0 && MP <= 30000) {
                                item.setMp((short) MP);
                            }
                            if ("可以交易".equals(是否可以交易)) {
                                short flag = item.getFlag();
                                if (item.getType() == MapleInventoryType.EQUIP.getType()) {
                                    flag |= (short) ItemFlag.KARMA_EQ.getValue();
                                } else {
                                    flag |= (short) ItemFlag.KARMA_USE.getValue();
                                }
                                item.setFlag((byte) flag);
                            }
                            if (给予时间 > 0) {
                                item.setExpiration(System.currentTimeMillis() + (long) (给予时间 * 24 * 60 * 60 * 1000));
                            }
                            if (可加卷次数 > 0) {
                                item.setUpgradeSlots((byte) 可加卷次数);
                            }
                            if (制作人名字 != null) {
                                item.setOwner(制作人名字);
                            }
                            final String name = ii.getName(物品ID);
                            if (物品ID / 10000 == 114 && name != null && name.length() > 0) {
                                final String msg = "你已获得称号 <" + name + ">";
                                mch.getClient().getPlayer().dropMessage(5, msg);
                            }
                            MapleInventoryManipulator.addbyItem(mch.getClient(), item.copy());
                        } else {
                            MapleInventoryManipulator.addById(mch.getClient(), 物品ID, (short) 1, "", null, (byte) 0);
                        }
                        mch.getClient().getSession().write(MaplePacketCreator.getShowItemGain(物品ID, (short) 1, true));
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "[信息]:发送成功。");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "[信息]:错误!" + e);
        }
    }

    private void 发送福利(final int a) {
        final boolean result1 = this.a1.getText().matches("[0-9]+");
        if (result1) {
            int 数量;
            if ("100000000".equals(this.a1.getText())) {
                数量 = 100;
            } else {
                数量 = Integer.parseInt(this.a1.getText());
            }
            if (数量 <= 0 || 数量 > 999999999) {
                return;
            }
            String 类型 = "";
            for (final ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (final MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    switch (a) {
                        case 1: {
                            类型 = "点券";
                            mch.modifyCSPoints(1, 数量, true);
                            break;
                        }
                        case 2: {
                            类型 = "抵用券";
                            mch.modifyCSPoints(2, 数量, true);
                            break;
                        }
                        case 3: {
                            类型 = "金币";
                            mch.gainMeso(数量, true);
                            break;
                        }
                        case 4: {
                            类型 = "经验";
                            mch.gainExp(数量, false, false, false);
                            break;
                        }
                        case 5: {
                            类型 = "人气";
                            mch.addFame(数量);
                            break;
                        }
                        case 6: {
                            类型 = "豆豆";
                            mch.gainBeans(数量);
                            break;
                        }
                    }
                    mch.startMapEffect("管理员发放 " + 数量 + " " + 类型 + "给在线的所有玩家！", 5121009);
                }
            }
            JOptionPane.showMessageDialog(null, "[信息]:发放 " + 数量 + " " + 类型 + "给在线的所有玩家。");
            this.a1.setText("");
            JOptionPane.showMessageDialog(null, "发送成功");
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:请输入要发送数量。");
        }
    }

    public void 刷新反应堆() {
        for (int i = this.反应堆.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.反应堆.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM reactordrops ");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) this.反应堆.getModel()).insertRow(this.反应堆.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("reactordropid")), Integer.valueOf(rs.getInt("reactorid")), Integer.valueOf(rs.getInt("itemid")), Integer.valueOf(rs.getInt("chance")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.反应堆.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 反应堆.getSelectedRow();
                final String a = 反应堆.getValueAt(i, 0).toString();
                final String a2 = 反应堆.getValueAt(i, 1).toString();
                final String a3 = 反应堆.getValueAt(i, 2).toString();
                final String a4 = 反应堆.getValueAt(i, 3).toString();
                反应堆序列号.setText(a);
                反应堆代码.setText(a2);
                反应堆物品.setText(a3);
                反应堆概率.setText(a4);
            }
        });
    }

    public void openWindow(final Windows w) {
        if (!this.windows.containsKey(w)) {
            switch (w) {
                case 一键还原: {
                    this.windows.put(w, new 一键还原());
                    break;
                }
                case 代码查询工具: {
                    this.windows.put(w, new 代码查询工具());
                    break;
                }
                case 活动控制台: {
                    this.windows.put(w, new 活动控制台());
                    break;
                }
                case 游戏抽奖工具: {
                    this.windows.put(w, new 游戏抽奖工具());
                    break;
                }
                case 删除自添加NPC工具: {
                    this.windows.put(w, new 删除自添加NPC工具());
                    break;
                }
                default: {
                    return;
                }
            }
            this.windows.get(w).setDefaultCloseOperation(1);
        }
        this.windows.get(w).setVisible(true);
    }

    public void 刷新封MAC() {
        for (int i = this.封MAC.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.封MAC.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM macbans");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) this.封MAC.getModel()).insertRow(this.封MAC.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("macbanid")), rs.getString("mac"), NPCConversationManager.MAC取账号(rs.getString("mac"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void 刷新封IP() {
        for (int i = this.封IP.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.封IP.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM ipbans");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) this.封IP.getModel()).insertRow(this.封IP.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("ipbanid")), rs.getString("ip"), NPCConversationManager.IP取账号(rs.getString("ip"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void 删除SN库存() {
        PreparedStatement ps2 = null;
        ResultSet rs2 = null;
        try {
            ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM character7 WHERE Name = ?");
            ps2.setInt(1, Integer.parseInt(this.商品编码.getText()));
            rs2 = ps2.executeQuery();
            if (rs2.next()) {
                final String sqlstr = " delete from character7 where Name =" + Integer.parseInt(this.商品编码.getText()) + ";";
                ps2.executeUpdate(sqlstr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void 删除SN库存2() {
        PreparedStatement ps2 = null;
        ResultSet rs2 = null;
        try {
            ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM character7 WHERE Name = ?  &&  channel = 2");
            ps2.setInt(1, Integer.parseInt(this.商品编码.getText()));
            rs2 = ps2.executeQuery();
            if (rs2.next()) {
                final String sqlstr = " delete from character7 where Name =" + Integer.parseInt(this.商品编码.getText()) + ";";
                ps2.executeUpdate(sqlstr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void 删除SN库存3() {
        PreparedStatement ps2 = null;
        ResultSet rs2 = null;
        try {
            ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM character7 WHERE Name = ?  &&  channel = 3");
            ps2.setInt(1, Integer.parseInt(this.商品编码.getText()));
            rs2 = ps2.executeQuery();
            if (rs2.next()) {
                final String sqlstr = " delete from character7 where Name =" + Integer.parseInt(this.商品编码.getText()) + ";";
                ps2.executeUpdate(sqlstr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void 删除SN库存4() {
        PreparedStatement ps2 = null;
        ResultSet rs2 = null;
        try {
            ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM character7 WHERE Name = ?  &&  channel = 4");
            ps2.setInt(1, Integer.parseInt(this.商品编码.getText()));
            rs2 = ps2.executeQuery();
            if (rs2.next()) {
                final String sqlstr = " delete from character7 where Name =" + Integer.parseInt(this.商品编码.getText()) + ";";
                ps2.executeUpdate(sqlstr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Gaincharacter7(final String Name, final int Channale, final int Piot) {
        try {
            int ret = Getcharacter7(Name, Channale);
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO character7 (channel, Name,Point) VALUES (?, ?, ?)");
                    ps.setInt(1, Channale);
                    ps.setString(2, Name);
                    ps.setInt(3, ret);
                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("xxxxxxxx:" + e);
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException e2) {
                        System.out.println("xxxxxxxxzzzzzzz:" + e2);
                    }
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException e2) {
                        System.out.println("xxxxxxxxzzzzzzz:" + e2);
                    }
                }
            }
            ret += Piot;
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps2 = con.prepareStatement("UPDATE character7 SET `Point` = ? WHERE Name = ? and channel = ?");
            ps2.setInt(1, ret);
            ps2.setString(2, Name);
            ps2.setInt(3, Channale);
            ps2.execute();
            ps2.close();
        } catch (SQLException sql) {
            System.err.println("Getcharacter7!!55" + sql);
        }
    }

    public static int Getcharacter7(final String Name, final int Channale) {
        int ret = -1;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM character7 WHERE channel = ? and Name = ?");
            ps.setInt(1, Channale);
            ps.setString(2, Name);
            final ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("Point");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        return ret;
    }

    public static int Get商城物品() {
        int ret = -1;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT * FROM cashshop_modified_items WHERE serial = ?");
            final int serial = 0;
            ps.setInt(1, serial);
            final ResultSet rs = ps.executeQuery();
            rs.next();
            ret = rs.getInt("meso");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        return ret;
    }

    public static void Gain商城物品(final int Piot, final int Piot1) {
        try {
            int ret = Get商城物品();
            if (ret == -1) {
                ret = 0;
                PreparedStatement ps = null;
                try {
                    ps = DatabaseConnection.getConnection().prepareStatement("INSERT INTO cashshop_modified_items (serial,meso) VALUES (?, ?)");
                    final int serial = 0;
                    ps.setInt(1, serial);
                    ps.setInt(2, ret);
                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("xxxxxxxx:" + e);
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException e2) {
                        System.out.println("xxxxxxxxzzzzzzz:" + e2);
                    }
                } finally {
                    try {
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException e2) {
                        System.out.println("xxxxxxxxzzzzzzz:" + e2);
                    }
                }
            }
            ret += Piot;
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps2 = con.prepareStatement("UPDATE cashshop_modified_items SET `meso` = ? WHERE serial = ?");
            ps2.setInt(1, ret);
            final int serial2 = 0;
            ps2.setInt(2, serial2);
            ps2.execute();
            ps2.close();
        } catch (SQLException sql) {
            System.err.println("獲取錯誤!!55" + sql);
        }
    }

    public void 刷新商城扩充价格() {
        for (int i = this.商城扩充价格.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.商城扩充价格.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM configvalues WHERE id = 999 ");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) this.商城扩充价格.getModel()).insertRow(this.商城扩充价格.getRowCount(), new Object[]{rs.getString("Val")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void 上架() {
        try {
            final int SN_ = Integer.parseInt(String.valueOf(this.charTable.getValueAt(this.charTable.getSelectedRow(), 0)));
            for (int i = this.charTable.getModel().getRowCount() - 1; i >= 0; --i) {
                ((DefaultTableModel) this.charTable.getModel()).removeRow(i);
            }
            final int OnSale_ = 1;
            final CashItemInfo merchandise = new CashItemInfo(SN_, OnSale_);
            final int success = update上下架(merchandise);
            if (success == 0) {
                JOptionPane.showMessageDialog(null, "[信息]:上架失败。");
            } else {
                this.initCharacterPannel();
                JOptionPane.showMessageDialog(null, "[信息]:上架成功。");
            }
        } catch (NumberFormatException e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "[信息]:上架失败，请选中你要上架的道具。");
        }
    }

    public void 下架() {
        try {
            final int SN_ = Integer.parseInt(String.valueOf(this.charTable.getValueAt(this.charTable.getSelectedRow(), 0)));
            for (int i = this.charTable.getModel().getRowCount() - 1; i >= 0; --i) {
                ((DefaultTableModel) this.charTable.getModel()).removeRow(i);
            }
            final int OnSale_ = 0;
            final CashItemInfo merchandise = new CashItemInfo(SN_, OnSale_);
            final int success = update上下架(merchandise);
            if (success == 0) {
                JOptionPane.showMessageDialog(null, "[信息]:下架失败。");
            } else {
                this.initCharacterPannel();
                JOptionPane.showMessageDialog(null, "[信息]:下架成功。");
            }
        } catch (NumberFormatException e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "[信息]:下架失败，请选中你要上架的道具。");
        }
    }

    public static int update上下架(final CashItemInfo merchandise) {
        PreparedStatement ps = null;
        int resulet = 0;
        final Connection conn = DatabaseConnection.getConnection();
        int i = 0;
        try {
            ps = conn.prepareStatement("update cashshop_modified_items set showup = ? where serial = ?");
            ps.setInt(++i, merchandise.getOnSale());
            ps.setInt(++i, merchandise.getSN());
            resulet = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resulet;
    }

    public void 读取商品(final int a, final int b, final int c, final int d) {
        for (int i = this.charTable.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.charTable.getModel()).removeRow(i);
        }
        this.商品编码.setText("" + a + "");
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM cashshop_modified_items WHERE serial >= " + a + " && serial < " + b + "");
            rs = ps.executeQuery();
            while (rs.next()) {
                String 上架状态 = "";
                if (rs.getInt("showup") == 0) {
                    上架状态 = "已经下架↓";
                } else {
                    上架状态 = "已经上架↑";
                }
                String 出售状态2 = "";
                switch (rs.getInt("mark")) {
                    case -1: {
                        出售状态2 = "无";
                        break;
                    }
                    case 0: {
                        出售状态2 = "NEW";
                        break;
                    }
                    case 1: {
                        出售状态2 = "Sale";
                        break;
                    }
                    case 2: {
                        出售状态2 = "HOT";
                        break;
                    }
                    case 3: {
                        出售状态2 = "Event";
                        break;
                    }
                }
                String 类型 = "";
                if ("".equals(NPCConversationManager.SN取类型(rs.getInt("serial")))) {
                    类型 = "点券";
                } else {
                    类型 = "点/抵用券";
                }
                ((DefaultTableModel) this.charTable.getModel()).insertRow(this.charTable.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("serial")), Integer.valueOf(rs.getInt("itemid")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")), Integer.valueOf(rs.getInt("count")), Integer.valueOf(rs.getInt("discount_price")), Integer.valueOf(rs.getInt("period")), 出售状态2, 上架状态, NPCConversationManager.SN取出售(rs.getInt("serial")), NPCConversationManager.SN取库存(rs.getInt("serial")), NPCConversationManager.SN取折扣(rs.getInt("serial")), NPCConversationManager.SN取限购(rs.getInt("serial")), 类型});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT `serial` FROM cashshop_modified_items WHERE serial >= " + a + " && serial <" + b + " ORDER BY `serial` DESC LIMIT 1");
            try (final ResultSet rs2 = ps.executeQuery()) {
                if (rs2.next()) {
                    final String SN = rs2.getString("serial");
                    int sns = Integer.parseInt(SN);
                    ++sns;
                    this.商品编码.setText("" + sns);
                    ps.close();
                }
            }
            ps.close();
        } catch (SQLException ex) {
            System.err.println("出错读取商品：" + ex.getMessage());
        }
        if (c == 1 && d == 1) {
            this.显示类型.setText("热销产品");
            JOptionPane.showMessageDialog(null, "[信息]:显示热销产品，双击后可在热销产品下添加商品。");
        } else if (c == 1 && d == 2) {
            this.显示类型.setText("主题馆");
            JOptionPane.showMessageDialog(null, "[信息]:显示主题馆，双击后可在主题馆下添加商品。");
        } else if (c == 1 && d == 3) {
            this.显示类型.setText("活动");
            JOptionPane.showMessageDialog(null, "[信息]:显示活动，双击后可在活动下添加商品。");
        } else if (c == 2 && d == 1) {
            this.显示类型.setText("帽子");
            JOptionPane.showMessageDialog(null, "[信息]:显示帽子，双击后可在帽子下添加商品。");
        } else if (c == 2 && d == 2) {
            this.显示类型.setText("裙裤");
            JOptionPane.showMessageDialog(null, "[信息]:显示裙裤，双击后可在裙裤下添加商品。");
        } else if (c == 2 && d == 3) {
            this.显示类型.setText("披风");
            JOptionPane.showMessageDialog(null, "[信息]:显示披风，双击后可在披风下添加商品。");
        } else if (c == 2 && d == 4) {
            this.显示类型.setText("飞镖");
            JOptionPane.showMessageDialog(null, "[信息]:显示飞镖，双击后可在飞镖下添加商品。");
        } else if (c == 2 && d == 5) {
            this.显示类型.setText("长袍");
            JOptionPane.showMessageDialog(null, "[信息]:显示长袍，双击后可在长袍下添加商品。");
        } else if (c == 2 && d == 6) {
            this.显示类型.setText("脸饰");
            JOptionPane.showMessageDialog(null, "[信息]:显示脸饰，双击后可在脸饰下添加商品。");
        } else if (c == 2 && d == 7) {
            this.显示类型.setText("鞋子");
            JOptionPane.showMessageDialog(null, "[信息]:显示鞋子，双击后可在鞋子下添加商品。");
        } else if (c == 2 && d == 8) {
            this.显示类型.setText("骑宠");
            JOptionPane.showMessageDialog(null, "[信息]:显示骑宠，双击后可在骑宠下添加商品。");
        } else if (c == 2 && d == 9) {
            this.显示类型.setText("戒指");
            JOptionPane.showMessageDialog(null, "[信息]:显示戒指，双击后可在戒指下添加商品。");
        } else if (c == 2 && d == 10) {
            this.显示类型.setText("眼饰");
            JOptionPane.showMessageDialog(null, "[信息]:显示眼饰，双击后可在眼饰下添加商品。");
        } else if (c == 2 && d == 11) {
            this.显示类型.setText("手套");
            JOptionPane.showMessageDialog(null, "[信息]:显示手套，双击后可在手套下添加商品。");
        } else if (c == 2 && d == 12) {
            this.显示类型.setText("武器");
            JOptionPane.showMessageDialog(null, "[信息]:显示武器，双击后可在武器下添加商品。");
        } else if (c == 2 && d == 13) {
            this.显示类型.setText("上衣");
            JOptionPane.showMessageDialog(null, "[信息]:显示上衣，双击后可在上衣下添加商品。");
        } else if (c == 3 && d == 1) {
            this.显示类型.setText("喜庆物品");
            JOptionPane.showMessageDialog(null, "[信息]:显示喜庆物品，双击后可在喜庆物品下添加商品。");
        } else if (c == 3 && d == 2) {
            this.显示类型.setText("通讯物品");
            JOptionPane.showMessageDialog(null, "[信息]:显示通讯物品，双击后可在通讯物品下添加商品。");
        } else if (c == 3 && d == 3) {
            this.显示类型.setText("卷轴");
            JOptionPane.showMessageDialog(null, "[信息]:显示卷轴，双击后可在卷轴下添加商品。");
        } else if (c == 4 && d == 1) {
            this.显示类型.setText("会员卡");
            JOptionPane.showMessageDialog(null, "[信息]:显示会员卡，双击后可在会员卡下添加商品。");
        } else if (c == 4 && d == 2) {
            this.显示类型.setText("表情");
            JOptionPane.showMessageDialog(null, "[信息]:显示表情，双击后可在表情下添加商品。");
        } else if (c == 4 && d == 3) {
            this.显示类型.setText("个人商店");
            JOptionPane.showMessageDialog(null, "[信息]:显示个人商店，双击后可在个人商店下添加商品。");
        } else if (c == 4 && d == 4) {
            this.显示类型.setText("效果");
            JOptionPane.showMessageDialog(null, "[信息]:显示效果，双击后可在效果下添加商品。");
        } else if (c == 4 && d == 5) {
            this.显示类型.setText("游戏");
            JOptionPane.showMessageDialog(null, "[信息]:显示游戏，双击后可在游戏下添加商品。");
        } else if (c == 4 && d == 6) {
            this.显示类型.setText("纪念日");
            JOptionPane.showMessageDialog(null, "[信息]:显示纪念日，双击后可在纪念日下添加商品。");
        } else if (c == 5 && d == 1) {
            this.显示类型.setText("宠物");
            JOptionPane.showMessageDialog(null, "[信息]:显示宠物，双击后可在宠物下添加商品。");
        } else if (c == 5 && d == 2) {
            this.显示类型.setText("宠物服饰");
            JOptionPane.showMessageDialog(null, "[信息]:显示宠物服饰，双击后可在宠物服饰下添加商品。");
        } else if (c == 5 && d == 3) {
            this.显示类型.setText("其他");
            JOptionPane.showMessageDialog(null, "[信息]:显示其他，双击后可在其他下添加商品。");
        } else {
            this.显示类型.setText("XXXX");
            JOptionPane.showMessageDialog(null, "[信息]:XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX。");
        }
    }

    public void 刷新() {
        if ("热销产品".equals(this.显示类型.getText())) {
            this.读取商品(10000000, 10100000, 1, 1);
        } else if ("主题馆".equals(this.显示类型.getText())) {
            this.读取商品(10100000, 10200000, 1, 2);
        } else if ("活动".equals(this.显示类型.getText())) {
            this.读取商品(10200000, 10300000, 1, 3);
        } else if ("帽子".equals(this.显示类型.getText())) {
            this.读取商品(20000000, 20100000, 2, 1);
        } else if ("裙裤".equals(this.显示类型.getText())) {
            this.读取商品(20500000, 20600000, 2, 2);
        } else if ("披风".equals(this.显示类型.getText())) {
            this.读取商品(21100000, 21200000, 2, 3);
        } else if ("飞镖".equals(this.显示类型.getText())) {
            this.读取商品(21000000, 21100000, 2, 4);
        } else if ("长袍".equals(this.显示类型.getText())) {
            this.读取商品(20300000, 20400000, 2, 5);
        } else if ("脸饰".equals(this.显示类型.getText())) {
            this.读取商品(20100000, 20200000, 2, 6);
        } else if ("鞋子".equals(this.显示类型.getText())) {
            this.读取商品(20600000, 20700000, 2, 7);
        } else if ("骑宠".equals(this.显示类型.getText())) {
            this.读取商品(21200000, 21300000, 2, 8);
        } else if ("戒指".equals(this.显示类型.getText())) {
            this.读取商品(20900000, 21000000, 2, 9);
        } else if ("眼饰".equals(this.显示类型.getText())) {
            this.读取商品(20200000, 20300000, 2, 10);
        } else if ("手套".equals(this.显示类型.getText())) {
            this.读取商品(20700000, 20800000, 2, 11);
        } else if ("武器".equals(this.显示类型.getText())) {
            this.读取商品(20800000, 20900000, 2, 12);
        } else if ("上衣".equals(this.显示类型.getText())) {
            this.读取商品(20400000, 20500000, 2, 13);
        } else if ("喜庆物品".equals(this.显示类型.getText())) {
            this.读取商品(30000000, 30100000, 3, 1);
        } else if ("通讯物品".equals(this.显示类型.getText())) {
            this.读取商品(30100000, 30200000, 3, 2);
        } else if ("卷轴".equals(this.显示类型.getText())) {
            this.读取商品(30200000, 30300000, 3, 3);
        } else if ("会员卡".equals(this.显示类型.getText())) {
            this.读取商品(50000000, 50100000, 4, 1);
        } else if ("表情".equals(this.显示类型.getText())) {
            this.读取商品(50100000, 50200000, 4, 2);
        } else if ("个人商店".equals(this.显示类型.getText())) {
            this.读取商品(50200000, 50300000, 4, 3);
        } else if ("效果".equals(this.显示类型.getText())) {
            this.读取商品(50500000, 50600000, 4, 4);
        } else if ("纪念日".equals(this.显示类型.getText())) {
            this.读取商品(50300000, 50400000, 4, 6);
        } else if ("游戏".equals(this.显示类型.getText())) {
            this.读取商品(50400000, 50500000, 4, 5);
        } else if ("宠物".equals(this.显示类型.getText())) {
            this.读取商品(60000000, 60100000, 5, 1);
        } else if ("宠物服饰".equals(this.显示类型.getText())) {
            this.读取商品(60100000, 60200000, 5, 2);
        } else if ("其他".equals(this.显示类型.getText())) {
            this.读取商品(60200000, 60300000, 5, 3);
        } else if ("".equals(this.显示类型.getText())) {
            this.initCharacterPannel();
        }
    }

    public void initCharacterPannel() {
        final long start = System.currentTimeMillis();
        for (int i = this.charTable.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.charTable.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM cashshop_modified_items ");
            rs = ps.executeQuery();
            while (rs.next()) {
                String itemName = "";
                itemName = MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"));
                String 上架状态 = "";
                if (rs.getInt("showup") == 0) {
                    上架状态 = "已经下架↓";
                } else {
                    上架状态 = "已经上架↑";
                }
                String 出售状态2 = "";
                switch (rs.getInt("mark")) {
                    case -1: {
                        出售状态2 = "无";
                        break;
                    }
                    case 0: {
                        出售状态2 = "NEW";
                        break;
                    }
                    case 1: {
                        出售状态2 = "Sale";
                        break;
                    }
                    case 2: {
                        出售状态2 = "HOT";
                        break;
                    }
                    case 3: {
                        出售状态2 = "Event";
                        break;
                    }
                }
                ((DefaultTableModel) this.charTable.getModel()).insertRow(this.charTable.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("serial")), Integer.valueOf(rs.getInt("itemid")), "非详细分类不显示名称", Integer.valueOf(rs.getInt("count")), Integer.valueOf(rs.getInt("discount_price")), Integer.valueOf(rs.getInt("period")), 出售状态2, 上架状态, NPCConversationManager.SN取出售(rs.getInt("serial")), NPCConversationManager.SN取库存(rs.getInt("serial")), NPCConversationManager.SN取折扣(rs.getInt("serial")), NPCConversationManager.SN取限购(rs.getInt("serial"))});
            }
            final long n = System.currentTimeMillis() - start;
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.charTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = charTable.getSelectedRow();
                final String a1 = charTable.getValueAt(i, 0).toString();
                final String a2 = charTable.getValueAt(i, 1).toString();
                final String a3 = charTable.getValueAt(i, 3).toString();
                final String a4 = charTable.getValueAt(i, 4).toString();
                final String a5 = charTable.getValueAt(i, 5).toString();
                final String a6 = charTable.getValueAt(i, 6).toString();
                final String a7 = charTable.getValueAt(i, 7).toString();
                final String a8 = charTable.getValueAt(i, 8).toString();
                final String a9 = charTable.getValueAt(i, 9).toString();
                final String a10 = charTable.getValueAt(i, 10).toString();
                final String a11 = charTable.getValueAt(i, 11).toString();
                商品编码.setText(a1);
                商品代码.setText(a2);
                商品数量.setText(a3);
                商品价格.setText(a4);
                商品时间.setText(a5);
                商品库存.setText(a9);
                商品折扣.setText(a10);
                每日限购.setText(a11);
                if (null != charTable.getValueAt(i, 6).toString()) {
                    final String string = charTable.getValueAt(i, 6).toString();
                    int n = -1;
                    switch (string.hashCode()) {
                        case 26080: {
                            if (string.equals("无")) {
                                n = 0;
                                break;
                            }
                            break;
                        }
                        case 77184: {
                            if (string.equals("NEW")) {
                                n = 1;
                                break;
                            }
                            break;
                        }
                        case 2569319: {
                            if (string.equals("Sale")) {
                                n = 2;
                                break;
                            }
                            break;
                        }
                        case 71725: {
                            if (string.equals("HOT")) {
                                n = 3;
                                break;
                            }
                            break;
                        }
                        case 67338874: {
                            if (string.equals("Event")) {
                                n = 4;
                                break;
                            }
                            break;
                        }
                    }
                    switch (n) {
                        case 0: {
                            商品出售状态.setText("-1");
                            break;
                        }
                        case 1: {
                            商品出售状态.setText("0");
                            break;
                        }
                        case 2: {
                            商品出售状态.setText("1");
                            break;
                        }
                        case 3: {
                            商品出售状态.setText("2");
                            break;
                        }
                        case 4: {
                            商品出售状态.setText("3");
                            break;
                        }
                    }
                }
            }
        });
    }

    public void 查询商店(final int lx) {
        boolean result = this.查询商店.getText().matches("[0-9]+");
        if (lx == 0) {
            result = true;
        }
        if (result) {
            if (lx != 0 && Integer.parseInt(this.查询商店.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "[信息]:请填写正确的值。");
                return;
            }
            for (int i = this.游戏商店2.getModel().getRowCount() - 1; i >= 0; --i) {
                ((DefaultTableModel) this.游戏商店2.getModel()).removeRow(i);
            }
            try {
                final Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;
                ResultSet rs = null;
                if (lx == 0) {
                    ps = con.prepareStatement("SELECT * FROM shopitems");
                } else {
                    ps = con.prepareStatement("SELECT * FROM shopitems WHERE shopid = " + Integer.parseInt(this.查询商店.getText()) + " ");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    ((DefaultTableModel) this.游戏商店2.getModel()).insertRow(this.游戏商店2.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("shopitemid")), Integer.valueOf(rs.getInt("shopid")), Integer.valueOf(rs.getInt("itemid")), Integer.valueOf(rs.getInt("price")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))});
                }
                JOptionPane.showMessageDialog(null, "[信息]:商城物品查询成功。");
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.游戏商店2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    final int i = 游戏商店2.getSelectedRow();
                    final String a = 游戏商店2.getValueAt(i, 0).toString();
                    final String a2 = 游戏商店2.getValueAt(i, 1).toString();
                    final String a3 = 游戏商店2.getValueAt(i, 2).toString();
                    final String a4 = 游戏商店2.getValueAt(i, 3).toString();
                    商品序号.setText(a);
                    商店代码.setText(a2);
                    商品物品代码.setText(a3);
                    商品售价金币.setText(a4);
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:请输入你需要查询的商店ID。");
        }
    }

    public static int 在线玩家() {
        int p = 0;
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr != null) {
                    ++p;
                }
            }
        }
        return p;
    }

    public void 读取显示账号() {
        this.账号信息.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 账号信息.getSelectedRow();
                final String a = 账号信息.getValueAt(i, 0).toString();
                final String a2 = 账号信息.getValueAt(i, 1).toString();
                final String a3 = 账号信息.getValueAt(i, 5).toString();
                final String a4 = 账号信息.getValueAt(i, 6).toString();
                final String a5 = 账号信息.getValueAt(i, 4).toString();
                QQ.setText(a5);
                final String a6 = 账号信息.getValueAt(i, 10).toString();
                账号ID.setText(a);
                账号操作.setText(a2);
                账号.setText(a2);
                点券.setText(a3);
                抵用.setText(a4);
                管理1.setText(a6);
                账号提示语言.setText("[信息]:显示账号 " + 账号.getText() + " 下角色信息。");
                CongMS.this.刷新角色信息2();
            }
        });
    }

    public static int 在线账号() {
        int data = 0;
        int p = 0;
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT loggedin as DATA FROM accounts WHERE loggedin > 0");
            try (final ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    data = rs.getInt("DATA");
                    ++p;
                }
            }
            ps.close();
        } catch (SQLException Ex) {
            System.err.println("在线账号、出错");
        }
        return p;
    }

    private void ChangePassWord() {
        final String account = this.注册的账号.getText();
        final String password = this.注册的密码.getText();
        if (password.length() > 12) {
            this.账号提示语言.setText("[信息]:修改密码失败，密码过长。");
            return;
        }
        if (!AutoRegister.getAccountExists(account)) {
            this.账号提示语言.setText("[信息]:修改密码失败，账号不存在。");
            return;
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("Update accounts set password = ? Where name = ?");
            ps.setString(1, LoginCrypto.hexSha1(password));
            ps.setString(2, account);
            ps.execute();
            ps.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "错误!\r\n" + ex);
        }
        this.账号提示语言.setText("[信息]:修改密码成功。账号: " + account + " 密码: " + password + "");
    }

    public void 注册新账号() {
        final boolean result1 = this.注册的账号.getText().matches("[0-9]+");
        final boolean result2 = this.注册的密码.getText().matches("[0-9]+");
        if (this.注册的账号.getText().equals("") || this.注册的密码.getText().equals("")) {
            this.账号提示语言.setText("[信息]:请填写注册的账号密码");
            return;
        }
        final String account = this.注册的账号.getText();
        final String password = this.注册的密码.getText();
        if (password.length() > 10) {
            this.账号提示语言.setText("[信息]:注册失败，密码过长");
            return;
        }
        if (AutoRegister.getAccountExists(account)) {
            this.账号提示语言.setText("[信息]:注册失败，账号已存在");
            return;
        }
        Connection con;
        try {
            con = DatabaseConnection.getConnection();
        } catch (Exception ex) {
            System.out.println(ex);
            return;
        }
        try {
            final PreparedStatement ps = con.prepareStatement("INSERT INTO accounts (name, password) VALUES (?,?)");
            ps.setString(1, account);
            ps.setString(2, LoginCrypto.hexSha1(password));
            ps.executeUpdate();
            this.刷新账号信息();
            this.账号提示语言.setText("[信息]:注册成功。账号: " + account + " 密码: " + password + "");
        } catch (SQLException ex2) {
            System.out.println(ex2);
        }
    }

    private void 刷新账号信息() {
        for (int i = this.账号信息.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.账号信息.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM accounts order by id desc");
            rs = ps.executeQuery();
            while (rs.next()) {
                String 封号 = "";
                if (rs.getInt("banned") == 0) {
                    封号 = "正常";
                } else {
                    封号 = "封禁";
                }
                String 在线 = "";
                if (rs.getInt("loggedin") == 0) {
                    在线 = "不在线";
                } else {
                    在线 = "在线";
                }
                String QQ = "";
                if (rs.getString("qq") != null) {
                    QQ = rs.getString("qq");
                } else {
                    QQ = "未绑定QQ";
                }
                ((DefaultTableModel) this.账号信息.getModel()).insertRow(this.账号信息.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("id")), rs.getString("name"), rs.getString("SessionIP"), rs.getString("macs"), QQ, Integer.valueOf(rs.getInt("ACash")), Integer.valueOf(rs.getInt("mPoints")), rs.getString("lastlogin"), 在线, 封号, Integer.valueOf(rs.getInt("gm"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.读取显示账号();
    }

    private void 查找QQ() {
        for (int i = this.账号信息.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.账号信息.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM accounts WHERE qq =  '" + this.账号操作.getText() + " ' ");
            rs = ps.executeQuery();
            while (rs.next()) {
                String 封号 = "";
                if (rs.getInt("banned") == 0) {
                    封号 = "正常";
                } else {
                    封号 = "封禁";
                }
                String 在线 = "";
                if (rs.getInt("loggedin") == 0) {
                    在线 = "不在线";
                } else {
                    在线 = "在线";
                }
                String QQ = "";
                if (rs.getString("qq") != null) {
                    QQ = rs.getString("qq");
                } else {
                    QQ = "未绑定QQ";
                }
                ((DefaultTableModel) this.账号信息.getModel()).insertRow(this.账号信息.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("id")), rs.getString("name"), rs.getString("SessionIP"), rs.getString("macs"), QQ, Integer.valueOf(rs.getInt("ACash")), Integer.valueOf(rs.getInt("mPoints")), rs.getString("lastlogin"), 在线, 封号, Integer.valueOf(rs.getInt("gm"))});
            }
            this.账号提示语言.setText("[信息]:查找账号 " + this.账号操作.getText() + " 成功。");
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.账号信息.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 账号信息.getSelectedRow();
                final String a = 账号信息.getValueAt(i, 0).toString();
                final String a2 = 账号信息.getValueAt(i, 1).toString();
                final String a3 = 账号信息.getValueAt(i, 5).toString();
                final String a4 = 账号信息.getValueAt(i, 6).toString();
                账号ID.setText(a);
                账号操作.setText(a2);
                账号.setText(a2);
                点券.setText(a3);
                抵用.setText(a4);
                CongMS.this.刷新角色信息2();
            }
        });
    }

    private void 查找账号() {
        for (int i = this.账号信息.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.账号信息.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM accounts WHERE name =  '" + this.账号操作.getText() + "  '");
            rs = ps.executeQuery();
            while (rs.next()) {
                String 封号 = "";
                if (rs.getInt("banned") == 0) {
                    封号 = "正常";
                } else {
                    封号 = "封禁";
                }
                String 在线 = "";
                if (rs.getInt("loggedin") == 0) {
                    在线 = "不在线";
                } else {
                    在线 = "在线";
                }
                ((DefaultTableModel) this.账号信息.getModel()).insertRow(this.账号信息.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("id")), rs.getString("name"), rs.getString("SessionIP"), rs.getString("macs"), rs.getString("qq"), Integer.valueOf(rs.getInt("ACash")), Integer.valueOf(rs.getInt("mPoints")), rs.getString("lastlogin"), 在线, 封号, Integer.valueOf(rs.getInt("gm"))});
            }
            this.账号提示语言.setText("[信息]:查找账号 " + this.账号操作.getText() + " 成功。");
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.账号信息.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 账号信息.getSelectedRow();
                final String a = 账号信息.getValueAt(i, 0).toString();
                final String a2 = 账号信息.getValueAt(i, 1).toString();
                final String a3 = 账号信息.getValueAt(i, 5).toString();
                final String a4 = 账号信息.getValueAt(i, 6).toString();
                账号ID.setText(a);
                账号操作.setText(a2);
                账号.setText(a2);
                点券.setText(a3);
                抵用.setText(a4);
                CongMS.this.刷新角色信息2();
            }
        });
    }

    private void 刷新技能信息() {
        final boolean result1 = this.角色ID.getText().matches("[0-9]+");
        if (result1) {
            for (int i = this.技能信息.getModel().getRowCount() - 1; i >= 0; --i) {
                ((DefaultTableModel) this.技能信息.getModel()).removeRow(i);
            }
            try {
                final Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;
                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM skills  WHERE characterid =" + this.角色ID.getText() + "");
                rs = ps.executeQuery();
                while (rs.next()) {
                    final MapleDataProvider data = MapleDataProviderFactory.getDataProvider(new File(System.getProperty("net.sf.odinms.wzpath") + "/String.wz"));
                    String itemName = "";
                    final MapleData itemsData = data.getData("Skill.img");
                    for (final MapleData itemFolder : itemsData.getChildren()) {
                        final int itemId = Integer.parseInt(itemFolder.getName());
                        if (rs.getInt("skillid") == itemId) {
                            itemName = MapleDataTool.getString("name", itemFolder, "NO-NAME");
                        }
                    }
                    ((DefaultTableModel) this.技能信息.getModel()).insertRow(this.技能信息.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("id")), itemName, Integer.valueOf(rs.getInt("skillid")), Integer.valueOf(rs.getInt("skilllevel")), Integer.valueOf(rs.getInt("masterlevel"))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.技能信息.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    final int i = 技能信息.getSelectedRow();
                    final String a = 技能信息.getValueAt(i, 0).toString();
                    final String a2 = 技能信息.getValueAt(i, 2).toString();
                    final String a3 = 技能信息.getValueAt(i, 3).toString();
                    final String a4 = 技能信息.getValueAt(i, 4).toString();
                    技能序号.setText(a);
                    技能代码.setText(a2);
                    技能目前等级.setText(a3);
                    技能最高等级.setText(a4);
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "[信息]:请先点击你想查看的角色。");
        }
    }

    private void 刷新角色信息() {
        final String 输出 = "";
        for (int i = this.角色信息.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.角色信息.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM characters order by id desc");
            rs = ps.executeQuery();
            while (rs.next()) {
                String 在线 = "";
                if (Find.findChannel(rs.getString("name")) > 0) {
                    在线 = "在线";
                } else {
                    在线 = "离线";
                }
                ((DefaultTableModel) this.角色信息.getModel()).insertRow(this.角色信息.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("id")), Integer.valueOf(rs.getInt("accountid")), rs.getString("name"), MapleCarnivalChallenge.getJobNameById(rs.getInt("job")), Integer.valueOf(rs.getInt("level")), Integer.valueOf(rs.getInt("str")), Integer.valueOf(rs.getInt("dex")), Integer.valueOf(rs.getInt("luk")), Integer.valueOf(rs.getInt("int")), Integer.valueOf(rs.getInt("maxhp")), Integer.valueOf(rs.getInt("maxmp")), Integer.valueOf(rs.getInt("meso")), Integer.valueOf(rs.getInt("map")), 在线, Integer.valueOf(rs.getInt("gm")), Integer.valueOf(rs.getInt("hair")), Integer.valueOf(rs.getInt("face"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.角色信息.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 角色信息.getSelectedRow();
                final String a = 角色信息.getValueAt(i, 0).toString();
                final String a2 = 角色信息.getValueAt(i, 2).toString();
                final String a3 = 角色信息.getValueAt(i, 4).toString();
                final String a4 = 角色信息.getValueAt(i, 5).toString();
                final String a5 = 角色信息.getValueAt(i, 6).toString();
                final String a6 = 角色信息.getValueAt(i, 7).toString();
                final String a7 = 角色信息.getValueAt(i, 8).toString();
                final String a8 = 角色信息.getValueAt(i, 9).toString();
                final String a9 = 角色信息.getValueAt(i, 10).toString();
                final String a10 = 角色信息.getValueAt(i, 11).toString();
                final String a11 = 角色信息.getValueAt(i, 12).toString();
                final String a12 = 角色信息.getValueAt(i, 14).toString();
                final String a13 = 角色信息.getValueAt(i, 15).toString();
                final String a14 = 角色信息.getValueAt(i, 16).toString();
                角色ID.setText(a);
                角色昵称.setText(a2);
                等级.setText(a3);
                力量.setText(a4);
                敏捷.setText(a5);
                智力.setText(a6);
                运气.setText(a7);
                HP.setText(a8);
                MP.setText(a9);
                金币1.setText(a10);
                地图.setText(a11);
                GM.setText(a12);
                发型.setText(a13);
                脸型.setText(a14);
            }
        });
    }

    private void 刷新角色背包穿戴() {
        for (int i = this.角色背包穿戴.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.角色背包穿戴.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE characterid =" + this.角色ID.getText() + " && inventorytype = -1");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) this.角色背包穿戴.getModel()).insertRow(this.角色背包穿戴.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("inventoryitemid")), Integer.valueOf(rs.getInt("itemid")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")), Integer.valueOf(rs.getInt("quantity"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.角色背包穿戴.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 角色背包穿戴.getSelectedRow();
                final String a = 角色背包穿戴.getValueAt(i, 0).toString();
                final String a2 = 角色背包穿戴.getValueAt(i, 1).toString();
                final String a3 = 角色背包穿戴.getValueAt(i, 2).toString();
                身上穿戴序号1.setText(a);
                背包物品代码1.setText(a2);
                背包物品名字1.setText(a3);
            }
        });
    }

    private void 刷新角色装备背包() {
        for (int i = this.角色装备背包.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.角色装备背包.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE characterid =" + this.角色ID.getText() + " && inventorytype = 1");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) this.角色装备背包.getModel()).insertRow(this.角色装备背包.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("inventoryitemid")), Integer.valueOf(rs.getInt("itemid")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")), Integer.valueOf(rs.getInt("quantity"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.角色装备背包.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 角色装备背包.getSelectedRow();
                final String a = 角色装备背包.getValueAt(i, 0).toString();
                final String a2 = 角色装备背包.getValueAt(i, 1).toString();
                final String a3 = 角色装备背包.getValueAt(i, 2).toString();
                装备背包物品序号.setText(a);
                装备背包物品代码.setText(a2);
                装备背包物品名字.setText(a3);
            }
        });
    }

    private void 刷新角色消耗背包() {
        for (int i = this.角色消耗背包.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.角色消耗背包.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE characterid =" + this.角色ID.getText() + " && inventorytype = 2");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) this.角色消耗背包.getModel()).insertRow(this.角色消耗背包.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("inventoryitemid")), Integer.valueOf(rs.getInt("itemid")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")), Integer.valueOf(rs.getInt("quantity"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.角色消耗背包.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 角色消耗背包.getSelectedRow();
                final String a = 角色消耗背包.getValueAt(i, 0).toString();
                final String a2 = 角色消耗背包.getValueAt(i, 1).toString();
                消耗背包物品序号.setText(a);
                消耗背包物品代码.setText(a2);
            }
        });
    }

    private void 刷新角色特殊背包() {
        for (int i = this.角色特殊背包.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.角色特殊背包.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE characterid =" + this.角色ID.getText() + " && inventorytype = 5");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) this.角色特殊背包.getModel()).insertRow(this.角色特殊背包.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("inventoryitemid")), Integer.valueOf(rs.getInt("itemid")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")), Integer.valueOf(rs.getInt("quantity"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.角色特殊背包.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 角色特殊背包.getSelectedRow();
                final String a = 角色特殊背包.getValueAt(i, 0).toString();
                final String a2 = 角色特殊背包.getValueAt(i, 1).toString();
                特殊背包物品序号.setText(a);
                特殊背包物品代码.setText(a2);
            }
        });
    }

    private void 刷新角色游戏仓库() {
        for (int i = this.角色游戏仓库.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.角色游戏仓库.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE accountid =" + this.账号ID.getText());
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) this.角色游戏仓库.getModel()).insertRow(this.角色游戏仓库.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("inventoryitemid")), Integer.valueOf(rs.getInt("itemid")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")), Integer.valueOf(rs.getInt("quantity"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.角色游戏仓库.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 角色游戏仓库.getSelectedRow();
                final String a = 角色游戏仓库.getValueAt(i, 0).toString();
                final String a2 = 角色游戏仓库.getValueAt(i, 1).toString();
                游戏仓库物品序号.setText(a);
                游戏仓库物品代码.setText(a2);
            }
        });
    }

    private void 刷新角色商城仓库() {
        for (int i = this.角色商城仓库.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.角色商城仓库.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM csitems WHERE accountid =" + this.账号ID.getText());
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) this.角色商城仓库.getModel()).insertRow(this.角色商城仓库.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("inventoryitemid")), Integer.valueOf(rs.getInt("itemid")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")), Integer.valueOf(rs.getInt("quantity"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.角色商城仓库.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 角色商城仓库.getSelectedRow();
                final String a = 角色商城仓库.getValueAt(i, 0).toString();
                final String a2 = 角色商城仓库.getValueAt(i, 1).toString();
                商城仓库物品序号.setText(a);
                商城仓库物品代码.setText(a2);
            }
        });
    }

    private void 刷新角色点券拍卖行() {
        for (int i = this.角色点券拍卖行.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.角色点券拍卖行.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM auctionitems WHERE characterid =" + this.角色ID.getText());
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) this.角色点券拍卖行.getModel()).insertRow(this.角色点券拍卖行.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("id")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")), Integer.valueOf(rs.getInt("characterName"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.角色点券拍卖行.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 角色点券拍卖行.getSelectedRow();
                final String a = 角色点券拍卖行.getValueAt(i, 0).toString();
                角色点券拍卖行序号.setText(a);
            }
        });
    }

    private void 刷新角色金币拍卖行() {
        for (int i = this.角色金币拍卖行.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.角色金币拍卖行.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM auctionitems1 WHERE characterid =" + this.角色ID.getText());
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) this.角色金币拍卖行.getModel()).insertRow(this.角色金币拍卖行.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("id")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")), Integer.valueOf(rs.getInt("characterName"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.角色金币拍卖行.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 角色金币拍卖行.getSelectedRow();
                final String a = 角色金币拍卖行.getValueAt(i, 0).toString();
                角色金币拍卖行序号.setText(a);
            }
        });
    }

    private void 刷新角色其他背包() {
        for (int i = this.角色其他背包.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.角色其他背包.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE characterid =" + this.角色ID.getText() + " && inventorytype = 4");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) this.角色其他背包.getModel()).insertRow(this.角色其他背包.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("inventoryitemid")), Integer.valueOf(rs.getInt("itemid")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")), Integer.valueOf(rs.getInt("quantity"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.角色其他背包.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 角色其他背包.getSelectedRow();
                final String a = 角色其他背包.getValueAt(i, 0).toString();
                final String a2 = 角色其他背包.getValueAt(i, 1).toString();
                其他背包物品序号.setText(a);
                其他背包物品代码.setText(a2);
            }
        });
    }

    private void 刷新角色设置背包() {
        for (int i = this.角色设置背包.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.角色设置背包.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE characterid =" + this.角色ID.getText() + " && inventorytype = 3");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) this.角色设置背包.getModel()).insertRow(this.角色设置背包.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("inventoryitemid")), Integer.valueOf(rs.getInt("itemid")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")), Integer.valueOf(rs.getInt("quantity"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.角色设置背包.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 角色设置背包.getSelectedRow();
                final String a = 角色设置背包.getValueAt(i, 0).toString();
                final String a2 = 角色设置背包.getValueAt(i, 1).toString();
                final String a3 = 角色设置背包.getValueAt(i, 2).toString();
                设置背包物品序号.setText(a);
                设置背包物品代码.setText(a2);
                设置背包物品名字.setText(a3);
            }
        });
    }

    private void 刷新角色信息2() {
        for (int i = this.角色信息.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.角色信息.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM characters WHERE accountid =" + this.账号ID.getText() + "");
            rs = ps.executeQuery();
            while (rs.next()) {
                String 在线 = "";
                if (Find.findChannel(rs.getString("name")) > 0) {
                    在线 = "在线";
                } else {
                    在线 = "离线";
                }
                ((DefaultTableModel) this.角色信息.getModel()).insertRow(this.角色信息.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("id")), Integer.valueOf(rs.getInt("accountid")), rs.getString("name"), MapleCarnivalChallenge.getJobNameById(rs.getInt("job")), Integer.valueOf(rs.getInt("level")), Integer.valueOf(rs.getInt("str")), Integer.valueOf(rs.getInt("dex")), Integer.valueOf(rs.getInt("luk")), Integer.valueOf(rs.getInt("int")), Integer.valueOf(rs.getInt("maxhp")), Integer.valueOf(rs.getInt("maxmp")), Integer.valueOf(rs.getInt("meso")), Integer.valueOf(rs.getInt("map")), 在线, Integer.valueOf(rs.getInt("gm")), Integer.valueOf(rs.getInt("hair")), Integer.valueOf(rs.getInt("face"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.角色信息.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 角色信息.getSelectedRow();
                final String a = 角色信息.getValueAt(i, 0).toString();
                final String a2 = 角色信息.getValueAt(i, 2).toString();
                final String a3 = 角色信息.getValueAt(i, 4).toString();
                final String a4 = 角色信息.getValueAt(i, 5).toString();
                final String a5 = 角色信息.getValueAt(i, 6).toString();
                final String a6 = 角色信息.getValueAt(i, 7).toString();
                final String a7 = 角色信息.getValueAt(i, 8).toString();
                final String a8 = 角色信息.getValueAt(i, 9).toString();
                final String a9 = 角色信息.getValueAt(i, 10).toString();
                final String a10 = 角色信息.getValueAt(i, 11).toString();
                final String a11 = 角色信息.getValueAt(i, 12).toString();
                final String a12 = 角色信息.getValueAt(i, 14).toString();
                final String a13 = 角色信息.getValueAt(i, 15).toString();
                final String a14 = 角色信息.getValueAt(i, 16).toString();
                角色ID.setText(a);
                角色昵称.setText(a2);
                等级.setText(a3);
                力量.setText(a4);
                敏捷.setText(a5);
                智力.setText(a6);
                运气.setText(a7);
                HP.setText(a8);
                MP.setText(a9);
                金币1.setText(a10);
                地图.setText(a11);
                GM.setText(a12);
                发型.setText(a13);
                脸型.setText(a14);
            }
        });
    }

    public void 刷新怪物卡片() {
        for (int i = this.怪物爆物.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.怪物爆物.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM drop_data WHERE itemid >=2380000&& itemid <2390000");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) this.怪物爆物.getModel()).insertRow(this.怪物爆物.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("id")), Integer.valueOf(rs.getInt("dropperid")), Integer.valueOf(rs.getInt("itemid")), Integer.valueOf(rs.getInt("chance")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.怪物爆物.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 怪物爆物.getSelectedRow();
                final String a = 怪物爆物.getValueAt(i, 0).toString();
                final String a2 = 怪物爆物.getValueAt(i, 1).toString();
                final String a3 = 怪物爆物.getValueAt(i, 2).toString();
                final String a4 = 怪物爆物.getValueAt(i, 3).toString();
                怪物爆物序列号.setText(a);
                怪物爆物怪物代码.setText(a2);
                怪物爆物物品代码.setText(a3);
                怪物爆物爆率.setText(a4);
            }
        });
    }

    public void 刷新世界爆物() {
        for (int i = this.世界爆物.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.世界爆物.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM drop_data_global WHERE itemid !=0");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) this.世界爆物.getModel()).insertRow(this.世界爆物.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("id")), Integer.valueOf(rs.getInt("itemid")), rs.getString("chance"), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))});
            }
            this.世界爆物.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    final int i = 世界爆物.getSelectedRow();
                    final String a = 世界爆物.getValueAt(i, 0).toString();
                    final String a2 = 世界爆物.getValueAt(i, 1).toString();
                    final String a3 = 世界爆物.getValueAt(i, 2).toString();
                    世界爆物序列号.setText(a);
                    世界爆物物品代码.setText(a2);
                    世界爆物爆率.setText(a3);
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void 刷新指定怪物爆物() {
        final boolean result = this.查询怪物掉落代码.getText().matches("[0-9]+");
        if (result) {
            if (Integer.parseInt(this.查询怪物掉落代码.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "请填写正确的值");
            }
            for (int i = this.怪物爆物.getModel().getRowCount() - 1; i >= 0; --i) {
                ((DefaultTableModel) this.怪物爆物.getModel()).removeRow(i);
            }
            try {
                final Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = null;
                ResultSet rs = null;
                ps = con.prepareStatement("SELECT * FROM drop_data WHERE dropperid =  " + Integer.parseInt(this.怪物爆物怪物代码.getText()) + "");
                rs = ps.executeQuery();
                while (rs.next()) {
                    ((DefaultTableModel) this.怪物爆物.getModel()).insertRow(this.怪物爆物.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("id")), Integer.valueOf(rs.getInt("dropperid")), Integer.valueOf(rs.getInt("itemid")), Integer.valueOf(rs.getInt("chance")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))});
                }
            } catch (SQLException ex) {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.怪物爆物.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    final int i = 怪物爆物.getSelectedRow();
                    final String a = 怪物爆物.getValueAt(i, 0).toString();
                    final String a2 = 怪物爆物.getValueAt(i, 1).toString();
                    final String a3 = 怪物爆物.getValueAt(i, 2).toString();
                    final String a4 = 怪物爆物.getValueAt(i, 3).toString();
                    final String a5 = 怪物爆物.getValueAt(i, 4).toString();
                    怪物爆物序列号.setText(a);
                    怪物爆物怪物代码.setText(a2);
                    怪物爆物物品代码.setText(a3);
                    怪物爆物爆率.setText(a4);
                    怪物爆物物品名称.setText(a5);
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "请输入要查询的怪物代码 ");
        }
    }

    public void 刷新怪物爆物() {
        for (int i = this.怪物爆物.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.怪物爆物.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM drop_data WHERE itemid !=0");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) this.怪物爆物.getModel()).insertRow(this.怪物爆物.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("id")), Integer.valueOf(rs.getInt("dropperid")), Integer.valueOf(rs.getInt("itemid")), Integer.valueOf(rs.getInt("chance")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid"))});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.怪物爆物.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 怪物爆物.getSelectedRow();
                final String a = 怪物爆物.getValueAt(i, 0).toString();
                final String a2 = 怪物爆物.getValueAt(i, 1).toString();
                final String a3 = 怪物爆物.getValueAt(i, 2).toString();
                final String a4 = 怪物爆物.getValueAt(i, 3).toString();
                怪物爆物序列号.setText(a);
                怪物爆物怪物代码.setText(a2);
                怪物爆物物品代码.setText(a3);
                怪物爆物爆率.setText(a4);
            }
        });
    }

    public void 刷新泡点设置() {
        for (int i = this.在线泡点设置.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.在线泡点设置.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM configvalues WHERE id = 700 || id = 702 || id = 704 || id = 706 || id = 708 || id = 712");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) this.在线泡点设置.getModel()).insertRow(this.在线泡点设置.getRowCount(), new Object[]{rs.getString("id"), rs.getString("name"), rs.getString("Val")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.在线泡点设置.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 在线泡点设置.getSelectedRow();
                final String a = 在线泡点设置.getValueAt(i, 0).toString();
                final String a2 = 在线泡点设置.getValueAt(i, 1).toString();
                final String a3 = 在线泡点设置.getValueAt(i, 2).toString();
                泡点序号.setText(a);
                泡点类型.setText(a2);
                泡点值.setText(a3);
            }
        });
    }

    private void 刷新泡点金币开关() {
        String 泡点金币开关显示 = "";
        final int 泡点金币开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("泡点金币开关"));
        if (泡点金币开关 <= 0) {
            泡点金币开关显示 = "泡点金币:开启";
        } else {
            泡点金币开关显示 = "泡点金币:关闭";
        }
        this.泡点金币开关(泡点金币开关显示);
    }

    private void 刷新泡点点券开关() {
        String 泡点点券开关显示 = "";
        final int 泡点点券开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("泡点点券开关"));
        if (泡点点券开关 <= 0) {
            泡点点券开关显示 = "泡点点券:开启";
        } else {
            泡点点券开关显示 = "泡点点券:关闭";
        }
        this.泡点点券开关(泡点点券开关显示);
    }

    private void 刷新泡点经验开关() {
        String 泡点经验开关显示 = "";
        final int 泡点经验开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("泡点经验开关"));
        if (泡点经验开关 <= 0) {
            泡点经验开关显示 = "泡点经验:开启";
        } else {
            泡点经验开关显示 = "泡点经验:关闭";
        }
        this.泡点经验开关(泡点经验开关显示);
    }

    private void 刷新泡点抵用开关() {
        String 泡点抵用开关显示 = "";
        final int 泡点抵用开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("泡点抵用开关"));
        if (泡点抵用开关 <= 0) {
            泡点抵用开关显示 = "泡点抵用:开启";
        } else {
            泡点抵用开关显示 = "泡点抵用:关闭";
        }
        this.泡点抵用开关(泡点抵用开关显示);
    }

    private void 刷新泡点豆豆开关() {
        String 泡点豆豆开关显示 = "";
        final int 泡点豆豆开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("泡点豆豆开关"));
        if (泡点豆豆开关 <= 0) {
            泡点豆豆开关显示 = "泡点豆豆:开启";
        } else {
            泡点豆豆开关显示 = "泡点豆豆:关闭";
        }
        this.泡点豆豆开关(泡点豆豆开关显示);
    }

    private void 泡点点券开关(final String str) {
        this.泡点点券开关.setText(str);
    }

    private void 泡点经验开关(final String str) {
        this.泡点经验开关.setText(str);
    }

    private void 泡点抵用开关(final String str) {
        this.泡点抵用开关.setText(str);
    }

    private void 泡点金币开关(final String str) {
        this.泡点金币开关.setText(str);
    }

    private void 泡点豆豆开关(final String str) {
        this.泡点豆豆开关.setText(str);
    }

    private void 蓝蜗牛开关(final String str) {
        this.蓝蜗牛开关.setText(str);
    }

    private void 蘑菇仔开关(final String str) {
        this.蘑菇仔开关.setText(str);
    }

    private void 绿水灵开关(final String str) {
        this.绿水灵开关.setText(str);
    }

    private void 漂漂猪开关(final String str) {
        this.漂漂猪开关.setText(str);
    }

    private void 小青蛇开关(final String str) {
        this.小青蛇开关.setText(str);
    }

    private void 红螃蟹开关(final String str) {
        this.红螃蟹开关.setText(str);
    }

    private void 大海龟开关(final String str) {
        this.大海龟开关.setText(str);
    }

    private void 章鱼怪开关(final String str) {
        this.章鱼怪开关.setText(str);
    }

    private void 顽皮猴开关(final String str) {
        this.顽皮猴开关.setText(str);
    }

    private void 星精灵开关(final String str) {
        this.星精灵开关.setText(str);
    }

    private void 胖企鹅开关(final String str) {
        this.胖企鹅开关.setText(str);
    }

    private void 白雪人开关(final String str) {
        this.白雪人开关.setText(str);
    }

    private void 紫色猫开关(final String str) {
        this.紫色猫开关.setText(str);
    }

    private void 大灰狼开关(final String str) {
        this.大灰狼开关.setText(str);
    }

    private void 小白兔开关(final String str) {
        this.小白兔开关.setText(str);
    }

    private void 喷火龙开关(final String str) {
        this.喷火龙开关.setText(str);
    }

    private void 火野猪开关(final String str) {
        this.火野猪开关.setText(str);
    }

    private void 青鳄鱼开关(final String str) {
        this.青鳄鱼开关.setText(str);
    }

    private void 花蘑菇开关(final String str) {
        this.花蘑菇开关.setText(str);
    }

    private void 刷新花蘑菇开关() {
        String 花蘑菇显示 = "";
        final int 花蘑菇 = Integer.valueOf(CongMS.ConfigValuesMap.get("花蘑菇开关"));
        if (花蘑菇 <= 0) {
            花蘑菇显示 = "花蘑菇:开";
        } else {
            花蘑菇显示 = "花蘑菇:关";
        }
        this.花蘑菇开关(花蘑菇显示);
    }

    private void 刷新火野猪开关() {
        String 火野猪显示 = "";
        final int 火野猪 = Integer.valueOf(CongMS.ConfigValuesMap.get("火野猪开关"));
        if (火野猪 <= 0) {
            火野猪显示 = "火野猪:开";
        } else {
            火野猪显示 = "火野猪:关";
        }
        this.火野猪开关(火野猪显示);
    }

    private void 刷新青鳄鱼开关() {
        String 青鳄鱼显示 = "";
        final int 青鳄鱼 = Integer.valueOf(CongMS.ConfigValuesMap.get("青鳄鱼开关"));
        if (青鳄鱼 <= 0) {
            青鳄鱼显示 = "青鳄鱼:开";
        } else {
            青鳄鱼显示 = "青鳄鱼:关";
        }
        this.青鳄鱼开关(青鳄鱼显示);
    }

    private void 刷新喷火龙开关() {
        String 喷火龙显示 = "";
        final int 喷火龙 = Integer.valueOf(CongMS.ConfigValuesMap.get("喷火龙开关"));
        if (喷火龙 <= 0) {
            喷火龙显示 = "喷火龙:开";
        } else {
            喷火龙显示 = "喷火龙:关";
        }
        this.喷火龙开关(喷火龙显示);
    }

    private void 刷新小白兔开关() {
        String 小白兔显示 = "";
        final int 小白兔 = Integer.valueOf(CongMS.ConfigValuesMap.get("小白兔开关"));
        if (小白兔 <= 0) {
            小白兔显示 = "小白兔:开";
        } else {
            小白兔显示 = "小白兔:关";
        }
        this.小白兔开关(小白兔显示);
    }

    private void 刷新大灰狼开关() {
        String 大灰狼显示 = "";
        final int 大灰狼 = Integer.valueOf(CongMS.ConfigValuesMap.get("大灰狼开关"));
        if (大灰狼 <= 0) {
            大灰狼显示 = "大灰狼:开";
        } else {
            大灰狼显示 = "大灰狼:关";
        }
        this.大灰狼开关(大灰狼显示);
    }

    private void 刷新紫色猫开关() {
        String 紫色猫显示 = "";
        final int 紫色猫 = Integer.valueOf(CongMS.ConfigValuesMap.get("紫色猫开关"));
        if (紫色猫 <= 0) {
            紫色猫显示 = "紫色猫:开";
        } else {
            紫色猫显示 = "紫色猫:关";
        }
        this.紫色猫开关(紫色猫显示);
    }

    private void 石头人开关(final String str) {
        this.石头人开关.setText(str);
    }

    private void 刷新石头人开关() {
        String 石头人显示 = "";
        final int 石头人 = Integer.valueOf(CongMS.ConfigValuesMap.get("石头人开关"));
        if (石头人 <= 0) {
            石头人显示 = "石头人:开";
        } else {
            石头人显示 = "石头人:关";
        }
        this.石头人开关(石头人显示);
    }

    private void 刷新白雪人开关() {
        String 白雪人显示 = "";
        final int 白雪人 = Integer.valueOf(CongMS.ConfigValuesMap.get("白雪人开关"));
        if (白雪人 <= 0) {
            白雪人显示 = "白雪人:开";
        } else {
            白雪人显示 = "白雪人:关";
        }
        this.白雪人开关(白雪人显示);
    }

    private void 刷新胖企鹅开关() {
        String 胖企鹅显示 = "";
        final int 胖企鹅 = Integer.valueOf(CongMS.ConfigValuesMap.get("胖企鹅开关"));
        if (胖企鹅 <= 0) {
            胖企鹅显示 = "胖企鹅:开";
        } else {
            胖企鹅显示 = "胖企鹅:关";
        }
        this.胖企鹅开关(胖企鹅显示);
    }

    private void 刷新星精灵开关() {
        String 星精灵显示 = "";
        final int 星精灵 = Integer.valueOf(CongMS.ConfigValuesMap.get("星精灵开关"));
        if (星精灵 <= 0) {
            星精灵显示 = "星精灵:开";
        } else {
            星精灵显示 = "星精灵:关";
        }
        this.星精灵开关(星精灵显示);
    }

    private void 刷新顽皮猴开关() {
        String 顽皮猴显示 = "";
        final int 顽皮猴 = Integer.valueOf(CongMS.ConfigValuesMap.get("顽皮猴开关"));
        if (顽皮猴 <= 0) {
            顽皮猴显示 = "顽皮猴:开";
        } else {
            顽皮猴显示 = "顽皮猴:关";
        }
        this.顽皮猴开关(顽皮猴显示);
    }

    private void 刷新章鱼怪开关() {
        String 章鱼怪显示 = "";
        final int 章鱼怪 = Integer.valueOf(CongMS.ConfigValuesMap.get("章鱼怪开关"));
        if (章鱼怪 <= 0) {
            章鱼怪显示 = "章鱼怪:开";
        } else {
            章鱼怪显示 = "章鱼怪:关";
        }
        this.章鱼怪开关(章鱼怪显示);
    }

    private void 刷新大海龟开关() {
        String 大海龟显示 = "";
        final int 大海龟 = Integer.valueOf(CongMS.ConfigValuesMap.get("大海龟开关"));
        if (大海龟 <= 0) {
            大海龟显示 = "大海龟:开";
        } else {
            大海龟显示 = "大海龟:关";
        }
        this.大海龟开关(大海龟显示);
    }

    private void 刷新红螃蟹开关() {
        String 红螃蟹显示 = "";
        final int 红螃蟹 = Integer.valueOf(CongMS.ConfigValuesMap.get("红螃蟹开关"));
        if (红螃蟹 <= 0) {
            红螃蟹显示 = "红螃蟹:开";
        } else {
            红螃蟹显示 = "红螃蟹:关";
        }
        this.红螃蟹开关(红螃蟹显示);
    }

    private void 刷新小青蛇开关() {
        String 小青蛇显示 = "";
        final int 小青蛇 = Integer.valueOf(CongMS.ConfigValuesMap.get("小青蛇开关"));
        if (小青蛇 <= 0) {
            小青蛇显示 = "小青蛇:开";
        } else {
            小青蛇显示 = "小青蛇:关";
        }
        this.小青蛇开关(小青蛇显示);
    }

    private void 刷新蓝蜗牛开关() {
        String 蓝蜗牛显示 = "";
        final int 蓝蜗牛 = Integer.valueOf(CongMS.ConfigValuesMap.get("蓝蜗牛开关"));
        if (蓝蜗牛 <= 0) {
            蓝蜗牛显示 = "蓝蜗牛:开";
        } else {
            蓝蜗牛显示 = "蓝蜗牛:关";
        }
        this.蓝蜗牛开关(蓝蜗牛显示);
    }

    private void 刷新漂漂猪开关() {
        String 漂漂猪显示 = "";
        final int 漂漂猪 = Integer.valueOf(CongMS.ConfigValuesMap.get("漂漂猪开关"));
        if (漂漂猪 <= 0) {
            漂漂猪显示 = "漂漂猪:开";
        } else {
            漂漂猪显示 = "漂漂猪:关";
        }
        this.漂漂猪开关(漂漂猪显示);
    }

    private void 刷新绿水灵开关() {
        String 绿水灵显示 = "";
        final int 绿水灵 = Integer.valueOf(CongMS.ConfigValuesMap.get("绿水灵开关"));
        if (绿水灵 <= 0) {
            绿水灵显示 = "绿水灵:开";
        } else {
            绿水灵显示 = "绿水灵:关";
        }
        this.绿水灵开关(绿水灵显示);
    }

    private void 刷新蘑菇仔开关() {
        String 蘑菇仔显示 = "";
        final int 蘑菇仔 = Integer.valueOf(CongMS.ConfigValuesMap.get("蘑菇仔开关"));
        if (蘑菇仔 <= 0) {
            蘑菇仔显示 = "蘑菇仔:开";
        } else {
            蘑菇仔显示 = "蘑菇仔:关";
        }
        this.蘑菇仔开关(蘑菇仔显示);
    }

    private void 刷新指令通知开关() {
        String 刷新指令通知开关显示 = "";
        final int 指令通知开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("指令通知开关"));
        if (指令通知开关 <= 0) {
            刷新指令通知开关显示 = "指令通知:开启";
        } else {
            刷新指令通知开关显示 = "指令通知:关闭";
        }
        this.指令通知开关(刷新指令通知开关显示);
    }

    private void 刷新玩家聊天开关() {
        String 刷新玩家聊天开关显示 = "";
        final int 玩家聊天开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("玩家聊天开关"));
        if (玩家聊天开关 <= 0) {
            刷新玩家聊天开关显示 = "玩家聊天:开启";
        } else {
            刷新玩家聊天开关显示 = "玩家聊天:关闭";
        }
        this.玩家聊天开关(刷新玩家聊天开关显示);
    }

    private void 刷新禁止登陆开关() {
        String 刷新禁止登陆开关显示 = "";
        final int 禁止登陆开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("禁止登陆开关"));
        if (禁止登陆开关 <= 0) {
            刷新禁止登陆开关显示 = "游戏登陆:禁止";
        } else {
            刷新禁止登陆开关显示 = "游戏登陆:通行";
        }
        this.禁止登陆开关(刷新禁止登陆开关显示);
    }

    private void 刷新升级快讯() {
        String 升级快讯显示 = "";
        final int 升级快讯 = Integer.valueOf(CongMS.ConfigValuesMap.get("升级快讯开关"));
        if (升级快讯 <= 0) {
            升级快讯显示 = "升级快讯:开启";
        } else {
            升级快讯显示 = "升级快讯:关闭";
        }
        this.游戏升级快讯(升级快讯显示);
    }

    private void 刷新丢出金币开关() {
        String 刷新丢出金币开关显示 = "";
        final int 丢出金币开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("丢出金币开关"));
        if (丢出金币开关 <= 0) {
            刷新丢出金币开关显示 = "丢出金币:开启";
        } else {
            刷新丢出金币开关显示 = "丢出金币:关闭";
        }
        this.丢出金币开关(刷新丢出金币开关显示);
    }

    private void 刷新玩家交易开关() {
        String 刷新玩家交易开关显示 = "";
        final int 玩家交易开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("玩家交易开关"));
        if (玩家交易开关 <= 0) {
            刷新玩家交易开关显示 = "玩家交易:开启";
        } else {
            刷新玩家交易开关显示 = "玩家交易:关闭";
        }
        this.玩家交易开关(刷新玩家交易开关显示);
    }

    private void 刷新丢出物品开关() {
        String 刷新丢出物品开关显示 = "";
        final int 丢出物品开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("丢出物品开关"));
        if (丢出物品开关 <= 0) {
            刷新丢出物品开关显示 = "丢出物品:开启";
        } else {
            刷新丢出物品开关显示 = "丢出物品:关闭";
        }
        this.丢出物品开关(刷新丢出物品开关显示);
    }

    private void 刷新游戏指令开关() {
        String 刷新游戏指令开关显示 = "";
        final int 游戏指令开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("游戏指令开关"));
        if (游戏指令开关 <= 0) {
            刷新游戏指令开关显示 = "游戏指令:关闭";
        } else {
            刷新游戏指令开关显示 = "游戏指令:开启";
        }
        this.游戏指令开关(刷新游戏指令开关显示);
    }

    private void 刷新上线提醒开关() {
        String 刷新上线提醒开关显示 = "";
        final int 上线提醒开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("上线提醒开关"));
        if (上线提醒开关 <= 0) {
            刷新上线提醒开关显示 = "登录公告:开启";
        } else {
            刷新上线提醒开关显示 = "登录公告:关闭";
        }
        this.上线提醒开关(刷新上线提醒开关显示);
    }

    private void 刷新回收地图开关() {
        String 刷新回收地图开关显示 = "";
        final int 回收地图开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("回收地图开关"));
        if (回收地图开关 <= 0) {
            刷新回收地图开关显示 = "回收地图:开启";
        } else {
            刷新回收地图开关显示 = "回收地图:关闭";
        }
        this.回收地图开关(刷新回收地图开关显示);
    }

    private void 刷新管理隐身开关() {
        String 刷新管理隐身开关显示 = "";
        final int 管理隐身开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("管理隐身开关"));
        if (管理隐身开关 <= 0) {
            刷新管理隐身开关显示 = "管理隐身:开启";
        } else {
            刷新管理隐身开关显示 = "管理隐身:关闭";
        }
        this.管理隐身开关(刷新管理隐身开关显示);
    }

    private void 刷新管理加速开关() {
        String 刷新管理加速开关显示 = "";
        final int 管理加速开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("管理加速开关"));
        if (管理加速开关 <= 0) {
            刷新管理加速开关显示 = "管理加速:开启";
        } else {
            刷新管理加速开关显示 = "管理加速:关闭";
        }
        this.管理加速开关(刷新管理加速开关显示);
    }

    private void 刷新雇佣商人开关() {
        String 刷新雇佣商人开关显示 = "";
        final int 雇佣商人开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("雇佣商人开关"));
        if (雇佣商人开关 <= 0) {
            刷新雇佣商人开关显示 = "雇佣商人:开启";
        } else {
            刷新雇佣商人开关显示 = "雇佣商人:关闭";
        }
        this.雇佣商人开关(刷新雇佣商人开关显示);
    }

    private void 刷新欢迎弹窗开关() {
        String 刷新欢迎弹窗开关显示 = "";
        final int 欢迎弹窗开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("欢迎弹窗开关"));
        if (欢迎弹窗开关 <= 0) {
            刷新欢迎弹窗开关显示 = "欢迎弹窗:开启";
        } else {
            刷新欢迎弹窗开关显示 = "欢迎弹窗:关闭";
        }
        this.欢迎弹窗开关(刷新欢迎弹窗开关显示);
    }

    private void 刷新滚动公告开关() {
        String 刷新滚动公告开关显示 = "";
        final int 滚动公告开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("滚动公告开关"));
        if (滚动公告开关 <= 0) {
            刷新滚动公告开关显示 = "滚动公告:开启";
        } else {
            刷新滚动公告开关显示 = "滚动公告:关闭";
        }
        this.滚动公告开关(刷新滚动公告开关显示);
    }

    private void 刷新游戏喇叭开关() {
        String 刷新游戏喇叭开关显示 = "";
        final int 游戏喇叭开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("游戏喇叭开关"));
        if (游戏喇叭开关 <= 0) {
            刷新游戏喇叭开关显示 = "游戏喇叭:开启";
        } else {
            刷新游戏喇叭开关显示 = "游戏喇叭:关闭";
        }
        this.游戏喇叭开关(刷新游戏喇叭开关显示);
    }

    private void 滚动公告开关(final String str) {
        this.滚动公告开关.setText(str);
    }

    private void 回收地图开关(final String str) {
        this.回收地图开关.setText(str);
    }

    private void 玩家聊天开关(final String str) {
        this.玩家聊天开关.setText(str);
    }

    private void 上线提醒开关(final String str) {
        this.上线提醒开关.setText(str);
    }

    private void 指令通知开关(final String str) {
        this.指令通知开关.setText(str);
    }

    private void 雇佣商人开关(final String str) {
        this.雇佣商人开关.setText(str);
    }

    private void 欢迎弹窗开关(final String str) {
        this.欢迎弹窗开关.setText(str);
    }

    private void 管理隐身开关(final String str) {
        this.管理隐身开关.setText(str);
    }

    private void 管理加速开关(final String str) {
        this.管理加速开关.setText(str);
    }

    private void 游戏指令开关(final String str) {
        this.游戏指令开关.setText(str);
    }

    private void 游戏喇叭开关(final String str) {
        this.游戏喇叭开关.setText(str);
    }

    private void 丢出金币开关(final String str) {
        this.丢出金币开关.setText(str);
    }

    private void 玩家交易开关(final String str) {
        this.玩家交易开关.setText(str);
    }

    private void 丢出物品开关(final String str) {
        this.丢出物品开关.setText(str);
    }

    private void 禁止登陆开关(final String str) {
        this.禁止登陆开关.setText(str);
    }

    private void 游戏升级快讯(final String str) {
        this.游戏升级快讯.setText(str);
    }

    private void 冒险家等级上限(final String str) {
        this.冒险家等级上限.setText(str);
    }

    private void 骑士团等级上限(final String str) {
        this.骑士团等级上限.setText(str);
    }

    private void 游戏冒险家职业开关(final String str) {
        this.冒险家职业开关.setText(str);
    }

    private void 游戏骑士团职业开关(final String str) {
        this.骑士团职业开关.setText(str);
    }

    private void 游戏战神职业开关(final String str) {
        this.战神职业开关.setText(str);
    }

    private void 屠令广播开关(final String str) {
        this.屠令广播开关.setText(str);
    }

    private void 刷新登陆帮助() {
        String 显示 = "";
        final int S = Integer.valueOf(CongMS.ConfigValuesMap.get("登陆帮助开关"));
        if (S <= 0) {
            显示 = "登陆帮助:开启";
        } else {
            显示 = "登陆帮助:关闭";
        }
        this.登陆帮助开关.setText(显示);
    }

    private void 刷新怪物状态开关() {
        String 显示 = "";
        final int S = Integer.valueOf(CongMS.ConfigValuesMap.get("怪物状态开关"));
        if (S <= 0) {
            显示 = "怪物状态:开启";
        } else {
            显示 = "怪物状态:关闭";
        }
        this.怪物状态开关.setText(显示);
    }

    private void 刷新越级打怪开关() {
        String 显示 = "";
        final int S = Integer.valueOf(CongMS.ConfigValuesMap.get("越级打怪开关"));
        if (S <= 0) {
            显示 = "越级打怪:开启";
        } else {
            显示 = "越级打怪:关闭";
        }
        this.越级打怪开关.setText(显示);
    }

    private void 刷新地图名称开关() {
        String 显示 = "";
        final int S = Integer.valueOf(CongMS.ConfigValuesMap.get("地图名称开关"));
        if (S <= 0) {
            显示 = "地图名称:显示";
        } else {
            显示 = "地图名称:关闭";
        }
        this.地图名称开关.setText(显示);
    }

    private void 刷新过图存档时间() {
        String 显示 = "";
        final int S = Integer.valueOf(CongMS.ConfigValuesMap.get("过图存档开关"));
        if (S <= 0) {
            显示 = "过图存档:开启";
        } else {
            显示 = "过图存档:关闭";
        }
        this.过图存档开关.setText(显示);
    }

    private void 刷新吸怪检测开关() {
        String 显示 = "";
        final int S = Integer.valueOf(CongMS.ConfigValuesMap.get("吸怪检测开关"));
        if (S <= 0) {
            显示 = "吸怪检测:开启";
        } else {
            显示 = "吸怪检测:关闭";
        }
        this.吸怪检测开关.setText(显示);
    }

    private void 刷新冒险家等级上限() {
        String 冒险家等级上限显示 = "";
        final int 冒险家等级上限 = Integer.valueOf(CongMS.ConfigValuesMap.get("冒险家等级上限"));
        冒险家等级上限显示 = "" + 冒险家等级上限;
        this.冒险家等级上限(冒险家等级上限显示);
    }

    private void 刷新骑士团等级上限() {
        String 骑士团等级上限显示 = "";
        final int 骑士团等级上限 = Integer.valueOf(CongMS.ConfigValuesMap.get("骑士团等级上限"));
        骑士团等级上限显示 = "" + 骑士团等级上限;
        this.骑士团等级上限(骑士团等级上限显示);
    }

    private void 刷新冒险家职业开关() {
        String 冒险家职业开关显示 = "";
        final int 冒险家职业开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("冒险家职业开关"));
        if (冒险家职业开关 <= 0) {
            冒险家职业开关显示 = "冒险家:开启";
        } else {
            冒险家职业开关显示 = "冒险家:关闭";
        }
        this.游戏冒险家职业开关(冒险家职业开关显示);
    }

    private void 刷新骑士团职业开关() {
        String 骑士团职业开关显示 = "";
        final int 骑士团职业开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("骑士团职业开关"));
        if (骑士团职业开关 <= 0) {
            骑士团职业开关显示 = "骑士团:开启";
        } else {
            骑士团职业开关显示 = "骑士团:关闭";
        }
        this.游戏骑士团职业开关(骑士团职业开关显示);
    }

    private void 刷新战神职业开关() {
        String 战神职业开关显示 = "";
        final int 战神职业开关 = Integer.valueOf(CongMS.ConfigValuesMap.get("战神职业开关"));
        if (战神职业开关 <= 0) {
            战神职业开关显示 = "战   神:开启";
        } else {
            战神职业开关显示 = "战   神:关闭";
        }
        this.游戏战神职业开关(战神职业开关显示);
    }

    private void 刷新屠令广播开关() {
        String 屠令广播显示 = "";
        final int 屠令广播 = Integer.valueOf(CongMS.ConfigValuesMap.get("屠令广播开关"));
        if (屠令广播 <= 0) {
            屠令广播显示 = "屠令广播:开启";
        } else {
            屠令广播显示 = "屠令广播:关闭";
        }
        this.屠令广播开关(屠令广播显示);
    }

    private void 重启服务器() {
        try {
            final String 输出 = "关闭服务器倒数时间";
            this.minutesLeft = Integer.parseInt(this.jTextField22.getText());
            if (CongMS.ts == null && (CongMS.t == null || !CongMS.t.isAlive())) {
                CongMS.t = new Thread(ShutdownServer.getInstance());
                CongMS.ts = EventTimer.getInstance().register(new Runnable() {
                    @Override
                    public void run() {
                        if (minutesLeft == 0) {
                            ShutdownServer.getInstance();
                            t.start();
                            ts.cancel(false);
                            return;
                        }
                        Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(0, "本服将在 " + minutesLeft + "分钟后关闭. 请尽速关闭雇佣商人 并下线，以免造成损失."));
                        System.out.println("本服将在 " + minutesLeft + "分钟后关闭.");
                        minutesLeft--;
                    }
                }, 60000L);
            }
            this.jTextField22.setText("关闭服务器倒数时间");
            this.printChatLog(输出);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "错误!\r\n" + e);
        }
    }

    public void 按键开关(final String a, final int b) {
        final int 检测开关 = Integer.valueOf(CongMS.ConfigValuesMap.get(a));
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        if (检测开关 > 0) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps2.setInt(1, b);
                rs = ps2.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    final String sqlString3 = null;
                    final String sqlString4 = null;
                    sqlString2 = "update configvalues set Val= '0' where id= '" + b + "';";
                    final PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps2.setInt(1, b);
                rs = ps2.executeQuery();
                if (rs.next()) {
                    String sqlString2 = null;
                    final String sqlString3 = null;
                    final String sqlString4 = null;
                    sqlString2 = "update configvalues set Val= '1' where id='" + b + "';";
                    final PreparedStatement dropperid = DatabaseConnection.getConnection().prepareStatement(sqlString2);
                    dropperid.executeUpdate(sqlString2);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        GetConfigValues();
    }

    private void printChatLog(final String str) {
        this.输出窗口.setText(this.输出窗口.getText() + str + "\r\n");
    }

    private void sendNoticeGG() {
        try {
            final String str = this.jTextField2.getText();
            String 输出 = "";
            for (final ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (final MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    mch.startMapEffect(str, 5121009);
                    输出 = "[公告]:" + str;
                }
            }
            this.jTextField2.setText("");
            this.printChatLog(输出);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "错误!\r\n" + e);
        }
    }

    private void sendNotice(final int a) {
        try {
            final String str = this.noticeText.getText();
            final String 输出 = "";
            for (final ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (final MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    switch (a) {
                        case 0: {
                            Broadcast.broadcastMessage(MaplePacketCreator.getItemNotice(str.toString()));
                            continue;
                        }
                        case 1: {
                            Broadcast.broadcastMessage(MaplePacketCreator.serverMessage(str.toString()));
                            continue;
                        }
                        case 2: {
                            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(1, str));
                            continue;
                        }
                        case 3: {
                            Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(0, str));
                            continue;
                        }
                        case 4: {
                            mch.startMapEffect(str, Integer.parseInt(this.公告发布喇叭代码.getText()));
                            continue;
                        }
                    }
                }
                this.公告发布喇叭代码.setText("5120027");
            }
        } catch (Exception ex) {
        }
    }

    private void 刷新家族信息() {
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM characters");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = this.家族信息.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.家族信息.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM guilds");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) this.家族信息.getModel()).insertRow(this.家族信息.getRowCount(), new Object[]{Integer.valueOf(rs.getInt("guildid")), rs.getString("name"), rs.getString("rank1title"), rs.getString("rank2title"), rs.getString("rank3title"), rs.getString("rank4title"), rs.getString("rank5title"), rs.getString("GP")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.家族信息.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 家族信息.getSelectedRow();
                final String a1 = 家族信息.getValueAt(i, 0).toString();
                final String a2 = 家族信息.getValueAt(i, 1).toString();
                final String a3 = 家族信息.getValueAt(i, 2).toString();
                final String a4 = 家族信息.getValueAt(i, 3).toString();
                final String a5 = 家族信息.getValueAt(i, 4).toString();
                final String a6 = 家族信息.getValueAt(i, 5).toString();
                final String a7 = 家族信息.getValueAt(i, 6).toString();
                final String a8 = 家族信息.getValueAt(i, 7).toString();
                家族ID.setText(a1);
                家族名称.setText(a2);
                家族族长.setText(a3);
                家族成员2.setText(a4);
                家族成员3.setText(a5);
                家族成员4.setText(a6);
                家族成员5.setText(a7);
                家族GP.setText(a8);
            }
        });
    }

    public void 刷新经验加成表() {
        for (int i = this.经验加成表.getModel().getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel) this.经验加成表.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM configvalues WHERE id = 150 ||  id = 151  ||  id=152  ||  id=153  ");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel) this.经验加成表.getModel()).insertRow(this.经验加成表.getRowCount(), new Object[]{rs.getString("id"), rs.getString("name"), rs.getString("Val")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.经验加成表.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 经验加成表.getSelectedRow();
                final String a = 经验加成表.getValueAt(i, 0).toString();
                final String a2 = 经验加成表.getValueAt(i, 1).toString();
                final String a3 = 经验加成表.getValueAt(i, 2).toString();
                经验加成表序号.setText(a);
                经验加成表类型.setText(a2);
                经验加成表数值.setText(a3);
            }
        });
    }

    /* public static void main(final String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            for (int i = 0; i < CongMS.DEFAULT_FONT.length; ++i) {
                UIManager.put((Object)CongMS.DEFAULT_FONT[i], (Object)new Font("微软雅黑", 0, 14));
            }
        }
        catch (Exception ex) {}
        EventQueue.invokeLater((Runnable)new Runnable() {
            @Override
            public void run() {
                new CongMS().setVisible(true);
                System.out.println("初始化完成");
            }
        });
    }*/
    public static void main(final String[] args) {
        /*界面風格
         Metal
         Nimbus
         CDE/Motif
         Windows
         Windows Classic
         */

        //苹果皮肤
        starttime = System.currentTimeMillis();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
                    BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
                    org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
                    UIManager.put("RootPane.setupButtonVisible", false);//苹果设置开关
                    //BeautyEyeLNFHelper.translucencyAtFrameInactive = true;
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//显示win标题
                } catch (Exception e) {
                    e.printStackTrace();
                }
                instance = new CongMS();
                instance.setVisible(true);
            }
        });
    }


    static {
        CongMS.ConfigValuesMap = new HashMap<String, Integer>();
        CongMS.instance = new CongMS();
        CongMS.DEFAULT_FONT = new String[]{"Table.font", "TableHeader.font", "CheckBox.font", "Tree.font", "Viewport.font", "ProgressBar.font", "RadioButtonMenuItem.font", "ToolBar.font", "ColorChooser.font", "ToggleButton.font", "Panel.font", "TextArea.font", "Menu.font", "TableHeader.font", "OptionPane.font", "MenuBar.font", "Button.font", "Label.font", "PasswordField.font", "ScrollPane.font", "MenuItem.font", "ToolTip.font", "List.font", "EditorPane.font", "Table.font", "TabbedPane.font", "RadioButton.font", "CheckBoxMenuItem.font", "TextPane.font", "PopupMenu.font", "TitledBorder.font", "ComboBox.font"};
        CongMS.ts = null;
        CongMS.t = null;
    }

    public class HomePanel extends JPanel {
        ImageIcon icon;
        Image img;

        public HomePanel() {
            final ImageIcon icon = new ImageIcon(this.getClass().getClassLoader().getResource("image/logo.png"));
            this.img = icon.getImage();
        }

        public void paintComponent(final Graphics g) {
            super.paintComponent(g);
            g.drawImage(this.img, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public class HomePanel2 extends JPanel {
        ImageIcon icon;
        Image img;

        public HomePanel2() {
            final ImageIcon icon = new ImageIcon(this.getClass().getClassLoader().getResource("image/long.gif"));
            this.img = icon.getImage();
        }

        public void paintComponent(final Graphics g) {
            super.paintComponent(g);
            g.drawImage(this.img, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    private class Dis extends Thread {
        public Dis() {
        }

        @Override
        public void run() {
            while (true) {
                final CongMS this$0 = CongMS.this;
                ++t1;
                jLabel25.setText("-" + t1 / 60);
                try {
                    Thread.sleep(1000L);
                    if (hour == 0) {
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public enum Windows {
        一键还原,
        代码查询工具,
        活动控制台,
        游戏抽奖工具,
        删除自添加NPC工具;
    }
}
