package bg.sofia.uni.fmi.mjt.fittrack.workout.filter;

import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;

public class CaloriesWorkoutFilter implements WorkoutFilter {

    private static final int ZERO = 0;
    private final int min;
    private final int max;

    public CaloriesWorkoutFilter(int min, int max) {
        if (min < ZERO || max < ZERO) {
            throw new IllegalArgumentException("Max or min is/are negative.");
        } else if (max < min) {
            throw new IllegalArgumentException("Max is lower than min.");
        }

        this.min = min;
        this.max = max;
    }

    @Override
    public boolean matches(Workout workout) {
        return min <= workout.getCaloriesBurned() && workout.getCaloriesBurned() <= max;
    }
}
