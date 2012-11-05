/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stackpointer.jobs;

import java.util.ArrayList;
import junit.framework.Assert;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nathan
 */
public class LinkedInInterfaceTest {
    
    public LinkedInInterfaceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("* LinkedInInterfaceTest: @BeforeClass method");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("* LinkedInInterfaceTest: @BeforeClass method");
    }
    
    @Before
    public void setUp() {
        System.out.println("* LinkedInInterfaceTest: @BeforeClass method");
    }
    
    @After
    public void tearDown() {
        System.out.println("* LinkedInSInterfaceTest: @BeforeClass method");
    }

    /**
     * Test of establishConnection method, of class LinkedInInterface.
     */
    @Test
    public void testEstablishConnection() {
        System.out.println("establishConnection");
        LinkedInInterface instance = new LinkedInInterface();
        instance.establishConnection();
        boolean expResult = true;
        boolean result = instance.isConnectionEstablished();
        assertEquals(expResult, result);
    }

    /**
     * Test of isConnectionEstablished method, of class LinkedInInterface.
     */
    @Test
    public void testIsConnectionEstablished() {
        System.out.println("isConnectionEstablished");
        LinkedInInterface instance = new LinkedInInterface();
        //TODO - send in bad connection values;
        instance.establishConnection();
        boolean expResult = false;
        boolean result = instance.isConnectionEstablished();
        assertEquals(expResult, result);
        //TODO - send in good connection values;
        instance.establishConnection();
        expResult = true;
        result = instance.isConnectionEstablished();
        assertEquals(expResult, result);
    }

    /**
     * Test of updateLocalDatabase method, of class LinkedInInterface.
     */
    @Test
    public void testUpdateLocalDatabase() {
        System.out.println("updateLocalDatabase");
        LinkedInInterface instance = new LinkedInInterface();
        boolean expResult = true;
        boolean result = instance.updateLocalDatabase();
        assertEquals(expResult, result);
    }

    /**
     * Test of cleanDatabase method, of class LinkedInInterface.
     */
    @Test
    public void testCleanDatabase() {
        System.out.println("cleanDatabase");
        LinkedInInterface instance = new LinkedInInterface();
        boolean expResult = true;
        boolean result = instance.cleanDatabase();
        assertEquals(expResult, result);
    }

    /**
     * Test of updateJobPostings method, of class LinkedInInterface.
     */
    @Test
    public void testUpdateJobPostings() {
        System.out.println("updateJobPostings");
        //TODO - not sure how to properly validate this use case.
        LinkedInInterface instance = new LinkedInInterface();
        instance.updateJobPostings();
        boolean expResult = true;
        boolean result = false; //TODO - replace with proper test
        assertEquals(expResult, result);
    }

    /**
     * Test of getJobPostings method, of class LinkedInInterface.
     */
    @Test
    public void testGetJobPostings() {
        System.out.println("getJobPostings");
        LinkedInInterface instance = new LinkedInInterface();
        int expResult = 0;
        int result = instance.getJobPostings().size();
        assertEquals(expResult, result);
        instance.updateJobPostings();
        result = instance.getJobPostings().size();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testParseJobsFromJson() {
        String json = "{\"jobs\": {  \"_count\": 3,  \"_start\": 0,  \"_total\": 165780,  \"values\": [    {      \"company\": {        \"id\": 304553,        \"name\": \"PeakColo\"      },      \"description\": \"<p><strong>Principle Activities</strong> </p><ul><li>Implementation, installation and maintenance of PeakColo's cloud services </li><li>Act as a support escalation point to 1) fulfill customer service requests while delivering world-class customer service and 2) troubleshoot and remedy reported service issues </li><li>Plan and execute infrastructure upgrades while ensuring 100% service uptime </li><li>Execute complex customer changes </li><li>Mentor and train first level support staff </li><li>Enhance operational support processes </li><li>Maintain design documentation, enhance operational support systems </li><li>Review and act upon infrastructure capacity and performance trends </li></ul><p>  </p><p><strong>Challenges</strong> </p><ul><li>Fast paced, SLA-bound service provider environment with activities being highly visible and paramount to customer satisfaction </li><li>Breadth of technologies supported </li><li>High density cloud environment </li><li>Flexible schedule with on-call duties along with minimal required travel within the United States </li><li>Need to be self-motivated, independent and highly task oriented <p></p></li></ul><p>  </p><p>Qualified candidates should send their resumes to jobs@peakcolo.com for consideration.</p>\",      \"id\": 4098130,      \"locationDescription\": \"Downtown Denver\",      \"position\": {\"title\": \"Engineer II\"},      \"postingDate\": {        \"day\": 4,        \"month\": 11,        \"year\": 2012      }    },    {      \"company\": {        \"id\": 58792,        \"name\": \"Employment Solutions\"      },      \"description\": \"<p>  </p><p>We are Employment Solutions, a high performing and customer intimate regional staffing firm with a solid track record for service and growth.  We offer staffing solutions that maximize our customer's ability to do more with less and reduce static overhead.  We attribute our growth to our team's strong work ethic, our corporate commitment to excellence, our high quality service delivery and our teamwork. <br> <br> <br> Your sales skills encompass prospecting (including face to face cold calling), sales mining, qualifying and closing.  You must have demonstrated proven success in a high activity quota driven environment and want to <strong>earn six figures</strong>. <br> <br><br>This position does not require out of state or over-night travel. You will be working in a specific territory within the Denver Metropolitan area.<br> <br><br>Employment Solutions offers the right candidate a long-term progressive career path, independence and freedom, a full scope of Staffing Solutions for a wide variety of buyers as well as an opportunity to earn a lucrative income. </p><p>  </p><p><strong>Specific Requirements</strong><br> <br><br>You are a high achieving sales person with 3-10 years of consultative sales/solution selling business-to-business new account generation, specifically where you sell services or products to decision makers.  You are well versed in following a a proven structured sales process and do so with efficiency and perseverance.  You have a successful track record for new account acquisition, sales territory growth and customer retention.<br> <br><br>You are an upbeat, persuasive communicator and a great listener. You have strong relationship building, good questioning and probing skills and know when<br> and how to close deals.  <br> <br><br>You must have a proven and verifiable track record of outside sales.<br> <br><br>Bachelors Degree strongly preferred </p><p>  </p><p>  </p><p>STRONG INCOME POTENTIAL</p>\",      \"id\": 4098115,      \"locationDescription\": \"Greater Denver Area\",      \"position\": {\"title\": \"Outside Sales Representative\"},      \"postingDate\": {        \"day\": 4,        \"month\": 11,        \"year\": 2012      }    },    {      \"company\": {        \"id\": 2315328,        \"name\": \"IMMERGE, LLC\"      },      \"description\": \"<p>IMMERGE is an innovative, results-oriented contract sales company who is seeking an experienced Senior Corporate Recruiter in Denver, CO to lead our Talent Acquisition team.  The Senior Corporate Recruiter will be responsible to deliver top talent to the organization and ensure that the talent acquisition goals are exceeded.</p>\",      \"id\": 4098099,      \"locationDescription\": \"Denver, CO\",      \"position\": {\"title\": \"Senior Corporate Recruiter\"},      \"postingDate\": {        \"day\": 4,        \"month\": 11,        \"year\": 2012      }    }  ]}}";
        try
        {
            JSONObject parseMe = new JSONObject(json).getJSONObject("jobs");
            ArrayList<JobPosting> parsed = LinkedInInterface.parseJobsFromJson(parseMe);
            Assert.assertEquals(3, parsed.size()); //expect 3 jobs from sample input
        }
        catch (JSONException e)
        {
            System.out.println("Error: "+e);
        }
    }
}
