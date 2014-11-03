package edu.colostate.cs414.d.pizza.net;

import edu.colostate.cs414.d.pizza.net.api.AuthenticationResponse;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserResource {
    
    @Path("/request_token")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticationResponse requestToken(String username, String password) {
        // attempt a login, if succesful, generate a key and return it
        
        // TODO!
        return AuthenticationResponse.failure();
    }
    
    @Path("/invalidate_token")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response invalidateToken(String token) {
        // TODO: invalidate login
        
        // check key
        
        // throw an error if invalid
        // if (!checkKey()) {
        throw new ForbiddenException("Invalid authentication token");
        // }
        
        // return okay otherwise
        // return Response.ok();
    }
    
}
