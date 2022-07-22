package gui;

import javax.swing.table.AbstractTableModel;
import javax.swing.SwingUtilities;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.Action;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class TableDemo extends JPanel
{
    private boolean DEBUG;
    
    public TableDemo() {
        super((LayoutManager)new GridLayout(1, 0));
        this.DEBUG = false;
        final JTable table = new JTable((TableModel)new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        final JScrollPane scrollPane = new JScrollPane((Component)table);
        this.add((Component)scrollPane);
        final Action action = new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final TableCellListener tcl = (TableCellListener)e.getSource();
                System.out.printf("cell changed%n", new Object[0]);
                System.out.println("Row  : " + tcl.getRow());
                System.out.println("Column: " + tcl.getColumn());
                System.out.println("Old  : " + tcl.getOldValue());
                System.out.println("New  : " + tcl.getNewValue());
            }
        };
        final TableCellListener tcl = new TableCellListener(table, action);
    }
    
    private static void createAndShowGUI() {
        final JFrame frame = new JFrame("TableDemo - www.jb51.net");
        frame.setDefaultCloseOperation(3);
        final TableDemo newContentPane = new TableDemo();
        newContentPane.setOpaque(true);
        frame.setContentPane((Container)newContentPane);
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(final String[] args) {
        SwingUtilities.invokeLater((Runnable)new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    class MyTableModel extends AbstractTableModel
    {
        private String[] columnNames;
        private Object[][] data;
        
        MyTableModel() {
            this.columnNames = new String[] { "First Name", "Last Name", "Sport", "# of Years", "Vegetarian" };
            this.data = new Object[][] { { "Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false) }, { "John", "Doe", "Rowing", new Integer(3), new Boolean(true) }, { "Sue", "Black", "Knitting", new Integer(2), new Boolean(false) }, { "Jane", "White", "Speed reading", new Integer(20), new Boolean(true) }, { "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) } };
        }
        
        @Override
        public int getColumnCount() {
            return this.columnNames.length;
        }
        
        @Override
        public int getRowCount() {
            return this.data.length;
        }
        
        @Override
        public String getColumnName(final int col) {
            return this.columnNames[col];
        }
        
        @Override
        public Object getValueAt(final int row, final int col) {
            return this.data[row][col];
        }
        
        @Override
        public Class getColumnClass(final int c) {
            return this.getValueAt(0, c).getClass();
        }
        
        @Override
        public boolean isCellEditable(final int row, final int col) {
            return col >= 2;
        }
        
        @Override
        public void setValueAt(final Object value, final int row, final int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col + " to " + value + " (an instance of " + (Object)value.getClass() + ")");
            }
            this.data[row][col] = value;
            this.fireTableCellUpdated(row, col);
            if (DEBUG) {
                System.out.println("New value of data:");
                this.printDebugData();
            }
        }
        
        private void printDebugData() {
            final int numRows = this.getRowCount();
            final int numCols = this.getColumnCount();
            for (int i = 0; i < numRows; ++i) {
                System.out.print("  row " + i + ":");
                for (int j = 0; j < numCols; ++j) {
                    System.out.print(" " + this.data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }
}
