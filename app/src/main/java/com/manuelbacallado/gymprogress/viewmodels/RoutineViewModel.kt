package com.manuelbacallado.gymprogress.viewmodels

import android.arch.lifecycle.ViewModel
import android.content.Context
import com.manuelbacallado.gymprogress.db.dao.RoutineDAO
import com.manuelbacallado.gymprogress.models.Routine
import io.reactivex.Observable

class RoutineViewModel() : ViewModel() {

    private val list: ArrayList<Routine> by lazy { refreshData() }
    private lateinit var db : RoutineDAO

    fun setDBContext(context: Context) {
        db = RoutineDAO(context)
    }

    fun getRoutines() : Observable<ArrayList<Routine>> {
        return Observable.just(list)
    }

    fun editItem(position: Int) : Routine {
        return list.get(position)
    }

    fun deleteItem(position: Int) {
        db.deleteElement(list.get(position))
        list.remove(list.get(position))
    }

    private fun refreshData(): ArrayList<Routine> {
        return db.getAllElements(0) as ArrayList<Routine>
    }
}