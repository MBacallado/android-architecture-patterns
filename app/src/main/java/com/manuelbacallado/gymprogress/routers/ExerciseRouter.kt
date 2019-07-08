package com.manuelbacallado.gymprogress.routers

import android.content.Intent
import com.manuelbacallado.gymprogress.activities.*
import com.manuelbacallado.gymprogress.models.Exercise
import com.manuelbacallado.gymprogress.utils.Constants

class ExerciseRouter(val exerciseView: ExerciseActivity) {

    var parentId : Int = 0

    fun goToCreate() {
        val intent = Intent(exerciseView.applicationContext, InsertExerciseActivity::class.java)
        intent.putExtra(Constants.LOAD_EXERCISE_BOOLEAN, false)
        intent.putExtra(Constants.TRAINING_ID, parentId)
        exerciseView.startActivity(intent)
    }

    fun goToNextSection(id: Int) {
    }

    fun goToEdit(item: Any?) {
        val intent = Intent(exerciseView.applicationContext, InsertExerciseActivity::class.java)
        intent.putExtra(Constants.LOAD_EXERCISE_BOOLEAN, true)
        intent.putExtra(Constants.EXERCISE, item as Exercise)
        intent.putExtra(Constants.TRAINING_ID, parentId)
        exerciseView.startActivity(intent)
    }

    fun initData() {
        if (exerciseView.intent.extras != null) {
            parentId = exerciseView.intent.extras.getInt(Constants.TRAINING_ID)
        }
    }
}