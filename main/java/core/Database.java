package main.java.core;

import java.sql.Connection;
import main.java.auth.configure.Configuration;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database
{

    private Connection connection;
    private Statement statement;
    private ResultSet resultset;

    /* Les infos pour la connexion */
    private Configuration config = Configuration.getInstance();

    private static Database singleton = null;

    /* Récupération du singleton de connexion */
    public static Database getHinstance() throws SQLException
    {
        if (singleton == null)
        {
            singleton = new Database();
        }
        return singleton;
    }

    /* Constructeur privé s */
    public Database()
    {
        
    }
    
    public void Connect() throws SQLException
    {
        connection = (Connection) DriverManager.getConnection(config.getURL());
        statement = (Statement) connection.createStatement();
    }

    public void Connect(String user, String passwd) throws SQLException
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
    
    public PreparedStatement prepare(String prepare) throws SQLException
    {
        return (PreparedStatement) this.connection.prepareStatement(prepare) ;
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
