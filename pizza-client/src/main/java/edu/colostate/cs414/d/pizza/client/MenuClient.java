package edu.colostate.cs414.d.pizza.client;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.client.ClientResponse;

public class MenuClient {

	private Client client;
	private WebTarget root;
	
	public MenuClient(String target) {
		client = ClientBuilder.newClient()
				.register(JacksonJsonProvider.class);
		
		root = client.target(target);
	}
	
	public List<MenuItem> getMenu() {
		return root.path("/menu").request(MediaType.APPLICATION_JSON)
				.get()
				.readEntity(new GenericType<List<MenuItem>>() {});
	}
	
	public static void main(String[] args) {
		for (MenuItem i : new MenuClient("http://localhost:8080").getMenu()) {
			System.out.println(i);
		}
	}
	
}
