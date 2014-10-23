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
    private List<MenuItem> menuItems;

    private boolean testing = false;

    public OrderManager(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
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

    public double calculateSubtotal(Order order, List<DailySpecial> dailySpecials, List<DailySpecial> currentDailySpecials) {
        double totalCost = 0.0;
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        for(OrderItem orderItem : order.getItems()){
            for(int i = 0; i < orderItem.getQuantity(); i++){
                menuItems.add(new MenuItem(orderItem.getItem()));
            }
        }

        for(DailySpecial dailySpecial : dailySpecials) {
            if(Utility.tryApplyDailySpecial(dailySpecial,menuItems)){
                totalCost += dailySpecial.getPrice();
            }
        }

        for(DailySpecial dailySpecial : currentDailySpecials) {
            if(Utility.tryApplyDailySpecial(dailySpecial,menuItems)){
                totalCost += dailySpecial.getPrice();
            }
        }

        for(MenuItem menuItem : menuItems) {
            totalCost += menuItem.getPrice();
        }

        return totalCost;
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
}
