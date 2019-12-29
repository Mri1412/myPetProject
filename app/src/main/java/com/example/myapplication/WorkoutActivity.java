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
import com.example.myapplication.logic.workout.WorkoutImpl;

public class WorkoutActivity extends AppCompatActivity {

    private Workout workout;

/*    public void setWorkout(Workout workout) {
        this.workout = workout;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        Toolbar toolbar = findViewById(R.id.toolbarWorkout);
        setSupportActionBar(toolbar);



        //Intent intent = getIntent();
        //Workout newWorkout = intent.getParcelableExtra("workout");
        //this.setWorkout(newWorkout);

        workout = new WorkoutImpl();
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
