package stackpointer.database;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Andrew
 */
public class AnswerRepoTest {
    
    private final int TestAid = 123;
    private final int TestQid = 1;
    private final int TestUserId = 1;
    private final String AddTestText = "DB Test answer text";
    private final String UpdateTestText = "DB Test answer text 2";
    
    public AnswerRepoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of the add, update, retrieve(int), and delete methods
     * of class AnswerRepo.
     */
    @Test
    public void testAddUpdateRetrieveDelete() throws Exception {
        AnswerEntity a = new AnswerEntity();
        AnswerRepo repo = new AnswerRepo(DatabaseConnectionInfo.createDefault());
        
         /******************/
        /******* add ******/
        /******************/
        System.out.println("add");
        
        // we'll assume the database is not empty for associated data
        a.setAid(TestAid);
        a.setAssociatedQid(TestQid);
        a.setPostedByUserId(TestUserId);
        a.setText(AddTestText);
        a.setPostedTimestamp(new Date());
        boolean rowAdded = repo.add(a);
        
        assertTrue("add - record was not added", rowAdded);
        
        /******************/
        /***** update *****/
        /******************/
        System.out.println("update");
        
        a.setText(UpdateTestText);
        boolean rowUpdated = repo.update(a);
        
        assertTrue("update - record was not updated", rowUpdated);
        
        /******************/
        /**** retrieve ****/
        /******************/
        System.out.println("retrieve");
        
        List<AnswerEntity> answerList = repo.retrieve(TestQid);
        
        assertNotNull("retrieve - answerList is null", answerList);
        assertTrue("retrieve - answerList does not have at least one value",
                answerList.size() >= 1);
        
        AnswerEntity foundAnswer = null;
        for (AnswerEntity temp : answerList) {
            if (temp.getAid() == a.getAid()) {
                foundAnswer = temp;
                break;
            }
        }
        
        assertEquals("retrieve - aid not equal",
                a.getAssociatedQid(), foundAnswer.getAssociatedQid());
        assertEquals("retrieve - postedByUserId not equal",
                a.getPostedByUserId(), foundAnswer.getPostedByUserId());
        assertEquals("retrieve - text not equal",
                a.getText(), foundAnswer.getText());
        
        /******************/
        /***** delete *****/
        /******************/
        System.out.println("delete");
        
        boolean rowDeleted = repo.delete(a);
        
        assertTrue("delete - no record deleted", rowDeleted);
        assertTrue("delete - aid was not reset", a.getAid() == 0);
    }

    /**
     * Test of retrieve method, of class AnswerRepo.
     */
    @Test
    public void testRetrieve_withSetParameter() throws Exception {
        System.out.println("testRetrieve_withSetParameter");
        AnswerRepo repo = new AnswerRepo(DatabaseConnectionInfo.createDefault());
        
        Set<Integer> questionIds = new HashSet<Integer>();
        questionIds.add(TestQid);
        List<AnswerEntity> answerList = repo.retrieve(questionIds);
        
        assertNotNull("answerList is null", answerList);
        assertNotNull("set did not contain a valid number of answers",
                answerList.size() >= 0);
    }
}
