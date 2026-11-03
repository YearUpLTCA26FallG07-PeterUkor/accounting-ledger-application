package com.pluralsight;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // create array and load transactions from file to array
        String fileName = "transactions.csv";
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        TransactionsFileHandler.loadFile(transactions, fileName);

        boolean inLoop = true;
        do{
            // display home screen and handle user input
            char choice = Prompt.displayHomeScreen();

            // handle matches
            switch (choice){
                case 'D' -> addDeposit(transactions, fileName);
                case 'P' -> makePayment(transactions, fileName);
                case 'L' -> ledger();
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

    // ledger method
    public static void ledger(){

        boolean inLoop = true;
        do {

            // display ledger menu and handle user input
            char choice = Prompt.displayLedgerMenu();

            switch (choice){
                case 'A' -> System.out.println("Display all Entries " + choice);
                case 'D' -> System.out.println("Display Deposits " + choice);
                case 'P' -> System.out.println("Display Payments " + choice);
                case 'R' -> Ledger.reports();
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
