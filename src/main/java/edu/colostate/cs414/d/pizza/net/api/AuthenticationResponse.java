package edu.colostate.cs414.d.pizza.net.api;

public class AuthenticationResponse {
    
    private final boolean successful;
    private final String key;
    
    public AuthenticationResponse(boolean successful, String key) {
        this.successful = successful;
        this.key = key;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getKey() {
        return key;
    }
    
    public static AuthenticationResponse failure() {
        return new AuthenticationResponse(false, null);
    }
    
    public static AuthenticationResponse success(String key) {
        return new AuthenticationResponse(true, key);
    }
    
}
