package edu.colostate.cs414.d.pizza.ui.coupon;

import edu.colostate.cs414.d.pizza.ui.special.*;
import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import edu.colostate.cs414.d.pizza.ui.event.DailySpecialOrderAddedEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import org.timothyb89.eventbus.EventBus;
import org.timothyb89.eventbus.EventBusClient;
import org.timothyb89.eventbus.EventBusProvider;

public class CouponComponent extends JComponent implements EventBusProvider {

	private final DailySpecial special;
	
	private final EventBus bus;
	
	public CouponComponent(DailySpecial special) {
		this.special = special;
		
		bus = new EventBus() {{
			add(DailySpecialOrderAddedEvent.class);
		}};
		
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEtchedBorder());
		setPreferredSize(new Dimension(250, 0));
		
		StringBuilder b = new StringBuilder("<html><ul>");
		for (MenuItem i : special.getItems()) {
			b.append("<li>");
			b.append(i.getName());
			b.append("</li>");
		}
		itemsLabel = new JLabel(b.toString());
		itemsLabel.setVerticalTextPosition(SwingConstants.TOP);
		itemsLabel.setVerticalAlignment(SwingConstants.TOP);
		
		JScrollPane labelScroll = new JScrollPane(itemsLabel);
		labelScroll.setBorder(null);
		labelScroll.setViewportBorder(null);
		//labelScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		labelScroll.setPreferredSize(new Dimension(200, 75));
		add(labelScroll, BorderLayout.CENTER);
		
		priceLabel = new JLabel(String.format("$%.2f", special.getPrice()));
		priceLabel.setFont(priceLabel.getFont().deriveFont(Font.BOLD, 28));
		priceLabel.setPreferredSize(new Dimension(100, 0));
		priceLabel.setMaximumSize(new Dimension(150, 150));
		priceLabel.setVerticalAlignment(SwingConstants.CENTER);
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(priceLabel, BorderLayout.EAST);
		
		button = new JButton("Add");
		button.setIcon(new ImageIcon(getClass().getResource(
				"/edu/colostate/cs414/d/pizza/ui/add-tiny.png")));
		button.addActionListener(addButttonPressed);
		add(button, BorderLayout.SOUTH);
	}

	@Override
	public EventBusClient bus() {
		return bus.getClient();
	}
	
	private final ActionListener addButttonPressed = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			bus.push(new DailySpecialOrderAddedEvent(special));
		}
		
	};
	
	private JLabel itemsLabel;
	private JLabel priceLabel;
	private JButton button;
	
}
