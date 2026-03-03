package bg.sofia.uni.fmi.mjt.fittrack.workout.filter;

import bg.sofia.uni.fmi.mjt.fittrack.workout.CardioWorkout;
import bg.sofia.uni.fmi.mjt.fittrack.workout.StrengthWorkout;
import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaloriesWorkoutFilterTest {

    @Test
    void testValidationInCaloriesWorkoutFilter() {
        int min = -5, max = -3;
        assertThrows(IllegalArgumentException.class, () -> new CaloriesWorkoutFilter(min, max),
            "Should throw exception because min and max are negative.");
        int min1 = -5, max1 = 3;
        assertThrows(IllegalArgumentException.class, () -> new CaloriesWorkoutFilter(min1, max1),
            "Should throw exception because min is negative.");
        int min2 = 5, max2 = 3;
        assertThrows(IllegalArgumentException.class, () -> new CaloriesWorkoutFilter(min2, max2),
            "Should throw exception because min is bigger than max.");
    }

    @Test
    void testMatchesCorrectResult() {
        CaloriesWorkoutFilter filter = new CaloriesWorkoutFilter(20, 300);
        Workout workout1 = new CardioWorkout("ABC", 90, 5, 4);
        Workout workout2 = new StrengthWorkout("ABD", 80, 250, 5);
        assertFalse(filter.matches(workout1), "Should be false because is not in range.");
        assertTrue(filter.matches(workout2), "Should be true because is in the range.");
    }
}
