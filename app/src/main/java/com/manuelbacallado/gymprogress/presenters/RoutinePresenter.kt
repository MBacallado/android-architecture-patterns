package com.manuelbacallado.gymprogress.presenters

import android.content.Context
import com.manuelbacallado.gymprogress.activities.RoutineActivity
import com.manuelbacallado.gymprogress.interactors.RoutineInteractor
import com.manuelbacallado.gymprogress.interfaces.Init
import com.manuelbacallado.gymprogress.interfaces.PresenterInteractorFunctions
import com.manuelbacallado.gymprogress.routers.RoutineRouter

class RoutinePresenter(routineActivity: RoutineActivity) : PresenterInteractorFunctions, Init {

    private var routineInteractor = RoutineInteractor();
    private var routineRouter = RoutineRouter(routineActivity)

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

    fun goToCreate() {
        routineRouter.goToCreate()
    }

    fun goToNextSection(routineId: Int) {
        routineRouter.goToNextSection(routineId)
    }

    fun goToEdit(item: Any?) {
        routineRouter.goToEdit(item)
    }
}