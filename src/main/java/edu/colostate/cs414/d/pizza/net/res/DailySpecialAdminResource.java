package edu.colostate.cs414.d.pizza.net.res;

import edu.colostate.cs414.d.pizza.Kiosk;
import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import edu.colostate.cs414.d.pizza.net.Errors;
import edu.colostate.cs414.d.pizza.net.UserRole;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/admin/special")
public class DailySpecialAdminResource {
    
    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed(UserRole.MANAGER)
    public Response specialCreate(DailySpecial special) {
        if (special.getItems().isEmpty()) {
            throw Errors.badRequest("Specials must contain at least one item");
        }
        
        List<MenuItem> foundItems = new LinkedList<>();
        for (MenuItem rawItem : special.getItems()) {
            MenuItem i = Kiosk.getInstance().getMenuItem(rawItem.getId());
            if (i == null) {
                throw Errors.badRequest("No menu item found with id: " + special.getID());
            }
            
            foundItems.add(i);
        }
        
        Kiosk.getInstance().createDailySpecial(foundItems, special.getPrice());
        
        return Response.ok().build();
    }
    
    @Path("/remove")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed(UserRole.MANAGER)
    public Response specialRemove(int id) {
        DailySpecial special = Kiosk.getInstance().getDailySpecial(id);
        if (special == null || !special.isActive()) {
            throw Errors.badRequest("No special found with id: " + id);
        }
        
        Kiosk.getInstance().removeDailySpecial(special);
        
        return Response.ok().build();
    }
    
}
