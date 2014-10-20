package edu.colostate.cs414.d.pizza.db;


import java.sql.Connection;

public class UserDatabaseController {
    private Connection connection;

    public UserDatabaseController(){
        connection = Database.getInstance().getConnection();
    }
}
