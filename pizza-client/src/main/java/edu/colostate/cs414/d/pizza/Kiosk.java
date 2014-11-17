package edu.colostate.cs414.d.pizza;

import edu.colostate.cs414.d.pizza.api.menu.Coupon;
import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.api.menu.PizzaMenuItem;
import edu.colostate.cs414.d.pizza.api.order.Order;
import edu.colostate.cs414.d.pizza.api.order.OrderItem;
import edu.colostate.cs414.d.pizza.api.order.payment.CardPayment;
import edu.colostate.cs414.d.pizza.api.order.payment.CashPayment;
import edu.colostate.cs414.d.pizza.api.order.payment.CheckPayment;
import edu.colostate.cs414.d.pizza.api.user.User;
import edu.colostate.cs414.d.pizza.api.user.UserType;
import edu.colostate.cs414.d.pizza.client.*;
import java.util.List;

/**
 * A client-side implementation of Kiosk. This attempts to mirror the old
 * server-side Kiosk implementation, but will make use of the various REST
 * client classes.
 */
public class Kiosk {
	
	private static Kiosk instance;
	
	private String target;
	private User loggedInUser;
	
	private UserClient userClient;
	private UserAdminClient userAdminClient;
	
	private MenuClient menuClient;
	private MenuAdminClient menuAdminClient;
	
	private DailySpecialClient dailySpecialClient;
	private DailySpecialAdminClient dailySpecialAdminClient;
	
	private CouponClient couponClient;
	private CouponAdminClient couponAdminClient;
	
	private OrderClient orderClient;
	
	private AuthenticatedWebServiceClient[] authClients;
	
	private Kiosk() {
		
	}
	
	public void initialize(String target) {
		this.target = target;
		
		loggedInUser = null;
		
		userClient = new UserClient(target);
		userAdminClient = new UserAdminClient(target);
		
		menuClient = new MenuClient(target);
		menuAdminClient = new MenuAdminClient(target);
		
		dailySpecialClient = new DailySpecialClient(target);
		dailySpecialAdminClient = new DailySpecialAdminClient(target);
		
		couponClient = new CouponClient(target);
		couponAdminClient = new CouponAdminClient(target);
		
		authClients = new AuthenticatedWebServiceClient[] {
			userAdminClient,
			menuAdminClient,
			dailySpecialAdminClient,
			couponAdminClient
		};
	}
	
	public static Kiosk getInstance() {
		if (instance == null) {
			instance = new Kiosk();
		}
		
		return instance;
	}
	
	public static void init(String target) {
		Kiosk k = getInstance();
		if (k.target != null) {
			k.initialize(target);
		}
	}
	
	private void authenticateAll(String username, String password) {
		for (AuthenticatedWebServiceClient c : authClients) {
			c.authenticate(username, password);
		}
	}
	
	private void checkInitialized() {
		if (target == null) {
			throw new IllegalStateException(
					"Kiosk not initialized with target path: call initialize()");
		}
	}
	
	public List<PizzaMenuItem> viewMenu() {
		checkInitialized();
		
		return menuClient.getMenu();
	}
	
	public List<Order> viewPendingOrders() {
		checkInitialized();
		
		return orderClient.viewPending();
	}
	
	public List<Order> getDeliveredOrders() {
		checkInitialized();
		
		return orderClient.viewDelivered();
	}
	
	public List<Order> getUserOrders() {
		checkInitialized();
		
		return orderClient.viewUserHistory();
	}
	
	public List<OrderItem> viewOrderItems(Order order) {
		return order.getItems();
	}
	
	public List<DailySpecial> viewDailySpecials() {
		checkInitialized();
		
		return dailySpecialClient.getSpecials();
	}
	
	public PizzaMenuItem createMenuItem(String name, double price, String description) {
		return new PizzaMenuItem(name, price, description);
	}
	
