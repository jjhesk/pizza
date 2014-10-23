/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.colostate.cs414.d.pizza.db;

import edu.colostate.cs414.d.pizza.api.menu.MenuItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.colostate.cs414.d.pizza.utilities.Utility;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MenuDatabaseControllerTest {

    public static MenuDatabaseController menuDatabaseController;
    public static Connection connection;

    @BeforeClass
    public static void setUpClass() {
        Utility.removeDataFromDatabase();
        menuDatabaseController = new MenuDatabaseController();
        connection = Database.getInstance().getConnection();
        List<String> queries = Arrays.asList(
                "INSERT INTO MenuItem (name, price, description, status) VALUES ('menuItem1', 1.00, '', 'active');",
                "INSERT INTO MenuItem (name, price, description, status) VALUES ('menuItem2', 3.00, '', 'expired');",
                "INSERT INTO MenuItem (name, price, description, status) VALUES ('menuItem3', 2.00, 'description', 'active');",
                "INSERT INTO MenuItem (name, price, description, status) VALUES ('menuItem4', 1.00, '', 'active');"
                );
        try {
            for (String query : queries) {
                PreparedStatement preparedStatement = null;
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        Utility.removeDataFromDatabase();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetMenu() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(1, "menuItem1", 1.00, "", true));
        menuItems.add(new MenuItem(2, "menuItem2", 3.00, "", false));
        menuItems.add(new MenuItem(3, "menuItem3", 2.00, "description", true));
        menuItems.add(new MenuItem(4, "menuItem4", 1.00, "", true));

        List<MenuItem> returnedMenuList = new ArrayList<>();
        menuDatabaseController.getMenu(returnedMenuList);

        for(MenuItem menuItem : menuItems) {
            assertTrue(returnedMenuList.contains(menuItem));
        }
    }

    @Test
    public void testSetExpired() {
        MenuItem menuItem = new MenuItem(5,"menuItem5",6.00,"",true);
        menuDatabaseController.addMenuItem(menuItem);
        menuDatabaseController.setExpired(menuItem);
        MenuItem resultItem = new MenuItem(5,"menuItem5",6.00,"",false);
        List<MenuItem> returnedMenuList = new ArrayList<>();
        menuDatabaseController.getMenu(returnedMenuList);
        assertTrue(returnedMenuList.contains(resultItem));
        assertFalse(returnedMenuList.contains(menuItem));
    }

    @Test
    public void testAddMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(6,"menuItem6", 1.00, "", true));
        menuItems.add(new MenuItem(7,"menuItem7", 3.00, "", false));
        menuDatabaseController.addMenuItems(menuItems);
        List<MenuItem> returnedMenuList = new ArrayList<>();
        menuDatabaseController.getMenu(returnedMenuList);
        assertTrue(returnedMenuList.contains(menuItems.get(0)));
        assertTrue(returnedMenuList.contains(menuItems.get(1)));
    }

    @Test
    public void testAddMenuItem() {
        MenuItem menuItem = new MenuItem(8,"menuItem8",5.50,"",true);
        menuDatabaseController.addMenuItem(menuItem);
        List<MenuItem> returnedMenuList = new ArrayList<>();
        menuDatabaseController.getMenu(returnedMenuList);
        assertTrue(returnedMenuList.contains(menuItem));
    }
}
