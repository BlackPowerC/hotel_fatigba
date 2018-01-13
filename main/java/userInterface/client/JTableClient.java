package main.java.userInterface.client;


import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import main.java.app.CheckEditor;
import main.java.app.TableComponent;

public class JTableClient
{
    private static JTableClient singleton = null ;
    private JTableClientModel model ;
    private JTable ClientList ;
    private JScrollPane scroll ;
	
    private JTableClient()
    {
	Build_Table();
    }
	
    public static JTableClient getHinstance()
    {
	if(singleton == null)
	{
            singleton = new JTableClient() ;
	}
	return singleton ;
    }
	
    private void Build_Table()
    {
        model = new JTableClientModel() ;
	ClientList = new JTable(model) ;
	ClientList.setShowGrid(false);
	ClientList.getColumn("Fidélité").setCellEditor(new CheckEditor(new JCheckBox()));
	ClientList.setDefaultRenderer(JCheckBox.class, new TableComponent()) ;
		
	for(int i = 0; i<ClientList.getRowCount(); i++)
	{
            ClientList.setRowHeight(25) ;
	}
        scroll = new JScrollPane(ClientList) ;
    }

	public JTable getTable() {
		return ClientList;
	}

	public JScrollPane getScroll() {
		return scroll;
	}
	
	public JTableClientModel getModel()
	{
		return model ;
	}
	
	@Override
	public JTableClient clone()
	{
		return new JTableClient() ;
	}
}