package edu.colostate.cs414.d.pizza;

import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import edu.colostate.cs414.d.pizza.api.menu.MenuManager;
import edu.colostate.cs414.d.pizza.api.order.Order;
import edu.colostate.cs414.d.pizza.api.order.OrderManager;
import java.util.List;

/**
 *
 * @author tim
 */
public class Kiosk {

    private MenuManager menuManager;
    private OrderManager orderManager;

    public Kiosk(){
        menuManager = MenuManager.getInstance();
        orderManager = OrderManager.getInstance();
    }

    //View Menu
    public List<MenuItem> viewMenu(){
        return menuManager.getMenuItems();
    }

    //View Orders
	public List<Order> viewPendingOrders() {
		// TODO: DB operation for this
		throw new UnsupportedOperationException("Not implemented yet");
	}

    //View Daily Specials
    public List<DailySpecial> viewDailySpecials(){
        return menuManager.getDailySpecials();
    }

    //Creating and modifying the Menu
    public MenuItem createMenuItem(String name, double price, String description){
        return menuManager.createMenuItem(name, price, description);
    }

    public void saveMenu(List<MenuItem> menuItems) {
        menuManager.saveMenu(menuItems);
    }

    public void addMenuItem(MenuItem menuItem){
        menuManager.addMenuItem(menuItem);
    }

    public void removeMenuItem(MenuItem menuItem){
        menuManager.removeMenuItem(menuItem);
    }

    //Creating/Editing Daily Special
    public DailySpecial createDailySpecial(List<MenuItem> menuItems, Double price){
        return menuManager.createDailySpecial(menuItems, price);
    }

    public void removeDailySpecial(DailySpecial special){
        menuManager.removeDailySpecial(special);
    }

    //Creating an order
	public void placeOrder(Order order) {
		
	}
	
    //Making a payment
	public double calculateSubtotal(Order order) {
		// TODO!
		return -1;
	}

    //Completing Order
	public void completeOrder(Order order) {
		
	}

}
