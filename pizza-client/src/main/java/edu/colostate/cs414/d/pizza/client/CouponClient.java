package edu.colostate.cs414.d.pizza.client;

import edu.colostate.cs414.d.pizza.api.menu.Coupon;
import java.util.List;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class CouponClient extends WebServiceClient {

	private WebTarget root;
	
	public CouponClient(String targetPath) {
		super(targetPath);
	}

	@Override
	protected void init() {
		super.init();
		
		root = target.path("/coupon");
	}
	
	public List<Coupon> getCoupons() {
		return readAndVerify(
				root.path("/list").request(MediaType.APPLICATION_JSON).get(),
				new GenericType<List<Coupon>>() {});
	}
	
	public static void main(String[] args) {
		for (Coupon c : new CouponClient("http://localhost:8080").getCoupons()) {
			System.out.println(c.getId() + " - " + c.getMenuItem());
		}
	}
	
}
