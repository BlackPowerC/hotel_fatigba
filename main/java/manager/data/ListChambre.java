package main.java.manager.data;

import main.java.bo.Chambre;
import java.util.ArrayList;
import java.util.List;
import main.java.manager.ChambreManager;
import main.java.manager.FactoryManager;

public class ListChambre
{
    private List<Chambre> chambreTotal = new ArrayList<Chambre>();
    private List<Chambre> chambreDispo = new ArrayList<Chambre>();
    private static ListChambre singleton = null; //

    public static ListChambre getHinstance()
    {
        if(singleton == null)
        {
            singleton = new ListChambre();
        }
        return singleton;
    }

    /* Le constructeur */
    private ListChambre()
    {
        chambreTotal = (List<Chambre>) FactoryManager.getInstance().getManager(ChambreManager.class.getName()).findAll() ;
        for (Chambre chambre : chambreTotal)
        {
            if(chambre.isEtat())
            {
                chambreDispo.add(chambre);
            }
        }
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
