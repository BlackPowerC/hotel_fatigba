package manager ;

import bo.Chambre;
import bo.Reservation;
import com.mysql.jdbc.PreparedStatement;
import core.Database;
import core.Message;
import java.sql.Date;
import java.sql.SQLException;
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
        if(!entity.isValid() || entity == null)
        {
            return false ;
        }
        String sql= "INSERT INTO Reservation VALUES(NULL, ?, ?, ?, ?, ?, ?)" ;
        try
        {
            PreparedStatement ps = Database.getHinstance().prepare(sql);
            ps.setInt(1, entity.getClient().getId()) ;
            ps.setDate(3, (Date) entity.getDate().getTime());
            ps.setDate(4, (Date) entity.getDate_arrivee().getTime());
            ps.setDate(5, (Date) entity.getDate_fin().getTime());
            ps.setBoolean(6, false);
            for(Chambre ch : entity.getChambres())
            {
                ps.setInt(2, ch.getId());
                ps.execute() ;
            }
        }
        catch (SQLException sqlex)
        {
            Message.error("");
            sqlex.printStackTrace();
        }
        return true;
    }

    @Override
    public void delete(int id)
    {
        try
        {
            PreparedStatement ps = Database.getHinstance().prepare("DELETE FROM Reservation WHERE id = ?") ;
            ps.setInt(1, id) ;
            ps.execute();
        }catch(SQLException sqlex)
        {
            Message.error("");
            sqlex.printStackTrace();
        }
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