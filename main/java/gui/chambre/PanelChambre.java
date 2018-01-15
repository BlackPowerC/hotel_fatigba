package main.java.gui.chambre;

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

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import main.java.core.Database;

import main.java.app.Background;
import main.java.app.Buttons;
import main.java.app.JSearch;

import main.java.bo.CaracteristiqueChambre;
import main.java.bo.Chambre;
import main.java.bo.Image;
import main.java.bo.SituationChambre;
import main.java.bo.TypeChambre;

import com.mysql.jdbc.PreparedStatement;
import java.io.PrintStream;
import java.util.regex.Pattern;
import java.text.NumberFormat;
import main.java.core.Message;
import main.java.manager.ChambreManager;
import main.java.manager.FactoryManager;
import main.java.manager.data.ListChambre;
import main.java.gui.Observateur;

public class PanelChambre implements Observateur
{
    public class BtnSearchAction implements KeyListener
    {
        @Override
        public void keyPressed(KeyEvent arg0){
            throw new UnsupportedOperationException("Méthode indisponible") ;
        }

        @Override
        public void keyReleased(KeyEvent arg0)
        {
            String key = search.getSearch_field().getText();
            System.out.println("PanelChambre: BtnSearchAction: " + key);
            searchClient(key);
        }

        @Override
        public void keyTyped(KeyEvent arg0){
            throw new UnsupportedOperationException("Méthode indisponible") ;
        }
    }

