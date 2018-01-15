package main.java.app;

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
	
	private void build()
	{
		Font font = new Font("Purisa", Font.BOLD, 25);
		txtApp = new JLabel("HOTEL WEST OCEAN") ;
		txtApp.setFont(font) ;
		fond = new Background("/home/jordy/workspace/hotel_fatigba/src/ressource/backgrounds/bg-hotel-bay.jpg") ;
		fond.setLayout(null) ;
		
		calendar = new JCalendar() ;
		calendar.setBounds(0, 0,400, 300);
		Toolkit tk = Toolkit.getDefaultToolkit() ;
		
		txtApp.setBounds(((int)tk.getScreenSize().getWidth()-200)/2 - 400, 50 ,800, 100) ;
		addContent() ;
	}
	
	public Welcome()
	{
		build() ;
	}
	
	public Background getPanel()
	{
		return fond ;
 	}
}
