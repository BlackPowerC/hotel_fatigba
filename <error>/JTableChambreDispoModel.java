package manageChambre;

import dataFromDatabase.ListChambre;

public class JTableChambreDispoModel extends JTableChambreModel
{
	private static final long serialVersionUID = 1L;

	public JTableChambreDispoModel()
	{
		super() ;
		setData(ListChambre.getHinstance().getListChambreDispo());
	}
	
	public JTableChambreDispoModel(String []title)
	{
		super(title) ;
		setData(ListChambre.getHinstance().getListChambreDispo());
	}
	
	public Object getValueAt(int row, int col)
	{
		Chambre tmp = this.data.get(row) ;
 		if(tmp.getState() == false)
 		{
 			switch(col)
 			{
 			case 0:
 				return tmp.getId_chambre() ;
 			case 1:
 				return tmp.getType_chambre() ;
 			case 2:
 				return tmp.getSituation() ;
 			case 3:
 				return tmp.getPrix()+" €" ;
 			case 4:
 				return tmp.getState() ;
 			}
 		}
 		remove(row) ;
		return null;
	}
}
