package com.example.myapplication.models.exercise;

import android.os.Parcel;

public abstract class ExerciseImpl implements Exercise {

    //Properties
    String exerciseName;

    //Constructor
    public ExerciseImpl(String name){
        exerciseName = name;
    }

    // Interface methods
    @Override
    public String getName() {
        return exerciseName;
    }

    // -----------------------------------------------
    // For parcelling this class
    //------------------------------------------------

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(exerciseName);
    }

    public ExerciseImpl(Parcel src){
        exerciseName = src.readString();
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

}
