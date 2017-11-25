/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import java.util.regex.Pattern;

/**
 *
 * @author jordy
 */
public class Sexe extends Typologie
{
    public Sexe()
    {
        super() ;
    }
    
    @Override
    public boolean isValid()
    {
        return Pattern.compile("^Homme|Femme$", Pattern.CASE_INSENSITIVE).matcher(description).matches() ;
    }
}
