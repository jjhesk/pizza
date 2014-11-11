package edu.colostate.cs414.d.pizza.api.order.payment;

public class CashPayment extends Payment {
	private double amountGiven;
    private double change;

    public CashPayment(double amountGiven) {
        this.amountGiven = amountGiven;
    }

    public void calculateChange(double total) {
        this.change = this.amountGiven - total;
    }

    public double getAmountGiven() {
        return amountGiven;
    }

    public void setAmountGiven(double amountGiven) {
        this.amountGiven = amountGiven;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }
}
