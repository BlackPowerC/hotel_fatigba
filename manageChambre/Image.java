package manageChambre;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import app.Background;

public class Image 
{
	private CardLayout card ;
	private JPanel contentPanel ;
	private Background Single ; // OK
	private Background Double ; // OK
	private Background Triple ; // OK
	private Background Standard ; // OK
	private Background Junior ; // OK
	private Background Buisiness ; // OK
	private Background Presidentielle ; // OK
	private Background _default ;
	
	public Image()
	{
		Build_Panel() ;
	}
	
	private String []label = {"Single", "Double", 
			  "Triple", "Standard", 
			  "Junior", "Buisiness Class", 
			  "Présidentielle"} ;
	
	public void show(String label)
	{
		boolean flag = true ;
		for(int i = 0; i<this.label.length; i++)
		{
			if(label == this.label[i])
			{
				flag = false ;
			}
		}
		if(flag == false)
		{
			card.show(contentPanel, "Default");
			System.out.println("Default");
			return ;
		}
		card.show(contentPanel, label);
	}
	
	private void Build_Background()
	{
		_default = new Background("/home/jordy/workspace/Hotel/src/manageChambre/default.png") ;
		Double = new Background("/home/jordy/workspace/Hotel/src/manageChambre/double2.jpg") ;
		Single = new Background("/home/jordy/workspace/Hotel/src/manageChambre/single.jpg") ;
		Standard = new Background("/home/jordy/workspace/Hotel/src/manageChambre/standard.jpg") ;
		Junior = new Background("/home/jordy/workspace/Hotel/src/manageChambre/junior.jpg") ;
		Buisiness = new Background("/home/jordy/workspace/Hotel/src/manageChambre/buisiness.jpg") ;
		Presidentielle = new Background("/home/jordy/workspace/Hotel/src/manageChambre/Presidentielle.jpg") ;
		Triple = new Background("/home/jordy/workspace/Hotel/src/manageChambre/triple.jpg") ;
	}
	
	public void Build_Panel()
	{
		Build_Background() ;
		contentPanel = new JPanel() ;
		contentPanel.setPreferredSize(new Dimension(320, 180));
		card = new CardLayout() ;
		contentPanel.setLayout(card) ;
		addContent() ;
	}
	
	public void addContent()
	{
		contentPanel.add(_default, "Default") ;
		contentPanel.add(Single, label[0]) ;
		contentPanel.add(Double, label[1]) ;
		contentPanel.add(Triple, label[2]) ;
		contentPanel.add(Standard, label[3]) ;
		contentPanel.add(Junior, label[4]) ;
		contentPanel.add(Buisiness, label[5]) ;
		contentPanel.add(Presidentielle, label[6]) ;
	}
	
	public JPanel getPanel()
	{
		return contentPanel ;
	}
}
