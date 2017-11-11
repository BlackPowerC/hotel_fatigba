package manageChambre;

import java.util.List ;
import javax.swing.table.AbstractTableModel ;

public abstract class JTableChambreModel extends AbstractTableModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String [] title ;
	protected List<Chambre> data = null ;
	
	public JTableChambreModel(String label[])
	{
		title = label ;
	}
	
	public JTableChambreModel()
	{
		title = new String[]{"N° Chambre", "Type", "Sistuation", "Prix"} ;
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
	
	public Chambre getValueAt(int x)
	{
		Chambre m_new = new Chambre(data.get(x)) ;
		return m_new ;
	}
	
	public void setValueAt(Chambre chambre, int x)
	{
		data.get(x).setChambre(chambre) ;
	}
	

	public abstract Object getValueAt(int x, int y) ;
	
	public String getColumnName(int index)
	{
		return title[index] ;
	}
	
	
	/*
	public void setValueAt(Object ob, int row, int col)
	{
		if(ob instanceof String)
		{
			String tmp = (String) getValueAt(row, col) ; 
			tmp = (String) ob.toString() ;
		}
		if(ob instanceof Integer)
		{
			Integer tmp = (Integer) getValueAt(row, col) ; 
			tmp = (Integer) ob;
		}
	}*/
	
	public void addRow(Chambre tmp)
	{
		fireTableRowsInserted(tmp.getId_chambre()-1, tmp.getId_chambre()-1) ;
	}
	
	public void addRow()
	{
		//Client tmp = addReservation.getHinstance().getLastClient() ;
		fireTableRowsInserted(data.size()-1, data.size()-1) ;
	}
	
	public void remove(int x)
	{
		fireTableRowsDeleted(x, x) ;
	}
	
	public void setData(List<Chambre> list)
	{
		data = list ;
	}
}
