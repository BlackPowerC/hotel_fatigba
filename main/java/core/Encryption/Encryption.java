/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.core.Encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption
{
    private byte bytes[];
    private static Encryption p_singleton = null;

    private Encryption()
    {
    }

    public static Encryption getInstance()
    {
        if (p_singleton == null)
        {
            p_singleton = new Encryption();
        }
        return p_singleton;
    }

    public String encrypt(String plaintext)
    {   
        try
        {
            MessageDigest md5  = MessageDigest.getInstance("MD5");
            bytes = md5.digest(plaintext.getBytes()) ;
            return new String(bytes) ; 
        } catch (NoSuchAlgorithmException nsae)
        {
            nsae.printStackTrace();
        }
        return null ;
    }
}