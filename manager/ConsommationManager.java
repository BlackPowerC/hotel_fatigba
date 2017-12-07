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
    
    }

    @Override
    public List<Consommation> findAll()
    {
    
    }

    @Override
    public int update(Consommation entity)
    {
    
    }
    
}
