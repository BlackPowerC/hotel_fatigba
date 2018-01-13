package main.java.userInterface.chambre;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JTableChambreDispo
{
	private static JTableChambreDispo singleton = null ;
	private JTableChambreDispoModel model ;
	private JTable ChambreList ;
	private JScrollPane scroll ;
	
	private JTableChambreDispo()
	{
		Build_Table();
	}
	
	public static JTableChambreDispo getHinstance()
	{
		if(singleton == null)
		{
			singleton = new JTableChambreDispo() ;
		}
		return singleton ;
	}
	
	private void Build_Table()
	{
		model = new JTableChambreDispoModel() ;
		ChambreList = new JTable(model) ;
//		ChambreList.setShowGrid(false);
		
		for(int i = 0; i<ChambreList.getRowCount(); i++)
		{
			ChambreList.setRowHeight(25) ;
		}
		scroll = new JScrollPane(ChambreList) ;
	}

	public JTable getTable() {
		return ChambreList;
	}

	public JScrollPane getScroll() {
		return scroll;
	}
	
	public JTableChambreDispoModel getModel()
	{
		return model ;
	}
}