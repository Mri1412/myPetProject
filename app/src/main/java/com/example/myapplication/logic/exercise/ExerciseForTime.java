package com.example.myapplication.logic.exercise;

import android.os.Parcel;
import android.os.Parcelable;

public class ExerciseForTime extends ExerciseImpl {

    private int timeInSeconds;

    /* Constructors */
    public ExerciseForTime(String name, int time) {
        super(name);
        this.timeInSeconds = time;

    }

    /* Override methods */
    @Override
    public String getExerciseDescription() {
        StringBuilder exerciseDescription = new StringBuilder();
        int minutes = timeInSeconds/60;
        int seconds = timeInSeconds - minutes*60;
        String timeString = String.format("%d:%02d", minutes, seconds);

    exerciseDescription.append(timeString + "   " + exerciseName);
        return exerciseDescription.toString();
    }

    @Override
    public int getTimeRemainingInSeconds(){
        return timeInSeconds;
    }

    @Override
    public boolean hasTimer(){return true;}

    // -----------------------------------------------
    // For parcelling this class
    //------------------------------------------------

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(timeInSeconds);
    }

    public ExerciseForTime(Parcel src){
        super(src);
        timeInSeconds = src.readInt();
    }

    public static final Parcelable.Creator<ExerciseForTime> CREATOR
            = new Parcelable.Creator<ExerciseForTime>() {
        public ExerciseForTime createFromParcel(Parcel in) {
            return new ExerciseForTime(in);
        }

        public ExerciseForTime[] newArray(int size) {
            return new ExerciseForTime[size];
        }
    };
}
