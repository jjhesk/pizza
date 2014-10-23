package edu.colostate.cs414.d.pizza.db;

import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import edu.colostate.cs414.d.pizza.api.order.Order;
import edu.colostate.cs414.d.pizza.api.order.OrderItem;
import edu.colostate.cs414.d.pizza.api.order.OrderStatus;
import edu.colostate.cs414.d.pizza.api.order.OrderType;
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

public class OrderDatabaseControllerTest {
    
    Connection orderDB = null;

    @Before
    public void setUp() {
        Utility.removeDataFromDatabase();
        orderDB = Database.getInstance().getConnection();
        List<String> queries = Arrays.asList(
                "INSERT INTO UserOrder (customerName, customerAddress, status, type) VALUES ('customer1', 'addr', 'pending', 'eatin');",
                "INSERT INTO MenuItem (name, price, description, status) VALUES ('menuItem1', 1.00, '', 'active');",
                "INSERT INTO OrderItem (quantity, menuItemID, orderID) VALUES (1, (SELECT menuItemID FROM MenuItem WHERE name='menuItem1'), (SELECT orderID FROM UserOrder WHERE customerName='customer1'));"
                );
        try {
            for (String query : queries) {
                PreparedStatement preparedStatement = null;
                preparedStatement = orderDB.prepareStatement(query);
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
    public void testGetOrders() {
        List<Order> orders = new ArrayList<Order>();
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        MenuItem menuItem1 = new MenuItem(1, "menuItem1", 1.00, "", true);
        menuItems.add(menuItem1);
        OrderDatabaseController instance = new OrderDatabaseController();
        instance.getOrders(orders, menuItems);
        Order expOrder = new Order(1, OrderType.EATIN, "customer1", "addr");
        expOrder.setStatus(OrderStatus.PENDING);
        OrderItem orderItem = new OrderItem(1, menuItem1, 1);
        expOrder.addItem(orderItem);
        assertEquals(expOrder, orders.get(0));
    }

    @Test
    public void testAddOrder() {
        List<Order> orders = new ArrayList<Order>();
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        MenuItem menuItem1 = new MenuItem(1, "menuItem1", 1.00, "", true);
        menuItems.add(menuItem1);
        OrderDatabaseController instance = new OrderDatabaseController();
        Order expAddedOrder = new Order(2, OrderType.EATIN, "customer2", "addr");
        expAddedOrder.setStatus(OrderStatus.PENDING);
        OrderItem orderItem = new OrderItem(2, menuItem1, 1);
        expAddedOrder.addItem(orderItem);
        instance.addOrder(expAddedOrder);
        instance.getOrders(orders, menuItems);
        assertEquals(expAddedOrder, orders.get(1));
    }

    @Test
    public void testAddOrderItem() {
        List<Order> orders = new ArrayList<Order>();
        MenuItem menuItem1 = new MenuItem(1, "menuItem1", 1.00, "", true);
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        menuItems.add(menuItem1);
        OrderItem orderItem = new OrderItem(2, menuItem1, 1);
        OrderDatabaseController instance = new OrderDatabaseController();
        instance.addOrderItem(orderItem, 1);
        instance.getOrders(orders, menuItems);
        assertEquals(orderItem, orders.get(0).getItems().get(1));
    }

    @Test
    public void testCompleteOrder() {
        List<Order> orders = new ArrayList<Order>();
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        MenuItem menuItem1 = new MenuItem(1, "menuItem1", 1.00, "", true);
        menuItems.add(menuItem1);
        OrderDatabaseController instance = new OrderDatabaseController();
        instance.getOrders(orders, menuItems);
        Order expOrder = new Order(1, OrderType.EATIN, "customer1", "addr");
        expOrder.setStatus(OrderStatus.COMPLETE);
        OrderItem orderItem = new OrderItem(1, menuItem1, 1);
        expOrder.addItem(orderItem);
        instance.completeOrder(orders.get(0));
        instance.getOrders(orders, menuItems);
        assertEquals(expOrder, orders.get(0));
    }
}
