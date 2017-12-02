package manager ;

import bo.Client;
import bo.Nationalite;
import bo.Sexe;
import bo.TypeClient;
import com.mysql.jdbc.PreparedStatement;
import core.Database;
import java.io.PrintStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientManager extends Manager<Client>
{
    private static ClientManager p_singleton = null ;
    
    private ClientManager()
    {
        
    }
    
    public static ClientManager getInstance()
    {
        if(p_singleton == null)
        {
            p_singleton = new ClientManager() ;
        }
        return p_singleton ;
    }
    
    @Override
    public boolean insert(Client entity)
    {
        if(entity == null || !entity.isValid())
        {
            return false ;
        }
        
        String sql = "INSERT INTO CLIENT VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?)" ;
        try
        {
            PreparedStatement ps = (PreparedStatement) Database.getHinstance().getConnection().prepareStatement(sql) ;
            ps.setInt(1, entity.getId()) ;
            ps.setString(2, entity.getNom()) ;
            ps.setString(3, entity.getPrenom()) ;
            ps.setDate(4, new Date(entity.getDateNaissance().getTimeInMillis())) ;
            ps.setBoolean(5, entity.isFidelite()) ;
            ps.setBoolean(6, entity.isEtranger()) ;
            ps.setInt(7, entity.getNation().getId()) ;
            ps.setInt(8, entity.getSexe().getId()) ;
            ps.setInt(9, entity.getType().getId()) ;
            
            return ps.execute() ;
        }
        catch(SQLException sqlex)
        {
            /* Affichage d'un message d'erreur */
            sqlex.printStackTrace(new PrintStream(System.err));
        }
        return false ;
    }

    @Override
    public void delete(int id)
    {
        String sql = "DELETE FROM Client WHERE id=?" ;
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
        Sexe s ;
        Nationalite n ;
        TypeClient tc  ;
        String sql ="SELECT c.id, c.nom, c.prenom, c.age, c.fidele, c.etranger, c.id_nation, c.id_type, c.id_sexe, n.nom_fr_fr, s.descripion as sDescription, tc.descripion as tcDescription "
                        + "FROM Client c, Nation n, Sexe s, TypeClient tc "
                        + "WHERE c.id_nation = n.id "
                        + "AND c.id_type = tc.id "
                        + "AND c.id_sexe = s.id" ;
        try
        {
            PreparedStatement ps = (PreparedStatement) Database.getHinstance().getConnection().prepareStatement(sql) ;
            ResultSet rs = ps.executeQuery() ;
            while(rs.next())
            {
                s = new Sexe() ; s.setId(rs.getInt("id_sexe")); s.setDescription("sDescription") ;
                n = new Nationalite(); s.setId(rs.getInt("id_nation")); s.setDescription("nom_fr_fr") ;
                tc = new TypeClient(); s.setId(rs.getInt("id_type")); s.setDescription("tcDescription");
                all.add(new Client(rs, s, tc, n, rs.getBoolean("fidele"), rs.getBoolean("etranger"))) ;
            }
            return all;
        }
        catch(SQLException ex)
        {
            /* Affichafe d'un message d'erreur */
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
    }

    @Override
    public int update(Client entity)
    {
        if(entity == null || !entity.isValid())
        {
            return -1 ;
        }
        
        String sql ="UPDATE Client SET nom=?, prenom=?, age=?, fidele=?, etranger=?, id_nation=?, id_type=?, id_sexe=? WHERE id=?" ;
        try
        {
            PreparedStatement ps = (PreparedStatement) Database.getHinstance().getConnection().prepareStatement(sql) ;
            ps.setInt(1, entity.getId()) ;
            ps.setString(2, entity.getNom()) ;
            ps.setString(3, entity.getPrenom()) ;
            ps.setDate(4, new Date(entity.getDateNaissance().getTimeInMillis())) ;
            ps.setBoolean(5, entity.isFidelite()) ;
            ps.setBoolean(6, entity.isEtranger()) ;
            ps.setInt(7, entity.getNation().getId()) ;
            ps.setInt(8, entity.getSexe().getId()) ;
            ps.setInt(9, entity.getType().getId()) ;
            
            return ps.executeUpdate() ;
        }
        catch(SQLException sqlex)
        {
            /* Affichafe d'un message d'erreur */
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, sqlex);
        }
        return -1 ;
    }

    @Override
    public List<Client> findByCriteria(String criteria, boolean st)
    {
        List<Client> all = new ArrayList<Client>() ;
        Sexe s ;
        Nationalite n ;
        TypeClient tc  ;
        String strict = (st) ? "AND": "OR" ;
        String sql ="SELECT c.id, c.nom, c.prenom, c.age, c.fidele, c.etranger, c.id_nation, c.id_type, c.id_sexe, n.nom_fr_fr, s.descripion as sDescription, tc.descripion as tcDescription "
                        + "FROM Client c, Nation n, Sexe s, TypeClient tc "
                        + "WHERE c.id_nation = n.id "
                        + "AND c.id_type = tc.id "
                        + "AND c.id_sexe = s.id AND "
                        + "c.nom=? "+strict+" c.prenom=? "+strict+" n.nom_fr_fr=? " ;
        try
        {
            PreparedStatement ps = (PreparedStatement) Database.getHinstance().getConnection().prepareStatement(sql) ;
            for(int i=1; i<=3; i++)
            {
                
            }
            ResultSet rs = ps.executeQuery() ;
            while(rs.next())
            {
                s = new Sexe() ; s.setId(rs.getInt("id_sexe")); s.setDescription("sDescription") ;
                n = new Nationalite(); s.setId(rs.getInt("id_nation")); s.setDescription("nom_fr_fr") ;
                tc = new TypeClient(); s.setId(rs.getInt("id_type")); s.setDescription("tcDescription");
                all.add(new Client(rs, s, tc, n, rs.getBoolean("fidele"), rs.getBoolean("etranger"))) ;
            }
            return all;
        } catch (SQLException ex)
        {
            /* Affichafe d'un message d'erreur */
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
    }
}