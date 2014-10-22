/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.colostate.cs414.d.pizza.db;

import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DailySpecialDatabaseControllerTest {
    
    public DailySpecialDatabaseControllerTest() {
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

    @Test
    public void testGetDailySpecials() {
        System.out.println("getDailySpecials");
        List<DailySpecial> dailySpecials = null;
        List<MenuItem> menuItems = null;
        DailySpecialDatabaseController instance = new DailySpecialDatabaseController();
        instance.getDailySpecials(dailySpecials, menuItems);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAddDailySpecial() {
        System.out.println("addDailySpecial");
        DailySpecial dailySpecial = null;
        DailySpecialDatabaseController instance = new DailySpecialDatabaseController();
        instance.addDailySpecial(dailySpecial);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetExpired() {
        System.out.println("setExpired");
        DailySpecial special = null;
        DailySpecialDatabaseController instance = new DailySpecialDatabaseController();
        instance.setExpired(special);
        fail("The test case is a prototype.");
    }
}
