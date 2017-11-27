package userInterface.client;

import bo.Client;
import manager.data.ListClient;
import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;
import java.util.List;

public class JTableClientModel extends AbstractTableModel
{
    private List<Client> data = ListClient.getHinstance().getListClient();
    private String[] title;

    public JTableClientModel(String[] string)
    {
        title = string;
    }

    public JTableClientModel()
    {
        title = new String[]
        {
            "Nom", "Prenom", "Age", "Sexe", "Nationalité", "Type", "Fidélité"
        };
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

    public Client getValueAt(int x)
    {
        return data.get(x);
    }

    public void setValueAt(Client client, int x)
    {
        data.get(x).setClient(client);
    }

    @Override
    public Object getValueAt(int x, int y)
    {
        Client tmp = data.get(x);
        switch (y)
        {
            case 0:
                return tmp.getNom();
            case 1:
                return tmp.getPrenom();
            case 2:
                return tmp.getAge();
            case 3:
                return tmp.getSexe().getDescription();
            case 4:
                return tmp.getNation().getDescription();
            case 5:
                return tmp.getType().getDescription();
            case 6:
                return tmp.isFidelite() ;
        }
        return null;
    }

    @Override
    public String getColumnName(int index)
    {
        return title[index];
    }

    public void addRow()
    {
        //Client tmp = addReservation.getHinstance().getLastClient() ;
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    @Override
    public Class<?> getColumnClass(int col)
    {
        if (col == 6)
        {
            return Boolean.class;
        }

        return Object.class;
    }

    public void remove(int x)
    {
        fireTableRowsDeleted(x, x);
    }

    public void setData(List<Client> list)
    {
        data = new ArrayList<Client>(list);
    }
}
