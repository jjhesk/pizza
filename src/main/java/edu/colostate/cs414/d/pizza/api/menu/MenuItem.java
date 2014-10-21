package edu.colostate.cs414.d.pizza.api.menu;
s
public class MenuItem {
	
	private int id;
	
	private String name;
	private double price;
	private String description;
	
	private boolean active;

	public MenuItem() {
		active = true;
	}

	public MenuItem(String name, double price, String description) {
		this.name = name;
		this.price = price;
		this.description = description;
		active = true;
	}

	public MenuItem(int id, String name, double price, String description, boolean active) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.active = active;
	}

    //shallow copy
    public MenuItem(MenuItem item) {
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
        if (!this.name.equalsIgnoreCase(((MenuItem)other).name))
        {
            return false;
        }

        if (!(this.price == (((MenuItem)other).price)))
        {
            return false;
        }

        if (!(this.active == (((MenuItem)other).active)))
        {
            return false;
        }

        if (!this.description.equalsIgnoreCase(((MenuItem)other).description))
        {
            return false;
        }

        return true;
    }

    public String toString(){
        return Integer.toString(this.id) + " " + this.name + " " + Double.toString(this.price) + " " + this.description + " " + this.active;
    }

}
