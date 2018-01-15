package main.java.gui.facturation;

import main.java.bo.Facturation;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import main.java.manager.data.ListFacturation;

public class JTableFacturationModel extends AbstractTableModel
{

    private String[] title;
    private List<Facturation> listFacturation = ListFacturation.getHinstance().getList();

    public JTableFacturationModel()
    {
        title = new String[]
        {
            "Client",
            "Date",
            "Mode de paiement",
            "Total",
            "Réglé"
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
                return tmp.getClient().getNom()+" "+tmp.getClient().getPrenom();
            case 1:
                return tmp.getDate();
            case 2:
                return tmp.getPaiement().getDescription();
            case 3:
                return tmp.getTotal()+ " €";
            case 4:
                return tmp.isEtat() ;
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
