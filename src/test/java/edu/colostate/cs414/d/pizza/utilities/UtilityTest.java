package edu.colostate.cs414.d.pizza.utilities;

import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UtilityTest {

    @Test
    public void testGetMenuItem() {
        int menuItemID = 0;
        MenuItem testMenuItem = new MenuItem(menuItemID, "testMenuItem", 0, "a description", true);
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        menuItems.add(testMenuItem);
        assertEquals(testMenuItem, Utility.getMenuItem(menuItemID, menuItems));
    }
    
    @Test
    public void testGetMenuItemNotThere() {
        int menuItemID = 0;
        MenuItem testMenuItem = new MenuItem(menuItemID, "testMenuItem", 0, "a description", true);
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        menuItems.add(testMenuItem);
        assertEquals(null, Utility.getMenuItem(menuItemID + 1, menuItems));
    }

    @Test
    public void testTryApplyDailySpecial() {
        DailySpecial dailySpecial = null;
        List<MenuItem> dsMenuItems = new ArrayList<MenuItem>();
        dsMenuItems.add(new MenuItem(0, "testMenuItem0", 0, "a description", true));
        dsMenuItems.add(new MenuItem(1, "testMenuItem1", 0, "a description", true));
        dsMenuItems.add(new MenuItem(2, "testMenuItem2", 0, "a description", true));
        dailySpecial = new DailySpecial(0, 5.00, dsMenuItems, true);
        List<MenuItem> testItems = new ArrayList<MenuItem>();
        testItems.addAll(dsMenuItems);
        testItems.add(new MenuItem(3, "testMenuItem3", 0, "a description", true));
        assertEquals(true, Utility.tryApplyDailySpecial(dailySpecial, testItems));
        assertEquals(new MenuItem(3, "testMenuItem3", 0, "a description", true), testItems.get(0));
    }

    @Test
    public void testCalculateTax() {
        double subtotal = 1.00;
        double expResult = .07;
        double result = Utility.calculateTax(subtotal);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testCalculateTotalWithTax() {
        double subtotal = 1.0;
        double expResult = 1.07;
        double result = Utility.calculateTotalWithTax(subtotal);
        assertEquals(expResult, result, 0.0);
    }
}
