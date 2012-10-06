/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stackpointer.stackexchange;

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
public class StackExchangeInterfaceTest {
    
    public StackExchangeInterfaceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("* StackExchangeInterfaceTest: @BeforeClass method");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("* StackExchangeInterfaceTest: @AfterClass method");
    }
    
    @Before
    public void setUp() {
        System.out.println("* StackExchangeInterfaceTest: @Before method");
    }
    
    @After
    public void tearDown() {
        System.out.println("* StackExchangeInterfaceTest: @After method");
    }

    /**
     * Test of updateLocalDatabase method, of class StackExchangeInterface.
     */
//    @Test
//    public void testUpdateLocalDatabase() {
//        System.out.println("updateLocalDatabase");
//        StackExchangeInterface instance = new StackExchangeInterface();
//        boolean expResult = true;
//        boolean result = instance.updateLocalDatabase();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of cleanDatabase method, of class StackExchangeInterface.
//     */
//    @Test
//    public void testCleanDatabase() {
//        System.out.println("cleanDatabase");
//        StackExchangeInterface instance = new StackExchangeInterface();
//        boolean expResult = true;
//        boolean result = instance.cleanDatabase();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of updateTopQuestions method, of class StackExchangeInterface.
//     */
//    @Test
//    public void testUpdateTopQuestions() {
//        System.out.println("updateTopQuestions");
//        //TODO - not sure how to properly validate this use case.
//        StackExchangeInterface instance = new StackExchangeInterface();
//        instance.updateTopQuestions();
//        boolean expResult = true;
//        boolean result = false; //TODO - replace with proper test
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getTop100Questions method, of class StackExchangeInterface.
//     */
//    @Test
//    public void testGetTop100Questions() {
//        System.out.println("getTop100Questions");
//        StackExchangeInterface instance = new StackExchangeInterface();
//        int expResult = 0;
//        int result = instance.getTop100Questions().size();
//        assertEquals(expResult, result);
//        instance.updateTopQuestions();
//        result = instance.getTop100Questions().size();
//        assertEquals(expResult, result);
//    }
}
