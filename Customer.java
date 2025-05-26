package RBAMS;
import java.util.ArrayList;
import java.util.List;

class Customer {
    private String name;
    private List<BankAccount> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public String getName() {
        return name;
    }

    public void displayAllAccounts() {
        System.out.println("\nAccounts for " + name + ":");
        for (BankAccount account : accounts) {
            account.displayAccountInfo();
            if (account instanceof SavingsAccount) {
                System.out.println("Account Type: Savings Account");
            } else if (account instanceof CurrentAccount) {
                System.out.println("Account Type: Current Account");
                System.out.println("Overdraft Facility: 10,000");
            }
            System.out.println("----------------------");
        }
    }
}