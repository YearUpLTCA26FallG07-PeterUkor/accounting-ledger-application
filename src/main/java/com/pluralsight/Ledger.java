package com.pluralsight;

import java.util.ArrayList;

public class Ledger {

    // ledger screen loop
    public static void ledgerScreen(ArrayList<Transaction> transactions) {
        boolean inLoop = true;
        do {
            char choice = Prompt.displayLedgerMenu(getBalance(transactions));

            switch (choice) {
                case 'A' -> displayAll(transactions);
                case 'D' -> displayDeposits(transactions);
                case 'P' -> displayPayments(transactions);
                case 'R' -> Reports.reportsScreen(transactions);
                case 'H' -> {
                    inLoop = false;
                    System.out.println("Returning to Home Screen...");
                }
                default -> System.out.println("\nInvalid Input" +
                        "\nValid Inputs Include: A, D, P, R, or H" +
                        "\nPlease Enter a Valid Input");
            }
        } while (inLoop);
    }

    public static void displayAll(ArrayList<Transaction> transactions) { }
    public static void displayDeposits(ArrayList<Transaction> transactions) { }
    public static void displayPayments(ArrayList<Transaction> transactions) { }

    // sum all transaction amounts
    public static double getBalance(ArrayList<Transaction> transactions) {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }
}
