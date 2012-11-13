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
public class SXUserTest {
    
    public SXUserTest() {
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
     * Test of getRealName method, of class SXUser.
     */
    @Test
    public void testGetRealName() {
        System.out.println("getRealName");
        SXUser instance = new SXUser();
        instance.setRealName("Real Name");
        instance.setUserName("username");
        String expResult = "Real Name";
        String result = instance.getRealName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRealName method, of class SXUser.
     */
    @Test
    public void testSetRealName() {
        System.out.println("setRealName");
        String realName = "Test Name";
        SXUser instance = new SXUser();
        instance.setRealName(realName);
        String result = instance.getRealName();
        assertEquals(realName, result);
    }

    /**
     * Test of getUserName method, of class SXUser.
     */
    @Test
    public void testGetUserName() {
        System.out.println("getUserName");
        SXUser instance = new SXUser();
        instance.setRealName("Real Name");
        instance.setUserName("username");
        String expResult = "username";
        String result = instance.getUserName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUserName method, of class SXUser.
     */
    @Test
    public void testSetUserName() {
        System.out.println("setUserName");
        String userName = "differentUserName";
        SXUser instance = new SXUser();
        instance.setRealName("Real Name");
        instance.setUserName("username");
        instance.setUserName(userName);
        String result = instance.getUserName();
        assertEquals(userName, result);
    }

    /**
     * Test of getSXname method, of class SXUser.
     */
    @Test
    public void testGetSXname() {
        System.out.println("getSXname");
        SXUser instance = new SXUser();
        instance.setRealName("Real Name");
        instance.setUserName("username");
        String expResult = "Default";
        instance.setSXname(expResult);
        String result = instance.getSXname();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSXname method, of class SXUser.
     */
    @Test
    public void testSetSXname() {
        System.out.println("setSXname");
        String SXname = "NewSXname";
        SXUser instance = new SXUser();
        instance.setRealName("Real Name");
        instance.setUserName("username");
        instance.setSXname(SXname);
        String result = instance.getSXname();
        assertEquals(SXname, result);
    }

    /**
     * Test of getLoc method, of class SXUser.
     */
    @Test
    public void testGetLoc() {
        System.out.println("getLoc");
        SXUser instance = new SXUser();
        instance.setRealName("Real Name");
        instance.setUserName("username");
        Location expResult = new Location(0,0,0);
        instance.setLoc(expResult);
        Location result = instance.getLoc();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLoc method, of class SXUser.
     */
    @Test
    public void testSetLoc() {
        System.out.println("setLoc");
        Location loc = new Location(0,0,0);
        SXUser instance = new SXUser();
        instance.setRealName("Real Name");
        instance.setUserName("username");
        instance.setLoc(loc);
        Location result = instance.getLoc();
        assertEquals(loc, result);
    }

    /**
     * Test of getFollowing method, of class SXUser.
     */
    @Test
    public void testGetFollowing() {
        System.out.println("getFollowing");
        SXUser instance = new SXUser();
        instance.setRealName("Real Name");
        instance.setUserName("username");
        Set<SXUser> expResult = null;
        Set result = instance.getFollowing();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFollowing method, of class SXUser.
     */
    @Test
    public void testSetFollowing() {
        System.out.println("setFollowing");
        SXUser user1 = new SXUser();
        user1.setRealName("Real Name1");
        user1.setUserName("username1");
        SXUser user2 = new SXUser();
        user2.setRealName("Real Name2");
        user2.setUserName("username2");

        Set<SXUser> following = new HashSet<SXUser>();
        following.add(user1);
        following.add(user2);
        SXUser instance = new SXUser();
        instance.setRealName("Real Name");
        instance.setUserName("username");
        instance.setFollowing(following);
        Set<SXUser> result = instance.getFollowing();
        assertEquals(following, result);
    }

    /**
     * Test of getAsked method, of class SXUser.
     */
    @Test
    public void testGetAsked() {
        System.out.println("getAsked");
        SXUser instance = new SXUser();
        instance.setRealName("Real Name");
        instance.setUserName("username");
        List<Question> expResult = null;
        List<Question> result = instance.getAsked();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAsked method, of class SXUser.
     */
    @Test
    public void testSetAsked() {
        System.out.println("setAsked");
        List<Question> asked = null;
        SXUser instance = new SXUser();
        instance.setRealName("Real Name");
        instance.setUserName("username");
        instance.setAsked(asked);
        List<Question> result = instance.getAsked();
        assertEquals(asked, result);
    }

    /**
     * Test of getAnswered method, of class SXUser.
     */
    @Test
    public void testGetAnswered() {
        System.out.println("getAnswered");
        SXUser instance = new SXUser();
        instance.setRealName("Real Name");
        instance.setUserName("username");
        List<Answer> expResult = null;
        List<Answer> result = instance.getAnswered();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAnswered method, of class SXUser.
     */
    @Test
    public void testSetAnswered() {
        System.out.println("setAnswered");
        List<Answer> answered = null;
        SXUser instance = new SXUser();
        instance.setRealName("Real Name");
        instance.setUserName("username");
        instance.setAnswered(answered);
        List<Answer> result = instance.getAnswered();
        assertEquals(answered, result);
    }
}
