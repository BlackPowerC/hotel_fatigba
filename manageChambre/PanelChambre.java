package manageChambre;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import app.Background;
import app.Buttons;
import app.DataBaseCon;
import app.JSearch;
import app.Report;
import dataFromDatabase.ListChambre;

public class PanelChambre 
{
	public class Search_Action implements KeyListener
	{
		public void keyPressed(KeyEvent arg0)
		{
				
		}

		public void keyReleased(KeyEvent arg0) 
		{
			String key = search.getSearch_field().getText() ;
			System.out.println("PanelChambre: Search_Action: "+key);
			Search_Client(key) ;	
		}

		public void keyTyped(KeyEvent arg0)
		{	
			
		}
	}
	
	public class Update_Action implements ActionListener
	{
		public void actionPerformed(ActionEvent eve)
		{
			if(prix_field.getText().compareTo("") == 0 || prix_field.getText().equals(null))
			{
				JOptionPane.showMessageDialog(null, "Tout les champs sont requis", "Erreur", JOptionPane.WARNING_MESSAGE) ;
				return;
			}
			chambre.setPrix(Float.parseFloat(prix_field.getText())) ;
			
			if(situation_combo.getSelectedItem().toString().compareTo("") == 0 || situation_combo.getSelectedItem().toString().equals(null))
			{
				JOptionPane.showMessageDialog(null, "Tout les champs sont requis", "Erreur", JOptionPane.WARNING_MESSAGE) ;
				return;
			}
			chambre.setSituation(situation_combo.getSelectedItem().toString()) ;
			
			if(type_chambre_combo.getSelectedItem().toString().compareTo("") == 0 || type_chambre_combo.getSelectedItem().toString().equals(null))
			{
				JOptionPane.showMessageDialog(null, "Tout les champs sont requis", "Erreur", JOptionPane.WARNING_MESSAGE) ;
				return;
			}
			chambre.setType_chambre(type_chambre_combo.getSelectedItem().toString());
			
			/* Mise à jour de la chambre dans la base de données */
			String req = "update Chambre set type_chambre='"+chambre.getType_chambre()+"', situation='"+chambre.getSituation()+"', prix="+chambre.getPrix()+" where id_chambre="+chambre.getId_chambre() ;
			System.out.println(req);
			try
			{
				DataBaseCon.getHinstance().updateQuery(req) ;
			} 
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			
			/* Si La chambre est non occupée, on doit la modifier dans la liste des chambre dispo*/
			if(chambre.getState() == false)
			{
				Iterator<Chambre> yi = ListChambre.getHinstance().getListChambreDispo().iterator() ;
				Chambre tmp ;
				while(yi.hasNext())
				{
					tmp = yi.next() ;
					if(tmp.getId_chambre()== chambre.getId_chambre())
					{
						tmp.setChambre(chambre);
						break ;
					}
				}
			}
			/* On change la chambre dans la liste de toutes les chambres */
			ListChambre.getHinstance().getListChambreTotal().get(selectedRow).setChambre(chambre) ;
			
			panel.remove(scroll) ;
			panel.add(scroll) ;
			panel.revalidate();
			
			JOptionPane.showMessageDialog(null, "Tous les Champs sont bien remplis", "OK", JOptionPane.INFORMATION_MESSAGE) ;
		}
	}
	
	public class Table_Action implements MouseListener
	{
		public void mouseClicked(MouseEvent ev)
		{
			selectedRow = JTableChambreTotal.getHinstance().getTable().getSelectedRow() ;
			chambre = ListChambre.getHinstance().getListChambreTotal().get(selectedRow) ;
			type_chambre_combo.setSelectedItem(chambre.getType_chambre());
			chambreImage.show(chambre.getType_chambre());
			System.out.println("PanelChambre: Table_Action: "+chambre.getType_chambre());
			situation_combo.setSelectedItem(chambre.getSituation());
			prix_field.setText(""+chambre.getPrix());
		}

		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private int selectedRow ;
	
	private Background panel ;
	private Image chambreImage ;
	
	private JScrollPane scroll ;
	private Buttons bt ;
	
	private JButton print ;
	
	/* Label et champ associé */
	private JLabel type_chambre ;
	private JComboBox<String> type_chambre_combo ;
	
	private JLabel situation ;
	private JComboBox<String> situation_combo ;
	
	private JLabel prix ;
	private JFormattedTextField prix_field ;
	
	private JSearch search ;
	
	private JButton print_button ;
	
	private Chambre chambre ;
	
	private static PanelChambre singleton = null ;
	
