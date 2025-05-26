package RBAMS;

class CurrentAccount extends BankAccount {
    private static final double OVERDRAFT_LIMIT = 10000.0; // 10,000 overdraft limit

    public CurrentAccount(String accountHolderName, String accountNumber, double balance) {
        super(accountHolderName, accountNumber, balance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > (balance + OVERDRAFT_LIMIT)) {
            throw new InsufficientBalanceException("Withdrawal failed. Exceeds available balance and overdraft limit.");
        }
        balance -= amount;
        System.out.println("Withdrawal successful. New balance: " + balance);
        
        if (balance < 0) {
            System.out.println("Warning: Account is now in overdraft. Available overdraft: " + 
                              (OVERDRAFT_LIMIT + balance));
        }
    }
}