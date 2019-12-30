package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.logic.exercise.Exercise;
import com.example.myapplication.logic.workout.Workout;

public class WorkoutActivity extends AppCompatActivity {

    private Workout workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        Toolbar toolbar = findViewById(R.id.toolbarWorkout);
        setSupportActionBar(toolbar);

        // Set workout from workout selector
        Intent intent = getIntent();
        int workoutId = intent.getIntExtra("workoutId", 0);

        // TEMP get workout from workout selector activity
        WorkoutSelectorActivity workoutList = new WorkoutSelectorActivity();
        workout = workoutList.getWorkoutFromSelector(workoutId);

        workout.startWorkout();
        showNewExercise();

        ConstraintLayout screen = findViewById(R.id.workout_screen);
        screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(workout.hasNextExercise()){
                    workout.goToNextExercise();
                    showNewExercise();
                } else {
                    workout.finishWorkout();
                    showFinishScreen();
                }
            }
        });
    }

    private void showNewExercise() {
        Exercise exercise = workout.getCurrentExercise();
        TextView exerciseName = findViewById(R.id.workoutContentText);
        exerciseName.setText(exercise.getName());
    }

    private void showFinishScreen() {
        TextView textName = findViewById(R.id.workoutContentText);
        textName.setText("VICTORY!");
    }


}
