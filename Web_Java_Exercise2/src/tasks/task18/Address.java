package tasks.task18;

import java.util.Arrays;

class Address implements Cloneable {
    String city;
    Address(String city) { this.city = city; }

    @Override
    protected Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }
    @Override public String toString() { return city; }
}

class Person implements Cloneable {
    String name;
    Address address; // mutable reference!

    Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Deep clone — clone mutable fields too
    @Override
    protected Person clone() throws CloneNotSupportedException {
        Person copy = (Person) super.clone();
        copy.address = this.address.clone(); // deep copy!
        return copy;
    }

    @Override
    public String toString() { return name + " from " + address; }
}

class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person original = new Person("Alice", new Address("Sofia"));
        Person copy = original.clone();

        System.out.println("Original: " + original);
        System.out.println("Copy    : " + copy);

        copy.address.city = "Plovdiv";
        System.out.println("\nAfter changing copy's city:");
        System.out.println("Original: " + original); // still Sofia (deep copy!)
        System.out.println("Copy    : " + copy);
    }
}