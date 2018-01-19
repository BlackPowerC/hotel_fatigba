/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.manager.data;

import java.util.List;
import main.java.bo.Utilisateur;
import main.java.manager.UserManager;

/**
 *
 * @author jordy
 */

public class ListUser 
{
    private static ListUser p_singleton ;
    protected List<Utilisateur> data ;
    
    private ListUser()
    {
        this.data = UserManager.getInstance().findAll();
    }
    
    public static ListUser getInstance()
    {
        if(p_singleton == null)
        {
            p_singleton = new ListUser() ;
        }
        return p_singleton;
    }
    
    public List<Utilisateur> getList()
    {
        return data;
    }
}
