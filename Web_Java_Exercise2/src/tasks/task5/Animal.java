package tasks.task5;

public class Animal {
    protected String name;

    Animal(String name) {
        this.name = name;
        System.out.println("Animal constructor: " + name);
    }

    void speak() {
        System.out.println(name + " makes a sound.");
    }

    void info() {
        System.out.println("I am " + name + " (" + getClass().getSimpleName() + ")");
    }
}

class Dog extends Animal {
    Dog(String name) {
        super(name); // must call parent constructor first
    }

    @Override
    void speak() {
        System.out.println(name + " says: Woof!");
    }
}

class Cat extends Animal {
    Cat(String name) {
        super(name);
    }

    @Override
    void speak() {
        System.out.println(name + " says: Meow!");
    }
}

class Main {
    public static void main(String[] args) {
        Animal dog = new Dog("Rex");
        Animal cat = new Cat("Whiskers");

        dog.speak();
        cat.speak();
        dog.info();
        cat.info();
    }
}
