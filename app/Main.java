package app;

import Auth.Configure.ConnectionView;
import Auth.userconnection.AuthView;
import Auth.Configure.Configuration;
import core.Database;
import core.Encryption.AdvancedEncryption;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main
{

    public static void main(String[] args)
    {
        try
        {
            /* Something */
            Database.getHinstance();
            /* Vérification de la présence du fichier de configuration de la base de données */
            File database_configuration = new File("config.json");
           
            // On affiche la fenetre de configuration
            if (!database_configuration.exists())
            {
                ConnectionView cv = new ConnectionView();
            } // On affiche la fenetre de connexion
            else
            {
                Configuration cf = Configuration.getInstance();
                JSONParser ob = new JSONParser();

                JSONObject json = (JSONObject) ob.parse(new FileReader(database_configuration));
                cf.setConfiguration(json);
                System.out.println(cf.toString());
                System.out.println(cf.getURL());
                Database.getHinstance().Connect(
                        Configuration.getInstance().getDatabaseUser(),
                        AdvancedEncryption.getInstance().decrypt(
                                Configuration.getInstance().getDatabasePasswd(), "hotel_new"));
                AuthView av = new AuthView();

            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
