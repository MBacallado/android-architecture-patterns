package com.manuelbacallado.gymprogress.presenters

import android.content.Context
import com.manuelbacallado.gymprogress.interactors.ExerciseInteractor
import com.manuelbacallado.gymprogress.interfaces.Init
import com.manuelbacallado.gymprogress.interfaces.ParentId
import com.manuelbacallado.gymprogress.interfaces.PresenterInteractorFunctions

class ExercisePresenter : PresenterInteractorFunctions, ParentId, Init {

    private var exerciseInteractor = ExerciseInteractor();

    override fun init(context: Context) {
        exerciseInteractor.init(context)
    }

    override fun setParentId(parentId: Int) {
        exerciseInteractor.setParentId(parentId)
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
}