package main.java.manager.data;

import main.java.bo.Client;
import java.util.ArrayList;
import java.util.List;
import main.java.manager.ClientManager;
import main.java.manager.FactoryManager;

public class ListClient
{
    private List<Client> list = new ArrayList<Client>();
    private static ListClient singleton = null;

    public static ListClient getHinstance()
    {
        if (singleton == null)
        {
            singleton = new ListClient();
        }
        return singleton;
    }

    private ListClient()
    {
        list = (List<Client>) FactoryManager.getInstance().getManager(ClientManager.class.getName()).findAll() ;
    }

    public List<Client> getListClient()
    {
        return list;
    }
    
    public void setListClient(List<Client> l)
    {
        list = l ;
    }
    
    public Client getLastClient()
    {
        return list.get(list.size()-1);
    }
}
