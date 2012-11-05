/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stackpointer.jobs;

import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import stackpointer.jobs.JobPosting;
import stackpointer.jobs.LinkedInInterface;

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
        String jJson = "{\"values\":[{\"question_id\":12775016,\"creation_date\":1349667808,\"last_activity_date\":1349667808,\"score\":0,\"answer_count\":0,\"body\":\"<p>My code:</p>\\n\\n<pre><code>from PyQt4.QtGui import *\\n\\ndoc = QTextDocument()\\ncur = QTextCursor(doc)\\n\\ntable_fmt = QTextTableFormat()\\ntable_fmt.setBackground(QColor(\'#e0e0e0\'))\\ntable_fmt.setColumnWidthConstraints([\\n    QTextLength(QTextLength.PercentageLength, 30),\\n    QTextLength(QTextLength.PercentageLength, 70)\\n    ])\\n\\ntable = cur.insertTable(5,2, table_fmt)\\n\\ncur.insertText(\'sample text 1\')\\ncur.movePosition(cur.NextCell)\\ncur.insertText(\'sample text 2\')\\n\\nwriter = QTextDocumentWriter()\\nwriter.setFormat(writer.supportedDocumentFormats()[1])\\nwriter.setFileName(\'CV\')\\nwriter.write(doc)\\n</code></pre>\\n\\n<p>The output is both columns have enough width (50%), rather than 30%-70%.</p>\\n\\n<p>OS: Ubuntu 12.04\\nPyQt4: PyQt 4.9.5</p>\\n\",\"title\":\"Qt4/PyQt4: setColumnWidthConstraints has no effect\",\"tags\":[\"c++\",\"python\",\"qt4\",\"pyqt4\"],\"view_count\":1,\"owner\":{\"user_id\":1653521,\"display_name\":\"Hieu\",\"reputation\":51,\"user_type\":\"registered\",\"profile_image\":\"http://www.gravatar.com/avatar/1d94bb3590bae53c2c7264b1838b5b9d?d=identicon&r=PG\",\"link\":\"http://stackoverflow.com/users/1653521/hieu\"},\"link\":\"http://stackoverflow.com/questions/12775016/qt4-pyqt4-setcolumnwidthconstraints-has-no-effect\",\"is_answered\":false}],\"quota_remaining\":9956,\"quota_max\":10000,\"has_more\":true}";
        ArrayList<JobPosting> jobs = null;
        try
        {
            jobs = LinkedInInterface.parseJobsFromJson(new JSONObject(jJson));
        }
        catch(JSONException e)
        {
            
        }
        Assert.assertNotNull(jobs);
        Assert.assertEquals(jobs.size(), 1);
    }
}
