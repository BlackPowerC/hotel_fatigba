package Auth;

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
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import app.Background;
import app.DataBaseCon;

@SuppressWarnings("serial")
public class ConnectionView extends JFrame implements ActionListener, KeyListener
{

    private final Toolkit tk = Toolkit.getDefaultToolkit();
    private JButton ok;
    private JButton annuler;
    private JLabel label;
    private Background fond;

    private JLabel txtDataBaseName;
    private JTextField DataBaseName;
    private JLabel txtPass;
    private JPasswordField pass;
    private JLabel txtNom;
    private JTextField nom;

    public ConnectionView()
    {
        Build_Button();
        Build_Label();
        Build_TextField();
        Build_Panel();

        Dimension dm = new Dimension(tk.getScreenSize());

        setVisible(true);
        setResizable(false);
        setLocation((dm.width - 600) / 2, (dm.height - 480) / 2);
        setTitle("Connection à la BD");

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
        pass = new JPasswordField("dalida");
        pass.addKeyListener(this);
        DataBaseName = new JTextField("hotel1");
        DataBaseName.addKeyListener(this);
        nom = new JTextField("jordy");
        nom.addKeyListener(this);
    }

    private void Build_Label()
    {
        Font police = new Font("Purisa", Font.BOLD, 18);
        String str = "<html><font color=#FEFDF0>Connection à la base de données</font></html>";
        label = new JLabel(str);
        label.setFont(police);
        label.setBackground(Color.DARK_GRAY);

        txtPass = new JLabel("<html><font color=#FEFDF0>Mot de passe</font></html>");
        txtPass.setFont(police);
        txtNom = new JLabel("<html><font color=#FEFDF0>Utilisateur</font></html>");
        txtNom.setFont(police);
        txtDataBaseName = new JLabel("<html><font color=#FEFDF0>Base de données</font></html>");
        txtDataBaseName.setFont(police);
    }

    private void setPosition()
    {
        int w = 220, h = 30, x = (600 - w) / 2;
        txtNom.setBounds(x, 60, w, h);
        nom.setBounds(x, 90, w, h);
        txtDataBaseName.setBounds(x, 140, w, h);
        DataBaseName.setBounds(x, 170, w, h);
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
        fond.add(DataBaseName);
        fond.add(pass);
        fond.add(txtNom);
        fond.add(txtDataBaseName);
        fond.add(txtPass);
        fond.add(ok);
        fond.add(annuler);

        getContentPane().add(fond);
    }

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
                DataBaseCon.getHinstance(DataBaseName.getText(), nom.getText(), pass.getText());
                AuthView c = new AuthView();
                this.dispose();
            } catch (SQLException e)
            {

                JOptionPane.showMessageDialog(null,
                        "Impossible de se connecter à la base !",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                //e.printStackTrace();
            }
        }
    }

    public void keyPressed(KeyEvent ev)
    {
        int code = ev.getKeyCode();
        if (code == KeyEvent.VK_ENTER)
        {
            try
            {
                DataBaseCon.getHinstance(DataBaseName.getText(), nom.getText(), pass.getText());
                AuthView c = new AuthView();
                this.dispose();
            } catch (SQLException e)
            {

                JOptionPane.showMessageDialog(null,
                        "Impossible de se connecter à la base !",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                //e.printStackTrace();
            }
        } else if (code == KeyEvent.VK_ESCAPE)
        {
            System.exit(DISPOSE_ON_CLOSE);
        }
    }

    public void keyReleased(KeyEvent ev)
    {

    }

    public void keyTyped(KeyEvent ev)
    {

    }
}
