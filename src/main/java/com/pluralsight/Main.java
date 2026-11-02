package com.pluralsight;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        boolean inLoop = true;
        do{
            // display home screen and handle user input
            char choice = MenuConsole.homeScreenMenuDisplay();

            // pass matches for now
            switch (choice){
                case 'D' -> System.out.println("Add Deposit " + choice);
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
            char choice = MenuConsole.ledgerMenuDisplay();

            switch (choice){
                case 'A' -> System.out.println("Display all Entries " + choice);
                case 'D' -> System.out.println("Display Deposits " + choice);
                case 'P' -> System.out.println("Display Payments " + choice);
                case 'R' -> System.out.println("Reports Screen " + choice);
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
}
