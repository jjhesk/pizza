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

    //Gets all orders
    public void getOrders(List<Order> orders, List<MenuItem> menuItems) {
        orders.clear(); //should always already be empty? yes but leave to put our minds at ease?
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
                String userName = resultSet.getString("userName");
                if (userName != null) {
                    order.setUserName(userName);
                }
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

    public void addOrder(Order order) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if(order.getUserName() == null){
            String query = "INSERT INTO UserOrder(customerAddress, customerName, status, type) Values(?,?,?,?)";
            try {
                preparedStatement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, order.getCustomerAddress());
                preparedStatement.setString(2, order.getCustomerName());
                preparedStatement.setString(3, order.getStatus().toString().toLowerCase());
                preparedStatement.setString(4, order.getType().toString().toLowerCase());
                preparedStatement.executeUpdate();
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    int id = resultSet.getInt(1);
                    order.setId(id);
                    for (OrderItem orderItem : order.getItems()){
                        this.addOrderItem(orderItem, id);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            String query = "INSERT INTO UserOrder(userName, customerAddress, customerName, status, type) Values(?, ?,?,?,?)";
            try {
                preparedStatement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, order.getUserName());
                preparedStatement.setString(2, order.getCustomerAddress());
                preparedStatement.setString(3, order.getCustomerName());
                preparedStatement.setString(4, order.getStatus().toString().toLowerCase());
                preparedStatement.setString(5, order.getType().toString().toLowerCase());
                preparedStatement.executeUpdate();
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    int id = resultSet.getInt(1);
                    order.setId(id);
                    for (OrderItem orderItem : order.getItems()){
                        this.addOrderItem(orderItem, id);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addOrderItem(OrderItem orderItem, int orderID) {
        String query = "INSERT INTO OrderItem(quantity,menuItemID,orderID) Values(?,?,?)";
        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, orderItem.getQuantity());
            preparedStatement.setInt(2, orderItem.getItem().getId());
            preparedStatement.setInt(3, orderID);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                orderItem.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void completeOrder(Order order) {
        String query = "UPDATE UserOrder SET status = 'complete' WHERE orderID = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
