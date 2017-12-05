package manager;

import bo.Sexe;
import bo.TypeUtilisateur;
import bo.Utilisateur;
import com.mysql.jdbc.PreparedStatement;
import core.Database;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserManager extends Manager<Utilisateur>
{

    private static UserManager p_singleton = null;

    private UserManager()
    {

    }

    public static UserManager getInstance()
    {
        if (p_singleton == null)
        {
            p_singleton = new UserManager();
        }
        return p_singleton;
    }

    @Override
    public boolean insert(Utilisateur entity)
    {
        if(entity == null || !entity.isValid())
        {
            return false ;
        }
        
        String sql = "INSERT INTO Utilisateur VALUES(?, ?, ?, ?, ?, ?)" ;
        try
        {
            PreparedStatement st = (PreparedStatement) Database.getHinstance().getConnection().prepareStatement(sql) ;
            st.setInt(1, entity.getId()) ;
            st.setInt(2, entity.getType().getId()) ;
            st.setInt(3, entity.getSexe().getId()) ;
            st.setString(4, entity.getNom()) ;
            st.setString(5, entity.getPrenom()) ;
            st.setString(6, entity.getEmail()) ;
            st.setString(7, entity.getPassword()) ;
            
            return st.execute() ;
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
        String sql = "DELETE FROM Utilisateur WHERE id=?";
        try
        {
            PreparedStatement ps = (PreparedStatement) Database.getHinstance().getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        }
        catch (SQLException ex)
        {
            /* Affichafe d'un message d'erreur */
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Utilisateur findById(int id)
    {
        Utilisateur u ;
        Sexe s ;
        TypeUtilisateur tu  ;
        String sql ="SELECT u.id, u.nom, u.prenom, u.email, u.password, u.id_type, u.id_sexe, s.descripion as sDescription, tu.descripion as tuDescription "
                        + "FROM Utilisateur u, Sexe s, TypeUtilisateur tu "
                        + "WHERE u.id_type = tu.id "
                        + "AND u.id_sexe = s.id "
                        + "AND u.id = ?" ;
        try
        {
            PreparedStatement ps = (PreparedStatement) Database.getHinstance().getConnection().prepareStatement(sql) ;
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery() ;
            if(rs.next())
            {
                s = new Sexe() ; s.setId(rs.getInt("id_sexe")); s.setDescription("sDescription") ;
                tu = new TypeUtilisateur(); s.setId(rs.getInt("id_type")); s.setDescription("tuDescription");
                u = new Utilisateur(rs, s, tu);
                return u ;
            }
        }
        catch (SQLException ex)
        {
            /* Affichafe d'un message d'erreur */
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
    }

    @Override
    public List<Utilisateur> findByCriteria(String criteria, boolean strict)
    {
        List<Utilisateur> lu = new ArrayList<Utilisateur>() ;
        Sexe s ;
        TypeUtilisateur tu  ;
        String st = (strict) ? "AND": "OR" ;
        String sql ="SELECT u.id, u.nom, u.prenom, u.email, u.password, u.id_type, u.id_sexe, s.descripion as sDescription, tu.descripion as tuDescription "
                        + "FROM Utilisateur u "
                        + "LEFT JOIN TypeUtilisateur tu ON u.id_type = tu.id "
                        + "RIGHT JOIN Sexe s ON u.id_sexe = s.id "
                        + "WHERE u.nom=? "+st+" u.prenom=?" ;
        try
        {
            PreparedStatement ps = (PreparedStatement) Database.getHinstance().getConnection().prepareStatement(sql) ;
            ps.setString(1, criteria);
            ps.setString(2, criteria);
            ResultSet rs =ps.executeQuery() ;
            while(rs.next())
            {
                s = new Sexe() ; s.setId(rs.getInt("id_sexe")); s.setDescription("sDescription") ;
                tu = new TypeUtilisateur(); s.setId(rs.getInt("id_type")); s.setDescription("tuDescription");
                lu.add(new Utilisateur(rs, s, tu));
            }
            return lu ;
        }
        catch (SQLException ex)
        {
            /* Affichafe d'un message d'erreur */
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
    }

    @Override
    public List<Utilisateur> findAll()
    {
        List<Utilisateur> lu = new ArrayList<Utilisateur>() ;
        Sexe s ;
        TypeUtilisateur tu  ;
        String sql ="SELECT u.id, u.nom, u.prenom, u.email, u.password, u.id_type, u.id_sexe, s.descripion as sDescription, tu.descripion as tuDescription "
                        + "FROM Utilisateur u "
                        + "LEFT JOIN TypeUtilisateur tu ON u.id_type = tu.id "
                        + "RIGHT JOIN Sexe s ON u.id_sexe = s.id" ;
        try
        {
            ResultSet rs =Database.getHinstance().executeQuery(sql) ;
            while(rs.next())
            {
                s = new Sexe() ; s.setId(rs.getInt("id_sexe")); s.setDescription("sDescription") ;
                tu = new TypeUtilisateur(); s.setId(rs.getInt("id_type")); s.setDescription("tuDescription");
                lu.add(new Utilisateur(rs, s, tu));
            }
            return lu ;
        }
        catch (SQLException ex)
        {
            /* Affichafe d'un message d'erreur */
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
    }

    @Override
    public int update(Utilisateur entity)
    {
        if(entity == null || !entity.isValid())
        {
            /* Affichafe d'un message d'erreur */
            return -1 ;
        }
        String sql="UPDATE Utilisateur set id_type=?, id_sexe=?, nom=?, prenom=?, email=?, password=? WHERE id=?" ;
        try
        {
            PreparedStatement st = (PreparedStatement) Database.getHinstance().getConnection().prepareStatement(sql) ;
            st.setInt(1, entity.getType().getId());
            st.setInt(2, entity.getSexe().getId());
            st.setString(3, entity.getNom());
            st.setString(4, entity.getPrenom());
            st.setString(5, entity.getEmail());
            st.setString(6, entity.getPassword());
            st.setInt(7, entity.getId());
            return st.executeUpdate() ;
        }
        catch(SQLException sqlex)
        {
            /* Affichafe d'un message d'erreur */
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, sqlex);
        }
        return -1 ;
    }
}
