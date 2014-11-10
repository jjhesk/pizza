package edu.colostate.cs414.d.pizza.db;

import edu.colostate.cs414.d.pizza.api.user.User;
import edu.colostate.cs414.d.pizza.api.user.UserFactory;
import edu.colostate.cs414.d.pizza.api.user.UserType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDatabaseController {
    private Connection connection;
    private UserFactory userFactory;

    public UserDatabaseController(){
        connection = Database.getInstance().getConnection();
        userFactory = new UserFactory();
    }

    public void getUsers(List<User> users) {
        users.clear();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM User");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                UserType userType = UserType.valueOf(resultSet.getString("userType").toUpperCase());
                users.add(userFactory.createUser(userType, userName, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        String query = "INSERT INTO User(userName,password,userType,rewardpoints) Values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getUserType().toString().toLowerCase());
            preparedStatement.setInt(4, user.getRewardPoints());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUser(String userName) {
        String query = "DELETE FROM User WHERE userName = ?";
        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePoints(User user) {
        String query = "UPDATE User SET rewardpoints = ? WHERE userName = ?";
        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user.getRewardPoints());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
