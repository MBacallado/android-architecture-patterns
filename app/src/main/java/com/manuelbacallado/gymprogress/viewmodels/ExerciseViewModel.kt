package com.manuelbacallado.gymprogress.viewmodels

import android.arch.lifecycle.ViewModel
import android.content.Context
import com.manuelbacallado.gymprogress.db.dao.ExerciseDAO
import com.manuelbacallado.gymprogress.db.dao.TrainingDaysDAO
import com.manuelbacallado.gymprogress.models.Exercise
import com.manuelbacallado.gymprogress.models.TrainingDay
import io.reactivex.Observable

class ExerciseViewModel() : ViewModel() {

    private val list: ArrayList<Exercise> by lazy { refreshData() }
    private lateinit var db : ExerciseDAO
    private var trainingId : Int = 0

    fun setDBContext(context: Context) {
        db = ExerciseDAO(context)
    }

    fun setId(trainingId: Int) {
        this.trainingId = trainingId
    }

    fun getExercises() : Observable<ArrayList<Exercise>> {
        return Observable.just(list)
    }

    fun editItem(position: Int) : Exercise {
        return list.get(position)
    }

    fun deleteItem(position: Int) {
        db.deleteElement(list.get(position))
        list.remove(list.get(position))
    }

    private fun refreshData(): ArrayList<Exercise> {
        return db.getAllElements(trainingId) as ArrayList<Exercise>
    }
}