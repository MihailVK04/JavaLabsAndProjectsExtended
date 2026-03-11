package tasks.task19;

// Custom checked exception
class InsufficientFundsException extends Exception {
    private final double deficit;

    InsufficientFundsException(double deficit) {
        super(String.format("Insufficient funds. Need $%.2f more.", deficit));
        this.deficit = deficit;
    }

    double getDeficit() { return deficit; }
}

// Custom unchecked exception
class InvalidAccountException extends RuntimeException {
    InvalidAccountException(String id) {
        super("No account found: " + id);
    }
}

class WalletService {
    private double balance;

    WalletService(double balance) {
        this.balance = balance;
    }

    void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        if (amount > balance) throw new InsufficientFundsException(amount - balance);
        balance -= amount;
        System.out.printf("Withdrew $%.2f. Remaining: $%.2f%n", amount, balance);
    }
}

class Main {
    public static void main(String[] args) {
        WalletService wallet = new WalletService(100);

        try {
            wallet.withdraw(30);
            wallet.withdraw(200); // will throw
        } catch (InsufficientFundsException e) {
            System.out.println("Caught: " + e.getMessage());
            System.out.printf("Deficit: $%.2f%n", e.getDeficit());
        } finally {
            System.out.println("Transaction complete.");
        }
    }
}
