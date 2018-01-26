/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.gui.admin.panel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import main.java.app.Background;
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

        /* Les Layout */
        private JPanel panel;
        private JPanel panelButtons ;
        private JPanel panelFrom ;

        public Form()
        {
            this.buttons = new Buttons();
            buildPanel() ;
            buildField();
            buildLabel();
            addContent();
        }
        
        /**
         * Cette fonction instancie et paramettre les panels
         */
        public void buildPanel()
        {
            this.panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            this.panel.setBackground(new Color(0, 0, 0, 0));
            this.panelButtons = new JPanel(new GridLayout(1, 4));
            this.panelButtons.setBackground(new Color(0, 0, 0, 0));
            this.panelFrom = new JPanel(new GridLayout(6 ,2, 50, 50));
            this.panelFrom.setBackground(new Color(0, 0, 0, 0));
        }
        
        /**
         * Cette fonction ajoute des composants SWING dans le panel.
         */
        public void addContent()
        {
            /* Ajout des contrôls */
            for (int i = 0; i <= 3; i++)
            {
                this.panelButtons.add(buttons.getButtons(i));
            }
            /* Le panel du formulaire */
            this.panelFrom.add(labelNom);
            this.panelFrom.add(txtNom);

            this.panelFrom.add(labelPrenom);
            this.panelFrom.add(txtPrenom);

            this.panelFrom.add(labelEmail);
            this.panelFrom.add(txtEmail);

            this.panelFrom.add(labelSexe);
            this.panelFrom.add(comboSexe);

            this.panelFrom.add(labelType);
            this.panelFrom.add(comboType);

            this.panelFrom.add(labelPasswd);
            this.panelFrom.add(txtPasswd);
            /* Le panel principal */
            this.panel.add(this.panelFrom) ;
            this.panel.add(this.panelButtons) ;
        }

        /**
         * Cette fonction instancie les objets JLabel de la classe.
         */
        private void buildLabel()
        {
            String tmp1 = "<html><font color=#FEFDF0>";
            String tmp2 = "<font/></html>";
            Font font = new Font("Purisa", Font.BOLD, 16);
            labelNom = new JLabel(tmp1+"Nom"+tmp2);
            labelNom.setFont(font);
            labelPrenom = new JLabel(tmp1+"Prénom"+tmp2);
            labelPrenom.setFont(font);
            labelEmail = new JLabel(tmp1+"E-mail"+tmp2);
            labelEmail.setFont(font);
            labelType = new JLabel(tmp1+"Type d'utilisateur"+tmp2);
            labelType.setFont(font);
            labelPasswd = new JLabel(tmp1+"Mot de passe"+tmp2);
            labelPasswd.setFont(font);
            labelSexe = new JLabel(tmp1+"Sexe"+tmp2);
            labelSexe.setFont(font);
        }

        /**
         * Cette fonction instancie les champs de texte du formulaire oû l'on
         * entre des données.
         */
        private void buildField()
        {
            txtNom = new JTextField(20);
            txtNom.setToolTipText("Nom de l'utilisateur");
            
            txtPrenom = new JTextField(20);
            txtPrenom.setToolTipText("Prénom de l'utilisateur");

            txtEmail = new JTextField(20);
            txtEmail.setToolTipText("E-mail de l'utilisateur");

            txtPasswd = new JPasswordField(20);
            comboSexe = new JComboBox<>(new String[]
            {
                "", "homme", "femme"
            });
            comboType = new JComboBox<>(new String[]
            {
                "", "clientAdmin", "reservationAdmin", "admin"
            });
        }

        public JPanel getPanel()
        {
            return this.panel;
        }
    }

    private static UserPanel singleton;
    private Form form;
    private Background panel;
    private GridLayout gridLayout;
    private JTableUser tableUser;

    public static UserPanel getHinstance()
    {
        if (singleton == null)
        {
            singleton = new UserPanel();
        }
        return singleton;
    }

    private UserPanel()
    {
        build();
        addContent();
    }

    private void build()
    {
        this.form = new Form();
        this.panel = new Background();
        this.gridLayout = new GridLayout(1, 2, 0, 20);
        this.panel.setLayout(gridLayout);
        this.tableUser = JTableUser.getHinstance();
    }

    private void addContent()
    {
        this.panel.add(form.getPanel());
        this.panel.add(tableUser.getScroll());
    }

    public Background getPanel()
    {
        return this.panel;
    }
}
