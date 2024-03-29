package edu.colostate.cs414.d.pizza.ui;

import edu.colostate.cs414.d.pizza.api.menu.PizzaMenuItem;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class MenuItemTableModel extends AbstractTableModel {
    
    public static final String[] COLUMNS = {
        "Item",
        "Price"
    };
    
    private List<PizzaMenuItem> items;

    public MenuItemTableModel(List<PizzaMenuItem> items) {
        this.items = new ArrayList<>(items);
    }

    public MenuItemTableModel() {
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
        PizzaMenuItem item = items.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return item.getName();
            case 1:
                return String.format("$%.2f", item.getPrice());
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
            default:
                return Object.class;
        }
    }
    
    public PizzaMenuItem getItem(int index) {
        return items.get(index);
    }
    
    public void addItem(PizzaMenuItem item) {
        int n = items.size();
        
        items.add(item);
        fireTableRowsInserted(n, n);
    }
    
    public void removeItem(PizzaMenuItem item) {
        int n = items.indexOf(item);
        
        items.remove(item);
        fireTableRowsDeleted(n, n);
    }

    public List<PizzaMenuItem> getItems() {
        return items;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
}
