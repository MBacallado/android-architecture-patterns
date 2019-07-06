package com.manuelbacallado.gymprogress.presenter

import android.content.Intent
import com.manuelbacallado.gymprogress.activities.*
import com.manuelbacallado.gymprogress.db.dao.TrainingDaysDAO
import com.manuelbacallado.gymprogress.interfaces.InitDatabase
import com.manuelbacallado.gymprogress.interfaces.LoadIntentData
import com.manuelbacallado.gymprogress.interfaces.PresenterFunctions
import com.manuelbacallado.gymprogress.models.TrainingDay
import com.manuelbacallado.gymprogress.utils.Constants

class TrainingPresenter(var trainingView: TrainingDayActivity) : InitDatabase, LoadIntentData ,PresenterFunctions {

    private val list: ArrayList<TrainingDay> by lazy { refreshData() }
    private lateinit var db : TrainingDaysDAO
    private var routineId : Int = 0

    override fun initDatabase() {
        db = TrainingDaysDAO(trainingView)
    }

    override fun addItem() {
        val intent = Intent(trainingView.applicationContext, InsertTrainingDayActivity::class.java)
        intent.putExtra(Constants.LOAD_TRAINING_BOOLEAN, false)
        intent.putExtra(Constants.ROUTINE_ID, routineId)
        trainingView.startActivity(intent)
    }

    override fun loadIntentData() {
        if (trainingView.intent.extras != null) {
            routineId = trainingView.intent.extras.getInt(Constants.ROUTINE_ID)
        }
    }

    override fun clickItem(position: Int) {
        val intent = Intent(trainingView.applicationContext, ExerciseActivity::class.java)
        intent.putExtra(Constants.TRAINING_ID, list.get(position).trainingDayId)
        trainingView.startActivity(intent)
    }

    override fun editItem(position: Int){
        val intent = Intent(trainingView.applicationContext, InsertTrainingDayActivity::class.java)
        intent.putExtra(Constants.LOAD_TRAINING_BOOLEAN, true)
        intent.putExtra(Constants.TRAININGDAY, list.get(position))
        intent.putExtra(Constants.ROUTINE_ID, routineId)
        trainingView.startActivity(intent)
    }

    override fun deleteItem(position: Int) {
        db.deleteElement(list.get(position))
        list.remove(list.get(position))
    }

    private fun refreshData(): ArrayList<TrainingDay> {
        return db.getAllElements(routineId) as ArrayList<TrainingDay>
    }

    fun getItemList() : ArrayList<TrainingDay> {
        return list
    }
}