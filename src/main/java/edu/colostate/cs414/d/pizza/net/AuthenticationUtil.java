package edu.colostate.cs414.d.pizza.net;

import edu.colostate.cs414.d.pizza.api.user.User;

public class AuthenticationUtil {
    
    public static User getUser(String key) {
        
        // user not found?
        // throw new ForbiddenException("Invalid authentication token");
        
        throw new UnsupportedOperationException();
    }
    
    public static void verifyManager(String key) {
        User user = getUser(key);
    }
    
}
