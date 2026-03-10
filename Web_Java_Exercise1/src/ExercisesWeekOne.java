import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Utility class for comments
 * @author Misho
 * @version 1.0
 */
class CommentsTask {

    /**
     * Calculates the area of the rectangle
     * @param width the width (positive)
     * @param height the height (positive)
     * @return area as double
     */
    public static double rectangleArea(double width, double height) {
        //Multiply width with height
        return width * height;
    }
}

public class ExercisesWeekOne {

    public static void variableAndDataTypes() {
        int age = 21;
        double height = 1.75d;
        boolean isStudent = true;
        String name = "Mihail";
        //System.out.println("My name is " + name + ". I'm " + age + " years old and I'm " + height
        //    + " meters high. Am I a student: " + isStudent);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Height: " + height + "m");
        System.out.println("Student: " + isStudent);
    }

    public static void primitiveTypes() {
        byte letter = 5;
        short smallNum = 1;
        int normalNum = 1024;
        long bigNum = 1024*1024;
        float floatPointNum = 10.25f;
        double bigFloatPointNum = 25_000.25d;
        char normalLetter = 'm';
        boolean condition = true;
        System.out.println("Byte: " + letter + " ,max : " + Byte.MAX_VALUE + " ,min : " + Byte.MIN_VALUE);
        System.out.println("Short: " + smallNum + " ,max : " + Short.MAX_VALUE + " ,min : " + Short.MIN_VALUE);
        System.out.println("Int: " + normalNum + " ,max : " + Integer.MAX_VALUE + " ,min : " + Integer.MIN_VALUE);
        System.out.println("Long: " + bigNum + " ,max : " + Long.MAX_VALUE + " ,min : " + Long.MIN_VALUE);
        System.out.println("Float: " + floatPointNum + " ,max : " + Float.MAX_VALUE + " ,min : " + Float.MIN_VALUE);
        System.out.println("Double: " + bigFloatPointNum + " ,max : " + Double.MAX_VALUE + " ,min : " + Double.MIN_VALUE);
        System.out.println("Char: " + normalLetter + " ,max : " + Character.MAX_VALUE + " ,min : " + Character.MIN_VALUE);
        System.out.println("Boolean: " + condition + " ,max : " + Boolean.TRUE + " ,min : " + Boolean.FALSE);
    }

    public static void referenceTypes() {
        String greeting = "Hello";
        String copy = greeting;
        System.out.println("greeting: " + greeting);
        System.out.println("Copy    : " + copy);

        int[] original = {1, 2, 3};
        int[] alias = original;
        alias[0] = 99;

        System.out.println("\nAfter alias[0] = 99:");
        System.out.println("\noriginal after:" + original[0]);
        System.out.println("\nalias after:" + alias[0]);
        System.out.println("\nSame object:" + (original==alias));
    }

    public static void typeCasting() {
        int intNum = 250;
        long longNum = intNum;
        float floatNum = longNum;
        double doubleNum = floatNum;
        System.out.println("int -> long:" + longNum);
        System.out.println("long -> float:" + floatNum);
        System.out.println("float -> double:" + doubleNum);

        double nearlyFour = 3.999999;
        int newFour = (int) nearlyFour;
        System.out.println(newFour + " casted to int: " + newFour);

        char letter = 'M';
        int ascii = (int) letter;
        char nextLetter = (char) (ascii+1);
        System.out.println("Char before: " + letter);
        System.out.println("Widening ; " + ascii);
        System.out.println("Narrowing : " + nextLetter);
    }

    public static void operators() {
        int a = 5, b = 12;

        System.out.println("(a + b) -> " + (a + b));
        System.out.println("(a / b) -> " + (a / b));
        System.out.println("(a % b) -> " + (a % b));

        boolean x = true, y = false;
        System.out.println("(x && y) -> " + (x && y));
        System.out.println("(x || y) -> " + (x || y));

        int c = 10;
        c += 5;
        System.out.println("(c += 5) -> " + c);
        c -= 2;
        System.out.println("(c -= 2) -> " + c);

        System.out.println("(5 & 3) -> " + (5 & 3));
        System.out.println("(5 | 3) -> " + (5 | 3));
        System.out.println("(5 ^ 3) -> " + (5 ^ 3));
        System.out.println("(5 << 1) -> " + (5 << 1));

        String result = (a > b) ? "a is greater" : "b is greater ot equal";
        System.out.println("Ternary: " + result);
    }

    public static void inputAndOutput() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter first name: ");
            String name = scanner.next();

            System.out.print("Enter last name: ");
            String lastName = scanner.next();

            System.out.print("Enter age: ");
            int age = scanner.nextInt();

