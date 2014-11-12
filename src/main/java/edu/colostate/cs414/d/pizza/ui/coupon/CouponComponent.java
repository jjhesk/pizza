package edu.colostate.cs414.d.pizza.ui.coupon;

import edu.colostate.cs414.d.pizza.api.menu.Coupon;
import edu.colostate.cs414.d.pizza.ui.event.CouponOrderAddedEvent;
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

	private final Coupon coupon;
	
	private final EventBus bus;
	
	public CouponComponent(Coupon coupon) {
		this.coupon = coupon;
		
		bus = new EventBus() {{
			add(CouponOrderAddedEvent.class);
		}};
		
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEtchedBorder());
		setPreferredSize(new Dimension(300, 0));

        StringBuilder b = new StringBuilder("<html><ul>");
        b.append("<li>");
        b.append(coupon.getMenuItem().getName());
        b.append("</li>");

        itemsLabel = new JLabel(b.toString());
        itemsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        itemsLabel.setVerticalAlignment(SwingConstants.CENTER);
		itemsLabel.setVerticalTextPosition(SwingConstants.TOP);

		
		JScrollPane labelScroll = new JScrollPane(itemsLabel);
		labelScroll.setBorder(null);
		labelScroll.setViewportBorder(null);
		//labelScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		labelScroll.setPreferredSize(new Dimension(200, 75));
		add(labelScroll, BorderLayout.CENTER);


        if(coupon.getRewardPoints() == 1){
            rewardPoints = new JLabel(String.format("%d reward point", coupon.getRewardPoints()));
        }
        else{
            rewardPoints = new JLabel(String.format("%d reward points", coupon.getRewardPoints()));
        }

		rewardPoints.setFont(rewardPoints.getFont().deriveFont(Font.BOLD, 12));
		rewardPoints.setPreferredSize(new Dimension(150, 0));
		rewardPoints.setMaximumSize(new Dimension(150, 150));
		rewardPoints.setVerticalAlignment(SwingConstants.CENTER);
		rewardPoints.setHorizontalAlignment(SwingConstants.CENTER);
		add(rewardPoints, BorderLayout.EAST);
		
		button = new JButton("Redeem");
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
			bus.push(new CouponOrderAddedEvent(coupon));
		}
		
	};
	
	private JLabel itemsLabel;
	private JLabel rewardPoints;
	private JButton button;
	
}
