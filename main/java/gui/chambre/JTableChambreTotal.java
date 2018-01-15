package main.java.gui.chambre;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import main.java.app.CheckEditor;

public class JTableChambreTotal
{

    private static JTableChambreTotal singleton = null;
    private JTableChambreTotalModel model;
    private JTable ChambreList;
    private JScrollPane scroll;

    private JTableChambreTotal()
    {
        Build_Table();
    }

    public static JTableChambreTotal getHinstance()
    {
        if (singleton == null)
        {
            singleton = new JTableChambreTotal();
        }
        return singleton;
    }

    private void Build_Table()
    {
        model = new JTableChambreTotalModel();
        ChambreList = new JTable(model);
        ChambreList.setDefaultEditor(JCheckBox.class, new CheckEditor(new JCheckBox()));
//		ChambreList.setShowGrid(false);
        for (int i = 0; i < ChambreList.getRowCount(); i++)
        {
            ChambreList.setRowHeight(25);
        }
        scroll = new JScrollPane(ChambreList);
    }

    public JTable getTable()
    {
        return ChambreList;
    }

    public JScrollPane getScroll()
    {
        return scroll;
    }

    public JTableChambreTotalModel getModel()
    {
        return model;
    }
}
