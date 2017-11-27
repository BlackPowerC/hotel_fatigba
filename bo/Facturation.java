package bo;

import java.util.GregorianCalendar;

public class Facturation extends Entity
{
    protected GregorianCalendar date ;
    protected Client client ;
    protected ModePaiement paiement ;
    
    
    @Override
    public boolean isValid()
    {
        return true ;
    }
   
}
