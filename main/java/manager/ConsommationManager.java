/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.manager;

import main.java.bo.Client;
import main.java.bo.Consommation;
import main.java.bo.Service;
import com.mysql.jdbc.PreparedStatement;
import main.java.core.Database;
import main.java.core.Message;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jordy
 */
public class ConsommationManager extends Manager<Consommation>
{
    private static ConsommationManager p_singleton = null ;
    
    private ConsommationManager()
    {
        
    }
    
    public static ConsommationManager getInstance()
    {
        if(p_singleton == null)
        {
            p_singleton = new ConsommationManager() ;
        }
        return p_singleton ;
    }
    
    @Override
    public boolean insert(Consommation entity)
    {
        if(entity == null || !entity.isValid())
        {
            return false ;
        }
        
        String sql = "INSERT INTO Consommation VALUES(NULL, ? , ?)" ;
        try
        {
            PreparedStatement ps = (PreparedStatement) Database.getHinstance().prepare(sql) ;
            ps.setInt(1, entity.getClient().getId()) ;
            ps.setInt(2, entity.getService().getId());
            return ps.execute() ;
        }
        catch(SQLException sqlex)
        {
            Message.error("") ;
            sqlex.printStackTrace();
        }
        return false ;
    }

    @Override
    public void delete(int id)
    {
        String sql="DELETE FROM Consommation WHERE id=?";
        try
        {
            PreparedStatement ps = Database.getHinstance().prepare(sql) ;
            ps.setInt(1, id) ;
            ps.execute() ;
        }
        catch(SQLException sqlex)
        {
            Message.error("Impossible de supprimer la données dont l'id vaut: "+id);
            sqlex.printStackTrace(new PrintStream(System.err));
        }
    }

    @Override
    public Consommation findById(int id)
    {
        Consommation c ;
        Service s ;
        Client cl ;
        String sql = "SELECT * FROM Consommation WHERE id=?" ;
        try
        {
            PreparedStatement ps = Database.getHinstance().prepare(sql) ;
            ps.setInt(1, id) ;
            ResultSet rs = ps.executeQuery() ;
            if(rs.next())
            {
                cl = (Client) FactoryManager.getInstance()
                        .getManager(FactoryManager.CLIENT_MANAGER)
                        .findById(rs.getInt("id_client")) ;
                s = (Service) FactoryManager.getInstance()
                        .getManager(FactoryManager.SERVICE_MANAGER)
                        .findById(rs.getInt("id_service"));
                c = new Consommation(id, s, cl) ;
                return c ;
            }
            
            return null ;
        }
        catch(SQLException sqlex)
        {
            Message.error(sqlex.getMessage()+ " !");
            sqlex.printStackTrace(new PrintStream(System.err));
        }
        return null;
    }

    @Override
    public List<Consommation> findByCriteria(String criteria, boolean strict)
    {
        return new ArrayList<Consommation>();
    }

    @Override
    public List<Consommation> findAll()
    {
        List<Consommation> list = new ArrayList<>() ;
        Consommation c ;
        Service s ;
        Client cl ;
        String sql = "SELECT * FROM Consommation" ;
        try
        {
            ResultSet rs = Database.getHinstance().executeQuery(sql) ;
            while(rs.next())
            {
                list.add(new Consommation(rs ,
                    (Service) FactoryManager.getInstance().getManager(FactoryManager.SERVICE_MANAGER).findById(rs.getInt("id_service")),
                    (Client) FactoryManager.getInstance().getManager(FactoryManager.CLIENT_MANAGER).findById(rs.getInt("id_client"))
                ));
            }
            return list ;
        }
        catch(SQLException sqlex)
        {
            Message.error(sqlex.getMessage()+ " !");
            sqlex.printStackTrace(new PrintStream(System.err));
        }
        return null;
    }

    @Override
    public int update(Consommation entity)
    {
        if(entity == null || !entity.isValid())
        {
            return -1 ;
        }
        String sql="UPDATE Consommation SET id_client=?, id_service=? WHERE id=?";
        try
        {
            PreparedStatement ps = Database.getHinstance().prepare(sql) ;
            ps.setInt(1, entity.getClient().getId()) ;
            ps.setInt(1, entity.getService().getId()) ;
            ps.setInt(1, entity.getId()) ;
            return ps.executeUpdate();
        }
        catch(SQLException sqlex)
        {
            //Message.error("Impossible de mettre à jour la données dont l'id vaut: "+id);
            sqlex.printStackTrace(new PrintStream(System.err));
        }
        return -1 ;
    }
    
}
