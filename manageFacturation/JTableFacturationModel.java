package manageFacturation;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dataFromDatabase.ListFacturation;

public class JTableFacturationModel extends AbstractTableModel
{

    private String[] title;
    private List<Facturation> listFacturation = ListFacturation.getHinstance().getList();

    public JTableFacturationModel()
    {
        title = new String[]
        {
            "Client",
            "Total à payer", "Total avec remise",
            "Mode de paiment"
        };
    }

    public JTableFacturationModel(String[] label)
    {
        title = label;
    }

    public int getColumnCount()
    {
        return title.length;
    }

    public int getRowCount()
    {
        return listFacturation.size();
    }

    public boolean isCellEditable(int x, int y)
    {
        return false;
    }

    public Facturation getValueAt(int row)
    {
        return new Facturation(listFacturation.get(row));
    }

    public void setValueAt(Facturation f, int x)
    {
        listFacturation.get(x).setFacturation(f);
    }

    public Object getValueAt(int row, int col)
    {
        Facturation tmp = listFacturation.get(row);
        switch (col)
        {
            case 0:
                return tmp.getNom_prenom() + " €";
            case 1:
                return tmp.getTotal() + " €";
            case 2:
                return tmp.getTotalRem() + " €";
            case 3:
                return tmp.getMode();
        }
        return null;
    }

    public String getColumnName(int index)
    {
        return title[index];
    }

    public void addRow()
    {
        fireTableRowsInserted(listFacturation.size() - 1, listFacturation.size() - 1);
    }

    public void remove(int x)
    {
        fireTableRowsDeleted(x, x);
    }

    public void setData(List<Facturation> list)
    {
        listFacturation = new ArrayList<Facturation>(list);
    }
}
