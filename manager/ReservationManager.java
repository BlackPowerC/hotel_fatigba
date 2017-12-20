package manager ;

import bo.Reservation;
import java.util.List;

public class ReservationManager extends Manager<Reservation>
{
    private static ReservationManager p_singleton = null ;
    
    private ReservationManager()
    {
        
    }
    
    public static ReservationManager getInstance()
    {
        if(p_singleton == null)
        {
            p_singleton = new ReservationManager() ;
        }
        return p_singleton ;
    }
    
    @Override
    public boolean insert(Reservation entity)
    {
        
    }

    @Override
    public void delete(int id)
    {
        
    }

    @Override
    public Reservation findById(int id)
    {
        
    }

    @Override
    public List<Reservation> findByCriteria(String criteria, boolean strict)
    {
        
    }

    @Override
    public List<Reservation> findAll()
    {
        
    }

    @Override
    public int update(Reservation entity)
    {
        
    }
    
}