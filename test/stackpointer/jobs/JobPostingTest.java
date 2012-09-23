/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stackpointer.jobs;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import stackpointer.common.Location;

/**
 *
 * @author Nathan
 */
public class JobPostingTest {
    
    public JobPostingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("* JobPostingTest: @BeforeClass method");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("* JobPostingTest: @AfterClass method");
    }
    
    @Before
    public void setUp() {
        System.out.println("* JobPostingTest: @Before method");
    }
    
    @After
    public void tearDown() {
        System.out.println("* JobPostingTest: @After method");
    }

    /**
     * Test of getLoc method, of class JobPosting.
     */
    @Test
    public void testGetLoc() {
        System.out.println("getLoc");
        Location loc = new Location(0,0,0);
        JobPosting instance = new JobPosting(loc, new Date(), "Headline", "Description", "Company");
        Location result = instance.getLoc();
        assertEquals(loc, result);
    }

    /**
     * Test of setLoc method, of class JobPosting.
     */
    @Test
    public void testSetLoc() {
        System.out.println("setLoc");
        Location loc = new Location(0,0,0);
        JobPosting instance = new JobPosting(loc, new Date(), "Headline", "Description", "Company");
        Location newLoc = new Location(10,10,16803);
        instance.setLoc(newLoc);
        Location result = instance.getLoc();
        assertEquals(newLoc, result);
    }

    /**
     * Test of getDatePosted method, of class JobPosting.
     */
    @Test
    public void testGetDatePosted() {
        System.out.println("getDatePosted");
        Location loc = new Location(0,0,0);
        JobPosting instance = new JobPosting(loc, new Date(), "Headline", "Description", "Company");
        Date expResult = new Date();
        Date result = instance.getDatePosted();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDatePosted method, of class JobPosting.
     */
    @Test
    public void testSetDatePosted() {
        System.out.println("setDatePosted");
        Date datePosted = new Date();
        Location loc = new Location(0,0,0);
        JobPosting instance = new JobPosting(loc, new Date(), "Headline", "Description", "Company");
        instance.setDatePosted(datePosted);
        Date result = instance.getDatePosted();
        assertEquals(datePosted, result);
    }

    /**
     * Test of getHeadline method, of class JobPosting.
     */
    @Test
    public void testGetHeadline() {
        System.out.println("getHeadline");
        Location loc = new Location(0,0,0);
        JobPosting instance = new JobPosting(loc, new Date(), "Headline", "Description", "Company");
        String expResult = "Headline";
        String result = instance.getHeadline();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHeadline method, of class JobPosting.
     */
    @Test
    public void testSetHeadline() {
        System.out.println("setHeadline");
        String headline = "New Heading";
        Location loc = new Location(0,0,0);
        JobPosting instance = new JobPosting(loc, new Date(), "Headline", "Description", "Company");
        instance.setHeadline(headline);
        String result = instance.getHeadline();
        assertEquals(headline, result);
    }

    /**
     * Test of getDescription method, of class JobPosting.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Location loc = new Location(0,0,0);
        JobPosting instance = new JobPosting(loc, new Date(), "Headline", "Description", "Company");
        String expResult = "Description";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class JobPosting.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "New Description";
        Location loc = new Location(0,0,0);
        JobPosting instance = new JobPosting(loc, new Date(), "Headline", "Description", "Company");
        instance.setDescription(description);
        String result = instance.getDescription();
        assertEquals(description, result);
    }

    /**
     * Test of getCompany method, of class JobPosting.
     */
    @Test
    public void testGetCompany() {
        System.out.println("getCompany");
        Location loc = new Location(0,0,0);
        JobPosting instance = new JobPosting(loc, new Date(), "Headline", "Description", "Company");
        String expResult = "Company";
        String result = instance.getCompany();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCompany method, of class JobPosting.
     */
    @Test
    public void testSetCompany() {
        System.out.println("setCompany");
        String company = "New Company";
        Location loc = new Location(0,0,0);
        JobPosting instance = new JobPosting(loc, new Date(), "Headline", "Description", "Company");
        instance.setCompany(company);
        String result = instance.getCompany();
        assertEquals(company, result);
    }
}
