package edu.colostate.cs414.d.pizza.api.order;

import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import edu.colostate.cs414.d.pizza.utilities.Utility;
import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.db.OrderDatabaseController;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {

    private static OrderManager instance;
    private OrderDatabaseController orderDatabase;
    private List<Order> orders;

    private boolean testing = false;

    public OrderManager(List<MenuItem> menuItems) {
        orderDatabase = new OrderDatabaseController();
        orders = new ArrayList<Order>();
        orderDatabase.getOrders(orders, menuItems);
    }

    public static OrderManager getInstance(List<MenuItem> menuItems) {
        if (instance == null) {
            instance = new OrderManager(menuItems);
        }
        return instance;
    }

    public Order createOrder(OrderType type, String name, String address) {
        return new Order(type, name, address);
    }

    public Order createOrder() {
        return new Order();
    }
	
	public List<Order> getPendingOrders() {
            List<Order> pendingOrders = new ArrayList<Order>();
            for (Order order : this.orders) {
                if (order.getStatus() == OrderStatus.PENDING) {
                    pendingOrders.add(order);
                }
            }
            return pendingOrders;
	}
	
	public void addOrder(Order order) {
            if(!testing) orderDatabase.addOrder(order);
            this.orders.add(order);
	}
	
	public List<OrderItem> getOrderItems(Order order) {
		return order.getItems();
	}

    public double calculateSubtotal(Order order, List<DailySpecial> orderedDailySpecials, List<DailySpecial> currentDailySpecials) {
        return order.calculateSubtotal(orderedDailySpecials, currentDailySpecials);
    }

    public OrderItem createOrderItem(MenuItem menuItem, int quantity) {
        return new OrderItem(menuItem,quantity);
    }

    public List<OrderItem> createDailySpecialOrderItems(DailySpecial special) {
        ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
        for (MenuItem menuItem : special.getItems()){
            orderItems.add(this.createOrderItem(menuItem,1));
        }
        return orderItems;
    }

    public void completeOrder(Order order) {
        if(!testing) orderDatabase.completeOrder(order);
        order.setStatus(OrderStatus.COMPLETE);
    }

    public void enableTest(){ this.testing = true; }

    public List<Order> getDeliveredOrders() {
        List<Order> deliveredOrders = new ArrayList<Order>();
        for (Order order : this.orders) {
            if (order.getStatus() == OrderStatus.COMPLETE && order.getType() == OrderType.DELIVERY) {
                deliveredOrders.add(order);
            }
        }
        return deliveredOrders;
    }

    public Order getOrder(int id) {
        for(Order order : this.orders){
            if(order.getId() == id){
                return order;
            }
        }
        return null;
    }
}
