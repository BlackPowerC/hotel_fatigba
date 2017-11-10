package manageClient;

/**
 * 
 * @author jordy
 */
public class Client extends Personne
{
	private int m_membre;
	private String m_type;
	
	private Boolean m_is_no_local;
	private int m_id_client;
	private Boolean m_has_fidelity_card = false;

	public Client(int age, String nom, String prenom, String sexe,
			String nation, boolean a, String type, int n)
	{
		super(age, nom, prenom, sexe, nation);
		m_type = type;
		m_has_fidelity_card = a;
		m_membre = n;
	}

	public Client(Client client)
	{
		super(client.m_age, client.m_nom, client.m_prenom, client.m_sexe,client.m_nation);
		m_type = client.m_type;
		m_has_fidelity_card = client.m_has_fidelity_card;
		m_membre = client.m_membre;
		m_id_client = client.m_id_client;
	}

	public Client()
	{
		super();
	}

	public String getM_type()
	{
		return m_type;
	}

	public int getM_id_client()
	{
		return m_id_client;
	}

	public int getM_membre()
	{
		return m_membre;
	}

	public Boolean isM_has_fidelity_card() {
		return m_has_fidelity_card;
	}

	public Boolean isM_is_no_local() {
		return m_is_no_local;
	}

	public void setM_type(String m_type) {
		this.m_type = m_type;
	}

	public void setM_id_client(int m_id_client) {
		this.m_id_client = m_id_client;
	}

	public void setM_membre(int m_membre) {
		this.m_membre = m_membre;
	}

	public void setM_has_fidelity_card(Boolean m_has_fidelity_card) {
		this.m_has_fidelity_card = m_has_fidelity_card;
	}

	public void setM_is_no_local(Boolean m_is_no_local) {
		this.m_is_no_local = m_is_no_local;
	}

	public int getM_age() {
		return m_age;
	}

	public String getM_nom() {
		return m_nom;
	}

	public String getM_prenom() {
		return m_prenom;
	}

	public String getM_sexe() {
		return m_sexe;
	}

	public String getM_nation() {
		return m_nation;
	}

	public void setM_age(int m_age) {
		this.m_age = m_age;
	}

	public void setM_nom(String m_nom) {
		this.m_nom = m_nom;
	}

	public void setM_prenom(String m_prenom) {
		this.m_prenom = m_prenom;
	}

	public void setM_sexe(String m_sexe) {
		this.m_sexe = m_sexe;
	}

	public void setM_nation(String m_nation) {
		this.m_nation = m_nation;
	}

	
	
	@Override
	public String toString() {
		return "Client [m_membre=" + m_membre + ", m_type=" + m_type + ", m_is_no_local=" + m_is_no_local
				+ ", m_id_client=" + m_id_client + ", m_has_fidelity_card=" + m_has_fidelity_card + ", m_age=" + m_age
				+ ", m_nom=" + m_nom + ", m_sexe=" + m_sexe + ", m_prenom=" + m_prenom + ", m_nation=" + m_nation + "]";
	}

	public void setClient(Client client)
	{
		m_sexe = client.m_sexe;
		m_nation = client.m_nation;
		m_age = client.m_age;
		m_nom = client.m_nom;
		m_prenom = client.m_prenom;
		m_type = client.m_type;
		m_has_fidelity_card = client.m_has_fidelity_card;
		m_membre = client.m_membre;
		m_id_client = client.m_id_client;
	}
	
}
