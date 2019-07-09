package com.manuelbacallado.gymprogress.presenters

import android.content.Context
import com.manuelbacallado.gymprogress.interactors.InsertRoutineInteractor
import com.manuelbacallado.gymprogress.models.Routine

class InsertRoutinePresenter {

    private var insertRoutineInteractor = InsertRoutineInteractor();

    fun init(context: Context) {
        insertRoutineInteractor.initDatabase(context)
    }

    fun saveData(routineText: String, startDateText: String, finishDateText: String, spinnerDay: Int,
                 spinnerTypes: String) {
        var routineAux = Routine(0, routineText, startDateText, finishDateText, spinnerDay, spinnerTypes)
        insertRoutineInteractor.saveData(routineAux)
    }

    fun editData(routineText: String, startDateText: String, finishDateText: String, spinnerDay: Int,
                 spinnerTypes: String, id: Int){
        var routineAux = Routine(id, routineText, startDateText, finishDateText, spinnerDay, spinnerTypes)
        insertRoutineInteractor.editData(routineAux)
    }
}