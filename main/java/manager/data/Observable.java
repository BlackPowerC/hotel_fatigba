/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.manager.data;

import java.util.ArrayList;
import java.util.List;
import main.java.gui.Observateur;

/**
 *
 * @author jordy
 */
public abstract class Observable
{
    protected List<Observateur> lo ;
    
    public Observable()
    {
        lo = new ArrayList<>();
    }
    
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
        lo.forEach((o) ->
        {
            o.update();
        });
    }
}
