package edu.colostate.cs414.d.pizza.net.res;

import edu.colostate.cs414.d.pizza.Kiosk;
import edu.colostate.cs414.d.pizza.api.menu.Coupon;
import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import edu.colostate.cs414.d.pizza.net.Errors;
import edu.colostate.cs414.d.pizza.net.UserRole;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/admin/coupon")
public class CouponAdminResource {
    
    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed(UserRole.MANAGER)
    public Coupon couponCreate(Coupon coupon) {
		int points = coupon.getRewardPoints();
        if (coupon.getRewardPoints() <= 0) {
            throw Errors.badRequest("Invalid coupon points: " + points);
        }
        
		int itemId = coupon.getMenuItem().getId();
        MenuItem item = Kiosk.getInstance().getMenuItem(itemId);
        if (item == null || !item.isActive()) {
            throw Errors.badRequest("Unknown menu item with id: " + itemId);
        }
        
        Coupon ret = new Coupon(item, points);
        Kiosk.getInstance().addCoupon(ret);
        
        return ret;
    }
    
    @Path("/remove")
    @POST
    @RolesAllowed(UserRole.MANAGER)
    public Response couponRemove(int couponId) {
        Coupon coupon = Kiosk.getInstance().getCoupon(couponId);
        if (coupon == null || !coupon.isActive()) {
            throw Errors.badRequest("No coupon found with id: " + couponId);
        }
        
        Kiosk.getInstance().removeCoupon(coupon);
        
        return Response.ok().build();
    }
    
}
