package edu.colostate.cs414.d.pizza.net.res;

import edu.colostate.cs414.d.pizza.Kiosk;
import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.net.Errors;
import edu.colostate.cs414.d.pizza.net.UserRole;
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
    public Response specialCreate(List<Integer> itemIds, double price) {
        // TODO
        
        if (true) {
            throw new UnsupportedOperationException("not implemented yet");
        }
        
        return Response.ok().build();
    }
    
    @Path("/remove")
    @POST
    @RolesAllowed(UserRole.MANAGER)
    public Response specialRemove(int id) {
        DailySpecial special = null; // getSpecial(id) ?
        if (special == null) {
            throw Errors.badRequest("No special found with id: " + id);
        }
        
        Kiosk.getInstance().removeDailySpecial(special);
        
        return Response.ok().build();
    }
    
}
