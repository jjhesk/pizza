/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.colostate.cs414.d.pizza.api.order;

import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
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
public class OrderManagerTest {
    
    public OrderManagerTest() {
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
        List<MenuItem> menuItems = null;
        OrderManager expResult = null;
        OrderManager result = OrderManager.getInstance(menuItems);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreateOrder_3args() {
        System.out.println("createOrder");
        OrderType type = null;
        String name = "";
        String address = "";
        OrderManager instance = null;
        Order expResult = null;
        Order result = instance.createOrder(type, name, address);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreateOrder_0args() {
        System.out.println("createOrder");
        OrderManager instance = null;
        Order expResult = null;
        Order result = instance.createOrder();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetOrders() {
        System.out.println("getOrders");
        OrderManager instance = null;
        List expResult = null;
        List result = instance.getOrders();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetOrder() {
        System.out.println("getOrder");
        int id = 0;
        OrderManager instance = null;
        Order expResult = null;
        Order result = instance.getOrder(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAddOrder() {
        System.out.println("addOrder");
        Order order = null;
        OrderManager instance = null;
        instance.addOrder(order);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRemoveOrder() {
        System.out.println("removeOrder");
        Order order = null;
        OrderManager instance = null;
        instance.removeOrder(order);
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateOrder() {
        System.out.println("updateOrder");
        Order order = null;
        OrderManager instance = null;
        instance.updateOrder(order);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetOrderItem() {
        System.out.println("getOrderItem");
        int id = 0;
        OrderManager instance = null;
        OrderItem expResult = null;
        OrderItem result = instance.getOrderItem(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetOrderItems() {
        System.out.println("getOrderItems");
        Order order = null;
        OrderManager instance = null;
        List expResult = null;
        List result = instance.getOrderItems(order);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAddOrderItem() {
        System.out.println("addOrderItem");
        OrderItem item = null;
        OrderManager instance = null;
        instance.addOrderItem(item);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRemoveOrderItem() {
        System.out.println("removeOrderItem");
        OrderItem item = null;
        OrderManager instance = null;
        instance.removeOrderItem(item);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCalculateSubtotal() {
        System.out.println("calculateSubtotal");
        Order order = null;
        List<DailySpecial> dailySpecials = null;
        List<DailySpecial> currentDailySpecials = null;
        OrderManager instance = null;
        double expResult = 0.0;
        double result = instance.calculateSubtotal(order, dailySpecials, currentDailySpecials);
        assertEquals(expResult, result, 0.0);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreateOrderItem() {
        System.out.println("createOrderItem");
        MenuItem menuItem = null;
        int quantity = 0;
        OrderManager instance = null;
        OrderItem expResult = null;
        OrderItem result = instance.createOrderItem(menuItem, quantity);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreateDailySpecialOrderItems() {
        System.out.println("createDailySpecialOrderItems");
        DailySpecial special = null;
        OrderManager instance = null;
        List expResult = null;
        List result = instance.createDailySpecialOrderItems(special);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCompleteOrder() {
        System.out.println("completeOrder");
        Order order = null;
        OrderManager instance = null;
        instance.completeOrder(order);
        fail("The test case is a prototype.");
    }
}
