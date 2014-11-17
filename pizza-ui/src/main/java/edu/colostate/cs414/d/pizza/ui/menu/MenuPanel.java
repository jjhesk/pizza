package edu.colostate.cs414.d.pizza.ui.menu;

import edu.colostate.cs414.d.pizza.api.menu.PizzaMenuItem;
import edu.colostate.cs414.d.pizza.ui.event.CouponItemAddedEvent;
import edu.colostate.cs414.d.pizza.ui.event.DailySpecialItemAddedEvent;
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

	private List<PizzaMenuItem> items;
    private final Map<PizzaMenuItem, MenuItemComponent> itemMap;
	private final MenuFeature feature;
	private final int columns;
	
	private final EventBus bus;
	
	public MenuPanel(List<PizzaMenuItem> items, MenuFeature feature, int columns) {
		this.items = items;
		this.feature = feature;
		this.columns = columns;
		
        itemMap = new HashMap<>();
        
		bus = new EventBus() {{
			add(OrderItemCreateEvent.class);
                        add(DailySpecialItemAddedEvent.class);
			add(CouponItemAddedEvent.class);
			add(MenuItemCreateEvent.class);
			add(MenuItemEditEvent.class);
			add(MenuItemRemoveEvent.class);
		}};
		
		initComponents();
	}
	
	public MenuPanel(List<PizzaMenuItem> items, MenuFeature feature) {
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
	
	private List<PizzaMenuItem> filterItems(String query) {
		query = query.toLowerCase();
		
		List<PizzaMenuItem> ret = new LinkedList<>();
		
		for (PizzaMenuItem i : items) {
			if (i.getName().toLowerCase().contains(query)
					|| i.getDescription().toLowerCase().contains(query)) {
				ret.add(i);
			}
		}
		
		return ret;
	}
	
	private void initMenuItems(List<PizzaMenuItem> items) {
		itemContainer.removeAll();
        itemMap.clear();
		
		for (PizzaMenuItem item : items) {
			MenuItemComponent c = new MenuItemComponent(item, feature);
			c.bus().register(this);
            
			itemContainer.add(c);
            itemMap.put(item, c);
		}
		
		revalidate();
		repaint();
	}
	
	public void refreshMenuItems(List<PizzaMenuItem> newItems) {
        //Set the local copy of active items to the new item list.
        items = newItems;
        initMenuItems(newItems);

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
    private void doDailySpecialItemAdded(DailySpecialItemAddedEvent event) {
        bus.push(event);
		
		System.out.println("event sent");
    }
    
        @EventHandler
    private void doCouponItemAdded(CouponItemAddedEvent event) {
                bus.push(event);
		System.out.println("event sent");
    }
        
	@EventHandler
	private void doMenuItemEdited(MenuItemEditEvent event) {
		bus.push(event);
                
        //Update the local copy of active items.
        items.remove(event.getOriginalItem());
        items.add(event.getNewItem());

        //If a filter is being used, recalculate the items since a name may have changed.
        if (!filterField.getText().isEmpty() && filterField.getForeground() == Color.black) {
            initMenuItems(filterItems(filterField.getText()));
        }
	}
	
	@EventHandler
	private void doMenuItemRemoved(MenuItemRemoveEvent event) {
		bus.push(event);
        
        MenuItemComponent c = itemMap.get(event.getItem());
        itemContainer.remove(c);
        itemMap.remove(event.getItem());

        //Update the local copy of active items. No need to redo filter.
        items.remove(event.getItem());

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

                //This was causing the items to be recreated instantly, 
                //so the buttons were never getting pressed.
                //initMenuItems(items);
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
			
			PizzaMenuItem i = d.getReturnedItem();
			if (i == null) {
				return;
			}
			
			bus.push(new MenuItemCreateEvent(i));
            
                        //Update the local list of active items.
                        items.add(i);
                        
                        //If a filter is being applied, recalculate filter.
                        //Otherwise just add it to the current UI.
                        if (filterField.getText().length() > 0 && filterField.getForeground() == Color.black) {
                            initMenuItems(filterItems(filterField.getText()));
                        }
                        else {
                            // update the UI
                            MenuItemComponent c = new MenuItemComponent(i, feature);
                            c.bus().register(MenuPanel.this);

                            itemContainer.add(c);
                            itemMap.put(i, c);
                        }
            
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
