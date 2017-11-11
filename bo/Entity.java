package bo;

public abstract class Entity
{
    protected int id ;

    public Entity()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    
    public abstract boolean isValid() ;
}