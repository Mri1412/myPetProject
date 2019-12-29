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

        //TEMP hardcoding of list (does nothing yet)
        final Workout workout1 = new WorkoutImpl();
        workout1.addExercise(new ExerciseImpl("Push Up")).addExercise(new ExerciseImpl("Burpee"));
        workouts.add(workout1);

        final Workout workout2 = new WorkoutImpl();
        workout2.addExercise(new ExerciseImpl("Sit up")).addExercise(new ExerciseImpl("Jumping Jack")).addExercise(new ExerciseImpl("Squat"));
        workouts.add(workout2);

        //create buttons etc. from workouts
        onClickWorkoutButton(R.id.workoutButton1, workout1);

        onClickWorkoutButton(R.id.workoutButton2, workout2);


    }

    private void onClickWorkoutButton(int buttonId, final Workout workout) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkoutSelectorActivity.this, WorkoutActivity.class);
                startActivity(intent);
            }
        });

    }
}
