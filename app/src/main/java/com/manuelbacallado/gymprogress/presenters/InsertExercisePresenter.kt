package com.manuelbacallado.gymprogress.presenters

import android.content.Context
import com.manuelbacallado.gymprogress.activities.InsertExerciseActivity
import com.manuelbacallado.gymprogress.interactors.InsertExerciseInteractor
import com.manuelbacallado.gymprogress.models.Exercise
import com.manuelbacallado.gymprogress.routers.InsertExerciseRouter

class InsertExercisePresenter(insertExerciseActivity: InsertExerciseActivity) {

    private var insertExerciseInteractor = InsertExerciseInteractor();
    private var insertExerciseRouter = InsertExerciseRouter(insertExerciseActivity)

    fun init(context: Context) {
        insertExerciseInteractor.initDatabase(context)
        insertExerciseRouter.initData()
    }

    fun saveData(exerciseName: String, initialWeight: Int, finalWeight: Int) {
        var exerciseAux = Exercise(0, exerciseName, initialWeight, finalWeight, insertExerciseRouter.parentId)
        insertExerciseInteractor.saveData(exerciseAux)
    }

    fun editData(exerciseName: String, initialWeight: Int, finalWeight: Int) {
        var exerciseAux = Exercise(insertExerciseRouter.exercise.exerciseId, exerciseName, initialWeight, finalWeight, insertExerciseRouter.parentId)
        insertExerciseInteractor.editData(exerciseAux)
    }

    fun getRouter() = insertExerciseRouter

    fun goToCreate() {
        insertExerciseRouter.goToCreate()
    }
}