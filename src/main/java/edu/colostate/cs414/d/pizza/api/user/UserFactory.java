package edu.colostate.cs414.d.pizza.api.user;

public class UserFactory {
    public UserFactory(){

    }
    public User createUser(UserType userType, String userName, String password, int rewardPoints){
        User user = null;
        switch(userType){
            case CASHIER:
                user = new Cashier(userName, password, userType);
                break;

            case CHEF:
                user = new Chef(userName, password, userType);
                break;

            case CUSTOMER:
                user = new Customer(userName,password,userType, rewardPoints);
                break;

            case MANAGER:
                user = new Manager(userName,password,userType);
                break;
        }
        return user;
    }
}
