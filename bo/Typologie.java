package bo ;

public abstract class Typologie extends Entity
{
    protected String description ;

    public Typologie()
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

    @Override
    public boolean isValid()
    {
        return this.description.isEmpty() ;
    }
}