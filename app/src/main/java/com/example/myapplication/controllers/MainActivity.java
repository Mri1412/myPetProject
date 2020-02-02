package com.example.myapplication.controllers;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.models.viewmodels.WorkoutViewModel;
import com.example.myapplication.models.exercise.ExerciseForReps;
import com.example.myapplication.models.exercise.ExerciseForTime;
import com.example.myapplication.models.workout.Workout;
import com.example.myapplication.models.workout.WorkoutImpl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    /* Class methods */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);

        Button buttonWorkout = findViewById(R.id.buttonWorkout);
        buttonWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WorkoutSelectorActivity.class);
                startActivity(intent);
            }
        });

        Button buttonWorkoutHistory = findViewById(R.id.workoutHistoryButton);
        buttonWorkoutHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WorkoutHistoryActivity.class);
                startActivity(intent);
            }
        });


        loadWorkouts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadWorkouts(){
        WorkoutViewModel model = ViewModelProviders.of(this).get(WorkoutViewModel.class);

        //Temporary hardcoding of workouts list, this will be filled from user created workouts later.
        Workout tmpWorkout = new WorkoutImpl("Anton");
        tmpWorkout.addExercise(new ExerciseForReps("Push Up", 5)).addExercise(new ExerciseForReps("Burpee", 15))
                .addExercise(new ExerciseForTime("Plank1", 10)).addExercise(new ExerciseForTime("Plank2",5))
                .addExercise(new ExerciseForTime("Plank3", 21)).addExercise(new ExerciseForTime("Plank4", 15));
        model.addWorkout(tmpWorkout);

        tmpWorkout = new WorkoutImpl("Marianne");
        tmpWorkout.addExercise(new ExerciseForReps("Sit up", 10)).addExercise(new ExerciseForReps("Jumping Jack", 40))
                .addExercise(new ExerciseForReps("Squat", 20));
        model.addWorkout(tmpWorkout);
    }
}
