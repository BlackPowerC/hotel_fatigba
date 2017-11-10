package app;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;

import com.toedter.calendar.JCalendar;

public class Welcome 
{
	private static Welcome singleton = null ;
	
	private JLabel txtApp ;
	private JCalendar calendar ;
	private Background fond ;
	
	public static Welcome getHinstance()
	{
		return (singleton == null) ? (singleton = new Welcome()) : singleton ;
	}
	
	public void addContent()
	{
	//	fond.add(calendar);
		fond.add(txtApp) ;
	}
	
	private void Build()
	{
		String tmp1 = "<html><font color=#FEFDF0>";
		String tmp2 = "<font/></html>";
		Font font = new Font("Purisa", Font.BOLD, 25);
		txtApp = new JLabel("HOTEL WEST OCEAN") ;
		txtApp.setFont(font) ;
		fond = new Background("/home/jordy/workspace/Hotel/src/backgrounds/bg-hotel-bay.jpg") ;
		fond.setLayout(null) ;
		
		calendar = new JCalendar() ;
		calendar.setBounds(0, 0,400, 300);
		Toolkit tk = Toolkit.getDefaultToolkit() ;
	//	setSize((int)tk.getScreenSize().getWidth()-20, (int) tk.getScreenSize().getHeight()-40);
		
		
		txtApp.setBounds(((int)tk.getScreenSize().getWidth()-20)/2 - 400, 50 ,800, 100) ;
		addContent() ;
	}
	
	public Welcome()
	{
		Build() ;
	}
	
	public Background getPanel()
	{
		return fond ;
 	}
}
