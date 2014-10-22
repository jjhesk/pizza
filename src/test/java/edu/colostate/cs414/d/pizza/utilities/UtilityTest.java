/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.colostate.cs414.d.pizza.utilities;

import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rawlin
 */
public class UtilityTest {
    
    public UtilityTest() {
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
    public void testGetMenuItem() {
        System.out.println("getMenuItem");
        int menuItemID = 0;
        List<MenuItem> menuItems = null;
        MenuItem expResult = null;
        MenuItem result = Utility.getMenuItem(menuItemID, menuItems);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testTryApplyDailySpecial() {
        System.out.println("tryApplyDailySpecial");
        DailySpecial dailySpecial = null;
        List<MenuItem> menuItems = null;
        boolean expResult = false;
        boolean result = Utility.tryApplyDailySpecial(dailySpecial, menuItems);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCalculateTax() {
        System.out.println("calculateTax");
        double subtotal = 0.0;
        double expResult = 0.0;
        double result = Utility.calculateTax(subtotal);
        assertEquals(expResult, result, 0.0);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCalculateTotal() {
        System.out.println("calculateTotal");
        double subtotal = 0.0;
        double tax = 0.0;
        double expResult = 0.0;
        double result = Utility.calculateTotal(subtotal, tax);
        assertEquals(expResult, result, 0.0);
        fail("The test case is a prototype.");
    }
}
