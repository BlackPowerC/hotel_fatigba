package Auth.userconnection;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import app.Background;
import core.Database;
import app.MainView;
import bo.Sexe;
import bo.TypeUtilisateur;
import bo.Utilisateur;
import com.mysql.jdbc.PreparedStatement;
import core.Encryption.Encryption;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("serial")
public class AuthView extends JFrame implements ActionListener, KeyListener
{
    private Toolkit tk = Toolkit.getDefaultToolkit();
    /* Boutons de contrôle */
    private JButton ok;
    private JButton annuler;
    private JLabel label;
    private Background fond;

    /* Champs et label du formulaire de connexion */
    private JLabel txtPrenom;
    private JTextField prenom;
    private JLabel txtPass;
    private JPasswordField pass;
    private JLabel txtNom;
    private JTextField nom;

    /* Objet temporaire */
    private Utilisateur user;
    private Utilisateur tmp;

    public AuthView()
    {
        user = new Utilisateur();
        tmp = new Utilisateur();

        Build_Button();
        Build_Label();
        Build_TextField();
        Build_Panel();

        Dimension dm = new Dimension(tk.getScreenSize());

        setVisible(true);
        setResizable(false);
        setLocation((dm.width - 600) / 2, (dm.height - 480) / 2);
        setTitle("Connection à l'application");

        setSize(600, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void Build_Button()
    {
        ok = new JButton("OK");
        ok.setSize(200, 50);
        ok.addActionListener(this);
        ok.setIcon(new ImageIcon("/home/jordy/workspace/Hotel/src/icons/check/checkmark_32.png"));

        annuler = new JButton("Annuler");
        annuler.setSize(200, 50);
        annuler.addActionListener(this);
        annuler.setIcon(new ImageIcon("/home/jordy/workspace/Hotel/src/icons/check/error_32.png"));
    }

    public void Build_TextField()
    {
        pass = new JPasswordField("jordy");
        pass.setEchoChar(' ');
        pass.addKeyListener(this);
        prenom = new JTextField("fatigba");
        prenom.addKeyListener(this);
        nom = new JTextField("jordy");
        nom.addKeyListener(this);
    }

    private void Build_Label()
    {
        Font police = new Font("Purisa", Font.BOLD, 18);
        String str = "<html><font color=#FEFDF0>Ecran de connexion</font></html>";
        label = new JLabel(str);
        label.setFont(police);
        label.setBackground(Color.DARK_GRAY);

        txtPass = new JLabel("<html><font color=#FEFDF0>Mot de passe</font></html>");
        txtPass.setFont(police);
        txtNom = new JLabel("<html><font color=#FEFDF0>Nom</font></html>");
        txtNom.setFont(police);
        txtPrenom = new JLabel("<html><font color=#FEFDF0>Prenom</font></html>");
        txtPrenom.setFont(police);
    }

    private void setPosition()
    {
        int w = 220, h = 30, x = (600 - w) / 2;
        txtNom.setBounds(x, 60, w, h);
        nom.setBounds(x, 90, w, h);
        txtPrenom.setBounds(x, 140, w, h);
        prenom.setBounds(x, 170, w, h);
        txtPass.setBounds(x, 205, w, 60);
        pass.setBounds(x, 250, w, h);

        ok.setBounds(x - 50, 350, 150, 50);
        annuler.setBounds(x + 150, 350, 150, 50);
    }

    private void Build_Panel()
    {
        fond = new Background("/home/jordy/workspace/Hotel/src/backgrounds/1165013_fusions-acquisitions-la-securite-informatique-au-coeur-des-preoccupations-141438-1.jpg");
        setPosition();
        addContent();
    }

    private void addContent()
    {
        fond.setLayout(null);
        fond.add(nom);
        fond.add(prenom);
        fond.add(pass);
        fond.add(txtNom);
        fond.add(txtPrenom);
        fond.add(txtPass);
        fond.add(ok);
        fond.add(annuler);

        getContentPane().add(fond);
    }

    private void authentification()
    {
        tmp.setNom(nom.getText());
        tmp.setPrenom(prenom.getText());
        tmp.setPassword(Encryption.getInstance().encrypt(new String(pass.getPassword())));
        if (tmp.getNom().compareTo(user.getNom()) == 0
                && tmp.getPrenom().compareTo(user.getPrenom()) == 0
                && tmp.getPassword().compareTo(user.getPassword()) == 0)
        {
            this.dispose();
            MainView main = new MainView("HOTEL");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Données erronées", "Erreur", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ev)
    {
        Object o = ev.getSource();
        if (o.equals(ok))
        {
            try
            {
                String sql = "SELECT nom, prenom, password FROM Utilisateur WHERE nom=?, prenom=? password=?" ;
                PreparedStatement ps = (PreparedStatement) Database.getHinstance().getConnection().prepareStatement(sql) ;
                ps.setString(1, nom.getText());
                ps.setString(3, Encryption.getInstance().encrypt(pass.getText()));
                ps.setString(2, prenom.getText());
                
                ResultSet rs = ps.executeQuery() ;
                if(rs.next())
                {
                    user.setNom(rs.getString("nom"));
                    user.setPassword(rs.getString("password"));
                    user.setPrenom(rs.getString("prenom"));
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Données erronées", "Erreur", JOptionPane.WARNING_MESSAGE);
                    return ;
                }
            }
            catch(SQLException sqlex)
            {
                
            }
            authentification();
        }
        else if(o.equals(annuler))
        {
            System.exit(DISPOSE_ON_CLOSE);
        }
    }

    @Override
    public void keyPressed(KeyEvent ev)
    {
        int code = ev.getKeyCode();
        if (code == KeyEvent.VK_ENTER)
        {
            authentification();
        }
        else if(code == KeyEvent.VK_ESCAPE)
        {
            try
            {
                Database.getHinstance().close();
                System.exit(DISPOSE_ON_CLOSE);
            }
            catch(SQLException ex)
            {
                Logger.getLogger(AuthView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ev)
    {
        Object ob = ev.getSource();
        if (ob.equals(nom))
        {
            tmp.setNom(nom.getText());
        }

        if (ob.equals(prenom))
        {
            tmp.setPrenom(prenom.getText());
        }

        if (ob.equals(pass))
        {
            tmp.setPassword(pass.getText());
        }
    }

    @Override
    public void keyTyped(KeyEvent ev)
    {

    }
}
