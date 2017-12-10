/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import bo.Client;
import bo.Consommation;
import bo.Nationalite;
import bo.Service;
import bo.Sexe;
import bo.TypeClient;
import com.mysql.jdbc.PreparedStatement;
import core.Database;
import core.Message;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
        List<Consommation> list = new ArrayList<Consommation>() ;
        Consommation co ;
        Service se ;
        Client n ;
        String s = (!strict) ? "OR" : "AND";
        try
        {
            String sql="SELECT c.id AS id_client, c.nom, c.prenom, c.age, c.fidele, c.etranger, c.email, c.id_nation, c.id_type, c.id_sexe, " +
                              "co.id AS id_consommation, co.id_service, " +
                                "se.description AS seDescription, se.prix, se.etat, " +
                                "tc.description AS tcDescription, " +
                                "n.nom_fr_fr, " +
                                "S.description as sDescription"+
                                    "FROM Client c, Consommation co, Service se, Sexe S, TypeClient tc, Nation n " +
                                        "WHERE c.id = co.id_client AND co.id_service = se.id  " +
                                        "AND c.id_nation = n.id " +
                                        "AND tc.id = c.id_type " +
                                        "AND S.id = c.id_sexe AND (c.prenom = ? "+s+" c.nom=?)" ;
            PreparedStatement ps = (PreparedStatement) Database.getHinstance().getConnection().prepareStatement(sql) ;
            ps.setString(1, criteria);
            ps.setString(2, criteria);
            ResultSet rs = ps.executeQuery() ;
            while(rs.next())
            {
                n = new Client(rs.getInt("id_client"), rs.getString("nom"), rs.getString("Prenom"), rs.getString("email"), "", 
                               new Sexe(), 
                               new TypeClient(), 
                               new Nationalite(), 
                               rs.getBoolean("fidele"), 
                               rs.getBoolean("etranger"), 
                               new GregorianCalendar());
                /* data bout nation */
                n.getNation().setId(rs.getInt("id_nation"));
                n.getNation().setDescription(rs.getString("nom_nom_fr"));
                /* data about Sexe */
                n.getType().setId(rs.getInt("id_type"));
                n.getType().setDescription(rs.getString("tcDescription"));
                /* data about Type */
                n.getSexe().setId(rs.getInt("id_sexe"));
                n.getSexe().setDescription(rs.getString("sDescription"));
                list.add(
                    new Consommation(rs, 
                                new Service(rs.getInt("id_service"), rs.getString("seDesctiprion"), rs.getFloat("prix")),
                                n)
                        ) ;
            }
            return list ;
        }
        catch(SQLException sqlex)
        {
            //Message.error("Impossible de mettre à jour la données dont l'id vaut: "+id);
            sqlex.printStackTrace(new PrintStream(System.err));
        }
        return null ;
    }

    @Override
    public List<Consommation> findAll()
    {
        List<Consommation> list = new ArrayList<Consommation>() ;
        Consommation co ;
        Service se ;
        Client n ;
        try
        {
            String sql="SELECT c.id AS id_client, c.nom, c.prenom, c.age, c.fidele, c.etranger, c.email, c.id_nation, c.id_type, c.id_sexe, " +
                              "co.id AS id_consommation, co.id_service, " +
                                "se.description AS seDescription, se.prix, se.etat, " +
                                "tc.description AS tcDescription, " +
                                "n.nom_fr_fr, " +
                                "S.description as sDescription"+
                                    "FROM Client c, Consommation co, Service se, Sexe S, TypeClient tc, Nation n " +
                                        "WHERE c.id = co.id_client AND co.id_service = se.id  " +
                                        "AND c.id_nation = n.id " +
                                        "AND tc.id = c.id_type " +
                                        "AND S.id = c.id_sexe" ;
            ResultSet rs = Database.getHinstance().executeQuery(sql) ;
            while(rs.next())
            {
                n = new Client(rs.getInt("id_client"), rs.getString("nom"), rs.getString("Prenom"), rs.getString("email"), "", 
                               new Sexe(), 
                               new TypeClient(), 
                               new Nationalite(), 
                               rs.getBoolean("fidele"), 
                               rs.getBoolean("etranger"),  
                               new GregorianCalendar());
                /* data bout nation */
                n.getNation().setId(rs.getInt("id_nation"));
                n.getNation().setDescription(rs.getString("nom_nom_fr"));
                /* data about Sexe */
                n.getType().setId(rs.getInt("id_type"));
                n.getType().setDescription(rs.getString("tcDescription"));
                /* data about Type */
                n.getSexe().setId(rs.getInt("id_sexe"));
                n.getSexe().setDescription(rs.getString("sDescription"));
                list.add(
                    new Consommation(rs, 
                                new Service(rs.getInt("id_service"), rs.getString("seDesctiprion"), rs.getFloat("prix")),
                                n)
                        ) ;
            }
            return list ;
        }
        catch(SQLException sqlex)
        {
            //Message.error("Impossible de mettre à jour la données dont l'id vaut: "+id);
            sqlex.printStackTrace(new PrintStream(System.err));
        }
        return null ;
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
