package edu.colostate.cs414.d.pizza.api.menu;

public class PizzaMenuItem {
	
	private int id;
	
	private String name;
	private double price;
	private String description;
	
	private boolean active;

	public PizzaMenuItem() {
		active = true;
	}

	public PizzaMenuItem(String name, double price, String description) {
		this.name = name;
		this.price = price;
		this.description = description;
		active = true;
	}

	public PizzaMenuItem(int id, String name, double price, String description, boolean active) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.active = active;
	}

    //shallow copy
    public PizzaMenuItem(PizzaMenuItem item) {
        this.name = item.getName();
        this.price = item.getPrice();
        this.description = item.getDescription();
        this.active = item.isActive();
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    public boolean equals(Object other)
    {
        if (!this.name.equalsIgnoreCase(((PizzaMenuItem)other).name))
        {
            return false;
        }

        if (!(this.price == (((PizzaMenuItem)other).price)))
        {
            return false;
        }

        if (!(this.active == (((PizzaMenuItem)other).active)))
        {
            return false;
        }

        if(this.description == null && ((PizzaMenuItem)other).description != null){
            return false;
        }

        if(this.description != null && ((PizzaMenuItem)other).description == null){
            return false;
        }

        if(this.description == null && ((PizzaMenuItem)other).description == null){
            return true;
        }

        if (!this.description.equalsIgnoreCase(((PizzaMenuItem)other).description))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return name + " $" + price;
    }

}
