package edu.colostate.cs414.d.pizza.api.menu;

/**
 *
 * @author tim
 */
public class MenuItem {
	
	private int id;
	
	private String name;
	private double price;
	private String description;
	
	private boolean active;

	public MenuItem() {
		active = true;
	}

	public MenuItem(String name, double price, String description) {
		this.name = name;
		this.price = price;
		this.description = description;
		
		active = true;
	}

	public MenuItem(int id, String name, double price, String description) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		
		active = true;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}