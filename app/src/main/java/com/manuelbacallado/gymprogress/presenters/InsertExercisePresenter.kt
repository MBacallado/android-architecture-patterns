package com.manuelbacallado.gymprogress.presenters

import android.content.Context
import com.manuelbacallado.gymprogress.interactors.InsertExerciseInteractor
import com.manuelbacallado.gymprogress.models.Exercise

class InsertExercisePresenter {

    private var insertExerciseInteractor = InsertExerciseInteractor();

    fun init(context: Context) {
        insertExerciseInteractor.initDatabase(context)
    }

    fun saveData(exerciseName: String, initialWeight: Int, finalWeight: Int, parentId: Int) {
        var exerciseAux = Exercise(0, exerciseName, initialWeight, finalWeight, parentId)
        insertExerciseInteractor.saveData(exerciseAux)
    }

    fun editData(exerciseName: String, initialWeight: Int, finalWeight: Int, parentId: Int, id: Int) {
        var exerciseAux = Exercise(id, exerciseName, initialWeight, finalWeight, parentId)
        insertExerciseInteractor.editData(exerciseAux)
    }
}