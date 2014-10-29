package edu.colostate.cs414.d.pizza.test.api.user;

import edu.colostate.cs414.d.pizza.api.user.Cashier;
import edu.colostate.cs414.d.pizza.api.user.Chef;
import edu.colostate.cs414.d.pizza.api.user.Customer;
import edu.colostate.cs414.d.pizza.api.user.Manager;
import edu.colostate.cs414.d.pizza.api.user.User;
import edu.colostate.cs414.d.pizza.api.user.UserFactory;
import edu.colostate.cs414.d.pizza.api.user.UserType;
import edu.colostate.cs414.d.pizza.utilities.Utility;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserFactoryTest {

    private UserFactory userFactory;

    @Before
    public void setUp() {
        userFactory = new UserFactory();
    }

    @Test
    public void testCreateUserManager() {
        UserType userType = UserType.MANAGER;
        User user = userFactory.createUser(userType, "Rawlin", "passwordsaredumb");
        assertTrue(user instanceof Manager);
    }

    @Test
    public void testCreateUserCustomer() {
        UserType userType = UserType.CUSTOMER;
        User user = userFactory.createUser(userType, "Michael", "IWANNAEAT");
        assertTrue(user instanceof Customer);
    }

    @Test
    public void testCreateUserCashier() {
        UserType userType = UserType.CASHIER;
        User user = userFactory.createUser(userType, "Jeff", "chaching");
        assertTrue(user instanceof Cashier);
    }

    @Test
    public void testCreateUserChef() {
        UserType userType = UserType.CHEF;
        User user = userFactory.createUser(userType, "Tim", "Cookofftime");
        assertTrue(user instanceof Chef);
    }
    
}
