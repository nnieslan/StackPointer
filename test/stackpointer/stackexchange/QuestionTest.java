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
import stackpointer.common.SXUser;

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
        SXUser expResult = new SXUser();
        expResult.setRealName("Real Name");
        expResult.setUserName("username");
        Question instance = new Question();
        instance.setAskedBy(expResult);
        instance.setqText("Question Text");
        SXUser result = instance.getAskedBy();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAskedBy method, of class Question.
     */
    @Test
    public void testSetAskedBy() {
        System.out.println("setAskedBy");
        SXUser user = new SXUser();
        user.setRealName("RealName");
        user.setUserName("username");
        Question instance = new Question();
        instance.setAskedBy(user);
        instance.setqText("Question Text");       
        SXUser askedBy = new SXUser();
        askedBy.setRealName("Real Name");
        askedBy.setUserName("username");
        instance.setAskedBy(askedBy);
        SXUser result = instance.getAskedBy();
        assertEquals(askedBy, result);
    }

    /**
     * Test of getNumAnswers method, of class Question.
     */
    @Test
    public void testGetNumAnswers() {
        System.out.println("getNumAnswers");
        SXUser user = new SXUser();
        user.setRealName("Real Name");
        user.setUserName("username");
        Question instance = new Question();
        instance.setAskedBy(user);
        instance.setqText("Question Text");
        List<Answer> answers = new ArrayList<Answer>();
        answers.add(new Answer());
        answers.add(new Answer());
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
        SXUser user = new SXUser();
        user.setRealName("Real Name");
        user.setUserName("username");
        Question instance = new Question();
        instance.setAskedBy(user);
        instance.setqText("Question Text");
        List<Answer> answers = new ArrayList<Answer>();
        answers.add(new Answer());
        answers.add(new Answer());
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
        SXUser user = new SXUser();
        user.setRealName("Real Name");
        user.setUserName("username");
        Question instance = new Question();
        instance.setAskedBy(user);
        instance.setqText("Question Text");
        List<Answer> answers = new ArrayList<Answer>();
        answers.add(new Answer());
        answers.add(new Answer());
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
        SXUser user = new SXUser();
        user.setRealName("Real Name");
        user.setUserName("username");
        Question instance = new Question();
        instance.setAskedBy(user);
        instance.setqText("Question Text");
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
        SXUser user = new SXUser();
        user.setRealName("Real Name");
        user.setUserName("username");
        Question instance = new Question();
        instance.setAskedBy(user);
        instance.setqText("Question Text");
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
        SXUser user = new SXUser();
        user.setRealName("Real Name");
        user.setUserName("username");
        Question instance = new Question();
        instance.setAskedBy(user);
        instance.setqText("Question Text");
        List<Answer> answers = new ArrayList<Answer>();
        answers.add(new Answer());
        answers.add(new Answer());
        instance.setAnswers(answers);
        List<Answer> result = instance.getAnswers();
        assertEquals(answers, result);
    }
}
