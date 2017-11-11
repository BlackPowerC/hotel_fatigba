package dataFromDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.DataBaseCon;
import manageService.UseService;

public class ListUseService
{
    private List<UseService> listUserService = new ArrayList<UseService>();
    private UseService us;
    private UseService last_us;
    private static ListUseService singleton = null;

    public static ListUseService getHinstance()
    {
        if (singleton == null)
        {
            singleton = new ListUseService();
        }
        return singleton;
    }

    public ListUseService()
    {
        us = new UseService();
        last_us = new UseService();
        String req = "select u.state, u.id_cl, u.id_us, s.id_ser, c.id_client, description, prix, nom, prenom "
                + "FROM Service s, UseService u, Client c "
                + "WHERE s.id_ser = u.id_ser and c.id_client = u.id_cl "
                + "ORDER BY u.id_us ASC";
        try
        {
            ResultSet rs = DataBaseCon.getHinstance().executeQuery(req);
            while (rs.next())
            {
                us.setId_cl(rs.getInt("id_cl"));
                us.setId_ser(rs.getInt("id_ser"));
                us.setId_us(rs.getInt("id_us"));
                us.setNom_prenom(rs.getString("nom") + " " + rs.getString("prenom"));
                us.setPrix(rs.getFloat("prix"));
                us.setDesc_service(rs.getString("description"));
                us.setState(rs.getBoolean("state"));
                System.out.println("Id_user: " + us.getId_us());
                listUserService.add(new UseService(us));
                last_us = us;
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public List<UseService> getList()
    {
        return listUserService;
    }

    public UseService getLast()
    {
        return last_us;
    }

    public void setLast(UseService ser)
    {
        last_us.setUseService(ser);
    }
}
