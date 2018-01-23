/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.bo.validator;

import java.util.regex.Pattern;
import main.java.bo.Personne;

/**
 *
 * @author jordy
 * @since 0.2.1
 * Cette classe sert de validateur pour l'objet Personne.
 */
public class PersonneValidator implements ValidatorInterface<Personne>
{
    @Override
    public boolean isValid(Personne p)
    {
        if(p == null)
        {
            return false;
        }
        EntityValidator ev = (EntityValidator) ValidatorFactory.getInstance()
                .getValidator(EntityValidator.class.getName());
        String namePattern = "^[\\w]+$";
        String emailPattern = "^[\\w]+@[\\w]+\\.[a-z]{2,6}$";
        boolean localValid ;
        localValid = Pattern.compile(namePattern, Pattern.CASE_INSENSITIVE)
                        .matcher(p.getNom()).matches()
                && Pattern.compile(namePattern, Pattern.CASE_INSENSITIVE)
                        .matcher(p.getPrenom()).matches()
                && Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE)
                        .matcher(p.getEmail()).matches();
        return ev.isValid(p) && localValid ;
    }    
}
