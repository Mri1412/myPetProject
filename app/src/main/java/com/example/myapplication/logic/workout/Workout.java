package com.example.myapplication.logic.workout;
import com.example.myapplication.logic.exercise.Exercise;

public interface Workout {
    Exercise goToNextExercise();
    Exercise getCurrentExercise();
    void startWorkout();
    void finishWorkout();
    boolean hasNextExercise();
    Workout addExercise(Exercise exercise);
}
