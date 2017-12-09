/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import bo.Client;
import bo.Consommation;
import bo.Service;
import com.mysql.jdbc.PreparedStatement;
import core.Database;
import core.Message;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jordy
 */
public class ConsommationManager extends Manager<Consommation>
{
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
            PreparedStatement ps =  (PreparedStatement) Database.getHinstance().getConnection().prepareStatement(sql) ;
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
        String sql = "";
        try
        {
            PreparedStatement ps = (PreparedStatement) Database.getHinstance().getConnection().prepareStatement(sql) ;
        }
        catch(SQLException sqlex)
        {
            Message.error("Impossible de supprimer la données dont l'id vaut: "+id);
            sqlex.printStackTrace(new PrintStream(System.err));
        }
        return null;
    }

    @Override
    public List<Consommation> findByCriteria(String criteria, boolean strict)
    {
        try
        {
            String sql="" ;
        }
        catch(SQLException sqlex)
        {
            
        }
    }

    @Override
    public List<Consommation> findAll()
    {
    
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
            PreparedStatement ps =  (PreparedStatement) Database.getHinstance().getConnection().prepareStatement(sql) ;
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
