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
                case 'P' -> System.out.println("Make Payment(Debit) " + choice);
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

    public static void addDeposit(ArrayList<Transaction> transactions) {
        String transactionDate;
        String transactionTime;
        String transactionDescription;
        String transactionVendor;
        double transactionAmount;
        String transactionType;

        do {
            String transactionString = Prompt.transactionPrompt("Deposit");
            String[] transactionArray = transactionString.split("\\|");

            if (transactionArray.length != 6){
                System.out.println("!!! Invalid Transaction Entry !!!");
                continue;
            }

            transactionDate = transactionArray[0];
            transactionTime = transactionArray[1];
            transactionDescription = transactionArray[2];
            transactionVendor = transactionArray[3];
            transactionAmount = Double.parseDouble(transactionArray[4]);
            transactionType = transactionArray[5];

            Transaction transaction = new Transaction(transactionDate, transactionTime,
                    transactionDescription, transactionVendor, transactionAmount, transactionType);

            transactions.add(transaction);

        } while (Prompt.addAnotherTransaction());
    }

}
