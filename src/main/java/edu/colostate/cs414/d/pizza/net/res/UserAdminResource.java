package edu.colostate.cs414.d.pizza.net.res;

import edu.colostate.cs414.d.pizza.Kiosk;
import edu.colostate.cs414.d.pizza.api.user.User;
import edu.colostate.cs414.d.pizza.api.user.UserType;
import edu.colostate.cs414.d.pizza.net.Errors;
import edu.colostate.cs414.d.pizza.net.UserRole;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/admin/user")
public class UserAdminResource {

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed(UserRole.MANAGER)
    public Response userCreate(User user) {
        if (Kiosk.getInstance().addUser(
                user.getUserName(),
                user.getPassword(),
                user.getUserType())) {
            return Response.ok().build();
        } else {
            throw Errors.badRequest("User already exists:" + user.getUserName());
        }
    }
    
    @POST
    @Path("/remove")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed(UserRole.MANAGER)
    public void userRemove(String username) {
        User user = Kiosk.getInstance().getUser(username);
        if (user == null) {
            throw Errors.badRequest("User not found: " + username);
        }
        
        Kiosk.getInstance().removeUser(username);
    }
    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(UserRole.MANAGER)
    public List<User> getUsers() {
        return Kiosk.getInstance().getUsers();
    }
    
}
