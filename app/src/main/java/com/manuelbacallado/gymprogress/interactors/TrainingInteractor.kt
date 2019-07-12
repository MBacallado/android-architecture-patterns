package com.manuelbacallado.gymprogress.interactors

import android.content.Context
import com.manuelbacallado.gymprogress.db.dao.TrainingDaysDAO
import com.manuelbacallado.gymprogress.interfaces.Init
import com.manuelbacallado.gymprogress.interfaces.ParentId
import com.manuelbacallado.gymprogress.interfaces.PresenterInteractorFunctions
import com.manuelbacallado.gymprogress.models.TrainingDay

class TrainingInteractor : PresenterInteractorFunctions, ParentId, Init {

    private val list: ArrayList<TrainingDay> by lazy { refreshData() }
    private lateinit var db : TrainingDaysDAO
    private var parentId: Int = 0

    override fun init (context: Context){
        db = TrainingDaysDAO(context)
    }

    override fun setParentId(parentId: Int) {
        this.parentId = parentId
    }

    override fun getItem(longClickItemPosition: Int): Any {
        return list.get(longClickItemPosition)
    }

    override fun getItems() : List<Any>? {
        return list
    }

    private fun refreshData(): ArrayList<TrainingDay> {
        return db.getAllElements(parentId) as ArrayList<TrainingDay>
    }

    override fun deleteItem(longClickItemPosition: Int) {
        db.deleteElement(list.get(longClickItemPosition))
        list.remove(list.get(longClickItemPosition))
    }
}