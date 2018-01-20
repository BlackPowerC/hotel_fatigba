/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.bo.validator;

import main.java.bo.Entity;

/**
 *
 * @author jordy
 * @since 0.2.1
 * Cette classe sert de validateur pour l'objet Entity.
 */
class EntityValidator implements ValidatorInterface<Entity>
{

    @Override
    public boolean isValid(Entity enity)
    {
        if(enity == null)
        {
            return false ;
        }
        return enity.getId() > 0;
    }
    
}
