package manager;

import bo.CaracteristiqueChambre;
import core.Database;
import bo.Chambre;
import bo.SituationChambre;
import bo.TypeChambre;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* Classe Valide */

public class ChambreManager extends Manager<Chambre>
{

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
                + "SC.description AS scDescritpon, "
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
            return lc ;
        }
        catch(SQLException sqlex)
        {
            /* Affichafe d'un message d'erreur */
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, sqlex);
        }
        return null ;
    }

    @Override
    public int update(Chambre chambre)
    {
        if(chambre == null) {return -1 ;}
        try
        {
            String sql = "UPDATE chambre SET id_type=?, id_caracteristique=?, prix=?, etat=? WHERE id=?" ;
            PreparedStatement ps = (PreparedStatement) Database.getHinstance().getConnection().prepareStatement(sql) ;
            ps.setInt(1, chambre.getType().getId()) ;
            ps.setInt(2, chambre.getCaracteristique().getId()) ;
            ps.setFloat(3, chambre.getPrix()) ;
            ps.setBoolean(4, chambre.isEtat()) ;
            ps.setInt(5, chambre.getId()) ;
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
    public List<Chambre> findByCriteria(String criteria, boolean st)
    {
        
    }
}
