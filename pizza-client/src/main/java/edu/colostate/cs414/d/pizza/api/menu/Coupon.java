package edu.colostate.cs414.d.pizza.api.menu;

public class Coupon {
    private int id;
    private MenuItem menuItem;
    private int rewardPoints;
    private boolean active;

	public Coupon() {
		active = true;
	}
	
    public Coupon(MenuItem menuItem, int rewardPoints) {
        this.menuItem = menuItem;
        this.rewardPoints = rewardPoints;
        this.active = true;
    }

    public Coupon(int id, MenuItem menuItem, int rewardPoints, boolean active) {
        this.id = id;
        this.menuItem = menuItem;
        this.rewardPoints = rewardPoints;
        this.active = active;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
