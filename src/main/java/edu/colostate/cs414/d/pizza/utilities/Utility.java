package edu.colostate.cs414.d.pizza.utilities;

import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import edu.colostate.cs414.d.pizza.db.Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utility {
    public static MenuItem getMenuItem(int menuItemID, List<MenuItem> menuItems) {
        for(MenuItem menuItem : menuItems) {
            if(menuItem.getId() == menuItemID) {
                return menuItem;
            }
        }
        return null;
    }

    public static boolean tryApplyDailySpecial(DailySpecial dailySpecial, List<MenuItem> menuItems){
        boolean apply = false;
        List<MenuItem> tempMenuItems = new ArrayList<>();
        for(MenuItem menuItem : menuItems){
            tempMenuItems.add(new MenuItem(menuItem));
        }

        for(MenuItem menuItem : dailySpecial.getItems()){
            if(tempMenuItems.contains(menuItem)){
                tempMenuItems.remove(menuItem);
            }
            else{
                return false;
            }
        }

        for (MenuItem menuItem : dailySpecial.getItems()) {
            menuItems.remove(menuItem);
        }

        return true;
    }

	public static double calculateTax(double subtotal) {
            //can be set to anything
            double tax = .07;
            return subtotal*tax;
	}
	
	public static double calculateTotalWithTax(double subtotal) {
            return subtotal + calculateTax(subtotal);
	}

    //soley for testing purposes nothing else. DO NOT CALL FROM ANYWHERE ELSE
    public static void removeDataFromDatabase(){
        Connection connection = Database.getInstance().getConnection();
        List<String> queries = Arrays.asList("SET FOREIGN_KEY_CHECKS = 0;",
                "DROP TABLE if exists UserOrder;",
                "DROP TABLE if exists User;",
                "DROP TABLE if exists OrderItem;",
                "DROP TABLE if exists DailySpecialItem;",
                "DROP TABLE if exists DailySpecial;",
                "DROP TABLE if exists MenuItem;",
                "SET FOREIGN_KEY_CHECKS = 1;",
                "CREATE TABLE User (userName varchar(255) NOT NULL, password varchar(255) NOT NULL, userType ENUM('cashier', 'chef', 'customer', 'manager') NOT NULL, PRIMARY KEY (userName));",
                "CREATE TABLE MenuItem (menuItemID int NOT NULL AUTO_INCREMENT, name varchar(255), price double NOT NULL, description varchar(255), status ENUM('active', 'expired') NOT NULL, PRIMARY KEY (menuItemID));",
                "CREATE TABLE UserOrder (orderID int NOT NULL AUTO_INCREMENT, customerName varchar(255), customerAddress varchar(255), status ENUM('new', 'pending', 'cancelled', 'complete'), type ENUM('delivery', 'pickup', 'eatin'), total double, PRIMARY KEY (orderID));",
                "CREATE TABLE OrderItem (orderItemID int NOT NULL AUTO_INCREMENT, quantity int, menuItemID int, orderID int, FOREIGN KEY (menuItemID) REFERENCES MenuItem(menuItemID), FOREIGN KEY (orderID) REFERENCES UserOrder(orderID), PRIMARY KEY (orderItemID));",
                "CREATE TABLE DailySpecial (dailySpecialID int NOT NULL AUTO_INCREMENT, status ENUM('active', 'expired') NOT NULL, price double, PRIMARY KEY (dailySpecialID));",
                "CREATE TABLE DailySpecialItem (dailySpecialItemID int NOT NULL AUTO_INCREMENT, dailySpecialID int, menuItemID int, FOREIGN KEY (menuItemID) REFERENCES MenuItem(menuItemID), FOREIGN KEY (dailySpecialID) REFERENCES DailySpecial(dailySpecialID), PRIMARY KEY (dailySpecialItemID));"
        );
        try {
            for (String query : queries){
                PreparedStatement preparedStatement = null;
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
	
}
