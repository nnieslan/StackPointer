package stackpointer.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import stackpointer.common.SXUser;
import stackpointer.stackexchange.Question;

/**
 *
 * @author Andrew
 */
public class SXDatabaseFacadeTest {
    
    public SXDatabaseFacadeTest() {
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
     * Test of syncQuestions method, of class SXDatabaseFacade.
     */
    @Test
    public void testSyncQuestions() {
        System.out.println("syncQuestions");
        List<Question> questionList = new ArrayList<Question>();
        
        List<String> tags = new ArrayList<String>();
        tags.add(".net");
        tags.add("mysql");
        
        SXUser u = new SXUser();
        u.setSXid(1);
        
        Question q = new Question();
        q.setQid(123);
        q.setPostedTimestamp(new Date());
        q.setqText("test question text 1");
        q.setqTitle("test question title 1");
        q.setAnswers(null);
        q.setAskedBy(u);
        //q.setTags(tags);
        questionList.add(q);
        
        q = new Question();
        q.setQid(456);
        q.setPostedTimestamp(new Date());
        q.setqText("test question text 2");
        q.setqTitle("test question title 2");
        q.setAnswers(null);
        q.setAskedBy(u);
        questionList.add(q);
        
        try {
            SXDatabaseFacade sxDB = new SXDatabaseFacade();
            int numAdded = sxDB.syncQuestions(questionList);
            assertTrue("not all questions were added", numAdded == questionList.size());
            
            // Now we need to clean up after ourselves
            QuestionRepo repo = new QuestionRepo(
                    DatabaseConnectionInfo.createDefault());
            for (Question question : questionList) {
                if (repo.exists(question.getQid())) {
                    repo.delete(question.getQid());
                }
            }
        } catch (SQLException ex) {
            fail("SQLException thrown");
        }
    }

    /**
     * Test of retrieveTop100Questions method, of class SXDatabaseFacade.
     */
    @Test
    public void testRetrieveTop100Questions() {
        System.out.println("retrieveTop100Questions");
        SXDatabaseFacade sxDB = new SXDatabaseFacade();
        List<Question> questionList = sxDB.retrieveTop100Questions();
        assertNotNull(questionList);
        assertTrue(questionList.size() >= 0);
    }
}
