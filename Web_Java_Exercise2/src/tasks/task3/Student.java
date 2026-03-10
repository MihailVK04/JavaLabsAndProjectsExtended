package tasks.task3;

public class Student {
    private String name;
    private int age;
    private String major;

    // Primary constructor
    Student(String name, int age, String major) {
        this.name = name;
        this.age = age;
        this.major = major;
    }

    // Chains to primary — default major
    Student(String name, int age) {
        this(name, age, "Undeclared");
    }

    // Chains to 2-arg — default age
    Student(String name) {
        this(name, 18);
    }

    void display() {
        System.out.printf("%s, age %d, major: %s%n", name, age, major);
    }

    public static void main(String[] args) {
        new Student("Alice", 21, "CS").display();
        new Student("Bob", 20).display();
        new Student("Carol").display();
    }
}
