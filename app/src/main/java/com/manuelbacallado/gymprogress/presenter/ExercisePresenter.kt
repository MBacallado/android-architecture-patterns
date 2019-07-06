package com.manuelbacallado.gymprogress.presenter

import android.content.Intent
import android.widget.Toast
import com.manuelbacallado.gymprogress.activities.*
import com.manuelbacallado.gymprogress.db.dao.ExerciseDAO
import com.manuelbacallado.gymprogress.models.Exercise
import com.manuelbacallado.gymprogress.utils.Constants

class ExercisePresenter(var exerciseView: ExerciseActivity) {

    private val list: ArrayList<Exercise> by lazy { refreshData() }
    private lateinit var db : ExerciseDAO
    private var trainingId : Int = 0

    fun initDatabase() {
        db = ExerciseDAO(exerciseView)
    }

    fun addItem() {
        val intent = Intent(exerciseView.applicationContext, InsertExerciseActivity::class.java)
        intent.putExtra(Constants.LOAD_TRAINING_BOOLEAN, false)
        intent.putExtra(Constants.TRAINING_ID, trainingId)
        exerciseView.startActivity(intent)
    }

    fun loadIntentData() {
        if (exerciseView.intent.extras != null) {
            trainingId = exerciseView.intent.extras.getInt(Constants.TRAINING_ID)
        }
    }

    fun clickItem(position: Int) {
        Toast.makeText(exerciseView.applicationContext, "Clic", Toast.LENGTH_LONG).show()
    }

    fun editItem(longClickItemPosition: Int){
        val intent = Intent(exerciseView.applicationContext, InsertExerciseActivity::class.java)
        intent.putExtra(Constants.LOAD_EXERCISE_BOOLEAN, true)
        intent.putExtra(Constants.EXERCISE, list.get(longClickItemPosition))
        intent.putExtra(Constants.TRAINING_ID, trainingId)
        exerciseView.startActivity(intent)
    }

    fun deleteItem(longClickItemPosition: Int) {
        db.deleteElement(list.get(longClickItemPosition))
        list.remove(list.get(longClickItemPosition))
    }

    private fun refreshData(): ArrayList<Exercise> {
        return db.getAllElements(trainingId) as ArrayList<Exercise>
    }

    fun getItemList() : ArrayList<Exercise> {
        return list
    }
}