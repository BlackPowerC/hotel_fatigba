package Auth;

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
import app.DataBaseCon;
import app.MainView;

@SuppressWarnings("serial")
public class AuthView extends JFrame implements ActionListener, KeyListener
{

    private Toolkit tk = Toolkit.getDefaultToolkit();
    private JButton ok;
    private JButton annuler;
    private JLabel label;
    private Background fond;

    private JLabel txtPrenom;
    private JTextField prenom;
    private JLabel txtPass;
    private JPasswordField pass;
    private JLabel txtNom;
    private JTextField nom;

    private User user;
    private User tmp;

    public AuthView()
    {
        user = new User();
        tmp = new User();
        try
        {
            ResultSet rs = DataBaseCon.getHinstance().executeQuery("select nom, prenom, pass from User where id = 1");
            if (rs.next())
            {
                user.nom = rs.getString("nom");
                user.prenom = rs.getString("prenom");
                user.pass = rs.getString("pass");
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

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

    public void actionPerformed(ActionEvent ev)
    {
        Object o = ev.getSource();
        if (o.equals(ok))
        {
            tmp.nom = nom.getText();
            tmp.prenom = prenom.getText();
            tmp.pass = pass.getText();
            if (tmp.nom.compareTo(user.nom) == 0 && tmp.prenom.compareTo(user.prenom) == 0 && tmp.pass.compareTo(user.pass) == 0)
            {
                this.dispose();
                MainView main = new MainView("HOTEL");
            } else
            {
                JOptionPane.showMessageDialog(null, "Données erronées", "Erreur", JOptionPane.WARNING_MESSAGE);
            }
        } else if (o.equals(annuler))
        {
            System.exit(DISPOSE_ON_CLOSE);
        }
    }

    public void keyPressed(KeyEvent ev)
    {
        int code = ev.getKeyCode();
        if (code == KeyEvent.VK_ENTER)
        {
            tmp.nom = nom.getText();
            tmp.prenom = prenom.getText();
            tmp.pass = pass.getText();
            if (tmp.nom.compareTo(user.nom) == 0 && tmp.prenom.compareTo(user.prenom) == 0 && tmp.pass.compareTo(user.pass) == 0)
            {
                this.dispose();
                @SuppressWarnings("unused")
                MainView main = new MainView("HOTEL");
            } else
            {
                JOptionPane.showMessageDialog(null, "Données erronées", "Erreur", JOptionPane.WARNING_MESSAGE);
            }
        } else if (code == KeyEvent.VK_ESCAPE)
        {
            System.exit(DISPOSE_ON_CLOSE);
        }
    }

    public void keyReleased(KeyEvent ev)
    {
        Object ob = ev.getSource();
        if (ob.equals(nom))
        {
            tmp.nom = nom.getText();
        }

        if (ob.equals(prenom))
        {
            tmp.prenom = prenom.getText();
        }

        if (ob.equals(pass))
        {
            tmp.pass = pass.getText();
        }
    }

    public void keyTyped(KeyEvent ev)
    {

    }
}
