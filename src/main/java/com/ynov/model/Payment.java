package com.ynov.model;

/**
 * Created by camoroso on 09/03/2017.
 */
public class Payment {

    private String cardNumber;
    private String cvv;
    private long validityDate;
    private double amount;

    public Payment() {
    }

    public Payment(String cardNumber, String cvv, long validityDate, double amount) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.validityDate = validityDate;
        this.amount = amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public long getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(long validityDate) {
        this.validityDate = validityDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
