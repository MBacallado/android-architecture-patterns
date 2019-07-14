package com.manuelbacallado.gymprogress.presenters

import android.content.Context
import com.manuelbacallado.gymprogress.activities.InsertTrainingDayActivity
import com.manuelbacallado.gymprogress.interactors.InsertTrainingInteractor
import com.manuelbacallado.gymprogress.models.TrainingDay
import com.manuelbacallado.gymprogress.routers.InsertTrainingRouter

class InsertTrainingPresenter(insertTrainingDayActivity: InsertTrainingDayActivity) {

    private var insertTrainingInteractor = InsertTrainingInteractor();
    private var insertTrainingRouter = InsertTrainingRouter(insertTrainingDayActivity)

    fun init(context: Context) {
        insertTrainingInteractor.initDatabase(context)
        insertTrainingRouter.initData()
    }

    fun saveData(spinnerTrainingDay: String, timeText: Int, groupText: String) {
        var trainingDayAux = TrainingDay(0, spinnerTrainingDay, timeText, groupText, insertTrainingRouter.parentId)
        insertTrainingInteractor.saveData(trainingDayAux)
    }

    fun editData(spinnerTrainingDay: String, timeText: Int, groupText: String) {
        var trainingDayAux = TrainingDay(insertTrainingRouter.trainingDay.trainingDayId, spinnerTrainingDay, timeText, groupText, insertTrainingRouter.parentId)
        insertTrainingInteractor.editData(trainingDayAux)
    }

    fun getRouter() = insertTrainingRouter

    fun goToCreate() {
        insertTrainingRouter.goToCreate()
    }
}