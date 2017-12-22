package userInterface.facturation;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import app.Background;
import app.Buttons;
import app.Report;
import manager.data.ListChambre;
import manager.data.ListClient;
import manager.data.ListFacturation;
import manager.data.ListReservation;
import manager.data.ListService;
import manager.data.ListUseService;
import bo.Client;
import bo.Facturation;
import userInterface.client.JTableClient;
import bo.Reservation;
import userInterface.Observateur;

public class PanelFacturation implements Observateur
{
    @Override
    public void update()
    {
    
    }

    public class Reset_Action implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ev)
        {
            Flush();
        }
    }

    public class OK_Action implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ev)
        {
            
        }
    }

    public class Combo_Action implements ActionListener
    {

        public void actionPerformed(ActionEvent ev)
        {
            
        }
    }

    private void Flush()
    {
        updating = false;
        field_id_cl.setText("");
        fieldTotal.setText("");
        fieldTotalRes.setText("");
        fieldTotalSer.setText("");
        comboRemise.setSelectedIndex(0);
        comboMode.setSelectedIndex(0);
    }

    public class Table_Action implements MouseListener
    {

        public void mouseClicked(MouseEvent e)
        {
            
        }

        public void mouseEntered(MouseEvent arg0)
        {
            // TODO Auto-generated method stub

        }

        public void mouseExited(MouseEvent arg0)
        {

        }

        public void mousePressed(MouseEvent arg0)
        {

        }

        public void mouseReleased(MouseEvent arg0)
        {

        }
    }

    private void updateAll()
    {
        
    }

    private Background panel;

    private Buttons bt;

    private JButton print;

    private JLabel txtTableCLient;
    private JLabel txtTableFacture;

    private JLabel txt_id_cl;
    private JTextField field_id_cl;

    private JLabel txtTotal;
    private JTextField fieldTotal;

    private JLabel txtMode;
    private JComboBox<String> comboMode;

    private JLabel txtRemise;
    private JComboBox<Integer> comboRemise;

    private JLabel txtTotalRes;
    private JTextField fieldTotalRes;

    private JLabel txtTotalSer;
    private JTextField fieldTotalSer;

    private JTableClient table;

    private Facturation facture;

    private boolean updating = false;

    private static PanelFacturation singleton = null;

    private void Build_Panel()
    {
        facture = new Facturation();
        panel = new Background();
        addContent();
        setPosition();
    }

    private PanelFacturation()
    {
        Build_Button();
        Build_Label();
        Build_TextField();
        Build_Panel();
    }

    public static PanelFacturation getHinstance()
    {
        if (singleton == null)
        {
            singleton = new PanelFacturation();
        }
        return singleton;
    }

    private void setPosition()
    {
        System.out.println("PanelFacturation: setPosition");
        int x1 = 70, x2 = 360 + 15, w1 = 220, w2 = 260, h = 20;

//		JTable Des Facturation
        JTableFacturation.getHinstance().getScroll().setBounds(x2 + 15 + 20 + w2, 30 + 15, x2 + 200, (235 - 30 + h) / 2);

        /* Le JTable des Clients */
        table.getScroll().setBounds(x2 + 15 + 20 + w2, 15 + 205, x2 + 200, (235 - 30 + h) / 2);

        /* Le label id_cl et son champe */
        txt_id_cl.setBounds(x1, 15 + 30, w1, h);
        field_id_cl.setBounds(x2, 15 + 30, w2, h);

        /* infos total réservation */
        txtTotalRes.setBounds(x1, 15 + 65, w2, h);
        fieldTotalRes.setBounds(x2, 15 + 65, w2, h);
        /* infos total service */
        txtTotalSer.setBounds(x1, 15 + 100, w1 + 60, h);
        fieldTotalSer.setBounds(x2, 15 + 100, w2, h);
        /*infos remise */
        txtRemise.setBounds(x1, 15 + 135, w1 + 100, h);
        comboRemise.setBounds(x2, 15 + 135, w2, h);
        /* infos total */
        txtTotal.setBounds(x1, 15 + 170, w1 + 100, h);
        fieldTotal.setBounds(x2, 15 + 170, w2, h);
        /* infos mode paiement */
        txtMode.setBounds(x1, 15 + 205, w1 + 100, h);
        comboMode.setBounds(x2, 15 + 205, w2, h);

        /* Positionnnement du bouton ok */
        bt.getButtons(0).setBounds(x2 + 20 - 5 - 10 - 20, 15 + 240, 48, 48);
        /* Positionnnement du bouton reset */
        bt.getButtons(1).setBounds(x2 + 100 - 5 - 10 - 20, 15 + 240, 48, 48);
        /* Positionnnement du bouton update */
//		bt.getButtons(2).setBounds(x2+180-5-10-20, 300+30, 48, 48) ;
        /* Positionnnement du bouton delete */
//		bt.getButtons(3).setBounds(x2 + 260-5-10-20, 15+ 240, 48, 48);

        txtTableCLient.setBounds(x2 + 15 + 20 + w2 + (x2 + 200 - w1) / 2, 200 - 20, w1 + 100, h);
        txtTableFacture.setBounds(x2 + 15 + 20 + w2 + (x2 + 200 - w1) / 2, 10, w1, h);
        print.setBounds(x2 + 180 - 5 - 10 - 20, 15 + 240, 48 * 2 + 10, 48);
        print.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent arg0)
            {
                Report.Generer("/home/jordy/workspace/Hotel/src/manageFacturation/facture.jrxml");

            }
        });
    }

    private void addContent()
    {
        panel.add(print);
        panel.add(txt_id_cl);
        panel.add(txtMode);
        panel.add(comboMode);
        panel.add(txtRemise);
        panel.add(txtTotal);
        panel.add(txtTotalRes);
        panel.add(txtTotalSer);
        panel.add(field_id_cl);
        panel.add(fieldTotal);
        panel.add(fieldTotalRes);
        panel.add(fieldTotalSer);
        panel.add(comboRemise);
        panel.add(bt.getButtons(0));
        panel.add(bt.getButtons(1));
        panel.add(bt.getButtons(3));
        panel.add(table.getScroll());
        panel.add(JTableFacturation.getHinstance().getScroll());
        panel.add(txtTableCLient);
        panel.add(txtTableFacture);
    }

    private void Build_Label()
    {
        String tmp1 = "<html><font color=#FEFDF0>";
        String tmp2 = "<font/></html>";
        Font font = new Font("Purisa", Font.BOLD, 16);

        txtTableFacture = new JLabel(tmp1 + "Liste des Facturés" + tmp2);
        txtTableFacture.setFont(font);

        txtTableCLient = new JLabel(tmp1 + "Liste des Clients" + tmp2);
        txtTableCLient.setFont(font);

        txt_id_cl = new JLabel(tmp1 + "N° Client: " + tmp2);
        txt_id_cl.setFont(font);

        txtRemise = new JLabel(tmp1 + "Remise: " + tmp2);
        txtRemise.setFont(font);

        txtTotal = new JLabel(tmp1 + "Total à payer: " + tmp2);
        txtTotal.setFont(font);

        txtTotalRes = new JLabel(tmp1 + "Total des réservations: " + tmp2);
        txtTotalRes.setFont(font);

        txtTotalSer = new JLabel(tmp1 + "Total des services: " + tmp2);
        txtTotalSer.setFont(font);

        txtMode = new JLabel(tmp1 + "Mode de paiement: " + tmp2);
        txtMode.setFont(font);
    }

    private void Build_TextField()
    {
        field_id_cl = new JTextField();
        field_id_cl.setEditable(false);

        fieldTotalRes = new JTextField();
        fieldTotalRes.setEditable(false);

        fieldTotalSer = new JTextField();
        fieldTotalSer.setEditable(false);

        fieldTotal = new JTextField();
        fieldTotal.setEditable(false);
    }

    private void Build_Button()
    {
        table = JTableClient.getHinstance().clone();
        table.getTable().addMouseListener(new Table_Action());
        JTableFacturation.getHinstance().getTable().addMouseListener(new Table_Action());
        bt = new Buttons();
        bt.getButtons(1).addActionListener(new Reset_Action());
        bt.getButtons(0).addActionListener(new OK_Action());
        print = new JButton("imprimer");
        Build_Combo();
    }

    private void Build_Combo()
    {
        comboRemise = new JComboBox<Integer>(new Integer[]
        {
            0, 5, 10, 12, 15, 20
        });
        comboRemise.addActionListener(new Combo_Action());
        comboMode = new JComboBox<String>(new String[]
        {
            "", "Chèque", "Virement", "Espèce"
        });
        comboMode.addActionListener(new Combo_Action());
    }

    public Background getPanel()
    {
        return panel;
    }

    public JComboBox<Integer> getCombo()
    {
        return comboRemise;
    }
}
