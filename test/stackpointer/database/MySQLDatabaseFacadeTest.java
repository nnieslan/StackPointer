/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stackpointer.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import stackpointer.common.Location;
import stackpointer.common.User;
import stackpointer.jobs.JobPosting;
import stackpointer.stackexchange.Answer;
import stackpointer.stackexchange.Question;

/**
 *
 * @author Nathan
 */
public class MySQLDatabaseFacadeTest {
    
    public MySQLDatabaseFacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("* MySQLDatabaseFacadeTest: @BeforeClass method");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("* MySQLDatabaseFacadeTest: @AfterClass method");
    }
    
    @Before
    public void setUp() {
        System.out.println("* MySQLDatabaseFacadeTest: @Before method");
    }
    
    @After
    public void tearDown() {
        System.out.println("* MySQLDatabaseFacadeTest: @After method");
    }

    /**
     * Test of openConnection method, of class MySQLDatabaseFacade.
     */
    @Test
    public void testOpenConnection() throws SQLException {
        System.out.println("openConnection");
        MySQLDatabaseFacade instance = new MySQLDatabaseFacade(DatabaseConnectionInfo.createDefault());
        Connection expResult = null;
        instance.openConnection();
        assertNotNull(instance.connection);
    }

    /**
     * Test of verifyConnection method, of class MySQLDatabaseFacade.
     */
    @Test
    public void testVerifyConnection() {
        System.out.println("verifyConnection");
        MySQLDatabaseFacade instance = new MySQLDatabaseFacade(DatabaseConnectionInfo.createDefault());
        boolean expResult = true;
        boolean result = instance.verifyConnection();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of retrieveUsers method, of class MySQLDatabaseFacade.
     */
    @Test
    public void testRetrieveUsers() {
        System.out.println("retrieveUsers");
        MySQLDatabaseFacade instance = new MySQLDatabaseFacade(DatabaseConnectionInfo.createDefault());
        List<User> result = instance.retrieveUsers();
        assertNotNull(result);
        assertTrue(result.size() >= 0);
    }

    /**
     * Test of retrieveQuestions method, of class MySQLDatabaseFacade.
     */
    @Test
    public void testRetrieveQuestions() {
        System.out.println("retrieveQuestions");
        MySQLDatabaseFacade instance = new MySQLDatabaseFacade(DatabaseConnectionInfo.createDefault());
        List<Question> result = instance.retrieveQuestions();
        assertNotNull(result);
        assertTrue(result.size() >= 0);
    }

    /**
     * Test of retrieveAnswers method, of class MySQLDatabaseFacade.
     */
    @Test
    public void testRetrieveAnswers() {
        System.out.println("retrieveAnswers");
        MySQLDatabaseFacade instance = new MySQLDatabaseFacade(DatabaseConnectionInfo.createDefault());
        List<Answer> expResult = new ArrayList<Answer>();
        List<Answer> result = instance.retrieveAnswers(1);
        assertNotNull(result);
        assertTrue(result.size() >= 0);
    }
    
    /**
     * Test of retrieveJobPostings method, of class MySQLDatabaseFacade.
     */
    @Test
    public void testRetrieveJobPostings() {
        System.out.println("retrieveJobPostings");
        MySQLDatabaseFacade instance = new MySQLDatabaseFacade(DatabaseConnectionInfo.createDefault());
        List<JobPosting> result = instance.retrieveJobPostings();
        assertNotNull(result);
        assertTrue(result.size() >= 0);
    }
    
    /**
     * Test of addUser method, of class MySQLDatabaseFacade.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        MySQLDatabaseFacade instance = new MySQLDatabaseFacade(DatabaseConnectionInfo.createDefault());
        User user = new User();
        user.setRealName("testUnitCreatedUser");
        user.setUserName("userName");
        // Create some relatively unique fake sx user id
        long longTime = (new Date()).getTime() % 10000000;
        String longStr = Long.toString(longTime);
        String sxid = "sxid" + longStr;
        user.setSXid(sxid);
        boolean success = instance.addUser(user);
        assertTrue(success);
    }
    
    /**
     * Test of addQuestion method, of class MySQLDatabaseFacade.
     */
    @Test
    public void testAddQuestion() {
        System.out.println("addQuestion");
        MySQLDatabaseFacade instance = new MySQLDatabaseFacade(DatabaseConnectionInfo.createDefault());
        User user = new User();
        user.setRealName("realName");
        user.setUserName("userName");
        user.setUid(1);
        Question question = new Question();
        question.setAskedBy(user);
        question.setqText("question posted by test unit");
        boolean success = instance.addQuestion(question);
        assertTrue(success);
    }
    
    /**
     * Test of addAnswer method, of class MySQLDatabaseFacade.
     */
    @Test
    public void testAddAnswer() {
        System.out.println("addAnswer");
        MySQLDatabaseFacade instance = new MySQLDatabaseFacade(DatabaseConnectionInfo.createDefault());
        User user = new User();
        user.setRealName("realName");
        user.setUserName("userName");
        user.setUid(2);
        Question question = new Question();
        question.setAskedBy(user);
        question.setqText("question text");
        question.setQid(1);
        Answer answer = new Answer();
        answer.setAnsweredBy(user);
        answer.setAnsText("answer posted by test unit");
        answer.setAnswering(question);
        boolean success = instance.addAnswer(answer);
        assertTrue(success);
    }

    /**
     * Test of addJobPosting method, of class MySQLDatabaseFacade.
     */
    @Test
    public void testAddJobPosting() {
        System.out.println("addAnswer");
        MySQLDatabaseFacade instance = new MySQLDatabaseFacade(DatabaseConnectionInfo.createDefault());
        Location location = new Location(1.0f, 1.0f, 0);
        Date date = new Date();
        JobPosting jobPosting = new JobPosting(location, date,
                "job posting by test unit", "description", "company");
        boolean success = instance.addJobPosting(jobPosting);
        assertTrue(success);
    }
}
