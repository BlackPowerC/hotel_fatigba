package manageChambre;

public class Chambre 
{
	private int id_chambre;
	private String type_chambre;
	private String situation;
	private float prix;
	private Boolean state;

	public Chambre() 
	{
		id_chambre = 0;
		type_chambre = "";
		situation = "";
		prix = 0.0f;
	}

	public Chambre(Chambre chambre)
	{
		this.id_chambre = chambre.id_chambre;
		this.type_chambre = chambre.type_chambre;
		this.situation = chambre.situation;
		this.prix = chambre.prix;
		this.state = chambre.state;
	}

	public Chambre(int id_chambre, String type_chambre, String situation, float prix, int num_res, Boolean state)
	{
		this.id_chambre = id_chambre;
		this.type_chambre = type_chambre;
		this.situation = situation;
		this.prix = prix;
		this.state = state;
	}

	public int getId_chambre() 
	{
		return id_chambre;
	}

	public void setId_chambre(int id_chambre)
	{
		this.id_chambre = id_chambre;
	}

	public String getType_chambre()
	{
		return type_chambre;
	}

	public void setType_chambre(String type_chambre)
	{
		this.type_chambre = type_chambre;
	}

	public String getSituation()
	{
		return situation;
	}

	public void setSituation(String situation)
	{
		this.situation = situation;
	}

	public float getPrix()
	{
		return prix;
	}

	public void setPrix(float prix)
	{
		this.prix = prix;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}
	
	public void setChambre(Chambre chambre)
	{
		this.id_chambre = chambre.id_chambre;
		this.type_chambre = chambre.type_chambre;
		this.situation = chambre.situation;
		this.prix = chambre.prix;
		this.state = chambre.state;
	}
	
	public String toString() {
		return "Chambre [id_chambre=" + id_chambre + ", type_chambre=" + type_chambre + ", situation=" + situation
				+ ", prix=" + prix + ", state=" + state + "]";
	}

}
