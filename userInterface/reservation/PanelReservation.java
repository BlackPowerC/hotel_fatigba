package userInterface.reservation;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import app.Background;
import app.Buttons;
import manager.data.ListChambre;
import manager.data.ListClient;
import manager.data.ListReservation;
import bo.Chambre;
import userInterface.chambre.JTableChambreDispo;
//import manageChambre.JTableChambreDispoModel;
import userInterface.chambre.JTableChambreTotal;
import bo.Client;
import bo.Reservation;
import userInterface.client.JTableClient;

public class PanelReservation
{

    /* OK */
    public class Table_Action extends MouseAdapter
    {
        /* Clique dans la JTable reservation */
        public void mouseClicked(MouseEvent ev)
        {
        }
    }

    /* OK */
    public class Reset_Action implements ActionListener
    {

        public void actionPerformed(ActionEvent ev)
        {
            Flush();
            updating = false;
            JOptionPane.showMessageDialog(null, "Champ réinitialisés", "OK", JOptionPane.WARNING_MESSAGE);
        }
    }

    public class ComboAction implements ActionListener
    {

        public void actionPerformed(ActionEvent ev)
        {
            
        }
    }

    /* OK */
    public class Update_Action implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            if (updating)
            {
            }
        }
    }

    public class OK_Action implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            
        }
    }

    /* OK */
    public class Delete_Action implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
        }
    }

    private int selectedRow;

    private static PanelReservation singleton = null;

    private boolean updating = false;

    private Buttons control;

    private Reservation reservation = new Reservation();

    private JLabel txtIDClient;
    private JTextField id_client;

    private JLabel txtListReservation;
    private JLabel txtListChambre;
    private JLabel txtListClient;

    private JDateChooser date_res;
    private JLabel txtdate_res;

    private JDateChooser date_debut;
    private JLabel txtdate_debut;

    private JDateChooser date_fin;
    private JLabel txtdate_fin;

    private Background panel;
    private JScrollPane scroll;
    private JScrollPane scrollClient;
    private JTableClient clientTable;

    private JTextField id_chambre;
    private JTextField txtDescription;
    private String Description;
    private JLabel labelChambre;
    private JLabel labelDescription;

    private JLabel txtEtat;
    private JComboBox<String> EtatCombo;

    private void updateAllRoom(boolean bool)
    {

    }

    private void Flush()
    {
        date_debut.setDate(null);
        date_res.setDate(null);
        date_fin.setDate(null);
//		reservation.setDate_res(null);
//		reservation.setDebut_res(null);
//		reservation.setFin_res(null);
        txtDescription.setText("");
        id_chambre.setText("");
        EtatCombo.setSelectedIndex(0);
        id_client.setText("");

        EtatCombo.removeItem("Annulée");
    }

    private void Build_Combo()
    {
        EtatCombo = new JComboBox<String>(new String[]
        {
            "", "Réservée", "Confirmée"
        });
        EtatCombo.addActionListener(new ComboAction());
    }

    private void Build_TextField()
    {
        id_chambre = new JTextField();
        id_chambre.setEditable(false);
        txtDescription = new JTextField();
        txtDescription.setEditable(false);
        id_client = new JTextField();
        id_client.setEditable(false);
    }

    private void Build_Panel()
    {
        System.out.println("addReservation: Build_Panel");
        panel = new Background();
        panel.setLayout(null);
        addContent();
        setPosition();
    }

    private void Build_Button()
    {
        control = new Buttons();
        control.getButtons(0).addActionListener(new OK_Action());
        control.getButtons(1).addActionListener(new Reset_Action());
        control.getButtons(2).addActionListener(new Update_Action());
        control.getButtons(3).addActionListener(new Delete_Action());
    }

    private void Build_Scroll()
    {
        clientTable = JTableClient.getHinstance().clone();
        scroll = JTableReservation.getHinstance().getScroll();
        scrollClient = clientTable.getScroll();
        JTableChambreDispo.getHinstance().getTable().addMouseListener(new Table_Action());
        clientTable.getTable().addMouseListener(new Table_Action());
        JTableReservation.getHinstance().getTable().addMouseListener(new Table_Action());
    }

    private void addContent()
    {
        System.out.println("PanelReservation: addContent");
        panel.add(id_chambre);
        panel.add(txtIDClient);
        panel.add(id_client);
        panel.add(txtEtat);
        panel.add(EtatCombo);
        panel.add(labelChambre);
        panel.add(labelDescription);
        panel.add(txtDescription);
        panel.add(date_debut);
        panel.add(date_res);
        panel.add(txtdate_res);
        panel.add(txtdate_debut);
        panel.add(date_fin);
        panel.add(txtdate_fin);
        panel.add(txtListChambre);
        panel.add(txtListReservation);
        panel.add(txtListClient);
        for (int i = 0; i < 4; i++)
        {
            panel.add(control.getButtons(i));
        }
        panel.add(scroll);
        panel.add(scrollClient);
        panel.add(JTableChambreDispo.getHinstance().getScroll());
    }

    private void Build_Label()
    {
        System.out.println("PanelReservation: Build_Label");
        String tmp1 = "<html><font color=#FEFDF0>";
        String tmp2 = "<font/></html>";
        Font font = new Font("Purisa", Font.BOLD, 16);

        txtListClient = new JLabel(tmp1 + "Liste des clients" + tmp2);
        txtListClient.setFont(font);

        txtIDClient = new JLabel(tmp1 + "N° Client: " + tmp2);
        txtIDClient.setFont(font);

        txtEtat = new JLabel(tmp1 + "Etat de la Réservation: " + tmp2);
        txtEtat.setFont(font);

        txtListChambre = new JLabel(tmp1 + "Liste des chambres disponibles" + tmp2);
        txtListChambre.setFont(font);

        txtListReservation = new JLabel(tmp1 + "Liste des réservations" + tmp2);
        txtListReservation.setFont(font);

        labelDescription = new JLabel(tmp1 + "Description: " + tmp2);

        labelChambre = new JLabel(tmp1 + "Chambre n°: " + tmp2);
        labelChambre.setFont(font);

        txtdate_res = new JLabel(tmp1 + "Date de Réservation: " + tmp2);
        txtdate_res.setFont(font);
        txtdate_debut = new JLabel(tmp1 + "Date de début de résevation: "
                + tmp2);
        txtdate_debut.setFont(font);
        txtdate_fin = new JLabel(tmp1 + "Date de fin de résevation: " + tmp2);
        txtdate_fin.setFont(font);

        txtDescription.setFont(font);
        labelDescription.setFont(font);
    }

    private void Build_Calendar()
    {
        System.out.println("addReservation: Build_Calendar");
        date_res
                = new JDateChooser();
        date_fin = new JDateChooser();
        date_debut = new JDateChooser();
        date_res.setDateFormatString("yyyy-MM-dd");
        date_fin.setDateFormatString("yyyy-MM-dd");
        date_debut.setDateFormatString("yyyy-MM-dd");
//		date_fin.addMouseListener(new DateChooser_Action());
//		date_fin.addMouseListener(new DateChooser_Action());
    }

    private void setPosition()
    {
        System.out.println("PanelReservation: setPosition");
        int x1 = 70, x2 = 360 + 15, w1 = 220, w2 = 260, h = 20;
        /* Les labels pour les JTables */
        txtListChambre.setBounds(x2 + 15 + 20 + w2 + (x2 + 200 - w1 - 100) / 2, 200 - 20, w1 + 100, h);
        txtListReservation.setBounds(x2 + 15 + 20 + w2 + (x2 + 200 - w1) / 2, 10, w1, h);
        txtListClient.setBounds(x2 + 15 + 20 + w2 + (x2 + 200 - w1) / 2, 200 - 40 + 200, w1, h);

        scroll.setBounds(x2 + 15 + 20 + w2, 30 + 15, x2 + 200, (235 - 30 + h) / 2);
        JTableChambreDispo.getHinstance().getScroll().setBounds(x2 + 15 + 20 + w2, 15 + 200, x2 + 200, (235 - 30 + h) / 2);

        scrollClient.setBounds(x2 + 15 + 20 + w2, 15 + 270 + (235 - 30 + h) / 2, x2 + 200, (235 - 30 + h) / 2);
        /* Infos chambre */
        labelChambre.setBounds(x1, 15 + 30, w1, h);
        id_chambre.setBounds(x2, 15 + 30, w2, h);
        /* Infos desctiption chambre */
        labelDescription.setBounds(x1, 15 + 65, w2, h);
        txtDescription.setBounds(x2, 15 + 65, w2, (h * 2) + 20);
        /* Infos date Reservation */
        txtdate_res.setBounds(x1, 15 + 130 + 10, w1 + 60, h);
        date_res.setBounds(x2, 15 + 130 + 10, w2, h);
        /* Infos debut resarvation */
        txtdate_debut.setBounds(x1, 15 + 165 + 10, w1 + 100, h);
        date_debut.setBounds(x2, 15 + 165 + 10, w2, h);
        /* Infos fin reservation */
        txtdate_fin.setBounds(x1, 15 + 200 + 10, w1 + 100, h);
        date_fin.setBounds(x2, 15 + 200 + 10, w2, h);

        /* L'état de la réservation */
        txtEtat.setBounds(x1, 15 + 235 + 10, w1 + 100, h);
        EtatCombo.setBounds(x2, 15 + 235 + 10, w2, h);
        /* L'ID du client */
        txtIDClient.setBounds(x1, 15 + 270 + 10, w1 + 100, h);
        id_client.setBounds(x2, 15 + 270 + 10, w2, h);

        /* positionnement du bouton recherche */
 /* Positionnnement du bouton ok */
        control.getButtons(0).setBounds(x2 + 20 - 5 - 10 - 20, 300 + 15 + 15, 48, 48);
        /* Positionnnement du bouton reset */
        control.getButtons(1).setBounds(x2 + 100 - 5 - 10 - 20, 300 + 15 + 15, 48, 48);
        /* Positionnnement du bouton update */
        control.getButtons(2).setBounds(x2 + 180 - 5 - 10 - 20, 300 + 30, 48, 48);
        /* Positionnnement du bouton delete */
        control.getButtons(3).setBounds(x2 + 260 - 5 - 10 - 20, 300 + 15 + 15, 48, 48);
    }

    private void Build_All()
    {
        System.out.println("PanelReservation: Build_All");
        Build_Combo();
        Build_TextField();
        Build_Scroll();
        Build_Button();
        Build_Label();
        Build_Calendar();
        Build_Panel();
    }

    private PanelReservation()
    {
        Build_All();
    }

    public static PanelReservation getHinstance()
    {
        if (singleton == null)
        {
            singleton = new PanelReservation();
        }
        return singleton;
    }

    public Background getPanel()
    {
        System.out.println("PanelReservation: getPanel");
        return panel;
    }

    public JTableClient getClientTable()
    {
        return clientTable;
    }

}
