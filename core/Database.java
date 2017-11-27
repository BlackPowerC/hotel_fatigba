package core;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import core.Encryption.AdvancedEncryption;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database
{
    private Connection connection;
    private Statement statement;
    private ResultSet resultset;

    /* Les infos pour la connexion */
    private Configuration config = Configuration.getInstance() ;

    private static Database singleton = null;

    /* Récupération du singleton de connexion */
    public static Database getHinstance(Configuration cf) throws SQLException
    {
        if (singleton == null)
        {
           if(cf.getSgbd().compareToIgnoreCase("sqlite") == 0)
           {
                singleton = new Database();
           }
           else
           {
                singleton = new Database(cf.getDatabaseUser(), AdvancedEncryption.getInstance().decrypt(cf.getDatabasePasswd(), "hotel_fatigba")) ;
           }
        }
        return singleton;
    }

    /* Constructeur privé s */
    private Database() throws SQLException
    {
        String url = "jdbc:sqlite:"+Database.class.getResource("").toString() ;

        connection = (Connection) DriverManager.getConnection(url);
        statement = (Statement) connection.createStatement();

    }

    private Database(String user, String passwd) throws SQLException
    {
        connection = (Connection) DriverManager.getConnection(config.getURL(), user, passwd);
        statement = (Statement) connection.createStatement();
    }

    /* fonction de requete select */
    public ResultSet executeQuery(String SelectQuery)
    {
        try
        {
            resultset = statement.executeQuery(SelectQuery);
        } catch (SQLException ex)
        {
            ex.printStackTrace(System.err);
        }
        return resultset;
    }

    /* Récupérer l'objet statement */
    public Statement getStatement()
    {
        return statement;
    }

    /* Récupérer l'objet resultset */
    public ResultSet getResultset()
    {
        return resultset;
    }

    /* Récupérer l'objet connection */
    public Connection getConnection()
    {
        return connection;
    }

    /* Fonction d'insertion et de modification de données insert */
    public int updateQuery(String updateQuery)
    {
        int rs = 0;
        try
        {
            rs = statement.executeUpdate(updateQuery);
        } catch (SQLException ex)
        {
            ex.printStackTrace(System.err);
        }
        return rs;
    }
    
    /* Fermeture de la connexion */
    public void close()
    {
        try
        {
            statement.close();
            connection.close();
        } catch (SQLException ex)
        {
            ex.printStackTrace(System.err);
        }
    }
}
