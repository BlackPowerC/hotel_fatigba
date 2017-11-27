package manager.data;

import bo.Client;
import java.util.ArrayList;
import java.util.List;

public class ListClient
{
    private List<Client> list = new ArrayList<Client>();
    private Client client;
    private Client last_in_the_list;
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
        
    }

    public List<Client> getListClient()
    {
        return list;
    }

    public Client getLastClient()
    {
        return last_in_the_list;
    }

    public void setLastClient(Client client)
    {
        last_in_the_list.setClient(client);
    }
}
