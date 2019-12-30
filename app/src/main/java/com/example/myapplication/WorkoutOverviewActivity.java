package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.logic.workout.Workout;

public class WorkoutOverviewActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_overview);

        // get workout id
        Intent intent = getIntent();
        final int workoutId = intent.getIntExtra("workoutId", 0);

        // TEMP get workout
        WorkoutSelectorActivity workoutList = new WorkoutSelectorActivity();
        Workout workout = workoutList.getWorkoutFromSelector(workoutId);
        String workoutName = workout.getWorkoutName();

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarWorkoutOverview);
        toolbar.setTitle(workoutName);
        setSupportActionBar(toolbar);

        //Set the exercise string
        StringBuilder exerciseContent = new StringBuilder();
        for (String exerciseName: workout.getExerciseNames()) {
            exerciseContent.append(exerciseName + "\n" + "\n");
        }
        TextView exerciseString = findViewById(R.id.exerciseString);
        exerciseString.setText(exerciseContent);

        //Start button
        Button button = findViewById(R.id.startWorkoutButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkoutOverviewActivity.this, WorkoutActivity.class);
                intent.putExtra("workoutId", workoutId);
                startActivity(intent);
            }
        });


    }
}
