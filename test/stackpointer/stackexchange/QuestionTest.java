/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stackpointer.stackexchange;

import java.util.ArrayList;
import java.util.List;
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
public class QuestionTest {
    
    public QuestionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("* LocationTest: @BeforeClass method");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("* LocationTest: @AfterClass method");
    }
    
    @Before
    public void setUp() {
        System.out.println("* LocationTest: @Before method");
    }
    
    @After
    public void tearDown() {
        System.out.println("* LocationTest: @After method");
    }

    /**
     * Test of getAskedBy method, of class Question.
     */
    @Test
    public void testGetAskedBy() {
        System.out.println("getAskedBy");
        User expResult = new User("Real Name", "username");
        Question instance = new Question(expResult, "Question Text", null);
        User result = instance.getAskedBy();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAskedBy method, of class Question.
     */
    @Test
    public void testSetAskedBy() {
        System.out.println("setAskedBy");
        User user = new User("RealName","username");
        Question instance = new Question(user, "Question Text", null);
        User askedBy = new User("Real Name", "username");
        instance.setAskedBy(askedBy);
        User result = instance.getAskedBy();
        assertEquals(askedBy, result);
    }

    /**
     * Test of getNumAnswers method, of class Question.
     */
    @Test
    public void testGetNumAnswers() {
        System.out.println("getNumAnswers");
        User user = new User("Real Name", "username");
        Question instance = new Question(user, "Question Text", null);
        List<Answer> answers = new ArrayList<Answer>();
        answers.add(new Answer(user, "Answer Text 1", instance));
        answers.add(new Answer(user, "Answer Text 1", instance));
        instance.setAnswers(answers);
        int expResult = 2;
        int result = instance.getNumAnswers();
        assertEquals(expResult, result);
    }

    /**
     * Test of isAnswered method, of class Question.
     */
    @Test
    public void testIsAnswered() {
        System.out.println("isAnswered");
        User user = new User("Real Name", "username");
        Question instance = new Question(user, "Question Text", null);
        List<Answer> answers = new ArrayList<Answer>();
        answers.add(new Answer(user, "Answer Text 1", instance));
        answers.add(new Answer(user, "Answer Text 1", instance));
        instance.setAnswers(answers);
        boolean expResult = true;
        boolean result = instance.isAnswered();
        assertEquals(expResult, result);
    }

    /**
     * Test of getqText method, of class Question.
     */
    @Test
    public void testGetqText() {
        System.out.println("getqText");
        User user = new User("Real Name", "username");
        Question instance = new Question(user, "Question Text", null);
        List<Answer> answers = new ArrayList<Answer>();
        answers.add(new Answer(user, "Answer Text 1", instance));
        answers.add(new Answer(user, "Answer Text 1", instance));
        instance.setAnswers(answers);
        String expResult = "Question Text";
        String result = instance.getqText();
        assertEquals(expResult, result);
    }

    /**
     * Test of setqText method, of class Question.
     */
    @Test
    public void testSetqText() {
        System.out.println("setqText");
        User user = new User("Real Name", "username");
        Question instance = new Question(user, "Question Text", null);
        String expResult = "New Text";
        instance.setqText(expResult);
        String result = instance.getqText();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAnswers method, of class Question.
     */
    @Test
    public void testGetAnswers() {
        System.out.println("getAnswers");
        User user = new User("Real Name", "username");
        Question instance = new Question(user, "Question Text", null);
        List expResult = null;
        List result = instance.getAnswers();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAnswers method, of class Question.
     */
    @Test
    public void testSetAnswers() {
        System.out.println("setAnswers");
        User user = new User("Real Name", "username");
        Question instance = new Question(user, "Question Text", null);
        List<Answer> answers = new ArrayList<Answer>();
        answers.add(new Answer(user, "Answer Text 1", instance));
        answers.add(new Answer(user, "Answer Text 1", instance));
        instance.setAnswers(answers);
        List<Answer> result = instance.getAnswers();
        assertEquals(answers, result);
    }
}
