package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.logic.exercise.ExerciseImpl;
import com.example.myapplication.logic.workout.Workout;
import com.example.myapplication.logic.workout.WorkoutImpl;

import java.util.ArrayList;

public class WorkoutSelectorActivity extends AppCompatActivity {

    private ArrayList<Workout> workouts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_selector);
        Toolbar toolbar = findViewById(R.id.toolbarWorkoutSelector);
        setSupportActionBar(toolbar);

        // Workout definition is temporarily stored in WorkoutActivity

        //create buttons etc. from workouts
        onClickWorkoutButton(R.id.workoutButton1, 0);

        onClickWorkoutButton(R.id.workoutButton2, 1);


    }

    private void onClickWorkoutButton(int buttonId, final int workoutId) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkoutSelectorActivity.this, WorkoutActivity.class);
                intent.putExtra("workoutId", workoutId);
                startActivity(intent);
            }
        });

    }
}
