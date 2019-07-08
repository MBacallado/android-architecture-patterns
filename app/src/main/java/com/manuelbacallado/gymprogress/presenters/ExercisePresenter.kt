package com.manuelbacallado.gymprogress.presenters

import android.content.Context
import com.manuelbacallado.gymprogress.interactors.ExerciseInteractor

class ExercisePresenter {

    private var exerciseInteractor = ExerciseInteractor();

    fun init(context: Context) {
        exerciseInteractor.initDatabase(context)
    }

    fun setParentId(parentId: Int) {
        exerciseInteractor.setParentId(parentId)
    }

    fun getItem(longClickItemPosition: Int): Any? {
        return exerciseInteractor.getItem(longClickItemPosition)
    }

    fun getItems() : List<Any>? {
        return exerciseInteractor.getItemList()
    }

    fun deleteItem(longClickItemPosition: Int) {
        exerciseInteractor.deleteItem(longClickItemPosition)
    }
}