	public void clearMenu() {
		checkInitialized();
		
		menuAdminClient.clearMenu();
	}
	
	public void addMenuItem(PizzaMenuItem item) {
		checkInitialized();
		
		menuAdminClient.itemCreate(item);
	}
	
	public void removeMenuItem(PizzaMenuItem item) {
		checkInitialized();
		
		menuAdminClient.itemRemove(item.getId());
	}
	
	public DailySpecial createDailySpecial(List<PizzaMenuItem> items, Double price) {
		checkInitialized();
		
		return dailySpecialAdminClient.specialCreate(new DailySpecial(price, items));
	}
	
	public void removeDailySpecial(DailySpecial special) {
		checkInitialized();
		
		dailySpecialAdminClient.specialRemove(special.getID());
	}
	
	public Order createOrder() {
		return new Order();
	}
	
	public OrderItem createOrderItem(PizzaMenuItem item, int quantity) {
		return new OrderItem(item, quantity);
	}
	
	public List<OrderItem> createDailySpecialOrderItems(DailySpecial special) {
		checkInitialized();
		
		return dailySpecialClient.getItems(special.getID());
	}
	
	public void placeOrder(Order order) {
		checkInitialized();
		
		orderClient.placeOrder(order);
	}
	
	public double calculateSubtotal(Order order, List<DailySpecial> specials) {
		return order.calculateSubtotal(specials, viewDailySpecials());
	}
	
	public void submitCashPayment(Order order, double amountGiven) {
		// TODO: verify correct on client side
        CashPayment cash = new CashPayment(amountGiven);
        order.setPayment(cash);
    }
        
	public void submitCheckPayment(Order order, String name, int routingNo,
			int accountNo, int checkNo, double amtGiven) {
		// TODO: verify correct on client side
        CheckPayment check = new CheckPayment(name, routingNo, accountNo, checkNo, amtGiven);
        order.setPayment(check);
    }
        
	public void submitCardPayment(Order order, String name, int cardNo,
			int secCode, int expYear, int expMonth, String billingAddr,
			String billingCity, String billingZipCode) {
		// TODO: verify correct on client side
		CardPayment card = new CardPayment(name, cardNo, secCode, expYear,
				expMonth, billingAddr, billingCity, billingZipCode);
		order.setPayment(card);
	}
	
	public void completeOrder(Order order) {
		checkInitialized();
		
		orderClient.complete(order.getId());
	}
	
	public List<User> getUsers() {
		checkInitialized();
		
		return userAdminClient.getUsers();
	}
	
	public boolean addUser(String username, String password, UserType type) {
		checkInitialized();
		
		User user = new User(username, password, type, 0);
		
		try {
			userAdminClient.userCreate(user);
			return true;
		} catch (WebServiceException ex) {
			// derp
			return false;
		}
	}
	
	public void removeUser(String username) {
		userAdminClient.userRemove(username);
	}
	
	public User authenticateUser(String username, String password) {
		checkInitialized();
		
		authenticateAll(username, password);
		
		return userClient.login();
	}
	
	public User registerUser(String username, String password) {
		checkInitialized();
		
		return userClient.register(username, password);
	}
	
	public void loginUser(User user) {
		// TODO: ???
		// verify correct for client
		this.loggedInUser = user;
	}
	
	public List<Coupon> viewCoupons() {
		checkInitialized();
		
		return couponClient.getCoupons();
	}
	
	public void addCoupon(Coupon coupon) {
		checkInitialized();
		
		couponAdminClient.couponCreate(coupon);
	}
	
	public void removeCoupon(Coupon coupon) {
		checkInitialized();
		
		couponAdminClient.couponRemove(coupon.getId());
	}
	
	public void updateRewardPoints(int points) {
		// TODO: ???
		// not sure how to implement this
		throw new UnsupportedOperationException("not implemented yet");
	}

	
	
	public User getLoggedInUser() {
		return loggedInUser;
	}
	
}
