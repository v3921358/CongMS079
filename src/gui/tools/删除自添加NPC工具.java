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

public class 删除自添加NPC工具 extends JFrame
{
    private JLabel jLabel285;
    private JLabel jLabel336;
    private JScrollPane jScrollPane106;
    private JPanel 删除NPC;
    private JButton 删除自添加npc;
    private JTextField 删除自添加npc代码;
    private JButton 刷新自添加NPC;
    private JTable 自添加NPC;
    
    public 删除自添加NPC工具() {
        final ImageIcon icon = new ImageIcon(this.getClass().getClassLoader().getResource("image/Icon.png"));
        this.setIconImage(icon.getImage());
        this.setTitle("删除自添加NPC工具");
        this.initComponents();
    }
    
    private void initComponents() {
        this.删除NPC = new JPanel();
        this.jScrollPane106 = new JScrollPane();
        this.自添加NPC = new JTable();
        this.刷新自添加NPC = new JButton();
        this.删除自添加npc代码 = new JTextField();
        this.删除自添加npc = new JButton();
        this.jLabel336 = new JLabel();
        this.jLabel285 = new JLabel();
        this.setResizable(false);
        this.getContentPane().setLayout((LayoutManager)new AbsoluteLayout());
        this.删除NPC.setBorder((Border)BorderFactory.createTitledBorder(null, "删除自添加NPC", 0, 2, new Font("幼圆", 0, 24)));
        this.删除NPC.setLayout((LayoutManager)new AbsoluteLayout());
        this.自添加NPC.setModel((TableModel)new DefaultTableModel(new Object[0][], new String[] { "地图代码", "NPC代码" }) {
            boolean[] canEdit = { false, false };
            
            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.jScrollPane106.setViewportView((Component)this.自添加NPC);
        this.删除NPC.add((Component)this.jScrollPane106, (Object)new AbsoluteConstraints(10, 30, 570, 420));
        this.刷新自添加NPC.setFont(new Font("幼圆", 0, 15));
        this.刷新自添加NPC.setText("刷新列表");
        this.刷新自添加NPC.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                删除自添加NPC工具.this.刷新自添加NPCActionPerformed(evt);
            }
        });
        this.删除NPC.add((Component)this.刷新自添加NPC, (Object)new AbsoluteConstraints(320, 480, -1, -1));
        this.删除NPC.add((Component)this.删除自添加npc代码, (Object)new AbsoluteConstraints(150, 490, 80, -1));
        this.删除自添加npc.setFont(new Font("幼圆", 0, 15));
        this.删除自添加npc.setText("删除");
        this.删除自添加npc.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                删除自添加NPC工具.this.删除自添加npcActionPerformed(evt);
            }
        });
        this.删除NPC.add((Component)this.删除自添加npc, (Object)new AbsoluteConstraints(250, 480, -1, -1));
        this.jLabel336.setFont(new Font("幼圆", 0, 15));
        this.jLabel336.setText("NPC代码:");
        this.删除NPC.add((Component)this.jLabel336, (Object)new AbsoluteConstraints(80, 490, -1, 20));
        this.jLabel285.setFont(new Font("幼圆", 0, 15));
        this.jLabel285.setForeground(new Color(255, 51, 51));
        this.jLabel285.setText("提示:删除GM用指令添加的NPC");
        this.删除NPC.add((Component)this.jLabel285, (Object)new AbsoluteConstraints(150, 460, -1, 20));
        this.getContentPane().add((Component)this.删除NPC, (Object)new AbsoluteConstraints(0, 0, 590, 520));
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    private void 刷新自添加NPCActionPerformed(final ActionEvent evt) {
        this.刷新自添加NPC();
    }
    
    private void 删除自添加npcActionPerformed(final ActionEvent evt) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        final boolean result1 = this.删除自添加npc代码.getText().matches("[0-9]+");
        if (result1) {
            if (Integer.parseInt(this.删除自添加npc代码.getText()) < 0) {
                JOptionPane.showMessageDialog(null, (Object)"请填写正确的值");
            }
            try {
                ps1 = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM wz_customlife WHERE dataid = ?");
                ps1.setInt(1, Integer.parseInt(this.删除自添加npc代码.getText()));
                rs = ps1.executeQuery();
                if (rs.next()) {
                    final String sqlstr = " delete from wz_customlife where dataid =" + Integer.parseInt(this.删除自添加npc代码.getText()) + "";
                    ps1.executeUpdate(sqlstr);
                    JOptionPane.showMessageDialog(null, (Object)("成功删除 " + Integer.parseInt(this.删除自添加npc代码.getText()) + " npc.重启生效。"));
                    this.刷新自添加NPC();
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(删除自添加NPC工具.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, (Object)"请输入数字 ");
        }
    }
    
    public void 刷新自添加NPC() {
        for (int i = ((DefaultTableModel)(DefaultTableModel)this.自添加NPC.getModel()).getRowCount() - 1; i >= 0; --i) {
            ((DefaultTableModel)(DefaultTableModel)this.自添加NPC.getModel()).removeRow(i);
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            ps = con.prepareStatement("SELECT * FROM wz_customlife");
            rs = ps.executeQuery();
            while (rs.next()) {
                ((DefaultTableModel)this.自添加NPC.getModel()).insertRow(this.自添加NPC.getRowCount(), new Object[] { Integer.valueOf(rs.getInt("mid")), rs.getString("dataid") });
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(删除自添加NPC工具.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
        }
    }
}
