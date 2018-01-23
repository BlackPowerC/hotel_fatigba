/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.bo.validator;

import main.java.bo.Chambre;

/**
 *
 * @author jordy
 */
public class ChambreValidator implements ValidatorInterface<Chambre>
{

    public ChambreValidator()
    {
    }

    @Override
    public boolean isValid(Chambre enity)
    {
        if(enity == null)
        {
            return false;
        }
        EntityValidator ev = (EntityValidator) ValidatorFactory.getInstance().getValidator(EntityValidator.class.getName());
        TypologieValidator tv = (TypologieValidator) ValidatorFactory.getInstance().getValidator(TypologieValidator.class.getName());
        
        return ev.isValid(enity) 
                && tv.isValid(enity.getSituation())
                && tv.isValid(enity.getType())
                && tv.isValid(enity.getCaracteristique())
                && (enity.getPrix() > 0.0f);
    }

}
