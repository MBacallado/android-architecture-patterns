package com.manuelbacallado.gymprogress.presenters

import android.content.Context
import com.manuelbacallado.gymprogress.activities.TrainingDayActivity
import com.manuelbacallado.gymprogress.interactors.TrainingInteractor
import com.manuelbacallado.gymprogress.interfaces.Init
import com.manuelbacallado.gymprogress.interfaces.PresenterInteractorFunctions
import com.manuelbacallado.gymprogress.routers.TrainingRouter

class TrainingPresenter(trainingDayActivity: TrainingDayActivity) : PresenterInteractorFunctions, Init {

    private var trainingInteractor = TrainingInteractor();
    private var trainingRouter = TrainingRouter(trainingDayActivity)

    override fun init(context: Context) {
        trainingInteractor.init(context)
        trainingRouter.initData()
        this.setParentId()
    }

    fun setParentId() {
        trainingInteractor.setParentId(trainingRouter.parentId)
    }

    override fun getItem(longClickItemPosition: Int): Any? {
        return trainingInteractor.getItem(longClickItemPosition)
    }

    override fun getItems() : List<Any>? {
        return trainingInteractor.getItems()
    }

    override fun deleteItem(longClickItemPosition: Int) {
        trainingInteractor.deleteItem(longClickItemPosition)
    }

    fun goToCreate() {
        trainingRouter.goToCreate()
    }

    fun goToNextSection(trainingDayId: Int) {
        trainingRouter.goToNextSection(trainingDayId)
    }

    fun goToEdit(item: Any?) {
        trainingRouter.goToEdit(item)
    }
}