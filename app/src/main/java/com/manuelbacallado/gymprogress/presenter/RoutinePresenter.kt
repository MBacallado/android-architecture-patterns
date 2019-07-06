package com.manuelbacallado.gymprogress.presenter

import android.content.Intent
import com.manuelbacallado.gymprogress.activities.InsertRoutineActivity
import com.manuelbacallado.gymprogress.activities.RoutineActivity
import com.manuelbacallado.gymprogress.activities.TrainingDayActivity
import com.manuelbacallado.gymprogress.db.dao.RoutineDAO
import com.manuelbacallado.gymprogress.interfaces.InitDatabase
import com.manuelbacallado.gymprogress.interfaces.PresenterFunctions
import com.manuelbacallado.gymprogress.models.Routine
import com.manuelbacallado.gymprogress.utils.Constants

class RoutinePresenter(var routineView: RoutineActivity) : InitDatabase, PresenterFunctions {

    private val list: ArrayList<Routine> by lazy { refreshData() }
    private lateinit var db : RoutineDAO

    override fun initDatabase() {
        db = RoutineDAO(routineView)
    }

    override fun addItem() {
        val intent = Intent(routineView.applicationContext, InsertRoutineActivity::class.java)
        intent.putExtra(Constants.LOAD_ROUTINE_BOOLEAN, false)
        routineView.startActivity(intent)
    }

    override fun clickItem(position: Int) {
        val intent = Intent(routineView.applicationContext, TrainingDayActivity::class.java)
        intent.putExtra(Constants.ROUTINE_ID, list.get(position).routineId)
        routineView.startActivity(intent)
    }

    override fun editItem(position: Int){
        val intent = Intent(routineView.applicationContext, InsertRoutineActivity::class.java)
        intent.putExtra(Constants.LOAD_ROUTINE_BOOLEAN, true)
        intent.putExtra(Constants.ROUTINE, list.get(position))
        routineView.startActivity(intent)
    }

    override fun deleteItem(position: Int) {
        db.deleteElement(list.get(position))
        list.remove(list.get(position))
    }

    private fun refreshData(): ArrayList<Routine> {
        return db.getAllElements(0) as ArrayList<Routine>
    }

    fun getItemList() : ArrayList<Routine> {
        return list
    }
}