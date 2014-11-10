package edu.colostate.cs414.d.pizza.api.user;

public class Cashier extends User {
    public Cashier(String userName, String password, UserType userType) {
        super(userName, password, userType, 0);
    }
}
