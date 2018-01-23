/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.bo.validator;

import main.java.bo.Client;

/**
 *
 * @author jordy
 * @since 0.2.1 Cette classe sert de validateur pour l'objet Client.
 */
public class ClientValidator implements ValidatorInterface<Client>
{

    @Override
    public boolean isValid(Client enity)
    {
        if (enity == null)
        {
            return false;
        }
        PersonneValidator pv = (PersonneValidator) ValidatorFactory.getInstance()
                .getValidator(PersonneValidator.class.getName());
        TypologieValidator tv = (TypologieValidator) ValidatorFactory.getInstance()
                .getValidator(TypologieValidator.class.getName());
        return pv.isValid(enity)
                && tv.isValid(enity.getNation())
                && tv.isValid(enity.getSexe())
                && tv.isValid(enity.getType());
    }

}
