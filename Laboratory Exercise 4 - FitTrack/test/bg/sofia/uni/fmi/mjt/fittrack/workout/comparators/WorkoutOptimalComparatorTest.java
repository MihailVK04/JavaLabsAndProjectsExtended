package bg.sofia.uni.fmi.mjt.fittrack.workout.comparators;

import bg.sofia.uni.fmi.mjt.fittrack.workout.CardioWorkout;
import bg.sofia.uni.fmi.mjt.fittrack.workout.StrengthWorkout;
import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;
import bg.sofia.uni.fmi.mjt.fittrack.workout.YogaSession;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkoutOptimalComparatorTest {

    WorkoutOptimalComparator comparator = new WorkoutOptimalComparator();


    @Test
    void testCompareCorrect() {
        Workout workout1 = new StrengthWorkout("ABC", 90, 250, 5);
        Workout workout2 = new CardioWorkout("ABD", 80, 200, 4);
        assertTrue(comparator.compare(workout1,workout2) < 0, "Compare should be negative.");
        Workout workout3 = new YogaSession("ABD", 80, 250, 4);
        assertTrue(comparator.compare(workout1,workout3) < 0, "Compare should be negative.");
        Workout workout4 = new YogaSession("ABD", 80, 250, 5);
        assertTrue(comparator.compare(workout1,workout4) < 0, "Compare should be negative.");
        Workout workout5 = new YogaSession("ABD", 90, 250, 5);
        assertTrue(comparator.compare(workout1,workout5) < 0, "Compare should be negative.");
        Workout workout6 = new YogaSession("ABC", 90, 250, 5);
        assertTrue(comparator.compare(workout1,workout6) < 0, "Compare should be negative.");
        Workout workout7 = new StrengthWorkout("ABC", 90, 250, 5);
        assertEquals(0, comparator.compare(workout1, workout7), "Compare should be 0.");
    }
}