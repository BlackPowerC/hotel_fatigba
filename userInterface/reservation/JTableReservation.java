package userInterface.reservation;

import javax.swing.*;

public class JTableReservation
{

    private JTable tableReservation;
    private JScrollPane scroll;
    private JTableReservationModel mdl;
    private static JTableReservation singleton = null;

    private JTableReservation()
    {
        Build_Table();
    }

    public static JTableReservation getHinstance()
    {
        if (singleton == null)
        {
            singleton = new JTableReservation();
        }
        return singleton;
    }

    private void Build_Table()
    {
        mdl = new JTableReservationModel();
        tableReservation = new JTable(mdl);
//		tableReservation.setShowGrid(false);

        for (int i = 0; i < tableReservation.getRowCount(); i++)
        {
            tableReservation.setRowHeight(25);
        }
        scroll = new JScrollPane(tableReservation);
    }

    public JScrollPane getScroll()
    {
        return scroll;
    }

    public JTable getTable()
    {
        return tableReservation;
    }

    public JTableReservationModel getModel()
    {
        return mdl;
    }
}
