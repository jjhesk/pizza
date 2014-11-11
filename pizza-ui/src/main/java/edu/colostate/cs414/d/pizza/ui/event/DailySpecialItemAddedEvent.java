package edu.colostate.cs414.d.pizza.ui.event;

import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import org.timothyb89.eventbus.Event;

public class DailySpecialItemAddedEvent extends Event {
    
    private final MenuItem item;

    public DailySpecialItemAddedEvent(MenuItem item) {
        this.item = item;
    }

    public MenuItem getItem() {
        return item;
    }
    
}
