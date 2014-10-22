package edu.colostate.cs414.d.pizza.api.order;

import edu.colostate.cs414.d.pizza.api.menu.MenuItem;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Order {
	
	private int id;
	private OrderStatus status;
	private OrderType type;
	private Date startDate;
	private Date completionDate;
	private String customerName;
	private String customerAddress;
	private List<OrderItem> items;

    public Order(){
        status = OrderStatus.NEW;
        //startDate = new Date();
        items = new ArrayList<OrderItem>();
    }

	public Order(OrderType type, String customerName, String customerAddress) {
		this.type = type;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		//startDate = new Date();
		status = OrderStatus.NEW;
		items = new ArrayList<OrderItem>();
	}

	public Order(int id, OrderType type, String customerName, String customerAddress) {
		this(type, customerName, customerAddress);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

    public OrderType getType() { return type; }

    public void setType(OrderType orderType) { this.type = orderType; }

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public List<OrderItem> getItems() {
		return items;
	}
	
	public void addItem(OrderItem item) {
		items.add(item);
	}
	
	public void addItem(MenuItem item, int quantity) {
		items.add(new OrderItem(item, quantity));
	}
	
	public void removeItem(OrderItem item) {
		items.remove(item);
	}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 37 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 37 * hash + Objects.hashCode(this.customerName);
        hash = 37 * hash + Objects.hashCode(this.customerAddress);
        hash = 37 * hash + Objects.hashCode(this.items);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.customerAddress, other.customerAddress)) {
            return false;
        }
        if (!Objects.equals(this.items, other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", status=" + status + ", type=" + type + ", startDate=" + startDate + ", customerAddress=" + customerAddress + ", items=" + items + '}';
    }  
}
