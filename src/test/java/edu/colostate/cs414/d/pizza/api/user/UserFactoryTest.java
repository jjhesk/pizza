package edu.colostate.cs414.d.pizza.api.user;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserFactoryTest {

    @Test
    public void testCreateUserManager() {
        UserType userType = UserType.MANAGER;
        User user = UserFactory.createUser(userType, "Rawlin", "passwordsaredumb");
        assertTrue(user instanceof Manager);
    }

    @Test
    public void testCreateUserCustomer() {
        UserType userType = UserType.CUSTOMER;
        User user = UserFactory.createUser(userType, "Michael", "IWANNAEAT");
        assertTrue(user instanceof Customer);
    }

    @Test
    public void testCreateUserCashier() {
        UserType userType = UserType.CASHIER;
        User user = UserFactory.createUser(userType, "Jeff", "chaching");
        assertTrue(user instanceof Cashier);
    }

    @Test
    public void testCreateUserChef() {
        UserType userType = UserType.CHEF;
        User user = UserFactory.createUser(userType, "Tim", "Cookofftime");
        assertTrue(user instanceof Chef);
    }
    
}
