package main.java.manager ;

import main.java.bo.Chambre;
import main.java.bo.Client;
import main.java.bo.Reservation;
import com.mysql.jdbc.PreparedStatement;
import main.java.core.Database;
import main.java.core.Message;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import main.java.utils.Util;

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
        Reservation reservation ;
        String date, date_arrive, date_fin ;
        String sql = "SELECT * FROM Reservation "
                        + "LEFT JOIN Reserve ON Reserve.id_reservation = Reservation.id "
                        + "WHERE Reservation.id = ?";
        try
        {
            PreparedStatement ps = Database.getHinstance().prepare(sql) ;
            ResultSet rs = ps.executeQuery() ;
            if(rs.next())
            {
                /* Récupération des chambres */
                Vector<Integer> range = new Vector<>();
                while(rs.next()) {range.add(rs.getInt("id_chambre")) ;}
                ArrayList<Chambre> list_chambre = ChambreManager.getInstance().findInRange(range);
                rs.previous();
                /* Récupération des dates */
                date = rs.getString("date"); date_arrive = rs.getString("date_arrivee"); date_fin = rs.getString("date_fin");
                /* Récupération du client ayant réservé */
                Client client = (Client) FactoryManager.getInstance()
                        .getManager(FactoryManager.CLIENT_MANAGER)
                        .findById(rs.getInt("id_client")) ;
                /* Construction de l'objet réservation */
                reservation = new Reservation(id, client, list_chambre, 
                        Util.stringToCalendar(date), 
                        Util.stringToCalendar(date_arrive), 
                        Util.stringToCalendar(date_fin), 
                        rs.getBoolean("etat"));
                rs.close();
                return reservation ;
            }
            return null ;
        }catch(SQLException sqlex)
        {
            Message.error(sqlex.getMessage()+" !") ;
            sqlex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reservation> findByCriteria(String criteria, boolean strict)
    {
        return new ArrayList<>();
    }

    @Override
    public List<Reservation> findAll()
    {
        return new ArrayList<>();
    }
    
    @Override
    public int update(Reservation entity)
    {
        return -1;
    }
}