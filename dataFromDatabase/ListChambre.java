package dataFromDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

import app.DataBaseCon;
import manageChambre.Chambre;

public class ListChambre
{
    private List<Chambre> chambreTotal = new ArrayList<Chambre>(); //
    private List<Chambre> chambreDispo = new ArrayList<Chambre>();
    private Chambre chambre; //
    private Chambre last_room;
    private static ListChambre singleton = null; //

    public static ListChambre getHinstance()
    {
        if (singleton == null)
        {
            singleton = new ListChambre();
        }
        return singleton;
    }

    /* Le constructeur */
    private ListChambre()
    {
        chambre = new Chambre();
        last_room = new Chambre();
        ResultSet rs;
        try
        {
            DataBaseCon.getHinstance().executeQuery("select * from Chambre");
            rs = DataBaseCon.getHinstance().getResultset();
            while (rs.next())
            {
                chambre.setId_chambre(rs.getInt("id_chambre"));
                chambre.setType_chambre(rs.getString("type_chambre"));
                chambre.setPrix(rs.getFloat("prix"));
                chambre.setSituation(rs.getString("situation"));
                chambre.setState(rs.getBoolean("state"));
                last_room.setChambre(chambre);
                if (!rs.getBoolean("state"))
                {
                    chambreDispo.add(new Chambre(chambre));
                }
                chambreTotal.add(new Chambre(chambre));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<Chambre> getListChambreTotal()
    {
        return chambreTotal;
    }

    public List<Chambre> getListChambreDispo()
    {
        return chambreDispo;
    }

    public Chambre getLastRoom()
    {
        return last_room;
    }

    public void setLastRoom(Chambre ch)
    {
        last_room.setChambre(ch);
    }

    public void setListChambre(List<Chambre> ch)
    {
        chambreTotal = ch;
    }
}
