package app;

import Auth.Configure.ConnectionView;
import Auth.userconnection.AuthView;
import Auth.Configure.Configuration;
import core.Database;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main
{
    public static void main(String[] args)
    {
        /* Vérification de la présence du fichier de configuration de la base de données */
        File database_configuration = new File(Main.class.getResource("../Auth/Configure/config.json").getPath());
        // On affiche la fenetre de configuration
        if (!database_configuration.exists())
        {
            ConnectionView cv = new ConnectionView();
        }
        // On affiche la fenetre de connexion
        else
        {
            Configuration cf = Configuration.getInstance();
            JSONParser ob = new JSONParser();
            try
            {
                JSONObject json = (JSONObject) ob.parse(new FileReader(database_configuration)) ;
                cf.setConfiguration(json) ;
                System.out.println(cf.toString()) ;
                System.out.println(cf.getURL()) ;
                Database.getHinstance(cf) ;
                AuthView av = new AuthView();
            }
            catch(FileNotFoundException ex)
            {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch(Exception ex)
            {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