	private void Search_Client(String key)
	{
		
		if(key.equals(null) || key.compareTo("") == 0)
		{
			JTableChambreTotal.getHinstance().getModel().setData(ListChambre.getHinstance().getListChambreTotal());
			System.out.println("PanelChambre: ListClient.getHinstance().getListClient()");
		}
		else
		{
			String req = "select * from Chambre where type_chambre LIKE '"+key+"%' or "+"situation LIKE '"+key+"%' or prix LIKE '"+key+"%' or state LIKE '"+key+"%'" ;
			System.out.println(req) ;
			
			Chambre tmp = new Chambre() ;
			List<Chambre> new_ListClient = new ArrayList<Chambre>() ;
			try
			{
				ResultSet rs = DataBaseCon.getHinstance().executeQuery(req) ;
				System.out.println("PanelChambre: new_ListClient");
				while(rs.next())
				{
					tmp.setId_chambre(rs.getInt("id_chambre"));
					tmp.setSituation(rs.getString("situation"));
					tmp.setType_chambre(rs.getString("type_chambre"));
					tmp.setState(rs.getBoolean("state"));
					tmp.setPrix(rs.getFloat("prix"));
					
					new_ListClient.add(new Chambre(tmp)) ;
				}
				JTableChambreTotal.getHinstance().getModel().setData(new_ListClient) ;
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
	
	private void Build_Button()
	{
		print = new JButton("imprimer") ;
		print.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Report.Generer("/home/jordy/workspace/Hotel/src/manageChambre/Chambres.jrxml");
				
			}
		});
		
		bt = new Buttons() ;
		bt.getButtons(1).addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				prix_field.setText(null);
				situation_combo.setSelectedItem("") ;
				type_chambre_combo.setSelectedItem("");
			}
		});
		bt.getButtons(2).addActionListener(new Update_Action()); ;
		System.out.println("Panel Chambre: Build_Button");
	}
	
	private PanelChambre()
	{
		search = new JSearch() ;
		chambre = new Chambre() ;
		chambreImage = new Image() ;
		Build_Panel() ;
	}
	
	private void Build_Label()
	{
		String tmp1 = "<html><font color=#FEFDF0>";
		String tmp2 = "<font/></html>";
		Font font = new Font("Purisa", Font.BOLD, 16);
		type_chambre = new JLabel(tmp1+"Type de Chambre: "+tmp2) ;
		type_chambre.setFont(font) ;
		prix = new JLabel(tmp1+"Prix: "+tmp2) ;
		prix.setFont(font);
		situation = new JLabel(tmp1+"Situation: "+tmp2) ;
		situation.setFont(font) ;
	}
	
	private void Build_TextField()
	{
		type_chambre_combo = new JComboBox<String>(new String[]{"" ,"Buisiness Class" ,"Single", "Double", "Triple", "Junior", "Executive", "Présidentielle", "Standard"}) ;
		situation_combo = new JComboBox<String>(new String[]{"", "Autoroute", "Interieur", "Piscine", "Mer", "Terasse"}) ;
		prix_field = new JFormattedTextField(NumberFormat.getNumberInstance()) ;
		search.getComponent(0).addKeyListener(new Search_Action()); 
	}
	
	private void Build_Panel()
	{
		panel = new Background() ;
		panel.setLayout(null);
		Build_Button() ;
		Build_Label() ;
		Build_TextField() ;
		addContent() ;
		setPosition() ;
	}
	
	private void addContent()
	{
		JTableChambreTotal.getHinstance().getTable().addMouseListener(new Table_Action());
		scroll = JTableChambreTotal.getHinstance().getScroll() ;
		panel.add(print) ;
		panel.add(chambreImage.getPanel()) ;
		panel.add(search.getComponent(0)) ;
		panel.add(search.getComponent(1)) ;
		panel.add(bt.getButtons(1)) ;
		panel.add(bt.getButtons(2)) ;
		panel.add(scroll) ;
		panel.add(type_chambre) ;
		panel.add(prix) ;
		panel.add(prix_field) ;
		panel.add(situation) ;
		panel.add(situation_combo) ;
		panel.add(type_chambre_combo) ;
	}
	
	private void setPosition()
	{
		int x1 = 70, x2 = 360, w1 = 180, y = 30+15, h = 20+10, w2 = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() ;
		/* type de chambre et son combo */
		type_chambre.setBounds(x1, y, w1+40+10, h) ;
		type_chambre_combo.setBounds(x1+w1+20, y, w1, h) ;
		
		/* Le Label de situation et son combo */
		situation.setBounds(x1+2*(w1+20), y, w1, h) ;
		situation_combo.setBounds(x1+3*(w1+20), y, w1, h) ;
		
		/* Le Label de prix et son champ */
		prix.setBounds(x1+4*(w1+20), y, w1, h) ;
		prix_field.setBounds(x1+5*(w1+20), y, w1, h) ;
		
		/* Le JTable */
//		scroll.setBounds(w1+(5*(w1+20) - x2*2)/2, y+h+20, x2*2, 235-30+h);
		scroll.setBounds((w2- x2*2)/2 -50, y+h+20, x2*2, 235-30+h);
		
		/* Les boutons de controls */
//		bt.setPosition(w1+(5*(w1+20) - x2*2)/2, 235-30+h+10, h);
//		bt.getButtons(0).setBounds(x2 + 20-5-10-20, 300+50+15, 48, 48);
		/* Positionnnement du bouton reset */
		bt.getButtons(1).setBounds((w2-20-48-15)/2 -50, 300+50, 48, 48);
		/* Positionnnement du bouton update */
		bt.getButtons(2).setBounds((w2-20+48+15)/2 -50, 300+50, 48, 48) ;
		/* Positionnnement du bouton delete */
//		bt.getButtons(3).setBounds(x2 + 260-5-10-20, 300+50+15, 48, 48);
		/* positionnement du champ de recherche */
		search.getComponent(0).setBounds((w2- x2*2)/2 +110 -50, 300+50, 180, 20);
		search.getComponent(1).setBounds((w2- x2*2)/2 -50, 300+50 , 180, 20);
		print.setBounds((w2-20+48+15)/2 +20, 300+50, 48*2+20, 48);
		/* les images */
		chambreImage.getPanel().setBounds((w2- x2*2)/2 +x2*2 +15 -50, y+h+20, 320, 180);
	}
	
	public static PanelChambre getHinstance()
	{
		if(singleton == null)
		{
			singleton = new PanelChambre() ;
		}
		return singleton ;
	}
	
	public Background getPanel()
	{
		return panel ;
	}
}
