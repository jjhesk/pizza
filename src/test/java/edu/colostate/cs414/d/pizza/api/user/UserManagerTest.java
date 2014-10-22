/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.colostate.cs414.d.pizza.api.user;

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
public class UserManagerTest {
    
    public UserManagerTest() {
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
    public void testGetInstance() {
        System.out.println("getInstance");
        UserManager expResult = null;
        UserManager result = UserManager.getInstance();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        UserManager instance = new UserManager();
        List expResult = null;
        List result = instance.getUsers();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAuthenticateUser() {
        System.out.println("authenticateUser");
        String userName = "";
        String password = "";
        UserManager instance = new UserManager();
        User expResult = null;
        User result = instance.authenticateUser(userName, password);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAddUser() {
        System.out.println("addUser");
        String userName = "";
        String password = "";
        UserType userType = null;
        UserManager instance = new UserManager();
        boolean expResult = false;
        boolean result = instance.addUser(userName, password, userType);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRemoveUser() {
        System.out.println("removeUser");
        String userName = "";
        UserManager instance = new UserManager();
        instance.removeUser(userName);
        fail("The test case is a prototype.");
    }
}
