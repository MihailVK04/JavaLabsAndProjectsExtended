package tasks.task6;

abstract class Shape {
    abstract double area();
    abstract String name();

    void printArea() {
        System.out.printf("%s: area = %.2f%n", name(), area());
    }
}

class Circle extends Shape {
    private double radius;
    Circle(double radius) { this.radius = radius; }

    @Override double area() { return Math.PI * radius * radius; }
    @Override String name() { return "Circle(r=" + radius + ")"; }
}

class Rectangle extends Shape {
    private double w, h;
    Rectangle(double w, double h) { this.w = w; this.h = h; }

    @Override double area() { return w * h; }
    @Override String name() { return "Rect(" + w + "x" + h + ")"; }
}

class Triangle extends Shape {
    private double base, height;
    Triangle(double base, double height) { this.base = base; this.height = height; }

    @Override double area() { return 0.5 * base * height; }
    @Override String name() { return "Tri(b=" + base + ",h=" + height + ")"; }
}

class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
            new Circle(5),
            new Rectangle(4, 6),
            new Triangle(3, 8),
            new Circle(2.5)
        };

        for (Shape s : shapes) {
            s.printArea(); // polymorphic dispatch
        }
    }
}
