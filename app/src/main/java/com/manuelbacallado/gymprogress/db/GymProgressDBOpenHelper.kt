package com.manuelbacallado.gymprogress.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.manuelbacallado.gymprogress.utils.Constants

class GymProgressDBOpenHelper(context: Context) :
    SQLiteOpenHelper(context, Constants.DATABASE_NAME,
        null, Constants.DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_ROUTINE_TABLE = ("CREATE TABLE " +
                Constants.TABLE_ROUTINE_NAME + "(" + Constants.COLUMN_ROUTINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constants.COLUMN_ROUTINE_NAME + " TEXT NOT NULL, " + Constants.COLUMN_ROUTINE_START_DATE + " TEXT NOT NULL, " +
                Constants.COLUMN_ROUTINE_FINISH_DATE + " TEXT NOT NULL, " + Constants.COLUMN_ROUTINE_TRAINING_DAYS + " INTEGER NOT NULL, " +
                Constants.COLUMN_ROUTINE_TRAINING_TYPES + " TEXT NOT NULL " + ")");

        val CREATE_TRAINING_DAY_TABLE = ("CREATE TABLE " +
                Constants.TABLE_TRAINING_NAME + "(" + Constants.COLUMN_TRAINING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constants.COLUMN_TRAINING_DAY + " TEXT NOT NULL, " + Constants.COLUMN_TRAINING_TIME + " INTEGER NOT NULL, " + Constants.COLUMN_TRAINING_GROUP + " TEXT NOT NULL, " +
                Constants.COLUM_FOREIGN_KEY_TRAINING_ROUTINE_ID + " INTEGER NOT NULL )");

        val CREATE_EXERCISE_TABLE = ("CREATE TABLE " +
                Constants.TABLE_EXERCISE_NAME + "(" + Constants.COLUMN_EXERCISE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Constants.COLUMN_EXERCISE_NAME + " TEXT NOT NULL, " + Constants.COLUMN_EXERCISE_INITIALWEIGHT + " INTEGER NOT NULL, " + Constants.COLUMN_EXERCISE_FINALWEIGHT + " INTEGER NOT NULL, " +
                Constants.COLUMN_FOREIGN_KEY_EXERCISE_TRAINING_ID + " INTEGER NOT NULL )");
        //+
        //" FOREIGN KEY (" + Constants.COLUM_FOREIGN_KEY_TRAINING_ROUTINE_ID + ") REFERENCES routine(_id)"

        db.execSQL(CREATE_ROUTINE_TABLE)
        db.execSQL(CREATE_TRAINING_DAY_TABLE)
        db.execSQL(CREATE_EXERCISE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_ROUTINE_NAME)
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_TRAINING_NAME)
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_EXERCISE_NAME)
        onCreate(db)
    }
}