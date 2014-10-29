package edu.colostate.cs414.d.pizza.ui.user;

import edu.colostate.cs414.d.pizza.api.user.User;
import java.util.List;
import javax.swing.AbstractListModel;

public class UserListModel extends AbstractListModel {

	private final List<User> users;
	
	public UserListModel(List<User> users) {
		this.users = users;
	}
	
	@Override
	public int getSize() {
		return users.size();
	}

	@Override
	public Object getElementAt(int index) {
		User user = users.get(index);
		
		return String.format("%s (%s)", user.getUserName(), user.getUserType());
	}
	
	public User getUser(int index) {
		return users.get(index);
	}
	
}
