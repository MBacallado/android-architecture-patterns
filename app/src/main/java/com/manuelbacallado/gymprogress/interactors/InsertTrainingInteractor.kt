package com.manuelbacallado.gymprogress.interactors

import android.content.Context
import com.manuelbacallado.gymprogress.db.dao.TrainingDaysDAO
import com.manuelbacallado.gymprogress.models.TrainingDay

class InsertTrainingInteractor {

    private lateinit var db : TrainingDaysDAO

    fun initDatabase (context: Context){
        db = TrainingDaysDAO(context)
    }

    fun saveData(item: Any) {
        db.addElement(item as TrainingDay)
    }

    fun editData(item: Any) {
        db.editElement(item as TrainingDay)
    }
}