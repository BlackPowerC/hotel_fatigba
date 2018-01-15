package main.java.gui.chambre;

import main.java.bo.Chambre;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public abstract class JTableChambreModel extends AbstractTableModel
{
    protected String[] title;
    protected List<Chambre> data = null;

    public JTableChambreModel(String label[])
    {
        title = label;
    }

    public JTableChambreModel()
    {
        title = new String[] { "N° Chambre", "Type", "Sistuation", "Prix" };
    }

    @Override
    public int getColumnCount()
    {
        return this.title.length;
    }

    @Override
    public int getRowCount()
    {
        return this.data.size();
    }

    @Override
    public boolean isCellEditable(int x, int y)
    {
        return false;
    }

    public Chambre getValueAt(int x)
    {
        Chambre tmp = new Chambre(data.get(x));
        return tmp ;
    }

    public void setValueAt(Chambre chambre, int x)
    {
        data.get(x).setChambre(chambre);
    }

    @Override
    public abstract Object getValueAt(int x, int y);

    @Override
    public String getColumnName(int index)
    {
        return title[index];
    }

    public void addRow(Chambre tmp)
    {
        fireTableRowsInserted(tmp.getId() - 1, tmp.getId() - 1);
    }

    public void addRow()
    {
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void remove(int x)
    {
        fireTableRowsDeleted(x, x);
    }

    public void setData(List<Chambre> list)
    {
        data = list;
    }
}
