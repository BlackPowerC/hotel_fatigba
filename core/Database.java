package app;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseCon
{

    private Connection connection;
    private Statement statement;
    private ResultSet resultset;

    /* Les infos pour la connexion */
    private String url = "";
    private String user = "";
    private String passwd = "";

    private static DataBaseCon singleton = null;

    /* Récupération du singleton de connexion */
    public static DataBaseCon getHinstance() throws SQLException
    {
        if (singleton == null)
        {
            singleton = new DataBaseCon();
        }
        return singleton;
    }

    public static DataBaseCon getHinstance(String dataBaseName,
            String databaseUser,
            String userPassword) throws SQLException
    {
        if (singleton == null)
        {
            singleton = new DataBaseCon(dataBaseName, databaseUser, userPassword);
        }
        return singleton;
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

    /* Constructeur privé s */
    private DataBaseCon() throws SQLException
    {
        this.url = "jdbc:mysql://localhost/hotel";
        this.user = "jordy";
        this.passwd = "jordy";

        connection = (Connection) DriverManager.getConnection(url, user, passwd);
        statement = (Statement) connection.createStatement();

    }

    private DataBaseCon(String dataBaseName,
            String databaseUser,
            String userPassword) throws SQLException
    {
        this.url = "jdbc:mysql://localhost/" + dataBaseName;
        this.user = databaseUser;
        this.passwd = userPassword;

        connection = (Connection) DriverManager.getConnection(url, user, passwd);
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
}
