package edu.colostate.cs414.d.pizza.utilities;

import edu.colostate.cs414.d.pizza.api.menu.MenuItem;

import java.util.List;

public class Utility {
    public static MenuItem getMenuItem(int menuItemID, List<MenuItem> menuItems) {
        for(MenuItem menuItem : menuItems) {
            if(menuItem.getId() == menuItemID) {
                return menuItem;
            }
        }
        return null;
    }
	
	public static double calculateTax(double subtotal) {
		// TODO!
		return -1;
	}
	
	public static double calculateTotal(double subtotal, double tax) {
		// TODO!
		return -1;
	}
	
}
