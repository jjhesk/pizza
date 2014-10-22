/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.colostate.cs414.d.pizza.api.menu;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MenuManagerTest {
    
    public MenuManagerTest() {
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
     * Test of getInstance method, of class MenuManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        MenuManager expResult = MenuManager.getInstance();
        MenuManager result = MenuManager.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMenuItem method, of class MenuManager.
     */
    @Test
    public void testCreateMenuItem() {
        System.out.println("createMenuItem");
        String name = "";
        double price = 0.0;
        String description = "";
        MenuManager instance = new MenuManager();
        MenuItem expResult = null;
        MenuItem result = instance.createMenuItem(name, price, description);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveMenu method, of class MenuManager.
     */
    @Test
    public void testSaveMenu() {
        System.out.println("saveMenu");
        List<MenuItem> menuItems = null;
        MenuManager instance = new MenuManager();
        instance.saveMenu(menuItems);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMenuItems method, of class MenuManager.
     */
    @Test
    public void testGetMenuItems() {
        System.out.println("getMenuItems");
        MenuManager instance = new MenuManager();
        List<MenuItem> expResult = null;
        List<MenuItem> result = instance.getMenuItems();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllMenuItems method, of class MenuManager.
     */
    @Test
    public void testGetAllMenuItems() {
        System.out.println("getAllMenuItems");
        MenuManager instance = new MenuManager();
        List<MenuItem> expResult = null;
        List<MenuItem> result = instance.getAllMenuItems();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMenuItem method, of class MenuManager.
     */
    @Test
    public void testAddMenuItem() {
        System.out.println("addMenuItem");
        MenuItem item = null;
        MenuManager instance = new MenuManager();
        instance.addMenuItem(item);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeMenuItem method, of class MenuManager.
     */
    @Test
    public void testRemoveMenuItem() {
        System.out.println("removeMenuItem");
        MenuItem item = null;
        MenuManager instance = new MenuManager();
        instance.removeMenuItem(item);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDailySpecials method, of class MenuManager.
     */
    @Test
    public void testGetDailySpecials() {
        System.out.println("getDailySpecials");
        MenuManager instance = new MenuManager();
        List<DailySpecial> expResult = null;
        List<DailySpecial> result = instance.getDailySpecials();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDailySpecial method, of class MenuManager.
     */
    @Test
    public void testCreateDailySpecial() {
        System.out.println("createDailySpecial");
        List<MenuItem> menuItems = null;
        Double price = null;
        MenuManager instance = new MenuManager();
        DailySpecial expResult = null;
        DailySpecial result = instance.createDailySpecial(menuItems, price);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeDailySpecial method, of class MenuManager.
     */
    @Test
    public void testRemoveDailySpecial() {
        System.out.println("removeDailySpecial");
        DailySpecial special = null;
        MenuManager instance = new MenuManager();
        instance.removeDailySpecial(special);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkDailySpecials method, of class MenuManager.
     */
    @Test
    public void testCheckDailySpecials() {
        System.out.println("checkDailySpecials");
        MenuManager instance = new MenuManager();
        instance.checkDailySpecials();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
