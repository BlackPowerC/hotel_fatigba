package userInterface.consommation;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import app.Background;
import app.Buttons;
import app.DataBaseCon;
import manager.data.ListClient;
import manager.data.ListUseService;
import manageClient.Client;
import userInterface.client.JTableClient;

public class PanelConsommation
{

    /* Trois JTable
	 * 1-	Clients
	 * 2-	Service disponible
	 * 3-	Service Consommé
	 * */
    public class Table_Action extends MouseAdapter
    {

        public void mouseClicked(MouseEvent ev)
        {
            int row = 0;
            Object ob = ev.getSource();

            /* Le JTable CLient */
            if (ob.equals(clientTable.getTable()))
            {
                row = clientTable.getTable().getSelectedRow();
                Client tmp = ListClient.getHinstance().getListClient().get(row);
                id_cl_field.setText("" + tmp.getM_id_client());
                us.setNom_prenom(tmp.getM_nom() + " " + tmp.getM_prenom());
            }

            /* Le JTable des Services Disponibles */
            if (ob.equals(JTableService.getHinstance().getTable()))
            {
                row = JTableService.getHinstance().getTable().getSelectedRow();
                ser = JTableService.getHinstance().getModel().getValueAt(row);
                id_ser_field.setText("" + ser.getId_ser());
                desc_field.setText(ser.getDescription());
                prix_field.setText("" + ser.getPrix());
            }

            /* Le JTables des services consommées */
            if (ob.equals(JTableUseService.getHinstance().getTable()))
            {
                updating = true;
                row = JTableUseService.getHinstance().getTable().getSelectedRow();
                us = JTableUseService.getHinstance().getModel().getValueAt(row);
                /* on remplit touts les champs */
                id_ser_field.setText("" + us.getId_ser());
                desc_field.setText(us.getDesc_service());
                prix_field.setText("" + us.getPrix());
                id_cl_field.setText("" + us.getId_cl());
            }
        }
    }

    public class Reset_Action implements ActionListener
    {

        public void actionPerformed(ActionEvent ev)
        {
            Flush();
            JOptionPane.showMessageDialog(null, "Champ réinitialisés", "OK", JOptionPane.WARNING_MESSAGE);
            updating = false;
        }
    }

    public class OK_Action implements ActionListener
    {

        public void fatalError()
        {
            JOptionPane.showMessageDialog(null,
                    "Tout les champs sont requis", "Erreur",
                    JOptionPane.WARNING_MESSAGE);
        }

