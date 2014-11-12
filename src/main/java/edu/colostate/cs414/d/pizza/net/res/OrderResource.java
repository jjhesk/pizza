package edu.colostate.cs414.d.pizza.net.res;

import edu.colostate.cs414.d.pizza.Kiosk;
import edu.colostate.cs414.d.pizza.api.order.Order;
import edu.colostate.cs414.d.pizza.api.order.OrderManager;
import edu.colostate.cs414.d.pizza.api.order.OrderType;
import edu.colostate.cs414.d.pizza.net.AuthenticationFilter;
import edu.colostate.cs414.d.pizza.net.Errors;
import edu.colostate.cs414.d.pizza.net.UserRole;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/order")
public class OrderResource {
    
    @Context
    private ContainerRequestContext context;
    
    @Path("/place")
	@POST
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
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(UserRole.CUSTOMER)
    public List<Order> viewUserHistory() {
        String username = AuthenticationFilter.getUsername(context);
        
        return Kiosk.getInstance().getOrderManager().getUserOrders(username);
    }
    
    @Path("/view-pending")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(UserRole.CHEF)
    public List<Order> viewPending() {
        return Kiosk.getInstance().viewPendingOrders();
    }
    
    @Path("/view-delivered")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({UserRole.MANAGER, UserRole.CASHIER, UserRole.CHEF})
    public List<Order> viewDelivered() {
        return Kiosk.getInstance().getDeliveredOrders();
    }
    
    @Path("/complete")
	@POST
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
