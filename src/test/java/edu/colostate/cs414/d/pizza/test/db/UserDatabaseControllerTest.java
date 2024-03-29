package edu.colostate.cs414.d.pizza.test.db;

import edu.colostate.cs414.d.pizza.api.user.Cashier;
import edu.colostate.cs414.d.pizza.api.user.Chef;
import edu.colostate.cs414.d.pizza.api.user.Customer;
import edu.colostate.cs414.d.pizza.api.user.Manager;
import edu.colostate.cs414.d.pizza.api.user.User;
import edu.colostate.cs414.d.pizza.api.user.UserType;
import edu.colostate.cs414.d.pizza.db.Database;
import edu.colostate.cs414.d.pizza.db.UserDatabaseController;
import edu.colostate.cs414.d.pizza.utilities.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserDatabaseControllerTest {
    
    Connection userDB = null;

    @Before
    public void setUp() {
        Utility.removeDataFromDatabase();
        userDB = Database.getInstance().getConnection();
        List<String> queries = Arrays.asList(
                "INSERT INTO User (userName, password, userType,rewardpoints) VALUES ('cashier', 'pw', 'cashier',0);",
                "INSERT INTO User (userName, password, userType,rewardpoints) VALUES ('chef', 'pw', 'chef',0);",
                "INSERT INTO User (userName, password, userType,rewardpoints) VALUES ('customer', 'pw', 'customer',0);",
                "INSERT INTO User (userName, password, userType,rewardpoints) VALUES ('manager', 'pw', 'manager',0);"
                );
        try {
            for (String query : queries) {
                PreparedStatement preparedStatement = null;
                preparedStatement = userDB.prepareStatement(query);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @After
    public void tearDown() {
        Utility.removeDataFromDatabase();
    }

    @Test
    public void testGetUsers() {
        List<User> expectedUsers = new ArrayList<User>();
        expectedUsers.add(new Cashier("cashier", "pw", UserType.CASHIER));
        expectedUsers.add(new Chef("chef", "pw", UserType.CHEF));
        expectedUsers.add(new Customer("customer", "pw", UserType.CUSTOMER,0));
        expectedUsers.add(new Manager("manager", "pw", UserType.MANAGER));
        List<User> actualUsers = new ArrayList<User>();
        UserDatabaseController instance = new UserDatabaseController();
        instance.getUsers(actualUsers);
        for (User u: expectedUsers) {
            assertTrue(actualUsers.contains(u));
        }
    }

    @Test
    public void testAddUser() {
        List<User> expectedUsers = new ArrayList<User>();
        User user = new Manager("manager2", "pw", UserType.MANAGER);
        UserDatabaseController instance = new UserDatabaseController();
        instance.addUser(user);
        instance.getUsers(expectedUsers);
        assertTrue(expectedUsers.contains(user));
    }

    @Test
    public void testRemoveUser() {
        List<User> expectedUsers = new ArrayList<User>();
        String userName = "manager";
        UserDatabaseController instance = new UserDatabaseController();
        instance.removeUser(userName);
        instance.getUsers(expectedUsers);
        assertFalse(expectedUsers.contains(new Manager("manager", "pw", UserType.MANAGER)));
    }
}
