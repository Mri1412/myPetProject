package com.example.myapplication.logic.workout;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.myapplication.logic.exercise.Exercise;
import java.util.ArrayList; // import the ArrayList class
import java.util.List;

public class WorkoutImpl implements Workout {

    /* Properties */
    private ArrayList<Exercise> exercises = new ArrayList<>();
    private String workoutName;
    private int currentExerciseIndex;
    boolean isFinished = false;


    /* Constructor */
    public WorkoutImpl(String workoutName) {
        this.workoutName = workoutName;
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
    public boolean isFinished() {
        return isFinished;
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

    @Override
    public String getWorkoutName() {
        return this.workoutName;
    }

    @Override
    public List<String> getExerciseDescriptions() {
        List<String> exerciseDescriptions = new ArrayList<>();
        for(Exercise exercise: exercises) {
            exerciseDescriptions.add(exercise.getExerciseDescription());
        }
        return exerciseDescriptions;
    }

    // -----------------------------------------------
    // For parcelling this class
    //------------------------------------------------

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(workoutName);
        dest.writeInt(currentExerciseIndex);
        dest.writeList(exercises);
    }

    @SuppressWarnings("unchecked")
    public WorkoutImpl(Parcel src){
        workoutName = src.readString();
        currentExerciseIndex = src.readInt();
        exercises = src.readArrayList(Exercise.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    public static final Parcelable.Creator<WorkoutImpl> CREATOR
            = new Parcelable.Creator<WorkoutImpl>() {
        public WorkoutImpl createFromParcel(Parcel in) {
            return new WorkoutImpl(in);
        }

        public WorkoutImpl[] newArray(int size) {
            return new WorkoutImpl[size];
        }
    };
}
