package tasks.task17;

import java.util.*;

class Product implements Comparable<Product> {
    String name;
    double price;
    int rating;

    Product(String name, double price, int rating) {
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    // Natural ordering: by price ascending
    @Override
    public int compareTo(Product other) {
        return Double.compare(this.price, other.price);
    }

    @Override
    public String toString() {
        return String.format("%-10s $%.2f ★%d", name, price, rating);
    }

    public int getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }
}

class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>(List.of(
            new Product("Mouse",    25.99, 4),
            new Product("Keyboard", 79.99, 5),
            new Product("Monitor",  299.99, 4),
            new Product("Headset",  59.99, 3)
        ));

        // Natural order (Comparable — by price)
        Collections.sort(products);
        System.out.println("By price:");
        products.forEach(System.out::println);

        // Custom Comparator — by rating desc, then name
        //products.sort(Comparator.comparingInt(Product::getRating()).reversed()
        //    .thenComparing(Product::getName()));
        System.out.println("\nBy rating desc:");
        products.forEach(System.out::println);
    }
}
