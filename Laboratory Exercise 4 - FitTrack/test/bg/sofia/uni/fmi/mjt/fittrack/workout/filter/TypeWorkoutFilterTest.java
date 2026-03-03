package bg.sofia.uni.fmi.mjt.fittrack.workout.filter;

import bg.sofia.uni.fmi.mjt.fittrack.workout.CardioWorkout;
import bg.sofia.uni.fmi.mjt.fittrack.workout.StrengthWorkout;
import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;
import bg.sofia.uni.fmi.mjt.fittrack.workout.WorkoutType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TypeWorkoutFilterTest {

    @Test
    void testValidationInTypeWorkoutFilter() {
        assertThrows(IllegalArgumentException.class, () -> new TypeWorkoutFilter(null),
            "Should throw exception because name is null.");
    }

    @Test
    void testMatchesCorrectResult() {
        TypeWorkoutFilter filter1 = new TypeWorkoutFilter(WorkoutType.STRENGTH);
        Workout workout1 = new CardioWorkout("EXC", 10, 5, 4);
        Workout workout2 = new StrengthWorkout("ABD", 80, 250, 5);
        assertFalse(filter1.matches(workout1), "Should be false because is not a type match.");
        assertTrue(filter1.matches(workout2), "Should be true because is a type match.");
    }
}
