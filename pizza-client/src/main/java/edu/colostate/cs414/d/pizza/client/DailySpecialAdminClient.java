package edu.colostate.cs414.d.pizza.client;

import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class DailySpecialAdminClient extends AuthenticatedWebServiceClient {
	
	private WebTarget root;
	
	public DailySpecialAdminClient(String targetPath, String username, String password) {
		super(targetPath, username, password);
	}
	
	public DailySpecialAdminClient(String targetPath) {
		this(targetPath, null, null);
	}

	@Override
	protected void init() {
		super.init();
		
		root = target.path("/admin/special");
	}
	
	public void specialCreate(DailySpecial special) {
		Response resp = root.path("/create").request(MediaType.APPLICATION_JSON)
				.post(Entity.json(special));
		
		verify(resp);
	}
	
	public void specialRemove(int id) {
		Response resp = root.path("/remove").request(MediaType.APPLICATION_JSON)
				.post(Entity.json(id));
		
		verify(resp);
	}
	
	public static void main(String[] args) {
		DailySpecialAdminClient c = new DailySpecialAdminClient("http://localhost:8080");
		//c.authenticate("manager", "manager");
		
		//c.specialCreate(new DailySpecial(15.00, Arrays.asList(new MenuItem(1, null, 0, null, true))));
		try {
			c.specialRemove(3);
		} catch (WebServiceException ex) {
			System.out.println("caught exception D:");
			ex.printStackTrace();
		}
	}
	
}
