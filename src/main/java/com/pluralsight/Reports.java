package com.pluralsight;

import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;

public class Reports {
    // show transactions from current calendar month
    public static void monthToDate(ArrayList<Transaction> transactions) {
        // build YearMonth object
        YearMonth yearMonth = YearMonth.now();
        findMatchingMonth(transactions, yearMonth);
    }

    // show transactions from previous calendar month
    public static void previousMonth(ArrayList<Transaction> transactions) {

        // create a month object
        YearMonth previousMonth = YearMonth.now().minusMonths(1);
        findMatchingMonth(transactions, previousMonth);
    }

    // show transactions from current year
    public static void yearToDate(ArrayList<Transaction> transactions) {
        // create new year object
        Year year = Year.now();
        findMatchingYear(transactions, year);
    }

    // show transactions from previous year
    public static void previousYear(ArrayList<Transaction> transactions) {
        Year previousYear = Year.now().minusYears(1);
        findMatchingYear(transactions, previousYear);
    }

    // show transactions matching a vendor name
    public static void searchByVendor(ArrayList<Transaction> transactions) {
        String vendor = Menu.textPrompt("Vendor");
        boolean found = false;

        for (Transaction transaction : transactions) {
            // search vendors
            if (transaction.getVendor().toLowerCase().contains(vendor.toLowerCase())) {
                found = true;
                System.out.println(transaction);
            }
        }

        if (!found) {
            System.out.println("\nNo transactions found for vendor: " + vendor);
        }
    }

    // search for transactions with matching months
    public static void findMatchingMonth(ArrayList<Transaction> transactions, YearMonth yearMonth) {
        boolean found = false;

        // compare YearMonth object
        for (Transaction transaction : transactions) {
            // get YearMonth from each transaction LocalDate
            YearMonth localYearMonth = YearMonth.from(transaction.getDate());

            if (localYearMonth.equals(yearMonth)) {
                found = true;
                System.out.println(transaction);
            }
        }

        if (!found) {
            System.out.println("\nNo transactions found");
        }
    }

    // search for transactions with matching years
    public static void findMatchingYear(ArrayList<Transaction> transactions,  Year year) {
        // compare year object
        boolean found = false;
        for (Transaction transaction : transactions) {
            // get Year from each transaction LocalDate
            Year localYear = Year.from(transaction.getDate());

            if (localYear.equals(year)) {
                found = true;
                System.out.println(transaction);
            }
        }

        if (!found) {
            System.out.println("\nNo transactions found");
        }
    }
}