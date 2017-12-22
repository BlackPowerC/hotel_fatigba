package userInterface.reservation;

import bo.Reservation;
import manager.data.ListReservation;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class JTableReservationModel extends AbstractTableModel
{

    private static final long serialVersionUID = 1L;
    private List<Reservation> reservationList = ListReservation.getHinstance().getListReservation();
    private String[] label;

    public JTableReservationModel(String m_label[])
    {
        label = m_label;
    }

    public JTableReservationModel()
    {
        label = new String[]
        {
            "N° Chambre", "Client", "Debut", "Fin", "Date", "Etat"
        };
    }

    public int getColumnCount()
    {
        return this.label.length;
    }

    public String getColumnName(int index)
    {
        return label[index];
    }

    public int getRowCount()
    {
        return this.reservationList.size();
    }

    public boolean isCellEditable()
    {
        return false;
    }

    public Reservation getValueAt(int row)
    {
        Reservation tmp = new Reservation(reservationList.get(row));
        return tmp;
    }

    public void setValueAt(Reservation reservation, int row)
    {
        reservationList.get(row).setReservation(reservation);
    }

    public Object getValueAt(int row, int col)
    {
        Reservation tmp = reservationList.get(row);
//		System.out.println("JTableReservationModel: "+tmp.toString());
        switch (col)
        {

        }
        return null;
    }

    public void addRow()
    {
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void remove(int row)
    {
        fireTableRowsDeleted(row, row);
    }
}
