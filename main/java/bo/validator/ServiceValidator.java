/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.bo.validator;

import main.java.bo.Service;

/**
 *
 * @author jordy
 */
public class ServiceValidator implements ValidatorInterface<Service>
{

    public ServiceValidator()
    {
    }

    @Override
    public boolean isValid(Service enity)
    {
        if (enity == null)
        {
            return false;
        }
        EntityValidator ev = (EntityValidator) ValidatorFactory.getInstance()
                .getValidator(EntityValidator.class.getName());
        return ev.isValid(enity)
                && !enity.getDescription().isEmpty() 
                && (enity.getPrix() > 0.0f);
    }

}
