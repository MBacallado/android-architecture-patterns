package com.manuelbacallado.gymprogress.interactors

import android.content.Context
import com.manuelbacallado.gymprogress.db.dao.RoutineDAO
import com.manuelbacallado.gymprogress.models.Routine

class InsertRoutineInteractor {

    private lateinit var db : RoutineDAO

    fun initDatabase (context: Context){
        db = RoutineDAO(context)
    }

    fun saveData(item: Any) {
        db.addElement(item as Routine)
    }

    fun editData(item: Any) {
        db.editElement(item as Routine)
    }
}