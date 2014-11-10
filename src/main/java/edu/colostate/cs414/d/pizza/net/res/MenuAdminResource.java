package edu.colostate.cs414.d.pizza.net.res;

import edu.colostate.cs414.d.pizza.Kiosk;
import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import edu.colostate.cs414.d.pizza.net.Errors;
import edu.colostate.cs414.d.pizza.net.UserRole;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/admin/menu")
public class MenuAdminResource {
    
    @Path("/item-create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(UserRole.MANAGER)
    public MenuItem itemCreate(String name, double price, String description) {
        if (name == null || name.isEmpty()) {
            throw Errors.badRequest("A non-empty name is required");
        }
        
        if (price < 0) {
            throw Errors.badRequest("Price must not be less than zero");
        }
        
        if (description == null) {
            description = "";
        }
        
        MenuItem item = new MenuItem(name, price, description);
        
        Kiosk.getInstance().addMenuItem(item);
        
        return item;
    }
    
    @Path("/item-remove")
    @POST
    @RolesAllowed(UserRole.MANAGER)
    public Response itemRemove(int itemId) {
        MenuItem item = Kiosk.getInstance().getMenuItem(itemId);
        if (item == null) {
            throw Errors.badRequest("No item with id " + itemId + " found");
        }
        
        Kiosk.getInstance().removeMenuItem(item);
        
        return Response.ok().build();
    }
    
}
