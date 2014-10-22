package edu.colostate.cs414.d.pizza.ui.menu;

import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import edu.colostate.cs414.d.pizza.ui.event.MenuItemCreateEvent;
import edu.colostate.cs414.d.pizza.ui.event.MenuItemEditEvent;
import edu.colostate.cs414.d.pizza.ui.event.MenuItemRemoveEvent;
import edu.colostate.cs414.d.pizza.ui.event.OrderItemCreateEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import org.timothyb89.eventbus.EventBus;
import org.timothyb89.eventbus.EventBusClient;
import org.timothyb89.eventbus.EventBusProvider;
import org.timothyb89.eventbus.EventHandler;
import org.timothyb89.eventbus.EventScanMode;
import org.timothyb89.eventbus.EventScanType;

@EventScanMode(type = EventScanType.EXTENDED)
public class MenuPanel extends JPanel implements EventBusProvider {

	private final List<MenuItem> items;
    private final Map<MenuItem, MenuItemComponent> itemMap;
	private final MenuFeature feature;
	private final int columns;
	
	private final EventBus bus;
	
	public MenuPanel(List<MenuItem> items, MenuFeature feature, int columns) {
		this.items = items;
		this.feature = feature;
		this.columns = columns;
		
        itemMap = new HashMap<>();
        
		bus = new EventBus() {{
			add(OrderItemCreateEvent.class);
			
			add(MenuItemCreateEvent.class);
			add(MenuItemEditEvent.class);
			add(MenuItemRemoveEvent.class);
		}};
		
		initComponents();
	}
	
	public MenuPanel(List<MenuItem> items, MenuFeature feature) {
		this(items, feature, 1);
	}

	@Override
	public EventBusClient bus() {
		return bus.getClient();
	}
	
	private void initComponents() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(400, 300));
		
		itemContainer = new JPanel(new GridLayout(0, columns));
		initMenuItems(items);
		
		itemScrollPane = new JScrollPane(itemContainer);
		itemScrollPane.setBorder(BorderFactory.createEmptyBorder());
		add(itemScrollPane, BorderLayout.CENTER);
		
		filterContainer = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		
		filterField = new JTextField("filter...");
		filterField.setForeground(Color.gray);
		filterField.addFocusListener(filterFocusHandler);
		filterField.addKeyListener(filterTextHandler);
		filterField.setPreferredSize(new Dimension(150, 25));
		filterContainer.add(filterField);
		
		if (feature == MenuFeature.ADMIN) {
			adminCreateItemButton = new JButton("Create Item");
			adminCreateItemButton.addActionListener(adminCreateItemListener);
			filterContainer.add(adminCreateItemButton);
		}
		
		add(filterContainer, BorderLayout.SOUTH);
	}
	
	private List<MenuItem> filterItems(String query) {
		query = query.toLowerCase();
		
		List<MenuItem> ret = new LinkedList<>();
		
		for (MenuItem i : items) {
			if (i.getName().toLowerCase().contains(query)
					|| i.getDescription().toLowerCase().contains(query)) {
				ret.add(i);
			}
		}
		
		return ret;
	}
	
	private void initMenuItems(List<MenuItem> items) {
		itemContainer.removeAll();
        itemMap.clear();
		
		for (MenuItem item : items) {
			MenuItemComponent c = new MenuItemComponent(item, feature);
			c.bus().register(this);
            
			itemContainer.add(c);
            itemMap.put(item, c);
		}
		
		revalidate();
		repaint();
	}
	
	public void refreshMenuItems(List<MenuItem> newItems) {
		initMenuItems(items);
		
		// reset the filter for good measure
		filterField.setText("filter...");
		filterField.setForeground(Color.gray);
	}
	
	@EventHandler
	private void doOrderItemCreated(OrderItemCreateEvent event) {
		// push event from child up
		bus.push(event);
	}
	
	@EventHandler
	private void doMenuItemEdited(MenuItemEditEvent event) {
		bus.push(event);
	}
	
	@EventHandler
	private void doMenuItemRemoved(MenuItemRemoveEvent event) {
		bus.push(event);
        
        MenuItemComponent c = itemMap.get(event.getItem());
        itemContainer.remove(c);
        itemMap.remove(event.getItem());
        
        revalidate();
        repaint();
	}
	
	private final FocusListener filterFocusHandler = new FocusListener() {

		@Override
		public void focusGained(FocusEvent e) {
			if (filterField.getText().equals("filter...")) {
				filterField.setText("");
				filterField.setForeground(Color.black);
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			if (filterField.getText().equals("")) {
				filterField.setText("filter...");
				filterField.setForeground(Color.gray);
				
				initMenuItems(items);
			}
		}
	};
	
	private final KeyAdapter filterTextHandler = new KeyAdapter() {

		@Override
		public void keyReleased(KeyEvent e) {
			String text = filterField.getText();
			
			if (text.length() > 0) {
				initMenuItems(filterItems(text));
			} else {
				initMenuItems(items);
			}
		}
		
	};
	
	private final ActionListener adminCreateItemListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object ancestor = SwingUtilities.getWindowAncestor(MenuPanel.this);
			
			MenuItemEditDialog d;
			if (ancestor instanceof Frame) {
				d = new MenuItemEditDialog((Frame) ancestor, null);
			} else if (ancestor instanceof Dialog) {
				d = new MenuItemEditDialog((Dialog) ancestor, null);
			} else {
				d = new MenuItemEditDialog((Frame) null, null);
			}
			
			d.setVisible(true);
			
			// wait for user input ...
			
			MenuItem i = d.getReturnedItem();
			if (i == null) {
				return;
			}
			
			bus.push(new MenuItemCreateEvent(i));
            
            // update the UI
            MenuItemComponent c = new MenuItemComponent(i, feature);
			c.bus().register(MenuPanel.this);
            
			itemContainer.add(c);
            itemMap.put(i, c);
            
            revalidate();
            repaint();
		}
		
	};
	
	private JScrollPane itemScrollPane;
	private JPanel itemContainer;
	
	private JPanel filterContainer;
	private JTextField filterField;
	
	private JButton adminCreateItemButton;
	
}
