package bg.sofia.uni.fmi.mjt.fittrack;

import bg.sofia.uni.fmi.mjt.fittrack.exception.OptimalPlanImpossibleException;
import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;
import bg.sofia.uni.fmi.mjt.fittrack.workout.comparators.WorkoutDifficultyComparator;
import bg.sofia.uni.fmi.mjt.fittrack.workout.comparators.WorkoutOptimalComparator;
import bg.sofia.uni.fmi.mjt.fittrack.workout.WorkoutType;
import bg.sofia.uni.fmi.mjt.fittrack.workout.filter.WorkoutFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class FitPlanner implements FitPlannerAPI {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private final Collection<Workout> availableWorkouts;

    private List<Workout> getOptimalListBy(int totalMinutes, int[] lastIndex, int[] prevTime) {
        Set<Workout> optimalWeeklyPlan = new TreeSet<>(new WorkoutOptimalComparator());
        List<Workout> workouts = new ArrayList<>(availableWorkouts);
        int t = totalMinutes;
        while (t > 0 && lastIndex[t] != -1) {
            Workout w = workouts.get(lastIndex[t]);
            optimalWeeklyPlan.add(w);
            t = prevTime[t];
        }
        return new ArrayList<>(optimalWeeklyPlan);
    }

    public FitPlanner(Collection<Workout> availableWorkouts) {
        if (availableWorkouts == null) {
            throw new IllegalArgumentException("Available workouts is null.");
        }

        this.availableWorkouts = availableWorkouts;
    }

    @Override
    public List<Workout> findWorkoutsByFilters(List<WorkoutFilter> filters) {
        if (filters == null) {
            throw new IllegalArgumentException("Filters is empty");
        }

        List<Workout> result = new ArrayList<>(availableWorkouts);
        for (WorkoutFilter filter : filters) {
            Iterator<Workout> it = result.iterator();
            while (it.hasNext()) {
                Workout w = it.next();
                if (!filter.matches(w)) {
                    it.remove();
                }
            }
        }
        return new ArrayList<>(result);
    }

    @Override
    public List<Workout> generateOptimalWeeklyPlan(int totalMinutes) throws OptimalPlanImpossibleException {
        if (totalMinutes < ZERO) {
            throw new IllegalArgumentException("Total minutes is negative!");
        } else if (totalMinutes == ZERO || availableWorkouts.isEmpty()) {
            return List.of();
        }
        int[] dp = new int[totalMinutes + ONE];
        int[] prevTime = new int[totalMinutes + ONE];
        int[] lastIndex = new int[totalMinutes + ONE];
        Arrays.fill(prevTime, -ONE);
        Arrays.fill(lastIndex, -ONE);
        int i = ZERO;
        for (Workout w : availableWorkouts) {
            int duration = w.getDuration();
            for (int t = totalMinutes; t >= duration; t--) {
                int candidate = dp[t - duration] + w.getCaloriesBurned();
                if (candidate > dp[t]) {
                    dp[t] = candidate;
                    prevTime[t] = t - duration;
                    lastIndex[t] = i;
                }
            }
            i++;
        }
        if (dp[totalMinutes] == ZERO) {
            throw new OptimalPlanImpossibleException("No valid plan within time limit");
        }
        return getOptimalListBy(totalMinutes, lastIndex, prevTime);
    }

    @Override
    public Map<WorkoutType, List<Workout>> getWorkoutsGroupedByType() {
        Map<WorkoutType, List<Workout>> map = new HashMap<>();
        for (Workout w : availableWorkouts) {
            if (!map.containsKey(w.getType())) {
                List<Workout> list = new ArrayList<>();
                list.add(w);
                map.put(w.getType(), list);
            } else {
                List<Workout> oldList = map.get(w.getType());
                oldList.add(w);
                map.put(w.getType(), oldList);
            }
        }
        return Map.copyOf(map);
    }

    @Override
    public List<Workout> getWorkoutsSortedByCalories() {
        Set<Workout> set = new TreeSet<>(new WorkoutOptimalComparator());
        set.addAll(availableWorkouts);
        List<Workout> result = new ArrayList<>(set);
        return List.copyOf(result);
    }

    @Override
    public List<Workout> getWorkoutsSortedByDifficulty() {
        Set<Workout> set = new TreeSet<>(new WorkoutDifficultyComparator());
        set.addAll(availableWorkouts);
        List<Workout> result = new ArrayList<>(set);
        return List.copyOf(result);
    }

    @Override
    public Set<Workout> getUnmodifiableWorkoutSet() {
        Set<Workout> set = new HashSet<>(availableWorkouts);
        return Set.copyOf(set);
    }
}
