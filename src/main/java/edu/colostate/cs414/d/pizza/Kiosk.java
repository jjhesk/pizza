package edu.colostate.cs414.d.pizza;

import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import edu.colostate.cs414.d.pizza.api.menu.MenuManager;
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
    //View Order

    //View Daily Specials

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

    //Creating Daily Special

    //Creating an order

    //Making a payment

    //Completing Order

}
