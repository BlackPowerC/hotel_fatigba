package manageService;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JTableService 
{
	private JScrollPane scroll ;
	private JTable table ;
	private JTableServiceModel model ;
	private static JTableService singleton = null ;
	
	private void Rendering()
	{
		for(int i = 0; i<table.getRowCount(); i++)
		{
			table.setRowHeight(25);
		}
	}
	
	private void Build_All()
	{
		model = new JTableServiceModel() ;
		table = new JTable(model) ;
//		table.setShowGrid(false);
		scroll = new JScrollPane(table) ;
	}
	
	public static JTableService getHinstance()
	{
		if(singleton == null)
		{
			singleton = new JTableService() ;
		}
		return singleton ;
	}
	
	private JTableService()
	{
		Build_All(); 
		Rendering();
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public JTable getTable() {
		return table;
	}

	public JTableServiceModel getModel() {
		return model;
	}
	
	
}
