package com.pluralsight;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;

public class Reports {

    // reports screen loop
    public static void reportsScreen(ArrayList<Transaction> transactions) {
        boolean inLoop = true;
        do {
            char choice = Prompt.displayReportsMenu(Ledger.getBalance(transactions));

            switch (choice) {
                case '1' -> monthToDate(transactions);
                case '2' -> previousMonth(transactions);
                case '3' -> yearToDate(transactions);
                case '4' -> previousYear(transactions);
                case '5' -> searchByVendor(transactions);
                case '6' -> CustomSearch.search(transactions);
                case '0' -> {
                    inLoop = false;
                    System.out.println("Returning to Ledger Screen...");
                }
                default -> System.out.println("\nInvalid Input" +
                        "\nValid Inputs Include: 1, 2, 3, 4, 5, 6, or 0" +
                        "\nPlease Enter a Valid Input");
            }
        } while (inLoop);
    }

    public static void monthToDate(ArrayList<Transaction> transactions) {
        // build YearMonth object
        YearMonth yearMonth = YearMonth.now();
        findMatchingMonth(transactions, yearMonth);
    }

    // show transactions from last calendar month
    public static void previousMonth(ArrayList<Transaction> transactions) {

        // create a month object
        YearMonth previousMonth = YearMonth.now().minusMonths(1);
        findMatchingMonth(transactions, previousMonth);
    }

    public static void yearToDate(ArrayList<Transaction> transactions) {
        // create new year object
        Year year = Year.now();
        findMatchingYear(transactions, year);
    }
    public static void previousYear(ArrayList<Transaction> transactions) {
        Year previousYear = Year.now().minusYears(1);
        findMatchingYear(transactions, previousYear);
    }

    // show transactions matching a vendor name
    public static void searchByVendor(ArrayList<Transaction> transactions) {
        String vendor = Prompt.textPrompt("Vendor");
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