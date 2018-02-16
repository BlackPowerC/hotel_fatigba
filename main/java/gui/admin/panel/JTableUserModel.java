/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.gui.admin.panel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import main.java.bo.Utilisateur;
import main.java.manager.data.ListUser;

/**
 *
 * @author jordy fatigba
 * @since 0.2.1
 */
public class JTableUserModel extends AbstractTableModel
{
    protected String[] column ;
    protected List<Utilisateur> data  ; 
    
    public JTableUserModel()
    {
        this.column = new String[] {"Nom", "Prénom", "E-mail", "Sexe", "Mot de passe", "Type"} ;
        this.data = ListUser.getInstance().getList() ; 
    }
    
    public String getColumnName(int column)
    {
        return this.column[column];
    }
    
    @Override
    public int getRowCount()
    {
        return data.size() ;
    }

    @Override
    public int getColumnCount()
    {
        return column.length ;
    }
    
    public Utilisateur getValueAt(int rowIdex)
    {
        Utilisateur tmp = new Utilisateur(data.get(rowIdex)) ;
        return tmp;
    }
    
    public void setValueAt(int rowIdex, Utilisateur u)
    {
        data.get(rowIdex).setUtilisateur(u) ;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Utilisateur u = data.get(rowIndex) ;
        switch(columnIndex)
        {
            case 0:
                return u.getNom();
            case 1:
                return u.getPrenom();
            case 2:
                return u.getEmail();
            case 3:
                return u.getSexe().getDescription();
            case 4:
                return u.getPassword();
            case 5:
                return u.getType().getDescription();
        }
        return null;
    }
    
    public void addRow()
    {
        fireTableRowsInserted(data.size()-1, data.size()-1);
    }
    
    public void deleteRow()
    {
        fireTableRowsDeleted(data.size()-1, data.size()-1);
    }
    
}
