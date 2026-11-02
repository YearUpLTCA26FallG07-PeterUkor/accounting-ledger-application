package com.pluralsight;

public class Console {

    // home screen menu display
    public static void homeScreenMenuDisplay() {
        System.out.println("""
                    \n=== HOME SCREEN ===
                    D) Add Deposit - Enter Deposit Information
                    P) Make Payment(Debit) - Enter Debit Information
                    L) Ledger - Display Ledger Screen
                    X) Exit - Exit Application
                    """);
    }

    // ledger screen menu display
    public static void ledgerMenuDisplay() {
        System.out.println("""
                A) All - Display All Entries
                D) Deposits - Display Only Deposits
                P) Payments - Display Only Payments
                R) Reports - Run Pre-Defined Reports
                H) Home - Go Back To Home Page
                """);
    }
}
