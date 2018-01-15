package main.java.gui.facturation;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JTableFacturation
{

    private static JTableFacturation singleton = null;
    private JTableFacturationModel model;
    private JTable FacturationList;
    private JScrollPane scroll;

    private JTableFacturation()
    {
        Build_Table();
    }

    public static JTableFacturation getHinstance()
    {
        if (singleton == null)
        {
            singleton = new JTableFacturation();
        }
        return singleton;
    }

    private void Build_Table()
    {
        model = new JTableFacturationModel();
        FacturationList = new JTable(model);

        for (int i = 0; i < FacturationList.getRowCount(); i++)
        {
            FacturationList.setRowHeight(25);
        }
        scroll = new JScrollPane(FacturationList);
    }

    public JTable getTable()
    {
        return FacturationList;
    }

    public JScrollPane getScroll()
    {
        return scroll;
    }

    public JTableFacturationModel getModel()
    {
        return model;
    }

    @Override
    public JTableFacturation clone()
    {
        return new JTableFacturation();
    }
}
