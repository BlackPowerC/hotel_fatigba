package manageClient;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List ;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import app.Background;
import app.Buttons;
import app.DataBaseCon;
import app.JSearch;
import app.Report;
//import app.Report;
import dataFromDatabase.ListClient;

public class PanelClient
{
	public class Radio_Action implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			Object ob = ev.getSource() ;
			if(ob.equals(fidNo))
			{
				client.setM_has_fidelity_card(false);
			}
			if(ob.equals(fidYes))
			{
				client.setM_has_fidelity_card(true);
			}
		}
	}
	
	/* OK */
	public class Search_Action implements KeyListener
	{
		public void keyPressed(KeyEvent arg0)
		{
				
		}

		public void keyReleased(KeyEvent arg0) 
		{
			String key = search.getSearch_field().getText() ;
			System.out.println("PanelClient: Search_Action: "+key);
			Search_Client(key) ;	
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
//			Report rep = new Report() ;
//			rep.setReportName((String)print_list.getSelectedItem()) ;
//			rep.setJrxml((String)print_list.getSelectedItem()) ;
//			rep.Build_Report() ;
		}
	}

	/* OK */
	public class Combo_Action implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			
			Object ob = ev.getSource() ;
			if(ob.equals(typeClient_list))
			{
				client.setM_type(typeClient_list.getSelectedItem().toString());
				System.out.println("PanelClient: Combo_Action: "+client.getM_type());
				if(client.getM_type().compareTo("Groupe") == 0)
				{
					nombre_list.enable(true);
				}
				else
				{
					nombre_list.enable(false);
				}
			}
			
			if(ob.equals(nombre_list))
			{
				client.setM_membre((Integer) nombre_list.getSelectedItem());
			}
			
			if(ob.equals(age_list))
				client.setM_age((Integer) age_list.getSelectedItem());
			if(ob.equals(sex_list))
				client.setM_sexe(sex_list.getSelectedItem().toString());
			if(ob.equals(nation_list))
				client.setM_nation(nation_list.getSelectedItem().toString());
		}
	}
	
	/* OK */
	public class TextFieldAction implements KeyListener
	{
		public void keyReleased(KeyEvent ev)
		{
			Object ob = ev.getSource() ;
			if(ob.equals(nom_field))
				client.setM_nom(nom_field.getText());
			if(ob.equals(prenom_field))
				client.setM_prenom(prenom_field.getText());
		}

		public void keyPressed(KeyEvent arg0)
		{
		}

		public void keyTyped(KeyEvent arg0)
		{	
		}
	}
	
	public class Ok_Action implements ActionListener
	{
		public void actionPerformed(ActionEvent action)
		{
			/* On vérifie la validité de tout les champs */
			if(updating)
			{
				controls.getButtons(0).disable();
				//JOptionPane.showMessageDialog(null, "Ces données sont pour une mise à jour !", "Erreur", JOptionPane.WARNING_MESSAGE) ;
				return ;
			}
			all_fields_is_ok = true;
			/* Check nom */
			if (client.getM_nom().equals("") || client.getM_nom().equals(null))
			{
				all_fields_is_ok = false;
			}
			/* Check prenom */
			if (client.getM_prenom().equals("") || client.getM_prenom().equals(null))
			{
				all_fields_is_ok = false;
			}
			/* Check nation */
			if (client.getM_nation().equals("")	|| client.getM_nation().equals(null))
			{
				all_fields_is_ok = false;
			}
			/* Check sexe */
			if (client.getM_sexe().equals("") || client.getM_sexe().equals(null))
			{
				all_fields_is_ok = false;
			}
			if(all_fields_is_ok == false)
			{
				JOptionPane.showMessageDialog(null, 
						"Tout les champs sont requis", "Erreur", 
						JOptionPane.WARNING_MESSAGE) ;
				return;
			}
			if(all_fields_is_ok)
			{	
				/* Récupération du model de donnée */
				JTableClientModel mdl = JTableClient.getHinstance().getModel() ;
				mdl.addRow() ;
				
				/*
				 * Récupération de l'id du dernier client de la liste pour créer
				 * l'id du nouveau client dans la liste
				 */
				if(ListClient.getHinstance().getListClient().size() == 0)
				{
					client.setM_id_client(1) ;
				}
				else
				{
					client.setM_id_client(ListClient.getHinstance().getLastClient().getM_id_client() + 1);
				}
				/*
				 * Le dernier client doit devenir le nouveau et on l'envoie dans
				 * la classe Reservation view
				 */
				/* On a le dernier client */
				ListClient.getHinstance().setLastClient(client);
				/* On récupère la liste des id de clients */
				ListClient.getHinstance().getListClient().add(new Client(client)) ;
				//PanelReservation.getHinstance().getID_ClientList().addItem(client.getM_id_client());
				panel.remove(scroll);
				panel.add(scroll) ;
				panel.revalidate() ;
				
				/* On ajoute l'id du dernier client ajouter dans la liste */
				//PanelReservation.getHinstance().getID_ClientList().addItem(client.getM_id_client());
				
				/* Connection à la base de données pour y mettre des données */
				String sql = "insert into Client (id_client, nom, prenom, type_client, card, local, "
						+ "sexe, nationalite, age, membre) values "
						+ "("+client.getM_id_client() + ", " + "'" + client.getM_nom() + "'" + ", " + "'"
						+ client.getM_prenom() + "'" + ", " + "'"
						+ client.getM_type() + "', " + client.isM_has_fidelity_card()+", 0" + ", '"
						+ client.getM_sexe() + "', " + "'"
						+ client.getM_nation() + "', " + client.getM_age()
						+ ", " + client.getM_membre() + " )" ;
				
				try
				{
					DataBaseCon.getHinstance().updateQuery(sql);
				} 
				catch(SQLException e) 
				{
					e.printStackTrace();
				}
				System.out.println("PanelClient: client.toString()"+client.toString()) ;
				System.out.println("PanelClient: insert req: "+sql) ;
				
				/* Mise à jour de l'affichage */
				addContent();
				/*Flushing des JtextField et JComboBox*/
				Flush();
				updating = false ;
				JOptionPane.showMessageDialog(null, 
						"Tous les Champs sont bien remplis", "OK", 
						JOptionPane.INFORMATION_MESSAGE) ;
			}
		}
	}
	
	/* OK */
	public class Reset_Action implements ActionListener
	{
		public void actionPerformed(ActionEvent action)
		{
			Flush();
			JOptionPane.showMessageDialog(null, "Champ réinitialisés", "OK", JOptionPane.WARNING_MESSAGE) ;
			updating = false ;
		}
	}
	
	/* OK */
	public class Delete_Action implements  ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			if(updating)
			{
				controls.getButtons(0).disable();
				/* Mise à jour de la base de données*/
				String sql = "delete from Client where id_client="+client.getM_id_client() ;
				try {
					DataBaseCon.getHinstance().updateQuery(sql) ;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/* Mise à jour de la table */
				JTableClientModel mdl =(JTableClientModel) ClientTable.getModel() ;
				mdl.remove(selectedRow) ;
				/* Mise à jour de la Liste des clients */
				ListClient.getHinstance().getListClient().remove(selectedRow) ;
				/* Le dernier client */
				if(ListClient.getHinstance().getListClient().size() > 1)
				{
					ListClient.getHinstance().setLastClient(ListClient.getHinstance().getListClient().get(mdl.getRowCount()-1));
				}
				
				/* le client supprimé est retirer de la liste de reservation */
				//PanelReservation.getHinstance().getID_ClientList().removeItem(client.getM_id_client());
				System.out.println(sql) ;
				
				Flush() ;
				
				updating = false ;
				controls.getButtons(0).enable();
				JOptionPane.showMessageDialog(null, 
						"Données supprimées !", "OK", 
						JOptionPane.INFORMATION_MESSAGE) ;
			}
		}
	}
		
	/* OK */
	public class Update_Action implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
	    {
			if(updating)
			{
				/* bouton ok desactivé */
				controls.getButtons(0).disable();
				/* Mise à jour dans la base */
				String sql = "update Client set nom='"+
						client.getM_nom()+"', prenom='"+client.getM_prenom()+
						"', type_client='"+client.getM_type()+
						"', sexe='"+client.getM_sexe()+"', age="+
						client.getM_age()+", nationalite='"+client.getM_nation()
						+"', card="+client.isM_has_fidelity_card()+" where id_client="+client.getM_id_client() ;
				
				System.out.println(sql) ;
				try {
					DataBaseCon.getHinstance().updateQuery(sql) ;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				/* Mise à jour dans la Table */
				int x = selectedRow ;
				/* Mise à jour de La liste */
				ListClient.getHinstance().getListClient().get(x).setClient(client) ;
				
				Flush() ;
				
				/* mise à jour de l'affichage */
				panel.remove(scroll);
				panel.add(scroll) ;
				panel.revalidate();
				
				updating = false ;
				
				/* réactiver */
				controls.getButtons(0).enable();
				JOptionPane.showMessageDialog(null, 
						"MAJ des données effectué !", "OK", 
						JOptionPane.INFORMATION_MESSAGE) ;
			}
		}
	}
	
	/* OK */
	public class Table_Action extends MouseAdapter
	{ 
		public void mouseClicked(MouseEvent ev)
		{
			/* Récupération du model de donnée */
			JTableClientModel mdl = (JTableClientModel) ClientTable.getModel() ;
			int x  = ClientTable.getSelectedRow() ;
			selectedRow = x ;
			
			/* Récupération des données */
			client = (Client) mdl.getValueAt(x) ;
			
			/* affichage des données dans le formulaire */
			sex_list.setSelectedItem(client.getM_sexe()) ;
			age_list.setSelectedItem(client.getM_age()) ;
			nation_list.setSelectedItem(client.getM_nation()) ;
			nom_field.setText(client.getM_nom()) ;
			prenom_field.setText(client.getM_prenom()) ;
			typeClient_list.setSelectedItem(client.getM_type()) ;
			fidYes.setSelected(client.isM_has_fidelity_card() ? true: false) ;
			fidNo.setSelected(!client.isM_has_fidelity_card() ? true: false) ;
			
			updating = true;
		}
	}
	
	private int selectedRow = 0 ;
	private Boolean updating = false ;
	private static PanelClient singleton = null;
	
	private JTable ClientTable ;
	private JScrollPane scroll ;
	
	private Background panel;
	private Client client = new Client();
	private Boolean all_fields_is_ok;

	private JTextField nom_field;
	private JTextField prenom_field;

	private JComboBox<String> sex_list;
	private JComboBox<Integer> age_list;
	private JComboBox<String> nation_list;
	private JComboBox<Integer> nombre_list;
	private JComboBox<String> typeClient_list;
	
	private JComboBox<String> print_list ;
	private JButton print_button ;
	
	private Buttons controls ;
	
	private JSearch search ;
	
	private JLabel txtListClient ;
	private JLabel txtAge;
	private JLabel txtNom;
	private JLabel txtSexe;
	private JLabel txtPrenom;
	private JLabel txtType;
	private JLabel txtNationalite;
	private JLabel txtNombre;
	private JLabel txtFid ;
	
	private ButtonGroup fidGrp ;
	private JRadioButton fidYes ;
	private JRadioButton fidNo ;

	private String[] nation = {"", "Afghanistan", "Afrique du sud", "Albanie",
			"Algérie", "Allemagne", "Andorre", "Angola", "Argentine",
			"Arménie", "Australie", "Autriche", "Azerbaidjan", "Bahamas",
			"Bahrein", "Bangladesh", "Belarus", "Bénin", "Bhoutan",
			"Bélize", "Birmanie", "Bolivie", "Bosnie et Herzégovine",
			"Botswana", "Brésil", "Brunie", "Bulgarie", "Burkina Faso",
			"Burundi", "Cambodge", "Cameroun", "Cap-Vert", "Centrafrique",
			"Chili", "Chine", "Chybre", "Colombie", "Comores",
			"Congo-Brazzaville", "Congo-Kinshasa", "Iles Cook",
			"Corée du Sud", "Corée du Nord", "Costa Rica", "Cote d'Ivoire",
			"Croatie", "Cuba", "Danemark", "Djibouti", "Dominique",
			"République dominicaine", "Egypte", "Equateur", "Erythrée",
			"Espagne", "Estonie", "Etats-Unis", "Ethiopie", "Fidji", "Finlande",
			"France", "Gabon", "Gambie", "Géorgie", "Ghana", "Grèce",
			"Grenade", "Guatemala", "Guinée", "Guyana", "Haiti",
			"Honduras", "Hongie", "Inde", "Indonésie", "Irlande",
			"Islande", "Israel", "Italie", "Japon", "Jordanie",
			"Kazakhstan", "Kenya", "Kirghizistan", "Kiribati", "Koweit",
			"Loas", "Lesotho", "Lettonie", "Liban", "Libéria", "Lybie",
			"Liechtenstein", "Lituanie", "Luxembourg", "Macédoine",
			"Madagascar", "Malaisie", "Malawi", "Maldives", "Malte",
			"Maroc", "Marshall", "Maurice", "Mauritanie", "Mexique",
			"Micronésie", "Moldavie", "Monaco", "Mozambique", "Namibie",
			"Nauru", "Népal", "Nicaragua", "Niger", "Nioué", "Norvège",
			"Nouvelle-Zélande", "Oman", "Ouganda", "Ousbékistan",
			"Pakistan", "Palaos", "Panama", "Papouasie-Nouvelle-Guinée",
			"Paraguay", "Pay-Bas", "Pérou", "Philippines", "Pologne",
			"Pologne", "Quatar", "Royaume-Uni", "Russie", "Rwanda",
			"Saint-Christophe", "Sainte-Lucie", "Saint-Marin",
			"Saint-Vincent-et-les Grenadines", "Salomon", "Salvador",
			"Samoa", "Sao Tomé-et-Principe", "Sénégal", "Serbie",
			"Seychelles", "Sierra Leone", "Singapour", "Slovaquie", 
			"Slovénie", "Somalie","Soudan", "Soudan du Sud", "Sri Lanka",
			"Suède", "Suisse", "Suriname", "Swaziland", "Syrie",
			"Tadjikistan", "Tanzanie", "Tchad" ,"Thailande", "Togo",
			"Tonga", "Trinité-et-Tobago", "Tunisie", "Turkménistan",
			"Turquie", "Tuvalu", 
			"Uruguay", "Vanuatu", "Vatican", "Vénézuela", "Viet Nam", 
			"Yémen", "Zambie", "Ziwbabwé"};
	
	private void Search_Client(String key)
	{
		JTableClientModel mdl = JTableClient.getHinstance().getModel() ;
		
		if(key.equals(null) || key.compareTo("") == 0)
		{
			mdl.setData(ListClient.getHinstance().getListClient());
			System.out.println("PanelClient: ListClient.getHinstance().getListClient()");
		}
		else
		{
			String req = "select * from Client where nom LIKE '"+key+"%' or "+"prenom LIKE '"+key+"%' or "+"sexe LIKE '"+key+"%' or "+"nationalite LIKE '"+key+"%' or "+"type_client LIKE '"+key+"%'" ;
			System.out.println(req) ;

			Client tmp = new Client() ;
			List<Client> new_ListClient = new ArrayList<Client>() ;
			try
			{
				ResultSet rs = DataBaseCon.getHinstance().executeQuery(req) ;
				System.out.println("PanelClient: new_ListClient");
				while(rs.next())
				{
					tmp.setM_id_client(rs.getInt("id_client"));
					tmp.setM_nom(rs.getString("nom"));
					tmp.setM_prenom(rs.getString("prenom"));
					tmp.setM_type(rs.getString("type_client"));
					tmp.setM_sexe(rs.getString("sexe"));
					tmp.setM_nation(rs.getString("nationalite"));
					tmp.setM_age(rs.getInt("age"));
					tmp.setM_membre(rs.getInt("membre"));
					new_ListClient.add(new Client(tmp)) ;
				}
				mdl.setData(new_ListClient) ;
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		panel.remove(scroll);
		panel.add(scroll) ;
		panel.revalidate();
	}
	
	private void Flush()
	{
		nom_field.setText("");
		prenom_field.setText("");
		age_list.setSelectedIndex(0);
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
		txtFid = new JLabel(tmp1+"Carte de fidélité: "+tmp2) ;
		txtFid.setFont(font) ;
		txtListClient = new JLabel(tmp1 + "Liste des clients" + tmp2) ;
		txtListClient.setFont(font) ;
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
		scroll = JTableClient.getHinstance().getScroll() ;
		ClientTable = JTableClient.getHinstance().getTable() ;
		ClientTable.addMouseListener(new Table_Action()) ;
	}
	
	/* Créer les listes pour les nationalité et le sexe */
	private void Build_Combo() 
	{
		nombre_list = new JComboBox<Integer>(new Integer[]{1, 2, 3});
		nombre_list.addActionListener(new Combo_Action());
		nombre_list.enable(false);

		typeClient_list = new JComboBox<String>(new String[]{ "", "Privé", "Groupe", "Affaire", "TOP","V.I.P" });
		typeClient_list.addActionListener(new Combo_Action());

		age_list = new JComboBox<Integer>() ;
		age_list.addActionListener(new Combo_Action()) ;
		for (int i = 0; i < 82; i++) 
			age_list.addItem(i + 18);

		sex_list = new JComboBox<String>(new String[] { "", "Homme", "Femme" });
		sex_list.addActionListener(new Combo_Action());

		nation_list = new JComboBox<String>(nation);
		nation_list.addActionListener(new Combo_Action());
		
		print_list = new JComboBox<String>(new String[]{"", "Clients fidèles", "Clients étrangers", "Clients arrivées par période", "Clients partis par période"}) ;
		print_list.addActionListener(new Combo_Action());
	}

	/* Paramettrage des buttons et champs de texte */
	private void Build_Button_and_TextField() 
	{
		controls = new Buttons() ;
		
		controls.getButtons(2).addActionListener(new Update_Action()) ;
		
		controls.getButtons(0).addActionListener(new Ok_Action());
		
		controls.getButtons(1).addActionListener(new Reset_Action());
		
		controls.getButtons(3).addActionListener(new Delete_Action()) ;
		
		print_button = new JButton("Imprimer") ;
		print_button.addActionListener(new Print_Action()) ;
		
		fidGrp = new ButtonGroup() ;
		fidYes = new JRadioButton("Oui", false) ;
		fidYes.addActionListener(new Radio_Action());
		fidNo = new JRadioButton("Non", true) ;
		fidNo.addActionListener(new Radio_Action());
		
		search = new JSearch() ;
		search.getComponent(0).addKeyListener(new Search_Action());
		
		nom_field = new JTextField();
		nom_field.addKeyListener(new TextFieldAction());
		nom_field.setColumns(20);

		prenom_field = new JTextField();
		prenom_field.addKeyListener(new TextFieldAction());
		prenom_field.setColumns(20);
	}

	private void Build_Panel()
	{
		panel = new Background();
		panel.setLayout(null);
		Build_Label();
		addContent();
	}

	/* Ajout des contenus des l'onglet */
	private void addContent()
	{
		SetPosition();
		for(int i = 0; i<4; i++)
		{
			panel.add(controls.getButtons(i)) ;
		}
		panel.add(search.getComponent(0)) ;
		panel.add(search.getComponent(1)) ;
		fidGrp.add(fidNo);
		fidGrp.add(fidYes);
		panel.add(fidNo);
		panel.add(fidYes);
		panel.add(txtFid) ;
		//panel.add(print_list) ;
		panel.add(print_button) ;
		panel.add(txtListClient) ;
		panel.add(txtAge) ;
		panel.add(txtNom) ;
		panel.add(txtType) ;
		panel.add(txtSexe) ;
		panel.add(sex_list) ;
		panel.add(age_list) ;
		panel.add(nom_field) ;
		panel.add(txtPrenom) ;
		panel.add(txtNombre) ;
		panel.add(nation_list) ;
		panel.add(nombre_list) ;
		panel.add(prenom_field) ;
		panel.add(txtNationalite) ;
		panel.add(typeClient_list) ;
		panel.add(scroll) ;
    }

	private void SetPosition() 
	{
		int x1 = 70, x2 = 300, w1 = 220, w2 = 260, h = 20;
		/* Champ de recherche et son bouton */
		search.getComponent(0).setBounds(x2+15+20+x2*2-20-48 +100+10, 15+245+h+(24)/2, w2, h) ;
		search.getComponent(1).setBounds(x2+15+20+x2*2-20-48, 15+245+h+(24)/2, w2, h) ;
		print_button.setBounds(/*x2+15+20+x2*2-100-w2*/x2+15+w2+20, 15+245+h+(24)/2, 100, h);
		print_list.setBounds(x2+15+w2+20+100+15, 15+245+h+(24)/2, 150, h) ;
		/* Le JTableCLient et son Label*/
		// scroll.setBounds(x2+15+w2+20, 30+15, x2*2, 235-30+h) ;
		scroll.setBounds(x2+15+w2+20, 30+15, x2+15+20+x2*2-20-48 +100+10 - ( 15+245+h+(40)/2), 235-30+h) ;
		txtListClient.setBounds(x2+20+15+w2 +(x2*2 - w1)/2, 10, w1, h);
		
		/* Positionnnement du label nom et de son champ */
		txtNom.setBounds(x1, 30+15, w1, h);
		nom_field.setBounds(x2, 30+15, w2, h);
		/* Positionnnement du label prénom et de son champ */
		txtPrenom.setBounds(x1, 65+15, w1, h);
		prenom_field.setBounds(x2, 65+15, w2, h);
		/* Positionnnement du label age et de son champ */
		txtAge.setBounds(x1, 100+15, w1, h);
		age_list.setBounds(x2, 100+15, w2, h);
		/* Positionnnement du label nationalité et de son champ */
		txtNationalite.setBounds(x1, 15+135, w1, h);
		nation_list.setBounds(x2, 135+15, w2, h);
		/* Positionnnement du label sexe et de son champ */
		txtSexe.setBounds(x1, 15+170, w1, h);
		sex_list.setBounds(x2, 15+170, w2, h);
		/* Positionnnement du label sexe et de son champ */
		txtType.setBounds(x1, 15+200, w1, h);
		typeClient_list.setBounds(x2,15+ 200, w2, h);
		/* Positionnnement du label nombre de client et de son champ */
		txtNombre.setBounds(x1, 235+15, w1 + 40, h);
		nombre_list.setBounds(x2, 235+15, w2, h);
		
		txtFid.setBounds(x1, 270+15, w1, h);
		fidYes.setBounds(x2, 270+15, w2/2 - 5, h);
		fidNo.setBounds(x2+(w2/2 - 5)+10, 270+15, w2/2 - 5, h);
		
		controls.setPosition(300, 260, 20);
	}

	/* Constructeur de la l'onglet */
 	private PanelClient()
	{
		client = new Client();
		Build_Table() ;
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
