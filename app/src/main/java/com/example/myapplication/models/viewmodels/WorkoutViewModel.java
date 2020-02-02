package com.example.myapplication.models.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.models.repositories.WorkoutRepository;
import com.example.myapplication.models.workout.Workout;

import java.util.List;

public class WorkoutViewModel extends AndroidViewModel {

    MutableLiveData<List<Workout>> workouts = new MutableLiveData<>();

    public WorkoutViewModel(Application application){
        super(application);
        workouts.setValue(WorkoutRepository.getInstance(getApplication().getApplicationContext()).getWorkouts());
    }

    public LiveData<List<Workout>> getWorkouts(){
        return workouts;
    }

    public void addWorkout(Workout workout){
        WorkoutRepository repository = WorkoutRepository.getInstance(getApplication().getApplicationContext());
        repository.getWorkouts().add(workout);
        workouts.setValue(repository.getWorkouts());
    }
}
