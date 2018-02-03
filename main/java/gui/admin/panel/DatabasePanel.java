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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import main.java.app.Background;
import main.java.auth.configure.Configuration;
import main.java.auth.configure.ConnectionView;
import main.java.core.Message;
import main.java.gui.Observateur;
import main.resources.Rc;
import org.json.simple.JSONObject;

/**
 *
 * @author jordy
 * @since 0.2.1
 */
public class DatabasePanel implements Observateur
{

    /**
     * Cette classe exécute l'action du bouton OK.
     */
    public class OkBtnAction implements ActionListener
    {

        public void actionPerformed(ActionEvent ev)
        {

        }
    }

    /**
     * Cette classe exécute l'action du bouton supprimer.
     */
    public class DeleteBtnAction implements ActionListener
    {

        public void actionPerformed(ActionEvent ev)
        {

        }
    }

    /**
     * Cette classe exécute l'action du bouton update.
     */
    public class UpdateBtnAction implements ActionListener
    {

        public void actionPerformed(ActionEvent ev)
        {

        }
    }

    /**
     * Cette classe exécute l'action du bouton reset.
     */
    public class ResetBtnAction implements ActionListener
    {

        public void actionPerformed(ActionEvent ev)
        {

        }
    }
    
    /**
     * Cette classe exécute l'action du bouton reset.
     */
    public class UploadBtnAction implements ActionListener
    {

        public void actionPerformed(ActionEvent ev)
        {

        }
    }
    
    private Background mainPanel;
    private JPanel formPanel;
    private JPanel btnsPanel;


    /* Le Formulaire */
    // Nom de la base de données
    private JLabel txtDatabaseName;
    private JTextField databaseName;
    // Hote de la base de sonnées
    private JLabel txtDatabaseHost;
    private JTextField databaseHost;
    // Port d'écoute du SGBD
    private JLabel txtDatabasePort;
    private JSpinner databasePort;
    // Mot de passe de la base de donnée
    private JLabel txtPass;
    private JPasswordField pass;
    // Type de SGBD
    private JLabel txtDatabaseSGBD;
    private JComboBox databaseSGBD;
    // Nom de l'admin de la base de données
    private JLabel txtDatabaseUser;
    private JTextField user;
    
    private static DatabasePanel singleton;
    
    public static DatabasePanel getHinstance()
    {
        if (singleton == null)
        {
            singleton = new DatabasePanel();
        }
        return singleton;
    }
    
    private DatabasePanel()
    {
        buildButton();
        buildLabel();
        buildInput();
        buildPanel();
    }

    private void buildButton()
    {

    }

    private void buildInput()
    {
        /* Mot de passe de la base de données */
        pass = new JPasswordField();
        pass.setToolTipText("Saisir le mot de passe de la base de données");
        pass.setEchoChar(' ');

        /* Nom de la base de données */
        databaseName = new JTextField();
        databaseName.setToolTipText("Saisir le nom de la base de données");

        /* Utilisateur de la base de donnes */
        user = new JTextField();
        user.setToolTipText("Saisir le nom d'utilisateur de la base de données");

        // Hote de la base de sonnées
        databaseHost = new JTextField();
        databaseHost.setToolTipText("Saisir l'adresse IP ou le nom de domaine du SGBD");

        // Port d'écoute du SGBD
        databasePort = new JSpinner();
        databasePort.setToolTipText("Selectionner le port d'écoute du SGBD");

        // Type de SGBD
        String[] sgbds = new String[]
        {
            "", "SQLITE", "MYSQL", "POSTGRESQL", "MARIADB"
        };
        databaseSGBD = new JComboBox<String>(sgbds);
        databaseSGBD.setToolTipText("Choisir le type de SGBD");
    }

    private void buildLabel()
    {
        Font police = new Font("Purisa", Font.BOLD, 15);
        String str = "<html><font color=#FEFDF0>Connection à la base de données</font></html>";

        txtPass = new JLabel("<html><font color=#FEFDF0>Mot de passe</font></html>");
        txtPass.setFont(police);

        txtDatabaseUser = new JLabel("<html><font color=#FEFDF0>Utilisateur</font></html>");
        txtDatabaseUser.setFont(police);

        txtDatabaseName = new JLabel("<html><font color=#FEFDF0>Nom de la base de données</font></html>");
        txtDatabaseName.setFont(police);

        txtDatabaseHost = new JLabel("<html><font color=#FEFDF0>Hôte de la base de données</font></html>");
        txtDatabaseHost.setFont(police);

        txtDatabasePort = new JLabel("<html><font color=#FEFDF0>Port d'écoute du SGBD</font></html>");
        txtDatabasePort.setFont(police);

        txtDatabaseSGBD = new JLabel("<html><font color=#FEFDF0>SGBD</font></html>");
        txtDatabaseSGBD.setFont(police);
    }

    private void setPosition()
    {

    }

    private void buildPanel()
    {
        formPanel = new JPanel(new GridLayout(6, 2, 50, 50));
        formPanel.setBackground(new Color(0, 0, 0, 0));
        btnsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPanel = new Background();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        addContent();
    }

    private void addContent()
    {
        formPanel.add(txtDatabaseUser);
        formPanel.add(user);

        formPanel.add(txtPass);
        formPanel.add(pass);

        formPanel.add(txtDatabaseName);
        formPanel.add(databaseName);

        formPanel.add(txtDatabaseHost);
        formPanel.add(databaseHost);

        formPanel.add(txtDatabaseSGBD);
        formPanel.add(databaseSGBD);

        formPanel.add(txtDatabasePort);
        formPanel.add(databasePort);

        mainPanel.add(formPanel) ;
        mainPanel.add(btnsPanel) ;
    }

    /**
     * Action
     */
    private void createConfiguration()
    {
        /* Céation du fichier JSon de configuration */
        JSONObject json = new JSONObject();
        json.put("Host", databaseHost.getText());
        json.put("Pass", pass.getText());
        json.put("Name", databaseName.getText());
        json.put("User", user.getText());
        json.put("Port", databasePort.getValue().toString());
        json.put("SGBD", databaseSGBD.getSelectedItem().toString());
        File configuration = new File(Rc.class.getResource("").getFile() + "config/db_config.json");

        try
        {
            /* On écrit dans le fichier de configuration */
            configuration.createNewFile();
            FileWriter fos = new FileWriter(configuration);
            fos.write(json.toString());
            fos.close();

            Configuration cf = Configuration.getInstance();
            cf.setDatabaseName(databaseName.getText());
            cf.setDatabasePasswd(pass.getText());
            cf.setDatabaseUser(user.getText());
            cf.setDatabaseHost(databaseHost.getText());
            cf.setSgbd((String) databaseSGBD.getSelectedItem());
            cf.setSgbdPort(databasePort.getValue().toString());
            cf.setSgbdHost(databaseHost.getText());
        } catch (FileNotFoundException ex)
        {
            Message.error("Fichier de configuration non trouvé !");
            Logger.getLogger(ConnectionView.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex)
        {
            Message.error("Erreur inconnue !");
            Logger.getLogger(ConnectionView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update()
    {
    }

    public Background getPanel()
    {
        return this.mainPanel;
    }
}
