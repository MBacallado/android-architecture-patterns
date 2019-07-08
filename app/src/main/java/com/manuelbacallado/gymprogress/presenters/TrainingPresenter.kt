package com.manuelbacallado.gymprogress.presenters

import android.content.Context
import com.manuelbacallado.gymprogress.interactors.TrainingInteractor

class TrainingPresenter {

    private var trainingInteractor = TrainingInteractor();

    fun init(context: Context) {
        trainingInteractor.initDatabase(context)
    }

    fun setParentId(parentId: Int) {
        trainingInteractor.setParentId(parentId)
    }

    fun getItem(longClickItemPosition: Int): Any? {
        return trainingInteractor.getItem(longClickItemPosition)
    }

    fun getItems() : List<Any>? {
        return trainingInteractor.getItemList()
    }

    fun deleteItem(longClickItemPosition: Int) {
        trainingInteractor.deleteItem(longClickItemPosition)
    }

}