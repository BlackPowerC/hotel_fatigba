/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.gui.admin.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import main.java.app.Button;
import main.java.auth.configure.Configuration;
import main.java.auth.configure.ConfigurationValidator;
import main.java.auth.configure.ConnectionView;
import main.java.core.Message;
import main.java.gui.Observateur;
import main.resources.Rc;
import org.json.simple.JSONObject;

/**
 * Cette classe affiche le formulaire de modification de la configuration
 * du SGBD de l'application.
 * @author jordy
 * @since 0.2.1
 */
public class DatabasePanel implements Observateur
{
    /**
     * Cette classe exécute l'action du bouton update.
     */
    public class UpdateBtnAction implements ActionListener
    {

        public void actionPerformed(ActionEvent ev)
        {
            Configuration cf = Configuration.getInstance() ;
            cf.setDatabaseHost(databaseHost.getText());
            cf.setDatabaseName(databaseName.getText());
            cf.setDatabaseUser(user.getText());
            cf.setDatabasePasswd(pass.getText());
            cf.setSgbd(databaseSGBD.getSelectedItem().toString());
            cf.setSgbdHost(databaseHost.getText());
            cf.setSgbdPort(databasePort.getValue().toString());
            System.out.println(Integer.parseInt(cf.getSgbdPort()));
            ConfigurationValidator cv = new ConfigurationValidator() ;
            if(cv.isValid(cf))
            {
                createConfiguration();
                Message.information("La configuration prendra éffet au prochain lancement !");
            }
            else
            {
                Message.warning("La configuration est invalide !") ;
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
            databaseHost.setText("");
            databaseName.setText("");
            databasePort.setValue(0);
            user.setText("");
            pass.setText("");
            databaseSGBD.setSelectedIndex(0);
        }
    }
    
    /**
     * Cette classe exécute l'action du bouton reset.
     */
    public class UploadBtnAction implements ActionListener
    {

        public void actionPerformed(ActionEvent ev)
        {
            loadConfiguration();
        }
    }
    
    private final String configFilePath ;
    
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
    
    /* Boutons de contrôle */
    private Button btnOk ;
    private Button btnCancel ;
    private Button btnUpload ;
    
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
        this.configFilePath = Rc.class.getResource("").getFile() + "config/db_config.json";
        buildButton();
        buildLabel();
        buildInput();
        buildPanel();
        loadConfiguration();
    }

    private void buildButton()
    {
        this.btnOk = new Button(Rc.class.getResource("").getFile()+"icons/PNG-48/Save.png") ;
        this.btnOk.setPreferredSize(new Dimension(45, 45));
        this.btnOk.setToolTipText("Valider !");
        this.btnOk.addActionListener(new UpdateBtnAction());
        
        this.btnCancel = new Button(Rc.class.getResource("").getFile()+"icons/PNG-48/Delete.png") ;
        this.btnCancel.setPreferredSize(new Dimension(45, 45));
        this.btnCancel.setToolTipText("Annuler !");
        this.btnCancel.addActionListener(new ResetBtnAction());
        
        this.btnUpload = new Button(Rc.class.getResource("").getFile()+"icons/PNG-48/Search.png") ;
        this.btnUpload.setPreferredSize(new Dimension(45, 45));
        this.btnUpload.setToolTipText("Charger !");
        this.btnUpload.addActionListener(new UploadBtnAction());
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

    private void buildPanel()
    {
        formPanel = new JPanel(new GridLayout(6, 2, 50, 50));
        formPanel.setBackground(new Color(0, 0, 0, 0));
        
        btnsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnsPanel.setBackground(new Color(0, 0, 0, 0));
        btnsPanel.setPreferredSize(new Dimension(55, 3*45+40));
        
        mainPanel = new Background();
        mainPanel.setLayout(new BorderLayout(0, 50));
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
        
        btnsPanel.add(btnOk) ;
        btnsPanel.add(btnUpload) ;
        btnsPanel.add(btnCancel) ;
        
        mainPanel.add(formPanel, BorderLayout.CENTER) ;
        mainPanel.add(btnsPanel, BorderLayout.SOUTH) ;
    }

    /**
     * Action
     */
    private void loadConfiguration()
    {
        Configuration cf = Configuration.getInstance();
        databaseHost.setText(cf.getDatabaseHost());
        databaseName.setText(cf.getDatabaseName());
        databasePort.setValue(Integer.parseInt(cf.getSgbdPort()));
        databaseSGBD.setSelectedItem(cf.getSgbd());
        user.setText(cf.getDatabaseUser());
    }
    
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
        File configuration = new File(this.configFilePath);

        try
        {
            /* On écrit dans le fichier de configuration */
            if(configuration.exists())
            {
                configuration.delete();
                configuration.createNewFile();
            }
            else
            {
                
                configuration.createNewFile();
            }
            FileWriter fos = new FileWriter(configuration);
            fos.write(json.toString());
            fos.close();

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
