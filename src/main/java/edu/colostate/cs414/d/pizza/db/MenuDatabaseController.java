package edu.colostate.cs414.d.pizza.db;

import edu.colostate.cs414.d.pizza.api.menu.MenuItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDatabaseController {
    private Connection connection;

    public MenuDatabaseController(){
        connection = Database.getInstance().getConnection();
    }

    public void getMenu(List<MenuItem> menuItems) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM MenuItem");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                String description = (resultSet.getString(4) != null) ? resultSet.getString(4) : "";
                boolean active = (resultSet.getString(5).equalsIgnoreCase("active")) ? true : false;
                MenuItem menuItem = new MenuItem(id,name,price,description,active);
                menuItems.add(menuItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setExpired(MenuItem menuItem) {
        String query = "UPDATE MenuItem SET status = 'expired' WHERE menuItemID = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, menuItem.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMenuItems(List<MenuItem> menuItems) {
        String query = "INSERT INTO MenuItem(name,price,description,status) Values(?,?,?,?)";
        for(MenuItem menuItem : menuItems) {
            this.addMenuItem(menuItem);
        }

    }

    public void addMenuItem(MenuItem menuItem) {
        String query = "INSERT INTO MenuItem(name,price,description,status) Values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, menuItem.getName());
            preparedStatement.setDouble(2, menuItem.getPrice());
            preparedStatement.setString(3, menuItem.getDescription());
            String status = (menuItem.isActive()) ? "active" : "expired";
            preparedStatement.setString(4, status);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                menuItem.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*Testing purpose*/
    public static void main(String args[]) {
        MenuDatabaseController menuDatabaseController = new MenuDatabaseController();
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        /*menuDatabaseController.getMenu(items);
        System.out.println(items.toString());
        menuDatabaseController.setExpired(items.get(0));
        items.clear();
        menuDatabaseController.getMenu(items);
        System.out.println(items.toString());

        ArrayList<MenuItem> itemsToEnter = new ArrayList<MenuItem>();
        itemsToEnter.add(new MenuItem("test",99.3,""));
        itemsToEnter.add(new MenuItem("phone",33.3,"this is a phone not a pizza"));
        itemsToEnter.add(new MenuItem("lets go",222,null));

        menuDatabaseController.addMenuItems(itemsToEnter);

        System.out.println(itemsToEnter.toString());

        menuDatabaseController.getMenu(items);
        System.out.println(items.toString());
        */

    }

}
