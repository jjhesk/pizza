package edu.colostate.cs414.d.pizza.net.res;

import edu.colostate.cs414.d.pizza.Kiosk;
import edu.colostate.cs414.d.pizza.api.menu.Coupon;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/coupon")
public class CouponResource {
    
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Coupon> getCoupons() {
        return Kiosk.getInstance().viewCoupons();
    }
    
}
