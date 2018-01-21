package main.java.app;

import main.java.auth.configure.ConnectionView;
import main.java.auth.userconnection.AuthView;
import main.java.auth.configure.Configuration;
import main.java.core.Database;
import main.java.core.Message;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import main.resources.Rc;

public class Main
{

    public static void lookAndFell()
    {
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args)
    {
        try
        {
            lookAndFell();
            /* Something */
            Database.getHinstance();
            /* Vérification de la présence du fichier de configuration de la base de données */
            File database_configuration = new File(Rc.class.getResource("").getFile()+"config/db_config.json");

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
                if(cf.getSgbd().compareToIgnoreCase("sqlite") == 0)
                {
                    Database.getHinstance().Connect();
                }
                else
                {
                    Database.getHinstance().Connect(cf.getDatabaseUser(),cf.getDatabasePasswd());   
                }
                SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        new AuthView();
                    }
                });
            }
        } catch (SQLException ex)
        {
            Message.error("Impossible de se connecter à la base de données !");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            File to_delete = new File(Rc.class.getResource("").getFile()+"config/db_config.json");
            to_delete.delete();
            Main.main(args);
        } catch (FileNotFoundException ex)
        {
            Message.error("Fichier de configuration non trouvé !");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex)
        {
            Message.error("Erreur inconnue !");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
