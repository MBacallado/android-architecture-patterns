package com.manuelbacallado.gymprogress.presenters

import android.content.Context
import com.manuelbacallado.gymprogress.interactors.InsertTrainingInteractor
import com.manuelbacallado.gymprogress.models.TrainingDay

class InsertTrainingPresenter {

    private var insertTrainingInteractor = InsertTrainingInteractor();

    fun init(context: Context) {
        insertTrainingInteractor.initDatabase(context)
    }

    fun saveData(spinnerTrainingDay: String, timeText: Int, groupText: String, parentId: Int) {
        var trainingDayAux = TrainingDay(0, spinnerTrainingDay, timeText, groupText, parentId)
        insertTrainingInteractor.saveData(trainingDayAux)
    }

    fun editData(spinnerTrainingDay: String, timeText: Int, groupText: String, id: Int, parentId: Int) {
        var trainingDayAux = TrainingDay(id, spinnerTrainingDay, timeText, groupText, parentId)
        insertTrainingInteractor.editData(trainingDayAux)
    }
}