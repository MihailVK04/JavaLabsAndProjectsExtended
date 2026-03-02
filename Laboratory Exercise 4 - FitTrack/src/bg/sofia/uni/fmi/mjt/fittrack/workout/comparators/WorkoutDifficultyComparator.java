package bg.sofia.uni.fmi.mjt.fittrack.workout.comparators;

import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;

import java.util.Comparator;

public class WorkoutDifficultyComparator implements Comparator<Workout> {

    @Override
    public int compare(Workout o1, Workout o2) {
        if (o1.getDifficulty() != o2.getDifficulty()) {
            return Integer.compare(o1.getDifficulty(), o2.getDifficulty());
        } else if (o1.getCaloriesBurned() != o2.getCaloriesBurned()) {
            return Integer.compare(o2.getCaloriesBurned(), o1.getCaloriesBurned());
        } else if (o1.getDuration() != o2.getDuration()) {
            return Integer.compare(o2.getDuration(), o1.getDuration());
        } else if (!o1.getName().equals(o2.getName())) {
            return o1.getName().compareTo(o2.getName());
        } else {
            return o1.getType().compareTo(o2.getType());
        }
    }
}
