package edu.colostate.cs414.d.pizza.net.res;

import edu.colostate.cs414.d.pizza.Kiosk;
import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import edu.colostate.cs414.d.pizza.net.Errors;
import edu.colostate.cs414.d.pizza.net.UserRole;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/admin/menu")
public class MenuAdminResource {
    
    @Path("/item-create")
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(UserRole.MANAGER)
    public MenuItem itemCreate(MenuItem item) {
        if (item.getName() == null || item.getName().isEmpty()) {
            throw Errors.badRequest("A non-empty name is required");
        }
        
        if (item.getPrice() < 0) {
            throw Errors.badRequest("Price must not be less than zero");
        }
        
        if (item.getDescription() == null) {
            item.setDescription("");
        }
        
        Kiosk.getInstance().addMenuItem(item);
        
        return item;
    }
    
    @Path("/item-remove")
    @POST
    @RolesAllowed(UserRole.MANAGER)
    public Response itemRemove(int itemId) {
        MenuItem item = Kiosk.getInstance().getMenuItem(itemId);
        if (item == null || !item.isActive()) {
            throw Errors.badRequest("No item with id " + itemId + " found");
        }
        
        Kiosk.getInstance().removeMenuItem(item);
        
        return Response.ok().build();
    }
    
    @GET
    @Path("/clear")
    @RolesAllowed(UserRole.MANAGER)
    public Response clearMenu() {
        Kiosk.getInstance().clearMenu();
        
        return Response.ok().build();
    }
    
}
