/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stackpointer.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import stackpointer.common.User;
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
        MySQLDatabaseFacade instance = new MySQLDatabaseFacade(new DatabaseConnectionInfo("url","username","password"));
        Connection expResult = null;
        Connection result = instance.openConnection();
        assertEquals(expResult, result);
        fail("Connection not open");
    }

    /**
     * Test of verifyConnection method, of class MySQLDatabaseFacade.
     */
    @Test
    public void testVerifyConnection() {
        System.out.println("verifyConnection");
        MySQLDatabaseFacade instance = new MySQLDatabaseFacade(new DatabaseConnectionInfo("url","username","password"));
        boolean expResult = true;
        boolean result = instance.verifyConnection();
        assertEquals(expResult, result);
    }

    /**
     * Test of retrieveQuestions method, of class MySQLDatabaseFacade.
     */
    @Test
    public void testRetrieveQuestions() {
        System.out.println("retrieveQuestions");
        MySQLDatabaseFacade instance = new MySQLDatabaseFacade(new DatabaseConnectionInfo("url","username","password"));
        List<Question> expResult = new ArrayList<Question>();
        expResult.add(new Question(new User("Asdf","Asdf"),"asdf",null));
        List<Question> result = instance.retrieveQuestions();
        assertEquals(expResult, result);
    }

    /**
     * Test of retrieveAnswers method, of class MySQLDatabaseFacade.
     */
    @Test
    public void testRetrieveAnswers() {
        System.out.println("retrieveAnswers");
        MySQLDatabaseFacade instance = new MySQLDatabaseFacade(new DatabaseConnectionInfo("url","username","password"));
        List<Answer> expResult = new ArrayList<Answer>();
        expResult.add(new Answer(new User("asdf","asdf"),"Asdf", new Question(new User("Asdf","Asdf"),"asdf",null)));
        List<Answer> result = instance.retrieveAnswers(1L);
        assertEquals(expResult, result);
    }
}
