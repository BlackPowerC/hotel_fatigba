/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.gui.admin.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import main.java.app.Background;
import main.java.app.Buttons;
import main.java.bo.Sexe;
import main.java.bo.TypeUtilisateur;
import main.java.bo.Utilisateur;
import main.java.core.Message;
import main.java.gui.Observateur;
import main.java.manager.FactoryManager;
import main.java.manager.Manager;
import main.java.manager.UserManager;
import main.java.manager.data.ListUser;

/**
 * Cette classe affiche le panneau de gestion des utilisateurs de l'aplication.
 * @author jordy
 * @since 0.2.1
 * @see Utilisateur
 */
public class UserPanel implements Observateur
{
    /**
     * Cette classe représente un formulaire permettant de manipuler les
     * utilisateurs.
     */
    public class Form
    {
        /**
        * Cette classe exécute l'action du bouton OK.
        */
        public class OkBtnAction implements ActionListener
        {
            public void actionPerformed(ActionEvent ev)
            {
                if(updating)
                {
                    Message.warning("Impossible d'insérer une données destiner pour une MAJ !");
                }
                else
                {
                    hydrateUser() ;
                    user.setId(1);
                    if(!user.isValid())
                    {
                        Message.warning("Certaines informations ne sont pas valides !");
                        return;
                    }
                    /* Insertion dans la base de données */
                    Manager m = (UserManager) FactoryManager.getInstance().getManager(FactoryManager.USER_MANAGER) ;
                    if(!m.insert(user))
                    {
                        Message.warning("Impossible d'enregister cet utilisateur !");
                        return ;
                    }
                    /* Insertion d'une ligne dans la JTable */
                    JTableUserModel mdl = (JTableUserModel) JTableUser.getHinstance().getTable().getModel() ;
                    mdl.addRow() ;
                    if(ListUser.getInstance().getList().size() == 0)
                    {
                        user.setId(1);
                    }
                    else
                    {
                        user.setId(ListUser.getInstance().getLast().getId()+1);
                    }
                    ListUser.getInstance().getList().add(new Utilisateur(user)) ;
                    
                    update();
                }
                form.reset();
            }
        }
    
        /**
        * Cette classe exécute l'action du bouton supprimer.
        */
        public class DeleteBtnAction implements ActionListener
        {
            public void actionPerformed(ActionEvent ev)
            {
                if(updating)
                {
                    UserManager.getInstance().delete(user.getId()) ;
                    Message.information("Utilisateur supprimé !");
                    updating = false ;
                    form.reset();
                }
            }
        }
    
        /**
        * Cette classe exécute l'action du bouton update.
        */
        public class UpdateBtnAction implements ActionListener
        {
            public void actionPerformed(ActionEvent ev)
            {
                if(updating)
                {

                }
            }
        }
    
        /**
        * Cette classe exécute l'action du bouton reset.
        */
        public class ResetBtnAction implements ActionListener
        {
            public void actionPerformed(ActionEvent ev)
            {
                updating = false ;
                form.reset();
                Message.warning("Champ réinitialisés !");
            }
        }
    
        /**
        * Cette classe exécute l'action du clic sur une ligne de la JTable.
        */
        public class TableAction implements MouseListener
        {

            @Override
            public void mouseClicked(MouseEvent e)
            {
                updating = true;
                int i = JTableUser.getHinstance().getTable().getSelectedRow() ;
                user.setUtilisateur(ListUser.getInstance().getList().get(i)) ;
                hydrateForm(user) ;
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
            }
            
        }
        
        /* Jeton */
        private boolean updating ;
        /* Objet métiers */
        private Utilisateur user ;
        
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
            this.user = new Utilisateur() ;
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
            this.panel = new JPanel(new BorderLayout(0, 50));
            this.panel.setBackground(new Color(0, 0, 0, 0));
            
            this.panelButtons = new JPanel();
            this.panelButtons.setBackground(new Color(0, 0, 0, 0));
            this.panelButtons.setPreferredSize(new Dimension(55, 3*45+40));
            
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
                buttons.getButtons(i).setPreferredSize(new Dimension(45, 45));
            }
            
            buttons.getButtons(1).addActionListener(new ResetBtnAction());
            buttons.getButtons(3).addActionListener(new DeleteBtnAction());
            buttons.getButtons(0).addActionListener(new OkBtnAction());
            JTableUser.getHinstance().getTable().addMouseListener(new TableAction());
            
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
            this.panel.add(this.panelFrom, BorderLayout.CENTER) ;
            this.panel.add(this.panelButtons, BorderLayout.SOUTH) ;
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
                "", "admin", "clientAdmin", "chambreAdmin",
                "reservationAdmin", "serviceAdmin", "facturationAdmin"
            });
        }
        
        public void reset()
        {
            this.comboSexe.setSelectedIndex(0);
            this.comboType.setSelectedIndex(0);
            this.txtEmail.setText("");
            this.txtNom.setText("");
            this.txtPasswd.setText("");
            this.txtPrenom.setText("");
        }
        
        /**
         * Cette fonction sera appelée par la classe gérant l'action du clic
         * sur une ligne de la table des Utilisateurs.
         */
        private void hydrateForm(Utilisateur user)
        {
            this.comboSexe.setSelectedIndex(user.getSexe().getId());
            this.comboType.setSelectedIndex(user.getType().getId());
            this.txtEmail.setText(user.getEmail());
            this.txtPrenom.setText(user.getPrenom());
            this.txtNom.setText(user.getEmail());
            this.txtPasswd.setText(user.getPassword());
        }
        
        /**
         * Cette fonction sera appelée lors du clic sur un bouton ajout ou modifier.
         * Elle remplit un objet de la classe Utilisateur.
         */
        private void hydrateUser()
        {
            Sexe sexe = new Sexe() ;
            sexe.setId(comboSexe.getSelectedIndex());
            sexe.setDescription(comboSexe.getSelectedItem().toString());
            
            TypeUtilisateur typeUtilisateur = new TypeUtilisateur() ;
            typeUtilisateur.setId(comboType.getSelectedIndex());
            typeUtilisateur.setDescription(comboType.getSelectedItem().toString());
            
            this.user.setEmail(txtEmail.getText());
            this.user.setNom(txtNom.getText());
            this.user.setPassword(txtPasswd.getText());
            this.user.setPrenom(txtPrenom.getText());
            this.user.setType(typeUtilisateur);
            this.user.setSexe(sexe);
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
    
    @Override
    public void update()
    {
        panel.remove(tableUser.getScroll());
        panel.add(tableUser.getScroll());
        panel.revalidate();
    }
}
