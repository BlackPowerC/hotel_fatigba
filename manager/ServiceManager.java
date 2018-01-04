/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import bo.Service;
import com.mysql.jdbc.PreparedStatement;
import core.Database;
import core.Message;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jordy
 */
public class ServiceManager extends Manager<Service>
{

    @Override
    public boolean insert(Service entity)
    {
    	if(entity == null || !entity.isValid())
    	{
            return false ;
    	}
        try
        {
            String sql = "INSERT INTO Service VALUES(NULL, ?, ?, ?)" ;
            PreparedStatement ps = Database.getHinstance().prepare(sql) ;
            ps.setFloat(1, entity.getPrix()) ;
            ps.setString(2, entity.getDescription());
            ps.setBoolean(3, true);
            return ps.execute() ;
            
        }catch(SQLException sqlex)
        {
            
        }
        return false ;
    }

    @Override
    public void delete(int id)
    {
        
    }

    @Override
    public Service findById(int id)
    {
        String sql = "SELECT * FROM Service WHERE id=?" ; 
        try
        {
            PreparedStatement ps = Database.getHinstance().prepare(sql) ;
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                Service s = new Service(rs);
                return s;
            }
            return null ;
        }catch(SQLException sqlex)
        {
            Message.error(sqlex.getMessage()+" !");
            sqlex.printStackTrace();
        }
        return null ;
    }

    @Override
    public List<Service> findByCriteria(String criteria, boolean strict)
    {
        List<Service> list_service = new ArrayList<Service>();
        String sql = (strict) ? "SELECT * FROM Service s WHERE s.description = ?"
                :"SELECT * FROM Service s WHERE s.description LIKE '?%'" ; 
        try
        {
            PreparedStatement ps = Database.getHinstance().prepare(sql) ;
            ps.setString(1, criteria);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                list_service.add(new Service(rs));
            }
            return list_service ;
        }catch(SQLException sqlex)
        {
            Message.error(sqlex.getMessage()+" !");
            sqlex.printStackTrace();
        }
        return null ;
    }

    @Override
    public List<Service> findAll()
    {
        List<Service> list_service = new ArrayList<Service>();
        String sql = "SELECT * FROM Service" ; 
        try
        {
            ResultSet rs = Database.getHinstance().executeQuery(sql) ;
            while(rs.next())
            {
                list_service.add(new Service(rs));
            }
            return list_service ;
        }catch(SQLException sqlex)
        {
            Message.error(sqlex.getMessage()+" !");
            sqlex.printStackTrace();
        }
        return null ;
    }

    @Override
    public int update(Service entity)
    {
    	if(entity == null || !entity.isValid())
    	{
    		return -1 ;
    	}
        String sql ="UPDATE Service SET prix=?, description=?, etat=? WHERE id=?" ;
        try
        {
            PreparedStatement ps = Database.getHinstance().prepare(sql) ;
            ps.setFloat(1, entity.getPrix()) ;
            ps.setString(2, entity.getDescription()) ;
            ps.setBoolean(3, false) ;
            ps.setInt(4, entity.getId()) ;
            
            return ps.executeUpdate() ;
        }
        catch(SQLException sqlex)
        {
            /* Affichafe d'un message d'erreur */
            Message.error(sqlex.getMessage()+" !");
            sqlex.printStackTrace();
        }
        return -1 ;
    }
    
}
