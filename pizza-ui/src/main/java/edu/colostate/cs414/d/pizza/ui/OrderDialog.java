package edu.colostate.cs414.d.pizza.ui;

import edu.colostate.cs414.d.pizza.Kiosk;
import edu.colostate.cs414.d.pizza.api.menu.Coupon;
import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.api.order.Order;
import edu.colostate.cs414.d.pizza.api.order.OrderItem;
import edu.colostate.cs414.d.pizza.api.order.OrderStatus;
import edu.colostate.cs414.d.pizza.api.order.OrderType;
import edu.colostate.cs414.d.pizza.api.user.Customer;
import edu.colostate.cs414.d.pizza.api.user.User;
import edu.colostate.cs414.d.pizza.api.user.UserType;
import edu.colostate.cs414.d.pizza.ui.coupon.CouponPanel;
import edu.colostate.cs414.d.pizza.ui.event.CouponOrderAddedEvent;
import edu.colostate.cs414.d.pizza.ui.event.DailySpecialOrderAddedEvent;
import edu.colostate.cs414.d.pizza.ui.event.OrderItemCreateEvent;
import edu.colostate.cs414.d.pizza.ui.menu.MenuFeature;
import edu.colostate.cs414.d.pizza.ui.menu.MenuPanel;
import edu.colostate.cs414.d.pizza.ui.special.DailySpecialPanel;
import edu.colostate.cs414.d.pizza.utilities.Utility;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.timothyb89.eventbus.EventHandler;
import org.timothyb89.eventbus.EventScanMode;
import org.timothyb89.eventbus.EventScanType;

@EventScanMode(type = EventScanType.EXTENDED)
public class OrderDialog extends JDialog {

	private final Kiosk kiosk;
    private int rewardPoints;
	
	private Map<DailySpecial, List<OrderItem>> specialMap;
	private Map<OrderItem, DailySpecial> specialReverseMap;
	