            System.out.println("Hello, " + name + " " + lastName + "!");
            System.out.printf("You are %d years old.%n", age);
            System.out.printf("In 10 years you will be %d.%n", age + 10);
        }
    }

    public static void testComment() {
        /*
         * Multi-line: testing rectangleArea
         * with a 5 x 3 rectangle.
         */
        double area = CommentsTask.rectangleArea(5.0, 3.0);
        System.out.println("Area: " + area); //inline comment
    }

    public static void namingConventions() {
        String multiline = """
            public class MyClass {
                static final int MAX_SIZE = 100;
                int myValue = 5;
                void printHello() {
                    System.out.println("hello");
                }
            }
            """;
        System.out.println(multiline);
    }

    public static void ifElseStatements() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter score (0-100): ");
            int score = scanner.nextInt();
            String grade;

            if (score < 0 || score > 100) {
                grade = "Invalid input";
            } else if (score >= 90) {
                grade = "A";
            } else if (score >= 80) {
                grade = "B";
            } else if (score >= 70) {
                grade = "C";
            } else if (score >= 60) {
                grade = "D";
            } else {
                grade = "F";
            }

            System.out.println("Grade :" + grade);
        }
    }

    public static void switchStatement() {
        int day = 4;

        switch (day) {
            case 1: System.out.println("Monday"); break;
            case 2: System.out.println("Tuesday"); break;
            case 3: System.out.println("Wednesday"); break;
            case 4: System.out.println("Thursday"); break;
            case 5: System.out.println("Friday"); break;
            case 6: System.out.println("Saturday"); break;
            case 7: System.out.println("Sunday"); break;
            default: System.out.println("Invalid");
        }

        String weekday = switch (day) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            case 7 -> "Sunday";
            default -> "Invalid";
        };
        System.out.println("Modern :" + weekday);
    }

    public static void forLoop() {
        for (int i = 1; i <= 10; i++) System.out.println(i + " ");
        System.out.println();

        int sum = 0;
        for (int i = 1; i <= 100; i++) sum += i;
        System.out.println("Sum of 1-100: " + sum);

        for (int i = 1; i <= 10; i++) System.out.printf("4 x %2d = %d%n", i, 4 * i);
    }

    public static void whileLoop() {
        try (Scanner scanner = new Scanner(System.in)) {
            int secret = 42, guess = -1, tries = 0;
            while (secret != guess) {
                System.out.println("Guess number (1-100) : ");
                guess = scanner.nextInt();
                tries ++;
                if (guess < secret) {
                    System.out.println("Too low!");
                } else if (guess > secret) {
                    System.out.println("Too high!");
                }
            }
            System.out.println("It took you : " + tries);

            int num = 123456789, count = 0;
            int temp = Math.abs(num);
            if (temp == 0) {
                count = 1;
            } else {
                while (temp > 0) {
                    temp /= 10;
                    count++;
                }
            }
            System.out.println("Digits of num : " + count);
        }
    }

    public static void doWhileLoop() {
        try (Scanner sc = new Scanner(System.in)) {
            int choice; double result = 0;
            do {
                System.out.println("1. +10  2. -5  3. Quit  [current: " + result + "]");
                choice = sc.nextInt();
                switch (choice) {
                    case 1 -> result += 10;
                    case 2 -> result -= 5;
                    case 3 -> System.out.println("Bye!");
                    default -> System.out.println("Invalid.");
                }
            } while (choice != 3);
        }
    }

    public static void enhancedForLoop() {
        int[] numbers = {34, 7, 23, 90, 12, 56, 3};
        int max = numbers[0];
        for (int n : numbers) if (n > max) max = n;
        System.out.println("Max: " + max);

        List<String> fruits = List.of("Apple", "Banana", "Cherry", "Mango");
        for (String fruit : fruits) System.out.println("  - " + fruit);
    }

    public static void breakAndContinue() {
        int[] data = {5, 13, 8, 21, 4, 99, 17};
        int target = 4, idx = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == target) { idx = i; break; }
        }
        System.out.println("Found " + target + " at index: " + idx);

        System.out.print("Odd 1-20: ");
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0) continue;
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void test() {
        // Special test of a question about IntegerPool
        Integer one;
        Integer two;
        boolean bool;
        int place = 0;

        for (int i = 1; i <= 555; i++) {
            one = i;
            two = i;
            bool = one == two;
            if (bool) {
                place = i;
            }
            System.out.println(bool);
        }
        System.out.println("Place :" + place);
    }

    public static void stringBasics() {
        String s1 = "Hello";              // String Pool
        String s2 = new String("World"); // new heap object

        System.out.println("Length  : " + s1.length());
        System.out.println("charAt 0: " + s1.charAt(0));
        System.out.println("Last    : " + s1.charAt(s1.length() - 1));

        String s3 = s1 + ", " + s2 + "!";
        System.out.println("Concat  : " + s3);
        System.out.println("concat(): " + s1.concat(" Java"));

        System.out.println("isEmpty : " + "".isEmpty());
        System.out.println("isBlank : " + "   ".isBlank()); // Java 11+
    }

    public static void stringMethods() {
        String s = "  Hello, World!  ";
        System.out.println("trim()      : '" + s.trim() + "'");
        System.out.println("toUpper()   : " + s.trim().toUpperCase());
        System.out.println("toLower()   : " + s.trim().toLowerCase());
        System.out.println("replace()   : " + s.trim().replace("World", "Java"));
        System.out.println("contains()  : " + s.contains("World"));
        System.out.println("startsWith(): " + s.trim().startsWith("Hello"));
        System.out.println("indexOf()   : " + s.indexOf("World"));
        System.out.println("substring() : " + s.trim().substring(7, 12));
        String[] parts = s.trim().split(", ");
        System.out.println("split[0]    : " + parts[0]);
        System.out.println("split[1]    : " + parts[1]);
    }

    public static void stringImmutability() {
        String s = "Hello";
        System.out.println("Before id: " + System.identityHashCode(s));
        s = s + " World"; // creates a NEW object; old one may be GC'd
        System.out.println("After  id: " + System.identityHashCode(s)); // different!

        // String Pool — literals are reused
        String a = "Java";
        String b = "Java";
        String c = new String("Java");
        System.out.println("a == b  (pool): " + (a == b));    // true
        System.out.println("a == c  (new) : " + (a == c));    // false
        System.out.println("a.equals(c)   : " + a.equals(c)); // true

        // Performance: bad (many temp objects) vs good (StringBuilder)
        String bad = "";
        for (int i = 0; i < 5; i++) bad += i;
        System.out.println("bad concat   : " + bad);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) sb.append(i);
        System.out.println("StringBuilder: " + sb);
    }

    public static void stringBuilder() {
        StringBuilder sb = new StringBuilder();
        sb.append("The").append(" quick").append(" brown").append(" fox");
        System.out.println("append : " + sb);

        sb.insert(4, " very");
        System.out.println("insert : " + sb);

        sb.delete(4, 9);
        System.out.println("delete : " + sb);

        sb.replace(10, 15, "red");
        System.out.println("replace: " + sb);

        sb.reverse();
        System.out.println("reverse: " + sb);
        sb.reverse(); // restore

        System.out.println("length : " + sb.length());
        System.out.println("result : " + sb.toString());
    }

    public static void stringBuffer() throws InterruptedException {
        StringBuffer buffer = new StringBuffer();

        Thread t1 = new Thread(() -> { for (int i = 0; i < 5; i++) buffer.append("A"); });
        Thread t2 = new Thread(() -> { for (int i = 0; i < 5; i++) buffer.append("B"); });

        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("Result (10 chars): " + buffer);
        System.out.println("Length: " + buffer.length()); // always 10

        // API identical to StringBuilder
        StringBuffer sb = new StringBuffer("Hello");
        sb.append(" World").insert(5, ",");
        System.out.println("Modified: " + sb);
    }

    public static void stringFormatting() {
        System.out.printf("%-15s %5s %8s%n", "Item", "Qty", "Price");
        System.out.println("-".repeat(30));
        System.out.printf("%-15s %5d %8.2f%n", "Apple",  3,  1.50);
        System.out.printf("%-15s %5d %8.2f%n", "Banana", 6,  0.30);
        System.out.printf("%-15s %5d %8.2f%n", "Cherry", 2, 12.00);
        System.out.println("-".repeat(30));
        double total = 3*1.50 + 6*0.30 + 2*12.00;
        System.out.printf("%-15s %5s %8.2f%n", "TOTAL", "", total);

        String msg = String.format("Hello, %s! You have %d messages.", "Bob", 5);
        System.out.println("\n" + msg);
        System.out.printf("Hex: %x  |  Sci: %e%n", 255, 123456.789);
    }

    public static void stringComparison() {
        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = new String("Hello");

        System.out.println("s1 == s2 (pool) : " + (s1 == s2));        // true
        System.out.println("s1 == s3 (new)  : " + (s1 == s3));        // false
        System.out.println("s1.equals(s3)   : " + s1.equals(s3));     // true
        System.out.println("equalsIgnoreCase: " + s1.equalsIgnoreCase("hello"));

        int cmp = "apple".compareTo("banana");
        System.out.println("\napple vs banana: " + cmp); // negative
        System.out.println("First alpha     : " + (cmp < 0 ? "apple" : "banana"));

        String url = "https://example.com/page";
        System.out.println("\nstartsWith https: " + url.startsWith("https"));
        System.out.println("endsWith   /page: " + url.endsWith("/page"));
    }

