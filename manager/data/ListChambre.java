package manager.data;

import bo.Chambre;
import java.util.ArrayList;
import java.util.List;

public class ListChambre
{
    private List<Chambre> chambreTotal = new ArrayList<Chambre>();
    private List<Chambre> chambreDispo = new ArrayList<Chambre>();
    private static ListChambre singleton = null; //

    public static ListChambre getHinstance()
    {
        if (singleton == null)
        {
            singleton = new ListChambre();
        }
        return singleton;
    }

    /* Le constructeur */
    private ListChambre()
    {

    }

    public List<Chambre> getListChambreTotal()
    {
        return chambreTotal;
    }

    public List<Chambre> getListChambreDispo()
    {
        return chambreDispo;
    }

    public void setListChambre(List<Chambre> ch)
    {
        chambreTotal = ch;
    }
}
