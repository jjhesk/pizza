package edu.colostate.cs414.d.pizza;

import edu.colostate.cs414.d.pizza.api.menu.Coupon;
import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import edu.colostate.cs414.d.pizza.api.menu.MenuManager;
import edu.colostate.cs414.d.pizza.api.order.Order;
import edu.colostate.cs414.d.pizza.api.order.OrderItem;
import edu.colostate.cs414.d.pizza.api.order.OrderManager;
import edu.colostate.cs414.d.pizza.api.order.payment.CardPayment;
import edu.colostate.cs414.d.pizza.api.order.payment.CashPayment;
import edu.colostate.cs414.d.pizza.api.order.payment.CheckPayment;
import edu.colostate.cs414.d.pizza.api.user.User;
import edu.colostate.cs414.d.pizza.api.user.UserManager;
import edu.colostate.cs414.d.pizza.api.user.UserType;

import java.util.List;

public class Kiosk {

    private MenuManager menuManager;
    private OrderManager orderManager;
    private UserManager userManager;
    private User loggedInUser;
    private static Kiosk instance;

    public static Kiosk getInstance() {
        if (instance == null) {
            instance = new Kiosk();
        }
        return instance;
    }

    private Kiosk(){
        menuManager = MenuManager.getInstance();
        orderManager = OrderManager.getInstance(menuManager.getAllMenuItems());
        userManager = UserManager.getInstance();
        loggedInUser = null;
    }

    //View Menu (returns active menu items)
    public List<MenuItem> viewMenu(){
        return menuManager.getActiveMenuItems();
    }

    //View Orders (returns pending orders)
	public List<Order> viewPendingOrders() {
            return orderManager.getPendingOrders();
	}

    //View delivered Orders (returns delivered orders)
    public List<Order> getDeliveredOrders() { return orderManager.getDeliveredOrders();}

    public List<Order> getUserOrders() { return orderManager.getUserOrders(this.loggedInUser.getUserName()); }

    public List<OrderItem> viewOrderItems(Order order) {
        return orderManager.getOrderItems(order);
    }

    //View Daily Specials
    public List<DailySpecial> viewDailySpecials(){
        return menuManager.getCurrentDailySpecials();
    }

    //Creating and modifying the Menu
    public MenuItem createMenuItem(String name, double price, String description){
        return menuManager.createMenuItem(name, price, description);
    }

    public void clearMenu() {
        menuManager.clearMenu();
    }

    public void addMenuItem(MenuItem menuItem){
        menuManager.addMenuItem(menuItem);
    }

    public void removeMenuItem(MenuItem menuItem){
        menuManager.removeMenuItem(menuItem);
    }

    //Creating/Editing Daily Special
    public DailySpecial createDailySpecial(List<MenuItem> menuItems, Double price){
        return menuManager.createDailySpecial(menuItems, price);
    }

    public void removeDailySpecial(DailySpecial special){
        menuManager.removeDailySpecial(special);
    }

    //Creating and placing an order
    public Order createOrder() {
        return orderManager.createOrder();
    }

    public OrderItem createOrderItem(MenuItem menuItem, int quantity) {
        return orderManager.createOrderItem(menuItem, quantity);
    }

    public List<OrderItem> createDailySpecialOrderItems(DailySpecial special) {
        return orderManager.createDailySpecialOrderItems(special);
    }

	public void placeOrder(Order order) {
        orderManager.addOrder(order);
	}
	
    //Making a payment
	public double calculateSubtotal(Order order, List<DailySpecial> dailySpecials) {
		return orderManager.calculateSubtotal(order, dailySpecials, this.viewDailySpecials());
	}
        
    public void submitCashPayment(Order order, double amountGiven) {
        CashPayment cash = new CashPayment(amountGiven);
        order.setPayment(cash);
    }
        
    public void submitCheckPayment(Order order, String name, int routingNo,
        int accountNo, int checkNo, double amtGiven) {
        CheckPayment check = new CheckPayment(name, routingNo, accountNo, checkNo, amtGiven);
        order.setPayment(check);
    }
        
    public void submitCardPayment(Order order, String name, int cardNo,
        int secCode, int expYear, int expMonth, String billingAddr,
        String billingCity, String billingZipCode) {
        CardPayment card = new CardPayment(name, cardNo, secCode, expYear,
        expMonth, billingAddr, billingCity, billingZipCode);
        order.setPayment(card);
    }

    //Completing Order
	public void completeOrder(Order order) {
		orderManager.completeOrder(order);
	}

    //Users
    public List<User> getUsers() {
        return userManager.getUsers();
    }

    public boolean addUser(String userName, String password, UserType userType){
        return userManager.addUser(userName, password, userType);
    }

    public User authenticateUser(String userName, String password) {
        return userManager.authenticateUser(userName,password);
    }

    public void removeUser(String userName) {
        userManager.removeUser(userName);
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
    
    public void loginUser(User user) {
        this.loggedInUser = user;
    }

    //View coupons (returns active coupons)
    public List<Coupon> viewCoupons(){
        return menuManager.getActiveCoupons();
    }

    //Deleting, adding coupons

    public void addCoupon(Coupon coupon){
        menuManager.addCoupon(coupon);
    }

    public void removeCoupon(Coupon coupon){
        menuManager.removeCoupon(coupon);
    }

    public void updateRewardPoints(int rewardPoints) { userManager.updateRewardPoints(this.loggedInUser, rewardPoints); }

    public User getUser(String userName){ return userManager.getUser(userName); }

    public DailySpecial getDailySpecial(int id){ return menuManager.getDailySpecial(id); }

    public MenuItem getMenuItem(int id){ return menuManager.getMenuItem(id); }

    public Order getOrder(int id){ return orderManager.getOrder(id); }

    public Coupon getCoupon(int id){ return menuManager.getCoupon(id);}

    public OrderManager getOrderManager() {
        return orderManager;
    }

    public MenuManager getMenuManager() {
        return menuManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }
    
}
