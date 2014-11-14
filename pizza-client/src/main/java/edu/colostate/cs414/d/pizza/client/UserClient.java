package edu.colostate.cs414.d.pizza.client;

import edu.colostate.cs414.d.pizza.api.user.User;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class UserClient extends AuthenticatedWebServiceClient {

	private WebTarget root;
	
	public UserClient(String target) {
		super(target);
	}

	public UserClient(String target, String username, String password) {
		super(target, username, password);
	}

	@Override
	protected void init() {
		super.init();
		
		root = target.path("/user");
	}
	
	public User login() {
		return readAndVerify(
				root.request(MediaType.APPLICATION_JSON).get(),
				new GenericType<User>() {});
	}
	
}
