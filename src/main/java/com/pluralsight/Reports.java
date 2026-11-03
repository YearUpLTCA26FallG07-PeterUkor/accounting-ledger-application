package com.pluralsight;

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
                case '0' -> {
                    inLoop = false;
                    System.out.println("Returning to Ledger Screen...");
                }
                default -> System.out.println("\nInvalid Input" +
                        "\nValid Inputs Include: 1, 2, 3, 4, 5, or 0" +
                        "\nPlease Enter a Valid Input");
            }
        } while (inLoop);
    }

    public static void monthToDate(ArrayList<Transaction> transactions) { }
    public static void previousMonth(ArrayList<Transaction> transactions) { }
    public static void yearToDate(ArrayList<Transaction> transactions) { }
    public static void previousYear(ArrayList<Transaction> transactions) { }
    public static void searchByVendor(ArrayList<Transaction> transactions) { }
}