package com.pluralsight;

public class HelperFunctions {

    // build String
    public static String stringBuilderHelper(
            String formattedDateTime,
            String transactionDescription,
            String transactionVendor,
            double transactionAmount,
            String transactionType) {
        // build string
        return formattedDateTime + "|" +
                transactionDescription + "|" +
                transactionVendor + "|" +
                String.format("%.2f", transactionAmount) + "|" +
                transactionType;
    }
}
