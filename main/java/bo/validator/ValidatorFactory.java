/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.bo.validator;

/**
 *
 * @author jordy
 * @since 0.2.1
 * @see ValidatorInterface
 * Usine de fabrication d'instances de ValidatorInterface.
 */
public class ValidatorFactory
{
    /* Constantes */
    public static final int ENTITY_VALIDATOR = 1;
    public static final int CLIENT_VALIDATOR = 2;
    public static final int PERSONNE_VALIDATOR = 3;
    public static final int CONSOMMATION_VALIDATOR = 4;
    public static final int SERVICE_VALIDATOR = 5;
    public static final int CHAMBRE_VALIDATOR = 6;
    public static final int TYPOLOGIE_VALIDATOR = 7;
    public static final int FACTURATION_VALIDATOR = 8;
    public static final int RESERVATION_VALIDATOR = 9;
    /* Singleton */
    private static ValidatorFactory p_singleton;
    /* Validator*/
    private PersonneValidator pv ;
    private ClientValidator cv ;
    private EntityValidator ev ;
    private ReservationValidator rv;
    private ConsommationValidator csv;
    private TypologieValidator tv;
    private FacturationValidator fv;
    private ChambreValidator chv;
    private ServiceValidator sv;
    
    public static ValidatorFactory getInstance()
    {
        if(p_singleton == null)
        {
            p_singleton = new ValidatorFactory() ;
        }
        return p_singleton;
    }
    
    /**
     * Constructeur privé.
     */
    private ValidatorFactory()
    {
        this.loadCache();
    }
    
    /**
     * Cette classe intance à l'avance tout les validateurs de l'usine.
     */
    private void loadCache()
    {
        this.pv = new PersonneValidator() ;
        this.cv = new ClientValidator() ;
        this.ev = new EntityValidator() ;
        this.sv = new ServiceValidator() ;
        this.chv = new ChambreValidator() ;
        this.fv = new FacturationValidator() ;
        this.tv = new TypologieValidator() ;
        this.csv = new ConsommationValidator() ;
        this.rv = new ReservationValidator() ;
    }
    
    public  ValidatorInterface<?> getValidator(int id)
    {
        switch(id)
        {
            case CLIENT_VALIDATOR:
                return this.cv;
            case PERSONNE_VALIDATOR:
                return pv;
            case ENTITY_VALIDATOR:
                return ev;
            case SERVICE_VALIDATOR:
                return this.sv;
            case CHAMBRE_VALIDATOR:
                return chv;
            case CONSOMMATION_VALIDATOR:
                return csv;
            case FACTURATION_VALIDATOR:
                return this.fv;
            case RESERVATION_VALIDATOR:
                return rv;
            case TYPOLOGIE_VALIDATOR:
                return tv;
        }
        return null;
    }
    
    public  ValidatorInterface<?> getValidator(String className)
    {
        if(ClientValidator.class.getName().compareTo(className) == 0)
        {
            return this.cv;
        }
        if(EntityValidator.class.getName().compareTo(className) == 0)
        {
            return this.ev;
        }
        if(PersonneValidator.class.getName().compareTo(className) == 0)
        {
            return this.pv;
        }
        if(ServiceValidator.class.getName().compareTo(className) == 0)
        {
            return this.sv;
        }
        if(ChambreValidator.class.getName().compareTo(className) == 0)
        {
            return this.chv;
        }
        if(ConsommationValidator.class.getName().compareTo(className) == 0)
        {
            return this.csv;
        }
        if(FacturationValidator.class.getName().compareTo(className) == 0)
        {
            return this.fv;
        }
        if(ReservationValidator.class.getName().compareTo(className) == 0)
        {
            return this.rv;
        }
        if(TypologieValidator.class.getName().compareTo(className) == 0)
        {
            return this.tv;
        }
        return null;
    }
}
