/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stackpointer.database;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import stackpointer.stackexchange.Answer;
import stackpointer.stackexchange.Question;

/**
 *
 * @author Nathan
 */
public class DatabaseFacadeTest {
    
    public DatabaseFacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("* DatabaseFacadeTest: @BeforeClass method");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("* DatabaseFacadeTest: @BeforeClass method");
    }
    
    @Before
    public void setUp() {
        System.out.println("* DatabaseFacadeTest: @BeforeClass method");
    }
    
    @After
    public void tearDown() {
        System.out.println("* DatabaseFacadeTest: @BeforeClass method");
    }

    /**
     * Test of verifyConnection method, of class DatabaseFacade.
     */
    @Test
    public void testVerifyConnection() {
        System.out.println("verifyConnection");
        DatabaseFacade instance = new DatabaseFacadeImpl();
        boolean expResult = true;
        boolean result = instance.verifyConnection();
        assertEquals(expResult, result);
    }

    /**
     * Test of retrieveQuestions method, of class DatabaseFacade.
     */
    @Test
    public void testRetrieveQuestions() {
        System.out.println("retrieveQuestions");
        DatabaseFacade instance = new DatabaseFacadeImpl();
        List<Question> expResult = new ArrayList<Question>();
        List<Question> result = instance.retrieveQuestions();
        assertEquals(expResult, result);
    }

    /**
     * Test of retrieveAnswers method, of class DatabaseFacade.
     */
    @Test
    public void testRetrieveAnswers() {
        System.out.println("retrieveAnswers");
        DatabaseFacade instance = new DatabaseFacadeImpl();
        List<Answer> expResult = new ArrayList<Answer>();
        List<Answer> result = instance.retrieveAnswers(1L);
        assertEquals(expResult, result);
    }

    public class DatabaseFacadeImpl implements DatabaseFacade {

        public boolean verifyConnection() {
            return false;
        }

        public List<Question> retrieveQuestions() {
            return null;
        }

        public List<Answer> retrieveAnswers(Long questionId) {
            return null;
        }
    }
}
