package com.example.myapplication.models.exercise;

import android.os.Parcelable;

public interface Exercise extends Parcelable {

    String getName();
    String getExerciseDescription();
    boolean hasTimer();
    int getTimeRemainingInSeconds();
}

