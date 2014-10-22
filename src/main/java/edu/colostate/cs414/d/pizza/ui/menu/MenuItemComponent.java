package edu.colostate.cs414.d.pizza.ui.menu;

import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import edu.colostate.cs414.d.pizza.api.order.OrderItem;
import edu.colostate.cs414.d.pizza.ui.event.MenuItemCreateEvent;
import edu.colostate.cs414.d.pizza.ui.event.MenuItemEditEvent;
import edu.colostate.cs414.d.pizza.ui.event.MenuItemRemoveEvent;
import edu.colostate.cs414.d.pizza.ui.event.OrderItemCreateEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import org.timothyb89.eventbus.EventBus;
import org.timothyb89.eventbus.EventBusClient;
import org.timothyb89.eventbus.EventBusProvider;

public class MenuItemComponent extends JComponent implements EventBusProvider {
	
	private final EventBus bus;
	
	private final MenuItem item;
	private final MenuFeature feature;
	
	@SuppressWarnings("OverridableMethodCallInConstructor")
	public MenuItemComponent(MenuItem item, MenuFeature feature) {
		this.item = item;
		this.feature = feature;
		
		bus = new EventBus() {{
			add(OrderItemCreateEvent.class);
			
			add(MenuItemCreateEvent.class);
			add(MenuItemEditEvent.class);
			add(MenuItemRemoveEvent.class);
		}};
		
		initComponents();
	}

	@Override
	public EventBusClient bus() {
		return bus.getClient();
	}
	
	protected void initComponents() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(0, 100));
		setBorder(BorderFactory.createCompoundBorder(
				new EtchedBorder(),
				new EmptyBorder(10, 10, 10, 10)));
		
		nameContainer = new JPanel(new GridLayout(0, 1));
		nameContainer.setOpaque(false);
		
		nameLabel = new JLabel(item.getName());
		nameLabel.setOpaque(false);
		nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD, 18));
		nameContainer.add(nameLabel);
		
		descriptionLabel = new JLabel(item.getDescription());
		descriptionLabel.setOpaque(false);
		descriptionLabel.setText("<html>" + item.getDescription());
		nameContainer.add(descriptionLabel);
		
		add(nameContainer, BorderLayout.WEST);
		
		priceLabel = new JLabel(String.format("$%.2f", item.getPrice()));
		priceLabel.setFont(priceLabel.getFont().deriveFont(Font.BOLD, 28));
		priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		priceLabel.setOpaque(false);
		priceLabel.setPreferredSize(new Dimension(150, 0));
		add(priceLabel, BorderLayout.EAST);
		
		if (feature == MenuFeature.ORDERING) {
			initOrderPanel();
		} else if (feature == MenuFeature.ADMIN) {
			initAdminPanel();
		}
	}
	
	protected void initOrderPanel() {
		buttonContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonContainer.add(new JLabel("Qty:"));

		quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		quantitySpinner.setPreferredSize(new Dimension(75, 25));
		buttonContainer.add(quantitySpinner);

		addButton = new JButton("Add to Order");
		addButton.addActionListener(orderAddListener);
		buttonContainer.add(addButton);

		add(buttonContainer, BorderLayout.SOUTH);
	}
	
	protected void initAdminPanel() {
		buttonContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		adminRemoveButton = new JButton("Remove");
		adminRemoveButton.addActionListener(adminRemoveListener);
		buttonContainer.add(adminRemoveButton);
		
		adminModifyButton = new JButton("Edit");
		adminModifyButton.addActionListener(adminModifyListener);
		buttonContainer.add(adminModifyButton);

		add(buttonContainer, BorderLayout.SOUTH);
	}
	
	private final ActionListener orderAddListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			OrderItem i = new OrderItem(item, (int) quantitySpinner.getValue());
			
			bus.push(new OrderItemCreateEvent(i));
			
			quantitySpinner.setValue(1);
		}
		
	};
	
	private final ActionListener adminRemoveListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			bus.push(new MenuItemRemoveEvent(item));
		}
		
	};
	
	private final ActionListener adminModifyListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			MenuItemEditDialog d = new MenuItemEditDialog(
					(JFrame) SwingUtilities.getWindowAncestor(MenuItemComponent.this),
					item);
			d.setVisible(true);
			
			// wait for user input ...
			
			MenuItem i = d.getReturnedItem();
			if (i == null) {
				return;
			}
			
			bus.push(new MenuItemEditEvent(item, i));
			
			// don't update the UI here
			// we'll be recreating the menu item and reinitializing the menu
		}
		
	};
	
	private JPanel nameContainer;
	private JLabel nameLabel;
	private JLabel descriptionLabel;
	
	private JLabel priceLabel;
	
	private JPanel buttonContainer;
	private JSpinner quantitySpinner;
	private JButton addButton;
	
	private JButton adminRemoveButton;
	private JButton adminModifyButton;
	
}
