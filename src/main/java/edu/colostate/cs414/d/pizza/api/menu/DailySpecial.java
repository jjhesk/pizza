package edu.colostate.cs414.d.pizza.api.menu;

import java.util.List;

public class DailySpecial {
	private int id;

	private final double price;
	private final List<MenuItem> items;
    private boolean active;

	public DailySpecial(double price, List<MenuItem> items) {
		this.price = price;
		this.items = items;
        this.active = true;
	}

    public DailySpecial(int id, double price, List<MenuItem> items, Boolean active) {
        this.id = id;
        this.price = price;
        this.items = items;
        this.active = active;
    }

    public boolean checkStatus(){
        for (MenuItem menuItem : items) {
            if(!menuItem.isActive()){
                active = false;
                return false;
            }
        }
        return true;
    }

   	public double getPrice() {
		return price;
	}

	public List<MenuItem> getItems() {
		return items;
	}

    public int getID() { return id; }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setId(int id) { this.id = id; }

}
