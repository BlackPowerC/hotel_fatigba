/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author jordy
 */
public class Configuration
{
    private String databaseName;
    private String databasePasswd ;
    private String databaseUser ;
    private String sgbd ;
    private String sgbdPort ;
    private String sgbdHost ;
    private static Configuration p_singleton ;
    
    public static Configuration getInstance()
    {
        if(p_singleton == null)
        {
            p_singleton = new Configuration() ;
        }
        return p_singleton;
    }
    
    private Configuration()
    {
        
    }    
    
    public String getDatabaseName()
    {
        return databaseName;
    }

    public void setDatabaseName(String databaseName)
    {
        this.databaseName = databaseName;
    }

    public String getDatabasePasswd()
    {
        return databasePasswd;
    }

    public void setDatabasePasswd(String databasePasswd)
    {
        this.databasePasswd = databasePasswd;
    }

    public String getDatabaseUser()
    {
        return databaseUser;
    }

    public void setDatabaseUser(String databaseUser)
    {
        this.databaseUser = databaseUser;
    }

    public String getSgbd()
    {
        return sgbd;
    }

    public void setSgbd(String sgbd)
    {
        this.sgbd = sgbd;
    }

    public String getSgbdPort()
    {
        return sgbdPort;
    }

    public void setSgbdPort(String sgbdPort)
    {
        this.sgbdPort = sgbdPort;
    }

    public String getSgbdHost()
    {
        return sgbdHost;
    }

    public void setSgbdHost(String shbdHost)
    {
        this.sgbdHost = shbdHost;
    }
    
    
}
