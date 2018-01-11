/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import Auth.userconnection.SessionException;
import Auth.userconnection.SessionHandler;
import core.Message;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author jordy
 */
public class Menu
{
    public class ItemExitAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                SessionHandler.getInstance().end() ;
                Message.information("Fermeture de la session !");
            }
            catch(SessionException se)
            {
                Message.warning(se.getMessage()+" !");
            }
            System.exit(0);
        }
    }
            
    private static Menu p_singleton;
    protected JMenu menuFile ;
    protected JMenuItem itemExit ;
    protected JMenuBar menuBar;
    
    public static Menu getInstance()
    {
        if(p_singleton == null)
        {
            p_singleton = new Menu() ;
        }
        return p_singleton;
    }
    
    private Menu()
    {
        menuFile = new JMenu("Fichier");
        itemExit = new JMenuItem("Quitter") ;
        menuBar = new JMenuBar();
        setMenu();
    }
    
    private void setMenu()
    {
        /**/
        KeyStroke k = KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK) ;
        itemExit.setAccelerator(k);
        itemExit.addActionListener(new ItemExitAction());
        /**/
        menuFile.setMnemonic('F');
        menuFile.add(itemExit) ;
        /**/
        menuBar.add(menuFile);
    }
    
    public JMenuBar getMenu()
    {
        return menuBar;
    }
}
