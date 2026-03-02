package bg.sofia.uni.fmi.mjt.fittrack.workout.comparators;

import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;

import java.util.Comparator;

public class WorkoutDifficultyComparator implements Comparator<Workout> {

    @Override
    public int compare(Workout o1, Workout o2) {
        if (o1.getDifficulty() != o2.getDifficulty()) {
            return Integer.compare(o2.getDifficulty(), o1.getDifficulty());
        } if (o1.getCaloriesBurned() != o2.getCaloriesBurned()) {
            return Integer.compare(o2.getCaloriesBurned(), o1.getCaloriesBurned());
        } else if (o1.getDuration() != o2.getDuration()) {
            return Integer.compare(o2.getDuration(), o1.getDuration());
        } else {
            return o1.getName().compareTo(o2.getName());
        }
    }
}
