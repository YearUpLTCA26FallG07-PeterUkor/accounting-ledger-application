package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Prompt {

    static Scanner input = new Scanner(System.in);

    // home screen menu display
    public static char displayHomeScreen() {
        System.out.println("""
                    \n=== HOME SCREEN ===
                    D) Add Deposit - Enter Deposit Information
                    P) Make Payment(Debit) - Enter Debit Information
                    L) Ledger - Display Ledger Screen
                    X) Exit - Exit Application
                    """);
        return handleUserInput();
    }

    // ledger screen menu display
    public static char displayLedgerMenu() {
        System.out.println("""
                A) All - Display All Entries
                D) Deposits - Display Only Deposits
                P) Payments - Display Only Payments
                R) Reports - Run Pre-Defined Reports
                H) Home - Go Back To Home Page
                """);
        return handleUserInput();
    }

    public static char displayReportsMenu() {
        System.out.println("""
                1) Month To Date
                2) Previous Month
                3) Year To Date
                4) Previous Year
                5) Search by Vendor
                0) Back - Go Back To The Ledger Page
                """);
        return handleUserInput();
    }

    // handle user input
    public static char handleUserInput() {
        // prompt for input
        System.out.print("Your input: ");

        // use scanner from Main Class to accept input
        String userInput = input.nextLine().trim();
        char choice = '-';

        // accept input only if user inputs a char
        // convert to upper case to handle lowercase characters
        if (userInput.length() == 1){
            choice = userInput.toUpperCase().charAt(0);
        }

        return choice;
    }

    public static String transactionPrompt(String transactionType){
        String formattedDateTime;
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
            try {
                System.out.println("Amount: ");
                String transactionAmountString = input.nextLine();
                transactionAmount = Double.parseDouble(transactionAmountString);

            } catch (NumberFormatException e){
                System.out.println("\n!!! Invalid Entry, Try Again. !!!\n");
                continue;
            }

            // check for transaction type against amount
            if (transactionType.equalsIgnoreCase("Deposit") && (transactionAmount <= 0)){
                System.out.println("\n!!! Deposit Can Not Be Zero or Negative !!!");
                continue;
            } else if (transactionType.equalsIgnoreCase("Payment") && (transactionAmount >= 0)) {
                System.out.println("\n!!! Payments Can Not Be Zero or Positive !!!");
                continue;
            }


            break;

        } while (true);

        // create date time string
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");
        formattedDateTime = localDateTime.format(dateTimeFormatter);

        return HelperFunctions.stringBuilderHelper(formattedDateTime, transactionDescription,
                transactionVendor, transactionAmount, transactionType);
    }

    public static boolean addAnotherTransaction(){
        System.out.print("Add A New transaction (y/n): ");
        String userInput = input.nextLine().trim();

        return userInput.equalsIgnoreCase("y");
    }


}
