package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class TransactionsFileHandler {

    public static void loadFile(ArrayList<Transaction> transactions, String fileName) {
        try {
            // create file reader and buffer reader
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // read from file
            int lineNumber = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lineNumber++;

                // split and check transaction parts
                String[] transactionParts = line.split("\\|");
                if (transactionParts.length != 5) {
                    invalidTransactionEntry(transactionParts, lineNumber);
                    System.out.println("Exactly 5 parts divided by 4 \"|\" expected");
                    continue;
                }
                // create transaction object
                Transaction transaction = getTransaction(transactionParts, lineNumber);

                // if transaction is not null, add transaction object to list
                if (transaction != null) {
                    transactions.add(transaction);
                }

            }

        } catch (IOException e) {
            System.out.println("!!! Error reading file !!!");
        }

    }

    // parse transactionParts array and return transaction
    private static Transaction getTransaction(String[] transactionParts, int lineNumber) {

        LocalDate localDate;
        LocalTime localTime;
        String transactionDescription = transactionParts[2];
        String vendor = transactionParts[3];
        String transactionType;
        double transactionAmount;

        // handle invalid date entries
        try {
            localDate = LocalDate.parse(transactionParts[0]);
            localTime = LocalTime.parse(transactionParts[1]);
        } catch (DateTimeException e){
            invalidTransactionEntry(transactionParts, lineNumber);
            System.out.println("Invalid DateTime Entry");
            return null;
        }

        // handle invalid amount entry
        try {
            transactionAmount = Double.parseDouble(transactionParts[4]);
        } catch (NumberFormatException e) {
            invalidTransactionEntry(transactionParts, lineNumber);
            System.out.println("Invalid entry for amount");
            return null;
        }

        // reject zero NaN or infinite transaction amounts
        if ((transactionAmount == 0) ||
                (Double.isNaN(transactionAmount)) ||
                (Double.isInfinite(transactionAmount))) {
            invalidTransactionEntry(transactionParts, lineNumber);
            System.out.println("Amount cannot be zero, infinite or NaN");
            return null;
        }

        // set transaction type depending on amount
        transactionType = transactionAmount > 0 ? "Credit"  : "Debit";

        return new Transaction(localDate, localTime, transactionDescription,
                vendor, transactionAmount, transactionType);
    }

    private static void invalidTransactionEntry(String[] transactionParts, int lineNumber) {
        System.out.println("\n - Invalid Transaction Entry: " +
                lineNumber + ") " +
                Arrays.toString(transactionParts));
    }

}
