package bo;

public class Chambre extends Entity
{
    protected TypeChambre type ;
    protected CaracteristiqueChambre caracteristique ;
    protected SituationChambre situation ;
    protected float prix ;
    protected boolean etat ;

    public Chambre(TypeChambre type, CaracteristiqueChambre caracteristique, SituationChambre situation, float prix, boolean etat)
    {
        this.type = type;
        this.caracteristique = caracteristique;
        this.situation = situation;
        this.prix = prix;
        this.etat = etat;
    }
    
    public Chambre(TypeChambre type, CaracteristiqueChambre caracteristique, SituationChambre situation, float prix, boolean etat)
    {
        this.type = type;
        this.caracteristique = caracteristique;
        this.situation = situation;
        this.prix = prix;
        this.etat = etat;
    }
    
    public Chambre()
    {
    }
    
    
}
