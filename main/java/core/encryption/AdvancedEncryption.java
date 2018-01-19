/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.core.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.io.UnsupportedEncodingException;

public class AdvancedEncryption
{
    private byte key[];
    private SecretKeySpec keySpec;
    private static AdvancedEncryption p_singleton = null;

    private AdvancedEncryption()
    {
    }

    public static AdvancedEncryption getInstance()
    {
        if (p_singleton == null)
        {
            p_singleton = new AdvancedEncryption();
        }
        return p_singleton;
    }

    public void setKey(String anotherKey)
    {
        MessageDigest md5 = null;
        try
        {
            key = anotherKey.getBytes("UTF-8");
            md5 = MessageDigest.getInstance("MD5");
            key = Arrays.copyOf(md5.digest(key), 16);
            keySpec = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException nsae)
        {
            nsae.printStackTrace();
        } catch (UnsupportedEncodingException uee)
        {
            uee.printStackTrace();
        }
    }

    public String encrypt(String plainText, String key)
    {
        try
        {
            setKey(key);
            Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, keySpec);
            return Base64.getEncoder().encodeToString(c.doFinal(plainText.getBytes("UTF-8")));
        } catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public String decrypt(String cipherText, String key)
    {
        try
        {
            setKey(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(cipherText)));
        } catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}