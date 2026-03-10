package tasks.task14;

sealed interface Shape permits Circle, Rectangle, Triangle {
    double area();
}

record Circle(double radius) implements Shape {
    public double area() { return Math.PI * radius * radius; }
}

record Rectangle(double w, double h) implements Shape {
    public double area() { return w * h; }
}

final class Triangle implements Shape {
    private final double base, height;
    Triangle(double base, double height) {
        this.base = base; this.height = height;
    }
    public double area() { return 0.5 * base * height; }
}

class Main {
    static String describe(Shape s) {
        return switch (s) {
            case Circle c    -> "Circle r=" + c.radius();
            case Rectangle r -> "Rect " + r.w() + "x" + r.h();
            case Triangle t  -> "Triangle";
        }; // no default needed — compiler knows all cases
    }

    public static void main(String[] args) {
        Shape[] shapes = { new Circle(5), new Rectangle(3, 4), new Triangle(6, 8) };
        for (Shape s : shapes)
            System.out.printf("%s → area = %.2f%n", describe(s), s.area());
    }
}
