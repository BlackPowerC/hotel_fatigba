package main.java.gui.consommation;

import main.java.bo.Consommation;
import javax.swing.table.AbstractTableModel;

import main.java.manager.data.ListUseService;

import java.util.List;

public class JTableUserServiceModel extends AbstractTableModel
{
    private List<Consommation> data = ListUseService.getHinstance().getList();
    private String label[];

    public JTableUserServiceModel()
    {
        label = new String[]
        {
            "Client", "Service", "Prix"
        };
    }

    public JTableUserServiceModel(String[] label)
    {
        this.label = label;
    }

    public int getRowCount()
    {
        return data.size();
    }

    public String getColumnName(int col)
    {
        return label[col];
    }

    public Consommation getValueAt(int row)
    {
        return data.get(row) ;
    }

    public Object getValueAt(int row, int col)
    {
        Consommation tmp = data.get(row);
        switch (col)
        {
            case 0:
                return tmp.getClient().getNom();
            case 1:
                return tmp.getService().getDescription();
            case 2:
                return tmp.getService().getPrix()+ " €";
        }
        return null;
    }

    public void setValueAt(Consommation ser, int index)
    {
        try
        {
            ListUseService.getHinstance().getList().get(index) ;
        } catch (ArrayIndexOutOfBoundsException ex)
        {
            ex.printStackTrace();
        }
    }

    public int getColumnCount()
    {
        return label.length;
    }

    public void addRow()
    {
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void remove(int row)
    {
        fireTableRowsDeleted(row, row);
    }
}
