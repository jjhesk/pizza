package edu.colostate.cs414.d.pizza.net;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/admin/menu")
public class MenuAdminResource {
    
    @Path("/item_create")
    @POST
    public void itemCreate(String token, String name, double price, String description) {
        // 
    }
    
    
}
