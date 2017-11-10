package Auth;

public class Auth
{

    private String nom;
    private String prenom;
    private String password;

    private static Auth singleton;

    public static Auth getHinstance()
    {
        if (singleton == null)
        {
            singleton = new Auth();
        }
        return singleton;
    }

    private Auth()
    {
        this.password = "";
        this.setNom("");
        this.setPrenom("");
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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String Password)
    {
        this.password = Password;
    }
}
