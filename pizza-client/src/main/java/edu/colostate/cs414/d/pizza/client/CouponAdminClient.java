package edu.colostate.cs414.d.pizza.client;

import edu.colostate.cs414.d.pizza.api.menu.Coupon;
import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class CouponAdminClient extends AuthenticatedWebServiceClient {

	private WebTarget root;
	
	public CouponAdminClient(String targetPath, String username, String password) {
		super(targetPath, username, password);
	}
	
	public CouponAdminClient(String targetPath) {
		this(targetPath, null, null);
	}

	@Override
	protected void init() {
		super.init();
		
		root = target.path("/admin/coupon");
	}
	
	public void couponCreate(Coupon coupon) {
		verify(root.path("/create")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(coupon)));
	}
	
	public void couponRemove(int id) {
		verify(root.path("/remove")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(id)));
	}

	public static void main(String[] args) {
		CouponAdminClient c = new CouponAdminClient("http://localhost:8080");
		c.authenticate("manager", "manager");
		
		//c.couponCreate(new Coupon(new MenuItem(6, null, 0, null, true), 3));
		c.couponRemove(6);
	}
	
}
