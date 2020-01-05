package com.example.myapplication.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;
import com.example.myapplication.models.viewmodels.WorkoutViewModel;
import com.example.myapplication.models.workout.Workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutSelectorActivity extends AppCompatActivity {

    List<Workout> workouts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_selector);
        Toolbar toolbar = findViewById(R.id.toolbarWorkoutSelector);
        setSupportActionBar(toolbar);

        WorkoutViewModel model = ViewModelProviders.of(this).get(WorkoutViewModel.class);
        workouts = model.getWorkouts().getValue();

        for(Workout workout : workouts){
            addWorkoutButton(workout);
        }
    }

    private void addWorkoutButton(final Workout workout) {
        LinearLayout layout = findViewById(R.id.workout_selector_layout);
        Button button = new Button(this);
        button.setText(workout.getWorkoutName());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkoutSelectorActivity.this, WorkoutOverviewActivity.class);
                intent.putExtra("Workout", workout);
                startActivity(intent);
            }
        });
        layout.addView(button);
    }

}
