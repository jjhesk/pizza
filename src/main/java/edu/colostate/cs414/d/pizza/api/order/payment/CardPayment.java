package edu.colostate.cs414.d.pizza.api.order.payment;

public class CardPayment extends Payment {
    private String name;
    private int cardNumber;
    private int securityCode;
    private int expirationYear;
    private int expirationMonth;
    private String billingAddress;
    private String billingCity;
    private String billingZipCode;

    public CardPayment(String name, int cardNumber, int securityCode, int expirationYear, int expirationMonth, String billingAddress, String billingCity, String billingZipCode) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.expirationYear = expirationYear;
        this.expirationMonth = expirationMonth;
        this.billingAddress = billingAddress;
        this.billingCity = billingCity;
        this.billingZipCode = billingZipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }

    public int getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingZipCode() {
        return billingZipCode;
    }

    public void setBillingZipCode(String billingZipCode) {
        this.billingZipCode = billingZipCode;
    }
}
