package com.manuelbacallado.gymprogress.interactors

import android.content.Context
import com.manuelbacallado.gymprogress.db.dao.RoutineDAO
import com.manuelbacallado.gymprogress.models.Routine

class RoutineInteractor {

    private val list: ArrayList<Routine> by lazy { refreshData() }
    private lateinit var db : RoutineDAO

    fun initDatabase (context: Context){
        db = RoutineDAO(context)
    }

    fun getItem(longClickItemPosition: Int): Any {
        return list.get(longClickItemPosition)
    }

    fun getRoutineList() : List<Any>? {
        return list
    }

    private fun refreshData(): ArrayList<Routine> {
        return db.getAllElements(0) as ArrayList<Routine>
    }

    fun deleteItem(longClickItemPosition: Int) {
        db.deleteElement(list.get(longClickItemPosition))
        list.remove(list.get(longClickItemPosition))
    }


}