	/**
	 * Creates new form OrderDialog
	 */
	public OrderDialog(Frame parent, Kiosk kiosk) {
		super(parent, true);
		
		this.kiosk = kiosk;
        User loggedInUser = kiosk.getLoggedInUser();
		if(loggedInUser != null){
            rewardPoints = loggedInUser.getRewardPoints();
        }
        else{
            rewardPoints = 0;
        }

		specialMap = new HashMap<>();
		specialReverseMap = new HashMap<>();
        orderTableModel = new OrderItemTableModel();
        orderCouponTableModel = new OrderCouponTableModel();

                
		initComponents();
		initMenu();
		initSpecials();
        initCoupons();

        points.setText(String.valueOf(rewardPoints));
                
                orderTable.getSelectionModel().addListSelectionListener(selectionListener);
                orderCouponTable.getSelectionModel().addListSelectionListener(selectionListener1);
		setLocationRelativeTo(parent);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        orderTypeGroup = new ButtonGroup();
        orderPanel = new JPanel();
        removeItemButton = new JButton();
        orderTableScroll = new JScrollPane();
        orderTable = new JTable();
        subtotalLabel = new JLabel();
        subtotalField = new JLabel();
        taxLabel = new JLabel();
        taxField = new JLabel();
        totalField = new JLabel();
        totalLabel = new JLabel();
        placeOrderButton = new JButton();
        cancelOrderButton = new JButton();
        typeLabel = new JLabel();
        orderTypeTakeout = new JRadioButton();
        orderTypeDelivery = new JRadioButton();
        orderTypeEatIn = new JRadioButton();
        nameLabel = new JLabel();
        nameField = new JTextField();
        addressLabel = new JLabel();
        addressFieldScroll = new JScrollPane();
        addressField = new JTextArea();
        jLabel1 = new JLabel();
        points = new JLabel();
        orderCouponTableScroll = new JScrollPane();
        orderCouponTable = new JTable();
        removeCouponButton = new JButton();
        menuWrapper = new JPanel();
        specialsWrapper = new JPanel();
        couponWrapper = new JPanel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Place Order");
        setMinimumSize(new Dimension(400, 300));

        orderPanel.setBorder(BorderFactory.createTitledBorder("My Order"));

        removeItemButton.setText("Remove");
        removeItemButton.setEnabled(false);
        removeItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                removeItemButtonActionPerformed(evt);
            }
        });

        orderTable.setModel(orderTableModel);
        orderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderTable.getTableHeader().setReorderingAllowed(false);
        orderTableScroll.setViewportView(orderTable);

        subtotalLabel.setText("Subtotal:");

        subtotalField.setText("$0.00");

        taxLabel.setText("Tax:");

        taxField.setText("$0.00");

        totalField.setText("$0.00");

        totalLabel.setText("Total:");

        placeOrderButton.setIcon(new ImageIcon(getClass().getResource("/edu/colostate/cs414/d/pizza/ui/okay.png"))); // NOI18N
        placeOrderButton.setText("Place Order");
        placeOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                placeOrderButtonActionPerformed(evt);
            }
        });

        cancelOrderButton.setIcon(new ImageIcon(getClass().getResource("/edu/colostate/cs414/d/pizza/ui/cancel.png"))); // NOI18N
        cancelOrderButton.setText("Cancel Order");
        cancelOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelOrderButtonActionPerformed(evt);
            }
        });

        typeLabel.setText("Type:");

        orderTypeGroup.add(orderTypeTakeout);
        orderTypeTakeout.setSelected(true);
        orderTypeTakeout.setText("Takeout");

        orderTypeGroup.add(orderTypeDelivery);
        orderTypeDelivery.setText("Delivery");

        orderTypeGroup.add(orderTypeEatIn);
        orderTypeEatIn.setText("Eat In");

        nameLabel.setText("Name:");

        addressLabel.setText("Address:");

        addressField.setColumns(20);
        addressField.setRows(5);
        addressFieldScroll.setViewportView(addressField);

        jLabel1.setText("Reward Points:");

        points.setText("0");

        orderCouponTable.setModel(orderCouponTableModel);
        orderCouponTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderCouponTable.getTableHeader().setReorderingAllowed(false);
        orderCouponTableScroll.setViewportView(orderCouponTable);

        removeCouponButton.setText("Remove");
        removeCouponButton.setEnabled(false);
        removeCouponButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                removeCouponButtonActionPerformed(evt);
            }
        });

        GroupLayout orderPanelLayout = new GroupLayout(orderPanel);
        orderPanel.setLayout(orderPanelLayout);
        orderPanelLayout.setHorizontalGroup(orderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(orderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(orderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(orderPanelLayout.createSequentialGroup()
                        .addComponent(placeOrderButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelOrderButton))
                    .addGroup(orderPanelLayout.createSequentialGroup()
                        .addGroup(orderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(addressLabel)
                            .addComponent(nameLabel)
                            .addComponent(typeLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(orderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(orderPanelLayout.createSequentialGroup()
                                .addComponent(orderTypeTakeout)
                                .addGap(12, 12, 12)
                                .addComponent(orderTypeDelivery)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(orderTypeEatIn)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(nameField)
                            .addComponent(addressFieldScroll)))
                    .addComponent(orderTableScroll, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(orderCouponTableScroll, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(orderPanelLayout.createSequentialGroup()
                        .addGroup(orderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(orderPanelLayout.createSequentialGroup()
                                .addComponent(subtotalLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(subtotalField))
                            .addGroup(orderPanelLayout.createSequentialGroup()
                                .addComponent(taxLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(taxField))
                            .addGroup(GroupLayout.Alignment.TRAILING, orderPanelLayout.createSequentialGroup()
                                .addComponent(totalLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(totalField))
                            .addGroup(orderPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(points)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(GroupLayout.Alignment.TRAILING, orderPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(orderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(removeItemButton, GroupLayout.Alignment.TRAILING)
                            .addComponent(removeCouponButton, GroupLayout.Alignment.TRAILING)))))
        );
        orderPanelLayout.setVerticalGroup(orderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(orderPanelLayout.createSequentialGroup()
                .addComponent(orderTableScroll, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(removeItemButton)
                .addGap(18, 18, 18)
                .addComponent(orderCouponTableScroll, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(removeCouponButton)
                .addGap(18, 18, 18)
                .addGroup(orderPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(points, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(orderPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(orderTypeTakeout)
                    .addComponent(orderTypeDelivery)
                    .addComponent(orderTypeEatIn)
                    .addComponent(typeLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(orderPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(orderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(addressLabel)
                    .addComponent(addressFieldScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(orderPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(subtotalLabel)
                    .addComponent(subtotalField))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(orderPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(taxLabel)
                    .addComponent(taxField))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(orderPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(totalField)
                    .addComponent(totalLabel))
                .addGap(18, 18, 18)
                .addGroup(orderPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelOrderButton)
                    .addComponent(placeOrderButton))
                .addContainerGap())
        );

        menuWrapper.setBorder(BorderFactory.createTitledBorder("Menu"));
        menuWrapper.setLayout(new BorderLayout());

        specialsWrapper.setBorder(BorderFactory.createTitledBorder("Today's Specials"));
        specialsWrapper.setLayout(new BorderLayout());

        couponWrapper.setBorder(BorderFactory.createTitledBorder("Certificates"));
        couponWrapper.setLayout(new BorderLayout());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(menuWrapper, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(specialsWrapper, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(couponWrapper, GroupLayout.PREFERRED_SIZE, 549, GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(orderPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(orderPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuWrapper, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(specialsWrapper, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(couponWrapper, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        couponWrapper.getAccessibleContext().setAccessibleName("Coupons");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void placeOrderButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_placeOrderButtonActionPerformed
        if (nameField.getText().isEmpty()) {
            error("You must provide a name.");
            return;
        }
        
        if (orderTypeDelivery.isSelected()) {
            if (addressField.getText().isEmpty()) {
                error("You must provide an address for delivery orders.");
                return;
            }
        }
        
        Order order = createOrder();

        User loggedInUser = kiosk.getLoggedInUser();

        if(loggedInUser.getUserType().equals(UserType.CUSTOMER)){
            order.setUserName(loggedInUser.getUserName());
        }
        
        double total = updateTotals();
        
        if(orderTableModel.getItems().isEmpty() && orderCouponTableModel.getItems().isEmpty()){
            error("You must order 1 or more items in order to place an order");
            return;
        }

        
        PaymentDialog paymentDialog = new PaymentDialog(this, kiosk, order, total);
        paymentDialog.setVisible(true);
          
        if(order.getStatus() != OrderStatus.PENDING){
            error("Incorrect Payment was given please try again");
            return;
        }

        for(Coupon coupon : orderCouponTableModel.getItems()){
            order.getItems().add(new OrderItem(coupon.getMenuItem(), 1));
        }

        kiosk.placeOrder(order);
        
        rewardPoints++;

        if(loggedInUser.getUserType().equals(UserType.CUSTOMER)){
            kiosk.updateRewardPoints(rewardPoints);
        }

        dispose();
    }//GEN-LAST:event_placeOrderButtonActionPerformed

    private void cancelOrderButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_cancelOrderButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelOrderButtonActionPerformed

    private void removeItemButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_removeItemButtonActionPerformed
                int row = orderTable.getSelectedRow();
		if (row < 0) {
			error("No order item selected!");
			return;
		}
		
		OrderItem item = orderTableModel.getItem(row);
                if (specialReverseMap.containsKey(item)) {
			// remove the whole special from the order if one item is removed
			DailySpecial special = specialReverseMap.get(item);
			
			for (OrderItem other : specialMap.get(special)) {
				specialReverseMap.remove(other);
			}
			
			specialMap.remove(special);
		}
		
                orderTableModel.removeItem(item);
        
                updateTotals();
		
		if (orderTableModel.getItems().isEmpty()) {
			removeItemButton.setEnabled(false);
		}
    }//GEN-LAST:event_removeItemButtonActionPerformed

    private void removeCouponButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_removeCouponButtonActionPerformed
         int row = orderCouponTable.getSelectedRow();
		if (row < 0) {
			error("No order item selected!");
			return;
		}
		
		Coupon item = orderCouponTableModel.getItem(row);
                rewardPoints += item.getRewardPoints();
                points.setText(String.valueOf(rewardPoints));
                orderCouponTableModel.removeItem(item);
		
		if (orderCouponTableModel.getItems().isEmpty()) {
			removeCouponButton.setEnabled(false);
		}
    }//GEN-LAST:event_removeCouponButtonActionPerformed

	private void initMenu() {
        MenuPanel panel = new MenuPanel(kiosk.viewMenu(), MenuFeature.ORDERING);
        panel.bus().register(this);
        
	menuWrapper.add(panel, BorderLayout.CENTER);
	}
	
	private void initSpecials() {
		specialsPanel = new DailySpecialPanel();
		specialsPanel.bus().register(this);
		specialsWrapper.add(specialsPanel, BorderLayout.CENTER);
	}

    private void initCoupons() {
        couponPanel = new CouponPanel();
        couponPanel.bus().register(this);
        couponWrapper.add(couponPanel, BorderLayout.CENTER);
    }
    
    private void error(String message) {
        JOptionPane.showMessageDialog(
                this, message, "Error",
                JOptionPane.ERROR_MESSAGE);
    }
    
    private OrderType getOrderType() {
        if (orderTypeTakeout.isSelected()) {
            return OrderType.PICKUP;
        } else if (orderTypeDelivery.isSelected()) {
            return OrderType.DELIVERY;
        } else if (orderTypeEatIn.isSelected()) {
            return OrderType.EATIN;
        } else {
            return null;
        }
    }
    
    private Order createOrder() {
        Order ret = new Order(
                getOrderType(),
                nameField.getText(),
                addressField.getText());
		
		ret.setStartDate(new Date());
		ret.setStatus(OrderStatus.NEW);
        
        for (OrderItem item : orderTableModel.getItems()) {
            ret.addItem(item);
        }
        
        return ret;
    }
	
	private List<DailySpecial> getSpecials() {
		List<DailySpecial> ret = new ArrayList<>();
		ret.addAll(specialMap.keySet());
		
		return ret;
	}
    
    private double updateTotals() {
        // TODO: daily specials
        double subtotal = kiosk.calculateSubtotal(createOrder(), getSpecials());
        double tax = Utility.calculateTax(subtotal);
        double total = Utility.calculateTotalWithTax(subtotal);
        
        subtotalField.setText(String.format("$%.2f", subtotal));
        taxField.setText(String.format("$%.2f", tax));
        totalField.setText(String.format("$%.2f", total));
        
        return total;
    }
    
    @EventHandler
    private void doOrderItemAdded(OrderItemCreateEvent event) {
        orderTableModel.addItem(event.getItem());
        
        updateTotals();
    }
    
	@EventHandler
	private void doSpecialOrderAdded(DailySpecialOrderAddedEvent event) {
		DailySpecial special = event.getSpecial();
		
		List<OrderItem> items = kiosk.createDailySpecialOrderItems(special);
		specialMap.put(special, items);
		
		for (OrderItem i : items) {
			orderTableModel.addItem(i);
			specialReverseMap.put(i, special);
		}
		
		updateTotals();
	}
	
    @EventHandler
    private void doCouponOrderAdded(CouponOrderAddedEvent event) {
        Coupon coupon = event.getCoupon();
        if(rewardPoints < coupon.getRewardPoints()){
            error("Not enought points to redeem");
            return;
        }
        orderCouponTableModel.addItem(event.getCoupon());
        rewardPoints -= coupon.getRewardPoints();
        points.setText(String.valueOf(rewardPoints));
    }
    
    private final ListSelectionListener selectionListener = new ListSelectionListener() {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            removeItemButton.setEnabled(true);
        }
        
    };
    
    private final ListSelectionListener selectionListener1 = new ListSelectionListener() {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            removeCouponButton.setEnabled(true);
        }
        
    };
    
    private OrderItemTableModel orderTableModel;
    private OrderCouponTableModel orderCouponTableModel;
    private DailySpecialPanel specialsPanel;
    private CouponPanel couponPanel;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JTextArea addressField;
    private JScrollPane addressFieldScroll;
    private JLabel addressLabel;
    private JButton cancelOrderButton;
    private JPanel couponWrapper;
    private JLabel jLabel1;
    private JPanel menuWrapper;
    private JTextField nameField;
    private JLabel nameLabel;
    private JTable orderCouponTable;
    private JScrollPane orderCouponTableScroll;
    private JPanel orderPanel;
    private JTable orderTable;
    private JScrollPane orderTableScroll;
    private JRadioButton orderTypeDelivery;
    private JRadioButton orderTypeEatIn;
    private ButtonGroup orderTypeGroup;
    private JRadioButton orderTypeTakeout;
    private JButton placeOrderButton;
    private JLabel points;
    private JButton removeCouponButton;
    private JButton removeItemButton;
    private JPanel specialsWrapper;
    private JLabel subtotalField;
    private JLabel subtotalLabel;
    private JLabel taxField;
    private JLabel taxLabel;
    private JLabel totalField;
    private JLabel totalLabel;
    private JLabel typeLabel;
    // End of variables declaration//GEN-END:variables
}
