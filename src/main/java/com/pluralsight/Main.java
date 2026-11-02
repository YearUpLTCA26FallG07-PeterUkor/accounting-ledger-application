package com.pluralsight;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        boolean inLoop = true;
        do{
            // display home screen
            System.out.println("""
                    \n=== HOME SCREEN ===
                    D) Add Deposit - Enter Deposit Information
                    P) Make Payment(Debit) - Enter Debit Information
                    L) Ledger - Display Ledger Screen
                    X) Exit - Exit Application
                    """);
            System.out.print("Your Option: ");
            // trim input
            String userInput = input.nextLine().trim();
            char choice = '0';

            // accept input only if user inputs a char
            // convert to upper case to handle lowercase characters
            if (userInput.length() == 1){
                choice = userInput.toUpperCase().charAt(0);
            }

            // pass matches for now
            switch (choice){
                case 'D' -> System.out.println("Add Deposit" + userInput);
                case 'P' -> System.out.println("Make Payment(Debit)" + userInput);
                case 'L' -> System.out.println("Ledger" + userInput);
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
}