//    public static void regularExpressions() {
//        // Validate email
//        String emailRe = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$";
//        for (String e : new String[]{"user@example.com", "bad@", "a@b.io"})
//            System.out.printf("%-20s -> %s%n", e, e.matches(emailRe) ? "valid" : "invalid");
//
//        // Extract numbers
//        String text = "Order 42 has 3 items worth $99 and 2 extras";
//        Matcher m = Pattern.compile("\d+").matcher(text);
//        System.out.print("Numbers: ");
//        while (m.find()) System.out.print(m.group() + " ");
//        System.out.println();
//
//        // Collapse spaces
//        String messy = "Hello    World   from   Java";
//        System.out.println("Cleaned: '" + messy.replaceAll("\s+", " ") + "'");
//    }

    public static void arraysBasics() {
        int[] a = new int[5];
        int[] b = {10, 20, 30, 40, 50};
        int[] c = new int[]{1, 2, 3};

        a[0] = 100; a[4] = 500;
        System.out.println("a[0]=" + a[0] + ", a[4]=" + a[4]);
        System.out.println("b.length: " + b.length);
        System.out.print("Array b: ");
        for (int v : b) System.out.print(v + " ");
        System.out.println();
        System.out.println("Array c: " + Arrays.toString(c));

        try {
            System.out.println(b[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }

    public static void arrayOperations() {
        int[] nums = {64, 25, 12, 92, 38, 7, 55};
        int min = nums[0], max = nums[0], sum = 0;
        for (int n : nums) {
            if (n < min) min = n;
            if (n > max) max = n;
            sum += n;
        }
        System.out.println("Min    : " + min);
        System.out.println("Max    : " + max);
        System.out.printf ("Average: %.2f%n", (double) sum / nums.length);

        // Two-pointer reverse
        int l = 0, r = nums.length - 1;
        while (l < r) { int t = nums[l]; nums[l++] = nums[r]; nums[r--] = t; }
        System.out.print("Reversed: ");
        for (int n : nums) System.out.print(n + " ");
    }

    public static void multidimensionalArrays() {
        int[][] matrix = new int[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                matrix[i][j] = (i + 1) * (j + 1);

        System.out.println("3x3 Table:");
        for (int[] row : matrix) {
            for (int v : row) System.out.printf("%4d", v);
            System.out.println();
        }

        // Jagged — rows of different lengths
        int[][] jagged = new int[4][];
        for (int i = 0; i < jagged.length; i++) {
            jagged[i] = new int[i + 1];
            for (int j = 0; j <= i; j++) jagged[i][j] = i + j;
        }
        System.out.println("\nJagged:");
        for (int[] row : jagged) {
            for (int v : row) System.out.print(v + " ");
            System.out.println();
        }
    }

    public static void arraysClass() {
        int[] nums = {64, 25, 12, 92, 38, 7, 55};

        Arrays.sort(nums);
        System.out.println("Sorted : " + Arrays.toString(nums));

        int idx = Arrays.binarySearch(nums, 38);
        System.out.println("38 at  : index " + idx);

        int[] filled = new int[5];
        Arrays.fill(filled, 7);
        System.out.println("Filled : " + Arrays.toString(filled));

        int[] a = {1, 2, 3}, b = {1, 2, 3}, c = {1, 2, 4};
        System.out.println("a==b   : " + Arrays.equals(a, b));
        System.out.println("a==c   : " + Arrays.equals(a, c));

        int[][] mat = {{1, 2}, {3, 4}};
        System.out.println("2D     : " + Arrays.deepToString(mat));
    }

    public static void copyingArrays() {
        int[] orig = {1, 2, 3, 4, 5};

        // Manual loop
        int[] loop = new int[orig.length];
        for (int i = 0; i < orig.length; i++) loop[i] = orig[i];

        // System.arraycopy
        int[] sys = new int[orig.length];
        System.arraycopy(orig, 0, sys, 0, orig.length);

        // Arrays.copyOf (can resize)
        int[] copy     = Arrays.copyOf(orig, orig.length);
        int[] extended = Arrays.copyOf(orig, 8);

        // clone
        int[] cloned = orig.clone();

        orig[0] = 999;
        System.out.println("Original : " + Arrays.toString(orig));
        System.out.println("Loop     : " + Arrays.toString(loop));
        System.out.println("Sys      : " + Arrays.toString(sys));
        System.out.println("copyOf   : " + Arrays.toString(copy));
        System.out.println("Extended : " + Arrays.toString(extended));
        System.out.println("Cloned   : " + Arrays.toString(cloned));
    }

    static void printWelcome() {
        System.out.println("Welcome to Java Methods!");
    }

    static int square(int n) {
        return n * n;
    }

    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0) return false;
        return true;
    }

    static void printSquare(int n) {
        System.out.println(n + "^2 = " + (n * n));
    }

    static double bmi(double kg, double m) {
        return kg / (m * m);
    }

    static void greet(String name, String g) {
        System.out.println(g + ", " + name + "!");
    }
    static void greet(String name) { greet(name, "Hello"); } // default

    static int     add(int a, int b)         { return a + b; }
    static double  divide(double a, double b){ return a / b; }
    static boolean isEven(int n)             { return n % 2 == 0; }
    static String  classify(int n) {
        if (n > 0) return "positive";
        if (n < 0) return "negative";
        return "zero";
    }
    static int[] range(int start, int end) {
        int[] arr = new int[end - start];
        for (int i = 0; i < arr.length; i++) arr[i] = start + i;
        return arr;
    }

    static double area(double radius) {
        return Math.PI * radius * radius;
    }
    static double area(double w, double h) {
        return w * h;
    }
    static double area(double a, double b, double c) {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s-a) * (s-b) * (s-c));
    }

    static int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) total += n;
        return total;
    }

    static void printAll(String label, String... items) {
        System.out.print(label + ": ");
        for (String s : items) System.out.print(s + " ");
        System.out.println();
    }

    static long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    static int binarySearch(int[] a, int t, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = (lo + hi) / 2;
        if (a[mid] == t) return mid;
        return a[mid] < t ? binarySearch(a, t, mid+1, hi)
            : binarySearch(a, t, lo, mid-1);
    }

    static void hanoi(int n, char from, char to, char aux) {
        if (n == 1) { System.out.println("Disk 1: " + from + " -> " + to); return; }
        hanoi(n-1, from, aux, to);
        System.out.println("Disk " + n + ": " + from + " -> " + to);
        hanoi(n-1, aux, to, from);
    }

    static void tryChangeInt(int x) {
        x = 999;
        System.out.println("  inside (prim): " + x);
    }
    static void tryReassign(int[] arr) {
        arr = new int[]{99, 99, 99}; // only local ref changes
        System.out.println("  inside (reassign): " + Arrays.toString(arr));
    }
    static void mutate(int[] arr) {
        arr[0] = 999; // modifies the actual heap object
    }

    // Generic class
    static class Pair<A, B> {
        private A first;
        private B second;

        Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        A getFirst()  { return first; }
        B getSecond() { return second; }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }

    // Generic method
    static <T> void printArray(T[] arr) {
        for (T item : arr) System.out.print(item + " ");
        System.out.println();
    }

    static class Stack<T> {
        private ArrayList<T> data = new ArrayList<>();

        void push(T item) {
            data.add(item);
        }

        T pop() {
            if (isEmpty()) throw new RuntimeException("Stack is empty");
            return data.remove(data.size() - 1);
        }

        T peek() {
            if (isEmpty()) throw new RuntimeException("Stack is empty");
            return data.get(data.size() - 1);
        }

        boolean isEmpty() { return data.isEmpty(); }
        int     size()    { return data.size(); }

        @Override
        public String toString() { return data.toString(); }
    }

    // Swap two elements in any array
    static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Max element — T must be Comparable
    static <T extends Comparable<T>> T max(List<T> list) {
        if (list.isEmpty()) throw new NoSuchElementException();
        T result = list.get(0);
        for (T item : list)
            if (item.compareTo(result) > 0) result = item;
        return result;
    }

    // Array to List
    static <T> List<T> toList(T[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }

    // Upper bound: T must be Number or a subclass
    static class NumberBox<T extends Number> {
        private T value;

        NumberBox(T value) { this.value = value; }

        double doubled()  { return value.doubleValue() * 2; }
        boolean isWhole() { return value.doubleValue() == Math.floor(value.doubleValue()); }

        @Override
        public String toString() { return "NumberBox[" + value + "]"; }
    }

    // Sum a list of any numeric type
    static <T extends Number> double sum(List<T> list) {
        double total = 0;
        for (T item : list) total += item.doubleValue();
        return total;
    }

    // Unbounded: reads anything as Object
    static void printList(List<?> list) {
        for (Object o : list) System.out.print(o + " ");
        System.out.println();
    }

    // Upper bounded: reads Numbers (producer — use extends)
    static double sumList(List<? extends Number> list) {
        double sum = 0;
        for (Number n : list) sum += n.doubleValue();
        return sum;
    }

    // Lower bounded: adds Integers into a list (consumer — use super)
    static void addIntegers(List<? super Integer> list, int count) {
        for (int i = 1; i <= count; i++) list.add(i);
    }

    static class Box<T> {
        private T value;
        Box(T v) { this.value = v; }
        T get()  { return value; }
    }

    static <T> void printTyped(T value, Class<T> clazz) {
        System.out.println("Value: " + value + " | Type: " + clazz.getSimpleName());
    }

    static class Container<T> {
        private T value;
        // private static T staticField; // COMPILE ERROR — cannot use T in static context

        Container(T value) { this.value = value; }

        void demo() {
            // Cannot do: T obj = new T(); — COMPILE ERROR
            // Cannot do: T[] arr = new T[10]; — COMPILE ERROR
            // Cannot do: if (value instanceof T) — COMPILE ERROR

            // Workaround: use Object array and cast (like ArrayList does internally)
            Object[] rawArr = new Object[5];
            rawArr[0] = value;
            System.out.println("Raw array[0]: " + rawArr[0]);
        }
    }

    @FunctionalInterface
    interface MathOperation {
        int operate(int a, int b);
    }

    static int doubleIt(int n) { return n * 2; }

    static class Greeter {
        String prefix;
        Greeter(String p) { this.prefix = p; }
        void greet(String name) { System.out.println(prefix + name); }
    }

    record Employee(String name, double salary) {}

    record Person(String name, String city, int age) {}

    static Optional<String> findUser(int id) {
        Map<Integer, String> db = Map.of(1, "Alice", 2, "Bob", 3, "Carol");
        return Optional.ofNullable(db.get(id));
    }

    static Optional<String> getEmail(String name) {
        Map<String, String> emails = Map.of("Alice", "alice@example.com");
        return Optional.ofNullable(emails.get(name));
    }

    public static void main() {
        variableAndDataTypes();
        primitiveTypes();
        referenceTypes();
        typeCasting();
        operators();
        //inputAndOutput();
        testComment();
        namingConventions();
        //ifElseStatements();
        switchStatement();
        forLoop();
        //whileLoop();
        //doWhileLoop();
        enhancedForLoop();
        breakAndContinue();
        //test();
        stringBasics();
        stringMethods();
        stringImmutability();
        stringBuilder();
        try {
            stringBuffer();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        stringFormatting();
        stringComparison();
        arraysBasics();
        arrayOperations();
        multidimensionalArrays();
        arraysClass();
        copyingArrays();

        printWelcome();
        System.out.println("7 squared = " + square(7));
        System.out.print("Primes to 20: ");
        for (int i = 2; i <= 20; i++)
            if (isPrime(i)) System.out.print(i + " ");
        System.out.println();

        printSquare(5);
        System.out.printf("BMI: %.2f%n", bmi(70, 1.75));
        greet("Alice");
        greet("Bob", "Good morning");

        System.out.println("add(3,4)     = " + add(3, 4));
        System.out.println("divide(7,2)  = " + divide(7, 2));
        System.out.println("isEven(8)    = " + isEven(8));
        System.out.println("classify(-3) = " + classify(-3));
        System.out.println("range(1,6)   = " + Arrays.toString(range(1, 6)));

        System.out.printf("Circle (r=5)   : %.4f%n", area(5.0));
        System.out.printf("Rectangle (4x6): %.4f%n", area(4.0, 6.0));
        System.out.printf("Triangle(3,4,5): %.4f%n", area(3.0, 4.0, 5.0));

        System.out.println(sum());
        System.out.println(sum(5));
        System.out.println(sum(1, 2, 3, 4, 5));
        System.out.println(sum(new int[]{10, 20, 30}));

        printAll("Colors", "red", "green", "blue");
        printAll("Empty");

        System.out.println("5! = " + factorial(5));
        System.out.print("Fibonacci: ");
        for (int i = 0; i < 8; i++) System.out.print(fibonacci(i) + " ");
        int[] sorted = {3, 7, 12, 19, 25, 38, 44};
        System.out.println("\nBinarySearch 25: " + binarySearch(sorted, 25, 0, sorted.length-1));
        System.out.println("\nHanoi (3 disks):");
        hanoi(3, 'A', 'C', 'B');

        int n = 42;
        tryChangeInt(n);
        System.out.println("After changeInt: " + n); // still 42

        int[] a = {1, 2, 3};
        tryReassign(a);
        System.out.println("After reassign : " + Arrays.toString(a)); // unchanged

        int[] b = {1, 2, 3};
        mutate(b);
        System.out.println("After mutate   : " + Arrays.toString(b)); // [999,2,3]

        Pair<String, Integer> p1 = new Pair<>("Alice", 30);
        Pair<Double, Boolean> p2 = new Pair<>(3.14, true);

        System.out.println("Pair 1: " + p1);
        System.out.println("Name  : " + p1.getFirst());
        System.out.println("Age   : " + p1.getSecond());
        System.out.println("Pair 2: " + p2);

        Integer[] nums    = {1, 2, 3, 4, 5};
        String[]  fruits  = {"Apple", "Mango", "Cherry"};
        System.out.print("Ints   : "); printArray(nums);
        System.out.print("Strings: "); printArray(fruits);

        Stack<String> words = new Stack<>();
        words.push("Java");
        words.push("is");
        words.push("fun");
        System.out.println("Stack  : " + words);
        System.out.println("Peek   : " + words.peek());
        System.out.println("Pop    : " + words.pop());
        System.out.println("After  : " + words);

        Stack<Integer> nums1 = new Stack<>();
        for (int i = 1; i <= 5; i++) nums1.push(i * 10);
        System.out.println("\nNums   : " + nums1);
        System.out.println("Size   : " + nums1.size());
        while (!nums1.isEmpty()) System.out.print(nums1.pop() + " ");
        System.out.println();

        Integer[] nums2 = {1, 2, 3, 4, 5};
        System.out.println("Before swap: " + Arrays.toString(nums2));
        swap(nums2, 0, 4);
        System.out.println("After swap : " + Arrays.toString(nums2));

        List<Integer> ints    = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6);
        List<String>  strings = Arrays.asList("banana", "apple", "cherry");
        System.out.println("Max int    : " + max(ints));
        System.out.println("Max string : " + max(strings));

        String[] fruits1 = {"Mango", "Kiwi", "Peach"};
        List<String> fruitList = toList(fruits1);
        System.out.println("As list    : " + fruitList);

        NumberBox<Integer> intBox    = new NumberBox<>(42);
        NumberBox<Double>  dblBox    = new NumberBox<>(3.14);
        NumberBox<Long>    longBox   = new NumberBox<>(100L);

        System.out.println(intBox  + " doubled=" + intBox.doubled()  + " whole=" + intBox.isWhole());
        System.out.println(dblBox  + " doubled=" + dblBox.doubled()  + " whole=" + dblBox.isWhole());
        System.out.println(longBox + " doubled=" + longBox.doubled() + " whole=" + longBox.isWhole());

        List<Integer> ints1    = List.of(1, 2, 3, 4, 5);
        List<Double>  doubles = List.of(1.5, 2.5, 3.0);
        System.out.println("\nSum ints   : " + sum(ints1));
        System.out.println("Sum doubles: " + sum(doubles));

        List<Integer> ints2    = Arrays.asList(1, 2, 3);
        List<Double>  doubles1 = Arrays.asList(1.5, 2.5, 3.0);
        List<String>  strings1 = Arrays.asList("a", "b", "c");

        System.out.print("Ints   : "); printList(ints2);
        System.out.print("Doubles: "); printList(doubles1);
        System.out.print("Strings: "); printList(strings1);

        System.out.println("Sum ints   : " + sumList(ints2));
        System.out.println("Sum doubles: " + sumList(doubles1));

        List<Number> numbers = new ArrayList<>();
        addIntegers(numbers, 5);
        System.out.println("Added to Number list: " + numbers);

        Box<String>  sBox = new Box<>("Hello");
        Box<Integer> iBox = new Box<>(42);

        // At runtime, both are just "Box" — type info is erased
        System.out.println("Same class? " + (sBox.getClass() == iBox.getClass())); // true
        System.out.println("Class name : " + sBox.getClass().getName());

        // Cannot use instanceof with generic types
        // if (sBox instanceof Box<String>) — COMPILE ERROR
        // Use raw type instead:
        System.out.println("Is Box?    : " + (sBox instanceof Box));

        // Lists also share the same raw type
        List<String>  ls = new ArrayList<>();
        List<Integer> li = new ArrayList<>();
        System.out.println("Same list class: " + (ls.getClass() == li.getClass())); // true

        // Workaround: pass Class<T> token to get type at runtime
        System.out.println("\nUsing class token:");
        printTyped("World", String.class);
        printTyped(100,     Integer.class);

        // Cannot use primitives as type arguments
        // Container<int> bad = new Container<>(5); // COMPILE ERROR
        Container<Integer> ok = new Container<>(5); // use wrapper
        ok.demo();

        // Generic arrays are not allowed
        // List<String>[] arr = new ArrayList<String>[5]; // COMPILE ERROR
        // Workaround: use a raw type or List of Lists
        @SuppressWarnings("unchecked")
        List<String>[] workaround = new ArrayList[5]; // unchecked warning
        workaround[0] = new ArrayList<>();
        workaround[0].add("OK");
        System.out.println("Array workaround: " + workaround[0]);

        // Overloading by erasure is forbidden
        // void process(List<String> l) {}
        // void process(List<Integer> l) {} // COMPILE ERROR — same erasure
        System.out.println("\nGeneric restrictions demonstrated.");

        // Runnable (no params, no return)
        Runnable r = () -> System.out.println("Running!");
        r.run();

        // MathOperation — various syntax forms
        MathOperation add      = (c, d) -> c + d;
        MathOperation multiply = (c, d) -> c * d;
        MathOperation max      = (c, d) -> { return c > d ? c : d; }; // block body

        System.out.println("5 + 3 = " + add.operate(5, 3));
        System.out.println("5 * 3 = " + multiply.operate(5, 3));
        System.out.println("max(5,3) = " + max.operate(5, 3));

        // Comparator for sorting
        List<String> names = new ArrayList<>(Arrays.asList("Charlie", "Alice", "Bob", "Dave"));
        names.sort((c, d) -> c.compareTo(d));          // ascending
        System.out.println("Sorted asc : " + names);
        names.sort((c, d) -> d.compareTo(c));          // descending
        System.out.println("Sorted desc: " + names);

        // Capturing effectively final variable
        String prefix = "Hello, ";
        names.forEach(name -> System.out.println(prefix + name));

        // 1. Static method reference: ClassName::staticMethod
        Function<Integer, Integer> dbl = ExercisesWeekOne::doubleIt;
        System.out.println("Double 5: " + dbl.apply(5));

        // 2. Instance method on a particular object: instance::method
        Greeter g = new Greeter("Hello, ");
        Consumer<String> greet = g::greet;
        greet.accept("Alice");

        // 3. Instance method on arbitrary object: ClassName::instanceMethod
        List<String> names1 = Arrays.asList("Charlie", "Alice", "Bob");
        names1.sort(String::compareTo);          // equivalent to (a,b) -> a.compareTo(b)
        System.out.println("Sorted: " + names1);

        // 4. Constructor reference: ClassName::new
        Supplier<ArrayList<String>> listFactory = ArrayList::new;
        ArrayList<String> list = listFactory.get();
        list.add("Java");
        list.add("Rocks");
        System.out.println("List: " + list);

        // Common use — println as consumer
        names.forEach(System.out::println);

        // Create stream from List
        List<Integer> nums3 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // filter + forEach
        System.out.print("Even numbers: ");
        nums3.stream()
            .filter(n1 -> n1 % 2 == 0)
            .forEach(n1 -> System.out.print(n1 + " "));
        System.out.println();

        // map + collect
        List<Integer> squared = nums3.stream()
            .map(n1 -> n1 * n1)
            .collect(Collectors.toList());
        System.out.println("Squared: " + squared);

        // Stream from array
        int[] arr = {5, 3, 8, 1, 9, 2};
        int sum = Arrays.stream(arr).sum();
        System.out.println("Array sum: " + sum);

        // Streams are single-use!
        Stream<Integer> stream = nums3.stream().filter(n1 -> n1 > 5);
        System.out.println("Count > 5: " + stream.count());
        // stream.count() again would throw IllegalStateException

        List<Employee> employees = List.of(
            new Employee("Alice",  85_000),
            new Employee("Bob",    42_000),
            new Employee("Carol",  95_000),
            new Employee("Dave",   67_000),
            new Employee("Eve",   110_000),
            new Employee("Frank",  38_000)
        );

        // Full pipeline: filter → sort → map → limit → collect
        List<String> result = employees.stream()
            .filter(e -> e.salary() > 60_000)           // keep high earners
            .sorted(Comparator.comparingDouble(Employee::salary).reversed()) // highest first
            .map(e -> e.name().toUpperCase())            // extract & format name
            .limit(3)                                    // top 3
            .collect(Collectors.toList());

        System.out.println("Top 3 high earners: " + result);

        // Count, min, max using streams
        OptionalDouble avg = employees.stream()
            .mapToDouble(Employee::salary)
            .average();
        System.out.printf("Avg salary: $%.0f%n", avg.orElse(0));

        double maxSalary = employees.stream()
            .mapToDouble(Employee::salary)
            .max()
            .orElse(0);
        System.out.printf("Max salary: $%.0f%n", maxSalary);

        List<Integer> nums4 = Arrays.asList(5, 3, 8, 1, 9, 2, 3, 7, 5, 1);

        // filter — keep elements matching predicate
        System.out.print("filter > 4: ");
        nums4.stream().filter(n1 -> n1 > 4).forEach(n1 -> System.out.print(n1 + " "));
        System.out.println();

        // map — transform each element
        System.out.print("map *2    : ");
        nums4.stream().map(n1 -> n1 * 2).forEach(n1 -> System.out.print(n1 + " "));
        System.out.println();

        // distinct — remove duplicates
        System.out.print("distinct  : ");
        nums4.stream().distinct().forEach(n1 -> System.out.print(n1 + " "));
        System.out.println();

        // sorted — natural order
        System.out.print("sorted    : ");
        nums4.stream().distinct().sorted().forEach(n1 -> System.out.print(n1 + " "));
        System.out.println();

        // limit + skip — pagination
        List<Integer> page2 = nums4.stream().distinct().sorted()
            .skip(3).limit(3).collect(Collectors.toList());
        System.out.println("skip3,lim3: " + page2);

        // flatMap — flatten nested collections
        List<List<Integer>> nested = List.of(List.of(1, 2), List.of(3, 4), List.of(5));
        List<Integer> flat = nested.stream()
            .flatMap(List::stream)
            .collect(Collectors.toList());
        System.out.println("flatMap   : " + flat);

        // peek — inspect without consuming (useful for debugging)
        long count = nums4.stream()
            .filter(n1 -> n1 > 3)
            .peek(n1 -> System.out.print("peek:" + n1 + " "))
            .count();
        System.out.println(" count > 3 : " + count);

        List<Integer> nums5 = Arrays.asList(4, 7, 2, 9, 1, 5, 8, 3, 6);

        System.out.println("count      : " + nums5.stream().count());
        System.out.println("sum        : " + nums5.stream().mapToInt(Integer::intValue).sum());
        System.out.println("min        : " + nums5.stream().mapToInt(Integer::intValue).min().getAsInt());
        System.out.println("max        : " + nums5.stream().mapToInt(Integer::intValue).max().getAsInt());
        System.out.printf ("average    : %.2f%n", nums5.stream().mapToInt(Integer::intValue).average().getAsDouble());

        System.out.println("anyMatch>8 : " + nums5.stream().anyMatch(n1 -> n1 > 8));
        System.out.println("allMatch>0 : " + nums5.stream().allMatch(n1 -> n1 > 0));
        System.out.println("noneMatch<0: " + nums5.stream().noneMatch(n1 -> n1 < 0));

        Optional<Integer> first = nums5.stream().filter(n1 -> n1 > 5).findFirst();
        System.out.println("findFirst>5: " + first.orElse(-1));

        // reduce — combine all elements into one value
        int product = nums5.stream().reduce(1, (c, d) -> c * d);
        System.out.println("reduce (*) : " + product);

        String joined = List.of("Java","is","great").stream()
            .reduce("", (c, d) -> c.isEmpty() ? d : c + " " + d);
        System.out.println("reduce (+) : " + joined);

        List<Person> people = List.of(
            new Person("Alice", "Munich",  30),
            new Person("Bob",   "Berlin",  25),
            new Person("Carol", "Munich",  35),
            new Person("Dave",  "Berlin",  28),
            new Person("Eve",   "Hamburg", 22)
        );

        // toList / toSet
        List<String> names2 = people.stream().map(Person::name).collect(Collectors.toList());
        System.out.println("Names: " + names2);

        // joining
        String joined1 = people.stream().map(Person::name).collect(Collectors.joining(", ", "[", "]"));
        System.out.println("Joined: " + joined1);

        // groupingBy
        Map<String, List<Person>> byCity = people.stream().collect(Collectors.groupingBy(Person::city));
        byCity.forEach((city, ps) ->
            System.out.println(city + ": " + ps.stream().map(Person::name).collect(Collectors.joining(", "))));

        // counting per group
        Map<String, Long> countByCity = people.stream().collect(Collectors.groupingBy(Person::city, Collectors.counting()));
        System.out.println("Count: " + countByCity);

        // partitioningBy
        Map<Boolean, List<String>> byAdult = people.stream()
            .collect(Collectors.partitioningBy(p -> p.age() >= 30, Collectors.mapping(Person::name, Collectors.toList())));
        System.out.println(">=30 : " + byAdult.get(true));
        System.out.println("< 30 : " + byAdult.get(false));

        // summarizingInt
        IntSummaryStatistics stats = people.stream().collect(Collectors.summarizingInt(Person::age));
        System.out.printf("Age stats: min=%d max=%d avg=%.1f%n", stats.getMin(), stats.getMax(), stats.getAverage());

        List<Integer> bigList = IntStream.rangeClosed(1, 1_000_000)
            .boxed().collect(Collectors.toList());

        // Sequential
        long t1 = System.currentTimeMillis();
        long seqSum = bigList.stream()
            .mapToLong(Integer::longValue).sum();
        long seqTime = System.currentTimeMillis() - t1;

        // Parallel
        long t2 = System.currentTimeMillis();
        long parSum = bigList.parallelStream()
            .mapToLong(Integer::longValue).sum();
        long parTime = System.currentTimeMillis() - t2;

        System.out.println("Sequential sum : " + seqSum + " (" + seqTime + "ms)");
        System.out.println("Parallel sum   : " + parSum + " (" + parTime + "ms)");

        // Ordering: sequential preserves order, parallel may not
        System.out.print("Sequential: ");
        IntStream.range(0, 5).forEach(i -> System.out.print(i + " "));
        System.out.println();

        System.out.print("Parallel  : ");
        IntStream.range(0, 5).parallel().forEach(i -> System.out.print(i + " "));
        System.out.println("(order may vary)");

        // forEachOrdered restores order in parallel
        System.out.print("Ordered   : ");
        IntStream.range(0, 5).parallel().forEachOrdered(i -> System.out.print(i + " "));
        System.out.println();

        // Creating Optional
        Optional<String> present = Optional.of("Hello");
        Optional<String> empty   = Optional.empty();
        Optional<String> nullable = Optional.ofNullable(null);

        System.out.println("present.isPresent(): " + present.isPresent());
        System.out.println("empty.isEmpty()     : " + empty.isEmpty());     // Java 11+

        // get / orElse / orElseGet / orElseThrow
        System.out.println("present.get()       : " + present.get());
        System.out.println("empty.orElse(\"??\")   : " + empty.orElse("??"));
        System.out.println("orElseGet           : " + empty.orElseGet(() -> "computed"));

        // ifPresent
        present.ifPresent(v -> System.out.println("ifPresent: " + v));

        // map and filter
        Optional<Integer> length = present.map(String::length);
        System.out.println("map to length       : " + length.orElse(0));

        Optional<String> upper = present.filter(s -> s.length() > 3).map(String::toUpperCase);
        System.out.println("filter+map          : " + upper.orElse("short"));

        // Chaining Optionals with flatMap
        Optional<String> email = findUser(1).flatMap(ExercisesWeekOne::getEmail);
        System.out.println("\nUser 1 email: " + email.orElse("not found"));
        Optional<String> email2 = findUser(2).flatMap(ExercisesWeekOne::getEmail);
        System.out.println("User 2 email: " + email2.orElse("not found"));
    }
}

