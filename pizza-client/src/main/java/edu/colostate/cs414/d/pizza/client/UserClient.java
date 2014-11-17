package edu.colostate.cs414.d.pizza.client;

import edu.colostate.cs414.d.pizza.api.user.User;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

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
				root.path("/login").request(MediaType.APPLICATION_JSON).get(),
				new GenericType<User>() {});
	}
	
	public User register(String username, String password) {
		MultivaluedMap<String, String> post = new MultivaluedHashMap<>();
		post.add("username", username);
		post.add("password", password);
		
		return readAndVerify(
				root.path("/register")
						.request(MediaType.APPLICATION_JSON)
						.post(Entity.form(post)),
				new GenericType<User>() {});
	}
	
	public void updateRewardPoints(int points) {
		verify(root.path("/updateRewardPoints")
				.request()
				.post(Entity.json(points)));
	}
	
}
