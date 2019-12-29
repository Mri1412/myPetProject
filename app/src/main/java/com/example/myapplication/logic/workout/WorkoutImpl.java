package com.example.myapplication.logic.workout;

import com.example.myapplication.logic.exercise.Exercise;
import com.example.myapplication.logic.exercise.ExerciseImpl;
import java.util.ArrayList; // import the ArrayList class

public class WorkoutImpl implements Workout {

    /* Properties */
    public ArrayList<Exercise> exercises = new ArrayList<>();
    private int currentExerciseIndex;


    /* Constructor */
    public WorkoutImpl() {
        currentExerciseIndex = 0;

        // TEMP hardcoded constructor
        this.addExercise(new ExerciseImpl("Push Up")).addExercise(new ExerciseImpl("Burpee"));
    }

    /* Interface methods */

    @Override
    public Exercise goToNextExercise() {
        if ( this.hasNextExercise() ) {
            currentExerciseIndex++;
            Exercise exercise = exercises.get(currentExerciseIndex);
            return exercise;
        } else {
            // This method should only be called if there is a next exercise
            throw new RuntimeException("'hasNextExercise' was called while this was the last exercise");
        }
    }

    @Override
    public Exercise getCurrentExercise() {
        return exercises.get(currentExerciseIndex);

    }

    @Override
    public void startWorkout() {
        currentExerciseIndex = 0;
    }

    @Override
    public void finishWorkout() {

    }

    @Override
    public boolean hasNextExercise() {
        int exLength = exercises.size();
        if ( currentExerciseIndex == exLength-1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Workout addExercise(Exercise exercise) {
        exercises.add(exercise);
        return this;
    }
}
