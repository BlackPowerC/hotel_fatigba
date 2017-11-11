package manageClient;

/**
 * 
 * @author jordy
 */
public abstract class Personne {
	protected int m_age; // ok
	protected String m_nom; // ok
	protected String m_sexe; // ok
	protected String m_prenom; // ok
	protected String m_nation; // ok

	public Personne(int age, String nom, String prenom, String sexe,
			String nation) {
		m_sexe = sexe;
		m_nation = nation;
		m_age = age;
		m_nom = nom;
		m_prenom = prenom;
	}

	public Personne() {
		m_age = 0;
		m_nation = "";
		m_sexe = "";
		m_nom = "";
		m_prenom = "";
	}

	public abstract int getM_age();

	public abstract void setM_age(int m_age);

	public abstract String getM_nom();

	public abstract void setM_nom(String m_nom);

	public abstract String getM_prenom();

	public abstract void setM_prenom(String m_prenom);

	public abstract String getM_sexe();

	public abstract String getM_nation();

	public abstract void setM_nation(String m_nation);
}