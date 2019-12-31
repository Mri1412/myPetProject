package com.example.myapplication.controllers;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.R;
import com.example.myapplication.logic.exercise.Exercise;
import com.example.myapplication.logic.workout.Workout;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkoutActivity extends AppCompatActivity {

    private Workout workout;
    long timerTime = 0, timerStartTime = 0;
    long exerciseTime = 0, exerciseStartTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        Toolbar toolbar = findViewById(R.id.toolbarWorkout);
        setSupportActionBar(toolbar);

        //get workout from arguments
        workout = getIntent().getParcelableExtra("Workout");

        workout.startWorkout();

        showNewExercise();
        timerStartTime = SystemClock.uptimeMillis();
        runOnUiThread(timerLoop);

        ConstraintLayout screen = findViewById(R.id.workout_screen);
        screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exerciseFinished();
            }
        });
    }

    private void showNewExercise() {
        Exercise exercise = workout.getCurrentExercise();
        TextView exerciseName = findViewById(R.id.workoutContentText);
        exerciseName.setText(exercise.getExerciseDescription());

        TextView exerciseTimeView = findViewById(R.id.workoutTimeRemainingText);
        ConstraintLayout workoutScreen = findViewById(R.id.workout_screen);
        if(exercise.hasTimer()){
            int seconds = 0, minutes = 0;
            seconds = exercise.getTimeRemainingInSeconds();
            minutes = seconds / 60;
            seconds = seconds % 60;

            exerciseTimeView.setText(getString(R.string.exercise_timer, minutes, seconds));
            exerciseTimeView.setVisibility(View.VISIBLE);

            workoutScreen.setClickable(false);

            exerciseStartTime = SystemClock.uptimeMillis();
            exerciseTimeView.post(exerciseCountdownLoop);
        }else{
            exerciseTimeView.setVisibility(View.INVISIBLE);
            workoutScreen.setClickable(true);
        }
    }

    private void showFinishScreen() {
        TextView textName = findViewById(R.id.workoutContentText);
        textName.setText("VICTORY!");

        TextView exerciseTimeView = findViewById(R.id.workoutTimeRemainingText);
        exerciseTimeView.setVisibility(View.INVISIBLE);
    }

    private Runnable exerciseCountdownLoop = new Runnable() {

        public void run() {
            int seconds = 0, minutes = 0;
            exerciseTime = exerciseStartTime + 1000*workout.getCurrentExercise().getTimeRemainingInSeconds() - SystemClock.uptimeMillis();
            seconds = (int) ((exerciseTime + 999) / 1000);
            minutes = seconds / 60;
            seconds = seconds % 60;

            TextView timerText = findViewById(R.id.workoutTimeRemainingText);

            if(exerciseTime > 0){
                timerText.setText(getString(R.string.exercise_timer, minutes, seconds));

                if(!workout.isFinished()){
                    timerText.post(exerciseCountdownLoop);
                }
            } else{
                timerText.setText(getString(R.string.exercise_timer, 0, 0));

                exerciseFinished();
            }
        }
    };

    private Runnable timerLoop = new Runnable() {

        public void run() {
            int seconds = 0, minutes = 0;
            timerTime = SystemClock.uptimeMillis() - timerStartTime;
            seconds = (int) (timerTime / 1000);
            minutes = seconds / 60;
            seconds = seconds % 60;

            TextView timerText = findViewById(R.id.timerText);
            timerText.setText(getString(R.string.workout_timer, minutes, seconds));

            if(!workout.isFinished()){
                timerText.post(timerLoop);
            }
        }
    };

    private void exerciseFinished(){
        if(workout.hasNextExercise()){
            workout.goToNextExercise();
            showNewExercise();
        } else {
            workout.finishWorkout();
            showFinishScreen();
        }
    }
}
