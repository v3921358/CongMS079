package gui.tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import database.DatabaseConnection;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import java.awt.Component;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.LayoutManager;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JFrame;

public class 游戏抽奖工具 extends JFrame
{
    private JLabel jLabel285;
    private JLabel jLabel336;
    private JScrollPane jScrollPane106;
    private JPanel 删除NPC;
    private JButton 删除游戏抽奖;
    private JTextField 删除游戏抽奖代码;
    private JButton 刷新游戏抽奖;
    private JTable 游戏抽奖;
    
    public 游戏抽奖工具() {
        final ImageIcon icon = new ImageIcon(this.getClass().getClassLoader().getResource("image/Icon.png"));
        this.setIconImage(icon.getImage());
        this.setTitle("游戏抽奖工具");
        this.initComponents();
    }
    
    private void initComponents() {
        this.删除NPC = new JPanel();
        this.jScrollPane106 = new JScrollPane();
        this.游戏抽奖 = new JTable();
        this.刷新游戏抽奖 = new JButton();
        this.删除游戏抽奖代码 = new JTextField();
        this.删除游戏抽奖 = new JButton();
        this.jLabel336 = new JLabel();
        this.jLabel285 = new JLabel();
        this.setResizable(false);
        this.getContentPane().setLayout((LayoutManager)new AbsoluteLayout());
        this.删除NPC.setBorder((Border)BorderFactory.createTitledBorder(null, "游戏抽奖管理工具", 0, 2, new Font("幼圆", 0, 24)));
        this.删除NPC.setLayout((LayoutManager)new AbsoluteLayout());
        this.游戏抽奖.setModel((TableModel)new DefaultTableModel(new Object[0][], new String[] { "物品代码", "名称" }) {
            boolean[] canEdit = { false, false };
            
            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.jScrollPane106.setViewportView((Component)this.游戏抽奖);
        this.删除NPC.add((Component)this.jScrollPane106, (Object)new AbsoluteConstraints(10, 30, 570, 420));
        this.刷新游戏抽奖.setFont(new Font("幼圆", 0, 15));
        this.刷新游戏抽奖.setText("刷新列表");
        this.刷新游戏抽奖.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                游戏抽奖工具.this.刷新游戏抽奖ActionPerformed(evt);
            }
        });
        this.删除NPC.add((Component)this.刷新游戏抽奖, (Object)new AbsoluteConstraints(330, 480, -1, -1));
        this.删除NPC.add((Component)this.删除游戏抽奖代码, (Object)new AbsoluteConstraints(150, 481, 80, 30));
        this.删除游戏抽奖.setFont(new Font("幼圆", 0, 15));
        this.删除游戏抽奖.setText("删除");
        this.删除游戏抽奖.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                游戏抽奖工具.this.删除游戏抽奖ActionPerformed(evt);
            }
        });
        this.删除NPC.add((Component)this.删除游戏抽奖, (Object)new AbsoluteConstraints(250, 480, -1, -1));
        this.jLabel336.setFont(new Font("幼圆", 0, 15));
        this.jLabel336.setText("物品代码:");
        this.删除NPC.add((Component)this.jLabel336, (Object)new AbsoluteConstraints(80, 480, -1, 30));
        this.jLabel285.setFont(new Font("幼圆", 0, 15));
        this.jLabel285.setForeground(new Color(255, 51, 51));
        this.jLabel285.setText("提示:删除GM用指令添加的物品道具");
        this.删除NPC.add((Component)this.jLabel285, (Object)new AbsoluteConstraints(120, 460, -1, 20));
        this.getContentPane().add((Component)this.删除NPC, (Object)new AbsoluteConstraints(0, 0, 590, 520));
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    private void 刷新游戏抽奖ActionPerformed(final ActionEvent evt) {
        this.刷新游戏抽奖();
    }
    
    private void 删除游戏抽奖ActionPerformed(final ActionEvent evt) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result1 = this.删除游戏抽奖代码.getText().matches("[0-9]+");
        if (result1) {
            if (Integer.parseInt(this.删除游戏抽奖代码.getText()) < 0) {
                JOptionPane.showMessageDialog(null, (Object)"请填写正确的值");
            }
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM gashapon_items WHERE itemid = ?");
                ps1.setInt(1, Integer.parseInt(this.删除游戏抽奖代码.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from gashapon_items where itemid =" + Integer.parseInt(this.删除游戏抽奖代码.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    JOptionPane.showMessageDialog(null, (Object)("成功删除 " + Integer.parseInt(this.删除游戏抽奖代码.getText()) + " 物品.重启生效。"));
                    this.刷新游戏抽奖();
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(游戏抽奖工具.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, (Object)"请输入数字 ");
        }
    }
    
    public void 刷新游戏抽奖() {
        for (int i = ((DefaultTableModel)(DefaultTableModel)this.游戏抽奖.getModel()).getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel)(DefaultTableModel)this.游戏抽奖.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM gashapon_items");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel)this.游戏抽奖.getModel()).insertRow(this.游戏抽奖.getRowCount(), new Object[] { Integer.valueOf(rs.getInt("itemid")), rs.getString("name") });
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(游戏抽奖工具.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
        }
    }
}
