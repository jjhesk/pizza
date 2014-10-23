package edu.colostate.cs414.d.pizza.db;

import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
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

public class DailySpecialDatabaseControllerTest {
    
    private static DailySpecialDatabaseController dailySpecialDatabaseController;
    private static Connection connection;
    private static List<MenuItem> menuItems = new ArrayList<>();

    @BeforeClass
    public static void setUpClass() {
        Utility.removeDataFromDatabase();
        dailySpecialDatabaseController = new DailySpecialDatabaseController();
        connection = Database.getInstance().getConnection();
        menuItems.add(new MenuItem(1, "menuItem1", 1.00, "", true));
        menuItems.add(new MenuItem(2, "menuItem2", 3.00, "", true));
        menuItems.add(new MenuItem(3, "menuItem3", 2.00, "description", true));
        menuItems.add(new MenuItem(4, "menuItem4", 1.00, "", true));
        menuItems.add(new MenuItem(5, "menuItem5", 3.00, "", true));
        List<String> queries = Arrays.asList(
                "INSERT INTO MenuItem (menuItemID, name, price, description, status) VALUES (1,'menuItem1', 1.00, '', 'active');",
                "INSERT INTO MenuItem (menuItemID, name, price, description, status) VALUES (2,'menuItem2', 3.00, '', 'active');",
                "INSERT INTO MenuItem (menuItemID, name, price, description, status) VALUES (3,'menuItem3', 2.00, 'description', 'active');",
                "INSERT INTO MenuItem (menuItemID, name, price, description, status) VALUES (4,'menuItem4', 1.00, '', 'active');",
                "INSERT INTO MenuItem (menuItemID, name, price, description, status) VALUES (5,'menuItem5', 3.00, '', 'active');",
                "INSERT INTO DailySpecial(dailySpecialID,status,price) VALUES (1,'active',10.4);",
                "INSERT INTO DailySpecialItem (dailySpecialID, menuItemID) VALUES (1,1);",
                "INSERT INTO DailySpecialItem (dailySpecialID, menuItemID) VALUES (1,2);",
                "INSERT INTO DailySpecialItem (dailySpecialID, menuItemID) VALUES (1,3);"
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
        //Utility.removeDataFromDatabase();
    }

    @Test
    public void testGetDailySpecials() {
        List<MenuItem> tempMenuItems = new ArrayList<>();
        tempMenuItems.add(new MenuItem(1, "menuItem1", 1.00, "", true));
        tempMenuItems.add(new MenuItem(2, "menuItem2", 3.00, "", true));
        tempMenuItems.add(new MenuItem(3, "menuItem3", 2.00, "description", true));
        DailySpecial dailySpecial = new DailySpecial(10.4,tempMenuItems);

        List<DailySpecial> specials = new ArrayList<>();
        dailySpecialDatabaseController.getDailySpecials(specials, menuItems);
        assertTrue(dailySpecial.equals(specials.get(0)));
    }

    @Test
    public void testAddDailySpecial() {
        List<MenuItem> tempMenuItems = new ArrayList<>();
        tempMenuItems.add(new MenuItem(3, "menuItem3", 2.00, "description", true));
        tempMenuItems.add(new MenuItem(4, "menuItem4", 1.00, "", true));
        tempMenuItems.add(new MenuItem(5, "menuItem5", 3.00, "", true));
        DailySpecial dailySpecial = new DailySpecial(8.8,tempMenuItems);

        List<DailySpecial> specials = new ArrayList<>();
        dailySpecialDatabaseController.addDailySpecial(dailySpecial);
        dailySpecialDatabaseController.getDailySpecials(specials, menuItems);
        assertTrue(specials.contains(dailySpecial));
    }

    @Test
    public void testSetExpired() {
        List<MenuItem> tempMenuItems = new ArrayList<>();
        tempMenuItems.add(new MenuItem(5, "menuItem5", 3.00, "", true));
        DailySpecial dailySpecial = new DailySpecial(4.3,tempMenuItems);

        List<DailySpecial> specials = new ArrayList<>();
        dailySpecialDatabaseController.addDailySpecial(dailySpecial);
        dailySpecial.setActive(false);
        dailySpecialDatabaseController.setExpired(dailySpecial);
        dailySpecialDatabaseController.getDailySpecials(specials, menuItems);
        assertTrue(specials.contains(dailySpecial));
    }
}
