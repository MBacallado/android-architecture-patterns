package com.manuelbacallado.gymprogress.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.manuelbacallado.gymprogress.listener.IDatabaseFunctions
import com.manuelbacallado.gymprogress.models.TrainingDay

class TrainingDaysDBOpenHelper(context: Context,
                               factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME,
        factory, DATABASE_VERSION), IDatabaseFunctions {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TRAINING_DAY_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DAY + " TEXT NOT NULL, " + COLUMN_TIME + " INTEGER NOT NULL, " + COLUMN_GROUP + " TEXT NOT NULL, " +
                COLUM_FOREIGN_KEY_ROUTINE_ID + " INTEGER NOT NULL, " +
                " FOREIGN KEY (" + COLUM_FOREIGN_KEY_ROUTINE_ID + ") REFERENCES routine(_id)");
        db.execSQL(CREATE_TRAINING_DAY_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    override fun addElement(obj: Any) {
        val trainingDay = obj as TrainingDay
        val values = ContentValues()
        values.put(COLUMN_DAY, trainingDay.day)
        values.put(COLUMN_TIME, trainingDay.timeAmount)
        values.put(COLUMN_GROUP, trainingDay.group)
        values.put(COLUM_FOREIGN_KEY_ROUTINE_ID, trainingDay.routineId)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    override fun editElement(obj: Any) {
        val trainingDay = obj as TrainingDay
        val values = ContentValues()
        values.put(COLUMN_DAY, trainingDay.day)
        values.put(COLUMN_TIME, trainingDay.timeAmount)
        values.put(COLUMN_GROUP, trainingDay.group)
        val db = this.writableDatabase
        db.update(TABLE_NAME, values,COLUMN_ID + " = ?", arrayOf(trainingDay.trainingDayId.toString()));
        db.close()
    }

    override fun deleteElement(obj: Any) {
        val trainingDay = obj as TrainingDay
        val db = this.writableDatabase
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", arrayOf(trainingDay.trainingDayId.toString()));
    }

    override fun getAllElements(obj: Any): Cursor? {
        val trainingDay = obj as TrainingDay
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME WHERE routineId = ?", arrayOf(trainingDay.routineId.toString()))
    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "gymprogress.db"
        val TABLE_NAME = "trainingDay"
        val COLUMN_ID = "_id"
        val COLUMN_DAY = "day"
        val COLUMN_TIME = "timeAmount"
        val COLUMN_GROUP = "group"
        val COLUM_FOREIGN_KEY_ROUTINE_ID = "routineId"
    }
}