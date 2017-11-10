package dataFromDatabase;

import manageClient.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.DataBaseCon;

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
        /* Connection à la base de données pour avoir les infos sur les clients */
        client = new Client();
        last_in_the_list = new Client();
        try
        {
            ResultSet rs = DataBaseCon.getHinstance().executeQuery("select * from Client order by id_client asc");
            while (rs.next())
            {
                client.setM_id_client(rs.getInt("id_client"));
                client.setM_nom(rs.getString("nom"));
                client.setM_type(rs.getString("type_client"));
                client.setM_prenom(rs.getString("prenom"));
                client.setM_membre(rs.getInt("membre"));
                client.setM_age(rs.getInt("age"));
                client.setM_nation(rs.getString("nationalite"));
                client.setM_sexe(rs.getString("sexe"));
                client.setM_has_fidelity_card(rs.getBoolean("card"));
                list.add(new Client(client));
                last_in_the_list = client;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
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
