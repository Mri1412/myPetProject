package com.example.myapplication.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class WorkoutFinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_finish);

        show_time();

        Button buttonWorkout = findViewById(R.id.saveWorkoutButton);
        buttonWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkoutFinishActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void show_time() {
        long timerTime = getIntent().getLongExtra("timerTime", 0);

        int seconds = 0, minutes = 0;
        seconds = (int) (timerTime / 1000);
        minutes = seconds / 60;
        seconds = seconds % 60;

        TextView timerText = findViewById(R.id.timerText);
        timerText.setText(getString(R.string.workout_timer, minutes, seconds));
    }




}
