package main.java.gui.consommation;

import main.java.bo.Service;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import main.java.manager.data.ListService;

public class JTableServiceModel extends AbstractTableModel
{

    private List<Service> data = ListService.getHinstance().getList();
    private String[] title;

    public JTableServiceModel()
    {
        title = new String[]
        {
            "Service", "Description", "Prix"
        };
    }

    public JTableServiceModel(String[] label)
    {
        title = label;
    }

    public String getColumnName(int col)
    {
        return title[col];
    }

    public int getRowCount()
    {
        return data.size();
    }

    public int getColumnCount()
    {
        return title.length;
    }

    public boolean isCellEditable(int row, int col)
    {
        return false;
    }

    public Service getValueAt(int row)
    {
        Service m_new = new Service(data.get(row));
        return m_new;
    }

    public Object getValueAt(int row, int col)
    {
        Service tmp = data.get(row);
        switch (col)
        {
            case 0:
                return tmp.getId();
            case 1:
                return tmp.getDescription();
            case 2:
                return tmp.getPrix() + " €";
        }
        return null;
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
