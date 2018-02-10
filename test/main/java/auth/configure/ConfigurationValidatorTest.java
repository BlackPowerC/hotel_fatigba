/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.main.java.auth.configure;

import java.util.regex.Pattern;
import main.java.auth.configure.Configuration;
import main.java.auth.configure.ConfigurationValidator;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jordy
 */
public class ConfigurationValidatorTest
{

    private static ConfigurationValidator cv;

    public ConfigurationValidatorTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
        cv = new ConfigurationValidator();
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
        Configuration cf = Configuration.getInstance();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Name", "");
        jsonObject.put("Pass", "");
        jsonObject.put("User", "");
        jsonObject.put("Host", "");
        jsonObject.put("SGBD", "");
        jsonObject.put("Port", "-1");
        cf.setConfiguration(jsonObject);
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of isValid method, of class ConfigurationValidator. Avec le champ
     * sgbd valant "".
     */
    @Test
    public void testIsValid1()
    {
        /* Le test doit Réussir */
        assertFalse(cv.isValid(Configuration.getInstance()));
    }

    /**
     * Test of isValid method, of class ConfigurationValidator. Avec le champ
     * sgbd valant SQLITE.
     */
    @Test
    public void testIsValid2()
    {
        /* Le test doit Réussir */
        Configuration.getInstance().setSgbd("SQLITE");
        assertTrue(cv.isValid(Configuration.getInstance()));
    }

    /**
     * Test of isValid method, of class ConfigurationValidator. Avec le champ
     * sgbd valant MYSQL.
     */
    @Test
    public void testIsValid3()
    {
        /* Le test doit Réussir */
        Configuration.getInstance().setSgbd("MYSQL");
        assertFalse(cv.isValid(Configuration.getInstance()));
    }

    /**
     * Test of isValid method, of class ConfigurationValidator. 
     * Avec les champs
     * - sgbd: MYSQL; 
     * - sgbdHost: localhost
     * - databaseName: _
     * - databaseUser: toto
     */
    @Test
    public void testIsValid4()
    {
        /* Le test doit Réussir */
        Configuration.getInstance().setSgbd("MYSQL");
        Configuration.getInstance().setSgbdHost("localhost");
        Configuration.getInstance().setDatabaseName("");
        Configuration.getInstance().setDatabaseUser("toto");
        assertFalse(cv.isValid(Configuration.getInstance()));
    }
    
    /**
     * Test of isValid method, of class ConfigurationValidator. 
     * Avec les champs
     * - sgbd: SQLITE; 
     * - sgbdHost: localhost
     * - databaseName: _
     * - databaseUser: toto
     */
    @Test
    public void testIsValid5()
    {
        /* Le test doit Réussir */
        Configuration.getInstance().setSgbd("SQLITE");
        Configuration.getInstance().setSgbdHost("localhost");
        Configuration.getInstance().setDatabaseName("");
        Configuration.getInstance().setDatabaseUser("toto");
        assertTrue(cv.isValid(Configuration.getInstance()));
    }
    
    /**
     * Test of isValid method, of class ConfigurationValidator. 
     * Avec les champs
     * - sgbd: MYSQL; 
     * - sgbdHost: localhost
     * - databaseName: ablam
     * - databaseUser: toto
     */
    @Test
    public void testIsValid6()
    {
        /* Le test doit Réussir */
        Configuration.getInstance().setSgbd("MYSQL");
        Configuration.getInstance().setSgbdHost("localhost");
        Configuration.getInstance().setDatabaseName("ablam");
        Configuration.getInstance().setDatabaseUser("toto");
        assertFalse(cv.isValid(Configuration.getInstance()));
    }
 
    /**
     * Test of isValid method, of class ConfigurationValidator. 
     * Avec les champs
     * - sgbd: MYSQL; 
     * - sgbdHost: 1.5.5.8
     * - databaseName: ablam
     * - databaseUser: toto
     */
    @Test
    public void testIsValid7()
    {
        /* Le test doit Réussir */
        Configuration.getInstance().setSgbd("MYSQL");
        Configuration.getInstance().setSgbdHost("1.5.5.8");
        Configuration.getInstance().setDatabaseName("ablam");
        Configuration.getInstance().setDatabaseUser("toto");
        assertFalse(cv.isValid(Configuration.getInstance()));
    }
    
    /**
     * Test of isValid method, of class ConfigurationValidator. 
     * Avec les champs
     * - sgbd: MYSQL; 
     * - sgbdHost: 1.5.5.8
     * - databaseName: ablam
     * - databaseUser: toto
     * - sgbdPort = -1
     */
    @Test
    public void testIsValid8()
    {
        /* Le test doit Réussir */
        Configuration.getInstance().setSgbd("MYSQL");
        Configuration.getInstance().setSgbdPort("-1");
        Configuration.getInstance().setSgbdHost("1.5.5.8");
        Configuration.getInstance().setDatabaseName("ablam");
        Configuration.getInstance().setDatabaseUser("toto");
        assertFalse(cv.isValid(Configuration.getInstance()));
    }
    
    /**
     * Test of isValid method, of class ConfigurationValidator. 
     * Avec les champs
     * - sgbd: MARIADB; 
     * - sgbdHost: 1.5.5.8
     * - databaseName: ablam
     * - databaseUser: toto
     * - sgbdPort = 10
     */
    @Test
    public void testIsValid9()
    {
        /* Le test doit Réussir */
        Configuration.getInstance().setSgbd("MARIADB");
        Configuration.getInstance().setSgbdPort("10");
        Configuration.getInstance().setSgbdHost("1.5.5.8");
        Configuration.getInstance().setDatabaseName("ablam");
        Configuration.getInstance().setDatabaseUser("toto");
        System.out.println(Integer.parseInt(Configuration.getInstance().getSgbdPort()));
        System.out.println(Pattern.compile("^\\w+$", Pattern.CASE_INSENSITIVE)
                            .matcher(Configuration.getInstance().getDatabaseName()).matches());
        assertTrue(cv.isValid(Configuration.getInstance()));
    }

}
