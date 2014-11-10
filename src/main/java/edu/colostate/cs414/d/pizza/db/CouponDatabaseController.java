package edu.colostate.cs414.d.pizza.db;

import edu.colostate.cs414.d.pizza.api.menu.Coupon;

import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import edu.colostate.cs414.d.pizza.utilities.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CouponDatabaseController {
    private Connection connection;

    public CouponDatabaseController(){
        connection = Database.getInstance().getConnection();
    }

    public void getCoupons(List<Coupon> coupons, List<MenuItem> menuItems) {
        PreparedStatement couponPreparedStatement = null;
        ResultSet couponResultSet = null;

        try {
            couponPreparedStatement = connection.prepareStatement("SELECT * FROM Coupon");
            couponResultSet = couponPreparedStatement.executeQuery();
            while(couponResultSet.next()){

                int id = couponResultSet.getInt(1);
                int rewards = couponResultSet.getInt(2);
                int menuItem = couponResultSet.getInt(3);
                boolean active = (couponResultSet.getString(4).equalsIgnoreCase("active")) ? true : false;

                Coupon coupon = new Coupon(id, Utility.getMenuItem(menuItem,menuItems), rewards, active);

                coupons.add(coupon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCoupon(Coupon coupon){
        String query = "INSERT INTO Coupon(rewardPointsNeeded,menuItemID,status) Values(?,?,?)";
        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, coupon.getRewardPoints());
            preparedStatement.setInt(2, coupon.getMenuItem().getId());
            String status = (coupon.isActive()) ? "active" : "expired";
            preparedStatement.setString(3, status);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                coupon.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setExpired(Coupon coupon) {
        String query = "UPDATE Coupon SET status = 'expired' WHERE couponID = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, coupon.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
