package stackpointer.database;

import java.util.Date;
import java.util.List;
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
public class QuestionRepoTest {
    
    public QuestionRepoTest() {
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
     * Test of the add, update, and delete methods of class QuestionRepo.
     */
    @Test
    public void testAddUpdateDelete() throws Exception {
        int qid = 0;
        QuestionEntity q = new QuestionEntity();                
        QuestionRepo repo = new QuestionRepo(
                DatabaseConnectionInfo.createDefault());
        
        /******************/
        /******* add ******/
        /******************/
        System.out.println("add");
        
        q.setPostedByUserId(1);
        q.setPostedTimestamp(new Date());
        q.setTitle("DB Test Question Title");
        q.setText("DB Test Question body text");
        boolean rowAdded = repo.add(q);
        
        assertTrue("add - record was not added", rowAdded);
        assertTrue("add - qid was not retrieved", q.getQid() > 0);
        
        /******************/
        /***** update *****/
        /******************/
        System.out.println("update");
        
        q.setPostedByUserId(2);
        q.setPostedTimestamp(new Date());
        q.setTitle("DB Test Question Title 2");
        q.setText("DB Test Question body text 2");
        boolean rowUpdated = repo.update(q);
        
        assertTrue("update - record was not updated", rowUpdated);
        
        /******************/
        /***** delete *****/
        /******************/
        System.out.println("delete");
        
        boolean rowDeleted = repo.delete(q);
        
        assertTrue("delete - no record deleted", rowDeleted);
        assertTrue("delete - qid was not reset", q.getQid() == 0);
    }

    /**
     * Test of retrieveLast100 method, of class QuestionRepo.
     */
    @Test
    public void testRetrieveLast100() throws Exception {
        System.out.println("retrieveLast100");
        QuestionRepo repo = new QuestionRepo(
                DatabaseConnectionInfo.createDefault());
        List<QuestionEntity> questionList = repo.retrieveLast100();
        assertNotNull(questionList);
        assertTrue(questionList.size() >= 0);
        assertTrue(questionList.size() <= 100);
    }
}
