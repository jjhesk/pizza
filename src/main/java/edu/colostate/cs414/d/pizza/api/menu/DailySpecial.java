package edu.colostate.cs414.d.pizza.api.menu;

import java.util.Date;
import java.util.List;

/**
 *
 * @author tim
 */
public class DailySpecial {
	
	private final double price;
	private final List<MenuItem> items;
    private boolean active;

	public DailySpecial(double price, List<MenuItem> items) {
		this.price = price;
		this.items = items;
        this.active = true;
	}

    public void checkStatus(){
        for (MenuItem menuItem : items) {
            if(!menuItem.isActive()){
                active = false;
            }
        }
    }

   	public double getPrice() {
		return price;
	}

	public List<MenuItem> getItems() {
		return items;
	}

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
