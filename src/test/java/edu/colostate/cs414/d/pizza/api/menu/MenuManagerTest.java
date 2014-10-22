/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.colostate.cs414.d.pizza.api.menu;

import java.util.ArrayList;
import java.util.List;

import edu.colostate.cs414.d.pizza.utilities.Utility;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MenuManagerTest {

    private MenuManager menuManager;
    
    @Before
    public void setUp() {
        Utility.removeDataFromDatabase();
        menuManager = MenuManager.getInstance();

        menuManager.addMenuItem(new MenuItem(1, "testMenuItem1", 1.5, "", true));
        menuManager.addMenuItem(new MenuItem(2, "testMenuItem2", 14, null, true));
        menuManager.addMenuItem(new MenuItem(3, "testMenuItem3", 1.4, "a description", true));
        menuManager.addMenuItem(new MenuItem(4, "testMenuItem4", 19, "a description", false));
        menuManager.addMenuItem(new MenuItem(5, "testMenuItem5", 1, "a description", true));
    }
    
    @After
    public void tearDown() {
        Utility.removeDataFromDatabase();
    }

    @Test
    public void testGetInstance() {
        MenuManager expResult = MenuManager.getInstance();
        MenuManager result = MenuManager.getInstance();
        assertEquals(expResult, result);
    }

    @Test
    public void testCreateMenuItem() {
        String name = "Pizza";
        double price = 4.5;
        String description = "This is a pizza!";
        MenuItem newItem = new MenuItem(name,price,description);
        MenuItem result = menuManager.createMenuItem(name, price, description);
        assertEquals(newItem, result);
    }

    @Test
    public void testCreateMenuItemWithoutDescription() {
        String name = "Pizza";
        double price = 1;
        String description = null;
        MenuItem newItem = new MenuItem(name,price,description);
        MenuItem result = menuManager.createMenuItem(name, price, description);
        assertEquals(newItem, result);
    }

    @Test
    public void testSaveMenu() {
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        menuItems.add(new MenuItem(6, "newMenuItem6", 1, "a description", true));
        menuItems.add(new MenuItem(7, "newMenuItem7", 1, "a description", true));
        menuItems.add(new MenuItem(8, "newMenuItem8", 1, "a description", true));
        assertTrue(menuManager.getMenuItems().size() == 4);
        menuManager.saveMenu(menuItems);
        assertTrue(menuManager.getMenuItems().size() == 3);
        assertTrue(menuManager.getMenuItems().contains(menuItems.get(0)));
        assertTrue(menuManager.getMenuItems().contains(menuItems.get(1)));
        assertTrue(menuManager.getMenuItems().contains(menuItems.get(2)));
    }

    @Test
    public void testGetMenuItems() {
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        menuItems.add(new MenuItem(6, "newMenuItem6", 1, "a description", true));
        menuItems.add(new MenuItem(7, "newMenuItem7", 1, "a description", false));
        assertTrue(menuManager.getMenuItems().size() == 4);
        menuManager.addMenuItem(menuItems.get(0));
        menuManager.addMenuItem(menuItems.get(1));
        assertTrue(menuManager.getMenuItems().size() == 5);
        assertTrue(menuManager.getMenuItems().contains(menuItems.get(0)));
        assertFalse(menuManager.getMenuItems().contains(menuItems.get(1)));
    }

    @Test
    public void testGetAllMenuItems() {
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        menuItems.add(new MenuItem(6, "newMenuItem6", 1, "a description", true));
        menuItems.add(new MenuItem(7, "newMenuItem7", 1, "a description", false));
        assertTrue(menuManager.getAllMenuItems().size() == 5);
        menuManager.addMenuItem(menuItems.get(0));
        menuManager.addMenuItem(menuItems.get(1));
        assertTrue(menuManager.getAllMenuItems().size() == 7);
        assertTrue(menuManager.getAllMenuItems().contains(menuItems.get(0)));
        assertTrue(menuManager.getAllMenuItems().contains(menuItems.get(1)));
    }

    @Test
    public void testAddMenuItemNewItem() {
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        menuItems.add(new MenuItem(6, "newMenuItem6", 1, "a description", true));
        assertTrue(menuManager.getMenuItems().size() == 4);
        menuManager.addMenuItem(menuItems.get(0));
        assertTrue(menuManager.getMenuItems().contains(menuItems.get(0)));
        assertTrue(menuManager.getMenuItems().size() == 5);
    }

    @Test
    public void testAddMenuItemAlreadyExist() {
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        menuItems.add(new MenuItem(1, "testMenuItem1", 1.5, "", true));
        assertTrue(menuManager.getMenuItems().size() == 4);
        menuManager.addMenuItem(menuItems.get(0));
        assertTrue(menuManager.getMenuItems().size() == 4);
    }

    @Test
    public void testRemoveMenuItem() {
        MenuItem deleteItem = menuManager.getMenuItems().get(1);
        assertTrue(menuManager.getMenuItems().size() == 4);
        menuManager.removeMenuItem(deleteItem);
        assertFalse(menuManager.getMenuItems().contains(deleteItem));
        assertTrue(menuManager.getMenuItems().size() == 3);
    }

    @Test
    public void testRemoveMenuItemRemovesDailySpecial() {
        MenuItem deleteItem = menuManager.getMenuItems().get(1);
        List<MenuItem> menuItems = menuManager.getMenuItems();
        DailySpecial special = menuManager.createDailySpecial(menuItems,9.9);
        assertTrue(menuManager.getMenuItems().size() == 4);
        menuManager.removeMenuItem(deleteItem);
        assertFalse(menuManager.getMenuItems().contains(deleteItem));
        assertTrue(menuManager.getMenuItems().size() == 3);
        assertFalse(special.isActive());
    }

    /**
     * Test of getDailySpecials method, of class MenuManager.
     */
    @Test
    public void testGetDailySpecials() {
        System.out.println("getDailySpecials");
        MenuManager instance = MenuManager.getInstance();
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
        MenuManager instance = MenuManager.getInstance();
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
        MenuManager instance = MenuManager.getInstance();
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
        MenuManager instance = MenuManager.getInstance();
        instance.checkDailySpecials();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
