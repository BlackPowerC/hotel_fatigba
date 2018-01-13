package main.java.app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView extends JFrame
{
    private JPanel container;
    private JPanel mainPanel;
    private CardLayout card;

    private void buildPanel()
    {
        card = new CardLayout();
        mainPanel = new JPanel(new BorderLayout());
        container = new JPanel();
        container.setLayout(card);
    }

    private void addContent()
    {
        /* Le container */
        container.add(MainApp.getHinstance().getTab(), "MainApp");
        mainPanel.add(container, BorderLayout.CENTER);
    }

    public MainView(String title)
    {
        super(title);
        Toolkit tk = Toolkit.getDefaultToolkit();
        setSize((int) tk.getScreenSize().getWidth() - 20, (int) tk.getScreenSize().getHeight() - 40);

        setVisible(true);
        setResizable(false);

        buildPanel();
        addContent();
        
        this.setJMenuBar(Menu.getInstance().getMenu());
        setContentPane(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
