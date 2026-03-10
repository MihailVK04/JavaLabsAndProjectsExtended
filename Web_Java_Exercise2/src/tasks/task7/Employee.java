package tasks.task7;

abstract class Employee {
    protected String name;
    protected int id;

    Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    abstract double calculatePay();

    // Concrete method shared by all employees
    void printPaySlip() {
        System.out.printf("[%d] %s — Pay: $%.2f%n", id, name, calculatePay());
    }
}

class SalariedEmployee extends Employee {
    private double annualSalary;

    SalariedEmployee(String name, int id, double annualSalary) {
        super(name, id);
        this.annualSalary = annualSalary;
    }

    @Override
    double calculatePay() {
        return annualSalary / 12; // monthly
    }
}

class HourlyEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    HourlyEmployee(String name, int id, double hourlyRate, int hoursWorked) {
        super(name, id);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    double calculatePay() {
        int overtime = Math.max(0, hoursWorked - 160);
        int regular = hoursWorked - overtime;
        return regular * hourlyRate + overtime * hourlyRate * 1.5;
    }
}

class Main {
    public static void main(String[] args) {
        Employee[] staff = {
            new SalariedEmployee("Alice", 101, 96000),
            new HourlyEmployee("Bob", 102, 25, 180),
            new HourlyEmployee("Carol", 103, 30, 160)
        };
        for (Employee e : staff) e.printPaySlip();
    }
}
