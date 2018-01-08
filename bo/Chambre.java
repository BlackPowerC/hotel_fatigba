package bo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Chambre extends Entity
{
    protected TypeChambre type ;
    protected CaracteristiqueChambre caracteristique ;
    protected SituationChambre situation ;
    protected float prix ;
    protected boolean etat ;

    public Chambre(ResultSet rs, TypeChambre tc, CaracteristiqueChambre cc, SituationChambre sc) throws SQLException
    {
        super();
        super.id = rs.getInt("id") ;
        this.type = tc ;
        this.caracteristique = cc ;
        this.situation = sc ;
        this.prix = rs.getFloat("prix") ;
        this.etat = rs.getBoolean("etat") ;
    }

    public Chambre(int id, TypeChambre type, CaracteristiqueChambre caracteristique, SituationChambre situation, float prix, boolean etat)
    {
        this.id = id ;
        this.type = type;
        this.caracteristique = caracteristique;
        this.situation = situation;
        this.prix = prix;
        this.etat = etat;
    }
    
    public Chambre(Chambre another)
    {
        this.id = another.id ;
        this.type = another.type;
        this.caracteristique = another.caracteristique;
        this.situation = another.situation;
        this.prix = another.prix;
        this.etat = another.etat;
    }
    
    public Chambre()
    {
    }

    public TypeChambre getType()
    {
        return type;
    }

    public void setType(TypeChambre type)
    {
        this.type = type;
    }

    public CaracteristiqueChambre getCaracteristique()
    {
        return caracteristique;
    }

    public void setCaracteristique(CaracteristiqueChambre caracteristique)
    {
        this.caracteristique = caracteristique;
    }

    public SituationChambre getSituation()
    {
        return situation;
    }

    public void setSituation(SituationChambre situation)
    {
        this.situation = situation;
    }

    public float getPrix()
    {
        return prix;
    }

    public void setPrix(float prix)
    {
        this.prix = prix;
    }

    public boolean isEtat()
    {
        return etat;
    }

    public void setEtat(boolean etat)
    {
        this.etat = etat;
    }
    
    public void setChambre(Chambre another)
    {
        this.id = another.id ;
        this.type = another.type;
        this.caracteristique = another.caracteristique;
        this.situation = another.situation;
        this.prix = another.prix;
        this.etat = another.etat;
    }
    
    @Override
    public boolean isValid()
    {
        return super.isValid() && situation.isValid() &&
               type.isValid() &&
               caracteristique.isValid() &&
               (prix > 0.0f) ;
    }
}
