package manager.data;

import java.util.ArrayList;
import java.util.List;

import bo.Facturation;

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
        
    }
}
