package manager;

public class FactoryManager
{
    private static FactoryManager p_singleton = null ;
    
    public static int CHAMBRE_MANAGER =1;
    public static int CLIENT_MANAGER =2;
    public static int CONSOMMATION_MANAGER =3;
    public static int FACTURATION_MANAGER =4;
    public static int RESERVARION_MANAGER =5;
    public static int USER_MANAGER =6 ;
    
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