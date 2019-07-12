package com.manuelbacallado.gymprogress.presenters

import android.content.Context
import com.manuelbacallado.gymprogress.interactors.RoutineInteractor
import com.manuelbacallado.gymprogress.interfaces.Init
import com.manuelbacallado.gymprogress.interfaces.PresenterInteractorFunctions

class RoutinePresenter : PresenterInteractorFunctions, Init {

    private var routineInteractor = RoutineInteractor();

    override fun init(context: Context) {
        routineInteractor.init(context)
    }

    override fun getItem(longClickItemPosition: Int): Any? {
        return routineInteractor.getItem(longClickItemPosition)
    }

    override fun getItems() : List<Any>? {
        return routineInteractor.getItems()
    }

    override fun deleteItem(longClickItemPosition: Int) {
        routineInteractor.deleteItem(longClickItemPosition)
    }
}