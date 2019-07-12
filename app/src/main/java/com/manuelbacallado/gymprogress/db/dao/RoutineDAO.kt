package com.manuelbacallado.gymprogress.db.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.manuelbacallado.gymprogress.db.GymProgressDBOpenHelper
import com.manuelbacallado.gymprogress.interfaces.IDatabaseFunctions
import com.manuelbacallado.gymprogress.models.Routine
import com.manuelbacallado.gymprogress.utils.Constants
import java.sql.SQLException

class RoutineDAO : IDatabaseFunctions  {

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
        val routine = obj as Routine
        val values = ContentValues()
        values.put(Constants.COLUMN_ROUTINE_NAME, routine.name)
        values.put(Constants.COLUMN_ROUTINE_START_DATE, routine.startDate.toString())
        values.put(Constants.COLUMN_ROUTINE_FINISH_DATE, routine.finishDate.toString())
        values.put(Constants.COLUMN_ROUTINE_TRAINING_DAYS, routine.trainingDays)
        values.put(Constants.COLUMN_ROUTINE_TRAINING_TYPES, routine.trainingTypes)
        database.insert(Constants.TABLE_ROUTINE_NAME, null, values)
    }

    override fun editElement(obj: Any) {
        val routine = obj as Routine
        val values = ContentValues()
        values.put(Constants.COLUMN_ROUTINE_NAME, routine.name)
        values.put(Constants.COLUMN_ROUTINE_START_DATE, routine.startDate.toString())
        values.put(Constants.COLUMN_ROUTINE_FINISH_DATE, routine.finishDate.toString())
        values.put(Constants.COLUMN_ROUTINE_TRAINING_DAYS, routine.trainingDays)
        values.put(Constants.COLUMN_ROUTINE_TRAINING_TYPES, routine.trainingTypes)
        database.update(
            Constants.TABLE_ROUTINE_NAME, values,
            Constants.COLUMN_ROUTINE_ID + " = ?", arrayOf(routine.routineId.toString()));
    }

    override fun deleteElement(obj: Any) {
        val routine = obj as Routine
        database.delete(Constants.TABLE_ROUTINE_NAME, Constants.COLUMN_ROUTINE_ID + " = ?", arrayOf(routine.routineId.toString()));
    }

    override fun getAllElements(id: Int): List<Any>? {
        val cursor = database.rawQuery("SELECT * FROM ${Constants.TABLE_ROUTINE_NAME}", null)
        val list = ArrayList<Any>()
        if (cursor.moveToFirst()) {
            do {
                val routine = Routine(cursor.getInt(
                    cursor.getColumnIndex(Constants.COLUMN_ROUTINE_ID)),
                    cursor.getString(cursor.getColumnIndex(Constants.COLUMN_ROUTINE_NAME)),
                    cursor.getString(cursor.getColumnIndex(Constants.COLUMN_ROUTINE_START_DATE)),
                    cursor.getString(cursor.getColumnIndex(Constants.COLUMN_ROUTINE_FINISH_DATE)),
                    cursor.getInt(cursor.getColumnIndex(Constants.COLUMN_ROUTINE_TRAINING_DAYS)),
                    cursor.getString(cursor.getColumnIndex(Constants.COLUMN_ROUTINE_TRAINING_TYPES)))
                list.add(routine)
            }while (cursor.moveToNext())
        }
        return list
    }
}