package com.manuelbacallado.gymprogress.interactors

import android.content.Context
import com.manuelbacallado.gymprogress.db.dao.TrainingDaysDAO
import com.manuelbacallado.gymprogress.models.TrainingDay

class TrainingInteractor {

    private val list: ArrayList<TrainingDay> by lazy { refreshData() }
    private lateinit var db : TrainingDaysDAO
    private var parentId: Int = 0

    fun initDatabase (context: Context){
        db = TrainingDaysDAO(context)
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

    private fun refreshData(): ArrayList<TrainingDay> {
        return db.getAllElements(parentId) as ArrayList<TrainingDay>
    }

    fun deleteItem(longClickItemPosition: Int) {
        db.deleteElement(list.get(longClickItemPosition))
        list.remove(list.get(longClickItemPosition))
    }
}