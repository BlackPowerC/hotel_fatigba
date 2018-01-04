/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.GregorianCalendar;
import java.util.regex.Pattern;

/**
 * Cette classe contient des fonctions utilitaires pour faire du sale.
 * @author jordy
 */
public class Util
{
    /**
     * Cette fonction convertit une chaine de carractère au format<br>
     * yyyy-mm-dd ou yyyy-mm-dd hh:mm:ss en une instance de GregorianCalendar.
     * @param gregoriancalendar
     * @return Une instance de GregorianCalendar qui correspond à la chaine
     * passée en paramettre.
     */
    public static GregorianCalendar stringToCalendar(String gregoriancalendar)
    {
        String regex_yyyy_mm_dd = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$" ;
        String regex_yyyy_mm_dd_hh_mm_ss = "^[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}$" ;
        if(Pattern.compile(regex_yyyy_mm_dd).matcher(gregoriancalendar).matches())
        {
            String[] params = regex_yyyy_mm_dd.split("-") ;
            int year = Integer.parseInt(params[0]) ;
            int month = Integer.parseInt(params[1]) ;
            int day = Integer.parseInt(params[2]) ;
            return new GregorianCalendar(year, month, day) ;
        }
        else if(Pattern.compile(regex_yyyy_mm_dd_hh_mm_ss).matcher(gregoriancalendar).matches())
        {
            String[] datetime = regex_yyyy_mm_dd_hh_mm_ss.split(" ") ;
            /* La date */
            String[] dateparams = datetime[0].split("-") ;
            int year = Integer.parseInt(dateparams[0]) ;
            int month = Integer.parseInt(dateparams[1]) ;
            int day = Integer.parseInt(dateparams[2]) ;
            /* L'heure */
            String[] timeparams = datetime[1].split(":") ;
            int hour = Integer.parseInt(timeparams[0]) ;
            int minute = Integer.parseInt(timeparams[1]) ;
            int second = Integer.parseInt(timeparams[2]) ;
            return new GregorianCalendar(year, month, day, hour, minute, second) ;
        }
        return null ;
    }
}
