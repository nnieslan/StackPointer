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
import stackpointer.common.SXUser;

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
        SXUser user = new SXUser();
        user.setRealName("Real Name");
        user.setUserName("username");
        Question question = new Question();
        question.setAskedBy(user);
        question.setqText("Question Text");
        Answer answer = new Answer();
        answer.setAnsweredBy(user);
        answer.setAnsText("Answer Text");
        answer.setAnswering(question);
        SXUser result = answer.getAnsweredBy();
        assertEquals(user, result);
    }

    /**
     * Test of setAnsweredBy method, of class Answer.
     */
    @Test
    public void testSetAnsweredBy() {
        System.out.println("setAnsweredBy");
        SXUser user = new SXUser();
        user.setRealName("Real Name");
        user.setUserName("username");
        Question question = new Question();
        question.setAskedBy(user);
        question.setqText("Question Text");
        Answer answer = new Answer();
        answer.setAnsweredBy(user);
        answer.setAnsText("Answer Text");
        answer.setAnswering(question);
        SXUser answeredBy = new SXUser();
        answeredBy.setRealName("Real Name 2");
        answeredBy.setUserName("username2");
        answer.setAnsweredBy(answeredBy);
        SXUser result = answer.getAnsweredBy();
        assertEquals(answeredBy, result);
    }

    /**
     * Test of getAnsText method, of class Answer.
     */
    @Test
    public void testGetAnsText() {
        System.out.println("getAnsText");
        SXUser user = new SXUser();
        user.setRealName("Real Name");
        user.setUserName("username");
        Question question = new Question();
        question.setAskedBy(user);
        question.setqText("Question Text");
        Answer answer = new Answer();
        answer.setAnsweredBy(user);
        answer.setAnsText("Answer Text");
        answer.setAnswering(question);
        String expResult = "Answer Text";
        String result = answer.getAnsText();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAnsText method, of class Answer.
     */
    @Test
    public void testSetAnsText() {
        System.out.println("setAnsText");
        String ansText = "Next Text";
        SXUser user = new SXUser();
        user.setRealName("Real Name");
        user.setUserName("username");
        Question question = new Question();
        question.setAskedBy(user);
        question.setqText("Question Text");
        Answer answer = new Answer();
        answer.setAnsweredBy(user);
        answer.setAnsText("Answer Text");
        answer.setAnswering(question);
        answer.setAnsText(ansText);
        String result = answer.getAnsText();
        assertEquals(ansText, result);
    }

    /**
     * Test of getAnswering method, of class Answer.
     */
    @Test
    public void testGetAnswering() {
        System.out.println("getAnswering");
        SXUser user = new SXUser();
        user.setRealName("Real Name");
        user.setUserName("username");
        Question question = new Question();
        question.setAskedBy(user);
        question.setqText("Question Text");
        Answer answer = new Answer();
        answer.setAnsweredBy(user);
        answer.setAnsText("Answer Text");
        answer.setAnswering(question);
        Question result = answer.getAnswering();
        assertEquals(question, result);
    }

    /**
     * Test of setAnswering method, of class Answer.
     */
    @Test
    public void testSetAnswering() {
        System.out.println("setAnswering");
        SXUser user = new SXUser();
        user.setRealName("Real Name");
        user.setUserName("username");
        Question question = new Question();
        question.setAskedBy(user);
        question.setqText("Question Text");
        Answer answer = new Answer();
        answer.setAnsweredBy(user);
        answer.setAnsText("Answer Text");
        answer.setAnswering(question);
        Question answering = new Question();
        answering.setAskedBy(user);
        answering.setqText("New Text");
        answer.setAnswering(answering);
        Question result = answer.getAnswering();
        assertEquals(answering, result);
    }
}
