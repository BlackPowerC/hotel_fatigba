/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.gui.admin.panel;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author jordy
 * @since 0.2.1
 */
public class UserPanel
{
    /**
     * Cette classe représente un formulaire permettant de manipuler les utilisateurs.
     */
    private class Form
    {
        /* Les champs du formulaire */
        private JTextField txtNom; 
        private JLabel labelNom; 
        
        private JTextField txtPrenom; 
        private JLabel labelPrenom; 
        
        private JTextField txtEmail; 
        private JLabel labelEmail;
        
        private JComboBox<String> comboSexe; 
        private JLabel labelSexe;
        
        private JComboBox<String> comboType;
        private JLabel labelType; 
        
        private JPasswordField txtPasswd;
        private JLabel labelPasswd;
        
        /**
         * Cette fonction instancie les objets JLabel de la classe.
         */
        private void buildLabel()
        {
            labelNom = new JLabel("Nom") ;
            labelPrenom = new JLabel("Prénom") ;
            labelEmail = new JLabel("E-mail") ;
            labelType = new JLabel("Type d'utilisateur") ;
            labelPasswd = new JLabel("Mot de passe") ;
            labelSexe = new JLabel("Sexe") ;
        }
        
        /**
         * Cette fonction instancie les objets du formulaire oû l'on entre des
         * données.
         */
        private void buildField()
        {
            txtNom = new JTextField() ;
            txtNom.setToolTipText("Nom de l'utilisateur");
            
            txtPrenom = new JTextField();
            txtPrenom.setToolTipText("Prénom de l'utilisateur");
                    
            txtEmail = new JTextField() ;
            txtEmail.setToolTipText("E-mail de l'utilisateur") ;
                    
            txtPasswd = new JPasswordField() ;
            comboSexe = new JComboBox<>(new String[]{"", "homme", "femme"}) ;
            comboType = new JComboBox<>(new String[]{"", "clientAdmin", "reservationAdmin", "admin"}) ;
        }
    }
}
