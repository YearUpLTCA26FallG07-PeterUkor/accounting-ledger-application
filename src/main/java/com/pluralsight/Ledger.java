package com.pluralsight;

import java.util.ArrayList;

public class Ledger {

    public static void displayTransactions(ArrayList<Transaction> transactions) {

        if (transactions.isEmpty()) {
            System.out.println("\nNo transactions found");
            return;
        }

        System.out.println("\n" + Transaction.tableHeader());
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
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
                // print the column titles above the first match
                if (!found) {
                    System.out.println("\n" + Transaction.tableHeader());
                }
                found = true;
                System.out.println(transaction);
            }
        }

        // display a message if no type is found
        if (!found) {
            System.out.println("\nNo transactions found");
        }

    }

}
