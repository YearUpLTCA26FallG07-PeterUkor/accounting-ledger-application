package com.pluralsight;

import java.time.LocalDate;
import java.util.ArrayList;

public class CustomSearch {

    // prompt for search values and display transactions matching every value entered
    public static void search(ArrayList<Transaction> transactions) {

        System.out.println("\n--- Custom Search ---");

        // prompt for search values, blank values are null and skipped
        LocalDate startDate = CustomSearchPrompt.optionalDatePrompt("Start Date");
        LocalDate endDate = CustomSearchPrompt.optionalDatePrompt("End Date");
        String description = CustomSearchPrompt.optionalTextPrompt("Description");
        String vendor = CustomSearchPrompt.optionalTextPrompt("Vendor");
        Double amount = CustomSearchPrompt.optionalAmountPrompt("Amount");

        // reject a date range that can never match
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            System.out.println("\n!!! Start Date cannot be after End Date !!!");
            return;
        }

        // display transactions that pass every filter
        boolean found = false;
        for (Transaction transaction : transactions) {
            if (matches(transaction, startDate, endDate, description, vendor, amount)) {
                // print the column titles above the first match
                if (!found) {
                    System.out.println("\n" + Transaction.tableHeader());
                }
                found = true;
                System.out.println(transaction);
            }
        }

        // display a message if no match is found
        if (!found) {
            System.out.println("\nNo transactions found");
        }
    }

    // check a transaction against each filter, null filters are skipped
    private static boolean matches(Transaction transaction, LocalDate startDate, LocalDate endDate,
                                   String description, String vendor, Double amount) {

        LocalDate date = transaction.getDate();

        // start and end dates checks are inclusive
        if (startDate != null && date.isBefore(startDate)) {
            return false;
        }
        if (endDate != null && date.isAfter(endDate)) {
            return false;
        }

        // description and vendor match partially and ignore case
        if (description != null && !containsIgnoreCase(transaction.getDescription(), description)) {
            return false;
        }
        if (vendor != null && !containsIgnoreCase(transaction.getVendor(), vendor)) {
            return false;
        }

        // compare amounts in whole cents to avoid decimal rounding issues
        if (amount != null && Math.round(transaction.getAmount() * 100) != Math.round(amount * 100)) {
            return false;
        }

        return true;
    }

    // search whether one text matches another text
    private static boolean containsIgnoreCase(String text, String search) {
        return text.toLowerCase().contains(search.toLowerCase());
    }
}