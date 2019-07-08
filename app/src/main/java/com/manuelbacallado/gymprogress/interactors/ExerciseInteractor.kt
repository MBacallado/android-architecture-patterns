package com.manuelbacallado.gymprogress.interactors

import android.content.Context
import com.manuelbacallado.gymprogress.db.dao.ExerciseDAO
import com.manuelbacallado.gymprogress.models.Exercise

class ExerciseInteractor {

    private val list: ArrayList<Exercise> by lazy { refreshData() }
    private lateinit var db : ExerciseDAO
    private var parentId: Int = 0

    fun initDatabase (context: Context){
        db = ExerciseDAO(context)
    }

    fun setParentId(parentId: Int) {
        this.parentId = parentId
    }

    fun getItem(longClickItemPosition: Int): Any {
        return list.get(longClickItemPosition)
    }

    fun getItemList() : List<Any>? {
        return list
    }

    private fun refreshData(): ArrayList<Exercise> {
        return db.getAllElements(parentId) as ArrayList<Exercise>
    }

    fun deleteItem(longClickItemPosition: Int) {
        db.deleteElement(list.get(longClickItemPosition))
        list.remove(list.get(longClickItemPosition))
    }
}