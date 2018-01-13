/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.core;

import javax.swing.JOptionPane;

/**
 *
 * @author jordy
 */
public class Message
{
    public static void information(String message)
    {
        JOptionPane.showMessageDialog(null,message, "OK",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void error(String message)
    {
        JOptionPane.showMessageDialog(null,message, "OK",JOptionPane.ERROR_MESSAGE);
    }
    
    public static void warning(String message)
    {
        JOptionPane.showMessageDialog(null,message, "OK",JOptionPane.WARNING_MESSAGE);
    }
}
