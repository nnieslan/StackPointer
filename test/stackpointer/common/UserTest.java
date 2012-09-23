/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stackpointer.common;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import stackpointer.stackexchange.Answer;
import stackpointer.stackexchange.Question;

/**
 *
 * @author Nathan
 */
public class UserTest {
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("* UserTest: @BeforeClass method");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("* UserTest: @AfterClass method");
    }
    
    @Before
    public void setUp() {
        System.out.println("* UserTest: @Before method");
    }
    
    @After
    public void tearDown() {
        System.out.println("* UserTest: @After method");
    }

    /**
     * Test of getRealName method, of class User.
     */
    @Test
    public void testGetRealName() {
        System.out.println("getRealName");
        User instance = new User("Real Name", "username");
        String expResult = "Real Name";
        String result = instance.getRealName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRealName method, of class User.
     */
    @Test
    public void testSetRealName() {
        System.out.println("setRealName");
        String realName = "Test Name";
        User instance = new User("Real Name", "username");
        instance.setRealName(realName);
        String result = instance.getRealName();
        assertEquals(realName, result);
    }

    /**
     * Test of getUserName method, of class User.
     */
    @Test
    public void testGetUserName() {
        System.out.println("getUserName");
        User instance = new User("Real Name", "username");
        String expResult = "username";
        String result = instance.getUserName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUserName method, of class User.
     */
    @Test
    public void testSetUserName() {
        System.out.println("setUserName");
        String userName = "differentUserName";
        User instance = new User("Real Name", "username");
        instance.setUserName(userName);
        String result = instance.getUserName();
        assertEquals(userName, result);
    }

    /**
     * Test of getSXid method, of class User.
     */
    @Test
    public void testGetSXid() {
        System.out.println("getSXid");
        User instance = new User("Real Name", "username");
        String expResult = "Default";
        instance.setSXid(expResult);
        String result = instance.getSXid();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSXid method, of class User.
     */
    @Test
    public void testSetSXid() {
        System.out.println("setSXid");
        String SXid = "NewSXid";
        User instance = new User("Real Name", "username");
        instance.setSXid(SXid);
        String result = instance.getSXid();
        assertEquals(SXid, result);
    }

    /**
     * Test of getLoc method, of class User.
     */
    @Test
    public void testGetLoc() {
        System.out.println("getLoc");
        User instance = new User("Real Name", "username");
        Location expResult = new Location(0,0,0);
        instance.setLoc(expResult);
        Location result = instance.getLoc();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLoc method, of class User.
     */
    @Test
    public void testSetLoc() {
        System.out.println("setLoc");
        Location loc = new Location(0,0,0);
        User instance = new User("Real Name", "username");
        instance.setLoc(loc);
        Location result = instance.getLoc();
        assertEquals(loc, result);
    }

    /**
     * Test of getFollowing method, of class User.
     */
    @Test
    public void testGetFollowing() {
        System.out.println("getFollowing");
        User instance = new User("Real Name", "username");
        Set<User> expResult = null;
        Set result = instance.getFollowing();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFollowing method, of class User.
     */
    @Test
    public void testSetFollowing() {
        System.out.println("setFollowing");
        User user1 = new User("Real Name1","username1");
        User user2 = new User("Real Name2","username2");

        Set<User> following = new HashSet<User>();
        following.add(user1);
        following.add(user2);
        User instance = new User("Real Name", "username");
        instance.setFollowing(following);
        Set<User> result = instance.getFollowing();
        assertEquals(following, result);
    }

    /**
     * Test of getAsked method, of class User.
     */
    @Test
    public void testGetAsked() {
        System.out.println("getAsked");
        User instance = new User("Real Name", "username");
        List<Question> expResult = null;
        List<Question> result = instance.getAsked();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAsked method, of class User.
     */
    @Test
    public void testSetAsked() {
        System.out.println("setAsked");
        List<Question> asked = null;
        User instance = new User("Real Name", "username");
        instance.setAsked(asked);
        List<Question> result = instance.getAsked();
        assertEquals(asked, result);
    }

    /**
     * Test of getAnswered method, of class User.
     */
    @Test
    public void testGetAnswered() {
        System.out.println("getAnswered");
        User instance = new User("Real Name", "username");
        List<Answer> expResult = null;
        List<Answer> result = instance.getAnswered();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAnswered method, of class User.
     */
    @Test
    public void testSetAnswered() {
        System.out.println("setAnswered");
        List<Answer> answered = null;
        User instance = new User("Real Name", "username");
        instance.setAnswered(answered);
        List<Answer> result = instance.getAnswered();
        assertEquals(answered, result);
    }
}
