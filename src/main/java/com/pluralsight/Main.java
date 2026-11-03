package com.pluralsight;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        ArrayList<Transaction> transactions = new ArrayList<Transaction>();

        boolean inLoop = true;
        do{
            // display home screen and handle user input
            char choice = Prompt.displayHomeScreen();

            // pass matches for now
            switch (choice){
                case 'D' -> addDeposit(transactions);
                case 'P' -> makePayment(transactions);
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
    public static void addDeposit(ArrayList<Transaction> transactions) {
        String transactionType = "Credit";
        handleTransaction(transactions, transactionType);
    }

    // handle payments
    public static void makePayment(ArrayList<Transaction> transactions) {
        String transactionType = "Debit";
        handleTransaction(transactions, transactionType);
    }

    // handle transactions
    public static void handleTransaction(ArrayList<Transaction> transactions, String transactionType) {

        do {

            // prompt user for input and create transaction Object
            Transaction transaction = Prompt.transactionPrompt(transactionType);

            // add object to the list
            transactions.add(transaction);

            // prompt for add another transaction
        } while (Prompt.addAnotherTransaction());
    }

}
