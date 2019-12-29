package com.example.myapplication.logic.exercise;

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

}
