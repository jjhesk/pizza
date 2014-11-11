package edu.colostate.cs414.d.pizza.ui;

import edu.colostate.cs414.d.pizza.api.order.Order;
import edu.colostate.cs414.d.pizza.api.order.OrderItem;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.table.AbstractTableModel;

public class PendingOrderTableModel extends AbstractTableModel {

	public static final String[] COLUMNS = {
		"Order Id",
        "Item",
        "Qty"
    };
    
    private final Map<OrderItem, Order> orderMap;
	private final List<OrderItem> collapsedItems;

    public PendingOrderTableModel(List<Order> orders) {
		System.out.println("pending table");
        orderMap = new HashMap<>();
		
		collapsedItems = new ArrayList<>();
		for (Order o : orders) {
			System.out.println(o);
			for (OrderItem i : o.getItems()) {
				System.out.println(i);
				orderMap.put(i, o);
				collapsedItems.add(i);
			}
		}
    }
    
    @Override
    public int getRowCount() {
        return collapsedItems.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OrderItem item = collapsedItems.get(rowIndex);
		
        switch (columnIndex) {
            case 0:
                return orderMap.get(item).getId();
            case 1:
                return item.getItem().getName();
            case 2:
                return item.getQuantity();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
				return Integer.class;
            case 1:
                return String.class;
            case 2:
                return Integer.class;
            default:
                return Object.class;
        }
    }
    
	public Order getOrder(int index) {
		OrderItem i = collapsedItems.get(index);
		return orderMap.get(i);
	}
	
    public List<OrderItem> getItems() {
        return collapsedItems;
    }
	
	/**
	 * Get the range of items for the order also containing the item at
	 * {@code index}.
	 * @param index the item index
	 * @return a 2-long int array containing the (inclusive) start and end
	 *     indices
	 */
	public int[] getRange(int index) {
		Order order = getOrder(index);
		
		int start = index;
		do {
			int tryStart = start - 1;
			
			if (tryStart >= 0 && getOrder(tryStart) == order) {
				start = tryStart;
			} else {
				break;
			}
		} while (start > 0);
		
		int end = index;
		do {
			int tryEnd = end + 1;
			
			if (tryEnd < collapsedItems.size() && getOrder(tryEnd) == order) {
				end = tryEnd;
			} else {
				break;
			}
		} while (end < collapsedItems.size() - 1);
		
		return new int[] {start, end};
	}

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
	
}
