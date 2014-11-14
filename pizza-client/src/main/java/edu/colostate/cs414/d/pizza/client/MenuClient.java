package edu.colostate.cs414.d.pizza.client;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import edu.colostate.cs414.d.pizza.api.menu.PizzaMenuItem;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.client.ClientResponse;

public class MenuClient extends WebServiceClient {

	private Client client;
	private WebTarget root;
	
	public MenuClient(String targetPath) {
		super(targetPath);
	}

	@Override
	protected void init() {
		super.init();
		
		root = target.path("/menu");
	}
	
	public List<PizzaMenuItem> getMenu() {
		return readAndVerify(
				root.request(MediaType.APPLICATION_JSON).get(),
				new GenericType<List<PizzaMenuItem>>() {});
	}
	
	public static void main(String[] args) {
		for (PizzaMenuItem i : new MenuClient("http://localhost:8080").getMenu()) {
			System.out.println(i);
		}
	}
	
}
