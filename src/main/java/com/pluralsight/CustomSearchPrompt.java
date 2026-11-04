package com.pluralsight;

import java.time.DateTimeException;
import java.time.LocalDate;

public class CustomSearchPrompt {

    // optional text, returns null if left blank
    public static String optionalTextPrompt(String label) {
        do {
            System.out.print(label + " (leave blank to skip): ");
            String userInput = Prompt.input.nextLine().trim();

            // blank input means skip this filter
            if (userInput.isEmpty()) {
                return null;
            }

            // "|" can never match since it is not allowed in descriptions or vendors
            if (!userInput.contains("|")) {
                return userInput;
            }
            System.out.println("\n!!! " + label + " cannot contain \"|\" !!!\n");
        } while (true);
    }

    // optional date in yyyy-MM-dd, returns null if left blank
    public static LocalDate optionalDatePrompt(String label) {
        do {
            System.out.print(label + " (yyyy-MM-dd, leave blank to skip): ");
            String userInput = Prompt.input.nextLine().trim();

            // blank input means skip this filter
            if (userInput.isEmpty()) {
                return null;
            }

            // handle invalid date entries
            try {
                return LocalDate.parse(userInput);
            } catch (DateTimeException e) {
                System.out.println("\n!!! Invalid Date, use yyyy-MM-dd (e.g. 2023-04-15) !!!\n");
            }
        } while (true);
    }

    // optional amount, returns null if left blank
    public static Double optionalAmountPrompt(String label) {
        do {
            System.out.print(label + " (leave blank to skip): ");
            String userInput = Prompt.input.nextLine().trim();

            // blank input means skip this filter
            if (userInput.isEmpty()) {
                return null;
            }

            // handle invalid amount entries
            try {
                double amount = Double.parseDouble(userInput);

                // reject NaN and infinite amounts
                if (!Double.isNaN(amount) && !Double.isInfinite(amount)) {
                    return amount;
                }
            } catch (NumberFormatException ignored) {
                // not a number, fall through to error message
            }
            System.out.println("\n!!! Invalid Amount, Try Again. !!!\n");
        } while (true);
    }
}
