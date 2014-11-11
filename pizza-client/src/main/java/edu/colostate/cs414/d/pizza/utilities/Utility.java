package edu.colostate.cs414.d.pizza.utilities;

import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import java.util.ArrayList;
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

    public static boolean tryApplyDailySpecial(DailySpecial dailySpecial, List<MenuItem> menuItems){
        boolean apply = false;
        List<MenuItem> tempMenuItems = new ArrayList<>();
        for(MenuItem menuItem : menuItems){
            tempMenuItems.add(new MenuItem(menuItem));
        }

        for(MenuItem menuItem : dailySpecial.getItems()){
            if(tempMenuItems.contains(menuItem)){
                tempMenuItems.remove(menuItem);
            }
            else{
                return false;
            }
        }

        for (MenuItem menuItem : dailySpecial.getItems()) {
            menuItems.remove(menuItem);
        }

        return true;
    }

	public static double calculateTax(double subtotal) {
            //can be set to anything
            double tax = .07;
            return subtotal*tax;
	}
	
	public static double calculateTotalWithTax(double subtotal) {
            return subtotal + calculateTax(subtotal);
	}

}