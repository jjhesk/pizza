package edu.colostate.cs414.d.pizza.api.order;

import edu.colostate.cs414.d.pizza.api.menu.MenuItem;

public class OrderItem {
	
	private int id;
	
	private MenuItem item;
	private int quantity;
	
	// TODO: options?
	public OrderItem() {
	}

	public OrderItem(MenuItem item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
	public OrderItem(int id, MenuItem item, int quantity) {
		this.id = id;
		this.item = item;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MenuItem getItem() {
		return item;
	}

	public void setItem(MenuItem item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
