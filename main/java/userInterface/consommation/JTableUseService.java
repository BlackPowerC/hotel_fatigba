package main.java.userInterface.consommation;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JTableUseService
{

    private JScrollPane scroll;
    private JTable table;
    private JTableUserServiceModel model;
    private static JTableUseService singleton = null;

    private void Rendering()
    {
        for (int i = 0; i < table.getRowCount(); i++)
        {
            table.setRowHeight(25);
        }
    }

    private void Build_All()
    {
        model = new JTableUserServiceModel();
        table = new JTable(model);
        scroll = new JScrollPane(table);
    }

    public static JTableUseService getHinstance()
    {
        if (singleton == null)
        {
            singleton = new JTableUseService();
        }
        return singleton;
    }

    private JTableUseService()
    {
        Build_All();
        Rendering();
    }

    public JScrollPane getScroll()
    {
        return scroll;
    }

    public JTable getTable()
    {
        return table;
    }

    public JTableUserServiceModel getModel()
    {
        return model;
    }

}
