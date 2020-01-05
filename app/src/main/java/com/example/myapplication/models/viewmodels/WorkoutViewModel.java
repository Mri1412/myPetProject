package com.example.myapplication.models.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.models.repositories.WorkoutRepository;
import com.example.myapplication.models.workout.Workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutViewModel extends ViewModel {

    MutableLiveData<List<Workout>> workouts = new MutableLiveData<>();

    public WorkoutViewModel(){
        super();
        workouts.setValue(WorkoutRepository.getInstance().getWorkouts());
    }

    public LiveData<List<Workout>> getWorkouts(){
        return workouts;
    }

    public void addWorkout(Workout workout){
        WorkoutRepository repository = WorkoutRepository.getInstance();
        repository.getWorkouts().add(workout);
        workouts.setValue(repository.getWorkouts());
    }
}
