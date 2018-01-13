/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.Auth.userconnection.session;

import com.mysql.jdbc.PreparedStatement;
import main.java.core.Database;
import main.java.core.Message;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import main.java.utils.Util;

/**
 * @since  0.0.0
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
    
    /**
     * Cette fonction permet de demarrer une session.
     * Elle lance une exception si la session est déjà démarrée.
     * @throws SessionException 
     */
    public void start() throws SessionException
    {
        if(this.session == null  || !this.session.isValid() || start)
        {
            throw new SessionException("Impossible de démarrer une session !") ;
        }
        
        String sql = "INSERT INTO Session VALUES (NULL, ?, ?, ?, ?)" ;
        try
        {
            PreparedStatement ps = Database.getHinstance().prepare(sql) ;
            ps.setInt(4, session.getUser().getId()) ;
            ps.setString(1, Util.calendarToString(session.getDebut())) ;
            ps.setString(2, " ") ;
            ps.setLong(3, 0) ;
            
            if(ps.execute())
            {
                start = true ;
            }
        }catch(SQLException sqlex)
        {
            Message.warning("Impossible de démarrer une session !");
            Message.error(sqlex.getMessage()+" !") ;
            sqlex.printStackTrace(); 
        }
    }
    
    /**
     * Cette fonction permet de demarrer une session.
     * Elle lance une exception si la session est déjà arrêtée.
     * @throws SessionException 
     */
    public void end() throws SessionException
    {
        if(this.session == null  || !this.session.isValid() || start)
        {
            throw new SessionException("Impossible de fermer une session non démarrée !") ;
        }
        start = false ;
        Calendar c = Calendar.getInstance();
        this.session.setFin(new GregorianCalendar(
                c.get(Calendar.YEAR), c.get(Calendar.MONTH+1), c.get(Calendar.DAY_OF_MONTH),
                c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND))) ;
        this.session.setTime(this.session.getFin().getTimeInMillis() - this.session.getDebut().getTimeInMillis()) ;
        try
        {
            /* On récupère l'ID de la dernière session */
            ResultSet rs = Database.getHinstance().executeQuery("SELECT id FROM Session") ;
            rs.afterLast();
            if(rs.previous())
            {
                this.session.setId(rs.getInt("id"));
            }
            else
            {
                this.session.setId(1);
            }
            
            String sql = "UPDATE Session SET duree=?, fin=? WHERE id=?" ;
            PreparedStatement ps = Database.getHinstance().prepare(sql) ;
            ps.setLong(1, session.getTime()) ;
            ps.setString(2, Util.calendarToString(session.getFin())) ;
            ps.setInt(3, this.session.getId()) ;
            ps.executeUpdate() ;
        }catch(SQLException sqlex)
        {
            Message.error(sqlex.getMessage()+" !") ;
            Message.warning("Votre session s'est mal terminée !");
            sqlex.printStackTrace(); 
        }
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
