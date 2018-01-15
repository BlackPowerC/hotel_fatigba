/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.manager;

import main.java.bo.Client;
import main.java.bo.Consommation;
import main.java.bo.Facturation;
import main.java.bo.ModePaiement;
import main.java.bo.Reservation;
import main.java.core.Database;
import main.java.core.Message;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.utils.Util;

/**
 *
 * @author jordy
 */
public class FacturationManager extends Manager<Facturation>
{
    private static FacturationManager p_singleton = null ;
    
    private FacturationManager()
    {
        
    }
    
    public static FacturationManager getInstance()
    {
        if(p_singleton == null)
        {
            p_singleton = new FacturationManager() ;
        }
        return p_singleton ;
    }
   
    
    @Override
    public boolean insert(Facturation entity)
    {
        if(!entity.isValid() || entity == null)
        {
            return false ;   
        }
        String sql = "INSERT INTO Reservation VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)" ;
        try
        {
            PreparedStatement ps = Database.getHinstance().prepare(sql) ;
            ps.setInt(1, entity.getClient().getId()) ;
            ps.setInt(2, entity.getConsommation().getId()) ;
            ps.setInt(3, entity.getReservation().getId()) ;
            ps.setString(4, entity.getDate().toString()) ;
            ps.setInt(5, entity.getPaiement().getId()) ;
            ps.setFloat(6, entity.getTotal()) ;
            ps.setBoolean(7, entity.isEtat()) ;
            return ps.execute();
        }
        catch(SQLException sqlex)
        {
            Message.error(sqlex.getMessage()+" !") ;
            sqlex.printStackTrace() ;
        }
        return false ;
    }

    @Override
    public void delete(int id)
    {
        try
        {
            String sql = "DELETE FROM Reservation WHERE id=?" ;
            PreparedStatement ps = Database.getHinstance().prepare(sql) ;
            ps.setInt(1, id) ;
            ps.execute() ;
        }catch(SQLException sqlex)
        {
            Message.error(sqlex.getMessage()+" !") ;
            sqlex.printStackTrace() ;
        }
    }

    @Override
    public Facturation findById(int id)
    {
        String sql = "SELECT * FROM Facturation f "
                + "LEFT JOIN Paiement p ON p.id = f.id_paiement"
                + "WHERE id = ?" ;
        try
        {
            PreparedStatement stmt = Database.getHinstance().prepare(sql) ;
            stmt.setInt(1, id) ;
            ResultSet rs = stmt.executeQuery() ;
            if(rs.next())
            {
                Facturation f = new Facturation();
                ModePaiement mp = new ModePaiement() ;
                f.setClient((Client) FactoryManager.getInstance()
                        .getManager(FactoryManager.FACTURATION_MANAGER)
                        .findById(rs.getInt("id_client")));
                f.setConsommation((Consommation) FactoryManager.getInstance()
                        .getManager(FactoryManager.CONSOMMATION_MANAGER)
                        .findById(rs.getInt("id_consommation")));
                f.setReservation((Reservation) FactoryManager.getInstance()
                        .getManager(FactoryManager.RESERVATION_MANAGER)
                        .findById(rs.getInt("id_Reservation")));
                f.setDate(Util.stringToCalendar(rs.getString("date")));
                f.setEtat(true);
                /* Le paiement */
                mp.setDescription(rs.getString("description"));
                mp.setId(rs.getInt("id_paiement"));
                f.setPaiement(mp);
                /* misc */
                f.setTotal(rs.getFloat("total"));
                f.setId(id);
                rs.close();
                return f ;
            }
            return null ;
        }catch(SQLException sqlex)
        {
            Message.error(sqlex.getMessage()+" !") ;
            sqlex.printStackTrace();
        }
        return null ;
    }

    @Override
    public List<Facturation> findByCriteria(String criteria, boolean strict)
    {
        return new ArrayList<Facturation>();
    }

    @Override
    public List<Facturation> findAll()
    {
        List<Facturation> list = new ArrayList<>() ;
        String sql = "SELECT * FROM Facturation f LEFT JOIN Paiement p ON p.id = f.id_paiement" ;
        try
        {
            PreparedStatement stmt = Database.getHinstance().prepare(sql) ;
            ResultSet rs = stmt.executeQuery() ;
            while(rs.next())
            {
                Facturation f = new Facturation();
                ModePaiement mp = new ModePaiement() ;
                f.setClient((Client) FactoryManager.getInstance()
                        .getManager(FactoryManager.FACTURATION_MANAGER)
                        .findById(rs.getInt("id_client")));
                f.setConsommation((Consommation) FactoryManager.getInstance()
                        .getManager(FactoryManager.CONSOMMATION_MANAGER)
                        .findById(rs.getInt("id_consommation")));
                f.setReservation((Reservation) FactoryManager.getInstance()
                        .getManager(FactoryManager.RESERVATION_MANAGER)
                        .findById(rs.getInt("id_Reservation")));
                f.setDate(Util.stringToCalendar(rs.getString("date")));
                f.setEtat(true);
                /* Le paiement */
                mp.setDescription(rs.getString("description"));
                mp.setId(rs.getInt("id_paiement"));
                f.setPaiement(mp);
                /* misc */
                f.setTotal(rs.getFloat("total"));
                f.setId(rs.getInt("id"));
                list.add(f);
            }
            rs.close();
            return list;
        }catch(SQLException sqlex)
        {
            Message.error(sqlex.getMessage()+" !") ;
            sqlex.printStackTrace();
        }
        return list ;
    }

    @Override
    public int update(Facturation entity)
    {
        if(!entity.isValid() || entity == null)
        {
            return -1 ;
        }
        try
        {
            String sql = "UPDATE Facturation "
                    + "SET id_client=?, "
                    + "id_consommation=?, "
                    + "id_reservation=?, "
                    + "date=?, "
                    + "id_paiement=?, "
                    + "total=?, "
                    + "etat=? "
                    + "WHERE id = ?" ;
            PreparedStatement ps = Database.getHinstance().prepare(sql );
            ps.setInt(1, entity.getClient().getId()) ;
            ps.setInt(2, entity.getConsommation().getId()) ;
            ps.setInt(3, entity.getReservation().getId()) ;
            ps.setString(4, entity.getDate().toString()) ;
            ps.setInt(5, entity.getPaiement().getId()) ;
            ps.setFloat(6, entity.getTotal()) ;
            ps.setBoolean(7, entity.isEtat()) ;
            return ps.executeUpdate() ;
        }catch(SQLException sqlex)
        {
            Message.error(sqlex.getMessage()+" !") ;
            sqlex.printStackTrace();
        }
        return -1 ;
    }
}
