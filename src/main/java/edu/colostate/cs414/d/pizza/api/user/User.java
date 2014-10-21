package edu.colostate.cs414.d.pizza.api.user;

public class User {

	private String userName;

    private String password;

    private UserType userType;

	public User(String userName, String password, UserType userType) {
		this.userName = userName;
        this.password = password;
        this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String name) {
		this.userName = name;
	}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() { return userType; }

    public void setUserType(UserType userType) { this.userType = userType; }

}
