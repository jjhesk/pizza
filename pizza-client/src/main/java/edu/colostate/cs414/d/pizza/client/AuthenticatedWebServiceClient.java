package edu.colostate.cs414.d.pizza.client;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class AuthenticatedWebServiceClient extends WebServiceClient {
	
	private String username;
	private String password;
	
	public AuthenticatedWebServiceClient(String target) {
		super(target);
	}
	
	@SuppressWarnings("OverridableMethodCallInConstructor")
	public AuthenticatedWebServiceClient(String target, String username, String password) {
		super(target);
		
		this.username = username;
		this.password = password;
	}

	@Override
	protected void subinit() {
		if (username != null && password != null) {
			client = client.register(HttpAuthenticationFeature.basic(username, password));
		}
	}
	
	public void authenticate(String username, String password) {
		this.username = username;
		this.password = password;
		
		init();
	}
	
}
