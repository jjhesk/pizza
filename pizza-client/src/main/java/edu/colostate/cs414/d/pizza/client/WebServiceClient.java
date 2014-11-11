package edu.colostate.cs414.d.pizza.client;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.filter.LoggingFilter;

public abstract class WebServiceClient {
	
	private String targetPath;
	protected Client client;
	protected WebTarget target;
	
	@SuppressWarnings("OverridableMethodCallInConstructor")
	public WebServiceClient(String targetPath) {
		this.targetPath = targetPath;
		
		init();
	}
	
	protected void init() {
		client = ClientBuilder.newClient()
				.register(LoggingFilter.class)
				.register(JacksonJsonProvider.class);
		
		subinit();
		
		target = client.target(targetPath);	
	}
	
	protected void subinit() {
		
	}
	
	protected void verify(Response resp) {
		// TODO: this could be a filter
		switch (resp.getStatusInfo().getFamily()) {
			case CLIENT_ERROR:
			case SERVER_ERROR:
				if (resp.hasEntity()) {
					throw new WebServiceException(resp.readEntity(String.class));
				} else {
					throw new WebServiceException("An unknown error occurred.");
				}
		}
	}
	
}
