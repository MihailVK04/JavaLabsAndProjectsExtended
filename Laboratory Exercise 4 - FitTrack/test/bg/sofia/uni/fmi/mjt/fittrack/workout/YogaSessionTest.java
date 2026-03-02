package bg.sofia.uni.fmi.mjt.fittrack.workout;

import bg.sofia.uni.fmi.mjt.fittrack.exception.InvalidWorkoutException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class YogaSessionTest {

    @Test
    void testValidationInCardioWorkout() {
        assertThrows(InvalidWorkoutException.class,
            () -> new YogaSession(null, 5, 5, 5),
            "Should throw exception because of name.");
        assertThrows(InvalidWorkoutException.class,
            () -> new YogaSession("", 5, 5, 5),
            "Should throw exception because of name.");
        assertThrows(InvalidWorkoutException.class,
            () -> new YogaSession("Running", -5, 5, 5),
            "Should throw exception because of duration.");
        assertThrows(InvalidWorkoutException.class,
            () -> new YogaSession("Running", 5, -5, 5),
            "Should throw exception because of cardio.");
        assertThrows(InvalidWorkoutException.class,
            () -> new YogaSession("Running", 5, 5, 7),
            "Should throw exception because of difficulty(higher).");
        assertThrows(InvalidWorkoutException.class,
            () -> new YogaSession("Running", 5, 5, -7),
            "Should throw exception because of difficulty(lower).");
    }
}
