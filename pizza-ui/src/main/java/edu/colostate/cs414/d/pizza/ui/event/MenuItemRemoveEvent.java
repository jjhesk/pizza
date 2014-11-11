package edu.colostate.cs414.d.pizza.ui.event;

import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import org.timothyb89.eventbus.Event;

public class MenuItemRemoveEvent extends Event {
	
	private final MenuItem item;

	public MenuItemRemoveEvent(MenuItem item) {
		this.item = item;
	}

	public MenuItem getItem() {
		return item;
	}
	
}
