package manager ;

import bo.Client;
import com.mysql.jdbc.PreparedStatement;
import core.Database;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;

public class ClientManager extends Manager<Client>
{

    @Override
    public boolean insert(Client entity)
    {
        if(entity == null) {return false;}
        String sql = "INSERT INTO CLIENT VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?)" ;
        try
        {
            PreparedStatement ps = (PreparedStatement) Database.getHinstance().getConnection().prepareStatement(sql) ;
            ps.setString(1) ;
            ps.setString(2) ;
            ps.setDate(3) ;
            ps.setBoolean(4, true);
            ps.setInt(5);
            ps.setInt(6);
            ps.setInt(7);
        }catch(SQLException sqlex)
        {
            sqlex.printStackTrace(new PrintStream(System.err));
        }
        return true ;
    }

    @Override
    public void delete(int id)
    {
    
    }

    @Override
    public Client findById(int id)
    {
    
    }

    @Override
    public List<Client> findAll()
    {
    
    }

    @Override
    public int update(Client entity)
    {
    
    }

    @Override
    public List<Client> findByCriteria(String criteria)
    {
    }
    
}