package manageFacturation;

public class Facturation 
{
	private int id_fa ;
	private int id_cl ;
	private String nom_prenom ;
	private String mode ;
	private float total ;
	private float totalRem ;
	private float totalSer ;
	private float totalRes ;
	
	public Facturation(int id_fa, int id_cl, String mode,
			float total, float totalRes, float totalSer, float totalRem, String nom_prenom)
	{
		this.id_fa = id_fa;
		this.id_cl = id_cl;
		this.mode = mode;
		this.total = total;
		this.totalRes = totalRes;
		this.totalSer = totalSer;
		this.nom_prenom = nom_prenom ;
		this.totalRem = totalRem ;
	}
	
	public Facturation()
	{
		super();
		this.id_fa = 0;
		this.id_cl = 0;
		this.mode = "";
		this.nom_prenom = "" ;
		this.total = this.totalRes = this.totalSer = this.totalRem = .0f;
	}



	public Facturation(Facturation f)
	{
		this.id_fa = f.id_fa;
		this.id_cl = f.id_cl;
		this.mode = f.mode;
		this.total = f.total;
		this.totalRes = f.totalRes;
		this.totalSer = f.totalSer;
		this.nom_prenom = f.nom_prenom ;
		this.totalRem = f.totalRem ;
	}
	
	public void setFacturation(Facturation f)
	{
		this.id_fa = f.id_fa;
		this.id_cl = f.id_cl;
		this.mode = f.mode;
		this.total = f.total;
		this.totalRes = f.totalRes;
		this.totalSer = f.totalSer;
		this.nom_prenom = f.nom_prenom ;
		this.totalRem = f.totalRem ;
	}
	
	public Facturation getFacturation()
	{
		return new Facturation(this) ;
	}

	public int getId_fa() {
		return id_fa;
	}

	public void setId_fa(int id_fa) {
		this.id_fa = id_fa;
	}

	public int getId_cl() {
		return id_cl;
	}

	public void setId_cl(int id_cl) {
		this.id_cl = id_cl;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getTotalSer() {
		return totalSer;
	}

	public void setTotalSer(float totalSer) {
		this.totalSer = totalSer;
	}

	public float getTotalRes() {
		return totalRes;
	}

	public void setTotalRes(float totalRes) {
		this.totalRes = totalRes;
	}

	public String getNom_prenom() {
		return nom_prenom;
	}

	public void setNom_prenom(String nom_prenom) {
		this.nom_prenom = nom_prenom;
	}

	public float getTotalRem() {
		return totalRem;
	}

	public void setTotalRem(float totalRem) {
		this.totalRem = totalRem;
	}
}
