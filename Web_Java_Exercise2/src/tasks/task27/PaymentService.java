package tasks.task27;

import java.util.*;

// ISP: separate interfaces for each capability
interface PaymentProcessor {
    boolean processPayment(double amount);
    String methodName();
}

interface PaymentValidator {
    boolean validate(double amount);
}

interface ReceiptGenerator {
    void generateReceipt(String method, double amount, boolean success);
}

// OCP: new payment methods = new classes
class CreditCardProcessor implements PaymentProcessor {
    public boolean processPayment(double amount) {
        System.out.printf("  Charging credit card $%.2f...%n", amount);
        return true;
    }
    public String methodName() { return "Credit Card"; }
}

class PayPalProcessor implements PaymentProcessor {
    public boolean processPayment(double amount) {
        System.out.printf("  Processing PayPal $%.2f...%n", amount);
        return true;
    }
    public String methodName() { return "PayPal"; }
}

class CryptoProcessor implements PaymentProcessor {
    public boolean processPayment(double amount) {
        System.out.printf("  Sending crypto equivalent of $%.2f...%n", amount);
        return amount < 10000; // limit
    }
    public String methodName() { return "Crypto"; }
}

// SRP: validator only validates
class AmountValidator implements PaymentValidator {
    public boolean validate(double amount) {
        return amount > 0 && amount <= 50000;
    }
}

// SRP: receipt only generates output
class ConsoleReceipt implements ReceiptGenerator {
    public void generateReceipt(String method, double amount, boolean success) {
        String status = success ? "SUCCESS" : "FAILED";
        System.out.printf("  Receipt: %s via %s — %s%n%n",
            String.format("$%.2f", amount), method, status);
    }
}

// DIP: depends on abstractions
class PaymentService {
    private final PaymentValidator validator;
    private final ReceiptGenerator receipt;

    PaymentService(PaymentValidator validator, ReceiptGenerator receipt) {
        this.validator = validator;
        this.receipt = receipt;
    }

    void pay(double amount, PaymentProcessor processor) {
        System.out.println("Processing " + processor.methodName() + ":");
        if (!validator.validate(amount)) {
            System.out.println("  Invalid amount!");
            receipt.generateReceipt(processor.methodName(), amount, false);
            return;
        }
        boolean ok = processor.processPayment(amount);
        receipt.generateReceipt(processor.methodName(), amount, ok);
    }
}

class Main {
    public static void main(String[] args) {
        PaymentService service = new PaymentService(
            new AmountValidator(), new ConsoleReceipt()
        );

        service.pay(49.99, new CreditCardProcessor());
        service.pay(150.00, new PayPalProcessor());
        service.pay(15000, new CryptoProcessor());
    }
}
