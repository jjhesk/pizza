/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.colostate.cs414.d.pizza.db;

import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import edu.colostate.cs414.d.pizza.api.order.Order;
import edu.colostate.cs414.d.pizza.api.order.OrderItem;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderDatabaseControllerTest {
    
    public OrderDatabaseControllerTest() {
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
    public void testGetOrders() {
        System.out.println("getOrders");
        List<Order> orders = null;
        List<MenuItem> menuItems = null;
        OrderDatabaseController instance = new OrderDatabaseController();
        instance.getOrders(orders, menuItems);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAddOrder() {
        System.out.println("addOrder");
        Order order = null;
        OrderDatabaseController instance = new OrderDatabaseController();
        instance.addOrder(order);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAddOrderItem() {
        System.out.println("addOrderItem");
        OrderItem orderItem = null;
        int orderID = 0;
        OrderDatabaseController instance = new OrderDatabaseController();
        instance.addOrderItem(orderItem, orderID);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCompleteOrder() {
        System.out.println("completeOrder");
        Order order = null;
        OrderDatabaseController instance = new OrderDatabaseController();
        instance.completeOrder(order);
        fail("The test case is a prototype.");
    }
}
