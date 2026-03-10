package tasks.task11;

class Engine {
    private int horsepower;

    Engine(int hp) { this.horsepower = hp; }

    void start() { System.out.println("Engine (" + horsepower + "hp) started."); }
    void stop()  { System.out.println("Engine stopped."); }
}

class Car {
    private String model;
    private Engine engine; // Composition: Car HAS-A Engine

    Car(String model, Engine engine) {
        this.model = model;
        this.engine = engine;
    }

    void drive() {
        System.out.println(model + ":");
        engine.start();
        System.out.println("  Driving...");
    }

    void park() {
        System.out.println("  Parked.");
        engine.stop();
    }
}

class Main {
    public static void main(String[] args) {
        Engine v6 = new Engine(300);
        Engine electric = new Engine(450);

        Car sedan = new Car("Camry", v6);
        Car tesla = new Car("Model S", electric);

        sedan.drive();
        sedan.park();
        System.out.println();
        tesla.drive();
        tesla.park();
    }
}
