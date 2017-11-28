package app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView extends JFrame
{
    public class Action implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ev)
        {
            Object o = ev.getSource();
            if (o.equals(accueil))
            {
                card.show(container, "Accueil");
                return;
            }
            if (o.equals(mainApp))
            {
                card.show(container, "MainApp");
            }
            if (o.equals(quit))
            {
                System.exit(0);
            }
        }
    }

    private JPanel container;
    private JPanel panelButton;
    private JPanel mainPanel;
    private JButton quit;
    private JButton accueil;
    private JButton mainApp;
    private CardLayout card;

    private void buildButton()
    {
        accueil = new JButton("Accueil");
        quit = new JButton("Quitter");
        quit.addActionListener(new Action());
        accueil.addActionListener(new Action());
        mainApp = new JButton("Application");
        mainApp.addActionListener(new Action());
    }

    private void buildPanel()
    {
        card = new CardLayout();
        mainPanel = new JPanel(new BorderLayout());
        panelButton = new JPanel();

        container = new JPanel();
        container.setLayout(card);
    }

    private void addContent()
    {
        /* Le panel des buttons */
        panelButton.add(accueil);
        panelButton.add(mainApp);
        panelButton.add(quit);
        /* Le container */
        container.add(Welcome.getHinstance().getPanel(), "Accueil");
        container.add(MainApp.getHinstance().getTab(), "MainApp");
        /* la panel Principale */
        mainPanel.add(panelButton, BorderLayout.NORTH);
        mainPanel.add(container, BorderLayout.CENTER);
    }

    public MainView(String title)
    {
        super(title);
        Toolkit tk = Toolkit.getDefaultToolkit();
        setSize((int) tk.getScreenSize().getWidth() - 20, (int) tk.getScreenSize().getHeight() - 40);

        setVisible(true);
        setResizable(false);

        buildButton();
        buildPanel();
        addContent();

        setContentPane(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
