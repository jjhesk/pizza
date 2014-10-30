package edu.colostate.cs414.d.pizza.net;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

public class PizzaServerConfig extends ResourceConfig {

    public PizzaServerConfig() {
        super(MenuResource.class,
                JacksonFeature.class);
    }
    
}
