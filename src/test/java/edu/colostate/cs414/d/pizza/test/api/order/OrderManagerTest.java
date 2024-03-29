package edu.colostate.cs414.d.pizza.test.api.order;

import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import edu.colostate.cs414.d.pizza.api.order.Order;
import edu.colostate.cs414.d.pizza.api.order.OrderManager;
import edu.colostate.cs414.d.pizza.api.order.OrderStatus;
import edu.colostate.cs414.d.pizza.api.order.OrderType;
import edu.colostate.cs414.d.pizza.utilities.Utility;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderManagerTest {
    
    private List<MenuItem> menuItems;
    private OrderManager orderManager;

    @Before
    public void setUp() {
        Utility.removeDataFromDatabase();
        menuItems = new ArrayList<MenuItem>();
        menuItems.add(new MenuItem(0, "testMenuItem0", 1, "a description", true));
        menuItems.add(new MenuItem(1, "testMenuItem1", 1, "a description", true));
        menuItems.add(new MenuItem(2, "testMenuItem2", 1, "a description", true));
        menuItems.add(new MenuItem(3, "testMenuItem3", 1, "a description", true));
        menuItems.add(new MenuItem(4, "testMenuItem4", 1, "a description", true));
        orderManager = new OrderManager(menuItems);
        orderManager.enableTest();
    }
    
    @After
    public void tearDown() {
        orderManager.getPendingOrders().clear();
        Utility.removeDataFromDatabase();
    }

    @Test
    public void testGetInstance() {
        OrderManager orderManager1 = OrderManager.getInstance(null);
        OrderManager orderManager2 = OrderManager.getInstance(null);
        assertSame(orderManager1, orderManager2);
    }

    @Test
    public void testGetPendingOrders() {
        Order o0 = new Order(0, OrderType.EATIN, "customer0", "customer0 address");
        o0.addItem(menuItems.get(0), 1);
        o0.addItem(menuItems.get(1), 1);
        o0.addItem(menuItems.get(2), 1);
        o0.setStatus(OrderStatus.PENDING);
        assertEquals(0, orderManager.getPendingOrders().size());
        orderManager.addOrder(o0);
        assertEquals(1, orderManager.getPendingOrders().size());
        assertSame(o0, orderManager.getPendingOrders().get(0));
    }

    @Test
    public void testCalculateSubtotal() {
        Order o0 = new Order(0, OrderType.EATIN, "customer0", "customer0 address");
        o0.addItem(menuItems.get(0), 1);
        o0.addItem(menuItems.get(1), 1);
        o0.addItem(menuItems.get(2), 1);
        List<DailySpecial> dailySpecials = new ArrayList<DailySpecial>();
        List<DailySpecial> currentDailySpecials = new ArrayList<DailySpecial>();
        double expResult = 3.0;
        double result = orderManager.calculateSubtotal(o0, dailySpecials, currentDailySpecials);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testCalculateSubtotalWithDailySpecial() {
        Order o0 = new Order(0, OrderType.EATIN, "customer0", "customer0 address");
        o0.addItem(menuItems.get(0), 1);
        o0.addItem(menuItems.get(1), 1);
        o0.addItem(menuItems.get(2), 1);
        List<DailySpecial> dailySpecials = new ArrayList<DailySpecial>();
        List<DailySpecial> currentDailySpecials = new ArrayList<DailySpecial>();
        // add a daily special
        List<MenuItem> selectedDailySpecialItems = new ArrayList<MenuItem>();
        selectedDailySpecialItems.add(menuItems.get(0));
        selectedDailySpecialItems.add(menuItems.get(1));
        selectedDailySpecialItems.add(menuItems.get(2));
        dailySpecials.add(new DailySpecial(2.0, selectedDailySpecialItems));
        currentDailySpecials.addAll(dailySpecials);
        double expResult = 2.0;
        double result = orderManager.calculateSubtotal(o0, dailySpecials, currentDailySpecials);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testCompleteOrder() {
        Order o0 = new Order(0, OrderType.EATIN, "customer0", "customer0 address");
        o0.setStatus(OrderStatus.NEW);
        assertFalse(o0.getStatus().equals(OrderStatus.COMPLETE));
        orderManager.completeOrder(o0);
        assertEquals(OrderStatus.COMPLETE, o0.getStatus());
    }
}
