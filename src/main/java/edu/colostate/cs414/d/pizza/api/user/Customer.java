package edu.colostate.cs414.d.pizza.api.user;

public class Customer extends User {
    private int rewardPoints;
    public Customer(String userName, String password, UserType userType, int rewardPoints) {
        super(userName, password, userType, rewardPoints);
    }
}

