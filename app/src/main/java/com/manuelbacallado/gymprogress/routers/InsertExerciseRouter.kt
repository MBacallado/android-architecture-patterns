package com.manuelbacallado.gymprogress.routers

import android.content.Intent
import com.manuelbacallado.gymprogress.activities.*
import com.manuelbacallado.gymprogress.interfaces.GoToCreate
import com.manuelbacallado.gymprogress.interfaces.InitData
import com.manuelbacallado.gymprogress.models.Exercise
import com.manuelbacallado.gymprogress.utils.Constants

class InsertExerciseRouter(val insertExerciseView: InsertExerciseActivity) : GoToCreate, InitData {

    lateinit var exercise: Exercise
    var load: Boolean = false
    var parentId : Int = 0

    override fun goToCreate() {
        val intent = Intent(insertExerciseView.applicationContext, ExerciseActivity::class.java)
        intent.putExtra(Constants.TRAINING_ID, parentId)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        insertExerciseView.startActivity(intent)
    }

    override fun initData() {
        if (insertExerciseView.intent.extras != null) {
            parentId = insertExerciseView.intent.extras.getInt(Constants.TRAINING_ID)
            load = insertExerciseView.intent.extras.getBoolean(Constants.LOAD_EXERCISE_BOOLEAN)
            if (load != null && load){
                exercise = insertExerciseView.intent.getParcelableExtra<Exercise>(Constants.EXERCISE)
            }
        }
    }
}