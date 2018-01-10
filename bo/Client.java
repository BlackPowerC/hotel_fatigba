package bo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author jordy
 */
public class Client extends Personne
{
    protected TypeClient type ;
    protected Nationalite nation ;
    protected boolean etranger ;
    protected boolean fidelite ;
    protected Date dateNaissance ;

    public Client(int id, String nom, String prenom, String email, Sexe sexe, TypeClient type, Nationalite nation, boolean etranger, boolean fidelite, Date dateNaissance)
    {
        super(id, nom, prenom, email, sexe);
        this.type = type;
        this.nation = nation;
        this.etranger = etranger;
        this.fidelite = fidelite;
        this.dateNaissance = dateNaissance;
    }

    public Client(Client another)
    {
        super(another);
        this.type = another.type;
        this.nation = another.nation;
        this.etranger = another.etranger;
        this.fidelite = another.fidelite;
        this.dateNaissance = another.dateNaissance;
    }

    public Client()
    {
        super() ;
        type = new TypeClient();
        nation = new Nationalite();
        dateNaissance = new Date(0, 0, 0);
    }

    public TypeClient getType()
    {
        return type;
    }

    public void setType(TypeClient type)
    {
        this.type = type;
    }

    public Nationalite getNation()
    {
        return nation;
    }

    public void setNation(Nationalite nation)
    {
        this.nation = nation;
    }

    public boolean isEtranger()
    {
        return etranger;
    }

    public void setEtranger(boolean etranger)
    {
        this.etranger = etranger;
    }

    public boolean isFidelite()
    {
        return fidelite;
    }

    public void setFidelite(boolean fidelite)
    {
        this.fidelite = fidelite;
    }
    
    @Override
    public boolean isValid()
    {
        return super.isValid() &&
               nation.isValid() &&
               type.isValid() &&
               sexe.isValid() ;
    }
    
    public int getAge()
    {
        Calendar c = Calendar.getInstance() ;
        Date current = new Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)) ;
        return Period.between(dateNaissance.toLocalDate(), current.toLocalDate()).getYears()-1900;
    }

    public Date getDateNaissance()
    {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance)
    {
        this.dateNaissance = dateNaissance;
    }
    
    public void setClient(Client another)
    {
        this.setPersonne(another);
        this.type = another.type;
        this.nation = another.nation;
        this.etranger = another.etranger;
        this.fidelite = another.fidelite;
        this.dateNaissance = another.dateNaissance;
    }
}
