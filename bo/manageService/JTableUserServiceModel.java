package manageService;

import javax.swing.table.AbstractTableModel;

import dataFromDatabase.ListUseService;

import java.util.List;

public class JTableUserServiceModel extends AbstractTableModel
{

    private List<UseService> data = ListUseService.getHinstance().getList();
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

    public UseService getValueAt(int row)
    {
        UseService tmp = new UseService(data.get(row));
        return tmp;
    }

    public Object getValueAt(int row, int col)
    {
        UseService tmp = data.get(row);
        switch (col)
        {
            case 0:
                return tmp.getNom_prenom();
            case 1:
                return tmp.getDesc_service();
            case 2:
                return tmp.getPrix() + " €";
        }
        return null;
    }

    public void setValueAt(UseService ser, int index)
    {
        try
        {
            ListUseService.getHinstance().getList().get(index).setUseService(ser);
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
