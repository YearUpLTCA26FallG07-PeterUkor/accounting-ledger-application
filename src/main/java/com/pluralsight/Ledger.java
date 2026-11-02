package com.pluralsight;

public class Ledger {
    public static void reports(){
        boolean inLoop = true;

        do {
            char choice = Prompt.displayReportsMenu();

            switch (choice) {
                case '1' -> System.out.println("Month To Date " + choice);
                case '2' -> System.out.println("Previous Month " + choice);
                case '3' -> System.out.println("Year To Date " + choice);
                case '4' -> System.out.println("Previous Year " + choice);
                case '5' -> System.out.println("Search by Vendor " + choice);
                case '0' -> {
                    inLoop = false;
                    System.out.println("Returning to Ledger Screen...");
                }

                default -> System.out.println("\nInvalid Input" +
                        "\nValid Inputs Include: 1, 2, 3, 4, 5, or 0" +
                        "\nPlease Enter a Valid Input");
            }
        } while (inLoop);
    }
}
