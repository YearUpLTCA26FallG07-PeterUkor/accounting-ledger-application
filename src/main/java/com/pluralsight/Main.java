package com.pluralsight;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // create array and load transactions from file to array
        String fileName = "transactions.csv";
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();

        // load transactions from file to arrayList
        TransactionsFileHandler.loadFile(transactions, fileName);

        boolean inLoop = true;
        do{
            // display home screen and handle user input
            char choice = Prompt.displayHomeScreen(Ledger.getBalance(transactions));

            // handle matches
            switch (choice){
                case 'D' -> addDeposit(transactions, fileName);
                case 'P' -> makePayment(transactions, fileName);
                case 'L' -> Ledger.ledgerScreen(transactions);
                case 'X' -> {
                    inLoop = false;
                    System.out.println("Thank you.");
                    System.out.println("Exiting Application...");
                }

                // handle other inputs
                default -> System.out.println("\nInvalid Input" +
                        "\nValid Inputs Include: D, P, L, or X" +
                        "\nPlease Enter a Valid Input");
            }
        } while (inLoop);
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
            Transaction transaction = Prompt.transactionPrompt(transactionType);

            // save transaction to file, then add object to the list
            if (TransactionsFileHandler.writeTransaction(transaction, filename)) {
                transactions.add(transaction);
            }

            // prompt for add another transaction
        } while (Prompt.addAnotherTransaction());
    }

}
