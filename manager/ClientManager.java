package manager ;

import bo.Client;
import bo.Nationalite;
import bo.Sexe;
import bo.TypeClient;
import com.mysql.jdbc.PreparedStatement;
import core.Database;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientManager extends Manager<Client>
{

    @Override
    public boolean insert(Client entity)
    {
        if(entity == null) 
        {
            return false;
        }
        
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
        String sql = "DELETE FROM Chambre WHERE id=?" ;
        try
        {
            PreparedStatement ps = (PreparedStatement) Database.getHinstance().getConnection().prepareStatement(sql) ;
            ps.setInt(1, id);
            ps.execute() ;
        } catch (SQLException ex)
        {
            /* Affichafe d'un message d'erreur */
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Client findById(int id)
    {
        Client cl ;
        Sexe s ;
        Nationalite n ;
        TypeClient tc  ;
        String sql ="SELECT c.id, c.nom, c.prenom, c.age, c.fidele, c.etranger, c.id_nation, c.id_type, c.id_sexe, n.nom_fr_fr, s.descripion as sDescription, tc.descripion as tcDescription "
                        + "FROM Client c, Nation n, Sexe s, TypeClient tc "
                        + "WHERE c.id_nation = n.id "
                        + "AND c.id_type = tc.id "
                        + "AND c.id_sexe = s.id AND c.id = ?" ;
        try
        {
            PreparedStatement ps = (PreparedStatement) Database.getHinstance().getConnection().prepareStatement(sql) ;
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery() ;
            if(rs.next())
            {
                s = new Sexe() ; s.setId(rs.getInt("id_sexe")); s.setDescription("sDescription") ;
                n = new Nationalite(); s.setId(rs.getInt("id_nation")); s.setDescription("nom_fr_fr") ;
                tc = new TypeClient(); s.setId(rs.getInt("id_type")); s.setDescription("tcDescription");
                cl = new Client(rs, s, tc, n, rs.getBoolean("fidele"), rs.getBoolean("etranger")) ;
                return cl ;
            }
        } catch (SQLException ex)
        {
            /* Affichafe d'un message d'erreur */
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
    }

    @Override
    public List<Client> findAll()
    {
        List<Client> all = new ArrayList<Client>() ;
        Client cl ;
        Sexe s ;
        Nationalite n ;
        TypeClient tc  ;
        String sql ="SELECT c.id, c.nom, c.prenom, c.age, c.fidele, c.etranger, c.id_nation, c.id_type, c.id_sexe, n.nom_fr_fr, s.descripion as sDescription, tc.descripion as tcDescription "
                        + "FROM Client c, Nation n, Sexe s, TypeClient tc "
                        + "WHERE c.id_nation = n.id "
                        + "AND c.id_type = tc.id "
                        + "AND c.id_sexe = s.id AND" ;
        try
        {
            PreparedStatement ps = (PreparedStatement) Database.getHinstance().getConnection().prepareStatement(sql) ;
            ResultSet rs = ps.executeQuery() ;
            while(rs.next())
            {
                s = new Sexe() ; s.setId(rs.getInt("id_sexe")); s.setDescription("sDescription") ;
                n = new Nationalite(); s.setId(rs.getInt("id_nation")); s.setDescription("nom_fr_fr") ;
                tc = new TypeClient(); s.setId(rs.getInt("id_type")); s.setDescription("tcDescription");
                cl = new Client(rs, s, tc, n, rs.getBoolean("fidele"), rs.getBoolean("etranger")) ;
                all.add(cl) ;
            }
            return all;
        } catch (SQLException ex)
        {
            /* Affichafe d'un message d'erreur */
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
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