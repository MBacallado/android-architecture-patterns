package com.manuelbacallado.gymprogress.utils

class Constants {

    companion object {
        //ROUTINE TABLE
        val DATABASE_NAME = "gymprogress.db"
        val DATABASE_VERSION = 1
        val TABLE_ROUTINE_NAME = "routine"
        val COLUMN_ROUTINE_ID = "_id"
        val COLUMN_ROUTINE_NAME = "name"
        val COLUMN_ROUTINE_START_DATE = "startDate"
        val COLUMN_ROUTINE_FINISH_DATE = "finishDate"
        val COLUMN_ROUTINE_TRAINING_DAYS = "trainingDays"
        val COLUMN_ROUTINE_TRAINING_TYPES = "trainingType"
        //TRAINING DAY TABLE
        val TABLE_TRAINING_NAME = "training"
        val COLUMN_TRAINING_ID = "_id"
        val COLUMN_TRAINING_DAY = "day"
        val COLUMN_TRAINING_TIME = "timeAmount"
        val COLUMN_TRAINING_GROUP = "trainingGroup"
        val COLUM_FOREIGN_KEY_TRAINING_ROUTINE_ID = "routineId"

        //EXERCISE TABLE
        val TABLE_EXERCISE_NAME = "exercise"
        val COLUMN_EXERCISE_ID = "_id"
        val COLUMN_EXERCISE_NAME = "name"
        val COLUMN_EXERCISE_INITIALWEIGHT = "initialWeight"
        val COLUMN_EXERCISE_FINALWEIGHT = "finalWeight"
        val COLUMN_FOREIGN_KEY_EXERCISE_TRAINING_ID = "trainingId"

        //CONSTANTS
        val ROUTINE = "routine"
        val TRAININGDAY = "trainingDay"
        val EXERCISE = "exercise"
        val ROUTINE_ID = "routineId"
        val TRAINING_ID = "trainingId"
        val LOAD_ROUTINE_BOOLEAN = "loadRoutine"
        val LOAD_TRAINING_BOOLEAN = "loadTraining"
        val LOAD_EXERCISE_BOOLEAN = "loadExercise"
    }
}