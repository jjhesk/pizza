package edu.colostate.cs414.d.pizza.net.res;

import edu.colostate.cs414.d.pizza.Kiosk;
import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/menu")
public class MenuResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MenuItem> getMenu() {
        return Kiosk.getInstance().viewMenu();
    }
    
}
