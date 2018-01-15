package main.java.gui.chambre;

import main.java.bo.Chambre;
import main.java.manager.data.ListChambre;

public class JTableChambreDispoModel extends JTableChambreModel
{

    private static final long serialVersionUID = 1L;

    public JTableChambreDispoModel()
    {
        super();
        setData(ListChambre.getHinstance().getListChambreDispo());
    }

    public JTableChambreDispoModel(String[] title)
    {
        super(title);
        setData(ListChambre.getHinstance().getListChambreDispo());
    }

    @Override
    public Object getValueAt(int row, int col)
    {
        Chambre tmp = this.data.get(row);
        if (!tmp.isEtat())
        {
            switch (col)
            {
                case 0:
                    return tmp.getId();
                case 1:
                    return tmp.getType().getDescription();
                case 2:
                    return tmp.getSituation().getDescription();
                case 3:
                    return tmp.getPrix() + " €";
                case 4:
                    return tmp.isEtat() ;
            }
        }
        return null;
    }
}
