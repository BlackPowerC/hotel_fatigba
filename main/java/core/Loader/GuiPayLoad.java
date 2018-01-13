/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.Loader;

/**
 * La class GuiPayLoad charge de manière dynamique des classes implémentant l'interface
 * Gui.
 * @author jordy
 * @since 0.0.0
 */
public class GuiPayLoad extends Payload<Object>
{
    private static GuiPayLoad p_singleton = null;
    private boolean loaded ;
    private GuiPayLoad()
    {
        super();
        this.loaded = false ;
    }

    public static GuiPayLoad getInstance()
    {
        if (p_singleton == null)
        {
            p_singleton = new GuiPayLoad();
        }
        return p_singleton;
    }

    @Override
    public Object get(String key)
    {
        return null ;
    }
    
    /**
     * <p>
     * Cette fonction charge des instances de la classe Class
     * qui correspondent à des classes implémentant l'interface <code>Gui</code>
     * </p>
     * <p>
     * Les classes à charger sont renseignés dans le fichier GuiPayLoad.json.
     * </p>
     */
    @Override
    public void loadCache()
    {
        if(!this.loaded)
        {
            
        }
    } 
}
