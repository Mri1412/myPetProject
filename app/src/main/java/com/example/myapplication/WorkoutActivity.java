package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.logic.exercise.Exercise;
import com.example.myapplication.logic.workout.Workout;

import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class WorkoutActivity extends AppCompatActivity {

    private Workout workout;
    long time = 0, startTime = 0;

    ExecutorService timerService = Executors.newFixedThreadPool(1);

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
        startTime = SystemClock.uptimeMillis();
        timerService.execute(timerLoop);

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
        timerService.shutdownNow(); // This stops the timer from running, by cancelling any running tasks

        TextView textName = findViewById(R.id.workoutContentText);
        textName.setText("VICTORY!");

        while(!timerService.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                //The point is to wait until the timerService is shut down, just swallow the exception
            }
        }
    }



    private Runnable timerLoop = new Runnable() {

        public void run() {

            int seconds = 0, minutes = 0;

            time = SystemClock.uptimeMillis() - startTime;

            seconds = (int) (time / 1000);

            minutes = seconds / 60;

            seconds = seconds % 60;

            TextView timerText = findViewById(R.id.timerText);
            timerText.setText(getString(R.string.workout_timer, minutes, seconds));

            if(!timerService.isTerminated() && !timerService.isShutdown()){
                timerService.execute(timerLoop);
            }
        }

    };
}
