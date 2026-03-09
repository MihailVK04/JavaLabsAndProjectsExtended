import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    }
}

