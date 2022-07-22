package gui.tools;

import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.EventQueue;
import javax.swing.LookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import javax.swing.JDialog;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.LayoutManager;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import tools.SearchGenerator;
import java.util.Iterator;
import java.awt.Component;
import javax.swing.JOptionPane;
import tools.SearchGenerator.SearchType;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFrame;

public class 代码查询工具 extends JFrame
{
    private JButton jButton1;
    private JComboBox jComboBox1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel4;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JTextField jTextField1;
    
    public 代码查询工具() {
        final ImageIcon icon = new ImageIcon(this.getClass().getClassLoader().getResource("image/Icon.png"));
        this.setIconImage(icon.getImage());
        this.setTitle("【代码查询工具，可关闭】");
        this.initComponents();
        this.jTable1.getColumnModel().getColumn(0).setPreferredWidth(75);
        this.jTable1.getColumnModel().getColumn(1).setPreferredWidth(275);
        this.jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
    }
    
    public void init() {
        this.jTextField1.setText("");
        this.jComboBox1.setSelectedIndex(0);
        this.modelClear();
        this.setSize(400, 300);
    }
    
    public void modelClear() {
        final DefaultTableModel model = (DefaultTableModel)this.jTable1.getModel();
        for (int count = model.getRowCount(), i = 0; i < count; ++i) {
            model.removeRow(0);
        }
    }
    
    public boolean addModel(final int type, final Map<Integer, String> data, final boolean show) {
        return this.addModel(SearchType.valueOf(SearchType.nameOf(type)), data, show);
    }
    
    public boolean addModel(final SearchType type, final Map<Integer, String> data, final boolean show) {
        if (data == null || data.isEmpty()) {
            if (show) {
                JOptionPane.showMessageDialog(null, (Object)"未找到。");
            }
            return false;
        }
        final DefaultTableModel model = (DefaultTableModel)this.jTable1.getModel();
        final Iterator<Integer> iterator = data.keySet().iterator();
        while (iterator.hasNext()) {
            final int i = (int)Integer.valueOf(iterator.next());
            model.addRow(new Object[] { type.name(), data.get((Object)Integer.valueOf(i)), Integer.valueOf(i) });
        }
        return true;
    }
    
    public void search() {
        this.modelClear();
        final String str = this.jTextField1.getText();
        final int type = this.jComboBox1.getSelectedIndex();
        if (type == 0) {
            boolean find = false;
            for (int i = 1; i <= SearchGenerator.职业; ++i) {
                final boolean show = this.addModel(i, SearchGenerator.getSearchData(i, str), i == SearchGenerator.职业 && !find);
                if (!find) {
                    find = show;
                }
            }
        }
        else {
            this.addModel(type, SearchGenerator.getSearchData(type, str), true);
        }
    }
    
    private void initComponents() {
        this.jPanel4 = new JPanel();
        this.jLabel1 = new JLabel();
        this.jComboBox1 = new JComboBox();
        this.jLabel2 = new JLabel();
        this.jTextField1 = new JTextField();
        this.jButton1 = new JButton();
        this.jScrollPane1 = new JScrollPane();
        this.jTable1 = new JTable();
        this.setResizable(false);
        this.getContentPane().setLayout((LayoutManager)new AbsoluteLayout());
        this.jLabel1.setText("搜索类型:");
        this.jPanel4.add((Component)this.jLabel1);
        this.jComboBox1.setModel((ComboBoxModel<String>)new DefaultComboBoxModel<String>(new String[] { "全部", "道具", "NPC", "地图", "怪物", "任务", "技能", "职业", "服务端包头", "客户端包头", "发型", "脸型", "SN" }));
        this.jComboBox1.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                代码查询工具.this.jComboBox1ActionPerformed(evt);
            }
        });
        this.jPanel4.add((Component)this.jComboBox1);
        this.jLabel2.setText("搜索关键字:");
        this.jPanel4.add((Component)this.jLabel2);
        this.jTextField1.setColumns(10);
        this.jPanel4.add((Component)this.jTextField1);
        this.jButton1.setText("搜索");
        this.jButton1.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                代码查询工具.this.jButton1ActionPerformed(evt);
            }
        });
        this.jPanel4.add((Component)this.jButton1);
        this.jTable1.setModel((TableModel)new DefaultTableModel(new Object[0][], new String[] { "类型", "名称或ID", "值" }) {
            Class[] types = { String.class, String.class, Integer.class };
            boolean[] canEdit = { false, true, true };
            
            @Override
            public Class getColumnClass(final int columnIndex) {
                return this.types[columnIndex];
            }
            
            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.jScrollPane1.setViewportView((Component)this.jTable1);
        this.jPanel4.add((Component)this.jScrollPane1);
        this.getContentPane().add((Component)this.jPanel4, (Object)new AbsoluteConstraints(0, 0, 460, 480));
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    private void jComboBox1ActionPerformed(final ActionEvent evt) {
    }
    
    private void jButton1ActionPerformed(final ActionEvent evt) {
        this.search();
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
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex3) {
            Logger.getLogger(代码查询工具.class.getName()).log(Level.SEVERE, null, (Throwable)ex3);
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
                new 代码查询工具().setVisible(true);
            }
        });
    }
}
