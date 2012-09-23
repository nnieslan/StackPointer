/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stackpointer.common;

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
public class LocationTest {
    
    public LocationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("* LocationTest: @BeforeClass method");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("* LocationTest: @AfterClass method");
    }
    
    @Before
    public void setUp() {
        System.out.println("* LocationTest: @Before method");
    }
    
    @After
    public void tearDown() {
        System.out.println("* LocationTest: @After method");
    }

    /**
     * Test of getLat method, of class Location.
     */
    @Test
    public void testGetLatDEFAULT() {
        System.out.println("getLat");
        Location instance = new Location(0,0,0);
        float expResult = 0.0F;
        float result = instance.getLat();
        assertEquals(expResult, result, 0.0F);
    }
    
    /**
     * Test of getLat method, of class Location.
     */
    @Test
    public void testGetLatUPDATED() {
        System.out.println("getLat");
        Location instance = new Location(0,0,0);
        instance.setLat(10.0F);
        float expResult = 10.0F;
        float result = instance.getLat();
        assertEquals(expResult, result, 10.0F);
    }

    /**
     * Test of setLat method, of class Location.
     */
    @Test
    public void testSetLat() {
        System.out.println("setLat");
        float lat = 10.0F;
        Location instance = new Location(0,0,0);
        instance.setLat(lat);
        float result = instance.getLat();
        assertEquals(lat, result, 10.0F);
    }

    /**
     * Test of getLon method, of class Location.
     */
    @Test
    public void testGetLonDEFAULT() {
        System.out.println("getLon");
        Location instance = new Location(0,0,0);
        float expResult = 0.0F;
        float result = instance.getLon();
        assertEquals(expResult, result, 0.0F);
    }
    
    /**
     * Test of getLon method, of class Location.
     */
    @Test
    public void testGetLonUPDATED() {
        System.out.println("getLon");
        Location instance = new Location(0,0,0);
        instance.setLon(10.0F);
        float expResult = 10.0F;
        float result = instance.getLon();
        assertEquals(expResult, result, 10.0F);
    }

    /**
     * Test of setLon method, of class Location.
     */
    @Test
    public void testSetLon() {
        System.out.println("setLon");
        float lon = 10.0F;
        Location instance = new Location(0,0,0);
        instance.setLon(lon);
        float result = instance.getLon();
        assertEquals(lon, result, 10.0F);
    }

    
    /**
     * Test of getZip method, of class Location.
     */
    @Test
    public void testGetZipDEFAULT() {
        System.out.println("getZip");
        Location instance = new Location(0,0,0);
        int expResult = 0;
        int result = instance.getZip();
        assertEquals(expResult, result, 0);
    }
    
    /**
     * Test of getZip method, of class Location.
     */
    @Test
    public void testGetZipUPDATED() {
        System.out.println("getZip");
        Location instance = new Location(0,0,0);
        instance.setZip(16803);
        int expResult = 16803;
        int result = instance.getZip();
        assertEquals(expResult, result, 16803);
    }

    /**
     * Test of setZip method, of class Location.
     */
    @Test
    public void testSetZip() {
        System.out.println("setZip");
        int zip = 16803;
        Location instance = new Location(0,0,0);
        instance.setZip(zip);
        float result = instance.getZip();
        assertEquals(zip, result, 16803);
    }
}
