package bo ;

public abstract class Typologie extends Entity
{
    protected String description ;
    
    public Typologie(String description)
    {
        this.description = description ;
    }
    
    public Typologie(Typologie another)
    {
        this.description = another.description ;
    }
    
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
    
    public void setTypologie(Typologie another)
    {
        this.description = another.description ;
    }
    
    @Override
    public boolean isValid()
    {
        return this.description.isEmpty() ;
    }
}