package main.java.gui.chambre;

import main.java.bo.Chambre;
import main.java.manager.data.ListChambre;

public class JTableChambreTotalModel extends JTableChambreModel
{
    private static final long serialVersionUID = 2638029768967334873L;

    public JTableChambreTotalModel()
    {
        super(new String[]
        {
            "N° Chambre", "Type", "Sistuation", "Prix", "Etat"
        });
        setData(ListChambre.getHinstance().getListChambreTotal());
    }

    public JTableChambreTotalModel(String[] title)
    {
        super(title);
        setData(ListChambre.getHinstance().getListChambreTotal());
    }

    @Override
    public Class<?> getColumnClass(int col)
    {
        if (col == 4)
        {
            return Boolean.class;
        }

        return Object.class;
    }

    @Override
    public Object getValueAt(int row, int col)
    {
        Chambre tmp = this.data.get(row);
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
        return null;
    }
}
