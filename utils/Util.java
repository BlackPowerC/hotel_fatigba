/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Calendar;
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
    
    /**
     * Fonction inverse de <code>stringToCalendar</code>
     * @param date
     * @return une Chaine de caractère au format yyyy-mm-dd hh:mm:ss
     */
    public static String calendarToString(GregorianCalendar date)
    {
        String _date = date.get(Calendar.YEAR)+"-"+date.get(Calendar.MONTH+1)+"-"+date.get(Calendar.DAY_OF_MONTH) ;
        String time = date.get(Calendar.HOUR_OF_DAY)+":"+date.get(Calendar.MINUTE)+":"+date.get(Calendar.SECOND) ;
        return _date+" "+time ;
    }
    
    /**
     * 
     * @return Une chaine de caractère représentant la date courante au format yyyy-mm-dd hh:mm:ss
     */
    public static String nowString()
    {
        Calendar c = Calendar.getInstance();
        GregorianCalendar now = new GregorianCalendar(
                c.get(Calendar.YEAR), c.get(Calendar.MONTH+1), c.get(Calendar.DAY_OF_MONTH),
                c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND)
        ) ;
        return calendarToString(now) ;
    }
    
    /**
     * 
     * @return La date courant sous la forme d'une instance de <code>GregorianCalendar</code>
     */
    public static GregorianCalendar nowCalendar()
    {
        Calendar c = Calendar.getInstance();
        GregorianCalendar now = new GregorianCalendar(
                c.get(Calendar.YEAR), c.get(Calendar.MONTH+1), c.get(Calendar.DAY_OF_MONTH),
                c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND)
        ) ;
        return now ;
    }
}
