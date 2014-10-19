package edu.colostate.cs414.d.pizza.db;

import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author tim
 */
public class MenuManager {

	private static MenuManager instance;
	
	private Connection connection;
	
	private MenuManager() {
		connection = Database.getInstance().getConnection();
	}
	
	public List<MenuItem> getMenuItems() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public void addMenuItem(MenuItem item) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public void removeMenuItem(MenuItem item) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public void updateMenuItem(MenuItem item) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public List<DailySpecial> getDailySpecials() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public void addDailySpecial(DailySpecial special) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public void removeDailySpecial(DailySpecial special) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	public void updateDailySpecial(DailySpecial special) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
}
