package tasks.task29;

import java.util.*;

// Observer interface
interface PriceObserver {
    void onPriceChange(String product, double oldPrice, double newPrice);
}

// Subject
class Store {
    private final Map<String, Double> prices = new HashMap<>();
    private final List<PriceObserver> observers = new ArrayList<>();

    void addObserver(PriceObserver o)    { observers.add(o); }
    void removeObserver(PriceObserver o) { observers.remove(o); }

    void setPrice(String product, double price) {
        double oldPrice = prices.getOrDefault(product, 0.0);
        prices.put(product, price);
        if (price < oldPrice) { // notify only on price drop
            for (PriceObserver o : observers)
                o.onPriceChange(product, oldPrice, price);
        }
    }
}

// Concrete observers
class Customer implements PriceObserver {
    private final String name;
    Customer(String name) { this.name = name; }

    @Override
    public void onPriceChange(String product, double oldPrice, double newPrice) {
        System.out.printf("  %s notified: %s dropped $%.2f → $%.2f!%n",
            name, product, oldPrice, newPrice);
    }
}

class Main {
    public static void main(String[] args) {
        Store store = new Store();
        Customer alice = new Customer("Alice");
        Customer bob   = new Customer("Bob");

        store.addObserver(alice);
        store.addObserver(bob);

        store.setPrice("Laptop", 999.99);
        System.out.println("Price set to $999.99 — no notification (first price)");

        store.setPrice("Laptop", 1099.99);
        System.out.println("Price raised — no notification");

        System.out.println("Price dropped:");
        store.setPrice("Laptop", 849.99); // triggers notification
    }
}
