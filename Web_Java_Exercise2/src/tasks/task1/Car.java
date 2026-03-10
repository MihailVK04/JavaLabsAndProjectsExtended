package tasks.task1;

public class Car {
    String brand;
    String model;
    int year;

    Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    void displayInfo() {
        System.out.println(year + " " + brand + " " + model);
    }

    public static void main(String[] args) {
        Car car1 = new Car("Toyota", "Camry", 2022);
        Car car2 = new Car("Honda", "Civic", 2023);

        car1.displayInfo();
        car2.displayInfo();

        System.out.println("Same object? " + (car1 == car2)); // false
    }
}