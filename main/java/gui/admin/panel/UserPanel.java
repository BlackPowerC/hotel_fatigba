/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.gui.admin.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import main.java.app.Buttons;

/**
 *
 * @author jordy
 * @since 0.2.1
 */
public class UserPanel
{

    /**
     * Cette classe représente un formulaire permettant de manipuler les
     * utilisateurs.
     */
    public class Form
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

        /* Les contrôls */
        private Buttons buttons;
        private Box hBox1;
        private Box hBox2;

        /* Les Layout */
        private GridLayout gridLayout;
        private JPanel panel;

        public Form()
        {
            this.buttons = new Buttons();
            this.hBox1 = Box.createHorizontalBox();
            this.hBox2 = Box.createHorizontalBox();
            this.gridLayout = new GridLayout(7, 2, 5, 8);
            this.panel.setLayout(this.gridLayout);
        }
        
        /**
         * Cette fonction ajoute des composants SWING dans le panel.
         */
        public void addContent()
        {
            /* Ajout des contrôls */
            for (int i = 0; i <= 1; i++)
            {
                this.hBox1.add(buttons.getButtons(i));
                this.hBox2.add(buttons.getButtons(i + 2));
            }
            this.panel.add(txtNom);
            this.panel.add(labelNom);

            this.panel.add(txtPrenom);
            this.panel.add(labelPrenom);

            this.panel.add(txtEmail);
            this.panel.add(labelEmail);

            this.panel.add(comboSexe);
            this.panel.add(labelSexe);

            this.panel.add(comboType);
            this.panel.add(labelType);

            this.panel.add(txtPasswd);
            this.panel.add(labelPasswd);
            
            this.panel.add(hBox1) ;
            this.panel.add(hBox2) ;
        }

        /**
         * Cette fonction instancie les objets JLabel de la classe.
         */
        private void buildLabel()
        {
            labelNom = new JLabel("Nom");
            labelPrenom = new JLabel("Prénom");
            labelEmail = new JLabel("E-mail");
            labelType = new JLabel("Type d'utilisateur");
            labelPasswd = new JLabel("Mot de passe");
            labelSexe = new JLabel("Sexe");
        }

        /**
         * Cette fonction instancie les champs de texte du formulaire oû l'on entre des
         * données.
         */
        private void buildField()
        {
            txtNom = new JTextField();
            txtNom.setToolTipText("Nom de l'utilisateur");

            txtPrenom = new JTextField();
            txtPrenom.setToolTipText("Prénom de l'utilisateur");

            txtEmail = new JTextField();
            txtEmail.setToolTipText("E-mail de l'utilisateur");

            txtPasswd = new JPasswordField();
            comboSexe = new JComboBox<>(new String[]
            {
                "", "homme", "femme"
            });
            comboType = new JComboBox<>(new String[]
            {
                "", "clientAdmin", "reservationAdmin", "admin"
            });
        }
    }
}
