package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.logic.exercise.Exercise;
import com.example.myapplication.logic.exercise.ExerciseImpl;
import com.example.myapplication.logic.workout.Workout;
import com.example.myapplication.logic.workout.WorkoutImpl;

public class WorkoutActivity extends AppCompatActivity {

    private Workout workout = new WorkoutImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        Toolbar toolbar = findViewById(R.id.toolbarWorkout);
        setSupportActionBar(toolbar);

        // Set workout from workout selector
        Intent intent = getIntent();
        int workoutId = intent.getIntExtra("workoutId", 0);
        setWorkoutFromSelector(workoutId);

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

    private void setWorkoutFromSelector(int workoutId) {
        System.out.println("workoud id:");
        System.out.println(workoutId);
        switch (workoutId) {
            case 0:
                this.workout.addExercise(new ExerciseImpl("Push Up")).addExercise(new ExerciseImpl("Burpee"));
                break;
            case 1:
                this.workout.addExercise(new ExerciseImpl("Sit up")).addExercise(new ExerciseImpl("Jumping Jack")).addExercise(new ExerciseImpl("Squat"));
                break;
            default:
                throw new RuntimeException("Unknown workout selected");
        }

    }
}
