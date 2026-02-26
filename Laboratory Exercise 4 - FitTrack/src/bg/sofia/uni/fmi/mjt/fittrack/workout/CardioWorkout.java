package bg.sofia.uni.fmi.mjt.fittrack.workout;

import bg.sofia.uni.fmi.mjt.fittrack.exception.InvalidWorkoutException;

public final class CardioWorkout implements Workout {

    private static final int ZERO = 0;
    private static final int MINIMUM_DIFFICULTY = 1;
    private static final int MAXIMUM_DIFFICULTY = 5;
    private final String name;
    private final int duration;
    private final int caloriesBurned;
    private final int difficulty;
    private final WorkoutType type = WorkoutType.CARDIO;

    private void validateData(String name, int duration, int caloriesBurned, int difficulty) {
        if (name == null) {
            throw new InvalidWorkoutException("Name is null.");
        } else if (name.isBlank()) {
            throw new InvalidWorkoutException("Name is blank.");
        } else if (duration <= ZERO) {
            throw new InvalidWorkoutException("Duration is not positive.");
        } else if (caloriesBurned <= ZERO) {
            throw new InvalidWorkoutException("Burned calories is not positive.");
        } else if (difficulty < MINIMUM_DIFFICULTY || difficulty > MAXIMUM_DIFFICULTY) {
            throw new InvalidWorkoutException("Difficulty is not in bounds [1,5]");
        }
    }

    CardioWorkout(String name, int duration, int caloriesBurned, int difficulty) {
        validateData(name, duration, caloriesBurned, difficulty);
        this.name = name;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
        this.difficulty = difficulty;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    @Override
    public int getDifficulty() {
        return difficulty;
    }

    @Override
    public WorkoutType getType() {
        return type;
    }
}
