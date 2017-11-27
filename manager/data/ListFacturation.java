package manager.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.DataBaseCon;
import manageFacturation.Facturation;

public class ListFacturation
{

    private List<Facturation> data = new ArrayList<Facturation>();
    private Facturation facturation;
    private Facturation last;

    private static ListFacturation singleton = null;

    public static ListFacturation getHinstance()
    {
        if (singleton == null)
        {
            singleton = new ListFacturation();
        }
        return singleton;
    }

    public List<Facturation> getList()
    {
        return data;
    }

    public Facturation getLast()
    {
        return last;
    }

    public void setLast(Facturation f)
    {
        last.setFacturation(f);
    }

    private ListFacturation()
    {
        facturation = new Facturation();
        last = new Facturation();
        String req = "select c.id_client, c.nom, c.prenom, f.id_fa, f.id_cl, f.total, f.totalRem, f.totalSer, f.totalRes, f.mode "
                + "from Facturation f, Client c "
                + "where c.id_client = f.id_cl";
        try
        {
            DataBaseCon.getHinstance().executeQuery(req);
            ResultSet rs = DataBaseCon.getHinstance().getResultset();
            while (rs.next())
            {
                facturation.setId_cl(rs.getInt("id_cl"));
                facturation.setId_fa(rs.getInt("id_fa"));
                facturation.setMode(rs.getString("mode"));
                facturation.setNom_prenom(rs.getString("nom") + " " + rs.getString("prenom"));
                facturation.setTotal(rs.getFloat("total"));
                facturation.setTotalRem(rs.getFloat("totalRem"));
                facturation.setTotalSer(rs.getFloat("totalSer"));
                facturation.setTotalRes(rs.getFloat("totalRes"));
                last.setFacturation(facturation);
                data.add(new Facturation(facturation));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
