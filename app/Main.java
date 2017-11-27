package app;

import Auth.Configure.ConnectionView;
import Auth.userconnection.AuthView;
import core.Database;
import java.io.File ;

public class Main
{
    public static void main(String[] args)
    {
        File database_configuration = new File("/home/jordy/workspace/hotel_fatigba/config.json") ;
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
