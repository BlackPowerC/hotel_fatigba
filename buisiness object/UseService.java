package manageService;

public class UseService
{
	private int id_us ;
	private int id_ser ;
	private int id_cl ;
	private float prix ;
	private String nom_prenom ;
	private String desc_service ;
	private boolean state ;
	
	public UseService() 
	{
		prix = 0.0f ;
		nom_prenom = desc_service = "" ;
		id_us = id_ser = id_cl = 0 ;
		state = false ;
	}
	
	public UseService(UseService us)
	{
		this.id_us = us.id_us;
		this.id_ser = us.id_ser;
		this.id_cl = us.id_cl;
		this.desc_service = us.desc_service ;
		this.nom_prenom = us.nom_prenom ;
		this.prix = us.prix ;
		this.state = us.state ;
	}

	public int getId_us()
	{
		return id_us;
	}
	
	public void setId_us(int id_us)
	{
		this.id_us = id_us;
	}
	
	public int getId_ser()
	{
		return id_ser;
	}
	
	public void setId_ser(int id_ser)
	{
		this.id_ser = id_ser;
	}
	
	public int getId_cl()
	{
		return id_cl;
	}
	
	public void setId_cl(int id_cl)
	{
		this.id_cl = id_cl;
	}
	
	
	public String getNom_prenom() {
		return nom_prenom;
	}

	public void setNom_prenom(String nom_prenom) {
		this.nom_prenom = nom_prenom;
	}

	public String getDesc_service() {
		return desc_service;
	}

	public void setDesc_service(String desc_service) {
		this.desc_service = desc_service;
	}
	
	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}
	
	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public void setUseService(UseService us)
	{
		this.id_us = us.id_us;
		this.id_ser = us.id_ser;
		this.id_cl = us.id_cl;
		this.desc_service = us.desc_service ;
		this.nom_prenom = us.nom_prenom ;
		this.prix = us.prix ;
		this.state = us.state ;
	}

	public String insertSQL() 
	{
		return "insert into UseService values ("+id_us+","+id_cl+","+id_ser+", false)" ;
	}
	
	public String updateSQL()
	{
		return "update UseService set id_cl="+id_cl+", id_ser="+id_ser+", state=false where id_us="+id_us ;
	}
	
	public String deleteSQL()
	{
		return "delete from UseService where id_us="+id_us ;
	}
}
