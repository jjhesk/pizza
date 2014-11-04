package edu.colostate.cs414.d.pizza.net;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

public class Errors {
    
    /**
     * Creates a new 400 error with the given message as a response entity.
     * @param message the error message to be included in the reply
     * @return a new exception instance
     */
    public static final BadRequestException badRequest(String message) {
        return new BadRequestException(Response
                .status(Response.Status.BAD_REQUEST)
                .entity(message)
                .build());
    }
    
    private Errors() {
        
    }
    
}
