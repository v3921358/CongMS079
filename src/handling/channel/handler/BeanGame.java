package handling.channel.handler;

import java.util.List;
import client.MapleCharacter;
import tools.FileoutputUtil;
import constants.GameConstants;
import server.Randomizer;
import tools.MaplePacketCreator;
import client.MapleBeans;
import java.util.ArrayList;
import constants.BeansConstants;
import client.MapleClient;
import tools.data.LittleEndianAccessor;

public class BeanGame
{
    public static int 进洞次数;
    public static int 第一排;
    public static int 第三排;
    public static int 第二排;
    public static int 启动打怪效果;
    public static int 中奖率;
    public static int 加速旋转;
    public static boolean 打中女皇出现特效A;
    public static boolean 打中女皇出现特效B;
    public static int 蓝;
    public static int 绿;
    public static int 红;
    public static int 黄金狗设置局数;
    
    public static final void BeansGameAction(final LittleEndianAccessor slea, final MapleClient c) {
        final BeansConstants Beans = new BeansConstants();
        final int 海洋帽子几率 = Beans.get海洋帽子几率();
        final int 力度搞假 = Beans.get力度搞假();
        final int 豆豆奖励范围 = Beans.get豆豆獎勵範圍();
        final String[] 黄金狗几率 = Beans.get黄金狗几率();
        final String[] 大白怪 = Beans.get大白怪();
        final String[] 小白怪 = Beans.get小白怪();
        final String[] 紫色怪 = Beans.get紫色怪();
        final String[] 粉色怪 = Beans.get粉色怪();
        final String[] 飞侠 = Beans.get飛俠();
        final String[] 海盗 = Beans.get海盜();
        final String[] 法师 = Beans.get法師();
        final String[] 战士 = Beans.get戰士();
        final String[] 弓箭手 = Beans.get弓箭手();
        final String[] 女皇 = Beans.get女皇();
        final String[] 白怪奖励 = Beans.get白怪獎勵();
        final String[] 色怪奖励 = Beans.get色怪獎勵();
        final String[] 五职业奖励 = Beans.get五职业獎勵();
        final String[] 女皇奖励 = Beans.get女皇獎勵();
        final MapleCharacter chr = c.getPlayer();
        final List<MapleBeans> beansInfo = new ArrayList<MapleBeans>();
        final int type = slea.readByte();
        int 力度 = 0;
        int 豆豆序号 = 0;
        if (chr.getBeans() <= 0) {
            c.getPlayer().dropMessage(1, "你沒有小鋼珠，無法使用。");
            c.sendPacket(MaplePacketCreator.enableActions());
            return;
        }
        switch (type) {
            case 0: {
                slea.readShort();
                力度 = Randomizer.rand(1000, 5000);
                chr.setBeansRange(力度);
                c.getSession().write((Object)MaplePacketCreator.enableActions());
                break;
            }
            case 1: {
                chr.setBeansRange(力度);
                c.getSession().write((Object)MaplePacketCreator.enableActions());
                break;
            }
            case 2: {
                if (get进洞次数() > 1) {
                    set进洞次数(0);
                    break;
                }
                set进洞次数(0);
                break;
            }
            case 3: {
                gain进洞次数(1);
                if (get进洞次数() > 7) {
                    set进洞次数(7);
                }
                c.getSession().write((Object)MaplePacketCreator.BeansJDCS(get进洞次数(), BeanGame.加速旋转, BeanGame.蓝, BeanGame.绿, BeanGame.红));
                break;
            }
            case 4: {
                if (get进洞次数() == 0) {
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                gain进洞次数(-1);
                int 概率 = 0;
                if (BeanGame.黄金狗设置局数 > 0) {
                    概率 = 100;
                }
                if (Randomizer.nextInt(Integer.parseInt(大白怪[0])) > Integer.parseInt(大白怪[1]) && 概率 != 100) {
                    BeanGame.第一排 = 0;
                    BeanGame.第三排 = 0;
                    if (Randomizer.nextInt(Integer.parseInt(大白怪[2])) > Integer.parseInt(大白怪[3])) {
                        BeanGame.第二排 = 0;
                        BeanGame.中奖率 = 100;
                    }
                    else {
                        BeanGame.中奖率 = 0;
                    }
                    BeanGame.启动打怪效果 = 1;
                    BeanGame.打中女皇出现特效A = false;
                    BeanGame.打中女皇出现特效B = false;
                }
                else if (Randomizer.nextInt(Integer.parseInt(紫色怪[0])) > Integer.parseInt(紫色怪[1]) && 概率 != 100) {
                    BeanGame.第一排 = 9;
                    BeanGame.第三排 = 7;
                    if (Randomizer.nextInt(Integer.parseInt(紫色怪[2])) > Integer.parseInt(紫色怪[3])) {
                        BeanGame.第二排 = 1;
                        BeanGame.中奖率 = 100;
                    }
                    else {
                        BeanGame.中奖率 = 0;
                    }
                    BeanGame.启动打怪效果 = 1;
                    BeanGame.打中女皇出现特效A = false;
                    BeanGame.打中女皇出现特效B = false;
                }
                else if (Randomizer.nextInt(Integer.parseInt(粉色怪[0])) > Integer.parseInt(粉色怪[1]) && 概率 != 100) {
                    BeanGame.第一排 = 1;
                    BeanGame.第三排 = 2;
                    if (Randomizer.nextInt(Integer.parseInt(粉色怪[2])) > Integer.parseInt(粉色怪[3])) {
                        BeanGame.第二排 = 3;
                        BeanGame.中奖率 = 100;
                    }
                    else {
                        BeanGame.中奖率 = 0;
                    }
                    BeanGame.启动打怪效果 = 1;
                    BeanGame.打中女皇出现特效A = false;
                    BeanGame.打中女皇出现特效B = false;
                }
                else if (Randomizer.nextInt(Integer.parseInt(小白怪[0])) > Integer.parseInt(小白怪[1]) && 概率 != 100) {
                    BeanGame.第一排 = 5;
                    BeanGame.第三排 = 1;
                    if (Randomizer.nextInt(Integer.parseInt(小白怪[2])) > Integer.parseInt(小白怪[3])) {
                        BeanGame.第二排 = 5;
                        BeanGame.中奖率 = 100;
                    }
                    else {
                        BeanGame.中奖率 = 0;
                    }
                    BeanGame.启动打怪效果 = 1;
                    BeanGame.打中女皇出现特效A = false;
                    BeanGame.打中女皇出现特效B = false;
                }
                else if (Randomizer.nextInt(Integer.parseInt(飞侠[0])) > Integer.parseInt(飞侠[1]) && 概率 != 100) {
                    BeanGame.第一排 = 4;
                    BeanGame.第三排 = 9;
                    if (Randomizer.nextInt(Integer.parseInt(飞侠[2])) > Integer.parseInt(飞侠[3])) {
                        BeanGame.第二排 = 2;
                        BeanGame.中奖率 = 100;
                    }
                    else {
                        BeanGame.中奖率 = 0;
                    }
                    BeanGame.启动打怪效果 = 1;
                    BeanGame.打中女皇出现特效A = false;
                    BeanGame.打中女皇出现特效B = false;
                }
                else if (Randomizer.nextInt(Integer.parseInt(海盗[0])) > Integer.parseInt(海盗[1]) && 概率 != 100) {
                    BeanGame.第一排 = 7;
                    BeanGame.第三排 = 5;
                    if (Randomizer.nextInt(Integer.parseInt(海盗[2])) > Integer.parseInt(海盗[3])) {
                        BeanGame.第二排 = 4;
                        BeanGame.中奖率 = 100;
                    }
                    else {
                        BeanGame.中奖率 = 0;
                    }
                    BeanGame.启动打怪效果 = 1;
                    BeanGame.打中女皇出现特效A = false;
                    BeanGame.打中女皇出现特效B = false;
                }
                else if (Randomizer.nextInt(Integer.parseInt(法师[0])) > Integer.parseInt(法师[1]) && 概率 != 100) {
                    BeanGame.第一排 = 2;
                    BeanGame.第三排 = 4;
                    if (Randomizer.nextInt(Integer.parseInt(法师[2])) > Integer.parseInt(法师[3])) {
                        BeanGame.第二排 = 6;
                        BeanGame.中奖率 = 100;
                    }
                    else {
                        BeanGame.中奖率 = 0;
                    }
                    BeanGame.启动打怪效果 = 1;
                    BeanGame.打中女皇出现特效A = false;
                    BeanGame.打中女皇出现特效B = false;
                }
                else if (Randomizer.nextInt(Integer.parseInt(战士[0])) > Integer.parseInt(战士[1]) && 概率 != 100) {
                    BeanGame.第一排 = 8;
                    BeanGame.第三排 = 8;
                    if (Randomizer.nextInt(Integer.parseInt(战士[2])) > Integer.parseInt(战士[3])) {
                        BeanGame.第二排 = 7;
                        BeanGame.中奖率 = 100;
                    }
                    else {
                        BeanGame.中奖率 = 0;
                    }
                    BeanGame.启动打怪效果 = 1;
                    BeanGame.打中女皇出现特效A = false;
                    BeanGame.打中女皇出现特效B = false;
                }
                else if (Randomizer.nextInt(Integer.parseInt(弓箭手[0])) > Integer.parseInt(弓箭手[1]) && 概率 != 100) {
                    BeanGame.第一排 = 6;
                    BeanGame.第三排 = 3;
                    if (Randomizer.nextInt(Integer.parseInt(弓箭手[2])) > Integer.parseInt(弓箭手[3])) {
                        BeanGame.第二排 = 8;
                        BeanGame.中奖率 = 100;
                    }
                    else {
                        BeanGame.中奖率 = 0;
                    }
                    BeanGame.启动打怪效果 = 1;
                    BeanGame.打中女皇出现特效A = false;
                    BeanGame.打中女皇出现特效B = false;
                }
                else if (Randomizer.nextInt(Integer.parseInt(女皇[0])) > Integer.parseInt(女皇[1]) || 概率 == 100) {
                    BeanGame.第一排 = 3;
                    BeanGame.第三排 = 6;
                    if (BeanGame.黄金狗设置局数 > 0) {
                        BeanGame.第二排 = 9;
                        BeanGame.中奖率 = 100;
                        BeanGame.黄金狗设置局数 = 0;
                    }
                    else if (Randomizer.nextInt(Integer.parseInt(女皇[2])) > Integer.parseInt(女皇[3])) {
                        BeanGame.第二排 = 9;
                        BeanGame.中奖率 = 100;
                    }
                    else {
                        BeanGame.中奖率 = 0;
                    }
                    BeanGame.启动打怪效果 = 1;
                    BeanGame.打中女皇出现特效A = false;
                    BeanGame.打中女皇出现特效B = false;
                }
                else {
                    switch (BeanGame.第一排 = Randomizer.nextInt(10)) {
                        case 0: {
                            BeanGame.第三排 = Randomizer.nextInt(9) + 1;
                            break;
                        }
                        case 1: {
                            BeanGame.第三排 = Randomizer.nextInt(7) + 3;
                            break;
                        }
                        case 2: {
                            BeanGame.第三排 = Randomizer.nextInt(5) + 5;
                            break;
                        }
                        case 3: {
                            BeanGame.第三排 = Randomizer.nextInt(3) + 7;
                            break;
                        }
                        case 4: {
                            BeanGame.第三排 = Randomizer.nextInt(9);
                            break;
                        }
                        case 5: {
                            BeanGame.第三排 = Randomizer.nextInt(8) + 2;
                            break;
                        }
                        case 6: {
                            BeanGame.第三排 = Randomizer.nextInt(6) + 4;
                            break;
                        }
                        case 7: {
                            BeanGame.第三排 = Randomizer.nextInt(4) + 6;
                            break;
                        }
                        case 8: {
                            BeanGame.第三排 = Randomizer.nextInt(8);
                            break;
                        }
                        case 9: {
                            BeanGame.第三排 = Randomizer.nextInt(7);
                            break;
                        }
                    }
                    BeanGame.第二排 = Randomizer.nextInt(10);
                    BeanGame.启动打怪效果 = 0;
                    BeanGame.中奖率 = 0;
                    BeanGame.加速旋转 = 0;
                    BeanGame.打中女皇出现特效A = false;
                    BeanGame.打中女皇出现特效B = false;
                }
                c.getSession().write((Object)MaplePacketCreator.BeansJDXZ(get进洞次数(), BeanGame.第一排, BeanGame.第三排, BeanGame.第二排, BeanGame.启动打怪效果, BeanGame.中奖率, BeanGame.加速旋转, BeanGame.打中女皇出现特效A, BeanGame.打中女皇出现特效B));
                c.sendPacket(MaplePacketCreator.enableActions());
                if (BeanGame.第二排 == 9) {
                    break;
                }
                if (Randomizer.nextInt(Integer.parseInt(黄金狗几率[0])) == Integer.parseInt(黄金狗几率[1]) && BeanGame.黄金狗设置局数 == 0) {
                    BeanGame.黄金狗设置局数 = 1;
                    c.getSession().write((Object)MaplePacketCreator.BeansHJG((byte)1));
                    break;
                }
                BeanGame.黄金狗设置局数 = 0;
                c.getSession().write((Object)MaplePacketCreator.BeansHJG((byte)0));
                break;
            }
            case 5: {
                if ((BeanGame.第一排 == 0 && BeanGame.第三排 == 0 && BeanGame.第二排 == 0) || (BeanGame.第一排 == 9 && BeanGame.第三排 == 7 && BeanGame.第二排 == 1) || (BeanGame.第一排 == 4 && BeanGame.第三排 == 9 && BeanGame.第二排 == 2) || (BeanGame.第一排 == 1 && BeanGame.第三排 == 2 && BeanGame.第二排 == 3) || (BeanGame.第一排 == 7 && BeanGame.第三排 == 5 && BeanGame.第二排 == 4) || (BeanGame.第一排 == 5 && BeanGame.第三排 == 1 && BeanGame.第二排 == 5) || (BeanGame.第一排 == 2 && BeanGame.第三排 == 4 && BeanGame.第二排 == 6) || (BeanGame.第一排 == 8 && BeanGame.第三排 == 8 && BeanGame.第二排 == 7) || (BeanGame.第一排 == 6 && BeanGame.第三排 == 3 && BeanGame.第二排 == 8) || (BeanGame.第一排 == 3 && BeanGame.第三排 == 6 && BeanGame.第二排 == 9)) {
                    final int itemId = 0;
                    final int experi = Randomizer.nextInt(Math.abs(GameConstants.getExpNeededForLevel((int)c.getPlayer().getLevel()) / 800) + 1);
                    final int beilu = (c.getPlayer().getLevel() <= 50) ? 1 : ((c.getPlayer().getLevel() > 50 && c.getPlayer().getLevel() <= 100) ? 2 : ((c.getPlayer().getLevel() > 100 && c.getPlayer().getLevel() <= 150) ? 4 : ((c.getPlayer().getLevel() > 150 && c.getPlayer().getLevel() <= 200) ? 8 : 1)));
                    final int experi2 = (int)Math.ceil((double)(experi / beilu));
                    final int x = Randomizer.nextInt(100) + 1;
                    final int count = 1;
                    switch (BeanGame.第二排) {
                        case 0:
                        case 5: {
                            if (Randomizer.nextInt(Integer.parseInt(白怪奖励[0])) > Randomizer.nextInt(Integer.parseInt(白怪奖励[1]))) {
                                c.getPlayer().gainExp(experi2, false, false, false);
                                c.getPlayer().dropMessage(5, "在小鋼珠中获得：" + experi2 + "經驗值！");
                            }
                            else if (Randomizer.nextInt(Integer.parseInt(白怪奖励[2])) > Randomizer.nextInt(Integer.parseInt(白怪奖励[3]))) {
                                c.getPlayer().gainExp(experi2, false, false, false);
                                c.getPlayer().dropMessage(5, "在小鋼珠中获得：" + experi2 + "經驗值！");
                            }
                            c.sendPacket(MaplePacketCreator.enableActions());
                            break;
                        }
                        case 1:
                        case 3: {
                            if (Randomizer.nextInt(Integer.parseInt(色怪奖励[0])) > Randomizer.nextInt(Integer.parseInt(色怪奖励[1]))) {
                                c.getPlayer().gainExp(experi2, false, false, false);
                                c.getPlayer().dropMessage(5, "在小鋼珠中获得：" + experi2 + "經驗值！");
                            }
                            else if (Randomizer.nextInt(Integer.parseInt(色怪奖励[4])) > Randomizer.nextInt(Integer.parseInt(色怪奖励[5]))) {
                                c.getPlayer().gainExp(experi2, false, false, false);
                                c.getPlayer().dropMessage(5, "在小鋼珠中获得：" + experi2 + "經驗值！");
                            }
                            else if (Randomizer.nextInt(Integer.parseInt(色怪奖励[2])) > Randomizer.nextInt(Integer.parseInt(色怪奖励[3]))) {
                                c.getPlayer().gainExp(experi2, false, false, false);
                                c.getPlayer().dropMessage(5, "在小鋼珠中获得：" + experi2 + "經驗值！");
                            }
                            c.sendPacket(MaplePacketCreator.enableActions());
                            break;
                        }
                        case 2:
                        case 4:
                        case 6:
                        case 7:
                        case 8: {
                            if (Randomizer.nextInt(Integer.parseInt(五职业奖励[0])) > Randomizer.nextInt(Integer.parseInt(五职业奖励[1]))) {
                                c.getPlayer().gainExp(experi2, false, false, false);
                                c.getPlayer().dropMessage(5, "在小鋼珠中获得：" + experi2 + "經驗值！");
                            }
                            else if (Randomizer.nextInt(Integer.parseInt(五职业奖励[4])) > Randomizer.nextInt(Integer.parseInt(五职业奖励[5]))) {
                                c.getPlayer().gainExp(experi2, false, false, false);
                                c.getPlayer().dropMessage(5, "在小鋼珠中获得：" + experi2 + "經驗值！");
                            }
                            else if (Randomizer.nextInt(Integer.parseInt(五职业奖励[6])) > Randomizer.nextInt(Integer.parseInt(五职业奖励[7]))) {
                                c.getPlayer().gainExp(experi2, false, false, false);
                                c.getPlayer().dropMessage(5, "在小鋼珠中获得：" + experi2 + "經驗值！");
                            }
                            else if (Randomizer.nextInt(Integer.parseInt(五职业奖励[2])) > Randomizer.nextInt(Integer.parseInt(五职业奖励[3]))) {
                                c.getPlayer().gainExp(experi2, false, false, false);
                                c.getPlayer().dropMessage(5, "在小鋼珠中获得：" + experi2 + "經驗值！");
                            }
                            c.sendPacket(MaplePacketCreator.enableActions());
                            break;
                        }
                        case 9: {
                            if (Randomizer.nextInt(Integer.parseInt(女皇奖励[4])) > Randomizer.nextInt(Integer.parseInt(女皇奖励[5]))) {
                                c.getPlayer().gainExp(experi2, false, false, false);
                                c.getPlayer().dropMessage(5, "在小鋼珠中获得：" + experi2 + "經驗值！");
                            }
                            else if (x == 海洋帽子几率) {
                                c.getPlayer().gainExp(experi2, false, false, false);
                                c.getPlayer().dropMessage(5, "在小鋼珠中获得：" + experi2 + "經驗值！");
                            }
                            else if (Randomizer.nextInt(Integer.parseInt(女皇奖励[6])) > Randomizer.nextInt(Integer.parseInt(女皇奖励[7]))) {
                                c.getPlayer().gainExp(experi2, false, false, false);
                                c.getPlayer().dropMessage(5, "在小鋼珠中获得：" + experi2 + "經驗值！");
                            }
                            else if (Randomizer.nextInt(Integer.parseInt(女皇奖励[2])) > Randomizer.nextInt(Integer.parseInt(女皇奖励[3]))) {
                                c.getPlayer().gainExp(experi2, false, false, false);
                                c.getPlayer().dropMessage(5, "在小鋼珠中获得：" + experi2 + "經驗值！");
                            }
                            c.sendPacket(MaplePacketCreator.enableActions());
                            break;
                        }
                        default: {
                            System.out.println("未处理的类型A【" + type + "】\n包" + slea.toString());
                            break;
                        }
                    }
                    final int 奖励豆豆 = 1 * 豆豆奖励范围;
                    chr.modifyJF(4, 奖励豆豆);
                    c.getPlayer().dropMessage(5, "在小鋼珠中获得: " + 奖励豆豆 + "小鋼珠積分！");
                    if (chr.getMapId() == 809030000) {
                        final String notea = "恭喜你小鋼珠成功中獎！當前中获得：" + 奖励豆豆 + "小鋼珠積分！";
                        c.getSession().write((Object)MaplePacketCreator.BeansGameMessage(1, 1, notea));
                    }
                    if (BeanGame.黄金狗设置局数 > 0 && BeanGame.第二排 == 9) {
                        BeanGame.黄金狗设置局数 = 0;
                        c.getSession().write((Object)MaplePacketCreator.BeansHJG((byte)0));
                    }
                    break;
                }
                break;
            }
            case 7: {
                if (BeanGame.黄金狗设置局数 > 0) {
                    BeanGame.黄金狗设置局数 = 0;
                    c.getSession().write((Object)MaplePacketCreator.BeansHJG((byte)0));
                    break;
                }
                break;
            }
            case 11: {
                slea.readShort();
                力度 = Randomizer.rand(1000, 5000);
                豆豆序号 = slea.readInt() + 1;
                chr.setBeansRange(力度);
                chr.setBeansNum(豆豆序号);
                if (豆豆序号 == 1) {
                    chr.setCanSetBeansNum(false);
                    break;
                }
                break;
            }
            case 6: {
                slea.skip(1);
                final int 循环次数 = slea.readByte();
                if (循环次数 == 0) {
                    c.sendPacket(MaplePacketCreator.enableActions());
                    return;
                }
                if (循环次数 != 1) {
                    slea.skip((循环次数 - 1) * 8);
                }
                if (chr.isCanSetBeansNum()) {
                    chr.setBeansNum(chr.getBeansNum() + 循环次数);
                }
                chr.gainBeans(-循环次数);
                chr.setCanSetBeansNum(true);
                break;
            }
            default: {
                System.out.println("未處理未知類型【" + type + "】\n包" + slea.toString());
                FileoutputUtil.logToFile("logs/小钢珠未知類型.txt", "时间: " + FileoutputUtil.NowTime() + " IP: " + chr.getClient().getSessionIPAddress() + " MAC: " + chr.getNowMacs() + chr.getName() + "类型【" + type + "】\n包" + slea.toString() + "\r\n");
                break;
            }
        }
        if (type == 11 || type == 6) {
            for (int i = 0; i < 5; ++i) {
                beansInfo.add(new MapleBeans(rand(1000, 5000), getBeanType(), chr.getBeansNum() + i));
            }
            c.getSession().write((Object)MaplePacketCreator.showBeans(beansInfo));
            c.sendPacket(MaplePacketCreator.enableActions());
        }
        c.getSession().write((Object)MaplePacketCreator.updateBeans(c.getPlayer()));
        c.sendPacket(MaplePacketCreator.enableActions());
    }
    
    private static int getBeanType() {
        final int random = rand(1, 100);
        final int beanType = 0;
        return beanType;
    }
    
    public static final int get进洞次数() {
        return BeanGame.进洞次数;
    }
    
    public static final void gain进洞次数(final int a) {
        BeanGame.进洞次数 += a;
    }
    
    public static final void set进洞次数(final int a) {
        BeanGame.进洞次数 = a;
    }
    
    private static int rand(final int lbound, final int ubound) {
        return (int)(Math.random() * (double)(ubound - lbound + 1) + (double)lbound);
    }
    
    public static final void updateBeans(final LittleEndianAccessor slea, final MapleClient c) {
        c.getSession().write((Object)MaplePacketCreator.updateBeans(c.getPlayer()));
        c.getSession().write((Object)MaplePacketCreator.enableActions());
    }
    
    static {
        BeanGame.进洞次数 = 0;
        BeanGame.第一排 = 0;
        BeanGame.第三排 = 0;
        BeanGame.第二排 = 0;
        BeanGame.启动打怪效果 = 0;
        BeanGame.中奖率 = 0;
        BeanGame.加速旋转 = 0;
        BeanGame.打中女皇出现特效A = false;
        BeanGame.打中女皇出现特效B = false;
        BeanGame.蓝 = 0;
        BeanGame.绿 = 0;
        BeanGame.红 = 0;
        BeanGame.黄金狗设置局数 = 0;
    }
    
    public enum BeansType
    {
        开始打豆豆(0), 
        颜色求进洞(3), 
        进洞旋转(4), 
        奖励豆豆效果(5), 
        未知效果(6), 
        黄金狗(7), 
        奖励豆豆效果B(8), 
        领奖NPC(9);
        
        final byte type;
        
        private BeansType(final int type) {
            this.type = (byte)type;
        }
        
        public byte getType() {
            return this.type;
        }
    }
}
