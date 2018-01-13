package manager.data;

import bo.Nationalite;
import core.Database;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListNation
{
    private List<Nationalite> list = new ArrayList<Nationalite>();
    private static ListNation singleton = null;

    public static ListNation getHinstance()
    {
        if (singleton == null)
        {
            singleton = new ListNation();
        }
        return singleton;
    }

    private ListNation()
    {
        Nationalite nation ;
        try
        {
            ResultSet rs = Database.getHinstance().executeQuery("SELECT id, nom_fr_fr FROM Nation") ;
            while(rs.next())
            {
                nation = new Nationalite() ;
                nation.setId(rs.getInt("id"));
                nation.setDescription(rs.getString("nom_fr_fr"));
                list.add(nation) ;
            }
        }catch(SQLException sqlex)
        {
            sqlex.printStackTrace(new PrintStream(System.err));
        }
    }

    public List<Nationalite> getList()
    {
        return list;
    }
}
