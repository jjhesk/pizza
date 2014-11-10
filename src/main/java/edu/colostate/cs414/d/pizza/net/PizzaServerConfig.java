package edu.colostate.cs414.d.pizza.net;

import edu.colostate.cs414.d.pizza.net.res.CouponAdminResource;
import edu.colostate.cs414.d.pizza.net.res.CouponResource;
import edu.colostate.cs414.d.pizza.net.res.DailySpecialAdminResource;
import edu.colostate.cs414.d.pizza.net.res.DailySpecialResource;
import edu.colostate.cs414.d.pizza.net.res.MenuAdminResource;
import edu.colostate.cs414.d.pizza.net.res.MenuResource;
import edu.colostate.cs414.d.pizza.net.res.OrderResource;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class PizzaServerConfig extends ResourceConfig {

    public PizzaServerConfig() {
        super(MenuResource.class,
                MenuAdminResource.class,
                DailySpecialResource.class,
                DailySpecialAdminResource.class,
                CouponResource.class,
                CouponAdminResource.class,
                OrderResource.class,
                AuthenticationFilter.class,
                JacksonFeature.class);
    }
    
}
