package edu.colostate.cs414.d.pizza.ui.component;

import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import edu.colostate.cs414.d.pizza.api.order.OrderItem;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import org.timothyb89.eventbus.EventBus;
import org.timothyb89.eventbus.EventBusClient;
import org.timothyb89.eventbus.EventBusProvider;

public class MenuItemComponent extends JComponent implements EventBusProvider {
	
	private final EventBus bus;
	
	private final MenuItem item;
	private final boolean orderingEnabled;
	
	public MenuItemComponent(MenuItem item, boolean orderingEnabled) {
		this.item = item;
		this.orderingEnabled = orderingEnabled;
		
		bus = new EventBus() {{
			add(OrderItemCreateEvent.class);
		}};
		
		initComponents();
	}

	@Override
	public EventBusClient bus() {
		return bus.getClient();
	}
	
	private void initComponents() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(0, 100));
		setBorder(BorderFactory.createEtchedBorder());
		
		nameContainer = new JPanel(new GridLayout(0, 1));
		
		nameLabel = new JLabel(item.getName());
		nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD));
		nameContainer.add(nameLabel);
		
		descriptionLabel = new JLabel(item.getDescription());
		descriptionLabel.setText("<html>" + item.getDescription());
		nameContainer.add(descriptionLabel);
		
		add(nameContainer, BorderLayout.WEST);
		
		priceLabel = new JLabel(String.format("$%.2f", item.getPrice()));
		priceLabel.setFont(priceLabel.getFont().deriveFont(Font.BOLD, 28));
		priceLabel.setPreferredSize(new Dimension(150, 0));
		add(priceLabel, BorderLayout.EAST);
		
		if (orderingEnabled) {
			buttonContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			buttonContainer.add(new JLabel("Qty:"));

			quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
			quantitySpinner.setPreferredSize(new Dimension(75, 25));
			buttonContainer.add(quantitySpinner);

			addButton = new JButton("Add to Order");
			addButton.addActionListener(addListener);
			buttonContainer.add(addButton);
			
			add(buttonContainer, BorderLayout.SOUTH);
		}
	}
	
	private final ActionListener addListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			OrderItem i = new OrderItem(item, (int) quantitySpinner.getValue());
			
			bus.push(new OrderItemCreateEvent(i));
			
			quantitySpinner.setValue(1);
		}
		
	};
	
	private JPanel nameContainer;
	private JLabel nameLabel;
	private JLabel descriptionLabel;
	
	private JLabel priceLabel;
	
	private JPanel buttonContainer;
	private JSpinner quantitySpinner;
	private JButton addButton;
	
}
