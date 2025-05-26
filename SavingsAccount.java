package RBAMS;

class SavingsAccount extends BankAccount {
    private static final double INTEREST_RATE = 0.05; // 5% annual interest

    public SavingsAccount(String accountHolderName, String accountNumber, double balance) {
        super(accountHolderName, accountNumber, balance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Withdrawal failed. Insufficient balance in savings account.");
        }
        balance -= amount;
        System.out.println("Withdrawal successful. New balance: " + balance);
    }

    public void applyInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        System.out.println("Interest applied: " + interest);
        System.out.println("New balance after interest: " + balance);
    }
}