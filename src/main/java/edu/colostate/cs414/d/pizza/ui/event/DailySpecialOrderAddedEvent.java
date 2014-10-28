package edu.colostate.cs414.d.pizza.ui.event;

import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import org.timothyb89.eventbus.Event;

public class DailySpecialOrderAddedEvent extends Event {
	
	private final DailySpecial special;

	public DailySpecialOrderAddedEvent(DailySpecial special) {
		this.special = special;
	}

	public DailySpecial getSpecial() {
		return special;
	}
	
}
