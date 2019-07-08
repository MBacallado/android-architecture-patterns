package com.manuelbacallado.gymprogress.presenters

import android.content.Context
import com.manuelbacallado.gymprogress.interactors.RoutineInteractor

class RoutinePresenter {

    private var routineInteractor = RoutineInteractor();

    fun init(context: Context) {
        routineInteractor.initDatabase(context)
    }

    fun getItem(longClickItemPosition: Int): Any? {
        return routineInteractor.getItem(longClickItemPosition)
    }

    fun getItems() : List<Any>? {
        return routineInteractor.getRoutineList()
    }

    fun deleteItem(longClickItemPosition: Int) {
        routineInteractor.deleteItem(longClickItemPosition)
    }

}