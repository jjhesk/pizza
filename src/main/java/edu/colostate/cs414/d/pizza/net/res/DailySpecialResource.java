package edu.colostate.cs414.d.pizza.net.res;

import edu.colostate.cs414.d.pizza.Kiosk;
import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.api.order.OrderItem;
import edu.colostate.cs414.d.pizza.net.Errors;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/special")
public class DailySpecialResource {
    
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DailySpecial> getSpecials() {
        return Kiosk.getInstance().viewDailySpecials();
    }
    
    @Path("/items")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderItem> getItems(int id) {
        DailySpecial special = Kiosk.getInstance().getDailySpecial(id);
        if (special == null) {
            throw Errors.badRequest("No special found with id: " + id);
        }
        
        return Kiosk.getInstance().createDailySpecialOrderItems(special);
    }
    
}
