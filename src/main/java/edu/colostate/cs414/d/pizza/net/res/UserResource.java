package edu.colostate.cs414.d.pizza.net.res;

import edu.colostate.cs414.d.pizza.Kiosk;
import edu.colostate.cs414.d.pizza.api.user.User;
import edu.colostate.cs414.d.pizza.net.AuthenticationFilter;
import edu.colostate.cs414.d.pizza.net.Errors;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;

@Path("/user")
public class UserResource {
	
    @Context
    private ContainerRequestContext context;
    
    @GET
    @Path("/login")
    @RolesAllowed({})
    public User login() {
        String username = AuthenticationFilter.getUsername(context);
        
        User user = Kiosk.getInstance().getUser(username);
        if (user == null) {
            throw Errors.badRequest("Couldn't find user (?!) " + username);
        }
        
        return user;
    }
    
}
