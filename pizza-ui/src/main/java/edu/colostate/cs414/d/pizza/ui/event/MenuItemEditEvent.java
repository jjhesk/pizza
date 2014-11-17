package edu.colostate.cs414.d.pizza.ui.event;

import edu.colostate.cs414.d.pizza.api.menu.PizzaMenuItem;
import org.timothyb89.eventbus.Event;

public class MenuItemEditEvent extends Event {
	
	private final PizzaMenuItem originalItem;
	private final PizzaMenuItem newItem;

	public MenuItemEditEvent(PizzaMenuItem originalItem, PizzaMenuItem newItem) {
		this.originalItem = originalItem;
		this.newItem = newItem;
	}

	public PizzaMenuItem getOriginalItem() {
		return originalItem;
	}

	public PizzaMenuItem getNewItem() {
		return newItem;
	}
	
}
