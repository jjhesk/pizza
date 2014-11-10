package edu.colostate.cs414.d.pizza.net.res;

import edu.colostate.cs414.d.pizza.Kiosk;
import edu.colostate.cs414.d.pizza.api.menu.Coupon;
import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import edu.colostate.cs414.d.pizza.net.Errors;
import edu.colostate.cs414.d.pizza.net.UserRole;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/admin/coupon")
public class CouponAdminResource {
    
    @Path("/create")
    @POST
    @RolesAllowed(UserRole.MANAGER)
    public Response couponCreate(int itemId, int points) {
        if (points <= 0) {
            throw Errors.badRequest("Invalid coupon points: " + points);
        }
        
        MenuItem item = Kiosk.getInstance().getMenuItem(itemId);
        if (item == null) {
            throw Errors.badRequest("Unknown menu item with id: " + itemId);
        }
        
        Kiosk.getInstance().addCoupon(new Coupon(item, points));
        
        return Response.ok().build();
    }
    
    @Path("/remove")
    @POST
    @RolesAllowed(UserRole.MANAGER)
    public Response couponRemove(int couponId) {
        Coupon coupon = Kiosk.getInstance().getCoupon(couponId);
        if (coupon == null) {
            throw Errors.badRequest("No coupon found with id: " + couponId);
        }
        
        Kiosk.getInstance().removeCoupon(coupon);
        
        return Response.ok().build();
    }
    
}
