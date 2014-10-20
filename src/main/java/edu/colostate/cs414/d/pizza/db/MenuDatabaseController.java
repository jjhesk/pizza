package edu.colostate.cs414.d.pizza.db;

import java.sql.Connection;

public class MenuDatabaseController {
    private Connection connection;

    public MenuDatabaseController(){
        connection = Database.getInstance().getConnection();
    }
}
