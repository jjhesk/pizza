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
}
