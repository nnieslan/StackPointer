/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stackpointer.database;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import stackpointer.common.User;
import stackpointer.jobs.JobPosting;
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
        List<Answer> result = instance.retrieveAnswers(1);
        assertEquals(expResult, result);
    }

    public class DatabaseFacadeImpl implements DatabaseFacade {

        @Override
        public boolean verifyConnection() {
            return false;
        }

        @Override
        public List<User> retrieveUsers() {
            return null;
        }
        
        @Override
        public List<Question> retrieveQuestions() {
            return null;
        }

        @Override
        public List<Answer> retrieveAnswers(int questionId) {
            return null;
        }
        
        @Override
        public List<JobPosting> retrieveJobPostings() {
            return null;
        }
        
        @Override
        public boolean addUser(User user) {
            return true;
        }

        @Override
        public boolean addQuestion(Question question) {
            return true;
        }

        @Override
        public boolean addAnswer(Answer answer) {
            return true;
        }

        @Override
        public boolean addJobPosting(JobPosting jobPosting) {
            return true;
        }
    }
}
