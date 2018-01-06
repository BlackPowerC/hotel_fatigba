/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auth.userconnection;

import bo.Session;
import com.mysql.jdbc.PreparedStatement;
import core.Database;
import core.Message;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import utils.Util;

/**
 *
 * @author jordy
 */
public class SessionHandler 
{
    private boolean start ;
    private Session session ;
    private static SessionHandler p_singleton = null ;
    
    public static SessionHandler getInstance()
    {
        if(p_singleton == null)
        {
            p_singleton = new SessionHandler() ;
        }
        return p_singleton ;
    }
    
    private SessionHandler()
    {
        start = false ;
    }
    
    public boolean start()
    {
        if(this.session == null || !this.session.isValid())
        {
            return false ;
        }
        if(start)
        {
            return false ;
        }
        String sql = "INSERT INTO Session VALUES (NULL, ?, ?, ?, ?)" ;
        try
        {
            PreparedStatement ps = Database.getHinstance().prepare(sql) ;
            ps.setInt(1, session.getUser().getId()) ;
            ps.setString(2, Util.calendarToString(session.getDebut())) ;
            ps.setString(3, " ") ;
            ps.setLong(4, 0) ;
            
            if(ps.execute())
            {
                start = true ;
                Message.information("Démarrage de la session !");
                return start ; 
            }
        }catch(SQLException sqlex)
        {
            Message.warning("Impossible de démarrer une session !");
            Message.error(sqlex.getMessage()+" !") ;
            sqlex.printStackTrace(); 
        }
        return false ;
    }
    
    public int end()
    {
        if(this.session == null || !this.session.isValid())
        {
            return -1 ;
        }
        if(!start)
        {
            return - 1;
        }
        start = false ;
        Calendar c = Calendar.getInstance();
        this.session.setFin(new GregorianCalendar(
                c.get(Calendar.YEAR), c.get(Calendar.MONTH+1), c.get(Calendar.DAY_OF_MONTH),
                c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND))) ;
        this.session.setTime(this.session.getFin().getTimeInMillis() - this.session.getDebut().getTimeInMillis()) ;
        try
        {
            String sql = "UPDATE Session SET time=?, fin=? WHERE id=?" ;
            PreparedStatement ps = Database.getHinstance().prepare(sql) ;
            ps.setLong(1, session.getTime()) ;
            ps.setString(2, Util.calendarToString(session.getFin())) ;
            ps.setInt(3, this.session.getId()) ;
            Message.information("Fermeture de la session !");
            return ps.executeUpdate() ;
        }catch(SQLException sqlex)
        {
            Message.error(sqlex.getMessage()+" !") ;
            Message.warning("Votre session s'est mal terminée !");
            sqlex.printStackTrace(); 
        }
        return -1;
    }
    
    public Session getSession()
    {
        return session;
    }

    public void setSession(Session session)
    {
        this.session = session;
    }

    public boolean isStart()
    {
        return start;
    }

    public void setStart(boolean start)
    {
        this.start = start;
    }
}
