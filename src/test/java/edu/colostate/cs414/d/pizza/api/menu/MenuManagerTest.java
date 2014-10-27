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

    private static MenuManager menuManager;
    
    @Before
    public void setUp() {
        menuManager = new MenuManager();
        menuManager.enableTest();
        menuManager.getAllMenuItems().clear();
        menuManager.getCurrentDailySpecials().clear();
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
    public void testClearMenu() {
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        menuItems = menuManager.getActiveMenuItems();
        menuManager.clearMenu();
        assertTrue(menuManager.getActiveMenuItems().size() == 0);
        assertFalse(menuItems.get(0).isActive());
        assertFalse(menuItems.get(1).isActive());
        assertFalse(menuItems.get(2).isActive());
    }

    @Test
    public void testGetActiveMenuItems() {
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        menuItems.add(new MenuItem(6, "newMenuItem6", 1, "a description", true));
        menuItems.add(new MenuItem(7, "newMenuItem7", 1, "a description", false));
        assertTrue(menuManager.getActiveMenuItems().size() == 4);
        menuManager.addMenuItem(menuItems.get(0));
        menuManager.addMenuItem(menuItems.get(1));
        assertTrue(menuManager.getActiveMenuItems().size() == 5);
        assertTrue(menuManager.getActiveMenuItems().contains(menuItems.get(0)));
        assertFalse(menuManager.getActiveMenuItems().contains(menuItems.get(1)));
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
        assertTrue(menuManager.getActiveMenuItems().size() == 4);
        menuManager.addMenuItem(menuItems.get(0));
        assertTrue(menuManager.getActiveMenuItems().contains(menuItems.get(0)));
        assertTrue(menuManager.getActiveMenuItems().size() == 5);
    }

    @Test
    public void testAddMenuItemAlreadyExist() {
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        menuItems.add(new MenuItem(1, "testMenuItem1", 1.5, "", true));
        assertTrue(menuManager.getActiveMenuItems().size() == 4);
        menuManager.addMenuItem(menuItems.get(0));
        assertTrue(menuManager.getActiveMenuItems().size() == 4);
    }

    @Test
    public void testRemoveMenuItem() {
        MenuItem deleteItem = menuManager.getActiveMenuItems().get(1);
        assertTrue(menuManager.getActiveMenuItems().size() == 4);
        menuManager.removeMenuItem(deleteItem);
        assertFalse(menuManager.getActiveMenuItems().contains(deleteItem));
        assertTrue(menuManager.getActiveMenuItems().size() == 3);
    }

    @Test
    public void testRemoveMenuItemRemovesDailySpecial() {
        MenuItem deleteItem = menuManager.getActiveMenuItems().get(1);
        List<MenuItem> menuItems = menuManager.getActiveMenuItems();
        DailySpecial special = menuManager.createDailySpecial(menuItems,9.9);
        assertTrue(menuManager.getActiveMenuItems().size() == 4);
        menuManager.removeMenuItem(deleteItem);
        assertFalse(menuManager.getActiveMenuItems().contains(deleteItem));
        assertTrue(menuManager.getActiveMenuItems().size() == 3);
        assertFalse(special.isActive());
    }

    @Test
    public void testGetDailySpecials() {
        List<MenuItem> menuItems = menuManager.getActiveMenuItems();
        DailySpecial special = menuManager.createDailySpecial(menuItems,9.9);
        menuItems.remove(2);
        DailySpecial specialDelete = menuManager.createDailySpecial(menuItems,8.4);
        assertTrue(menuManager.getCurrentDailySpecials().size() == 2);
        menuManager.removeDailySpecial(specialDelete);
        assertTrue(menuManager.getCurrentDailySpecials().size() == 1);
    }

    @Test
    public void testCreateDailySpecial() {
        List<MenuItem> menuItems = menuManager.getActiveMenuItems();
        DailySpecial special = menuManager.createDailySpecial(menuItems,9.9);
        assertTrue(menuItems.size() == special.getItems().size());
        assertTrue(special.isActive());
        assertTrue(special.getPrice() == 9.9);
    }

    @Test
    public void testRemoveDailySpecial() {
        List<MenuItem> menuItems = menuManager.getActiveMenuItems();
        DailySpecial special = menuManager.createDailySpecial(menuItems,9.9);
        assertTrue(menuManager.getCurrentDailySpecials().size() == 1);
        menuManager.removeDailySpecial(special);
        assertTrue(menuManager.getCurrentDailySpecials().size() == 0);
        assertFalse(special.isActive());
    }

    @Test
    public void testCheckDailySpecialsStillValid() {
        List<MenuItem> menuItems = menuManager.getActiveMenuItems();
        DailySpecial specialDelete = menuManager.createDailySpecial(menuItems,9.9);
        assertTrue(menuManager.getCurrentDailySpecials().size() == 1);
        menuManager.checkDailySpecials();
        assertTrue(specialDelete.isActive());
        assertTrue(menuManager.getCurrentDailySpecials().size() == 1);
    }

    @Test
    public void testCheckDailySpecialsNotValid() {
        List<MenuItem> menuItems = menuManager.getActiveMenuItems();
        DailySpecial specialDelete = menuManager.createDailySpecial(menuItems,9.9);
        assertTrue(menuManager.getCurrentDailySpecials().size() == 1);
        menuItems.get(2).setActive(false);
        menuManager.checkDailySpecials();
        assertFalse(specialDelete.isActive());
        assertTrue(menuManager.getCurrentDailySpecials().size() == 0);
    }
}
