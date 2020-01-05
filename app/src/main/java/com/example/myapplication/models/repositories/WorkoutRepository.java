package com.example.myapplication.models.repositories;

import com.example.myapplication.models.workout.Workout;

import java.util.ArrayList;
import java.util.List;

//Threadsafe singleton repository
public class WorkoutRepository {
    private static final WorkoutRepository repository = new WorkoutRepository();
    private List<Workout> workouts = new ArrayList<>();

    public static WorkoutRepository getInstance() {return repository;}

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
