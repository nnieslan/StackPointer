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
import stackpointer.common.User;

/**
 *
 * @author Nathan
 */
public class AnswerTest {
    
    public AnswerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("* AnswerTest: @BeforeClass method");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("* AnswerTest: @AfterClass method");
    }
    
    @Before
    public void setUp() {
        System.out.println("* AnswerTest: @Before method");
    }
    
    @After
    public void tearDown() {
        System.out.println("* AnswerTest: @After method");
    }

    /**
     * Test of getAnsweredBy method, of class Answer.
     */
    @Test
    public void testGetAnsweredBy() {
        System.out.println("getAnsweredBy");
        User user = new User("Real Name","username");
        Question question = new Question(user, "Question Text", null);
        Answer instance = new Answer(user, "Answer Text", question);
        User result = instance.getAnsweredBy();
        assertEquals(user, result);
    }

    /**
     * Test of setAnsweredBy method, of class Answer.
     */
    @Test
    public void testSetAnsweredBy() {
        System.out.println("setAnsweredBy");
        User user = new User("Real Name","username");
        Question question = new Question(user, "Question Text", null);
        Answer instance = new Answer(user, "Answer Text", question);
        User answeredBy = new User("Real Name 2","username2");
        instance.setAnsweredBy(answeredBy);
        User result = instance.getAnsweredBy();
        assertEquals(answeredBy, result);
    }

    /**
     * Test of getAnsText method, of class Answer.
     */
    @Test
    public void testGetAnsText() {
        System.out.println("getAnsText");
        User user = new User("Real Name","username");
        Question question = new Question(user, "Question Text", null);
        Answer instance = new Answer(user, "Answer Text", question);
        String expResult = "Answer Text";
        String result = instance.getAnsText();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAnsText method, of class Answer.
     */
    @Test
    public void testSetAnsText() {
        System.out.println("setAnsText");
        String ansText = "Next Text";
        User user = new User("Real Name","username");
        Question question = new Question(user, "Question Text", null);
        Answer instance = new Answer(user, "Answer Text", question);
        instance.setAnsText(ansText);
        String result = instance.getAnsText();
        assertEquals(ansText, result);
    }

    /**
     * Test of getAnswering method, of class Answer.
     */
    @Test
    public void testGetAnswering() {
        System.out.println("getAnswering");
        User user = new User("Real Name","username");
        Question question = new Question(user, "Question Text", null);
        Answer instance = new Answer(user, "Answer Text", question);
        Question result = instance.getAnswering();
        assertEquals(question, result);
    }

    /**
     * Test of setAnswering method, of class Answer.
     */
    @Test
    public void testSetAnswering() {
        System.out.println("setAnswering");
        User user = new User("Real Name","username");
        Question question = new Question(user, "Question Text", null);
        Answer instance = new Answer(user, "Answer Text", question);
        Question answering = new Question(user, "New Text", null);
        instance.setAnswering(answering);
        Question result = instance.getAnswering();
        assertEquals(answering, result);
    }
}
