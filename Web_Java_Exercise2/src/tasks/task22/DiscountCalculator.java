package tasks.task22;

// Strategy interface — open for extension
interface DiscountStrategy {
    double apply(double price);
    String name();
}

// Each strategy is a separate class
class SummerDiscount implements DiscountStrategy {
    public double apply(double price) { return price * 0.15; }
    public String name() { return "Summer Sale (15%)"; }
}

class VipDiscount implements DiscountStrategy {
    public double apply(double price) { return price * 0.20; }
    public String name() { return "VIP (20%)"; }
}

class BulkDiscount implements DiscountStrategy {
    private final int minQty;
    BulkDiscount(int minQty) { this.minQty = minQty; }
    public double apply(double price) { return price * 0.10; }
    public String name() { return "Bulk (10%, min " + minQty + ")"; }
}

// Calculator is CLOSED for modification — never needs to change
class DiscountCalculator {
    double calculate(double price, DiscountStrategy strategy) {
        double discount = strategy.apply(price);
        System.out.printf("%s: $%.2f → save $%.2f → pay $%.2f%n",
            strategy.name(), price, discount, price - discount);
        return price - discount;
    }
}

class Main {
    public static void main(String[] args) {
        DiscountCalculator calc = new DiscountCalculator();
        double price = 100.0;

        calc.calculate(price, new SummerDiscount());
        calc.calculate(price, new VipDiscount());
        calc.calculate(price, new BulkDiscount(10));
        // New discounts = new classes, no modification to calculator!
    }
}
