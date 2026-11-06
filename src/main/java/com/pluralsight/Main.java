package com.pluralsight;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        // create array and load transactions from file to array
        String fileName = "transactions.csv";
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();

        // load transactions from file to arrayList
        TransactionsFileHandler.loadFile(transactions, fileName);

        // sort the list
        transactions.sort(Comparator
                .comparing(Transaction::getDate)
                .thenComparing(Transaction::getTime)
                .reversed());

        // display the home screen menu and handle user inputs
        // call add deposit and make payments methods
        Menu.displayHomeScreen(transactions, fileName);

    }

    // handle deposits
    public static void addDeposit(ArrayList<Transaction> transactions, String filename) {
        String transactionType = "Credit";
        handleTransaction(transactions, transactionType, filename);
    }

    // handle payments
    public static void makePayment(ArrayList<Transaction> transactions, String filename) {
        String transactionType = "Debit";
        handleTransaction(transactions, transactionType, filename);
    }

    // handle transactions
    public static void handleTransaction(ArrayList<Transaction> transactions, String transactionType, String filename) {

        do {

            // prompt user for input and create transaction Object
            Transaction transaction = Menu.transactionPrompt(transactionType);

            // save transaction to file, then add object to the list
            if (TransactionsFileHandler.writeTransaction(transaction, filename)) {
                // add the object to the top of the list to stay sorted
                transactions.add(0, transaction);
            }

            // prompt for add another transaction
        } while (Menu.addAnotherTransaction());
    }

}
