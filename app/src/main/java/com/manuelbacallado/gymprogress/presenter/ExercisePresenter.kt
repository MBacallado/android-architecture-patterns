package com.manuelbacallado.gymprogress.presenter

import android.content.Intent
import android.widget.Toast
import com.manuelbacallado.gymprogress.activities.*
import com.manuelbacallado.gymprogress.db.dao.ExerciseDAO
import com.manuelbacallado.gymprogress.interfaces.InitDatabase
import com.manuelbacallado.gymprogress.interfaces.LoadIntentData
import com.manuelbacallado.gymprogress.interfaces.PresenterFunctions
import com.manuelbacallado.gymprogress.models.Exercise
import com.manuelbacallado.gymprogress.utils.Constants

class ExercisePresenter(var exerciseView: ExerciseActivity) : InitDatabase, LoadIntentData, PresenterFunctions {

    private val list: ArrayList<Exercise> by lazy { refreshData() }
    private lateinit var db : ExerciseDAO
    private var trainingId : Int = 0

    override fun initDatabase() {
        db = ExerciseDAO(exerciseView)
    }

    override fun addItem() {
        val intent = Intent(exerciseView.applicationContext, InsertExerciseActivity::class.java)
        intent.putExtra(Constants.LOAD_TRAINING_BOOLEAN, false)
        intent.putExtra(Constants.TRAINING_ID, trainingId)
        exerciseView.startActivity(intent)
    }

    override fun loadIntentData() {
        if (exerciseView.intent.extras != null) {
            trainingId = exerciseView.intent.extras.getInt(Constants.TRAINING_ID)
        }
    }

    override fun clickItem(position: Int) {
        Toast.makeText(exerciseView.applicationContext, "Clic", Toast.LENGTH_LONG).show()
    }

    override fun editItem(position: Int){
        val intent = Intent(exerciseView.applicationContext, InsertExerciseActivity::class.java)
        intent.putExtra(Constants.LOAD_EXERCISE_BOOLEAN, true)
        intent.putExtra(Constants.EXERCISE, list.get(position))
        intent.putExtra(Constants.TRAINING_ID, trainingId)
        exerciseView.startActivity(intent)
    }

    override fun deleteItem(position: Int) {
        db.deleteElement(list.get(position))
        list.remove(list.get(position))
    }

    private fun refreshData(): ArrayList<Exercise> {
        return db.getAllElements(trainingId) as ArrayList<Exercise>
    }

    fun getItemList() : ArrayList<Exercise> {
        return list
    }
}