        public void noError()
        {
            JOptionPane.showMessageDialog(null,
                    "Tous les Champs sont bien remplis", "OK",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        public void actionPerformed(ActionEvent ev)
        {
            if (updating)
            {
                return;
            }
            if (id_cl_field.getText().equals(null) || id_cl_field.getText().compareTo("") == 0)
            {
                fatalError();
                return;
            }
            if (id_ser_field.getText().equals(null) || id_ser_field.getText().compareTo("") == 0)
            {
                fatalError();
                return;
            }

            /* id du service */
            us.setId_ser(Integer.parseInt(id_ser_field.getText()));
            /* id du client */
            us.setId_cl(Integer.parseInt(id_cl_field.getText()));
            /* description */
            us.setDesc_service(desc_field.getText());
            /* prix */
            us.setPrix(java.lang.Float.parseFloat(prix_field.getText()));
            /* nom et prénom */

            if (ListUseService.getHinstance().getList().size() == 0)
            {
                us.setId_us(1);
            } else
            {

                us.setId_us(ListUseService.getHinstance().getLast().getId_us() + 1);
                System.out.println("Last UseService id: " + ListUseService.getHinstance().getLast().getId_us());
                System.out.println("Current UseService id: " + us.getId_us());
            }
            ListUseService.getHinstance().setLast(us);
            ListUseService.getHinstance().getList().add(new UseService(us));

            String req = us.insertSQL();
            System.out.println("PanelService: " + req);
            try
            {
                DataBaseCon.getHinstance().updateQuery(req);
            } catch (SQLException e)
            {
                e.printStackTrace();
            }

            JTableUserServiceModel mdl = JTableUseService.getHinstance().getModel();
            mdl.addRow();

            Flush();
            updating = false;
            noError();
        }
    }

    public class Delete_Action implements ActionListener
    {

        public void actionPerformed(ActionEvent ev)
        {
            if (updating)
            {
                int row = JTableUseService.getHinstance().getTable().getSelectedRow();
                us = ListUseService.getHinstance().getList().get(row);
                String req = us.deleteSQL();

                /* On supprimme dans la BD */
                try
                {
                    DataBaseCon.getHinstance().updateQuery(req);
                } catch (SQLException ex)
                {
                    ex.printStackTrace();
                }

                /* Dans l'application */
                JTableUserServiceModel mdl = JTableUseService.getHinstance().getModel();
                mdl.remove(row);
                ListUseService.getHinstance().getList().remove(row);
                /* Le Dernier Service consommé */
                if (ListUseService.getHinstance().getList().size() > 1)
                {
                    ListUseService.getHinstance().setLast(ListUseService.getHinstance().getList().get(mdl.getRowCount() - 1));
                }
                Flush();

                updating = false;
//				bt.getButtons(0).enable();
                JOptionPane.showMessageDialog(null,
                        "Données supprimées !", "OK",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public class Update_Action implements ActionListener
    {

        public void actionPerformed(ActionEvent ev)
        {
            if (updating)
            {
                int row = JTableUseService.getHinstance().getTable().getSelectedRow();
                us.setId_cl(Integer.parseInt(id_cl_field.getText()));
                us.setDesc_service(desc_field.getText());
                us.setPrix(Float.parseFloat(prix_field.getText()));
                us.setId_cl(Integer.parseInt(id_cl_field.getText()));

                Client tmp = new Client();
                Iterator<Client> it = ListClient.getHinstance().getListClient().iterator();
                while (it.hasNext() && tmp.getM_id_client() != us.getId_cl())
                {
                    tmp = it.next();
                }
                us.setNom_prenom(tmp.getM_nom() + " " + tmp.getM_prenom());

                ListUseService.getHinstance().getList().get(row).setUseService(us);

                String req = us.updateSQL();
                try
                {
                    System.out.println(req);
                    DataBaseCon.getHinstance().updateQuery(req);
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }

                panel.remove(JTableUseService.getHinstance().getScroll());
                panel.add(JTableUseService.getHinstance().getScroll());
                panel.revalidate();

                Flush();

                JOptionPane.showMessageDialog(null,
                        "MAJ des données effectué !", "OK",
                        JOptionPane.INFORMATION_MESSAGE);

                updating = false;
            }
        }
    }

    private Background panel;

    private JScrollPane scroll_client;

    private JLabel txtListClient;
    private JTableClient clientTable;

    private JLabel txtTableUse;

    private JTextField id_cl_field;
    private JLabel txt_id_cl;
    private JTextField id_ser_field;
    private JLabel txt_id_ser;

    private JTextField desc_field;
    private JLabel txt_desc;

    private JTextField prix_field;
    private JLabel txt_prix;

    private Buttons bt;
    private UseService us;

    private JLabel txtListService;
    private Service ser;

    private boolean updating = false;

    private static PanelConsommation singleton = null;

    private void Flush()
    {
        id_cl_field.setText(null);
        id_ser_field.setText(null);
        prix_field.setText(null);
        desc_field.setText(null);
    }

    private void Build_Panel()
    {
        panel = new Background();
        panel.setLayout(null);
    }

    private void Build_Button()
    {

        bt = new Buttons();
        bt.getButtons(0).addActionListener(new OK_Action());
    }

    private void Build_Table()
    {
        JTableService.getHinstance().getTable().addMouseListener(new Table_Action());
        JTableUseService.getHinstance().getTable().addMouseListener(new Table_Action());

        clientTable = JTableClient.getHinstance().clone();
        scroll_client = clientTable.getScroll();
        clientTable.getTable().addMouseListener(new Table_Action());
    }

    private void addContent()
    {

        panel.add(txt_desc);
        panel.add(txt_id_cl);
        panel.add(txt_id_ser);
        panel.add(txt_prix);
        panel.add(desc_field);
        panel.add(id_cl_field);
        panel.add(id_ser_field);
        panel.add(prix_field);
        panel.add(txtListClient);
        panel.add(txtListService);
        for (int i = 0; i < 5; i++)
        {
            panel.add(bt.getButtons(i));
        }
        panel.add(scroll_client);
        panel.add(JTableUseService.getHinstance().getScroll());
        panel.add(JTableService.getHinstance().getScroll());
        panel.add(txtTableUse);
    }

    private void Build_Label()
    {
        String tmp1 = "<html><font color=#FEFDF0>";
        String tmp2 = "<font/></html>";
        Font font = new Font("Purisa", Font.BOLD, 16);

        txtTableUse = new JLabel(tmp1 + "Liste des Consomateurs" + tmp2);
        txtTableUse.setFont(font);

        txtListService = new JLabel(tmp1 + "Liste des services" + tmp2);
        txtListService.setFont(font);

        txtListClient = new JLabel(tmp1 + "Liste des clients" + tmp2);
        txtListClient.setFont(font);

        txt_desc = new JLabel(tmp1 + "Description: " + tmp2);
        txt_desc.setFont(font);

        txt_id_cl = new JLabel(tmp1 + "N° Client: " + tmp2);
        txt_id_cl.setFont(font);

        txt_id_ser = new JLabel(tmp1 + "N° Service: " + tmp2);
        txt_id_ser.setFont(font);

        txt_prix = new JLabel(tmp1 + "Prix: " + tmp2);
        txt_prix.setFont(font);
    }

    public static PanelConsommation getHinstance()
    {
        if (singleton == null)
        {
            singleton = new PanelConsommation();
        }
        return singleton;
    }

    private PanelConsommation()
    {
        us = new UseService();
        ser = new Service();
        Build_Panel();
        Build_Button();
        Build_Label();
        Build_Table();
        Build_TextField();
        addContent();
        setPosition();
    }

    private void setPosition()
    {
        int x1 = 70, x2 = 360 + 15, w1 = 220, w2 = 260, h = 20;

        /* Le formulaire */
        // Numero de service
        txt_id_ser.setBounds(x1, 15 + 30, w1, h);
        id_ser_field.setBounds(x2, 15 + 30, w2, h);
        // Description
        txt_desc.setBounds(x1, 15 + 65, w2, h);
        desc_field.setBounds(x2, 15 + 65, w2, (h * 2) + 20);
        // Le prix
        txt_prix.setBounds(x1, 15 + 130 + 10, w1 + 60, h);
        prix_field.setBounds(x2, 15 + 130 + 10, w2, h);
        // Le n° de client
        txt_id_cl.setBounds(x1, 15 + 165 + 10, w1 + 100, h);
        id_cl_field.setBounds(x2, 15 + 165 + 10, w2, h);

        /* Les JTables */
        // Liste des Services Consommées
        JTableUseService.getHinstance().getScroll().setBounds(x2 + 15 + 20 + w2, 30 + 15, x2 + 200, (235 - 30 + h) / 2);
        // Liste Des Services Diponibles
        txtListService.setBounds(x2 + 15 + 20 + w2 + (x2 + 200 - w1) / 2, 200 - 20, w1 + 100, h);
        JTableService.getHinstance().getScroll().setBounds(x2 + 15 + 20 + w2, 15 + 200, x2 + 200, (235 - 30 + h) / 2);
        // Liste des Clients
        txtListClient.setBounds(x2 + 15 + 20 + w2 + (x2 + 200 - w1) / 2, 200 - 40 + 200, w1, h);
        scroll_client.setBounds(x2 + 15 + 20 + w2, 15 + 270 + (235 - 30 + h) / 2, x2 + 200, (235 - 30 + h) / 2);

        /* Les boutons d'actions */
 /* positionnement du bouton recherche */
 /* Positionnnement du bouton ok */
        bt.getButtons(0).setBounds(x2 + 20 - 5 - 10 - 20, 15 + 165 + 15 + h + 10, 48, 48);
        bt.getButtons(2).addActionListener(new Update_Action());
        /* Positionnnement du bouton reset */
        bt.getButtons(1).setBounds(x2 + 100 - 5 - 10 - 20, 15 + 165 + 15 + h + 10, 48, 48);
        bt.getButtons(1).addActionListener(new Reset_Action());
        /* Positionnnement du bouton update */
        bt.getButtons(2).setBounds(x2 + 180 - 5 - 10 - 20, 15 + 165 + 15 + h + 10, 48, 48);
        /* Positionnnement du bouton delete */
        bt.getButtons(3).setBounds(x2 + 260 - 5 - 10 - 20, 15 + 165 + 15 + h + 10, 48, 48);
        bt.getButtons(3).addActionListener(new Delete_Action());

        txtTableUse.setBounds(x2 + 15 + 20 + w2 + (x2 + 200 - w1) / 2, 10, w1, h);
    }

    public void Build_TextField()
    {
        id_cl_field = new JTextField();
        id_cl_field.setEditable(false);

        id_ser_field = new JTextField();
        id_ser_field.setEditable(false);

        desc_field = new JTextField();
        desc_field.setEditable(false);

        prix_field = new JTextField();
        prix_field.setEditable(false);
    }

    public Background getPanel()
    {
        return panel;
    }
}
