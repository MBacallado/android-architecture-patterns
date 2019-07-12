package com.manuelbacallado.gymprogress.routers

import android.content.Intent
import com.manuelbacallado.gymprogress.activities.*
import com.manuelbacallado.gymprogress.interfaces.GoToCreate
import com.manuelbacallado.gymprogress.interfaces.GoToOthersScreens
import com.manuelbacallado.gymprogress.interfaces.InitData
import com.manuelbacallado.gymprogress.models.Exercise
import com.manuelbacallado.gymprogress.utils.Constants

class ExerciseRouter(val exerciseView: ExerciseActivity) : GoToCreate, GoToOthersScreens, InitData {

    var parentId : Int = 0

    override fun goToCreate() {
        val intent = Intent(exerciseView.applicationContext, InsertExerciseActivity::class.java)
        intent.putExtra(Constants.LOAD_EXERCISE_BOOLEAN, false)
        intent.putExtra(Constants.TRAINING_ID, parentId)
        exerciseView.startActivity(intent)
    }

    override fun goToNextSection(id: Int) {
    }

    override fun goToEdit(item: Any?) {
        val intent = Intent(exerciseView.applicationContext, InsertExerciseActivity::class.java)
        intent.putExtra(Constants.LOAD_EXERCISE_BOOLEAN, true)
        intent.putExtra(Constants.EXERCISE, item as Exercise)
        intent.putExtra(Constants.TRAINING_ID, parentId)
        exerciseView.startActivity(intent)
    }

    override fun initData() {
        if (exerciseView.intent.extras != null) {
            parentId = exerciseView.intent.extras.getInt(Constants.TRAINING_ID)
        }
    }
}