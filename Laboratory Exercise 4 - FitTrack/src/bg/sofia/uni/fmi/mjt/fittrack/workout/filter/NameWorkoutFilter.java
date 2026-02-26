package bg.sofia.uni.fmi.mjt.fittrack.workout.filter;

import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;

/**
 * Workout filter for the names. If caseSensitive is true the filter will try to perfectly match the name.
 */
public class NameWorkoutFilter implements WorkoutFilter{

    private final String keyword;
    private final boolean caseSensitive;

    public NameWorkoutFilter(String keyword, boolean caseSensitive) {
        if (keyword == null) {
            throw new IllegalArgumentException("Keyword for filter is null.");
        } else if (keyword.isBlank()) {
            throw new IllegalArgumentException("Keyword for filter is blank.");
        }
        this.keyword = keyword;
        this.caseSensitive = caseSensitive;
    }

    @Override
    public boolean matches(Workout workout) {
        if (caseSensitive) {
            return workout.getName().equals(keyword);
        } else {
            return workout.getName().contains(keyword);
        }
    }
}
