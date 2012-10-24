/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stackpointer.stackexchange;

import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import stackpointer.common.User;

/**
 *
 * @author Nathan
 */
public class StackExchangeInterfaceTest {
    
    public StackExchangeInterfaceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("* StackExchangeInterfaceTest: @BeforeClass method");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("* StackExchangeInterfaceTest: @AfterClass method");
    }
    
    @Before
    public void setUp() {
        System.out.println("* StackExchangeInterfaceTest: @Before method");
    }
    
    @After
    public void tearDown() {
        System.out.println("* StackExchangeInterfaceTest: @After method");
    }
    
    @Test
    public void testParseUserFromJson() {
        String userJson = "{\"user_id\":1653488,\"display_name\":\"Ngo Ky\",\"reputation\":44,\"user_type\":\"registered\",\"profile_image\":\"http://www.gravatar.com/avatar/78c301f58b30490ff7339dd7f8157d0e?d=identicon&r=PG\",\"link\":\"http://stackoverflow.com/users/1653488/ngo-ky\",\"accept_rate\":83}";
        User u = null;
        try
        {
            u = StackExchangeInterface.parseUserFromJson(new JSONObject(userJson));
        }
        catch(JSONException e)
        {
            
        }
        Assert.assertTrue(u.getSXname().equals("Ngo Ky"));
    }
    
    @Test
    public void testParseQuestionsFromJson() {
        String qJson = "{\"items\":[{\"question_id\":12775016,\"creation_date\":1349667808,\"last_activity_date\":1349667808,\"score\":0,\"answer_count\":0,\"body\":\"<p>My code:</p>\\n\\n<pre><code>from PyQt4.QtGui import *\\n\\ndoc = QTextDocument()\\ncur = QTextCursor(doc)\\n\\ntable_fmt = QTextTableFormat()\\ntable_fmt.setBackground(QColor(\'#e0e0e0\'))\\ntable_fmt.setColumnWidthConstraints([\\n    QTextLength(QTextLength.PercentageLength, 30),\\n    QTextLength(QTextLength.PercentageLength, 70)\\n    ])\\n\\ntable = cur.insertTable(5,2, table_fmt)\\n\\ncur.insertText(\'sample text 1\')\\ncur.movePosition(cur.NextCell)\\ncur.insertText(\'sample text 2\')\\n\\nwriter = QTextDocumentWriter()\\nwriter.setFormat(writer.supportedDocumentFormats()[1])\\nwriter.setFileName(\'CV\')\\nwriter.write(doc)\\n</code></pre>\\n\\n<p>The output is both columns have enough width (50%), rather than 30%-70%.</p>\\n\\n<p>OS: Ubuntu 12.04\\nPyQt4: PyQt 4.9.5</p>\\n\",\"title\":\"Qt4/PyQt4: setColumnWidthConstraints has no effect\",\"tags\":[\"c++\",\"python\",\"qt4\",\"pyqt4\"],\"view_count\":1,\"owner\":{\"user_id\":1653521,\"display_name\":\"Hieu\",\"reputation\":51,\"user_type\":\"registered\",\"profile_image\":\"http://www.gravatar.com/avatar/1d94bb3590bae53c2c7264b1838b5b9d?d=identicon&r=PG\",\"link\":\"http://stackoverflow.com/users/1653521/hieu\"},\"link\":\"http://stackoverflow.com/questions/12775016/qt4-pyqt4-setcolumnwidthconstraints-has-no-effect\",\"is_answered\":false}],\"quota_remaining\":9956,\"quota_max\":10000,\"has_more\":true}";
        ArrayList<Question> ql = null;
        try
        {
            ql = StackExchangeInterface.parseQuestionsFromJson(new JSONObject(qJson));
        }
        catch(JSONException e)
        {
            
        }
        Assert.assertNotNull(ql);
        Assert.assertEquals(ql.size(), 1);
    }

    /**
     * Test of updateLocalDatabase method, of class StackExchangeInterface.
     */
//    @Test
//    public void testUpdateLocalDatabase() {
//        System.out.println("updateLocalDatabase");
//        StackExchangeInterface instance = new StackExchangeInterface();
//        boolean expResult = true;
//        boolean result = instance.updateLocalDatabase();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of cleanDatabase method, of class StackExchangeInterface.
//     */
//    @Test
//    public void testCleanDatabase() {
//        System.out.println("cleanDatabase");
//        StackExchangeInterface instance = new StackExchangeInterface();
//        boolean expResult = true;
//        boolean result = instance.cleanDatabase();
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of updateTopQuestions method, of class StackExchangeInterface.
//     */
//    @Test
//    public void testUpdateTopQuestions() {
//        System.out.println("updateTopQuestions");
//        //TODO - not sure how to properly validate this use case.
//        StackExchangeInterface instance = new StackExchangeInterface();
//        instance.updateTopQuestions();
//        boolean expResult = true;
//        boolean result = false; //TODO - replace with proper test
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of getTop100Questions method, of class StackExchangeInterface.
//     */
//    @Test
//    public void testGetTop100Questions() {
//        System.out.println("getTop100Questions");
//        StackExchangeInterface instance = new StackExchangeInterface();
//        int expResult = 0;
//        int result = instance.getTop100Questions().size();
//        assertEquals(expResult, result);
//        instance.updateTopQuestions();
//        result = instance.getTop100Questions().size();
//        assertEquals(expResult, result);
//    }
}
