package stackpointer.database;

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
public class UserRepoTest {
    
    private final String AddTestSXID = "DBTestSXID";
    private final String AddTestUsername = "DB Test Username";
    private final String AddTestLocationText = "New York, NY";
    private final double AddTestLocationLat = 40.73269;
    private final double AddTestLocationLon = -73.990173; 
    private final String UpdateTestSXID = "DBTestSXID2";
    private final String UpdateTestUsername = "DB Test Username 2";
    private final String UpdateTestLocationText = "Los Angeles, CA";
    private final double UpdateTestLocationLat = 34.040143;
    private final double UpdateTestLocationLon = -118.258209;
    
    public UserRepoTest() {
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
     * Test of UserRepo class methods.
     */
    @Test    
    public void testAddUpdateRetrieveDelete() throws Exception {
        int uid = 0;
        UserEntity u = new UserEntity();                
        UserRepo repo = new UserRepo(DatabaseConnectionInfo.createDefault());
        
        /******************/
        /******* add ******/
        /******************/
        System.out.println("add");
        
        u.setSxid(AddTestSXID);
        u.setUsername(AddTestUsername);
        u.setLocationText(AddTestLocationText);
        u.setLocationLat(AddTestLocationLat);
        u.setLocationLon(AddTestLocationLon);
        boolean rowAdded = repo.add(u);
        uid = u.getUid();
        
        assertTrue("add - record was not added", rowAdded);
        assertTrue("add - uid was not retrieved", u.getUid() > 0);
        
        /******************/
        /***** update *****/
        /******************/
        System.out.println("update");
        
        u.setSxid(UpdateTestSXID);
        u.setUsername(UpdateTestUsername);
        u.setLocationText(UpdateTestLocationText);
        u.setLocationLat(UpdateTestLocationLat);
        u.setLocationLon(UpdateTestLocationLon);
        boolean rowUpdated = repo.update(u);
        
        assertTrue("update - record was not updated", rowUpdated);
        
        /******************/
        /**** retrieve ****/
        /******************/
        System.out.println("retreive");
        
        Set<Integer> userIds = new HashSet<Integer>();
        userIds.add(uid);
        List<UserEntity> userList = repo.retrieve(userIds);
        
        assertNotNull("retrieve - userList is null", userList);
        assertTrue("retrieve - userList did not contain 1 record", userList.size() == 1);
        
        UserEntity expected = userList.get(0);
        assertEquals("retrieve - uid not equal",
                expected.getUid(), uid);
        assertEquals("retrieve - sxid not equal",
                expected.getSxid(), UpdateTestSXID);
        assertEquals("retrieve - username not equal",
                expected.getUsername(), UpdateTestUsername);
        assertEquals("retrieve - location text not equal",
                expected.getLocationText(), UpdateTestLocationText);
        assertEquals("retrieve - location lat not equal",
                expected.getLocationLat(), UpdateTestLocationLat, 0.00001);
        assertEquals("retrieve - location lon not equal",
                expected.getLocationLon(), UpdateTestLocationLon, 0.00001);
        
        /******************/
        /***** delete *****/
        /******************/
        System.out.println("delete");
        
        boolean rowDeleted = repo.delete(u);
        
        assertTrue("delete - no record deleted", rowDeleted);
        assertTrue("delete - uid was not reset", u.getUid() == 0);
    }
}
