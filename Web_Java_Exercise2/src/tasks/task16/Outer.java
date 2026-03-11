package tasks.task16;

class Outer {
    private String name = "Outer";

    // 1. Static nested — no access to outer instance
    static class StaticNested {
        void greet() { System.out.println("Hello from StaticNested"); }
    }

    // 2. Inner (non-static) — has access to outer instance
    class Inner {
        void greet() { System.out.println("Hello from Inner, owner=" + name); }
    }

    void demo() {
        // 3. Local class — defined inside a method
        class Local {
            void greet() { System.out.println("Hello from Local, owner=" + name); }
        }
        new Local().greet();

        // 4. Anonymous class — one-off implementation
        Runnable anon = new Runnable() {
            @Override public void run() {
                System.out.println("Hello from Anonymous, owner=" + name);
            }
        };
        anon.run();
    }
}

class Main {
    public static void main(String[] args) {
        new Outer.StaticNested().greet();   // no outer instance needed
        new Outer().new Inner().greet();     // needs outer instance
        new Outer().demo();                  // local + anonymous
    }
}
