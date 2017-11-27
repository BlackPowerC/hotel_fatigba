package manager.data;

import manageReservation.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.DataBaseCon;

public class ListReservation
{

    private List<Reservation> listReservation = new ArrayList<Reservation>();
    private Reservation last_reservation_in_list;
    private Reservation reservation;
    private static ListReservation singleton = null;

    public static ListReservation getHinstance()
    {
        if (singleton == null)
        {
            singleton = new ListReservation();
        }
        return singleton;
    }

    private ListReservation()
    {
        ResultSet rs;
        reservation = new Reservation();
        last_reservation_in_list = new Reservation();
        try
        {
            DataBaseCon.getHinstance().executeQuery("select r.solved, r.id_res, r.id_cl, r.id_ch, r.debut_res, r.fin_res, r.date_res, r.state, c.nom, c.prenom from Reservation r, Client c where r.id_cl = c.id_client");
            rs = DataBaseCon.getHinstance().getResultset();
            while (rs.next())
            {
                reservation.setId_res(rs.getInt("id_res"));
                reservation.setId_cl(rs.getInt("id_cl"));
                reservation.setId_ch(rs.getInt("id_ch"));
                reservation.setNom_prenom_client(rs.getString("nom") + " " + rs.getString("prenom"));
                reservation.setDebut_res(rs.getString("debut_res"));
                reservation.setFin_res(rs.getString("fin_res"));
                reservation.setDate_res(rs.getString("date_res"));
                reservation.setEtat_res(rs.getString("state"));
                reservation.setSolved(rs.getBoolean("solved"));
                listReservation.add(new Reservation(reservation));
                last_reservation_in_list = reservation;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<Reservation> getListReservation()
    {
        return listReservation;
    }

    public Reservation getLastReservartion()
    {
        return last_reservation_in_list;
    }

    public void setLastReservation(Reservation r)
    {
        last_reservation_in_list.setReservation(r);
    }
}
