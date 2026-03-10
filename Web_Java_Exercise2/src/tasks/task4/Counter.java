package tasks.task4;

public class Counter {
    private static int totalInstances = 0;
    private final int id;

    Counter() {
        totalInstances++;
        this.id = totalInstances;
    }

    int getId() { return id; }

    static int getTotalInstances() {
        return totalInstances;
    }

    public static void main(String[] args) {
        Counter a = new Counter();
        Counter b = new Counter();
        Counter c = new Counter();

        System.out.println("a.id = " + a.getId());
        System.out.println("b.id = " + b.getId());
        System.out.println("c.id = " + c.getId());
        System.out.println("Total: " + Counter.getTotalInstances());
    }
}
