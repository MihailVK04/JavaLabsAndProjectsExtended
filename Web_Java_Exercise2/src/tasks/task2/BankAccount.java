package tasks.task2;

public class BankAccount {
    private double balance;
    private final String owner;

    public BankAccount(String owner, double initialDeposit) {
        this.owner = owner;
        this.balance = Math.max(0, initialDeposit);
    }

    public double getBalance() { return balance; }
    public String getOwner()   { return owner; }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amount;
        System.out.printf("Deposited $%.2f. Balance: $%.2f%n", amount, balance);
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            System.out.println("Withdrawal denied.");
            return false;
        }
        balance -= amount;
        System.out.printf("Withdrew $%.2f. Balance: $%.2f%n", amount, balance);
        return true;
    }

    public static void main(String[] args) {
        BankAccount acc = new BankAccount("Alice", 1000);
        acc.deposit(500);
        acc.withdraw(200);
        acc.withdraw(5000); // denied
        System.out.println("Final: $" + acc.getBalance());
    }
}
