package app;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableComponent extends DefaultTableCellRenderer
{
    private static final long serialVersionUID = 1L;
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelect, boolean focus, int row, int column)
    {
        if (value instanceof JButton)
        {
            return (JButton) value;
        }
        else if (value instanceof JComboBox<?>)
        {
            return (JComboBox<?>) value;
        }
        else if (value instanceof Boolean)
        {
            return (JCheckBox) value;
        }
        else
        {
            return this;
        }
    }
}
