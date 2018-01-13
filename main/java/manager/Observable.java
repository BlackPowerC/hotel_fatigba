/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.util.ArrayList;
import java.util.List;
import userInterface.Observateur;

/**
 *
 * @author jordy
 */
public abstract class Observable
{
    protected List<Observateur> lo = new ArrayList<Observateur>() ;
    
    public void addObservateur(Observateur o)
    {
        lo.add(o) ;
    }
    
    public void removeObservateur(Observateur o)
    {
        lo.remove(o) ;
    }
    
    public void notifier()
    {
        for(Observateur o: lo)
        {
            o.update() ;
        }
    }
}
