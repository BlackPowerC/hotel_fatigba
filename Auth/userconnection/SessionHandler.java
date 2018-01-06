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
    private Session session ;
    private static SessionHandler p_singleton = null ;
    
    private static SessionHandler getInstance()
    {
        if(p_singleton == null)
        {
            p_singleton = new SessionHandler() ;
        }
        return p_singleton ;
    }

    public boolean start()
    {
        if(this.session == null || !this.session.isValid())
        {
            return false ;
        }
        
        String sql = "INSERT INTO Session VALUES (NULL, ?, ?, ? ?)" ;
        try
        {
            PreparedStatement ps = Database.getHinstance().prepare(sql) ;
            ps.setInt(1, session.getUser().getId()) ;
            ps.setString(2, Util.calendarToString(session.getDebut())) ;
            ps.setString(3, " ") ;
            ps.setLong(4, 0) ;
            
            return ps.execute() ;
        }catch(SQLException sqlex)
        {
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
            return ps.executeUpdate() ;
        }catch(SQLException sqlex)
        {
            Message.error(sqlex.getMessage()+" !") ;
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
}
