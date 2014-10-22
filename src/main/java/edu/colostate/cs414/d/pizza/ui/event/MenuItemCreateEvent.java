package edu.colostate.cs414.d.pizza.ui.event;

import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import org.timothyb89.eventbus.Event;

public class MenuItemCreateEvent extends Event {

	private final MenuItem item;

	public MenuItemCreateEvent(MenuItem item) {
		this.item = item;
	}

	public MenuItem getItem() {
		return item;
	}
	
}
