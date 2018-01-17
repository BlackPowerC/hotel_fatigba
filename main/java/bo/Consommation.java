package main.java.bo ;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Consommation extends Entity
{
    protected Service service ;
    protected Client client ;

    public Consommation()
    {
        super();
        service = new Service();
        client = new Client();
    }

    public Consommation(int id, Service service, Client client)
    {
        this.id = id;
        this.service = service;
        this.client = client;
    }
    
    public Consommation(Consommation c)
    {
        this.id = c.id;
        this.service=c.service;
        this.client = c.client;
    }
    
    public Consommation(ResultSet rs, Service service, Client client) throws SQLException
    {
        super();
        this.id = rs.getInt("id") ;
        this.service = service;
        this.client = client;
    }

    @Override
    public boolean isValid()
    {
        return client.isValid() && service.isValid() ;
    }

    public Service getService()
    {
        return service;
    }

    public void setService(Service service)
    {
        this.service.setService(service);
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client.setClient(client);
    }
    
    public void setConsommation(Consommation c)
    {
        this.id = c.id;
        this.service=c.service;
        this.client = c.client;
    }
}