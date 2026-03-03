import java.util.List;
import java.util.Scanner;

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
    }
}

