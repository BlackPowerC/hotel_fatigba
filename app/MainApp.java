package app;

import javax.swing.JTabbedPane;

import manageChambre.PanelChambre;
import userInterface.client.PanelClient;
import manageFacturation.PanelFacturation;
import userInterface.reservation.PanelReservation;
import userInterface.consommation.PanelConsommation;

public class MainApp
{

    private static MainApp singleton;
    private JTabbedPane container;

    public static MainApp getHinstance()
    {
        if (singleton == null)
        {
            singleton = new MainApp();
        }
        return singleton;
    }

    private MainApp()
    {
        Build();
    }

    public JTabbedPane getTab()
    {
        return container;
    }

    private void Build()
    {
        container = new JTabbedPane(JTabbedPane.TOP);

        container.add("Client", PanelClient.getHinstance().getPanel());
        container.add("Réservation", PanelReservation.getHinstance().getPanel());
        container.add("Service", PanelConsommation.getHinstance().getPanel());
        container.add("Chambre", PanelChambre.getHinstance().getPanel());
        container.add("Facturation", PanelFacturation.getHinstance().getPanel());
    }
}
