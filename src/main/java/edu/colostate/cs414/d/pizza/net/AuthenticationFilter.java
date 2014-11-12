package edu.colostate.cs414.d.pizza.net;

import edu.colostate.cs414.d.pizza.api.user.User;
import edu.colostate.cs414.d.pizza.api.user.UserManager;
import java.io.IOException;
import java.lang.reflect.Method;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.glassfish.grizzly.http.util.Base64Utils;

/**
 * Checks for authentication on incoming requests. Resource methods annotated
 * with @{@link RolesAllowed} or @{@link DenyAll} will automatically have users
 * of the incorrect type denied.
 */
@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String CHALLENGE = "Basic realm=\"pizza\"";

    @Context
    private ResourceInfo resourceInfo;
	
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Method m = resourceInfo.getResourceMethod();
        if (m.isAnnotationPresent(DenyAll.class)) {
            throw new ForbiddenException();
        }
        
        // if no roles are defined, move on
        if (!m.isAnnotationPresent(RolesAllowed.class)) {
            return;
        }

        String[] roles = m.getAnnotation(RolesAllowed.class).value();
        
        String[] auth = getAuthHeader(requestContext);
        String username = auth[0];
        String password = auth[1];

        User user = UserManager.getInstance().authenticateUser(username, password);
        if (user == null) {
            throw new WebApplicationException(respond401("Invalid credentials"));
        }
        
        boolean allowed = false;
        for (String role : roles) {
            if (role.equalsIgnoreCase(user.getUserType().name())) {
                allowed = true;
                break;
            }
        }

        if (!allowed) {
            throw new WebApplicationException(respond401("Not allowed"));
        }
    }

    public static String[] getAuthHeader(ContainerRequestContext request) {
        String header = request.getHeaderString("Authorization");
        if (header == null || header.isEmpty()) {
            throw new WebApplicationException(respond401("No credentials provided"));
        }

        if (header.startsWith("Basic") || header.startsWith("basic")) {
            header = header.replaceFirst("[bB]asic ", "");

            byte[] data = Base64Utils.decode(header.toCharArray());
            String authStr = new String(data);

            return authStr.split(":", 2);
        } else {
            throw new WebApplicationException(respond401("Invalid credential type"));
        }
    }
    
    public static String getUsername(ContainerRequestContext request) {
        return getAuthHeader(request)[0];
    }
    
    private static Response respond401(Object entity) {
        return Response
                .status(Response.Status.UNAUTHORIZED)
                .header(HttpHeaders.WWW_AUTHENTICATE, CHALLENGE)
                .entity(entity)
                .build();
    }

}
