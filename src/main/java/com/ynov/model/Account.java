package com.ynov.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by camoroso on 09/03/2017.
 */
@Document
public class Account {

    @Id
    private String cardNumber;
    private String cvv;
    private long validityDate;
    private double sum;

    public Account(String cardNumber, String cvv, long validityDate) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.validityDate = validityDate;
        this.sum = 300;
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

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
