package com.example.myapplication.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;
import com.example.myapplication.models.workout.Workout;

public class WorkoutOverviewActivity extends AppCompatActivity {

    Workout workout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_overview);

        // get workout from arguments
        workout = getIntent().getParcelableExtra("Workout");
        String workoutName = workout.getWorkoutName();

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarWorkoutOverview);
        toolbar.setTitle(workoutName);
        setSupportActionBar(toolbar);

        //Set the exercise string
        StringBuilder exerciseContent = new StringBuilder();
        for (String exerciseDescription: workout.getExerciseDescriptions()) {
            exerciseContent.append(exerciseDescription + "\n" + "\n");
        }
        TextView exerciseString = findViewById(R.id.exerciseString);
        exerciseString.setText(exerciseContent);
        exerciseString.setMovementMethod(new ScrollingMovementMethod());

        //Start button
        Button button = findViewById(R.id.startWorkoutButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkoutOverviewActivity.this, WorkoutActivity.class);
                intent.putExtra("Workout", workout);
                startActivity(intent);
            }
        });


    }
}
