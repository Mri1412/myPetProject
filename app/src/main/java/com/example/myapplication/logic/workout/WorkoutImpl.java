package com.example.myapplication.logic.workout;

import com.example.myapplication.logic.exercise.Exercise;
import com.example.myapplication.logic.exercise.ExerciseImpl;
import java.util.ArrayList; // import the ArrayList class

public class WorkoutImpl implements Workout {

    /* Properties */
    public ArrayList<ExerciseImpl> exercises;
    private int currentExerciseIndex;


    /* Constructor */
    public WorkoutImpl() {
        ArrayList<ExerciseImpl> newList = new ArrayList<ExerciseImpl>();
        ExerciseImpl newEx = new ExerciseImpl("Push up");
        newList.add(newEx);
        newEx = new ExerciseImpl("Burpees");
        newList.add(newEx);
        exercises = newList;
        currentExerciseIndex = 0;
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
}
