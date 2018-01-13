package bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Reservation extends Entity
{
    protected Client client ;
    protected ArrayList<Chambre> chambres ;
    protected GregorianCalendar date ;
    protected GregorianCalendar date_arrivee ;
    protected GregorianCalendar date_fin ;
    protected boolean etat ;

    public Reservation(ResultSet rs, Client client, ArrayList<Chambre> chambres) throws SQLException
    {
        super();
        super.id = rs.getInt("id") ;
        this.client = client;
        this.chambres = chambres;
        this.etat = rs.getBoolean("etat") ;
    }

    public Reservation(int id, Client client, ArrayList<Chambre> chambres, GregorianCalendar date, GregorianCalendar date_arrivee, GregorianCalendar date_fin, boolean etat)
    {
        this.id = id ;
        this.client = client;
        this.chambres = chambres;
        this.date = date;
        this.date_arrivee = date_arrivee;
        this.date_fin = date_fin;
        this.etat = etat;
    }
    
    public Reservation(Reservation another)
    {
        this.id = another.id ;
        this.client = another.client;
        this.chambres = another.chambres;
        this.date = another.date;
        this.date_arrivee = another.date_arrivee;
        this.date_fin = another.date_fin;
        this.etat = another.etat;
    }
    
    public Reservation()
    {
        super();
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    public ArrayList<Chambre> getChambres()
    {
        return chambres;
    }

    public void setChambres(ArrayList<Chambre> chambres)
    {
        this.chambres = chambres;
    }

    public GregorianCalendar getDate()
    {
        return date;
    }

    public void setDate(GregorianCalendar date)
    {
        this.date = date;
    }

    public GregorianCalendar getDate_arrivee()
    {
        return date_arrivee;
    }

    public void setDate_arrivee(GregorianCalendar date_arrivee)
    {
        this.date_arrivee = date_arrivee;
    }

    public GregorianCalendar getDate_fin()
    {
        return date_fin;
    }

    public void setDate_fin(GregorianCalendar date_fin)
    {
        this.date_fin = date_fin;
    }

    public boolean isEtat()
    {
        return etat;
    }

    public void setEtat(boolean etat)
    {
        this.etat = etat;
    }
    
    public void setReservation(Reservation another)
    {
        this.id = another.id ;
        this.client = another.client;
        this.chambres = another.chambres;
        this.date = another.date;
        this.date_arrivee = another.date_arrivee;
        this.date_fin = another.date_fin;
        this.etat = another.etat;
    }
    
    @Override
    public boolean isValid()
    {
        /* Une réservation est valide si le client et les chambres sont valides et si date précède date_arrivee qui lui meme précède date_fin */
        for (Chambre chambre : chambres)
        {
            if(chambre.isValid()==false)
            {
                return false ;
            }
        }
        if(date.getTimeInMillis() > date_arrivee.getTimeInMillis() || date_fin.getTimeInMillis() < date_arrivee.getTimeInMillis())
        {
            return false ;
        }
        return client.isValid() ;
    }
    
}
