package edu.colostate.cs414.d.pizza.db;

import edu.colostate.cs414.d.pizza.api.order.Order;
import edu.colostate.cs414.d.pizza.api.order.OrderItem;
import edu.colostate.cs414.d.pizza.api.order.OrderType;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author tim
 */
public class OrderManager {
	
	private static OrderManager instance;
	
	private Connection connection;

	public OrderManager() {
		connection = Database.getInstance().getConnection();
	}
	
	public static OrderManager getInstance() {
		if (instance == null) {
			instance = new OrderManager();
		}
		
		return instance;
	}

	public Order createOrder(OrderType type, String name, String address) {
		return new Order(type, name, address);
	}
	
	public List<Order> getOrders() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public Order getOrder(int id) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public void addOrder(Order order) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public void removeOrder(Order order) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public void updateOrder(Order order) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public OrderItem getOrderItem(int id) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public List<OrderItem> getOrderItems(Order order) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public void addOrderItem(OrderItem item) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public void removeOrderItem(OrderItem item) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
}
