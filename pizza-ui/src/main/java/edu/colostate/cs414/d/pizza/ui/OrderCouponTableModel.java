package edu.colostate.cs414.d.pizza.ui;

import edu.colostate.cs414.d.pizza.api.menu.Coupon;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class OrderCouponTableModel extends AbstractTableModel {

    public static final String[] COLUMNS = {
            "Item",
            "Reward Points",
    };

    private List<Coupon> items;

    public OrderCouponTableModel() {
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
        Coupon item = items.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return item.getMenuItem().getName();
            case 1:
                return String.valueOf(item.getRewardPoints());
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

    public Coupon getItem(int index) {
        return items.get(index);
    }

    public void addItem(Coupon item) {
        int n = items.size();

        items.add(item);
        fireTableRowsInserted(n, n);
    }

    public void removeItem(Coupon item) {
        int n = items.indexOf(item);

        items.remove(item);
        fireTableRowsDeleted(n, n);
    }

    public List<Coupon> getItems() {
        return items;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

}
