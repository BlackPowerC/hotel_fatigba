package app;

import javax.swing.SwingUtilities;
import Auth.ConnectionView;

public class Main
{
    public static void main(String args[])
    {
        Class s ;
        SwingUtilities.invokeLater(new Runnable()
        {
            @SuppressWarnings("unused")
            public void run()
            {
                ConnectionView auth = new ConnectionView();
            }
        });
    }
}
