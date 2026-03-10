package tasks.task10;

import java.util.Objects;
import java.util.HashSet;

public class Point {
    private final int x;
    private final int y;

    Point(int x, int y) { this.x = x; this.y = y; }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point p)) return false;
        return x == p.x && y == p.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public static void main(String[] args) {
        Point a = new Point(3, 4);
        Point b = new Point(3, 4);
        Point c = new Point(1, 2);

        System.out.println("a = " + a);
        System.out.println("a.equals(b): " + a.equals(b));
        System.out.println("a.equals(c): " + a.equals(c));
        System.out.println("a == b     : " + (a == b)); // false — different objects

        // HashSet uses hashCode + equals
        HashSet<Point> set = new HashSet<>();
        set.add(a);
        set.add(b); // duplicate — not added
        set.add(c);
        System.out.println("Set size: " + set.size()); // 2
    }
}
