package edu.colostate.cs414.d.pizza.test.api.user;

import edu.colostate.cs414.d.pizza.api.user.User;
import edu.colostate.cs414.d.pizza.api.user.UserFactory;
import edu.colostate.cs414.d.pizza.api.user.UserManager;
import edu.colostate.cs414.d.pizza.api.user.UserType;
import java.util.List;

import edu.colostate.cs414.d.pizza.utilities.Utility;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserManagerTest {

    private UserManager userManager;
    private UserFactory userFactory;
    
    @Before
    public void setUp() {
        Utility.removeDataFromDatabase();
        userManager = new UserManager();
        userFactory = new UserFactory();
        userManager.enableTest();
        userManager.addUser("Michael", "Password1234",(UserType.CASHIER));
        userManager.addUser("Rawlin", "cookingaway",(UserType.CHEF));
        userManager.addUser("Tim", "Manager?",(UserType.MANAGER));
    }
    
    @After
    public void tearDown() {
        Utility.removeDataFromDatabase();
    }

    @Test
    public void testGetInstance() {
        UserManager expResult = UserManager.getInstance();
        UserManager result = UserManager.getInstance();
        assertEquals(expResult, result);
    }

    @Test
    public void testAuthenticateUserValid() {
        User user = userFactory.createUser(UserType.CASHIER, "Michael", "Password1234",0);
        User returnedUser = userManager.authenticateUser("Michael", "Password1234");
        assertEquals(user, returnedUser);
    }

    @Test
    public void testAuthenticateUserInvalidUserName() {
        User returnedUser = userManager.authenticateUser("Jeff", "Password1234");
        assertEquals(null, returnedUser);
    }

    @Test
    public void testAuthenticateUserInvalidPassword() {
        User returnedUser = userManager.authenticateUser("Rawlin", "ILOVEXBOXS");
        assertEquals(null, returnedUser);
    }

    @Test
    public void testAddUserValid() {
        User newUser = userFactory.createUser(UserType.CASHIER, "Jeff", "Iamfinallyhere",0);
        boolean added = userManager.addUser("Jeff","Iamfinallyhere",UserType.CASHIER);
        assertTrue(userManager.getUsers().contains(newUser) && added);
    }

    @Test
    public void testAddUserInValidUserName() {
        User newUser = userFactory.createUser(UserType.CASHIER, "Michael", "thesecondcomingofmichael",0);
        boolean added = userManager.addUser("Michael","thesecondcomingofmichael",UserType.CASHIER);
        assertFalse(userManager.getUsers().contains(newUser) || added);
    }

    @Test
    public void testRemoveUserExistingUser() {
        User user = userFactory.createUser(UserType.CASHIER, "Rawlin", "cookingaway",0);
        userManager.removeUser("Rawlin");
        assertFalse(userManager.getUsers().contains(user));
        assertTrue(userManager.getUsers().size() == 2);
    }

    @Test
    public void testRemoveUserNonExistentUser() {
        User user = userFactory.createUser(UserType.CASHIER, "Jeff", "javaftw",0);
        userManager.removeUser("Jeff");
        assertTrue(userManager.getUsers().size() == 3);
    }
}
