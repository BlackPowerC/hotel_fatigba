package bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class Service extends Entity
{
    private String description;
    private float prix;
    
    public Service(ResultSet rs) throws SQLException
    {
        super() ;
        this.description = rs.getString("description") ;
        this.id = rs.getInt("id") ;
        this.prix = rs.getFloat("prix") ;
                
    }

    public Service(int id, String description, float prix)
    {
        this.id = id;
        this.description = description;
        this.prix = prix;
    }
    
    public Service(Service another)
    {
        this.id = another.id;
        this.description = another.description;
        this.prix = another.prix;
    }
    
    public Service()
    {
        super() ;
    }
    
    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public float getPrix()
    {
        return prix;
    }

    public void setPrix(float prix)
    {
        this.prix = prix;
    }
    
    public void setService(Service another)
    {
        this.id = another.id;
        this.description = another.description;
        this.prix = another.prix;
    }
    
    @Override
    public boolean isValid()
    {
        return super.isValid() && !this.description.isEmpty() && (prix > 0.0f) ;
    }
}
