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
 * Usine de fabrique d'instance de ValidatorInterface.
 */
public class ValidatorFactory
{
    /* Constantes */
    public static final int CLIENT_VALIDATOR = 2;
    public static final int ENTITY_VALIDATOR = 1;
    public static final int PERSONNE_VALIDATOR = 3;
    /* Singleton */
    private static ValidatorFactory p_singleton;
    /* Validator*/
    private PersonneValidator pv ;
    private ClientValidator cv ;
    private EntityValidator ev ;
    
    private static ValidatorFactory getInstance()
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
        this.ev = new EntityValidator()  ;
    }
}
