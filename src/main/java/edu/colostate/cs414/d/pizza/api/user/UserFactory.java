package edu.colostate.cs414.d.pizza.api.user;

public class UserFactory {
    public static User createUser(UserType userType, String userName, String password){
        User user = null;
        switch(userType){
            case CASHIER:
                user = new Cashier(userName,password,userType);
                break;

            case CHEF:
                user = new Chef(userName,password,userType);
                break;

            case CUSTOMER:
                user = new Customer(userName,password,userType);
                break;

            case MANAGER:
                user = new Manager(userName,password,userType);
                break;

            default:
                // throw some exception
                break;
        }
        return user;
    }
}
