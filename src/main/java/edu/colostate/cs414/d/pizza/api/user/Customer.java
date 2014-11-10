package edu.colostate.cs414.d.pizza.api.user;

public class Customer extends User {
    private int rewardPoints;
    public Customer(String userName, String password, UserType userType) {
        super(userName, password, userType);
        rewardPoints = 0;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }
}

