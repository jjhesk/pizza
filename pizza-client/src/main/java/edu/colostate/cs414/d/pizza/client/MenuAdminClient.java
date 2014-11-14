package edu.colostate.cs414.d.pizza.client;

import edu.colostate.cs414.d.pizza.api.menu.PizzaMenuItem;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class MenuAdminClient extends AuthenticatedWebServiceClient {

	private WebTarget root;
	
	public MenuAdminClient(String target) {
		super(target);
	}

	public MenuAdminClient(String target, String username, String password) {
		super(target, username, password);
	}

	@Override
	protected void init() {
		super.init();
		
		root = target.path("/admin/menu");
	}
	
	public PizzaMenuItem itemCreate(PizzaMenuItem item) {
		return readAndVerify(
				root.path("/item-create")
						.request(MediaType.APPLICATION_JSON)
						.post(Entity.json(item)),
				new GenericType<PizzaMenuItem>() {});
	}
	
	public void itemRemove(int id) {
		verify(root.path("/item-remove")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(id)));
	}
	
	public void clearMenu() {
		verify(root.path("/clear").request().get());
	}
	
}
