package com.manuelbacallado.gymprogress.viewmodels

import android.arch.lifecycle.ViewModel
import android.content.Context
import com.manuelbacallado.gymprogress.db.dao.TrainingDaysDAO
import com.manuelbacallado.gymprogress.models.TrainingDay
import io.reactivex.Observable

class TrainingViewModel() : ViewModel() {

    private val list: ArrayList<TrainingDay> by lazy { refreshData() }
    private lateinit var db : TrainingDaysDAO
    private var routineId : Int = 0

    fun setDBContext(context: Context) {
        db = TrainingDaysDAO(context)
    }

    fun setId(routineId: Int) {
        this.routineId = routineId
    }

    fun getTrainingDays() : Observable<ArrayList<TrainingDay>> {
        return Observable.just(list)
    }

    fun editItem(position: Int) : TrainingDay {
        return list.get(position)
    }

    fun deleteItem(position: Int) {
        db.deleteElement(list.get(position))
        list.remove(list.get(position))
    }

    private fun refreshData(): ArrayList<TrainingDay> {
        return db.getAllElements(routineId) as ArrayList<TrainingDay>
    }
}