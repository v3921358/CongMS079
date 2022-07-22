package gui.tools;

import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.EventQueue;
import javax.swing.LookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import javax.swing.JDialog;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import server.MapleItemInformationProvider;
import handling.world.MapleParty;
import server.Start;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import gui.CongMS;
import javax.swing.JOptionPane;
import database.DatabaseConnection;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.Icon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout.Group;
import javax.swing.GroupLayout.Alignment;
import java.awt.Container;
import javax.swing.GroupLayout;
import java.awt.Component;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.Color;
import java.awt.LayoutManager;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTabbedPane;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class 活动控制台 extends JFrame
{
    private JPanel BOSS相关;
    private JLabel ZEVMS2提示框1;
    private JLabel jLabel264;
    private JLabel jLabel267;
    private JLabel jLabel269;
    private JLabel jLabel270;
    private JLabel jLabel272;
    private JLabel jLabel273;
    private JLabel jLabel274;
    private JLabel jLabel275;
    private JLabel jLabel323;
    private JLabel jLabel324;
    private JLabel jLabel325;
    private JLabel jLabel343;
    private JLabel jLabel344;
    private JLabel jLabel379;
    private JLabel jLabel380;
    private JLabel jLabel381;
    private JLabel jLabel382;
    private JPanel jPanel11;
    private JPanel jPanel12;
    private JPanel jPanel14;
    private JPanel jPanel15;
    private JPanel jPanel18;
    private JPanel jPanel20;
    private JPanel jPanel4;
    private JPanel jPanel8;
    private JPanel jPanel9;
    private JPanel jPanel91;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane10;
    private JScrollPane jScrollPane104;
    private JSeparator jSeparator11;
    private JSeparator jSeparator12;
    private JTabbedPane jTabbedPane6;
    private JTextPane jTextPane1;
    private JButton 修改神秘商人;
    private JButton 修改钓鱼物品;
    private JButton 删除钓鱼物品;
    private JButton 刷新野外BOSS刷新时间;
    private JButton 刷新野外BOSS刷新时间修改;
    private JButton 刷新钓鱼物品;
    private JButton 周末倍率开关;
    private JTextField 幸运职业代码;
    private JButton 幸运职业修改;
    private JButton 幸运职业开关;
    private JButton 新增钓鱼物品;
    private JButton 每日送货开关3;
    private JTextField 神秘商人出现时间;
    private JButton 神秘商人开关;
    private JTextField 野外BOSS;
    private JTable 野外BOSS刷新时间;
    private JTextField 野外BOSS刷新时间值;
    private JTextField 野外BOSS序号;
    private JButton 野外通缉开关;
    private JTable 钓鱼物品;
    private JTextField 钓鱼物品代码;
    private JTextField 钓鱼物品名称;
    private JTextField 钓鱼物品序号;
    private JTextField 钓鱼物品概率;
    private JPanel 钓鱼管理;
    private JButton 魔族攻城开关;
    private JButton 魔族突袭开关;
    
    public 活动控制台() {
        final ImageIcon icon = new ImageIcon(this.getClass().getClassLoader().getResource("image/Icon2.png"));
        this.setIconImage(icon.getImage());
        this.setTitle("游戏活动管理控制台 可关闭 ");
        this.initComponents();
        this.刷新野外BOSS刷新时间();
        this.刷新魔族突袭开关();
        this.刷新魔族攻城开关();
        this.刷新神秘商人开关();
        this.刷新野外通缉开关();
        this.刷新幸运职业开关();
        this.刷新幸运职业();
        this.刷新神秘商人时间();
        this.刷新周末倍率开关();
    }
    
    private void initComponents() {
        this.jTabbedPane6 = new JTabbedPane();
        this.jPanel20 = new JPanel();
        this.jPanel9 = new JPanel();
        this.魔族突袭开关 = new JButton();
        this.jLabel270 = new JLabel();
        this.jPanel4 = new JPanel();
        this.魔族攻城开关 = new JButton();
        this.jLabel264 = new JLabel();
        this.jPanel15 = new JPanel();
        this.野外通缉开关 = new JButton();
        this.jLabel275 = new JLabel();
        this.jPanel8 = new JPanel();
        this.幸运职业开关 = new JButton();
        this.jLabel269 = new JLabel();
        this.jLabel267 = new JLabel();
        this.jPanel14 = new JPanel();
        this.神秘商人开关 = new JButton();
        this.jLabel274 = new JLabel();
        this.jPanel11 = new JPanel();
        this.周末倍率开关 = new JButton();
        this.jLabel272 = new JLabel();
        this.jPanel12 = new JPanel();
        this.每日送货开关3 = new JButton();
        this.jLabel273 = new JLabel();
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
        this.BOSS相关 = new JPanel();
        this.jScrollPane104 = new JScrollPane();
        this.野外BOSS刷新时间 = new JTable();
        this.刷新野外BOSS刷新时间 = new JButton();
        this.野外BOSS序号 = new JTextField();
        this.野外BOSS刷新时间值 = new JTextField();
        this.野外BOSS = new JTextField();
        this.刷新野外BOSS刷新时间修改 = new JButton();
        this.jLabel323 = new JLabel();
        this.jLabel324 = new JLabel();
        this.jLabel325 = new JLabel();
        this.jPanel18 = new JPanel();
        this.jScrollPane1 = new JScrollPane();
        this.jTextPane1 = new JTextPane();
        this.神秘商人出现时间 = new JTextField();
        this.jLabel343 = new JLabel();
        this.修改神秘商人 = new JButton();
        this.幸运职业代码 = new JTextField();
        this.jLabel344 = new JLabel();
        this.幸运职业修改 = new JButton();
        this.setResizable(false);
        this.getContentPane().setLayout((LayoutManager)new AbsoluteLayout());
        this.jPanel9.setBackground(new Color(255, 255, 255));
        this.jPanel9.setBorder((Border)BorderFactory.createTitledBorder(null, "魔族突袭", 1, 2, new Font("幼圆", 0, 18)));
        this.jPanel9.setLayout((LayoutManager)new AbsoluteLayout());
        this.魔族突袭开关.setText("开关");
        this.魔族突袭开关.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                活动控制台.this.魔族突袭开关ActionPerformed(evt);
            }
        });
        this.jPanel9.add((Component)this.魔族突袭开关, (Object)new AbsoluteConstraints(890, 30, 80, 30));
        this.jLabel270.setFont(new Font("幼圆", 0, 18));
        this.jLabel270.setForeground(new Color(51, 102, 255));
        this.jLabel270.setText("开启后，每日22:00 - 22:10，蝙蝠魔会偷袭在野外的冒险家，高于30级，落单弱小的玩家偷袭概率最高");
        this.jPanel9.add((Component)this.jLabel270, (Object)new AbsoluteConstraints(10, 30, 840, 20));
        this.jPanel4.setBackground(new Color(255, 255, 255));
        this.jPanel4.setBorder((Border)BorderFactory.createTitledBorder(null, "魔族攻城", 1, 2, new Font("幼圆", 0, 18)));
        this.jPanel4.setLayout((LayoutManager)new AbsoluteLayout());
        this.魔族攻城开关.setText("开关");
        this.魔族攻城开关.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                活动控制台.this.魔族攻城开关ActionPerformed(evt);
            }
        });
        this.jPanel4.add((Component)this.魔族攻城开关, (Object)new AbsoluteConstraints(890, 30, 80, 30));
        this.jLabel264.setFont(new Font("幼圆", 0, 18));
        this.jLabel264.setForeground(new Color(51, 102, 255));
        this.jLabel264.setText("开启后，周末晚上 21:10 之后魔族会进行攻城，从林中之城开始攻向明珠港，射手村，废弃都市，魔法密林。");
        this.jPanel4.add((Component)this.jLabel264, (Object)new AbsoluteConstraints(10, 30, 870, 30));
        this.jPanel15.setBackground(new Color(255, 255, 255));
        this.jPanel15.setBorder((Border)BorderFactory.createTitledBorder(null, "野外通缉", 1, 2, new Font("幼圆", 0, 18)));
        this.jPanel15.setLayout((LayoutManager)new AbsoluteLayout());
        this.野外通缉开关.setText("开关");
        this.野外通缉开关.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                活动控制台.this.野外通缉开关ActionPerformed(evt);
            }
        });
        this.jPanel15.add((Component)this.野外通缉开关, (Object)new AbsoluteConstraints(890, 30, 80, 30));
        this.jLabel275.setFont(new Font("幼圆", 0, 18));
        this.jLabel275.setForeground(new Color(51, 102, 255));
        this.jLabel275.setText("开启后，服务端会在启动后 1 小时候发布通缉令，通缉目标被击杀后会 1 小时再次发");
        this.jPanel15.add((Component)this.jLabel275, (Object)new AbsoluteConstraints(10, 30, 820, 30));
        this.jPanel8.setBackground(new Color(255, 255, 255));
        this.jPanel8.setBorder((Border)BorderFactory.createTitledBorder(null, "幸运职业", 1, 2, new Font("幼圆", 0, 18)));
        this.jPanel8.setLayout((LayoutManager)new AbsoluteLayout());
        this.幸运职业开关.setText("开关");
        this.幸运职业开关.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                活动控制台.this.幸运职业开关ActionPerformed(evt);
            }
        });
        this.jPanel8.add((Component)this.幸运职业开关, (Object)new AbsoluteConstraints(890, 30, 80, 30));
        this.jLabel269.setFont(new Font("幼圆", 0, 18));
        this.jLabel269.setForeground(new Color(51, 102, 255));
        this.jLabel269.setText("开启后，给予指定的职业增加50%的额外狩猎经验，每日 11:00 23:00 会随机重置指定的职业，二转后生效。");
        this.jPanel8.add((Component)this.jLabel269, (Object)new AbsoluteConstraints(10, 30, 880, 30));
        this.jLabel267.setFont(new Font("幼圆", 0, 18));
        this.jLabel267.setForeground(new Color(255, 0, 51));
        this.jPanel14.setBackground(new Color(255, 255, 255));
        this.jPanel14.setBorder((Border)BorderFactory.createTitledBorder(null, "神秘商人", 1, 2, new Font("幼圆", 0, 18)));
        this.jPanel14.setLayout((LayoutManager)new AbsoluteLayout());
        this.神秘商人开关.setText("开关");
        this.神秘商人开关.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                活动控制台.this.神秘商人开关ActionPerformed(evt);
            }
        });
        this.jPanel14.add((Component)this.神秘商人开关, (Object)new AbsoluteConstraints(890, 30, 80, 30));
        this.jLabel274.setFont(new Font("幼圆", 0, 18));
        this.jLabel274.setForeground(new Color(51, 102, 255));
        this.jLabel274.setText("开启后，服务端会开始召唤神秘商人，商人每次只会待 5 分钟，消失并告知下一次出现的信息（9900001.js）");
        this.jPanel14.add((Component)this.jLabel274, (Object)new AbsoluteConstraints(10, 30, 870, 30));
        this.jPanel11.setBackground(new Color(255, 255, 255));
        this.jPanel11.setBorder((Border)BorderFactory.createTitledBorder(null, "周末随机双倍活动", 1, 2, new Font("幼圆", 0, 18)));
        this.jPanel11.setLayout((LayoutManager)new AbsoluteLayout());
        this.周末倍率开关.setText("开关");
        this.周末倍率开关.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                活动控制台.this.周末倍率开关ActionPerformed(evt);
            }
        });
        this.jPanel11.add((Component)this.周末倍率开关, (Object)new AbsoluteConstraints(890, 30, 80, 30));
        this.jLabel272.setFont(new Font("幼圆", 0, 18));
        this.jLabel272.setForeground(new Color(51, 102, 255));
        this.jLabel272.setText("开启后，周六，周日凌晨会随机开启24小时2倍经验，爆率，或者经验爆率活动。");
        this.jPanel11.add((Component)this.jLabel272, (Object)new AbsoluteConstraints(10, 30, 820, 30));
        this.jPanel12.setBackground(new Color(255, 255, 255));
        this.jPanel12.setBorder((Border)BorderFactory.createTitledBorder(null, "每日送货", 1, 2, new Font("幼圆", 0, 18)));
        this.jPanel12.setLayout((LayoutManager)new AbsoluteLayout());
        this.每日送货开关3.setText("开关");
        this.每日送货开关3.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                活动控制台.this.每日送货开关3ActionPerformed(evt);
            }
        });
        this.jPanel12.add((Component)this.每日送货开关3, (Object)new AbsoluteConstraints(1050, 30, 130, -1));
        this.jLabel273.setFont(new Font("幼圆", 0, 18));
        this.jLabel273.setForeground(new Color(255, 0, 51));
        this.jLabel273.setText("开启后，每日12:00之后开始送货，从明珠港开始，到废弃都市结束。");
        this.jPanel12.add((Component)this.jLabel273, (Object)new AbsoluteConstraints(80, 30, 820, 30));
        this.jPanel11.add((Component)this.jPanel12, (Object)new AbsoluteConstraints(30, 400, 1190, 80));
        final GroupLayout jPanel20Layout = new GroupLayout((Container)this.jPanel20);
        this.jPanel20.setLayout((LayoutManager)jPanel20Layout);
        jPanel20Layout.setHorizontalGroup((Group)jPanel20Layout.createParallelGroup(Alignment.LEADING).addComponent((Component)this.jPanel9, -1, -1, 32767).addComponent((Component)this.jPanel4, -1, -1, 32767).addComponent((Component)this.jPanel15, -1, -1, 32767).addGroup((Group)jPanel20Layout.createSequentialGroup().addComponent((Component)this.jPanel8, -2, 980, -2).addGap(0, 0, 32767)).addGroup((Group)jPanel20Layout.createSequentialGroup().addGroup((Group)jPanel20Layout.createParallelGroup(Alignment.TRAILING).addComponent((Component)this.jPanel11, -2, 980, -2).addComponent((Component)this.jPanel14, -2, 981, -2)).addPreferredGap(ComponentPlacement.RELATED).addComponent((Component)this.jLabel267, -2, 610, -2).addContainerGap(-1, 32767)));
        jPanel20Layout.setVerticalGroup((Group)jPanel20Layout.createParallelGroup(Alignment.LEADING).addGroup((Group)jPanel20Layout.createSequentialGroup().addComponent((Component)this.jPanel9, -2, 80, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent((Component)this.jPanel4, -2, 80, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent((Component)this.jPanel8, -2, 80, -2).addGap(14, 14, 14).addComponent((Component)this.jPanel15, -2, 80, -2).addPreferredGap(ComponentPlacement.RELATED).addGroup((Group)jPanel20Layout.createParallelGroup(Alignment.LEADING).addComponent((Component)this.jLabel267, -2, 30, -2).addComponent((Component)this.jPanel14, -2, 80, -2)).addPreferredGap(ComponentPlacement.UNRELATED).addComponent((Component)this.jPanel11, -2, 80, -2).addGap(0, 138, 32767)));
        this.jTabbedPane6.addTab("活动控制台", (Icon)new ImageIcon(this.getClass().getClassLoader().getResource("image/1.png")), (Component)this.jPanel20);
        this.钓鱼管理.setBackground(new Color(255, 255, 255));
        this.钓鱼管理.setBorder((Border)BorderFactory.createTitledBorder(null, "钓鱼管理", 2, 2, new Font("幼圆", 0, 24)));
        this.钓鱼管理.setLayout((LayoutManager)new AbsoluteLayout());
        this.钓鱼物品.setFont(new Font("幼圆", 0, 15));
        this.钓鱼物品.setModel((TableModel)new DefaultTableModel(new Object[0][], new String[] { "序号", "代码", "概率", "物品名称" }) {
            boolean[] canEdit = { false, false, false, false };
            
            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.钓鱼物品.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane10.setViewportView((Component)this.钓鱼物品);
        this.钓鱼管理.add((Component)this.jScrollPane10, (Object)new AbsoluteConstraints(50, 40, 770, 380));
        this.钓鱼管理.add((Component)this.jSeparator11, (Object)new AbsoluteConstraints(600, 10, -1, -1));
        this.钓鱼管理.add((Component)this.jSeparator12, (Object)new AbsoluteConstraints(600, 10, -1, -1));
        this.jPanel91.setBackground(new Color(255, 255, 255));
        this.jPanel91.setBorder((Border)BorderFactory.createTitledBorder(null, "钓鱼编辑", 2, 2, new Font("幼圆", 0, 18)));
        this.jPanel91.setLayout((LayoutManager)new AbsoluteLayout());
        this.修改钓鱼物品.setFont(new Font("幼圆", 0, 15));
        this.修改钓鱼物品.setText("修改");
        this.修改钓鱼物品.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                活动控制台.this.修改钓鱼物品ActionPerformed(evt);
            }
        });
        this.jPanel91.add((Component)this.修改钓鱼物品, (Object)new AbsoluteConstraints(220, 90, -1, 30));
        this.刷新钓鱼物品.setFont(new Font("幼圆", 0, 15));
        this.刷新钓鱼物品.setText("刷新钓鱼物品");
        this.刷新钓鱼物品.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                活动控制台.this.刷新钓鱼物品ActionPerformed(evt);
            }
        });
        this.jPanel91.add((Component)this.刷新钓鱼物品, (Object)new AbsoluteConstraints(20, 90, -1, 30));
        this.钓鱼物品代码.setFont(new Font("幼圆", 0, 15));
        this.钓鱼物品代码.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                活动控制台.this.钓鱼物品代码ActionPerformed(evt);
            }
        });
        this.jPanel91.add((Component)this.钓鱼物品代码, (Object)new AbsoluteConstraints(100, 50, 110, 30));
        this.新增钓鱼物品.setFont(new Font("幼圆", 0, 15));
        this.新增钓鱼物品.setText("新增");
        this.新增钓鱼物品.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                活动控制台.this.新增钓鱼物品ActionPerformed(evt);
            }
        });
        this.jPanel91.add((Component)this.新增钓鱼物品, (Object)new AbsoluteConstraints(150, 90, -1, 30));
        this.钓鱼物品概率.setFont(new Font("幼圆", 0, 15));
        this.jPanel91.add((Component)this.钓鱼物品概率, (Object)new AbsoluteConstraints(210, 50, 100, 30));
        this.钓鱼物品名称.setEditable(false);
        this.钓鱼物品名称.setFont(new Font("幼圆", 0, 15));
        this.jPanel91.add((Component)this.钓鱼物品名称, (Object)new AbsoluteConstraints(310, 50, 150, 30));
        this.删除钓鱼物品.setFont(new Font("幼圆", 0, 15));
        this.删除钓鱼物品.setText("删除");
        this.删除钓鱼物品.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                活动控制台.this.删除钓鱼物品ActionPerformed(evt);
            }
        });
        this.jPanel91.add((Component)this.删除钓鱼物品, (Object)new AbsoluteConstraints(290, 90, -1, 30));
        this.钓鱼物品序号.setEditable(false);
        this.钓鱼物品序号.setFont(new Font("幼圆", 0, 15));
        this.钓鱼物品序号.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                活动控制台.this.钓鱼物品序号ActionPerformed(evt);
            }
        });
        this.jPanel91.add((Component)this.钓鱼物品序号, (Object)new AbsoluteConstraints(20, 50, 80, 30));
        this.jLabel379.setFont(new Font("幼圆", 0, 15));
        this.jLabel379.setText("物品名字；");
        this.jPanel91.add((Component)this.jLabel379, (Object)new AbsoluteConstraints(310, 30, -1, -1));
        this.jLabel380.setFont(new Font("幼圆", 0, 15));
        this.jLabel380.setText("序列号；");
        this.jPanel91.add((Component)this.jLabel380, (Object)new AbsoluteConstraints(20, 30, -1, -1));
        this.jLabel381.setFont(new Font("幼圆", 0, 15));
        this.jLabel381.setText("物品代码；");
        this.jPanel91.add((Component)this.jLabel381, (Object)new AbsoluteConstraints(100, 30, -1, -1));
        this.jLabel382.setFont(new Font("幼圆", 0, 15));
        this.jLabel382.setText("垂钓概率；");
        this.jPanel91.add((Component)this.jLabel382, (Object)new AbsoluteConstraints(210, 30, -1, -1));
        this.钓鱼管理.add((Component)this.jPanel91, (Object)new AbsoluteConstraints(60, 430, 480, 130));
        this.ZEVMS2提示框1.setFont(new Font("幼圆", 0, 18));
        this.ZEVMS2提示框1.setText("[信息]：");
        this.钓鱼管理.add((Component)this.ZEVMS2提示框1, (Object)new AbsoluteConstraints(0, 725, 1260, 30));
        this.jTabbedPane6.addTab("渔场钓鱼管理", (Icon)new ImageIcon(this.getClass().getResource("/provider/WzXML/1.png")), (Component)this.钓鱼管理);
        this.BOSS相关.setBackground(new Color(255, 255, 255));
        this.BOSS相关.setBorder((Border)BorderFactory.createTitledBorder(null, "BOSS刷新时间", 2, 2, new Font("幼圆", 0, 24)));
        this.BOSS相关.setLayout((LayoutManager)new AbsoluteLayout());
        this.野外BOSS刷新时间.setModel((TableModel)new DefaultTableModel(new Object[0][], new String[] { "序号", "野外BOSS", "刷新时间/分" }) {
            boolean[] canEdit = { false, false, false };
            
            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.野外BOSS刷新时间.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane104.setViewportView((Component)this.野外BOSS刷新时间);
        this.BOSS相关.add((Component)this.jScrollPane104, (Object)new AbsoluteConstraints(10, 30, 460, 610));
        this.刷新野外BOSS刷新时间.setFont(new Font("幼圆", 0, 15));
        this.刷新野外BOSS刷新时间.setText("刷新");
        this.刷新野外BOSS刷新时间.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                活动控制台.this.刷新野外BOSS刷新时间ActionPerformed(evt);
            }
        });
        this.BOSS相关.add((Component)this.刷新野外BOSS刷新时间, (Object)new AbsoluteConstraints(680, 180, 90, 30));
        this.野外BOSS序号.setEditable(false);
        this.BOSS相关.add((Component)this.野外BOSS序号, (Object)new AbsoluteConstraints(480, 100, 100, 30));
        this.BOSS相关.add((Component)this.野外BOSS刷新时间值, (Object)new AbsoluteConstraints(790, 100, 110, 30));
        this.野外BOSS.setEditable(false);
        this.BOSS相关.add((Component)this.野外BOSS, (Object)new AbsoluteConstraints(580, 100, 210, 30));
        this.刷新野外BOSS刷新时间修改.setFont(new Font("幼圆", 0, 15));
        this.刷新野外BOSS刷新时间修改.setText("修改");
        this.刷新野外BOSS刷新时间修改.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                活动控制台.this.刷新野外BOSS刷新时间修改ActionPerformed(evt);
            }
        });
        this.BOSS相关.add((Component)this.刷新野外BOSS刷新时间修改, (Object)new AbsoluteConstraints(590, 180, 90, 30));
        this.jLabel323.setFont(new Font("幼圆", 0, 14));
        this.jLabel323.setText("刷新时间；");
        this.BOSS相关.add((Component)this.jLabel323, (Object)new AbsoluteConstraints(790, 80, -1, -1));
        this.jLabel324.setFont(new Font("幼圆", 0, 14));
        this.jLabel324.setText("序号；");
        this.BOSS相关.add((Component)this.jLabel324, (Object)new AbsoluteConstraints(480, 80, -1, -1));
        this.jLabel325.setFont(new Font("幼圆", 0, 14));
        this.jLabel325.setText("BOSS；");
        this.BOSS相关.add((Component)this.jLabel325, (Object)new AbsoluteConstraints(580, 80, -1, -1));
        this.jTabbedPane6.addTab("野外BOSS刷新时间", (Icon)new ImageIcon(this.getClass().getResource("/gui/1.png")), (Component)this.BOSS相关);
        this.jPanel18.setBackground(new Color(255, 255, 255));
        this.jPanel18.setLayout((LayoutManager)new AbsoluteLayout());
        this.jTextPane1.setEditable(false);
        this.jTextPane1.setFont(new Font("幼圆", 0, 18));
        this.jTextPane1.setForeground(new Color(51, 0, 255));
        this.jTextPane1.setText("[每日送货] 每日 12:00 - 23:59\n[魔族偷袭] 每日 22:00 - 22:10\n[魔族攻城] 周日 21:00 - 21:30\n[每日答题] 周一至周五 20:10 - 20:20 周末 20:10 - 20:59\n[神秘商人] 完全随机出现，无规律\n[野外通缉] 系统发布一个后，玩家完成后 1 小时刷新\n[幸运职业] 11:00 23:00 随机抽取职业群，增加 50% 狩猎经验\n[周末狂欢] 周六，周日凌晨随机开启2倍经验，2倍爆率，2倍经验和爆率\n[喜从天降] 周日，22:30 会在 2 频道市场狂欢发放物品\n[鱼来鱼往] 周一至周五 21:30 - 21:40 周末 21:30 - 21:59 在水下世界举行\n");
        this.jTextPane1.setToolTipText("");
        this.jScrollPane1.setViewportView((Component)this.jTextPane1);
        this.jPanel18.add((Component)this.jScrollPane1, (Object)new AbsoluteConstraints(20, 20, 620, 540));
        this.jPanel18.add((Component)this.神秘商人出现时间, (Object)new AbsoluteConstraints(650, 50, 110, 30));
        this.jLabel343.setFont(new Font("幼圆", 0, 15));
        this.jLabel343.setText("神秘商人；");
        this.jPanel18.add((Component)this.jLabel343, (Object)new AbsoluteConstraints(650, 30, -1, 20));
        this.修改神秘商人.setFont(new Font("幼圆", 0, 15));
        this.修改神秘商人.setText("修改");
        this.修改神秘商人.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                活动控制台.this.修改神秘商人ActionPerformed(evt);
            }
        });
        this.jPanel18.add((Component)this.修改神秘商人, (Object)new AbsoluteConstraints(760, 50, 70, 30));
        this.jPanel18.add((Component)this.幸运职业代码, (Object)new AbsoluteConstraints(650, 210, 110, 30));
        this.jLabel344.setFont(new Font("幼圆", 0, 15));
        this.jLabel344.setText("幸运职业；");
        this.jPanel18.add((Component)this.jLabel344, (Object)new AbsoluteConstraints(650, 190, -1, 20));
        this.幸运职业修改.setFont(new Font("幼圆", 0, 15));
        this.幸运职业修改.setText("修改");
        this.幸运职业修改.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                活动控制台.this.幸运职业修改ActionPerformed(evt);
            }
        });
        this.jPanel18.add((Component)this.幸运职业修改, (Object)new AbsoluteConstraints(760, 210, 70, 30));
        this.jTabbedPane6.addTab("预览", (Component)this.jPanel18);
        this.getContentPane().add((Component)this.jTabbedPane6, (Object)new AbsoluteConstraints(0, 0, 990, 690));
        this.pack();
        this.setLocationRelativeTo(null);
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
                    JOptionPane.showMessageDialog(null, (Object)"修改钓鱼物品成功。");
                    this.刷新钓鱼();
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, (Object)"[信息]:输入<物品代码><概率>。");
        }
    }
    
    private void 刷新钓鱼物品ActionPerformed(final ActionEvent evt) {
        JOptionPane.showMessageDialog(null, (Object)"[信息]:刷新钓鱼奖励成功。");
        this.刷新钓鱼();
    }
    
    private void 钓鱼物品代码ActionPerformed(final ActionEvent evt) {
    }
    
    private void 新增钓鱼物品ActionPerformed(final ActionEvent evt) {
        final boolean result1 = this.钓鱼物品代码.getText().matches("[0-9]+");
        final boolean result2 = this.钓鱼物品概率.getText().matches("[0-9]+");
        if (result1 && result2) {
            if (Integer.parseInt(this.钓鱼物品代码.getText()) < 0 && Integer.parseInt(this.钓鱼物品概率.getText()) < 0) {
                JOptionPane.showMessageDialog(null, (Object)"[信息]:请填写正确的值。");
                return;
            }
            try (final Connection con = DatabaseConnection.getConnection();
                 final PreparedStatement ps = con.prepareStatement("INSERT INTO 钓鱼物品 (itemid, chance ,expiration) VALUES (?, ?, ?)")) {
                ps.setInt(1, Integer.parseInt(this.钓鱼物品代码.getText()));
                ps.setInt(2, Integer.parseInt(this.钓鱼物品概率.getText()));
                ps.setInt(3, 1);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, (Object)"[信息]:新增钓鱼奖励成功。");
                this.刷新钓鱼();
            }
            catch (SQLException ex) {
                Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, (Object)"[信息]:请输入<物品代码><概率>。");
        }
    }
    
    private void 删除钓鱼物品ActionPerformed(final ActionEvent evt) {
        final String 输出 = "";
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result1 = this.钓鱼物品序号.getText().matches("[0-9]+");
        if (result1) {
            try {
                for (int i = ((DefaultTableModel)(DefaultTableModel)this.钓鱼物品.getModel()).getRowCount() - 1; i >= 0; --i) {
                    ((DefaultTableModel)(DefaultTableModel)this.钓鱼物品.getModel()).removeRow(i);
                }
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM 钓鱼物品 WHERE id = ?");
                ps1.setInt(1, Integer.parseInt(this.钓鱼物品序号.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from 钓鱼物品 where id =" + Integer.parseInt(this.钓鱼物品序号.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    JOptionPane.showMessageDialog(null, (Object)"[信息]:删除钓鱼奖励物品成功。");
                    this.刷新钓鱼();
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, (Object)"[信息]:请选择你要删除的钓鱼物品。");
        }
    }
    
    private void 钓鱼物品序号ActionPerformed(final ActionEvent evt) {
    }
    
    private void 刷新野外BOSS刷新时间ActionPerformed(final ActionEvent evt) {
        this.刷新野外BOSS刷新时间();
    }
    
    private void 刷新野外BOSS刷新时间修改ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        final boolean result1 = this.野外BOSS刷新时间值.getText().matches("[0-9]+");
        if (result1) {
            try {
                ps = DatabaseConnection.getConnection().prepareStatement("UPDATE configvalues SET Val = ? WHERE id = ?");
                ps2 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM configvalues WHERE id = ?");
                ps2.setInt(1, Integer.parseInt(this.野外BOSS序号.getText()));
                rs = ps2.executeQuery();
                if (rs.next()) {
                    String sqlString1 = null;
                    sqlString1 = "update configvalues set Val = '" + this.野外BOSS刷新时间值.getText() + "' where id= " + this.野外BOSS序号.getText() + ";";
                    final PreparedStatement Val = DatabaseConnection.getConnection().prepareStatement(sqlString1);
                    Val.executeUpdate(sqlString1);
                    this.刷新野外BOSS刷新时间();
                    Start.GetConfigValues();
                    JOptionPane.showMessageDialog(null, (Object)"修改成功，已经生效");
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(活动控制台.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, (Object)"请选择你要修改的刷新时间");
        }
    }
    
    private void 魔族突袭开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("魔族突袭开关", 2400);
        this.刷新魔族突袭开关();
    }
    
    private void 魔族攻城开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("魔族攻城开关", 2404);
        this.刷新魔族攻城开关();
    }
    
    private void 野外通缉开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("野外通缉开关", 2407);
        this.刷新野外通缉开关();
    }
    
    private void 幸运职业开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("幸运职业开关", 749);
        this.刷新幸运职业开关();
    }
    
    private void 修改神秘商人ActionPerformed(final ActionEvent evt) {
        final boolean result1 = this.神秘商人出现时间.getText().matches("[0-9]+");
        if (result1) {
            MapleParty.神秘商人时间 = Integer.parseInt(this.神秘商人出现时间.getText());
            this.刷新神秘商人时间();
            JOptionPane.showMessageDialog(null, (Object)("修改成功，神秘商人出现时间变更为 " + this.神秘商人出现时间.getText()));
        }
    }
    
    private void 幸运职业修改ActionPerformed(final ActionEvent evt) {
        final boolean result1 = this.幸运职业代码.getText().matches("[0-9]+");
        if (result1) {
            MapleParty.幸运职业 = Integer.parseInt(this.幸运职业代码.getText());
            this.刷新幸运职业();
            JOptionPane.showMessageDialog(null, (Object)("修改成功，幸运职业变更为 " + this.幸运职业代码.getText()));
        }
    }
    
    private void 神秘商人开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("神秘商人开关", 2406);
        this.刷新神秘商人开关();
    }
    
    private void 周末倍率开关ActionPerformed(final ActionEvent evt) {
        this.按键开关("周末倍率开关", 2405);
        this.刷新周末倍率开关();
    }
    
    private void 每日送货开关3ActionPerformed(final ActionEvent evt) {
    }
    
    private void 刷新周末倍率开关() {
        String 显示 = "";
        final int S = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"周末倍率开关"));
        if (S <= 0) {
            显示 = "开启";
        }
        else {
            显示 = "关闭";
        }
        this.周末倍率开关.setText(显示);
    }
    
    private void 刷新神秘商人时间() {
        this.神秘商人出现时间.setText("" + MapleParty.神秘商人时间 + "");
    }
    
    private void 刷新幸运职业() {
        this.幸运职业代码.setText("" + MapleParty.幸运职业 + "");
    }
    
    private void 刷新神秘商人开关() {
        String 显示 = "";
        final int S = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"神秘商人开关"));
        if (S <= 0) {
            显示 = "开启";
        }
        else {
            显示 = "关闭";
        }
        this.神秘商人开关.setText(显示);
    }
    
    private void 刷新幸运职业开关() {
        String 显示 = "";
        final int S = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"幸运职业开关"));
        if (S <= 0) {
            显示 = "开启";
        }
        else {
            显示 = "关闭";
        }
        this.幸运职业开关.setText(显示);
    }
    
    private void 刷新野外通缉开关() {
        String 显示 = "";
        final int S = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"野外通缉开关"));
        if (S <= 0) {
            显示 = "开启";
        }
        else {
            显示 = "关闭";
        }
        this.野外通缉开关.setText(显示);
    }
    
    private void 刷新魔族突袭开关() {
        String 显示 = "";
        final int S = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"魔族突袭开关"));
        if (S <= 0) {
            显示 = "开启";
        }
        else {
            显示 = "关闭";
        }
        this.魔族突袭开关.setText(显示);
    }
    
    private void 刷新魔族攻城开关() {
        String 显示 = "";
        final int S = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)"魔族攻城开关"));
        if (S <= 0) {
            显示 = "开启";
        }
        else {
            显示 = "关闭";
        }
        this.魔族攻城开关.setText(显示);
    }
    
    private void 刷新钓鱼() {
        for (int i = ((DefaultTableModel)(DefaultTableModel)this.钓鱼物品.getModel()).getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel)(DefaultTableModel)this.钓鱼物品.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM 钓鱼物品");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel)this.钓鱼物品.getModel()).insertRow(this.钓鱼物品.getRowCount(), new Object[] { Integer.valueOf(rs.getInt("id")), Integer.valueOf(rs.getInt("itemid")), Integer.valueOf(rs.getInt("chance")), MapleItemInformationProvider.getInstance().getName(rs.getInt("itemid")) });
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
        }
        this.钓鱼物品.addMouseListener((MouseListener)new MouseAdapter() {
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
    
    public void 刷新野外BOSS刷新时间() {
        for (int i = ((DefaultTableModel)(DefaultTableModel)this.野外BOSS刷新时间.getModel()).getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel)(DefaultTableModel)this.野外BOSS刷新时间.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM configvalues WHERE id >= 10000 && id < 20000");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel)this.野外BOSS刷新时间.getModel()).insertRow(this.野外BOSS刷新时间.getRowCount(), new Object[] { rs.getString("id"), rs.getString("name"), rs.getString("Val") });
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(活动控制台.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
        }
        this.野外BOSS刷新时间.addMouseListener((MouseListener)new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                final int i = 野外BOSS刷新时间.getSelectedRow();
                final String a = 野外BOSS刷新时间.getValueAt(i, 0).toString();
                final String a2 = 野外BOSS刷新时间.getValueAt(i, 1).toString();
                final String a3 = 野外BOSS刷新时间.getValueAt(i, 2).toString();
                野外BOSS序号.setText(a);
                野外BOSS.setText(a2);
                野外BOSS刷新时间值.setText(a3);
            }
        });
    }
    
    public void 按键开关(final String a, final int b) {
        final int 检测开关 = (int)Integer.valueOf(CongMS.ConfigValuesMap.get((Object)a));
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
            }
            catch (SQLException ex) {
                Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
            }
        }
        else {
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
            }
            catch (SQLException ex) {
                Logger.getLogger(CongMS.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
            }
        }
        CongMS.GetConfigValues();
    }
    
    public static void main(final String[] args) {
        try {
            for (final LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals((Object)info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(活动控制台.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(活动控制台.class.getName()).log(Level.SEVERE, null, (Throwable)ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(活动控制台.class.getName()).log(Level.SEVERE, null, (Throwable)ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(活动控制台.class.getName()).log(Level.SEVERE, null, (Throwable)ex4);
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.setLookAndFeel((LookAndFeel)new SubstanceBusinessBlackSteelLookAndFeel());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater((Runnable)new Runnable() {
            @Override
            public void run() {
                new 活动控制台().setVisible(true);
            }
        });
    }
}
