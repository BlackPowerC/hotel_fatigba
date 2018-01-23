/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.bo.validator;

import main.java.bo.Consommation;

/**
 *
 * @author jordy
 */
public class ConsommationValidator implements ValidatorInterface<Consommation>
{

    public ConsommationValidator()
    {
    }

    @Override
    public boolean isValid(Consommation enity)
    {
        if (enity == null)
        {
            return false;
        }
        EntityValidator ev = (EntityValidator) ValidatorFactory.getInstance()
                .getValidator(EntityValidator.class.getName());
        ServiceValidator sv = (ServiceValidator) ValidatorFactory.getInstance()
                .getValidator(ServiceValidator.class.getName());
        ClientValidator cv = (ClientValidator) ValidatorFactory.getInstance()
                .getValidator(ClientValidator.class.getName());
        return cv.isValid(enity.getClient()) 
                && sv.isValid(enity.getService())
                && ev.isValid(enity) ;
    }

}
