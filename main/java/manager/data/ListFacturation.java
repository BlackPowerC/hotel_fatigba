package main.java.manager.data;

import java.util.ArrayList;
import java.util.List;

import main.java.bo.Facturation;

public class ListFacturation
{
    private List<Facturation> list = new ArrayList<Facturation>();

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
        return list;
    }

    public Facturation getLast()
    {
        return list.get(list.size()-1);
    }

    public void setLast(Facturation f)
    {
       // list.get(list.size()-1).setFacturation(f);
    }

    private ListFacturation()
    {
        
    }
}
