package com.example.myapplication.models.repositories;

import android.content.Context;

import com.example.myapplication.controllers.data.SQLiteDatabaseController;
import com.example.myapplication.models.workout.Workout;

import java.util.ArrayList;
import java.util.List;

//Threadsafe singleton repository
public class WorkoutRepository {
    private static final WorkoutRepository repository = new WorkoutRepository();
    private static SQLiteDatabaseController dbController;
    private List<Workout> workouts = new ArrayList<>();

    public static WorkoutRepository getInstance(Context context) {
        if(dbController == null){
            dbController = new SQLiteDatabaseController(context);
        }
        return repository;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void addWorkout(Workout workout){

        workouts.add(workout);
    }

    private void loadUsersFromDisk(){
        //Implement later, when we can save workouts to disk.
        return;
    }
}
