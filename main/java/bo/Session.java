/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author jordy
 */
public class Session extends Entity
{
    private Utilisateur user ;
    private GregorianCalendar debut ;
    private GregorianCalendar fin ;
    private long time ;

    public Session(Utilisateur user)
    {
        Calendar c = Calendar.getInstance();
        this.user = user ;
        this.debut = new GregorianCalendar(
                c.get(Calendar.YEAR), c.get(Calendar.MONTH+1), c.get(Calendar.DAY_OF_MONTH),
                c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND)
        ) ;
        this.time = 0L;
        this.fin = null ;
    }

    public GregorianCalendar getDebut()
    {
        return debut;
    }

    public void setDebut(GregorianCalendar debut)
    {
        this.debut = debut;
    }

    public GregorianCalendar getFin()
    {
        return fin;
    }

    public void setFin(GregorianCalendar fin)
    {
        this.fin = fin;
    }

    public long getTime()
    {
        return time;
    }

    public void setTime(long time)
    {
        this.time = time;
    }
    
    
    
    public Utilisateur getUser()
    {
        return user;
    }

    public void setUser(Utilisateur user)
    {
        this.user = user;
    }

    @Override
    public boolean isValid()
    {
        return this.user.isValid() ;
    }
}
