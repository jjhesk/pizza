package edu.colostate.cs414.d.pizza.db;

import java.sql.Connection;

public class OrderDatabaseController {
    private Connection connection;

    public OrderDatabaseController(){
        connection = Database.getInstance().getConnection();
    }
}
