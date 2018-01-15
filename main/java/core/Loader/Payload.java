package main.java.core.Loader;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Payload<T>
{
    private Map<String, Class<T>> cache ;
    
    public Payload()
    {
        this.cache = new HashMap<>();
    }
    
    public abstract T get(String key) ;
    
    public abstract void loadCache() ;
    
    public void add(String key)
    {
        try
        {
            Class _class = CustomClassLoader.getInstance().loadClass(key);
            this.cache.put(key, _class) ;
        } catch (ClassNotFoundException | NullPointerException ex)
        {
            Logger.getLogger(Payload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void flushCache()
    {
        this.cache.clear() ;
    }
    
    public int getCacheSize()
    {
        return this.cache.size() ;
    }
}