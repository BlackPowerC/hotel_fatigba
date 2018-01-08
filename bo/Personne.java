package bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

/**
 *
 * @author jordy
 */
public class Personne extends Entity
{
    protected String nom;
    protected String prenom;
    protected String email ;
    protected Sexe sexe ;
    
    public Personne(ResultSet rs, Sexe sexe) throws SQLException
    {
        super() ;
        this.sexe = sexe ;
        this.nom = rs.getString("nom");
        this.prenom = rs.getString("prenom");
        this.email = rs.getString("email");
        this.id = rs.getInt("id") ;
    }

    public Personne(int id, String nom, String prenom, String email, Sexe sexe)
    {
        this.id = id ;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.sexe = sexe;
    }
    
    public Personne(Personne another)
    {
        this.id = another.id ;
        this.nom = another.nom;
        this.prenom = another.prenom;
        this.email = another.email;
        this.sexe = another.sexe;
    }
    
    public Personne()
    {
        super() ;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Sexe getSexe()
    {
        return sexe;
    }

    public void setSexe(Sexe sexe)
    {
        this.sexe = sexe;
    }
    
    public void setPersonne(Personne another)
    {
        this.id = another.id ;
        this.nom = another.nom;
        this.prenom = another.prenom;
        this.email = another.email;
        this.sexe = another.sexe;
    }
    
    @Override
    public boolean isValid()
    {
        String namePattern = "^[a-zA-Z]$";
        String emailPattern = "^[a-zA-Z0-9]@[a-zA-Z0-9].{fr,com}$";
        boolean localValid ;
        localValid = Pattern.compile(namePattern).matcher(this.nom).matches()
                && Pattern.compile(namePattern).matcher(this.prenom).matches()
                && Pattern.compile(emailPattern).matcher(this.email).matches();
        return super.isValid() && localValid ;
    }
}
