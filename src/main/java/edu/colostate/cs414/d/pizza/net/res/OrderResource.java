package edu.colostate.cs414.d.pizza.net.res;

import edu.colostate.cs414.d.pizza.Kiosk;
import edu.colostate.cs414.d.pizza.api.order.Order;
import edu.colostate.cs414.d.pizza.api.order.OrderType;
import edu.colostate.cs414.d.pizza.net.Errors;
import edu.colostate.cs414.d.pizza.net.UserRole;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/order")
public class OrderResource {
    
    @Path("/place")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed(UserRole.CUSTOMER)
    public Response placeOrder(Order order) {
        if (order.getCustomerName() == null || order.getCustomerName().isEmpty()) {
            throw Errors.badRequest("A name must be provided.");
        }
        
        if (order.getType() == OrderType.DELIVERY) {
            if (order.getCustomerAddress() == null || order.getCustomerAddress().isEmpty()) {
                throw Errors.badRequest("An address must be provided for delivery.");
            }
        }
        
        if (order.getItems().isEmpty()) {
            throw Errors.badRequest("Orders cannot be empty.");
        }
        
        Kiosk.getInstance().placeOrder(order);
        
        return Response.ok().build();
    }
    
    @Path("/history")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(UserRole.CUSTOMER)
    public List<Order> viewUserHistory() {
        // TODO order history
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Path("/view-pending")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(UserRole.CHEF)
    public List<Order> viewPending() {
        return Kiosk.getInstance().viewPendingOrders();
    }
    
    @Path("/complete")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(UserRole.CHEF)
    public Response complete(int id) {
        Order order = Kiosk.getInstance().getOrder(id);
        if (order == null) {
            throw Errors.badRequest("No order found with the given id: " + id);
        }
        
        Kiosk.getInstance().completeOrder(order);
        
        return Response.ok().build();
    }
    
}
