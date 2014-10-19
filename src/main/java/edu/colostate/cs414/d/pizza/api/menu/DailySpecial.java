package edu.colostate.cs414.d.pizza.api.menu;

import java.util.Date;
import java.util.List;

/**
 *
 * @author tim
 */
public class DailySpecial {
	
	private final double price;
	private final Date startDate;
	private final Date endDate;
	private final List<MenuItem> items;

	public DailySpecial(double price, Date startDate, Date endDate, List<MenuItem> items) {
		this.price = price;
		this.startDate = startDate;
		this.endDate = endDate;
		this.items = items;
	}

	public double getPrice() {
		return price;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public List<MenuItem> getItems() {
		return items;
	}
	
}
