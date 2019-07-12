package com.manuelbacallado.gymprogress.presenters

import android.content.Context
import com.manuelbacallado.gymprogress.interactors.TrainingInteractor
import com.manuelbacallado.gymprogress.interfaces.Init
import com.manuelbacallado.gymprogress.interfaces.ParentId
import com.manuelbacallado.gymprogress.interfaces.PresenterInteractorFunctions

class TrainingPresenter : PresenterInteractorFunctions, ParentId, Init {

    private var trainingInteractor = TrainingInteractor();

    override fun init(context: Context) {
        trainingInteractor.init(context)
    }

    override fun setParentId(parentId: Int) {
        trainingInteractor.setParentId(parentId)
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
}