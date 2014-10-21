package edu.colostate.cs414.d.pizza.api.user;

import edu.colostate.cs414.d.pizza.db.UserDatabaseController;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private List<User> users;
    private static UserManager instance;
    private UserDatabaseController userDatabase;

    public UserManager(){
        userDatabase = new UserDatabaseController();
        users = new ArrayList<User>();
        userDatabase.getUsers(users);
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

	public List<User> getUsers() {
        return users;
	}
	
	public User authenticateUser(String userName, String password) {
        for(User user : users) {
            if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
	}
	
	public boolean addUser(String userName, String password, UserType userType) {
        for(User user : users) {
            if(user.getUserName().equals(userName)){
                return false;
            }
        }
        User user = UserFactory.createUser(userType,userName,password);
        userDatabase.addUser(user);
        this.users.add(user);
        return true;
	}

	public void removeUser(String userName) {
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUserName().equals(userName)){
                users.remove(i);
                userDatabase.removeUser(userName);
                return;
            }
        }
    }
	
}
