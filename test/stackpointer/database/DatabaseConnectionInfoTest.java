/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stackpointer.database;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nathan
 */
public class DatabaseConnectionInfoTest {
    
    public DatabaseConnectionInfoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("* DatabaseConnectionInfoTest: @BeforeClass method");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("* DatabaseConnectionInfoTest: @AfterClass method");
    }
    
    @Before
    public void setUp() {
        System.out.println("* DatabaseConnectionInfoTest: @Before method");
    }
    
    @After
    public void tearDown() {
        System.out.println("* DatabaseConnectionInfoTest: @After method");
    }

    /**
     * Test of getUrl method, of class DatabaseConnectionInfo.
     */
    @Test
    public void testGetUrl() {
        System.out.println("getUrl");
        DatabaseConnectionInfo instance = new DatabaseConnectionInfo("url","username","password");
        String expResult = "url";
        String result = instance.getUrl();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUrl method, of class DatabaseConnectionInfo.
     */
    @Test
    public void testSetUrl() {
        System.out.println("setUrl");
        String url = "new url";
        DatabaseConnectionInfo instance = new DatabaseConnectionInfo("url","username","password");
        instance.setUrl(url);
        String result = instance.getUrl();
        assertEquals(url, result);
    }

    /**
     * Test of getUsername method, of class DatabaseConnectionInfo.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        DatabaseConnectionInfo instance = new DatabaseConnectionInfo("url","username","password");
        String expResult = "username";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class DatabaseConnectionInfo.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "new username";
        DatabaseConnectionInfo instance = new DatabaseConnectionInfo("url","username","password");
        instance.setUsername(username);
        String result = instance.getUsername();
        assertEquals(username, result);
    }

    /**
     * Test of getPassword method, of class DatabaseConnectionInfo.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        DatabaseConnectionInfo instance = new DatabaseConnectionInfo("url","username","password");
        String expResult = "password";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class DatabaseConnectionInfo.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "new password";
        DatabaseConnectionInfo instance = new DatabaseConnectionInfo("url","username","password");
        instance.setPassword(password);
        String result = instance.getPassword();
        assertEquals(password, result);
    }
}
