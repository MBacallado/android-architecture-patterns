package com.manuelbacallado.gymprogress.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.manuelbacallado.gymprogress.listener.IDatabaseFunctions
import com.manuelbacallado.gymprogress.models.Routine

class RoutineDBOpenHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME,
        null, DATABASE_VERSION), IDatabaseFunctions {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_ROUTINE_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COLUMN_NAME + " TEXT NOT NULL, " + COLUMN_START_DATE + " TEXT NOT NULL, " +
                COLUMN_FINISH_DATE + " TEXT NOT NULL, " + COLUMN_TRAINING_DAYS + " INTEGER NOT NULL, " +
                COLUMN_TRAINING_TYPES + " TEXT NOT NULL " + ")");
        db.execSQL(CREATE_ROUTINE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    override fun addElement(obj: Any) {
        val routine = obj as Routine
        val values = ContentValues()
        values.put(COLUMN_NAME, routine.name)
        values.put(COLUMN_START_DATE, routine.startDate.toString())
        values.put(COLUMN_FINISH_DATE, routine.finishDate.toString())
        values.put(COLUMN_TRAINING_DAYS, routine.trainingDays)
        values.put(COLUMN_TRAINING_TYPES, routine.trainingTypes)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    override fun editElement(obj: Any) {
        val routine = obj as Routine
        val values = ContentValues()
        values.put(COLUMN_NAME, routine.name)
        values.put(COLUMN_START_DATE, routine.startDate.toString())
        values.put(COLUMN_FINISH_DATE, routine.finishDate.toString())
        values.put(COLUMN_TRAINING_DAYS, routine.trainingDays)
        values.put(COLUMN_TRAINING_TYPES, routine.trainingTypes)
        val db = this.writableDatabase
        db.update(TABLE_NAME, values,COLUMN_ID + " = ?", arrayOf(routine.routineId.toString()));
        db.close()
    }

    override fun deleteElement(obj: Any) {
        val routine = obj as Routine
        val db = this.writableDatabase
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", arrayOf(routine.routineId.toString()));
    }

    override fun getAllElements(obj: Any): List<Any>? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val list = ArrayList<Any>()
        if (cursor.moveToFirst()) {
            do {
                val routine = Routine(cursor.getInt(
                    cursor.getColumnIndex(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_START_DATE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_FINISH_DATE)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_TRAINING_DAYS)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_TRAINING_TYPES)))
                list.add(routine)
            }while (cursor.moveToNext())
        }
        return list
    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "gymprogress.db"
        val TABLE_NAME = "routine"
        val COLUMN_ID = "_id"
        val COLUMN_NAME = "name"
        val COLUMN_START_DATE = "startDate"
        val COLUMN_FINISH_DATE = "finishDate"
        val COLUMN_TRAINING_DAYS = "trainingDays"
        val COLUMN_TRAINING_TYPES = "trainingType"
    }
}