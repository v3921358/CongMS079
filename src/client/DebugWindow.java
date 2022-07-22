package client;

import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.LookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import javax.swing.UIManager;
import javax.swing.JDialog;
import server.quest.MapleQuest;
import java.awt.Point;
import java.util.Iterator;
import java.util.List;
import server.maps.MapleMap;
import server.life.MapleMonster;
import server.maps.MapleMapObject;
import java.util.Arrays;
import server.maps.MapleMapObjectType;
import tools.MaplePacketCreator;
import constants.GameConstants;
import tools.data.MaplePacketLittleEndianWriter;
import tools.HexTool;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import java.awt.Color;
import java.awt.LayoutManager;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

public class DebugWindow extends JFrame
{
    private MapleClient c;
    private JButton AP;
    private JButton SP;
    private JButton jButton1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JPanel jPanel10;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JPanel jPanel8;
    private JPanel jPanel9;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTabbedPane jTabbedPane1;
    private JTextArea jTextArea1;
    private JButton 主教;
    private JTextField 任务代码;
    private JButton 任务完成;
    private JButton 任务开始;
    private JButton 侠客;
    private JButton 侠盗;
    private JButton 冰雷巫师;
    private JButton 冰雷法师;
    private JButton 冰雷魔导师;
    private JButton 冲锋队长;
    private JButton 准骑士;
    private JButton 刷新;
    private JButton 刷新11;
    private JButton 刷物品;
    private JButton 刺客;
    private JButton 剑客;
    private JButton 勇士;
    private JButton 升级;
    private JButton 圣骑士;
    private JButton 坐标;
    private JButton 夜行者1;
    private JButton 夜行者2;
    private JButton 夜行者3;
    private JButton 大副;
    private JButton 奇袭者1;
    private JButton 奇袭者2;
    private JButton 奇袭者3;
    private JTextArea 封包;
    private JButton 射手;
    private JButton 弓箭手;
    private JButton 弩弓手;
    private JButton 战士;
    private JButton 战神1;
    private JButton 战神2;
    private JButton 战神3;
    private JButton 战神4;
    private JButton 抵用券;
    private JButton 拳手;
    private JButton 斗士;
    private JButton 无影人;
    private JButton 无敌;
    private JButton 枪战士;
    private JButton 海盗;
    private JButton 清怪;
    private JButton 清物;
    private JButton 游侠;
    private JButton 满属性;
    private JButton 满技能;
    private JButton 火枪手;
    private JButton 火毒巫师;
    private JButton 火毒法师;
    private JButton 火毒法师1;
    private JButton 火毒魔导师;
    private JButton 炎术士1;
    private JButton 炎术士2;
    private JButton 炎术士3;
    private JButton 点券;
    private JButton 牧师;
    private JTextField 物品代码;
    private JTextField 物品数量;
    private JButton 独行客;
    private JButton 神射手;
    private JButton 祭司;
    private JButton 箭神;
    private JButton 船长;
    private JButton 英雄;
    private JButton 金币;
    private JButton 隐士;
    private JButton 风灵使者1;
    private JButton 风灵使者2;
    private JButton 风灵使者3;
    private JButton 飞侠;
    private JButton 骑士;
    private JButton 魂骑士1;
    private JButton 魂骑士2;
    private JButton 魂骑士3;
    private JButton 魔法师;
    private JButton 黑骑士;
    private JButton 龙骑士;
    
    public DebugWindow() {
        this.initComponents();
    }
    
    public MapleClient getC() {
        return this.c;
    }
    
    public void setC(final MapleClient c) {
        this.c = c;
        if (c.getPlayer() != null) {
            this.setTitle("玩家操作面板: " + c.getPlayer().getName() + " ");
        }
        final ImageIcon icon = new ImageIcon(this.getClass().getClassLoader().getResource("gui/图片/pp/2.png"));
        this.setIconImage(icon.getImage());
    }
    
