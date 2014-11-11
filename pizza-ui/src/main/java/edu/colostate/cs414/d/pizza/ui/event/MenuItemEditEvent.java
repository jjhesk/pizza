package edu.colostate.cs414.d.pizza.ui.event;

import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import org.timothyb89.eventbus.Event;

public class MenuItemEditEvent extends Event {
	
	private final MenuItem originalItem;
	private final MenuItem newItem;

	public MenuItemEditEvent(MenuItem originalItem, MenuItem newItem) {
		this.originalItem = originalItem;
		this.newItem = newItem;
	}

	public MenuItem getOriginalItem() {
		return originalItem;
	}

	public MenuItem getNewItem() {
		return newItem;
	}
	
}
