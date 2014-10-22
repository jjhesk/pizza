package edu.colostate.cs414.d.pizza.api.menu;

import edu.colostate.cs414.d.pizza.db.DailySpecialDatabaseController;
import edu.colostate.cs414.d.pizza.db.MenuDatabaseController;

import java.util.ArrayList;
import java.util.List;

public class MenuManager {

	private static MenuManager instance;

    private MenuDatabaseController menuDatabase;
    private DailySpecialDatabaseController dailySpecialDatabase;

    private List<MenuItem> menuItems;

    private List<DailySpecial> dailySpecials;
	
	private MenuManager() {
        menuDatabase  = new MenuDatabaseController();
        dailySpecialDatabase = new DailySpecialDatabaseController();
        menuItems = new ArrayList<MenuItem>();
        menuDatabase.getMenu(menuItems);
        dailySpecials = new ArrayList<DailySpecial>();
        dailySpecialDatabase.getDailySpecials(dailySpecials, menuItems);

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
            menuDatabase.setExpired(menuItem);
        }
        this.checkDailySpecials();
        menuDatabase.addMenuItems(menuItems);
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

    //returns all menu Items
    public List<MenuItem> getAllMenuItems() {
        return menuItems;
    }

	
	public void addMenuItem(MenuItem item) {
        if(!this.menuItems.contains(item)){
            this.menuItems.add(item);
            menuDatabase.addMenuItem(item);
        }
	}
	
	public void removeMenuItem(MenuItem item) {
        item.setActive(false);
        menuDatabase.setExpired(item);
        this.checkDailySpecials();
	}

    //returns current daily specials
	public List<DailySpecial> getDailySpecials() {
        ArrayList<DailySpecial> currentDailySpecials = new ArrayList<DailySpecial>();
        for (DailySpecial dailySpecial : this.dailySpecials) {
            if(dailySpecial.isActive()) {
                currentDailySpecials.add(dailySpecial);
            }
        }
		return currentDailySpecials;
	}

    public DailySpecial createDailySpecial(List<MenuItem> menuItems, Double price) {
        DailySpecial dailySpecial = new DailySpecial(price, menuItems);
        dailySpecialDatabase.addDailySpecial(dailySpecial);
        return dailySpecial;
    }

	public void removeDailySpecial(DailySpecial special) {
		special.setActive(false);
        dailySpecialDatabase.setExpired(special);
	}

    public void checkDailySpecials(){
        for (DailySpecial dailySpecial : this.dailySpecials) {
            if(dailySpecial.isActive()) {
                if(!dailySpecial.checkStatus()) {
                    dailySpecialDatabase.setExpired(dailySpecial);
                }
            }
        }
    }


}
