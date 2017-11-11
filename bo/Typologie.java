package bo ;

public class Typologie extends Entity
{
    protected String description ;

    public Typologie()
    {
    }
    
    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public boolean isValid()
    {
        return description.isEmpty() ;
    }
}