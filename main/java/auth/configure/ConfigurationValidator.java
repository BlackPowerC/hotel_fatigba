/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.auth.configure;

import java.util.regex.Pattern;
import main.java.bo.validator.ValidatorInterface;

/**
 * Cette classe à pour role de vérifier la validité du fichier de configuration
 * de la base de données.
 * @author jordy
 * @since 0.2.2
 * @see Configuration
 */
public class ConfigurationValidator implements ValidatorInterface<Configuration>
{

    @Override
    public boolean isValid(Configuration enity)
    {
        if(enity == null)
        {
            return false ;
        }
        if(enity.getSgbd().compareToIgnoreCase("") == 0)
        {
            return false ;
        }
        if(enity.getSgbd().compareToIgnoreCase("sqlite") == 0)
        {
            return true;
        }
        else
        {
            String ipv4Regex = "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$" ;
            return 
                    Pattern.compile(ipv4Regex, Pattern.CASE_INSENSITIVE)
                            .matcher(enity.getSgbdHost()).matches()
                    && Pattern.compile("^\\w+$", Pattern.CASE_INSENSITIVE)
                            .matcher(enity.getDatabaseName()).matches()
                    && Pattern.compile("^\\w+$", Pattern.CASE_INSENSITIVE)
                            .matcher(enity.getDatabaseUser()).matches()
                    && Integer.parseInt(enity.getSgbdPort()) > 1 ;
        }
    }    
}
