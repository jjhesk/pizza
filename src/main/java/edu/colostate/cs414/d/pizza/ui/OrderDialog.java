package edu.colostate.cs414.d.pizza.ui;

import edu.colostate.cs414.d.pizza.Kiosk;
import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.api.order.Order;
import edu.colostate.cs414.d.pizza.api.order.OrderItem;
import edu.colostate.cs414.d.pizza.api.order.OrderType;
import edu.colostate.cs414.d.pizza.ui.event.OrderItemCreateEvent;
import edu.colostate.cs414.d.pizza.ui.menu.MenuFeature;
import edu.colostate.cs414.d.pizza.ui.menu.MenuPanel;
import edu.colostate.cs414.d.pizza.utilities.Utility;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
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
	
	/**
	 * Creates new form OrderDialog
	 */
	public OrderDialog(Frame parent, Kiosk kiosk) {
		super(parent, true);
		
		this.kiosk = kiosk;
		
        orderTableModel = new OrderItemTableModel();
        
		initComponents();
		initMenu();
		
        orderTable.getSelectionModel().addListSelectionListener(selectionListener);
        
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
        menuWrapper = new JPanel();
        specialsPanel = new JPanel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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

        GroupLayout orderPanelLayout = new GroupLayout(orderPanel);
        orderPanel.setLayout(orderPanelLayout);
        orderPanelLayout.setHorizontalGroup(
            orderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(orderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(orderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(orderPanelLayout.createSequentialGroup()
                        .addComponent(placeOrderButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelOrderButton))
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
                                .addComponent(totalField)))
                        .addContainerGap())
                    .addGroup(orderPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(removeItemButton))
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
                            .addComponent(addressFieldScroll, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)))))
            .addComponent(orderTableScroll, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        orderPanelLayout.setVerticalGroup(
            orderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(orderPanelLayout.createSequentialGroup()
                .addComponent(orderTableScroll, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeItemButton)
                .addGap(27, 27, 27)
                .addGroup(orderPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(orderTypeTakeout)
                    .addComponent(orderTypeDelivery)
                    .addComponent(orderTypeEatIn)
                    .addComponent(typeLabel))
                .addGap(18, 18, 18)
                .addGroup(orderPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(orderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(addressLabel)
                    .addComponent(addressFieldScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
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

        specialsPanel.setBorder(BorderFactory.createTitledBorder("Today's Specials"));

        GroupLayout specialsPanelLayout = new GroupLayout(specialsPanel);
        specialsPanel.setLayout(specialsPanelLayout);
        specialsPanelLayout.setHorizontalGroup(
            specialsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        specialsPanelLayout.setVerticalGroup(
            specialsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(menuWrapper, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
                    .addComponent(specialsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orderPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(orderPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuWrapper, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(specialsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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
        kiosk.placeOrder(order);
        
        // TODO: show payment dialog here
        JOptionPane.showMessageDialog(this, "TODO show payment dialog");
        
        dispose();
    }//GEN-LAST:event_placeOrderButtonActionPerformed

    private void cancelOrderButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_cancelOrderButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelOrderButtonActionPerformed

    private void removeItemButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_removeItemButtonActionPerformed
        OrderItem item = orderTableModel.getItem(orderTable.getSelectedRow());
        
        orderTableModel.removeItem(item);
        
        updateTotals();
    }//GEN-LAST:event_removeItemButtonActionPerformed

	private void initMenu() {
		MenuPanel panel = new MenuPanel(kiosk.viewMenu(), MenuFeature.ORDERING);
        panel.bus().register(this);
        
		menuWrapper.add(panel, BorderLayout.CENTER);
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
        
        for (OrderItem item : orderTableModel.getItems()) {
            ret.addItem(item);
        }
        
        return ret;
    }
    
    private void updateTotals() {
        // TODO: daily specials
        List<DailySpecial> dummy = new ArrayList<>();
        double subtotal = kiosk.calculateSubtotal(createOrder(), dummy);
        double tax = Utility.calculateTax(subtotal);
        double total = Utility.calculateTotalWithTax(subtotal);
        
        subtotalField.setText(String.format("$%.2f", subtotal));
        taxField.setText(String.format("$%.2f", tax));
        totalField.setText(String.format("$%.2f", total));
    }
    
    @EventHandler
    private void doOrderItemAdded(OrderItemCreateEvent event) {
        orderTableModel.addItem(event.getItem());
        
        updateTotals();
    }
    
    private final ListSelectionListener selectionListener = new ListSelectionListener() {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            removeItemButton.setEnabled(true);
        }
        
    };
    
    private OrderItemTableModel orderTableModel;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JTextArea addressField;
    private JScrollPane addressFieldScroll;
    private JLabel addressLabel;
    private JButton cancelOrderButton;
    private JPanel menuWrapper;
    private JTextField nameField;
    private JLabel nameLabel;
    private JPanel orderPanel;
    private JTable orderTable;
    private JScrollPane orderTableScroll;
    private JRadioButton orderTypeDelivery;
    private JRadioButton orderTypeEatIn;
    private ButtonGroup orderTypeGroup;
    private JRadioButton orderTypeTakeout;
    private JButton placeOrderButton;
    private JButton removeItemButton;
    private JPanel specialsPanel;
    private JLabel subtotalField;
    private JLabel subtotalLabel;
    private JLabel taxField;
    private JLabel taxLabel;
    private JLabel totalField;
    private JLabel totalLabel;
    private JLabel typeLabel;
    // End of variables declaration//GEN-END:variables
}
