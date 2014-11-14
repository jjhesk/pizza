package edu.colostate.cs414.d.pizza.client;

import edu.colostate.cs414.d.pizza.api.order.Order;
import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class OrderClient extends AuthenticatedWebServiceClient {

	private WebTarget root;
	
	public OrderClient(String target) {
		super(target);
	}

	public OrderClient(String target, String username, String password) {
		super(target, username, password);
	}

	@Override
	protected void init() {
		super.init();
		
		root = target.path("/order");
	}
	
	public Order placeOrder(Order order) {
		return readAndVerify(
				root.path("/place")
						.request(MediaType.APPLICATION_JSON)
						.post(Entity.json(order)),
				new GenericType<Order>() {});
	}
	
	public List<Order> viewUserHistory() {
		return readAndVerify(
				root.path("/history").request(MediaType.APPLICATION_JSON).get(),
				new GenericType<List<Order>>() {});
	}
	
	public List<Order> viewPending() {
		return readAndVerify(
				root.path("/view-pending")
						.request(MediaType.APPLICATION_JSON)
						.get(),
				new GenericType<List<Order>>() {});
	}
	
	public List<Order> viewDelivered() {
		return readAndVerify(
				root.path("/view-delivered")
						.request(MediaType.APPLICATION_JSON)
						.get(),
				new GenericType<List<Order>>() {});
	}
	
	public Order complete(int orderId) {
		return readAndVerify(
				root.path("/view-pending")
						.request(MediaType.APPLICATION_JSON)
						.post(Entity.json(orderId)),
				new GenericType<Order>() {});
	}
	
	public static void main(String[] args) {
		OrderClient c = new OrderClient("http://localhost:8080");
		//c.authenticate("manager", "manager");
		c.authenticate("m", "m");
		
		for (Order o : c.viewUserHistory()) {
			System.out.println(o + "\n");
		}
	}
	
}
