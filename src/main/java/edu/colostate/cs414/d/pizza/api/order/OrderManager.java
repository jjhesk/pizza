package edu.colostate.cs414.d.pizza.api.order;

import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import edu.colostate.cs414.d.pizza.utilities.Utility;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {

	private static OrderManager instance;

    private List<MenuItem> menuItems;

	public OrderManager(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
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
}
