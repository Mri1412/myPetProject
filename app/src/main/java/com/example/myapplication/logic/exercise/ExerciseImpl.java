package com.example.myapplication.logic.exercise;

import android.os.Parcel;
import android.os.Parcelable;

public class ExerciseImpl implements Exercise {

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

    public static final Parcelable.Creator<ExerciseImpl> CREATOR
            = new Parcelable.Creator<ExerciseImpl>() {
        public ExerciseImpl createFromParcel(Parcel in) {
            return new ExerciseImpl(in);
        }

        public ExerciseImpl[] newArray(int size) {
            return new ExerciseImpl[size];
        }
    };

}
