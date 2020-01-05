package com.example.myapplication.models.workout;
import android.os.Parcelable;

import com.example.myapplication.models.exercise.Exercise;

import java.util.List;

public interface Workout extends Parcelable {
    Exercise goToNextExercise();
    Exercise getCurrentExercise();
    void startWorkout();
    void finishWorkout();
    boolean isFinished();
    boolean hasNextExercise();
    Workout addExercise(Exercise exercise);
    String getWorkoutName();
    List<String> getExerciseDescriptions();
}
