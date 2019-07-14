package com.manuelbacallado.gymprogress.interactors

import android.content.Context
import com.manuelbacallado.gymprogress.db.dao.RoutineDAO
import com.manuelbacallado.gymprogress.interfaces.Init
import com.manuelbacallado.gymprogress.interfaces.PresenterInteractorFunctions
import com.manuelbacallado.gymprogress.models.Routine

class RoutineInteractor() : PresenterInteractorFunctions, Init{

    private val list: ArrayList<Routine> by lazy { refreshData() }
    private lateinit var db : RoutineDAO

    override fun init (context: Context){
        db = RoutineDAO(context)
    }

    override fun getItem(longClickItemPosition: Int): Any {
        return list.get(longClickItemPosition)
    }

    override fun getItems() : List<Any>? {
        return list
    }

    private fun refreshData(): ArrayList<Routine> {
        return db.getAllElements(0) as ArrayList<Routine>
    }

    override fun deleteItem(longClickItemPosition: Int) {
        db.deleteElement(list.get(longClickItemPosition))
        list.remove(list.get(longClickItemPosition))
    }
}