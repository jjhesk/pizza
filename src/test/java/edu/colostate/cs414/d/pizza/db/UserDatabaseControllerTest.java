/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.colostate.cs414.d.pizza.db;

import edu.colostate.cs414.d.pizza.api.user.User;
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
public class UserDatabaseControllerTest {
    
    public UserDatabaseControllerTest() {
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
    public void testGetUsers() {
        System.out.println("getUsers");
        List<User> users = null;
        UserDatabaseController instance = new UserDatabaseController();
        instance.getUsers(users);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAddUser() {
        System.out.println("addUser");
        User user = null;
        UserDatabaseController instance = new UserDatabaseController();
        instance.addUser(user);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRemoveUser() {
        System.out.println("removeUser");
        String userName = "";
        UserDatabaseController instance = new UserDatabaseController();
        instance.removeUser(userName);
        fail("The test case is a prototype.");
    }
}
