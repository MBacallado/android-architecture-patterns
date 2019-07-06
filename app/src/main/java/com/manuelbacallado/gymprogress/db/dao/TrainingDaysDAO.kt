package com.manuelbacallado.gymprogress.db.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.manuelbacallado.gymprogress.db.GymProgressDBOpenHelper
import com.manuelbacallado.gymprogress.interfaces.DatabaseFunctions
import com.manuelbacallado.gymprogress.models.TrainingDay
import com.manuelbacallado.gymprogress.utils.Constants
import java.sql.SQLException

class TrainingDaysDAO: DatabaseFunctions {
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
        val trainingDay = obj as TrainingDay
        Log.d("TRAINING DAY DATABASE", "DAY: ${trainingDay.day}")
        Log.d("TRAINING DAY DATABASE", "TIME AMOUNT: ${trainingDay.timeAmount}")
        Log.d("TRAINING DAY DATABASE", "GROUP: ${trainingDay.group}")
        Log.d("TRAINING DAY DATABASE", "ROUTINE ID: ${trainingDay.routineId}")
        val values = ContentValues()
        values.put(Constants.COLUMN_TRAINING_DAY, trainingDay.day)
        values.put(Constants.COLUMN_TRAINING_TIME, trainingDay.timeAmount)
        values.put(Constants.COLUMN_TRAINING_GROUP, trainingDay.group)
        values.put(Constants.COLUM_FOREIGN_KEY_TRAINING_ROUTINE_ID, trainingDay.routineId)
        database.insert(Constants.TABLE_TRAINING_NAME, null, values)
    }

    override fun editElement(obj: Any) {
        val trainingDay = obj as TrainingDay
        val values = ContentValues()
        values.put(Constants.COLUMN_TRAINING_DAY, trainingDay.day)
        values.put(Constants.COLUMN_TRAINING_TIME, trainingDay.timeAmount)
        values.put(Constants.COLUMN_TRAINING_GROUP, trainingDay.group)
        database.update(
            Constants.TABLE_TRAINING_NAME, values,
            Constants.COLUMN_TRAINING_ID + " = ?", arrayOf(trainingDay.trainingDayId.toString()));
    }

    override fun deleteElement(obj: Any) {
        val trainingDay = obj as TrainingDay
        database.delete(Constants.TABLE_TRAINING_NAME, Constants.COLUMN_TRAINING_ID + " = ?", arrayOf(trainingDay.trainingDayId.toString()));
    }

    override fun getAllElements(id: Int): List<Any>? {
        val cursor = database.rawQuery("SELECT * FROM ${Constants.TABLE_TRAINING_NAME} WHERE ${Constants.COLUM_FOREIGN_KEY_TRAINING_ROUTINE_ID} = ${id}", null)
        val list = ArrayList<Any>()
        if (cursor.moveToFirst()) {
            do {
                val trainingDay = TrainingDay(cursor.getInt(
                    cursor.getColumnIndex(Constants.COLUMN_TRAINING_ID)),
                    cursor.getString(cursor.getColumnIndex(Constants.COLUMN_TRAINING_DAY)),
                    cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_TRAINING_TIME)),
                    cursor.getString(cursor.getColumnIndex(Constants.COLUMN_TRAINING_GROUP)),
                    cursor.getInt(cursor.getColumnIndex(Constants.COLUM_FOREIGN_KEY_TRAINING_ROUTINE_ID)))
                list.add(trainingDay)
            }while (cursor.moveToNext())
        }
        return list
    }
}