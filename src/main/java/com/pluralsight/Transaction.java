package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private  String description;
    private String vendor;
    private double amount;
    private String transactionType;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount, String transactionType) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
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
                "Date: " + date.format(dateFormatter) + "\n" +
                "Time: " + time.format(timeFormatter) + "\n" +
                "Description: " + description + "\n" +
                "Vendor: " + vendor + "\n" +
                "Amount: " + String.format("%.2f", amount) + "\n";
    }

    public String toFileString() {
        return date.format(dateFormatter) + "|" + time.format(timeFormatter) + "|" + description + "|" +
                vendor + "|" + String.format("%.2f", amount);
    }
}
