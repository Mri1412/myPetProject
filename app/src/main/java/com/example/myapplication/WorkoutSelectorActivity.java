package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.logic.exercise.ExerciseForReps;
import com.example.myapplication.logic.exercise.ExerciseForTime;
import com.example.myapplication.logic.workout.Workout;
import com.example.myapplication.logic.workout.WorkoutImpl;

import java.util.ArrayList;
import java.util.List;

public class WorkoutSelectorActivity extends AppCompatActivity {

    List<Workout> workouts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_selector);
        Toolbar toolbar = findViewById(R.id.toolbarWorkoutSelector);
        setSupportActionBar(toolbar);

        //Temporary hardcoding of workouts list, this will be filled from user created workouts later.
        Workout tmpWorkout = new WorkoutImpl("Anton");
        tmpWorkout.addExercise(new ExerciseForReps("Push Up", 5)).addExercise(new ExerciseForReps("Burpee", 15))
                .addExercise(new ExerciseForTime("Plank1", 60)).addExercise(new ExerciseForTime("Plank2",40))
                .addExercise(new ExerciseForTime("Plank3", 21)).addExercise(new ExerciseForTime("Plank4", 120));
        workouts.add(tmpWorkout);

        tmpWorkout = new WorkoutImpl("Marianne");
        tmpWorkout.addExercise(new ExerciseForReps("Sit up", 10)).addExercise(new ExerciseForReps("Jumping Jack", 40))
                .addExercise(new ExerciseForReps("Squat", 20));
        workouts.add(tmpWorkout);


        for(Workout workout : workouts){
            addWorkoutButton(workout);
        }
    }

    private void addWorkoutButton(final Workout workout) {
        LinearLayout layout = findViewById(R.id.workout_selector_layout);
        Button button = new Button(this);
        button.setText(workout.getWorkoutName());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkoutSelectorActivity.this, WorkoutOverviewActivity.class);
                intent.putExtra("Workout", workout);
                startActivity(intent);
            }
        });
        layout.addView(button);
    }

}
