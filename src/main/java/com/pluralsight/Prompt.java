package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Prompt {

    static Scanner input = new Scanner(System.in);

    // home screen menu display
    public static char displayHomeScreen(double balance) {
        displayBalance(balance);
        System.out.println("""
                    === HOME SCREEN ===
                    D) Add Deposit - Enter Deposit Information
                    P) Make Payment(Debit) - Enter Debit Information
                    L) Ledger - Display Ledger Screen
                    X) Exit - Exit Application
                    """);
        return handleUserInput();
    }

    // ledger screen menu display
    public static char displayLedgerMenu(double balance) {
        displayBalance(balance);
        System.out.println("""
                === LEDGER ===
                A) All - Display All Entries
                D) Deposits - Display Only Deposits
                P) Payments - Display Only Payments
                R) Reports - Run Pre-Defined Reports
                H) Home - Go Back To Home Page
                """);
        return handleUserInput();
    }

    // reports screen menu display
    public static char displayReportsMenu(double balance) {
        displayBalance(balance);
        System.out.println("""
                === REPORTS ===
                1) Month To Date
                2) Previous Month
                3) Year To Date
                4) Previous Year
                5) Search by Vendor
                0) Back - Go Back To The Ledger Page
                """);
        return handleUserInput();
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

        System.out.print("Transaction Description: ");
        transactionDescription = input.nextLine();

        System.out.print("Vendor: ");
        transactionVendor = input.nextLine();

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

    // prompt user whether they want to enter another transaction
    public static boolean addAnotherTransaction(){
        System.out.print("Add A New transaction (y/n): ");
        String userInput = input.nextLine().trim();

        return userInput.equalsIgnoreCase("y");
    }


}
