package app;

import Auth.Configure.ConnectionView;
import Auth.userconnection.AuthView;
import bo.Chambre ;
import core.Database;
import java.io.File ;
import java.util.List ;
import manager.ChambreManager ;

public class Main
{
    
    public static void main(String[] args)
    {
        File database_configuration = new File(Database.class.getResource("config.json").toString()) ;
        if(!database_configuration.exists())
        {
            ConnectionView cv = new ConnectionView() ;
        }
        else
        {
            AuthView av = new AuthView() ;
        }
    }
}
