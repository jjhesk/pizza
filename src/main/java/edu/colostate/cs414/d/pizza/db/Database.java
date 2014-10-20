package edu.colostate.cs414.d.pizza.db;

import java.sql.*;
import java.util.Properties;

/**
 *
 * @author tim
 */

public class Database {
	
	private static Database instance;
	
	private Connection connection;
	
	private Database() {

		// init connection here

        /* Will work for connecting from cs machines
        String url = "jdbc:mysql://" +
                "urlhere:porthere/username";
        Properties props = new Properties();
        props.setProperty("user","usernamehere");
        props.setProperty("password","passwordhere");
        try {
            System.out.println("Connection");
            connection = DriverManager.getConnection(url, props);
            //System.out.println("yeah");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
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

    /*Take out later used for testing
    public static void main(String args[]) throws SQLException {

        Database database = new Database();

        PreparedStatement preparedStatement = database.connection.prepareStatement("SELECT * FROM User");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
        }
    }
    */
}
