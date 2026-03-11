package tasks.task23;

sealed interface Shape permits Rectangle, Square, Circle {
    double area();
    String describe();
}

record Rectangle(double width, double height) implements Shape {
    public double area() { return width * height; }
    public String describe() { return "Rect(" + width + "x" + height + ")"; }
}

record Square(double side) implements Shape {
    public double area() { return side * side; }
    public String describe() { return "Square(" + side + ")"; }
}

record Circle(double radius) implements Shape {
    public double area() { return Math.PI * radius * radius; }
    public String describe() { return "Circle(r=" + radius + ")"; }
}

class AreaCalculator {
    // Works correctly for ANY Shape — no surprises
    static void printArea(Shape s) {
        System.out.printf("%s → area = %.2f%n", s.describe(), s.area());
    }
}

class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
            new Rectangle(5, 10),
            new Square(7),
            new Circle(3)
        };

        for (Shape s : shapes) {
            AreaCalculator.printArea(s);
        }
    }
}
