package main.java.manager.data;

import java.util.ArrayList;
import java.util.List;

import main.java.bo.Service;
import main.java.manager.FactoryManager;

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
