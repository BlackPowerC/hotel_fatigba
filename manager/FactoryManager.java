package manager;

public class FactoryManager
{
    private static FactoryManager p_singleton = null ;
    
    private FactoryManager()
    {
        
    }
    
    public static FactoryManager getInstance()
    {
        if(p_singleton == null)
        {
            p_singleton = new FactoryManager() ;
        }
        return p_singleton ;
    }
    
    public Manager<?> getManager(String classname)
    {
        if(classname.compareToIgnoreCase(ClientManager.class.getName()) == 0)
        {
            return ClientManager.getInstance() ;
        }
        if(classname.compareToIgnoreCase(ChambreManager.class.getName()) == 0)
        {
            return ChambreManager.getInstance() ;
        }
        if(classname.compareToIgnoreCase(FacturationManager.class.getName()) == 0)
        {
            return FacturationManager.getInstance() ;
        }
        if(classname.compareToIgnoreCase(ReservationManager.class.getName()) == 0)
        {
            return ReservationManager.getInstance() ;
        }
        if(classname.compareToIgnoreCase(UserManager.class.getName()) == 0)
        {
            return UserManager.getInstance() ;
        }
        return null ;
    }
}