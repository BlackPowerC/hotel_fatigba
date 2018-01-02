package app;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class ComboEditor extends DefaultCellEditor
{
    private JComboBox box;

    public ComboEditor(JComboBox box)
    {
        super(box);
        this.box = box;
    }

    public Component getTableCellEditorComponent(JTable table, boolean is, int row, int col)
    {
        return this.box;
    }
}
