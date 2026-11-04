# Accounting Ledger Application

A command-line Java application for tracking financial transactions. Users can record deposits and payments, view their ledger and run reports. Every transaction is saved to `transactions.csv`, so the data is still there the next time the app starts.

## Features

- **Home Screen:**
  - Add deposit - record money coming in
  - Make payment - record money going out
  - Ledger - open the ledger screen
  - Exit - close the application

- **Ledger:** (newest entries first)
  - All entries - show every transaction
  - Deposits only - show only deposits
  - Payments only - show only payments
  - Reports - open the reports screen

- **Reports:**
  - Month to date - transactions from the current month
  - Previous month - transactions from last month
  - Year to date - transactions from the current year
  - Previous year - transactions from last year
  - Search by vendor - transactions matching a vendor name
  - Custom search - filter by start date, end date, description, vendor and amount (any field can be left blank)

- **Current balance:** shown above every menu.
- **Input validation:** invalid menu choices, amounts and dates are rejected, and the user is asked again.

## How to Run

Requires Java 17 or newer.

### IntelliJ

1. Open the project folder in IntelliJ.
2. Open `src/main/java/com/pluralsight/Main.java`.
3. Click the green **Run** arrow next to `main`.

### Command Line

From the project folder, compile the code:

```bash
javac -d target/classes src/main/java/com/pluralsight/*.java
```

Then start the app:

```bash
java -cp target/classes com.pluralsight.Main
```

## Notes

- Deposits are entered as positive amounts and payments as negative amounts (for example `-89.50`).
- Transactions are stored in `transactions.csv` in this format:

```
date|time|description|vendor|amount
2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50
```

---

*README.md Written by Peter Ukor, reviewed with Anthropic's Claude.*
