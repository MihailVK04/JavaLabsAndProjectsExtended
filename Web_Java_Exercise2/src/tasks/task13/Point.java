package tasks.task13;

// One line replaces ~40 lines of boilerplate
record Point(int x, int y) {
    // Custom compact constructor for validation
    Point {
        if (x < 0 || y < 0) throw new IllegalArgumentException("Negative coords");
    }

    // Custom method
    double distanceTo(Point other) {
        int dx = this.x - other.x;
        int dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}

class Main {
    public static void main(String[] args) {
        Point a = new Point(3, 4);
        Point b = new Point(0, 0);
        Point c = new Point(3, 4);

        System.out.println("a = " + a);            // auto toString
        System.out.println("a.x() = " + a.x());    // auto accessor
        System.out.println("a.equals(c): " + a.equals(c)); // auto equals
        System.out.println("a == c     : " + (a == c));
        System.out.printf("Distance a→b: %.2f%n", a.distanceTo(b));

        // Records are immutable — no setters
        // a.x = 10; // COMPILE ERROR
    }
}
