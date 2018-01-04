package manager.data;

import java.util.ArrayList;
import java.util.List;

import bo.Service;
import manager.FactoryManager;

public class ListService
{
    private List<Service> list = new ArrayList<Service>();
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
        return list;
    }

    private ListService()
    {
        list = (List<Service>) FactoryManager.getInstance().getManager(FactoryManager.SERVICE_MANAGER).findAll() ;
    }

}
