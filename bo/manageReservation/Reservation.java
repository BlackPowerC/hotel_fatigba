package manageReservation;

public class Reservation
{
    private int id_res;
    private int id_cl;
    private String nom_prenom_client;
    private int id_ch;

    String debut_res;
    String fin_res;
    String date_res;

    String state;

    boolean solved;

    public Reservation()
    {
        id_res = id_cl = id_ch = 0;
        debut_res = "";
        date_res = "";
        fin_res = "";
        solved = false;
    }

    public Reservation(Reservation reservation)
    {
        id_res = reservation.id_res;
        id_cl = reservation.id_cl;
        id_ch = reservation.id_ch;
        debut_res = reservation.debut_res;
        date_res = reservation.date_res;
        fin_res = reservation.fin_res;
        state = reservation.state;
        nom_prenom_client = reservation.nom_prenom_client;
        solved = reservation.solved;
    }

    public void setReservation(Reservation reservation)
    {
        id_res = reservation.id_res;
        id_cl = reservation.id_cl;
        id_ch = reservation.id_ch;
        debut_res = reservation.debut_res;
        date_res = reservation.date_res;
        fin_res = reservation.fin_res;
        state = reservation.state;
        nom_prenom_client = reservation.nom_prenom_client;
        solved = reservation.solved;
    }

    public String getNom_prenom_client()
    {
        return nom_prenom_client;
    }

    public void setNom_prenom_client(String nom_prenom_client)
    {
        this.nom_prenom_client = nom_prenom_client;
    }

    public int getId_res()
    {
        return id_res;
    }

    public void setId_res(int id_res)
    {
        this.id_res = id_res;
    }

    public int getId_cl()
    {
        return id_cl;
    }

    public void setId_cl(int id_cl)
    {
        this.id_cl = id_cl;
    }

    public int getId_ch()
    {
        return id_ch;
    }

    public void setId_ch(int id_ch)
    {
        this.id_ch = id_ch;
    }

    public String getDebut_res()
    {
        return debut_res;
    }

    public void setDebut_res(String debut_res)
    {
        this.debut_res = debut_res;
    }

    public String getFin_res()
    {
        return fin_res;
    }

    public void setFin_res(String fin_res)
    {
        this.fin_res = fin_res;
    }

    public String getDate_res()
    {
        return date_res;
    }

    public void setDate_res(String date_res)
    {
        this.date_res = date_res;
    }

    public String getEtat_res()
    {
        return state;
    }

    public void setEtat_res(String etat_res)
    {
        this.state = etat_res;
    }

    public boolean isSolved()
    {
        return solved;
    }

    public void setSolved(boolean solved)
    {
        this.solved = solved;
    }

    public String updateSQL()
    {
        return "update Reservation set id_cl=" + this.id_cl
                + ", id_ch=" + this.id_ch + ", debut_res='" + this.debut_res.toString()
                + "' , fin_res='" + this.fin_res.toString() + "' , date_res='" + this.date_res.toString()
                + "' , state='" + this.state + "' where id_res=" + this.id_res;
    } 

    public String deleteSQL()
    {
        return "delete from Reservation where id_res=" + this.id_res;
    }

    public String insertSQL()
    {
        return "insert into Reservation values ("
                + this.id_res + "," + this.id_cl + "," + this.id_ch + ",'" + this.debut_res.toString() + "','" + this.fin_res.toString()
                + "','" + this.date_res.toString() + "','" + this.state + "', 0, 0)";
    }

    @Override
    public String toString()
    {
        return "Reservation [id_res=" + id_res + ", id_cl=" + id_cl + ", nom_prenom_client=" + nom_prenom_client
                + ", id_ch=" + id_ch + ", debut_res=" + debut_res + ", fin_res=" + fin_res + ", date_res=" + date_res
                + ", state=" + state + ", solved=" + solved + "]";
    }
}
