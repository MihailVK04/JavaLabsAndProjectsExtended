package bg.sofia.uni.fmi.mjt.fittrack;

import bg.sofia.uni.fmi.mjt.fittrack.exception.OptimalPlanImpossibleException;
import bg.sofia.uni.fmi.mjt.fittrack.workout.CardioWorkout;
import bg.sofia.uni.fmi.mjt.fittrack.workout.StrengthWorkout;
import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;
import bg.sofia.uni.fmi.mjt.fittrack.workout.WorkoutType;
import bg.sofia.uni.fmi.mjt.fittrack.workout.filter.NameWorkoutFilter;
import bg.sofia.uni.fmi.mjt.fittrack.workout.filter.TypeWorkoutFilter;
import bg.sofia.uni.fmi.mjt.fittrack.workout.filter.WorkoutFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


import static org.junit.jupiter.api.Assertions.*;


public class FitPlannerTest {

    static List<Workout> list;
    static FitPlanner planner;
    static FitPlanner empty;

    @BeforeAll
    static void setList() {
        list = new ArrayList<>();
        list.add(new StrengthWorkout("UpperBody", 100,200, 4));
        list.add(new StrengthWorkout("LowerBody", 90,200, 5));
        list.add(new CardioWorkout("Swimming", 100,220, 4));
        list.add(new CardioWorkout("Running", 70,180, 5));
        list.add(new CardioWorkout("Climbing", 60,150, 3));
        planner = new FitPlanner(list);
        empty = new FitPlanner( new ArrayList<>());
    }

    @Test
    void testValidationInFitPLanner() {
        assertThrows(IllegalArgumentException.class, () -> new FitPlanner(null),
            "Should throw exception, because of parameter being null.");
    }

    @Test
    void testFindWorkoutsByFilterCorrect() {
        List<WorkoutFilter> filters = new ArrayList<>();
        filters.add(new TypeWorkoutFilter(WorkoutType.STRENGTH));
        List<Workout> workouts = new ArrayList<>();
        workouts.add(list.get(0));
        workouts.add(list.get(1));
        assertIterableEquals(workouts, planner.findWorkoutsByFilters(filters), "The results should equal." );
        filters.add(new NameWorkoutFilter("Upper", false));
        workouts.clear();
        workouts.add(list.getFirst());
        assertIterableEquals(workouts, planner.findWorkoutsByFilters(filters), "The results should equal." );
        assertTrue(empty.findWorkoutsByFilters(filters).isEmpty(), "There should be no workouts");
    }

    @Test
    void testFindWorkoutsByFilterThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> planner.findWorkoutsByFilters(null),
            "Should throw an exception because filters is null");
    }

    @Test
    void testGenerateOptimalWeeklyPlanCorrectResult() {
        List<Workout> workouts = new ArrayList<>();
        workouts.add(list.get(1));
        workouts.add(list.get(4));
        assertIterableEquals(workouts, planner.generateOptimalWeeklyPlan(155),
            "The results should equal." );
        assertTrue(empty.generateOptimalWeeklyPlan(155).isEmpty(), "There should be no workouts");
        assertTrue(planner.generateOptimalWeeklyPlan(0).isEmpty(), "There should be no workouts");
    }

    @Test
    void testGenerateOptimalWeeklyPlanThrowsExceptions() {
        assertThrows(OptimalPlanImpossibleException.class, () -> planner.generateOptimalWeeklyPlan(50),
            "Optimal weekly plan should be impossible.");
        assertThrows(IllegalArgumentException.class, () -> planner.generateOptimalWeeklyPlan(-5),
            "Should throw because minutes is negative.");
    }

    @Test
    void testGetWorkoutsGroupedByTypeCorrectResult() {
        Map<WorkoutType, List<Workout>> result = list
            .stream()
            .collect(Collectors
                .collectingAndThen(Collectors
                    .groupingBy(Workout::getType, Collectors
                        .collectingAndThen(Collectors
                            .toList(), List::copyOf)), Map::copyOf));
        Map<WorkoutType, List<Workout>> actual = planner.getWorkoutsGroupedByType();
        for (WorkoutType type : result.keySet()) {
            assertIterableEquals(result.get(type), actual.get(type),
                "The results should equal." );
        }
        assertTrue(empty.getWorkoutsGroupedByType().isEmpty(), "There should be no workouts");
    }

    @Test
    void testGetWorkoutsSortedByCaloriesCorrectResult() {
        List<Workout> workouts = new ArrayList<>();
        workouts.add(list.get(2));
        workouts.add(list.get(1));
        workouts.add(list.get(0));
        workouts.add(list.get(3));
        workouts.add(list.get(4));
        assertIterableEquals(workouts, planner.getWorkoutsSortedByCalories(),
            "The results should equal." );
        assertTrue(empty.getWorkoutsSortedByCalories().isEmpty(), "There should be no workouts");
    }

    @Test
    void testGetWorkoutsSortedByDifficultyCorrectResult() {
        List<Workout> workouts = new ArrayList<>();
        workouts.add(list.get(4));
        workouts.add(list.get(2));
        workouts.add(list.get(0));
        workouts.add(list.get(1));
        workouts.add(list.get(3));
        assertIterableEquals(workouts, planner.getWorkoutsSortedByDifficulty(),
            "The results should equal." );
        assertTrue(empty.getWorkoutsSortedByDifficulty().isEmpty(), "There should be no workouts");
    }

    @Test
    void testGetUnmodifiedWorkoutSetCorrectSet() {
        Set<Workout> workouts = list.stream().collect(Collectors.toSet());
        for (Workout w : planner.getUnmodifiableWorkoutSet()) {
            assertTrue(workouts.contains(w), "The workout should be in the set");
        }
        assertTrue(empty.getUnmodifiableWorkoutSet().isEmpty(), "There should be no workouts");
    }
}
