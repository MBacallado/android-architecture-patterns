package com.manuelbacallado.gymprogress.presenters

import android.content.Context
import com.manuelbacallado.gymprogress.activities.ExerciseActivity
import com.manuelbacallado.gymprogress.interactors.ExerciseInteractor
import com.manuelbacallado.gymprogress.interfaces.Init
import com.manuelbacallado.gymprogress.interfaces.PresenterInteractorFunctions
import com.manuelbacallado.gymprogress.routers.ExerciseRouter

class ExercisePresenter(exerciseActivity: ExerciseActivity) : PresenterInteractorFunctions, Init {

    private var exerciseInteractor = ExerciseInteractor();
    private var exerciseRouter = ExerciseRouter(exerciseActivity)

    override fun init(context: Context) {
        exerciseInteractor.init(context)
        exerciseRouter.initData()
        this.setParentId()
    }

    fun setParentId() {
        exerciseInteractor.setParentId(exerciseRouter.parentId)
    }

    override fun getItem(longClickItemPosition: Int): Any? {
        return exerciseInteractor.getItem(longClickItemPosition)
    }

    override fun getItems() : List<Any>? {
        return exerciseInteractor.getItems()
    }

    override fun deleteItem(longClickItemPosition: Int) {
        exerciseInteractor.deleteItem(longClickItemPosition)
    }

    fun goToCreate() {
        exerciseRouter.goToCreate()
    }

    fun goToNextSection(exerciseId: Int) {
        exerciseRouter.goToNextSection(exerciseId)
    }

    fun goToEdit(item: Any?) {
        exerciseRouter.goToEdit(item)
    }
}