    public class BtnUpdateAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent eve)
        {
            if(updating)
            {
                setChambreData() ;
                
                if(!chambre.isValid())
                {
                    Message.warning("Certaines informations ne sont pas valides !");
                }
                else
                {
                    if(((ChambreManager)FactoryManager.getInstance().getManager(FactoryManager.CHAMBRE_MANAGER)).update(chambre) != -1)
                    {
                        ListChambre.getHinstance()
                            .getListChambreTotal()
                            .get(selectedRow).setChambre(chambre);
                        Message.information("Mise à jour de la chambre effectuée !");
                    }
                    update() ;
                }
            }
            else
            {
                Message.warning("Aucunes données à mettre à jour !");
            }
        }

        private void setChambreData()
        {
            /* Caractériqtique */
            chambre.getCaracteristique().setId(caracteristique_chambre_combo.getSelectedIndex());
            chambre.getCaracteristique().setDescription(caracteristique_chambre_combo.getSelectedItem().toString());
            /* Type */
            chambre.getType().setDescription(type_chambre_combo.getSelectedItem().toString());
            chambre.getType().setId(type_chambre_combo.getSelectedIndex());
            /* situation */
            chambre.getSituation().setDescription(situation_combo.getSelectedItem().toString());
            chambre.getSituation().setId(situation_combo.getSelectedIndex());
            /* prix */
            chambre.setPrix(Float.parseFloat(prix_field.getText()));
        }
    }

    public class TableAction implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent ev)
        {
            updating = true ;
            selectedRow = JTableChambreTotal.getHinstance().getTable().getSelectedRow() ;
            chambre = JTableChambreTotal.getHinstance().getModel().getValueAt(selectedRow);
            /* Remplissage du formulaire */
            type_chambre_combo.setSelectedItem(chambre.getType().getDescription()) ;
            situation_combo.setSelectedItem(chambre.getSituation().getDescription()) ;
            prix_field.setText(Float.toString(chambre.getPrix())) ;
            caracteristique_chambre_combo.setSelectedItem(chambre.getCaracteristique().getDescription());
        }

        @Override
        public void mouseEntered(MouseEvent arg0){}

        @Override
        public void mouseExited(MouseEvent arg0){}

        @Override
        public void mousePressed(MouseEvent arg0){}

        @Override
        public void mouseReleased(MouseEvent arg0){}
    }

    private int selectedRow;
    private boolean updating ;

    private Background panel;
    private Image chambreImage;

    private JScrollPane scroll;
    private Buttons bt;

    private JButton print;

    /* Label et champ associé */
    private JLabel caracteristique_chambre ;
    private JComboBox<String> caracteristique_chambre_combo ;
    
    private JLabel type_chambre;
    private JComboBox<String> type_chambre_combo;

    private JLabel situation;
    private JComboBox<String> situation_combo;

    private JLabel prix;
    private JFormattedTextField prix_field;

    private JSearch search;

    private JButton print_button;

    private Chambre chambre;

    private static PanelChambre singleton = null;

    private void searchClient(String key)
    {
        if (key == null || !Pattern.compile("^[a-zA-Z0-9]+", Pattern.CASE_INSENSITIVE).matcher(key).matches())
        {
        }
        else
        {
            String req = 
                    "SELECT c.id, c.id_type, c.id_situation, c.id_caracteristique, c.prix, c.etat, "
                   +"tc.description AS tcDescription, cc.description AS ccDescription, sc.description AS scDescription "
                   +"FROM Chambre c, SituationChambre sc, TypeChambre tc, CaracteristiqueChambre cc "
                   +"WHERE tcDescription LIKE '?%' or scDescription LIKE '?%' or prix LIKE '?%'";
            System.out.println(req) ;

            TypeChambre tc ;
            SituationChambre sc ;
            CaracteristiqueChambre cc ;
            List<Chambre> newListChambre = new ArrayList<Chambre>();
            try
            {
                PreparedStatement ps = (PreparedStatement) Database.getHinstance().getConnection().prepareStatement(req) ;
                ResultSet rs = ps.executeQuery() ;
                for(int i=1; i<=3; i++) {ps.setString(i, req);}
                System.out.println("PanelChambre: newListChambre");
                while (rs.next())
                {
                    tc = new TypeChambre() ; tc.setId(rs.getInt("id_type")); tc.setDescription(rs.getString("tcDescription"));
                    cc = new CaracteristiqueChambre() ; cc.setId(rs.getInt("id_caracteristique")); cc.setDescription(rs.getString("tcDescription"));
                    sc = new SituationChambre() ; sc.setId(rs.getInt("id_situation")); sc.setDescription(rs.getString("scDescription"));
                    newListChambre.add(new Chambre(rs, tc, cc, sc));
                }
                JTableChambreTotal.getHinstance().getModel().setData(newListChambre);
            } catch (SQLException e)
            {
                e.printStackTrace(new PrintStream(System.out));
            }
        }
        panel.remove(scroll);
        panel.add(scroll);
        panel.revalidate();
    }

    private void buildButton()
    {
        print = new JButton("imprimer");;
        bt = new Buttons();
        bt.getButtons(1).addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ev)
            {
                flush();
            }
        });
        bt.getButtons(2).addActionListener(new BtnUpdateAction());
        System.out.println("Panel Chambre: buildButton");
    }

    private PanelChambre()
    {
        search = new JSearch();
        chambre = new Chambre();
        chambreImage = new Image();
        updating = false;
        Build_Panel();
    }

    private void buildLabel()
    {
        String tmp1 = "<html><font color=#FEFDF0>";
        String tmp2 = "<font/></html>";
        Font font = new Font("Purisa", Font.BOLD, 16);
        type_chambre = new JLabel(tmp1 + "Type de Chambre: " + tmp2);
        type_chambre.setFont(font);
        prix = new JLabel(tmp1 + "Prix: " + tmp2);
        prix.setFont(font);
        situation = new JLabel(tmp1 + "Situation: " + tmp2);
        situation.setFont(font);
        caracteristique_chambre = new JLabel(tmp1+"Caractéristique: "+tmp2) ;
        caracteristique_chambre.setFont(font) ;
    }

    private void buildTextField()
    {
        caracteristique_chambre_combo = new JComboBox<String>(new String[]{"", "ventilée", "climatisée"}) ;
        situation_combo = new JComboBox<String>(new String[]{"", "intérieur", "jardin", "balcon"});
        type_chambre_combo = new JComboBox<String>(new String[]{"", "single", "double", "triple"});

        prix_field = new JFormattedTextField(NumberFormat.getNumberInstance());
        search.getComponent(0).addKeyListener(new BtnSearchAction());
    }

    private void Build_Panel()
    {
        panel = new Background();
        panel.setLayout(null);
        buildButton();
        buildLabel();
        buildTextField();
        addContent();
        setPosition();
    }

    private void addContent()
    {
        JTableChambreTotal.getHinstance().getTable().addMouseListener(new TableAction());
        scroll = JTableChambreTotal.getHinstance().getScroll();
        panel.add(print);
        panel.add(chambreImage.getPanel());
        panel.add(search.getComponent(0));
        panel.add(search.getComponent(1));
        panel.add(bt.getButtons(1));
        panel.add(bt.getButtons(2));
        panel.add(scroll);
        panel.add(type_chambre);
        panel.add(prix);
        panel.add(caracteristique_chambre) ;
        panel.add(caracteristique_chambre_combo) ;
        panel.add(prix_field);
        panel.add(situation);
        panel.add(situation_combo);
        panel.add(type_chambre_combo);
    }

    private void setPosition()
    {
        int x1 = 70, x2 = 360, w1 = 180, y = 30 + 15, h = 20 + 10, w2 = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        /* type de chambre et son combo */
        type_chambre.setBounds(x1, y, w1 + 40 + 10, h);
        type_chambre_combo.setBounds(x1 + w1 + 20, y, w1, h);

        /* Le Label de situation et son combo */
        situation.setBounds(x1 + 2 * (w1 + 20), y, w1, h);
        situation_combo.setBounds(x1 + 3 * (w1 + 20), y, w1, h);

        /* Le Label de prix et son champ */
        prix.setBounds(x1 + 4 * (w1 + 20), y+h+10, w1, h);
        prix_field.setBounds(x1 + 5 * (w1 + 20), y+h+10, w1, h);
        
        caracteristique_chambre.setBounds(x1 + 4 * (w1 + 20), y, w1, h);
        caracteristique_chambre_combo.setBounds(x1 + 5 * (w1 + 20), y, w1, h);

        /* Le JTable */
//		scroll.setBounds(w1+(5*(w1+20) - x2*2)/2, y+h+20, x2*2, 235-30+h);
        scroll.setBounds(x1, y + h + 20, x2 * 2, 235 - 30 + h);

        /* Les boutons de controls */
//		bt.setPosition(w1+(5*(w1+20) - x2*2)/2, 235-30+h+10, h);
//		bt.getButtons(0).setBounds(x2 + 20-5-10-20, 300+50+15, 48, 48);
        /* Positionnnement du bouton reset */
        bt.getButtons(1).setBounds((w2 - 20 - 48 - 15) / 2 - 50, 300 + 50, 48, 48);
        /* Positionnnement du bouton update */
        bt.getButtons(2).setBounds((w2 - 20 + 48 + 15) / 2 - 50, 300 + 50, 48, 48);
        /* Positionnnement du bouton delete */
//		bt.getButtons(3).setBounds(x2 + 260-5-10-20, 300+50+15, 48, 48);
        /* positionnement du champ de recherche */
        search.getComponent(0).setBounds((w2 - x2 * 2) / 2 + 110 - 50, 300 + 50, 180, 20);
        search.getComponent(1).setBounds((w2 - x2 * 2) / 2 - 50, 300 + 50, 180, 20);
        print.setBounds((w2 - 20 + 48 + 15) / 2 + 20, 300 + 50, 48 * 2 + 20, 48);
        /* les images */
        chambreImage.getPanel().setBounds((w2 - x2 * 2) / 2 + x2 * 2 + 15 - 50, y + h + 20, 320, 180);
    }

    public static PanelChambre getHinstance()
    {
        if (singleton == null)
        {
            singleton = new PanelChambre();
        }
        return singleton;
    }

    public Background getPanel()
    {
        return panel;
    }
    
    private void flush()
    {
        prix_field.setText("");
        situation_combo.setSelectedItem("");
        type_chambre_combo.setSelectedItem("");
        caracteristique_chambre_combo.setSelectedItem("") ;
    }
    
    @Override
    public void update()
    {
        flush() ;
        panel.remove(scroll);
        panel.add(scroll) ;
        panel.revalidate();
    }
}
