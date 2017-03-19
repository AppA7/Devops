package com.ynov.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by camoroso on 09/03/2017.
 */
@Document
public class ReportedPayment {

    @Id
    private String id;
    private long timestamp;
    private Payment payment;

    public ReportedPayment() {
    }

    public ReportedPayment(long timestamp, Payment payment) {
        this.timestamp = timestamp;
        this.payment = payment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
