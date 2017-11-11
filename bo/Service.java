package bo;

public class Service
{
    private int id_ser;
    private String description;
    private float prix;

    public Service(int m_id_ser, String m_description, float m_prix)
    {
        id_ser = m_id_ser;
        description = m_description;
        prix = m_prix;
    }

    public Service(Service service)
    {
        id_ser = service.id_ser;
        description = service.description;
        prix = service.prix;
    }

    public Service()
    {
        id_ser = 0;
        description = "";
        prix = 0.0f;
    }

    public int getId_ser()
    {
        return id_ser;
    }

    public void setId_ser(int id_ser)
    {
        this.id_ser = id_ser;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public float getPrix()
    {
        return prix;
    }

    public void setPrix(float prix)
    {
        this.prix = prix;
    }

    @Override
    public String toString()
    {
        return "Service [id_ser=" + id_ser + ", description=" + description + ", prix=" + prix + "]";
    }
}
