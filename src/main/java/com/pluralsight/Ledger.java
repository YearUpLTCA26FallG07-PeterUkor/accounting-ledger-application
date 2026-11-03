package com.pluralsight;

import java.util.ArrayList;

public class Ledger {

    // ledger screen loop
    public static void ledgerScreen(ArrayList<Transaction> transactions) {
        boolean inLoop = true;
        do {
            char choice = Prompt.displayLedgerMenu(getBalance(transactions));

            switch (choice) {
                case 'A' -> displayTransactions(transactions);
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

    public static void displayTransactions(ArrayList<Transaction> transactions) {

        if (transactions.isEmpty()) {
            System.out.println("\nNo transactions found");
            return;
        }

        for (Transaction transaction : transactions) {
            System.out.print(transaction.toString());
        }
    }

    public static void displayDeposits(ArrayList<Transaction> transactions) {
        displayTransactionsByType(transactions, "Credit");
    }

    public static void displayPayments(ArrayList<Transaction> transactions) {
        displayTransactionsByType(transactions, "Debit");
    }

    public static void displayTransactionsByType(ArrayList<Transaction> transactions, String transactionType) {

        // bool to handle no match found
        boolean found = false;

        // display transactionType matches
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionType().equalsIgnoreCase(transactionType)) {
                found = true;
                System.out.println(transaction);
            }
        }

        // display a message if no type is found
        if (!found) {
            System.out.println("\nNo transactions found");
        }

    }

    // sum all transaction amounts
    public static double getBalance(ArrayList<Transaction> transactions) {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }
}
