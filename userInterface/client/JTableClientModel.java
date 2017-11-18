package manageClient;

import dataFromDatabase.ListClient;
import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;
import java.util.List;

public class JTableClientModel extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	private List<Client> data = ListClient.getHinstance().getListClient() ;
	private String[] title ;
	
	public JTableClientModel(String[] string)
	{
		title = string ;
	}
	
	public JTableClientModel()
	{
		title = new String[]{"Nom", "Prenom", "Age", "Sexe", "Nationalité", "Type", "Fidélité"} ;
	}
	
	public int getColumnCount()
	{
		return this.title.length ;
	}
	
	public int getRowCount()
	{
		return this.data.size() ;
	}
	
	public boolean isCellEditable(int x, int y)
	{
		return false ;
	}
	
	public Client getValueAt(int x)
	{
		Client m_new = new Client(data.get(x)) ;
		return m_new ;
	}
	
	public void setValueAt(Client client, int x)
	{
		data.get(x).setClient(client) ;
	}
	
	
	public Object getValueAt(int x, int y)
	{
		Client tmp = data.get(x) ;
		Object ob ;
 		switch(y)
		{
		case 0:
			return tmp.getM_nom() ;
		case 1:
			return tmp.getM_prenom() ;
		case 2:
			return tmp.getM_age() ;
		case 3:
			ob = tmp.getM_sexe() ;
			return ob ;
		case 4:
			ob = tmp.getM_nation() ;
			return ob ;
		case 5:
			ob = tmp.getM_type() ;
			return ob ;
		case 6:
			return tmp.isM_has_fidelity_card() ;
		}
		return null;
	}
	
	public String getColumnName(int index)
	{
		return title[index] ;
	}
	
	public void addRow()
	{
		//Client tmp = addReservation.getHinstance().getLastClient() ;
		fireTableRowsInserted(data.size()-1, data.size()-1) ;
	}
	
	public Class<?> getColumnClass(int col)
	{
		if(col == 6)
			return Boolean.class ;
		
		return Object.class ;
	}
	
	public void remove(int x)
	{
		fireTableRowsDeleted(x, x) ;
	}
	
	public void setData(List<Client> list)
	{
		data =  new ArrayList<Client>(list);
	}
}
