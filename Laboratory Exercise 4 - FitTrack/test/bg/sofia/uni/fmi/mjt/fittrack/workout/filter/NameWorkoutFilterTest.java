package bg.sofia.uni.fmi.mjt.fittrack.workout.filter;

import bg.sofia.uni.fmi.mjt.fittrack.workout.CardioWorkout;
import bg.sofia.uni.fmi.mjt.fittrack.workout.StrengthWorkout;
import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NameWorkoutFilterTest {

    @Test
    void testValidationInNameWorkoutFilter() {
        assertThrows(IllegalArgumentException.class, () -> new NameWorkoutFilter(null, false),
            "Should throw exception because name is null.");
        assertThrows(IllegalArgumentException.class, () -> new NameWorkoutFilter("", false),
            "Should throw exception because name is empty.");
    }

    @Test
    void testMatchesCorrectResult() {
        NameWorkoutFilter filter1 = new NameWorkoutFilter("AB", false);
        NameWorkoutFilter filter2 = new NameWorkoutFilter("AB", true);
        Workout workout1 = new CardioWorkout("EXC", 10, 5, 4);
        Workout workout2 = new StrengthWorkout("ABD", 80, 250, 5);
        Workout workout3 = new StrengthWorkout("AB", 80, 250, 5);
        assertFalse(filter2.matches(workout2), "Should be false because is not a full match.");
        assertFalse(filter1.matches(workout1), "Should be false because is not a match.");
        assertTrue(filter1.matches(workout2), "Should be true because is a match.");
        assertTrue(filter2.matches(workout3), "Should be true because is a full match.");
    }
}
