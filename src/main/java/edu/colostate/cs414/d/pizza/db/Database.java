package edu.colostate.cs414.d.pizza.db;

import java.sql.Connection;

/**
 *
 * @author tim
 */
public class Database {
	
	private static Database instance;
	
	private Connection connection;
	
	private Database() {
		// init connection here
		connection = null;
	}

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		
		return instance;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
}
