package com.example.myapplication.controllers;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.R;

public class WorkoutHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_history);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarWorkoutHistory);
        setSupportActionBar(toolbar);

        addWorkouts();
        addWorkouts();
        addWorkouts();

    }


    private void addWorkouts() {
        LinearLayout layout = findViewById(R.id.workout_history_layout);
        TextView textView = new TextView(this);
        textView.setText("Test");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(24);



        layout.addView(textView);
    }
}


