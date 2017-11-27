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
    
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true ;
        }
        if(obj == null)
        {
            return false ;
        }
        if(this.getClass().getName().compareTo(obj.getClass().getName()) != 0)
        {
            return true ;
        }
        final Entity another = (Entity) obj ;
        
        return another.id == this.id ;
    }

    @Override
    public int hashCode()
    {
        return Integer.hashCode(this.id) ;
    }
}