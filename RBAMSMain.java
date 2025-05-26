package RBAMS;

import java.util.List;
import java.util.Scanner;

public class RBAMSMain {
    private static Scanner scanner = new Scanner(System.in);
    private static Customer customer;

    public static void main(String[] args) {
        System.out.println("Welcome to Rural Bank of Nepal\n");
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        customer = new Customer(customerName);

        // Create sample accounts for the customer
        createSampleAccounts();

        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    performDeposit();
                    break;
                case 2:
                    performWithdrawal();
                    break;
                case 3:
                    applyInterest();
                    break;
                case 4:
                    customer.displayAllAccounts();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Thank you for using Rural Bank of Nepal. Goodbye! Mr/Mrs " + customerName);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createSampleAccounts() {
        // Create sample accounts for the customer
        SavingsAccount savingsAccount = new SavingsAccount(
            customer.getName(), 
            "SA001", 
            5000.0
        );
        
        CurrentAccount currentAccount = new CurrentAccount(
            customer.getName(), 
            "CA001", 
            10000.0
        );
        
        customer.addAccount(savingsAccount);
        customer.addAccount(currentAccount);
    }

    private static void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Add Interest (Savings Account only)");
        System.out.println("4. View Accounts");
        System.out.println("5. Exit");
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            System.out.print(prompt);
        }
        return scanner.nextInt();
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.next();
            System.out.print(prompt);
        }
        return scanner.nextDouble();
    }

    private static BankAccount selectAccount() {
        System.out.println("\nSelect Account Type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        int accountType = getIntInput("Enter choice: ");

        List<BankAccount> accounts = customer.getAccounts();
        for (BankAccount account : accounts) {
            if (accountType == 1 && account instanceof SavingsAccount) {
                return account;
            } else if (accountType == 2 && account instanceof CurrentAccount) {
                return account;
            }
        }

        System.out.println("No account of selected type found. Using first available account.");
        return accounts.get(0);
    }

    private static void performDeposit() {
        BankAccount account = selectAccount();
        double amount = getDoubleInput("Enter deposit amount: ");
        account.deposit(amount);
    }

    private static void performWithdrawal() {
        BankAccount account = selectAccount();
        double amount = getDoubleInput("Enter withdrawal amount: ");
        
        try {
            account.withdraw(amount);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void applyInterest() {
        BankAccount account = selectAccount();
        
        if (account instanceof SavingsAccount) {
            ((SavingsAccount) account).applyInterest();
        } else {
            System.out.println("Interest can only be applied to Savings Accounts.");
        }
    }
}