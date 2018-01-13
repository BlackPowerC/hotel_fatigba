package main.java.app;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class CheckEditor extends DefaultCellEditor
{
	private static final long serialVersionUID = 1L;
	private JCheckBox box ;
	
	 public CheckEditor(JCheckBox coche)
	 {
		 super(coche) ;
		 box = coche ;
	 }
	 
	 public Component getTableCellEditorComponent(JTable table, boolean is, int row, int col)
	 {
		 return box ;
	 }
}