    private void initComponents() {
        this.jScrollPane1 = new JScrollPane();
        this.jTextArea1 = new JTextArea();
        this.jPanel1 = new JPanel();
        this.jLabel1 = new JLabel();
        this.jScrollPane2 = new JScrollPane();
        this.封包 = new JTextArea();
        this.jButton1 = new JButton();
        this.升级 = new JButton();
        this.无敌 = new JButton();
        this.SP = new JButton();
        this.AP = new JButton();
        this.刷新 = new JButton();
        this.清怪 = new JButton();
        this.清物 = new JButton();
        this.坐标 = new JButton();
        this.满技能 = new JButton();
        this.满属性 = new JButton();
        this.jPanel2 = new JPanel();
        this.jTabbedPane1 = new JTabbedPane();
        this.jPanel3 = new JPanel();
        this.战士 = new JButton();
        this.剑客 = new JButton();
        this.勇士 = new JButton();
        this.英雄 = new JButton();
        this.准骑士 = new JButton();
        this.骑士 = new JButton();
        this.圣骑士 = new JButton();
        this.枪战士 = new JButton();
        this.龙骑士 = new JButton();
        this.黑骑士 = new JButton();
        this.jPanel4 = new JPanel();
        this.魔法师 = new JButton();
        this.火毒法师 = new JButton();
        this.火毒巫师 = new JButton();
        this.火毒魔导师 = new JButton();
        this.冰雷法师 = new JButton();
        this.冰雷巫师 = new JButton();
        this.冰雷魔导师 = new JButton();
        this.牧师 = new JButton();
        this.祭司 = new JButton();
        this.主教 = new JButton();
        this.jPanel5 = new JPanel();
        this.弓箭手 = new JButton();
        this.火毒法师1 = new JButton();
        this.射手 = new JButton();
        this.神射手 = new JButton();
        this.弩弓手 = new JButton();
        this.游侠 = new JButton();
        this.箭神 = new JButton();
        this.jPanel6 = new JPanel();
        this.飞侠 = new JButton();
        this.刺客 = new JButton();
        this.无影人 = new JButton();
        this.隐士 = new JButton();
        this.侠盗 = new JButton();
        this.独行客 = new JButton();
        this.侠客 = new JButton();
        this.jPanel7 = new JPanel();
        this.海盗 = new JButton();
        this.拳手 = new JButton();
        this.斗士 = new JButton();
        this.冲锋队长 = new JButton();
        this.火枪手 = new JButton();
        this.大副 = new JButton();
        this.船长 = new JButton();
        this.jPanel8 = new JPanel();
        this.魂骑士1 = new JButton();
        this.魂骑士2 = new JButton();
        this.魂骑士3 = new JButton();
        this.炎术士1 = new JButton();
        this.炎术士2 = new JButton();
        this.炎术士3 = new JButton();
        this.风灵使者1 = new JButton();
        this.风灵使者3 = new JButton();
        this.风灵使者2 = new JButton();
        this.jPanel9 = new JPanel();
        this.夜行者1 = new JButton();
        this.夜行者2 = new JButton();
        this.夜行者3 = new JButton();
        this.奇袭者3 = new JButton();
        this.奇袭者2 = new JButton();
        this.奇袭者1 = new JButton();
        this.jPanel10 = new JPanel();
        this.战神1 = new JButton();
        this.战神2 = new JButton();
        this.战神3 = new JButton();
        this.战神4 = new JButton();
        this.任务开始 = new JButton();
        this.金币 = new JButton();
        this.点券 = new JButton();
        this.抵用券 = new JButton();
        this.刷物品 = new JButton();
        this.任务代码 = new JTextField();
        this.任务完成 = new JButton();
        this.刷新11 = new JButton();
        this.物品数量 = new JTextField();
        this.物品代码 = new JTextField();
        this.jLabel2 = new JLabel();
        this.jTextArea1.setColumns(20);
        this.jTextArea1.setRows(5);
        this.jScrollPane1.setViewportView((Component)this.jTextArea1);
        this.setResizable(false);
        this.getContentPane().setLayout((LayoutManager)new AbsoluteLayout());
        this.jPanel1.setBackground(new Color(255, 255, 255));
        this.jPanel1.setLayout((LayoutManager)new AbsoluteLayout());
        this.jPanel1.add((Component)this.jLabel1, (Object)new AbsoluteConstraints(0, 163, -1, -1));
        this.封包.setColumns(20);
        this.封包.setRows(5);
        this.jScrollPane2.setViewportView((Component)this.封包);
        this.jPanel1.add((Component)this.jScrollPane2, (Object)new AbsoluteConstraints(10, 480, 420, 90));
        this.jButton1.setText("发送封包");
        this.jButton1.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.jButton1ActionPerformed(evt);
            }
        });
        this.jPanel1.add((Component)this.jButton1, (Object)new AbsoluteConstraints(10, 570, 420, 30));
        this.升级.setFont(new Font("幼圆", 0, 15));
        this.升级.setText("升级");
        this.升级.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.升级ActionPerformed(evt);
            }
        });
        this.jPanel1.add((Component)this.升级, (Object)new AbsoluteConstraints(150, 100, 140, -1));
        this.无敌.setFont(new Font("幼圆", 0, 15));
        this.无敌.setText("无敌");
        this.无敌.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.无敌ActionPerformed(evt);
            }
        });
        this.jPanel1.add((Component)this.无敌, (Object)new AbsoluteConstraints(150, 70, 140, -1));
        this.SP.setFont(new Font("幼圆", 0, 15));
        this.SP.setText("SP+10");
        this.SP.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.SPActionPerformed(evt);
            }
        });
        this.jPanel1.add((Component)this.SP, (Object)new AbsoluteConstraints(150, 40, 140, -1));
        this.AP.setFont(new Font("幼圆", 0, 15));
        this.AP.setText("AP+10");
        this.AP.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.APActionPerformed(evt);
            }
        });
        this.jPanel1.add((Component)this.AP, (Object)new AbsoluteConstraints(150, 10, 140, -1));
        this.刷新.setFont(new Font("幼圆", 0, 15));
        this.刷新.setText("刷新");
        this.刷新.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.刷新ActionPerformed(evt);
            }
        });
        this.jPanel1.add((Component)this.刷新, (Object)new AbsoluteConstraints(290, 10, 140, -1));
        this.清怪.setFont(new Font("幼圆", 0, 15));
        this.清怪.setText("清怪");
        this.清怪.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.清怪ActionPerformed(evt);
            }
        });
        this.jPanel1.add((Component)this.清怪, (Object)new AbsoluteConstraints(10, 100, 140, -1));
        this.清物.setFont(new Font("幼圆", 0, 15));
        this.清物.setText("清物");
        this.清物.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.清物ActionPerformed(evt);
            }
        });
        this.jPanel1.add((Component)this.清物, (Object)new AbsoluteConstraints(10, 70, 140, -1));
        this.坐标.setFont(new Font("幼圆", 0, 15));
        this.坐标.setText("坐标");
        this.坐标.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.坐标ActionPerformed(evt);
            }
        });
        this.jPanel1.add((Component)this.坐标, (Object)new AbsoluteConstraints(290, 40, 140, -1));
        this.满技能.setFont(new Font("幼圆", 0, 15));
        this.满技能.setText("满技能");
        this.满技能.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.满技能ActionPerformed(evt);
            }
        });
        this.jPanel1.add((Component)this.满技能, (Object)new AbsoluteConstraints(10, 40, 140, -1));
        this.满属性.setFont(new Font("幼圆", 0, 15));
        this.满属性.setText("满属性");
        this.满属性.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.满属性ActionPerformed(evt);
            }
        });
        this.jPanel1.add((Component)this.满属性, (Object)new AbsoluteConstraints(10, 10, 140, -1));
        this.jPanel2.setLayout((LayoutManager)new AbsoluteLayout());
        this.jPanel3.setBackground(new Color(255, 255, 255));
        this.jPanel3.setLayout((LayoutManager)new AbsoluteLayout());
        this.战士.setFont(new Font("幼圆", 0, 15));
        this.战士.setText("战士");
        this.战士.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.战士ActionPerformed(evt);
            }
        });
        this.jPanel3.add((Component)this.战士, (Object)new AbsoluteConstraints(20, 10, 120, -1));
        this.剑客.setFont(new Font("幼圆", 0, 15));
        this.剑客.setText("剑客");
        this.剑客.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.剑客ActionPerformed(evt);
            }
        });
        this.jPanel3.add((Component)this.剑客, (Object)new AbsoluteConstraints(20, 40, 120, -1));
        this.勇士.setFont(new Font("幼圆", 0, 15));
        this.勇士.setText("勇士");
        this.勇士.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.勇士ActionPerformed(evt);
            }
        });
        this.jPanel3.add((Component)this.勇士, (Object)new AbsoluteConstraints(20, 70, 120, -1));
        this.英雄.setFont(new Font("幼圆", 0, 15));
        this.英雄.setText("英雄");
        this.英雄.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.英雄ActionPerformed(evt);
            }
        });
        this.jPanel3.add((Component)this.英雄, (Object)new AbsoluteConstraints(20, 100, 120, -1));
        this.准骑士.setFont(new Font("幼圆", 0, 15));
        this.准骑士.setText("准骑士");
        this.准骑士.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.准骑士ActionPerformed(evt);
            }
        });
        this.jPanel3.add((Component)this.准骑士, (Object)new AbsoluteConstraints(150, 40, 120, -1));
        this.骑士.setFont(new Font("幼圆", 0, 15));
        this.骑士.setText("骑士");
        this.骑士.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.骑士ActionPerformed(evt);
            }
        });
        this.jPanel3.add((Component)this.骑士, (Object)new AbsoluteConstraints(150, 70, 120, -1));
        this.圣骑士.setFont(new Font("幼圆", 0, 15));
        this.圣骑士.setText("圣骑士");
        this.圣骑士.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.圣骑士ActionPerformed(evt);
            }
        });
        this.jPanel3.add((Component)this.圣骑士, (Object)new AbsoluteConstraints(150, 100, 120, -1));
        this.枪战士.setFont(new Font("幼圆", 0, 15));
        this.枪战士.setText("枪战士");
        this.枪战士.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.枪战士ActionPerformed(evt);
            }
        });
        this.jPanel3.add((Component)this.枪战士, (Object)new AbsoluteConstraints(280, 40, 120, -1));
        this.龙骑士.setFont(new Font("幼圆", 0, 15));
        this.龙骑士.setText("龙骑士");
        this.龙骑士.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.龙骑士ActionPerformed(evt);
            }
        });
        this.jPanel3.add((Component)this.龙骑士, (Object)new AbsoluteConstraints(280, 70, 120, -1));
        this.黑骑士.setFont(new Font("幼圆", 0, 15));
        this.黑骑士.setText("黑骑士");
        this.黑骑士.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.黑骑士ActionPerformed(evt);
            }
        });
        this.jPanel3.add((Component)this.黑骑士, (Object)new AbsoluteConstraints(280, 100, 120, -1));
        this.jTabbedPane1.addTab("战士", (Component)this.jPanel3);
        this.jPanel4.setBackground(new Color(255, 255, 255));
        this.jPanel4.setLayout((LayoutManager)new AbsoluteLayout());
        this.魔法师.setFont(new Font("幼圆", 0, 15));
        this.魔法师.setText("魔法师");
        this.魔法师.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.魔法师ActionPerformed(evt);
            }
        });
        this.jPanel4.add((Component)this.魔法师, (Object)new AbsoluteConstraints(20, 10, 120, -1));
        this.火毒法师.setFont(new Font("幼圆", 0, 15));
        this.火毒法师.setText("火毒法师");
        this.火毒法师.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.火毒法师ActionPerformed(evt);
            }
        });
        this.jPanel4.add((Component)this.火毒法师, (Object)new AbsoluteConstraints(20, 40, 120, -1));
        this.火毒巫师.setFont(new Font("幼圆", 0, 15));
        this.火毒巫师.setText("火毒巫师");
        this.火毒巫师.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.火毒巫师ActionPerformed(evt);
            }
        });
        this.jPanel4.add((Component)this.火毒巫师, (Object)new AbsoluteConstraints(20, 70, 120, -1));
        this.火毒魔导师.setFont(new Font("幼圆", 0, 15));
        this.火毒魔导师.setText("火毒魔导师");
        this.火毒魔导师.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.火毒魔导师ActionPerformed(evt);
            }
        });
        this.jPanel4.add((Component)this.火毒魔导师, (Object)new AbsoluteConstraints(20, 100, 120, -1));
        this.冰雷法师.setFont(new Font("幼圆", 0, 15));
        this.冰雷法师.setText("冰雷法师");
        this.冰雷法师.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.冰雷法师ActionPerformed(evt);
            }
        });
        this.jPanel4.add((Component)this.冰雷法师, (Object)new AbsoluteConstraints(150, 40, 120, -1));
        this.冰雷巫师.setFont(new Font("幼圆", 0, 15));
        this.冰雷巫师.setText("冰雷巫师");
        this.冰雷巫师.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.冰雷巫师ActionPerformed(evt);
            }
        });
        this.jPanel4.add((Component)this.冰雷巫师, (Object)new AbsoluteConstraints(150, 70, 120, -1));
        this.冰雷魔导师.setFont(new Font("幼圆", 0, 15));
        this.冰雷魔导师.setText("冰雷魔导师");
        this.冰雷魔导师.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.冰雷魔导师ActionPerformed(evt);
            }
        });
        this.jPanel4.add((Component)this.冰雷魔导师, (Object)new AbsoluteConstraints(150, 100, 120, -1));
        this.牧师.setFont(new Font("幼圆", 0, 15));
        this.牧师.setText("牧师");
        this.牧师.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.牧师ActionPerformed(evt);
            }
        });
        this.jPanel4.add((Component)this.牧师, (Object)new AbsoluteConstraints(280, 40, 120, -1));
        this.祭司.setFont(new Font("幼圆", 0, 15));
        this.祭司.setText("祭司");
        this.祭司.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.祭司ActionPerformed(evt);
            }
        });
        this.jPanel4.add((Component)this.祭司, (Object)new AbsoluteConstraints(280, 70, 120, -1));
        this.主教.setFont(new Font("幼圆", 0, 15));
        this.主教.setText("主教");
        this.主教.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.主教ActionPerformed(evt);
            }
        });
        this.jPanel4.add((Component)this.主教, (Object)new AbsoluteConstraints(280, 100, 120, -1));
        this.jTabbedPane1.addTab("魔法师", (Component)this.jPanel4);
        this.jPanel5.setBackground(new Color(255, 255, 255));
        this.jPanel5.setLayout((LayoutManager)new AbsoluteLayout());
        this.弓箭手.setFont(new Font("幼圆", 0, 15));
        this.弓箭手.setText("弓箭手");
        this.弓箭手.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.弓箭手ActionPerformed(evt);
            }
        });
        this.jPanel5.add((Component)this.弓箭手, (Object)new AbsoluteConstraints(20, 10, 120, -1));
        this.火毒法师1.setFont(new Font("幼圆", 0, 15));
        this.火毒法师1.setText("猎人");
        this.火毒法师1.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.火毒法师1ActionPerformed(evt);
            }
        });
        this.jPanel5.add((Component)this.火毒法师1, (Object)new AbsoluteConstraints(20, 40, 120, -1));
        this.射手.setFont(new Font("幼圆", 0, 15));
        this.射手.setText("射手");
        this.射手.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.射手ActionPerformed(evt);
            }
        });
        this.jPanel5.add((Component)this.射手, (Object)new AbsoluteConstraints(20, 70, 120, -1));
        this.神射手.setFont(new Font("幼圆", 0, 15));
        this.神射手.setText("神射手");
        this.神射手.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.神射手ActionPerformed(evt);
            }
        });
        this.jPanel5.add((Component)this.神射手, (Object)new AbsoluteConstraints(20, 100, 120, -1));
        this.弩弓手.setFont(new Font("幼圆", 0, 15));
        this.弩弓手.setText("弩弓手");
        this.弩弓手.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.弩弓手ActionPerformed(evt);
            }
        });
        this.jPanel5.add((Component)this.弩弓手, (Object)new AbsoluteConstraints(150, 40, 120, -1));
        this.游侠.setFont(new Font("幼圆", 0, 15));
        this.游侠.setText("游侠");
        this.游侠.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.游侠ActionPerformed(evt);
            }
        });
        this.jPanel5.add((Component)this.游侠, (Object)new AbsoluteConstraints(150, 70, 120, -1));
        this.箭神.setFont(new Font("幼圆", 0, 15));
        this.箭神.setText("箭神");
        this.箭神.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.箭神ActionPerformed(evt);
            }
        });
        this.jPanel5.add((Component)this.箭神, (Object)new AbsoluteConstraints(150, 100, 120, -1));
        this.jTabbedPane1.addTab("弓箭手", (Component)this.jPanel5);
        this.jPanel6.setBackground(new Color(255, 255, 255));
        this.jPanel6.setLayout((LayoutManager)new AbsoluteLayout());
        this.飞侠.setFont(new Font("幼圆", 0, 15));
        this.飞侠.setText("飞侠");
        this.飞侠.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.飞侠ActionPerformed(evt);
            }
        });
        this.jPanel6.add((Component)this.飞侠, (Object)new AbsoluteConstraints(20, 10, 120, -1));
        this.刺客.setFont(new Font("幼圆", 0, 15));
        this.刺客.setText("刺客");
        this.刺客.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.刺客ActionPerformed(evt);
            }
        });
        this.jPanel6.add((Component)this.刺客, (Object)new AbsoluteConstraints(20, 40, 120, -1));
        this.无影人.setFont(new Font("幼圆", 0, 15));
        this.无影人.setText("无影人");
        this.无影人.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.无影人ActionPerformed(evt);
            }
        });
        this.jPanel6.add((Component)this.无影人, (Object)new AbsoluteConstraints(20, 70, 120, -1));
        this.隐士.setFont(new Font("幼圆", 0, 15));
        this.隐士.setText("隐士");
        this.隐士.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.隐士ActionPerformed(evt);
            }
        });
        this.jPanel6.add((Component)this.隐士, (Object)new AbsoluteConstraints(20, 100, 120, -1));
        this.侠盗.setFont(new Font("幼圆", 0, 15));
        this.侠盗.setText("侠盗");
        this.侠盗.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.侠盗ActionPerformed(evt);
            }
        });
        this.jPanel6.add((Component)this.侠盗, (Object)new AbsoluteConstraints(150, 100, 120, -1));
        this.独行客.setFont(new Font("幼圆", 0, 15));
        this.独行客.setText("独行客");
        this.独行客.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.独行客ActionPerformed(evt);
            }
        });
        this.jPanel6.add((Component)this.独行客, (Object)new AbsoluteConstraints(150, 70, 120, -1));
        this.侠客.setFont(new Font("幼圆", 0, 15));
        this.侠客.setText("侠客");
        this.侠客.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.侠客ActionPerformed(evt);
            }
        });
        this.jPanel6.add((Component)this.侠客, (Object)new AbsoluteConstraints(150, 40, 120, -1));
        this.jTabbedPane1.addTab("飞侠", (Component)this.jPanel6);
        this.jPanel7.setBackground(new Color(255, 255, 255));
        this.jPanel7.setLayout((LayoutManager)new AbsoluteLayout());
        this.海盗.setFont(new Font("幼圆", 0, 15));
        this.海盗.setText("海盗");
        this.海盗.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.海盗ActionPerformed(evt);
            }
        });
        this.jPanel7.add((Component)this.海盗, (Object)new AbsoluteConstraints(20, 10, 120, -1));
        this.拳手.setFont(new Font("幼圆", 0, 15));
        this.拳手.setText("拳手");
        this.拳手.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.拳手ActionPerformed(evt);
            }
        });
        this.jPanel7.add((Component)this.拳手, (Object)new AbsoluteConstraints(20, 40, 120, -1));
        this.斗士.setFont(new Font("幼圆", 0, 15));
        this.斗士.setText("斗士");
        this.斗士.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.斗士ActionPerformed(evt);
            }
        });
        this.jPanel7.add((Component)this.斗士, (Object)new AbsoluteConstraints(20, 70, 120, -1));
        this.冲锋队长.setFont(new Font("幼圆", 0, 15));
        this.冲锋队长.setText("冲锋队长");
        this.冲锋队长.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.冲锋队长ActionPerformed(evt);
            }
        });
        this.jPanel7.add((Component)this.冲锋队长, (Object)new AbsoluteConstraints(20, 100, 120, -1));
        this.火枪手.setFont(new Font("幼圆", 0, 15));
        this.火枪手.setText("火枪手");
        this.火枪手.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.火枪手ActionPerformed(evt);
            }
        });
        this.jPanel7.add((Component)this.火枪手, (Object)new AbsoluteConstraints(150, 40, 120, -1));
        this.大副.setFont(new Font("幼圆", 0, 15));
        this.大副.setText("大副");
        this.大副.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.大副ActionPerformed(evt);
            }
        });
        this.jPanel7.add((Component)this.大副, (Object)new AbsoluteConstraints(150, 70, 120, -1));
        this.船长.setFont(new Font("幼圆", 0, 15));
        this.船长.setText("船长");
        this.船长.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.船长ActionPerformed(evt);
            }
        });
        this.jPanel7.add((Component)this.船长, (Object)new AbsoluteConstraints(150, 100, 120, -1));
        this.jTabbedPane1.addTab("海盗", (Component)this.jPanel7);
        this.jPanel8.setBackground(new Color(255, 255, 255));
        this.jPanel8.setLayout((LayoutManager)new AbsoluteLayout());
        this.魂骑士1.setFont(new Font("幼圆", 0, 15));
        this.魂骑士1.setText("魂骑士1");
        this.魂骑士1.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.魂骑士1ActionPerformed(evt);
            }
        });
        this.jPanel8.add((Component)this.魂骑士1, (Object)new AbsoluteConstraints(20, 10, 120, -1));
        this.魂骑士2.setFont(new Font("幼圆", 0, 15));
        this.魂骑士2.setText("魂骑士2");
        this.魂骑士2.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.魂骑士2ActionPerformed(evt);
            }
        });
        this.jPanel8.add((Component)this.魂骑士2, (Object)new AbsoluteConstraints(20, 40, 120, -1));
        this.魂骑士3.setFont(new Font("幼圆", 0, 15));
        this.魂骑士3.setText("魂骑士3");
        this.魂骑士3.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.魂骑士3ActionPerformed(evt);
            }
        });
        this.jPanel8.add((Component)this.魂骑士3, (Object)new AbsoluteConstraints(20, 70, 120, -1));
        this.炎术士1.setFont(new Font("幼圆", 0, 15));
        this.炎术士1.setText("炎术士1");
        this.炎术士1.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.炎术士1ActionPerformed(evt);
            }
        });
        this.jPanel8.add((Component)this.炎术士1, (Object)new AbsoluteConstraints(150, 10, 120, -1));
        this.炎术士2.setFont(new Font("幼圆", 0, 15));
        this.炎术士2.setText("炎术士2");
        this.炎术士2.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.炎术士2ActionPerformed(evt);
            }
        });
        this.jPanel8.add((Component)this.炎术士2, (Object)new AbsoluteConstraints(150, 40, 120, -1));
        this.炎术士3.setFont(new Font("幼圆", 0, 15));
        this.炎术士3.setText("炎术士3");
        this.炎术士3.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.炎术士3ActionPerformed(evt);
            }
        });
        this.jPanel8.add((Component)this.炎术士3, (Object)new AbsoluteConstraints(150, 70, 120, -1));
        this.风灵使者1.setFont(new Font("幼圆", 0, 15));
        this.风灵使者1.setText("风灵使者1");
        this.风灵使者1.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.风灵使者1ActionPerformed(evt);
            }
        });
        this.jPanel8.add((Component)this.风灵使者1, (Object)new AbsoluteConstraints(280, 10, 120, -1));
        this.风灵使者3.setFont(new Font("幼圆", 0, 15));
        this.风灵使者3.setText("风灵使者3");
        this.风灵使者3.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.风灵使者3ActionPerformed(evt);
            }
        });
        this.jPanel8.add((Component)this.风灵使者3, (Object)new AbsoluteConstraints(280, 70, 120, -1));
        this.风灵使者2.setFont(new Font("幼圆", 0, 15));
        this.风灵使者2.setText("风灵使者2");
        this.风灵使者2.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.风灵使者2ActionPerformed(evt);
            }
        });
        this.jPanel8.add((Component)this.风灵使者2, (Object)new AbsoluteConstraints(280, 40, 120, -1));
        this.jTabbedPane1.addTab("骑士团", (Component)this.jPanel8);
        this.jPanel9.setBackground(new Color(255, 255, 255));
        this.jPanel9.setLayout((LayoutManager)new AbsoluteLayout());
        this.夜行者1.setFont(new Font("幼圆", 0, 15));
        this.夜行者1.setText("夜行者1");
        this.夜行者1.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.夜行者1ActionPerformed(evt);
            }
        });
        this.jPanel9.add((Component)this.夜行者1, (Object)new AbsoluteConstraints(20, 10, 120, -1));
        this.夜行者2.setFont(new Font("幼圆", 0, 15));
        this.夜行者2.setText("夜行者2");
        this.夜行者2.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.夜行者2ActionPerformed(evt);
            }
        });
        this.jPanel9.add((Component)this.夜行者2, (Object)new AbsoluteConstraints(20, 40, 120, -1));
        this.夜行者3.setFont(new Font("幼圆", 0, 15));
        this.夜行者3.setText("夜行者3");
        this.夜行者3.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.夜行者3ActionPerformed(evt);
            }
        });
        this.jPanel9.add((Component)this.夜行者3, (Object)new AbsoluteConstraints(20, 70, 120, -1));
        this.奇袭者3.setFont(new Font("幼圆", 0, 15));
        this.奇袭者3.setText("奇袭者3");
        this.奇袭者3.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.奇袭者3ActionPerformed(evt);
            }
        });
        this.jPanel9.add((Component)this.奇袭者3, (Object)new AbsoluteConstraints(150, 70, 120, -1));
        this.奇袭者2.setFont(new Font("幼圆", 0, 15));
        this.奇袭者2.setText("奇袭者2");
        this.奇袭者2.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.奇袭者2ActionPerformed(evt);
            }
        });
        this.jPanel9.add((Component)this.奇袭者2, (Object)new AbsoluteConstraints(150, 40, 120, -1));
        this.奇袭者1.setFont(new Font("幼圆", 0, 15));
        this.奇袭者1.setText("奇袭者1");
        this.奇袭者1.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.奇袭者1ActionPerformed(evt);
            }
        });
        this.jPanel9.add((Component)this.奇袭者1, (Object)new AbsoluteConstraints(150, 10, 120, -1));
        this.jTabbedPane1.addTab("*", (Component)this.jPanel9);
        this.jPanel10.setBackground(new Color(255, 255, 255));
        this.jPanel10.setLayout((LayoutManager)new AbsoluteLayout());
        this.战神1.setFont(new Font("幼圆", 0, 15));
        this.战神1.setText("战神1");
        this.战神1.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.战神1ActionPerformed(evt);
            }
        });
        this.jPanel10.add((Component)this.战神1, (Object)new AbsoluteConstraints(20, 10, 120, -1));
        this.战神2.setFont(new Font("幼圆", 0, 15));
        this.战神2.setText("战神2");
        this.战神2.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.战神2ActionPerformed(evt);
            }
        });
        this.jPanel10.add((Component)this.战神2, (Object)new AbsoluteConstraints(20, 40, 120, -1));
        this.战神3.setFont(new Font("幼圆", 0, 15));
        this.战神3.setText("战神3");
        this.战神3.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.战神3ActionPerformed(evt);
            }
        });
        this.jPanel10.add((Component)this.战神3, (Object)new AbsoluteConstraints(20, 70, 120, -1));
        this.战神4.setFont(new Font("幼圆", 0, 15));
        this.战神4.setText("战神4");
        this.战神4.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.战神4ActionPerformed(evt);
            }
        });
        this.jPanel10.add((Component)this.战神4, (Object)new AbsoluteConstraints(20, 100, 120, -1));
        this.jTabbedPane1.addTab("战神", (Component)this.jPanel10);
        this.jPanel2.add((Component)this.jTabbedPane1, (Object)new AbsoluteConstraints(0, 0, 420, 170));
        this.jPanel1.add((Component)this.jPanel2, (Object)new AbsoluteConstraints(10, 280, 420, 170));
        this.任务开始.setFont(new Font("幼圆", 0, 15));
        this.任务开始.setText("任务开始");
        this.任务开始.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.任务开始ActionPerformed(evt);
            }
        });
        this.jPanel1.add((Component)this.任务开始, (Object)new AbsoluteConstraints(10, 250, 140, -1));
        this.金币.setFont(new Font("幼圆", 0, 15));
        this.金币.setText("金币");
        this.金币.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.金币ActionPerformed(evt);
            }
        });
        this.jPanel1.add((Component)this.金币, (Object)new AbsoluteConstraints(290, 100, 140, -1));
        this.点券.setFont(new Font("幼圆", 0, 15));
        this.点券.setText("点券");
        this.点券.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.点券ActionPerformed(evt);
            }
        });
        this.jPanel1.add((Component)this.点券, (Object)new AbsoluteConstraints(290, 130, 140, -1));
        this.抵用券.setFont(new Font("幼圆", 0, 15));
        this.抵用券.setText("抵用券");
        this.抵用券.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.抵用券ActionPerformed(evt);
            }
        });
        this.jPanel1.add((Component)this.抵用券, (Object)new AbsoluteConstraints(290, 70, 140, -1));
        this.刷物品.setFont(new Font("幼圆", 0, 15));
        this.刷物品.setText("刷物品");
        this.刷物品.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.刷物品ActionPerformed(evt);
            }
        });
        this.jPanel1.add((Component)this.刷物品, (Object)new AbsoluteConstraints(10, 220, 140, -1));
        this.任务代码.setText("任务代码");
        this.jPanel1.add((Component)this.任务代码, (Object)new AbsoluteConstraints(300, 250, 130, -1));
        this.任务完成.setFont(new Font("幼圆", 0, 15));
        this.任务完成.setText("任务完成");
        this.任务完成.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.任务完成ActionPerformed(evt);
            }
        });
        this.jPanel1.add((Component)this.任务完成, (Object)new AbsoluteConstraints(150, 250, 140, -1));
        this.刷新11.setFont(new Font("幼圆", 0, 15));
        this.刷新11.setText("没用的");
        this.刷新11.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DebugWindow.this.刷新11ActionPerformed(evt);
            }
        });
        this.jPanel1.add((Component)this.刷新11, (Object)new AbsoluteConstraints(10, 130, 140, -1));
        this.物品数量.setText("10");
        this.jPanel1.add((Component)this.物品数量, (Object)new AbsoluteConstraints(300, 220, 130, -1));
        this.物品代码.setText("2000000");
        this.jPanel1.add((Component)this.物品代码, (Object)new AbsoluteConstraints(160, 220, 130, -1));
        this.jLabel2.setFont(new Font("幼圆", 0, 18));
        this.jLabel2.setText("准备发送；");
        this.jPanel1.add((Component)this.jLabel2, (Object)new AbsoluteConstraints(10, 460, -1, -1));
        this.getContentPane().add((Component)this.jPanel1, (Object)new AbsoluteConstraints(0, 0, 440, 610));
        this.pack();
    }
    
    private void jButton1ActionPerformed(final ActionEvent evt) {
        final byte[] data = HexTool.getByteArrayFromHexString(this.封包.getText());
        this.jLabel1.setText(null);
        final MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        final int packetheader = Integer.parseInt(this.封包.getText());
        final String packet_in = " 00 00 00 00 00 00 00 00 00 ";
        mplew.writeShort(packetheader);
        mplew.write(HexTool.getByteArrayFromHexString(packet_in));
        mplew.writeZeroBytes(20);
        this.c.sendPacket(mplew.getPacket());
        this.c.getPlayer().dropMessage(6, "已传送封包[" + packetheader + "][" + mplew.getPacket().length + "] : " + mplew.toString());
        this.封包.setText("" + (Integer.parseInt(this.封包.getText()) + 1) + "");
        System.err.println("" + this.c.getPlayer().getName() + " 已发送：" + (Integer.parseInt(this.封包.getText()) - 1) + "");
        this.jButton1.setText("已发送：" + (Integer.parseInt(this.封包.getText()) - 1) + "");
    }
    
    private void 升级ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().dropMessage(6, "[等级上升]:成功增加等级");
        this.c.getPlayer().setExp(0);
        this.c.getPlayer().updateSingleStat(MapleStat.EXP, 0);
        if (this.c.getPlayer().getLevel() < 200) {
            this.c.getPlayer().gainExp(GameConstants.getExpNeededForLevel((int)this.c.getPlayer().getLevel()) + 1, true, false, true);
        }
    }
    
    private void 无敌ActionPerformed(final ActionEvent evt) {
        final MapleCharacter player = this.c.getPlayer();
        if (player.isInvincible()) {
            player.setInvincible(false);
            player.dropMessage(6, "[无敌状态]:已经关闭");
        }
        else {
            player.setInvincible(true);
            player.dropMessage(6, "[无敌状态]:已经开启.");
        }
    }
    
    private void SPActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().gainSP(10);
    }
    
    private void APActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().gainAp((short)10);
    }
    
    private void 刷新ActionPerformed(final ActionEvent evt) {
        final MapleCharacter player = this.c.getPlayer();
        this.c.sendPacket(MaplePacketCreator.getCharInfo(player));
        player.getMap().removePlayer(player);
        player.getMap().addPlayer(player);
    }
    
    private void 清怪ActionPerformed(final ActionEvent evt) {
        final MapleMap map = this.c.getPlayer().getMap();
        final double range = Double.POSITIVE_INFINITY;
        final boolean drop = false;
        final List<MapleMapObject> monsters = map.getMapObjectsInRange(this.c.getPlayer().getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER));
        for (final MapleMapObject monstermo : map.getMapObjectsInRange(this.c.getPlayer().getPosition(), range, Arrays.asList(MapleMapObjectType.MONSTER))) {
            final MapleMonster mob = (MapleMonster)monstermo;
            map.killMonster(mob, this.c.getPlayer(), true, false, (byte)1);
        }
    }
    
    private void 清物ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().getMap().removeDrops();
    }
    
    private void 坐标ActionPerformed(final ActionEvent evt) {
        final Point pos = this.c.getPlayer().getPosition();
        this.c.getPlayer().dropMessage(6, "X: " + pos.x + " | Y: " + pos.y + " | RX0: " + (pos.x + 50) + " | RX1: " + (pos.x - 50) + " | FH: " + this.c.getPlayer().getFH() + "| CY:" + pos.y);
    }
    
    private void 满技能ActionPerformed(final ActionEvent evt) {
        final MapleCharacter player = this.c.getPlayer();
        player.maxSkills();
    }
    
    private void 满属性ActionPerformed(final ActionEvent evt) {
        final MapleCharacter player = this.c.getPlayer();
        player.getStat().setMaxHp((short)30000);
        player.getStat().setMaxMp((short)30000);
        player.getStat().setStr((short)32767);
        player.getStat().setDex((short)32767);
        player.getStat().setInt((short)32767);
        player.getStat().setLuk((short)32767);
        player.updateSingleStat(MapleStat.MAXHP, 30000);
        player.updateSingleStat(MapleStat.MAXMP, 30000);
        player.updateSingleStat(MapleStat.STR, 32767);
        player.updateSingleStat(MapleStat.DEX, 32767);
        player.updateSingleStat(MapleStat.INT, 32767);
        player.updateSingleStat(MapleStat.LUK, 32767);
    }
    
    private void 战士ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(100);
    }
    
    private void 魂骑士1ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(1100);
    }
    
    private void 剑客ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(110);
    }
    
    private void 勇士ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(111);
    }
    
    private void 英雄ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(112);
    }
    
    private void 准骑士ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(120);
    }
    
    private void 骑士ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(121);
    }
    
    private void 圣骑士ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(122);
    }
    
    private void 枪战士ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(130);
    }
    
    private void 龙骑士ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(131);
    }
    
    private void 黑骑士ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(132);
    }
    
    private void 魔法师ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(200);
    }
    
    private void 火毒法师ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(210);
    }
    
    private void 火毒巫师ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(211);
    }
    
    private void 火毒魔导师ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(212);
    }
    
    private void 冰雷法师ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(220);
    }
    
    private void 冰雷巫师ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(221);
    }
    
    private void 冰雷魔导师ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(222);
    }
    
    private void 牧师ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(230);
    }
    
    private void 祭司ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(231);
    }
    
    private void 主教ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(232);
    }
    
    private void 弓箭手ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(300);
    }
    
    private void 火毒法师1ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(310);
    }
    
    private void 射手ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(311);
    }
    
    private void 神射手ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(312);
    }
    
    private void 弩弓手ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(320);
    }
    
    private void 游侠ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(321);
    }
    
    private void 箭神ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(322);
    }
    
    private void 飞侠ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(400);
    }
    
    private void 刺客ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(410);
    }
    
    private void 无影人ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(411);
    }
    
    private void 隐士ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(412);
    }
    
    private void 侠盗ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(422);
    }
    
    private void 独行客ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(421);
    }
    
    private void 侠客ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(420);
    }
    
    private void 海盗ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(500);
    }
    
    private void 拳手ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(510);
    }
    
    private void 斗士ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(511);
    }
    
    private void 冲锋队长ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(512);
    }
    
    private void 火枪手ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(520);
    }
    
    private void 大副ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(521);
    }
    
    private void 船长ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(522);
    }
    
    private void 任务开始ActionPerformed(final ActionEvent evt) {
        final boolean a = this.任务代码.getText().matches("[0-9]+");
        if (a) {
            MapleQuest.getInstance(Integer.parseInt(this.任务代码.getText())).forceStart(this.c.getPlayer(), 0, null);
        }
    }
    
    private void 魂骑士2ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(1110);
    }
    
    private void 魂骑士3ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(1111);
    }
    
    private void 炎术士1ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(1200);
    }
    
    private void 炎术士2ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(1210);
    }
    
    private void 炎术士3ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(1211);
    }
    
    private void 风灵使者1ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(1300);
    }
    
    private void 风灵使者3ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(1311);
    }
    
    private void 风灵使者2ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(1310);
    }
    
    private void 夜行者1ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(1400);
    }
    
    private void 夜行者2ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(1410);
    }
    
    private void 夜行者3ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(1411);
    }
    
    private void 奇袭者3ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(1511);
    }
    
    private void 奇袭者2ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(1510);
    }
    
    private void 奇袭者1ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(1500);
    }
    
    private void 战神1ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(2100);
    }
    
    private void 战神2ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(2110);
    }
    
    private void 战神3ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(2111);
    }
    
    private void 战神4ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().changeJob(2112);
    }
    
    private void 金币ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().gainMeso(1000000, true);
    }
    
    private void 点券ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().modifyCSPoints(1, 100000);
    }
    
    private void 抵用券ActionPerformed(final ActionEvent evt) {
        this.c.getPlayer().modifyCSPoints(2, 100000);
    }
    
    private void 刷物品ActionPerformed(final ActionEvent evt) {
        final boolean a = this.物品代码.getText().matches("[0-9]+");
        final boolean b = this.物品数量.getText().matches("[0-9]+");
        if (a && b) {
            MaplePacketCreator.getShowItemGain(Integer.parseInt(this.物品代码.getText()), (short)Integer.parseInt(this.物品数量.getText()), true);
        }
    }
    
    private void 任务完成ActionPerformed(final ActionEvent evt) {
    }
    
    private void 刷新11ActionPerformed(final ActionEvent evt) {
    }
    
    public static void main(final String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.setLookAndFeel((LookAndFeel)new SubstanceBusinessBlackSteelLookAndFeel());
        }
        catch (Exception ex5) {}
        try {
            for (final LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals((Object)info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(DebugWindow.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(DebugWindow.class.getName()).log(Level.SEVERE, null, (Throwable)ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(DebugWindow.class.getName()).log(Level.SEVERE, null, (Throwable)ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(DebugWindow.class.getName()).log(Level.SEVERE, null, (Throwable)ex4);
        }
        EventQueue.invokeLater((Runnable)new Runnable() {
            @Override
            public void run() {
                new DebugWindow().setVisible(true);
            }
        });
    }
}
