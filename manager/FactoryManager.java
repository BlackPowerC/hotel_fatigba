package manager;

public class FactoryManager
{
    private static FactoryManager p_singleton = null ;
    
    public static final int CHAMBRE_MANAGER =1;
    public static final int CLIENT_MANAGER =2;
    public static final int CONSOMMATION_MANAGER =3;
    public static final int FACTURATION_MANAGER =4;
    public static final int RESERVATION_MANAGER =5;
    public static final int USER_MANAGER =6 ;
    public static final int SERVICE_MANAGER =7 ;
    
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
        if(classname.compareToIgnoreCase(ServiceManager.class.getName()) == 0)
        {
            return ServiceManager.getInstance() ;
        }
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
        if(classname.compareToIgnoreCase(ConsommationManager.class.getName()) == 0)
        {
            return ConsommationManager.getInstance() ;
        }
        return null ;
    }
    
    public Manager<?> getManager(int classeId)
    {
        switch(classeId)
        {
            case CLIENT_MANAGER:
                return ClientManager.getInstance() ;
            case CHAMBRE_MANAGER:
                return ChambreManager.getInstance() ;
            case FACTURATION_MANAGER:
                return FacturationManager.getInstance() ;
            case SERVICE_MANAGER:
                return ServiceManager.getInstance() ;
            case CONSOMMATION_MANAGER:
                return ConsommationManager.getInstance() ;    
            case RESERVATION_MANAGER:
                return ReservationManager.getInstance() ;
            case USER_MANAGER:
                return UserManager.getInstance() ;
        }
        return null ;
    }
}