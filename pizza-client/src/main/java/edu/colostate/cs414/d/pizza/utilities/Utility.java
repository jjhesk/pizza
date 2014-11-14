package edu.colostate.cs414.d.pizza.utilities;

import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.api.menu.PizzaMenuItem;
import java.util.ArrayList;
import java.util.List;

public class Utility {
    public static PizzaMenuItem getMenuItem(int menuItemID, List<PizzaMenuItem> menuItems) {
        for(PizzaMenuItem menuItem : menuItems) {
            if(menuItem.getId() == menuItemID) {
                return menuItem;
            }
        }
        return null;
    }

    public static boolean tryApplyDailySpecial(DailySpecial dailySpecial, List<PizzaMenuItem> menuItems){
        boolean apply = false;
        List<PizzaMenuItem> tempMenuItems = new ArrayList<>();
        for(PizzaMenuItem menuItem : menuItems){
            tempMenuItems.add(new PizzaMenuItem(menuItem));
        }

        for(PizzaMenuItem menuItem : dailySpecial.getItems()){
            if(tempMenuItems.contains(menuItem)){
                tempMenuItems.remove(menuItem);
            }
            else{
                return false;
            }
        }

        for (PizzaMenuItem menuItem : dailySpecial.getItems()) {
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
