/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.colostate.cs414.d.pizza.db;

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
public class MenuDatabaseControllerTest {
    
    public MenuDatabaseControllerTest() {
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
    public void testGetMenu() {
        System.out.println("getMenu");
        List<MenuItem> menuItems = null;
        MenuDatabaseController instance = new MenuDatabaseController();
        instance.getMenu(menuItems);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetExpired() {
        System.out.println("setExpired");
        MenuItem menuItem = null;
        MenuDatabaseController instance = new MenuDatabaseController();
        instance.setExpired(menuItem);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAddMenuItems() {
        System.out.println("addMenuItems");
        List<MenuItem> menuItems = null;
        MenuDatabaseController instance = new MenuDatabaseController();
        instance.addMenuItems(menuItems);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAddMenuItem() {
        System.out.println("addMenuItem");
        MenuItem menuItem = null;
        MenuDatabaseController instance = new MenuDatabaseController();
        instance.addMenuItem(menuItem);
        fail("The test case is a prototype.");
    }

    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        MenuDatabaseController.main(args);
        fail("The test case is a prototype.");
    }
}
