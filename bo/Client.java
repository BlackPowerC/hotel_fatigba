package bo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    protected GregorianCalendar dateNaissance ;

    public Client(ResultSet rs, Sexe sexe, TypeClient type, Nationalite nation, boolean etranger, boolean fidelite) throws SQLException
    {
        super(rs, sexe);
        Date n = rs.getDate("age") ;
        /*2010-10-05*/
        String[] newDate = n.toString().split("-") ;
        this.dateNaissance =  new GregorianCalendar(
                                    Integer.parseInt(newDate[0]), 
                                    Integer.parseInt(newDate[1]), 
                                    Integer.parseInt(newDate[2])) ;
        this.type = type;
        this.nation = nation;
        this.etranger = rs.getBoolean("etranger") ;
        this.fidelite = rs.getBoolean("fidelite") ;
    }

    public Client()
    {
        super() ;
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
               type.isValid() ;
    }
    
    public int getAge()
    {
        Calendar c = Calendar.getInstance() ;
        int age = ((c.get(Calendar.YEAR)+1900)-(dateNaissance.get(GregorianCalendar.YEAR)+1900)) ;
        return (c.get(Calendar.DAY_OF_YEAR) < dateNaissance.get(GregorianCalendar.DAY_OF_YEAR)) ? age-1: age;
    }
}
