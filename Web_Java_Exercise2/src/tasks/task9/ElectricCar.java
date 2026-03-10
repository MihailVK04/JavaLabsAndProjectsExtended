package tasks.task9;

// Abstract class: shared state + partial implementation
abstract class Vehicle {
    protected String make;
    protected int year;

    Vehicle(String make, int year) {
        this.make = make;
        this.year = year;
    }

    abstract String fuelType();

    void describe() {
        System.out.printf("%d %s [%s]%n", year, make, fuelType());
    }
}

// Interface: capability that any class can adopt
interface Electric {
    int batteryCapacityKWh();
    default void chargingInfo() {
        System.out.println("Battery: " + batteryCapacityKWh() + " kWh");
    }
}

class GasCar extends Vehicle {
    GasCar(String make, int year) { super(make, year); }
    @Override String fuelType() { return "Gasoline"; }
}

class ElectricCar extends Vehicle implements Electric {
    private int battery;
    ElectricCar(String make, int year, int battery) {
        super(make, year);
        this.battery = battery;
    }
    @Override String fuelType() { return "Electric"; }
    @Override public int batteryCapacityKWh() { return battery; }
}

class Main {
    public static void main(String[] args) {
        Vehicle gas = new GasCar("Toyota Camry", 2023);
        ElectricCar ev = new ElectricCar("Tesla Model 3", 2024, 75);

        gas.describe();
        ev.describe();
        ev.chargingInfo();

        // Checking capability
        Vehicle[] fleet = {gas, ev};
        for (Vehicle v : fleet) {
            if (v instanceof Electric e) {
                System.out.println(v.make + " is electric: " + e.batteryCapacityKWh() + " kWh");
            }
        }
    }
}
