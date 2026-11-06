package com.pluralsight;

import java.time.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    static Scanner input = new Scanner(System.in);

    // home screen menu display
    public static void displayHomeScreen(ArrayList<Transaction> transactions, String fileName) {
        double balance = 0;
        boolean inLoop = true;
        do{
            // calculate and display balance
            balance = getBalance(transactions);
            displayBalance(balance);

            // display home screen and handle user input
            System.out.println("""
                    === HOME SCREEN ===
                    D) Add Deposit - Enter Deposit Information
                    P) Make Payment(Debit) - Enter Debit Information
                    L) Ledger - Display Ledger Screen
                    X) Exit - Exit Application
                    """);

            // accept and handle user input
            char choice =  handleUserInput();
            switch (choice){
                case 'D' -> Main.addDeposit(transactions, fileName);
                case 'P' -> Main.makePayment(transactions, fileName);
                case 'L' -> displayLedgerMenu(transactions, balance);
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

    // ledger screen menu display
    public static void displayLedgerMenu(ArrayList<Transaction> transactions, double balance) {

        boolean inLoop = true;
        do {
            // display balance
            displayBalance(balance);

            // display ledger screen and handle user input
            System.out.println("""
                === LEDGER ===
                A) All - Display All Entries
                D) Deposits - Display Only Deposits
                P) Payments - Display Only Payments
                R) Reports - Run Pre-Defined Reports
                H) Home - Go Back To Home Page
                """);

            // accept and handle user input
            char choice = handleUserInput();

            switch (choice) {
                case 'A' -> Ledger.displayTransactions(transactions);
                case 'D' -> Ledger.displayDeposits(transactions);
                case 'P' -> Ledger.displayPayments(transactions);
                case 'R' -> displayReportsMenu(transactions, balance);
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

    // reports screen menu display
    public static void displayReportsMenu(ArrayList<Transaction> transactions,double balance) {


        boolean inLoop = true;
        do {
            // display balance
            displayBalance(balance);

            // display ledger screen and handle user input
            System.out.println("""
                === REPORTS ===
                1) Month To Date
                2) Previous Month
                3) Year To Date
                4) Previous Year
                5) Search by Vendor
                6) Custom Search
                0) Back - Go Back To The Ledger Page
                """);

            // accept and handle user input
            char choice = handleUserInput();

            switch (choice) {
                case '1' -> Reports.monthToDate(transactions);
                case '2' -> Reports.previousMonth(transactions);
                case '3' -> Reports.yearToDate(transactions);
                case '4' -> Reports.previousYear(transactions);
                case '5' -> Reports.searchByVendor(transactions);
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

    public static void displayBalance(double balance) {
        System.out.printf("%n=== TOTAL BALANCE: %.2f ===%n", balance);
    }

    // handle user input for Menus
    public static char handleUserInput() {
        // prompt for input
        System.out.print("Your input: ");

        // use scanner to accept input
        String userInput = input.nextLine().trim();
        char choice = '-';

        // accept input only if user inputs a char
        // convert to upper case to handle lowercase characters
        if (userInput.length() == 1){
            choice = userInput.toUpperCase().charAt(0);
        }

        return choice;
    }

    // prompt for transaction information and create transaction object
    public static Transaction transactionPrompt(String transactionType){

        String transactionDescription;
        String transactionVendor;
        double transactionAmount;

        System.out.println("\n--- Enter The Following Information ---");

        // prompt for description and vendor
        transactionDescription = textPrompt("Transaction Description");
        transactionVendor = textPrompt("Transaction Vendor");

        // handle invalid input for amount
        do {
            // accept input as string and handle string to double conversion
            try {
                System.out.print("Amount: ");
                String transactionAmountString = input.nextLine();
                transactionAmount = Double.parseDouble(transactionAmountString);

            } catch (NumberFormatException e){
                System.out.println("\n!!! Invalid Entry, Try Again. !!!\n");
                continue;
            }

            // check if amount is Not A Number or is infinity
            if (Double.isNaN(transactionAmount) || Double.isInfinite(transactionAmount)) {
                System.out.println("\n!!! Invalid Entry, Try Again. !!!\n");
                continue;
            }

            // round to cents so the amount matches what is saved to the file
            transactionAmount = Math.round(transactionAmount * 100) / 100.0;

            // check for transaction type against amount
            // credits cannot be negative and debits cannot be positive
            if (transactionType.equalsIgnoreCase("Credit") && (transactionAmount <= 0)){
                System.out.println("\n!!! Deposit Can Not Be Zero or Negative !!!");
                continue;
            } else if (transactionType.equalsIgnoreCase("Debit") && (transactionAmount >= 0)) {
                System.out.println("\n!!! Payments Can Not Be Zero or Positive !!!");
                continue;
            }

            break;

        } while (true);

        // get date and time, and split in to date and time
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = localDateTime.toLocalDate();
        LocalTime localTime = localDateTime.toLocalTime().withNano(0);

        // create transaction object and return
        return new Transaction(localDate, localTime, transactionDescription,
                transactionVendor, transactionAmount, transactionType);
    }

    // prompt for text that is not empty and has no "|"
    public static String textPrompt(String label) {
        do {
            System.out.print(label + ": ");
            String userInput = input.nextLine().trim();

            if (!userInput.isEmpty() && !userInput.contains("|")) {
                return userInput;
            }
            System.out.println("\n!!! " + label + " cannot be empty or contain \"|\" !!!\n");
        } while (true);
    }

    // prompt user whether they want to enter another transaction
    public static boolean addAnotherTransaction(){
        System.out.print("Add A New transaction (y/n): ");
        String userInput = input.nextLine().trim();

        return userInput.equalsIgnoreCase("y");
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
