package com.manuelbacallado.gymprogress.presenter

import android.content.Intent
import android.widget.Toast
import com.manuelbacallado.gymprogress.activities.InsertRoutineActivity
import com.manuelbacallado.gymprogress.activities.RoutineActivity
import com.manuelbacallado.gymprogress.activities.TrainingDayActivity
import com.manuelbacallado.gymprogress.db.dao.RoutineDAO
import com.manuelbacallado.gymprogress.models.Routine
import com.manuelbacallado.gymprogress.utils.Constants

class RoutinePresenter(var routineView: RoutineActivity) {

    private val list: ArrayList<Routine> by lazy { refreshData() }
    private lateinit var db : RoutineDAO

    fun initDatabase() {
        db = RoutineDAO(routineView)
    }

    fun clickItem(position: Int) {
        Toast.makeText(routineView.applicationContext, "Mostrando routine id: ${list.get(position).routineId}", Toast.LENGTH_LONG).show()
        val intent = Intent(routineView.applicationContext, TrainingDayActivity::class.java)
        intent.putExtra(Constants.ROUTINE_ID, list.get(position).routineId)
        routineView.startActivity(intent)
    }

    fun editItem(longClickItemPosition: Int){
        Toast.makeText(routineView.applicationContext, "Mostrando Item para editar: ${list.get(longClickItemPosition).routineId}", Toast.LENGTH_LONG).show()
        val intent = Intent(routineView.applicationContext, InsertRoutineActivity::class.java)
        intent.putExtra(Constants.LOAD_ROUTINE_BOOLEAN, true)
        intent.putExtra(Constants.ROUTINE, list.get(longClickItemPosition))
        routineView.startActivity(intent)
    }

    fun deleteItem(longClickItemPosition: Int) {
        Toast.makeText(routineView.applicationContext, "Mostrando Item para borrar: ${list.get(longClickItemPosition).name}", Toast.LENGTH_LONG).show()
        db.deleteElement(list.get(longClickItemPosition))
        list.remove(list.get(longClickItemPosition))
    }

    private fun refreshData(): ArrayList<Routine> {
        return db.getAllElements(0) as ArrayList<Routine>
    }

    fun getItemList() : ArrayList<Routine> {
        return list
    }
}