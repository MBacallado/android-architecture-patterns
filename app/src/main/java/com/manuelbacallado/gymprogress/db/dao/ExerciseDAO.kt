package com.manuelbacallado.gymprogress.db.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.manuelbacallado.gymprogress.db.GymProgressDBOpenHelper
import com.manuelbacallado.gymprogress.listener.IDatabaseFunctions
import com.manuelbacallado.gymprogress.models.Exercise
import com.manuelbacallado.gymprogress.utils.Constants
import java.sql.SQLException

class ExerciseDAO : IDatabaseFunctions  {

    constructor(context: Context) {
        dbHelper = GymProgressDBOpenHelper(context)
        open()
    }

    lateinit var dbHelper: GymProgressDBOpenHelper;
    lateinit var database: SQLiteDatabase;

    fun open(){
        try {
            database = dbHelper.writableDatabase;
        }catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun close(){
        database.close()
        dbHelper.close()
    }

    override fun addElement(obj: Any) {
        val exercise = obj as Exercise
        val values = ContentValues()
        values.put(Constants.COLUMN_EXERCISE_NAME, exercise.exerciseName)
        values.put(Constants.COLUMN_EXERCISE_INITIALWEIGHT, exercise.initialWeight)
        values.put(Constants.COLUMN_EXERCISE_FINALWEIGHT, exercise.finalWeight)
        values.put(Constants.COLUMN_FOREIGN_KEY_EXERCISE_TRAINING_ID, exercise.trainingDayId)
        database.insert(Constants.TABLE_EXERCISE_NAME, null, values)
    }

    override fun editElement(obj: Any) {
        val exercise = obj as Exercise
        val values = ContentValues()
        values.put(Constants.COLUMN_EXERCISE_NAME, exercise.exerciseName)
        values.put(Constants.COLUMN_EXERCISE_INITIALWEIGHT, exercise.initialWeight)
        values.put(Constants.COLUMN_EXERCISE_FINALWEIGHT, exercise.finalWeight)
        database.update(
            Constants.TABLE_EXERCISE_NAME, values,
            Constants.COLUMN_EXERCISE_ID + " = ?", arrayOf(exercise.exerciseId.toString()));
    }

    override fun deleteElement(obj: Any) {
        val exercise = obj as Exercise
        database.delete(Constants.TABLE_EXERCISE_NAME, Constants.COLUMN_EXERCISE_ID + " = ?", arrayOf(exercise.exerciseId.toString()));
    }

    override fun getAllElements(id: Int): List<Any>? {
        val cursor = database.rawQuery("SELECT * FROM ${Constants.TABLE_EXERCISE_NAME} WHERE ${Constants.COLUMN_FOREIGN_KEY_EXERCISE_TRAINING_ID} = ${id}", null)
        val list = ArrayList<Any>()
        if (cursor.moveToFirst()) {
            do {
                val exercise = Exercise(cursor.getInt(
                    cursor.getColumnIndex(Constants.COLUMN_EXERCISE_ID)),
                    cursor.getString(cursor.getColumnIndex(Constants.COLUMN_EXERCISE_NAME)),
                    cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_EXERCISE_INITIALWEIGHT)),
                    cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_EXERCISE_FINALWEIGHT)),
                    cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_FOREIGN_KEY_EXERCISE_TRAINING_ID)))
                list.add(exercise)
            }while (cursor.moveToNext())
        }
        return list
    }
}