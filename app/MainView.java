/*package app;

import manageClient.PanelClient;
import manageReservation.PanelReservation;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainView extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private JPanel container ;
	private CardLayout card ;
	private JButton clientPanel ;
	private JButton reservationPanel ;
	private JPanel buttonPanel ;
	private JPanel contentPanel ;
	private Toolkit tk ;
	
	private void Build_Button()
	{
		clientPanel = new JButton("Client") ;
//		clientPanel.setSize(22, 100);
		clientPanel.addActionListener(this) ;
		reservationPanel = new JButton("Reservation") ;
//		reservationPanel.setBounds(0, 0, 22, 100);
		reservationPanel.addActionListener(this) ;
	}
	
	private void addContent()
	{
		buttonPanel.add(clientPanel, BorderLayout.WEST) ;
		buttonPanel.add(reservationPanel, BorderLayout.EAST) ;
	}
	
	private void Build_ButtonPanel()
	{
		buttonPanel = new JPanel(new BorderLayout()) ;
		Build_Button() ;
		addContent() ;
	}
	
	public MainView(String title)
	{
		super(title);
		setVisible(true);
		setResizable(false);
		tk = Toolkit.getDefaultToolkit() ;
		
		Build_ButtonPanel() ;
		
		setSize((int)tk.getScreenSize().getWidth()-20, (int) tk.getScreenSize().getHeight()-40);
		
		card = new CardLayout() ;
		container = new JPanel(new BorderLayout()) ;
		contentPanel = new JPanel(card) ;
		contentPanel.add(PanelClient.getHinstance().getPanel(), "client");
		contentPanel.add(PanelReservation.getHinstance().getPanel(), "reservation");
		container.add(buttonPanel, BorderLayout.NORTH);
		container.add(contentPanel, BorderLayout.CENTER) ;

		setContentPane(container);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent ev)
	{
		Object ob = ev.getSource() ;
		if(ob.equals(clientPanel))
		{
			card.show(contentPanel, "client");
		}
		if(ob.equals(reservationPanel))
		{
			card.show(contentPanel, "reservation");
		}
	}
}
 */
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
