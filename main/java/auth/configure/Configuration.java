/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.auth.configure;

import main.resources.Rc;
import org.json.simple.JSONObject;

/**
 *
 * @author jordy
 */
public class Configuration
{
    private String databaseName;
    private String databasePasswd ;
    private String databaseUser ;
    private String databaseHost ;
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
    
    private Configuration() {}
    
    public void setConfiguration(JSONObject json)
    {
        if(json == null)
        {
            return ;
        }
        databaseName = (String) json.get("Name");
        databasePasswd = (String) json.get("Pass");
        databaseUser = (String) json.get("User");
        databaseHost = (String) json.get("Host");
        sgbd = (String) json.get("SGBD");
        sgbdPort = (String) json.get("Port");
        sgbdHost = (String) json.get("Host");
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

    public String getDatabaseHost()
    {
        return databaseHost;
    }

    public void setDatabaseHost(String databaseHost)
    {
        this.databaseHost = databaseHost;
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

    public String getURL()
    {
        if(this.sgbd.compareToIgnoreCase("sqlite") == 0)
        {
            return "jdbc:"+this.sgbd+"://" + Rc.class.getResource("").getFile()+"database/hotel_new.sqlite";
        }
        return "jdbc:"+this.sgbd+"://"+this.databaseHost+":"+this.sgbdPort+"/"+this.databaseName ;
    }

    @Override
    public String toString()
    {
        return "Configuration{" + "databaseName=" + databaseName + ", databasePasswd=" + databasePasswd + ", databaseUser=" + databaseUser + ", databaseHost=" + databaseHost + ", sgbd=" + sgbd + ", sgbdPort=" + sgbdPort + ", sgbdHost=" + sgbdHost + '}';
    }
}
