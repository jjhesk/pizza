package edu.colostate.cs414.d.pizza.client;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class DailySpecialClient {
	
	private Client client;
	private WebTarget root;
	
	public DailySpecialClient(String target) {
		client = ClientBuilder.newClient()
				.register(JacksonJsonProvider.class);
		
		root = client.target(target).path("/special");
	}
	
	public List<DailySpecial> getSpecials() {
		return root.path("/lsit").request(MediaType.APPLICATION_JSON)
				.get()
				.readEntity(new GenericType<List<DailySpecial>>() {});
	}
	
	public static void main(String[] args) {
		for (DailySpecial i : new DailySpecialClient("http://localhost:8080").getSpecials()) {
			System.out.println(i);
		}
	}
	
}
