package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.logic.exercise.ExerciseImpl;
import com.example.myapplication.logic.workout.Workout;
import com.example.myapplication.logic.workout.WorkoutImpl;

import java.util.ArrayList;

public class WorkoutSelectorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_selector);
        Toolbar toolbar = findViewById(R.id.toolbarWorkoutSelector);
        setSupportActionBar(toolbar);

        //create buttons etc. from workouts
        onClickWorkoutButton(R.id.workoutButton1, 0);
        onClickWorkoutButton(R.id.workoutButton2, 1);
    }

    private void onClickWorkoutButton(int buttonId, final int workoutId) {
        Button button = findViewById(buttonId);
        String workoutName = this.getWorkoutFromSelector(workoutId).getWorkoutName();
        button.setText(workoutName);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WorkoutSelectorActivity.this, WorkoutOverviewActivity.class);
                intent.putExtra("workoutId", workoutId);
                startActivity(intent);
            }
        });

    }

    public Workout getWorkoutFromSelector(int workoutId) {
        System.out.println("workoud id:");
        System.out.println(workoutId);
        switch (workoutId) {
            case 0:
                Workout workout1 = new WorkoutImpl("Anton");
                workout1.addExercise(new ExerciseImpl("Push Up")).addExercise(new ExerciseImpl("Burpee")).addExercise(new ExerciseImpl("Plank1")).addExercise(new ExerciseImpl("Plank2")).addExercise(new ExerciseImpl("Plank3")).addExercise(new ExerciseImpl("Plank4")).addExercise(new ExerciseImpl("Plank5")).addExercise(new ExerciseImpl("Plank6")).addExercise(new ExerciseImpl("Plank7"));
                return workout1;
            case 1:
                Workout workout2 = new WorkoutImpl("Marianne");
                workout2.addExercise(new ExerciseImpl("Sit up")).addExercise(new ExerciseImpl("Jumping Jack")).addExercise(new ExerciseImpl("Squat"));
                return workout2;
            default:
                throw new RuntimeException("Unknown workout selected");
        }
    }



}
