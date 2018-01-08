package bo;

import java.util.GregorianCalendar;

public class Facturation extends Entity
{
    protected GregorianCalendar date ;
    protected Client client ;
    protected ModePaiement paiement ;
    protected Consommation consommation ;
    protected Reservation reservation ;
    protected float total ;
    protected boolean etat ;
    
    public Facturation()
    {
        
    }

    public Facturation(GregorianCalendar date, Client client, ModePaiement paiement, Consommation consommation, Reservation reservation, float total, boolean etat)
    {
        this.date = date;
        this.client = client;
        this.paiement = paiement;
        this.consommation = consommation;
        this.reservation = reservation;
        this.total = total;
        this.etat = etat;
    }
    
    public Facturation(Facturation f)
    {
        this.date = f.date ;
        this.client = f.client ;
        this.paiement = f.paiement ;
        this.consommation = f.consommation ;
        this.etat = f.etat ;
        this.total = f.total ;
    }

    public GregorianCalendar getDate()
    {
        return date;
    }

    public void setDate(GregorianCalendar date)
    {
        this.date = date;
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    public ModePaiement getPaiement()
    {
        return paiement;
    }

    public void setPaiement(ModePaiement paiement)
    {
        this.paiement = paiement;
    }
    
    public void setFacturation(Facturation f)
    {
        this.date = f.date ;
        this.client = f.client ;
        this.paiement = f.paiement ;
        this.consommation = f.consommation ;
        this.etat = f.etat ;
        this.total = f.total ;
    }

    public Consommation getConsommation()
    {
        return consommation;
    }

    public void setConsommation(Consommation consommation)
    {
        this.consommation = consommation;
    }

    public Reservation getReservation()
    {
        return reservation;
    }

    public void setReservation(Reservation reservation)
    {
        this.reservation = reservation;
    }

    public float getTotal()
    {
        return total;
    }

    public void setTotal(float total)
    {
        this.total = total;
    }

    public boolean isEtat()
    {
        return etat;
    }

    public void setEtat(boolean etat)
    {
        this.etat = etat;
    }
    
    @Override
    public boolean isValid()
    {
        return super.isValid() &&
                client.isValid() && 
                paiement.isValid() &&
                reservation.isValid() &&
                consommation.isValid() &&
                paiement.isValid() 
                && total > 0.0f ;
    }
}
