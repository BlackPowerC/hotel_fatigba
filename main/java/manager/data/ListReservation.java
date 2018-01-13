package main.java.manager.data;

import main.java.bo.Reservation;
import java.util.List;
import main.java.manager.FactoryManager;
import main.java.manager.ReservationManager;

public class ListReservation
{
    private List<Reservation> list ;
    private static ListReservation singleton = null;

    public static ListReservation getHinstance()
    {
        if (singleton == null)
        {
            singleton = new ListReservation();
        }
        return singleton;
    }

    private ListReservation()
    {
        list = (List<Reservation>) FactoryManager.getInstance().getManager(ReservationManager.class.getName()).findAll() ;
    }

    public List<Reservation> getListReservation()
    {
        return list;
    }

    public Reservation getLastReservartion()
    {
        return list.get(list.size()-1) ;
    }

    public void setLastReservation(Reservation r)
    {
        list.get(list.size()-1);
    }
}
