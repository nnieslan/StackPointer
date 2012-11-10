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
import stackpointer.common.Location;

/**
 *
 * @author Andrew
 */
public class UserRepoTest {
    
    private final String AddTestSXID = "DBTestSXID";
    private final String AddTestUsername = "DB Test Username";
    private final Location AddTestLocation = new Location("New York, NY");
    private final String UpdateTestSXID = "DBTestSXID2";
    private final String UpdateTestUsername = "DB Test Username 2";
    private final Location UpdateTestLocation = new Location("Los Angeles, CA");
    
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
        u.setLocation(AddTestLocation);
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
        u.setLocation(UpdateTestLocation);
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
        assertEquals("retrieve - location not equal",
                expected.getLocation(), UpdateTestLocation);
        
        /******************/
        /***** delete *****/
        /******************/
        System.out.println("delete");
        
        boolean rowDeleted = repo.delete(u);
        
        assertTrue("delete - no record deleted", rowDeleted);
        assertTrue("delete - uid was not reset", u.getUid() == 0);
    }
}
