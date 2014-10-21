package edu.colostate.cs414.d.pizza.db;

import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import edu.colostate.cs414.d.pizza.api.order.Order;
import edu.colostate.cs414.d.pizza.api.order.OrderItem;
import edu.colostate.cs414.d.pizza.api.order.OrderStatus;
import edu.colostate.cs414.d.pizza.api.order.OrderType;
import edu.colostate.cs414.d.pizza.utilities.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDatabaseController {
    private Connection connection;

    public OrderDatabaseController(){
        connection = Database.getInstance().getConnection();
    }

    //should this get ALL orders, including cancelled/complete?
    public void getOrders(List<Order> orders, List<MenuItem> menuItems) {
        orders.clear(); //should always already be empty?
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM UserOrder");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int orderID = resultSet.getInt("orderID");
                String address = resultSet.getString("customerAddress");
                OrderStatus status = OrderStatus.valueOf(resultSet.getString("status").toUpperCase());
                OrderType type = OrderType.valueOf(resultSet.getString("type").toUpperCase());
                String name = resultSet.getString("customerName");
                Order order = new Order(type, name, address);
                order.setId(orderID);
                order.setStatus(status);
                //get all items on order and add them
                PreparedStatement orderItemsQuery = 
                        connection.prepareStatement("SELECT * FROM OrderItem WHERE orderID = ?");
                orderItemsQuery.setInt(1, orderID);
                ResultSet orderItems = orderItemsQuery.executeQuery();
                while (orderItems.next()) {
                    int orderItemID = orderItems.getInt("orderItemID");
                    int quantity = orderItems.getInt("quantity");
                    int menuItemID = orderItems.getInt("menuItemID");
                    MenuItem menuItem = Utility.getMenuItem(menuItemID, menuItems);
                    OrderItem orderItem = new OrderItem(orderItemID, menuItem, quantity);
                    order.addItem(orderItem);
                }
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
