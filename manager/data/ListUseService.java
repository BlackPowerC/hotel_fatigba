package manager.data ;

import bo.Consommation;
import java.util.ArrayList ;
import java.util.List ;
import manager.ConsommationManager;
import manager.FactoryManager;

public class ListUseService
{
    private List<Consommation> list = new ArrayList<Consommation>();
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
        list = (List<Consommation>) FactoryManager.getInstance().getManager(ConsommationManager.class.getName()).findAll() ;
    }

    public List<Consommation> getList()
    {
        return list;
    }

    public Consommation getLast()
    {
        return list.get(list.size()-1) ;
    }

    public void setLast(Consommation ser)
    {
        
    }
}
