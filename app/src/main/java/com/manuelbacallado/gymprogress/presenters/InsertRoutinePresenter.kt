package com.manuelbacallado.gymprogress.presenters

import android.content.Context
import com.manuelbacallado.gymprogress.activities.InsertRoutineActivity
import com.manuelbacallado.gymprogress.interactors.InsertRoutineInteractor
import com.manuelbacallado.gymprogress.models.Routine
import com.manuelbacallado.gymprogress.routers.InsertRoutineRouter

class InsertRoutinePresenter(insertRoutineActivity: InsertRoutineActivity) {

    private var insertRoutineInteractor = InsertRoutineInteractor();
    private var insertRoutineRouter = InsertRoutineRouter(insertRoutineActivity)

    fun init(context: Context) {
        insertRoutineInteractor.initDatabase(context)
        insertRoutineRouter.initData()
    }

    fun saveData(routineText: String, startDateText: String, finishDateText: String, spinnerDay: Int,
                 spinnerTypes: String) {
        var routineAux = Routine(0, routineText, startDateText, finishDateText, spinnerDay, spinnerTypes)
        insertRoutineInteractor.saveData(routineAux)
    }

    fun editData(routineText: String, startDateText: String, finishDateText: String, spinnerDay: Int,
                 spinnerTypes: String){
        var routineAux = Routine(insertRoutineRouter.routine.routineId, routineText, startDateText, finishDateText, spinnerDay, spinnerTypes)
        insertRoutineInteractor.editData(routineAux)
    }

    fun getRouter() = insertRoutineRouter

    fun goToCreate() {
        insertRoutineRouter.goToCreate()
    }
}