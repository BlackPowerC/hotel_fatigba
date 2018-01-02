package Auth.Configure;

import org.json.simple.JSONObject;

import Auth.userconnection.AuthView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import app.Background;
import core.Database;
import core.Encryption.AdvancedEncryption;
import core.Message;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

@SuppressWarnings("serial")
public class ConnectionView extends JFrame implements ActionListener, KeyListener
{

    private final Toolkit tk = Toolkit.getDefaultToolkit();
    private JButton ok;
    private JButton annuler;
    private JLabel label;
    private Background fond;

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

    public ConnectionView()
    {
        Build_Button();
        Build_Label();
        Build_Input();
        Build_Panel();

        Dimension dm = new Dimension(tk.getScreenSize());

        setVisible(true);
        setResizable(false);
        setLocation((dm.width - 600) / 2, (dm.height - 480) / 2);
        setTitle("Connection à la BD");

        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void Build_Button()
    {
        ok = new JButton("OK");
        ok.setSize(200, 50);
        ok.addActionListener(this);
        ok.setIcon(new ImageIcon("/home/jordy/workspace/hotel_fatigba/src/ressource/icons/PNG-48/Add.png"));

        annuler = new JButton("Annuler");
        annuler.setSize(200, 50);
        annuler.addActionListener(this);
        annuler.setIcon(new ImageIcon("/home/jordy/workspace/hotel_fatigba/src/ressource/icons/PNG-48/Delete.png"));
    }

    public void Build_Input()
    {
        /* Mot de passe de la base de données */
        pass = new JPasswordField();
        pass.setToolTipText("Saisir le mot de passe de la base de données");
        pass.setEchoChar(' ');
        pass.addKeyListener(this);
        /* Nom de la base de données */
        databaseName = new JTextField();
        databaseName.addKeyListener(this);
        databaseName.setToolTipText("Saisir le nom de la base de données");
        /* Utilisateur de la base de donnes */
        user = new JTextField();
        user.addKeyListener(this);
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

    private void Build_Label()
    {
        Font police = new Font("Purisa", Font.BOLD, 15);
        String str = "<html><font color=#FEFDF0>Connection à la base de données</font></html>";

        label = new JLabel(str);
        label.setFont(police);
        label.setBackground(Color.DARK_GRAY);

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
        int w = 280, h = 30, x = (600 - w) / 2;
        // Utilisateur de la base de données
        txtDatabaseUser.setBounds(x-120, 60, w, h);
        user.setBounds(x+100, 60, w, h);
        // Nom de la base de données
        txtPass.setBounds(x-120, 90+30, w, h);
        pass.setBounds(x+100, 90+30, w, h);
        // Mote de passe de la base de données
        txtDatabaseName.setBounds(x-120, 120+50, w, h);
        databaseName.setBounds(x+100, 120+50, w, h);
        // Hote de la base de sonnées
        txtDatabaseHost.setBounds(x-120, 150+70, w, h);
        databaseHost.setBounds(x+100, 150+70, w, h);
        // Type de SGBD
        txtDatabaseSGBD.setBounds(x-120, 180+90, w, h);
        databaseSGBD.setBounds(x+100, 180+90, w, h);
        // Port d'écoute du SGBD
        txtDatabasePort.setBounds(x-120, 210+110, w, h);
        databasePort.setBounds(x+100, 210+110, w, h);

        ok.setBounds(x - 50, 500, 150, 50);
        annuler.setBounds(x + 150, 500, 150, 50);
    }

    private void Build_Panel()
    {
        fond = new Background("/home/jordy/workspace/hotel_fatigba/src/ressource/backgrounds/1165013_fusions-acquisitions-la-securite-informatique-au-coeur-des-preoccupations-141438-1.jpg");
        setPosition();
        addContent();
    }

    private void addContent()
    {
        fond.setLayout(null);
        fond.add(txtDatabaseUser);
        fond.add(user);

        fond.add(txtPass);
        fond.add(pass);

        fond.add(txtDatabaseName);
        fond.add(databaseName);

        fond.add(txtDatabaseHost);
        fond.add(databaseHost);

        fond.add(txtDatabaseSGBD);
        fond.add(databaseSGBD);

        fond.add(txtDatabasePort);
        fond.add(databasePort);

        fond.add(ok);
        fond.add(annuler);

        getContentPane().add(fond);
    }

    private boolean validation()
    {
        return true;
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
        File configuration = new File("config.json");

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
            cf.setSgbd((String) databaseSGBD.getSelectedItem());
            cf.setSgbdPort(databasePort.getValue().toString());
            cf.setSgbdHost(databaseHost.getText());
        }
        catch (FileNotFoundException ex)
        {
            Message.error("Fichier de configuration non trouvé !");
            Logger.getLogger(ConnectionView.class.getName()).log(Level.SEVERE, null, ex);

        }
        catch (IOException ex)
        {
            Message.error("Erreur inconnue !");
            Logger.getLogger(ConnectionView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ev)
    {
        Object ob = ev.getSource();
        if (ob.equals(annuler))
        {
            System.exit(DISPOSE_ON_CLOSE);
        }
        if (ob.equals(ok))
        {
            try
            {
                if (databaseSGBD.getSelectedItem().toString().equalsIgnoreCase("") || databaseSGBD.getSelectedItem().toString() == null)
                {
                    /* afficher un message d'erreur */
                    return;
                }
                Configuration cf = Configuration.getInstance();
                createConfiguration();
                Database.getHinstance().Connect(cf.getDatabaseUser(),cf.getDatabasePasswd());
                AuthView c = new AuthView();
                this.dispose();
            } catch (SQLException ex)
            {
                /* Impossible de se connecter à la base de données */
                Message.error("Impossible de se connecter à la base de données !");
                Logger.getLogger(ConnectionView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent ev)
    {
        if (ev.getKeyCode()== KeyEvent.VK_ENTER)
        {
            try
            {
                if (databaseSGBD.getSelectedItem().toString().equalsIgnoreCase("") || databaseSGBD.getSelectedItem().toString() == null)
                {
                    /* afficher un message d'erreur */
                    return;
                }
                Configuration cf = Configuration.getInstance();
                createConfiguration();
                Database.getHinstance().Connect(cf.getDatabaseUser(), cf.getDatabasePasswd());
                AuthView c = new AuthView();
                this.dispose();
            } catch (SQLException ex)
            {
                /* Impossible de se connecter à la base de données */
                Message.error("Impossible de se connecter à la base de données !");
                Logger.getLogger(ConnectionView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (ev.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            System.exit(DISPOSE_ON_CLOSE);
        }
    }

    @Override
    public void keyReleased(KeyEvent ev){}

    @Override
    public void keyTyped(KeyEvent ev){}
}
