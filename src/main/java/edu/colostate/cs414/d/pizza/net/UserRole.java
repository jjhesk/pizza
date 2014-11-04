package edu.colostate.cs414.d.pizza.net;

import edu.colostate.cs414.d.pizza.api.user.UserType;

/**
 * Role definitions following {@link UserType}. These need to be plain constant
 * strings so we can reference them from annotations (i.e. compile constants).
 */
public class UserRole {

    public static final String MANAGER  = "MANAGER";
    public static final String CHEF     = "CHEF";
    public static final String CASHIER  = "CASHIER";
    public static final String CUSTOMER = "CUSTOMER";
    
    private UserRole() { }
    
}
