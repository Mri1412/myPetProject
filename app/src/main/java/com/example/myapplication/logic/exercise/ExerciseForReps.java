package com.example.myapplication.logic.exercise;

import android.os.Parcel;
import android.os.Parcelable;

public class ExerciseForReps extends ExerciseImpl {

    private int reps;

    public ExerciseForReps(String name, int reps) {
        super(name);
        this.reps = reps;
    }


    @Override
    public String getExerciseDescription() {
        StringBuilder exerciseDescription = new StringBuilder();
        String repString = new Integer(reps).toString();
        exerciseDescription.append(repString + "x   " + exerciseName);
        return exerciseDescription.toString();
    }

    @Override
    public int getTimeRemainingInSeconds(){
        return 0;
    }

    @Override
    public boolean hasTimer(){return false;}

    // -----------------------------------------------
    // For parcelling this class
    //------------------------------------------------

    @Override
    public void writeToParcel(Parcel dest, int flags) {
       super.writeToParcel(dest, flags);
       dest.writeInt(reps);
    }

    public ExerciseForReps(Parcel src){
        super(src);
        reps = src.readInt();
    }

    public static final Parcelable.Creator<ExerciseForReps> CREATOR
            = new Parcelable.Creator<ExerciseForReps>() {
        public ExerciseForReps createFromParcel(Parcel in) {
            return new ExerciseForReps(in);
        }

        public ExerciseForReps[] newArray(int size) {
            return new ExerciseForReps[size];
        }
    };
}
