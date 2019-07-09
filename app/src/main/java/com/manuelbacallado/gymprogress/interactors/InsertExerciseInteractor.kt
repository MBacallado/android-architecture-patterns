package com.manuelbacallado.gymprogress.interactors

import android.content.Context
import com.manuelbacallado.gymprogress.db.dao.ExerciseDAO
import com.manuelbacallado.gymprogress.models.Exercise

class InsertExerciseInteractor {

    private lateinit var db : ExerciseDAO

    fun initDatabase (context: Context){
        db = ExerciseDAO(context)
    }

    fun saveData(item: Any) {
        db.addElement(item as Exercise)
    }

    fun editData(item: Any) {
        db.editElement(item as Exercise)
    }
}