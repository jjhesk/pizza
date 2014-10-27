package edu.colostate.cs414.d.pizza.api.order.payment;

public class CheckPayment extends Payment {
    private String name;
    private int routingNumber;
    private int accountNumber;
    private int checkNumber;
    private double amountGiven;
    private double change;

    public CheckPayment(String name, int routingNumber, int accountNumber, int checkNumber, double amountGiven) {
        this.name = name;
        this.routingNumber = routingNumber;
        this.accountNumber = accountNumber;
        this.checkNumber = checkNumber;
        this.amountGiven = amountGiven;
    }

    public void calculateChange(double total) {
        this.change = this.amountGiven - total;
    }

    public int getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(int routingNumber) {
        this.routingNumber = routingNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(int checkNumber) {
        this.checkNumber = checkNumber;
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
