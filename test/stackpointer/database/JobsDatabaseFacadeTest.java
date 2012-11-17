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
import stackpointer.common.Location;
import stackpointer.jobs.JobPosting;

/**
 *
 * @author Andrew
 */
public class JobsDatabaseFacadeTest {
    
    public JobsDatabaseFacadeTest() {
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
     * Test of syncJobPostings method, of class JobsDatabaseFacade.
     */
    @Test
    public void testSyncJobPostings() {
        System.out.println("syncJobPostings");
        
        List<JobPosting> jobsList = new ArrayList<JobPosting>();
        
        JobPosting jp = new JobPosting();
        jp.setLinkedInId(123);
        jp.setCompany("test company 1");
        jp.setDatePosted(new Date());
        jp.setDescription("test description 1");
        jp.setHeadline("test headline 1");
        jp.setLoc(new Location("Toronto, Canada"));
        jobsList.add(jp);
        
        jp = new JobPosting();
        jp.setLinkedInId(456);
        jp.setCompany("test company 2");
        jp.setDatePosted(new Date());
        jp.setDescription("test description 2");
        jp.setHeadline("test headline 2");
        jp.setLoc(new Location("Paris, France"));
        jobsList.add(jp);
        
        try {
            JobsDatabaseFacade jobsDB = new JobsDatabaseFacade();
            int numAdded = jobsDB.syncJobPostings(jobsList);
            assertTrue(numAdded == jobsList.size());
            
            // Now we need to clean up after ourselves
            JobPostingRepo repo = new JobPostingRepo(
                    DatabaseConnectionInfo.createDefault());
            for (JobPosting jobPosting : jobsList) {
                if (repo.exists(jobPosting.getLinkedInId())) {
                    repo.delete(jobPosting.getLinkedInId());
                }
            }
            
        } catch (SQLException ex) {
            fail("SQLException thrown");
        }
    }

    /**
     * Test of retrieveAllJobPostings method, of class JobsDatabaseFacade.
     */
    @Test
    public void testRetrieveAllJobPostings() {
        JobsDatabaseFacade jobsDB = new JobsDatabaseFacade();
        List<JobPosting> jobsList = jobsDB.retrieveAllJobPostings();
        assertNotNull(jobsList);
        assertTrue(jobsList.size() >= 0);
    }
}
