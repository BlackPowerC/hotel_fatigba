package main.java.gui.consommation;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import main.java.app.Background;
import main.java.app.Buttons;
import main.java.bo.Consommation;
import main.java.bo.Service;
import main.java.core.Message;
import main.java.manager.data.ListClient;
import main.java.manager.data.ListUseService;
import main.java.bo.Client;
import main.java.manager.ConsommationManager;
import main.java.manager.FactoryManager;
import main.java.gui.Observateur;
import main.java.gui.client.JTableClient;

public class PanelConsommation implements Observateur
{
    /* Trois JTable
	 * 1-	Clients
	 * 2-	Service disponible
	 * 3-	Service Consommé
	 * */
    public class TableAction extends MouseAdapter
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
                id_cl_field.setText("" + tmp.getId());
                us.setClient(tmp);
            }

            /* Le JTable des Services Disponibles */
            if (ob.equals(JTableService.getHinstance().getTable()))
            {
                row = JTableService.getHinstance().getTable().getSelectedRow();
                ser = JTableService.getHinstance().getModel().getValueAt(row);
                id_ser_field.setText("" + ser.getId());
                desc_field.setText(ser.getDescription());
                prix_field.setText("" + ser.getPrix());
                us.setService(ser);
            }

            /* Le JTables des services consommées */
            if (ob.equals(JTableUseService.getHinstance().getTable()))
            {
                updating = true;
                row = JTableUseService.getHinstance().getTable().getSelectedRow();
                us = JTableUseService.getHinstance().getModel().getValueAt(row);
                /* on remplit touts les champs */
                id_ser_field.setText("" + us.getService().getId());
                desc_field.setText(us.getService().getDescription());
                prix_field.setText("" + us.getService().getPrix());
                id_cl_field.setText("" + us.getClient().getId());
            }
        }
    }

    public class BtnResetAction implements ActionListener
    {

        public void actionPerformed(ActionEvent ev)
        {
            Flush();
            Message.information("Champ réinitialisés !");
            updating = false;
        }
    }

    public class BtnOKAction implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            if (updating)
            {
                return;
            }
            
            if(!us.isValid())
            {
                Message.warning("Certaines données sont incorrectes !") ;
                return ;
            }
            
            if (ListUseService.getHinstance().getList().size() == 0)
            {
                us.setId(1) ;
            }
            else
            {
                us.setId(ListUseService.getHinstance().getLast().getId()+1);
            }
            ListUseService.getHinstance().getList().add(new Consommation(us));

            if(((ConsommationManager) FactoryManager.getInstance()
                    .getManager(FactoryManager.CONSOMMATION_MANAGER))
                        .insert(us))
            {
                JTableUserServiceModel mdl = JTableUseService.getHinstance().getModel();
                mdl.addRow();
            }
            else
            {
                Message.warning("Impossible de faire une insertion !") ;
                return ;
            }

            Flush();
            update();
            Message.information("Consommation enregistrée !");
            updating = false;
        }
    }

    public class BtnDeleteAction implements ActionListener
    {

        public void actionPerformed(ActionEvent ev)
        {
            if (updating)
            {
                int row = JTableUseService.getHinstance().getTable().getSelectedRow();
//                us = ListUseService.getHinstance().getList().get(row);

                /* On supprimme dans la BD */
                ((ConsommationManager) FactoryManager.getInstance()
                        .getManager(FactoryManager.CONSOMMATION_MANAGER))
                            .delete(row) ;

                /* Dans l'application */
                JTableUserServiceModel mdl = JTableUseService.getHinstance().getModel();
                mdl.remove(row);
                ListUseService.getHinstance().getList().remove(row);
                /* Le Dernier Service consommé */
                if (ListUseService.getHinstance().getList().size() > 1)
                {
                    ListUseService.getHinstance()
                            .setLast(ListUseService.getHinstance()
                                    .getList().get(mdl.getRowCount() - 1));
                }
                Flush();

                updating = false;
//				bt.getButtons(0).enable();
                Message.information("Données supprimées !");
            }
        }
    }

    public class BtnUpdateAction implements ActionListener
    {

        public void actionPerformed(ActionEvent ev)
        {
            if (updating)
            {   
                if(!us.isValid())
                {
                    Message.warning("Certaines données sont incorrectes !") ;
                }
                if(((ConsommationManager) FactoryManager.getInstance()
                        .getManager(FactoryManager.CONSOMMATION_MANAGER))
                            .update(us) != -1)
                {
                    int row = JTableUseService.getHinstance().getTable().getSelectedRow();
                    ListUseService.getHinstance().getList().get(row).setConsommation(us);
                }
                else
                {
                    Message.warning("Impossible de faire une mise à jour !") ;
                    return  ;
                }
                
                Flush();
                update() ;
                Message.information("MAJ des données effectué !");

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
    private Consommation us;

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

    private void buildPanel()
    {
        panel = new Background();
        panel.setLayout(null);
    }

    private void buildButton()
    {

        bt = new Buttons();
        bt.getButtons(0).addActionListener(new BtnOKAction());
    }

    private void buildTable()
    {
        JTableService.getHinstance().getTable().addMouseListener(new TableAction());
        JTableUseService.getHinstance().getTable().addMouseListener(new TableAction());

        clientTable = JTableClient.getHinstance().clone();
        scroll_client = clientTable.getScroll();
        clientTable.getTable().addMouseListener(new TableAction());
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

    private void buildLabel()
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
        us = new Consommation();
        ser = new Service();
        buildPanel();
        buildButton();
        buildLabel();
        buildTable();
        buildTextField();
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
        bt.getButtons(2).addActionListener(new BtnUpdateAction());
        /* Positionnnement du bouton reset */
        bt.getButtons(1).setBounds(x2 + 100 - 5 - 10 - 20, 15 + 165 + 15 + h + 10, 48, 48);
        bt.getButtons(1).addActionListener(new BtnResetAction());
        /* Positionnnement du bouton update */
        bt.getButtons(2).setBounds(x2 + 180 - 5 - 10 - 20, 15 + 165 + 15 + h + 10, 48, 48);
        /* Positionnnement du bouton delete */
        bt.getButtons(3).setBounds(x2 + 260 - 5 - 10 - 20, 15 + 165 + 15 + h + 10, 48, 48);
        bt.getButtons(3).addActionListener(new BtnDeleteAction());

        txtTableUse.setBounds(x2 + 15 + 20 + w2 + (x2 + 200 - w1) / 2, 10, w1, h);
    }

    public void buildTextField()
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
    
    @Override
    public void update()
    {
        panel.remove(JTableUseService.getHinstance().getScroll());
        panel.add(JTableUseService.getHinstance().getScroll());
        panel.revalidate();
    }
}
