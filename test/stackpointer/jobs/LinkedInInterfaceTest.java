/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stackpointer.jobs;

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
public class LinkedInInterfaceTest {
    
    public LinkedInInterfaceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("* LinkedInInterfaceTest: @BeforeClass method");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("* LinkedInInterfaceTest: @BeforeClass method");
    }
    
    @Before
    public void setUp() {
        System.out.println("* LinkedInInterfaceTest: @BeforeClass method");
    }
    
    @After
    public void tearDown() {
        System.out.println("* LinkedInSInterfaceTest: @BeforeClass method");
    }

    /**
     * Test of establishConnection method, of class LinkedInInterface.
     */
    @Test
    public void testEstablishConnection() {
        System.out.println("establishConnection");
        LinkedInInterface instance = new LinkedInInterface();
        instance.establishConnection();
        boolean expResult = true;
        boolean result = instance.isConnectionEstablished();
        assertEquals(expResult, result);
    }

    /**
     * Test of isConnectionEstablished method, of class LinkedInInterface.
     */
    @Test
    public void testIsConnectionEstablished() {
        System.out.println("isConnectionEstablished");
        LinkedInInterface instance = new LinkedInInterface();
        //TODO - send in bad connection values;
        instance.establishConnection();
        boolean expResult = false;
        boolean result = instance.isConnectionEstablished();
        assertEquals(expResult, result);
        //TODO - send in good connection values;
        instance.establishConnection();
        expResult = true;
        result = instance.isConnectionEstablished();
        assertEquals(expResult, result);
    }

    /**
     * Test of updateLocalDatabase method, of class LinkedInInterface.
     */
    @Test
    public void testUpdateLocalDatabase() {
        System.out.println("updateLocalDatabase");
        LinkedInInterface instance = new LinkedInInterface();
        boolean expResult = true;
        boolean result = instance.updateLocalDatabase();
        assertEquals(expResult, result);
    }

    /**
     * Test of cleanDatabase method, of class LinkedInInterface.
     */
    @Test
    public void testCleanDatabase() {
        System.out.println("cleanDatabase");
        LinkedInInterface instance = new LinkedInInterface();
        boolean expResult = true;
        boolean result = instance.cleanDatabase();
        assertEquals(expResult, result);
    }

    /**
     * Test of updateJobPostings method, of class LinkedInInterface.
     */
    @Test
    public void testUpdateJobPostings() {
        System.out.println("updateJobPostings");
        //TODO - not sure how to properly validate this use case.
        LinkedInInterface instance = new LinkedInInterface();
        instance.updateJobPostings();
        boolean expResult = true;
        boolean result = false; //TODO - replace with proper test
        assertEquals(expResult, result);
    }

    /**
     * Test of getJobPostings method, of class LinkedInInterface.
     */
    @Test
    public void testGetJobPostings() {
        System.out.println("getJobPostings");
        LinkedInInterface instance = new LinkedInInterface();
        int expResult = 0;
        int result = instance.getJobPostings().size();
        assertEquals(expResult, result);
        instance.updateJobPostings();
        result = instance.getJobPostings().size();
        assertEquals(expResult, result);
    }
}
