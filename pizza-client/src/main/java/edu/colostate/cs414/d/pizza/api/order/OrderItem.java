package edu.colostate.cs414.d.pizza.api.order;

import edu.colostate.cs414.d.pizza.api.menu.PizzaMenuItem;
import java.util.Objects;

public class OrderItem {
	
	private int id;
	private PizzaMenuItem item;
	private int quantity;
	
	// TODO: options?
	public OrderItem() {
	}

	public OrderItem(PizzaMenuItem item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
	public OrderItem(int id, PizzaMenuItem item, int quantity) {
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

	public PizzaMenuItem getItem() {
		return item;
	}

	public void setItem(PizzaMenuItem item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.item);
        hash = 97 * hash + this.quantity;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderItem other = (OrderItem) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.item, other.item)) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return item.getName() + " qty:" + String.valueOf(quantity);
    } 
}
