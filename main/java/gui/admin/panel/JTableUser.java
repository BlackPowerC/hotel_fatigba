/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.gui.admin.panel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author jordy
 * @since 0.2.1
 */
public class JTableUser
{
    
    private static JTableUser singleton;
    private JScrollPane scroll;
    private JTable table;
    private JTableUserModel tableUserModel;

    public static JTableUser getHinstance()
    {
        if (singleton == null)
        {
            singleton = new JTableUser();
        }
        return singleton;
    }
    
    private JTableUser()
    {
        buildTable();
    }
    
    /**
     * Cette fonction instancie les champs de la classe.
     */
    private void buildTable()
    {
        tableUserModel = new JTableUserModel();
        table = new JTable(tableUserModel);
        table.setShowGrid(true);
        for (int i = 0; i < table.getRowCount(); i++)
        {
            table.setRowHeight(25);
        }
        scroll = new JScrollPane(table);
    }

    public JScrollPane getScroll()
    {
        return scroll;
    }

    public JTable getTable()
    {
        return table;
    }

    public JTableUserModel getTableUserModel()
    {
        return tableUserModel;
    }

}
