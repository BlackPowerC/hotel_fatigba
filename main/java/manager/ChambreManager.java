package main.java.manager;

import main.java.bo.CaracteristiqueChambre;
import main.java.core.Database;
import main.java.bo.Chambre;
import main.java.bo.SituationChambre;
import main.java.bo.TypeChambre;
import com.mysql.jdbc.PreparedStatement;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.core.Message;

/* Classe Valide */

public class ChambreManager extends Manager<Chambre>
{
    private static ChambreManager p_singleton = null ;
    
    private ChambreManager()
    {
        
    }
    
    public static ChambreManager getInstance()
    {
        if(p_singleton == null)
        {
            p_singleton = new ChambreManager() ;
        }
        return p_singleton ;
    }
    
    @Override
    public boolean insert(Chambre entity)
    {
        return false;
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
        }
        catch (SQLException ex)
        {
            /* Affichafe d'un message d'erreur */
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Chambre findById(int id)
    {
        TypeChambre tc;
        CaracteristiqueChambre cc;
        SituationChambre sc ;
        Chambre c = null;
        String query = "SELECT c.id, c.id_type, c.id_situation, c.id_caracteristique, c.prix, c.etat, "
                + "SC.description AS scDescritpion, "
                + "CC.description AS ccDescription, "
                + "TC.description AS tcDescription "
                + "FROM Chambre c, CaracteristiqueChambre CC, SituationChambre SC, TypeChambre TC "
                + "WHERE c.id_type = TC.id "
                + "AND c.id_situation = SC.id "
                + "AND c.id_caracteristique = CC.id WHERE c.id = ?" ;
        try
        {
            PreparedStatement prepare = (PreparedStatement) Database.getHinstance().getConnection().prepareStatement(query) ;
            prepare.setInt(1, id) ;
            ResultSet rs = prepare.executeQuery() ;
            if(rs.next())
            {
                /* Type Chambre*/
                tc = new TypeChambre();
                tc.setId(rs.getInt("id_type"));
                tc.setDescription(rs.getString("tcDescription"));
                /* Caractéristique Chambre */
                cc = new CaracteristiqueChambre();
                cc.setId(rs.getInt("id_caracteristique"));
                cc.setDescription(rs.getString("ccDescription"));
                /*Situation chambre*/
                sc = new SituationChambre();
                sc.setId(rs.getInt("id_caracteristique"));
                sc.setDescription(rs.getString("scDescription"));
                c = new Chambre(rs,tc, cc, sc) ;
            }
            rs.close();
            return c ;
        }
        catch(SQLException sqlex)
        {
            /* Affichafe d'un message d'erreur */
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, sqlex);
        }
        return null ;
    }

    @Override
    public List<Chambre> findAll()
    {
        List<Chambre> lc = new ArrayList<Chambre>();
        TypeChambre tc;
        CaracteristiqueChambre cc;
        SituationChambre sc;
        String query = "SELECT c.id, c.id_type, c.id_situation, c.id_caracteristique, c.prix, c.etat, "
                + "SC.description AS scDescription, "
                + "CC.description AS ccDescription, "
                + "TC.description AS tcDescription "
                + "FROM Chambre c, CaracteristiqueChambre CC, SituationChambre SC, TypeChambre TC "
                + "WHERE c.id_type = TC.id "
                + "AND c.id_situation = SC.id "
                + "AND c.id_caracteristique = CC.id" ;
        try
        {
            ResultSet rs = Database.getHinstance().executeQuery(query);
            while (rs.next())
            {
                /* Type Chambre*/
                tc = new TypeChambre();
                tc.setId(rs.getInt("id_type"));
                tc.setDescription(rs.getString("tcDescription"));
                /* Caractéristique Chambre */
                cc = new CaracteristiqueChambre();
                cc.setId(rs.getInt("id_caracteristique"));
                cc.setDescription(rs.getString("ccDescription"));
                /*Situation chambre*/
                sc = new SituationChambre();
                sc.setId(rs.getInt("id_caracteristique"));
                sc.setDescription(rs.getString("scDescription"));
                lc.add(new Chambre(rs,tc, cc, sc)) ;
            }
            rs.close();
            return lc ;
        }
        catch(SQLException sqlex)
        {
            /* Affichafe d'un message d'erreur */
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, sqlex);
        }
        return lc ;
    }

    @Override
    public int update(Chambre chambre)
    {
        if(chambre == null) {return -1 ;}
        try
        {
            String sql = "UPDATE Chambre SET id_type=?, id_caracteristique=?, id_situation=?, prix=?, etat=? WHERE id=?" ;
            PreparedStatement ps = Database.getHinstance().prepare(sql) ;
            ps.setInt(1, chambre.getType().getId()) ;
            ps.setInt(2, chambre.getCaracteristique().getId()) ;
            ps.setInt(3, chambre.getSituation().getId()) ;
            ps.setFloat(4, chambre.getPrix()) ;
            ps.setBoolean(5, chambre.isEtat()) ;
            ps.setInt(6, chambre.getId()) ;
            System.out.println(ps.asSql());
            return ps.executeUpdate() ; 
        }
        catch(SQLException sqlex)
        {
            /* Affichafe d'un message d'erreur */
            Message.error(sqlex.getMessage()+" !");
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, sqlex);
        }
        return -1 ;
    }

    @Override
    public List<Chambre> findByCriteria(String criteria, boolean st)
    {
        /* Recherche selon le type et la caractéristique et la situation */
        List<Chambre> lc = new ArrayList<Chambre>() ;
        TypeChambre tc ;
        CaracteristiqueChambre cc ;
        SituationChambre sc ;
        String strict = (st)? "AND":"OR" ;
        
        String sql="SELECT c.id, c.id_type, c.id_situation, c.id_caracteristique, c.prix, c.etat, "
                + "SC.description AS scDescription, "
                + "CC.description AS ccDescription, "
                + "TC.description AS tcDescription "
                + "FROM Chambre c, CaracteristiqueChambre CC, SituationChambre SC, TypeChambre TC "
                + "WHERE c.id_type = TC.id "
                + "AND c.id_situation = SC.id "
                + "AND c.id_caracteristique = CC.id "
                + "AND (SC.description = '?%' "+strict+" CC.description = '?%' "+strict+" TC.description= '?%')" ;
        try
        {
            PreparedStatement stmt = Database.getHinstance().prepare(sql) ;
            stmt.setString(1, criteria);
            stmt.setString(2, criteria);
            stmt.setString(3, criteria);
            System.out.println(stmt.getPreparedSql());
            ResultSet rs = stmt.executeQuery() ;
            while(rs.next())
            {
                /* Type Chambre*/
                tc = new TypeChambre();
                tc.setId(rs.getInt("id_type"));
                tc.setDescription(rs.getString("tcDescription"));
                /* Caractéristique Chambre */
                cc = new CaracteristiqueChambre();
                cc.setId(rs.getInt("id_caracteristique"));
                cc.setDescription(rs.getString("ccDescription"));
                /*Situation chambre*/
                sc = new SituationChambre();
                sc.setId(rs.getInt("id_caracteristique"));
                sc.setDescription(rs.getString("scDescription"));
                lc.add(new Chambre(rs,tc, cc, sc)) ;
            }
            rs.close();
            return lc ;
        }
        catch(SQLException sqlex)
        {
            /* Affichage d'un message d'erreur */
            Message.error(sqlex.getMessage()+" !") ;
            sqlex.printStackTrace(new PrintStream(System.err)) ;
        }
        return lc ;
    }

    public ArrayList<Chambre> findInRange(Vector<Integer> range) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
