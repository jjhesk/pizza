package edu.colostate.cs414.d.pizza.ui.event;

import edu.colostate.cs414.d.pizza.api.order.OrderItem;
import org.timothyb89.eventbus.Event;

public class OrderItemCreateEvent extends Event {
	
	private final OrderItem item;

	public OrderItemCreateEvent(OrderItem item) {
		this.item = item;
	}

	public OrderItem getItem() {
		return item;
	}
	
}
