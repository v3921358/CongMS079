package gui;

import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;
import java.beans.PropertyChangeEvent;
import javax.swing.Action;
import javax.swing.JTable;
import java.beans.PropertyChangeListener;

public class TableCellListener implements PropertyChangeListener, Runnable
{
    private JTable table;
    private Action action;
    private int row;
    private int column;
    private Object oldValue;
    private Object newValue;
    
    public TableCellListener(final JTable table, final Action action) {
        this.table = table;
        this.action = action;
        this.table.addPropertyChangeListener((PropertyChangeListener)this);
    }
    
    private TableCellListener(final JTable table, final int row, final int column, final Object oldValue, final Object newValue) {
        this.table = table;
        this.row = row;
        this.column = column;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
    
    public int getColumn() {
        return this.column;
    }
    
    public Object getNewValue() {
        return this.newValue;
    }
    
    public Object getOldValue() {
        return this.oldValue;
    }
    
    public int getRow() {
        return this.row;
    }
    
    public JTable getTable() {
        return this.table;
    }
    
    @Override
    public void propertyChange(final PropertyChangeEvent e) {
        if ("tableCellEditor".equals((Object)e.getPropertyName())) {
            if (this.table.isEditing()) {
                this.processEditingStarted();
            }
            else {
                this.processEditingStopped();
            }
        }
    }
    
    private void processEditingStarted() {
        SwingUtilities.invokeLater((Runnable)this);
    }
    
    @Override
    public void run() {
        this.row = this.table.convertRowIndexToModel(this.table.getEditingRow());
        this.column = this.table.convertColumnIndexToModel(this.table.getEditingColumn());
        this.oldValue = this.table.getModel().getValueAt(this.row, this.column);
        if (this.oldValue == null) {
            this.oldValue = "";
        }
        this.newValue = null;
    }
    
    private void processEditingStopped() {
        this.newValue = this.table.getModel().getValueAt(this.row, this.column);
        if (this.newValue == null) {
            this.newValue = "";
        }
        if (!this.newValue.equals(this.oldValue)) {
            final TableCellListener tcl = new TableCellListener(this.getTable(), this.getRow(), this.getColumn(), this.getOldValue(), this.getNewValue());
            final ActionEvent event = new ActionEvent((Object)tcl, 1001, "");
            this.action.actionPerformed(event);
        }
    }
}
