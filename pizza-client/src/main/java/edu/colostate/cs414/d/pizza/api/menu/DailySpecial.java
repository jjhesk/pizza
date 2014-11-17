package edu.colostate.cs414.d.pizza.api.menu;

import java.util.LinkedList;
import java.util.List;

public class DailySpecial {
    private int id;
    private double price;
    private final List<PizzaMenuItem> items;
    private boolean active;

	public DailySpecial() {
		active = true;
		items = new LinkedList<>();
	}
	
    public DailySpecial(double price, List<PizzaMenuItem> items) {
        this.price = price;
        this.items = items;
        this.active = true;
    }

    public DailySpecial(int id, double price, List<PizzaMenuItem> items, Boolean active) {
        this.id = id;
        this.price = price;
        this.items = items;
        this.active = active;
    }

    public boolean checkStatus(){
        for (PizzaMenuItem menuItem : items) {
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

	public void setPrice(double price) {
		this.price = price;
	}
	
	public List<PizzaMenuItem> getItems() {
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

    public boolean equals(Object other)
    {
        if (!(this.price == (((DailySpecial)other).price)))
        {
            return false;
        }

        if (!(this.items.size() == (((DailySpecial)other).items.size())))
        {
            return false;
        }

        for (PizzaMenuItem menuItem : this.getItems()) {
            if(!((DailySpecial)other).items.contains(menuItem)){
                return  false;
            }
        }

        if(!(this.active == ((DailySpecial)other).active)){
            return false;
        }

        return true;
    }
    
    @Override
    public String toString() {
        String output = "";
        output += "[";
        for (int i = 0; i < this.items.size(); i++){
            if(i<this.items.size()-1){
                output += items.get(i).getName() + ", "; 
            }
            else{
                output += items.get(i).getName();
            }
        }
        output += "] $" + String.valueOf(price);
        return output;
    }
}
