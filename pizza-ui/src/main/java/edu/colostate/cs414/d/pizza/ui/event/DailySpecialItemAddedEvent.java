package edu.colostate.cs414.d.pizza.ui.event;

import edu.colostate.cs414.d.pizza.api.menu.PizzaMenuItem;
import org.timothyb89.eventbus.Event;

public class DailySpecialItemAddedEvent extends Event {
    
    private final PizzaMenuItem item;

    public DailySpecialItemAddedEvent(PizzaMenuItem item) {
        this.item = item;
    }

    public PizzaMenuItem getItem() {
        return item;
    }
    
}
