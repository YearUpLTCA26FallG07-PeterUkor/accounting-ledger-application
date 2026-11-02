package com.pluralsight;

import java.util.Scanner;

public class MenuConsole {

    static Scanner input = new Scanner(System.in);

    // home screen menu display
    public static char homeScreenMenuDisplay() {
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
    public static char ledgerMenuDisplay() {
        System.out.println("""
                A) All - Display All Entries
                D) Deposits - Display Only Deposits
                P) Payments - Display Only Payments
                R) Reports - Run Pre-Defined Reports
                H) Home - Go Back To Home Page
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
}
