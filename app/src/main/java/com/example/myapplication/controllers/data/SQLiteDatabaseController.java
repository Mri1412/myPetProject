package com.example.myapplication.controllers.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

public class SQLiteDatabaseController extends SQLiteOpenHelper {

    private static String DB_NAME = "LocalData";
    private static int DB_VERSION = 1;
    private final File DB_FILE;

    public SQLiteDatabaseController(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        DB_FILE = context.getDatabasePath(DB_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createWorkouts = "CREATE TABLE Workouts ( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name TEXT, " + "user TEXT )";

        db.execSQL(createWorkouts);

        String createExercises = "CREATE TABLE Exercises ( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "+ "workout INTEGER, "  + "size INTEGER, " + "name TEXT )";

        db.execSQL(createExercises);

        String createResults = "CREATE TABLE WorkoutResults ( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "workout INTEGER, " + "time INTEGER )";

        db.execSQL(createResults);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Do nothing
    }
}
