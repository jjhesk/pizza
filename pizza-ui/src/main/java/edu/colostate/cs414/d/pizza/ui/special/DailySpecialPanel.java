package edu.colostate.cs414.d.pizza.ui.special;

import edu.colostate.cs414.d.pizza.Kiosk;
import edu.colostate.cs414.d.pizza.api.menu.DailySpecial;
import edu.colostate.cs414.d.pizza.ui.event.DailySpecialOrderAddedEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.timothyb89.eventbus.EventBus;
import org.timothyb89.eventbus.EventBusClient;
import org.timothyb89.eventbus.EventBusProvider;
import org.timothyb89.eventbus.EventHandler;
import org.timothyb89.eventbus.EventScanMode;
import org.timothyb89.eventbus.EventScanType;

@EventScanMode(type = EventScanType.EXTENDED)
public class DailySpecialPanel extends JPanel implements EventBusProvider {
	
	private final EventBus bus;
	private final Kiosk kiosk;
	
	public DailySpecialPanel() {
		bus = new EventBus() {{
			add(DailySpecialOrderAddedEvent.class);	
		}};
		
		kiosk = Kiosk.getInstance();
		
		initComponents();
		initSpecials();
	}

	@Override
	public EventBusClient bus() {
		return bus.getClient();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		
		container = new JPanel(new GridLayout(1, 0));
		
		scrollPane = new JScrollPane(container);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initSpecials() {
		for (DailySpecial special : kiosk.viewDailySpecials()) {
			DailySpecialComponent c = new DailySpecialComponent(special);
			c.bus().register(this);
			
			container.add(c);
		}
	}
	
	@EventHandler
	private void doSpecialOrderAdded(DailySpecialOrderAddedEvent event) {
		// push events up so clients can register to our bus for all events
		bus.push(event);
	}
	
	private JScrollPane scrollPane;
	private JPanel container;
	
}
