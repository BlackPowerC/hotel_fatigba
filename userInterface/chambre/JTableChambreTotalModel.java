package manageChambre;

import dataFromDatabase.ListChambre;

public class JTableChambreTotalModel extends JTableChambreModel
{

    /**
     *
     */
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

    public Class<?> getColumnClass(int col)
    {
        if (col == 4)
        {
            return Boolean.class;
        }

        return Object.class;
    }

    public Object getValueAt(int row, int col)
    {
        Chambre tmp = this.data.get(row);
        switch (col)
        {
            case 0:
                return tmp.getId_chambre();
            case 1:
                return tmp.getType_chambre();
            case 2:
                return tmp.getSituation();
            case 3:
                return tmp.getPrix() + " €";
            case 4:
                return tmp.getState();
        }
        return null;
    }
}
