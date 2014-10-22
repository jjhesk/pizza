package edu.colostate.cs414.d.pizza.db;

import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import edu.colostate.cs414.d.pizza.utilities.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DailySpecialDatabaseController {
    private Connection connection;

    public DailySpecialDatabaseController(){
        connection = Database.getInstance().getConnection();
    }

    public void getDailySpecials(List<DailySpecial> dailySpecials, List<MenuItem> menuItems) {
        String query = "SELECT * FROM DailySpecialItem WHERE dailySpecialID = ?";
        PreparedStatement dailySpecialPreparedStatement = null;
        PreparedStatement preparedStatement = null;
        ResultSet dailySpecialResultSet = null;
        ResultSet resultSet = null;
        List<MenuItem> dailySpecialItems = new ArrayList<MenuItem>();

        try {
            dailySpecialPreparedStatement = connection.prepareStatement("SELECT * FROM DailySpecial");
            dailySpecialResultSet = dailySpecialPreparedStatement.executeQuery();
            while(dailySpecialResultSet.next()){

                //get ids
                int id = dailySpecialResultSet.getInt(1);

                //get items apart of each daily special
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()) {
                    int menuItemID = resultSet.getInt(3);
                    dailySpecialItems.add(Utility.getMenuItem(menuItemID, menuItems));
                }

                boolean active = (resultSet.getString(2).equalsIgnoreCase("active")) ? true : false;
                double price = resultSet.getDouble(3);

                DailySpecial dailySpecial = new DailySpecial(id, price, dailySpecialItems, active);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDailySpecial(DailySpecial dailySpecial) {
        String query = "INSERT INTO DailySpecial(status,price) Values(?,?)";
        PreparedStatement preparedStatement = null;
        PreparedStatement newPreparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            String status = (dailySpecial.isActive()) ? "active" : "expired";
            preparedStatement.setString(1, status);
            preparedStatement.setDouble(2, dailySpecial.getPrice());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                dailySpecial.setId(resultSet.getInt(1));
            }

            for (MenuItem menuItem : dailySpecial.getItems()) {
                query = "INSERT INTO DailySpecialItem(dailySpecialID,menuItemID) Values(?,?)";
                newPreparedStatement = connection.prepareStatement(query);
                newPreparedStatement.setInt(1,dailySpecial.getID());
                newPreparedStatement.setInt(2,menuItem.getId());
                newPreparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void setExpired(DailySpecial special) {
        String query = "UPDATE DailySpecial SET status = 'expired' WHERE dailySpecialID = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, special.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
