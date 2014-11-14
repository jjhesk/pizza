package edu.colostate.cs414.d.pizza.client;

import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.api.order.OrderItem;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class DailySpecialClient extends WebServiceClient {
	
	protected WebTarget root;
	
	public DailySpecialClient(String targetPath) {
		super(targetPath);
	}

	@Override
	protected void init() {
		super.init();
		
		root = target.path("/special");
	}
	
	public List<DailySpecial> getSpecials() {
		return readAndVerify(
				root.path("/list")
						.request(MediaType.APPLICATION_JSON)
						.get(),
				new GenericType<List<DailySpecial>>() {});
	}
	
	public List<OrderItem> getItems(int id) {
		return readAndVerify(
				root.path("/items")
						.request(MediaType.APPLICATION_JSON)
						.post(Entity.json(id)),
				new GenericType<List<OrderItem>>() {});
	}
	
	public static void main(String[] args) {
		for (DailySpecial i : new DailySpecialClient("http://localhost:8080").getSpecials()) {
			System.out.printf("%d - $%6.2f - %s%n", i.getID(), i.getPrice(), Arrays.toString(i.getItems().toArray()));
		}
	}
	
}
