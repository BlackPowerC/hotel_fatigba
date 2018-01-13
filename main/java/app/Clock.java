package app ;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class Clock implements ActionListener
{
    private final Calendar calendar ;
//  private static Clock singleton = null ;
    
    private int seconde ;
    private int minute ;
    private int heure ;
    private final Timer timer ;
    private JLabel label ;
    
/*  public static Clock getHinstance()
    {
    	if(singleton == null)
    	{
    		singleton = new Clock() ;
    	}
    	return singleton ;
    }
*/    
    public Clock()
    {        
        seconde = 0 ;
        minute = 0 ;
        heure = 0;
        timer = new Timer(1000,this) ;
        
        calendar = Calendar.getInstance() ;
        Build_Label() ;
        run() ;
    }

    private void run()
    {
        timer.setRepeats(true);
        timer.start ();
    }
    
    private void Build_Label()
    {
        heure = calendar.get(Calendar.HOUR_OF_DAY) ;
        minute = calendar.get(Calendar.MINUTE) ;
        seconde = calendar.get(Calendar.SECOND) ;
        label = new JLabel("<html><font color=#FEFDF0>"+heure+" heures :"+minute+" minutes :"+seconde+" secondes"+"<font/></html>") ;
    }
    
    public void actionPerformed(ActionEvent event)
    {
        seconde++ ;
        if(seconde >= 60)
        {
            seconde = 0 ;
            minute++ ;
        }
        if(minute >= 60)
        {
            minute = 0 ;
            heure++ ;
        }
        if(heure >= 24)
        {
            heure = 0 ;
        }
        label.setText("<html><font color=#FEFDF0>"+heure+" heures :"+minute+" minutes :"+seconde+" secondes"+"<font/></html>");
    }
    
    public JLabel getLabel()
    {
    	return label ;
    }
}