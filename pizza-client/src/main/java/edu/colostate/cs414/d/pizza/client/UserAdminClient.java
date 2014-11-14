package edu.colostate.cs414.d.pizza.client;

import edu.colostate.cs414.d.pizza.api.user.User;
import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class UserAdminClient extends AuthenticatedWebServiceClient {

	private WebTarget root;
	
	public UserAdminClient(String target) {
		super(target);
	}

	public UserAdminClient(String target, String username, String password) {
		super(target, username, password);
	}

	@Override
	protected void init() {
		super.init();
		
		root = target.path("/admin/user");
	}
	

	public void userCreate(User user) {
		verify(root.path("/create")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(user)));
	}
	
	public void userRemove(String username) {
		verify(root.path("/remove")
				.request()
				.post(Entity.json(username)));
	}
	
	public List<User> getUsers() {
		return readAndVerify(
				root.path("/list")
						.request(MediaType.APPLICATION_JSON)
						.get(),
				new GenericType<List<User>>() {});
	}
	
}
