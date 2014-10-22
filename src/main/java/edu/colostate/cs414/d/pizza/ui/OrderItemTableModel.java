package edu.colostate.cs414.d.pizza.ui;

import edu.colostate.cs414.d.pizza.api.order.OrderItem;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class OrderItemTableModel extends AbstractTableModel {

    public static final String[] COLUMNS = {
        "Item",
        "Price",
        "Qty"
    };
    
    private List<OrderItem> items;

    public OrderItemTableModel(List<OrderItem> items) {
        this.items = new ArrayList<>(items);
    }

    public OrderItemTableModel() {
        items = new ArrayList<>();
    }
    
    @Override
    public int getRowCount() {
        return items.size();
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
        OrderItem item = items.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return item.getItem().getName();
            case 1:
                return String.format("$%.2f", item.getItem().getPrice());
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
            case 1:
                return String.class;
            case 2:
                return Integer.class;
            default:
                return Object.class;
        }
    }
    
    public OrderItem getItem(int index) {
        return items.get(index);
    }
    
    public void addItem(OrderItem item) {
        int n = items.size();
        
        items.add(item);
        fireTableRowsInserted(n, n);
    }
    
    public void removeItem(OrderItem item) {
        int n = items.indexOf(item);
        
        items.remove(item);
        fireTableRowsDeleted(n, n);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
}
