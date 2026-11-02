package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private String date;
    private String time;
    private  String description;
    private String vendor;
    private double amount;
    private String transactionType;

    public Transaction(String date, String time, String description, String vendor, double amount, String transactionType) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "\nTransaction Type: " + transactionType + "\n" +
                "Date: " + date + "\n" +
                "Time: " + time + "\n" +
                "Description: " + description + "\n" +
                "Vendor: " + vendor + "\n" +
                "Amount: " + amount + "\n";
    }
}
