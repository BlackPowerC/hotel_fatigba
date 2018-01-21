package main.java.manager ;

import main.java.bo.Client;
import main.java.bo.Nationalite;
import main.java.bo.Sexe;
import main.java.bo.TypeClient;
import java.sql.PreparedStatement;
import main.java.core.Database;
import main.java.core.Message;
import java.io.PrintStream;
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
        
        String sql = "INSERT INTO Client VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
        try
        {
            PreparedStatement ps = Database.getHinstance().prepare(sql) ;
            ps.setInt(1, entity.getId()) ;
            ps.setString(2, entity.getNom()) ;
            ps.setString(3, entity.getPrenom()) ;
            ps.setDate(4, entity.getDateNaissance()) ;
            ps.setBoolean(5, entity.isFidelite()) ;
            ps.setBoolean(6, entity.isEtranger()) ;
            ps.setString(7, entity.getEmail());
            ps.setInt(8, entity.getNation().getId()) ;
            ps.setInt(9, entity.getSexe().getId()) ;
            ps.setInt(10, entity.getType().getId()) ;
            
            return ps.execute() ;
        }
        catch(SQLException sqlex)
        {
            /* Affichage d'un message d'erreur */
            Message.error(sqlex.getMessage());
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
            PreparedStatement ps = Database.getHinstance().prepare(sql) ;
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
        String sql ="SELECT c.id, c.nom, c.prenom, c.email, c.age, c.fidele, c.etranger, c.id_nation, c.id_type, c.id_sexe, n.nom_fr_fr, s.description as sDescription, tc.description as tcDescription "
                        + "FROM Client c, Nation n, Sexe s, TypeClient tc "
                        + "WHERE c.id_nation = n.id "
                        + "AND c.id_type = tc.id "
                        + "AND c.id_sexe = s.id AND c.id = ?" ;
        try
        {
            PreparedStatement ps =Database.getHinstance().prepare(sql) ;
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery() ;
            if(rs.next())
            {
                s = new Sexe() ; s.setId(rs.getInt("id_sexe")); s.setDescription(rs.getString("sDescription")) ;
                n = new Nationalite(); n.setId(rs.getInt("id_nation")); n.setDescription(rs.getString("nom_fr_fr")) ;
                tc = new TypeClient(); tc.setId(rs.getInt("id_type")); tc.setDescription(rs.getString("tcDescription"));
                cl = new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), 
                        s, tc, n, rs.getBoolean("fidele"), rs.getBoolean("etranger"), rs.getDate("age")) ;
                rs.close();
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
        String sql ="SELECT c.id, c.nom, c.email, c.prenom, c.age, c.fidele, c.etranger, c.id_nation, c.id_type, c.id_sexe, n.nom_fr_fr, s.description as sDescription, tc.description as tcDescription "
                        + "FROM Client c, Nation n, Sexe s, TypeClient tc "
                        + "WHERE c.id_nation = n.id "
                        + "AND c.id_type = tc.id "
                        + "AND c.id_sexe = s.id" ;
        try
        {
            PreparedStatement ps = Database.getHinstance().prepare(sql) ;
            ResultSet rs = ps.executeQuery() ;
            while(rs.next())
            {
                s = new Sexe() ; s.setId(rs.getInt("id_sexe")); s.setDescription(rs.getString("sDescription")) ;
                n = new Nationalite(); n.setId(rs.getInt("id_nation")); n.setDescription(rs.getString("nom_fr_fr")) ;
                tc = new TypeClient(); tc.setId(rs.getInt("id_type")); tc.setDescription(rs.getString("tcDescription"));
                    all.add(
                            new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), 
                            s, tc, n, rs.getBoolean("fidele"), rs.getBoolean("etranger"), rs.getDate("age"))
                           ) ;
            }
            rs.close();
            return all;
        }
        catch(SQLException ex)
        {
            /* Affichafe d'un message d'erreur */
            Message.error("ERREUR DE REQUÊTES SQL !");
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all ;
    }

    @Override
    public int update(Client entity)
    {
        if(entity == null || !entity.isValid())
        {
            return -1 ;
        }
        
        String sql ="UPDATE Client SET nom=?, prenom=?, email=?, age=?, fidele=?, etranger=?, id_nation=?, id_type=?, id_sexe=? WHERE id=?" ;
        try
        {
            PreparedStatement ps = Database.getHinstance().prepare(sql) ;
            ps.setInt(10, entity.getId()) ;
            ps.setString(1, entity.getNom()) ;
            ps.setString(2, entity.getPrenom()) ;
            ps.setDate(4, entity.getDateNaissance()) ;
            ps.setBoolean(5, entity.isFidelite()) ;
            ps.setBoolean(6, entity.isEtranger()) ;
            ps.setInt(7, entity.getNation().getId()) ;
            ps.setInt(8, entity.getSexe().getId()) ;
            ps.setInt(9, entity.getType().getId()) ;
            ps.setString(3, entity.getEmail()) ;
            
            return ps.executeUpdate() ;
        }
        catch(SQLException sqlex)
        {
            /* Affichafe d'un message d'erreur */
            Message.error(sqlex.getMessage()+ " !");
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
        criteria += "%" ;
        try
        {
            String sql ="SELECT c.id, c.nom, c.prenom, c.email, c.age, c.fidele, c.etranger, c.id_nation, c.id_type, c.id_sexe, n.nom_fr_fr, s.description as sDescription, tc.description as tcDescription "
                        + "FROM Client c, Nation n, Sexe s, TypeClient tc "
                        + "WHERE c.id_nation = n.id "
                        + "AND c.id_type = tc.id "
                        + "AND c.id_sexe = s.id AND "
                        + "c.nom LIKE ? "+strict+" c.prenom LIKE ? " ;
            
            PreparedStatement ps = Database.getHinstance().prepare(sql) ;
            for(int i=1; i<=2; i++)
            {
                ps.setString(i, criteria);
            }
            ResultSet rs = ps.executeQuery() ;
            while(rs.next())
            {
                s = new Sexe() ; s.setId(rs.getInt("id_sexe")); s.setDescription(rs.getString("sDescription")) ;
                n = new Nationalite(); n.setId(rs.getInt("id_nation")); n.setDescription(rs.getString("nom_fr_fr")) ;
                tc = new TypeClient(); tc.setId(rs.getInt("id_type")); tc.setDescription(rs.getString("tcDescription"));
                all.add(
                            new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), 
                            s, tc, n, rs.getBoolean("fidele"), rs.getBoolean("etranger"), rs.getDate("age"))
                           ) ;
            }
            rs.close();
            return all;
        } catch (SQLException ex)
        {
            /* Affichafe d'un message d'erreur */
            Message.error(ex.getMessage()+" !");
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all ;
    }
}