package com.example.myapplication.logic.workout;
import com.example.myapplication.logic.exercise.Exercise;

import java.util.List;

public interface Workout {
    Exercise goToNextExercise();
    Exercise getCurrentExercise();
    void startWorkout();
    void finishWorkout();
    boolean hasNextExercise();
    Workout addExercise(Exercise exercise);
    String getWorkoutName();
    List<String> getExerciseNames();
}
