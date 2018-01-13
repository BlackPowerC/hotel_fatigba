package main.java.userInterface.client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import main.java.app.Background;
import main.java.app.Buttons;
import main.java.app.JSearch;
import main.java.app.Report;
import main.java.bo.Client;
import com.toedter.calendar.JDateChooser;
import main.java.core.Message;
import main.java.manager.ClientManager;
import main.java.manager.FactoryManager;
//import app.Report;
import main.java.manager.data.ListClient;
import main.java.manager.data.ListNation;
import main.java.userInterface.Observateur;

public class PanelClient implements Observateur
{
    @Override
    public void update()
    {
        panel.remove(scroll);
        panel.add(scroll);
        panel.revalidate();
    }

    /* OK */
    public class Search_Action implements KeyListener
    {
        public void keyPressed(KeyEvent arg0)
        {

        }

        public void keyReleased(KeyEvent arg0)
        {
            String key = search.getSearch_field().getText();
            System.out.println("PanelClient: Search_Action: " + key);
            Search_Client(key);
        }

        public void keyTyped(KeyEvent arg0)
        {

        }
    }

    public class Print_Action implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            Report.Generer("/home/jordy/workspace/Hotel/src/manageClient/Clients.jrxml");
        }
    }

    public class BtnOkAction implements ActionListener
    {
        public void setClientInformation()
        {
            client.setNom(nom_field.getText()) ;
            client.setPrenom(prenom_field.getText());
            client.setEmail(email_field.getText());
            /* Sexe */
            client.getSexe().setId(sex_list.getSelectedIndex());
            client.getSexe().setDescription(sex_list.getSelectedItem().toString());
            /* Nationalite */
            client.getNation().setId(nation_list.getSelectedIndex());
            client.getNation().setDescription(nation_list.getSelectedItem().toString());
            /* Typeclient */
            client.getType().setId(sex_list.getSelectedIndex());
            client.getType().setDescription(sex_list.getSelectedItem().toString());
            /* fidélité et étranger */
            client.setEtranger(true) ;
            client.setFidelite(fidYes.isSelected()) ;
            client.setDateNaissance(new java.sql.Date(dateChooser.getDate().getTime())) ;
            /*
            * Récupération de l'id du dernier client de la liste pour créer
            * l'id du nouveau client dans la liste
            */
            if (ListClient.getHinstance().getListClient().size() == 0)
            {
                client.setId(1);
            } else
            {
                client.setId(ListClient.getHinstance().getLastClient().getId() + 1);
            }
        }
        public void actionPerformed(ActionEvent action)
        {
            /* On vérifie la validité de tout les champs */
            if (updating)
            {
                controls.getButtons(0).disable();
                //JOptionPane.showMessageDialog(null, "Ces données sont pour une mise à jour !", "Erreur", JOptionPane.WARNING_MESSAGE) ;
                return;
            }
            /* Récupération des informations */
            setClientInformation();
            if (!client.isValid())
            {
                Message.warning("Certaines informations ne sont pas valides !");
                return;
            }
            else
            {
                /* Récupération du model de donnée */
                JTableClientModel mdl = JTableClient.getHinstance().getModel();
                mdl.addRow();

                /* Connection à la base de données pour y mettre des données */
                ((ClientManager) FactoryManager.getInstance().getManager(FactoryManager.CLIENT_MANAGER)).insert(client) ;
                
                    /* Ajout du nouveau client */
                    ListClient.getHinstance().getListClient().add(new Client(client));
                    Message.information("Tous les Champs sont bien remplis");
                
            }
            /* Mise à jour de l'affichage */
            update();
            updating = false;
            /* Effacement des champs du formulaire */
            Flush();
            System.out.println(BtnOkAction.class.getName()+" : actionPerformed");
        }
    }

    /* OK */
    public class BtnResestAction implements ActionListener
    {
        public void actionPerformed(ActionEvent action)
        {
            Flush();
            Message.warning("Champ réinitialisés !");
            updating = false;
            System.out.println(BtnResestAction.class.getName()+" : actionPerformed");
        }
    }

    /* OK */
    public class BtnDeleteAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ev)
        {
            if (updating)
            {
                if(client.getId() > 0)
                {
                    controls.getButtons(0).disable();
                    /* Suppression du client de la base de données */
                    ((ClientManager) FactoryManager.getInstance().getManager(FactoryManager.CLIENT_MANAGER)).delete(client.getId());
                    /* Mise à jour de la table */
                    JTableClientModel mdl = (JTableClientModel) ClientTable.getModel();
                    mdl.remove(selectedRow);
                    /* Mise à jour de la Liste des clients */
                    ListClient.getHinstance().getListClient().remove(selectedRow);
                    Message.information("Client supprimé !");
                }
                else
                {
                    System.out.println("Client non supprimé !") ;
                }
            }
            Flush();
            /* MAJ de l'affichage */
            update() ;
            updating = false;
            controls.getButtons(0).enable();
            /* Debug */
            System.out.println(BtnDeleteAction.class.getName()+" : actionPerformed");
        }
    }

    /* OK */
    public class BtnUpdateAction implements ActionListener
    {
        public void setClientInformation()
        {
            client.setNom(nom_field.getText()) ;
            client.setPrenom(prenom_field.getText());
            client.setEmail(email_field.getText());
            /* Sexe */
            client.getSexe().setId(sex_list.getSelectedIndex());
            client.getSexe().setDescription(sex_list.getSelectedItem().toString());
            /* Nationalite */
            client.getNation().setId(nation_list.getSelectedIndex());
            client.getNation().setDescription(nation_list.getSelectedItem().toString());
            /* Typeclient */
            client.getType().setId(sex_list.getSelectedIndex());
            client.getType().setDescription(sex_list.getSelectedItem().toString());
            /* fidélité et étranger */
            client.setEtranger(true) ;
            client.setFidelite(fidYes.isSelected()) ;
            client.setDateNaissance(new java.sql.Date(dateChooser.getDate().getTime())) ;
        }
        
        public void actionPerformed(ActionEvent ev)
        {
            if (updating)
            {
                /* bouton ok desactivé */
                controls.getButtons(0).disable();
                setClientInformation() ;
                if(client.isValid())
                {
                    /* Mise à jour dans la base */
                    if(((ClientManager) FactoryManager.getInstance().getManager(FactoryManager.CLIENT_MANAGER)).update(client) != -1)
                    {
                       /* Mise à jour dans la Table */
                        int x = selectedRow;
                        /* Mise à jour de La liste */
                        ListClient.getHinstance().getListClient().get(x).setClient(client);
                        Message.information("Mise à jour du client effectué !");                     
                    }
                }
                else
                {
                    System.out.println("Client non mis à jour !");
                }
            }
            Flush();
            /* mise à jour de l'affichage */
            update() ;
            updating = false;
            /* réactiver */
            controls.getButtons(0).enable();
            /* Debug */
            System.out.println(BtnUpdateAction.class.getName()+" : actionPerformed");
        }
    }

    /* OK */
    public class TableAction extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent ev)
        {
            int x = ClientTable.getSelectedRow();
            selectedRow = x;

            /* Récupération des données */
            JTableClientModel mdl = (JTableClientModel) ClientTable.getModel() ;
            client = mdl.getValueAt(x) ;
//          client.setClient(ListClient.getHinstance().getListClient().get(x));
            
            /* affichage des données dans le formulaire */
            sex_list.setSelectedItem(client.getSexe().getDescription());
            dateChooser.setDate(client.getDateNaissance());
            nation_list.setSelectedItem(client.getNation().getDescription());
            nom_field.setText(client.getNom());
            email_field.setText(client.getEmail());
            prenom_field.setText(client.getPrenom());
            typeClient_list.setSelectedItem(client.getType().getDescription());
            fidYes.setSelected(client.isFidelite());
            fidNo.setSelected(!client.isFidelite());
            updating = true;
            /* Debug */
            System.out.println(TableAction.class.getName()+" : actionPerformed");
        }
    }

    private int selectedRow = 0;
    private Boolean updating = false;
    private static PanelClient singleton = null;

    private JTable ClientTable;
    private JScrollPane scroll;

    private Background panel;
    private Client client = new Client();

    private JTextField nom_field;
    private JTextField prenom_field;
    private JTextField email_field ;

    private JComboBox<String> sex_list;
    private JDateChooser dateChooser;
    private JComboBox<String> nation_list;
    private JComboBox<Integer> nombre_list;
    private JComboBox<String> typeClient_list;

    private JComboBox<String> print_list;
    private JButton print_button;

    private Buttons controls;

    private JSearch search;

    private JLabel txtListClient;
    private JLabel txtAge;
    private JLabel txtNom;
    private JLabel txtSexe;
    private JLabel txtPrenom;
    private JLabel txtType;
    private JLabel txtNationalite;
    private JLabel txtNombre;
    private JLabel txtFid;
    private JLabel txtEmail;

    private ButtonGroup fidGrp;
    private JRadioButton fidYes;
    private JRadioButton fidNo;

    private void Search_Client(String key)
    {
        JTableClientModel mdl = JTableClient.getHinstance().getModel();

        if (key ==null || key.compareTo("") == 0)
        {
            mdl.setData(ListClient.getHinstance().getListClient());
            System.out.println("PanelClient: ListClient.getHinstance().getListClient()");
        } else
        {
            List<Client> new_ListClient;
            new_ListClient = ((ClientManager) FactoryManager.getInstance().getManager(FactoryManager.CLIENT_MANAGER)).findByCriteria(key, false) ;
            mdl.setData(new_ListClient);
        }
        update() ;
    }

    private void Flush()
    {
        nom_field.setText("");
        prenom_field.setText("");
        email_field.setText("");
//        age_list.setSelectedIndex(0);
        nation_list.setSelectedIndex(0);
        typeClient_list.setSelectedIndex(0);
        sex_list.setSelectedIndex(0);
        nombre_list.setSelectedIndex(0);
        fidNo.setSelected(true);
    }

    private void Build_Label()
    {
        String tmp1 = "<html><font color=#FEFDF0>";
        String tmp2 = "<font/></html>";
        Font font = new Font("Purisa", Font.BOLD, 16);
        txtEmail = new JLabel(tmp1 + "E-Mail: " + tmp2);
        txtEmail.setFont(font);
        txtFid = new JLabel(tmp1 + "Carte de fidélité: " + tmp2);
        txtFid.setFont(font);
        txtListClient = new JLabel(tmp1 + "Liste des clients" + tmp2);
        txtListClient.setFont(font);
        txtAge = new JLabel(tmp1 + "Age: " + tmp2);
        txtAge.setFont(font);
        txtNom = new JLabel(tmp1 + "Nom: " + tmp2);
        txtNom.setFont(font);
        txtSexe = new JLabel(tmp1 + "Sexe: " + tmp2);
        txtSexe.setFont(font);
        txtPrenom = new JLabel(tmp1 + "Prenom: " + tmp2);
        txtPrenom.setFont(font);
        txtType = new JLabel(tmp1 + "Type de client: " + tmp2);
        txtType.setFont(font);
        txtNationalite = new JLabel(tmp1 + "Nationalité: " + tmp2);
        txtNationalite.setFont(font);
        txtNombre = new JLabel(tmp1 + "Nombre de membre: " + tmp2);
        txtNombre.setFont(font);
    }

    private void Build_Table()
    {
        scroll = JTableClient.getHinstance().getScroll();
        ClientTable = JTableClient.getHinstance().getTable();
        ClientTable.addMouseListener(new TableAction());
    }

    /* Créer les listes pour les nationalité et le sexe */
    private void Build_Combo()
    {
        /* Nombre de client dans un groupe */
        nombre_list = new JComboBox<>(new Integer[] {1, 2, 3});
        nombre_list.enable(false);
        /* Type de client */
        typeClient_list = new JComboBox<>(new String[]{"", "lambda", "v.i.p", "premium", "master"});
        /* Sexe des client */
        sex_list = new JComboBox<>(new String[]{ "", "homme", "femme"});
        /* Nationalité des clients */      
        nation_list = new JComboBox<>(new String[]{""}) ;
        ListNation.getHinstance().getList().forEach((n) -> {
            nation_list.addItem(n.getDescription());
        });

        print_list = new JComboBox<>(new String[]
        {
            "", "Clients fidèles", "Clients étrangers", "Clients arrivées par période", "Clients partis par période"
        });
    }

    /* Paramettrage des buttons et champs de texte */
    private void Build_Button_and_TextField()
    {
        controls = new Buttons();

        controls.getButtons(2).addActionListener(new BtnUpdateAction());

        controls.getButtons(0).addActionListener(new BtnOkAction());

        controls.getButtons(1).addActionListener(new BtnResestAction());

        controls.getButtons(3).addActionListener(new BtnDeleteAction());

        print_button = new JButton("Imprimer");
        print_button.addActionListener(new Print_Action());

        fidGrp = new ButtonGroup();
        fidYes = new JRadioButton("Oui", false);
        fidNo = new JRadioButton("Non", true);

        search = new JSearch();
        search.getComponent(0).addKeyListener(new Search_Action());

        nom_field = new JTextField();
        nom_field.setColumns(20);
        
        email_field = new JTextField();
        email_field.setColumns(20);

        prenom_field = new JTextField();
        prenom_field.setColumns(20);
    }

    private void Build_Panel()
    {
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        panel = new Background();
        panel.setLayout(null);
        Build_Label();
        addContent();
    }

    /* Ajout des contenus des l'onglet */
    private void addContent()
    {
        SetPosition();
        for (int i = 0; i < 4; i++)
        {
            panel.add(controls.getButtons(i));
        }
        panel.add(search.getComponent(0));
        panel.add(search.getComponent(1));
        fidGrp.add(fidNo);
        fidGrp.add(fidYes);
        panel.add(fidNo);
        panel.add(fidYes);
        panel.add(txtFid);
        panel.add(email_field);
        panel.add(txtEmail);
        //panel.add(print_list) ;
        panel.add(print_button);
        panel.add(txtListClient);
        panel.add(txtAge);
        panel.add(txtNom);
        panel.add(txtType);
        panel.add(txtSexe);
        panel.add(sex_list);
        panel.add(dateChooser);
        panel.add(nom_field);
        panel.add(txtPrenom);
        panel.add(txtNombre);
        panel.add(nation_list);
        panel.add(nombre_list);
        panel.add(prenom_field);
        panel.add(txtNationalite);
        panel.add(typeClient_list);
        panel.add(scroll);
    }

    private void SetPosition()
    {
        int x1 = 70, x2 = 300, w1 = 220, w2 = 260, h = 20;
        /* Champ de recherche et son bouton */
        search.getComponent(0).setBounds(x2 + 15 + 20 + x2 * 2 - 20 - 48 + 100 + 10, 15 + 245 + h + (24) / 2, w2, h);
        search.getComponent(1).setBounds(x2 + 15 + 20 + x2 * 2 - 20 - 48, 15 + 245 + h + (24) / 2, w2, h);
        print_button.setBounds(/*x2+15+20+x2*2-100-w2*/x2 + 15 + w2 + 20, 15 + 245 + h + (24) / 2, 100, h);
        print_list.setBounds(x2 + 15 + w2 + 20 + 100 + 15, 15 + 245 + h + (24) / 2, 150, h);
        /* Le JTableCLient et son Label*/
        // scroll.setBounds(x2+15+w2+20, 30+15, x2*2, 235-30+h) ;
        scroll.setBounds(x2 + 15 + w2 + 20, 30 + 15, x2 + 15 + 20 + x2 * 2 - 20 - 48 + 100 + 10 - (15 + 245 + h + (40) / 2), 235 - 30 + h);
        txtListClient.setBounds(x2 + 20 + 15 + w2 + (x2 * 2 - w1) / 2, 10, w1, h);

        /* Positionnnement du label nom et de son champ */
        txtNom.setBounds(x1, 30 + 15, w1, h);
        nom_field.setBounds(x2, 30 + 15, w2, h);
        /* Positionnnement du label prénom et de son champ */
        txtPrenom.setBounds(x1, 65 + 15, w1, h);
        prenom_field.setBounds(x2, 65 + 15, w2, h);
        /* Positionnnement du label Email et de son champ */
        txtEmail.setBounds(x1, 100 + 15, w1, h);
        email_field.setBounds(x2, 100 + 15, w2, h);
        /* Positionnnement du label dateChooser et de son champ */
        txtAge.setBounds(x1, 135 + 15, w1, h);
        dateChooser.setBounds(x2, 135 + 15, w2, h);
        /* Positionnnement du label nationalité et de son champ */
        txtNationalite.setBounds(x1, 15 + 170, w1, h);
        nation_list.setBounds(x2, 170 + 15, w2, h);
        /* Positionnnement du label sexe et de son champ */
        txtSexe.setBounds(x1, 15 + 205, w1, h);
        sex_list.setBounds(x2, 15 + 205, w2, h);
        /* Positionnnement du label sexe et de son champ */
        txtType.setBounds(x1, 15 + 240, w1, h);
        typeClient_list.setBounds(x2, 15 + 240, w2, h);
        /* Positionnnement du label nombre de client et de son champ */
        txtNombre.setBounds(x1, 275 + 15, w1 + 40, h);
        nombre_list.setBounds(x2, 275 + 15, w2, h);

        txtFid.setBounds(x1, 310 + 15, w1, h);
        fidYes.setBounds(x2, 310 + 15, w2 / 2 - 5, h);
        fidNo.setBounds(x2 + (w2 / 2 - 5) + 10, 310 + 15, w2 / 2 - 5, h);

        controls.setPosition(300, 300, 20);
    }

    /* Constructeur de la l'onglet */
    private PanelClient()
    {
        client = new Client();
        Build_Table();
        Build_Label();
        Build_Button_and_TextField();
        Build_Combo();
        Build_Panel();
//		print_button.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent arg0) { Report.Generer();	}});
    }

    public static PanelClient getHinstance()
    {
        if (singleton == null)
        {
            singleton = new PanelClient();
        }
        return singleton;
    }

    public Background getPanel()
    {
        return panel;
    }
}