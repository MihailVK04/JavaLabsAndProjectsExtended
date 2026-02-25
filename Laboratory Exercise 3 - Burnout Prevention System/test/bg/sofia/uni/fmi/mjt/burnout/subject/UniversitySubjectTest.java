package bg.sofia.uni.fmi.mjt.burnout.subject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniversitySubjectTest {

    @Test
    void testConstructorThrowsException() {
        assertThrows(IllegalArgumentException.class,
            () -> new UniversitySubject(null, 4, 3, Category.MATH, 25),
            "The constructor should throw the correct exception because of name(null)");
        assertThrows(IllegalArgumentException.class,
            () -> new UniversitySubject("", 4, 3, Category.MATH, 25),
            "The constructor should throw the correct exception because of name(blank)");
        assertThrows(IllegalArgumentException.class,
            () -> new UniversitySubject("Discrete Math", 0, 3, Category.MATH, 25),
            "The constructor should throw the correct exception because of credits(0)");
        assertThrows(IllegalArgumentException.class,
            () -> new UniversitySubject("Discrete Math", 4, -1, Category.MATH, 25),
            "The constructor should throw the correct exception because of rating(too low)");
        assertThrows(IllegalArgumentException.class,
            () -> new UniversitySubject("Discrete Math", 4, 6, Category.MATH, 25),
            "The constructor should throw the correct exception because of rating(too high)");
        assertThrows(IllegalArgumentException.class,
            () -> new UniversitySubject("Discrete Math", 4, 3, null, 25),
            "The constructor should throw the correct exception because of Category(null)");
        assertThrows(IllegalArgumentException.class,
            () -> new UniversitySubject("Discrete Math", 4, 3, Category.MATH, -5),
            "The constructor should throw the correct exception because of study time(negative)");
    }
}
