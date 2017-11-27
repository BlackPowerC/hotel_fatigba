package manager.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.DataBaseCon;
import manageService.Service;

public class ListService
{

    private Service service;
    private List<Service> listService = new ArrayList<Service>();
    private static ListService singleton = null;

    public static ListService getHinstance()
    {
        if (singleton == null)
        {
            singleton = new ListService();
        }
        return singleton;
    }

    public List<Service> getList()
    {
        return listService;
    }

    private ListService()
    {
        String req = "select * from Service";
        try
        {
            service = new Service();
            ResultSet rs = DataBaseCon.getHinstance().executeQuery(req);
            while (rs.next())
            {
                service.setId_ser(rs.getInt(1));
                service.setDescription(rs.getString(2));
                service.setPrix(rs.getFloat(3));
                listService.add(new Service(service));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

}
