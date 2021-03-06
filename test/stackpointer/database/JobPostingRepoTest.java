package stackpointer.database;

import java.util.ArrayList;
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
public class JobPostingRepoTest {
    
    private final int AddTestJpid = 123;
    private final String AddTestCompany = "DB Test Company";
    private final String AddTestHeadline = "DB Test Headline";
    private final String AddTestLocationText = "DB Test Location";
    private final double AddTestLocationLat = 42.489061;
    private final double AddTestLocationLon = -83.711014;
    private final String AddTestDescription = "DB Test Description";
    private final String UpdateTestCompany = "DB Test Company 2";
    private final String UpdateTestHeadline = "DB Test Headline 2";
    private final String UpdateTestLocationText = "DB Test Location 2";
    private final double UpdateTestLocationLat = 12.489061;
    private final double UpdateTestLocationLon = -43.711014;
    private final String UpdateTestDescription = "DB Test Description 2";
    
    public JobPostingRepoTest() {
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
     * Test of the add, update, retrieve, and delete methods
     * of class JobPostingRepo.
     */
    @Test
    public void testAddUpdateRetrieveDelete() throws Exception {
        JobPostingEntity j = new JobPostingEntity();                
        JobPostingRepo repo = new JobPostingRepo(
                DatabaseConnectionInfo.createDefault());
        
        /******************/
        /******* add ******/
        /******************/
        System.out.println("add");
        
        j.setJpid(AddTestJpid);
        j.setDatePosted(new Date());
        j.setCompany(AddTestCompany);
        j.setDescription(AddTestDescription);
        j.setHeadline(AddTestHeadline);
        j.setLocationText(AddTestLocationText);
        boolean rowAdded = repo.add(j);
        
        assertTrue("add - record was not added", rowAdded);
        
        /******************/
        /***** update *****/
        /******************/
        System.out.println("update");
        
        j.setDatePosted(new Date());
        j.setCompany(UpdateTestCompany);
        j.setDescription(UpdateTestDescription);
        j.setHeadline(UpdateTestHeadline);
        j.setLocationText(UpdateTestLocationText);
        j.setLocationLat(AddTestLocationLat);
        j.setLocationLon(AddTestLocationLon);
        boolean rowUpdated = repo.update(j);
        
        assertTrue("update - record was not updated", rowUpdated);
        
         /******************/
        /**** retrieve ****/
        /******************/
        System.out.println("retreive");
        
        List<JobPostingEntity> jobList = repo.retrieve();
        
        assertNotNull("retrieve - jobList is null", jobList);
        assertTrue("retrieve - jobList did not contain at least one record",
                jobList.size() >= 1);
        
        JobPostingEntity foundJob = null;
        for (JobPostingEntity temp : jobList) {
            if (temp.getJpid() == j.getJpid()) {
                foundJob = temp;
                break;
            }
        }
        
        assertNotNull("retrieve - did not find target job in jobList", foundJob);
        assertEquals("retrieve - company did not equal",
                j.getCompany(), foundJob.getCompany());
        assertEquals("retrieve - description did not equal",
                j.getDescription(), foundJob.getDescription());
        assertEquals("retrieve - headline did not equal",
                j.getHeadline(), foundJob.getHeadline());
        assertEquals("retrieve - location text did not equal",
                j.getLocationText(), foundJob.getLocationText());
        assertEquals("retrieve - location lat did not equal",
                j.getLocationLat(), foundJob.getLocationLat(), 0.00001);
        assertEquals("retrieve - location lon did not equal",
                j.getLocationLon(), foundJob.getLocationLon(), 0.00001);
        
        /******************/
        /***** delete *****/
        /******************/
        System.out.println("delete");
        
        boolean rowDeleted = repo.delete(j);
        
        assertTrue("delete - no record deleted", rowDeleted);
        assertTrue("delete - jpid was not reset", j.getJpid() == 0);
    }
    
    /**
     * Test of the retrieve by keywords method of class JobPostingRepo.
     */
    @Test
    public void testRetrieveWithKeywords() throws Exception {
        System.out.println("retrieveByKeyword");
        
        JobPostingRepo repo = new JobPostingRepo(
                DatabaseConnectionInfo.createDefault());
        
        List<String> keywords = new ArrayList<String>();
        keywords.add("web");
        keywords.add("developer");
        
        List<JobPostingEntity> jobsList = repo.retrieve(keywords);
        
        assertNotNull(jobsList);
    }
    
}
