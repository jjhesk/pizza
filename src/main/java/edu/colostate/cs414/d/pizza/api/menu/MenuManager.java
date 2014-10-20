package edu.colostate.cs414.d.pizza.api.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuManager {

	private static MenuManager instance;

    private List<MenuItem> menuItems;

    private List<DailySpecial> dailySpecials;
	
	public MenuManager() {
        menuItems = new ArrayList<MenuItem>();
        dailySpecials = new ArrayList<DailySpecial>();
	}

    public static MenuManager getInstance() {
        if (instance == null) {
            instance = new MenuManager();
        }
        return instance;
    }

    public MenuItem createMenuItem(String name, double price, String description){
        return new MenuItem(name, price, description);
    }

    public void saveMenu(List<MenuItem> menuItems) {
        for (MenuItem menuItem: this.menuItems) {
            menuItem.setActive(false);
        }
        this.menuItems.addAll(menuItems);
    }

    //returns current menu Items
	public List<MenuItem> getMenuItems() {
        ArrayList<MenuItem> currentItems = new ArrayList<MenuItem>();
        for (MenuItem menuItem: this.menuItems) {
            if(menuItem.isActive()) {
                currentItems.add(menuItem);
            }
        }
		return currentItems;
	}
	
	public void addMenuItem(MenuItem item) {
        if(!this.menuItems.contains(item)){
            this.menuItems.add(item);
        }
	}
	
	public void removeMenuItem(MenuItem item) {
        item.setActive(false);
	}
	
	public List<DailySpecial> getDailySpecials() {
		return dailySpecials;
	}
	
	public void addDailySpecial(DailySpecial special) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public void removeDailySpecial(DailySpecial special) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public void updateDailySpecial(DailySpecial special) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
}
